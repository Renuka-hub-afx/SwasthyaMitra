package com.example.swasthyamitra.models

data class UserGoalData(
    val documentId: String = "",
    val userId: String = "",
    val goalType: String = "",
    val targetValue: Double = 0.0,
    val currentValue: Double = 0.0,
    val targetWeight: Double = 0.0,
    val targetCalories: Double = 0.0,
    val dailyCalories: Double = 0.0,
    val activityLevel: String = "",
    val dietPreference: String = "",
    val bmr: Double = 0.0,
    val tdee: Double = 0.0,
    val createdAt: Long = 0L,
    val updatedAt: Long = 0L
)
