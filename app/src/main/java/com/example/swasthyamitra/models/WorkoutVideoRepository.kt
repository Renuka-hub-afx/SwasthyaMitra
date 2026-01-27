package com.example.swasthyamitra.models

object WorkoutVideoRepository {
    fun getSmartRecommendation(goalType: String, calorieStatus: String, intensity: String): List<WorkoutVideo> {
        return listOf(
            WorkoutVideo(
                videoId = "UBMk30rjy0o",
                title = "20 Min Full Body Workout",
                category = "Strength",
                durationMinutes = 20,
                explanation = "A balanced full body workout requiring no equipment."
            ),
            WorkoutVideo(
                videoId = "gC_L9qAHVJ8",
                title = "10 Min Morning Yoga",
                category = "Flexibility",
                durationMinutes = 10,
                explanation = "Wake up your body and mind with this flow."
            ),
             WorkoutVideo(
                videoId = "ml6cT4AZdqI",
                title = "High Intensity Cardio",
                category = "Cardio",
                durationMinutes = 15,
                explanation = "Boost your metabolism with this quick cardio session."
            )
        )
    }
}
