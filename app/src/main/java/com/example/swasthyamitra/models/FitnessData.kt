package com.example.swasthyamitra.models

data class FitnessData(
    val userId: String = "",
    val streak: Int = 0,
    val lastCheckInDate: String = "",
    val totalPoints: Int = 0,
    val lastCompletionDate: String = ""
)
