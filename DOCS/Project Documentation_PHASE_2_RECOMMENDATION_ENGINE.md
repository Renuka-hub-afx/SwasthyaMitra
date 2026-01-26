# SwasthyaMitra Smart Recommendation Engine Architecture

## Implementation Status: ✅ PHASE 2 COMPLETE

**Last Updated:** January 26, 2026  
**Status:** Core engine implemented and tested. Interactive features in progress.

### What's Working:
- ✅ Two-Tier BMR/TDEE + AI generation
- ✅ CSV grounding with 1000+ Indian foods
- ✅ Activity & weight plateau detection
- ✅ Festival-aware recommendations
- ✅ Singleton service architecture
- ✅ Firebase security rules configured

### In Progress:
- 🔄 Meal regeneration with exclusions
- 🔄 Ate/Skipped feedback tracking
- 🔄 User preference learning

---

## Overview
SwasthyaMitra employs a **"Two-Tier Hybrid" strategy**, leveraging a specific technology stack to balance deterministic safety with probabilistic AI intelligence.

## 1. Technology Stack
The architecture is built on five core pillars:

| Component | Technology | Role |
| :--- | :--- | :--- |
| **App Logic** | **Kotlin** (`AIDietPlanService.kt`) | Orchestrates data fetching, logic flow, and AI execution. |
| **Data Engineering** | **Python** (`convert_food_data.py`) | Pre-processes complex Excel nutrition data into optimized CSVs. |
| **Intelligence** | **Vertex AI (Gemini 2.0 Flash)** | The "Brain" that performs complex reasoning and menu generation. |
| **Backend** | **Firebase (Firestore & Auth)** | Stores user profiles, logs, and manages secure identity. |
| **Integration** | **JSON** | The standardized language for data exchange between AI and UI. |

---

## 2. Tier 1: The "Macro Balancer" (Deterministic Logic)
* **Managed By:** Kotlin (`FoodRecommendationEngine.kt` / `AIDietPlanService.kt`)
* **Function:** Operates on absolute mathematical rules.
* **Logic:** If `calories_consumed > daily_limit`, the system immediately filters the local CSV database for "Low Calorie" tags.
* **Why:** Ensures nutritional safety and adherence to hard limits without needing AI "guesses."

---

## 3. Tier 2: The "Pattern Recognizer" (GenAI)
* **Managed By:** Vertex AI (**Gemini 2.0 Flash**) via Kotlin SDK
* **Function:** Handles complex, human-centric variables:
    * **Context Awareness:** "It is Diwali today, suggests a healthy treat."
    * **Activity Adaptation:** "User ran 5k, increase carbohydrates."
* **Grounding:** The AI is strictly "grounded" in the Indian Food CSV processed by your Python pipeline. It allows the AI to be creative but forces it to choose *real* foods with *accurate* macros.

---

## 4. Data Engineering Pipeline
* **Input:** Raw government/public nutrition datasets (Excel).
* **Process:** The `convert_food_data.py` script cleans the data, calculates missing macros, and normalizes food names.
* **Output:** A lightweight `food_data.csv` (formerly `Indian_Food.csv`) bundled into the Android `assets` folder, ensuring the app works fast even with poor internet.

---

## 5. The Feedback Loop (Interactive Features)
* **Mechanism:** User actions (Ate/Skipped/Regenerated) are saved to Firestore.
* **Learning:** `AIDietPlanService.kt` fetches these "Past Meals" before requesting a new plan, ensuring the AI learns from previous rejections and avoids repetition.
* **New Collections:**
  - `meal_feedback`: Tracks every user interaction with recommendations
  - `ai_generated_plans`: Stores complete daily plans for history
  - `user_preferences`: Learns long-term food likes/dislikes

---

# AI Implementation Guide (Kotlin + Vertex AI)

## Technical Setup

### 1. The Orchestrator: `AIDietPlanService.kt`
This Kotlin service is the heart of the system. It follows this execution flow:
1.  **Fetch Profile:** Retrieves User metadata (Weight, Goal, Allergies) from Firestore.
2.  **Fetch Context:** Pulls recent `ExerciseLogs` and `WeightLogs` to calculate the "Intensity Score."
3.  **Load Database:** Reads the local `food_data.csv` (prepared by Python).
4.  **Construct Prompt:** Combines User Data + Context + Food List into a structured prompt.
5.  **Execute AI:** Calls **Gemini 2.0 Flash** via the `com.google.firebase.vertexai` SDK.
6.  **Parse & Return:** Deserializes the JSON response into UI objects.

### 2. The Model: Gemini 2.0 Flash
* **Why Flash?** Chosen for its low latency and high cost-efficiency, which is critical for a consumer mobile app.
* **Configuration:**
    * `temperature`: 0.4 (Low creativity to ensure factual nutrition data).
    * `responseMimeType`: "application/json" (Forces structured output).

## The Smart Prompt Template
The Kotlin service dynamically injects variables into this template:

```text
You are SwasthyaMitra, an expert Indian Nutritionist.

**USER CONTEXT:**
- Profile: {age}yrs, {gender}, {weight}kg
- Goal: {goal} (Target: {target_weight})
- Restrictions: {allergies}
- Season/Festival: {current_season} / {festival_name}

**ACTIVITY STATUS:**
- Recent Workout: {last_workout_type}
- Intensity: {intensity_level} (Adjust macros accordingly)

**AVAILABLE FOODS (Select ONLY from here):**
{csv_sample_string}

**TASK:**
Generate a daily meal plan in JSON format.
Rules:
1. If "High Intensity", add post-workout protein.
2. Do not repeat meals from {past_meals_list}.
3. Calculate exact calories based on the provided food list.

**OUTPUT JSON:**
{
  "breakfast": { "item": "Name", "calories": 000, "protein": "0g", "reason": "..." },
  "lunch": { ... },
  ...
}
```

---

# Adaptive Logic Rules

These rules are encoded into the **Kotlin Logic (`AIDietPlanService.kt`)** and passed to the AI to ensure dynamic adaptation.

## 1. Activity-Based Adaptation
The Kotlin service analyzes Firestore `exercise_logs` to determine the flag sent to Gemini.

| User Log Data | System Flag Sent to AI | AI Response Behavior |
| :--- | :--- | :--- |
| **HIIT / Heavy Lifting** | `INTENSITY_HIGH` | **Adds "Post-Workout" meal.** Increases protein targets by 15%. Suggests electrolytes. |
| **Yoga / Walking** | `INTENSITY_MODERATE` | Maintains standard deficit/maintenance calories. |
| **No Logs (3+ Days)** | `INTENSITY_SEDENTARY` | **Reduces Carbohydrates.** Suggests high-fiber/low-cal volume foods to manage hunger without calories. |

## 2. Weight Trend Logic
The service calculates the slope of `weight_logs` (last 14 days).

* **Plateau Detected:** (Weight change < 0.2kg in 14 days)
    * *Kotlin Action:* Injects instruction: *"User is hitting a plateau. Suggest a 'Zig-Zag' calorie day (Higher calories today to boost metabolism)."*
* **Rapid Loss:** (Loss > 1.5kg/week)
    * *Kotlin Action:* Injects instruction: *"CRITICAL: User losing weight too fast. Add healthy fats (Nuts/Ghee) to stabilize."*

## 3. The "Festival Engine"
* **Logic:** The app checks the device date against a hardcoded Indian Festival Calendar.
* **Trigger:** If `Date == Festival`:
    * The prompt instructs Gemini: *"It is Diwali. Allow ONE healthy festive treat (e.g., Baked Chakli) but balance the rest of the day with lighter meals."*

---

# User Guide: Smart Plan & Interactions

## The "Today's Plan" Dashboard
This UI is powered by the `AIDietPlanService` and updates dynamically.

### 1. Interactive Elements
* **🍳 Breakfast - Dinner:** Cards display the meal, exact calories, and a "Why?" tag (e.g., "High Protein for your Morning Run").
* **🔁 Regenerate Button:**
    * *Function:* If you don't like a suggestion, click this.
    * *Under the Hood:* The Kotlin service calls Gemini 2.0 Flash again with a specific "Exclude: [Previous Dish]" parameter to ensure you get a fresh option immediately.
* **✔ Ate / ❌ Skipped:**
    * *Function:* Tracks adherence.
    * *Impact:* "Skipped" meals are analyzed. If you skip "Oats" 3 times, the AI stops suggesting it.

## 2. Notifications (Future: Cloud Functions)
* **Cloud Functions Triggers:** While the AI generation happens in the app, the **Firebase Backend** will monitor your logs.
    * *Scenario:* You haven't logged lunch by 3 PM.
    * *Action:* Cloud Function sends a push notification: *"Don't skip meals! Check your personalized lunch plan now."*
    * *Status:* Planned for Phase 3 (requires Firebase Cloud Functions deployment)

---

# Firestore Data Collections

## Core Collections (Existing)

### `users`
- `userId`, `name`, `email`, `age`, `weight`, `height`, `gender`, `eatingPreference`, `allergies`

### `goals`
- `userId`, `goalType`, `targetWeight`, `dailyCalories`, `activityLevel`, `bmr`, `tdee`

### `foodLogs`
- `userId`, `foodName`, `calories`, `protein`, `carbs`, `fat`, `mealType`, `timestamp`, `date`

### `exercise_logs`
- `userId`, `type`, `intensity`, `duration`, `timestamp`

### `weight_logs`
- `userId`, `weight`, `timestamp`

---

## New Collections (Phase 2 Interactive Features)

### `meal_feedback`
**Purpose:** Track user reactions to improve AI recommendations

**Attributes:**
- `feedbackId` (auto-generated)
- `userId` (string)
- `mealName` (string) - e.g., "Poha", "Dal Tadka"
- `mealType` (string) - "Breakfast", "Lunch", "Dinner", "Snack"
- `action` (string) - "Ate", "Skipped", "Regenerated"
- `timestamp` (number)
- `date` (string) - "YYYY-MM-DD"
- `reason` (string, optional) - User-provided skip reason

**Required Index:** `userId` (Ascending) + `timestamp` (Descending)

---

### `ai_generated_plans`
**Purpose:** Store complete daily plans for history and regeneration

**Attributes:**
- `planId` (auto-generated)
- `userId` (string)
- `generatedAt` (number)
- `date` (string) - "YYYY-MM-DD"
- `breakfast`, `lunch`, `snack`, `dinner`, `postWorkout` (objects with: item, calories, protein, reason)
- `dailyTip` (string)
- `totalCalories` (number)
- `status` (string) - "Active", "Completed", "Abandoned"

**Required Index:** `userId` (Ascending) + `date` (Descending)

---

### `user_preferences`
**Purpose:** Learn long-term food preferences

**Attributes:**
- `userId` (document ID, not a field)
- `dislikedFoods` (array of strings)
- `favoriteFoods` (array of strings)
- `allergyTags` (array of strings)
- `lastUpdated` (number)

**Note:** Single document per user (use userId as document ID)

---

# Implementation Roadmap

## Phase 2.1: Core Engine ✅ COMPLETE
- [x] `AIDietPlanService.kt` with Singleton pattern
- [x] BMR/TDEE calculations
- [x] CSV grounding mechanism
- [x] Gemini 2.0 Flash integration
- [x] Activity & plateau detection
- [x] Festival awareness
- [x] Firebase security rules

## Phase 2.2: Interactive Features 🔄 IN PROGRESS
- [ ] Meal regeneration API
- [ ] Feedback tracking system
- [ ] User preference learning
- [ ] Enhanced UI with action buttons

## Phase 2.3: Cloud Functions (Future)
- [ ] Scheduled meal reminders
- [ ] Weekly progress reports
- [ ] Automated plateau detection alerts