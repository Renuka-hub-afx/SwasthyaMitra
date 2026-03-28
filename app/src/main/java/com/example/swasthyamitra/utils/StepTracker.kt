package com.example.swasthyamitra.utils

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import kotlin.math.sqrt

// Simple accelerometer-based step counter used by StepCounterService; counts peaks above threshold
class StepTracker(private val context: Context, private val onStepUpdate: (Int) -> Unit) : SensorEventListener {

    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val accelerometer: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    
    private var currentSteps = 0
    private var lastStepTime = 0L
    
    // Tunable parameters
    private val STEP_THRESHOLD = 12.0 // acceleration vector magnitude needed to count as a step
    private val STEP_DELAY_MS = 250   // minimum milliseconds between steps to debounce rapid vibration

    // Configurable calorie factor (approx .04 kcal per step)
    val caloriesPerStep = 0.04

    // Returns false if the device has no accelerometer (service will shut down gracefully)
    fun isSensorAvailable(): Boolean {
        return accelerometer != null
    }

    // Registers the accelerometer listener — service will start receiving sensor events
    fun start() {
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
            Log.d("StepTracker", "Accelerometer registered for shake detection")
        } else {
            Log.w("StepTracker", "Accelerometer not found")
        }
    }

    // Unregisters the listener and resets counters (called on service stop or day reset)
    fun stop() {
        sensorManager.unregisterListener(this)
        currentSteps = 0
        lastStepTime = 0L
        Log.d("StepTracker", "Accelerometer unregistered")
    }
    
    fun resetSteps() {
        currentSteps = 0
        onStepUpdate(0)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null || event.sensor.type != Sensor.TYPE_ACCELEROMETER) return

        val x = event.values[0]
        val y = event.values[1]
        val z = event.values[2]
        
        // Calculate 3D acceleration magnitude from x/y/z axes (removes direction bias)
        val magnitude = sqrt(x * x + y * y + z * z)

        // Count step only if magnitude exceeds threshold AND enough time has passed since last step
        if (magnitude > STEP_THRESHOLD) {
            val now = System.currentTimeMillis()
            
            // Debounce: only count if enough time has passed since last step
            if (now - lastStepTime > STEP_DELAY_MS) {
                currentSteps++
                lastStepTime = now
                onStepUpdate(currentSteps)
                Log.d("StepTracker", "Step detected! Total: $currentSteps (magnitude: $magnitude)")
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // No-op
    }
}
