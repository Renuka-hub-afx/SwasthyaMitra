# 🤖 AI Suite Documentation

SwasthyaMitra leverages **Google's Gemini 2.0 Flash** model via the Firebase GenAI SDK to provide intelligent, context-aware features.

## 1. AI Rasoi (Smart Pantry) 👨‍🍳
*   **Function**: Computer Vision + Generative Recipe Creation.
*   **How it works**:
    1.  User takes a photo of ingredients.
    2.  App sends the Bitmap to Gemini Vision.
    3.  **Prompt**: "Identify ingredients -> Suggest ONE healthy Indian recipe with macros".
    4.  **Output**: Structured JSON (Recipe Name, Calories, Ingredients, Instructions).
*   **Code Reference**: `SmartPantryActivity.kt`, `AIPantryService.kt`

## 2. AI Coach 💬
*   **Function**: Daily behavioral analysis and motivation.
*   **How it works**:
    1.  Aggregates user data: Steps, Water, Calories, Macros, Time of Day, Period Status.
    2.  **Prompt**: "Act as a kind friend. Analyze this data. Give 1 sentence of advice."
    3.  **Period Mode**: If active, prompts AI to be extra gentle and focus on comfort.
*   **Code Reference**: `AICoachMessageService.kt`, `homepage.kt`

## 3. Smart Exercise Recommendations 🏋️
*   **Function**: Context-aware workout suggestions.
*   **Logic**:
    *   **Time**: Morning vs Night suggestions.
    *   **Metabolism**: High carb intake? Suggest Cardio. High protein? Suggest Strength.
    *   **Period Mode**: Suggests Yoga/Stretching if active.
    *   **Age/Gender**: tailored safety advice.
*   **Code Reference**: `AIExerciseRecommendationService.kt`

## 4. AI Diet Planner 🥗
*   **Function**: Full day meal planning.
*   **Logic**: Generates Breakfast/Lunch/Snack/Dinner based on TDEE and Dietary Preference (Veg/Non-Veg).
*   **Code Reference**: `AIDietPlanService.kt`

---

## 🧠 Model Configuration
*   **Model**: `gemini-2.0-flash`
*   **Temperature**:
    *   Coach: `0.8` (Creative, friendly)
    *   Recipes/Diet: `0.4` (Structured, reliable)
*   **Safety Settings**: Default
