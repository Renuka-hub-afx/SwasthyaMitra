package com.example.swasthyamitra.services

import android.app.*
import android.content.*
import android.location.Location
import android.os.*
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.lifecycle.MutableLiveData
import com.example.swasthyamitra.MainActivity
import com.example.swasthyamitra.R
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
import android.media.RingtoneManager
import android.os.Vibrator
import com.example.swasthyamitra.models.WalkingSession
import com.example.swasthyamitra.safety.EmergencyContactManager
import com.example.swasthyamitra.safety.SafetyMonitorManager
import com.example.swasthyamitra.safety.SOSManager
import com.example.swasthyamitra.safety.EmergencyContact
import java.util.*

class TrackingService : Service() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var activityRecognitionClient: ActivityRecognitionClient
    private var activityPendingIntent: PendingIntent? = null
    
    private lateinit var emergencyContactManager: EmergencyContactManager
    private lateinit var sosManager: SOSManager
    private lateinit var safetyMonitorManager: SafetyMonitorManager

    private val countdownHandler = Handler(Looper.getMainLooper())
    private var countdownRunnable: Runnable? = null

    private var isTracking = false
    private var isPaused = false
    private var isStill = false
    private var stillStartTime: Long = 0L
    private val INACTIVITY_THRESHOLD_MS = 45000L // 45 seconds

    private var startSteps = -1
    private var currentSessionSteps = 0
    private var totalDistance = 0.0
    private var startTime = 0L
    private val pathPoints = mutableListOf<LatLng>()

    private val binder = LocalBinder()

    companion object {
        const val CHANNEL_ID = "TrackingChannel"
        const val NOTIFICATION_ID = 999
        
        val isTrackingLive = MutableLiveData<Boolean>(false)
        val pathPointsLive = MutableLiveData<List<LatLng>>(emptyList())
        val distanceLive = MutableLiveData<Double>(0.0)
        val stepsLive = MutableLiveData<Int>(0)
        val paceLive = MutableLiveData<String>("0'00")
        val isGhostModeLive = MutableLiveData<Boolean>(false)
        val isSOSActiveLive = MutableLiveData<Boolean>(false)
        val countdownLive = MutableLiveData<Int>(-1)
        
        const val ACTION_SAFETY_ALERT = "com.example.swasthyamitra.SAFETY_ALERT"
        const val ACTION_START_COUNTDOWN = "com.example.swasthyamitra.START_COUNTDOWN"
        const val ACTION_CANCEL_SOS = "com.example.swasthyamitra.CANCEL_SOS"
        const val ACTION_TRIGGER_SOS = "com.example.swasthyamitra.TRIGGER_SOS"
    }

    inner class LocalBinder : Binder() {
        fun getService(): TrackingService = this@TrackingService
    }

    override fun onBind(intent: Intent?): IBinder = binder

    override fun onCreate() {
        super.onCreate()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        activityRecognitionClient = ActivityRecognition.getClient(this)
        
        createNotificationChannel()
        
        emergencyContactManager = EmergencyContactManager(this)
        sosManager = SOSManager(this)
        safetyMonitorManager = SafetyMonitorManager(INACTIVITY_THRESHOLD_MS)

        // Listen for step updates
        val stepFilter = IntentFilter(StepCounterService.ACTION_UPDATE_STEPS)
        androidx.core.content.ContextCompat.registerReceiver(
            this,
            stepReceiver,
            stepFilter,
            androidx.core.content.ContextCompat.RECEIVER_NOT_EXPORTED
        )
    }

    private val stepReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val totalToday = intent?.getIntExtra("steps", 0) ?: 0
            if (isTracking && !isPaused) {
                if (startSteps == -1) {
                    startSteps = totalToday
                }
                currentSessionSteps = totalToday - startSteps
                stepsLive.postValue(currentSessionSteps)
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            "ACTION_START" -> startTracking()
            "ACTION_STOP" -> stopTracking()
            "ACTION_TOGGLE_GHOST" -> toggleGhostMode()
            ACTION_CANCEL_SOS -> cancelSOS()
            ACTION_TRIGGER_SOS -> triggerManualSOS(intent.getStringExtra("reason") ?: "Manual SOS")
            "ACTION_ACTIVITY_TRANSITION" -> {
                val type = intent.getIntExtra("activity_type", -1)
                handleActivityTransition(type)
            }
        }
        return START_STICKY
    }

    private fun toggleGhostMode() {
        val current = isGhostModeLive.value ?: false
        isGhostModeLive.postValue(!current)
        if (!current) {
            // Started Ghost Mode
            Log.d("TrackingService", "Ghost Mode Activated")
        }
    }

    private fun startTracking() {
        if (isTracking) return
        isTracking = true
        isTrackingLive.postValue(true)
        startTime = System.currentTimeMillis()
        startSteps = -1 // Reset to fetch current baseline
        pathPoints.clear()
        totalDistance = 0.0
        stillStartTime = 0L
        
        distanceLive.postValue(0.0)
        stepsLive.postValue(0)
        pathPointsLive.postValue(emptyList())

        startForeground(NOTIFICATION_ID, createNotification("Tracking your path..."))
        requestLocationUpdates()
        requestActivityUpdates()
    }

    private fun stopTracking() {
        isTracking = false
        isTrackingLive.postValue(false)
        removeLocationUpdates()
        removeActivityUpdates()
        saveSessionToFirestore()
        stopForeground(true)
        stopSelf()
    }

    private fun requestLocationUpdates() {
        val request = LocationRequest.Builder(Priority.PRIORITY_BALANCED_POWER_ACCURACY, 5000L)
            .setMinUpdateIntervalMillis(2000L)
            .build()

        try {
            fusedLocationClient.requestLocationUpdates(request, locationCallback, Looper.getMainLooper())
        } catch (e: SecurityException) {
            Log.e("TrackingService", "Location permission missing", e)
        }
    }

    private fun removeLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult) {
            if (!isTracking) return
            
            val location = result.lastLocation ?: return
            
            // NEW Safety Monitoring
            if (isGhostModeLive.value == true && !isPaused) {
                safetyMonitorManager.updateData(
                    currentSessionSteps, 
                    location.latitude, 
                    location.longitude,
                    isStill
                )

                if (safetyMonitorManager.isThresholdExceeded() && !isSOSActiveLive.value!! && countdownLive.value!! == -1) {
                    startSafetyCountdown()
                }
            }

            if (isPaused) return
            
            val newPoint = LatLng(location.latitude, location.longitude)
            
            if (pathPoints.isNotEmpty()) {
                val lastLoc = Location("last").apply {
                    latitude = pathPoints.last().latitude
                    longitude = pathPoints.last().longitude
                }
                totalDistance += lastLoc.distanceTo(location).toDouble()
                distanceLive.postValue(totalDistance)
            }
            
            pathPoints.add(newPoint)
            pathPointsLive.postValue(pathPoints.toList())
            
            // Pace calculation
            val timeMinutes = (System.currentTimeMillis() - startTime) / 60000.0
            val distKm = totalDistance / 1000.0
            if (distKm > 0.01) {
                val paceMinPerKm = timeMinutes / distKm
                val mins = paceMinPerKm.toInt()
                val secs = ((paceMinPerKm - mins) * 60).toInt()
                paceLive.postValue(String.format("%d'%02d", mins, secs))
            }
            
            updateNotification("Distance: ${String.format("%.2f", totalDistance / 1000)} km" + 
                if(isGhostModeLive.value == true) " [Ghost \uD83D\uDC7B]" else "")
        }
    }

    private fun requestActivityUpdates() {
        val intent = Intent(this, ActivityTransitionReceiver::class.java)
        activityPendingIntent = PendingIntent.getBroadcast(
            this, 1, intent, 
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        // Setup transitions
        val transitions = mutableListOf<ActivityTransition>()
        transitions.add(ActivityTransition.Builder()
            .setActivityType(DetectedActivity.STILL)
            .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER)
            .build())
        transitions.add(ActivityTransition.Builder()
            .setActivityType(DetectedActivity.WALKING)
            .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER)
            .build())
        transitions.add(ActivityTransition.Builder()
            .setActivityType(DetectedActivity.RUNNING)
            .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER)
            .build())

        val request = ActivityTransitionRequest(transitions)
        try {
            activityRecognitionClient.requestActivityTransitionUpdates(request, activityPendingIntent!!)
        } catch (e: SecurityException) {
            Log.e("TrackingService", "Activity Recognition permission missing", e)
        }
    }

    private fun removeActivityUpdates() {
        activityPendingIntent?.let {
            activityRecognitionClient.removeActivityTransitionUpdates(it)
        }
    }

    // Updated Activity Transition Logic
    fun handleActivityTransition(type: Int) {
        isStill = (type == DetectedActivity.STILL)
        
        // Feed into safety monitor immediately on transition
        if (isGhostModeLive.value == true && !isPaused) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    safetyMonitorManager.updateData(
                        currentSessionSteps,
                        it.latitude,
                        it.longitude,
                        isStill
                    )
                }
            }
        }

        if (isStill) {
            updateNotification("Status: Staying Still")
        } else {
            updateNotification("Status: Moving")
        }
    }

    private fun startSafetyCountdown() {
        Log.d("TrackingService", "Starting safety countdown")
        countdownLive.postValue(10)
        sendBroadcast(Intent(ACTION_START_COUNTDOWN))
        
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        val ringtone = RingtoneManager.getRingtone(applicationContext, alarmUri)

        countdownRunnable = object : Runnable {
            var secondsLeft = 10
            override fun run() {
                if (secondsLeft > 0) {
                    // Vibrate and sound periodically
                    vibrator.vibrate(200)
                    if (!ringtone.isPlaying) ringtone.play()

                    secondsLeft--
                    countdownLive.postValue(secondsLeft)
                    countdownHandler.postDelayed(this, 1000)
                } else {
                    // Time up! Trigger SOS
                    ringtone.stop()
                    triggerManualSOS("Automatic SOS - User Inactive")
                    countdownLive.postValue(-1)
                }
            }
        }
        countdownHandler.post(countdownRunnable!!)
    }

    private fun cancelSOS() {
        Log.d("TrackingService", "SOS Canceled by user")
        countdownRunnable?.let { countdownHandler.removeCallbacks(it) }
        countdownLive.postValue(-1)
        safetyMonitorManager.reset()
        isSOSActiveLive.postValue(false)
        
        // Also ensure GPS accuracy returns to balanced if it was boosted
        // (Actually, boost happens in trigger, so maybe not needed here)
    }

    private fun triggerManualSOS(reason: String) {
        if (isSOSActiveLive.value == true) return
        
        Log.d("TrackingService", "Triggering SOS: $reason")
        isSOSActiveLive.postValue(true)
        countdownLive.postValue(-1)
        countdownRunnable?.let { countdownHandler.removeCallbacks(it) }

        // Fetch contact and location to send SOS
        val contact = emergencyContactManager.getLocalContact()
        if (contact == null) {
            Log.e("TrackingService", "Cannot send SOS: No contact saved")
            return
        }

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                sosManager.sendSOS(contact, location.latitude, location.longitude, reason)
                // Boost GPS accuracy for continuous sharing
                boostGpsAccuracy()
            } else {
                Log.e("TrackingService", "Cannot send SOS: Location is null")
            }
        }
    }

    private fun boostGpsAccuracy() {
        // Switch to High Accuracy if SOS is active
        removeLocationUpdates()
        val request = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 5000L)
            .setMinUpdateIntervalMillis(2000L)
            .build()
        try {
            fusedLocationClient.requestLocationUpdates(request, locationCallback, Looper.getMainLooper())
        } catch (e: SecurityException) {
            Log.e("TrackingService", "Location permission missing for High Accuracy", e)
        }
    }

    private fun createNotification(content: String): Notification {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Walking Tracker")
            .setContentText(content)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .build()
    }

    private fun updateNotification(content: String) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, createNotification(content))
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, "Walking Tracking", NotificationManager.IMPORTANCE_LOW)
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    private fun saveSessionToFirestore() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val db = FirebaseFirestore.getInstance("renu")
        
        val endTime = System.currentTimeMillis()
        val durationMinutes = (endTime - startTime) / 60000.0
        val distanceKm = totalDistance / 1000.0
        val pace = if (distanceKm > 0) durationMinutes / distanceKm else 0.0

        val session = WalkingSession(
            userId = userId,
            startTime = startTime,
            endTime = endTime,
            totalSteps = currentSessionSteps,
            totalDistanceMeters = totalDistance,
            averagePace = pace,
            routePoints = pathPoints.map { GeoPoint(it.latitude, it.longitude) }
        )

        db.collection("users").document(userId).collection("walking_sessions").add(session)
            .addOnSuccessListener { Log.d("TrackingService", "Session saved") }
            .addOnFailureListener { e -> Log.e("TrackingService", "Failed to save session", e) }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(stepReceiver)
    }
}
