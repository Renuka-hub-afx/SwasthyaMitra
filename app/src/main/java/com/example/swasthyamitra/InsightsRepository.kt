package com.example.swasthyamitra

import com.example.swasthyamitra.auth.FirebaseAuthHelper

data class WeeklyMetrics(
    val balanceScore: Int,
    val category: String,
    val narrative: String,
    val microGoal: String,
    val insights: List<DailyInsight>
)

data class DailyInsight(
    val dayName: String,
    val caloriesConsumed: Int,
    val steps: Int
)

class InsightsRepository(private val authHelper: FirebaseAuthHelper, private val userId: String) {
    suspend fun getWeeklyMetrics(): WeeklyMetrics {
        // Return dummy data to satisfy build and runtime
        return WeeklyMetrics(
            balanceScore = 85,
            category = "Balanced Warrior",
            narrative = "You've maintained a great balance between calorie intake and activity this week. Keep it up!",
            microGoal = "Try to hit 6,000 steps tomorrow.",
            insights = listOf(
                DailyInsight("Mon", 2100, 5200),
                DailyInsight("Tue", 2250, 4800),
                DailyInsight("Wed", 1950, 6100),
                DailyInsight("Thu", 2300, 5500),
                DailyInsight("Fri", 2000, 5900),
                DailyInsight("Sat", 2500, 8000),
                DailyInsight("Sun", 1800, 3500)
            )
        )
    }
}
