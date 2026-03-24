package com.example.swasthyamitra.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
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

/**
 * StepCounterService is a Foreground Service that tracks steps in the background.
 * It uses the hardware accelerometer (via StepTracker) to detect motion and 
 * calculates steps and calories burned. Results are synchronized to Firestore 
 * periodically and displayed in a persistent notification.
 */
class StepCounterService : Service() {

    private var stepTracker: StepTracker? = null
    private var currentSteps = 0
    private var currentCalories = 0
    private var sessionStartTime = 0L
    private var sessionStartDate = ""  // Used to handle date transitions (midnight)
    private var saveTimer: Timer? = null // Periodic timer for cloud synchronization
    
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    
    companion object {
        private const val NOTIFICATION_ID = 1001
        private const val CHANNEL_ID = "step_counter_channel"
        
        // LiveData used by UI components to observe real-time tracking progress
        val stepsLive = MutableLiveData<Int>()
        val caloriesLive = MutableLiveData<Int>()
        val isRunningLive = MutableLiveData<Boolean>()
        
        // Actions for controlling the service via Intents
        const val ACTION_START = "ACTION_START"
        const val ACTION_STOP = "ACTION_STOP"
        const val ACTION_UPDATE_STEPS = "com.example.swasthyamitra.step.UPDATE_STEPS"
    }

    override fun onCreate() {
        super.onCreate()
        // Ensure the notification channel exists for the background service
        createNotificationChannel()
        isRunningLive.postValue(false)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Route intent actions to their respective control functions
        when (intent?.action) {
            ACTION_START -> startTracking()
            ACTION_STOP -> stopTracking()
        }
        // START_STICKY ensures the service restarts if it's killed by the OS
        return START_STICKY
    }

    /**
     * Resumes or starts the step tracking process.
     * It first fetches today's data from Firestore to ensure continuity.
     */
    private fun startTracking() {
        if (stepTracker != null) {
            Log.d("StepCounterService", "Already tracking")
            return
        }
        
        // Promote service to Foreground within the required 5-second window
        startForeground(NOTIFICATION_ID, createNotification())
        
        sessionStartTime = System.currentTimeMillis()
        sessionStartDate = dateFormat.format(Date())
        
        // Fetch existing step data from Firestore for today to resume from the last point
        fetchTodayStepsFromFirestore { existingSteps, existingCalories ->
            // Initialize local counters with cloud data
            currentSteps = existingSteps
            currentCalories = existingCalories
            
            // Update UI observers immediately
            stepsLive.postValue(currentSteps)
            caloriesLive.postValue(currentCalories)
            
            Log.d("StepCounterService", "Resuming from Firestore: $currentSteps steps, $currentCalories kcal")
            
            // Sync notification text with the loaded step count
            updateNotification()
            
            // Initialize the step tracker with the current base count
            val baseSteps = currentSteps
            stepTracker = StepTracker(this) { newSteps ->
                // Calculate total steps by adding newly detected steps to the starting base
                currentSteps = baseSteps + newSteps
                currentCalories = com.example.swasthyamitra.utils.CalorieCalculator.calculateFromStepsInt(currentSteps)
                
                // 1. Update UI observers via LiveData
                stepsLive.postValue(currentSteps)
                caloriesLive.postValue(currentCalories)
                
                // 2. Persist to SharedPreferences for offline speed
                saveToSharedPreferences()
                
                // 3. Broadcast update for other app components (like StepManager)
                val broadcastIntent = Intent(ACTION_UPDATE_STEPS)
                broadcastIntent.putExtra("steps", currentSteps)
                broadcastIntent.putExtra("calories", currentCalories.toDouble())
                sendBroadcast(broadcastIntent)
                
                // 4. Update the live notification
                updateNotification()
            }
            
            // Start listening to the accelerometer sensor
            if (stepTracker?.isSensorAvailable() == true) {
                stepTracker?.start()
                isRunningLive.postValue(true)
                
                // Launch a background timer to sync data with the cloud every 5 minutes
                startPeriodicSave()
                
                Log.d("StepCounterService", "Step tracking started")
            } else {
                Log.e("StepCounterService", "Accelerometer not available")
                stopSelf()
            }
        }
    }
    
    /**
     * Fetches today's step count and calories from Firestore.
     * Returns results via callback to initialize the service state.
     */
    private fun fetchTodayStepsFromFirestore(callback: (Int, Int) -> Unit) {
        val userId = auth.currentUser?.uid
        if (userId == null) {
            Log.w("StepCounterService", "User not logged in, starting from 0")
            callback(0, 0)
            return
        }
        
        val today = dateFormat.format(Date())
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

    /**
     * Gracefully stops the service after performing a final sync and cleanup.
     */
    private fun stopTracking() {
        // Perform a final synchronization to the cloud
        saveToFirestore()
        
        // Stop the background sync timer
        saveTimer?.cancel()
        saveTimer = null
        
        // Unregister the step tracker to save battery
        stepTracker?.stop()
        stepTracker = null
        
        isRunningLive.postValue(false)
        
        // Shut down the service
        stopForeground(true)
        stopSelf()
        
        Log.d("StepCounterService", "Step tracking stopped")
    }

    /**
     * Starts a timer that triggers a cloud sync every 5 minutes.
     */
    private fun startPeriodicSave() {
        saveTimer = fixedRateTimer("FirestoreSave", false, 5 * 60 * 1000L, 5 * 60 * 1000L) {
            saveToFirestore()
        }
    }

    /**
     * High-level logic to save current steps to Firestore.
     * Includes logic to handle the transition from one day to the next (midnight).
     */
    private fun saveToFirestore() {
        val userId = auth.currentUser?.uid ?: return
        val today = dateFormat.format(Date())
        
        if (currentSteps == 0) return 
        
        // Midnight detection: if the date changed during a session, 
        // finalize the old day's data and reset the counter for the new day.
        if (sessionStartDate.isNotEmpty() && today != sessionStartDate) {
            Log.d("StepCounterService", "Date changed from $sessionStartDate to $today — resetting for new day")
            saveForDate(userId, sessionStartDate, currentSteps, currentCalories)
            
            // Reset for the new date
            currentSteps = 0
            currentCalories = 0
            sessionStartDate = today
            sessionStartTime = System.currentTimeMillis()
            stepsLive.postValue(0)
            caloriesLive.postValue(0)
            saveToSharedPreferences()
            return
        }
        
        // Normal save for today
        saveForDate(userId, today, currentSteps, currentCalories)
        saveToSharedPreferences()
    }
    
    /**
     * Performs the actual Firestore write for a specific date.
     * Saves daily totals and adds session details as a history item.
     */
    private fun saveForDate(userId: String, date: String, steps: Int, calories: Int) {
        val docRef = db.collection("users").document(userId)
            .collection("daily_steps").document(date)
        
        // Store absolute totals for the day
        val data = hashMapOf(
            "date" to date,
            "steps" to steps,
            "calories" to calories,
            "lastUpdated" to FieldValue.serverTimestamp()
        )
        
        // Record session metadata (for detailed activity history)
        val session = hashMapOf(
            "startTime" to sessionStartTime,
            "endTime" to System.currentTimeMillis(),
            "steps" to steps,
            "calories" to calories
        )
        
        docRef.set(data, SetOptions.merge())
            .addOnSuccessListener {
                // Append this specific session to the history array for that day
                docRef.update("sessions", FieldValue.arrayUnion(session))
                Log.d("StepCounterService", "Saved to Firestore ($date): $steps steps, $calories kcal")
            }
            .addOnFailureListener { e ->
                Log.e("StepCounterService", "Failed to save to Firestore", e)
            }
    }
    
    /**
     * Persists recent data to SharedPreferences for quick retrieval without network.
     */
    private fun saveToSharedPreferences() {
        val today = dateFormat.format(Date())
        val prefs = getSharedPreferences("StepCounterPrefs", Context.MODE_PRIVATE)
        prefs.edit().apply {
            putInt("daily_steps", currentSteps)
            putInt("daily_calories", currentCalories)
            putString("last_date", today)
            putLong("last_updated", System.currentTimeMillis())
            apply()
        }
    }

    /**
     * Configures the Notification Channel required for Android 8.0+ background services.
     */
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

    /**
     * Creates the persistent notification object shown to the user while tracking is active.
     */
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

    /**
     * Updates an existing notification in real-time with latest step data.
     */
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

