# Hydration Tracking

## Overview
The Hydration feature (`HydrationActivity.kt`) helps users track their daily water intake, set personalized goals, and receive timely reminders.

## Key Components

### 1. Tracking & Logs
*   **Data Source:** `HydrationRepository`.
*   **Storage:** Firestore collection `users/{userId}/hydration_logs`.
*   **Features:**
    *   **Quick Add:** Buttons for 250ml and 500ml.
    *   **Custom Entry:** Input specific amounts.
    *   **History:** View logs for specific dates.
    *   **Edit/Delete:** Users can remove accidental entries.

### 2. Smart Goals
*   **Calculator:** `WaterGoalCalculator` suggests daily targets based on user weight.
*   **Customization:** Users can manually override the recommended goal.

### 3. Reminders
*   **Manager:** `WaterReminderManager`.
*   **Configuration:** Users set "Wake Time" and "Sleep Time".
*   **Logic:** Calculations distribute reminders evenly throughout the active day to ensure the goal is met.
*   **Persistence:** Schedule preferences are saved to Firestore.

## Integration
*   **Dashboard:** Displays a summary of today's total intake on the Homepage.
