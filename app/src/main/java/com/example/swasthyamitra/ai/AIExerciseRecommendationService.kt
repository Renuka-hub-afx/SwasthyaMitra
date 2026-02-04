package com.example.swasthyamitra.ai

import android.content.Context
import android.util.Log
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
import com.google.firebase.ai.type.generationConfig
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
        val reason: String,
        val gifUrl: String = "",
        val ageExplanation: String = "",
        val genderNote: String = "",
        val motivationalMessage: String = ""
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
            val isOnPeriod = profile["isOnPeriod"] as? Boolean ?: false
            
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

            // 2. Load Exercises from Assets with Smart Filtering
            val exerciseSample = loadSmartExerciseSample(gender, age, isOnPeriod, 15)

            // 3. Construct Prompt with Motivational Support
            val periodConstraint = if (isOnPeriod) {
                """
                **PERIOD MODE - MOTIVATIONAL SUPPORT:**
                The user is on her period. Be extra encouraging and supportive!
                - RECOMMEND: Gentle yoga, stretching, breathing exercises, light walking
                - FOCUS: Exercises that ease cramps, boost mood, and improve circulation
                - TONE: Warm, empathetic, and motivating (e.g., "You're doing great by listening to your body!")
                - BENEFITS: Explain how the exercise helps with period symptoms
                - Include a motivationalMessage field with encouraging words
                """
            } else ""

            val promptText = """
                You are a friendly Sports Scientist for the SwasthyaMitra app.
                $periodConstraint
                
                **USER CONTEXT:**
                - Age: $age years old
                - Gender: $gender
                - Current Goal: $goalType
                - Period Status: ${if (isOnPeriod) "ON PERIOD 🌸" else "Normal"}
                - Current Time: $timeOfDay
                - Available Time: $workoutTimeLimit
                
                **METABOLIC STATUS:**
                - Consumed: $consumed / $targetCalories kcal
                - Burned (Steps): $stepCalories kcal
                
                **TASK:**
                Recommend exactly ONE exercise from the provided list that best fits the user's current context.
                
                **GENDER-SPECIFIC GUIDANCE:**
                ${if (gender.equals("Female", ignoreCase = true)) """
                - For females: Focus on exercises that support pelvic floor strength, core stability, and overall wellness
                - Consider hormonal cycle impact on energy and recovery
                - Emphasize functional strength for daily activities
                """ else """
                - For males: Focus on building strength, power, and athletic performance
                - Emphasize progressive overload and muscle building
                - Include compound movements for maximum efficiency
                """}
                
                **AGE-SPECIFIC GUIDANCE:**
                ${when {
                    age < 18 -> "Young athlete: Focus on bodyweight exercises, proper form, and building foundation. Avoid heavy weights."
                    age in 18..50 -> "Prime years: Perfect time for building strength and muscle. Can handle moderate to high intensity."
                    else -> "Mature fitness: Prioritize low-impact exercises, joint health, and maintaining mobility. Focus on controlled movements."
                }}
                
                **LOGIC RULES:**
                ${if (isOnPeriod) """
                1. COMFORT: Choose exercises that feel good and don't cause discomfort
                2. BENEFITS: Prioritize movements that ease cramps and boost mood
                3. GENTLE: Stick to low-intensity, restorative activities
                4. MOTIVATION: Be extra encouraging and supportive!
                """ else """
                1. DURATION: Must fit within $workoutTimeLimit
                2. NUTRITION: High Carbs → Cardio/HIIT. High Protein → Strength training
                3. INTENSITY: Match to current energy levels and goals
                4. PROGRESSION: Challenge appropriately for age and fitness level
                """}
                
                Available Exercises (JSON):
                $exerciseSample
                
                **STRICT OUTPUT FORMAT (JSON ONLY):**
                {
                  "name": "Exercise Name",
                  "targetMuscle": "...",
                  "bodyPart": "...",
                  "equipment": "...",
                  "instructions": ["Step 1", "Step 2"],
                  "reason": "Explain why this exercise is perfect for their current situation",
                  "gifUrl": "path/to/exercise.gif",
                  "ageExplanation": "Why this exercise is great for age $age",
                  "genderNote": "Gender-specific benefits for $gender",
                  "motivationalMessage": "${if (isOnPeriod) "Encouraging message for period mode" else ""}"
                }
            """.trimIndent()

            // 4. Call Gemini 2.0
            val config = generationConfig {
                temperature = 0.4f
                responseMimeType = "application/json"
            }
            val generativeModel = Firebase.ai(backend = GenerativeBackend.googleAI())
                .generativeModel("gemini-2.0-flash", generationConfig = config)
            
            val response = try {
                kotlinx.coroutines.withTimeout(30000) { // 30s timeout
                    generativeModel.generateContent(promptText)
                }
            } catch (e: Exception) {
                throw Exception("Coach is busy thinking. Please try again.")
            }
            
            val jsonStr = response.text ?: throw Exception("AI response empty")
            val cleanJson = jsonStr.trim().removeSurrounding("```json", "```").trim()
            
            val json = JSONObject(cleanJson)
            val result = ExerciseRec(
                name = json.getString("name"),
                targetMuscle = json.getString("targetMuscle"),
                bodyPart = json.getString("bodyPart"),
                equipment = json.getString("equipment"),
                instructions = mutableListOf<String>().apply {
                    val arr = json.getJSONArray("instructions")
                    for (i in 0 until arr.length()) add(arr.getString(i))
                },
                reason = json.getString("reason"),
                gifUrl = json.optString("gifUrl", ""),
                ageExplanation = json.optString("ageExplanation", ""),
                genderNote = json.optString("genderNote", ""),
                motivationalMessage = json.optString("motivationalMessage", "")
            )

            Result.success(result)

        } catch (e: Exception) {
            Log.e(TAG, "Error: ${e.message}", e)
            Result.failure(e)
        }
    }

    private fun loadSmartExerciseSample(gender: String, age: Int, isOnPeriod: Boolean, limit: Int): String {
        return try {
            val input = context.assets.open("combined_exercises.json")
            val reader = BufferedReader(InputStreamReader(input))
            val content = reader.readText()
            val allExercises = JSONArray(content)
            
            // Filter exercises based on user context
            val filtered = mutableListOf<JSONObject>()
            for (i in 0 until allExercises.length()) {
                val exercise = allExercises.getJSONObject(i)
                
                // Period Mode: ONLY include period-safe exercises
                if (isOnPeriod) {
                    if (exercise.optBoolean("periodModeSafe", false)) {
                        filtered.add(exercise)
                    }
                    continue
                }
                
                // Gender filtering (prefer exercises tagged for user's gender)
                val genderTags = exercise.optJSONArray("genderTags")
                var genderMatch = false
                if (genderTags != null) {
                    for (j in 0 until genderTags.length()) {
                        if (genderTags.getString(j).equals(gender, ignoreCase = true)) {
                            genderMatch = true
                            break
                        }
                    }
                }
                
                // Age filtering
                val ageGroups = exercise.optJSONArray("ageGroups")
                var ageMatch = false
                if (ageGroups != null) {
                    for (j in 0 until ageGroups.length()) {
                        val ageGroup = ageGroups.getString(j)
                        when {
                            ageGroup == "all" -> ageMatch = true
                            ageGroup == "under18" && age < 18 -> ageMatch = true
                            ageGroup == "18-50" && age in 18..50 -> ageMatch = true
                            ageGroup == "over50" && age > 50 -> ageMatch = true
                        }
                    }
                }
                
                // Include if matches gender OR age (flexible filtering)
                if (genderMatch || ageMatch) {
                    filtered.add(exercise)
                }
            }
            
            // If no matches, fall back to all exercises
            val finalList = if (filtered.isEmpty()) {
                mutableListOf<JSONObject>().apply {
                    for (i in 0 until allExercises.length()) {
                        add(allExercises.getJSONObject(i))
                    }
                }
            } else filtered
            
            // Randomly sample from filtered list
            val sample = JSONArray()
            val sampled = finalList.shuffled().take(limit.coerceAtMost(finalList.size))
            for (exercise in sampled) {
                sample.put(exercise)
            }
            
            sample.toString()
        } catch (e: Exception) {
            Log.e(TAG, "Error loading exercises: ${e.message}", e)
            "[]"
        }
    }
    
    @Deprecated("Use loadSmartExerciseSample instead")
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
