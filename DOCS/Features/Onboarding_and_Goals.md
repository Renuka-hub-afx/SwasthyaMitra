# Feature: Onboarding & Goal Setting (BMR/TDEE)

## 🏁 Overview
The Onboarding & Goal Setting feature is the scientific backbone of SwasthyaMitra. It transforms raw physical data (Height, Weight, Age) into a personalized "Metabolic Blueprint" (BMR, TDEE, and Daily Caloric Target). This blueprint is what powers the AI Diet system to ensure every meal recommendation is biologically accurate.

---

## 🛠️ File Architecture
The onboarding follows a strict 3-stage sequence, ensuring no user enters the dashboard without a valid goal.

### **Stage 1: Physical Profiling**
- **`UserInfoActivity.kt`**: Captures **Height, Weight, and Gender**.
- **Logic**: Instantly calculates and displays **BMI** (Body Mass Index) with color-coded categories (Underweight, Normal, Overweight, Obese) to give the user immediate health feedback.

### **Stage 2: Mission Selection**
- **`InsertGoalActivity.kt`**: Captures the user's primary "Mission":
    - Lose Weight
    - Maintain Weight
    - Gain Muscle
    - General Health

### **Stage 3: Lifestyle & Metabolic Math**
- **`LifestyleActivity.kt`**: This is the "Engine Room". It captures **Activity Level** and **Diet Preferences** to perform the final metabolic calculations.

---

## 🧠 Core Logic & Implementation

### **1. Basal Metabolic Rate (BMR) - The Foundation**
The app implements the **Mifflin-St Jeor Equation**, currently considered the most accurate formula for healthy adults.

```kotlin
// From LifestyleActivity.kt
private fun calculateBMR(weight: Double, height: Double, age: Int, gender: String): Double {
    val baseBMR = (10 * weight) + (6.25 * height) - (5 * age)
    return if (gender.equals("Male", ignoreCase = true)) {
        baseBMR + 5
    } else {
        baseBMR - 161
    }
}
```

### **2. Total Daily Energy Expenditure (TDEE)**
BMR is multiplied by an **Activity Factor** to determine how many calories the user burns just staying alive and moving.

| Activity Level | Multiplier |
| :--- | :--- |
| Sedentary | 1.2 |
| Lightly Active | 1.375 |
| Moderately Active | 1.55 |
| Very Active | 1.725 |

```kotlin
// From LifestyleActivity.kt
private fun calculateTDEE(bmr: Double, activityLevel: String): Double {
    val factor = when (activityLevel) {
        "Sedentary" -> 1.2
        "Lightly Active" -> 1.375
        "Moderately Active" -> 1.55
        "Very Active" -> 1.725
        else -> 1.2
    }
    return bmr * factor
}
```

### **3. Goal-Based Calorie Adjustment**
Finally, the app applies a "Caloric Delta" based on the user's mission to produce the **Daily Target**.

- **Weight Loss**: `-500 kcal` (Calculated to lose ~0.5kg per week safely).
- **Weight Gain**: `+400 kcal` (Optimized for muscle synthesis).
- **Maintenance**: `+0 kcal` (TDEE = Target).

---

## 🔄 State Management & Routing
The application uses a "Sequential Guard" logic in `LoginActivity.kt`. Every time a user logs in, the app checks for profile completeness:

1. **Check Physical Stats**: If missing → `UserInfoActivity`
2. **Check Goal**: If missing → `InsertGoalActivity`
3. **Check Lifestyle**: If missing → `LifestyleActivity`
4. **All Complete**: → `homepage.kt`

---

## ✅ Feature Persistence
- **Firestore Integration**: All metrics (BMR, TDEE, Daily Target) are saved in a high-priority `goals` collection.
- **Dynamic Updates**: If a user updates their weight in the profile, the app can re-trigger these formulas to keep the goals updated as the user progresses.
