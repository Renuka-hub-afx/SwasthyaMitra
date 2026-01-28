package com.example.swasthyamitra.utils

import kotlin.math.roundToInt

/**
 * Utility class for calculating personalized daily water intake goals
 * based on user's weight, height, and activity level.
 */
object WaterGoalCalculator {
    
    // Constants for water calculation
    private const val ML_PER_KG_MIN = 30
    private const val ML_PER_KG_MAX = 35
    private const val ML_PER_KG_AVERAGE = 33
    
    private const val MINIMUM_DAILY_GOAL = 1500 // ml
    private const val MAXIMUM_DAILY_GOAL = 4000 // ml
    
    // Activity level adjustments
    private const val SEDENTARY_ADJUSTMENT = 0
    private const val MODERATE_ADJUSTMENT = 500
    private const val ACTIVE_ADJUSTMENT = 1000
    
    /**
     * Calculate daily water goal based on weight
     * Formula: Weight (kg) × 33ml
     * 
     * @param weightKg User's weight in kilograms
     * @return Daily water goal in milliliters
     */
    fun calculateDailyGoal(weightKg: Double): Int {
        if (weightKg <= 0) return MINIMUM_DAILY_GOAL
        
        val baseGoal = (weightKg * ML_PER_KG_AVERAGE).roundToInt()
        return baseGoal.coerceIn(MINIMUM_DAILY_GOAL, MAXIMUM_DAILY_GOAL)
    }
    
    /**
     * Calculate daily water goal with activity level adjustment
     * 
     * @param weightKg User's weight in kilograms
     * @param activityLevel Activity level: "sedentary", "moderate", "active"
     * @return Daily water goal in milliliters
     */
    fun calculateDailyGoalWithActivity(weightKg: Double, activityLevel: String = "moderate"): Int {
        val baseGoal = calculateDailyGoal(weightKg)
        
        val adjustment = when (activityLevel.lowercase()) {
            "sedentary" -> SEDENTARY_ADJUSTMENT
            "moderate" -> MODERATE_ADJUSTMENT
            "active" -> ACTIVE_ADJUSTMENT
            else -> MODERATE_ADJUSTMENT
        }
        
        return (baseGoal + adjustment).coerceIn(MINIMUM_DAILY_GOAL, MAXIMUM_DAILY_GOAL)
    }
    
    /**
     * Get recommended intake range for a given weight
     * 
     * @param weightKg User's weight in kilograms
     * @return Pair of (minimum, maximum) recommended intake in ml
     */
    fun getRecommendedIntakeRange(weightKg: Double): Pair<Int, Int> {
        if (weightKg <= 0) return Pair(MINIMUM_DAILY_GOAL, MINIMUM_DAILY_GOAL)
        
        val minIntake = (weightKg * ML_PER_KG_MIN).roundToInt()
            .coerceIn(MINIMUM_DAILY_GOAL, MAXIMUM_DAILY_GOAL)
        val maxIntake = (weightKg * ML_PER_KG_MAX).roundToInt()
            .coerceIn(MINIMUM_DAILY_GOAL, MAXIMUM_DAILY_GOAL)
        
        return Pair(minIntake, maxIntake)
    }
    
    /**
     * Calculate percentage of goal achieved
     * 
     * @param currentIntake Current water intake in ml
     * @param goal Daily goal in ml
     * @return Percentage (0-100+)
     */
    fun calculateProgress(currentIntake: Int, goal: Int): Int {
        if (goal <= 0) return 0
        return ((currentIntake.toFloat() / goal) * 100).roundToInt()
    }
    
    /**
     * Get remaining water needed to reach goal
     * 
     * @param currentIntake Current water intake in ml
     * @param goal Daily goal in ml
     * @return Remaining ml needed (0 if goal achieved)
     */
    fun getRemainingIntake(currentIntake: Int, goal: Int): Int {
        return (goal - currentIntake).coerceAtLeast(0)
    }
    
    /**
     * Format water amount for display
     * 
     * @param amountML Amount in milliliters
     * @return Formatted string (e.g., "2.5 L" or "500 ml")
     */
    fun formatWaterAmount(amountML: Int): String {
        return when {
            amountML >= 1000 -> {
                val liters = amountML / 1000.0
                String.format("%.1f L", liters)
            }
            else -> "$amountML ml"
        }
    }
    
    /**
     * Get goal explanation text
     * 
     * @param weightKg User's weight
     * @param goal Calculated goal
     * @return Explanation string
     */
    fun getGoalExplanation(weightKg: Double, goal: Int): String {
        return """
            Your daily water goal is calculated based on your body weight.
            
            Weight: ${String.format("%.1f", weightKg)} kg
            Formula: Weight × 33 ml/kg
            Calculated Goal: ${formatWaterAmount(goal)}
            
            This is a general recommendation. Adjust based on:
            • Activity level (exercise increases needs)
            • Climate (hot weather increases needs)
            • Health conditions (consult your doctor)
        """.trimIndent()
    }
    
    /**
     * Calculate optimal number of reminders per day
     * 
     * @param wakeHour Wake up hour (0-23)
     * @param sleepHour Sleep hour (0-23)
     * @return Recommended number of reminders
     */
    fun calculateOptimalReminderCount(wakeHour: Int, sleepHour: Int): Int {
        val activeHours = if (sleepHour > wakeHour) {
            sleepHour - wakeHour
        } else {
            (24 - wakeHour) + sleepHour
        }
        
        // Recommend one reminder every 2 hours
        return (activeHours / 2).coerceIn(4, 12)
    }
    
    /**
     * Calculate reminder interval in minutes
     * 
     * @param wakeHour Wake up hour (0-23)
     * @param sleepHour Sleep hour (0-23)
     * @return Interval in minutes
     */
    fun calculateReminderInterval(wakeHour: Int, sleepHour: Int): Long {
        val reminderCount = calculateOptimalReminderCount(wakeHour, sleepHour)
        val activeHours = if (sleepHour > wakeHour) {
            sleepHour - wakeHour
        } else {
            (24 - wakeHour) + sleepHour
        }
        
        val intervalHours = activeHours.toFloat() / reminderCount
        return (intervalHours * 60).toLong() // Convert to minutes
    }
}
