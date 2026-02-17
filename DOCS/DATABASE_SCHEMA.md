# Database Schema & Firestore Structure

## 📋 Overview

SwasthyaMitra uses Google Cloud Firestore as its primary database. Firestore is a NoSQL, document-oriented database that provides real-time synchronization, offline support, and automatic scaling.

---

## 🗄️ Complete Database Structure

### Root Collections

```
firestore/
├── users/                          # User profiles and data
├── challenges/                     # Community challenges
├── leaderboards/                   # Global leaderboards
└── system/                         # App configuration
```

---

## 📊 Detailed Schema

### 1. Users Collection

**Path**: `/users/{userId}`

#### Main User Document
```json
{
  "userId": "string (Firebase Auth UID)",
  "email": "string",
  "name": "string",
  "age": number,
  "gender": "Male" | "Female" | "Other",
  "height": number,              // in cm
  "weight": number,              // in kg
  "activityLevel": "Sedentary" | "Light" | "Moderate" | "Active" | "Very Active",
  "goal": "Lose Weight" | "Maintain" | "Gain Weight",
  "bmi": number,
  "calorieTarget": number,
  "proteinTarget": number,
  "carbsTarget": number,
  "fatsTarget": number,
  "createdAt": timestamp,
  "lastLogin": timestamp,
  "avatar": {
    "skinTone": number,
    "hairStyle": number,
    "outfit": number
  },
  "periodMode": boolean,         // Women's health mode
  "preferences": {
    "dietaryRestrictions": ["string"],
    "favoriteExercises": ["string"],
    "notificationsEnabled": boolean
  }
}
```

---

### 2. Daily Steps Subcollection

**Path**: `/users/{userId}/daily_steps/{date}`

```json
{
  "date": "string (yyyy-MM-dd)",
  "steps": number,
  "calories": number,
  "lastUpdated": timestamp,
  "sessions": [
    {
      "startTime": number (milliseconds),
      "endTime": number (milliseconds),
      "steps": number,
      "calories": number
    }
  ],
  "goal": number,                // Daily step goal
  "goalAchieved": boolean
}
```

**Indexes**:
- `date` (Ascending)
- `steps` (Descending)

---

### 3. Exercise Logs Subcollection

**Path**: `/users/{userId}/exercise_logs/{logId}`

```json
{
  "exerciseName": "string",
  "category": "Cardio" | "Strength" | "Flexibility" | "Sports",
  "duration": number,            // in minutes
  "caloriesBurned": number,
  "intensity": "Low" | "Moderate" | "High",
  "timestamp": timestamp,
  "date": "string (yyyy-MM-dd)",
  "notes": "string (optional)",
  "source": "manual" | "google_fit"
}
```

**Indexes**:
- `date` (Descending)
- `timestamp` (Descending)
- `category` (Ascending)

---

### 4. Food Logs Subcollection

**Path**: `/users/{userId}/food_logs/{logId}`

```json
{
  "foodName": "string",
  "calories": number,
  "protein": number,             // in grams
  "carbs": number,               // in grams
  "fats": number,                // in grams
  "servingSize": "string",
  "mealType": "Breakfast" | "Morning Snack" | "Lunch" | "Evening Snack" | "Dinner",
  "timestamp": timestamp,
  "date": "string (yyyy-MM-dd)",
  "source": "ai_generated" | "manual" | "barcode",
  "barcode": "string (optional)"
}
```

**Indexes**:
- `date` (Descending)
- `timestamp` (Descending)
- `mealType` (Ascending)

---

### 5. Sleep Logs Subcollection

**Path**: `/users/{userId}/sleep_logs/{logId}`

```json
{
  "sleepDate": "string (yyyy-MM-dd)",
  "bedTime": "string (HH:mm)",
  "wakeTime": "string (HH:mm)",
  "duration": number,            // in hours
  "quality": "Poor" | "Fair" | "Good" | "Excellent",
  "notes": "string (optional)",
  "timestamp": timestamp
}
```

**Indexes**:
- `sleepDate` (Descending)
- `quality` (Ascending)

---

### 6. Weight Logs Subcollection

**Path**: `/users/{userId}/weight_logs/{logId}`

```json
{
  "weight": number,              // in kg
  "date": "string (yyyy-MM-dd)",
  "timestamp": timestamp,
  "bmi": number,
  "notes": "string (optional)"
}
```

**Indexes**:
- `date` (Descending)
- `timestamp` (Descending)

---

### 7. Mood Logs Subcollection

**Path**: `/users/{userId}/mood_logs/{logId}`

```json
{
  "mood": "Happy" | "Sad" | "Stressed" | "Anxious" | "Energetic" | "Tired",
  "intensity": number,           // 1-10 scale
  "timestamp": timestamp,
  "date": "string (yyyy-MM-dd)",
  "notes": "string (optional)",
  "recommendations": ["string"]  // AI-generated suggestions
}
```

---

### 8. Gamification Subcollection

**Path**: `/users/{userId}/gamification/{type}`

#### XP and Level Document (`xp_level`)
```json
{
  "totalXP": number,
  "currentLevel": number,
  "xpForNextLevel": number,
  "lastUpdated": timestamp
}
```

#### Badges Document (`badges`)
```json
{
  "badges": [
    {
      "badgeId": "string",
      "name": "string",
      "description": "string",
      "tier": "Bronze" | "Silver" | "Gold" | "Platinum",
      "earnedAt": timestamp,
      "icon": "string"
    }
  ]
}
```

#### Streaks Document (`streaks`)
```json
{
  "currentStreak": number,       // consecutive days
  "longestStreak": number,
  "lastActivityDate": "string (yyyy-MM-dd)",
  "streakType": "daily_login" | "exercise" | "food_log"
}
```

---

### 9. Pantry Items Subcollection

**Path**: `/users/{userId}/pantry_items/{itemId}`

```json
{
  "itemName": "string",
  "category": "Vegetables" | "Fruits" | "Grains" | "Dairy" | "Meat" | "Other",
  "quantity": number,
  "unit": "string",
  "expiryDate": "string (yyyy-MM-dd)",
  "addedDate": timestamp,
  "isExpired": boolean
}
```

---

### 10. Safety Contacts Subcollection

**Path**: `/users/{userId}/safety_contacts/{contactId}`

```json
{
  "name": "string",
  "phoneNumber": "string",
  "relationship": "string",
  "isPrimary": boolean,
  "addedAt": timestamp
}
```

---

## 🔐 Firestore Security Rules

### Complete Rules Configuration

```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    
    // Helper function to check if user is authenticated
    function isAuthenticated() {
      return request.auth != null;
    }
    
    // Helper function to check if user owns the document
    function isOwner(userId) {
      return isAuthenticated() && request.auth.uid == userId;
    }
    
    // Users collection
    match /users/{userId} {
      // Allow read/write only to the owner
      allow read, write: if isOwner(userId);
      
      // Daily steps subcollection
      match /daily_steps/{date} {
        allow read, write: if isOwner(userId);
      }
      
      // Exercise logs subcollection
      match /exercise_logs/{logId} {
        allow read, write: if isOwner(userId);
      }
      
      // Food logs subcollection
      match /food_logs/{logId} {
        allow read, write: if isOwner(userId);
      }
      
      // Sleep logs subcollection
      match /sleep_logs/{logId} {
        allow read, write: if isOwner(userId);
      }
      
      // Weight logs subcollection
      match /weight_logs/{logId} {
        allow read, write: if isOwner(userId);
      }
      
      // Mood logs subcollection
      match /mood_logs/{logId} {
        allow read, write: if isOwner(userId);
      }
      
      // Gamification subcollection
      match /gamification/{type} {
        allow read, write: if isOwner(userId);
      }
      
      // Pantry items subcollection
      match /pantry_items/{itemId} {
        allow read, write: if isOwner(userId);
      }
      
      // Safety contacts subcollection
      match /safety_contacts/{contactId} {
        allow read, write: if isOwner(userId);
      }
    }
    
    // Challenges collection (public read, authenticated write)
    match /challenges/{challengeId} {
      allow read: if true;
      allow write: if isAuthenticated();
    }
    
    // Leaderboards (public read, system write only)
    match /leaderboards/{leaderboardId} {
      allow read: if true;
      allow write: if false;  // Only via Cloud Functions
    }
  }
}
```

---

## 📈 Data Flow Patterns

### 1. Create/Update Pattern
```
User Action (UI)
    ↓
Activity/Fragment
    ↓
Repository Layer (optional)
    ↓
Firestore SDK
    ↓
Cloud Firestore
    ↓
Success/Failure Callback
    ↓
Update UI
```

### 2. Real-Time Listener Pattern
```
Activity onCreate()
    ↓
Attach Firestore Listener
    ↓
Firestore sends initial data
    ↓
Display in UI
    ↓
Data changes in cloud
    ↓
Listener receives update
    ↓
UI automatically refreshes
```

### 3. Batch Write Pattern
```
Multiple operations needed
    ↓
Create WriteBatch
    ↓
Add set/update/delete operations
    ↓
Commit batch
    ↓
All succeed or all fail (atomic)
```

---

## 🔍 Common Queries

### Get Today's Food Logs
```kotlin
db.collection("users").document(userId)
    .collection("food_logs")
    .whereEqualTo("date", today)
    .orderBy("timestamp", Query.Direction.DESCENDING)
    .get()
```

### Get Last 7 Days Steps
```kotlin
val sevenDaysAgo = SimpleDateFormat("yyyy-MM-dd").format(Date(System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000))

db.collection("users").document(userId)
    .collection("daily_steps")
    .whereGreaterThanOrEqualTo("date", sevenDaysAgo)
    .orderBy("date", Query.Direction.ASCENDING)
    .get()
```

### Get Total XP
```kotlin
db.collection("users").document(userId)
    .collection("gamification").document("xp_level")
    .get()
```

### Get Expired Pantry Items
```kotlin
val today = SimpleDateFormat("yyyy-MM-dd").format(Date())

db.collection("users").document(userId)
    .collection("pantry_items")
    .whereLessThan("expiryDate", today)
    .get()
```

---

## 💾 Data Backup & Export

### Automatic Backups
- Firestore automatically backs up data
- Point-in-time recovery available
- Export to Google Cloud Storage

### User Data Export
```kotlin
suspend fun exportUserData(userId: String): JSONObject {
    val userData = JSONObject()
    
    // Export profile
    val profile = db.collection("users").document(userId).get().await()
    userData.put("profile", profile.data)
    
    // Export all subcollections
    val collections = listOf(
        "daily_steps", "exercise_logs", "food_logs",
        "sleep_logs", "weight_logs", "mood_logs"
    )
    
    for (collection in collections) {
        val docs = db.collection("users").document(userId)
            .collection(collection).get().await()
        userData.put(collection, docs.documents.map { it.data })
    }
    
    return userData
}
```

---

## 📊 Storage Optimization

### Best Practices
1. **Use Subcollections**: Organize related data hierarchically
2. **Denormalize When Needed**: Duplicate data for faster reads
3. **Delete Old Data**: Archive logs older than 1 year
4. **Compress Large Fields**: Use gzip for large text fields
5. **Batch Operations**: Group multiple writes together
6. **Index Wisely**: Only create necessary indexes

### Cost Optimization
- **Read Operations**: ~$0.06 per 100,000 reads
- **Write Operations**: ~$0.18 per 100,000 writes
- **Delete Operations**: ~$0.02 per 100,000 deletes
- **Storage**: ~$0.18 per GB/month

**Optimization Strategies**:
- Cache frequently accessed data locally
- Use real-time listeners instead of polling
- Implement pagination for large datasets
- Delete unnecessary old data

---

## 🔄 Data Migration

### Version 1 to Version 2 (Example)
```kotlin
suspend fun migrateUserData(userId: String) {
    val userDoc = db.collection("users").document(userId)
    
    // Add new fields with default values
    userDoc.update(
        mapOf(
            "periodMode" to false,
            "preferences.notificationsEnabled" to true
        )
    ).await()
    
    // Migrate old food logs format
    val foodLogs = userDoc.collection("food_logs").get().await()
    foodLogs.documents.forEach { doc ->
        if (!doc.contains("source")) {
            doc.reference.update("source", "manual").await()
        }
    }
}
```

---

## 📚 References

- [Firestore Documentation](https://firebase.google.com/docs/firestore)
- [Security Rules Guide](https://firebase.google.com/docs/firestore/security/get-started)
- [Best Practices](https://firebase.google.com/docs/firestore/best-practices)

---

**[← Back to Main README](../README.md)**
