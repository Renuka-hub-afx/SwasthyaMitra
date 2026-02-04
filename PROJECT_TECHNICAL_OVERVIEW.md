# SwasthyaMitra - Project Technical Overview

---

## 🎯 Core Features Implemented

### 1. **User Authentication & Onboarding**

- **Firebase Authentication** - Secure login/signup system.
- **Enhanced Security**: Strong password validation (Upper/Lower/Digit/Special) and phone number format verification.
- **Dedicated Reset Flow**: Standalone `ForgotPasswordActivity` with specialized UI for password recovery.
- **User Profile Management** - Personal information, goals, and preferences stored in Firestore.
- **Goal Setting** - Weight Loss, Weight Gain, Maintenance.
- **Lifestyle Assessment** - Activity level, dietary preferences.

**Technologies:**
- Firebase Authentication
- Cloud Firestore

**Files:**
- `LoginActivity.kt`
- `SignupActivity.kt`
- `ForgotPasswordActivity.kt`
- `UserInfoActivity.kt`
- `InsertGoalActivity.kt`
- `LifestyleActivity.kt`

---

### 2. **Workout & Physical Fitness**

- **Step Tracking** - Real-time step counter using Android sensors.
- **Smart Workout Recommendations** - AI-selected videos based on calorie balance:
  - **High Surplus** → Fat burning, HIIT (intense)
  - **Low Intake** → Recovery, Yoga, Mobility (gentle)
  - **Maintenance** → Yoga, Pilates, Flexibility (balanced wellness)

**Features:**
- 9 recommendation scenarios.
- Dynamic calorie-based adaptation.
- **Period-Aware Adaptation**: Specifically for female users, the AI tone and intensity suggestions shift during menstrual cycles.
- Personalized AI messaging.
- YouTube video integration.
- Progress tracking (Start/Complete buttons).
- Total duration calculation (45 min = 3 × 15 min).

**Technologies:**
- Repository Pattern for data management
- Android Sensors (Step Counter)
- YouTube Deep Linking

**Files:**
- `WorkoutDashboardActivity.kt`
- `WorkoutVideoRepository.kt`
- `StepManager.kt`

---

### 3. **AI Diet & Nutrition System**

The "Crown Jewel" of SwasthyaMitra. A closed-loop nutritionist that adapts daily.

- **Direct Grounding**: AI recommendations are derived from a local database of 1,000+ Indian food items.
- **Metabolic Math**: Uses Mifflin-St Jeor formula for precise TDEE targets.
- **Adaptive Context**: Reacts to lifestyle, weight plateaus, and even Indian Festivals.
- **Interactive Plans**: "Regenerate" specific meals or provide "Ate/Skipped" feedback.

**Features:**
- 5-meal daily plans (Breakfast, Lunch, Snacks, Dinner, Post-Workout).
- Barcode Scanning for packaged products.
- Indian Food Focus with localized data.
- Personalized coach messages.

**Technologies:**
- Firebase GenAI SDK (Gemini 2.0 Flash)
- Retrofit (OpenFoodFacts API)
- ML Kit (Barcode Scanning)
- CSV Parsing for grounding

**Files:**
- `AIDietPlanService.kt`
- `MealPlanActivity.kt`
- `FoodLogActivity.kt`
- `BarcodeScannerActivity.kt`
- `IndianFoodRepository.kt`

---

### 4. **Smart Hydration & Reminders**

- **Dynamic Goal Calculation** - Base goal (Weight × 33ml) + Activity/Weather offsets.
- **Smart Reminders** - Notifications that respect your wake/sleep cycle.
- **Quick Logging** - Add water sets (250ml/500ml) from UI or notifications.
- **History Tracking** - Visual progress and time-stamped logs.

**Technologies:**
- Android AlarmManager (setExact)
- BroadcastReceivers (Reboot persistent)

**Files:**
- `HydrationActivity.kt`
- `WaterReminderManager.kt`
- `WaterGoalCalculator.kt`

---

### 5. **Women's Health (Period Mode)**

- **Contextual UI** - One-tap toggle on the homepage.
- **AI Adaptation** - Shifting AI coaching to be supportive and empathetic.
- **Smart Recommendations** - Suggesting iron-rich foods and gentle movements.

**Files:**
- `homepage.kt` (State management)
- `AICoachMessageService.kt`
- `AIExerciseRecommendationService.kt`

---

### 6. **Gamification & Analysis**

- **XP & Levels** - Earn experience points for every healthy action.
- **Visual Analytics** - Interactive graphs for weight, water, and calorie trends.
- **Healthy Challenges** - Step-based rewards and persistence.

**Files:**
- `GamificationActivity.kt`
- `GraphActivity.kt`
- `ChallengesActivity.kt`

---

## 🛠️ Technical Stack Detailed

### **Mobile (Android Native)**
- **Language**: Kotlin 1.9+
- **Minimum SDK**: API 24 (Android 7.0)
- **Target SDK**: API 34 (Android 14)
- **UI Framework**: XML / ViewBinding (Material 3 Design)
- **Asynchrony**: Kotlin Coroutines & Flow

### **Firebase Suite**
| Service | Usage |
| :--- | :--- |
| **Firebase Authentication** | User login/signup with strong security rules |
| **Cloud Firestore** | Real-time database for user data and logs |
| **Firebase GenAI SDK** | Client-side integration with Gemini 2.0 Flash |
| **Firebase Realtime Database** | Sync for challenges, streaks |
| **Firebase App Check** | Security and abuse prevention |
| **Firebase Dynamic Links** | Deep linking for challenges |

### **APIs & Libraries**
- **Google ML Kit**: Barcode detection
- **OpenFoodFacts**: Global nutrition database
- **Retrofit 2**: Networking layer
- **MPAndroidChart**: Data visualization
- **Play Services**: Location & Step Sensors

---

## 📂 Project Directory Structure

```text
app/
├── src/main/
│   ├── java/com/example/swasthyamitra/
│   │   ├── auth/          # Login, Signup, Reset
│   │   ├── ai/            # Gemini Services
│   │   ├── tracking/      # Food, Water, Steps
│   │   ├── models/        # Data Structures
│   │   ├── repository/    # Local & API Access
│   │   └── utils/         # Math, Scheduling
│   ├── assets/            # Indian Foods (CSV/JSON), Exercise DB
│   └── res/               # Layouts, GIFs, Drawables
```

---

## 📈 Key Metrics & Success Criteria

1. **Accuracy**: AI Diet Plans must stay within ±10% of calculate TDEE.
2. **Speed**: Barcode scanning recognition in < 500ms.
3. **Privacy**: 100% user data isolation via Firestore Rules.
4. **Retention**: Daily streaks and XP system to improve user engagement.

---

## 🔮 Future Roadmap

1. **Wearable Integration**: Support for Google Fit/Health Connect.
2. **Community Hub**: Group challenges and healthy recipe sharing.
3. **Recipe Recognition**: AI-based photo analysis for food logging.
4. **Mental Wellness**: Meditation routines and stress tracking.

---

**Prepared for:** Academic Examination Presentation  
**Last Updated:** February 04, 2026  
**Version:** 1.1 (Enhanced Security & GenAI Integration)
