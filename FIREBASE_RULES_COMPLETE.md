# 🔐 **SWASTHYAMITRA - COMPLETE FIREBASE SECURITY RULES**

## 📋 **OVERVIEW**

This document contains **production-ready Firebase Security Rules** for both **Firestore** and **Realtime Database (RTDB)**, covering all implemented features including:

- ✅ Step Counter with hybrid validation (Phase 1)
- ✅ XP & Leveling System (Phase 2)
- ✅ Streak & Shield System
- ✅ Challenge & Leaderboard System (Phase 3)
- ✅ Ghost Mode Safety Tracking
- ✅ Food Logging & Meal Tracking
- ✅ AI Exercise Recommendations
- ✅ Sleep Tracking
- ✅ Health Metrics

---

## 📂 **FILES PROVIDED**

### **1. Firestore Rules**
**File**: `firestore.rules`
- **Location**: Root directory
- **Database**: Firestore "renu"
- **Status**: ✅ Updated with Phase 1 & 2 features

### **2. Realtime Database Rules**
**File**: `database.rules.UPDATED.json`
- **Location**: Root directory
- **Database**: RTDB (asia-southeast1)
- **Status**: ✅ Updated with comprehensive coverage

---

## 🎯 **FIRESTORE RULES - KEY FEATURES**

### **Security Model**
```
Principle: User-scoped data isolation
Structure: /users/{userId}/{subcollection}/{documentId}
Access: Users can only read/write their own data
```

### **Helper Functions**
```javascript
isAuthenticated()        // User is logged in
isOwner(userId)         // User owns this data path
ownsResource()          // User owns existing document
willOwnResource()       // User will own new document
isValidStepCount(steps) // Steps between 0-100,000
isValidCalories(cal)    // Calories between 0-10,000
isValidDate(date)       // Format: YYYY-MM-DD
```

---

## 📊 **FIRESTORE COLLECTION STRUCTURE**

### **User-Scoped Collections** (under `/users/{userId}/`)

#### **1. Daily Steps** (NEW - Phase 1.1)
```javascript
Path: /users/{userId}/daily_steps/{date}

Fields:
- steps: number (0-100,000)
- timestamp: Timestamp
- source: string ("hardware_sensor")
- userId: string
- date: string (YYYY-MM-DD)

Rules:
- Read: Owner only
- Create: Owner + validation (valid steps, valid date)
- Update: Owner + validation
- Delete: Owner only

Example:
/users/abc123/daily_steps/2026-02-14
{
  steps: 8543,
  timestamp: Timestamp,
  source: "hardware_sensor",
  userId: "abc123",
  date: "2026-02-14"
}
```

#### **2. Exercise Logs** (Enhanced - Phase 1)
```javascript
Path: /users/{userId}/exercise_logs/{logId}

Fields:
- exerciseName: string
- targetMuscle: string
- bodyPart: string
- caloriesBurned: number (>= 0)
- duration: number
- timestamp: Timestamp
- source: string ("AI_Recommendation" or "Manual")
- userId: string
- date: string

Rules:
- Read: Owner only
- Create: Owner + caloriesBurned >= 0
- Update/Delete: Owner only

Example:
/users/abc123/exercise_logs/log_001
{
  exerciseName: "Cat Pose",
  targetMuscle: "Core",
  caloriesBurned: 20,
  duration: 15,
  source: "AI_Recommendation",
  timestamp: Timestamp
}
```

#### **3. Food Logs**
```javascript
Path: /users/{userId}/foodLogs/{logId}

Fields:
- foodName: string
- calories: number
- protein: number
- carbs: number
- fat: number
- mealType: string
- timestamp: Timestamp

Rules:
- Read: Owner only
- Create: Owner + willOwnResource()
- Update/Delete: Owner + ownsResource()
```

#### **4. Sleep Logs**
```javascript
Path: /users/{userId}/sleep_logs/{logId}

Fields:
- bedtime: Timestamp
- wakeTime: Timestamp
- duration: number (hours)
- quality: string
- date: string

Rules:
- Read: Owner only
- Create/Update/Delete: Owner only
```

#### **5. Weight Logs**
```javascript
Path: /users/{userId}/weightLogs/{logId}

Fields:
- weightKg: number (0-500)
- timestamp: Timestamp
- date: string

Rules:
- Read: Owner only
- Create: Owner + weightKg > 0 and < 500
- Update/Delete: Owner only
```

#### **6. Hydration Logs**
```javascript
Path: /users/{userId}/hydration_logs/{logId}

Fields:
- amountMl: number
- timestamp: Timestamp
- date: string

Rules:
- Read: Owner only
- Create/Update/Delete: Owner only
```

#### **7. Mood Logs**
```javascript
Path: /users/{userId}/mood_logs/{moodId}

Fields:
- mood: string
- timestamp: Timestamp
- notes: string

Rules:
- Read: Owner only
- Create/Update/Delete: Owner only
```

#### **8. GPS Logs** (Ghost Mode)
```javascript
Path: /users/{userId}/gps_logs/{logId}

Fields:
- latitude: number
- longitude: number
- timestamp: Timestamp
- accuracy: number

Rules:
- Read: Owner only
- Create/Update/Delete: Owner only
```

#### **9. SOS Alerts** (Emergency)
```javascript
Path: /users/{userId}/sos_alerts/{alertId}

Fields:
- location: {lat, lng}
- timestamp: Timestamp
- contactNotified: boolean
- reason: string

Rules:
- Read: Owner only
- Create/Update/Delete: Owner only
```

---

### **Global Collections** (Read-Only)

#### **1. Food Database**
```javascript
Path: /foodDatabase/{foodId}

Access:
- Read: Any authenticated user
- Write: Admin only (false)

Purpose: Barcode scanning, food search
```

#### **2. Exercise Database**
```javascript
Path: /exerciseDatabase/{exerciseId}

Access:
- Read: Any authenticated user
- Write: Admin only (false)

Purpose: AI exercise recommendations
```

#### **3. Recipe Database**
```javascript
Path: /recipeDatabase/{recipeId}

Access:
- Read: Any authenticated user
- Write: Admin only (false)

Purpose: Meal planning suggestions
```

#### **4. Festival Calendar**
```javascript
Path: /festivalCalendar/{eventId}

Access:
- Read: Any authenticated user
- Write: Admin only (false)

Purpose: Regional festival tracking
```

---

## 🔥 **REALTIME DATABASE RULES - KEY FEATURES**

### **Security Model**
```
Principle: User-scoped data + shared challenges
Structure: /users/{uid}/* and /challenges/*
Validation: Type checking + range validation
```

---

## 📊 **RTDB STRUCTURE**

### **1. User Node** (`/users/{uid}/`)

#### **Profile**
```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "age": 25,
  "gender": "male"
}

Validation: Must have ['name', 'email']
```

#### **XP System** (NEW - Phase 2)
```json
{
  "xp": 225,
  "level": 3
}

Validation:
- xp: 0 - 1,000,000
- level: 1 - 100
```

#### **Streak & Shield System** (Phase 1.2)
```json
{
  "streak": 7,
  "shields": 3,
  "lastActiveDate": "2026-02-14",
  "activeShields": [
    {
      "id": "shield_001",
      "type": "FREEZE",
      "acquiredDate": "2026-02-10",
      "expiresAt": 1708531200000
    }
  ]
}

Validation:
- streak: >= 0
- shields: 0 - 100
- lastActiveDate: string
```

#### **Step Tracking**
```json
{
  "steps": 8543
}

Validation: 0 - 100,000
```

#### **Workout Data**
```json
{
  "totalWorkoutMinutes": 150,
  "workoutHistory": {
    "session_001": {
      "id": "session_001",
      "date": "2026-02-14",
      "category": "AI Exercise",
      "duration": 15,
      "completed": true,
      "caloriesBurned": 75
    }
  }
}

Validation: totalWorkoutMinutes >= 0
```

---

### **2. Daily Activity** (`/dailyActivity/{uid}/{date}/`)

```json
{
  "steps": 8543,
  "calories": 341,
  "workout": "Completed"
}

Validation:
- steps: 0 - 100,000
- calories: >= 0
- workout: string

Rules:
- Read: Owner only
- Write: Owner only
```

---

### **3. Challenges** (`/challenges/{challengeCode}/`)

```json
{
  "id": "A3F9K2",
  "name": "7-Day Step Challenge",
  "type": "steps",
  "duration": 7,
  "creatorId": "user123",
  "createdAt": 1708012800,
  "participants": {
    "user123": true,
    "user456": true
  }
}

Validation:
- id: 6 characters
- name: 1-100 characters
- type: "steps" | "workouts" | "calories" | "xp"
- duration: 7 | 14 | 30

Rules:
- Read: Any authenticated user
- Write: Any authenticated user
- Participants: User can add themselves
```

---

### **4. Leaderboards** (`/leaderboards/{challengeCode}/`)

```json
{
  "user123": {
    "name": "John Doe",
    "totalSteps": 35000,
    "rank": 1
  },
  "user456": {
    "name": "Jane Smith",
    "totalSteps": 28000,
    "rank": 2
  }
}

Rules:
- Read: Any authenticated user
- Write: Any authenticated user (for real-time updates)
```

---

### **5. Food Database** (`/foodDatabase/`)

```json
{
  "food_001": {
    "name": "Apple",
    "calories": 52,
    "barcode": "123456789"
  }
}

Rules:
- Read: Any authenticated user
- Write: false (admin only)
- Indexes: ["barcode", "name", "category"]
```

---

### **6. Exercise Database** (`/exerciseDatabase/`)

```json
{
  "exercise_001": {
    "name": "Push-ups",
    "targetMuscle": "Chest",
    "difficulty": "Medium",
    "equipment": "None"
  }
}

Rules:
- Read: Any authenticated user
- Write: false (admin only)
- Indexes: ["targetMuscle", "difficulty", "equipment"]
```

---

## 🛡️ **SECURITY PRINCIPLES**

### **1. Authentication Required**
```javascript
// All access requires authentication
".read": "auth != null"
".write": "auth != null"
```

### **2. User Data Isolation**
```javascript
// Users can only access their own data
".read": "$uid === auth.uid"
".write": "$uid === auth.uid"
```

### **3. Data Validation**
```javascript
// Type and range checking
"xp": {
  ".validate": "newData.isNumber() && 
                newData.val() >= 0 && 
                newData.val() <= 1000000"
}
```

### **4. Structured Data Requirements**
```javascript
// Ensure required fields exist
"profile": {
  ".validate": "newData.hasChildren(['name', 'email'])"
}
```

### **5. Challenge Participation**
```javascript
// Users can add themselves to challenges
"participants": {
  "$participantId": {
    ".write": "$participantId === auth.uid || 
               data.parent().child('creatorId').val() === auth.uid"
  }
}
```

---

## 🚀 **DEPLOYMENT INSTRUCTIONS**

### **Firestore Rules Deployment**

#### **Method 1: Firebase Console**
1. Open [Firebase Console](https://console.firebase.google.com)
2. Select your project: `swasthyamitra-ded44`
3. Go to **Firestore Database** → **Rules**
4. Select database: **"renu"**
5. Copy content from `firestore.rules`
6. Click **Publish**

#### **Method 2: Firebase CLI**
```bash
# Install Firebase CLI (if not installed)
npm install -g firebase-tools

# Login
firebase login

# Deploy Firestore rules
firebase deploy --only firestore:rules
```

---

### **Realtime Database Rules Deployment**

#### **Method 1: Firebase Console**
1. Open [Firebase Console](https://console.firebase.google.com)
2. Select your project: `swasthyamitra-ded44`
3. Go to **Realtime Database** → **Rules**
4. Select database: **asia-southeast1**
5. Copy content from `database.rules.UPDATED.json`
6. Click **Publish**

#### **Method 2: Firebase CLI**
```bash
# Deploy RTDB rules
firebase deploy --only database
```

---

## 🧪 **TESTING THE RULES**

### **Test 1: User Can Read Own Data**
```javascript
// Firestore
match /users/abc123/daily_steps/2026-02-14
auth.uid = "abc123"  → ✅ ALLOW

// RTDB
/users/abc123/xp
auth.uid = "abc123"  → ✅ ALLOW
```

### **Test 2: User Cannot Read Other User's Data**
```javascript
// Firestore
match /users/xyz789/daily_steps/2026-02-14
auth.uid = "abc123"  → ❌ DENY

// RTDB
/users/xyz789/xp
auth.uid = "abc123"  → ❌ DENY
```

### **Test 3: Step Count Validation**
```javascript
// Valid
steps: 5000  → ✅ ALLOW (within range)

// Invalid
steps: 150000  → ❌ DENY (exceeds 100,000)
steps: -500    → ❌ DENY (negative)
```

### **Test 4: Challenge Participation**
```javascript
// User adds themselves
/challenges/A3F9K2/participants/abc123
auth.uid = "abc123"  → ✅ ALLOW

// User adds someone else
/challenges/A3F9K2/participants/xyz789
auth.uid = "abc123"  → ❌ DENY
```

### **Test 5: Global Database Access**
```javascript
// Read food database
/foodDatabase/food_001
auth.uid = "abc123"  → ✅ ALLOW

// Write to food database
/foodDatabase/food_002
auth.uid = "abc123"  → ❌ DENY (admin only)
```

---

## 📋 **RULES COVERAGE**

### **Firestore Collections Covered:**
```
✅ users/{userId}/daily_steps/{date}         [NEW - Phase 1]
✅ users/{userId}/exercise_logs/{logId}      [Enhanced]
✅ users/{userId}/foodLogs/{logId}
✅ users/{userId}/waterLogs/{logId}
✅ users/{userId}/hydration_logs/{logId}
✅ users/{userId}/weightLogs/{logId}
✅ users/{userId}/sleep_logs/{logId}
✅ users/{userId}/meal_feedback/{feedbackId}
✅ users/{userId}/user_preferences/{prefId}
✅ users/{userId}/health_metrics/{metricId}
✅ users/{userId}/workouts/{workoutId}
✅ users/{userId}/lifestyle/{lifestyleId}
✅ users/{userId}/mood_logs/{moodId}
✅ users/{userId}/progress/{progressId}
✅ users/{userId}/streaks/{streakId}
✅ users/{userId}/challenges/{challengeId}
✅ users/{userId}/safety_contacts/{contactId}
✅ users/{userId}/period_logs/{logId}
✅ users/{userId}/recommendations/{recId}
✅ users/{userId}/activity_logs/{logId}
✅ users/{userId}/calorie_logs/{logId}
✅ users/{userId}/gps_logs/{logId}           [Ghost Mode]
✅ users/{userId}/sos_alerts/{alertId}       [Emergency]
✅ users/{userId}/* (recursive wildcard)

Global Collections:
✅ foodDatabase/{foodId}
✅ exerciseDatabase/{exerciseId}
✅ recipeDatabase/{recipeId}
✅ festivalCalendar/{eventId}
✅ supplementDatabase/{supplementId}
✅ healthTips/{tipId}
✅ workoutTemplates/{templateId}
```

### **RTDB Nodes Covered:**
```
✅ users/{uid}/profile
✅ users/{uid}/xp                           [NEW - Phase 2]
✅ users/{uid}/level                        [NEW - Phase 2]
✅ users/{uid}/streak
✅ users/{uid}/shields                      [NEW - Phase 1.2]
✅ users/{uid}/activeShields
✅ users/{uid}/steps
✅ users/{uid}/lastActiveDate
✅ users/{uid}/workoutHistory
✅ users/{uid}/completionHistory
✅ users/{uid}/totalWorkoutMinutes
✅ dailyActivity/{uid}/{date}
✅ challenges/{challengeCode}               [NEW - Phase 3]
✅ leaderboards/{challengeCode}             [NEW - Phase 3]
✅ foodDatabase
✅ exerciseDatabase
✅ userGoals/{uid}
✅ userPreferences/{uid}
✅ notifications/{uid}
✅ safetyContacts/{uid}                     [Ghost Mode]
✅ sosAlerts/{uid}                          [Emergency]
✅ appConfig
✅ healthContent
✅ completionHistory/{uid}
✅ aiModels
✅ featureFlags
```

---

## 🔒 **SECURITY BEST PRACTICES**

### **✅ DO:**
1. Always authenticate users before any operation
2. Validate data types and ranges
3. Use user-scoped paths (`/users/{uid}/`)
4. Test rules in Firebase Console simulator
5. Use indexes for frequently queried fields
6. Keep global databases read-only from app

### **❌ DON'T:**
1. Allow unauthenticated access to user data
2. Store sensitive data without encryption
3. Allow users to access other users' data
4. Skip validation on critical fields (XP, steps, etc.)
5. Use overly permissive wildcards
6. Allow app writes to global databases

---

## 📊 **VALIDATION RULES SUMMARY**

| Field | Type | Min | Max | Notes |
|-------|------|-----|-----|-------|
| steps | number | 0 | 100,000 | Daily limit |
| xp | number | 0 | 1,000,000 | Total XP earned |
| level | number | 1 | 100 | Calculated from XP |
| shields | number | 0 | 100 | Max shields allowed |
| calories | number | 0 | 10,000 | Daily limit |
| weightKg | number | 0 | 500 | Reasonable range |
| streak | number | 0 | ∞ | No upper limit |
| challengeCode | string | 6 | 6 | Exactly 6 chars |
| challengeName | string | 1 | 100 | Reasonable name |
| duration | number | - | - | 7, 14, or 30 only |
| type | string | - | - | Enum validation |

---

## 🎯 **IMPLEMENTATION STATUS**

### **Firestore Rules:**
✅ **DEPLOYED** - Updated with Phase 1 & 2 features  
📄 **File**: `firestore.rules`  
🔗 **Database**: Firestore "renu"

### **RTDB Rules:**
✅ **READY** - Updated with comprehensive coverage  
📄 **File**: `database.rules.UPDATED.json`  
🔗 **Database**: RTDB (asia-southeast1)  
⏳ **Status**: Needs deployment

---

## 🚨 **DEPLOYMENT CHECKLIST**

### **Before Deployment:**
- [ ] Backup existing rules
- [ ] Review all rule changes
- [ ] Test in Firebase Console simulator
- [ ] Check for syntax errors

### **After Deployment:**
- [ ] Verify app still works
- [ ] Test user data access
- [ ] Test challenge creation/join
- [ ] Test step logging
- [ ] Monitor Firebase Console for errors

---

## 📝 **MAINTENANCE**

### **When Adding New Features:**
1. Add collection to appropriate section
2. Define access rules (read/write)
3. Add validation if needed
4. Test in simulator
5. Deploy rules
6. Update this documentation

### **Regular Reviews:**
- Monthly: Check for unused rules
- Quarterly: Review validation ranges
- Annually: Security audit

---

## 🎊 **SUMMARY**

**Status**: ✅ **PRODUCTION-READY RULES PROVIDED**

**Coverage**:
- ✅ Firestore: 25+ subcollections + 7 global collections
- ✅ RTDB: 20+ nodes with full validation
- ✅ All Phase 1 & 2 features covered
- ✅ Challenge system ready (Phase 3)
- ✅ Ghost Mode security included

**Security**:
- ✅ User data isolation enforced
- ✅ Type and range validation
- ✅ Authentication required
- ✅ Admin-only global databases

**Next Steps**:
1. Deploy Firestore rules (already updated)
2. Deploy RTDB rules (use `database.rules.UPDATED.json`)
3. Test all features
4. Monitor for any access denied errors

🔐 **Your app is now secure with comprehensive Firebase Rules!** 🔐

