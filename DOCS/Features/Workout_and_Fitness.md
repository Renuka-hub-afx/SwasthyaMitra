# Feature: Workout & Fitness Tracking

## 🏋️ Overview
The Workout & Fitness module is a reactive system that tracks physical movement and provides intelligent exercise recommendations based on the user's daily caloric balance and weight goals.

---

## 🛠️ Technology Used
- **Android Sensor API**: Specifically uses the `TYPE_STEP_COUNTER` hardware sensor for low-power, background step tracking.
- **Firebase Realtime Database**: Selected for its lightning-fast sync speeds to handle high-frequency XP updates and live streaks.
- **YouTube Deep Linking**: Uses standard Android Intents to launch curated fitness content directly in the official YouTube app.
- **Kotlin Coroutines**: Manages asynchronous stats fetching and database writes without blocking the UI thread.
- **SharedPreferences**: Provides a local cache for step deltas to ensure data persistence during app restarts or sensor resets.

---

## 🛠️ File Architecture

### **1. The Hub (UI & Activity)**
- **`WorkoutDashboardActivity.kt`**: The central dashboard for viewing steps, calories burned, and tailored workout videos.
- **`activity_workout_dashboard.xml`**: The layout containing the live step counter, macro status, and video list.

### **2. The Movement Engine (Hardware Integration)**
- **`StepManager.kt`**: A specialized sensor listener that interfaces with the device's physical `STEP_COUNTER` hardware.

### **3. The Recommendation Layer**
- **`WorkoutVideoRepository.kt`**: The logic engine that filters a library of 50+ curated workout videos based on the user's current metabolic state.
- **`WorkoutVideo.kt`**: Data model for exercise sessions (Duration, Intensity, Category).

### **4. Persistence & Gamification**
- **Firebase Realtime Database**: Used for high-frequency activity logging and streak tracking.
- **`FitnessData.kt`**: Model for storing XP, levels, and workout history.

---

## 🧠 Core Logic & Implementation

### **1. Hardware Step Tracking**
The app uses the `TYPE_STEP_COUNTER` sensor for high accuracy and low battery consumption. It handles critical edge cases like device reboots.

```kotlin
// From StepManager.kt - Reboot & Delta Logic
override fun onSensorChanged(event: SensorEvent) {
    val currentSensorValue = event.values[0]
    
    // Reboot Detection: If current value < last stored value, sensor reset to 0
    if (currentSensorValue < lastSensorValue) {
        lastSensorValue = 0f
    }

    val delta = (currentSensorValue - lastSensorValue).toInt()
    if (delta > 0) {
        dailySteps += delta
        // Calorie Estimation: ~0.04 kcal per step
        val caloriesBurned = dailySteps * 0.04
        onStepUpdate(dailySteps, caloriesBurned)
    }
}
```

### **2. Dynamic "AI" Recommendations**
The dashboard doesn't just show videos; it analyzes your `netCalories` (Consumed - Burned) vs your `TargetBase`.

```kotlin
// From WorkoutDashboardActivity.kt
private fun updateAIRecommendation() {
    val netCalories = consumedCalories - burnedCalories
    val diff = netCalories - targetBase

    val calorieStatus = when {
        diff > 100 -> "High"      // Suggest High Intensity
        diff < -100 -> "Low"      // Suggest Recovery/Flexibility
        else -> "Balanced"        // Suggest Maintenance
    }
    
    // Fetch videos specifically for this status and user goal
    val videos = WorkoutVideoRepository.getSmartRecommendation(goalType, calorieStatus, "Moderate")
}
```

### **3. Completion & Reward System**
To prevent "cheating," a workout is only marked complete if the user actually starts the video.

```kotlin
// Logic flow for completion
btnStart.setOnClickListener {
    startedVideoIds.add(video.videoId)
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("youtube_url")))
}

btnComplete.setOnClickListener {
    if (startedVideoIds.contains(video.videoId)) {
         handleWorkoutCompletion(video) // Awards +100 XP
    }
}
```

---

## ✅ Feature Set
- **Live Step Sync**: Real-time step and calorie burn updates on every movement.
- **Context-Aware Recommendations**: High-calorie days trigger HIIT/Cardio suggestions, while low-calorie days suggest Yoga/Mobility.
- **Streak Tracking**: Encourages daily consistency through a Realtime Database sync.
- **Video Integration**: Direct deep-linking to curated YouTube fitness content.
- **XP System**: Integrates with the `GamificationActivity` to level up the user based on physical effort.
