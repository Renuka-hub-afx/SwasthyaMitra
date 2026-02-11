# 🛠️ Build & Setup Guide

## Prerequisites
1.  **Android Studio**: Ladybug or newer (Recommended).
2.  **JDK**: Version 17.
3.  **Firebase Account**: Access to `swasthyamitra-ded44` project.

## Project Configuration

### 1. Google Services
You must have the `google-services.json` file in `app/`.
*   **Project ID**: `swasthyamitra-ded44`
*   **API Key**: Must be enabled for "Generative Language API" on Google Cloud Console.

### 2. Gradle Setup
*   **Plugins**:
    *   `com.google.gms.google-services`
    *   `com.android.application`
    *   `org.jetbrains.kotlin.android`
*   **Dependencies**:
    *   `firebase-bom`: 33.x.x
    *   `firebase-ai`: For Gemini
    *   `mlkit:image-labeling`: For Food Rec (Local)

## How to Run

1.  **Clean Project**: `Build > Clean Project`.
2.  **Sync Gradle**: Ensure all dependencies download.
3.  **Select Device**: Physical Device recommended (for Camera/Step Sensor).
4.  **Run**: Click the ▶️ Play button.

## Troubleshooting
*   **"API Key not found"**: Check `local.properties` or `google-services.json`.
*   **"Quota Exceeded"**: The Blaze plan trial credits might be exhausted. Check Firebase Console.
