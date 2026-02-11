# 🔥 SwasthyaMitra Firebase Schema - Quick Reference

## 📊 FINAL STRUCTURE

```
SwasthyaMitra Firestore (Default Instance Only)
│
├── 👤 users/{userId}                     [User Profiles]
│   └── 📝 foodLogs/{logId}              [Subcollection - Food Diary]
│
├── 🎯 goals/{goalId}                     [User Goals & Metabolic Data]
├── 💪 exercise_logs/{logId}              [Exercise Tracking]
├── ⚖️  weightLogs/{logId}                [Weight Tracking]
├── 💧 waterLogs/{logId}                  [Hydration Tracking]
├── 🍽️  meal_feedback/{feedbackId}        [AI Diet Feedback]
├── ⭐ user_preferences/{userId}          [AI Learned Preferences]
├── 🤖 ai_generated_plans/{planId}        [AI Diet Plans]
├── 🏋️  workouts/{workoutId}              [Exercise Library - READ ONLY]
├── 🎉 festivalCalendar/{eventId}        [Festival Calendar - READ ONLY]
├── 🏆 challenges/{challengeId}           [Multiplayer Challenges]
├── 📖 saved_recipes/{userId}             [User Saved Recipes]
│   └── 🍳 recipes/{recipeId}            [Subcollection - Individual Recipes]
└── 🔔 notifications/{notifId}            [User Notifications]
```

---

## 🔑 KEY COLLECTIONS

### 📝 **foodLogs** (Subcollection)
**Path**: `users/{userId}/foodLogs/{logId}`  
**Access**: Owner only  
**Data**: User's food consumption logs  
**Retention**: 30 days (auto-cleanup)

```kotlin
// How to access
firestore.collection("users")
    .document(userId)
    .collection("foodLogs")
    .add(foodData)
```

---

### 💪 **exercise_logs** (Top-level with underscore)
**Path**: `exercise_logs/{logId}`  
**Access**: Owner writes, all authenticated read  
**Data**: Exercise activity tracking  

```kotlin
// How to access
firestore.collection("exercise_logs")
    .whereEqualTo("userId", userId)
    .get()
```

---

### ⭐ **user_preferences** (Document ID = User ID)
**Path**: `user_preferences/{userId}`  
**Access**: Owner only  
**Data**: AI learned food preferences  

```kotlin
// How to access
firestore.collection("user_preferences")
    .document(userId)
    .get()
```

---

## ❌ REMOVED COLLECTIONS

### What Was Deleted:
- ❌ `foodLogs` (top-level) - Was unused
- ❌ `exerciseLogs` (camelCase) - Duplicate
- ❌ `users/{userId}/exerciseLogs` - Unused subcollection
- ❌ `users/{userId}/weightLogs` - Unused subcollection
- ❌ `users/{userId}/hydrationLogs` - Unused subcollection
- ❌ `users/{userId}/mood_logs` - Unused subcollection
- ❌ `users/{userId}/goals` - Unused subcollection
- ❌ `users/{userId}/lifestyle` - Unused subcollection
- ❌ `moods` - Never implemented
- ❌ `mealHistory` - Duplicate of meal_feedback
- ❌ `recommendations` - Never implemented

---

## ✅ STANDARDIZATIONS

### 1. Firestore Instance
```kotlin
// ❌ BEFORE (Mixed)
FirebaseFirestore.getInstance("renu")  // Named instance
FirebaseFirestore.getInstance()         // Default instance

// ✅ AFTER (Consistent)
FirebaseFirestore.getInstance()         // ONLY default instance
```

### 2. Collection Naming
```kotlin
// ❌ BEFORE
exerciseLogs  // camelCase - inconsistent

// ✅ AFTER
exercise_logs // snake_case - standardized
```

---

## 📦 DATA MODELS

### FoodLog
```kotlin
data class FoodLog(
    val logId: String = "",
    val userId: String,
    val foodName: String,
    val calories: Int,
    val protein: Double,
    val carbs: Double,
    val fat: Double,
    val servingSize: String,
    val mealType: String,
    val barcode: String? = null,
    val photoUrl: String? = null,
    val timestamp: Long,
    val date: String
)
```

### User Preferences
```json
{
  "dislikedFoods": ["Food A", "Food B"],
  "favoriteFoods": ["Food C", "Food D"],
  "lastUpdated": Timestamp
}
```

### Meal Feedback
```json
{
  "userId": "abc123",
  "mealName": "Masala Dosa",
  "mealType": "Breakfast",
  "action": "Ate|Skipped|New",
  "timestamp": 1739174400000,
  "date": "2026-02-10"
}
```

---

## 🔐 SECURITY RULES

### Owner-Only:
- ✅ `users/{userId}` and all subcollections
- ✅ `user_preferences/{userId}`
- ✅ `saved_recipes/{userId}` and subcollections

### Shared (userId-filtered):
- ✅ `goals` - Owner writes, authenticated read
- ✅ `exercise_logs` - Owner writes, authenticated read
- ✅ `weightLogs` - Owner writes, authenticated read
- ✅ `waterLogs` - Owner writes, authenticated read
- ✅ `meal_feedback` - Owner writes, authenticated read
- ✅ `ai_generated_plans` - Owner reads only
- ✅ `challenges` - Creator/Opponent write, all read

### Read-Only:
- ✅ `workouts` - All authenticated read, admin write
- ✅ `festivalCalendar` - All authenticated read, admin write

---

## 📊 STORAGE ESTIMATES

| Collection | Docs/User/Month | Size/User/Month |
|------------|-----------------|-----------------|
| users | 1 | 3 KB |
| foodLogs (subcoll.) | 120 | 30 KB |
| exercise_logs | 60 | 20 KB |
| weightLogs | 30 | 5 KB |
| waterLogs | 200 | 15 KB |
| meal_feedback | 120 | 15 KB |
| user_preferences | 1 | 5 KB |
| ai_generated_plans | 30 | 90 KB |
| **TOTAL** | **~562** | **~183 KB** |

---

## 🎯 COMMON QUERIES

### Get Today's Food Logs
```kotlin
val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
val today = dateFormat.format(Date())

firestore.collection("users")
    .document(userId)
    .collection("foodLogs")
    .whereEqualTo("date", today)
    .get()
```

### Get Recent Exercise Logs
```kotlin
val cutoff = System.currentTimeMillis() - (7 * 24 * 60 * 60 * 1000L)

firestore.collection("exercise_logs")
    .whereEqualTo("userId", userId)
    .whereGreaterThanOrEqualTo("timestamp", cutoff)
    .get()
```

### Get User Preferences
```kotlin
firestore.collection("user_preferences")
    .document(userId)
    .get()
    .addOnSuccessListener { doc ->
        val disliked = doc.get("dislikedFoods") as? List<String>
        val favorites = doc.get("favoriteFoods") as? List<String>
    }
```

### Get User Goal
```kotlin
firestore.collection("goals")
    .whereEqualTo("userId", userId)
    .limit(1)
    .get()
    .addOnSuccessListener { snapshot ->
        val goal = snapshot.documents[0]
        val dailyCalories = goal.getDouble("dailyCalories")
        val bmr = goal.getDouble("bmr")
        val tdee = goal.getDouble("tdee")
    }
```

---

## 🧪 TESTING COMMANDS

### Deploy Rules
```bash
firebase deploy --only firestore:rules
```

### Build Project
```bash
./gradlew build
```

### Install Debug APK
```bash
./gradlew installDebug
```

---

## 📁 FILES UPDATED

### Code Files (12):
1. ✅ `FirebaseAuthHelper.kt`
2. ✅ `HydrationRepository.kt`
3. ✅ `homepage.kt`
4. ✅ `WorkoutDashboardActivity.kt`
5. ✅ `RecommendationRepository.kt`
6. ✅ `ExerciseReminderReceiver.kt`
7. ✅ `ProfileActivity.kt`
8. ✅ `InsightsRepository.kt`
9. ✅ `AISmartDietActivity.kt`
10. ✅ `AIDietPlanService.kt`
11. ✅ Previous AI diet fixes

### Configuration Files:
1. ✅ `firestore.rules` - Cleaned & deployed
2. ✅ `FIREBASE_COMPLETE_SCHEMA.md` - Full reference
3. ✅ `FIREBASE_CLEANUP_COMPLETE.md` - Cleanup details
4. ✅ This quick reference

---

## ✅ STATUS

**Date**: February 10, 2026  
**Status**: ✅ COMPLETE & DEPLOYED  
**Build**: ✅ SUCCESSFUL  
**Firebase**: ✅ RULES DEPLOYED  
**Instance**: ✅ DEFAULT ONLY  
**Collections**: ✅ 13 (NO DUPLICATES)  

---

## 🎉 RESULT

**Your Firebase is now clean, organized, and production-ready!**

- ✅ Single Firestore instance (default)
- ✅ No duplicate collections
- ✅ Clear data architecture
- ✅ Optimized security rules
- ✅ Comprehensive documentation

**Need help?** See `FIREBASE_COMPLETE_SCHEMA.md` for full details.

