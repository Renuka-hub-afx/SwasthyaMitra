# SwasthyaMitra Project Manual: Native Android Healthcare System

## 🌟 Overview
SwasthyaMitra is a native Android application built in Kotlin, designed to provide comprehensive health and nutrition tracking tailored for the Indian context. It integrates Generative AI to offer personalized meal plans while ensuring data security via Firebase.

---

## 🏗️ Technical Architecture
- **Platform**: Android Native (API 24+)
- **Language**: Kotlin
- **UI Architecture**: XML View System (Material Design) with MVVM patterns.
- **Backend Stack**:
    - **Authentication**: Firebase Auth (Email/Google)
    - **Database**: Firebase Cloud Firestore
    - **Storage**: (Phase 3) For user photos.
- **AI Capability**: Vertex AI (Gemini 2.0 Flash) with CSV Grounding.
- **External Dependencies**: OpenFoodFacts API, ML Kit.

---

## 📈 Implementation Phases

### Phase 1: Foundation (Secure Identity)
Built the core authentication and profile management system.
- Secure Login/Signup with Firebase.
- User Profile initialization.
- **Docs**: [Authentication.md](Features/Authentication.md)

### Phase 2: Metabolic Engine & Data Layer
Built the core mathematical engine and the offline food database.
- BMR and TDEE calculations using Mifflin-St Jeor.
- Integration of the local 1000+ Indian Food CSV dataset.
- Goal setting (Weight Loss/Gain/Maintenance).
- **Docs**: [Onboarding_and_Goals.md](Features/Onboarding_and_Goals.md), [Nutrition_Tracking.md](Features/Nutrition_Tracking.md)

### Phase 3: Smart Recommendation System (GenAI)
Integrated Generative AI to elevate user experience from "tracking" to "coaching."
- Vertex AI / Gemini 2.0 Flash integration.
- Adaptive logic based on exercise intensity and weight trends.
- Festival-aware meal planning.
- **Docs**: [AI_Diet_System.md](Features/AI_Diet_System.md)

### Phase 4: Hydration & Notification Ecosystem
Added specialized tracking for water intake and active reminders.
- Personalized water requirement calculation.
- Smart notification scheduling using Android AlarmManager.
- UX refinements for the Homepage dashboard.
- **Docs**: [Water_Tracker.md](Features/Water_Tracker.md)

### Phase 5: Advanced Input & Automation
Implemented tools to make data entry seamless.
- Barcode scanning for packaged foods.
- Photo capture for meals.
- Coach feedback loop.
- **Docs**: [Utility_Tools.md](Features/Utility_Tools.md)

---

## 📦 Database & Security
The system uses a strictly hierarchical Firestore structure guarded by robust Security Rules.
- **Schema Reference**: [Firebase_Schema.md](Firebase_Schema.md)

---

## 🚀 Setup & Launch
1. Ensure `google-services.json` is placed in the `app/` directory.
2. Deploy Firestore Rules from `firestore.rules`.
3. Build using Android Studio (Arctic Fox or newer).
