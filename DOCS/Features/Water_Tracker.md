# Feature: Water Tracker & Smart Reminders

## 🌊 Overview

The Water Tracker is a high-precision hydration management system. It calculates a user's biological water needs based on their physical stats and ensures consistency through an intelligent, battery-efficient reminder ecosystem.

---

## 🛠️ File Architecture

The feature is built using a decoupled architecture for maintainability:

### **1. UI & Logic Layer**

- **`HydrationActivity.kt`**: The central controller. Manages user interactions, updates progress bars, and toggles reminders.
- **`activity_hydration.xml`**: The layout containing the circular progress bar, quick-add buttons, and history list.
- **`WaterLogAdapter.kt`**: Bridges the data to the history RecyclerView.

### **2. Notification & Background Service**

- **`WaterReminderManager.kt`**: The "Scheduler". Calculates optimal reminder intervals using Android's `AlarmManager`.
- **`WaterReminderReceiver.kt`**: The "Broadcaster". Wakes up the app to show the notification and handles "Quick Add" actions.

### **3. Mathematical & Data Layer**

- **`WaterGoalCalculator.kt`**: The "Engine". Contains the formulas for metabolic water requirements.
- **`HydrationRepository.kt`**: Negotiates data flow between the UI and Cloud Firestore.
- **`WaterLog.kt`**: The data model representing a single intake event.

---

## 🧠 Core Logic & Algorithms

### **1. Biological Goal Calculation**

The app uses a weight-based formula to determine the daily target.

- **Formula**: `Weight (kg) × 33 ml/kg`
- **Constraints**: Minimum 1500 ml, Maximum 4000 ml.
- **Adjustments**: `+500 ml` for moderate activity, `+1000 ml` for high activity.

```kotlin
// From WaterGoalCalculator.kt
fun calculateDailyGoal(weightKg: Double): Int {
    if (weightKg <= 0) return 1500
    val baseGoal = (weightKg * 33).roundToInt()
    return baseGoal.coerceIn(1500, 4000)
}
```

### **2. Smart Reminder Scheduling**

Instead of simple intervals, the app respects the user's circadian rhythm:

1. It calculates **Active Hours** (Sleep Time - Wake Time).
2. It divides these hours into **2-hour blocks**.
3. It uses `AlarmManager.setExactAndAllowWhileIdle` to ensure notifications fire even in Doze Mode.

### **3. Real-time Progress Tracking**

The app performs real-time aggregation of today's logs from Firestore to update the UI progress bar.

```kotlin
// From HydrationActivity.kt
private fun updateProgressUI() {
    val progress = (currentIntake * 100 / dailyGoal)
    binding.waterProgressBar.progress = progress.coerceAtMost(100)
    binding.tvPercentage.text = "$progress% of daily goal"
}
```

---

## ✅ Implementation Features

- **Quick Logging**: Add 250ml or 500ml with a single tap from the UI or Notification.
- **History Management**: View time-stamped logs for today and previous days with the ability to delete entries.
- **Persistence**: Reminders stay active even after device reboots (using `BOOT_COMPLETED` receiver).
- **Customization**: Users can override the calculated biological goal with their own target volume.
