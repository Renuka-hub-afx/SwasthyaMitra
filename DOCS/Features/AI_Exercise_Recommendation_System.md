# AI Exercise Recommendation System: Closed-Loop Fitness 🏋️‍♂️🤖

This document outlines the technical design for a unique, AI-driven exercise recommendation engine that adapts based on user age, eating habits, and the custom `exercisedb_v1_sample`.

---

## 🏗️ 1. The "Closed-Loop" Logic
The core innovation is that **Exercise follows Diet**. The app doesn't just recommend random workouts; it reacts to what the user ate today.

| Scenario | Nutritional Trigger | Exercise Logic |
| :--- | :--- | :--- |
| **Calorie Surplus** | User exceeded target calories by > 200 | Recommend **High-Intensity Cardio** to burn the excess. |
| **High Carbs** | Carbs > 60% of daily macros | Recommend **Compound Movements** (Squats/Press) to use glucose. |
| **Protein Rich** | Protein target met or exceeded | Recommend **Hypertrophy/Strength** training for muscle repair. |
| **Sedentary / Low Cal** | User ate very little | Recommend **Low Impact/Yoga** to prevent fatigue. |

---

## 🧠 2. Gemini 2.0 Integration & Contextual Filtering
We use **Vertex AI (Gemini 2.0 Flash)** to marry the user's bio-data with the physical exercise database.

### The Input Context (Prompt):
> "You are a Sports Scientist. User is **{{age}}** years old. Goal: **{{goal}}**. 
> Today's intake: **{{calories}}** kcal, **{{protein}}**g. 
> Recommended from DB: Provide 3 exercises from this list: **{{exercise_db_sample}}**.
> **Rule**: If age > 50, avoid high-impact jumping. If surplus > 300, prioritize fat burning."

### Data Source:
- **Location**: `app/src/main/assets/exercisedb_v1_sample/exercises.json`
- **Fields used**: `targetMuscles`, `bodyParts`, `equipments`, `instructions`.

---

## 🛠️ 3. Technology Stack
1.  **AI Engine**: Google Vertex AI (Gemini 2.0 Flash).
2.  **Scheduling**: `WorkManager` (to analyze dinner intake and recommend next-morning workouts).
3.  **Local DB**: JSON parsing of assets for fast offline filtering.
4.  **UI**: Material Design 3 cards showing GIFs (from `gifs_360x360` folder) and instructions.

---

## 🗺️ 4. Implementation Steps

1.  **Step 1: Data Parser**: Create a `JsonParser` utility to read exercises from assets into a list of objects.
2.  **Step 2: Nutrition Sync**: Modify the existing `FoodLog` service to broadcast the "Current Macro Status" to the Exercise Engine.
3.  **Step 3: AI Service**: Build `AIExerciseRecommendationService.kt`.
    - It takes (User Bio + Macro Status + Random 10 Exercises from JSON).
    - It returns the "Best 3" for the user's specific context.
4.  **Step 4: The UI**:
    - Add a "Coach Recommendation" section to the Workout Activity.
    - Display the Exercise Name, Target Muscle, and a direct link to the GIF.

---

## 📢 5. WhatsApp Integration (Bonus)
The "Daily Digest" mentioned in the WhatsApp plan will include these recommended exercises automatically. 
*Example: "Hi Rahul! Since you had a high-carb lunch, try 3 sets of Barbell Squats today to keep the balance!"*

---
> [!IMPORTANT]
> This system ensures that users are never "over-training" on low calories or "under-training" on a surplus, making the app a true scientific health partner.
