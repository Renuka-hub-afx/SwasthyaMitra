# Analytics & Insights

## Overview
SwasthyaMitra provides deep insights into user progress through visual charts, scores, and historical data.

## 1. Insights Dashboard (`InsightsActivity.kt`)
*   **Purpose:** High-level weekly analysis of user behavior.
*   **Key Metrics:**
    *   **Balance Score (0-100):** A composite score derived from:
        *   **Consistency (40%):** Workout frequency (Target: 4 days/week).
        *   **Activity (30%):** Step count vs Goal.
        *   **Balance (30%):** Calorie intake vs Target.
    *   **Combined Chart:** Visualizes **Calories Consumed** (Bar Chart) vs **Steps Taken** (Line Chart) over the last 7 days.
    *   **Narrative:** AI-like text summary of the week's performance.

## 2. Progress Tracker (`ProgressActivity.kt`)
*   **Purpose:** Tracks gamification stats and workout history.
*   **Data Source:** Firebase Realtime Database.
*   **Key Features:**
    *   **Streaks:** Displays current and longest daily streak.
    *   **Weekly Summary:** Total workouts and calories burned in the last 7 days.
    *   **Validation:** Parses both new timestamp-based logs and legacy date-string logs.
    *   **Navigation:** Access to detailed history, weight progress, and badges.

## 3. Weight Progress (`WeightProgressActivity.kt`)
*   **Purpose:** Track weight changes over time.
*   **Features:**
    *   **Data:** stored in `users/{userId}/weight_logs`.
    *   **Visualization:** Line Chart with toggleable views (Weekly / Monthly).
    *   **Logging:** Simple input dialog to add current weight.

## 4. Manual Exercise Logging (`ManualExerciseActivity.kt`)
*   **Purpose:** Log custom activities.
*   **Features:**
    *   Auto-calculates calories based on duration and intensity (Light/Moderate/High).
    *   Updates both Firestore logs and Realtime Database stats.
