package com.example.swasthyamitra

import android.util.Log
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.suspendCancellableCoroutine
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class InsightsRepository(
    private val authHelper: FirebaseAuthHelper,
    private val userId: String
) {
    
    private val database = FirebaseDatabase.getInstance("https://swasthyamitra-c0899-default-rtdb.asia-southeast1.firebasedatabase.app").reference
    
    suspend fun getWeeklyMetrics(): WeeklyMetrics {
        val insights = fetchWeeklyInsights()
        val score = calculateBalanceScore(insights)
        val category = getScoreCategory(score)
        val narrative = generateNarrative(insights, score)
        val microGoal = generateMicroGoal(insights)
        
        return WeeklyMetrics(
            balanceScore = score,
            category = category,
            narrative = narrative,
            microGoal = microGoal,
            insights = insights
        )
    }
    
    private suspend fun fetchWeeklyInsights(): List<DailyInsight> = suspendCancellableCoroutine { continuation ->
        val insights = mutableListOf<DailyInsight>()
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val dayFormat = SimpleDateFormat("EEE", Locale.getDefault())
        
        // Get last 7 days
        calendar.add(Calendar.DAY_OF_YEAR, -6)
        
        for (i in 0 until 7) {
            val dateStr = dateFormat.format(calendar.time)
            val dayName = dayFormat.format(calendar.time)
            
            insights.add(DailyInsight(
                dayName = dayName,
                date = dateStr,
                caloriesConsumed = (1500..2500).random(), // Mock data
                steps = (2000..8000).random(), // Mock data
                workoutMinutes = (0..60).random() // Mock data
            ))
            
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }
        
        Log.d("InsightsRepository", "Generated ${insights.size} daily insights")
        continuation.resume(insights)
    }
    
    private fun calculateBalanceScore(insights: List<DailyInsight>): Int {
        if (insights.isEmpty()) return 0
        
        val avgSteps = insights.map { it.steps }.average()
        val avgCalories = insights.map { it.caloriesConsumed }.average()
        val workoutDays = insights.count { it.workoutMinutes > 0 }
        
        // Consistency score (40%): based on workout days
        val consistencyScore = (workoutDays / 7.0 * 40).toInt()
        
        // Activity score (30%): based on steps (target: 5000)
        val activityScore = ((avgSteps / 5000.0).coerceAtMost(1.0) * 30).toInt()
        
        // Balance score (30%): based on calories (target: 2000)
        val calorieBalance = 1.0 - kotlin.math.abs(avgCalories - 2000) / 2000.0
        val balanceScore = (calorieBalance.coerceAtLeast(0.0) * 30).toInt()
        
        return (consistencyScore + activityScore + balanceScore).coerceIn(0, 100)
    }
    
    private fun getScoreCategory(score: Int): String {
        return when {
            score >= 80 -> "Excellent 🌟"
            score >= 60 -> "Good 👍"
            score >= 40 -> "Fair 😊"
            else -> "Needs Improvement 💪"
        }
    }
    
    private fun generateNarrative(insights: List<DailyInsight>, score: Int): String {
        val avgSteps = insights.map { it.steps }.average().toInt()
        val workoutDays = insights.count { it.workoutMinutes > 0 }
        
        return when {
            score >= 80 -> "Amazing work this week! You're maintaining excellent balance with $avgSteps average daily steps and $workoutDays workout days. Keep up this fantastic momentum!"
            score >= 60 -> "Great progress! You averaged $avgSteps steps daily with $workoutDays workout days. You're on the right track to achieving your fitness goals."
            score >= 40 -> "You're making progress with $avgSteps average steps and $workoutDays workout days. Let's aim for more consistency to boost your score!"
            else -> "This week showed $avgSteps average steps and $workoutDays workout days. Small improvements each day will help you build momentum!"
        }
    }
    
    private fun generateMicroGoal(insights: List<DailyInsight>): String {
        val avgSteps = insights.map { it.steps }.average().toInt()
        val workoutDays = insights.count { it.workoutMinutes > 0 }
        
        return when {
            avgSteps < 3000 -> "🎯 This week: Aim for 3,500 daily steps"
            avgSteps < 5000 -> "🎯 This week: Reach 5,000 steps per day"
            workoutDays < 3 -> "🎯 This week: Complete 3 workout sessions"
            workoutDays < 4 -> "🎯 This week: Achieve 4 workout days"
            else -> "🎯 This week: Maintain your excellent routine!"
        }
    }
}
