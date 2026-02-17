package com.example.swasthyamitra.utils

/**
 * Centralized calorie calculation utility
 * Ensures consistent calorie calculations across all screens.
 *
 * Supports two modes:
 *  - Simple: flat 0.04 kcal/step (legacy, used by basic step counter)
 *  - MET-based: uses GPS speed to determine MET value for more accurate
 *    calorie burn estimation. Formula: kcal = MET × weight(kg) × hours
 */
object CalorieCalculator {
    
    // Standard calorie burn rate per step
    // Based on average: 1 step = 0.04 kcal
    private const val CALORIES_PER_STEP = 0.04

    // MET values by speed range (Compendium of Physical Activities)
    private const val MET_STANDING_STILL = 1.3
    private const val MET_SLOW_WALK = 2.0      // < 3.2 km/h
    private const val MET_MODERATE_WALK = 3.5   // 3.2 – 4.8 km/h
    private const val MET_BRISK_WALK = 4.3      // 4.8 – 6.4 km/h
    private const val MET_FAST_WALK = 5.0       // 6.4 – 8.0 km/h
    private const val MET_JOGGING = 7.0         // 8.0 – 9.7 km/h
    private const val MET_RUNNING = 9.8         // 9.7 – 12.1 km/h
    private const val MET_FAST_RUNNING = 11.5   // > 12.1 km/h

    private const val DEFAULT_WEIGHT_KG = 65.0
    
    /**
     * Calculate calories burned from step count (simple mode)
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
     * MET-based calorie calculation using GPS speed for accuracy.
     *
     * @param steps      Total validated step count (used as minimum floor)
     * @param speedMs    Current GPS speed in meters/second
     * @param durationMs Total tracking duration in milliseconds
     * @param weightKg   User's body weight in kg (default 65)
     * @return Calories burned as Int
     */
    fun calculateMETBasedCalories(
        steps: Int,
        speedMs: Double,
        durationMs: Long,
        weightKg: Double = DEFAULT_WEIGHT_KG
    ): Int {
        if (durationMs <= 0) return 0
        
        val met = getMETForSpeed(speedMs)
        val hours = durationMs / 3_600_000.0
        val metCalories = (met * weightKg * hours).toInt()
        
        // Use whichever is higher: MET-based or simple step-based
        // This prevents showing 0 calories when GPS speed is 0 but steps exist
        val simpleCalories = calculateFromStepsInt(steps)
        return maxOf(metCalories, simpleCalories)
    }

    /**
     * Determine MET value from current speed.
     *
     * @param speedMs Speed in meters per second
     * @return MET value
     */
    fun getMETForSpeed(speedMs: Double): Double {
        val speedKmh = speedMs * 3.6
        return when {
            speedKmh < 0.5  -> MET_STANDING_STILL
            speedKmh < 3.2  -> MET_SLOW_WALK
            speedKmh < 4.8  -> MET_MODERATE_WALK
            speedKmh < 6.4  -> MET_BRISK_WALK
            speedKmh < 8.0  -> MET_FAST_WALK
            speedKmh < 9.7  -> MET_JOGGING
            speedKmh < 12.1 -> MET_RUNNING
            else             -> MET_FAST_RUNNING
        }
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
