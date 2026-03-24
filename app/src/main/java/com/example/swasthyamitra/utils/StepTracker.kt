package com.example.swasthyamitra.utils

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import kotlin.math.sqrt

/**
 * StepTracker is a utility class that provides basic step counting using the device's accelerometer.
 * It is typically used as a fallback for devices without a dedicated hardware step counter sensor.
 * 
 * Logic:
 * It calculates the 3-axis magnitude of movement. If the magnitude exceeds a certain threshold
 * and a minimum amount of time has passed (to prevent double-counting), it registers a step.
 */
class StepTracker(private val context: Context, private val onStepUpdate: (Int) -> Unit) : SensorEventListener {

    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val accelerometer: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    
    private var currentSteps = 0
    private var lastStepTime = 0L
    
    // Movement magnitude threshold. Higher values make it less sensitive.
    private val STEP_THRESHOLD = 12.0 
    
    // Minimum time (ms) required between steps to filter out jitter or multiple peaks in one stride.
    private val STEP_DELAY_MS = 250 

    // Conversion factor to estimate calories burned per step (average value).
    val caloriesPerStep = 0.04

    /**
     * Checks if the required accelerometer sensor is available on the device.
     */
    fun isSensorAvailable(): Boolean {
        return accelerometer != null
    }

    /**
     * Registers the listener to start receiving accelerometer data.
     */
    fun start() {
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    /**
     * Unregisters the listener and resets the local session step count.
     */
    fun stop() {
        sensorManager.unregisterListener(this)
        currentSteps = 0
        lastStepTime = 0L
    }
    
    /**
     * Resets the step counter and notifies the callback with 0.
     */
    fun resetSteps() {
        currentSteps = 0
        onStepUpdate(0)
    }

    /**
     * Core logic for step detection. 
     * Calculates the vector magnitude from X, Y, and Z axes and compares it to a threshold.
     */
    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null || event.sensor.type != Sensor.TYPE_ACCELEROMETER) return

        val x = event.values[0]
        val y = event.values[1]
        val z = event.values[2]
        
        // Magnitude = sqrt(x^2 + y^2 + z^2)
        val magnitude = sqrt(x * x + y * y + z * z)
        
        // Check if movement is strong enough to be considered a step
        if (magnitude > STEP_THRESHOLD) {
            val now = System.currentTimeMillis()
            
            // Debounce: ensure steps aren't counted too close together (mechanical jitter)
            if (now - lastStepTime > STEP_DELAY_MS) {
                currentSteps++
                lastStepTime = now
                onStepUpdate(currentSteps) // Notify the UI or manager
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Not used
    }
}

