package com.example.swasthyamitra

data class FitnessData(
    val userId: String = "",
    val lastActiveDate: String = "",
    val streak: Int = 0,
    val shields: Int = 0,
    val steps: Int = 0,
    val xp: Int = 0,
    val lastStreakBreakDate: String = "",
    val completionHistory: Map<String, Boolean> = emptyMap(),
    val activeShields: List<ShieldInstance> = emptyList()
) : java.io.Serializable

data class WorkoutSession(
    val sessionId: String = "",
    val date: String = "",
    val duration: Int = 0,
    val caloriesBurned: Int = 0,
    val type: String = ""
) : java.io.Serializable
