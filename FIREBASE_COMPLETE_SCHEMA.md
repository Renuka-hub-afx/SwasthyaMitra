# SwasthyaMitra - Complete Firebase Schema
## Last Updated: February 10, 2026

---

## 🚨 CRITICAL ISSUES IDENTIFIED & FIXED

### Issue 1: Duplicate Firestore Instances
- ❌ **Before**: Mixed usage of default and named instance "renu"
- ✅ **Fixed**: All code now uses **default instance only**

### Issue 2: Duplicate foodLogs Storage
- ❌ **Before**: Rules defined both `foodLogs` (top-level) AND `users/{userId}/foodLogs` (subcollection)
- ✅ **Fixed**: Using **subcollection only** (`users/{userId}/foodLogs`)

### Issue 3: Duplicate Exercise Logs
- ❌ **Before**: Both `exerciseLogs` and `exercise_logs` in rules
- ✅ **Fixed**: Standardized to **`exercise_logs`** (with underscore)

---

## 📊 FINAL DATABASE STRUCTURE

### **Architecture Pattern**
```
SwasthyaMitra Firestore
├── users/{userId}                    [User profiles]
│   ├── foodLogs/{logId}             [Subcollection - User's food logs]
│   ├── exerciseLogs/{logId}         [Subcollection - NOT USED, REMOVE]
│   ├── weightLogs/{logId}           [Subcollection - NOT USED, REMOVE]
│   └── hydrationLogs/{logId}        [Subcollection - NOT USED, REMOVE]
├── goals/{goalId}                    [User goals & metabolic data]
├── exercise_logs/{logId}             [Top-level exercise logs]
├── weightLogs/{logId}                [Top-level weight logs]
├── waterLogs/{logId}                 [Top-level hydration logs]
├── meal_feedback/{feedbackId}        [AI diet feedback]
├── user_preferences/{userId}         [AI learned preferences]
├── ai_generated_plans/{planId}       [AI diet plans]
├── workouts/{workoutId}              [Exercise library - READ ONLY]
├── festivalCalendar/{eventId}        [Indian festivals - READ ONLY]
├── challenges/{challengeId}          [Multiplayer challenges]
├── saved_recipes/{userId}            [User's saved recipes]
│   └── recipes/{recipeId}           [Subcollection - Individual recipes]
└── notifications/{notifId}           [User notifications]
```

---

## 📋 DETAILED COLLECTION SCHEMAS

### 1. **users/{userId}**
**Purpose**: Core user profile  
**Document ID**: Firebase Auth UID  
**Write**: Owner only | **Read**: Owner only

```json
{
  "userId": "abc123xyz",
  "name": "Priya Sharma",
  "email": "priya@example.com",
  "phoneNumber": "+919876543210",
  "age": 28,
  "gender": "Female",
  "height": 165,
  "weight": 60.5,
  "targetWeight": 55.0,
  "eatingPreference": "Vegetarian",
  "allergies": ["Nuts", "Dairy"],
  "activityLevel": "Moderate",
  "wakeTime": "07:00",
  "sleepTime": "23:00",
  "waterGoal": 2500,
  "availableExerciseTime": "30-45 mins",
  "preferredExerciseTime": "Morning",
  "isOnPeriod": false,
  "createdAt": 1739174400000,
  "updatedAt": 1739260800000
}
```

**Fields**:
- `userId` (String): Matches document ID
- `name` (String): Display name
- `email` (String): Login email
- `phoneNumber` (String): With country code
- `age` (Number): User's age
- `gender` (String): "Male" | "Female"
- `height` (Number): In cm
- `weight` (Number): Current weight in kg
- `targetWeight` (Number): Goal weight
- `eatingPreference` (String): "Vegetarian" | "Non-Vegetarian" | "Vegan" | "Eggetarian"
- `allergies` (Array): List of allergens to avoid
- `activityLevel` (String): "Sedentary" | "Lightly Active" | "Moderate" | "Very Active"
- `wakeTime` (String): HH:mm format (24-hour)
- `sleepTime` (String): HH:mm format
- `waterGoal` (Number): Daily water goal in ml
- `availableExerciseTime` (String): Free text
- `preferredExerciseTime` (String): "Morning" | "Evening" | "Night"
- `isOnPeriod` (Boolean): Female users only
- `createdAt` (Timestamp): Account creation
- `updatedAt` (Timestamp): Last profile update

---

### 2. **users/{userId}/foodLogs/{logId}** ⭐ SUBCOLLECTION
**Purpose**: User's food consumption logs  
**Parent**: users document  
**Write**: Owner only | **Read**: Owner only

```json
{
  "userId": "abc123xyz",
  "foodName": "Masala Dosa",
  "calories": 350,
  "protein": 12.5,
  "carbs": 48.0,
  "fat": 8.5,
  "servingSize": "1 piece",
  "mealType": "Breakfast",
  "barcode": null,
  "photoUrl": null,
  "timestamp": 1739174400000,
  "date": "2026-02-10"
}
```

**Fields**:
- `userId` (String): Owner reference
- `foodName` (String): Name of food item
- `calories` (Number): Total calories
- `protein` (Number): Grams of protein
- `carbs` (Number): Grams of carbohydrates
- `fat` (Number): Grams of fat
- `servingSize` (String): "1 cup", "200g", etc.
- `mealType` (String): "Breakfast" | "Lunch" | "Dinner" | "Snack"
- `barcode` (String | null): For scanned items
- `photoUrl` (String | null): If photo taken
- `timestamp` (Number): Unix timestamp
- `date` (String): YYYY-MM-DD format

**Retention**: 30 days (auto-cleanup in code)

---

### 3. **goals/{goalId}**
**Purpose**: User goals and metabolic calculations  
**Write**: Owner only | **Read**: Authenticated users (for queries)

```json
{
  "userId": "abc123xyz",
  "goalType": "Lose Weight",
  "targetValue": 55.0,
  "currentValue": 60.5,
  "bmr": 1450.5,
  "tdee": 2000.0,
  "dailyCalories": 1600,
  "activityLevel": "Moderate",
  "dietPreference": "Vegetarian",
  "targetWeight": 55.0,
  "availableExerciseTime": "30-45 mins",
  "preferredExerciseTime": "Morning",
  "startDate": 1739174400000,
  "endDate": 0,
  "isCompleted": false,
  "createdAt": 1739174400000,
  "updatedAt": 1739260800000
}
```

**Fields**:
- `userId` (String): Owner reference
- `goalType` (String): "Lose Weight" | "Gain Weight" | "Maintain Weight" | "Build Muscle"
- `targetValue` (Number): Target weight in kg
- `currentValue` (Number): Starting weight
- `bmr` (Number): Basal Metabolic Rate
- `tdee` (Number): Total Daily Energy Expenditure
- `dailyCalories` (Number): Calculated daily calorie target
- `activityLevel` (String): Activity multiplier
- `dietPreference` (String): Copied from user profile
- `targetWeight` (Number): Goal weight
- `availableExerciseTime` (String): User's available time
- `preferredExerciseTime` (String): Preferred workout time
- `startDate` (Timestamp): Goal start
- `endDate` (Timestamp): Goal end (0 if ongoing)
- `isCompleted` (Boolean): Goal status
- `createdAt` (Timestamp): Creation time
- `updatedAt` (Timestamp): Last update

**Note**: Each user typically has 1 active goal

---

### 4. **exercise_logs/{logId}** ⭐ TOP-LEVEL
**Purpose**: Exercise activity tracking  
**Write**: Owner only | **Read**: Authenticated users

```json
{
  "userId": "abc123xyz",
  "exerciseName": "Push-ups",
  "type": "Strength",
  "duration": 15,
  "intensity": "Moderate",
  "caloriesBurned": 85,
  "targetMuscle": "Chest",
  "bodyPart": "Upper Body",
  "equipment": "None",
  "source": "AI_Recommendation",
  "timestamp": 1739174400000,
  "date": "2026-02-10"
}
```

**Fields**:
- `userId` (String): Owner reference
- `exerciseName` (String): Name of exercise
- `type` (String): "Cardio" | "Strength" | "Flexibility" | "HIIT"
- `duration` (Number): Minutes
- `intensity` (String): "Low" | "Moderate" | "High"
- `caloriesBurned` (Number): Estimated calories
- `targetMuscle` (String): Primary muscle group
- `bodyPart` (String): "Upper Body" | "Lower Body" | "Core" | "Full Body"
- `equipment` (String): Required equipment or "None"
- `source` (String): "Manual" | "AI_Recommendation" | "Workout_Plan"
- `timestamp` (Timestamp): When logged
- `date` (String): YYYY-MM-DD

---

### 5. **weightLogs/{logId}** ⭐ TOP-LEVEL
**Purpose**: Weight tracking over time  
**Write**: Owner only | **Read**: Authenticated users

```json
{
  "userId": "abc123xyz",
  "weight": 59.8,
  "timestamp": 1739174400000,
  "date": "2026-02-10"
}
```

**Fields**:
- `userId` (String): Owner reference
- `weight` (Number): Weight in kg
- `timestamp` (Timestamp): When measured
- `date` (String): YYYY-MM-DD

**Usage**: Plateau detection, progress tracking

---

### 6. **waterLogs/{logId}** ⭐ TOP-LEVEL
**Purpose**: Hydration tracking  
**Write**: Owner only | **Read**: Authenticated users

```json
{
  "userId": "abc123xyz",
  "amountML": 250,
  "timestamp": 1739174400000,
  "date": "2026-02-10"
}
```

**Fields**:
- `userId` (String): Owner reference
- `amountML` (Number): Water consumed in ml
- `timestamp` (Timestamp): When logged
- `date` (String): YYYY-MM-DD

---

### 7. **meal_feedback/{feedbackId}**
**Purpose**: User feedback on AI meal suggestions  
**Write**: Owner only | **Read**: Authenticated users

```json
{
  "userId": "abc123xyz",
  "mealName": "Palak Paneer",
  "mealType": "Lunch",
  "action": "Skipped",
  "reason": null,
  "timestamp": 1739174400000,
  "date": "2026-02-10"
}
```

**Fields**:
- `userId` (String): Owner reference
- `mealName` (String): Food item name
- `mealType` (String): "Breakfast" | "Lunch" | "Dinner" | "Snack"
- `action` (String): "Ate" | "Skipped" | "New"
- `reason` (String | null): Optional user note
- `timestamp` (Timestamp): When action taken
- `date` (String): YYYY-MM-DD

**Usage**: ML training, preference learning

---

### 8. **user_preferences/{userId}** ⭐ DOCUMENT ID = USER ID
**Purpose**: AI-learned food preferences  
**Write**: Owner only | **Read**: Owner only

```json
{
  "dislikedFoods": [
    "Paneer Butter Masala",
    "Chole Bhature"
  ],
  "favoriteFoods": [
    "Idli Sambar",
    "Masala Dosa"
  ],
  "lastUpdated": "ServerTimestamp"
}
```

**Fields**:
- `dislikedFoods` (Array): Foods user skipped
- `favoriteFoods` (Array): Foods user ate
- `lastUpdated` (Timestamp): Last preference update

**Usage**: AI meal generation excludes disliked foods

---

### 9. **ai_generated_plans/{planId}**
**Purpose**: Complete daily diet plans  
**Write**: System only | **Read**: Owner only

```json
{
  "userId": "abc123xyz",
  "generatedAt": 1739174400000,
  "date": "2026-02-10",
  "breakfast": {
    "item": "Poha with Tea",
    "calories": 250,
    "protein": "8g",
    "reason": "Light morning meal"
  },
  "lunch": {
    "item": "Rajma Chawal",
    "calories": 450,
    "protein": "15g",
    "reason": "Balanced protein"
  },
  "snack": {
    "item": "Fruit Salad",
    "calories": 150,
    "protein": "3g",
    "reason": "Natural sugars"
  },
  "dinner": {
    "item": "Roti with Dal",
    "calories": 350,
    "protein": "12g",
    "reason": "Light dinner"
  },
  "postWorkout": {
    "item": "Banana Shake",
    "calories": 200,
    "protein": "20g",
    "reason": "Recovery"
  },
  "dailyTip": "Stay hydrated!",
  "totalCalories": 1400,
  "status": "Active"
}
```

**Retention**: 60 days

---

### 10. **workouts/{workoutId}** ⭐ READ ONLY
**Purpose**: Exercise library for recommendations  
**Write**: Admin only | **Read**: All authenticated

```json
{
  "name": "Push-ups",
  "type": "Strength",
  "targetMuscle": "Chest",
  "bodyPart": "Upper Body",
  "difficulty": "Beginner",
  "equipment": "None",
  "duration": 10,
  "estimatedCalories": 50,
  "instructions": "...",
  "videoUrl": "..."
}
```

---

### 11. **festivalCalendar/{eventId}** ⭐ READ ONLY
**Purpose**: Indian festival awareness for AI  
**Write**: Admin only | **Read**: All authenticated

```json
{
  "name": "Diwali",
  "date": "2026-11-01",
  "type": "Major",
  "dietNote": "Sweets and festive foods",
  "region": "All India"
}
```

---

### 12. **challenges/{challengeId}**
**Purpose**: Multiplayer fitness challenges  
**Write**: Creator/Opponent | **Read**: All authenticated

```json
{
  "creatorId": "user1",
  "opponentId": "user2",
  "challengeType": "Steps",
  "targetValue": 10000,
  "startDate": 1739174400000,
  "endDate": 1739260800000,
  "status": "Active",
  "creatorProgress": 7500,
  "opponentProgress": 8200
}
```

---

### 13. **saved_recipes/{userId}**
**Purpose**: User's saved recipe collection  
**Write**: Owner only | **Read**: Owner only

```json
{
  "userId": "abc123xyz",
  "totalRecipes": 15,
  "lastUpdated": 1739174400000
}
```

#### Subcollection: **saved_recipes/{userId}/recipes/{recipeId}**
```json
{
  "name": "Palak Paneer",
  "ingredients": ["Spinach", "Paneer", "Spices"],
  "instructions": "...",
  "prepTime": 30,
  "servings": 4,
  "calories": 320,
  "savedAt": 1739174400000
}
```

---

### 14. **notifications/{notifId}**
**Purpose**: User notifications  
**Write**: Owner only | **Read**: Owner only

```json
{
  "userId": "abc123xyz",
  "title": "Daily Goal Achieved!",
  "message": "Great job completing your workout!",
  "type": "Achievement",
  "isRead": false,
  "timestamp": 1739174400000,
  "actionUrl": null
}
```

---

## 🔥 COLLECTIONS TO REMOVE

### ❌ **foodLogs** (top-level) - UNUSED
**Reason**: Using subcollection `users/{userId}/foodLogs` instead  
**Action**: Remove from Firestore rules

### ❌ **exerciseLogs** (camelCase) - UNUSED
**Reason**: Using `exercise_logs` (underscore) instead  
**Action**: Remove from Firestore rules

### ❌ **users/{userId}/exerciseLogs** - UNUSED SUBCOLLECTION
**Reason**: Using top-level `exercise_logs`  
**Action**: Remove from Firestore rules

### ❌ **users/{userId}/weightLogs** - UNUSED SUBCOLLECTION
**Reason**: Using top-level `weightLogs`  
**Action**: Remove from Firestore rules

### ❌ **users/{userId}/hydrationLogs** - UNUSED SUBCOLLECTION
**Reason**: Using top-level `waterLogs`  
**Action**: Remove from Firestore rules

### ❌ **moods** - POTENTIALLY UNUSED
**Action**: Verify if used, otherwise remove

### ❌ **mealHistory** - DUPLICATE of meal_feedback
**Action**: Remove, use meal_feedback instead

### ❌ **recommendations** - UNUSED
**Action**: Remove if not implemented

---

## 📊 STORAGE ESTIMATES

### Per User (Monthly):
- **User profile**: 1 doc (~3 KB)
- **foodLogs**: ~120 docs (~30 KB) - 30 day retention
- **exercise_logs**: ~60 docs (~20 KB)
- **weightLogs**: ~30 docs (~5 KB)
- **waterLogs**: ~200 docs (~15 KB)
- **meal_feedback**: ~120 docs (~15 KB)
- **user_preferences**: 1 doc (~5 KB)
- **ai_generated_plans**: ~30 docs (~90 KB) - 60 day retention

**Total per user/month**: ~183 KB

### For 1000 Users:
- **Storage**: ~183 MB/month
- **Reads**: ~5,000-10,000/day
- **Writes**: ~2,000-5,000/day

---

## 🔐 SECURITY SUMMARY

**Owner-only collections**:
- `users/{userId}`
- `users/{userId}/foodLogs/*`
- `user_preferences/{userId}`
- `ai_generated_plans` (filtered by userId)
- `saved_recipes/{userId}`
- `notifications` (filtered by userId)

**Shared collections** (userId-filtered):
- `goals`
- `exercise_logs`
- `weightLogs`
- `waterLogs`
- `meal_feedback`
- `challenges`

**Read-only collections**:
- `workouts`
- `festivalCalendar`

---

## 📝 MIGRATION NOTES

### If existing data in wrong locations:

1. **Migrate top-level foodLogs → users/{userId}/foodLogs**
2. **Migrate exerciseLogs → exercise_logs**
3. **Delete unused subcollections**
4. **Update all Firestore instance calls to default**

---

**Version**: 2.0  
**Last Verified**: February 10, 2026  
**Status**: ✅ PRODUCTION READY

