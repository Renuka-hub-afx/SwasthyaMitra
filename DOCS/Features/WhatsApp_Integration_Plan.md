# AI-Powered WhatsApp & Health Coach Plan 🤖📱

This plan details how to build a unique **AI Health Coach** within your app and a **Daily Proactive Digest** on WhatsApp that tells users exactly what to eat and how to train.

---

## 🛠️ 1. Technology & API Stack (The "Free" Tier)

Since you are on the **Firebase Blaze Plan**, we will leverage pay-as-you-go tiers that are mostly free for development:

| Feature | Technology | Cost / API Status |
| :--- | :--- | :--- |
| **AI Chatbot** | **Google Gemini 1.5 Flash** | **FREE** (up to 15 RPM / 1M TPM) |
| **Daily Scheduling** | **Android WorkManager** | Native (No Cost) |
| **Messaging** | **WhatsApp Cloud API** | **1,000 FREE** service conversations/month |
| **Storage** | **Firebase Firestore** | Blaze (Generous Free Level) |

---

## 🧠 2. Unique Feature: The AI Health Coach
Users can chat with the app about health problems, diet plateaus, or injury concerns directly in a Chat UI.

### Step 1: Integrated Gemini API
Use the `Google AI SDK for Android` to talk to Gemini without a backend.
```kotlin
// In your ChatViewModel
val generativeModel = GenerativeModel(
    modelName = "gemini-1.5-flash",
    apiKey = "YOUR_GEMINI_API_KEY" // Store in local.properties
)

suspend fun getHealthAdvice(userMessage: String): String {
    val prompt = "You are SwasthyaMitr AI, a health coach. A user asked: $userMessage. " +
                 "Answer based on their data: $currentWeight, $goalType."
    val response = generativeModel.generateContent(prompt)
    return response.text ?: "I'm not sure, please consult a doctor."
}
```

---

## 📢 3. Proactive WhatsApp Digest (Menu + Workouts)
Instead of a simple message, the worker will now fetch the **AI-generated Diet Plan** and **Workout Recommendations** to send a detailed digest.

### Step 2: The Detailed Worker Logic
```kotlin
class HealthDigestWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        // ... (auth and opt-in check as before)

        // 1. Fetch Today's Specific AI Plan from Firestore
        val dietPlan = fetchTodayDietDetails() // e.g., "Poha, Daal-Chawal, Salads"
        val workout = fetchRecommendedWorkout() // e.g., "15 min HIIT Cardio"
        
        // 2. Format Body for WhatsApp Template
        // Template: "Hi {{1}}! Today's Menu: {{2}}. Workout: {{3}}. Streak: {{4}}!"
        sendDetailedWhatsApp(name, dietPlan, workout, streakCount)
        
        return Result.success()
    }
}
```

---

## �️ 4. Step-by-Step Implementation Guide

### Phase 1: Setup Meta & Google AI
1.  **Meta Business Suite**: Create a 'Utility' category template called `daily_health_digest`.
    - Body: `Namaste {{1}}! 🥗 Today's Menu: {{2}}. 🏋️ Exercise: {{3}}. Stay strong at {{4}} days! �`
2.  **Google AI Studio**: Get your API key for **Gemini 1.5 Flash**.

### Phase 2: Building the App Logic
1.  **WorkManager**: Schedule `HealthDigestWorker` to run at 8:00 AM.
2.  **Data Fetching**: Ensure your `DailyHealthWorker` queries the `ai_generated_plans` collection to get the specific food names for the notification.
3.  **Chat UI**: Create a `ChatFragment` where the `generativeModel` is initialized to allow the user to ask "I have a headache, what should I eat?" or "I missed my workout, how to stay on track?".

---

## ⚠️ Important Implementation Notes
- **WhatsApp Webhooks**: If you want the user to reply *on WhatsApp* and have the AI answer *there*, you MUST use a Node.js server to receive the Meta Webhook. 
- **Recommendation**: For now, keep the **Chatting** inside the Kotlin App (using the Gemini SDK) and use **WhatsApp** for **Proactive Notifications** (Menu/Exercises). This keeps your architecture 100% Kotlin.

> [!TIP]
> **Why Gemini 1.5 Flash?** It is extremely fast, understands Indian context (Ayurveda, Indian foods), and is effectively free for your current user base.
