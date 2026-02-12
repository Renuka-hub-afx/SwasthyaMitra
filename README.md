# SwasthyaMitra 🥗
### Native Android Health & Nutrition Tracking for India

SwasthyaMitra (SWASTHYA: Health | MITRA: Friend) is a comprehensive healthcare application designed to simplify nutrition tracking and goal management. Built with a focus on Indian dietary habits, it leverages Generative AI to provide personalized coaching and lifestyle recommendations.

---

## ✨ Key Features

### 🔐 Secure Identity
- **Firebase Auth Integration**: Secure login via Email/Password and Google Sign-In.
- **Privacy First**: User-specific data isolation using Firestore Security Rules.

### ⚖️ Smart Metabolic Engine
- **Automated Calculations**: Personalized BMR and TDEE calculation based on the **Mifflin-St Jeor Equation**.
- **Goal-Oriented**: Tailored plans for Weight Loss, Muscle Gain, or Maintenance.

### 🍛 Indian Food Tracking
- **Extensive Database**: Grounded in a local 1000+ item Indian Food collection.
- **Macro Breakdown**: Real-time tracking of Protein, Carbs, and Fats.

### 🧠 AI Diet Plan (Gemini 2.0 Flash)
- **Generative Coaching**: Personalized meal recommendations using **Firebase GenAI SDK**.
- **Context-Aware**: Adapts to exercise intensity, weight plateaus, Indian Festivals, and specialized **Period Mode** logic.
- **Interactive Plans**: "Regenerate" specific meals or provide "Ate/Skipped" feedback to the AI.

### 💧 Hydration & Reminders
- **Smart Goals**: Water targets calculated based on body weight.
- **Active Notifications**: Timely reminders scheduled during your active hours using Android `AlarmManager`.

### 🛠️ Advanced Tools
- **Barcode Scanner**: Log packaged foods instantly via the **OpenFoodFacts API**.
- **Goal Visualization**: Interactive progress tracking and circular intake meters.

---

## 🛠️ Technology Stack

| Pillar | Technology |
| :--- | :--- |
| **Language** | Kotlin |
| **UI Framework** | Android XML (Material 3 Inspiration) |
| **Backend** | Firebase (Auth with Email Validation, Firestore, Cloud Functions) |
| **AI Engine** | Firebase GenAI SDK - Gemini 2.0 Flash |
| **APIs** | OpenFoodFacts Barcode |
| **Frameworks** | Retrofit (Networking), Coroutines (Async), ViewBinding |

---

## 📖 Documentation

Comprehensive documentation is available in the `docs` directory:

### Core
- **[Onboarding & Metabolic Engine](docs/ONBOARDING.md)**: Logic for BMR/TDEE and goal setting.
- **[Authentication](docs/AUTH.md)**: Login flows and user management.
- **[Database Schema](docs/DATABASE_SCHEMA.md)**: Full Firestore structure.
- **[Dashboard](docs/DASHBOARD.md)**: Central navigation hub.

### Features
- **[Food Logging](docs/FOOD_LOGGING.md)**: Macros, Barcode, and Indian Food DB.
- **[Workouts](docs/WORKOUTS.md)**: Exercise tracking and recommendations.
- **[Hydration](docs/HYDRATION.md)**: Water intake tracking.
- **[AI Features](docs/AI_FEATURES.md)**: Smart Diet, Pantry, and Visual Analysis.
- **[Wellness](docs/WELLNESS.md)**: Mood, Safety, and Posture Coach.
- **[Analytics](docs/INSIGHTS.md)**: Insights and weight progress.
- **[Gamification](docs/GAMIFICATION.md)**: XP, Streaks, Shields, and Levels.
- **[Social](docs/SOCIAL.md)**: Challenges and sharing.

---

## 🚀 Getting Started

1. **Clone the project** locally.
2. **Firebase Setup**:
    - Download `google-services.json` from your Firebase Console.
    - Place it in the `/app` directory.
    - Deploy the rules found in `firestore.rules`.
3. **Build & Run**:
    - Open the project in **Android Studio**.
    - Sync Gradle and Run on an emulator or physical device (API 24+).

---

## 🤝 Contribution
For team members, please refer to the [Project_Master_Guide.md](DOCS/Project_Master_Guide.md) to understand the current implementation phase before starting new feature development.
