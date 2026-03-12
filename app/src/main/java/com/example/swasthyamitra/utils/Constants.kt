package com.example.swasthyamitra.utils

/**
 * Application-wide constants for SwasthyaMitra.
 * Centralizes XP values, collection names, and configuration.
 */
object Constants {
    
    // ========== Firestore Collection Names ==========
    object Collections {
        const val USERS = "users"
        const val FOOD_LOGS = "foodLogs"
        const val EXERCISE_LOGS = "exercise_logs"
        const val SLEEP_LOGS = "sleep_logs"
        const val MOOD_LOGS = "mood_logs"
        const val WEIGHT_LOGS = "weightLogs"
        const val WATER_LOGS = "waterLogs"
        const val STEP_SESSIONS = "step_sessions"
        const val DAILY_STEPS = "daily_steps"
        const val DAILY_SUMMARY = "dailySummary"
        const val GAMIFICATION_DATA = "gamificationData"
        const val GOALS = "goals"
        const val BADGES = "badges"
        const val EMERGENCY_EVENTS = "emergency_events"
        const val RECOMMENDATIONS = "recommendations"
        const val MEAL_FEEDBACK = "meal_feedback"
        const val AI_GENERATED_PLANS = "ai_generated_plans"
    }
    
    // ========== Gamification XP Values ==========
    object XP {
        const val LOG_MEAL = 10
        const val LOG_WATER = 5
        const val LOG_SLEEP = 15
        const val LOG_MOOD = 5
        const val REACH_STEP_GOAL = 30
        const val LOG_WEIGHT = 10
        const val MAINTAIN_STREAK = 20
        const val COMPLETE_WORKOUT = 50  // Existing value
        const val AI_EXERCISE = 75        // Existing value
        
        // Level calculation
        const val XP_PER_LEVEL = 100
        
        // Shield rewards
        const val SHIELDS_PER_MILESTONE = 1
        const val MILESTONE_DAYS = 7
    }
    
    // ========== XP Source Identifiers ==========
    enum class XPSource(val xpValue: Int) {
        LOG_MEAL(XP.LOG_MEAL),
        LOG_WATER(XP.LOG_WATER),
        LOG_SLEEP(XP.LOG_SLEEP),
        LOG_MOOD(XP.LOG_MOOD),
        REACH_STEP_GOAL(XP.REACH_STEP_GOAL),
        LOG_WEIGHT(XP.LOG_WEIGHT),
        MAINTAIN_STREAK(XP.MAINTAIN_STREAK),
        COMPLETE_WORKOUT(XP.COMPLETE_WORKOUT),
        AI_EXERCISE(XP.AI_EXERCISE)
    }
    
    // ========== Health Goals & Limits ==========
    object Health {
        // Water intake
        const val WATER_BASE_ML_PER_KG = 33
        const val WATER_MIN_ML = 1500
        const val WATER_MAX_ML = 5000
        
        // Activity levels
        const val SEDENTARY_WATER_BONUS = 0
        const val LIGHT_ACTIVE_WATER_BONUS = 200
        const val MODERATE_ACTIVE_WATER_BONUS = 400
        const val VERY_ACTIVE_WATER_BONUS = 600
        
        // Step goals
        const val DEFAULT_STEP_GOAL = 10000
        const val MIN_STEP_GOAL = 1000
        const val MAX_STEP_GOAL = 30000
        
        // Weight change
        const val CALORIES_PER_KG = 7700
        
        // BMI categories
        const val BMI_UNDERWEIGHT = 18.5
        const val BMI_NORMAL_MAX = 24.9
        const val BMI_OVERWEIGHT_MAX = 29.9
    }
    
    // ========== Calorie Adjustments ==========
    object Calories {
        const val WEIGHT_LOSS_DEFICIT = -500
        const val MUSCLE_GAIN_SURPLUS = 300
        const val STAY_HEALTHY_ADJUSTMENT = 0
    }
    
    // ========== Activity Multipliers ==========
    object ActivityMultipliers {
        const val SEDENTARY = 1.2
        const val LIGHTLY_ACTIVE = 1.375
        const val MODERATELY_ACTIVE = 1.55
        const val VERY_ACTIVE = 1.725
    }
    
    // ========== Database Instance Names ==========
    object Database {
        const val FIRESTORE_INSTANCE_NAME = "renu"
    }
    
    // ========== Shared Preferences Keys ==========
    object Prefs {
        const val USER_ID = "userId"
        const val USER_NAME = "userName"
        const val USER_EMAIL = "userEmail"
        const val STEP_GOAL = "stepGoal"
        const val CURRENT_STREAK = "currentStreak"
        const val LAST_ACTIVE_DATE = "lastActiveDate"
    }
    
    // ========== Date Validation ==========
    object DateFormat {
        const val SIMPLE_DATE_PATTERN = "yyyy-MM-dd"
        const val ISO_8601_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    }
}
