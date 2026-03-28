package com.example.swasthyamitra.ai

// Android framework imports for app context and logging
import android.content.Context
import android.util.Log
// Custom models for mood tracking data structures
import com.example.swasthyamitra.models.MoodData
// Firebase AI (Gemini) imports for intelligent mindfulness recommendations
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
import com.google.firebase.ai.type.generationConfig
// Kotlin coroutines for async AI operations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// AI service for generating personalized mindfulness tips based on user's emotional state and mood patterns
class AIMoodRecommendationService(private val context: Context) {

    // Logging tag for debugging mood recommendation generation
    private val TAG = "AIMoodRecService"

    // Data class for structured mindfulness recommendation results
    data class RecommendationResult(
        val mindfulnessTip: String  // AI-generated personalized mindfulness advice
    )

    // Generate personalized mindfulness recommendation based on user's current mood and intensity level
    suspend fun getMoodBasedRecommendations(
        moodData: MoodData,           // User's current mood data (mood type, intensity)
        userProfile: Map<String, Any> // User profile information for personalization
    ): Result<RecommendationResult> = withContext(Dispatchers.IO) {
        try {
            // Extract mood information for AI prompt construction
            val mood      = moodData.mood      // Current emotional state (Happy/Sad/Stressed/etc.)
            val intensity = moodData.intensity // Mood intensity level (0.0 to 1.0 scale)
            val userName  = userProfile["name"] as? String ?: "User"  // User's name for personalization

            // Comprehensive AI prompt for mood-specific mindfulness guidance
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

            // Configure AI model for warm, supportive mindfulness guidance
            val config = generationConfig {
                temperature = 0.7f  // Balanced creativity for personalized but consistent advice
            }
            // Initialize Google AI Gemini model for mindfulness recommendation generation
            val generativeModel = Firebase.ai(backend = GenerativeBackend.googleAI())
                .generativeModel("gemini-2.0-flash", generationConfig = config)

            // Generate personalized mindfulness tip using AI
            val response = generativeModel.generateContent(promptText)
            val tip = response.text?.trim() ?: throw Exception("Empty AI response")

            // Return successful mindfulness recommendation
            Result.success(RecommendationResult(mindfulnessTip = tip))

        } catch (e: Exception) {
            // Log error and return failure if AI generation fails
            Log.e(TAG, "AI Generation Error", e)
            Result.failure(e)
        }
    }
}
