package com.example.swasthyamitra.ai

import android.content.Context
import android.util.Log
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.google.firebase.firestore.FirebaseFirestore
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
    private val firestore = FirebaseFirestore.getInstance("renu") // Added this line as per instruction
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
        val benefits: String = "", // Personalized benefits explanation
        val gifUrl: String = "",
        val ageExplanation: String = "",
        val genderNote: String = "",
        val motivationalMessage: String = "",
        val estimatedCalories: Int = 0,
        val recommendedDuration: String = "15 mins",
        
        // NEW: Enhanced fields for detailed explanations
        val intensity: String = "light", // "light", "moderate", "high"
        val goalAlignment: String = "", // How it helps their specific goal
        val tips: List<String> = emptyList(), // Pro tips for better execution
        val commonMistakes: List<String> = emptyList() // What to avoid
    )
    
    // Cache for exercise name -> GIF path mapping
    private var exerciseGifMap: Map<String, String> = emptyMap()


    private data class ExerciseData(
        val name: String,
        val type: String, // "json", "folder", or "csv"
        val path: String, // json gif path, folder path, or empty
        val details: String, // instructions or metadata
        val isPeriodSafe: Boolean = false // Strict filtering flag
    )

    /**
     * Load ALL exercises from all available datasets.
     * Tags them with properties like isPeriodSafe for downstream filtering.
     */
    private suspend fun loadAllExercises(): List<ExerciseData> = withContext(Dispatchers.IO) {
        val exercises = mutableListOf<ExerciseData>()
        val gifMap = mutableMapOf<String, String>()
        
        try {
            // 1. Load GYM exercises (exercisedb_v1_sample) - Machines/Heavy Weights
            try {
                val jsonString = context.assets.open("exercisedb_v1_sample/exercises.json").bufferedReader().use { it.readText() }
                val jsonArray = org.json.JSONArray(jsonString)
                
                for (i in 0 until jsonArray.length()) {
                    val obj = jsonArray.getJSONObject(i)
                    val name = obj.optString("name")
                    val gifPath = "exercisedb_v1_sample/gifs_360x360/" + obj.optString("gifUrl")
                    
                    // Gym exercises are generally NOT period-friendly (heavy lifting, machines)
                    exercises.add(ExerciseData(
                        name = name,
                        type = "json",
                        path = gifPath,
                        details = "Target: ${obj.optString("target")}, BodyPart: ${obj.optString("bodyPart")}",
                        isPeriodSafe = false
                    ))
                    gifMap[name.lowercase()] = gifPath
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error loading gym exercises", e)
            }

            // 2. Load YOGA poses (exercise 2) - Yoga/Stretching
            try {
                val yogaPoses = context.assets.list("exercise 2") ?: emptyArray()
                for (pose in yogaPoses) {
                    val files = context.assets.list("exercise 2/$pose") ?: emptyArray()
                    val imageFile = files.firstOrNull { it.endsWith(".png") || it.endsWith(".jpg") || it.endsWith(".gif") }
                    if (imageFile != null) {
                        val imagePath = "exercise 2/$pose/$imageFile"
                        
                        // Yoga is generally Period Safe
                        exercises.add(ExerciseData(
                            name = pose,
                            type = "folder",
                            path = imagePath,
                            details = "Yoga Pose / Stretching",
                            isPeriodSafe = true
                        ))
                        gifMap[pose.lowercase()] = imagePath
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error loading yoga exercises", e)
            }
            
            // 3. Load exercise types from exercise3.csv (Cardio/Sports/General)
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
                
                // Add unique exercise types from CSV
                for ((exerciseName, stats) in uniqueExercises) {
                    // Skip if already exists from JSON or folder sources
                    if (!gifMap.containsKey(exerciseName.lowercase())) {
                        val lowerName = exerciseName.lowercase()
                        // Determine safety based on keywords
                        val isSafe = lowerName.contains("yoga") || 
                                     lowerName.contains("pilates") || 
                                     lowerName.contains("walking") || 
                                     lowerName.contains("stretching") ||
                                     lowerName.contains("meditation")
                        
                        exercises.add(ExerciseData(
                            name = exerciseName,
                            type = "csv",
                            path = "", // No image available
                            details = "General Exercise (~${stats.first} mins, ~${stats.second} kcal)",
                            isPeriodSafe = isSafe
                        ))
                    }
                }
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
            
            // Load ALL exercises
            val allExercises = loadAllExercises()
            
            val isOnPeriod = userData["isOnPeriod"] as? Boolean ?: false

            // Filter exercises based on context
            val filteredExercises = if (isOnPeriod) {
                // strict mode: only period-safe
                allExercises.filter { it.isPeriodSafe && it.path.isNotEmpty() }
            } else {
                // normal mode: only those with images (for quality)
                allExercises.filter { it.path.isNotEmpty() }
            }

            // Fallback: If strict filtering leaves nothing (unlikely), relax it slightly
            val usableExercises = if (filteredExercises.isEmpty()) {
                 allExercises.filter { it.path.isNotEmpty() } 
            } else {
                 filteredExercises
            }

            val simplifiedList = usableExercises.joinToString("\n") { "- ${it.name} (${it.details})" }
            val goalType = userGoal["goalType"] as? String ?: "General Fitness"
            
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
                ### ✅ Exercise Workout Generator

                You are a **Fitness Coach** creating detailed workouts.

                **User:** Age $age | Gender $gender | Goal $goalType | Mood $mood
                Period: ${if (isOnPeriod) "Active (gentle only)" else "Inactive"}
                Calories: $consumed/$targetCalories

                **Exercises Available:** $simplifiedList

                ### Task: 3 exercises (Warm-up → Main → Cool-down)

                ### Rules:
                ${if (isOnPeriod) "**PERIOD MODE**: Only yoga/stretching. NO jumping/heavy!" else "Match intensity to mood"}
                - Reason: 1-2 sentences explaining why this exercise
                - Age/gender notes: Provide meaningful insights
                - Tips: 2-3 practical tips for better execution
                - Mistakes: 2-3 common mistakes to avoid
                - Motivational message: ONLY if period active

                ### Output JSON (3 exercises):
                [
                  {
                    "name": "Exercise name from list",
                    "targetMuscle": "Target area",
                    "bodyPart": "Body region",
                    "equipment": "Bodyweight or equipment",
                    "instructions": ["Step 1", "Step 2", "Step 3"],
                    "reason": "Why this exercise is good for you now",
                    "benefits": "How it helps your fitness goals",
                    "ageExplanation": "How age $age benefits from this",
                    "genderNote": "Specific benefit for $gender",
                    "motivationalMessage": "${if (isOnPeriod) "Period-friendly encouragement" else ""}",
                    "estimatedCalories": ${if (isOnPeriod) 50 else 120},
                    "recommendedDuration": "15 mins",
                    "intensity": "${if (isOnPeriod) "light" else "moderate"}",
                    "goalAlignment": "How it helps $goalType",
                    "tips": ["Tip 1: Keep your back straight", "Tip 2: Breathe deeply"],
                    "commonMistakes": ["Mistake 1: Arching back too much", "Mistake 2: Holding breath"]
                  }
                ]
                
                Make sure tips and mistakes are specific and actionable!
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
                    val rec = parseExerciseJson(json)
                    // STRICT CHECK: Only accept if we successfully resolved an image
                    if (rec.gifUrl.isNotEmpty()) {
                        results.add(rec)
                    }
                }
            } catch (e: Exception) {
                // Fallback: Try parsing single object if AI messed up
                try {
                    val json = JSONObject(cleanJson)
                    val rec = parseExerciseJson(json)
                    if (rec.gifUrl.isNotEmpty()) {
                        results.add(rec)
                    }
                } catch (e2: Exception) {
                    // ignore
                }
            }
            
            // ENSURE AT LEAST 3 EXERCISES - fill with fallback if needed
            if (results.size < 3 && usableExercises.isNotEmpty()) {
                val fallbackExercises = usableExercises.shuffled()
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
            recommendedDuration = json.optString("recommendedDuration", "15 mins"),
            
            // NEW: Enhanced fields
            intensity = json.optString("intensity", "light"),
            goalAlignment = json.optString("goalAlignment", "Supports your fitness journey"),
            tips = mutableListOf<String>().apply {
                val arr = json.optJSONArray("tips") ?: JSONArray()
                for (i in 0 until arr.length()) add(arr.getString(i))
            },
            commonMistakes = mutableListOf<String>().apply {
                val arr = json.optJSONArray("commonMistakes") ?: JSONArray()
                for (i in 0 until arr.length()) add(arr.getString(i))
            }
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
