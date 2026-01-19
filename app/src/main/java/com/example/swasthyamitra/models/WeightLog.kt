package com.example.swasthyamitra.models

data class WeightLog(
    val logId: String = "",
    val userId: String = "",
    val weight: Double = 0.0,
    val timestamp: Long = 0L,
    val date: String = "" // YYYY-MM-DD
)
