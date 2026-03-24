package com.example.swasthyamitra.step

import android.location.Location
import android.util.Log
import com.google.android.gms.location.DetectedActivity
import kotlin.math.abs

/**
 * StepGpsValidator acts as a "Senior Auditor" for the step tracking system.
 * While sensors detect motion, this class uses GPS and Activity AI to verify 
 * if that motion matches the physical reality of a person traveling on foot.
 * 
 * It employs 5 Audit Layers:
 * 1. GPS Distance: Does the GPS show the user actually moved the distance of these steps?
 * 2. Stride Calibration: Dynamically computes how long the user's step is based on GPS.
 * 3. Speed-Cadence: Is the step frequency (SPM) physically possible at this GPS speed?
 * 4. Activity Filtering: Suppresses steps if the user is in a vehicle or stationary.
 * 5. Anomaly Detection: Filters out impossible "spikes" (e.g., 300 steps in 30 seconds).
 */
class StepGpsValidator {

    companion object {
        private const val TAG = "StepGpsValidator"

        // LAYER 1: Distance Ratio. GPS distance must be at least 40% of sensor-calculated distance.
        private const val GPS_DISTANCE_RATIO_THRESHOLD = 0.40  
        private const val MIN_STEPS_FOR_GPS_CHECK = 20         

        // LAYER 2: Stride logic
        private const val DEFAULT_STRIDE_LENGTH_M = 0.72       
        private const val MIN_STRIDE_M = 0.3
        private const val MAX_STRIDE_M = 1.8
        private const val STRIDE_EMA_ALPHA = 0.15 // Weight for learning new stride lengths

        // LAYER 3/4: Speed & Activity ranges
        private const val WALK_MIN_SPEED_MS = 0.8
        private const val RUN_MAX_SPEED_MS = 5.0
        private const val ACTIVITY_CONFIDENCE_THRESHOLD = 70

        // LAYER 5: Anomaly limits
        private const val MAX_STEPS_PER_30S = 300
        private const val MAX_PEDESTRIAN_SPEED_MS = 12.0 // Human sprint limit
        private const val WINDOW_DURATION_MS = 30_000L   // 30-second audit window
    }

    // --- State: Counters and Rolling Audit Windows ---

    private var currentStrideLengthM = DEFAULT_STRIDE_LENGTH_M
    private var userHeightCm: Double = 0.0

    // Rolling audit window state (resets every 30s)
    private var windowStartTime = 0L
    private var windowStartSteps = 0
    private var windowGpsDistance = 0.0

    private var lastLocation: Location? = null
    private var totalGpsDistanceM = 0.0
    private var totalValidatedSteps = 0
    private var totalRawSteps = 0

    private var currentActivityType = DetectedActivity.UNKNOWN
    private var currentActivityConfidence = 0
    private var currentSpeedMs = 0.0

    private val recentStepTimestamps = mutableListOf<Long>()
    private var onValidationResult: ((ValidatedResult) -> Unit)? = null

    /**
     * Final report structure containing all audit metrics for a batch of steps.
     */
    data class ValidatedResult(
        val validatedSteps: Int,
        val rawSteps: Int,
        val strideLengthM: Double,
        val gpsDistanceM: Double,
        val speedMs: Double,
        val confidence: Double,
        val activityType: Int,
        val rejectionReason: String?
    )

    /**
     * Initializes the stride length based on the user's height.
     * Formula: Stride length ≈ 41.5% of height.
     */
    fun setUserHeight(heightCm: Double) {
        userHeightCm = heightCm
        if (heightCm > 0) {
            currentStrideLengthM = (heightCm * 0.415 / 100.0).coerceIn(MIN_STRIDE_M, MAX_STRIDE_M)
        }
    }

    /**
     * Audit point for new step signals. 
     * Immediately checks for anomalies (spikes) and activity context (vehicle check).
     */
    fun onStepsDetected(newRawSteps: Int, timestamp: Long): ValidatedResult {
        totalRawSteps = newRawSteps

        // AUDIT LAYER 5: SPIKE DETECTION
        recentStepTimestamps.add(timestamp)
        recentStepTimestamps.removeAll { timestamp - it > WINDOW_DURATION_MS }
        if (recentStepTimestamps.size > MAX_STEPS_PER_30S) {
            return buildResult("Anomaly: Extreme step spike detected")
        }

        // AUDIT LAYER 4: VEHICLE/STATIONARY FILTER
        val activityRejection = checkActivityFilter()
        if (activityRejection != null) return buildResult(activityRejection)

        // AUDIT LAYER 3: SPEED-CADENCE CROSS-CHECK
        val cadenceRejection = checkSpeedCadence(timestamp)
        if (cadenceRejection != null) return buildResult(cadenceRejection)

        // If checks pass, steps are credited to the validated total
        totalValidatedSteps = newRawSteps
        return buildResult(null)
    }

    /**
     * Core audit routine that processes GPS location updates.
     * Every 30 seconds, it performs a "Reality Check" comparing GPS travel vs sensor steps.
     */
    fun onLocationUpdate(location: Location, timestamp: Long) {
        currentSpeedMs = if (location.hasSpeed()) location.speed.toDouble() else 0.0

        // Accumulate segment distance (filtering out GPS jitter noise < 1m)
        lastLocation?.let { prev ->
            val segmentDist = prev.distanceTo(location).toDouble()
            if (segmentDist > 1.0) {
                totalGpsDistanceM += segmentDist
                windowGpsDistance += segmentDist
            }
        }
        lastLocation = location

        // --- THE 30-SECOND REALITY CHECK ---
        if (windowStartTime == 0L) {
            windowStartTime = timestamp; windowStartSteps = totalRawSteps; return
        }

        if (timestamp - windowStartTime >= WINDOW_DURATION_MS) {
            val windowSteps = totalRawSteps - windowStartSteps

            // AUDIT LAYER 1: VALIDATE DISTANCE RATIO
            if (windowSteps >= MIN_STEPS_FOR_GPS_CHECK && windowGpsDistance > 2.0) {
                val estimatedDist = windowSteps * currentStrideLengthM
                val ratio = windowGpsDistance / estimatedDist

                // If GPS shows much less movement than steps, discount the steps
                if (ratio < GPS_DISTANCE_RATIO_THRESHOLD) {
                    val adjustedSteps = (windowGpsDistance / currentStrideLengthM).toInt()
                    totalValidatedSteps = (totalValidatedSteps - (windowSteps - adjustedSteps)).coerceAtLeast(0)
                }

                // AUDIT LAYER 2: DYNAMIC STRIDE CALIBRATION
                // "Learn" the user's specific stride length by comparing clean GPS data to step counts.
                if (windowSteps > 10 && windowGpsDistance > 5.0) {
                    val measuredStride = windowGpsDistance / windowSteps
                    if (measuredStride in MIN_STRIDE_M..MAX_STRIDE_M) {
                        currentStrideLengthM = (STRIDE_EMA_ALPHA * measuredStride + (1 - STRIDE_EMA_ALPHA) * currentStrideLengthM).coerceIn(MIN_STRIDE_M, MAX_STRIDE_M)
                    }
                }
            }

            // Reset audit window for the next 30 seconds
            windowStartTime = timestamp; windowStartSteps = totalRawSteps; windowGpsDistance = 0.0
        }
    }

    /**
     * Internal filter that blocks steps if AI detects the device is in a vehicle.
     */
    private fun checkActivityFilter(): String? {
        if (currentActivityConfidence < ACTIVITY_CONFIDENCE_THRESHOLD) return null 
        return when (currentActivityType) {
            DetectedActivity.IN_VEHICLE -> "Suspended: Vehicle motion detected"
            DetectedActivity.STILL -> "Suspended: Stationary state"
            else -> null
        }
    }

    /**
     * Mathematically verifies if the current step rate (Cadence) 
     * is physically compatible with the current GPS speed.
     */
    private fun checkSpeedCadence(timestamp: Long): String? {
        if (currentSpeedMs < 0.1) return null 
        val recentSteps = recentStepTimestamps.count { timestamp - it <= 15_000L }
        val cadenceSPM = (recentSteps / 0.25) // convert 15s window to 60s (1 minute)

        if (cadenceSPM < 10) return null 

        val (expectedMin, expectedMax) = when {
            currentSpeedMs <= 2.0 -> Pair(70, 140) // Walking ranges
            currentSpeedMs <= 5.0 -> Pair(120, 210) // Running ranges
            else -> return "Speed anomaly: Too fast for pedestrian"
        }

        val expectedMid = (expectedMin + expectedMax) / 2.0
        if ((abs(cadenceSPM - expectedMid) / expectedMid) > 0.45) {
            return "Cadence Warning: Sensor frequency mismatch with speed"
        }
        return null
    }

    private fun calculateConfidence(): Double {
        var confidence = 50.0  // Starting neutral
        if (lastLocation != null && totalGpsDistanceM > 5) confidence += 15.0
        if (currentActivityConfidence > 80) confidence += 15.0
        if (totalRawSteps > 100 && totalGpsDistanceM > 50) confidence += 10.0 // Calibration bonus
        return confidence.coerceIn(0.0, 100.0)
    }

    fun reset() {
        totalGpsDistanceM = 0.0; totalValidatedSteps = 0; totalRawSteps = 0
        windowStartTime = 0L; windowGpsDistance = 0.0; lastLocation = null
        recentStepTimestamps.clear()
    }

    fun getValidatedSteps(): Int = totalValidatedSteps
    fun getCurrentSpeedKmh(): Double = currentSpeedMs * 3.6
    fun getStrideLengthM(): Double = currentStrideLengthM
    fun getConfidence(): Double = calculateConfidence()

    private fun buildResult(reason: String?) = ValidatedResult(totalValidatedSteps, totalRawSteps, currentStrideLengthM, totalGpsDistanceM, currentSpeedMs, calculateConfidence(), currentActivityType, reason)
    fun onActivityDetected(type: Int, conf: Int) { currentActivityType = type; currentActivityConfidence = conf }
    fun setListener(listener: (ValidatedResult) -> Unit) { onValidationResult = listener }
}

