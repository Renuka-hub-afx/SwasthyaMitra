# Women's Health Module 🌸

## Overview
A comprehensive suite of features designed to support women's health tracking, including menstrual cycle monitoring, symptom logging, and AI-driven insights.

## Core Features
1.  **Cycle Tracking**
    *   Track period start and end dates.
    *   Predict next period and fertile window.
    *   "Period Mode" toggle (Implemented) - adjusts app behavior (gentler workouts, comfort foods).

2.  **Symptom Logging**
    *   Log daily symptoms (Cramps, Mood, Flow, Energy).
    *   Visual calendar view of history.

3.  **AI Insights (SwasthyaMitra Coach)**
    *   Correlate symptoms with diet/exercise.
    *   "Cycle-Syncing" recommendations:
        *   **Follicular Phase**: High energy, strength training.
        *   **Luteal Phase**: Comfort foods, lighter activity.
        *   **Menstrual Phase**: Rest, hydration, iron-rich foods.

## Technical Architecture
*   **Data Model (`users/{uid}/cycle_logs`)**:
    *   `startDate`: Timestamp
    *   `endDate`: Timestamp
    *   `symptoms`: Array<String>
    *   `flowIntensity`: String (Light, Medium, Heavy)
    *   `mood`: String

*   **UI Components**:
    *   `WomensHealthActivity`: Main dashboard.
    *   `CalendarView`: Custom or library-based calendar for logging.
    *   `SymptomBottomSheet`: Quick logging interface.

## Integration Points
*   **Homepage**: Display current phase and predicted next date.
*   **AI Service**: Inject cycle phase into prompts for Diet/Exercise.
