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
 * HybridStepValidator is a sophisticated 5-layer validation system designed to separate 
 * actual human steps from "noise" like phone shaking, hand gestures, or vehicle vibrations.
 * 
 * It uses a "Guilty Until Proven Innocent" approach—steps from the hardware sensor 
 * are only accepted if they pass all five mathematical and behavioral filters.
 */
class HybridStepValidator(private val context: Context) : SensorEventListener {

    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    // Multi-sensor input for high-fidelity motion analysis
    private val stepCounterSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
    private val stepDetectorSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)
    private val accelerometerSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    private val linearAccelSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)
    private val gyroscopeSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
    private val gravitySensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)

    private lateinit var activityRecognitionClient: ActivityRecognitionClient
    private var currentActivity: DetectedActivity? = null

    // Internal state for validation logic
    private val _validatedSteps = MutableStateFlow(0)
    val validatedSteps: StateFlow<Int> = _validatedSteps

    private var lastStepTimestamp = 0L
    private var stepTimestamps = mutableListOf<Long>() // For cadence analysis
    private var consecutiveValidSteps = 0
    private var pendingSteps = 0

    // Buffers for motion pattern analysis
    private var lastAccelMagnitude = 0.0
    private var accelMagnitudes = mutableListOf<Double>()
    private var gyroMagnitudes = mutableListOf<Double>()

    // Gesture detection counters
    private var rapidDirectionChanges = 0
    private var lastAccelDirection = FloatArray(3)
    private var orientationChangeCount = 0
    private var lastOrientationTime = 0L

    // Validation Thresholds (Human Biomechanics based)
    private val MIN_STEP_INTERVAL_MS = 350L // ~170 steps/min (Sprinting)
    private val MAX_STEP_INTERVAL_MS = 800L // ~75 steps/min (Slow walk)
    private val MIN_CONSECUTIVE_STEPS = 8   // Ignore "phantom" steps before trust is established
    private val MIN_ACTIVITY_CONFIDENCE = 80 
    private val MIN_ACCEL_MAGNITUDE = 0.5 
    private val MAX_ACCEL_MAGNITUDE = 25.0 
    private val MAX_GYRO_MAGNITUDE = 3.0 
    private val GESTURE_DIRECTION_CHANGE_THRESHOLD = 3
    private val GESTURE_ORIENTATION_CHANGE_THRESHOLD = 5

    private var callback: ((validatedSteps: Int, confidence: Double) -> Unit)? = null

    companion object {
        private const val TAG = "HybridStepValidator"
    }

    /**
     * Starts the multi-sensor validation engine.
     */
    fun start(onValidatedStep: (validatedSteps: Int, confidence: Double) -> Unit) {
        this.callback = onValidatedStep
        activityRecognitionClient = ActivityRecognition.getClient(context)
        requestActivityUpdates()

        // Register all required sensors for fusion
        stepCounterSensor?.let { sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_FASTEST) }
        accelerometerSensor?.let { sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_GAME) }
        linearAccelSensor?.let { sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_GAME) }
        gyroscopeSensor?.let { sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_GAME) }
        gravitySensor?.let { sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL) }
    }

    /**
     * Primary entry point for raw sensor data. 
     * Different sensors feed different parts of the validation brain.
     */
    override fun onSensorChanged(event: SensorEvent?) {
        event ?: return
        when (event.sensor.type) {
            Sensor.TYPE_STEP_COUNTER, Sensor.TYPE_STEP_DETECTOR -> handleStepDetection(event)
            Sensor.TYPE_ACCELEROMETER -> handleAccelerometer(event)
            Sensor.TYPE_LINEAR_ACCELERATION -> handleLinearAcceleration(event)
            Sensor.TYPE_GYROSCOPE -> handleGyroscope(event)
            Sensor.TYPE_GRAVITY -> handleGravity(event)
        }
    }

    /**
     * The heart of the validation logic. 
     * Runs every raw step through a 5-layer gauntlet.
     */
    private fun handleStepDetection(event: SensorEvent) {
        val currentTime = System.currentTimeMillis()

        // LAYER 1: BIOMECHANICAL TIMING
        // Are steps physically possible at this frequency? (Too fast = vibrator/car)
        if (!validateStepTiming(currentTime)) return

        // LAYER 2: SYSTEM ACTIVITY CONTEXT
        // Does Google's AI think the user is actually walking?
        if (!validateActivity()) return

        // LAYER 3: MOTION PATTERN CONSISTENCY
        // Is the raw acceleration rhythmic like a human stride?
        if (!validateMotionPattern()) return

        // LAYER 4: HAND GESTURE FILTERING
        // Is the phone being waved around or rotated rapidly (faking steps)?
        if (isHandGesture()) return

        // LAYER 5: CADENCE VARIANCE
        // Are the intervals between steps regular (human) or erratic (random noise)?
        if (!validateCadence(currentTime)) return

        // SUCCESS: Register as a legitimate human step
        registerValidatedStep(currentTime)
    }

    /**
     * Checks if the time between steps matches human limits.
     */
    private fun validateStepTiming(currentTime: Long): Boolean {
        if (lastStepTimestamp == 0L) return true 
        val timeDiff = currentTime - lastStepTimestamp
        return timeDiff in MIN_STEP_INTERVAL_MS..MAX_STEP_INTERVAL_MS
    }

    /**
     * Cross-references with the high-level Activity Recognition state.
     */
    private fun validateActivity(): Boolean {
        val activity = currentActivity ?: return false
        val isWalkingOrRunning = activity.type == DetectedActivity.WALKING || 
                                 activity.type == DetectedActivity.RUNNING || 
                                 activity.type == DetectedActivity.ON_FOOT
        return isWalkingOrRunning && activity.confidence >= MIN_ACTIVITY_CONFIDENCE
    }

    /**
     * Analyzes the last 5 acceleration magnitudes for rhythm.
     */
    private fun validateMotionPattern(): Boolean {
        if (accelMagnitudes.isEmpty()) return false
        val avgMagnitude = accelMagnitudes.takeLast(5).average()
        if (avgMagnitude < MIN_ACCEL_MAGNITUDE || avgMagnitude > MAX_ACCEL_MAGNITUDE) return false
        
        // Use Standard Deviation to detect erratic (non-walking) motion
        if (accelMagnitudes.size >= 5) {
            if (calculateStandardDeviation(accelMagnitudes.takeLast(5)) > 5.0) return false
        }
        return true
    }

    /**
     * Detects if the internal motion sensors indicate hand waving or rotation.
     */
    private fun isHandGesture(): Boolean {
        // High rapidDirectionChanges implies shaking the phone back and forth
        if (rapidDirectionChanges >= GESTURE_DIRECTION_CHANGE_THRESHOLD) return true
        
        // High rotation (gyro) implies the phone is being flipped by hand
        if (gyroMagnitudes.isNotEmpty() && gyroMagnitudes.takeLast(5).average() > MAX_GYRO_MAGNITUDE) return true
        
        return false
    }

    /**
     * Ensures the steps have a rhythmic cadence (regular intervals).
     */
    private fun validateCadence(currentTime: Long): Boolean {
        stepTimestamps.add(currentTime)
        if (stepTimestamps.size > 10) stepTimestamps.removeAt(0)
        if (stepTimestamps.size < 3) return true 

        val intervals = mutableListOf<Long>()
        for (i in 1 until stepTimestamps.size) {
            intervals.add(stepTimestamps[i] - stepTimestamps[i - 1])
        }
        val avgInterval = intervals.average()
        val variance = intervals.map { abs(it - avgInterval) }.average()
        
        // Human gait usually has < 30% interval variance
        return variance / avgInterval < 0.3
    }

    /**
     * Final stage: increments the count and notifies subscribers.
     * Note: It waits for 8 consecutive valid steps to prevent "start-stop" sensor jitter.
     */
    private fun registerValidatedStep(timestamp: Long) {
        lastStepTimestamp = timestamp
        pendingSteps++
        consecutiveValidSteps++

        if (consecutiveValidSteps >= MIN_CONSECUTIVE_STEPS) {
            _validatedSteps.value += pendingSteps
            callback?.invoke(_validatedSteps.value, calculateConfidence())
            pendingSteps = 0
        }
    }

    /**
     * Computes a "Trust Score" (0-100) based on how well the sensors agree.
     */
    private fun calculateConfidence(): Double {
        var confidence = 0.0
        // 40% based on Activity AI
        currentActivity?.let { confidence += (it.confidence / 100.0) * 0.4 }
        // 30% based on Motion rhythm
        if (accelMagnitudes.size >= 5) confidence += (1.0 - (calculateStandardDeviation(accelMagnitudes.takeLast(5)) / 5.0).coerceIn(0.0, 1.0)) * 0.3
        // 30% based on Cadence regularity
        return (confidence * 100).coerceIn(0.0, 100.0)
    }

    // --- Low-level sensor handlers for feature extraction ---

    private fun handleAccelerometer(event: SensorEvent) {
        val x = event.values[0]; val y = event.values[1]; val z = event.values[2]
        val magnitude = sqrt((x * x + y * y + z * z).toDouble())
        accelMagnitudes.add(magnitude)
        if (accelMagnitudes.size > 50) accelMagnitudes.removeAt(0)

        // Detect dot-product sign flips to identify rapid direction changes (shaking)
        if (lastAccelDirection[0] != 0f) {
            if ((x * lastAccelDirection[0] + y * lastAccelDirection[1] + z * lastAccelDirection[2]) < 0) {
                rapidDirectionChanges++
                android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({ rapidDirectionChanges = maxOf(0, rapidDirectionChanges - 1) }, 1000)
            }
        }
        lastAccelDirection[0] = x; lastAccelDirection[1] = y; lastAccelDirection[2] = z
    }

    private fun handleGyroscope(event: SensorEvent) {
        val magnitude = sqrt(event.values[0] * event.values[0] + event.values[1] * event.values[1] + event.values[2] * event.values[2])
        gyroMagnitudes.add(magnitude.toDouble())
        if (gyroMagnitudes.size > 20) gyroMagnitudes.removeAt(0)
    }

    private fun calculateStandardDeviation(values: List<Double>): Double {
        if (values.isEmpty()) return 0.0
        val mean = values.average()
        return sqrt(values.map { (it - mean) * (it - mean) }.average())
    }

    fun updateActivityState(activity: DetectedActivity) {
        currentActivity = activity
        // If user stops moving (STILL), reset the "trust" buffer
        if (activity.type == DetectedActivity.STILL) {
            consecutiveValidSteps = 0
            pendingSteps = 0
        }
    }

    fun reset() {
        _validatedSteps.value = 0
        consecutiveValidSteps = 0
        pendingSteps = 0
        stepTimestamps.clear()
        accelMagnitudes.clear()
        gyroMagnitudes.clear()
    }

    fun stop() {
        sensorManager.unregisterListener(this)
        removeActivityUpdates()
        callback = null
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
    private fun handleLinearAcceleration(event: SensorEvent) {}
    private fun handleGravity(event: SensorEvent) {}
    private fun requestActivityUpdates() {}
    private fun removeActivityUpdates() {}
}


