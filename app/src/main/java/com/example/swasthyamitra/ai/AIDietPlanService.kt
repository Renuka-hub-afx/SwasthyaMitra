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
import kotlinx.coroutines.tasks.await
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class AIDietPlanService private constructor(private val context: Context) {

    private val authHelper = FirebaseAuthHelper(context)
    private val firestore = com.google.firebase.firestore.FirebaseFirestore.getInstance("renu")
    private val TAG = "AIDietPlanService"
    
    // Memory Cache for Food Samples
    private var foodCache: Map<String, List<String>>? = null
    private var veganDatasetCache: List<String>? = null
    
    // Comprehensive Dietary Exclusion Lists
    private val veganExclusions = listOf(
        // Meat & Poultry
        "Chicken", "Mutton", "Lamb", "Goat", "Beef", "Pork", "Meat", "Bacon", "Sausage",
        "Turkey", "Duck", "Quail",
        // Fish & Seafood
        "Fish", "Prawn", "Shrimp", "Crab", "Lobster", "Seafood", "Salmon", "Tuna",
        "Anchovy", "Sardine",
        // Eggs
        "Egg", "Omelette", "Scrambled", "Boiled Egg", "Anda", "Egg Curry",
        // Dairy Products
        "Milk", "Paneer", "Cheese", "Butter", "Ghee", "Cream", "Curd", "Yogurt", "Dahi",
        "Lassi", "Raita", "Kheer", "Kulfi", "Rabri", "Malai", "Makhani", "Shrikhand",
        "Buttermilk", "Whey", "Casein", "Lactose", "Condensed Milk",
        // Honey & Animal-Derived
        "Honey", "Gelatin", "Lard", "Fish Sauce", "Oyster Sauce", "Bone Broth"
    )
    
    private val vegetarianExclusions = listOf(
        // Meat & Poultry
        "Chicken", "Mutton", "Lamb", "Goat", "Beef", "Pork", "Meat", "Bacon", "Sausage",
        "Turkey", "Duck", "Quail",
        // Fish & Seafood
        "Fish", "Prawn", "Shrimp", "Crab", "Lobster", "Seafood", "Salmon", "Tuna",
        "Anchovy", "Sardine",
        // Eggs
        "Egg", "Omelette", "Scrambled", "Boiled Egg", "Anda", "Egg Curry",
        // Animal-Derived Ingredients
        "Gelatin", "Lard", "Fish Sauce", "Oyster Sauce", "Bone Broth", "Meat Broth"
    )

    companion object {
        @Volatile
        private var INSTANCE: AIDietPlanService? = null

        fun getInstance(context: Context): AIDietPlanService {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: AIDietPlanService(context.applicationContext).also { INSTANCE = it }
            }
        }
    }

    data class MealPlan(
        val breakfast: MealRec,
        val lunch: MealRec,
        val snack: MealRec,
        val dinner: MealRec,
        val postWorkout: MealRec? = null,
        val dailyTip: String = ""
    )

    data class MealRec(
        val item: String,
        val calories: Int,
        val protein: String,
        val reason: String,
        val tip: String = ""
    )

    /**
     * Core orchestration method to generate a personalized diet plan.
     */
    suspend fun generateSmartDietPlan(): Result<MealPlan> = withContext(Dispatchers.IO) {
        try {
            val user = authHelper.getCurrentUser() ?: return@withContext Result.failure(Exception("User not logged in"))
            val userId = user.uid

            // 1. Fetch Profile & Goals (Firestore)
            val profile = authHelper.getUserData(userId).getOrThrow()
            val goal = authHelper.getUserGoal(userId).getOrThrow()
            val isOnPeriod = profile["isOnPeriod"] as? Boolean ?: false

            val age = (profile["age"] as? Number)?.toInt() ?: 25
            val weight = (profile["weight"] as? Number)?.toDouble() ?: 70.0
            val height = (profile["height"] as? Number)?.toDouble() ?: 170.0
            val gender = profile["gender"] as? String ?: "Male"
            val dietaryPreference = profile["eatingPreference"] as? String ?: "Vegetarian"
            val allergies = (profile["allergies"] as? List<*>)?.joinToString(", ") ?: "None"

            // 2. Tier 1: Local Metabolic Math
            val bmr = calculateBMR(weight, height, age, gender)
            val activityLevel = goal["activityLevel"] as? String ?: "Sedentary"
            val tdee = calculateTDEE(bmr, activityLevel)
            val targetCalories = (goal["dailyCalories"] as? Number)?.toInt() ?: tdee.toInt()

            // 3. Dynamic Context (Logs)
            val exerciseLogs = authHelper.getRecentExerciseLogs(userId, 3)
            val weightLogs = authHelper.getRecentWeightLogs(userId, 14)
            val recentMeals = authHelper.getRecentFoodLogs(userId, 3)

            val intensityFlag = if (exerciseLogs.any { it["intensity"] == "High" || it["type"] == "HIIT" }) "INTENSITY_HIGH" else "INTENSITY_NORMAL"
            val plateauFlag = detectPlateau(weightLogs)
            val pastMealsList = recentMeals.take(10).joinToString { it.foodName }

            // 4. Get user preferences (disliked foods)
            val dislikedFoods = getUserPreferences(userId)

            // 5. Grounding Data (Vegan Dataset or CSV Sample)
            val foodSample = if (dietaryPreference == "Vegan") {
                loadVeganDataset(weight, height, allergies, 50)
            } else {
                loadFoodSampleFromCsv(dietaryPreference, 50)
            }

            // 6. Festival Check
            val festivalNote = getFestivalInstruction()

            // 7. Gemini Prompt Construction
            val promptText = buildPrompt(
                age, gender, weight, height, targetCalories, dietaryPreference, allergies,
                activityLevel, intensityFlag, plateauFlag, pastMealsList, dislikedFoods,
                foodSample, festivalNote, isOnPeriod
            )

            // 8. Execute Vertex AI (Gemini 2.0 Flash)
            val plan = callGeminiAPI(promptText)
            
            // 8.5. Validate Dietary Compliance
            validateDietaryCompliance(plan, dietaryPreference).getOrElse {
                Log.e(TAG, "Dietary compliance validation failed: ${it.message}")
                throw it
            }
            
            // 9. Save plan to Firestore for history
            savePlanToFirestore(userId, plan)

            Result.success(plan)

        } catch (e: Exception) {
            Log.e(TAG, "Error generating diet plan: ${e.message}", e)
            Result.failure(e)
        }
    }

    /**
     * Regenerate a single meal with exclusions
     */
    suspend fun regenerateMeal(
        mealType: String,
        excludedItems: List<String> = emptyList()
    ): Result<MealRec> = withContext(Dispatchers.IO) {
        try {
            val user = authHelper.getCurrentUser() ?: return@withContext Result.failure(Exception("User not logged in"))
            val userId = user.uid

            val profile = authHelper.getUserData(userId).getOrThrow()
            val goal = authHelper.getUserGoal(userId).getOrThrow()

            val dietaryPreference = profile["eatingPreference"] as? String ?: "Vegetarian"
            val targetCalories = (goal["dailyCalories"] as? Number)?.toInt() ?: 2000
            val isOnPeriod = profile["isOnPeriod"] as? Boolean ?: false
            val mealCalories = when(mealType) {
                "Breakfast" -> (targetCalories * 0.25).toInt()
                "Lunch" -> (targetCalories * 0.35).toInt()
                "Dinner" -> (targetCalories * 0.30).toInt()
                "Snack" -> (targetCalories * 0.10).toInt()
                else -> 300
            }

            val weight = (profile["weight"] as? Number)?.toDouble() ?: 70.0
            val height = (profile["height"] as? Number)?.toDouble() ?: 170.0
            val allergies = (profile["allergies"] as? List<*>)?.joinToString(", ") ?: "None"
            
            val foodSample = if (dietaryPreference == "Vegan") {
                loadVeganDataset(weight, height, allergies, 30)
            } else {
                loadFoodSampleFromCsv(dietaryPreference, 30)
            }
            val exclusionList = excludedItems.joinToString(", ")

            val festivalNote = getFestivalInstruction()
            val season = getSeason()
            val periodContext = if (isOnPeriod) {
                "STRICT: The user is on her period. Focus on iron-rich, warm, and comforting foods. Avoid heavy, spicy, or fried items if possible."
            } else ""

            val dietaryRules = getDietaryRules(dietaryPreference)
            
            val promptText = """
                You are SwasthyaMitra, an expert Indian Nutritionist.
                
                Generate a SINGLE $mealType suggestion.
                Target Calories: $mealCalories kcal
                Dietary Preference: $dietaryPreference
                
                $dietaryRules
                
                **CONTEXT:**
                - Season: $season
                - Period Context: $periodContext
                - Festival Context: $festivalNote
                - Health Focus: Hydration (water intake) and $mealType timing.
                
                EXCLUDE these items: $exclusionList
                
                Available Foods:
                $foodSample
                
                **TASK:**
                Provide a healthy suggestion and a "Pro Tip" specifically for this $mealType.
                ${if (isOnPeriod) "The suggestion must be supportive of menstrual comfort (e.g., warm dal, iron-rich greens, ginger tea)." else "The tip must be seasonal, festival-aware, or related to hydration and health."}
                
                Return ONLY JSON:
                {
                  "item": "...",
                  "calories": 0,
                  "protein": "...",
                  "reason": "...",
                  "tip": "..." 
                }
            """.trimIndent()

            val config = generationConfig {
                temperature = 0.5f
                responseMimeType = "application/json"
            }
            val generativeModel = Firebase.ai(backend = GenerativeBackend.googleAI())
                .generativeModel("gemini-2.0-flash", generationConfig = config)
            
            // Retry mechanism for meal regeneration
            var lastException: Exception? = null
            for (attempt in 1..2) {
                try {
                    Log.d(TAG, "Regenerate meal attempt $attempt for $mealType")
                    
                    val response = kotlinx.coroutines.withTimeout(30000) {
                        generativeModel.generateContent(promptText)
                    }
                    
                    val jsonStr = response.text
                    if (jsonStr.isNullOrBlank()) {
                        Log.e(TAG, "Attempt $attempt: Empty response")
                        lastException = Exception("AI response empty")
                        kotlinx.coroutines.delay(1000L * attempt)
                        continue
                    }
                    
                    val cleanJson = jsonStr.trim()
                        .removeSurrounding("```json", "```")
                        .removeSurrounding("```", "```")
                        .trim()
                    
                    // Handle both array and object responses
                    val meal = if (cleanJson.startsWith("[")) {
                        val jsonArray = org.json.JSONArray(cleanJson)
                        parseMeal(jsonArray.getJSONObject(0))
                    } else {
                        parseMeal(JSONObject(cleanJson))
                    }
                    
                    // Track regeneration
                    trackFeedback(userId, excludedItems.lastOrNull() ?: "", mealType, "Regenerated")
                    
                    return@withContext Result.success(meal)
                    
                } catch (e: kotlinx.coroutines.TimeoutCancellationException) {
                    Log.e(TAG, "Attempt $attempt: Timeout for $mealType")
                    lastException = Exception("AI took too long. Please try again.")
                    kotlinx.coroutines.delay(1500L * attempt)
                } catch (e: Exception) {
                    Log.e(TAG, "Attempt $attempt failed for $mealType: ${e.message}", e)
                    lastException = e
                    if (attempt < 2) {
                        kotlinx.coroutines.delay(1000L * attempt)
                    }
                }
            }
            
            // Return fallback meal after retries
            Log.w(TAG, "Using fallback for $mealType after failed attempts")
            Result.success(getFallbackMeal(mealType, mealCalories))
            
        } catch (e: Exception) {
            Log.e(TAG, "Error regenerating meal: ${e.message}", e)
            Result.failure(e)
        }
    }
    
    /**
     * Fallback meal for a specific meal type
     */
    private fun getFallbackMeal(mealType: String, targetCalories: Int): MealRec {
        return when (mealType.lowercase()) {
            "breakfast" -> MealRec(
                item = "Idli Sambar",
                calories = targetCalories,
                protein = "10g",
                reason = "Healthy South Indian breakfast, easy to digest.",
                tip = "Add coconut chutney for taste!"
            )
            "lunch" -> MealRec(
                item = "Vegetable Biryani with Raita",
                calories = targetCalories,
                protein = "15g",
                reason = "Flavorful and filling lunch option.",
                tip = "Balance with fresh salad."
            )
            "dinner" -> MealRec(
                item = "Chapati with Mixed Vegetable Curry",
                calories = targetCalories,
                protein = "12g",
                reason = "Light dinner for good digestion.",
                tip = "Eat at least 2 hours before sleep."
            )
            else -> MealRec(
                item = "Roasted Makhana (Fox Nuts)",
                calories = targetCalories,
                protein = "5g",
                reason = "Healthy, low-calorie snack option.",
                tip = "Great for evening cravings!"
            )
        }
    }
    /**
     * Track user feedback (Ate/Skipped/Regenerated)
     */
    suspend fun trackFeedback(
        userId: String,
        mealName: String,
        mealType: String,
        action: String,
        reason: String? = null
    ) = withContext(Dispatchers.IO) {
        try {
            val dateFormat = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
            val feedback = hashMapOf(
                "userId" to userId,
                "mealName" to mealName,
                "mealType" to mealType,
                "action" to action,
                "timestamp" to System.currentTimeMillis(),
                "date" to dateFormat.format(java.util.Date())
            )
            if (reason != null) feedback["reason"] = reason

            firestore.collection("meal_feedback").add(feedback).await()
            
            // Update user preferences if skipped
            if (action == "Skipped") {
                updateUserPreferences(userId, mealName, isDisliked = true)
            } else if (action == "Ate") {
                updateUserPreferences(userId, mealName, isDisliked = false)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error tracking feedback: ${e.message}", e)
        }
    }

    private suspend fun getUserPreferences(userId: String): String {
        return try {
            val doc = firestore.collection("user_preferences").document(userId).get().await()
            val disliked = doc.get("dislikedFoods") as? List<*> ?: emptyList<String>()
            disliked.joinToString(", ")
        } catch (e: Exception) {
            ""
        }
    }

    private suspend fun updateUserPreferences(userId: String, mealName: String, isDisliked: Boolean) {
        try {
            val docRef = firestore.collection("user_preferences").document(userId)
            val doc = docRef.get().await()
            
            if (!doc.exists()) {
                docRef.set(hashMapOf(
                    "dislikedFoods" to if (isDisliked) listOf(mealName) else emptyList<String>(),
                    "favoriteFoods" to if (!isDisliked) listOf(mealName) else emptyList<String>(),
                    "lastUpdated" to System.currentTimeMillis()
                )).await()
            } else {
                val disliked = (doc.get("dislikedFoods") as? List<*>)?.toMutableList() ?: mutableListOf()
                val favorites = (doc.get("favoriteFoods") as? List<*>)?.toMutableList() ?: mutableListOf()
                
                if (isDisliked && !disliked.contains(mealName)) {
                    disliked.add(mealName)
                    favorites.remove(mealName)
                } else if (!isDisliked && !favorites.contains(mealName)) {
                    favorites.add(mealName)
                    disliked.remove(mealName)
                }
                
                docRef.update(mapOf(
                    "dislikedFoods" to disliked,
                    "favoriteFoods" to favorites,
                    "lastUpdated" to System.currentTimeMillis()
                )).await()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error updating preferences: ${e.message}", e)
        }
    }

    private suspend fun savePlanToFirestore(userId: String, plan: MealPlan) {
        try {
            val dateFormat = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
            val planData = hashMapOf(
                "userId" to userId,
                "generatedAt" to System.currentTimeMillis(),
                "date" to dateFormat.format(java.util.Date()),
                "breakfast" to mapOf("item" to plan.breakfast.item, "calories" to plan.breakfast.calories, "protein" to plan.breakfast.protein, "reason" to plan.breakfast.reason),
                "lunch" to mapOf("item" to plan.lunch.item, "calories" to plan.lunch.calories, "protein" to plan.lunch.protein, "reason" to plan.lunch.reason),
                "snack" to mapOf("item" to plan.snack.item, "calories" to plan.snack.calories, "protein" to plan.snack.protein, "reason" to plan.snack.reason),
                "dinner" to mapOf("item" to plan.dinner.item, "calories" to plan.dinner.calories, "protein" to plan.dinner.protein, "reason" to plan.dinner.reason),
                "dailyTip" to plan.dailyTip,
                "totalCalories" to (plan.breakfast.calories + plan.lunch.calories + plan.snack.calories + plan.dinner.calories),
                "status" to "Active"
            )
            
            if (plan.postWorkout != null) {
                planData["postWorkout"] = mapOf("item" to plan.postWorkout.item, "calories" to plan.postWorkout.calories, "protein" to plan.postWorkout.protein, "reason" to plan.postWorkout.reason)
            }

            firestore.collection("ai_generated_plans").add(planData).await()
        } catch (e: Exception) {
            Log.e(TAG, "Error saving plan: ${e.message}", e)
        }
    }

    private fun buildPrompt(
        age: Int, gender: String, weight: Double, height: Double, targetCalories: Int,
        dietaryPreference: String, allergies: String, activityLevel: String,
        intensityFlag: String, plateauFlag: String, pastMealsList: String,
        dislikedFoods: String, foodSample: String, festivalNote: String,
        isOnPeriod: Boolean = false
    ): String {
        val dietaryRules = getDietaryRules(dietaryPreference)
        
        return """
            You are SwasthyaMitra, an expert Indian Nutritionist.
            
            **USER PROFILE:**
            - Stats: $age years, $gender, $weight kg, $height cm.
            - Target Daily Calories: $targetCalories kcal.
            - Preference: $dietaryPreference.
            - Allergies/Restrictions: $allergies.
            
            $dietaryRules
            
            **DYNAMIC STATUS:**
            - Activity Level: $activityLevel ($intensityFlag).
            - Weight Trend: $plateauFlag.
            - Anti-Repetition (Avoid these recently eaten foods): $pastMealsList.
            - Disliked Foods (NEVER suggest these): $dislikedFoods.
            
            **GROUNDING DATA (Use these items or similar Indian dishes):**
            $foodSample
            
            $festivalNote
            
            **TASK:**
            Generate a personalized daily meal plan in STRICT JSON format.
            Rules:
            ${if (isOnPeriod) """
            1. PERIOD MODE: Focus on iron-rich foods (Spinach, Beets, Dal, Pomegranate) and warm meals.
            2. COMFORT: Suggest easy-to-digest items. Mention ginger tea or dark chocolate in the daily tip.
            3. NO PRESSURE: Avoid suggesting heavy workouts; focus on recovery nutrition.
            """ else """
            1. If INTENSITY_HIGH, you MUST include a "postWorkout" meal rich in protein.
            2. If PLATEAU_DETECTED, suggest a slightly higher protein and fiber mix to boost metabolism.
            """}
            3. Ensure total calories are within +/- 100 of $targetCalories.
            4. Focus on authentic Indian dishes.
            5. NEVER suggest items from the disliked foods list.
            
            **STRICT OUTPUT FORMAT (JSON ONLY):**
            {
              "breakfast": { "item": "...", "calories": 0, "protein": "...", "reason": "..." },
              "lunch": { "item": "...", "calories": 0, "protein": "...", "reason": "..." },
              "snack": { "item": "...", "calories": 0, "protein": "...", "reason": "..." },
              "dinner": { "item": "...", "calories": 0, "protein": "...", "reason": "..." },
              "postWorkout": { "item": "...", "calories": 0, "protein": "...", "reason": "..." },
              "dailyTip": "..."
            }
        """.trimIndent()
    }
    
    private fun getDietaryRules(dietaryPreference: String): String {
        return when (dietaryPreference) {
            "Vegan" -> """
                **CRITICAL DIETARY RULES - STRICT VEGAN:**
                - NEVER suggest ANY animal products whatsoever
                - FORBIDDEN: All meat, fish, eggs, dairy (milk, paneer, ghee, butter, curd, cheese, yogurt, lassi, raita, kheer, kulfi, malai, cream), honey
                - ALLOWED ONLY: 100% plant-based foods (vegetables, fruits, grains, legumes, nuts, seeds)
                - PROTEIN SOURCES: Dal, chickpeas, tofu, soy chunks, quinoa, nuts, seeds, lentils
                - DAIRY ALTERNATIVES: Almond milk, coconut milk, soy milk, cashew cream
                - DOUBLE-CHECK: Every single suggested item must be completely plant-based
                - VERIFY: No hidden dairy in curries, desserts, or snacks
            """.trimIndent()
            "Vegetarian" -> """
                **CRITICAL DIETARY RULES - STRICT VEGETARIAN:**
                - NEVER suggest meat, fish, or eggs
                - FORBIDDEN: Chicken, mutton, fish, seafood, eggs, gelatin, meat broths, fish sauce
                - ALLOWED: Dairy products (milk, paneer, ghee, curd, yogurt), vegetables, fruits, grains, legumes
                - PROTEIN SOURCES: Dal, paneer, milk, curd, chickpeas, nuts, soy
                - VERIFY: No hidden meat/fish ingredients (e.g., fish sauce in Indo-Chinese dishes)
                - CHECK: No gelatin in desserts, no meat-based broths
            """.trimIndent()
            "Eggetarian" -> """
                **CRITICAL DIETARY RULES - EGGETARIAN:**
                - NEVER suggest meat, fish, or seafood
                - FORBIDDEN: Chicken, mutton, fish, seafood, meat broths
                - ALLOWED: Eggs, dairy products, vegetables, fruits, grains, legumes
                - PROTEIN SOURCES: Eggs, dal, paneer, milk, curd, chickpeas, nuts
            """.trimIndent()
            else -> """
                **DIETARY RULES - NON-VEGETARIAN:**
                - All food types allowed
                - BALANCE: Include both vegetarian and non-vegetarian options for variety
                - PROTEIN SOURCES: Chicken, fish, eggs, dal, paneer, legumes
            """.trimIndent()
        }
    }

    private suspend fun callGeminiAPI(promptText: String): MealPlan {
        val config = generationConfig {
            temperature = 0.4f
            responseMimeType = "application/json"
        }
        val generativeModel = Firebase.ai(backend = GenerativeBackend.googleAI())
            .generativeModel("gemini-2.0-flash", generationConfig = config)

        // Retry mechanism with backoff
        var lastException: Exception? = null
        for (attempt in 1..3) {
            try {
                Log.d(TAG, "Gemini API attempt $attempt for diet plan")
                
                val response = kotlinx.coroutines.withTimeout(45000) { // 45s timeout
                    generativeModel.generateContent(promptText)
                }
                
                val jsonStr = response.text
                if (jsonStr.isNullOrBlank()) {
                    Log.e(TAG, "Attempt $attempt: Empty response from AI")
                    lastException = Exception("AI response empty")
                    kotlinx.coroutines.delay(1000L * attempt) // Backoff delay
                    continue
                }
                
                val cleanJson = jsonStr.trim()
                    .removeSurrounding("```json", "```")
                    .removeSurrounding("```", "```")
                    .trim()
                
                Log.d(TAG, "Attempt $attempt: Got response, parsing JSON")
                
                val json = JSONObject(cleanJson)
                
                return MealPlan(
                    parseMeal(json.getJSONObject("breakfast")),
                    parseMeal(json.getJSONObject("lunch")),
                    parseMeal(json.getJSONObject("snack")),
                    parseMeal(json.getJSONObject("dinner")),
                    if (json.has("postWorkout")) parseMeal(json.getJSONObject("postWorkout")) else null,
                    json.optString("dailyTip", "Health is wealth!")
                )
                
            } catch (e: kotlinx.coroutines.TimeoutCancellationException) {
                Log.e(TAG, "Attempt $attempt: Timeout")
                lastException = Exception("AI is taking longer than usual. Please try again.")
                kotlinx.coroutines.delay(2000L * attempt)
            } catch (e: Exception) {
                Log.e(TAG, "Attempt $attempt failed: ${e.message}", e)
                lastException = e
                if (attempt < 3) {
                    kotlinx.coroutines.delay(1500L * attempt)
                }
            }
        }
        
        // All retries failed - return fallback meal plan
        Log.w(TAG, "All API attempts failed, using fallback meal plan")
        return getFallbackMealPlan()
    }
    
    /**
     * Fallback meal plan when AI is unavailable
     */
    private fun getFallbackMealPlan(): MealPlan {
        return MealPlan(
            breakfast = MealRec(
                item = "Poha with Vegetables",
                calories = 350,
                protein = "8g",
                reason = "Light, nutritious Indian breakfast to start your day right.",
                tip = "Add peanuts for extra protein!"
            ),
            lunch = MealRec(
                item = "Dal Rice with Vegetables",
                calories = 550,
                protein = "18g",
                reason = "Balanced meal with protein from dal and energy from rice.",
                tip = "Include a salad for extra fiber."
            ),
            snack = MealRec(
                item = "Fruit Bowl with Nuts",
                calories = 200,
                protein = "5g",
                reason = "Healthy snack to keep energy levels up.",
                tip = "Almonds and walnuts are great options!"
            ),
            dinner = MealRec(
                item = "Roti with Paneer Sabzi",
                calories = 450,
                protein = "20g",
                reason = "Protein-rich dinner for muscle recovery.",
                tip = "Have dinner 2-3 hours before sleep."
            ),
            dailyTip = "Stay hydrated and eat mindfully! 💧"
        )
    }

    private fun calculateBMR(weight: Double, height: Double, age: Int, gender: String): Double {
        val baseBMR = (10 * weight) + (6.25 * height) - (5 * age)
        return if (gender.equals("Male", ignoreCase = true)) baseBMR + 5 else baseBMR - 161
    }

    private fun calculateTDEE(bmr: Double, activityLevel: String): Double {
        val factor = when (activityLevel) {
            "Sedentary" -> 1.2
            "Lightly Active" -> 1.375
            "Moderately Active" -> 1.55
            "Very Active" -> 1.725
            else -> 1.2
        }
        return bmr * factor
    }

    private fun detectPlateau(logs: List<Map<String, Any>>): String {
        if (logs.size < 2) return "TREND_STABLE"
        val weights = logs.mapNotNull { (it["weight"] as? Number)?.toDouble() }
        if (weights.isEmpty()) return "TREND_STABLE"
        
        val max = weights.maxOrNull() ?: 0.0
        val min = weights.minOrNull() ?: 0.0
        
        return if (max - min < 0.2) "PLATEAU_DETECTED" else "TREND_NORMAL"
    }

    private fun loadFoodSampleFromCsv(preference: String, limit: Int): String {
        if (foodCache == null) {
            preloadFoodCache()
        }
        
        val allSamples = mutableListOf<String>()
        val files = listOf("food_data.csv", "foods (1).csv", "Indian_Food_DF (2).csv")
        
        files.forEach { fileName ->
            val cached = foodCache?.get(fileName) ?: emptyList()
            val filtered = cached.filter { line ->
                when (preference) {
                    "Vegan" -> {
                        // Use comprehensive vegan exclusion list
                        veganExclusions.none { exclusion ->
                            line.contains(exclusion, ignoreCase = true)
                        }
                    }
                    "Vegetarian" -> {
                        // Use comprehensive vegetarian exclusion list
                        vegetarianExclusions.none { exclusion ->
                            line.contains(exclusion, ignoreCase = true)
                        }
                    }
                    "Eggetarian" -> {
                        // Vegetarian + eggs allowed
                        val eggetarianExclusions = vegetarianExclusions.filter { it != "Egg" && it != "Omelette" && it != "Scrambled" && it != "Boiled Egg" && it != "Anda" && it != "Egg Curry" }
                        eggetarianExclusions.none { exclusion ->
                            line.contains(exclusion, ignoreCase = true)
                        }
                    }
                    else -> true // Non-Vegetarian - all foods allowed
                }
            }
            val samplesPerFile = (limit / files.size).coerceAtLeast(15)
            allSamples.addAll(filtered.shuffled().take(samplesPerFile))
        }

        return if (allSamples.isEmpty()) {
            "Garam Chai, Poha, Dal Tadka, Roti, Sabzi"
        } else {
            allSamples.shuffled().take(limit).joinToString("\n")
        }
    }
    
    private fun loadVeganDataset(weight: Double, height: Double, allergies: String, limit: Int): String {
        // Check cache first
        if (veganDatasetCache != null && veganDatasetCache!!.isNotEmpty()) {
            return veganDatasetCache!!.shuffled().take(limit).joinToString("\n")
        }
        
        try {
            // Calculate BMI to determine weight status
            val bmi = weight / ((height / 100) * (height / 100))
            val isObese = bmi >= 30
            
            // Select appropriate file based on weight and allergies
            val fileName = when {
                allergies.contains("gluten", ignoreCase = true) -> 
                    if (isObese) "Obese_vegans_gluten_allergy .xlsx" 
                    else "Normal_weight_vegans_gluten_allergy .xlsx"
                allergies.contains("nut", ignoreCase = true) || allergies.contains("peanut", ignoreCase = true) -> 
                    if (isObese) "Obese_vegans_nut_allergy .xlsx" 
                    else "Normal_weight_vegans_nut_allergy .xlsx"
                else -> 
                    if (isObese) "Obese_vegans_no_allergy .xlsx" 
                    else "Normal_weight_vegans_no_allergy .xlsx"
            }
            
            val veganFoods = parseVeganExcel(fileName)
            
            if (veganFoods.isNotEmpty()) {
                veganDatasetCache = veganFoods
                return veganFoods.shuffled().take(limit).joinToString("\n")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error loading vegan dataset: ${e.message}", e)
        }
        
        // Fallback to CSV filtering if vegan dataset fails
        Log.w(TAG, "Falling back to CSV filtering for vegan foods")
        return loadFoodSampleFromCsv("Vegan", limit)
    }
    
    private fun parseVeganExcel(fileName: String): List<String> {
        val foods = mutableListOf<String>()
        
        try {
            val inputStream = context.assets.open("vegan/Vegan_datasets/$fileName")
            val workbook = org.apache.poi.xssf.usermodel.XSSFWorkbook(inputStream)
            val sheet = workbook.getSheetAt(0)
            
            // Get header row to find column indices
            val headerRow = sheet.getRow(0)
            var foodNameIdx = -1
            var descriptionIdx = -1
            
            for (i in 0 until headerRow.lastCellNum) {
                val cellValue = headerRow.getCell(i)?.toString() ?: ""
                when {
                    cellValue.contains("Foods", ignoreCase = true) -> foodNameIdx = i
                    cellValue.contains("Description", ignoreCase = true) -> descriptionIdx = i
                }
            }
            
            if (foodNameIdx == -1) {
                Log.e(TAG, "Could not find 'Foods' column in $fileName")
                return emptyList()
            }
            
            // Parse data rows
            for (rowIdx in 1..sheet.lastRowNum) {
                val row = sheet.getRow(rowIdx) ?: continue
                val foodCell = row.getCell(foodNameIdx)
                val foodName = foodCell?.toString()?.trim() ?: ""
                
                if (foodName.isNotEmpty() && foodName != "null") {
                    val description = if (descriptionIdx != -1) {
                        row.getCell(descriptionIdx)?.toString()?.trim() ?: ""
                    } else ""
                    
                    val formattedFood = if (description.isNotEmpty()) {
                        "$foodName ($description)"
                    } else {
                        foodName
                    }
                    
                    foods.add(formattedFood)
                }
            }
            
            workbook.close()
            inputStream.close()
            
            Log.d(TAG, "Loaded ${foods.size} vegan foods from $fileName")
            
        } catch (e: Exception) {
            Log.e(TAG, "Error parsing vegan Excel file $fileName: ${e.message}", e)
        }
        
        return foods
    }
    
    private fun validateDietaryCompliance(mealPlan: MealPlan, dietaryPreference: String): Result<MealPlan> {
        val allItems = listOf(
            mealPlan.breakfast.item,
            mealPlan.lunch.item,
            mealPlan.snack.item,
            mealPlan.dinner.item,
            mealPlan.postWorkout?.item
        ).filterNotNull()
        
        val violations = when (dietaryPreference) {
            "Vegan" -> {
                allItems.filter { item ->
                    veganExclusions.any { exclusion ->
                        item.contains(exclusion, ignoreCase = true)
                    }
                }
            }
            "Vegetarian" -> {
                allItems.filter { item ->
                    vegetarianExclusions.any { exclusion ->
                        item.contains(exclusion, ignoreCase = true)
                    }
                }
            }
            else -> emptyList()
        }
        
        return if (violations.isNotEmpty()) {
            Log.e(TAG, "❌ DIETARY VIOLATION DETECTED for $dietaryPreference user: $violations")
            Result.failure(Exception("AI suggested non-compliant food: ${violations.joinToString()}. This violates $dietaryPreference dietary restrictions. Please regenerate."))
        } else {
            Log.d(TAG, "✅ Dietary compliance validated for $dietaryPreference user")
            Result.success(mealPlan)
        }
    }

    private fun preloadFoodCache() {
        val cache = mutableMapOf<String, List<String>>()
        val files = listOf("food_data.csv", "foods (1).csv", "Indian_Food_DF (2).csv")
        
        files.forEach { fileName ->
            try {
                context.assets.open(fileName).use { input ->
                    val reader = BufferedReader(InputStreamReader(input))
                    val header = reader.readLine() ?: return@forEach
                    val columns = parseCsvLine(header)
                    
                    val nameIdx = columns.indexOfFirst { it.contains("Name", true) || it.contains("Items", true) || it.equals("name", true) }
                    val calIdx = columns.indexOfFirst { it.contains("Calories", true) || it.contains("Energy", true) || it.contains("kcal", true) }
                    val proteinIdx = columns.indexOfFirst { it.contains("Protein", true) || it.contains("nutri_protein", true) }

                    if (nameIdx != -1) {
                        val foods = reader.lineSequence()
                            .mapNotNull { line ->
                                val parts = parseCsvLine(line)
                                if (parts.size > nameIdx) {
                                    val rawName = parts[nameIdx].trim().removeSurrounding("\"")
                                    if (rawName.isEmpty()) return@mapNotNull null
                                    
                                    val rawCal = if (calIdx != -1 && parts.size > calIdx) parts[calIdx] else ""
                                    val rawProtein = if (proteinIdx != -1 && parts.size > proteinIdx) parts[proteinIdx] else ""
                                    
                                    val cals = normalizeValue(rawCal, "kcal")
                                    val protein = normalizeValue(rawProtein, "g")
                                    
                                    "$rawName ($cals kcal, $protein protein)"
                                } else null
                            }
                            .toList()
                        cache[fileName] = foods
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error preloading $fileName: ${e.message}")
            }
        }
        foodCache = cache
    }

    private fun parseCsvLine(line: String): List<String> {
        val result = mutableListOf<String>()
        val sb = StringBuilder()
        var inQuotes = false
        for (c in line) {
            when {
                c == '\"' -> inQuotes = !inQuotes
                c == ',' && !inQuotes -> {
                    result.add(sb.toString())
                    sb.setLength(0)
                }
                else -> sb.append(c)
            }
        }
        result.add(sb.toString())
        return result
    }

    private fun normalizeValue(raw: String, unit: String): String {
        var clean = raw.trim().removeSurrounding("\"")
        if (clean.isEmpty()) return "N/A"
        
        // Remove thousand separator commas (e.g., "1,200" -> "1200")
        clean = clean.replace(",", "")
        
        if (unit == "kcal") {
            // Priority: Extract specific kcal unit if present (e.g., "1000 kj (240 kcal)" -> "240")
            val kcalMatch = Regex("""(\d+(\.\d+)?)\s*kcal""").find(clean)
            if (kcalMatch != null) return kcalMatch.groupValues[1]
        }
        
        // Fallback: Just find the first numeric value
        val digitMatch = Regex("""(\d+(\.\d+)?)""").find(clean)
        return if (digitMatch != null) {
            digitMatch.groupValues[1]
        } else {
            "N/A"
        }
    }

    private fun getFestivalInstruction(): String {
        val calendar = Calendar.getInstance()
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        
        return when {
            month == 1 && day == 14 -> "It is Makar Sankranti/Pongal/Lohri. Suggest something with sesame or jaggery."
            month == 1 && day == 26 -> "It is Republic Day. Suggest one tri-color themed healthy item or mention patriotic health enthusiasm."
            month == 3 && day in 10..25 -> "It's around Holi season. Mention cooling drinks like Thandai (healthy version)."
            month == 8 && day == 15 -> "It is Independence Day. Suggest a festive but healthy snack."
            month == 10 && day == 2 -> "It is Gandhi Jayanti. Focus on simple, sattvic meal suggestions."
            month == 10 || month == 11 -> "It's the festive season of Diwali/Navratri. Suggest healthy alternatives to traditional sweets."
            else -> "No specific festival today, but focus on general Indian seasonal wellness."
        }
    }

    private fun getSeason(): String {
        val month = Calendar.getInstance().get(Calendar.MONTH) + 1
        return when (month) {
            in 3..6 -> "Summer (Focus on hydration, cooling foods like curd/buttermilk)"
            in 7..9 -> "Monsoon (Focus on immunity, cooked warm foods, avoid raw leafy greens outside)"
            in 10..11 -> "Autumn (Balanced nutrition, seasonal fruits like guava/pear)"
            else -> "Winter (Focus on warming foods, healthy fats, root vegetables)"
        }
    }

    private fun parseMeal(json: JSONObject) = MealRec(
        json.getString("item"),
        json.getInt("calories"),
        json.getString("protein"),
        json.getString("reason"),
        json.optString("tip", "Stay hydrated and eat fresh!")
    )
}