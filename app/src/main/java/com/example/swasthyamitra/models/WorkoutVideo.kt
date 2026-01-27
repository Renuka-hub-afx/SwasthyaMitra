package com.example.swasthyamitra.models

data class WorkoutVideo(
    val videoId: String,
    val title: String,
    val explanation: String,
    val category: String,
    val durationMinutes: Int,
    val intensity: String = "Moderate",
    val caloriesBurn: Int = 0
)
