# User Profile & Gamification

## Overview
This feature manages the user's personal details, physical attributes, health goals, and gamification progress (XP, Levels, Streaks).

## Key Components

### 1. Profile Management (`ProfileActivity.kt`)
*   **Purpose:** Allows users to view and edit their personal information.
*   **Data Sources:**
    *   **Firestore:** `users/{userId}` (Core stats) and `users/{userId}/goals` (Targets).
    *   **SharedPreferences:** `user_profile` (Offline caching).
*   **Key Fields Managed:** Name, Age, Gender, Height, Weight, Goal Weight, Daily Calories.

### 2. Avatar Customization (`AvatarCustomizationActivity.kt`)
*   **Purpose:** Personalize profile appearance.
*   **Features:**
    *   **Presets:** Selection of 13 built-in avatars.
    *   **Gallery:** Import custom photos from device gallery.
    *   **Editor:** Undo/Redo functionality for changes.
    *   **Storage:** Saves selection to Firestore (`selected_avatar_id`) and persists gallery URIs locally via `AvatarManager`.

### 3. Gamification
*   **Moved:** Full details on XP, Levels, Badges, and Challenges are now in [GAMIFICATION.md](GAMIFICATION.md).
*   **Data Source:** Realtime Database (RTDB) for high-frequency updates (Steps, Streaks).

## Data Structure

### Firestore (`users/{userId}/goals`)
Stores current health targets:
*   `targetWeight`
*   `dailyCalories`
*   `activityLevel`
*   `dietPreference`

### Realtime Database (`users/{userId}`)
*   See [GAMIFICATION.md](GAMIFICATION.md) for schema details.
