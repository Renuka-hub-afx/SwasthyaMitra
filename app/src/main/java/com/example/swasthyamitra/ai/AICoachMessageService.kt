package com.example.swasthyamitra.ai

import android.content.Context
import android.util.Log
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.example.swasthyamitra.data.repository.HydrationRepository
import com.google.firebase.Firebase
import com.google.firebase.vertexai.vertexAI
import com.google.firebase.vertexai.type.generationConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Calendar

class AICoachMessageService private constructor(private val context: Context) {

    private val authHelper = FirebaseAuthHelper(context)
    private val hydrationRepo = HydrationRepository()
    private val TAG = "AICoachService"

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

            val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
            val timeOfDay = when (hour) {
                in 5..11 -> "Morning"
                in 12..16 -> "Afternoon"
                in 17..21 -> "Evening"
                else -> "Night"
            }

            // 2. Construct Prompt
            val periodContext = if (isOnPeriod) {
                "STRICT: The user is currently on her period (menstrual days). Switch tone to: EMPATHETIC, GENTLE, SUPPORTIVE. Focus on self-care, rest, and comfort."
            } else {
                "Tone: Encouraging, Concise, Scientific."
            }

            val promptText = """
                You are a professional Health & Fitness Coach for the SwasthyaMitra app.
                $periodContext
                
                **USER PROFILE:**
                - Name: $userName
                - Main Goal: $currentGoal
                - Current Time: $timeOfDay
                - Period Status: ${if (isOnPeriod) "ON PERIOD" else "Normal"}
                
                **TODAY'S LOGS:**
                - Calories: $consumed / $targetCalories kcal
                - Net Calories (Goal Adjustment): $netCalories kcal
                - Macros: P:${totalProtein.toInt()}g, C:${totalCarbs.toInt()}g, F:${totalFat.toInt()}g
                - Water: $waterTotal / $waterGoal ml
                - Activity (Steps): $steps steps ($burnedFromSteps kcal burned)
                
                **TASK:**
                Provide a 1-sentence personalized and insightful coach message based on the data above.
                
                **LOGIC RULES:**
                ${if (isOnPeriod) """
                1. EMOTIONAL SUPPORT: Acknowledge that she might feel low energy or discomfort. 
                2. GENTLE ADVICE: Suggest rest, hydration, or heat therapy for cramps.
                3. NO PRESSURE: Reinforce that it's okay to slow down and listen to her body.
                4. CULTURAL FLEXIBILITY: Use warm, caring language.
                """ else """
                1. If water is low (< 50% of goal) and it's afternoon/evening, mention hydration.
                2. If protein is low (< 40g), suggest a protein-rich snack.
                3. If on track (±100 kcal), congratulate on balance.
                4. If steps are low (< 3000) and it's evening, suggest a short walk.
                """}
                5. Address them by name ($userName).
                
                **OUTPUT:**
                Just the message string. No JSON, no quotes.
            """.trimIndent()

            // 3. Call AI
            val config = generationConfig {
                temperature = 0.7f
            }
            val generativeModel = Firebase.vertexAI.generativeModel("gemini-1.5-flash", generationConfig = config)
            
            val response = try {
                kotlinx.coroutines.withTimeout(15000) { // 15s timeout
                    generativeModel.generateContent(promptText)
                }
            } catch (e: Exception) {
                // Fallback to basic logic if AI fails
                return@withContext Result.success(getFallbackMessage(userName, currentGoal, timeOfDay))
            }
            
            val message = response.text?.trim() ?: getFallbackMessage(userName, currentGoal, timeOfDay)
            Result.success(message)

        } catch (e: Exception) {
            Log.e(TAG, "Error: ${e.message}", e)
            Result.failure(e)
        }
    }

    private fun getFallbackMessage(name: String, goal: String, time: String): String {
        return "Good $time $name! Keep logging your activities to reach your $goal goal."
    }
}
