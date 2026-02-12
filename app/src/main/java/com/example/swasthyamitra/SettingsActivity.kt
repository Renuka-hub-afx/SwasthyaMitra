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

    private lateinit var switchSmartNotifications: SwitchMaterial
    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        sharedPrefs = getSharedPreferences("app_settings", Context.MODE_PRIVATE)

        switchSmartNotifications = findViewById(R.id.switchSmartNotifications)
        val btnBack = findViewById<ImageView>(R.id.btnBack)

        // Load saved state
        val isEnabled = sharedPrefs.getBoolean("smart_notifications_enabled", false)
        switchSmartNotifications.isChecked = isEnabled

        switchSmartNotifications.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                enableNotifications()
            } else {
                disableNotifications()
            }
        }

        btnBack.setOnClickListener { finish() }
    }

    private fun enableNotifications() {
        // Request Permission for Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                101
            )
        }

        // Save State
        sharedPrefs.edit().putBoolean("smart_notifications_enabled", true).apply()

        // Schedule Workers
        scheduleWorkers()
        
        Toast.makeText(this, "Smart Notifications Enabled! \uD83D\uDCA7", Toast.LENGTH_SHORT).show()
    }

    private fun disableNotifications() {
        // Save State
        sharedPrefs.edit().putBoolean("smart_notifications_enabled", false).apply()

        // Cancel Workers
        WorkManager.getInstance(this).cancelUniqueWork("WaterReminderWork")
        WorkManager.getInstance(this).cancelUniqueWork("MealEventWork")

        Toast.makeText(this, "Notifications Disabled", Toast.LENGTH_SHORT).show()
    }

    private fun scheduleWorkers() {
        val workManager = WorkManager.getInstance(this)

        // Water: Every 1 hour
        val waterRequest = PeriodicWorkRequestBuilder<WaterNotificationWorker>(1, TimeUnit.HOURS)
            .build()

        workManager.enqueueUniquePeriodicWork(
            "WaterReminderWork",
            ExistingPeriodicWorkPolicy.UPDATE, // Update existing (reschedule) or KEEP
            waterRequest
        )

        // Meal/Events: Checks every 1 hour (to be safe and catch meal windows)
        // If we want 15 mins for precision we can, but 1 hour is battery friendly and "roughly" ok for meal windows (7-9, 12-14, 19-21)
        val mealRequest = PeriodicWorkRequestBuilder<MealEventWorker>(1, TimeUnit.HOURS)
            .build()
            
        workManager.enqueueUniquePeriodicWork(
            "MealEventWork",
            ExistingPeriodicWorkPolicy.UPDATE,
            mealRequest
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 101) {
            if (grantResults.isNotEmpty() && grantResults[0] == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                // Permission granted
            } else {
                Toast.makeText(this, "Permission denied. Notifications won't show.", Toast.LENGTH_LONG).show()
                // Optionally toggle switch back off
                // switchSmartNotifications.isChecked = false
            }
        }
    }
}
