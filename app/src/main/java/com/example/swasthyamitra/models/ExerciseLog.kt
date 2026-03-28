package com.example.swasthyamitra.models

// Represents one logged exercise entry saved to Firestore under users/{uid}/exercise_logs
data class ExerciseLog(
    val logId: String = "",
    val userId: String = "",
    val exerciseName: String = "",
    val caloriesBurned: Int = 0,
    val duration: Int = 0,           // In minutes
    val timestamp: Long = 0L,
    val date: String = "",           // Format: yyyy-MM-dd
    val intensity: String = "",      // "Low", "Moderate", "High"
    val source: String = "",         // "Manual", "AI_Recommendation", "Search"
    val notes: String = "",
    val targetMuscle: String = "",
    val bodyPart: String = ""
)
