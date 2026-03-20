# SwasthyaMitra (स्वास्थ्यमित्र) 🥗💪

**SwasthyaMitra** is a state-of-the-art, AI-integrated health and wellness Android application built in Kotlin. It provides a unique 360-degree approach to fitness, nutrition, mental health, and personal safety—all in one place.

---

## 🚀 Key Modules & Features

### 🧠 AI-Powered Intelligence
*   **AI Coach:** Gets dynamic, context-aware motivational messages on your dashboard.
*   **AI Exercise Recommendations:** personalised 5-exercise circuits based on age, gender, mood, and goal.
*   **AI Smart Diet:** Generate a full-day meal plan based on your BMR, TDEE, and health goals.
*   **AI Smart Pantry (Rasoi):** Input what's in your kitchen, and the AI suggests healthy recipes you can cook instantly.
*   **Period Mode:** Specialized support for female users with gentler activity and iron-rich nutrition suggestions.

### 🔥 Gamification & Social (Challenge a Friend)
*   **Streak System:** Protect your daily habits with streaks. Missed a day? Use a **Shield** to save your progress!
*   **Level Up:** Earn XP for every meal logged and workout completed. Climb the ranks from Beginner to Legend.
*   **Challenges:** Competitively challenge friends to a 7-day streak race using unique 6-digit codes.
*   **Badges:** Unlock milestone rewards like "Hydration Hero" and "Sleep Saint."

### 🚶 Fitness & Activity Tracking
*   **Live Map Tracking:** Foregound-service based GPS route mapping with real-time stats (Steps, Distance, Pace, Calories).
*   **Step Discovery:** Background step counting active throughout the day.
*   **Workout Dashboard:** Quick access to manual exercise logs, AI circuits, and history.

### 🥗 Nutrition & Hydration
*   **Food Logging:** Manual or barcode-based food entry with automated macro breakdown (Net Carbs, Protein, Fat).
*   **Water Tracker:** Personalised hydration goals with hourly smart notification nudges.
*   **Macro Insights:** Visual progress bars for your daily nutrient targets.

### 🛌 Wellness & Safety
*   **Sleep Tracker:** Monitor nightly cycles, rating quality, and tracking a 7-night targets streak.
*   **Mood Tracker:** Log your daily emotions and get curated self-care activities.
*   **🚨 One-Tap SOS:** Send a silent, background emergency SMS with your live GPS location to your saved contact with a single tap.

---

## 🛠️ Technology Stack

| Layer | Technology |
|---|---|
| **Architecture** | Simplified MVVM / Repository Pattern |
| **Primary Database** | **Firebase Firestore** (Instance: `renu`) - User profiles & history |
| **Realtime Sync** | **Firebase Realtime Database** - Live challenges & streak mirroring |
| **Identity** | **Firebase Authentication** (Email/Password) |
| **Background Tasks** | **WorkManager** (Hourly reminders) & Foreground Services (Live Tracking) |
| **Visuals** | **MPAndroidChart** (Analytics), **Glide** (GIFs), **Material Design 3** |
| **ML/AI** | **Rule-Based AI Engines** & **ML Kit** (Barcode/Vision) |

---

## 📂 Project Documentation Structure

For deep technical details on each module, explore the **[docs/](./docs/)** folder:

1.  [**00. Project Overview**](./docs/00_PROJECT_OVERVIEW.md) - Tech stack & database schema.
2.  [**01. Authentication**](./docs/01_AUTHENTICATION.md) - Sign-up & Goal initialization.
3.  [**02. Homepage Dashboard**](./docs/02_HOMEPAGE_DASHBOARD.md) - Real-time metrics UI.
4.  [**03. Food & Nutrition**](./docs/03_FOOD_NUTRITION_LOGGING.md) - Barcode scanning & macros.
5.  [**04. Water & Hydration**](./docs/04_WATER_HYDRATION.md) - Reminders & Goal logic.
6.  [**05. Workout & GPS Tracking**](./docs/05_WORKOUT_STEP_TRACKING.md) - Foreground service & Maps.
7.  [**06. AI Recommendations**](./docs/06_AI_EXERCISE_RECOMMENDATIONS.md) - The recommendation algorithm.
8.  [**07. Sleep Tracker**](./docs/07_SLEEP_TRACKER.md) - Nightly logging & streak guards.
9.  [**08. Gamification**](./docs/08_GAMIFICATION.md) - XP, Shields, and Levels.
10. [**09. Challenge a Friend**](./docs/09_CHALLENGE_A_FRIEND.md) - Competitive social features.
11. [**10. AI Smart Diet**](./docs/10_AI_SMART_DIET.md) - BMR/TDEE meal planning.
12. [**11. AI Smart Pantry**](./docs/11_AI_SMART_PANTRY.md) - Ingredient-to-recipe matching.
13. [**12. Mood Tracking**](./docs/12_MOOD_TRACKER.md) - Emotional well-being AI.
14. [**13. Progress & Insights**](./docs/13_PROGRESS_INSIGHTS.md) - Analytical charts (7/15/30 days).
15. [**14. SOS Alert Implementation**](./docs/14_SOS_EMERGENCY_ALERT.md) - Direct SMS & Safety logic.
16. [**15. Profile & Avatar**](./docs/15_PROFILE_AVATAR_SETTINGS.md) - Customization & Notifications.

---

## 🔧 Installation & Setup

1.  **Clone the Repository:**
    ```bash
    git clone https://github.com/your-repo/SwasthyaMitra.git
    ```
2.  **Android Studio Configuration:**
    *   Open the `SwasthyaMitra` folder.
    *   Ensure you have **API 26+ (Android 8.0)** SDK installed.
3.  **Firebase Integration:**
    *   Add your `google-services.json` to the `app/` folder.
    *   In the Firebase Console, initialize:
        *   **Authentication** (Email/Password provider).
        *   **Cloud Firestore** (Database: `renu`).
        *   **Realtime Database** (Asia-Southeast region recommended).
4.  **Google Maps API:**
    *   Get a key from [Google Cloud Console](https://console.cloud.google.com/).
    *   Add it to `local.properties` or `AndroidManifest.xml`.
5.  **Build:** Sync Gradle and press **Run**.

---

## 📱 Screenshots & Demo
*(Add your high-quality showcase images here)*

---

## 🛡️ Recent Updates (v2.0)
*   **Direct SOS:** Completely refactored the SOS feature to send emergency SMS *instantly* in the background, skipping any messaging app taps.
*   **Streak Protection:** Refined the shield system to protect user progress automatically upon missed check-ins.
*   **Enhanced Progress:** Added interactive line and bar charts for every tracked metric (Steps, Calories, Sleep, Water).

---
Developed with ❤️ for a Healthier Future.
