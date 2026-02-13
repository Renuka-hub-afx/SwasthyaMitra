# SwasthyaMitra - FINAL DATABASE SCHEMA

## 🏗️ Architecture: User-Centric Design

All user data is stored under `/users/{userId}/` for:
- ✅ Privacy & Security
- ✅ Easy data isolation
- ✅ Prevent duplicate collections
- ✅ GDPR compliance (easy to delete user data)

---

## 📊 COMPLETE DATABASE STRUCTURE

### ROOT LEVEL: User Profile
**Path:** `/users/{userId}`

```json
{
  "userId": "string (Firebase Auth UID)",
  "name": "string",
  "email": "string",
  "age": "number",
  "gender": "string (Male/Female/Other)",
  "height": "number (cm)",
  "weight": "number (kg)",
  "phoneNumber": "string",
  "selected_avatar_id": "string",
  
  // Health Status
  "isOnPeriod": "boolean",
  "lastPeriodDate": "string (yyyy-MM-dd)",
  
  // Exercise Preferences
  "preferredExerciseTime": "string (Morning/Afternoon/Evening/Night)",
  "currentDailyExercise": {
    "name": "string",
    "targetMuscle": "string",
    "reason": "string",
    "calories": "number",
    "duration": "number"
  },
  "lastExerciseDate": "string (yyyy-MM-dd)",
  
  // Quick Access (Cached)
  "lastMood": "string",
  "lastMoodDate": "string",
  "lastMoodIntensity": "number",
  
  // Timestamps
  "createdAt": "timestamp",
  "updatedAt": "timestamp"
}
```

---

## 📁 SUBCOLLECTIONS (Under /users/{userId}/)

### 1. Goals
**Path:** `/users/{userId}/goals/{goalId}`

```json
{
  "userId": "string",
  "goalType": "string (Weight Loss/Weight Gain/Muscle Gain/Stay Healthy)",
  "targetWeight": "number (kg)",
  "currentWeight": "number (kg)",
  "targetValue": "number (optional)",
  "currentValue": "number (optional)",
  "targetDate": "string (yyyy-MM-dd)",
  "dailyCalories": "number (calculated)",
  "startDate": "timestamp",
  "endDate": "timestamp (0 if active)",
  "isCompleted": "boolean",
  "createdAt": "timestamp"
}
```

**Indexes:**
- `userId` ASC, `createdAt` DESC

---

### 2. Food Logs (7+ Days History)
**Path:** `/users/{userId}/foodLogs/{logId}`

```json
{
  "userId": "string",
  "foodName": "string",
  "mealType": "string (breakfast/lunch/dinner/snack)",
  
  // Nutrition Info
  "calories": "number",
  "protein": "number (grams)",
  "carbs": "number (grams)",
  "fat": "number (grams)",
  "fiber": "number (grams, optional)",
  "sugar": "number (grams, optional)",
  
  // Quantity
  "quantity": "number",
  "unit": "string (grams/ml/pieces/servings)",
  
  // Metadata
  "date": "string (yyyy-MM-dd)",
  "timestamp": "timestamp",
  "source": "string (manual/barcode/ai/photo)",
  "imageUrl": "string (optional)",
  "barcode": "string (optional)",
  "confidence": "number (for AI, 0-1)",
  
  // Notes
  "notes": "string (optional)"
}
```

**Indexes:**
- `userId` ASC, `date` DESC
- `userId` ASC, `timestamp` DESC
- `userId` ASC, `mealType` ASC, `date` DESC

**Retention:** Minimum 7 days (recommended: keep forever for long-term trends)

---

### 3. Exercise Logs
**Path:** `/users/{userId}/exercise_logs/{logId}`

```json
{
  "userId": "string",
  "exerciseName": "string",
  "exerciseType": "string (cardio/strength/flexibility/yoga/sports)",
  
  // Metrics
  "durationMinutes": "number",
  "caloriesBurned": "number",
  
  // Strength Training
  "sets": "number (optional)",
  "reps": "number (optional)",
  "weightKg": "number (optional)",
  
  // Cardio
  "distanceKm": "number (optional)",
  "avgHeartRate": "number (optional, bpm)",
  
  // Metadata
  "date": "string (yyyy-MM-dd)",
  "timestamp": "timestamp",
  "source": "string (ai/manual/auto-tracking)",
  
  // Notes
  "notes": "string (optional)",
  "completedFully": "boolean (default true)"
}
```

**Indexes:**
- `userId` ASC, `date` DESC
- `userId` ASC, `exerciseType` ASC, `date` DESC

---

### 4. Hydration Logs
**Path:** `/users/{userId}/hydration_logs/{logId}`

```json
{
  "userId": "string",
  "amountMl": "number (milliliters)",
  "date": "string (yyyy-MM-dd)",
  "timestamp": "timestamp",
  "source": "string (manual/auto/reminder)",
  "beverageType": "string (water/juice/tea/coffee, optional)"
}
```

**Indexes:**
- `userId` ASC, `date` DESC

**Daily Goal:** 2500ml (adjustable per user)

---

### 5. Weight Logs
**Path:** `/users/{userId}/weightLogs/{logId}`

```json
{
  "userId": "string",
  "weightKg": "number",
  "date": "string (yyyy-MM-dd)",
  "timestamp": "timestamp",
  "bmi": "number (calculated)",
  "notes": "string (optional)"
}
```

**Indexes:**
- `userId` ASC, `date` ASC

---

### 6. User Preferences (AI Learning)
**Path:** `/users/{userId}/user_preferences/{preferenceId}`

**Note:** Usually only one document named "default"

```json
{
  "userId": "string",
  
  // Dietary Restrictions
  "dietaryRestrictions": ["vegetarian", "vegan", "gluten-free", "dairy-free"],
  
  // Dislikes (prevents repetitive suggestions)
  "dislikedFoods": ["broccoli", "mushrooms", "idli"],
  
  // Allergies
  "allergies": ["nuts", "shellfish", "soy"],
  
  // Preferences
  "preferredCuisine": ["Indian", "Italian", "Chinese", "Mexican"],
  "spiceLevel": "string (mild/medium/hot)",
  
  // Meal Timings
  "mealTimings": {
    "breakfast": "08:00",
    "lunch": "13:00",
    "dinner": "20:00"
  },
  
  // Budget
  "budgetPerMeal": "number (INR, optional)",
  
  "updatedAt": "timestamp"
}
```

**Purpose:** Powers AI meal recommendations to provide VARIETY

---

### 7. Meal Feedback (AI Learning)
**Path:** `/users/{userId}/meal_feedback/{feedbackId}`

```json
{
  "userId": "string",
  "mealName": "string",
  "liked": "boolean",
  "timestamp": "timestamp",
  "date": "string (yyyy-MM-dd)",
  "reason": "string (optional: too spicy/bland/expensive/unhealthy)",
  "willEatAgain": "boolean"
}
```

**Purpose:** 
- AI learns user taste preferences
- Prevents suggesting disliked meals
- Improves recommendations over time

---

### 8. Mood Logs
**Path:** `/users/{userId}/mood_logs/{logId}`

```json
{
  "userId": "string",
  "mood": "string (Happy/Calm/Tired/Sad/Stressed)",
  "intensity": "number (1-5)",
  "energy": "number (1-5)",
  "suggestion": "string (AI-generated)",
  "date": "string (yyyy-MM-dd)",
  "timestamp": "timestamp",
  
  // Context
  "triggers": ["work", "family", "health", "sleep"],
  "notes": "string (optional)"
}
```

**Indexes:**
- `userId` ASC, `date` DESC

---

### 9. Sleep Logs
**Path:** `/users/{userId}/sleep_logs/{logId}`

```json
{
  "userId": "string",
  "type": "string (sleep_start/sleep_end)",
  "timestamp": "timestamp",
  "date": "string (yyyy-MM-dd)",
  "source": "string (auto/manual)",
  "durationHours": "number (calculated for sleep_end)",
  "quality": "string (poor/fair/good/excellent, optional)"
}
```

---

### 10. Period Logs (Women's Health)
**Path:** `/users/{userId}/period_logs/{logId}`

```json
{
  "userId": "string",
  "startDate": "string (yyyy-MM-dd)",
  "endDate": "string (yyyy-MM-dd, optional)",
  "timestamp": "timestamp",
  "flow": "string (light/medium/heavy)",
  "symptoms": ["cramps", "fatigue", "mood swings", "headache"],
  "notes": "string (optional)"
}
```

---

### 11. Activity Logs (Auto-Tracking)
**Path:** `/users/{userId}/activity_logs/{logId}`

```json
{
  "userId": "string",
  "activityType": "string (Walking/Running/Cycling/Sedentary)",
  "durationMinutes": "number",
  "steps": "number (optional)",
  "distanceMeters": "number (optional)",
  "caloriesBurned": "number",
  "date": "string (yyyy-MM-dd)",
  "timestamp": "timestamp",
  "source": "string (auto_sensor/google_fit)",
  "confidence": "number (0-1)"
}
```

---

### 12. Lifestyle
**Path:** `/users/{userId}/lifestyle/{lifestyleId}`

**Note:** Usually only one document named "current"

```json
{
  "userId": "string",
  "activityLevel": "string (sedentary/lightly_active/moderately_active/very_active)",
  "occupation": "string (optional)",
  "sleepHours": "number (average per night)",
  "stressLevel": "string (low/medium/high)",
  "smokingStatus": "string (never/former/current)",
  "alcoholConsumption": "string (never/occasionally/regularly)",
  "updatedAt": "timestamp"
}
```

---

### 13. Health Metrics
**Path:** `/users/{userId}/health_metrics/{metricId}`

```json
{
  "userId": "string",
  "metricType": "string (blood_pressure/heart_rate/glucose/cholesterol)",
  
  // Blood Pressure
  "systolic": "number (optional, mmHg)",
  "diastolic": "number (optional, mmHg)",
  
  // Heart Rate
  "bpm": "number (optional)",
  
  // Blood Glucose
  "glucoseMgDl": "number (optional)",
  
  // Cholesterol
  "totalCholesterol": "number (optional, mg/dL)",
  "ldl": "number (optional)",
  "hdl": "number (optional)",
  
  "date": "string (yyyy-MM-dd)",
  "timestamp": "timestamp",
  "notes": "string (optional)"
}
```

---

### 14. Recommendations (AI Generated)
**Path:** `/users/{userId}/recommendations/{recommendationId}`

```json
{
  "userId": "string",
  "type": "string (meal/exercise/wellness)",
  "title": "string",
  "description": "string",
  "reason": "string (why this recommendation)",
  "confidence": "number (0-1)",
  "expiresAt": "timestamp",
  "status": "string (pending/accepted/rejected)",
  "createdAt": "timestamp"
}
```

---

### 15. Streaks (Gamification)
**Path:** `/users/{userId}/streaks/{streakId}`

**Note:** Usually only one document named "current"

```json
{
  "userId": "string",
  "currentStreak": "number (days)",
  "longestStreak": "number (days)",
  "lastActivityDate": "string (yyyy-MM-dd)",
  "totalDaysActive": "number",
  "badgesEarned": ["7_day_warrior", "hydration_master", "fitness_freak"],
  "points": "number",
  "level": "number"
}
```

---

### 16. Challenges
**Path:** `/users/{userId}/challenges/{challengeId}`

```json
{
  "userId": "string",
  "challengeType": "string (step_goal/water_intake/weight_loss/workout_streak)",
  "targetValue": "number",
  "currentValue": "number",
  "startDate": "string (yyyy-MM-dd)",
  "endDate": "string (yyyy-MM-dd)",
  "status": "string (active/completed/failed)",
  "reward": "string (badge/points)",
  "createdAt": "timestamp"
}
```

---

### 17. Safety Contacts
**Path:** `/users/{userId}/safety_contacts/{contactId}`

```json
{
  "userId": "string",
  "name": "string",
  "phoneNumber": "string",
  "relationship": "string (parent/spouse/friend/emergency)",
  "isPrimary": "boolean",
  "createdAt": "timestamp"
}
```

---

### 18. Calorie Logs (Daily Summary)
**Path:** `/users/{userId}/calorie_logs/{logId}`

**Note:** One document per day for quick dashboard access

```json
{
  "userId": "string",
  "date": "string (yyyy-MM-dd)",
  
  // Intake (from foodLogs)
  "caloriesIn": "number",
  "proteinIn": "number",
  "carbsIn": "number",
  "fatIn": "number",
  
  // Burned (from exercise_logs + BMR)
  "caloriesOut": "number",
  "bmrCalories": "number (base metabolic rate)",
  "exerciseCalories": "number",
  
  // Net
  "netBalance": "number (caloriesIn - caloriesOut)",
  "goalCalories": "number",
  "goalStatus": "string (deficit/surplus/maintenance)",
  
  "updatedAt": "timestamp"
}
```

**Purpose:** Pre-calculated daily summary for fast dashboard loading

---

### 19. Progress (Historical Snapshots)
**Path:** `/users/{userId}/progress/{progressId}`

**Note:** Weekly/monthly snapshots

```json
{
  "userId": "string",
  "periodType": "string (weekly/monthly)",
  "startDate": "string (yyyy-MM-dd)",
  "endDate": "string (yyyy-MM-dd)",
  
  // Weight Progress
  "startWeight": "number",
  "endWeight": "number",
  "weightChange": "number",
  
  // Averages
  "avgCaloriesPerDay": "number",
  "avgExerciseMinutesPerDay": "number",
  "avgWaterIntakeMl": "number",
  "avgSleepHours": "number",
  
  // Totals
  "totalExercises": "number",
  "totalSteps": "number",
  "totalActiveCaloriesBurned": "number",
  
  // Consistency
  "daysActive": "number",
  "foodLogConsistency": "number (percentage)",
  "exerciseConsistency": "number (percentage)",
  
  "createdAt": "timestamp"
}
```

---

### 20. Workouts (Planned/Custom)
**Path:** `/users/{userId}/workouts/{workoutId}`

```json
{
  "userId": "string",
  "workoutName": "string",
  "exercises": [
    {
      "name": "string",
      "sets": "number",
      "reps": "number",
      "restSeconds": "number"
    }
  ],
  "totalDurationMinutes": "number",
  "difficulty": "string (beginner/intermediate/advanced)",
  "targetMuscles": ["chest", "back", "legs"],
  "createdAt": "timestamp",
  "lastPerformed": "timestamp (optional)"
}
```

---

## 🌐 GLOBAL COLLECTIONS (Root Level - Read Only)

### Food Database
**Path:** `/foodDatabase/{foodId}`

```json
{
  "foodName": "string",
  "barcode": "string (optional)",
  "calories": "number (per 100g)",
  "protein": "number",
  "carbs": "number",
  "fat": "number",
  "fiber": "number",
  "category": "string (vegetables/fruits/grains/protein)",
  "servingSize": "number",
  "servingUnit": "string"
}
```

**Access:** Read-only for all authenticated users

---

### Exercise Database
**Path:** `/exerciseDatabase/{exerciseId}`

```json
{
  "exerciseName": "string",
  "exerciseType": "string (cardio/strength/flexibility/yoga)",
  "targetMuscles": ["chest", "triceps"],
  "difficulty": "string (beginner/intermediate/advanced)",
  "equipmentNeeded": ["dumbbells", "bench"],
  "caloriesPerMinute": "number (average)",
  "instructions": "string",
  "videoUrl": "string (optional)",
  "gifUrl": "string (optional)"
}
```

**Access:** Read-only for all authenticated users

---

### Festival Calendar
**Path:** `/festivalCalendar/{eventId}`

```json
{
  "festivalName": "string",
  "date": "string (yyyy-MM-dd)",
  "region": "string (India/North/South/East/West)",
  "specialFoods": ["pongal", "kheer", "ladoo"],
  "healthTips": "string"
}
```

**Access:** Read-only for all authenticated users

---

### Recipe Database
**Path:** `/recipeDatabase/{recipeId}`

```json
{
  "recipeName": "string",
  "cuisine": "string (Indian/Italian/Chinese)",
  "ingredients": ["rice", "dal", "spices"],
  "calories": "number (per serving)",
  "protein": "number",
  "carbs": "number",
  "fat": "number",
  "cookingTimeMinutes": "number",
  "difficulty": "string (easy/medium/hard)",
  "instructions": "string",
  "imageUrl": "string (optional)"
}
```

**Access:** Read-only for all authenticated users

---

## 🔒 SECURITY RULES SUMMARY

```
✅ Users can ONLY access their own data under /users/{userId}/
✅ Global databases (food, exercise, recipes) are READ-ONLY
❌ Users CANNOT access other users' data
❌ Users CANNOT write to global databases
❌ Root-level user collections are DENIED (prevents duplicates)
```

---

## 📈 REQUIRED FIRESTORE INDEXES

Create these composite indexes in Firebase Console:

1. **Food Logs:**
   - Collection: `foodLogs`
   - Fields: `userId` (Ascending), `date` (Descending)

2. **Exercise Logs:**
   - Collection: `exercise_logs`
   - Fields: `userId` (Ascending), `date` (Descending)

3. **Weight Logs:**
   - Collection: `weightLogs`
   - Fields: `userId` (Ascending), `date` (Ascending)

4. **Hydration Logs:**
   - Collection: `hydration_logs`
   - Fields: `userId` (Ascending), `date` (Descending)

5. **Mood Logs:**
   - Collection: `mood_logs`
   - Fields: `userId` (Ascending), `date` (Descending)

**Note:** Most indexes will be auto-created when Firebase detects the query pattern.

---

## ✅ SCHEMA VALIDATION CHECKLIST

Before deploying to production:

- [ ] All collections are under `/users/{userId}/`
- [ ] No duplicate root-level collections (e.g., `foodLogs` at root)
- [ ] user_preferences document exists for each user
- [ ] meal_feedback documents exist (for AI variety)
- [ ] Food logs have minimum 7 days retention
- [ ] All userId fields match Firebase Auth UID
- [ ] Timestamps are using Firebase Timestamp type
- [ ] Dates are in yyyy-MM-dd format
- [ ] Required indexes are created
- [ ] Firebase rules deployed and tested

---

## 🎯 DATA RETENTION POLICY

| Collection | Retention | Reason |
|------------|-----------|--------|
| foodLogs | Forever (min 7 days) | Long-term nutrition trends |
| exercise_logs | Forever | Progress tracking |
| weightLogs | Forever | Weight journey |
| hydration_logs | 30 days | Recent patterns |
| mood_logs | 90 days | Mental health trends |
| activity_logs | 30 days | Auto-tracking history |
| meal_feedback | Forever | AI learning data |
| user_preferences | Forever | Persistent preferences |
| streaks | Forever | Gamification |

**Manual Cleanup:** Users can delete old data from Profile → Settings → Clear History

---

## 📊 DATABASE SIZE ESTIMATION

For 1000 active users over 1 year:

- Food Logs: ~1,095,000 documents (3 meals/day × 365 days × 1000 users)
- Exercise Logs: ~365,000 documents (1/day × 365 × 1000)
- Hydration Logs: ~730,000 documents (2/day × 365 × 1000)
- Other Collections: ~50,000 documents

**Total:** ~2.24 million documents
**Storage:** ~5-10 GB
**Cost:** Within Firebase free tier for small apps, ~$25-50/month for medium apps

---

## 🚀 MIGRATION FROM DOUBLE/DUPLICATE SCHEMA

If you have data in both root-level AND user-level collections:

```javascript
// Firebase Console → Firestore → Run in Cloud Functions

const admin = require('firebase-admin');
const db = admin.firestore();

async function migrateFoodLogs() {
  // Get all root-level foodLogs
  const rootLogs = await db.collection('foodLogs').get();
  
  for (const doc of rootLogs.docs) {
    const data = doc.data();
    const userId = data.userId;
    
    // Move to user-level collection
    await db.collection('users')
      .doc(userId)
      .collection('foodLogs')
      .doc(doc.id)
      .set(data);
    
    // Delete root-level document
    await doc.ref.delete();
  }
  
  console.log(`Migrated ${rootLogs.size} food logs`);
}
```

**Run for all collections:** foodLogs, exercise_logs, weightLogs, etc.

---

**END OF SCHEMA DOCUMENTATION**

**Status:** ✅ Complete & Validated
**Last Updated:** February 12, 2026
**Database Version:** 2.0 (User-Centric)

