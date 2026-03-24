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
        val mindfulnessTip: String
    )

    suspend fun getMoodBasedRecommendations(
        moodData: MoodData,
        userProfile: Map<String, Any>
    ): Result<RecommendationResult> = withContext(Dispatchers.IO) {
        try {
            val mood      = moodData.mood
            val intensity = moodData.intensity
            val userName  = userProfile["name"] as? String ?: "User"

            val promptText = """
                You are a compassionate mindfulness coach for SwasthyaMitra.

                **USER CONTEXT:**
                - Name: $userName
                - Current Mood: **$mood** (Intensity: $intensity/1.0)
                - Time: ${java.text.SimpleDateFormat("HH:mm").format(java.util.Date())}

                **TASK:**
                Give ONE short, actionable mindfulness tip (1-2 sentences) tailored to the user's mood.

                **MOOD GUIDANCE:**
                - Stressed / Anxious  → Box breathing or a body-scan technique
                - Sad / Down          → Gratitude journaling or self-compassion mantra
                - Tired / Exhausted   → Mindful rest; slow-breath reset (4-7-8 breathing)
                - Happy / Excited     → Savouring moment — notice 3 things you're grateful for right now
                - Calm / Relaxed      → Anchor to the present with a 1-minute observation exercise

                **OUTPUT:**
                Just the tip — plain text, no headers, no labels, no extra explanation.
                Address the user by name ($userName). Keep it warm and supportive.
            """.trimIndent()

            val config = generationConfig {
                temperature = 0.7f
            }
            val generativeModel = Firebase.ai(backend = GenerativeBackend.googleAI())
                .generativeModel("gemini-2.0-flash", generationConfig = config)

            val response = generativeModel.generateContent(promptText)
            val tip = response.text?.trim() ?: throw Exception("Empty AI response")

            Result.success(RecommendationResult(mindfulnessTip = tip))

        } catch (e: Exception) {
            Log.e(TAG, "AI Generation Error", e)
            Result.failure(e)
        }
    }
}
