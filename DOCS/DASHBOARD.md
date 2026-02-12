# Dashboard & Homepage

## Overview
The **Homepage** (`homepage.kt`) serves as the central hub of the application, providing users with a snapshot of their daily progress and quick access to all key features.

## Key Features

### 1. Daily Summaries
*   **Step Counter:** Real-time step tracking using `StepManager`.
*   **Calorie Balance:** Visual comparison of Calories In (Food) vs. Calories Out (Exercise + BMR).
*   **Hydration & Workouts:** Quick view of today's water intake and workout count.
*   **Macro Breakdown:** Progress bars for Protein, Carbs, and Fats based on daily food logs.

### 2. Goal Tracking
*   Displays current active goal (e.g., "Weight Loss").
*   Calculates and displays "Weight Remaining" to reach the target.

### 3. AI Coach Integration
*   Displays dynamic, motivating messages via `AICoachMessageService`.
*   Adapts messages based on user progress (steps) and time of day.

### 4. Specialized Modes
*   **Period Mode:** A toggle for female users to adjust recommendations and tracking for their menstrual cycle. Updates `isOnPeriod` flag in Firestore.

### 5. Quick Navigation
*   **Food Log:** `FoodLogActivity`
*   **Workouts:** `WorkoutDashboardActivity`
*   **Hydration:** `HydrationActivity`
*   **AI Features:** `AISmartDietActivity` (Chef), `SmartPantryActivity` (Rasoi)
*   **Mood Tracking:** Quick mood entry buttons interacting with `LocalMoodAnalyzer`.

## Data Sources
*   **Firestore:** `users/{userId}` for profile/goals, subcollections for logs.
*   **SharedPreferences:** Step count persistence.
