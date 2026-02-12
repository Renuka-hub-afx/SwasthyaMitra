# User Onboarding Flow

## Overview
The onboarding process collects user data to personalize the SwasthyaMitra experience, calculating health metrics like BMI, BMR, and TDEE to set realistic goals.

## Flow Sequence

### 1. User Info (`UserInfoActivity.kt`)
*   **Input:** Age, Gender, Height, Weight.
*   **Logic:**
    *   Calculates **BMI** instantly.
    *   Updates `users/{userId}` with physical stats.

### 2. Goal Selection (`InsertGoalActivity.kt`)
*   **Input:** Primary Objective.
    *   Lose Weight
    *   Maintain Weight
    *   Gain Muscle
    *   General Health
*   **Logic:** Updates `users/{userId}/goals` with `goalType`.

### 3. Lifestyle & Calibration (`LifestyleActivity.kt`)
*   **Input:**
    *   **Activity Level:** Sedentary to Very Active (Determines TDEE Multiplier).
    *   **Diet Preference:** Veg, Vegan, Non-Veg, etc.
    *   **Schedule:** Wake/Sleep times, Preferred workout slots.
*   **Calculations:**
    *   **BMR:** Mifflin-St Jeor Equation.
    *   **TDEE:** BMR * Activity Factor.
    *   **Daily Target:** Adjusted based on Goal (e.g., -500kcal for Weight Loss).
*   **Final Output:** Saves all metrics to Firestore and redirects to Dashboard.
