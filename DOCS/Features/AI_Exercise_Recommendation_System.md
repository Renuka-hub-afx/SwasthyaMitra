# AI Exercise Recommendation System: Closed-Loop Fitness 🏋️‍♂️🤖

This document outlines the technical design for a unique, AI-driven exercise recommendation engine that adapts based on user age, gender, weight, mood, eating habits, and the custom `exercisedb_v1_sample`.

---

## 🏗️ 1. The "Closed-Loop" Logic
The core innovation is that **Exercise follows Context**. The app doesn't just recommend random workouts; it reacts to the user's holistic state.

| Scenario | Context Trigger | Exercise Logic |
| :--- | :--- | :--- |
| **Calorie Surplus** | User exceeded target calories | Recommend **High-Intensity Cardio/HIIT** to burn excess. |
| **Period Mode 🌸** | Menstrual cycle active | Recommend **Gentle Yoga / Stretching** for cramp relief & mood. |
| **Mood: Sad/Stressed** | User feeling low | Recommend **Rhythmic/Calming** movements to boost endorphins. |
| **Mood: Energetic** | User feeling happy | Recommend **Challenging/Strength** exercises to utilize energy. |
| **Weight Loss Goal** | Goal is "Weight Loss" | Prioritize **Calorie-burning** efficiency. |

---

## 🧠 2. Gemini Integration & Contextual Filtering
We use the **Firebase GenAI SDK** to marry the user's bio-data with the physical exercise database.

### The Input Context (Prompt):

> "You are a Sports Scientist. User is **{{age}}** years old, **{{gender}}**, weighing **{{weight}}kg**.
> Goal: **{{goal}}**. Mood: **{{mood}}**.
> Period Status: **{{period_user}}**.
> Today's intake: **{{calories}}** kcal.
> Task: Create a session of **3 DISTINCT exercises** (~15 mins each) from this list: **{{exercise_db_sample}}**.
> **Logic Rules**:
> 1. If Period Mode is ACTIVE, suggest ONLY restorative/gentle movements.
> 2. Match intensity to Mood (Sad -> Gentle, Angry -> Intense).
> 3. Provide a detailed **'Benefits'** explanation for why this specific user needs this."

### Data Source:

- **Location**: `app/src/main/assets/exercisedb_v1_sample/exercises.json` and `exercise 2` folder.
- **Fields used**: `name`, `target`, `bodyPart`, `gifUrl`, `instructions`.

---

## 🛠️ 3. Technology Stack

1.  **AI Engine**: Firebase GenAI SDK (Gemini 2.0 Flash).
2.  **Logic Layer**: `AIExerciseRecommendationService.kt`
    - Filters exercises by Age/Gender first.
    - Sends filtered list + Use Context to AI.
    - AI selects top 3 logical sequence (e.g., Warmup -> Core -> Strength).
3.  **UI**: `WorkoutDashboardActivity`
    - Displays one exercise at a time.
    - **"Skip"** / **"Mark as Done"** flow.
    - **Auto-advance**: Moving to next exercise automatically upon completion.
    - **Rich Insights**: Displaying "Why this is important" (benefits) dynamically.

---

## 🗺️ 4. User Flow

1.  **Daily Check**: Homepage checks if `currentDailyExercise` date == Today.
2.  **Generation**: If stale, calling `getExerciseRecommendation`.
3.  **Dashboard**: User enters Workout Dashboard.
    - Sees Exercise 1.
    - Reads "Why this is important" (personalized benefit).
    - Clicks "Mark as Done" -> Animation -> Moves to Exercise 2.
    - Completes all 3 for a full session (~45 mins).

---

## 📢 5. Key Features

- **Period Mode Safety**: Automatically detects period status and bans high-impact/inversion exercises.
- **Mood Scaling**: Adjusts workout intensity based on mental state (Holistic Health).
- **Weight-Adjusted**: Calorie burn estimates and difficulty refined by user weight.
- **Educational**: Teaches the user *why* they are doing what they are doing.

---
> [!IMPORTANT]
> This system ensures that users are never "over-training" on low calories or "under-training" on a surplus, making the app a true scientific health partner.
