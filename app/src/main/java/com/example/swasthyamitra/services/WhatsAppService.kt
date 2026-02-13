package com.example.swasthyamitra.services

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.example.swasthyamitra.config.N8nConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

/**
 * Service to send data to n8n workflows which will forward to WhatsApp
 */
object WhatsAppService {
    
    private const val TAG = "WhatsAppService"
    
    /**
     * Send meal plan to WhatsApp via n8n webhook
     */
    suspend fun sendMealPlanToWhatsApp(
        context: Context,
        phoneNumber: String,
        userName: String,
        mealPlan: MealPlanData
    ): Result<String> {
        return withContext(Dispatchers.IO) {
            try {
                val jsonPayload = JSONObject().apply {
                    put("phone", phoneNumber)
                    put("userName", userName)
                    put("date", mealPlan.date)
                    put("totalCalories", mealPlan.totalCalories)
                    put("meals", mealPlan.meals.joinToString("\n") { 
                        "• ${it.name} (${it.mealType}): ${it.calories} kcal"
                    })
                    put("protein", mealPlan.totalProtein)
                    put("carbs", mealPlan.totalCarbs)
                    put("fat", mealPlan.totalFat)
                }
                
                sendWebhookRequest(N8nConfig.getMealPlanWebhookUrl(), jsonPayload)
            } catch (e: Exception) {
                Log.e(TAG, "Error sending meal plan: ${e.message}")
                Result.failure(e)
            }
        }
    }
    
    /**
     * Send exercise plan to WhatsApp via n8n webhook
     */
    suspend fun sendExercisePlanToWhatsApp(
        context: Context,
        phoneNumber: String,
        userName: String,
        exercisePlan: ExercisePlanData
    ): Result<String> {
        return withContext(Dispatchers.IO) {
            try {
                val jsonPayload = JSONObject().apply {
                    put("phone", phoneNumber)
                    put("userName", userName)
                    put("date", exercisePlan.date)
                    put("totalCaloriesBurned", exercisePlan.totalCaloriesBurned)
                    put("exercises", exercisePlan.exercises.joinToString("\n") { 
                        "• ${it.name}: ${it.duration} min, ${it.caloriesBurned} kcal burned"
                    })
                    put("totalDuration", exercisePlan.totalDuration)
                }
                
                sendWebhookRequest(N8nConfig.getExercisePlanWebhookUrl(), jsonPayload)
            } catch (e: Exception) {
                Log.e(TAG, "Error sending exercise plan: ${e.message}")
                Result.failure(e)
            }
        }
    }
    
    /**
     * Send daily summary to WhatsApp via n8n webhook
     */
    suspend fun sendDailySummaryToWhatsApp(
        context: Context,
        phoneNumber: String,
        userName: String,
        summary: DailySummaryData
    ): Result<String> {
        return withContext(Dispatchers.IO) {
            try {
                val jsonPayload = JSONObject().apply {
                    put("phone", phoneNumber)
                    put("userName", userName)
                    put("date", summary.date)
                    put("caloriesConsumed", summary.caloriesConsumed)
                    put("caloriesBurned", summary.caloriesBurned)
                    put("netCalories", summary.caloriesConsumed - summary.caloriesBurned)
                    put("calorieGoal", summary.calorieGoal)
                    put("mealsLogged", summary.mealsLogged)
                    put("workoutsCompleted", summary.workoutsCompleted)
                }
                
                sendWebhookRequest(N8nConfig.getDailySummaryWebhookUrl(), jsonPayload)
            } catch (e: Exception) {
                Log.e(TAG, "Error sending daily summary: ${e.message}")
                Result.failure(e)
            }
        }
    }
    
    /**
     * Fallback: Open WhatsApp with pre-filled message (no n8n needed)
     */
    fun openWhatsAppWithMessage(context: Context, phoneNumber: String, message: String) {
        try {
            val formattedPhone = phoneNumber.replace("+", "").replace(" ", "")
            val url = "https://wa.me/$formattedPhone?text=${Uri.encode(message)}"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "WhatsApp not installed", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun sendWebhookRequest(url: String, jsonPayload: JSONObject): Result<String> {
        val connection = URL(url).openConnection() as HttpURLConnection
        return try {
            connection.requestMethod = "POST"
            connection.setRequestProperty("Content-Type", "application/json")
            connection.setRequestProperty("ngrok-skip-browser-warning", "true")
            connection.doOutput = true
            connection.connectTimeout = 10000
            connection.readTimeout = 10000
            
            connection.outputStream.use { os ->
                val input = jsonPayload.toString().toByteArray(Charsets.UTF_8)
                os.write(input, 0, input.size)
            }
            
            val responseCode = connection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val response = connection.inputStream.bufferedReader().readText()
                Log.d(TAG, "Webhook response: $response")
                Result.success(response)
            } else {
                val error = connection.errorStream?.bufferedReader()?.readText() ?: "Unknown error"
                Log.e(TAG, "Webhook error: $responseCode - $error")
                Result.failure(Exception("HTTP $responseCode: $error"))
            }
        } finally {
            connection.disconnect()
        }
    }
    
    // Data classes
    data class MealPlanData(
        val date: String,
        val totalCalories: Int,
        val totalProtein: Int,
        val totalCarbs: Int,
        val totalFat: Int,
        val meals: List<MealItem>
    )
    
    data class MealItem(
        val name: String,
        val mealType: String,
        val calories: Int
    )
    
    data class ExercisePlanData(
        val date: String,
        val totalCaloriesBurned: Int,
        val totalDuration: Int,
        val exercises: List<ExerciseItem>
    )
    
    data class ExerciseItem(
        val name: String,
        val duration: Int,
        val caloriesBurned: Int
    )
    
    data class DailySummaryData(
        val date: String,
        val caloriesConsumed: Int,
        val caloriesBurned: Int,
        val calorieGoal: Int,
        val mealsLogged: Int,
        val workoutsCompleted: Int
    )
}
