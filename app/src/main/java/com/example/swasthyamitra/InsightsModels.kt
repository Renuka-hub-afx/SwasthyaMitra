package com.example.swasthyamitra

data class DailyInsight(
    val dayName: String = "",
    val date: String = "",
    val caloriesConsumed: Int = 0,
    val steps: Int = 0,
    val workoutMinutes: Int = 0
)

data class WeeklyMetrics(
    val balanceScore: Int = 0,
    val category: String = "",
    val narrative: String = "",
    val microGoal: String = "",
    val insights: List<DailyInsight> = emptyList()
)
