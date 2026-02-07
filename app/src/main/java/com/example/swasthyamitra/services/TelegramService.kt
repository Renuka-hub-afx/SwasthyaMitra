package com.example.swasthyamitra.services

import android.util.Log
import com.example.swasthyamitra.config.TelegramConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Service to send notifications to Telegram
 * Completely FREE - no third-party services needed!
 */
object TelegramService {
    
    private const val TAG = "TelegramService"
    
    /**
     * Send a meal plan summary to Telegram
     */
    suspend fun sendMealPlanToTelegram(
        userName: String,
        date: String,
        meals: List<MealInfo>,
        totalCalories: Int,
        protein: Int,
        carbs: Int,
        fat: Int
    ): Result<Boolean> {
        val mealsList = meals.joinToString("\n") { "• ${it.name} (${it.mealType}): ${it.calories} kcal" }
        
        val message = """
🍽️ *Meal Plan for $userName*
📅 Date: $date

*Meals Logged:*
$mealsList

📊 *Nutrition Summary:*
• Total Calories: $totalCalories kcal
• Protein: ${protein}g
• Carbs: ${carbs}g
• Fat: ${fat}g

_Sent from SwasthyaMitra App_ 💪
        """.trimIndent()
        
        return sendMessage(message)
    }
    
    /**
     * Send exercise summary to Telegram
     */
    suspend fun sendExercisePlanToTelegram(
        userName: String,
        date: String,
        exercises: List<ExerciseInfo>,
        totalCaloriesBurned: Int,
        totalDuration: Int
    ): Result<Boolean> {
        val exercisesList = exercises.joinToString("\n") { "• ${it.name}: ${it.duration} min, ${it.caloriesBurned} kcal" }
        
        val message = """
🏋️ *Workout Summary for $userName*
📅 Date: $date

*Exercises Completed:*
$exercisesList

📊 *Stats:*
• Total Duration: $totalDuration minutes
• Calories Burned: $totalCaloriesBurned kcal

_Keep pushing! 💪 - SwasthyaMitra_
        """.trimIndent()
        
        return sendMessage(message)
    }
    
    /**
     * Send daily summary to Telegram
     */
    suspend fun sendDailySummaryToTelegram(
        userName: String,
        caloriesConsumed: Int,
        caloriesBurned: Int,
        calorieGoal: Int,
        mealsLogged: Int,
        workoutsCompleted: Int
    ): Result<Boolean> {
        val netCalories = caloriesConsumed - caloriesBurned
        val progress = if (calorieGoal > 0) ((caloriesConsumed.toFloat() / calorieGoal) * 100).toInt() else 0
        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val today = dateFormat.format(Date())
        
        val statusEmoji = when {
            progress < 80 -> "🟡"
            progress in 80..110 -> "🟢"
            else -> "🔴"
        }
        
        val message = """
📊 *Daily Summary for $userName*
📅 $today

$statusEmoji *Calorie Progress: $progress%*

🍽️ *Nutrition:*
• Consumed: $caloriesConsumed kcal
• Goal: $calorieGoal kcal
• Meals Logged: $mealsLogged

🏃 *Activity:*
• Burned: $caloriesBurned kcal
• Workouts: $workoutsCompleted

⚖️ *Net Calories: $netCalories kcal*

_Stay consistent! 🎯 - SwasthyaMitra_
        """.trimIndent()
        
        return sendMessage(message)
    }
    
    /**
     * Send a simple exercise summary to Telegram
     */
    suspend fun sendExerciseSummaryToTelegram(
        exerciseName: String,
        duration: String,
        caloriesBurned: Int,
        exerciseType: String,
        notes: String = ""
    ): Result<Boolean> {
        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val today = dateFormat.format(Date())
        
        val notesSection = if (notes.isNotEmpty()) "\n📝 $notes" else ""
        
        val message = """
🏃 *$exerciseName*
📅 $today

📊 *Stats:*
• Type: $exerciseType
• Duration: $duration
• Calories Burned: $caloriesBurned kcal
$notesSection

_Keep up the great work! 💪 - SwasthyaMitra_
        """.trimIndent()
        
        return sendMessage(message)
    }
    
    /**
     * Send a custom message to Telegram
     */
    suspend fun sendCustomMessage(message: String): Result<Boolean> {
        return sendMessage(message)
    }
    
    /**
     * Core function to send message via Telegram Bot API
     */
    private suspend fun sendMessage(text: String): Result<Boolean> {
        return withContext(Dispatchers.IO) {
            try {
                val url = URL(TelegramConfig.getSendMessageUrl())
                val connection = url.openConnection() as HttpURLConnection
                
                connection.requestMethod = "POST"
                connection.setRequestProperty("Content-Type", "application/json; charset=utf-8")
                connection.doOutput = true
                connection.connectTimeout = 15000
                connection.readTimeout = 15000
                
                val jsonPayload = JSONObject().apply {
                    put("chat_id", TelegramConfig.CHAT_ID)
                    put("text", text)
                    put("parse_mode", "Markdown")
                }
                
                connection.outputStream.use { os ->
                    val input = jsonPayload.toString().toByteArray(Charsets.UTF_8)
                    os.write(input, 0, input.size)
                }
                
                val responseCode = connection.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val response = connection.inputStream.bufferedReader().readText()
                    Log.d(TAG, "Telegram message sent successfully: $response")
                    Result.success(true)
                } else {
                    val error = connection.errorStream?.bufferedReader()?.readText() ?: "Unknown error"
                    Log.e(TAG, "Telegram API error: $responseCode - $error")
                    Result.failure(Exception("HTTP $responseCode: $error"))
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error sending Telegram message: ${e.message}")
                Result.failure(e)
            }
        }
    }
    
    // Data classes
    data class MealInfo(
        val name: String,
        val mealType: String,
        val calories: Int
    )
    
    data class ExerciseInfo(
        val name: String,
        val duration: Int,
        val caloriesBurned: Int
    )
}
