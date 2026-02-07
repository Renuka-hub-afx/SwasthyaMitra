package com.example.swasthyamitra.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.content.pm.ServiceInfo
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.swasthyamitra.MainActivity
import com.example.swasthyamitra.R
import com.example.swasthyamitra.features.steps.StepVerifier
// Temporarily commented out due to dependency issues
// import com.google.android.gms.location.ActivityRecognition
// import com.google.android.gms.location.ActivityRecognitionResult
// import com.google.android.gms.location.DetectedActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class StepCounterService : Service(), SensorEventListener {

    companion object {
        const val NOTIFICATION_ID = 888
        const val CHANNEL_ID = "StepCounterChannel"
        const val ACTION_UPDATE_STEPS = "com.example.swasthyamitra.UPDATE_STEPS"
        const val ACTION_ACTIVITY_UPDATE = "com.example.swasthyamitra.ACTIVITY_UPDATE"
    }

    private lateinit var sensorManager: SensorManager
    private var stepSensor: Sensor? = null
    
    // Core Logic
    private val stepVerifier = StepVerifier()
    private val binder = LocalBinder()
    
    // Persistence
    private lateinit var prefs: SharedPreferences
    private var dailySteps = 0
    private var lastDate = ""
    private var lastSensorValue: Float? = null
    private var lastUpdateTime: Long = 0L
    
    // Activity Recognition
    private var activityPendingIntent: PendingIntent? = null

    inner class LocalBinder : Binder() {
        fun getService(): StepCounterService = this@StepCounterService
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("StepCounterService", "Service Created")
        
        prefs = getSharedPreferences("StepCounterPrefs", Context.MODE_PRIVATE)
        loadData()
        
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        
        startForegroundService()
        registerSensors()
        // Temporarily disabled Activity Recognition
        // requestActivityUpdates()
    }
    
    private fun startForegroundService() {
        createNotificationChannel()
        
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, notificationIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("SwasthyaMitra Step Tracker")
            .setContentText("Steps today: $dailySteps")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .build()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            startForeground(
                NOTIFICATION_ID,
                notification,
                ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC
            )
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            startForeground(NOTIFICATION_ID, notification)
        } else {
            startForeground(NOTIFICATION_ID, notification)
        }
    }

    private fun registerSensors() {
        stepSensor?.let {
            // Using FASTEST to minimize hardware batching latency
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_FASTEST)
        }
    }
    
    /*
    // Temporarily disabled due to dependency issues
    private fun requestActivityUpdates() {
        val intent = Intent(this, StepCounterService::class.java)
        intent.action = ACTION_ACTIVITY_UPDATE
        
        activityPendingIntent = PendingIntent.getService(
            this,
            1,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
        )

        val client = ActivityRecognition.getClient(this)
        client.requestActivityUpdates(3000, activityPendingIntent!!) // 3 seconds detection
            .addOnSuccessListener { Log.d("StepCounterService", "Activity Recognition Registered") }
            .addOnFailureListener { e -> Log.e("StepCounterService", "Activity Recognition Failed", e) }
    }
    */

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Temporarily disabled Activity Recognition
        // if (intent?.action == ACTION_ACTIVITY_UPDATE) {
        //     handleActivityUpdate(intent)
        // }
        return START_STICKY
    }
    
    /*
    // Temporarily disabled due to dependency issues
    private fun handleActivityUpdate(intent: Intent) {
        if (ActivityRecognitionResult.hasResult(intent)) {
            val result = ActivityRecognitionResult.extractResult(intent)
            result?.mostProbableActivity?.let { activity ->
                Log.d("StepCounterService", "Activity: ${activity.type} Conf: ${activity.confidence}")
                
                val isMoving = when (activity.type) {
                    DetectedActivity.WALKING, DetectedActivity.RUNNING, DetectedActivity.ON_FOOT -> true
                    DetectedActivity.STILL, DetectedActivity.IN_VEHICLE, DetectedActivity.TILTING -> false
                    else -> true // Unknown usually means movement or transition, be permissive unless confident STILL
                }
                
                // If high confidence STILL, gate the steps
                if (activity.type == DetectedActivity.STILL && activity.confidence > 70) {
                    stepVerifier.isWalkingOrRunning = false
                } else if (activity.type == DetectedActivity.IN_VEHICLE && activity.confidence > 80) {
                    stepVerifier.isWalkingOrRunning = false // Reduce false positives in car
                } else if ((activity.type == DetectedActivity.WALKING || activity.type == DetectedActivity.RUNNING) && activity.confidence > 50) {
                    stepVerifier.isWalkingOrRunning = true
                } else {
                     // Default permsissive if unknown or low confidence
                     stepVerifier.isWalkingOrRunning = true
                }
            }
        }
    }
    */

    override fun onSensorChanged(event: SensorEvent?) {
        event ?: return
        if (event.sensor.type == Sensor.TYPE_STEP_COUNTER) {
            processStepUpdate(event.values[0])
        }
    }

    private fun processStepUpdate(rawSteps: Float) {
        val today = getTodayDate()
        
        if (today != lastDate) {
            // New Day: Reset
            dailySteps = 0
            lastDate = today
            lastSensorValue = rawSteps
            saveData()
            updateNotification()
            broadcastUpdate()
            return
        }

        if (lastSensorValue == null) {
            // Initial read (service start)
            lastSensorValue = rawSteps
            // We just set the baseline, we don't add to dailySteps yet 
            // because rawSteps is total-since-boot, not daily. 
            // dailySteps comes from Prefs.
            return
        }
        
        // Handle Reboot (Sensor reset to 0)
        if (rawSteps < lastSensorValue!!) {
            lastSensorValue = rawSteps
            return
        }

        val diff = (rawSteps - lastSensorValue!!).toInt()
        
        if (diff > 0) {
            val currentTime = System.currentTimeMillis()
            val timeDelta = if (lastUpdateTime > 0) currentTime - lastUpdateTime else 1000L
            
            // Anti-Cheat: 1. Rate Limiting (Reject shaking)
            // If the rate is impossibly high (e.g. > 6 steps/sec), reject it.
            if (!stepVerifier.validateBatch(diff, timeDelta)) {
                // Reject update, but DO update timestamp to prevent "catching up" next time with a huge delta
                lastUpdateTime = currentTime
                // Do NOT update lastSensorValue yet, so next valid update will incldue these steps? 
                // NO. If we reject "shaking", we must consume the raw value so we don't count them later.
                lastSensorValue = rawSteps
                Log.d("StepCounterService", "Ignored $diff steps due to high rate (shaking)")
                return
            }
            
            // Anti-Cheat: 2. Activity Gating
            // If Activity Recognition says "STILL" for a long time, we might ignore this.
            // But sometimes AR is laggy. 
            // We trust AR if it's confident we are NOT walking.
            
            if (stepVerifier.isWalkingOrRunning) {
                 dailySteps += diff
                 lastUpdateTime = currentTime
                 lastSensorValue = rawSteps
                 saveData()
                 updateNotification()
                 broadcastUpdate()
            } else {
                Log.d("StepCounterService", "Ignored $diff steps (Activity: STILL/Driving)")
                // We update lastSensorValue anyway so we don't process these steps later
                lastSensorValue = rawSteps 
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    private fun saveData() {
        prefs.edit().apply {
            putInt("daily_steps", dailySteps)
            putFloat("last_sensor_value", lastSensorValue ?: -1f)
            putString("last_date", lastDate)
            apply()
        }
        
        // Firebase Sync
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            try {
                FirebaseDatabase.getInstance("https://swasthyamitra-ded44-default-rtdb.asia-southeast1.firebasedatabase.app")
                    .getReference("dailyActivity").child(userId).child(lastDate).child("steps")
                    .setValue(dailySteps)
            } catch (e: Exception) {}
        }
    }

    private fun loadData() {
        val today = getTodayDate()
        val savedDate = prefs.getString("last_date", "") ?: ""
        
        if (savedDate == today) {
            dailySteps = prefs.getInt("daily_steps", 0)
            lastSensorValue = prefs.getFloat("last_sensor_value", -1f)
            if (lastSensorValue == -1f) lastSensorValue = null
            lastDate = savedDate
        } else {
            dailySteps = 0
            lastDate = today
            lastSensorValue = null
        }
    }

    private fun updateNotification() {
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("SwasthyaMitra Step Tracker")
            .setContentText("Steps today: $dailySteps")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setOngoing(true)
            .setOnlyAlertOnce(true)
            .build()
            
        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.notify(NOTIFICATION_ID, notification)
    }
    
    private fun broadcastUpdate() {
        val intent = Intent(ACTION_UPDATE_STEPS)
        intent.putExtra("steps", dailySteps)
        val calories = dailySteps * 0.04
        intent.putExtra("calories", calories)
        intent.setPackage(packageName) // Security
        sendBroadcast(intent)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Step Counter Service",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }

    private fun getTodayDate(): String {
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
    }

    override fun onDestroy() {
        super.onDestroy()
        sensorManager.unregisterListener(this)
        // Temporarily disabled Activity Recognition
        // activityPendingIntent?.let {
        //      ActivityRecognition.getClient(this).removeActivityUpdates(it)
        // }
    }
}
