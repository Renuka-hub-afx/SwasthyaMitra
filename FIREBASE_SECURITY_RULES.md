# Firebase Firestore Security Rules - FIX FOR PERMISSION_DENIED ERROR

## ⚠️ CRITICAL: Apply These Rules to Fix Food Logging

The "PERMISSION_DENIED" error occurs because your Firestore security rules are blocking writes to the `foodLogs` collection.

## 📋 Steps to Fix:

### 1. Go to Firebase Console
1. Open [Firebase Console](https://console.firebase.google.com/)
2. Select your project: **SwasthyaMitra**
3. Click **Firestore Database** in the left menu
4. Click the **Rules** tab

### 2. Replace Existing Rules with These:

```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    
    // Users collection - read/write only own document
    match /users/{userId} {
      allow read: if request.auth != null && request.auth.uid == userId;
      allow write: if request.auth != null && request.auth.uid == userId;
      allow create: if request.auth != null && request.auth.uid == userId;
    }
    
    // Food Logs collection - read/write only own logs
    match /foodLogs/{logId} {
      allow read: if request.auth != null && request.auth.uid == resource.data.userId;
      allow write: if request.auth != null && request.auth.uid == request.resource.data.userId;
      allow create: if request.auth != null && request.auth.uid == request.resource.data.userId;
      allow delete: if request.auth != null && request.auth.uid == resource.data.userId;
    }
    
    // Goals collection - read/write only own goals
    match /goals/{goalId} {
      allow read: if request.auth != null && request.auth.uid == resource.data.userId;
      allow write: if request.auth != null && request.auth.uid == request.resource.data.userId;
      allow create: if request.auth != null && request.auth.uid == request.resource.data.userId;
      allow delete: if request.auth != null && request.auth.uid == resource.data.userId;
    }
    
    // Deny all other access
    match /{document=**} {
      allow read, write: if false;
    }
  }
}
```

### 3. Click "Publish" Button

### 4. Wait 1-2 minutes for rules to propagate

---

## 🧪 Testing After Applying Rules:

1. **Uninstall the app** from your phone (to clear any cached permissions)
2. **Reinstall the app**
3. **Log in** with your credentials
4. **Try scanning a barcode** or **manually adding food**
5. You should now see **"✅ Food logged successfully!"**

---

## 🔍 What These Rules Do:

### Users Collection
- Users can only read/write their own profile document
- Each user's document ID must match their Firebase Auth UID

### Food Logs Collection
- Users can only:
  - **Read** food logs where `userId` matches their own UID
  - **Create** new food logs with their own `userId`
  - **Update** their own food logs
  - **Delete** their own food logs
- Prevents users from seeing or modifying other users' food logs

### Goals Collection
- Same security model as food logs
- Users can only access their own goals

---

## ⚠️ Common Issues:

### Issue 1: Still Getting PERMISSION_DENIED
**Solution:**
1. Make sure you clicked "Publish" after pasting the rules
2. Wait 1-2 minutes
3. Force close the app and reopen
4. If still failing, uninstall and reinstall the app

### Issue 2: "User not logged in" Error
**Solution:**
1. Check if Firebase Authentication is enabled
2. Make sure you're logged in before trying to add food
3. Check MainActivity's `checkAutoLogin()` is working

### Issue 3: Rules Applied But Still Failing
**Solution:**
1. Check the Firestore collection name is exactly `foodLogs` (case-sensitive)
2. Verify the `userId` field in your food log document matches your auth UID
3. Check Firebase Console > Authentication > Users to see your UID

---

## 📊 Expected Document Structure:

### foodLogs Collection:
```json
{
  "logId": "auto-generated-by-firestore",
  "userId": "user-firebase-auth-uid",
  "foodName": "Rice",
  "barcode": "1234567890123",
  "photoUrl": null,
  "calories": 205,
  "protein": 4.3,
  "carbs": 45.0,
  "fat": 0.4,
  "servingSize": "1 cup (150g)",
  "mealType": "Lunch",
  "timestamp": 1704298800000,
  "date": "2026-01-03"
}
```

### users Collection:
```json
{
  "userId": "firebase-auth-uid",
  "email": "user@example.com",
  "userName": "John Doe",
  "age": 25,
  "height": 170.0,
  "weight": 70.0,
  "gender": "Male",
  "eatingPreference": "Non-Vegetarian",
  "createdAt": 1704298800000
}
```

---

## ✅ Verification Checklist:

After applying rules, verify in Firebase Console:

1. ✅ Rules are published (green checkmark next to "Publish" button)
2. ✅ Authentication is enabled (Email/Password + Google Sign-In)
3. ✅ Firestore Database is created (in production mode recommended)
4. ✅ You can see your user document in `users/{yourUserId}` collection
5. ✅ Test creating a food log from the app
6. ✅ Check if food log appears in `foodLogs` collection in Firebase Console

---

## 🔒 Security Explanation:

These rules ensure:
- ✅ **Authentication Required**: Only logged-in users can access data
- ✅ **User Isolation**: Users can only see their own data
- ✅ **Write Protection**: Users can't modify other users' data
- ✅ **Data Integrity**: `userId` field must match authenticated user
- ✅ **Default Deny**: Any collection not explicitly allowed is denied

---

## 🚨 If You Need Open Access (NOT RECOMMENDED for Production):

**⚠️ WARNING: Only use for testing, NOT for production!**

```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    match /{document=**} {
      allow read, write: if request.auth != null;
    }
  }
}
```

This allows any authenticated user to read/write ALL data. **Use secure rules above instead!**

---

## 📝 Summary:

1. Copy the secure rules from this document
2. Paste in Firebase Console > Firestore > Rules
3. Click "Publish"
4. Wait 1-2 minutes
5. Uninstall and reinstall app
6. Test food logging

Your food logging should now work! ✅
