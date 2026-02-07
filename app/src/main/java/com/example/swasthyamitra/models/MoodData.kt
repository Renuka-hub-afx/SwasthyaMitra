package com.example.swasthyamitra.models

data class MoodData(
    var userId: String = "",
    var mood: String = "",        // happy, sad, stressed, calm, tired
    var intensity: Float = 0.5f,    // 0.0 to 1.0
    var energy: Float = 0.5f,       // 0.0 to 1.0
    var suggestion: String = "",
    var timestamp: Long = 0L,
    var date: String = ""
)
