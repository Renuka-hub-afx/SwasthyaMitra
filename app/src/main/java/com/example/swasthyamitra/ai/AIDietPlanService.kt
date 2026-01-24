package com.example.swasthyamitra.ai

import android.content.Context
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.vertexai.vertexAI
import com.google.firebase.vertexai.type.content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

class AIDietPlanService(private val context: Context) {

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private val TAG = "AI_DIET_SERVICE"

    data class DietPlan(
        val breakfast: MealRec, val lunch: MealRec,
        val dinner: MealRec, val snacks: MealRec,
        val dailyTip: String
    )

    data class MealRec(val name: String, val calories: Int, val protein: Int)

    suspend fun generateDietPlan(): DietPlan = withContext(Dispatchers.IO) {
        Log.d(TAG, "Starting diet plan generation")
        val userId = auth.currentUser?.uid ?: throw Exception("User not logged in")

        // 1. Fetch User Data
        Log.d(TAG, "Fetching user profile and goals for userId: $userId")
        val userDoc = db.collection("users").document(userId).get().await()
        val userProfile = userDoc.data ?: mapOf()
        val goalsQuery = db.collection("goals").whereEqualTo("userId", userId).get().await()
        val goalData = if (!goalsQuery.isEmpty) goalsQuery.documents[0].data else mapOf()

        // 2. Load CSV Food Data
        var foodContext = ""
        try {
            Log.d(TAG, "Loading food_data.csv from assets")
            context.assets.open("food_data.csv").use { inputStream ->
                val reader = BufferedReader(InputStreamReader(inputStream))
                
                // Parsing CSV manually with quote support
                val foods = reader.lineSequence()
                    .drop(1) // Skip header
                    .filter { it.isNotBlank() }
                    .take(1000) // Increase sample size
                    .mapNotNull { line ->
                        parseCsvLine(line)
                    }
                    .toList()
                
                if (foods.isNotEmpty()) {
                    foodContext = "Use these preferred Indian foods if possible (Name | kcal | Protein):\n" + 
                        foods.shuffled().take(60).joinToString("\n") { (name, cal, protein) ->
                            "- $name ($cal kcal, ${protein}g protein)"
                        }
                    Log.d(TAG, "Loaded ${foods.size} foods for context")
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error loading CSV: ${e.message}")
        }

        // 3. Build Prompt
        val promptText = """
            Act as an expert Indian Nutritionist. Create a personalized diet plan for today based on:
            
            User Profile:
            - Age: ${userProfile["age"]}
            - Weight: ${userProfile["weight"]} kg
            - Goal: ${goalData?.get("goalType") ?: "Maintain Health"}
            - Dietary Preference: ${userProfile["preference"] ?: "Any"}
            
            $foodContext
            
            Return the plan strictly as a JSON object with these fields:
            {
              "breakfast": { "name": "...", "calories": 0, "protein": 0 },
              "lunch": { "name": "...", "calories": 0, "protein": 0 },
              "dinner": { "name": "...", "calories": 0, "protein": 0 },
              "snacks": { "name": "...", "calories": 0, "protein": 0 },
              "dailyTip": "..."
            }
            Important:
            1. Suggest real dishes from the list provided if appropriate.
            2. Match the calories to the user's goal.
            3. Do not include any extra text, only the JSON block.
        """.trimIndent()

        Log.d(TAG, "Constructed Prompt:\n$promptText")

        // 4. Call Vertex AI
        val generativeModel = Firebase.vertexAI.generativeModel("gemini-2.5-flash")

        Log.d(TAG, "Calling Gemini API...")
        val response = generativeModel.generateContent(
            content {
                text(promptText)
            }
        )
        
        val responseText = response.text ?: ""
        Log.d(TAG, "Received Response:\n$responseText")
        
        val cleanJson = responseText.trim().removeSurrounding("```json", "```").trim()

        // 5. Parse and Return
        return@withContext try {
            val json = JSONObject(cleanJson)
            DietPlan(
                parseMeal(json.optJSONObject("breakfast")),
                parseMeal(json.optJSONObject("lunch")),
                parseMeal(json.optJSONObject("dinner")),
                parseMeal(json.optJSONObject("snacks")),
                json.optString("dailyTip", "Stay focused on your health goals!")
            )
        } catch (e: Exception) {
            Log.e(TAG, "Failed to parse AI response: ${e.message}")
            throw Exception("AI response parsing error. Raw response: $responseText", e)
        }
    }

    /**
     * Simple CSV parser that handles quoted strings and extraction of Name, Calories, and Protein.
     * Index 0: Name, Index 1: Calories, Index 3: Protein
     */
    private fun parseCsvLine(line: String): Triple<String, String, String>? {
        val result = mutableListOf<String>()
        var cur = StringBuilder()
        var inQuotes = false
        
        for (ch in line) {
            if (ch == '\"') {
                inQuotes = !inQuotes
            } else if (ch == ',' && !inQuotes) {
                result.add(cur.toString().trim())
                cur = StringBuilder()
            } else {
                cur.append(ch)
            }
        }
        result.add(cur.toString().trim())
        
        return if (result.size >= 4) {
            Triple(result[0], result[1], result[3])
        } else {
            null
        }
    }

    private fun parseMeal(json: JSONObject?) = MealRec(
        json?.optString("name", "Healthy Meal") ?: "Healthy Meal",
        json?.optInt("calories", 0) ?: 0,
        json?.optInt("protein", 0) ?: 0
    )
}