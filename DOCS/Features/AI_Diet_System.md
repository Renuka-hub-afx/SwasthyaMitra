# Feature: AI Diet System (Smart Recommendation Engine)

## 🤖 Overview
The AI Diet System is a sophisticated "Closed-Loop" nutritionist. It doesn't just suggest meals; it calculates your exact biological needs, grounds those needs in a massive database of raw Indian ingredients, and uses Gemini 2.0 to "cook" a daily plan that evolves based on your progress and taste.

---

## 🛠️ File Architecture

### **1. The Brain (Service Layer)**
- **`AIDietPlanService.kt`**: The core orchestrator. Handles Firebase data retrieval, CSV parsing, metabolic math, and Gemini API orchestration.
- **`MealPlan.kt`**: The data model that defines the structure of breakfast, lunch, dinner, snacks, and "post-workout" meals.

### **2. The Interface (UI Layer)**
- **`MealPlanActivity.kt`**: Displays the active plan in a card-based layout.
- **`MealPlanAdapter.kt`**: Handles the rendering of individual meal cards with "Ate/Skipped" feedback buttons.

### **3. The Knowledge (Data Assets)**
- **`food_data.csv`**, **`foods (1).csv`**, **`Indian_Food_DF (2).csv`**: These files provide over 1,000+ localized food entries used for AI grounding.

---

## 🧠 Core Logic & Implementation

### **1. Scientific Calculation (The Foundation)**
The app uses the **Mifflin-St Jeor Equation** to find the user's Basal Metabolic Rate (BMR) and then applies a physical activity multiplier to find the Total Daily Energy Expenditure (TDEE).

```kotlin
// From AIDietPlanService.kt (Internal Logic)
fun calculateBMR(weight: Double, height: Double, age: Int, gender: String): Double {
    return if (gender.equals("Male", true)) {
        (10 * weight) + (6.25 * height) - (5 * age) + 5
    } else {
        (10 * weight) + (6.25 * height) - (5 * age) - 161
    }
}
```

### **2. AI Grounding (Preventing Hallucination)**
To ensure Gemini doesn't suggest food with fake calories, the app "grounds" it. It samples 50-60 relevant items from the Indian CSVs and inserts them into the prompt.

```kotlin
// How we load the grounding data
private fun loadFoodSampleFromCsv(preference: String, limit: Int): String {
    val files = listOf("food_data.csv", "foods (1).csv", "Indian_Food_DF (2).csv")
    // Filters based on Vegan/Vegetarian/Eggetarian...
    // Returns a string list: "Paneer (250 kcal), Dal (150 kcal)..."
}
```

### **3. Adaptive Contextual Prompting**
The prompt is dynamic. The service checks for specific flags:
- **`INTENSITY_HIGH`**: If recent exercise logs show heavy cardio, the AI is forced to add a `postWorkout` meal.
- **`PLATEAU_DETECTED`**: If weight has stalled for 14 days, the AI suggests "Metabolic Boosters."
- **`SEASON/FESTIVAL`**: The AI receives instructions like "Suggest cooling foods" in summer or "Healthy Diwali snacks" in October.

---

## 🔄 User Feedback Loop
The AI System actually **learns** your preferences through the feedback model:

1.  **Ate**: Increases the "Favorite" weight of that food.
2.  **Skipped**: Adds the food to the `dislikedFoods` list in Firestore.
3.  **Regenerate**: If a user dislikes a specific meal, they can regenerate just that one slot without changing the entire day.

```kotlin
// Logic from trackFeedback()
if (action == "Skipped") {
    updateUserPreferences(userId, mealName, isDisliked = true)
}
```

---

## ✅ Deployment Specs
- **Model**: `gemini-2.0-flash`
- **Temperature**: `0.4` (Low temperature for precise nutritional adherence)
- **Format**: `application/json` (Ensures the app can parse result into native UI components)
