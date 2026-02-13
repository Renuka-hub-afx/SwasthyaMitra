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
    private var accelSensor: Sensor? = null
    
    // Core Logic
    private val stepVerifier = StepVerifier()
    private val binder = LocalBinder()
    private lateinit var activityRecognitionClient: com.google.android.gms.location.ActivityRecognitionClient
    private lateinit var activityUpdatePendingIntent: PendingIntent

    // Internal Receiver for activity updates forwarded by the global receiver
    private val activityUpdateReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == ACTION_ACTIVITY_UPDATE) {
                val type = intent.getIntExtra("activity_type", -1)
                val confidence = intent.getIntExtra("confidence", 0)
                
                if (type != -1) {
                    stepVerifier.currentActivityType = type
                    stepVerifier.currentConfidence = confidence
                    Log.d("StepCounterService", "Updated verifier activity: $type ($confidence%)")
                }
            }
        }
    }
    // Persistence
    private lateinit var prefs: SharedPreferences
    private var dailySteps = 0
    private var lastDate = ""
    private var lastSensorValue: Float? = null
    private var lastUpdateTime: Long = 0L

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
        accelSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        
        startForegroundService()
        registerSensors()
        
        // Initialize Activity Recognition
        activityRecognitionClient = com.google.android.gms.location.ActivityRecognition.getClient(this)
        val intent = Intent(this, com.example.swasthyamitra.receivers.ActivityUpdateReceiver::class.java)
        activityUpdatePendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        // Register local receiver for updates
        val filter = IntentFilter(ACTION_ACTIVITY_UPDATE)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            registerReceiver(activityUpdateReceiver, filter, Context.RECEIVER_NOT_EXPORTED)
        } else {
            registerReceiver(activityUpdateReceiver, filter)
        }
        
        requestActivityUpdates()
    }
    
    private fun requestActivityUpdates() {
        if (androidx.core.app.ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACTIVITY_RECOGNITION
            ) == android.content.pm.PackageManager.PERMISSION_GRANTED
        ) {
            activityRecognitionClient.requestActivityUpdates(3000L, activityUpdatePendingIntent)
                .addOnSuccessListener {
                    Log.d("StepCounterService", "Activity Recognition updates requested successfully")
                }
                .addOnFailureListener { e ->
                    Log.e("StepCounterService", "Failed to request activity updates", e)
                }
        } else {
            Log.w("StepCounterService", "Activity Recognition permission not granted")
        }
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
        } else {
            startForeground(NOTIFICATION_ID, notification)
        }
    }

    private fun registerSensors() {
        stepSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_FASTEST)
        }
        accelSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_GAME)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event ?: return
        when (event.sensor.type) {
            Sensor.TYPE_STEP_COUNTER -> processStepUpdate(event.values[0])
            Sensor.TYPE_ACCELEROMETER -> {
                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]
                val magnitude = Math.sqrt((x * x + y * y + z * z).toDouble())
                stepVerifier.currentMagnitude = magnitude
            }
        }
    }

    private fun processStepUpdate(rawSteps: Float) {
        val today = getTodayDate()
        
        if (today != lastDate) {
            dailySteps = 0
            lastDate = today
            lastSensorValue = rawSteps
            saveData()
            updateNotification()
            broadcastUpdate()
            return
        }

        if (lastSensorValue == null) {
            lastSensorValue = rawSteps
            return
        }
        
        if (rawSteps < lastSensorValue!!) {
            lastSensorValue = rawSteps
            return
        }

        val diff = (rawSteps - lastSensorValue!!).toInt()
        
        if (diff > 0) {
            var validatedSteps = 0
            // Process each step individually to satisfy interval/consistency checks
            for (i in 1..diff) {
                validatedSteps += stepVerifier.verifyStep()
            }

            if (validatedSteps > 0) {
                 dailySteps += validatedSteps
                 lastSensorValue = rawSteps
                 saveData()
                 updateNotification()
                 broadcastUpdate()
            } else {
                Log.d("StepCounterService", "Steps rejected by verifier")
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
        intent.setPackage(packageName)
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
        return SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
    }

    override fun onDestroy() {
        super.onDestroy()
        sensorManager.unregisterListener(this)
        try {
            unregisterReceiver(activityUpdateReceiver)
            activityRecognitionClient.removeActivityUpdates(activityUpdatePendingIntent)
        } catch (e: Exception) {
            Log.e("StepCounterService", "Error cleaning up", e)
        }
    }
}
