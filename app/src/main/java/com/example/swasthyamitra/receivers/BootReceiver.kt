package com.example.swasthyamitra.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.swasthyamitra.notifications.MealEventWorker
import com.example.swasthyamitra.notifications.WaterNotificationWorker
import com.example.swasthyamitra.services.StepCounterService
import java.util.concurrent.TimeUnit

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED || intent.action == "android.intent.action.QUICKBOOT_POWERON") {
            Log.d("BootReceiver", "Boot completed, restarting services and workers")
            
            // Restart step counter service
            val serviceIntent = Intent(context, StepCounterService::class.java)
            serviceIntent.action = StepCounterService.ACTION_START
            
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(serviceIntent)
            } else {
                context.startService(serviceIntent)
            }

            // Re-enqueue notification workers (WorkManager survives reboots but
            // AlarmManager-based reminders are lost, so ensure workers are active)
            try {
                val prefs = context.getSharedPreferences("app_settings", Context.MODE_PRIVATE)
                val workManager = WorkManager.getInstance(context)

                if (prefs.getBoolean("pref_water", true)) {
                    val waterRequest = PeriodicWorkRequestBuilder<WaterNotificationWorker>(
                        1, TimeUnit.HOURS
                    ).build()
                    workManager.enqueueUniquePeriodicWork(
                        "WaterReminderWork",
                        ExistingPeriodicWorkPolicy.KEEP,
                        waterRequest
                    )
                }

                val b = prefs.getBoolean("pref_breakfast", true)
                val l = prefs.getBoolean("pref_lunch", true)
                val d = prefs.getBoolean("pref_dinner", true)
                val e = prefs.getBoolean("pref_events", true)
                if (b || l || d || e) {
                    val mealRequest = PeriodicWorkRequestBuilder<MealEventWorker>(
                        1, TimeUnit.HOURS
                    ).build()
                    workManager.enqueueUniquePeriodicWork(
                        "MealEventWork",
                        ExistingPeriodicWorkPolicy.KEEP,
                        mealRequest
                    )
                }
                Log.d("BootReceiver", "Notification workers re-enqueued after boot")
            } catch (e: Exception) {
                Log.e("BootReceiver", "Error re-enqueuing workers: ${e.message}")
            }
        }
    }
}
