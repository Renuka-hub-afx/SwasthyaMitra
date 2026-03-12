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
        val isPeriodSafe: Boolean = false, // Strict filtering flag
        val genderTags: List<String> = emptyList(),  // e.g. ["female", "male", "all"]
        val ageGroups: List<String> = emptyList()    // e.g. ["18-50", "over50", "all"]
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

            // 4. Load combined_exercises.json (enhanced metadata with gender/age/period tags)
            try {
                val jsonString = context.assets.open("combined_exercises.json").bufferedReader().use { it.readText() }
                val jsonArray = org.json.JSONArray(jsonString)
                
                for (i in 0 until jsonArray.length()) {
                    val obj = jsonArray.getJSONObject(i)
                    val name = obj.optString("name", "").trim()
                    if (name.isEmpty()) continue
                    
                    val gifUrl = obj.optString("gifUrl", "")
                    val isPeriodSafe = obj.optBoolean("periodModeSafe", false)
                    
                    // Build rich details from enhanced metadata
                    val targetMuscles = mutableListOf<String>()
                    val tmArr = obj.optJSONArray("targetMuscles")
                    if (tmArr != null) {
                        for (j in 0 until tmArr.length()) targetMuscles.add(tmArr.getString(j))
                    }
                    val bodyParts = mutableListOf<String>()
                    val bpArr = obj.optJSONArray("bodyParts")
                    if (bpArr != null) {
                        for (j in 0 until bpArr.length()) bodyParts.add(bpArr.getString(j))
                    }
                    val intensityLevel = obj.optString("intensityLevel", "moderate")
                    val periodBenefits = obj.optString("periodBenefits", "")
                    
                    val details = buildString {
                        append("Target: ${targetMuscles.joinToString(", ")}")
                        append(", BodyPart: ${bodyParts.joinToString(", ")}")
                        append(", Intensity: $intensityLevel")
                        if (isPeriodSafe && periodBenefits.isNotEmpty()) {
                            append(", PeriodBenefit: $periodBenefits")
                        }
                    }
                    
                    val nameLower = name.lowercase()
                    
                    // If already exists, just update the gif map (don't add duplicate)
                    if (gifMap.containsKey(nameLower)) {
                        // Merge: update gif path if combined has a better one
                        if (gifUrl.isNotEmpty() && gifMap[nameLower]?.isEmpty() == true) {
                            gifMap[nameLower] = gifUrl
                        }
                    } else {
                        // Read gender & age tags from combined_exercises.json
                        val genderTagsList = mutableListOf<String>()
                        val genderTagsArr = obj.optJSONArray("genderTags")
                        if (genderTagsArr != null) {
                            for (j in 0 until genderTagsArr.length()) genderTagsList.add(genderTagsArr.getString(j).lowercase())
                        }
                        val ageGroupsList = mutableListOf<String>()
                        val ageGroupsArr = obj.optJSONArray("ageGroups")
                        if (ageGroupsArr != null) {
                            for (j in 0 until ageGroupsArr.length()) ageGroupsList.add(ageGroupsArr.getString(j).lowercase())
                        }

                        // New exercise from combined_exercises.json
                        exercises.add(ExerciseData(
                            name = name,
                            type = "combined",
                            path = gifUrl,
                            details = details,
                            isPeriodSafe = isPeriodSafe,
                            genderTags = genderTagsList,
                            ageGroups = ageGroupsList
                        ))
                        if (gifUrl.isNotEmpty()) {
                            gifMap[nameLower] = gifUrl
                        }
                    }
                }
                Log.d(TAG, "Loaded combined_exercises.json, total exercises: ${exercises.size}")
            } catch (e: Exception) {
                Log.e(TAG, "Error loading combined_exercises.json: ${e.message}")
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
            val genderLower = gender.lowercase()

            // ─── Step 1: Period-mode or visual filter ───────────────────────
            val periodOrImageFiltered = if (isOnPeriod) {
                allExercises.filter { it.isPeriodSafe && it.path.isNotEmpty() }
            } else {
                allExercises.filter { it.path.isNotEmpty() }
            }

            // ─── Step 2: Gender + Age filter on top ─────────────────────────
            // For exercises that came from combined_exercises.json (have tags),
            // keep only those matching the user's gender & age.
            // For exercises from other sources (no tags), always keep them.
            val ageGroupKey = when {
                age < 18         -> "under18"
                age in 18..50    -> "18-50"
                else             -> "over50"
            }

            val genderAgeFiltered = periodOrImageFiltered.filter { ex ->
                // If this exercise has no tags it came from a generic source – keep it
                if (ex.genderTags.isEmpty() && ex.ageGroups.isEmpty()) return@filter true

                val genderOk = ex.genderTags.isEmpty() ||
                               ex.genderTags.contains("all") ||
                               ex.genderTags.contains(genderLower)

                val ageOk = ex.ageGroups.isEmpty() ||
                            ex.ageGroups.contains("all") ||
                            ex.ageGroups.contains(ageGroupKey)

                genderOk && ageOk
            }

            Log.d(TAG, "Gender/Age filter: ${periodOrImageFiltered.size} -> ${genderAgeFiltered.size} exercises for $gender / age $age")

            // ─── Step 3: Fallback if too few results ─────────────────────────
            val usableExercises = when {
                genderAgeFiltered.size >= 10 -> genderAgeFiltered          // Enough variety
                genderAgeFiltered.isNotEmpty() -> genderAgeFiltered        // Use what we have
                periodOrImageFiltered.isNotEmpty() -> periodOrImageFiltered // Drop gender/age filter
                else -> allExercises.filter { it.path.isNotEmpty() }        // Last resort
            }

            // Shuffle and cap the list sent to Gemini (keeps prompt size manageable)
            val exerciseSample = usableExercises.shuffled().take(60)
            val simplifiedList = exerciseSample.joinToString("\n") { "- ${it.name} (${it.details})" }
            val goalType = userGoal["goalType"] as? String ?: "General Fitness"

            // Gender-specific coaching context for the prompt
            val genderContext = when (genderLower) {
                "female", "woman", "f" -> """
                    Gender: FEMALE
                    ======================
                    MUST PRIORITISE (choose exercises from these categories):
                    - Lower body strength: Glute bridges, squats, lunges, hip thrusts, clamshells, donkey kicks.
                    - Core stability: Planks, dead bugs, bird-dogs, Pilates-based moves (NOT crunches).
                    - Full-body toning: Resistance band work, bodyweight circuits, yoga flows.
                    - Flexibility & mobility: Hip flexor stretches, hamstring stretches, spinal twists.

                    AVOID unless explicitly requested:
                    - Heavy barbell bench press, heavy deadlifts, max-weight squats.
                    - Chest-dominant exercises (push-ups are fine, but not chest fly or cable crossovers as primary).

                    PELVIC FLOOR AWARENESS:
                    - Avoid extreme impact or heavy lifting if user shows fatigue/period mode.
                    - Recommend diaphragmatic breathing cues in cool-down.

                    GOAL ALIGNMENT:
                    - Weight Loss → priority: calorie-burning HIIT-light, glute work.
                    - Muscle Gain → priority: glutes, legs, shoulders (not bulk chest).
                    - General Fitness → balanced core + lower body + light cardio.
                    - Stress/Mood → yoga, walking, stretching.

                    Period active: ${if (isOnPeriod) "YES — gentle yoga/walk ONLY" else "No — normal female protocol"}.
                """.trimIndent()
                "male", "man", "m" -> """
                    Gender: MALE
                    ======================
                    MUST PRIORITISE (choose exercises from these categories):
                    - Upper body compound lifts: Push-ups, pull-ups, dips, bench press, overhead press, rows.
                    - Lower body compound: Squats, deadlifts, lunges, leg press.
                    - Core power: Plank variations, hanging knee raises, Russian twists.
                    - Cardio & endurance: Running, jumping jacks, burpees, cycling.

                    PROGRESSIVE OVERLOAD:
                    - For each exercise, suggest a progression (e.g., go from push-up → diamond push-up → archer push-up).
                    - Include a note on rep range: strength (3–5 reps), hypertrophy (8–12 reps), endurance (15+).

                    MUSCLE FOCUS MAP:
                    - Chest & Triceps → push-ups, dips, bench press.
                    - Back & Biceps → pull-ups, rows, face pulls.
                    - Shoulder → overhead press, lateral raises.
                    - Core → plank, dead bug, leg raises.
                    - Legs → squats, deadlifts, lunges, calf raises.

                    GOAL ALIGNMENT:
                    - Weight Loss → HIIT circuits, compound supersets, high rep cardio.
                    - Muscle Gain → compound lifts with progressive overload, 48-hr muscle rest.
                    - General Fitness → full body 3-day split.
                    - Stress/Mood → boxing/cardio to release energy, heavy compound to release tension.
                """.trimIndent()
                else -> """
                    Gender: Not Specified — provide well-rounded, balanced exercises.
                    Include a mix of: bodyweight cardio, core stability, and flexibility work.
                    Avoid anything extreme in intensity. Keep it safe and universally accessible.
                """.trimIndent()
            }

            // ─── Age-specific coaching context ───────────────────────────────
            val ageContext = when {
                age < 18 -> """
                    Age Group: Teen (under 18)
                    - AVOID heavy barbell lifts, max-effort powerlifting, and high-impact plyometrics on hard surfaces as growth plates are still developing.
                    - FOCUS on bodyweight fundamentals (push-ups, squats, lunges), flexibility, and coordination drills.
                    - Keep sessions fun and varied to build a healthy habit. Duration: 20–30 mins max.
                    - Intensity: Light to Moderate only.
                """.trimIndent()
                age in 18..25 -> """
                    Age Group: Young Adult (18–25)
                    - Peak muscle-building years — progressive overload and compound lifts are very effective.
                    - High intensity workouts (HIIT, strength training) are safe and beneficial.
                    - Recovery is fast; can train 5–6 days a week with proper nutrition.
                    - Emphasise learning correct form now to prevent future injuries.
                """.trimIndent()
                age in 26..35 -> """
                    Age Group: Prime Adult (26–35)
                    - Metabolism starts slowing slightly; combine strength training with cardio for best results.
                    - Focus on maintaining muscle mass while improving cardiovascular health.
                    - Warm-up and cool-down become more important. Add mobility work.
                    - 3–5 workout days per week is ideal.
                """.trimIndent()
                age in 36..50 -> """
                    Age Group: Mid-Adult (36–50)
                    - Muscle recovery takes longer; ensure at least 1 rest day between heavy sessions.
                    - PRIORITISE joint-friendly exercises: resistance bands, swimming, yoga, cycling.
                    - Add balance and core stability work to prevent age-related falls.
                    - Avoid exercises that put hard strain on knees/lower back without proper warm-up.
                    - Intensity: Moderate; avoid extended HIIT without medical clearance.
                """.trimIndent()
                else -> """
                    Age Group: Senior (51+)
                    - SAFETY FIRST: Low-impact exercises only — walking, water aerobics, chair yoga, resistance bands.
                    - Focus on bone density (weight-bearing), balance, and joint flexibility.
                    - AVOID heavy compound lifts, explosive plyometrics, and rapid direction changes.
                    - Sessions should be 15–25 minutes with plenty of rest between sets.
                    - Always recommend consulting a doctor before high-intensity activity.
                    - Intensity: Light only.
                """.trimIndent()
            }

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

            // Safe intensity based on age group
            val safeIntensity = when {
                isOnPeriod      -> "light"
                age < 18        -> "light"
                age in 18..35   -> if (mood.lowercase() in listOf("tired", "sad", "exhausted")) "light" else "moderate"
                age in 36..50   -> "moderate"
                else            -> "light"   // 51+
            }

            val periodConstraint = if (isOnPeriod) """
                CRITICAL: User is currently on their period.
                - STRICTLY FORBIDDEN: High intensity, jumping, heavy lifting, crunches, or inversions.
                - REQUIRED: Gentle stretching, yoga, walking, or light movements.
                - Focus on pain relief (cramps) and mood boosting.
            """.trimIndent() else ""

            val promptText = """
                ### ✅ Personalised Exercise Workout Generator

                You are a **Certified Fitness Coach** creating a safe, personalised workout.

                **User Profile:**
                - Age: $age | Gender: $gender | Weight: $weight kg
                - Goal: $goalType | Mood: $mood
                - Period Active: ${if (isOnPeriod) "YES – gentle mode only" else "No"}
                - Calories consumed today: $consumed / $targetCalories kcal
                - Time of day: $timeOfDay

                **Gender-Specific Coaching Guidelines:**
                $genderContext

                **Age-Specific Coaching Guidelines (Age $age):**
                $ageContext

                **Pre-filtered Exercise List (already matched to $gender, age group $ageGroupKey):**
                $simplifiedList

                ### Task:
                Choose exactly 3 exercises in this order: Warm-up → Main → Cool-down.
                ONLY use exercises from the list above.

                ### Strict Rules:
                ${if (isOnPeriod) "**⚠ PERIOD MODE ACTIVE**: ONLY yoga/stretching/walking. ABSOLUTELY NO jumping, heavy lifting, crunches, or inversions!" else "- Match intensity ($safeIntensity) to mood ($mood) and time of day ($timeOfDay)"}
                - CRITICAL GENDER RULE: You MUST follow the Gender Coaching Guidelines above. If the user is FEMALE, the 3 exercises MUST include at least 1 glute/lower-body exercise and 1 core/flexibility exercise. If the user is MALE, the 3 exercises MUST include at least 1 upper-body compound and 1 lower-body compound.
                - CRITICAL AGE RULE: Follow the Age-Specific Guidelines strictly. Do NOT suggest heavy lifts for seniors (51+) or for teens (under 18).
                - Each exercise's "reason" field MUST mention the user's gender ($gender) AND age ($age) AND goal ($goalType) specifically — not generic text.
                - Tips must be actionable form cues, not generic advice.
                - Mistakes must be ones a beginner of this gender and age group would actually make.

                ### Output JSON (exactly 3 exercises):
                [
                  {
                    "name": "Exercise name EXACTLY as it appears in the list above",
                    "targetMuscle": "Primary muscle targeted",
                    "bodyPart": "Body region (e.g. legs, core, upper body)",
                    "equipment": "Bodyweight / dumbbells / mat etc.",
                    "instructions": ["Step 1", "Step 2", "Step 3"],
                    "reason": "Why this exercise suits a $gender aged $age with $goalType goal right now",
                    "benefits": "Specific benefits for $gender body composition / physiology",
                    "ageExplanation": "Why this is ideal / needs modification at age $age",
                    "genderNote": "Gender-specific advantage or caution for $gender",
                    "motivationalMessage": "${if (isOnPeriod) "Gentle, supportive period-mode message" else "Short motivating line"}",
                    "estimatedCalories": ${if (isOnPeriod) 40 else 110},
                    "recommendedDuration": "15 mins",
                    "intensity": "${if (isOnPeriod) "light" else if (mood.lowercase() in listOf("tired", "sad", "exhausted")) "light" else "moderate"}",
                    "goalAlignment": "How this moves the user closer to $goalType",
                    "tips": ["Tip 1: specific cue", "Tip 2: breathing/form cue", "Tip 3: progression tip"],
                    "commonMistakes": ["Mistake 1: describe it", "Mistake 2: describe it"]
                  }
                ]
                
                Be precise, empathetic, and gender-aware in every field!
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
