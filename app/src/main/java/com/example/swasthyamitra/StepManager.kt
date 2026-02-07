package com.example.swasthyamitra

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.swasthyamitra.services.StepCounterService

/**
 * Client wrapper for StepCounterService.
 * UI components use this to get updates without managing Service binding manually.
 */
class StepManager(private val context: Context, private val onStepUpdate: (Int, Double) -> Unit) {

    private val stepReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == StepCounterService.ACTION_UPDATE_STEPS) {
                val steps = intent.getIntExtra("steps", 0)
                val calories = intent.getDoubleExtra("calories", 0.0)
                Log.d("StepManager", "Received update: $steps steps")
                onStepUpdate(steps, calories)
            }
        }
    }

    private var isRegistered = false

    fun start() {
        // 1. Start the Foreground Service (if not running)
        val serviceIntent = Intent(context, StepCounterService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(serviceIntent)
        } else {
            context.startService(serviceIntent)
        }

        // 2. Register for updates
        // Note: We used global sendBroadcast in Service, so we register global receiver.
        // For better security/performance, LocalBroadcastManager is preferred if within app process,
        // but Service and Activity are same process here. Service used sendBroadcast().
        if (!isRegistered) {
            val filter = IntentFilter(StepCounterService.ACTION_UPDATE_STEPS)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.registerReceiver(stepReceiver, filter, Context.RECEIVER_NOT_EXPORTED)
            } else {
                context.registerReceiver(stepReceiver, filter)
            }
            isRegistered = true
        }
        
        // 3. Trigger initial fetch from Prefs for instant UI
        val prefs = context.getSharedPreferences("StepCounterPrefs", Context.MODE_PRIVATE)
        val savedSteps = prefs.getInt("daily_steps", 0)
        val calories = savedSteps * 0.04
        onStepUpdate(savedSteps, calories)
    }

    fun stop() {
        if (isRegistered) {
            try {
                context.unregisterReceiver(stepReceiver)
                isRegistered = false
            } catch (e: Exception) {
                Log.e("StepManager", "Error unregistering receiver", e)
            }
        }
    }
}
