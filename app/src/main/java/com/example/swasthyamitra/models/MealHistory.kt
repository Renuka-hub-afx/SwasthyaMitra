package com.example.swasthyamitra.models

data class MealHistory(
    val userId: String = "",
    val date: String = "", // YYYY-MM-DD
    val breakfast: List<String> = emptyList(),
    val lunch: List<String> = emptyList(),
    val snack: List<String> = emptyList(),
    val dinner: List<String> = emptyList(),
    val postWorkout: List<String> = emptyList(),
    val hydrationTips: List<String> = emptyList(),
    val festivalSpecial: String? = null,
    val totalCalories: Int = 0,
    val protein: Double = 0.0,
    val carbs: Double = 0.0,
    val fats: Double = 0.0,
    val timestamp: Long = 0L
)
