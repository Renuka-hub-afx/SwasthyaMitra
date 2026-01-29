package com.example.swasthyamitra

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

class StepManager(private val context: Context, private val onStepUpdate: (Int, Double) -> Unit) : SensorEventListener {
    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
    
    private val prefs = context.getSharedPreferences("StepCounterPrefs", Context.MODE_PRIVATE)
    
    var dailySteps: Int = 0
    private var lastSensorValue: Float = 0f
    private var lastDate: String = ""

    init {
        loadDailySteps()
    }

    private fun loadDailySteps() {
        val today = getTodayDate()
        lastDate = prefs.getString("last_date", "") ?: ""
        
        if (today != lastDate) {
            // New day: Reset steps and update date
            dailySteps = 0
            lastDate = today
            // We set lastSensorValue to -1 to capture the first value of the new day
            lastSensorValue = -1f 
            saveData()
        } else {
            dailySteps = prefs.getInt("daily_steps", 0)
            lastSensorValue = prefs.getFloat("last_sensor_value", -1f)
        }
    }

    private fun saveData() {
        prefs.edit().apply {
            putInt("daily_steps", dailySteps)
            putFloat("last_sensor_value", lastSensorValue)
            putString("last_date", lastDate)
            apply()
        }
        
        // Also persist to Firebase for historical insights
        val userId = com.google.firebase.auth.FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val db = com.google.firebase.database.FirebaseDatabase.getInstance("https://swasthyamitra-c0899-default-rtdb.asia-southeast1.firebasedatabase.app").reference
            db.child("dailyActivity").child(userId).child(lastDate).child("steps").setValue(dailySteps)
        }
    }

    fun start() {
        stepSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI)
        }
        // Trigger initial update with stored values
        val caloriesBurned = dailySteps * 0.04
        onStepUpdate(dailySteps, caloriesBurned)
    }

    fun stop() {
        sensorManager.unregisterListener(this)
        saveData()
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_STEP_COUNTER) {
            val currentSensorValue = event.values[0]
            val today = getTodayDate()

            // 1. Date Change Detection (Mid-session)
            if (today != lastDate) {
                dailySteps = 0
                lastDate = today
                lastSensorValue = currentSensorValue
                saveData()
            }

            // 2. Initial value after start/reset
            if (lastSensorValue < 0) {
                lastSensorValue = currentSensorValue
                saveData()
                return
            }

            // 3. Reboot Detection
            // Sensor value resets to 0 on reboot. If current < last, a reboot happened.
            if (currentSensorValue < lastSensorValue) {
                // Treat as if we just started from 0
                lastSensorValue = 0f
            }

            // 4. Calculate Delta
            val delta = (currentSensorValue - lastSensorValue).toInt()
            if (delta > 0) {
                dailySteps += delta
                lastSensorValue = currentSensorValue
                saveData()
                
                // Estimate calories: ~0.04 kcal per step
                val caloriesBurned = dailySteps * 0.04
                onStepUpdate(dailySteps, caloriesBurned)
            } else if (delta == 0) {
                // Just update UI with current count even if no movement
                val caloriesBurned = dailySteps * 0.04
                onStepUpdate(dailySteps, caloriesBurned)
            }
        }
    }

    private fun getTodayDate(): String {
        return java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(java.util.Date())
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}
