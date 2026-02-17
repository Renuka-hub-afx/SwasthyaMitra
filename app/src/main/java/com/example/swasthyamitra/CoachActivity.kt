package com.example.swasthyamitra

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.swasthyamitra.data.repository.HydrationRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class CoachActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null
    private var lastPostureCheck = 0L
    private val POSTURE_CHECK_INTERVAL = 5000L 

    private lateinit var tvPostureAlert: TextView
    private lateinit var tvHydration: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coach)

        tvPostureAlert = findViewById(R.id.tvPostureAlert)
        tvHydration = findViewById(R.id.tvHydration)

        setupSensors()
        loadRealHydrationData()
    }

    private fun setupSensors() {
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI)
        } else {
            tvPostureAlert.text = "Accelerometer not found"
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            checkPosture(event)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    private fun checkPosture(event: SensorEvent) {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastPostureCheck > POSTURE_CHECK_INTERVAL) {
            lastPostureCheck = currentTime
            
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]
            
            if (y > 7.0 && Math.abs(x) < 2.0) {
                 tvPostureAlert.text = "⚠️ Posture Alert: You might be slouching! Straighten your back."
                 tvPostureAlert.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
            } else {
                 tvPostureAlert.text = "Posture: Good ✅"
                 tvPostureAlert.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark))
            }
        }
    }

    /**
     * Load real hydration data from Firebase and give contextual advice
     */
    private fun loadRealHydrationData() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId == null) {
            tvHydration.text = "Hydration: Please log in to see your data."
            return
        }

        lifecycleScope.launch {
            try {
                val hydrationRepo = HydrationRepository()
                val waterResult = hydrationRepo.getTodayWaterTotal(userId)
                val waterMl = waterResult.getOrDefault(0)
                val dailyGoal = 2000

                val message = when {
                    waterMl >= dailyGoal -> "💧 Great job! You've hit your water goal ($waterMl ml). Stay hydrated!"
                    waterMl >= dailyGoal * 0.75 -> "💧 Almost there! $waterMl / $dailyGoal ml. Just a few more glasses!"
                    waterMl >= dailyGoal * 0.5 -> "💧 You're halfway ($waterMl / $dailyGoal ml). Keep drinking water!"
                    waterMl > 0 -> "💧 $waterMl / $dailyGoal ml so far. Try to drink more water today!"
                    else -> "💧 No water logged today. Remember to drink at least $dailyGoal ml!"
                }

                runOnUiThread {
                    tvHydration.text = message
                    if (waterMl >= dailyGoal) {
                        tvHydration.setTextColor(ContextCompat.getColor(this@CoachActivity, android.R.color.holo_green_dark))
                    } else if (waterMl > 0) {
                        tvHydration.setTextColor(ContextCompat.getColor(this@CoachActivity, android.R.color.holo_blue_bright))
                    }
                }
            } catch (e: Exception) {
                runOnUiThread {
                    tvHydration.text = "Hydration: Remember to drink water regularly throughout the day."
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        // Unregister sensor listener to prevent memory leak
        sensorManager.unregisterListener(this)
    }

    override fun onResume() {
        super.onResume()
        // Re-register sensor listener
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        sensorManager.unregisterListener(this)
    }
}
