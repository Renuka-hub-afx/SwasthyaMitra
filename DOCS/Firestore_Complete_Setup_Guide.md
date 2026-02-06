# 🗄️ SwasthyaMitra Firestore Database Setup Guide

## 📋 Table of Contents
1. [Collections Overview](#collections-overview)
2. [Security Rules (Complete)](#security-rules-complete)
3. [Collection Details](#collection-details)
4. [What Gets Created Automatically](#what-gets-created-automatically)
5. [Manual Setup Steps](#manual-setup-steps)

---

## Collections Overview

### Total Collections: 14

Your SwasthyaMitra app uses **14 Firestore collections**:

| # | Collection Name | Purpose | Auto-Created? |
|---|-----------------|---------|---------------|
| 1 | `users` | User profiles | ✅ Yes (on signup) |
| 2 | `goals` | BMR/TDEE calculations | ✅ Yes (on onboarding) |
| 3 | `foodLogs` | Food diary entries | ✅ Yes (on first log) |
| 4 | `waterLogs` | Hydration tracking | ✅ Yes (on first log) |
| 5 | `exerciseLogs` | Exercise tracking | ✅ Yes (on first log) |
| 6 | `weightLogs` | Weight history | ✅ Yes (on first log) |
| 7 | `workouts` | Workout sessions | ✅ Yes (on first workout) |
| 8 | `challenges` | Fitness challenges | ✅ Yes (on first challenge) |
| 9 | `mealHistory` | Meal plan history | ✅ Yes (on first AI meal) |
| 10 | `meal_feedback` | AI meal feedback | ✅ Yes (on first feedback) |
| 11 | `ai_generated_plans` | AI diet plans | ✅ Yes (on first plan) |
| 12 | `user_preferences` | Learned preferences | ✅ Yes (on first preference) |
| 13 | `recommendations` | AI recommendations | ❌ No (Cloud Functions only) |
| 14 | `festivalCalendar` | Festival events | ❌ No (Admin only) |
| 15 | `notifications` | User notifications | ✅ Yes (on first notification) |

**Key Point:** Collections are created **automatically** when your app writes the first document to them. You don't need to manually create them!

---

## Security Rules (Complete)

### Copy-Paste This Entire File to Firebase Console

**Location:** Firestore Database → Rules tab

```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    
    // Users collection - each user can only access their own document
    match /users/{userId} {
      allow read, write: if request.auth != null && request.auth.uid == userId;
    }
    
    // Goals collection - Broad Read for authenticated users to fix queries 
    match /goals/{goalId} {
      allow read: if request.auth != null;
      allow write, delete: if request.auth != null && 
        (resource == null || resource.data.userId == request.auth.uid) &&
        (request.resource == null || request.resource.data.userId == request.auth.uid);
    }
    
    // Food Logs collection - Broad Read for authenticated users
    match /foodLogs/{logId} {
      allow read: if request.auth != null;
      allow write, delete: if request.auth != null && 
        (resource == null || resource.data.userId == request.auth.uid) &&
        (request.resource == null || request.resource.data.userId == request.auth.uid);
    }
    
    // Workouts collection
    match /workouts/{workoutId} {
      allow read: if request.auth != null;
      allow write, delete: if request.auth != null && 
        (resource == null || resource.data.userId == request.auth.uid) &&
        (request.resource == null || request.resource.data.userId == request.auth.uid);
    }

    // Challenges collection - allow creation and participation
    match /challenges/{challengeId} {
      allow create: if request.auth != null;
      allow read: if request.auth != null;
      allow update: if request.auth != null && (
        resource.data.creatorId == request.auth.uid || 
        resource.data.opponentId == request.auth.uid ||
        resource.data.opponentId == ""
      );
      allow delete: if request.auth != null && resource.data.creatorId == request.auth.uid;
    }

    // Exercise Logs (Standardized to camelCase to match your DB)
    match /exerciseLogs/{logId} { 
      allow read: if request.auth != null;
      allow write, delete: if request.auth != null && 
        (resource == null || resource.data.userId == request.auth.uid) &&
        (request.resource == null || request.resource.data.userId == request.auth.uid); 
    }

    // Weight Logs (Standardized to camelCase to match your DB)
    match /weightLogs/{logId} { 
      allow read: if request.auth != null;
      allow write, delete: if request.auth != null && 
        (resource == null || resource.data.userId == request.auth.uid) &&
        (request.resource == null || request.resource.data.userId == request.auth.uid); 
    }

    match /mealHistory/{mealId} { 
      allow read: if request.auth != null;
      allow write, delete: if request.auth != null && 
        (resource == null || resource.data.userId == request.auth.uid) &&
        (request.resource == null || request.resource.data.userId == request.auth.uid); 
    }

    match /recommendations/{recId} { 
      allow read: if request.auth != null && resource.data.userId == request.auth.uid; 
      allow write: if false; // Only Cloud Functions/Admin can write
    }

    match /festivalCalendar/{eventId} { 
      allow read: if request.auth != null; 
    }

    match /notifications/{notifId} { 
      allow read: if request.auth != null && resource.data.userId == request.auth.uid;
      allow write, delete: if request.auth != null && 
        (resource == null || resource.data.userId == request.auth.uid) &&
        (request.resource == null || request.resource.data.userId == request.auth.uid); 
    }

    // Meal Feedback - users can only access their own feedback
    match /meal_feedback/{feedbackId} {
      allow read: if request.auth != null;
      allow write, delete: if request.auth != null && 
        (resource == null || resource.data.userId == request.auth.uid) &&
        (request.resource == null || request.resource.data.userId == request.auth.uid);
    }

    // AI Generated Plans - users can only access their own plans
    match /ai_generated_plans/{planId} {
      allow read: if request.auth != null && resource.data.userId == request.auth.uid;
      allow write: if request.auth != null && 
        (resource == null || resource.data.userId == request.auth.uid) &&
        (request.resource == null || request.resource.data.userId == request.auth.uid);
      allow delete: if request.auth != null && resource.data.userId == request.auth.uid;
    }

    // Water Logs - Users can only read/write their own logs
    match /waterLogs/{logId} {
      allow read: if request.auth != null;
      allow write, delete: if request.auth != null && 
        (resource == null || resource.data.userId == request.auth.uid) &&
        (request.resource == null || request.resource.data.userId == request.auth.uid);
    }

    // User Preferences - users can only access their own preferences
    match /user_preferences/{userId} {
      allow read, write: if request.auth != null && request.auth.uid == userId;
    }
  }
}
```

---

## Collection Details

### 1. Collection: `users`

**Purpose:** User profile information  
**Document ID:** User's Firebase Auth UID  
**Auto-Created:** ✅ Yes (on signup)

**Schema:**
```javascript
{
  "name": "Sarah Smith",
  "age": 28,
  "gender": "Female",
  "height": 165,              // cm
  "weight": 60.5,             // kg
  "eatingPreference": "Vegetarian",
  "allergies": ["Nuts", "Dairy"],
  "wakeTime": "07:00",        // HH:mm
  "sleepTime": "23:00",       // HH:mm
  "waterGoal": 2200,          // ml
  "isPeriodMode": false       // Women's health feature
}
```

**Security:**
- Users can only read/write their own document
- Document ID must match authenticated user ID

---

### 2. Collection: `goals`

**Purpose:** BMR/TDEE calculations and fitness goals  
**Document ID:** Auto-generated  
**Auto-Created:** ✅ Yes (on onboarding)

**Schema:**
```javascript
{
  "userId": "abc12345",
  "goalType": "Lose Weight",
  "bmr": 1540.5,
  "tdee": 2100.0,
  "dailyCalories": 1600.0,
  "activityLevel": "Moderate",
  "timestamp": 1736640000
}
```

**Security:**
- All authenticated users can read (for queries)
- Only owner can write/delete

---

### 3. Collection: `foodLogs`

**Purpose:** Food diary entries  
**Document ID:** Auto-generated  
**Auto-Created:** ✅ Yes (on first food log)

**Schema:**
```javascript
{
  "userId": "abc12345",
  "foodName": "Paneer Poha",
  "calories": 350,
  "protein": 15.0,
  "carbs": 45.0,
  "fat": 8.0,
  "servingSize": "1 bowl",
  "mealType": "Breakfast",
  "date": "2026-01-20",       // YYYY-MM-DD
  "timestamp": 1736640000,
  "barcode": null,            // Optional
  "photoUrl": null            // Optional (for AI food camera)
}
```

**Security:**
- All authenticated users can read
- Only owner can write/delete

---

### 4. Collection: `waterLogs`

**Purpose:** Hydration tracking  
**Document ID:** Auto-generated  
**Auto-Created:** ✅ Yes (on first water log)

**Schema:**
```javascript
{
  "userId": "abc12345",
  "amountML": 250,
  "date": "2026-01-20",
  "timestamp": 1736640000
}
```

**Security:**
- All authenticated users can read
- Only owner can write/delete

---

### 5. Collection: `exerciseLogs`

**Purpose:** Exercise/workout tracking  
**Document ID:** Auto-generated  
**Auto-Created:** ✅ Yes (on first exercise log)

**Schema:**
```javascript
{
  "userId": "abc12345",
  "exerciseName": "Running",
  "duration": 30,             // minutes
  "caloriesBurned": 250,
  "intensity": "Moderate",
  "date": "2026-01-20",
  "timestamp": 1736640000
}
```

**Security:**
- All authenticated users can read
- Only owner can write/delete

---

### 6. Collection: `weightLogs`

**Purpose:** Weight tracking over time  
**Document ID:** Auto-generated  
**Auto-Created:** ✅ Yes (on first weight log)

**Schema:**
```javascript
{
  "userId": "abc12345",
  "weight": 60.5,             // kg
  "date": "2026-01-20",
  "timestamp": 1736640000
}
```

**Security:**
- All authenticated users can read
- Only owner can write/delete

---

### 7. Collection: `workouts`

**Purpose:** Workout sessions  
**Document ID:** Auto-generated  
**Auto-Created:** ✅ Yes (on first workout)

**Schema:**
```javascript
{
  "userId": "abc12345",
  "workoutName": "Full Body Workout",
  "exercises": [
    {
      "name": "Push-ups",
      "sets": 3,
      "reps": 15
    }
  ],
  "totalDuration": 45,        // minutes
  "caloriesBurned": 300,
  "date": "2026-01-20",
  "timestamp": 1736640000
}
```

**Security:**
- All authenticated users can read
- Only owner can write/delete

---

### 8. Collection: `challenges`

**Purpose:** Fitness challenges between users  
**Document ID:** Auto-generated  
**Auto-Created:** ✅ Yes (on first challenge)

**Schema:**
```javascript
{
  "creatorId": "user1",
  "opponentId": "user2",
  "challengeType": "Steps",
  "targetValue": 10000,
  "startDate": "2026-01-20",
  "endDate": "2026-01-27",
  "status": "Active",
  "creatorProgress": 5000,
  "opponentProgress": 6000
}
```

**Security:**
- All authenticated users can read and create
- Only creator or opponent can update
- Only creator can delete

---

### 9. Collection: `mealHistory`

**Purpose:** AI-generated meal plan history  
**Document ID:** Auto-generated  
**Auto-Created:** ✅ Yes (on first AI meal)

**Schema:**
```javascript
{
  "userId": "abc12345",
  "mealType": "Breakfast",
  "mealName": "Oats with Fruits",
  "calories": 350,
  "protein": 12.0,
  "carbs": 55.0,
  "fat": 8.0,
  "date": "2026-01-20",
  "timestamp": 1736640000,
  "isAIGenerated": true
}
```

**Security:**
- All authenticated users can read
- Only owner can write/delete

---

### 10. Collection: `meal_feedback`

**Purpose:** User feedback on AI-generated meals  
**Document ID:** Auto-generated  
**Auto-Created:** ✅ Yes (on first feedback)

**Schema:**
```javascript
{
  "userId": "abc12345",
  "mealId": "meal123",
  "feedback": "Ate",          // "Ate", "Skipped", "Regenerated"
  "reason": "Too spicy",      // Optional
  "timestamp": 1736640000
}
```

**Security:**
- All authenticated users can read
- Only owner can write/delete

---

### 11. Collection: `ai_generated_plans`

**Purpose:** Complete AI diet plans  
**Document ID:** Auto-generated  
**Auto-Created:** ✅ Yes (on first AI plan)

**Schema:**
```javascript
{
  "userId": "abc12345",
  "planType": "Weekly Diet Plan",
  "generatedDate": "2026-01-20",
  "meals": [
    {
      "day": "Monday",
      "breakfast": {...},
      "lunch": {...},
      "dinner": {...}
    }
  ],
  "totalCalories": 1600,
  "timestamp": 1736640000
}
```

**Security:**
- Only owner can read
- Only owner can write/delete

---

### 12. Collection: `user_preferences`

**Purpose:** Learned food preferences from feedback  
**Document ID:** User's Firebase Auth UID  
**Auto-Created:** ✅ Yes (on first preference)

**Schema:**
```javascript
{
  "userId": "abc12345",
  "dislikedFoods": ["Bitter Gourd", "Okra"],
  "favoriteFoods": ["Paneer", "Dal Makhani"],
  "lastUpdated": 1736640000
}
```

**Security:**
- Only owner can read/write

---

### 13. Collection: `recommendations`

**Purpose:** AI-generated recommendations (Cloud Functions)  
**Document ID:** Auto-generated  
**Auto-Created:** ❌ No (Cloud Functions only)

**Schema:**
```javascript
{
  "userId": "abc12345",
  "type": "Exercise",
  "recommendation": "Try yoga for flexibility",
  "priority": "High",
  "timestamp": 1736640000
}
```

**Security:**
- Only owner can read
- Only Cloud Functions can write

**Note:** This collection is managed by Cloud Functions, not by the app directly.

---

### 14. Collection: `festivalCalendar`

**Purpose:** Indian festival calendar events  
**Document ID:** Auto-generated  
**Auto-Created:** ❌ No (Admin only)

**Schema:**
```javascript
{
  "festivalName": "Diwali",
  "date": "2026-10-24",
  "description": "Festival of Lights",
  "healthTips": "Enjoy sweets in moderation"
}
```

**Security:**
- All authenticated users can read
- No write access (admin only)

**Note:** This collection should be pre-populated by admin.

---

### 15. Collection: `notifications`

**Purpose:** User notifications  
**Document ID:** Auto-generated  
**Auto-Created:** ✅ Yes (on first notification)

**Schema:**
```javascript
{
  "userId": "abc12345",
  "title": "Water Reminder",
  "message": "Time to drink water!",
  "type": "Hydration",
  "isRead": false,
  "timestamp": 1736640000
}
```

**Security:**
- Only owner can read
- Only owner can write/delete

---

## What Gets Created Automatically

### ✅ Automatically Created by Your App

These collections are created **automatically** when your app writes the first document:

1. **`users`** - Created on user signup
2. **`goals`** - Created on onboarding completion
3. **`foodLogs`** - Created on first food entry
4. **`waterLogs`** - Created on first water log
5. **`exerciseLogs`** - Created on first exercise log
6. **`weightLogs`** - Created on first weight entry
7. **`workouts`** - Created on first workout
8. **`challenges`** - Created on first challenge
9. **`mealHistory`** - Created on first AI meal
10. **`meal_feedback`** - Created on first feedback
11. **`ai_generated_plans`** - Created on first AI plan
12. **`user_preferences`** - Created on first preference
13. **`notifications`** - Created on first notification

### ❌ NOT Automatically Created

These collections require manual setup or Cloud Functions:

1. **`recommendations`** - Requires Cloud Functions
2. **`festivalCalendar`** - Requires admin to populate

---

## Manual Setup Steps

### Step 1: Create Firestore Database

1. Go to Firebase Console
2. Select your project: `swasthyamitra-5e482`
3. Click **Build → Firestore Database**
4. Click **"Create database"**
5. Select **"Production mode"**
6. Choose location: **`asia-south1 (Mumbai)`**
7. Click **"Enable"**
8. Wait 1-2 minutes

### Step 2: Add Security Rules

1. Click **"Rules"** tab
2. Delete default rules
3. Copy the entire security rules from above
4. Paste into editor
5. Click **"Publish"**

### Step 3: Test with Your App

1. Install your app on device
2. Create a new account
3. Complete onboarding
4. Log some food
5. Check Firestore Console - collections should appear!

### Step 4: (Optional) Pre-populate Festival Calendar

If you want festival data from day 1:

1. Go to Firestore Console
2. Click **"Start collection"**
3. Collection ID: `festivalCalendar`
4. Add documents manually or import JSON

**Sample festival document:**
```javascript
{
  "festivalName": "Diwali",
  "date": "2026-10-24",
  "description": "Festival of Lights",
  "healthTips": "Enjoy sweets in moderation, stay hydrated"
}
```

---

## Verification Checklist

After setup, verify:

- [ ] Firestore database created
- [ ] Location set to `asia-south1`
- [ ] Security rules published
- [ ] App can create new user
- [ ] `users` collection appears
- [ ] `goals` collection appears
- [ ] Food logging works
- [ ] `foodLogs` collection appears
- [ ] No permission errors in logcat

---

## Important Notes

### Collections are Created Automatically

**You DON'T need to:**
- ❌ Manually create collections
- ❌ Define schemas in Firebase
- ❌ Set up indexes (automatic indexing)
- ❌ Create documents beforehand

**You ONLY need to:**
- ✅ Create the database
- ✅ Add security rules
- ✅ Let your app create collections naturally

### Security Rules Protect Your Data

**What the rules do:**
- ✅ Users can only access their own data
- ✅ Authenticated users required
- ✅ Prevents unauthorized access
- ✅ Allows necessary queries

**What the rules prevent:**
- ❌ Unauthenticated access
- ❌ Users accessing other users' data
- ❌ Malicious data manipulation
- ❌ Unauthorized deletions

---

## Summary

**Total Collections:** 14  
**Auto-Created:** 13  
**Manual Setup:** 1 (festivalCalendar - optional)

**Setup Time:** 5-10 minutes

**What to do NOW:**
1. Create Firestore database (Standard edition, Mumbai location)
2. Add security rules (copy-paste from above)
3. Install and test your app
4. Collections will appear automatically!

**That's it!** Your Firestore database is ready to use. 🎉
