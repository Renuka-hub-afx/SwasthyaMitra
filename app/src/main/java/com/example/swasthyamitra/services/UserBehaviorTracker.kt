package com.example.swasthyamitra.services

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.swasthyamitra.R
import com.example.swasthyamitra.utils.ActivityRecognitionHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import java.util.*

class UserBehaviorTracker : Service() {

    private val serviceScope = CoroutineScope(Dispatchers.Default + Job())
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    companion object {
        private const val CHANNEL_ID = "behavior_tracking"
        private const val NOTIFICATION_ID = 101
    }

    override fun onCreate() {
        super.onCreate()
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance("renu") // Use renu database

        // Initialize activity recognition
        ActivityRecognitionHelper.initialize(this)

        createNotificationChannel()
        startForeground(NOTIFICATION_ID, createNotification())

        startBehaviorTracking()
    }

    private fun startBehaviorTracking() {
        serviceScope.launch {
            while (isActive) {
                try {
                    trackUserActivity()
                } catch (e: Exception) {
                    android.util.Log.e("BehaviorTracker", "Error tracking: ${e.message}", e)
                }
                delay(3600_000L) // Check every hour (reduced from 1 minute for battery)
            }
        }
    }

    private suspend fun trackUserActivity() {
        val userId = auth.currentUser?.uid ?: return

        withContext(Dispatchers.IO) {
            // Auto-track hydration based on device usage patterns
            trackHydrationNeeds(userId)

            // Auto-track meal times based on app usage
            trackMealPatterns(userId)

            // Auto-track physical activity from device sensors
            trackPhysicalActivity(userId)

            // Auto-track sleep patterns
            trackSleepPatterns(userId)
        }
    }

    private suspend fun trackHydrationNeeds(userId: String) {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)

        // Predict hydration needs based on time and activity (between 8 AM - 10 PM)
        if (hour in 8..22) {
            if (shouldRemindWater(userId)) {
                val predictedAmount = calculateWaterNeed(userId)

                // Auto-log predicted water intake
                val hydrationLog = hashMapOf(
                    "amountML" to predictedAmount,
                    "timestamp" to System.currentTimeMillis(),
                    "date" to getCurrentDate(),
                    "userId" to userId,
                    "source" to "auto",
                    "confidence" to 0.85 // AI confidence score
                )

                db.collection("users")
                    .document(userId)
                    .collection("waterLogs")
                    .add(hydrationLog)
                    .await()

                android.util.Log.d("BehaviorTracker", "Auto-logged ${predictedAmount}ml water")
            }
        }
    }

    private suspend fun trackMealPatterns(userId: String) {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)

        // Auto-detect meal times
        val mealType = when (hour) {
            in 7..10 -> "breakfast"
            in 12..15 -> "lunch"
            in 16..18 -> "snack"
            in 19..21 -> "dinner"
            else -> null
        }

        mealType?.let { type ->
            if (shouldLogMeal(userId, type)) {
                // Use ML to predict meal based on user history
                val predictedMeal = predictMealFromHistory(userId, type)

                val foodLog = hashMapOf(
                    "mealType" to type,
                    "foodName" to predictedMeal.name,
                    "calories" to predictedMeal.calories,
                    "protein" to predictedMeal.protein,
                    "carbs" to predictedMeal.carbs,
                    "fat" to predictedMeal.fat,
                    "timestamp" to System.currentTimeMillis(),
                    "date" to getCurrentDate(),
                    "userId" to userId,
                    "source" to "auto_predicted",
                    "confidence" to predictedMeal.confidence,
                    "servingSize" to "1 serving"
                )

                db.collection("users")
                    .document(userId)
                    .collection("foodLogs")
                    .add(foodLog)
                    .await()

                android.util.Log.d("BehaviorTracker", "Auto-logged meal: ${predictedMeal.name}")
            }
        }
    }

    private suspend fun trackPhysicalActivity(userId: String) {
        try {
            // Use device motion sensors
            val activityData = ActivityRecognitionHelper.getActivityData(this)

            if (activityData.isExercising) {
                val exerciseLog = hashMapOf(
                    "exerciseName" to activityData.activityType,
                    "durationMinutes" to activityData.durationMinutes,
                    "caloriesBurned" to activityData.caloriesBurned,
                    "type" to "cardio",
                    "timestamp" to System.currentTimeMillis(),
                    "date" to getCurrentDate(),
                    "source" to "auto_sensor",
                    "userId" to userId
                )

                db.collection("users")
                    .document(userId)
                    .collection("exercise_logs")
                    .add(exerciseLog)
                    .await()

                android.util.Log.d("BehaviorTracker", "Auto-logged exercise: ${activityData.activityType}")
            }
        } catch (e: Exception) {
            android.util.Log.e("BehaviorTracker", "Error tracking activity: ${e.message}")
        }
    }

    private suspend fun trackSleepPatterns(userId: String) {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)

        // Auto-detect sleep/wake patterns
        when (hour) {
            22 -> { // Bedtime (10 PM)
                val sleepLog = hashMapOf(
                    "type" to "sleep_start",
                    "timestamp" to System.currentTimeMillis(),
                    "date" to getCurrentDate(),
                    "userId" to userId,
                    "source" to "auto"
                )

                db.collection("users")
                    .document(userId)
                    .collection("sleep_logs")
                    .add(sleepLog)
                    .await()
            }
            7 -> { // Wake time (7 AM)
                val wakeLog = hashMapOf(
                    "type" to "wake_up",
                    "timestamp" to System.currentTimeMillis(),
                    "date" to getCurrentDate(),
                    "userId" to userId,
                    "source" to "auto"
                )

                db.collection("users")
                    .document(userId)
                    .collection("sleep_logs")
                    .add(wakeLog)
                    .await()
            }
        }
    }

    // Helper functions
    private suspend fun shouldRemindWater(userId: String): Boolean {
        return try {
            val today = getCurrentDate()
            val lastLog = db.collection("users")
                .document(userId)
                .collection("waterLogs")
                .whereEqualTo("date", today)
                .orderBy("timestamp", com.google.firebase.firestore.Query.Direction.DESCENDING)
                .limit(1)
                .get()
                .await()

            if (lastLog.isEmpty) return true

            val lastTimestamp = lastLog.documents[0].getLong("timestamp") ?: 0L
            val hoursSince = (System.currentTimeMillis() - lastTimestamp) / (1000 * 60 * 60)

            hoursSince >= 2 // Remind if 2+ hours since last water
        } catch (e: Exception) {
            true
        }
    }

    private fun calculateWaterNeed(userId: String): Int {
        // Base hydration need
        return 250 // 250ml default per interval
    }

    private suspend fun shouldLogMeal(userId: String, mealType: String): Boolean {
        return try {
            val today = getCurrentDate()
            val existingMeal = db.collection("users")
                .document(userId)
                .collection("foodLogs")
                .whereEqualTo("date", today)
                .whereEqualTo("mealType", mealType)
                .limit(1)
                .get()
                .await()

            existingMeal.isEmpty // Only log if not already logged
        } catch (e: Exception) {
            false
        }
    }

    private suspend fun predictMealFromHistory(userId: String, mealType: String): PredictedMeal {
        return try {
            // Get last 7 days of this meal type
            val logs = db.collection("users")
                .document(userId)
                .collection("foodLogs")
                .whereEqualTo("mealType", mealType)
                .orderBy("timestamp", com.google.firebase.firestore.Query.Direction.DESCENDING)
                .limit(7)
                .get()
                .await()

            if (logs.isEmpty) {
                // Return default meal based on type
                getDefaultMeal(mealType)
            } else {
                // Get most common meal
                val mealCounts = mutableMapOf<String, Int>()
                val mealData = mutableMapOf<String, Map<String, Any?>>()

                logs.documents.forEach { doc ->
                    val name = doc.getString("foodName") ?: ""
                    mealCounts[name] = (mealCounts[name] ?: 0) + 1
                    if (!mealData.containsKey(name)) {
                        mealData[name] = doc.data ?: emptyMap()
                    }
                }

                val mostCommon = mealCounts.maxByOrNull { it.value }?.key
                val data = mealData[mostCommon] ?: emptyMap()

                PredictedMeal(
                    name = mostCommon ?: "Healthy Meal",
                    calories = (data["calories"] as? Number)?.toInt() ?: 500,
                    protein = (data["protein"] as? Number)?.toDouble() ?: 20.0,
                    carbs = (data["carbs"] as? Number)?.toDouble() ?: 60.0,
                    fat = (data["fat"] as? Number)?.toDouble() ?: 15.0,
                    confidence = 0.80
                )
            }
        } catch (e: Exception) {
            android.util.Log.e("BehaviorTracker", "Error predicting meal: ${e.message}")
            getDefaultMeal(mealType)
        }
    }

    private fun getDefaultMeal(mealType: String): PredictedMeal {
        return when (mealType) {
            "breakfast" -> PredictedMeal("Oats with Fruits", 350, 12.0, 55.0, 8.0, 0.60)
            "lunch" -> PredictedMeal("Dal Rice with Vegetables", 550, 18.0, 85.0, 12.0, 0.60)
            "snack" -> PredictedMeal("Fruits and Nuts", 200, 5.0, 30.0, 8.0, 0.60)
            "dinner" -> PredictedMeal("Roti with Sabzi", 450, 15.0, 70.0, 10.0, 0.60)
            else -> PredictedMeal("Balanced Meal", 500, 20.0, 60.0, 15.0, 0.60)
        }
    }

    private fun getCurrentDate(): String {
        val calendar = Calendar.getInstance()
        return String.format(
            "%04d-%02d-%02d",
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH) + 1,
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    private fun createNotification(): Notification {
        val intent = Intent(this, Class.forName("com.example.swasthyamitra.MainActivity"))
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("SwasthyaMitra Active")
            .setContentText("Monitoring your health patterns")
            .setSmallIcon(android.R.drawable.ic_menu_compass)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .build()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Behavior Tracking",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Tracks health behavior in the background"
            }
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        serviceScope.cancel()
        super.onDestroy()
    }
}

data class PredictedMeal(
    val name: String,
    val calories: Int,
    val protein: Double,
    val carbs: Double,
    val fat: Double,
    val confidence: Double
)

