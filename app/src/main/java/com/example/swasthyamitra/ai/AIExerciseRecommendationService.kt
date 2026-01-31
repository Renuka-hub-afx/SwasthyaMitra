package com.example.swasthyamitra.ai

import android.content.Context
import android.util.Log
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.google.firebase.Firebase
import com.google.firebase.vertexai.vertexAI
import com.google.firebase.vertexai.type.content
import com.google.firebase.vertexai.type.generationConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

class AIExerciseRecommendationService private constructor(private val context: Context) {

    private val authHelper = FirebaseAuthHelper(context)
    private val TAG = "AIExerciseService"

    companion object {
        @Volatile
        private var INSTANCE: AIExerciseRecommendationService? = null

        fun getInstance(context: Context): AIExerciseRecommendationService {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: AIExerciseRecommendationService(context.applicationContext).also { INSTANCE = it }
            }
        }
    }

    data class ExerciseRec(
        val name: String,
        val targetMuscle: String,
        val bodyPart: String,
        val equipment: String,
        val instructions: List<String>,
        val reason: String
    )

    suspend fun getExerciseRecommendation(stepCalories: Int = 0): Result<ExerciseRec> = withContext(Dispatchers.IO) {
        try {
            val user = authHelper.getCurrentUser() ?: return@withContext Result.failure(Exception("User not logged in"))
            val userId = user.uid

            // 1. Fetch User Data
            val profile = authHelper.getUserData(userId).getOrThrow()
            val goal = authHelper.getUserGoal(userId).getOrThrow()
            val caloriesResult = authHelper.getTodayCalories(userId)
            val foodLogs = authHelper.getTodayFoodLogs(userId).getOrNull() ?: emptyList()

            val age = (profile["age"] as? Number)?.toInt() ?: 25
            val gender = profile["gender"] as? String ?: "Unknown"
            val goalType = goal["goalType"] as? String ?: "General Fitness"
            val consumed = caloriesResult.getOrDefault(0)
            val targetCalories = (goal["dailyCalories"] as? Number)?.toInt() ?: 2000
            val workoutTimeLimit = profile["availableExerciseTime"] as? String ?: "30m"
            val preferredTime = profile["preferredExerciseTime"] as? String ?: "Anytime"
            
            val totalProtein = foodLogs.sumOf { it.protein }
            val totalCarbs = foodLogs.sumOf { it.carbs }
            val totalFat = foodLogs.sumOf { it.fat }
            
            // Get current time of day
            val hour = java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY)
            val timeOfDay = when (hour) {
                in 5..11 -> "Morning"
                in 12..16 -> "Afternoon"
                in 17..21 -> "Evening"
                else -> "Night"
            }

            // 2. Load Exercises from Assets
            val exerciseSample = loadExerciseSample(15)

            // 3. Construct Prompt
            val promptText = """
                You are a Sports Scientist for the SwasthyaMitra app.
                
                **USER CONTEXT:**
                - Age: $age
                - Gender: $gender
                - Activity Level: $goalType
                - Current Goal: $goalType
                - Current Time of Day: $timeOfDay
                - Preferred Exercise Time: $preferredTime
                - Available Workout Time: $workoutTimeLimit
                
                **METABOLIC STATUS (TODAY):**
                - Consumed: $consumed / $targetCalories kcal
                - Burned (Steps): $stepCalories kcal
                - Net Macros: P: ${totalProtein.toInt()}g, C: ${totalCarbs.toInt()}g, F: ${totalFat.toInt()}g
                
                **TASK:**
                Recommend exactly ONE exercise from the provided list that best fits current context.
                
                **LOGIC RULES:**
                1. DURATION: Must fit within $workoutTimeLimit. (e.g., if 15m, suggest shorter burst or Yoga).
                2. GENDER: Tailor exercise choice to $gender.
                3. PREFERRED TIME: 
                   - If Preferred Time is "$preferredTime" and Current Time is "$timeOfDay", strongly encourage the user to workout now.
                   - If Current Time is NOT Preferred Time, suggest a lighter "bridge" activity or tell them why they should prep for their preferred slot.
                4. TIME OF DAY (Specifics): 
                   - Morning: Focus on mobility, yoga, or light cardio to wake up.
                   - Evening: Focus on burning excess calories (if consumed > target) or strength.
                4. STEP BURN: If Step Burn is HIGH (> 400 kcal), recommend recovery/low-intensity to avoid overtraining.
                5. NUTRITION: 
                   - High Carbs consumed -> Suggest Fat Burning/Cardio.
                   - High Protein consumed -> Suggest Strength/Resistance.
                6. AGE: 
                   - If Age < 18: Focus on bodyweight, flexibility, and fun movement. No heavy weights.
                   - If Age > 50: Strictly low-impact (Joint protection is #1). No high jumping or extreme loads.
                   - General: Optimize for metabolic health based on age group.
                
                Available Exercises (JSON):
                $exerciseSample
                
                **STRICT OUTPUT FORMAT (JSON ONLY):**
                {
                  "name": "Exercise Name",
                  "targetMuscle": "...",
                  "bodyPart": "...",
                  "equipment": "...",
                  "instructions": ["Step 1", "Step 2"],
                  "reason": "Explain why this exercise fits their gender ($gender), age ($age), and current diet."
                }
            """.trimIndent()

            // 4. Call Gemini 2.0
            val config = generationConfig {
                temperature = 0.4f
                responseMimeType = "application/json"
            }
            val generativeModel = Firebase.vertexAI.generativeModel("gemini-2.0-flash", generationConfig = config)
            
            val response = try {
                kotlinx.coroutines.withTimeout(30000) { // 30s timeout
                    generativeModel.generateContent(promptText)
                }
            } catch (e: kotlinx.coroutines.TimeoutCancellationException) {
                throw Exception("Coach is busy thinking. Please try again.")
            }
            
            val jsonStr = response.text ?: throw Exception("AI response empty")
            val cleanJson = jsonStr.trim().removeSurrounding("```json", "```").trim()
            
            val json = JSONObject(cleanJson)
            val result = ExerciseRec(
                json.getString("name"),
                json.getString("targetMuscle"),
                json.getString("bodyPart"),
                json.getString("equipment"),
                mutableListOf<String>().apply {
                    val arr = json.getJSONArray("instructions")
                    for (i in 0 until arr.length()) add(arr.getString(i))
                },
                json.getString("reason")
            )

            Result.success(result)

        } catch (e: Exception) {
            Log.e(TAG, "Error: ${e.message}", e)
            Result.failure(e)
        }
    }

    private fun loadExerciseSample(limit: Int): String {
        return try {
            val input = context.assets.open("exercisedb_v1_sample/exercises.json")
            val reader = BufferedReader(InputStreamReader(input))
            val content = reader.readText()
            val allExercises = JSONArray(content)
            
            val sample = JSONArray()
            val indices = (0 until allExercises.length()).shuffled().take(limit)
            for (i in indices) {
                sample.put(allExercises.getJSONObject(i))
            }
            sample.toString()
        } catch (e: Exception) {
            Log.e(TAG, "Error loading JSON: ${e.message}")
            "[]"
        }
    }
}
