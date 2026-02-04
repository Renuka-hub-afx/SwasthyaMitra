# Feature: Women's Health & Wellness (Period Mode)

## 🌸 Overview

SwasthyaMitra includes a specialized "Period Mode" designed to provide supportive health adjustments for female users. When activated, the entire app context shifts—including AI tone, exercise intensity recommendations, and nutritional focus.

---

## 🛠️ Implementation Details

### **1. Homepage Integration**

The feature is accessible via a primary toggle on the `homepage.kt`.

- **Persistence**: The state is stored in Firestore as a `periodMode` Boolean within the specific User document.
- **UI Feedback**: Activating the mode displays a specialized greeting and modifies the aesthetic vibe of the dashboard.

### **2. AI Coaching Adaptation**

The `AICoachMessageService.kt` and `AIDietPlanService.kt` listen to this flag.

- **Empathetic Tone**: The AI shifts from "Push harder" to "Listen to your body" and "Focus on recovery."
- **Focus Areas**: The prompts emphasize:
  - **Iron & Magnesium**: Prioritizing foods like spinach, nuts, and dark chocolate.
  - **Hydration**: Increasing water intake nudges.

### **3. Exercise Modification**

When Period Mode is **ACTIVE**, the `AIExerciseRecommendationService.kt` ignores high-intensity HIIT prompts.

- **Filtered Database**: The AI is instructed to select ONLY restorative yoga, gentle stretching, or slow walks from the `exercises.json` database.

---

## 🧠 Core Principles

- **Empowerment**: Providing data-driven comfort during a biological shift.
- **Privacy**: The mode is optional and the data remains encrypted under the user's UID.
- **Holistic Care**: Bridging the gap between general fitness and specialized biological needs.

---

## ✅ Deployment Status

- **Status**: ✅ ACTIVE
- **Platform**: Android Native (Kotlin)
- **Data Source**: Cloud Firestore
- **AI Engine**: Firebase GenAI SDK (Gemini 1.5/2.0 Flash)
