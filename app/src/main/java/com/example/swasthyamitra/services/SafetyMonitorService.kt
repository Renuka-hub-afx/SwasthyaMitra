package com.example.swasthyamitra.services

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.os.Looper
import android.telephony.SmsManager
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.gms.location.*

class SafetyMonitorService : Service() {

    companion object {
        const val ACTION_INACTIVITY_WARNING = "com.example.swasthyamitra.INACTIVITY_WARNING"
        const val ACTION_SOS_TRIGGERED = "com.example.swasthyamitra.SOS_TRIGGERED"
        private const val NOTIFICATION_ID = 1001
        private const val CHANNEL_ID = "SafetyMonitorChannel"
        private const val INACTIVITY_THRESHOLD_MS = 5 * 60 * 1000L // 5 minutes
    }

    private val binder = LocalBinder()
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    
    var totalDistance = 0.0
        private set
    
    private var lastLocation: Location? = null
    private var lastMovementTime = System.currentTimeMillis()
    private var isTracking = false

    inner class LocalBinder : Binder() {
        fun getService(): SafetyMonitorService = this@SafetyMonitorService
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    override fun onCreate() {
        super.onCreate()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        setupLocationCallback()
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            "START_TRACKING" -> startTracking()
            "STOP_TRACKING" -> stopTracking()
        }
        return START_STICKY
    }

    private fun setupLocationCallback() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.lastLocation?.let { location ->
                    handleLocationUpdate(location)
                }
            }
        }
    }

    private fun startTracking() {
        if (isTracking) return
        
        val notification = createNotification("Safety Ghost Active", "Monitoring your run...")
        startForeground(NOTIFICATION_ID, notification)
        
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) 
            == PackageManager.PERMISSION_GRANTED) {
            
            val locationRequest = LocationRequest.Builder(
                Priority.PRIORITY_HIGH_ACCURACY,
                5000L // 5 seconds
            ).build()
            
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
            
            isTracking = true
            lastMovementTime = System.currentTimeMillis()
            Log.d("SafetyMonitorService", "Tracking started")
        }
    }

    private fun stopTracking() {
        if (!isTracking) return
        
        fusedLocationClient.removeLocationUpdates(locationCallback)
        isTracking = false
        totalDistance = 0.0
        lastLocation = null
        
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
        
        Log.d("SafetyMonitorService", "Tracking stopped")
    }

    private fun handleLocationUpdate(location: Location) {
        lastLocation?.let { previous ->
            val distance = previous.distanceTo(location)
            if (distance > 5) { // Minimum 5 meters to count as movement
                totalDistance += distance
                lastMovementTime = System.currentTimeMillis()
                
                updateNotification("Distance: ${String.format("%.2f", totalDistance / 1000)} km")
            }
        }
        
        lastLocation = location
        checkInactivity()
    }

    private fun checkInactivity() {
        val timeSinceMovement = System.currentTimeMillis() - lastMovementTime
        if (timeSinceMovement > INACTIVITY_THRESHOLD_MS) {
            sendInactivityWarning()
        }
    }

    private fun sendInactivityWarning() {
        val intent = Intent(ACTION_INACTIVITY_WARNING)
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
        Log.d("SafetyMonitorService", "Inactivity warning sent")
    }

    fun sendSOS(contactNumber: String, reason: String) {
        val location = lastLocation
        val locationUrl = if (location != null) {
            "http://maps.google.com/maps?q=${location.latitude},${location.longitude}"
        } else {
            "Location unavailable"
        }
        
        val message = "SOS! $reason\nLocation: $locationUrl"
        
        try {
            val smsManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                getSystemService(SmsManager::class.java)
            } else {
                SmsManager.getDefault()
            }
            
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) 
                == PackageManager.PERMISSION_GRANTED) {
                smsManager.sendTextMessage(contactNumber, null, message, null, null)
                
                val intent = Intent(ACTION_SOS_TRIGGERED)
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
                
                Log.d("SafetyMonitorService", "SOS sent to $contactNumber")
            }
        } catch (e: Exception) {
            Log.e("SafetyMonitorService", "Failed to send SOS", e)
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Safety Monitor",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Monitors your safety during runs"
            }
            
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    private fun createNotification(title: String, content: String): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(content)
            .setSmallIcon(android.R.drawable.ic_menu_mylocation)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setOngoing(true)
            .build()
    }

    private fun updateNotification(content: String) {
        val notification = createNotification("Safety Ghost Active", content)
        val manager = getSystemService(NotificationManager::class.java)
        manager.notify(NOTIFICATION_ID, notification)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isTracking) {
            fusedLocationClient.removeLocationUpdates(locationCallback)
        }
    }
}
