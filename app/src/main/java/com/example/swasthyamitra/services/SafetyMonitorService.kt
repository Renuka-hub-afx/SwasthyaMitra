package com.example.swasthyamitra.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.telephony.SmsManager

class SafetyMonitorService : Service() {

    private val binder = LocalBinder()
    var totalDistance: Double = 0.0
    
    companion object {
        const val ACTION_SOS_TRIGGERED = "com.example.swasthyamitra.ACTION_SOS_TRIGGERED"
        const val ACTION_INACTIVITY_WARNING = "com.example.swasthyamitra.ACTION_INACTIVITY_WARNING"
    }

    inner class LocalBinder : Binder() {
        fun getService(): SafetyMonitorService = this@SafetyMonitorService
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action) {
            "START_TRACKING" -> startTracking()
            "STOP_TRACKING" -> stopTracking()
        }
        return START_STICKY
    }

    private fun startTracking() {
        Log.d("SafetyMonitorService", "Tracking started")
        // Initialize location tracking... logic would go here
    }

    private fun stopTracking() {
        Log.d("SafetyMonitorService", "Tracking stopped")
    }

    fun sendSOS(contact: String, reason: String) {
        // Send SMS
         try {
             val smsManager = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                 getSystemService(SmsManager::class.java)
             } else {
                 SmsManager.getDefault()
             }
             smsManager.sendTextMessage(contact, null, "SOS ALERT: $reason", null, null)
         } catch (e: Exception) {
             Log.e("SafetyMonitorService", "Failed to send SOS", e)
         }
    }
}
