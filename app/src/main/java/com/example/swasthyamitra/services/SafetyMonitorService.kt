package com.example.swasthyamitra.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class SafetyMonitorService : Service() {

    private val binder = LocalBinder()
    var totalDistance: Double = 0.0

    companion object {
        const val ACTION_INACTIVITY_WARNING = "com.example.swasthyamitra.action.INACTIVITY_WARNING"
        const val ACTION_SOS_TRIGGERED = "com.example.swasthyamitra.action.SOS_TRIGGERED"
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action == "START_TRACKING") {
            startTracking()
        } else if (intent?.action == "STOP_TRACKING") {
            stopTracking()
        }
        return START_STICKY
    }

    private fun startTracking() {
        Log.d("SafetyMonitorService", "Tracking started")
        // Logic to track distance and safety would go here
    }

    private fun stopTracking() {
        Log.d("SafetyMonitorService", "Tracking stopped")
    }

    fun sendSOS(contact: String, reason: String) {
        Log.d("SafetyMonitorService", "Sending SOS to $contact reason: $reason")
        val intent = Intent(ACTION_SOS_TRIGGERED)
        sendBroadcast(intent)
    }

    inner class LocalBinder : Binder() {
        fun getService(): SafetyMonitorService = this@SafetyMonitorService
    }
}
