package com.example.swasthyamitra.ai

import android.content.Context
import android.util.Log
import com.example.swasthyamitra.models.MoodData
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
import com.google.firebase.ai.type.generationConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AIMoodRecommendationService(private val context: Context) {

    private val TAG = "AIMoodRecService"

    data class RecommendationResult(
        val foodRecommendation: String,
        val exerciseRecommendation: String,
        val wellnessTip: String
    )

    suspend fun getMoodBasedRecommendations(
        moodData: MoodData, 
        userProfile: Map<String, Any>
    ): Result<RecommendationResult> = withContext(Dispatchers.IO) {
        try {
            val mood = moodData.mood
            val intensity = moodData.intensity
            val userName = userProfile["name"] as? String ?: "User"
            val goal = userProfile["goalType"] as? String ?: "Health"

            val promptText = """
                You are an empathetic wellness assistant for SwasthyaMitra.
                
                **USER CONTEXT:**
                - Name: $userName
                - Goal: $goal
                - Current Mood: **$mood** (Intensity: $intensity/1.0)
                - Time: ${java.text.SimpleDateFormat("HH:mm").format(java.util.Date())}
                
                **TASK:**
                Provide 3 specific recommendations to help the user feel better or maintain their positive state.
                
                1. **Food Recommendation**: deeply comforting but healthy food specific to their mood.
                   - If Sad: suggest warm, comforting foods (e.g., soup, dark chocolate).
                   - If Tired: suggest energy-boosting foods (not caffeine).
                   - If Stressed: suggest anxiety-reducing foods.
                   - If Happy: suggest celebratory but healthy meals.
                
                2. **Exercise Recommendation**: immediate physical activity to match their energy.
                   - If Stressed/Anxious: Yoga or heavy lifting (release tension).
                   - If Tired: Light stretching or a short walk.
                   - If Sad: Gentle movement to release endorphins.
                   - If Happy: Challenge workout.
                
                3. **Wellness Tip**: A quick, actionable tip (breathing, journaling, gratitude).
                
                **OUTPUT FORMAT:**
                Strictly return the result in this format with these separators:
                [FOOD] ...recommendation...
                [EXERCISE] ...recommendation...
                [TIP] ...tip...
                
                Keep each recommendation concise (1-2 sentences). Be supportive and kind.
            """.trimIndent()

            val config = generationConfig {
                temperature = 0.7f
            }
            val generativeModel = Firebase.ai(backend = GenerativeBackend.googleAI())
                .generativeModel("gemini-2.0-flash", generationConfig = config)

            val response = generativeModel.generateContent(promptText)
            val text = response.text ?: throw Exception("Empty AI response")

            // Parse the response
            val food = text.substringAfter("[FOOD]").substringBefore("[EXERCISE]").trim()
            val exercise = text.substringAfter("[EXERCISE]").substringBefore("[TIP]").trim()
            val tip = text.substringAfter("[TIP]").trim()

            Result.success(RecommendationResult(food, exercise, tip))

        } catch (e: Exception) {
            Log.e(TAG, "AI Generation Error", e)
            Result.failure(e)
        }
    }
}
