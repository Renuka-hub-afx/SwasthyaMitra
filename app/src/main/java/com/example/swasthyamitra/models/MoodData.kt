package com.example.swasthyamitra.models

// Captures a single mood check-in: emotion type, intensity, energy level, and AI suggestion
data class MoodData(
    var userId: String = "",
    var mood: String = "",        // "happy", "sad", "stressed", "calm", "tired"
    var intensity: Float = 0.5f,  // 0.0 (very mild) to 1.0 (very strong)
    var energy: Float = 0.5f,     // 0.0 (exhausted) to 1.0 (very energetic)
    var suggestion: String = "",  // AI-generated mindfulness/activity tip
    var timestamp: Long = 0L,
    var date: String = ""         // "YYYY-MM-DD" for daily grouping
)
