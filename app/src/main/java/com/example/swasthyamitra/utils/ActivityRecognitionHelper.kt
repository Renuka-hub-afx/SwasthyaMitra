package com.example.swasthyamitra.utils

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

object ActivityRecognitionHelper : SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var stepCounter: Sensor? = null
    private var accelerometer: Sensor? = null

    private var stepCount = 0
    private var lastStepCount = 0
    private var isMoving = false
    private var lastResetTime = System.currentTimeMillis()

    fun initialize(context: Context) {
        try {
            sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
            stepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

            stepCounter?.let {
                sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
            }
            accelerometer?.let {
                sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
            }
        } catch (e: Exception) {
            android.util.Log.e("ActivityRecognition", "Error initializing: ${e.message}")
        }
    }

    fun getActivityData(context: Context): ActivityData {
        val currentTime = System.currentTimeMillis()
        val timeDiff = (currentTime - lastResetTime) / 1000 / 60 // minutes

        // Calculate steps in current session
        val sessionSteps = stepCount - lastStepCount

        return ActivityData(
            isExercising = isMoving && sessionSteps > 100,
            activityType = detectActivityType(sessionSteps),
            durationMinutes = timeDiff.toInt().coerceAtMost(60),
            caloriesBurned = calculateCalories(sessionSteps)
        )
    }

    private fun detectActivityType(steps: Int): String {
        return when {
            steps > 1000 -> "Running"
            steps > 500 -> "Brisk Walking"
            steps > 200 -> "Walking"
            isMoving -> "Light Activity"
            else -> "Sedentary"
        }
    }

    private fun calculateDuration(steps: Int): Int {
        // Rough estimate: 100 steps = ~1 minute
        return (steps / 100).coerceAtMost(60)
    }

    private fun calculateCalories(steps: Int): Int {
        // Rough estimate: 20 steps = ~1 calorie
        return (steps / 20).coerceAtLeast(0)
    }

    fun resetSession() {
        lastStepCount = stepCount
        lastResetTime = System.currentTimeMillis()
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            when (it.sensor.type) {
                Sensor.TYPE_STEP_COUNTER -> {
                    stepCount = it.values[0].toInt()
                }
                Sensor.TYPE_ACCELEROMETER -> {
                    val x = it.values[0]
                    val y = it.values[1]
                    val z = it.values[2]
                    val acceleration = Math.sqrt((x * x + y * y + z * z).toDouble())
                    isMoving = acceleration > 11.0 // Threshold for movement (gravity is ~9.8)
                }
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Not needed for this use case
    }

    fun unregister() {
        try {
            sensorManager.unregisterListener(this)
        } catch (e: Exception) {
            android.util.Log.e("ActivityRecognition", "Error unregistering: ${e.message}")
        }
    }

    data class ActivityData(
        val isExercising: Boolean,
        val activityType: String,
        val durationMinutes: Int,
        val caloriesBurned: Int
    )
}

