package com.example.swasthyamitra

import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.database.FirebaseDatabase

import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.*
import android.util.Log
import kotlin.math.min

class InsightsRepository(private val authHelper: FirebaseAuthHelper, private val userId: String) {

    private val firestore = FirebaseFirestore.getInstance("renu") // Using RENU database instance
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val dayFormat = SimpleDateFormat("EEE", Locale.getDefault())

    suspend fun getWeeklyMetrics(): WeeklyMetrics {
        try {
            // Get data for the past 7 days
            val insights = mutableListOf<DailyInsight>()
            val calendar = Calendar.getInstance()
            
            // Start from 6 days ago to today (7 days total)
            calendar.add(Calendar.DAY_OF_YEAR, -6)
            
            var totalSteps = 0
            var totalCalories = 0
            var totalWorkoutMinutes = 0
            var daysWithWorkouts = 0
            
            for (i in 0 until 7) {
                val date = calendar.time
                val dateString = dateFormat.format(date)
                val dayName = dayFormat.format(date)
                
                // Fetch steps for this day
                val steps = getStepsForDate(dateString)
                
                // Fetch calories for this day
                val calories = getCaloriesForDate(dateString)
                
                // Fetch workout minutes for this day
                val workoutMinutes = getWorkoutMinutesForDate(dateString)
                
                insights.add(DailyInsight(dayName, dateString, calories, steps, workoutMinutes))
                
                totalSteps += steps
                totalCalories += calories
                totalWorkoutMinutes += workoutMinutes
                if (workoutMinutes > 0) daysWithWorkouts++
                
                calendar.add(Calendar.DAY_OF_YEAR, 1)
            }
            
            // Calculate balance score (0-100)
            val avgSteps = totalSteps / 7.0
            val avgCalories = totalCalories / 7.0
            
            // Consistency score (40%): Based on workout frequency (target: 4 days/week)
            val consistencyScore = min((daysWithWorkouts / 4.0 * 40).toInt(), 40)
            
            // Activity score (30%): Based on steps (target: 5000/day)
            val activityScore = min((avgSteps / 5000.0 * 30).toInt(), 30)
            
            // Balance score (30%): Based on calories (target: 2000/day)
            val nutritionScore = min((avgCalories / 2000.0 * 30).toInt(), 30)
            
            val balanceScore = consistencyScore + activityScore + nutritionScore
            
            // Determine category and narrative
            val (category, narrative, microGoal) = when {
                balanceScore >= 80 -> Triple(
                    "Excellent Balance ⭐",
                    "Outstanding! You're maintaining great balance this week with consistent workouts, active movement, and good nutrition.",
                    "Keep up the momentum! Challenge yourself to beat your step count.")
                balanceScore >= 60 -> Triple(
                    "Good Balance 👍",
                    "You're on the right track! Your activity and nutrition are well-balanced. A few more workouts would be perfect.",
                    "Try to add one more workout session this week.")
                balanceScore >= 40 -> Triple(
                    "Needs Improvement 📈",
                    "You're making progress, but there's room to grow. Focus on consistency in both movement and nutrition.",
                    "Set a goal to reach 5,000 steps and log at least one workout.")
                else -> Triple(
                    "Getting Started 🌱",
                    "Every journey starts somewhere! Let's build healthy habits one day at a time.",
                    "Start small: Aim for 3,000 steps today and log your meals.")
            }
            
            Log.d("InsightsRepository", "Weekly metrics calculated: Score=$balanceScore, Days with workouts=$daysWithWorkouts")
            
            return WeeklyMetrics(
                balanceScore = balanceScore,
                category = category,
                narrative = narrative,
                microGoal = microGoal,
                insights = insights
            )
            
        } catch (e: Exception) {
            Log.e("InsightsRepository", "Error fetching weekly metrics", e)
            // Return default data if error occurs
            return WeeklyMetrics(
                balanceScore = 0,
                category = "No Data",
                narrative = "Unable to load insights. Please ensure you've logged some activities.",
                microGoal = "Start by logging your first workout!",
                insights = emptyList()
            )
        }
    }
    
    private suspend fun getStepsForDate(date: String): Int {
        return try {
            val prefs = authHelper.getContext().getSharedPreferences("StepCounterPrefs", android.content.Context.MODE_PRIVATE)
            val savedDate = prefs.getString("last_date", "")
            val today = dateFormat.format(Date())

            val steps = if (date == today && date == savedDate) {
                prefs.getInt("daily_steps", 0)
            } else {
                // Try to get from Realtime Database
                val db = FirebaseDatabase.getInstance("https://swasthyamitra-ded44-default-rtdb.asia-southeast1.firebasedatabase.app").reference
                val snapshot = db.child("dailyActivity").child(userId).child(date).child("steps").get().await()
                snapshot.getValue(Long::class.java)?.toInt() ?: 0
            }

            Log.d("InsightsRepository", "Steps for $date: $steps")
            steps
        } catch (e: Exception) {
            Log.e("InsightsRepository", "Error getting steps for $date", e)
            0
        }
    }
    
    private suspend fun getCaloriesForDate(date: String): Int {
        return try {
            val foodLogs = firestore.collection("users")
                .document(userId)
                .collection("foodLogs")
                .whereEqualTo("date", date)
                .get()
                .await()
            
            var totalCalories = 0
            for (doc in foodLogs.documents) {
                totalCalories += doc.getLong("calories")?.toInt() ?: 0
            }
            Log.d("InsightsRepository", "Calories for $date: $totalCalories (from ${foodLogs.documents.size} food logs)")
            totalCalories
        } catch (e: Exception) {
            Log.e("InsightsRepository", "Error getting calories for $date", e)
            0
        }
    }
    
    private suspend fun getWorkoutMinutesForDate(date: String): Int {
        return try {
            val workouts = firestore.collection("workouts")
                .whereEqualTo("userId", userId)
                .whereEqualTo("date", date)
                .whereEqualTo("completed", true)
                .get()
                .await()
            
            var totalMinutes = 0
            for (doc in workouts.documents) {
                totalMinutes += doc.getLong("durationMinutes")?.toInt() ?: 30 // Default 30 min if not specified
            }
            totalMinutes
        } catch (e: Exception) {
            Log.e("InsightsRepository", "Error getting workout minutes for $date", e)
            0
        }
    }
}
