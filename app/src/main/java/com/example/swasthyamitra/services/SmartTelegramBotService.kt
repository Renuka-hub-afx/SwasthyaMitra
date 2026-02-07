package com.example.swasthyamitra.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.swasthyamitra.R
import com.example.swasthyamitra.ai.AICoachMessageService
import com.example.swasthyamitra.ai.AIDietPlanService
import com.example.swasthyamitra.ai.MealPlan
import com.example.swasthyamitra.ai.MealRec
import com.example.swasthyamitra.ai.AIExerciseRecommendationService
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.example.swasthyamitra.config.TelegramConfig
import com.example.swasthyamitra.homepage
import kotlinx.coroutines.*
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

/**
 * Smart Telegram Bot Service - 24/7 AI-powered Telegram assistant
 * Polls Telegram for incoming messages and responds using Vertex AI (Gemini)
 */
class SmartTelegramBotService : Service() {
    
    companion object {
        private const val TAG = "SmartTelegramBot"
        private const val CHANNEL_ID = "telegram_bot_channel"
        private const val NOTIFICATION_ID = 1001
        private const val POLL_INTERVAL_MS = 10000L // 10 seconds
        
        private var isRunning = false
        
        fun isServiceRunning(): Boolean = isRunning
        
        fun start(context: Context) {
            if (!isRunning) {
                val intent = Intent(context, SmartTelegramBotService::class.java)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    context.startForegroundService(intent)
                } else {
                    context.startService(intent)
                }
            }
        }
        
        fun stop(context: Context) {
            context.stopService(Intent(context, SmartTelegramBotService::class.java))
        }
    }
    
    private val serviceScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private var lastUpdateId: Long = 0
    private var pollingJob: Job? = null
    private lateinit var authHelper: FirebaseAuthHelper
    
    // Intent types for command parsing
    enum class TelegramIntent {
        DIET_PLAN,
        EXERCISE,
        PROGRESS,
        HELP,
        HYDRATION,
        UNKNOWN
    }
    
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "SmartTelegramBotService created")
        authHelper = FirebaseAuthHelper(this)
        createNotificationChannel()
    }
    
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "SmartTelegramBotService started")
        isRunning = true
        
        // Start as foreground service
        startForeground(NOTIFICATION_ID, createNotification())
        
        // Start polling
        startPolling()
        
        return START_STICKY
    }
    
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "SmartTelegramBotService destroyed")
        isRunning = false
        pollingJob?.cancel()
        serviceScope.cancel()
    }
    
    override fun onBind(intent: Intent?): IBinder? = null
    
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Telegram Bot Service",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "SwasthyaMitra AI assistant is listening on Telegram"
                setShowBadge(false)
            }
            
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
    
    private fun createNotification(): Notification {
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            Intent(this, homepage::class.java),
            PendingIntent.FLAG_IMMUTABLE
        )
        
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("SwasthyaMitra Bot Active")
            .setContentText("Your AI health assistant is listening on Telegram")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()
    }
    
    private fun startPolling() {
        pollingJob?.cancel()
        pollingJob = serviceScope.launch {
            Log.d(TAG, "Starting Telegram polling...")
            
            // Send startup message
            sendTelegramMessage("🤖 *SwasthyaMitra Bot is now active!*\n\nSend me commands like:\n• \"Plan my meals\"\n• \"Suggest an exercise\"\n• \"How am I doing?\"\n• \"Help\"")
            
            while (isActive) {
                try {
                    pollForUpdates()
                    delay(POLL_INTERVAL_MS)
                } catch (e: CancellationException) {
                    break
                } catch (e: Exception) {
                    Log.e(TAG, "Polling error: ${e.message}")
                    delay(POLL_INTERVAL_MS * 2) // Wait longer on error
                }
            }
        }
    }
    
    private suspend fun pollForUpdates() {
        withContext(Dispatchers.IO) {
            try {
                val url = URL(TelegramConfig.getUpdatesUrl(lastUpdateId + 1))
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.connectTimeout = 35000
                connection.readTimeout = 35000
                
                val responseCode = connection.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val response = connection.inputStream.bufferedReader().readText()
                    processUpdates(response)
                } else {
                    Log.e(TAG, "Telegram API error: $responseCode")
                }
                connection.disconnect()
            } catch (e: Exception) {
                Log.e(TAG, "Error polling Telegram: ${e.message}")
            }
        }
    }
    
    private suspend fun processUpdates(response: String) {
        try {
            val json = JSONObject(response)
            if (!json.getBoolean("ok")) return
            
            val result = json.getJSONArray("result")
            for (i in 0 until result.length()) {
                val update = result.getJSONObject(i)
                val updateId = update.getLong("update_id")
                lastUpdateId = maxOf(lastUpdateId, updateId)
                
                if (update.has("message")) {
                    val message = update.getJSONObject("message")
                    if (message.has("text")) {
                        val chatId = message.getJSONObject("chat").getLong("id")
                        val text = message.getString("text")
                        val fromName = message.optJSONObject("from")?.optString("first_name", "User") ?: "User"
                        
                        // Only respond to our configured chat
                        if (chatId.toString() == TelegramConfig.CHAT_ID) {
                            Log.d(TAG, "Received message from $fromName: $text")
                            handleIncomingMessage(text, fromName)
                        }
                    }
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error processing updates: ${e.message}")
        }
    }
    
    private suspend fun handleIncomingMessage(message: String, userName: String) {
        val intent = detectIntent(message)
        Log.d(TAG, "Detected intent: $intent for message: $message")
        
        // Send typing indicator
        sendTelegramMessage("⏳ _Processing your request..._")
        
        try {
            val response = when (intent) {
                TelegramIntent.DIET_PLAN -> handleDietPlanRequest(userName)
                TelegramIntent.EXERCISE -> handleExerciseRequest(userName)
                TelegramIntent.PROGRESS -> handleProgressRequest(userName)
                TelegramIntent.HYDRATION -> handleHydrationRequest(userName)
                TelegramIntent.HELP -> getHelpMessage()
                TelegramIntent.UNKNOWN -> handleUnknownMessage(message, userName)
            }
            
            sendTelegramMessage(response)
        } catch (e: Exception) {
            Log.e(TAG, "Error handling message: ${e.message}")
            sendTelegramMessage("❌ Sorry, something went wrong. Please try again!\n\n_Error: ${e.message}_")
        }
    }
    
    private fun detectIntent(message: String): TelegramIntent {
        val lowerMessage = message.lowercase().trim()
        
        return when {
            // Diet/Meal related
            lowerMessage.contains("diet") || 
            lowerMessage.contains("meal") || 
            lowerMessage.contains("food") ||
            lowerMessage.contains("eat") ||
            lowerMessage.contains("breakfast") ||
            lowerMessage.contains("lunch") ||
            lowerMessage.contains("dinner") ||
            lowerMessage.contains("plan my") ||
            lowerMessage.contains("what should i eat") -> TelegramIntent.DIET_PLAN
            
            // Exercise related
            lowerMessage.contains("exercise") ||
            lowerMessage.contains("workout") ||
            lowerMessage.contains("gym") ||
            lowerMessage.contains("fitness") ||
            lowerMessage.contains("burn") ||
            lowerMessage.contains("train") -> TelegramIntent.EXERCISE
            
            // Progress/Summary related
            lowerMessage.contains("progress") ||
            lowerMessage.contains("summary") ||
            lowerMessage.contains("status") ||
            lowerMessage.contains("how am i") ||
            lowerMessage.contains("how'm i") ||
            lowerMessage.contains("report") ||
            lowerMessage.contains("doing") -> TelegramIntent.PROGRESS
            
            // Hydration
            lowerMessage.contains("water") ||
            lowerMessage.contains("hydrat") ||
            lowerMessage.contains("drink") -> TelegramIntent.HYDRATION
            
            // Help
            lowerMessage.contains("help") ||
            lowerMessage.contains("command") ||
            lowerMessage == "/start" ||
            lowerMessage == "start" -> TelegramIntent.HELP
            
            else -> TelegramIntent.UNKNOWN
        }
    }
    
    private suspend fun handleDietPlanRequest(userName: String): String {
        return try {
            val dietService = AIDietPlanService.getInstance(this@SmartTelegramBotService)
            val result = dietService.generateSmartDietPlan()
            
            result.fold(
                onSuccess = { plan ->
                    val meals = listOf(
                        "🌅 *Breakfast*: ${plan.breakfast.item}\n   _${plan.breakfast.calories} kcal | Protein: ${plan.breakfast.protein}_",
                        "☀️ *Lunch*: ${plan.lunch.item}\n   _${plan.lunch.calories} kcal | Protein: ${plan.lunch.protein}_",
                        "🍪 *Snack*: ${plan.snack.item}\n   _${plan.snack.calories} kcal | Protein: ${plan.snack.protein}_",
                        "🌙 *Dinner*: ${plan.dinner.item}\n   _${plan.dinner.calories} kcal | Protein: ${plan.dinner.protein}_"
                    )
                    
                    val totalCalories = plan.breakfast.calories + plan.lunch.calories + plan.snack.calories + plan.dinner.calories
                    
                    """
🍽️ *Personalized Diet Plan for $userName*

${meals.joinToString("\n\n")}

📊 *Total: ~$totalCalories kcal*

💡 _${plan.dailyTip}_

_Generated by SwasthyaMitra AI 🤖_
                    """.trimIndent()
                },
                onFailure = { e ->
                    Log.e(TAG, "Error generating diet plan: ${e.message}")
                    """
❌ *Couldn't generate diet plan*

The AI might be busy or you may need to update your profile goals.

_Error: ${e.message}_
                    """.trimIndent()
                }
            )
        } catch (e: Exception) {
            Log.e(TAG, "Error generating diet plan: ${e.message}")
            "❌ Error: ${e.message}"
        }
    }
    
    private suspend fun handleExerciseRequest(userName: String): String {
        return try {
            val exerciseService = AIExerciseRecommendationService.getInstance(this@SmartTelegramBotService)
            val recommendation = exerciseService.getExerciseRecommendation(0)
            
            recommendation.fold(
                onSuccess = { rec ->
                    val instructions = rec.instructions.take(4).mapIndexed { i, step -> 
                        "${i + 1}. $step" 
                    }.joinToString("\n")
                    
                    """
🏋️ *Exercise Recommendation for $userName*

💪 *${rec.name}*
🎯 Target: ${rec.targetMuscle}
🔥 Burns: ~${rec.estimatedCalories} kcal
⏱️ Duration: ${rec.recommendedDuration}

📝 *How to do it:*
$instructions

_${rec.reason}_

_Keep pushing! 💪 - SwasthyaMitra AI_
                    """.trimIndent()
                },
                onFailure = { e ->
                    "❌ Couldn't generate exercise recommendation. Please try again!\n\n_Error: ${e.message}_"
                }
            )
        } catch (e: Exception) {
            Log.e(TAG, "Error generating exercise: ${e.message}")
            "❌ Error generating exercise recommendation. Please try again!"
        }
    }
    
    private suspend fun handleProgressRequest(userName: String): String {
        return try {
            val userId = authHelper.getCurrentUser()?.uid ?: return "❌ Please login to the app first to see your progress."
            
            val coachService = AICoachMessageService.getInstance(this@SmartTelegramBotService)
            val coachMessage = coachService.getCoachMessage(userId, 0)
            
            coachMessage.fold(
                onSuccess = { message ->
                    """
📊 *Your Daily Summary, $userName*

$message

_Stay consistent! 🎯 - SwasthyaMitra AI_
                    """.trimIndent()
                },
                onFailure = {
                    """
📊 *Progress Update for $userName*

Open the app to view your detailed progress including:
• Calories consumed vs burned
• Workout streak
• Hydration status
• Weekly statistics

_Keep tracking! 💪_
                    """.trimIndent()
                }
            )
        } catch (e: Exception) {
            Log.e(TAG, "Error getting progress: ${e.message}")
            "❌ Couldn't fetch progress. Please try again or open the app!"
        }
    }
    
    private fun handleHydrationRequest(userName: String): String {
        return """
💧 *Hydration Reminder for $userName*

Stay hydrated! Here are some tips:
• Drink at least 8 glasses (2L) of water daily
• Start your morning with a glass of water
• Set reminders every 2 hours
• Carry a water bottle with you

Open the SwasthyaMitra app to:
• Log your water intake 
• See your hydration progress
• Get personalized water goals

_Water is life! 💧 - SwasthyaMitra_
        """.trimIndent()
    }
    
    private fun getHelpMessage(): String {
        return """
🤖 *SwasthyaMitra AI Assistant*

Here's what I can help you with:

🍽️ *Diet & Nutrition*
• "Plan my meals"
• "What should I eat today?"
• "Suggest breakfast/lunch/dinner"

🏋️ *Exercise & Fitness*
• "Suggest an exercise"
• "Give me a workout"
• "What exercise should I do?"

📊 *Progress & Summary*
• "How am I doing?"
• "Show my progress"
• "Daily summary"

💧 *Hydration*
• "Water reminder"
• "How much water should I drink?"

_Just type naturally and I'll understand! 🧠_

_Powered by Vertex AI (Gemini) 🚀_
        """.trimIndent()
    }
    
    private suspend fun handleUnknownMessage(message: String, userName: String): String {
        // For unknown messages, try to give a helpful response
        return """
👋 Hi $userName! I didn't quite understand that.

Try one of these commands:
• "Plan my meals" - Get a personalized diet plan
• "Suggest an exercise" - Get workout recommendations
• "How am I doing?" - See your progress summary
• "Help" - See all available commands

_Just type naturally and I'll try to help! 🤖_
        """.trimIndent()
    }
    
    private suspend fun sendTelegramMessage(text: String): Boolean {
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
                connection.disconnect()
                
                responseCode == HttpURLConnection.HTTP_OK
            } catch (e: Exception) {
                Log.e(TAG, "Error sending Telegram message: ${e.message}")
                false
            }
        }
    }
}
