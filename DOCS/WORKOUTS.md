# Workouts & Exercise

## Overview
The Workout feature (`WorkoutDashboardActivity.kt`) helps users track physical activity, follow recommended routines, and visualize fitness progress. It combines manual logging with AI-driven recommendations.

## Key Components

### 1. Exercise Tracking
*   **Step Counter:** Uses device sensors (`StepManager`) to track daily steps and estimate passive calorie burn.
*   **Manual Logging:** Users can log custom exercises via `ManualExerciseActivity`.
*   **Video Workouts:** `WorkoutVideoRepository` provides curated YouTube video links based on the user's goal (e.g., "Weight Loss" -> "HIIT").

### 2. AI Recommendations
*   **Service:** `AIExerciseRecommendationService` generates personalized exercise suggestions.
*   **Inputs:** User goal, current steps, calorie balance status ("High"/"Low"), and optional mood data.
*   **Features:**
    *   Target muscle focus
    *   Estimated calorie burn
    *   Age/Gender specific insights
    *   GIF demonstrations (loaded from assets)

### 3. Data Sync (Hybrid Model)
This feature writes to **both** Firestore (for permanent logs) and Realtime Database (for gamification).

*   **Firestore:** `users/{userId}/exercise_logs`
    *   Stores permanent history of performed exercises.
    *   Used for generating historical charts in `ProgressActivity`.

*   **Realtime Database:** `users/{userId}` (FitnessData)
    *   Updates `xp`, `streak`, `totalWorkoutMinutes`, and `workoutHistory`.
    *   Used for gamification features (Levels, Leaderboards).

## Logic Flow
1.  **Recommendation:** App checks calorie balance (Calories In - Calories Out).
2.  **Suggestion:**
    *   *Surplus:* Suggests high-intensity cardio/strength.
    *   *Deficit:* Suggests moderate cardio or yoga.
3.  **Completion:** User marks exercise as done -> App updates Firestore Log -> App updates RTDB XP/Streak.
