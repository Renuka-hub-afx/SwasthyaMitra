package com.example.swasthyamitra.models

data class ExerciseLog(
    val logId: String = "",
    val userId: String = "",
    val exerciseType: String = "",
    val durationMinutes: Int = 0,
    val intensity: String = "", // low/moderate/high
    val caloriesBurned: Int = 0,
    val timestamp: Long = 0L,
    val date: String = "" // YYYY-MM-DD
)
