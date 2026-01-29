# Feature: Authentication & User Management

## 🔐 Overview
The Authentication system is the gateway to SwasthyaMitra. It doesn't just manage logins; it orchestrates the entire user state, handling the transition from a new signup to a fully onboarded user with personalized goals.

---

## 🛠️ File Architecture

### **1. The Gateway (UI Layer)**
- **`LoginActivity.kt`**: Handles user login and intelligent redirection based on profile completeness.
- **`SignupActivity.kt`**: Manages new user registration, including age calculation from Date of Birth.
- **`activity_login.xml` & `activity_signup.xml`**: The UI structures for credential entry.

### **2. The Middleman (Auth Logic)**
- **`FirebaseAuthHelper.kt`**: The singleton-style utility that wraps all Firebase Auth and Firestore user initialization logic.

### **3. The Data (Storage)**
- **Firebase Authentication**: Stores the secure credentials (UID, Email, Password hash).
- **Cloud Firestore (`users` collection)**: Stores the linkable profile data (Name, Height, Weight, Age).

---

## 🧠 Core Logic & Implementation

### **1. Secure User Initialization**
When a user signs up, the app doesn't just create an auth account; it immediately initializes a Firestore document to track their health journey.

```kotlin
// From FirebaseAuthHelper.kt
suspend fun signUpWithEmail(email: String, password: String, name: String, phoneNumber: String, age: Int): Result<FirebaseUser> {
    val result = auth.createUserWithEmailAndPassword(email, password).await()
    val user = result.user
    if (user != null) {
        val userData = hashMapOf(
            "userId" to user.uid,
            "name" to name,
            "email" to email,
            "age" to age,
            "createdAt" to System.currentTimeMillis()
        )
        firestore.collection("users").document(user.uid).set(userData).await()
    }
    return Result.success(user!!)
}
```

### **2. Multi-Stage Stage Onboarding Logic**
One of the most critical parts of the logic is the **Navigation Engine** found in `LoginActivity.kt`. It ensures that a user cannot see the homepage until they have completed their profile, set a goal, and configured their lifestyle.

```kotlin
// Logic from checkUserProfileAndNavigate()
when {
    // Profile, Goal, and Lifestyle are all complete
    height > 0 && weight > 0 && hasGoal && hasLifestyle -> navigateToHomePage(userId)
    
    // Missing Lifestyle data
    height > 0 && weight > 0 && hasGoal && !hasLifestyle -> navigateToLifestyle(userId)
    
    // Missing Goal data
    height > 0 && weight > 0 && !hasGoal -> navigateToInsertGoal(userId)
    
    // New user with no physical stats
    else -> navigateToUserInfo(userId)
}
```

### **3. Dynamic Age Calculation**
During signup, the app calculates age on-the-fly from the selected birth date to ensure metabolic accuracy later in the diet engine.

```kotlin
// From SignupActivity.kt
private fun calculateAge(dob: String): Int {
    val birthDate = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).parse(dob)
    val today = Calendar.getInstance()
    val birth = Calendar.getInstance().apply { time = birthDate }
    var age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR)
    if (today.get(Calendar.DAY_OF_YEAR) < birth.get(Calendar.DAY_OF_YEAR)) age--
    return age
}
```

---

## ✅ Security Features
- **Session Persistence**: Users stay logged in across app restarts thanks to Firebase's internal token management.
- **Identity Isolation**: The `userId` (UID) is used as a foreign key across all collections (`foodLogs`, `waterLogs`, `goals`), ensuring one user can never see another user's data.
- **Input Sanitization**: Strict regex validation for emails and minimum character requirements for passwords.
