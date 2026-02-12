# Gamification & Rewards

## Overview
SwasthyaMitra uses game design elements to motivate users, including a specialized Badge system and an XP/Leveling system.

## 1. XP & Leveling (`GamificationActivity.kt`)
*   **Data Source:** Firebase Realtime Database.
*   **Core Mechanics:**
    *   **XP (Experience Points):** Earned by completing workouts, logging food, etc.
    *   **Levels:** Calculated based on total XP.
    *   **Streaks:** Daily login/activity streak.
    *   **Shields:** Protect streaks from breaking on inactive days.

## 2. Badges & Stages (`BadgesActivity.kt`)
*   **Concept:** A "Journey" with 6 unlockable stages. Stages must be completed sequentially (e.g., Stage 2 unlocks only after Stage 1 is done).
*   **The Stages:**
    1.  **Hydration Hero 💧:** Drink 2000ml water (Data: `HydrationRepository`).
    2.  **Step Master 👣:** Walk 10,000 steps (Data: `StepManager`/RTDB).
    3.  **Sleep Saint 😴:** Sleep 8 hours (Placeholder data).
    4.  **Zen Master 🧘:** Complete 15 mins of "Yoga" or "Meditation" (Data: Workout History).
    5.  **Nutrition Ninja 🍎:** Log 3 meals (Data: `foodLogs`).
    6.  **Iron Legend 🏋️:** Complete 5 workouts (Data: Workout History).
*   **UI:** Grid view showing Locked 🔒, In Progress 🏃, and Completed 🎉 states.
