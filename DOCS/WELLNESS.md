# Wellness & Safety

## Overview
SwasthyaMitra goes beyond physical fitness to monitor mental well-being and physical safety during activities.

## 1. Mood Tracking (`MoodRecommendationActivity.kt`)
*   **Purpose:** Allows users to log their emotional state and visualize trends over time.
*   **Data Source:** `MoodRepository`.
*   **Key Features:**
    *   **Logging:** Users select from presets (Happy, Calm, Sad, Stressed, etc.) on the Homepage.
    *   **History:** Displays a chronological list of past logs.
    *   **Analysis:** A Pie Chart visualizes the distribution of moods (e.g., "60% Happy").
    *   **Integration:** Mood data is used by the **Workout Dashboard** to fine-tune AI exercise recommendations (e.g., "Stressed" -> "Yoga").

## 2. Safety Monitor (`SafetyActivity.kt`)
*   **Purpose:** Provides security for users exercising outdoors (e.g., jogging alone).
*   **Service:** `SafetyMonitorService` (runs in background/foreground).
*   **Key Features:**
    *   **Run Tracking:** Monitors real-time distance and location.
    *   **Emergency Contact:** User saves a trusted phone number locally.
    *   **SOS Alerts:**
        *   **Manual:** Long-press the SOS button to send an immediate SMS with the current location link.
        *   **Automated:** Detects lack of movement for 30 seconds. Triggers a countdown dialog ("Are you safe?"). If no response, automatically sends an SOS SMS.
