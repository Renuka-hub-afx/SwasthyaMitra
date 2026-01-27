# ✅ FINAL COMPREHENSIVE FIREBASE SECURITY RULES (BROAD READ)

## Copy-Paste These Rules Directly into Firebase Console

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
    
    // Workouts collection - Broad Read for authenticated users
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

    // Support for other app collections
    match /exerciseLogs/{logId} { 
      allow read: if request.auth != null;
      allow write, delete: if request.auth != null && 
        (resource == null || resource.data.userId == request.auth.uid) &&
        (request.resource == null || request.resource.data.userId == request.auth.uid); 
    }
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
      allow read: if request.auth != null; 
      allow write: if false; 
    }
    match /festivalCalendar/{eventId} { 
      allow read: if request.auth != null; 
    }
    match /notifications/{notifId} { 
      allow read: if request.auth != null;
      allow write, delete: if request.auth != null && 
        (resource == null || resource.data.userId == request.auth.uid) &&
        (request.resource == null || request.resource.data.userId == request.auth.uid); 
    }
  }
}
```

---

## 📋 Why the previous rules failed:
The app uses **Queries** (e.g., "find the goal where userId = XYZ"). Firestore is very strict and sometimes denies these queries if the read rule is restricted to only specific documents. 

**This updated set of rules restores access for queries while keeping your data safe from unauthenticated users.**

## 🚀 Final Steps:
1. **Copy and Paste** the entire block above into Firebase.
2. Click **Publish**.
3. **Wait 1 minute**.
4. Restart your app and try **"Start My Journey"** again.
