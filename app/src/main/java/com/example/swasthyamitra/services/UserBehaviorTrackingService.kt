package com.example.swasthyamitra.services

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.swasthyamitra.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.*

/**
 * Background service that automatically tracks user behavior and logs activities
 * - Auto-logs water intake based on time patterns
 * - Predicts meal times and auto-suggests logging
 * - Tracks activity patterns
 * - Monitors sleep patterns
 */
class UserBehaviorTrackingService : Service() {

    private val serviceScope = CoroutineScope(Dispatchers.Default + SupervisorJob())
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

    companion object {
        private const val TAG = "BehaviorTracker"
        private const val CHANNEL_ID = "behavior_tracking_channel"
        private const val NOTIFICATION_ID = 1001

        private const val CHECK_INTERVAL = 30 * 60 * 1000L // 30 minutes

        fun startService(context: Context) {
            val intent = Intent(context, UserBehaviorTrackingService::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(intent)
            } else {
                context.startService(intent)
            }
        }

        fun stopService(context: Context) {
            context.stopService(Intent(context, UserBehaviorTrackingService::class.java))
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Service created")

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance("renu")

        createNotificationChannel()
        startForeground(NOTIFICATION_ID, createNotification())

        startBehaviorTracking()
    }

    private fun startBehaviorTracking() {
        serviceScope.launch {
            while (isActive) {
                try {
                    val userId = auth.currentUser?.uid
                    if (userId != null) {
                        trackUserBehavior(userId)
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error in behavior tracking", e)
                }
                delay(CHECK_INTERVAL)
            }
        }
    }

    private suspend fun trackUserBehavior(userId: String) {
        withContext(Dispatchers.IO) {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val currentDate = dateFormat.format(calendar.time)

            Log.d(TAG, "Checking behavior for user $userId at hour $hour")

            // Auto-track hydration patterns
            trackHydrationPattern(userId, hour, currentDate)

            // Auto-track meal patterns
            trackMealPattern(userId, hour, currentDate)

            // Auto-track activity patterns
            trackActivityPattern(userId, currentDate)

            // Generate daily insights
            if (hour == 20) { // 8 PM - generate end of day insights
                generateDailyInsights(userId, currentDate)
            }
        }
    }

    private suspend fun trackHydrationPattern(userId: String, hour: Int, currentDate: String) {
        try {
            // Check if user is in active hours (8 AM to 10 PM)
            if (hour !in 8..22) return

            // Get today's water intake
            val todayLogs = db.collection("users")
                .document(userId)
                .collection("waterLogs")
                .whereEqualTo("date", currentDate)
                .get()
                .await()

            val totalWater = todayLogs.documents.sumOf { it.getLong("amountML")?.toInt() ?: 0 }
            val targetWater = 2500 // Default goal

            // Auto-remind if behind schedule
            if (totalWater < (targetWater * hour / 22)) {
                val deficit = (targetWater * hour / 22) - totalWater
                Log.d(TAG, "Water deficit detected: $deficit ml")

                // Store recommendation
                storeRecommendation(
                    userId,
                    "hydration",
                    "You're ${deficit}ml behind your water goal. Stay hydrated! 💧",
                    currentDate
                )
            }

        } catch (e: Exception) {
            Log.e(TAG, "Error tracking hydration", e)
        }
    }

    private suspend fun trackMealPattern(userId: String, hour: Int, currentDate: String) {
        try {
            // Determine meal type based on time
            val mealType = when (hour) {
                in 7..10 -> "breakfast"
                in 12..15 -> "lunch"
                in 18..21 -> "dinner"
                else -> null
            }

            if (mealType == null) return

            // Check if meal already logged
            val mealLogs = db.collection("users")
                .document(userId)
                .collection("foodLogs")
                .whereEqualTo("date", currentDate)
                .whereEqualTo("mealType", mealType)
                .get()
                .await()

            if (mealLogs.isEmpty) {
                // Predict meal from history
                val predictedMeal = predictMealFromHistory(userId, mealType)

                if (predictedMeal != null) {
                    storeRecommendation(
                        userId,
                        "meal",
                        "Time for $mealType! Based on your history, you usually have ${predictedMeal.name}. Log it? 🍽️",
                        currentDate
                    )
                } else {
                    storeRecommendation(
                        userId,
                        "meal",
                        "Don't forget to log your $mealType! 🍽️",
                        currentDate
                    )
                }
            }

        } catch (e: Exception) {
            Log.e(TAG, "Error tracking meal pattern", e)
        }
    }

    private suspend fun predictMealFromHistory(userId: String, mealType: String): PredictedMeal? {
        return try {
            // Get last 7 days of this meal type
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_YEAR, -7)
            val weekAgo = dateFormat.format(calendar.time)

            val historicalMeals = db.collection("users")
                .document(userId)
                .collection("foodLogs")
                .whereEqualTo("mealType", mealType)
                .whereGreaterThanOrEqualTo("date", weekAgo)
                .get()
                .await()

            // Find most frequent meal
            val mealFrequency = mutableMapOf<String, Int>()
            historicalMeals.documents.forEach { doc ->
                val foodName = doc.getString("foodName") ?: return@forEach
                mealFrequency[foodName] = (mealFrequency[foodName] ?: 0) + 1
            }

            val mostFrequent = mealFrequency.maxByOrNull { it.value }
            if (mostFrequent != null && mostFrequent.value >= 2) {
                // Get details of most frequent meal
                val mealDoc = historicalMeals.documents.find {
                    it.getString("foodName") == mostFrequent.key
                }
                mealDoc?.let {
                    PredictedMeal(
                        name = it.getString("foodName") ?: "",
                        calories = it.getLong("calories")?.toInt() ?: 0,
                        confidence = mostFrequent.value / 7.0
                    )
                }
            } else null

        } catch (e: Exception) {
            Log.e(TAG, "Error predicting meal", e)
            null
        }
    }

    private suspend fun trackActivityPattern(userId: String, currentDate: String) {
        try {
            // Get today's exercise logs
            val exerciseLogs = db.collection("users")
                .document(userId)
                .collection("exercise_logs")
                .whereEqualTo("date", currentDate)
                .get()
                .await()

            val totalMinutes = exerciseLogs.documents.sumOf {
                it.getLong("durationMinutes")?.toInt() ?: 0
            }

            // Recommend if no exercise today
            if (totalMinutes == 0) {
                val calendar = Calendar.getInstance()
                val hour = calendar.get(Calendar.HOUR_OF_DAY)

                if (hour >= 17) { // After 5 PM
                    storeRecommendation(
                        userId,
                        "exercise",
                        "You haven't exercised today. Even a 15-minute walk helps! 🏃‍♂️",
                        currentDate
                    )
                }
            }

        } catch (e: Exception) {
            Log.e(TAG, "Error tracking activity", e)
        }
    }

    private suspend fun generateDailyInsights(userId: String, currentDate: String) {
        try {
            val insights = mutableListOf<String>()

            // Water intake insight
            val waterLogs = db.collection("users")
                .document(userId)
                .collection("waterLogs")
                .whereEqualTo("date", currentDate)
                .get()
                .await()
            val totalWater = waterLogs.documents.sumOf { it.getLong("amountML")?.toInt() ?: 0 }

            if (totalWater >= 2500) {
                insights.add("✅ Great hydration today! You drank ${totalWater}ml")
            } else {
                insights.add("💧 You drank ${totalWater}ml today. Try for 2500ml tomorrow!")
            }

            // Food intake insight
            val foodLogs = db.collection("users")
                .document(userId)
                .collection("foodLogs")
                .whereEqualTo("date", currentDate)
                .get()
                .await()

            val totalCalories = foodLogs.documents.sumOf { it.getLong("calories")?.toInt() ?: 0 }
            val mealCount = foodLogs.size()

            insights.add("🍽️ You logged $mealCount meals with ${totalCalories} calories")

            // Exercise insight
            val exerciseLogs = db.collection("users")
                .document(userId)
                .collection("exercise_logs")
                .whereEqualTo("date", currentDate)
                .get()
                .await()

            val totalExercise = exerciseLogs.documents.sumOf {
                it.getLong("durationMinutes")?.toInt() ?: 0
            }

            if (totalExercise > 0) {
                insights.add("💪 Great job! You exercised for $totalExercise minutes")
            } else {
                insights.add("🏃 No exercise logged today. Try tomorrow!")
            }

            // Store daily summary
            db.collection("users")
                .document(userId)
                .collection("daily_insights")
                .document(currentDate)
                .set(mapOf(
                    "date" to currentDate,
                    "insights" to insights,
                    "waterMl" to totalWater,
                    "calories" to totalCalories,
                    "exerciseMinutes" to totalExercise,
                    "timestamp" to com.google.firebase.Timestamp.now()
                ))
                .await()

            Log.d(TAG, "Daily insights generated: $insights")

        } catch (e: Exception) {
            Log.e(TAG, "Error generating insights", e)
        }
    }

    private suspend fun storeRecommendation(
        userId: String,
        type: String,
        message: String,
        date: String
    ) {
        try {
            db.collection("users")
                .document(userId)
                .collection("recommendations")
                .add(mapOf(
                    "type" to type,
                    "message" to message,
                    "date" to date,
                    "timestamp" to com.google.firebase.Timestamp.now(),
                    "isRead" to false,
                    "source" to "auto_tracker"
                ))
                .await()

            Log.d(TAG, "Recommendation stored: $message")
        } catch (e: Exception) {
            Log.e(TAG, "Error storing recommendation", e)
        }
    }

    private fun createNotification(): Notification {
        val notificationIntent = Intent(this, Class.forName("com.example.swasthyamitra.homepage"))
        val pendingIntent = PendingIntent.getActivity(
            this, 0, notificationIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("SwasthyaMitra")
            .setContentText("Tracking your health journey")
            .setSmallIcon(android.R.drawable.ic_menu_compass)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_LOW)
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
                description = "Tracks your health behavior in the background"
            }

            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        serviceScope.cancel()
        Log.d(TAG, "Service destroyed")
        super.onDestroy()
    }

    data class PredictedMeal(
        val name: String,
        val calories: Int,
        val confidence: Double
    )
}

