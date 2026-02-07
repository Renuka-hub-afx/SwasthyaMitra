# SwasthyaMitra (Health Friend) 🏥🥗

> **AI-Powered Holistic Health & Nutrition App for India**

SwasthyaMitra is a native Android application designed to be your personal digital health companion. Unlike generic trackers, it specifically addresses the **Indian context**—understanding Indian diets (Roti/Sabzi/Dal), festivals, and lifestyle—powered by advanced **Generative AI**.

![Status](https://img.shields.io/badge/Status-Active_Development-green)
![AI](https://img.shields.io/badge/AI-Gemini_2.0_Flash-blue)
![Backend](https://img.shields.io/badge/Backend-Firebase-orange)

---

## 🌟 Key Features (As Built)

### 🤖 AI Health Suite (Powered by Gemini 2.0)
*   **AI Rasoi (Smart Pantry)**: Snap a photo of ingredients 📸 -> Get a healthy Indian recipe instantly.
*   **Personal AI Coach**: A chat-like interface that analyzes your daily logs (food, water, steps) and gives hyper-personalized advice (e.g., *"You missed lunch, grab a snack!"*).
*   **Smart Exercise Recs**: Suggests workouts based on your energy levels, period status, and time of day.
*   **AI Diet Plans**: Generates full day meal plans respecting dietary preferences (Veg/Non-Veg/Jain).

### 📊 Core Health Engine
*   **Indian Food Database**: Offline database of 1000+ Indian foods with accurate macros.
*   **Smart Logging**:
    *   **Search**: Fast search for local dishes.
    *   **Barcode Scanner**: Scan packaged foods.
    *   **Voice Logging**: (Planned)
*   **Hydration Tracker**: Smart water log with auto-calculated goals.
*   **Step Tracking**: Native pedometer integration.

### 🌸 Specialized Features
*   **Women's Health (Period Mode)**: Toggles specific logic for nutrition and exercise recommendations during menstruation.
*   **Gamification**: "Duels" to challenge friends and streak tracking.

---

## 🛠️ Technical Stack

*   **OS**: Android (Native Kotlin)
*   **Architecture**: MVVM (Model-View-ViewModel) + Coroutines
*   **Backend**: Firebase (Blaze Plan)
    *   **Auth**: Email/Password + Google Sign-In
    *   **Database**: Cloud Firestore (`renu` database) + Realtime Database
    *   **AI**: Firebase GenAI SDK (Vertex AI/Google AI)
    *   **Storage**: Firebase Storage
*   **Key Libraries**:
    *   `ML Kit` (Image Labeling)
    *   `ZXing` (Barcode Scanning)
    *   `Glide` (Image Loading)
    *   `MPAndroidChart` (Graphs)

---

## 📂 Project Structure

*   `app/src/main/java`: Kotlin Source Code
    *   `ai/`: AI Services (`AIPantryService`, `AICoachMessageService`...)
    *   `ui/`: Activities and Fragments
    *   `data/`: Repositories and Models
*   `DOCS/`: Project Documentation
    *   [AI Suite Details](Features/AI_Suite.md)
    *   [Health Engine Details](Features/Health_Engine.md)
    *   [Database Schema](Technical/Firebase_Schema.md)

---

## 🚀 Getting Started

1.  **Clone the Repo**: `git clone ...`
2.  **Firebase Setup**:
    *   Ensure `google-services.json` is present in `app/`.
    *   Project ID: `swasthyamitra-ded44` (Blaze Plan).
3.  **Build**: Open in Android Studio and hit **Run**.

---
*Developed with ❤️ for a Healthier India.*
