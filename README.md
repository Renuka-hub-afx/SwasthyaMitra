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
- **Generative Coaching**: Personalized meal recommendations using **Google Vertex AI**.
- **Context-Aware**: Adapts to exercise intensity, weight plateaus, and even Indian Festivals.
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
| **Backend** | Firebase (Auth, Firestore) |
| **AI Engine** | Vertex AI - Gemini 2.0 Flash |
| **APIs** | OpenFoodFacts Barcode |
| **Automation** | Android AlarmManager, Coroutines |

---

## 📖 Documentation

The project features a modular documentation system located in the `DOCS` directory:

- **[Master Guide](DOCS/Project_Master_Guide.md)**: Overview of implementation phases and project structure.
- **[Firebase Schema](DOCS/Firebase_Schema.md)**: Complete database collection and attribute reference.
- **Feature Deep Dives**:
    - [Architecture & AI Logic](DOCS/Features/AI_Diet_System.md)
    - [Metabolic Calculations](DOCS/Features/Onboarding_and_Goals.md)
    - [Nutrition Engine](DOCS/Features/Nutrition_Tracking.md)
    - [Hydration System](DOCS/Features/Water_Tracker.md)
    - [Utility Tools](DOCS/Features/Utility_Tools.md)
    - [Workout & Fitness](DOCS/Features/Workout_and_Fitness.md)
    - [WhatsApp Integration Plan](DOCS/Features/WhatsApp_Integration_Plan.md)

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
