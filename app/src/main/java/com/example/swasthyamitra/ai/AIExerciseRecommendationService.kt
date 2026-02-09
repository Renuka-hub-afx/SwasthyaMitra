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
        val benefits: String = "", // Added: Personalized benefits explanation
        val gifUrl: String = "",
        val ageExplanation: String = "",
        val genderNote: String = "",
        val motivationalMessage: String = "",
        val estimatedCalories: Int = 0,
        val recommendedDuration: String = "15 mins"
    )
    
    // Cache for exercise name -> GIF path mapping
    private var exerciseGifMap: Map<String, String> = emptyMap()


    private data class ExerciseData(
        val name: String,
        val type: String, // "json" or "folder"
        val path: String, // json gif path or folder path
        val details: String // instructions or "Yoga Pose"
    )

    /**
     * Load exercises based on user gender:
     * - Male: Gym exercises from exercisedb_v1_sample
     * - Female: Yoga poses from exercise 2
     * - Other/Unknown: Mix of both
     */
    private suspend fun loadCombinedExercises(gender: String): List<ExerciseData> = withContext(Dispatchers.IO) {
        val exercises = mutableListOf<ExerciseData>()
        val gifMap = mutableMapOf<String, String>()
        val isMale = gender.equals("Male", ignoreCase = true)
        val isFemale = gender.equals("Female", ignoreCase = true)
        
        try {
            // 1. Load GYM exercises for MALE or UNSPECIFIED users
            if (isMale || (!isMale && !isFemale)) {
                val jsonString = context.assets.open("exercisedb_v1_sample/exercises.json").bufferedReader().use { it.readText() }
                val jsonArray = org.json.JSONArray(jsonString)
                
                for (i in 0 until jsonArray.length()) {
                    val obj = jsonArray.getJSONObject(i)
                    val name = obj.optString("name")
                    val gifPath = "exercisedb_v1_sample/gifs_360x360/" + obj.optString("gifUrl")
                    exercises.add(ExerciseData(
                        name = name,
                        type = "json",
                        path = gifPath,
                        details = "Target: ${obj.optString("target")}, BodyPart: ${obj.optString("bodyPart")}"
                    ))
                    gifMap[name.lowercase()] = gifPath
                }
            }

            // 2. Load YOGA poses for FEMALE or UNSPECIFIED users
            if (isFemale || (!isMale && !isFemale)) {
                val yogaPoses = context.assets.list("exercise 2") ?: emptyArray()
                for (pose in yogaPoses) {
                    val files = context.assets.list("exercise 2/$pose") ?: emptyArray()
                    val imageFile = files.firstOrNull { it.endsWith(".png") || it.endsWith(".jpg") || it.endsWith(".gif") }
                    if (imageFile != null) {
                        val imagePath = "exercise 2/$pose/$imageFile"
                        exercises.add(ExerciseData(
                            name = pose,
                            type = "folder",
                            path = imagePath,
                            details = "Yoga Pose / Stretching"
                        ))
                        gifMap[pose.lowercase()] = imagePath
                    }
                }
            }
            
            // 3. Load exercise types from exercise3.csv (for ALL users)
            try {
                val csvStream = context.assets.open("exercise3.csv")
                val csvReader = java.io.BufferedReader(java.io.InputStreamReader(csvStream))
                val uniqueExercises = mutableMapOf<String, Pair<Int, Int>>() // name -> (avgDuration, avgCalories)
                
                csvReader.readLine() // Skip header
                csvReader.forEachLine { line ->
                    val parts = line.split(",")
                    if (parts.size >= 5) {
                        val exerciseType = parts[2].trim()
                        val duration = parts[3].toIntOrNull() ?: 0
                        val calories = parts[4].toIntOrNull() ?: 0
                        
                        if (exerciseType.isNotEmpty()) {
                            val existing = uniqueExercises[exerciseType]
                            if (existing == null) {
                                uniqueExercises[exerciseType] = Pair(duration, calories)
                            } else {
                                // Average the values
                                uniqueExercises[exerciseType] = Pair(
                                    (existing.first + duration) / 2,
                                    (existing.second + calories) / 2
                                )
                            }
                        }
                    }
                }
                csvReader.close()
                
                // Add unique exercise types from CSV (no images for these)
                for ((exerciseName, stats) in uniqueExercises) {
                    // Skip if already exists from JSON or folder sources
                    if (!gifMap.containsKey(exerciseName.lowercase())) {
                        exercises.add(ExerciseData(
                            name = exerciseName,
                            type = "csv",
                            path = "", // No image available
                            details = "General Exercise (~${stats.first} mins, ~${stats.second} kcal)"
                        ))
                    }
                }
                Log.d(TAG, "Loaded ${uniqueExercises.size} unique exercises from CSV")
            } catch (e: Exception) {
                Log.e(TAG, "Error loading exercise3.csv: ${e.message}")
            }
            
            // Update the class-level map
            exerciseGifMap = gifMap
            
        } catch (e: Exception) {
            Log.e(TAG, "Error loading exercises", e)
        }
        
        exercises
    }

    suspend fun getExerciseRecommendation(
        mood: String = "Neutral", 
        stepCalories: Int = 0
    ): Result<List<ExerciseRec>> = withContext(Dispatchers.IO) {
        try {
            val userId = authHelper.getCurrentUser()?.uid ?: return@withContext Result.failure(Exception("No user logged in"))
            val userData = authHelper.getUserData(userId).getOrNull() ?: emptyMap()
            val userGoal = authHelper.getUserGoal(userId).getOrNull() ?: emptyMap()

            // Safe data extraction - get gender FIRST to filter exercises
            val gender = userData["gender"] as? String ?: "Not specified"
            val age = (userData["age"] as? String)?.toIntOrNull() ?: (userData["age"] as? Long)?.toInt() ?: 25
            val weight = (userData["weight"] as? String)?.toDoubleOrNull() ?: (userData["weight"] as? Double) ?: 70.0
            
            // Load exercises filtered by gender
            val allExercises = loadCombinedExercises(gender)
            // ONLY include exercises that HAVE IMAGES (non-empty path)
            val availableExercises = allExercises.filter { it.path.isNotEmpty() }
            val simplifiedList = availableExercises.joinToString("\n") { "- ${it.name} (${it.details})" }
            val goalType = userGoal["goalType"] as? String ?: "General Fitness"
            val isOnPeriod = userData["isOnPeriod"] as? Boolean ?: false
            
            // Get current time
            val calendar = java.util.Calendar.getInstance()
            val hour = calendar.get(java.util.Calendar.HOUR_OF_DAY)
            val timeOfDay = when (hour) {
                in 5..11 -> "Morning"
                in 12..16 -> "Afternoon"
                in 17..22 -> "Evening"
                else -> "Night"
            }

            // Consumption Status
            val foodLogs = authHelper.getTodayFoodLogs(userId).getOrNull() ?: emptyList()
            val consumed = foodLogs.sumOf { it.calories }
            val targetCalories = (userGoal["dailyCalories"] as? Number)?.toInt() ?: 2000
            
            // Time constraints
            val workoutTimeLimit = "15 mins" // Fixed per exercise for a session flow

            var periodConstraint = ""
            if (isOnPeriod) {
                periodConstraint = """
                    CRITICAL: User is currently on their period.
                    - STRICTLY FORBIDDEN: High intensity, jumping, heavy lifting, crunches, or inversions.
                    - REQUIRED: Gentle stretching, yoga, walking, or light movements.
                    - Focus on pain relief (cramps) and mood boosting.
                """.trimIndent()
            }

            val promptText = """
                You are a friendly Sports Scientist for the SwasthyaMitra app.
                $periodConstraint

                **USER CONTEXT:**
                - Mood: $mood
                - Age: $age years old
                - Gender: $gender
                - Weight: $weight kg
                - Current Goal: $goalType
                - Period Status: ${if (isOnPeriod) "ON PERIOD 🌸" else "Normal"}
                - Current Time: $timeOfDay
                - Session Target: 3 Exercises (~45 mins total)

                **METABOLIC STATUS:**
                - Consumed: $consumed / $targetCalories kcal
                - Burned (Steps): $stepCalories kcal

                **TASK:**
                Create a balanced workout session consisting of exactly **3 DISTINCT exercises**.
                ⚠️ CRITICAL: You MUST choose ONLY from the "Available Exercises" list below.
                ⚠️ Use the EXACT exercise name as written - DO NOT invent new names like "Push-ups" or "Squats".
                ⚠️ Only choose exercises that appear in the list below.
                
                The sequence should be logical (e.g., Warmup -> Main -> Cool-down OR Upper -> Lower -> Core).
                Each exercise should be ~15 minutes.

                **MOOD GUIDANCE:**
                - Sad/Depressed: Gentle, rhythmic items.
                - Stressed/Anxious: Calming, focus-based items.
                - Angry/Frustrated: Higher intensity.
                - Happy/Energetic: Challenging items.
                - Tired: Restorative.

                **LOGIC RULES:**
                ${if (isOnPeriod) """
                1. COMFORT: Choose 3 exercises that feel good and don't cause discomfort
                2. BENEFITS: Prioritize movements that ease cramps and boost mood
                3. GENTLE: Stick to low-intensity
                """ else """
                1. DURATION: Each must be ~15 mins.
                2. MATCH MOOD: Ensure the intensity matches $mood.
                3. VARIETY: Do not repeat the same exercise type 3 times.
                """}

                📋 **Available Exercises (MUST choose from this list ONLY):**
                $simplifiedList

                **STRICT OUTPUT FORMAT (JSON ARRAY ONLY):**
                [
                    {
                      "name": "EXACT name from Available Exercises list above",
                      "targetMuscle": "...",
                      "bodyPart": "...",
                      "equipment": "...",
                      "instructions": ["Step 1", "Step 2"],
                      "reason": "Briefly why this fits the sequence.",
                      "benefits": "Explain specific benefits for a $age-year-old $gender weighing $weight kg with goal '$goalType' and mood '$mood'. If on period, explain how it helps cramps/mood.",
                      "ageExplanation": "Safety note for age $age",
                      "genderNote": "Benefit for $gender",
                      "motivationalMessage": "Short encouraging text.",
                      "estimatedCalories": 120,
                      "recommendedDuration": "15 mins"
                    },
                    ... (Total 3 items)
                ]
            """.trimIndent()

            val config = generationConfig {
                temperature = 0.5f // Slightly higher for variety
                responseMimeType = "application/json"
            }
            val generativeModel = Firebase.ai(backend = GenerativeBackend.googleAI())
                .generativeModel("gemini-2.0-flash", generationConfig = config)
            
            val response = try {
                kotlinx.coroutines.withTimeout(45000) { // 45s timeout for longer gen
                    generativeModel.generateContent(promptText)
                }
            } catch (e: Exception) {
                throw Exception("Coach is busy planning your session. Please try again.")
            }
            
            val jsonStr = response.text ?: throw Exception("AI response empty")
            val cleanJson = jsonStr.trim().removeSurrounding("```json", "```").trim()
            
            val results = mutableListOf<ExerciseRec>()
            
            try {
                // Try parsing as Array first
                val jsonArray = JSONArray(cleanJson)
                for (i in 0 until jsonArray.length()) {
                    val json = jsonArray.getJSONObject(i)
                    results.add(parseExerciseJson(json))
                }
            } catch (e: Exception) {
                // Fallback: Try parsing single object if AI messed up and make it a list of 1
                try {
                    val json = JSONObject(cleanJson)
                    results.add(parseExerciseJson(json))
                } catch (e2: Exception) {
                    throw e // Re-throw original array error or a new one
                }
            }
            
            // ENSURE AT LEAST 3 EXERCISES - fill with fallback if needed
            if (results.size < 3 && availableExercises.isNotEmpty()) {
                val fallbackExercises = availableExercises.shuffled()
                var index = 0
                while (results.size < 3 && index < fallbackExercises.size) {
                    val fallback = fallbackExercises[index]
                    // Avoid duplicates
                    if (results.none { it.name.equals(fallback.name, ignoreCase = true) }) {
                        results.add(ExerciseRec(
                            name = fallback.name,
                            targetMuscle = "Full Body",
                            bodyPart = "Multiple",
                            equipment = "body weight",
                            instructions = listOf("Follow the demonstration", "Maintain proper form", "Breathe steadily"),
                            reason = "Great exercise for your fitness goals!",
                            benefits = "Helps improve strength, flexibility, and overall fitness for a ${age}-year-old ${gender}.",
                            gifUrl = fallback.path,
                            ageExplanation = "Suitable for age $age with proper form",
                            genderNote = "Beneficial for $gender",
                            motivationalMessage = "You've got this! 💪",
                            estimatedCalories = 100,
                            recommendedDuration = "15 mins"
                        ))
                    }
                    index++
                }
                Log.d(TAG, "Added ${3 - results.size + (3 - results.size)} fallback exercises to reach 3")
            }

            Result.success(results)

        } catch (e: Exception) {
            Log.e(TAG, "Error: ${e.message}", e)
            Result.failure(e)
        }
    }

    private fun parseExerciseJson(json: JSONObject): ExerciseRec {
        val name = json.getString("name")
        
        // Smart GIF lookup with multiple strategies:
        // 1. Try exact match (case-insensitive)
        // 2. Try name without parentheses (e.g., "Cat (Yoga Pose)" -> "Cat")
        // 3. Try partial match (if name contains a key from the map)
        val nameLower = name.lowercase()
        var resolvedGifUrl = exerciseGifMap[nameLower] ?: ""
        
        if (resolvedGifUrl.isEmpty()) {
            // Strategy 2: Remove parenthetical suffix
            val nameWithoutParens = name.substringBefore("(").trim().lowercase()
            resolvedGifUrl = exerciseGifMap[nameWithoutParens] ?: ""
        }
        
        if (resolvedGifUrl.isEmpty()) {
            // Strategy 3: Check if any key is contained in the name
            for ((key, path) in exerciseGifMap) {
                if (nameLower.contains(key) || key.contains(nameLower.substringBefore("(").trim())) {
                    resolvedGifUrl = path
                    break
                }
            }
        }
        
        Log.d(TAG, "Resolved GIF for '$name' -> '$resolvedGifUrl'")
        
        return ExerciseRec(
            name = name,
            targetMuscle = json.optString("targetMuscle", ""),
            bodyPart = json.optString("bodyPart", ""),
            equipment = json.optString("equipment", "body weight"),
            instructions = mutableListOf<String>().apply {
                val arr = json.optJSONArray("instructions") ?: JSONArray()
                for (i in 0 until arr.length()) add(arr.getString(i))
            },
            reason = json.optString("reason", ""),
            benefits = json.optString("benefits", "Great for your overall health!"),
            gifUrl = resolvedGifUrl, // Use the looked-up path, not AI response
            ageExplanation = json.optString("ageExplanation", ""),
            genderNote = json.optString("genderNote", ""),
            motivationalMessage = json.optString("motivationalMessage", ""),
            estimatedCalories = json.optInt("estimatedCalories", 100),
            recommendedDuration = json.optString("recommendedDuration", "15 mins")
        )
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
