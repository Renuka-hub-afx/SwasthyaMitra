# SwasthyaMitra: Master Architecture Plan

## 1. Executive Summary
**SwasthyaMitra** is a holistic AI-powered health companion app designed to help users manage their physical and mental well-being. It combines traditional tracking (food, workout, water) with advanced AI features (personalized diet plans, workout recommendations, mood analysis) to provide a 360-degree health solution.

---

## 2. User Journey: Start to End

### Phase 1: Onboarding (The Setup)
*   **Goal**: Create a personalized profile and baseline for AI.
*   **Flow**:
    1.  **Auth**: Login/Signup via Firebase Auth.
    2.  **Basic Info**: Name, Age, Gender, Height, Weight.
    3.  **Goal Setting**: Choose from "Weight Loss", "Muscle Gain", "Maintenance", etc.
    4.  **Lifestyle Calibration**: Activity level, dietary preferences (Veg/Non-Veg), allergies.
    5.  **Calculations**: App automatically calculates BMI, BMR, TDEE, and Daily Calorie Targets.
    6.  **Data Storage**: Saved to `users/{userId}` and `users/{userId}/goals`.

### Phase 2: The Core Loop (Daily Engagement)
The user lands on the **Dashboard (Homepage)**, which serves as the central hub.
*   **Morning**:
    *   Check **AI Coach Message** (personalized greeting + tip).
    *   View **AI Diet Plan** generated for the day.
    *   Log **Hydration** (quick add).
*   **During the Day**:
    *   **Food Logging**: Scan barcodes, snap photos (future), or search database.
    *   **Activity Tracking**: Pedometer runs in background; manual workout logging.
    *   **Smart Pantry**: Ask AI "What can I cook with [ingredients]?" for recipes.
*   **Evening**:
    *   **Mood Tracking**: Log daily mood; receive immediate AI suggestions/exercises.
    *   **Review**: Check progress bars (Protein, Carbs, Fats, Calories).

### Phase 3: Long-Term Engagement (Retention)
*   **Gamification**: Earn XP, level up avatars, maintain streaks.
*   **Social**: Join challenges, view leaderboards.
*   **Insights**: Review weekly weight trends and calorie adherence in `ProgressActivity`.

---

## 3. Technical Architecture

### Architecture Pattern
The app follows a uniform **MVVM (Model-View-ViewModel)** architecture with a clean separation of concerns.

*   **View (Activity/Fragment)**: Handles UI rendering and user input. (e.g., `Homepage`, `FoodLogActivity`)
*   **ViewModel**: Holds UI state and implementation logic; survives configuration changes.
*   **Repository**: Single source of truth for data. Arbitrates between local (Room/SharedPreferences) and remote (Firestore) data sources. (e.g., `HydrationRepository`, `MoodRepository`)
*   **Model**: Data classes representing the schema. (e.g., `User`, `FoodLog`, `MoodData`)

### Technology Stack
*   **Language**: Kotlin (Android Native)
*   **Backend**: Firebase (Auth, Firestore, Cloud Functions, Storage)
*   **AI Engine**: Gemini 1.5 Flash (via Firebase Vertex AI SDK) for text/image generation.
*   **APIs**: OpenFoodFacts (Barcode scanning).

---

## 4. Database Schema Integration
See [DATABASE_SCHEMA.md](DATABASE_SCHEMA.md) for field-level details.

**Key Design Principle: User-Centric Isolation**
*   All data is nested under `/users/{userId}`.
*   **Writes**: App writes directly to subcollections (`foodLogs`, `exercise_logs`).
*   **Reads**: Repositories query these subcollections, often ordered by `timestamp`.
*   **Security**: Firestore Security Rules strictly enforce `request.auth.uid == userId` for these paths.

**Hybrid Approach**:
*   Some features like **Public Challenges** or **Global Recommendations** live at the root level but are linked via `userId`.

---

## 5. "How It Should Work" vs. Current State (Roadmap)

| Feature | Current State | How It **Should** Work (Ideal) |
| :--- | :--- | :--- |
| **Diet Plan** | Generates a static text plan for the day. | **Dynamic & Interactive**: Users should be able to "swap" meals, generating a new option instantly that fits their macros. Plan should auto-adjust based on logged food. |
| **Food Logging** | Manual search & Barcode scanning. | **Visual AI**: Snap a photo, identifying food and estimating portion size automatically using Gemini Vision constants. |
| **Workout Recommendations** | Basic rule-based or static AI suggestions. | **Context-Aware**: Recommendation changes based on *real-time* step count and time of day (e.g., "You're 2k steps short, try this 15-min HIIT"). |
| **Offline Mode** | Limited caching via Firestore. | **Robust Sync**: Full local database (Room) for all logs, syncing seamlessly when online to prevent data loss. |
| **Social** | Basic challenges. | **Real-time Duels**: Live step battles with friends using push notifications. |

---

## 6. Development Workflow (Standard Operating Procedure)
1.  **Plan**: Check `task.md` and define the feature in `MASTER_PLAN.md`.
2.  **Schema**: Update `DATABASE_SCHEMA.md` if new data is needed.
3.  **Rules**: Update `firestore.rules` to secure new paths.
4.  **Code**: Implement Repositories -> ViewModels -> UI.
5.  **Verify**: Log data and check Firestore Console.
