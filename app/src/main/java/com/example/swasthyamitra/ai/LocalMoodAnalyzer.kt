package com.example.swasthyamitra.ai

import com.example.swasthyamitra.models.MoodData

class LocalMoodAnalyzer {

    data class AnalysisResult(
        val intensity: Float,
        val energy: Float,
        val suggestion: String
    )

    fun analyze(mood: String): AnalysisResult {
        return when (mood.lowercase()) {
            "happy", "excited" -> AnalysisResult(
                intensity = 0.7f,
                energy = 0.8f,
                suggestion = "Great energy! Use it to tackle a challenging workout or learn something new."
            )
            "calm", "relaxed" -> AnalysisResult(
                intensity = 0.4f,
                energy = 0.6f,
                suggestion = "Perfect state for mindfulness or a steady, focused work session."
            )
            "sad", "down" -> AnalysisResult(
                intensity = 0.6f,
                energy = 0.3f,
                suggestion = "Be gentle with yourself. A short walk or talking to a friend might help."
            )
            "stressed", "anxious" -> AnalysisResult(
                intensity = 0.8f,
                energy = 0.4f,
                suggestion = "Try a 5-minute breathing exercise to reset your nervous system."
            )
            "tired", "exhausted" -> AnalysisResult(
                intensity = 0.5f,
                energy = 0.2f,
                suggestion = "Listen to your body. Rest is productive too. Consider a light stretch."
            )
            else -> AnalysisResult(
                intensity = 0.5f,
                energy = 0.5f,
                suggestion = "Track how you feel to see patterns over time."
            )
        }
    }
}
