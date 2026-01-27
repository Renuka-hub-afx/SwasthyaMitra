package com.example.swasthyamitra

data class FitnessData(
    val steps: Int = 0,
    val streak: Int = 0,
    val shields: Int = 0,
    val xp: Int = 0,
    val level: Int = 1,
    val lastActiveDate: String = "",
    val completionHistory: Map<String, Boolean> = HashMap(), // Date (YYYY-MM-DD) -> Completed
    val lastStreakBreakDate: String = "", // To track for comeback bonus
    val activeShields: List<ShieldInstance> = emptyList(),
    val totalWorkoutMinutes: Int = 0,
    val workoutHistory: Map<String, WorkoutSession> = HashMap() // UUID -> Session
) : java.io.Serializable

data class WorkoutSession(
    val id: String = "",
    val date: String = "",
    val category: String = "",
    val videoId: String = "",
    val duration: Int = 0,
    val completed: Boolean = false,
    val timestamp: Long = 0L
) : java.io.Serializable