# 🩺 Core Health Engine

The backbone of SwasthyaMitra is its metabolic calculator and tracking database.

## 1. Metabolic Calculations 🧮
*   **BMR (Basal Metabolic Rate)**: Uses Mifflin-St Jeor Equation.
    *   Input: `Weight`, `Height`, `Age`, `Gender`.
*   **TDEE (Total Daily Energy Expenditure)**: BMR × Activity Factor.
*   **Calorie Goals**:
    *   Weight Loss: TDEE - 500 kcal
    *   Weight Gain: TDEE + 300 kcal
    *   Maintenance: TDEE

## 2. Food Database 🍛
*   **Source**: Local CSV dataset (`indian_food_database.csv`) + Firestore User Logs.
*   **Coverage**: Optimized for Indian cuisine (Roti, Dal, Sabzi portions).
*   **Logging Methods**:
    *   **Manual**: Search database.
    *   **Barcode**: Uses ZXing to read EAN codes.

## 3. Hydration Tracker 💧
*   **Goal Calculation**: `Weight (kg) × 35 ml`.
*   **Features**:
    *   Quick add buttons (+250ml).
    *   Daily progress bar.
    *   Smart reminders (AlarmManager).

## 4. Women's Health (Period Mode) 🌸
*   **Toggle**: Available on Homepage for Female users.
*   **Effect**:
    *   **UI**: Adds specific "Period Mode" tag.
    *   **AI**: Alters Coach and Exercise prompts to be supportive/gentle.
    *   **DB**: Updates `isOnPeriod` flag in Firestore `users/{uid}`.

## 5. Step Tracking 👣
*   **Sensor**: Android `STEP_COUNTER` (Hardware Sensor).
*   **Persistence**: Saves daily totals to Firestore `dailyStats`.
