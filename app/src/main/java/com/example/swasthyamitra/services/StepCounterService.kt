package com.example.swasthyamitra.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.lifecycle.MutableLiveData
import com.example.swasthyamitra.R
import com.example.swasthyamitra.homepage
import com.example.swasthyamitra.utils.StepTracker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.fixedRateTimer

class StepCounterService : Service() {

    private var stepTracker: StepTracker? = null
    private var currentSteps = 0
    private var currentCalories = 0
    private var sessionStartTime = 0L
    private var saveTimer: Timer? = null
    
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    
    companion object {
        private const val NOTIFICATION_ID = 1001
        private const val CHANNEL_ID = "step_counter_channel"
        
        // LiveData for broadcasting to UI
        val stepsLive = MutableLiveData<Int>()
        val caloriesLive = MutableLiveData<Int>()
        val isRunningLive = MutableLiveData<Boolean>()
        
        const val ACTION_START = "ACTION_START"
        const val ACTION_STOP = "ACTION_STOP"
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        isRunningLive.postValue(false)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START -> startTracking()
            ACTION_STOP -> stopTracking()
        }
        return START_STICKY
    }

    private fun startTracking() {
        if (stepTracker != null) {
            Log.d("StepCounterService", "Already tracking")
            return
        }
        
        sessionStartTime = System.currentTimeMillis()
        
        // Fetch existing step data from Firestore for today
        fetchTodayStepsFromFirestore { existingSteps, existingCalories ->
            // Set initial values from Firestore (or 0 if no data)
            currentSteps = existingSteps
            currentCalories = existingCalories
            
            // Broadcast initial values to UI
            stepsLive.postValue(currentSteps)
            caloriesLive.postValue(currentCalories)
            
            Log.d("StepCounterService", "Resuming from Firestore: $currentSteps steps, $currentCalories kcal")
            
            // Start foreground service with notification
            startForeground(NOTIFICATION_ID, createNotification())
            
            // Initialize step tracker with offset
            val baseSteps = currentSteps
            stepTracker = StepTracker(this) { newSteps ->
                // Add new steps to existing count
                currentSteps = baseSteps + newSteps
                currentCalories = com.example.swasthyamitra.utils.CalorieCalculator.calculateFromStepsInt(currentSteps)
                
                // Broadcast to UI
                stepsLive.postValue(currentSteps)
                caloriesLive.postValue(currentCalories)
                
                // Update notification
                updateNotification()
            }
            
            if (stepTracker?.isSensorAvailable() == true) {
                stepTracker?.start()
                isRunningLive.postValue(true)
                
                // Start periodic save timer (every 5 minutes)
                startPeriodicSave()
                
                Log.d("StepCounterService", "Step tracking started")
            } else {
                Log.e("StepCounterService", "Accelerometer not available")
                stopSelf()
            }
        }
    }
    
    // Fetch today's step data from Firestore
    private fun fetchTodayStepsFromFirestore(callback: (Int, Int) -> Unit) {
        val userId = auth.currentUser?.uid
        if (userId == null) {
            Log.w("StepCounterService", "User not logged in, starting from 0")
            callback(0, 0)
            return
        }
        
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val docRef = db.collection("users").document(userId)
            .collection("daily_steps").document(today)
        
        docRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val steps = document.getLong("steps")?.toInt() ?: 0
                    val calories = document.getLong("calories")?.toInt() ?: 0
                    Log.d("StepCounterService", "Found existing data: $steps steps, $calories kcal")
                    callback(steps, calories)
                } else {
                    Log.d("StepCounterService", "No existing data for today, starting from 0")
                    callback(0, 0)
                }
            }
            .addOnFailureListener { e ->
                Log.e("StepCounterService", "Failed to fetch existing data", e)
                callback(0, 0)
            }
    }

    private fun stopTracking() {
        // Save final data to Firestore
        saveToFirestore()
        
        // Stop timer
        saveTimer?.cancel()
        saveTimer = null
        
        // Stop step tracker
        stepTracker?.stop()
        stepTracker = null
        
        isRunningLive.postValue(false)
        
        // Stop foreground service
        stopForeground(true)
        stopSelf()
        
        Log.d("StepCounterService", "Step tracking stopped")
    }

    private fun startPeriodicSave() {
        // Save to Firestore every 5 minutes
        saveTimer = fixedRateTimer("FirestoreSave", false, 5 * 60 * 1000L, 5 * 60 * 1000L) {
            saveToFirestore()
        }
    }

    private fun saveToFirestore() {
        val userId = auth.currentUser?.uid ?: return
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        
        if (currentSteps == 0) return // Nothing to save
        
        val docRef = db.collection("users").document(userId)
            .collection("daily_steps").document(today)
        
        // Save absolute values (not increment) since we resume from existing data
        val data = hashMapOf(
            "date" to today,
            "steps" to currentSteps,  // Absolute value
            "calories" to currentCalories,  // Absolute value
            "lastUpdated" to FieldValue.serverTimestamp()
        )
        
        // Add session info
        val session = hashMapOf(
            "startTime" to sessionStartTime,
            "endTime" to System.currentTimeMillis(),
            "steps" to currentSteps,
            "calories" to currentCalories
        )
        
        docRef.set(data, SetOptions.merge())
            .addOnSuccessListener {
                // Add session to array
                docRef.update("sessions", FieldValue.arrayUnion(session))
                Log.d("StepCounterService", "Saved to Firestore: $currentSteps steps, $currentCalories kcal")
            }
            .addOnFailureListener { e ->
                Log.e("StepCounterService", "Failed to save to Firestore", e)
            }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Step Counter",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Shows live step count and calories burned"
                setShowBadge(false)
            }
            
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    private fun createNotification(): Notification {
        val intent = Intent(this, homepage::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("🚶 Step Counter Active")
            .setContentText("Steps: $currentSteps | Calories: $currentCalories kcal")
            .setSmallIcon(R.drawable.ic_walk)
            .setOngoing(true)
            .setContentIntent(pendingIntent)
            .build()
    }

    private fun updateNotification() {
        val notification = createNotification()
        val manager = getSystemService(NotificationManager::class.java)
        manager.notify(NOTIFICATION_ID, notification)
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        stopTracking()
    }
}
