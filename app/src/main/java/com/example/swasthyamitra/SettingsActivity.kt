package com.example.swasthyamitra

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.swasthyamitra.notifications.MealEventWorker
import com.example.swasthyamitra.notifications.WaterNotificationWorker
import com.google.android.material.switchmaterial.SwitchMaterial
import java.util.concurrent.TimeUnit

class SettingsActivity : AppCompatActivity() {

    private lateinit var switchWater: SwitchMaterial
    private lateinit var switchBreakfast: SwitchMaterial
    private lateinit var switchLunch: SwitchMaterial
    private lateinit var switchDinner: SwitchMaterial
    private lateinit var switchEvents: SwitchMaterial
    
    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        sharedPrefs = getSharedPreferences("app_settings", Context.MODE_PRIVATE)

        switchWater = findViewById(R.id.switchWater)
        switchBreakfast = findViewById(R.id.switchBreakfast)
        switchLunch = findViewById(R.id.switchLunch)
        switchDinner = findViewById(R.id.switchDinner)
        switchEvents = findViewById(R.id.switchEvents)

        setupSwitch(switchWater, "pref_water", "WaterReminderWork")
        setupSwitch(switchBreakfast, "pref_breakfast", "MealEventWork")
        setupSwitch(switchLunch, "pref_lunch", "MealEventWork")
        setupSwitch(switchDinner, "pref_dinner", "MealEventWork")
        setupSwitch(switchEvents, "pref_events", "MealEventWork")

        findViewById<ImageView>(R.id.btnBack).setOnClickListener { finish() }
    }

    private fun setupSwitch(switch: SwitchMaterial, key: String, workerTag: String) {
        // Load State
        switch.isChecked = sharedPrefs.getBoolean(key, true) // Default true for now

        switch.setOnCheckedChangeListener { _, isChecked ->
            // Save State
            sharedPrefs.edit().putBoolean(key, isChecked).apply()
            
            if (isChecked) {
                checkPermission()
            }
            
            // Re-evaluate Workers
            updateWorkers(workerTag)
        }
    }

    private fun updateWorkers(tag: String) {
        val workManager = WorkManager.getInstance(this)
        
        if (tag == "WaterReminderWork") {
            if (sharedPrefs.getBoolean("pref_water", true)) {
                val waterRequest = PeriodicWorkRequestBuilder<WaterNotificationWorker>(1, TimeUnit.HOURS).build()
                workManager.enqueueUniquePeriodicWork("WaterReminderWork", ExistingPeriodicWorkPolicy.UPDATE, waterRequest)
            } else {
                workManager.cancelUniqueWork("WaterReminderWork")
            }
        } else if (tag == "MealEventWork") {
            // Only cancel if ALL meal/event prefs are false
            val b = sharedPrefs.getBoolean("pref_breakfast", true)
            val l = sharedPrefs.getBoolean("pref_lunch", true)
            val d = sharedPrefs.getBoolean("pref_dinner", true)
            val e = sharedPrefs.getBoolean("pref_events", true)
            
            if (b || l || d || e) {
                val mealRequest = PeriodicWorkRequestBuilder<MealEventWorker>(1, TimeUnit.HOURS).build()
                workManager.enqueueUniquePeriodicWork("MealEventWork", ExistingPeriodicWorkPolicy.UPDATE, mealRequest)
            } else {
                workManager.cancelUniqueWork("MealEventWork")
            }
        }
    }
    
    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 101)
        }
    }
    
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // Handle result if needed
    }
}
