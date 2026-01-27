package com.example.swasthyamitra.models

object WorkoutVideoRepository {
    
    private val allVideos = listOf(
        // Cardio
        WorkoutVideo("dZgVxmf6jkA", "10 Min HIIT Cardio", "High-intensity interval training for fat burning", "Cardio", 10, "High", 100),
        WorkoutVideo("ml6cT4AZdqI", "15 Min Fat Burning Cardio", "Moderate cardio to boost metabolism", "Cardio", 15, "Moderate", 120),
        WorkoutVideo("gC_L9qAHVJ8", "20 Min Full Body Cardio", "Complete cardio workout", "Cardio", 20, "Moderate", 150),
        
        // Strength
        WorkoutVideo("UBMk30rjy0o", "15 Min Upper Body Strength", "Build arm and shoulder strength", "Strength", 15, "Moderate", 80),
        WorkoutVideo("QpOz_boNRNo", "20 Min Full Body Strength", "Complete strength training", "Strength", 20, "High", 120),
        WorkoutVideo("IODxDxX7oi4", "10 Min Core Strength", "Strengthen your core muscles", "Strength", 10, "Moderate", 60),
        
        // Yoga
        WorkoutVideo("v7AYKMP6rOE", "15 Min Morning Yoga", "Gentle yoga to start your day", "Yoga", 15, "Low", 40),
        WorkoutVideo("oBu-pQG6sTY", "20 Min Full Body Yoga", "Complete yoga flow", "Yoga", 20, "Low", 50),
        WorkoutVideo("COp7BR_Dvps", "10 Min Relaxing Yoga", "Stress relief and flexibility", "Yoga", 10, "Low", 30),
        
        // Dance
        WorkoutVideo("gBAfejjUQoA", "15 Min Dance Cardio", "Fun dance workout", "Dance", 15, "Moderate", 110),
        WorkoutVideo("1919eTCoESo", "20 Min Zumba Workout", "High-energy dance fitness", "Dance", 20, "High", 140),
        
        // Pilates
        WorkoutVideo("K56Z12XNQ7s", "15 Min Pilates Core", "Core strengthening pilates", "Pilates", 15, "Moderate", 70),
        WorkoutVideo("kPJILpKXYxs", "20 Min Full Body Pilates", "Complete pilates workout", "Pilates", 20, "Moderate", 90)
    )
    
    fun getSmartRecommendation(goalType: String, calorieStatus: String, intensity: String): List<WorkoutVideo> {
        return when {
            goalType.contains("Weight Loss", ignoreCase = true) -> {
                when (calorieStatus) {
                    "High" -> allVideos.filter { it.category in listOf("Cardio", "Dance") && it.intensity == "High" }.take(3)
                    "Low" -> allVideos.filter { it.category in listOf("Strength", "Pilates") }.take(3)
                    else -> allVideos.filter { it.category in listOf("Cardio", "Strength") }.take(3)
                }
            }
            goalType.contains("Weight Gain", ignoreCase = true) || goalType.contains("Muscle", ignoreCase = true) -> {
                allVideos.filter { it.category in listOf("Strength", "Pilates") }.take(3)
            }
            else -> { // Maintenance
                allVideos.filter { it.category in listOf("Yoga", "Cardio", "Dance") && it.intensity != "High" }.take(3)
            }
        }.ifEmpty {
            allVideos.shuffled().take(3)
        }
    }
    
    fun getAllVideos(): List<WorkoutVideo> = allVideos
    
    fun getVideosByCategory(category: String): List<WorkoutVideo> {
        return allVideos.filter { it.category.equals(category, ignoreCase = true) }
    }
}
