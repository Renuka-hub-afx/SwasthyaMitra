# 📦 Database Schema (As Built)

SwasthyaMitra uses **Cloud Firestore** as the primary database (`renu` named database) and **Realtime Database** for some legacy/sync features.

## 🔥 Cloud Firestore (`renu`)

### `users` (Collection)
User profile and settings.
```json
{
  "uid": "string (Auth ID)",
  "name": "string",
  "email": "string",
  "age": number,
  "gender": "Male" | "Female",
  "height": number (cm),
  "weight": number (kg),
  "isOnPeriod": boolean,
  "goalType": "Weight Loss",
  "dailyCalories": number (Target)
}
```

### `foodLogs` (Collection)
Daily food entries.
```json
{
  "userId": "string",
  "date": "YYYY-MM-DD",
  "foodName": "string",
  "calories": number,
  "protein": number,
  "carbs": number,
  "fat": number,
  "timestamp": number
}
```

### `exerciseLogs` (Collection)
Workouts performed.
```json
{
  "userId": "string",
  "exerciseName": "string",
  "duration": "string",
  "caloriesBurned": number,
  "source": "Manual" | "AI_Recommendation",
  "date": "YYYY-MM-DD"
}
```

### `hydrationLogs` (Collection)
Water intake records.
```json
{
  "userId": "string",
  "amount": number (ml),
  "date": "YYYY-MM-DD",
  "timestamp": number
}
```

### `challenges` & `duels`
Gamification data for multiplayer features.

---

## ⚡ Realtime Database
*   **URL**: `swasthyamitra-ded44-default-rtdb.asia-southeast1.firebasedatabase.app`
*   **Usage**:
    *   `users/{uid}/completionHistory/{date}`: Boolean flag for daily streaks.
    *   Live duel status updates.
