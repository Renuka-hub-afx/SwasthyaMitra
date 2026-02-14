package com.example.swasthyamitra.step

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import com.google.android.gms.location.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.math.abs
import kotlin.math.sqrt

/**
 * Hybrid Step Validator - Multi-layer validation system
 * Combines hardware step sensor with intelligent validation layers
 */
class HybridStepValidator(private val context: Context) : SensorEventListener {

    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    // Sensors
    private val stepCounterSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
    private val stepDetectorSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)
    private val accelerometerSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    private val linearAccelSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)
    private val gyroscopeSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
    private val gravitySensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)

    // Activity Recognition
    private lateinit var activityRecognitionClient: ActivityRecognitionClient
    private var currentActivity: DetectedActivity? = null

    // Validation State
    private val _validatedSteps = MutableStateFlow(0)
    val validatedSteps: StateFlow<Int> = _validatedSteps

    private var lastStepTimestamp = 0L
    private var stepTimestamps = mutableListOf<Long>()
    private var consecutiveValidSteps = 0
    private var pendingSteps = 0

    // Motion validation data
    private var lastAccelMagnitude = 0.0
    private var accelMagnitudes = mutableListOf<Double>()
    private var gyroMagnitudes = mutableListOf<Double>()

    // Gesture detection
    private var rapidDirectionChanges = 0
    private var lastAccelDirection = FloatArray(3)
    private var orientationChangeCount = 0
    private var lastOrientationTime = 0L

    // Configuration
    private val MIN_STEP_INTERVAL_MS = 350L // Minimum time between steps
    private val MAX_STEP_INTERVAL_MS = 800L // Maximum time between steps
    private val MIN_CONSECUTIVE_STEPS = 8 // Minimum validated steps before UI update
    private val MIN_ACTIVITY_CONFIDENCE = 80 // Minimum confidence for activity
    private val MIN_ACCEL_MAGNITUDE = 0.5 // Minimum acceleration for step
    private val MAX_ACCEL_MAGNITUDE = 25.0 // Maximum acceleration (filter spikes)
    private val MAX_GYRO_MAGNITUDE = 3.0 // Maximum rotation rate
    private val GESTURE_DIRECTION_CHANGE_THRESHOLD = 3 // Max direction changes in 1s
    private val GESTURE_ORIENTATION_CHANGE_THRESHOLD = 5 // Max orientation changes in 1s

    private var callback: ((validatedSteps: Int, confidence: Double) -> Unit)? = null

    companion object {
        private const val TAG = "HybridStepValidator"
    }

    fun start(onValidatedStep: (validatedSteps: Int, confidence: Double) -> Unit) {
        this.callback = onValidatedStep

        // Initialize Activity Recognition
        activityRecognitionClient = ActivityRecognition.getClient(context)
        requestActivityUpdates()

        // Register sensors
        stepCounterSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_FASTEST)
            Log.d(TAG, "Step Counter registered")
        }

        stepDetectorSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_FASTEST)
            Log.d(TAG, "Step Detector registered")
        }

        accelerometerSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_GAME)
            Log.d(TAG, "Accelerometer registered")
        }

        linearAccelSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_GAME)
            Log.d(TAG, "Linear Acceleration registered")
        }

        gyroscopeSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_GAME)
            Log.d(TAG, "Gyroscope registered")
        }

        gravitySensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
            Log.d(TAG, "Gravity sensor registered")
        }

        Log.i(TAG, "Hybrid Step Validator started with multi-layer validation")
    }

    fun stop() {
        sensorManager.unregisterListener(this)
        removeActivityUpdates()
        callback = null
        Log.i(TAG, "Hybrid Step Validator stopped")
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event ?: return

        when (event.sensor.type) {
            Sensor.TYPE_STEP_COUNTER, Sensor.TYPE_STEP_DETECTOR -> {
                handleStepDetection(event)
            }
            Sensor.TYPE_ACCELEROMETER -> {
                handleAccelerometer(event)
            }
            Sensor.TYPE_LINEAR_ACCELERATION -> {
                handleLinearAcceleration(event)
            }
            Sensor.TYPE_GYROSCOPE -> {
                handleGyroscope(event)
            }
            Sensor.TYPE_GRAVITY -> {
                handleGravity(event)
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Handle accuracy changes if needed
    }

    private fun handleStepDetection(event: SensorEvent) {
        val currentTime = System.currentTimeMillis()

        // Layer 1: Time-based validation
        if (!validateStepTiming(currentTime)) {
            Log.d(TAG, "Step rejected: Invalid timing")
            return
        }

        // Layer 2: Activity Recognition validation
        if (!validateActivity()) {
            Log.d(TAG, "Step rejected: Invalid activity state")
            return
        }

        // Layer 3: Motion pattern validation
        if (!validateMotionPattern()) {
            Log.d(TAG, "Step rejected: Invalid motion pattern")
            return
        }

        // Layer 4: Gesture filtering
        if (isHandGesture()) {
            Log.d(TAG, "Step rejected: Hand gesture detected")
            return
        }

        // Layer 5: Cadence validation
        if (!validateCadence(currentTime)) {
            Log.d(TAG, "Step rejected: Invalid cadence")
            return
        }

        // Step passed all validations
        registerValidatedStep(currentTime)
    }

    private fun validateStepTiming(currentTime: Long): Boolean {
        if (lastStepTimestamp == 0L) {
            return true // First step
        }

        val timeDiff = currentTime - lastStepTimestamp
        return timeDiff in MIN_STEP_INTERVAL_MS..MAX_STEP_INTERVAL_MS
    }

    private fun validateActivity(): Boolean {
        val activity = currentActivity ?: return false

        // Accept only WALKING or RUNNING with high confidence
        val validActivity = activity.type == DetectedActivity.WALKING ||
                           activity.type == DetectedActivity.RUNNING ||
                           activity.type == DetectedActivity.ON_FOOT

        val highConfidence = activity.confidence >= MIN_ACTIVITY_CONFIDENCE

        // Reject if IN_VEHICLE, STILL, or UNKNOWN
        val rejectedActivity = activity.type == DetectedActivity.IN_VEHICLE ||
                              activity.type == DetectedActivity.STILL ||
                              activity.type == DetectedActivity.UNKNOWN

        return validActivity && highConfidence && !rejectedActivity
    }

    private fun validateMotionPattern(): Boolean {
        // Check if acceleration magnitude is within realistic walking/running range
        if (accelMagnitudes.isEmpty()) return false

        val avgMagnitude = accelMagnitudes.takeLast(5).average()

        if (avgMagnitude < MIN_ACCEL_MAGNITUDE || avgMagnitude > MAX_ACCEL_MAGNITUDE) {
            return false
        }

        // Check for rhythmic consistency
        if (accelMagnitudes.size >= 5) {
            val recentMagnitudes = accelMagnitudes.takeLast(5)
            val stdDev = calculateStandardDeviation(recentMagnitudes)

            // Motion should be consistent, not erratic
            if (stdDev > 5.0) {
                return false
            }
        }

        return true
    }

    private fun isHandGesture(): Boolean {
        // Check for rapid direction changes (indicator of hand waving)
        if (rapidDirectionChanges >= GESTURE_DIRECTION_CHANGE_THRESHOLD) {
            rapidDirectionChanges = 0 // Reset for next check
            return true
        }

        // Check for rapid orientation changes (indicator of phone being moved by hand)
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastOrientationTime < 1000 &&
            orientationChangeCount >= GESTURE_ORIENTATION_CHANGE_THRESHOLD) {
            orientationChangeCount = 0
            lastOrientationTime = currentTime
            return true
        }

        // Check gyroscope for excessive rotation
        if (gyroMagnitudes.isNotEmpty()) {
            val recentGyro = gyroMagnitudes.takeLast(5).average()
            if (recentGyro > MAX_GYRO_MAGNITUDE) {
                return true
            }
        }

        return false
    }

    private fun validateCadence(currentTime: Long): Boolean {
        stepTimestamps.add(currentTime)

        // Keep only last 10 timestamps
        if (stepTimestamps.size > 10) {
            stepTimestamps.removeAt(0)
        }

        if (stepTimestamps.size < 3) {
            return true // Need more data
        }

        // Check if cadence is consistent
        val intervals = mutableListOf<Long>()
        for (i in 1 until stepTimestamps.size) {
            intervals.add(stepTimestamps[i] - stepTimestamps[i - 1])
        }

        val avgInterval = intervals.average()

        // Cadence should be consistent (within 30% variance)
        val variance = intervals.map { abs(it - avgInterval) }.average()
        return variance / avgInterval < 0.3
    }

    private fun registerValidatedStep(timestamp: Long) {
        lastStepTimestamp = timestamp
        pendingSteps++
        consecutiveValidSteps++

        // Only update UI after minimum consecutive validated steps
        if (consecutiveValidSteps >= MIN_CONSECUTIVE_STEPS) {
            _validatedSteps.value += pendingSteps
            val confidence = calculateConfidence()
            callback?.invoke(_validatedSteps.value, confidence)
            pendingSteps = 0

            Log.i(TAG, "Validated steps: ${_validatedSteps.value}, Confidence: $confidence")
        }
    }

    private fun calculateConfidence(): Double {
        var confidence = 0.0

        // Activity confidence contribution (40%)
        currentActivity?.let {
            confidence += (it.confidence / 100.0) * 0.4
        }

        // Motion pattern consistency (30%)
        if (accelMagnitudes.size >= 5) {
            val recentMagnitudes = accelMagnitudes.takeLast(5)
            val stdDev = calculateStandardDeviation(recentMagnitudes)
            val consistency = 1.0 - (stdDev / 5.0).coerceIn(0.0, 1.0)
            confidence += consistency * 0.3
        }

        // Cadence regularity (30%)
        if (stepTimestamps.size >= 5) {
            val intervals = mutableListOf<Long>()
            for (i in 1 until stepTimestamps.size.coerceAtMost(6)) {
                intervals.add(stepTimestamps[stepTimestamps.size - i] - stepTimestamps[stepTimestamps.size - i - 1])
            }
            val avgInterval = intervals.average()
            val variance = intervals.map { abs(it - avgInterval) }.average()
            val regularity = 1.0 - (variance / avgInterval).coerceIn(0.0, 1.0)
            confidence += regularity * 0.3
        }

        return (confidence * 100).coerceIn(0.0, 100.0)
    }

    private fun handleAccelerometer(event: SensorEvent) {
        val x = event.values[0]
        val y = event.values[1]
        val z = event.values[2]

        val magnitude = sqrt((x * x + y * y + z * z).toDouble())
        accelMagnitudes.add(magnitude)

        // Keep only last 50 readings
        if (accelMagnitudes.size > 50) {
            accelMagnitudes.removeAt(0)
        }

        // Detect direction changes
        if (lastAccelDirection[0] != 0f) {
            val dotProduct = x * lastAccelDirection[0] + y * lastAccelDirection[1] + z * lastAccelDirection[2]
            if (dotProduct < 0) {
                rapidDirectionChanges++
                android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                    rapidDirectionChanges = maxOf(0, rapidDirectionChanges - 1)
                }, 1000)
            }
        }

        lastAccelDirection[0] = x
        lastAccelDirection[1] = y
        lastAccelDirection[2] = z
        lastAccelMagnitude = magnitude
    }

    private fun handleLinearAcceleration(event: SensorEvent) {
        // Additional validation using linear acceleration (gravity removed)
        val x = event.values[0]
        val y = event.values[1]
        val z = event.values[2]

        val magnitude = sqrt(x * x + y * y + z * z)

        // Detect sudden spikes (hand shaking)
        if (magnitude > 15.0) {
            rapidDirectionChanges += 2 // Penalize for spike
        }
    }

    private fun handleGyroscope(event: SensorEvent) {
        val x = event.values[0]
        val y = event.values[1]
        val z = event.values[2]

        val magnitude = sqrt(x * x + y * y + z * z)
        gyroMagnitudes.add(magnitude.toDouble())

        // Keep only last 20 readings
        if (gyroMagnitudes.size > 20) {
            gyroMagnitudes.removeAt(0)
        }
    }

    private fun handleGravity(event: SensorEvent) {
        // Track orientation changes
        val currentTime = System.currentTimeMillis()

        if (currentTime - lastOrientationTime > 1000) {
            orientationChangeCount = 0
            lastOrientationTime = currentTime
        }

        orientationChangeCount++
    }

    private fun calculateStandardDeviation(values: List<Double>): Double {
        if (values.isEmpty()) return 0.0

        val mean = values.average()
        val variance = values.map { (it - mean) * (it - mean) }.average()
        return sqrt(variance)
    }

    private fun requestActivityUpdates() {
        try {
            // ActivityRecognitionRequest is not needed for simple updates in newer APIs, 
            // or we use the minimal version. 
            // Checking standard implementation: requestActivityUpdates(interval, pendingIntent)
            val detectionIntervalMillis = 3000L

            val pendingIntent = android.app.PendingIntent.getBroadcast(
                context,
                0,
                android.content.Intent(context, ActivityRecognitionReceiver::class.java),
                android.app.PendingIntent.FLAG_UPDATE_CURRENT or android.app.PendingIntent.FLAG_MUTABLE
            )

            activityRecognitionClient.requestActivityUpdates(detectionIntervalMillis, pendingIntent)
        } catch (e: Exception) {
            Log.e(TAG, "Failed to request activity updates: ${e.message}")
        }
    }

    private fun removeActivityUpdates() {
        try {
            val pendingIntent = android.app.PendingIntent.getBroadcast(
                context,
                0,
                android.content.Intent(context, ActivityRecognitionReceiver::class.java),
                android.app.PendingIntent.FLAG_UPDATE_CURRENT or android.app.PendingIntent.FLAG_MUTABLE
            )

            activityRecognitionClient.removeActivityUpdates(pendingIntent)
        } catch (e: Exception) {
            Log.e(TAG, "Failed to remove activity updates: ${e.message}")
        }
    }

    fun updateActivityState(activity: DetectedActivity) {
        currentActivity = activity
        Log.d(TAG, "Activity updated: ${getActivityName(activity.type)} (${activity.confidence}%)")

        // Reset consecutive steps if activity changes to non-walking
        if (activity.type != DetectedActivity.WALKING &&
            activity.type != DetectedActivity.RUNNING &&
            activity.type != DetectedActivity.ON_FOOT) {
            consecutiveValidSteps = 0
            pendingSteps = 0
        }
    }

    private fun getActivityName(activityType: Int): String {
        return when (activityType) {
            DetectedActivity.WALKING -> "WALKING"
            DetectedActivity.RUNNING -> "RUNNING"
            DetectedActivity.ON_FOOT -> "ON_FOOT"
            DetectedActivity.STILL -> "STILL"
            DetectedActivity.IN_VEHICLE -> "IN_VEHICLE"
            DetectedActivity.ON_BICYCLE -> "ON_BICYCLE"
            DetectedActivity.UNKNOWN -> "UNKNOWN"
            else -> "OTHER"
        }
    }

    fun reset() {
        _validatedSteps.value = 0
        consecutiveValidSteps = 0
        pendingSteps = 0
        stepTimestamps.clear()
        accelMagnitudes.clear()
        gyroMagnitudes.clear()
        lastStepTimestamp = 0L
        rapidDirectionChanges = 0
        orientationChangeCount = 0
    }
}

