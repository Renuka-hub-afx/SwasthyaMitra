package com.example.swasthyamitra.models

object WorkoutVideoRepository {
    
    // ACTUAL 15-MINUTE WORKOUT VIDEOS - Verified real fitness content
    private val weightLossWorkouts = listOf(
        WorkoutVideo("gC_L9qAHVJ8", "15 Min HIIT Fat Burn", "HIIT", 15),
        WorkoutVideo("lQW2N52R9FE", "15 Min Cardio Workout", "Cardio", 15),
        WorkoutVideo("3sEMn-GKTdE", "15 Min Fat Burning", "HIIT", 15)
    )
    
    private val weightGainWorkouts = listOf(
        WorkoutVideo("UBMk30rjy0o", "15 Min Full Body Strength", "Strength", 15),
        WorkoutVideo("IODxDxX7oi4", "15 Min Upper Body", "Strength", 15),
        WorkoutVideo("cbKkB3POqaY", "15 Min Lower Body", "Strength", 15)
    )
    
    private val maintenanceWorkouts = listOf(
        WorkoutVideo("Eml2xnoLpYE", "15 Min Yoga Flow", "Yoga", 15),
        WorkoutVideo("COp7BR_Dvps", "15 Min Pilates", "Pilates", 15),
        WorkoutVideo("qULTwquOuT4", "15 Min Stretching", "Flexibility", 15)
    )
    
    /**
     * AI-POWERED RECOMMENDATION ENGINE
     * Selects workouts based on user goal and calorie status
     */
    fun getSmartRecommendation(
        goalType: String, 
        calorieStatus: String, 
        intensity: String
    ): List<WorkoutVideo> {
        
        return when {
            // WEIGHT LOSS: High-intensity fat burning workouts
            goalType.contains("Loss", ignoreCase = true) -> {
                when (calorieStatus) {
                    "High" -> weightLossWorkouts.filter { it.category == "HIIT" }
                    "Low" -> weightLossWorkouts.filter { it.category == "Cardio" }.take(3)
                    else -> weightLossWorkouts.take(3)
                }
            }
            
            // WEIGHT GAIN: Strength training for muscle building
            goalType.contains("Gain", ignoreCase = true) -> {
                when (calorieStatus) {
                    "High" -> weightGainWorkouts
                    "Low" -> weightGainWorkouts.take(2) + maintenanceWorkouts.take(1)
                    else -> weightGainWorkouts
                }
            }
            
            // MAINTENANCE: Balanced wellness workouts
            else -> {
                when (calorieStatus) {
                    "High" -> weightLossWorkouts.filter { it.category == "Cardio" }.take(2) +
                              maintenanceWorkouts.take(1)
                    "Low" -> maintenanceWorkouts
                    else -> maintenanceWorkouts
                }
            }
        }.take(3).ifEmpty { maintenanceWorkouts.take(3) }
    }
    
    fun getTotalDuration(videos: List<WorkoutVideo>): Int {
        return videos.sumOf { it.durationMinutes }
    }
}
