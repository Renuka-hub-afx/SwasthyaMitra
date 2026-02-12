package com.example.swasthyamitra.models

data class ExerciseLog(
    val logId: String = "",
    val userId: String = "",
    val exerciseName: String = "",
    val caloriesBurned: Int = 0,
    val duration: Int = 0, // In minutes
    val timestamp: Long = 0L,
    val date: String = "" // Format: yyyy-MM-dd
)
