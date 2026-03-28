package com.example.swasthyamitra.ai

// Android framework imports for context and logging
import android.content.Context
import android.util.Log
// Firebase imports for authentication, AI, and database access
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.example.swasthyamitra.data.repository.HydrationRepository
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
import com.google.firebase.ai.type.generationConfig
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
// Kotlin coroutines for async AI operations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
// Date and time utilities for behavioral analysis
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

// AI Coach service for generating personalized motivation messages based on user behavior and health data
class AICoachMessageService private constructor(private val context: Context) {

    // Firebase authentication helper for user data access
    private val authHelper = FirebaseAuthHelper(context)
    // Hydration repository for water intake tracking
    private val hydrationRepo = HydrationRepository()
    // Logging tag for debugging AI coach behavior
    private val TAG = "AICoachService"
    // Firestore instance for user profile and food log data
    private val firestore = FirebaseFirestore.getInstance("renu")
    // Realtime Database for exercise completion tracking
    private val realtimeDb = FirebaseDatabase.getInstance("https://swasthyamitra-ded44-default-rtdb.asia-southeast1.firebasedatabase.app").reference

    // Singleton pattern implementation to ensure single AI coach instance
    companion object {
        @Volatile
        private var INSTANCE: AICoachMessageService? = null

        // Get singleton instance with thread-safe initialization
        fun getInstance(context: Context): AICoachMessageService {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: AICoachMessageService(context.applicationContext).also { INSTANCE = it }
            }
        }
    }

    // Generate personalized AI coach message based on user's current health progress and behavior
    suspend fun getCoachMessage(userId: String, steps: Int = 0): Result<String> = withContext(Dispatchers.IO) {
        try {
            // Get current date for daily progress tracking
            val today = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())

            // Step 1: Fetch comprehensive user context from multiple data sources
            val profile = authHelper.getUserData(userId).getOrThrow()  // User profile (age, gender, goals)
            val goal = authHelper.getUserGoal(userId).getOrThrow()     // Fitness goals and targets
            val caloriesResult = authHelper.getTodayCalories(userId)   // Today's calorie consumption
            val foodLogs = authHelper.getTodayFoodLogs(userId).getOrNull() ?: emptyList()  // Today's meal logs
            val waterTotal = hydrationRepo.getTodayWaterTotal(userId).getOrDefault(0)      // Today's water intake
            val waterGoal = hydrationRepo.getWaterGoalWithCalculation(userId).getOrDefault(2500)  // Personalized water goal

            // Extract key profile information for personalization
            val userName = profile["name"] as? String ?: "User"
            val currentGoal = goal["goalType"] as? String ?: "Health"
            val targetCalories = (goal["dailyCalories"] as? Number)?.toInt() ?: 2000
            val consumed = caloriesResult.getOrDefault(0)

            // Calculate macronutrient totals from food logs for nutritional analysis
            val totalProtein = foodLogs.sumOf { it.protein }
            val totalCarbs = foodLogs.sumOf { it.carbs }
            val totalFat = foodLogs.sumOf { it.fat }

            // Estimate calories burned from step count and calculate net calories
            val burnedFromSteps = (steps * 0.04).toInt()  // Rough estimate: 0.04 kcal per step
            val netCalories = consumed - burnedFromSteps
            // Check menstrual cycle status for adjusted coaching tone and recommendations
            val isOnPeriod = profile["isOnPeriod"] as? Boolean ?: false

            // Step 2: Fetch behavior data from multiple sources to understand user patterns
            // Check if user completed exercise today from Real-time Database
            val exerciseCompletedToday = try {
                realtimeDb.child("users").child(userId).child("completionHistory").child(today)
                    .get().await().getValue(Boolean::class.java) ?: false
            } catch (e: Exception) { false }

            // Analyze meal logging pattern based on current time of day
            val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)  // Current hour (0-23)
            val mealCount = foodLogs.size  // Total meals logged today
            // Calculate expected meal count based on time progression through the day
            val expectedMeals = when (hour) {
                in 0..10 -> 0   // Early morning - no meals expected yet
                in 11..14 -> 1  // Should have breakfast by now
                in 15..18 -> 2  // Should have breakfast + lunch
                else -> 3       // Should have all 3 main meals (breakfast, lunch, dinner)
            }
            val missedMeals = (expectedMeals - mealCount).coerceAtLeast(0)  // Number of missed meals

            // Calculate hours since last food logging for adherence analysis
            val lastFoodLogTime = if (foodLogs.isNotEmpty()) {
                val latest = foodLogs.maxByOrNull { it.timestamp }  // Most recent food log
                val hoursSinceLast = (System.currentTimeMillis() - (latest?.timestamp ?: 0)) / (1000 * 60 * 60)
                hoursSinceLast.toInt()
            } else -1 // No food logged today

            // Calculate hydration progress as percentage of daily goal
            val waterPercentage = (waterTotal * 100) / waterGoal

            // Calculate comprehensive activity score for overall health assessment
            val activityScore = calculateActivityScore(
                steps = steps,                          // Step count for physical activity
                exerciseDone = exerciseCompletedToday, // Exercise completion status
                mealsLogged = mealCount,                // Nutrition tracking adherence
                waterPercent = waterPercentage,         // Hydration adherence
                hour = hour                             // Time context for expectations
            )

            // Determine time-of-day context for appropriate messaging
            val timeOfDay = when (hour) {
                in 5..11 -> "Morning"    // Morning greeting and motivation
                in 12..16 -> "Afternoon" // Midday check-in
                in 17..21 -> "Evening"   // Evening encouragement
                else -> "Night"          // Late night gentle reminder
            }

            // Step 3: Construct behavior-aware AI prompt with comprehensive analysis

            // Special context for menstrual cycle to ensure empathetic messaging
            val periodContext = if (isOnPeriod) {
                "STRICT: The user is currently on her period. Be EXTRA gentle, empathetic and supportive."
            } else ""

            // Build detailed behavior analysis for AI to understand user's current state
            val behaviorInsights = buildString {
                appendLine("**BEHAVIOR ANALYSIS:**")

                // Exercise status analysis with time-sensitive recommendations
                if (exerciseCompletedToday) {
                    appendLine("- ✅ Exercise: COMPLETED today! Celebrate this achievement.")
                } else if (hour >= 17) {
                    appendLine("- ⚠️ Exercise: NOT completed yet and it's $timeOfDay. Gentle reminder needed.")
                } else {
                    appendLine("- ⏳ Exercise: Pending (still time in the day)")
                }

                // Meal logging adherence analysis with concern escalation
                when {
                    lastFoodLogTime == -1 && hour >= 10 ->
                        appendLine("- ⚠️ Food: User hasn't logged ANY food today. This is concerning if it's $timeOfDay.")
                    lastFoodLogTime > 5 && hour >= 12 ->
                        appendLine("- ⚠️ Food: Last meal was $lastFoodLogTime hours ago. Check if they've eaten.")
                    missedMeals >= 2 ->
                        appendLine("- ⚠️ Meals: User has missed ~$missedMeals meal(s) for this time of day.")
                    mealCount >= expectedMeals ->
                        appendLine("- ✅ Meals: On track with $mealCount meals logged.")
                }

                // Hydration progress analysis with time-sensitive urgency
                when {
                    waterPercentage < 30 && hour >= 14 ->
                        appendLine("- ⚠️ Water: Only $waterPercentage% of goal. Need to hydrate!")
                    waterPercentage < 50 && hour >= 18 ->
                        appendLine("- ⚠️ Water: Only $waterPercentage% and evening already. Remind about water.")
                    waterPercentage >= 80 ->
                        appendLine("- ✅ Water: Great hydration at $waterPercentage%!")
                    else ->
                        appendLine("- ⏳ Water: $waterPercentage% - progressing normally.")
                }

                // Physical activity analysis with motivational messaging
                when {
                    steps < 1000 && hour >= 15 ->
                        appendLine("- ⚠️ Steps: Very low activity ($steps steps). Suggest movement.")
                    steps < 3000 && hour >= 18 ->
                        appendLine("- ⚠️ Steps: Only $steps steps by evening. Suggest a short walk.")
                    steps >= 8000 ->
                        appendLine("- ✅ Steps: Excellent activity with $steps steps!")
                    else ->
                        appendLine("- ⏳ Steps: $steps steps - progressing.")
                }

                // Overall health score summary for AI context
                appendLine("- 📊 Overall Activity Score: $activityScore/100")
            }

            // Comprehensive AI prompt with persona, user data, and behavioral insights
            val promptText = """
                You are a kind, supportive Health Coach for SwasthyaMitra app.
                $periodContext

                **YOUR PERSONA:**
                - Speak like a caring friend, not a strict coach
                - Be encouraging, never judgmental
                - Use warm, positive language
                - If user is slacking, be motivating but not pushy

                **USER PROFILE:**
                - Name: $userName
                - Goal: $currentGoal
                - Time: $timeOfDay ($hour:00)
                - Period Status: ${if (isOnPeriod) "ON PERIOD 🌸" else "Normal"}

                **TODAY'S DATA:**
                - Calories: $consumed / $targetCalories kcal
                - Macros: P:${totalProtein.toInt()}g, C:${totalCarbs.toInt()}g, F:${totalFat.toInt()}g
                - Water: $waterTotal / $waterGoal ml ($waterPercentage%)
                - Steps: $steps ($burnedFromSteps kcal burned)
                - Meals Logged: $mealCount

                $behaviorInsights

                **MESSAGE RULES:**
                1. Keep it to 1-2 SHORT sentences max
                2. Address by name ($userName)
                3. Based on behavior analysis above, pick the MOST IMPORTANT thing to mention
                4. If everything is good, celebrate! If something needs attention, gently suggest
                5. ${if (isOnPeriod) "Be extra gentle - focus on self-care and comfort" else "Be warm and encouraging"}
                6. Use 1 emoji maximum
                7. NEVER be preachy or lecture-like

                **EXAMPLES OF GOOD MESSAGES:**
                - "Hey $userName, you crushed your workout today! 💪 Don't forget to grab some water."
                - "$userName, noticed you haven't logged lunch yet - hungry? 🍽️"
                - "Take it easy today $userName, your body needs rest. Hot tea might help! ☕"

                **OUTPUT:**
                Just the message. No JSON, no quotes, no explanation.
            """.trimIndent()

            // Step 4: Execute AI coaching message generation using Gemini 2.0 Flash
            // Configure AI model with creative temperature for natural message variation
            val config = generationConfig {
                temperature = 0.8f  // Higher temperature for more creative, human-like responses
            }
            // Initialize Google AI Gemini model for coaching message generation
            val generativeModel = Firebase.ai(backend = GenerativeBackend.googleAI())
                .generativeModel("gemini-2.0-flash", generationConfig = config)

            // Execute AI generation with timeout protection and fallback handling
            val response = try {
                kotlinx.coroutines.withTimeout(15000) {  // 15-second timeout to prevent hanging
                    generativeModel.generateContent(promptText)
                }
            } catch (e: Exception) {
                // If AI fails, return smart fallback message based on user state
                return@withContext Result.success(getSmartFallbackMessage(
                    userName, currentGoal, timeOfDay, exerciseCompletedToday,
                    mealCount, waterPercentage, steps, isOnPeriod
                ))
            }

            // Extract message from AI response or use fallback if generation fails
            val message = response.text?.trim() ?: getSmartFallbackMessage(
                userName, currentGoal, timeOfDay, exerciseCompletedToday,
                mealCount, waterPercentage, steps, isOnPeriod
            )
            Result.success(message)

        } catch (e: Exception) {
            // Log and return error if any step in the coaching pipeline fails
            Log.e(TAG, "Error: ${e.message}", e)
            Result.failure(e)
        }
    }

    // Calculate comprehensive activity score (0-100) based on multiple health metrics
    private fun calculateActivityScore(
        steps: Int,          // Daily step count
        exerciseDone: Boolean, // Exercise completion status
        mealsLogged: Int,    // Number of meals logged today
        waterPercent: Int,   // Hydration percentage of daily goal
        hour: Int            // Current hour for time-based expectations
    ): Int {
        var score = 0

        // Steps scoring (max 25 points) - encourages daily movement
        score += when {
            steps >= 10000 -> 25  // Excellent activity level
            steps >= 7000 -> 20   // Good activity level
            steps >= 5000 -> 15   // Moderate activity level
            steps >= 3000 -> 10   // Low but acceptable
            steps >= 1000 -> 5    // Minimal activity
            else -> 0             // Sedentary
        }

        // Exercise scoring (max 25 points) - structured workout completion
        if (exerciseDone) score += 25

        // Meal logging scoring (max 25 points) - based on time-appropriate expectations
        val expectedMeals = when (hour) {
            in 0..10 -> 0   // Early morning - no meals expected
            in 11..14 -> 1  // Should have breakfast
            in 15..18 -> 2  // Should have breakfast + lunch
            else -> 3       // Should have all 3 main meals
        }
        // Award proportional points based on meal logging adherence
        score += if (expectedMeals > 0) (mealsLogged.coerceAtMost(expectedMeals) * 25) / expectedMeals else 25

        // Hydration scoring (max 25 points) - proportional to water goal achievement
        score += (waterPercent.coerceAtMost(100) * 25) / 100

        // Return score capped between 0-100
        return score.coerceIn(0, 100)
    }

    // Generate rule-based fallback message when AI generation fails or times out
    private fun getSmartFallbackMessage(
        name: String,         // User's name for personalization
        goal: String,         // User's fitness goal
        time: String,         // Time of day context
        exerciseDone: Boolean, // Exercise completion status
        mealsLogged: Int,     // Number of meals logged
        waterPercent: Int,    // Hydration percentage
        steps: Int,           // Step count
        isOnPeriod: Boolean   // Menstrual cycle status
    ): String {
        // Special gentle messaging during menstrual cycle
        if (isOnPeriod) {
            return "Take care of yourself today $name. Rest when you need to 💜"
        }

        // Rule-based message selection based on user behavior patterns
        return when {
            // Celebrate excellent performance
            exerciseDone && waterPercent >= 70 ->
                "Amazing day $name! You're crushing your $goal goals! 🌟"
            // Gentle exercise reminder in evening
            !exerciseDone && time == "Evening" ->
                "Hey $name, a quick evening walk would be great for your $goal goal! 🚶"
            // Food logging reminder if no meals recorded
            mealsLogged == 0 && time != "Morning" ->
                "$name, don't forget to log your meals - I'm here to help! 🍽️"
            // Hydration reminder for low water intake
            waterPercent < 40 ->
                "Time for some water $name! Hydration helps with your $goal goal 💧"
            // Movement reminder for low activity in evening
            steps < 2000 && time == "Evening" ->
                "Let's get moving $name! Even a short walk counts 👟"
            // Default encouraging message
            else ->
                "Good $time $name! Keep working on your $goal goal 💪"
        }
    }
}
