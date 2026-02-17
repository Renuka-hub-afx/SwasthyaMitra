package com.example.swasthyamitra.step

import android.location.Location
import android.util.Log
import com.google.android.gms.location.DetectedActivity
import kotlin.math.abs

/**
 * GPS-enhanced step validation engine.
 *
 * Five cross-validation layers that compare sensor-detected steps
 * against GPS location data in real-time to improve accuracy:
 *
 * 1. GPS Distance Validation   – discard steps when GPS shows no movement
 * 2. Dynamic Stride Calibration – compute stride length from GPS ÷ steps
 * 3. Speed-Based Cadence Check  – expected cadence from GPS speed
 * 4. Activity-Aware Filtering   – suppress steps when in vehicle / still
 * 5. Anomaly Detection          – spike / impossible speed filtering
 */
class StepGpsValidator {

    companion object {
        private const val TAG = "StepGpsValidator"

        // Layer 1 – GPS Distance Validation
        private const val GPS_DISTANCE_RATIO_THRESHOLD = 0.40  // GPS dist must be >= 40% of step-estimated dist
        private const val MIN_STEPS_FOR_GPS_CHECK = 20         // need enough steps before comparing

        // Layer 2 – Stride Calibration
        private const val DEFAULT_STRIDE_LENGTH_M = 0.72       // average stride (will be overridden by user height)
        private const val MIN_STRIDE_M = 0.3
        private const val MAX_STRIDE_M = 1.8
        private const val STRIDE_EMA_ALPHA = 0.15              // exponential moving average weight

        // Layer 3 – Speed-Based Step Rate
        private const val WALK_MIN_SPEED_MS = 0.8              // m/s
        private const val WALK_MAX_SPEED_MS = 2.0
        private const val RUN_MAX_SPEED_MS = 5.0
        private const val WALK_MIN_CADENCE = 70                // steps/min
        private const val WALK_MAX_CADENCE = 140
        private const val RUN_MIN_CADENCE = 120
        private const val RUN_MAX_CADENCE = 210
        private const val CADENCE_DEVIATION_TOLERANCE = 0.45   // 45% tolerance

        // Layer 4 – Activity filtering
        private const val ACTIVITY_CONFIDENCE_THRESHOLD = 70

        // Layer 5 – Anomaly detection
        private const val MAX_STEPS_PER_30S = 300
        private const val MAX_PEDESTRIAN_SPEED_MS = 12.0       // world-class sprint ~12 m/s
        private const val WINDOW_DURATION_MS = 30_000L
    }

    // ------- State -------

    // Stride & distance
    private var currentStrideLengthM = DEFAULT_STRIDE_LENGTH_M
    private var userHeightCm: Double = 0.0  // set via setUserHeight()

    // Rolling window tracking
    private var windowStartTime = 0L
    private var windowStartSteps = 0
    private var windowStartLocation: Location? = null
    private var windowGpsDistance = 0.0

    // Overall session
    private var lastLocation: Location? = null
    private var totalGpsDistanceM = 0.0
    private var totalValidatedSteps = 0
    private var totalRawSteps = 0

    // Activity recognition
    private var currentActivityType = DetectedActivity.UNKNOWN
    private var currentActivityConfidence = 0

    // Speed
    private var currentSpeedMs = 0.0

    // Anomaly window
    private val recentStepTimestamps = mutableListOf<Long>()

    // Confidence
    private var lastConfidence = 0.0

    // Listener
    private var onValidationResult: ((ValidatedResult) -> Unit)? = null

    // ------- Data classes -------

    data class ValidatedResult(
        val validatedSteps: Int,
        val rawSteps: Int,
        val strideLengthM: Double,
        val gpsDistanceM: Double,
        val speedMs: Double,
        val confidence: Double,
        val activityType: Int,
        val rejectionReason: String?       // null if accepted
    )

    // ------- Public API -------

    fun setListener(listener: (ValidatedResult) -> Unit) {
        onValidationResult = listener
    }

    fun setUserHeight(heightCm: Double) {
        userHeightCm = heightCm
        // Rule of thumb: stride ≈ 0.415 × height (cm) converted to meters
        if (heightCm > 0) {
            currentStrideLengthM = (heightCm * 0.415 / 100.0)
                .coerceIn(MIN_STRIDE_M, MAX_STRIDE_M)
            Log.d(TAG, "Initial stride from height ($heightCm cm): $currentStrideLengthM m")
        }
    }

    /** Called on each step detection event from HybridStepValidator / sensor */
    fun onStepsDetected(newRawSteps: Int, timestamp: Long): ValidatedResult {
        totalRawSteps = newRawSteps

        // --- Layer 5: Anomaly Detection (spike check) ---
        recentStepTimestamps.add(timestamp)
        recentStepTimestamps.removeAll { timestamp - it > WINDOW_DURATION_MS }
        if (recentStepTimestamps.size > MAX_STEPS_PER_30S) {
            val reason = "Anomaly: ${recentStepTimestamps.size} steps in 30s window"
            Log.w(TAG, reason)
            return buildResult(reason)
        }

        // --- Layer 4: Activity-Aware Filtering ---
        val activityRejection = checkActivityFilter()
        if (activityRejection != null) {
            return buildResult(activityRejection)
        }

        // --- Layer 3: Speed-Based Cadence Check ---
        val cadenceRejection = checkSpeedCadence(timestamp)
        if (cadenceRejection != null) {
            return buildResult(cadenceRejection)
        }

        // --- Layer 1&2 are checked in the rolling window (onLocationUpdate) ---
        // If we get here, step is accepted
        totalValidatedSteps = newRawSteps
        lastConfidence = calculateConfidence()

        return buildResult(null)
    }

    /** Called from location callback in the service */
    fun onLocationUpdate(location: Location, timestamp: Long) {
        currentSpeedMs = if (location.hasSpeed()) location.speed.toDouble() else 0.0

        // --- Layer 5: Impossible speed ---
        if (currentSpeedMs > MAX_PEDESTRIAN_SPEED_MS) {
            Log.w(TAG, "Impossible pedestrian speed: $currentSpeedMs m/s")
            // Don't discard all steps, but flag the window
        }

        // Accumulate GPS distance
        lastLocation?.let { prev ->
            val segmentDist = prev.distanceTo(location).toDouble()
            // Filter GPS jitter — ignore tiny movements
            if (segmentDist > 1.0) {
                totalGpsDistanceM += segmentDist
                windowGpsDistance += segmentDist
            }
        }
        lastLocation = location

        // --- Rolling window stride calibration ---
        if (windowStartTime == 0L) {
            windowStartTime = timestamp
            windowStartSteps = totalRawSteps
            windowStartLocation = location
            windowGpsDistance = 0.0
            return
        }

        val windowElapsed = timestamp - windowStartTime
        if (windowElapsed >= WINDOW_DURATION_MS) {
            val windowSteps = totalRawSteps - windowStartSteps

            // --- Layer 1: GPS Distance Validation ---
            if (windowSteps >= MIN_STEPS_FOR_GPS_CHECK && windowGpsDistance > 2.0) {
                val estimatedDist = windowSteps * currentStrideLengthM
                val ratio = windowGpsDistance / estimatedDist

                if (ratio < GPS_DISTANCE_RATIO_THRESHOLD) {
                    Log.w(TAG, "GPS validation fail: GPS=${windowGpsDistance}m vs estimated=${estimatedDist}m (ratio=$ratio)")
                    // Adjust validated steps downward based on GPS reality
                    val adjustedSteps = (windowGpsDistance / currentStrideLengthM).toInt()
                    val correction = windowSteps - adjustedSteps
                    if (correction > 0) {
                        totalValidatedSteps = (totalValidatedSteps - correction).coerceAtLeast(0)
                        Log.d(TAG, "Corrected $correction false steps in window")
                    }
                }

                // --- Layer 2: Dynamic Stride Calibration ---
                if (windowSteps > 10 && windowGpsDistance > 5.0) {
                    val measuredStride = windowGpsDistance / windowSteps
                    if (measuredStride in MIN_STRIDE_M..MAX_STRIDE_M) {
                        // Exponential moving average
                        currentStrideLengthM = (STRIDE_EMA_ALPHA * measuredStride +
                                (1 - STRIDE_EMA_ALPHA) * currentStrideLengthM)
                            .coerceIn(MIN_STRIDE_M, MAX_STRIDE_M)
                        Log.d(TAG, "Stride calibrated: $currentStrideLengthM m (measured: $measuredStride)")
                    }
                }
            }

            // Reset window
            windowStartTime = timestamp
            windowStartSteps = totalRawSteps
            windowStartLocation = location
            windowGpsDistance = 0.0
        }

        lastConfidence = calculateConfidence()
    }

    /** Called from Activity Recognition receiver */
    fun onActivityDetected(activityType: Int, confidence: Int) {
        currentActivityType = activityType
        currentActivityConfidence = confidence
        Log.d(TAG, "Activity: ${getActivityName(activityType)} ($confidence%)")
    }

    fun reset() {
        totalGpsDistanceM = 0.0
        totalValidatedSteps = 0
        totalRawSteps = 0
        windowStartTime = 0L
        windowStartSteps = 0
        windowStartLocation = null
        windowGpsDistance = 0.0
        lastLocation = null
        currentSpeedMs = 0.0
        recentStepTimestamps.clear()
        lastConfidence = 0.0
    }

    // ------- Getters -------

    fun getValidatedSteps(): Int = totalValidatedSteps
    fun getRawSteps(): Int = totalRawSteps
    fun getStrideLengthM(): Double = currentStrideLengthM
    fun getGpsDistanceM(): Double = totalGpsDistanceM
    fun getCurrentSpeedMs(): Double = currentSpeedMs
    fun getCurrentSpeedKmh(): Double = currentSpeedMs * 3.6
    fun getConfidence(): Double = lastConfidence
    fun getActivityType(): Int = currentActivityType

    // ------- Private helpers -------

    private fun checkActivityFilter(): String? {
        if (currentActivityConfidence < ACTIVITY_CONFIDENCE_THRESHOLD) {
            return null // Not confident enough to filter — allow steps
        }
        return when (currentActivityType) {
            DetectedActivity.IN_VEHICLE -> "Activity: In vehicle (${currentActivityConfidence}%)"
            DetectedActivity.ON_BICYCLE -> "Activity: On bicycle (${currentActivityConfidence}%)"
            DetectedActivity.STILL -> "Activity: Still (${currentActivityConfidence}%)"
            else -> null
        }
    }

    private fun checkSpeedCadence(timestamp: Long): String? {
        if (currentSpeedMs < 0.1) return null // No speed data — skip this check

        // Compute current cadence from recent step timestamps
        val recentWindow = 15_000L // 15-second cadence window
        val recentSteps = recentStepTimestamps.count { timestamp - it <= recentWindow }
        val cadencePerMin = (recentSteps / (recentWindow / 60_000.0))

        if (cadencePerMin < 10) return null // Not enough data

        // Determine expected cadence from speed
        val (expectedMin, expectedMax) = when {
            currentSpeedMs < WALK_MIN_SPEED_MS -> return null  // Too slow, skip check
            currentSpeedMs <= WALK_MAX_SPEED_MS -> Pair(WALK_MIN_CADENCE, WALK_MAX_CADENCE)
            currentSpeedMs <= RUN_MAX_SPEED_MS -> Pair(RUN_MIN_CADENCE, RUN_MAX_CADENCE)
            else -> return "Speed too high for pedestrian: ${currentSpeedMs} m/s"
        }

        val expectedMid = (expectedMin + expectedMax) / 2.0
        val deviation = abs(cadencePerMin - expectedMid) / expectedMid

        if (deviation > CADENCE_DEVIATION_TOLERANCE) {
            return "Cadence mismatch: ${cadencePerMin.toInt()} spm vs expected ${expectedMin}-${expectedMax} at ${String.format("%.1f", currentSpeedMs)} m/s"
        }

        return null
    }

    private fun calculateConfidence(): Double {
        var confidence = 50.0  // base

        // GPS availability boost
        if (lastLocation != null && totalGpsDistanceM > 5) {
            confidence += 15.0
        }

        // Activity recognition boost
        if (currentActivityConfidence > ACTIVITY_CONFIDENCE_THRESHOLD &&
            (currentActivityType == DetectedActivity.WALKING ||
             currentActivityType == DetectedActivity.RUNNING ||
             currentActivityType == DetectedActivity.ON_FOOT)) {
            confidence += 15.0
        }

        // Stride calibration quality
        if (totalRawSteps > 100 && totalGpsDistanceM > 50) {
            confidence += 10.0  // Good calibration data
        }

        // Step vs GPS distance consistency
        if (totalRawSteps > 0 && totalGpsDistanceM > 10) {
            val estimatedDist = totalRawSteps * currentStrideLengthM
            val ratio = totalGpsDistanceM / estimatedDist
            if (ratio in 0.7..1.3) {
                confidence += 10.0  // Good consistency
            }
        }

        return confidence.coerceIn(0.0, 100.0)
    }

    private fun buildResult(rejectionReason: String?): ValidatedResult {
        return ValidatedResult(
            validatedSteps = totalValidatedSteps,
            rawSteps = totalRawSteps,
            strideLengthM = currentStrideLengthM,
            gpsDistanceM = totalGpsDistanceM,
            speedMs = currentSpeedMs,
            confidence = lastConfidence,
            activityType = currentActivityType,
            rejectionReason = rejectionReason
        )
    }

    private fun getActivityName(type: Int): String = when (type) {
        DetectedActivity.WALKING -> "WALKING"
        DetectedActivity.RUNNING -> "RUNNING"
        DetectedActivity.ON_FOOT -> "ON_FOOT"
        DetectedActivity.STILL -> "STILL"
        DetectedActivity.IN_VEHICLE -> "IN_VEHICLE"
        DetectedActivity.ON_BICYCLE -> "ON_BICYCLE"
        DetectedActivity.TILTING -> "TILTING"
        DetectedActivity.UNKNOWN -> "UNKNOWN"
        else -> "OTHER($type)"
    }
}
