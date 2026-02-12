package com.example.swasthyamitra.utils

import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.example.swasthyamitra.models.ExerciseLog
import com.example.swasthyamitra.models.FoodLog
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

data class WeightPoint(
    val timestamp: Long,
    val weight: Float,
    val isProjected: Boolean,
    val dateStr: String,
    val caloriesIn: Int = 0,
    val caloriesOut: Int = 0,
    val mood: String = "",
    val moodIntensity: Float = 0f
)

class WeightProjectionHelper(private val authHelper: FirebaseAuthHelper) {
    
    private val firestore = FirebaseFirestore.getInstance("renu")

    suspend fun getProjectedWeightTrend(userId: String, days: Int): List<WeightPoint> {
        // 1. Fetch all necessary data
        val endTime = System.currentTimeMillis()
        val startTime = endTime - (days * 24 * 60 * 60 * 1000L)
        
        // Fetch Goals
        val goalResult = authHelper.getUserGoal(userId)
        
        // Strategy: Use BMR * 1.2 (Sedentary) as BASE, then add explicit Exercise Logs.
        val bmr = if (goalResult.isSuccess) {
            val goal = goalResult.getOrNull()
            (goal?.get("bmr") as? Number)?.toDouble() ?: 1600.0 // Default BMR
        } else {
            1600.0
        }
        
        // Base Sedentary Burn (Life functions + light movement)
        val sedentaryTDEE = bmr * 1.2

        // Fetch Logs (Weight, Food, Exercise)
        val weightLogs = authHelper.getRecentWeightLogs(userId, days + 5)
        val foodLogs = authHelper.getRecentFoodLogs(userId, days)
        val exerciseLogs = authHelper.getRecentExerciseLogs(userId, days)
        
        // Fetch Mood Logs manually (Repository access might be tricky if not passed in)
        val moodLogs = try {
             val snapshot = firestore.collection("users").document(userId)
                .collection("mood_logs")
                .whereGreaterThan("timestamp", startTime)
                .get()
                .await()
            
            snapshot.documents.map { doc ->
                val date = doc.getString("date") ?: ""
                val mood = doc.getString("mood") ?: ""
                val intensity = (doc.getDouble("intensity") ?: 0.5).toFloat()
                Triple(date, mood, intensity)
            }
        } catch (e: Exception) {
            emptyList()
        }

        // 2. Prepare Data Structures
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        
        // Map: Date String -> Actual Weight
        val weightMap = weightLogs.associate { log ->
            val ts = (log["timestamp"] as? Long) ?: 0L
            val date = if (ts > 0) dateFormat.format(Date(ts)) else (log["date"] as? String ?: "")
            date to ((log["weight"] as? Number)?.toFloat() ?: 0f)
        }

        // Map: Date String -> Calories In
        val foodMap = foodLogs.groupBy { it.date }.mapValues { entry ->
            entry.value.sumOf { it.calories }
        }

        // Map: Date String -> Calories Burned (Exercise)
        val exerciseMap = exerciseLogs.associate { log ->
            val ts = (log["timestamp"] as? Long) ?: 0L
            val date = if (ts > 0) dateFormat.format(Date(ts)) else (log["date"] as? String ?: "")
            val burned = (log["caloriesBurned"] as? Number)?.toInt() ?: 0
            date to burned
        }
        
        // Map: Date String -> Best Mood of Day (or Last)
        // If multiple moods, let's take the one with highest intensity or just the last one?
        // Let's take the last one logged for simplicity.
        val moodMap = moodLogs.associate { (date, mood, intensity) ->
            date to Pair(mood, intensity)
        }

        val resultPoints = mutableListOf<WeightPoint>()
        
        // Find Starting Weight
        var currentWeight = 70.0f
        
        // Fetch current profile weight as fallback
        val userProfileResult = authHelper.getUserData(userId)
        if (userProfileResult.isSuccess) {
            val w = (userProfileResult.getOrNull()?.get("weight") as? Number)?.toFloat()
            if (w != null && w > 0) {
                currentWeight = w
            }
        }

        val sortedWeights = weightLogs.mapNotNull { log ->
            val ts = (log["timestamp"] as? Long) ?: 0L
             val w = ((log["weight"] as? Number)?.toFloat())
             if (w != null) ts to w else null
        }.sortedBy { it.first }

        val lastWeightBeforeStart = sortedWeights.lastOrNull { it.first < startTime }
        if (lastWeightBeforeStart != null) {
            currentWeight = lastWeightBeforeStart.second
        } else if (sortedWeights.isNotEmpty()) {
            // If the first log is AFTER start time, we ideally want to project BACKWARDS from it or just start from it?
            // Actually, if we have a profile weight, that's likely the "current" weight. 
            // If the log is in the future relative to start time, we might be better off starting with profile weight 
            // if it's older? But profile weight doesn't have a timestamp.
            // Let's stick to: Use profile weight as default. If we find a log before start, use that.
            // If we only find logs after start, we start with profile weight -> project -> snap to log when we hit it.
        }

        // 3. Iterate Day by Day
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = startTime
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        for (i in 0..days) {
            val dateStr = dateFormat.format(calendar.time)
            val dayTimestamp = calendar.timeInMillis
 
            val caloriesIn = foodMap[dateStr] ?: 0
            val caloriesOut_Exercise = exerciseMap[dateStr] ?: 0
            val (mood, intensity) = moodMap[dateStr] ?: Pair("", 0f)

            val actualWeightForDay = weightMap[dateStr]
            
            if (actualWeightForDay != null && actualWeightForDay > 0) {
                // Real data available - Sync projection
                currentWeight = actualWeightForDay
                resultPoints.add(WeightPoint(
                    dayTimestamp, 
                    currentWeight, 
                    isProjected = false, 
                    dateStr,
                    caloriesIn,
                    caloriesOut_Exercise,
                    mood,
                    intensity
                ))
            } else {
                // Calculate Projection
                // Effective Output = Sedentary Base + Actual Workouts
                val totalOutput = sedentaryTDEE + caloriesOut_Exercise
                
                val dailyCalorieBalance = if (caloriesIn > 0) {
                    caloriesIn - totalOutput
                } else {
                    // If NO food logged, assume maintenance BUT credit exercise
                    -(caloriesOut_Exercise.toDouble())
                }

                val weightChange = dailyCalorieBalance / 7700.0
                currentWeight += weightChange.toFloat()
                
                resultPoints.add(WeightPoint(
                    dayTimestamp, 
                    currentWeight, 
                    isProjected = true, 
                    dateStr,
                    caloriesIn,
                    caloriesOut_Exercise,
                    mood,
                    intensity
                ))
            }

            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }

        return resultPoints
    }
}
