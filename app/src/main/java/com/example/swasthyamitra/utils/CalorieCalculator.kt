package com.example.swasthyamitra.utils

/**
 * Centralized calorie calculation utility
 * Ensures consistent calorie calculations across all screens
 */
object CalorieCalculator {
    
    // Standard calorie burn rate per step
    // Based on average: 1 step = 0.04 kcal
    private const val CALORIES_PER_STEP = 0.04
    
    /**
     * Calculate calories burned from step count
     * @param steps Number of steps taken
     * @return Calories burned (in kcal)
     */
    fun calculateFromSteps(steps: Int): Double {
        return steps * CALORIES_PER_STEP
    }
    
    /**
     * Calculate calories burned from step count (Int result)
     * @param steps Number of steps taken
     * @return Calories burned (in kcal) as Int
     */
    fun calculateFromStepsInt(steps: Int): Int {
        return (steps * CALORIES_PER_STEP).toInt()
    }
    
    /**
     * Format calories for display
     * @param calories Calorie value
     * @return Formatted string (e.g., "49.6 kcal")
     */
    fun formatCalories(calories: Double): String {
        return String.format("%.1f kcal", calories)
    }
    
    /**
     * Format calories with custom suffix
     * @param calories Calorie value
     * @param suffix Custom suffix (e.g., "kcal burned", "kcal")
     * @return Formatted string
     */
    fun formatCaloriesWithSuffix(calories: Double, suffix: String): String {
        return String.format("%.1f %s", calories, suffix)
    }
}
