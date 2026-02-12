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
        checkHydrationLogic(28, 6.0) 
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

    private fun checkHydrationLogic(temperature: Int, distanceKm: Double) {
        val message = if (temperature > 30 && distanceKm >= 5) {
            "It's hot ($temperature°C) & you ran $distanceKm km. Drink 500ml water now!"
        } else if (temperature > 25 && distanceKm >= 5) {
             "Good run! Stay hydrated. Drink ~250ml water."
        } else {
             "Hydration: Remember to drink water regularly throughout the day."
        }
        
        tvHydration.text = message
        if (message.contains("Drink")) {
            tvHydration.setTextColor(ContextCompat.getColor(this, android.R.color.holo_blue_bright))
        }
    }
}
