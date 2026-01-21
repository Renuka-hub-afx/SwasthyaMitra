# Food Logging Permission Denied - Troubleshooting Guide

## 🚨 Issue: Selected Food Not Showing in Food Logs

You're seeing this error at the bottom of the Food Diary screen:
```
Error loading logs: PERMISSION_DENIED: Missing or insu...
```

This means the app **cannot read** food logs from Firebase Firestore due to a permissions issue.

---

## 🔍 Root Causes (Most Likely)

### 1. **Firebase Rules Not Deployed** (90% of cases)
The Firestore security rules in your project exist locally but may not be deployed to Firebase.

### 2. **User Not Logged In** (5% of cases)
The user session may have expired or authentication failed.

### 3. **Internet Connection** (5% of cases)
No connection to Firebase servers.

---

## ✅ SOLUTION: Deploy Firebase Rules

### Step 1: Open Firebase Console
1. Go to: https://console.firebase.google.com/
2. Select project: **swasthyamitra-c0899**

### Step 2: Navigate to Firestore Database
1. Click **Firestore Database** in the left sidebar
2. Click **Rules** tab at the top

### Step 3: Check Current Rules
You should see rules that look like this. If they're **missing** or **different**, follow Step 4:

\`\`\`javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    // Food Logs collection
    match /foodLogs/{logId} {
      allow read, write: if request.auth != null && 
        (resource == null || resource.data.userId == request.auth.uid) &&
        (request.resource == null || request.resource.data.userId == request.auth.uid);
    }
    
    // Users collection
    match /users/{userId} {
      allow read, write: if request.auth != null && request.auth.uid == userId;
    }
    
    // Other collections...
  }
}
\`\`\`

### Step 4: Copy & Deploy Rules
1. Open the file: `SwasthyaMitra/firestore.rules` in your project
2. **Copy ALL the content** from that file
3. Go back to Firebase Console → Firestore Database → Rules tab
4. **Select all** existing text (Ctrl+A / Cmd+A)
5. **Paste** the copied rules
6. Click **Publish** button (top right)
7. Wait for "✅ Rules deployed successfully" message

### Step 5: Wait & Test
1. **Wait 2-3 minutes** for rules to propagate globally
2. **Force close** your app completely
3. **Reopen** the app
4. **Try adding food again**

---

## 🧪 Testing After Fix

### Test 1: Check User is Logged In
1. Open app
2. Check if you see your name/email on homepage
3. If not logged in → Log in first

### Test 2: Add Food
1. Homepage → Click Food Diary icon
2. Click **+** button (bottom right)
3. Choose any option (Manual/Search/Barcode)
4. Try adding a food item
5. **Expected result:** "✅ Food logged successfully!"
6. Food should appear in the list immediately

### Test 3: Check Firebase Console
1. Firebase Console → Firestore Database → **Data** tab
2. Click `foodLogs` collection
3. You should see your logged food documents with:
   - `userId` matches your Auth UID
   - `foodName`, `calories`, `protein`, etc. populated
   - `date` in YYYY-MM-DD format
   - `timestamp` as number

---

## 🔧 Alternative: Deploy via Firebase CLI

If you have Firebase CLI installed:

\`\`\`bash
cd SwasthyaMitra
firebase deploy --only firestore:rules
\`\`\`

---

## 🐛 Still Not Working? Advanced Debugging

### Check 1: Verify User Authentication
Open the app and check the Android Logcat for this message:
\`\`\`
FirebaseAuthHelper: Logging food: [Food Name] for user [USER_ID] on date [DATE]
\`\`\`

If you see "User not logged in" → **Log in again**

### Check 2: Verify Firebase Rules Applied
Firebase Console → Firestore Database → Rules tab:
- Check the "Last published" timestamp (should be recent)
- Rules should show green checkmark ✅

### Check 3: Check Internet Connection
- Ensure device/emulator has internet access
- Try opening a browser to verify

### Check 4: Check Firebase Project ID
In `app/google-services.json`, verify:
\`\`\`json
{
  "project_info": {
    "project_id": "swasthyamitra-c0899"
  }
}
\`\`\`

Should match the project you're deploying rules to.

---

## 📱 What I've Fixed in the Code

### 1. Better Error Messages
The app now shows:
- "⚠️ Not logged in. Please log in first."
- "❌ PERMISSION_DENIED - Check Firebase rules"
- "❌ No internet connection"

### 2. Added Logging
Android Logcat will show detailed logs:
\`\`\`
D/FirebaseAuthHelper: Logging food: Rice for user abc123 on date 2026-01-20
D/FirebaseAuthHelper: Food logged successfully with ID: xyz789
\`\`\`

Or if error:
\`\`\`
E/FirebaseAuthHelper: Error logging food
\`\`\`

### 3. User Authentication Check
App now verifies user is logged in before attempting to save/load food logs.

---

## 📊 Expected Behavior After Fix

### When Adding Food:
1. Select food from search/manual entry
2. Enter serving size
3. Select meal type
4. Click "Save" or "Add Food Log"
5. ✅ Toast: "Food logged successfully!"
6. Food appears in the list immediately
7. Nutrition summary updates (calories, protein, carbs, fat)

### When Viewing Food Logs:
1. Open Food Diary screen
2. Today's Nutrition shows totals (not 0s)
3. Food History shows all logged foods for today
4. Sorted by time (newest first)

---

## 🔒 Security Note

The Firebase rules ensure:
- Users can **only read their own** food logs
- Users can **only write their own** food logs
- `userId` in the document **must match** the authenticated user's UID
- No one can access other users' data

---

## ✅ Quick Checklist

- [ ] Firebase rules deployed to Console
- [ ] Waited 2-3 minutes after deployment
- [ ] User is logged in (see name on homepage)
- [ ] Internet connection working
- [ ] App completely restarted (force close + reopen)
- [ ] Firebase project ID matches in google-services.json

---

## 📞 Next Steps

1. **Deploy the Firebase rules** (Step-by-Step above)
2. **Restart the app**
3. **Try adding food**
4. If still not working, check Android Logcat for error messages

The enhanced error messages I've added will help pinpoint the exact issue!

---

**Last Updated:** January 20, 2026  
**Status:** Code fixed with better diagnostics  
**Action Required:** Deploy Firebase rules to Console
