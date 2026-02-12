# AI Features

## Overview
SwasthyaMitra leverages Generative AI (Gemini) to provide personalized nutrition advice and smart cooking features.

## 1. AI Smart Diet (`AISmartDietActivity.kt`)
*   **Service:** `AIDietPlanService`
*   **Functionality:** Generates a full-day meal plan (Breakfast, Lunch, Dinner, Snack) based on user preferences and goals.
*   **Key Features:**
    *   **Metabolic Analysis:** Analyzes recent workout intensity and weight trends to detect plateaus.
    *   **Regeneration:** Users can regenerate specific meals if they don't like the suggestion.
    *   **Smart Logging:** Clicking "Ate" automatically logs the AI-suggested meal to `foodLogs` with macronutrient details.
    *   **Social Sharing:** Generates a formatted WhatsApp message with the daily plan.

## 2. Smart Pantry / AI Rasoi (`SmartPantryActivity.kt`)
*   **Service:** `AIPantryService`
*   **Concept:** "Cook with what you have."
*   **Workflow:**
    1.  User takes a photo of ingredients or chooses from the gallery.
    2.  **Image Analysis:** AI identifies the ingredients in the image.
    3.  **Recipe Generation:** Creates a recipe using those specific ingredients.
    4.  **Logging:** Users can log the generated recipe directly to their daily diary.

## 3. AI Coach (`AICoachMessageService`)
*   **Location:** Homepage
*   **Functionality:** Provides dynamic, context-aware motivational messages based on:
    *   Time of day
    *   Step count progress
    *   Recent user activity

## 4. AI Structured Meal Plan (`MealPlanActivity.kt`)
*   **Engine:** `FoodRecommendationEngine` + `RecommendationRepository`.
*   **Features:**
    *   **Target Calculation:** Automatically calculates Calorie, Protein, Carb, and Fat targets based on Profile and Goal.
    *   **Local Generation:** Generates a full day's plan using the internal `IndianFoodRepository`.
    *   **Quick Log:** One-tap logging for suggested breakfast/lunch/dinner items.

## 5. Visual Food Analysis (`FoodPhotoCaptureActivity.kt`)
*   **Status:** *Beta / Placeholder*.
*   **Goal:** Estimate nutrition from food photos using ML/AI.
*   **Current State:** UI implemented; currently returns mock analysis data.
