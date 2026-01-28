package com.example.swasthyamitra.models

object WorkoutVideoRepository {
    fun getSmartRecommendation(goalType: String, calorieStatus: String, intensity: String): List<WorkoutVideo> {
        // Return dummy data for now
        return listOf(
            WorkoutVideo("dQw4w9WgXcQ", "Full Body HIIT", "HIIT", 15),
            WorkoutVideo("j57HgzJIbkU", "Yoga for Beginners", "Yoga", 20),
            WorkoutVideo("1gkQp8X-7qU", "Abs Workout", "Strength", 10)
        )
    }
}
