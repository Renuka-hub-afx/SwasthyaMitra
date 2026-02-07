package com.example.swasthyamitra.ai

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
import com.google.firebase.ai.type.content
import com.google.firebase.ai.type.generationConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class AIPantryService private constructor(private val context: Context) {

    private val TAG = "AIPantryService"

    companion object {
        @Volatile
        private var INSTANCE: AIPantryService? = null

        fun getInstance(context: Context): AIPantryService {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: AIPantryService(context.applicationContext).also { INSTANCE = it }
            }
        }
    }

    data class RecipeResult(
        val title: String,
        val calories: Int,
        val protein: Int,
        val carbs: Int,
        val fat: Int,
        val ingredientsDetected: List<String>,
        val instructions: List<String>,
        val reason: String
    )

    suspend fun generateRecipeFromImage(bitmap: Bitmap): Result<RecipeResult> = withContext(Dispatchers.IO) {
        try {
            val promptText = """
                You are an expert Indian Chef for the SwasthyaMitra App.
                
                Look at this image of ingredients.
                1. Identify the healthy ingredients available.
                2. Suggest ONE healthy, home-cooked Indian recipe using these ingredients.
                
                Strictly return JSON format:
                {
                  "title": "Recipe Name",
                  "calories": 300,
                  "protein": 15, // grams
                  "carbs": 40, // grams
                  "fat": 10, // grams
                  "ingredientsDetected": ["Item 1", "Item 2"],
                  "instructions": ["Step 1", "Step 2", "Step 3"],
                  "reason": "Why this is healthy and uses the ingredients well"
                }
            """.trimIndent()

            val generativeModel = Firebase.ai(backend = GenerativeBackend.googleAI())
                .generativeModel("gemini-2.0-flash")

            val inputContent = content {
                image(bitmap)
                text(promptText)
            }

            val response = generativeModel.generateContent(inputContent)
            
            val jsonStr = response.text?.trim()
                ?.removeSurrounding("```json", "```")
                ?.removeSurrounding("```", "```")
                ?.trim() 
                ?: throw Exception("Empty response from AI")

            val json = JSONObject(jsonStr)
            
            val result = RecipeResult(
                title = json.getString("title"),
                calories = json.optInt("calories", 0),
                protein = json.optInt("protein", 0),
                carbs = json.optInt("carbs", 0),
                fat = json.optInt("fat", 0),
                ingredientsDetected = mutableListOf<String>().apply {
                    val arr = json.getJSONArray("ingredientsDetected")
                    for (i in 0 until arr.length()) add(arr.getString(i))
                },
                instructions = mutableListOf<String>().apply {
                    val arr = json.getJSONArray("instructions")
                    for (i in 0 until arr.length()) add(arr.getString(i))
                },
                reason = json.getString("reason")
            )

            Result.success(result)

        } catch (e: Exception) {
            Log.e(TAG, "Error processing pantry image", e)
            Result.failure(e)
        }
    }
}
