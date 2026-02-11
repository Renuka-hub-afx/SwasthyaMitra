package com.example.swasthyamitra.ai

import android.content.Context
import android.util.Log
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.example.swasthyamitra.data.repository.HydrationRepository
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
import com.google.firebase.ai.type.generationConfig
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class AICoachMessageService private constructor(private val context: Context) {

    private val authHelper = FirebaseAuthHelper(context)
    private val hydrationRepo = HydrationRepository()
    private val TAG = "AICoachService"
    private val firestore = FirebaseFirestore.getInstance("renu")
    private val realtimeDb = FirebaseDatabase.getInstance("https://swasthyamitra-c0899-default-rtdb.asia-southeast1.firebasedatabase.app").reference

    companion object {
        @Volatile
        private var INSTANCE: AICoachMessageService? = null

        fun getInstance(context: Context): AICoachMessageService {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: AICoachMessageService(context.applicationContext).also { INSTANCE = it }
            }
        }
    }

    suspend fun getCoachMessage(userId: String, steps: Int = 0): Result<String> = withContext(Dispatchers.IO) {
        try {
            val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
            
            // 1. Fetch User Context
            val profile = authHelper.getUserData(userId).getOrThrow()
            val goal = authHelper.getUserGoal(userId).getOrThrow()
            val caloriesResult = authHelper.getTodayCalories(userId)
            val foodLogs = authHelper.getTodayFoodLogs(userId).getOrNull() ?: emptyList()
            val waterTotal = hydrationRepo.getTodayWaterTotal(userId).getOrDefault(0)
            val waterGoal = hydrationRepo.getWaterGoalWithCalculation(userId).getOrDefault(2500)

            val userName = profile["name"] as? String ?: "User"
            val currentGoal = goal["goalType"] as? String ?: "Health"
            val targetCalories = (goal["dailyCalories"] as? Number)?.toInt() ?: 2000
            val consumed = caloriesResult.getOrDefault(0)
            
            val totalProtein = foodLogs.sumOf { it.protein }
            val totalCarbs = foodLogs.sumOf { it.carbs }
            val totalFat = foodLogs.sumOf { it.fat }
            
            val burnedFromSteps = (steps * 0.04).toInt()
            val netCalories = consumed - burnedFromSteps
            val isOnPeriod = profile["isOnPeriod"] as? Boolean ?: false

            // 2. Fetch Behavior Data
            val exerciseCompletedToday = try {
                realtimeDb.child("users").child(userId).child("completionHistory").child(today)
                    .get().await().getValue(Boolean::class.java) ?: false
            } catch (e: Exception) { false }
            
            // Check meal logging pattern
            val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
            val mealCount = foodLogs.size
            val expectedMeals = when (hour) {
                in 0..10 -> 0 // Early morning - no meals expected yet
                in 11..14 -> 1 // Should have breakfast
                in 15..18 -> 2 // Should have breakfast + lunch
                else -> 3 // Should have all 3 meals
            }
            val missedMeals = (expectedMeals - mealCount).coerceAtLeast(0)
            
            // Check last food log time
            val lastFoodLogTime = if (foodLogs.isNotEmpty()) {
                val latest = foodLogs.maxByOrNull { it.timestamp }
                val hoursSinceLast = (System.currentTimeMillis() - (latest?.timestamp ?: 0)) / (1000 * 60 * 60)
                hoursSinceLast.toInt()
            } else -1 // No food logged today
            
            // Check water logging frequency
            val waterPercentage = (waterTotal * 100) / waterGoal
            
            // Calculate overall activity score
            val activityScore = calculateActivityScore(
                steps = steps,
                exerciseDone = exerciseCompletedToday,
                mealsLogged = mealCount,
                waterPercent = waterPercentage,
                hour = hour
            )

            val timeOfDay = when (hour) {
                in 5..11 -> "Morning"
                in 12..16 -> "Afternoon"
                in 17..21 -> "Evening"
                else -> "Night"
            }

            // 3. Construct Behavior-Aware Prompt
            val periodContext = if (isOnPeriod) {
                "STRICT: The user is currently on her period. Be EXTRA gentle, empathetic and supportive."
            } else ""

            val behaviorInsights = buildString {
                appendLine("**BEHAVIOR ANALYSIS:**")
                
                // Exercise status
                if (exerciseCompletedToday) {
                    appendLine("- ✅ Exercise: COMPLETED today! Celebrate this achievement.")
                } else if (hour >= 17) {
                    appendLine("- ⚠️ Exercise: NOT completed yet and it's $timeOfDay. Gentle reminder needed.")
                } else {
                    appendLine("- ⏳ Exercise: Pending (still time in the day)")
                }
                
                // Meal logging
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
                
                // Hydration
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
                
                // Steps
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
                
                // Overall score
                appendLine("- 📊 Overall Activity Score: $activityScore/100")
            }

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

            // 4. Call AI
            val config = generationConfig {
                temperature = 0.8f
            }
            val generativeModel = Firebase.ai(backend = GenerativeBackend.googleAI())
                .generativeModel("gemini-2.0-flash", generationConfig = config)
            
            val response = try {
                kotlinx.coroutines.withTimeout(15000) {
                    generativeModel.generateContent(promptText)
                }
            } catch (e: Exception) {
                return@withContext Result.success(getSmartFallbackMessage(
                    userName, currentGoal, timeOfDay, exerciseCompletedToday, 
                    mealCount, waterPercentage, steps, isOnPeriod
                ))
            }
            
            val message = response.text?.trim() ?: getSmartFallbackMessage(
                userName, currentGoal, timeOfDay, exerciseCompletedToday,
                mealCount, waterPercentage, steps, isOnPeriod
            )
            Result.success(message)

        } catch (e: Exception) {
            Log.e(TAG, "Error: ${e.message}", e)
            Result.failure(e)
        }
    }
    
    private fun calculateActivityScore(
        steps: Int, 
        exerciseDone: Boolean, 
        mealsLogged: Int, 
        waterPercent: Int,
        hour: Int
    ): Int {
        var score = 0
        
        // Steps (max 25 points)
        score += when {
            steps >= 10000 -> 25
            steps >= 7000 -> 20
            steps >= 5000 -> 15
            steps >= 3000 -> 10
            steps >= 1000 -> 5
            else -> 0
        }
        
        // Exercise (max 25 points)
        if (exerciseDone) score += 25
        
        // Meals (max 25 points) - based on time of day
        val expectedMeals = when (hour) {
            in 0..10 -> 0
            in 11..14 -> 1
            in 15..18 -> 2
            else -> 3
        }
        score += if (expectedMeals > 0) (mealsLogged.coerceAtMost(expectedMeals) * 25) / expectedMeals else 25
        
        // Water (max 25 points)
        score += (waterPercent.coerceAtMost(100) * 25) / 100
        
        return score.coerceIn(0, 100)
    }

    private fun getSmartFallbackMessage(
        name: String, 
        goal: String, 
        time: String,
        exerciseDone: Boolean,
        mealsLogged: Int,
        waterPercent: Int,
        steps: Int,
        isOnPeriod: Boolean
    ): String {
        if (isOnPeriod) {
            return "Take care of yourself today $name. Rest when you need to 💜"
        }
        
        return when {
            exerciseDone && waterPercent >= 70 -> 
                "Amazing day $name! You're crushing your $goal goals! 🌟"
            !exerciseDone && time == "Evening" -> 
                "Hey $name, a quick evening walk would be great for your $goal goal! 🚶"
            mealsLogged == 0 && time != "Morning" -> 
                "$name, don't forget to log your meals - I'm here to help! 🍽️"
            waterPercent < 40 -> 
                "Time for some water $name! Hydration helps with your $goal goal 💧"
            steps < 2000 && time == "Evening" -> 
                "Let's get moving $name! Even a short walk counts 👟"
            else -> 
                "Good $time $name! Keep working on your $goal goal 💪"
        }
    }
}
