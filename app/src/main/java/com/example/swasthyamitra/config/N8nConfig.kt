package com.example.swasthyamitra.config

object N8nConfig {
    // ngrok tunnel URL - Update this when ngrok restarts
    const val BASE_URL = "https://disconsolate-arie-nondiscriminatively.ngrok-free.dev"
    
    // Webhook endpoints (we'll create these in n8n)
    const val WEBHOOK_SEND_MEAL_PLAN = "/webhook/send-meal-plan"
    const val WEBHOOK_SEND_EXERCISE_PLAN = "/webhook/send-exercise-plan"
    const val WEBHOOK_SEND_DAILY_SUMMARY = "/webhook/send-daily-summary"
    
    fun getMealPlanWebhookUrl(): String = "$BASE_URL$WEBHOOK_SEND_MEAL_PLAN"
    fun getExercisePlanWebhookUrl(): String = "$BASE_URL$WEBHOOK_SEND_EXERCISE_PLAN"
    fun getDailySummaryWebhookUrl(): String = "$BASE_URL$WEBHOOK_SEND_DAILY_SUMMARY"
}
