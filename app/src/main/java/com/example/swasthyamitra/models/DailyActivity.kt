package com.example.swasthyamitra.models

// Snapshot of a user's activity for one day (steps, calories, workout type)
data class DailyActivity(
    val date: String = "",
    val steps: Int = 0,
    val calories: Int = 0,
    val workout: String = "" // e.g. "Yoga", "HIIT"
)
