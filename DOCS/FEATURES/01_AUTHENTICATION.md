# 🔐 Authentication & Onboarding System

## Overview

The Authentication & Onboarding system handles user registration, login, and initial profile setup through a multi-step onboarding flow.

---

## 🎯 Features

- Email/Password authentication via Firebase
- Multi-step onboarding (Personal Info → Goal → Lifestyle)
- Auto-login for returning users
- Profile completion tracking
- Secure user data storage

---

## 📁 Code Files

### Activities
- `MainActivity.kt` - Entry point, checks auth status
- `LoginActivity.kt` - User login
- `SignupActivity.kt` - New user registration
- `UserInfoActivity.kt` - Personal information (Step 1)
- `InsertGoalActivity.kt` - Health goal selection (Step 2)
- `LifestyleActivity.kt` - Lifestyle preferences (Step 3)

### Supporting Classes
- `auth/FirebaseAuthHelper.kt` - Authentication wrapper
- `UserApplication.kt` - App initialization

### Layouts
- `activity_main.xml` - Welcome screen
- `activity_login.xml` - Login form
- `activity_signup.xml` - Registration form
- `activity_user_info.xml` - Personal details form
- `activity_insert_goal.xml` - Goal selection screen
- `activity_lifestyle.xml` - Lifestyle preferences

---

## 🔄 User Flow

### New User
```
1. App Launch → MainActivity
2. Welcome Screen → "Start" button
3. LoginActivity → "Sign Up" link
4. SignupActivity → Enter email, password, confirm
5. UserInfoActivity → Name, age, gender, height, weight
6. InsertGoalActivity → Select goal (Weight Loss/Gain/Maintain)
7. LifestyleActivity → Activity level, diet preference, sleep schedule
8. Homepage → Start using app
```

### Returning User
```
1. App Launch → MainActivity
2. Auto-login check (PROFILE_COMPLETED flag)
3. If logged in + profile complete → Homepage directly
4. If profile incomplete → Resume onboarding at last step
5. If not logged in → LoginActivity
```

---

## 🗄️ Database Schema

### Firestore Collection: `users/{userId}`
```javascript
{
  // Basic Info (from SignupActivity)
  email: string,
  createdAt: timestamp,
  
  // Personal Info (from UserInfoActivity)
  name: string,
  age: number,
  gender: string,              // Male/Female/Other
  height: number,              // cm
  weight: number,              // kg
  
  // Goal (from InsertGoalActivity)
  // Stored in subcollection: users/{userId}/goals/{goalId}
  
  // Lifestyle (from LifestyleActivity)
  // Stored in subcollection: users/{userId}/lifestyle/{lifestyleId}
  
  // Additional fields
  avatar: string,              // Avatar ID
  isOnPeriod: boolean,         // For women's health
  lastLogin: timestamp
}
```

### Goals Subcollection: `users/{userId}/goals/{goalId}`
```javascript
{
  goalType: string,            // "Weight Loss", "Weight Gain", "Maintain", "No Goal"
  targetWeight: number,        // Target weight in kg
  dailyCalories: number,       // Calculated calorie target
  bmr: number,                 // Basal Metabolic Rate
  tdee: number,                // Total Daily Energy Expenditure
  createdAt: timestamp
}
```

### Lifestyle Subcollection: `users/{userId}/lifestyle/{lifestyleId}`
```javascript
{
  activityLevel: string,       // "Sedentary", "Lightly Active", "Moderately Active", "Very Active"
  dietPreference: string,      // "Vegetarian", "Non-Vegetarian", "Vegan"
  wakeTime: string,            // HH:MM format
  sleepTime: string,           // HH:MM format
  availableExerciseTime: string,  // "15m", "30m", "45m", "60m"
  preferredExerciseTime: string,  // "Morning", "Afternoon", "Evening", "Night"
  targetWeight: number,
  dailyCalories: number,
  bmr: number,
  tdee: number,
  createdAt: timestamp
}
```

---

## 💻 Key Code Implementation

### FirebaseAuthHelper.kt - Core Authentication

```kotlin
class FirebaseAuthHelper(private val context: Context) {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance("renu")
    
    // Login
    suspend fun loginUser(email: String, password: String): Result<FirebaseUser>
    
    // Signup
    suspend fun signUpUser(email: String, password: String): Result<FirebaseUser>
    
    // Save user data
    suspend fun saveUserData(userId: String, name: String, age: Int, 
                           gender: String, height: Double, weight: Double): Result<Unit>
    
    // Save goal
    suspend fun insertGoal(userId: String, goalType: String, 
                         targetWeight: Double, currentWeight: Double): Result<Unit>
    
    // Update with lifestyle
    suspend fun updateGoalWithCalories(userId: String, activityLevel: String,
                                      dietPreference: String, ...): Result<Unit>
    
    // Check profile completion
    suspend fun hasUserGoal(userId: String): Result<Boolean>
    suspend fun hasLifestyleData(userId: String): Result<Boolean>
}
```

### LoginActivity.kt - Login Logic

```kotlin
class LoginActivity : AppCompatActivity() {
    private fun handleLogin() {
        val email = emailInput.text.toString()
        val password = passwordInput.text.toString()
        
        lifecycleScope.launch {
            val result = authHelper.loginUser(email, password)
            result.onSuccess { user ->
                saveUserId(user.uid)
                checkUserProfileAndNavigate(user.uid)
            }.onFailure { error ->
                showErrorMessage(error)
            }
        }
    }
    
    private fun checkUserProfileAndNavigate(userId: String) {
        // Check profile completion
        // Navigate to appropriate screen:
        // - Homepage if complete
        // - UserInfoActivity if missing basic data
        // - InsertGoalActivity if missing goal
        // - LifestyleActivity if missing lifestyle
    }
}
```

### LifestyleActivity.kt - Calorie Calculation

```kotlin
private fun calculateDailyCalories() {
    // BMR Calculation (Mifflin-St Jeor Equation)
    val bmr = if (gender == "Male") {
        (10 * weight) + (6.25 * height) - (5 * age) + 5
    } else {
        (10 * weight) + (6.25 * height) - (5 * age) - 161
    }
    
    // TDEE (Total Daily Energy Expenditure)
    val activityMultiplier = when (selectedActivityLevel) {
        "Sedentary" -> 1.2
        "Lightly Active" -> 1.375
        "Moderately Active" -> 1.55
        "Very Active" -> 1.725
        else -> 1.2
    }
    val tdee = bmr * activityMultiplier
    
    // Adjust for goal
    val dailyCalories = when (selectedGoalType) {
        "Weight Loss" -> tdee - 500  // 500 cal deficit
        "Weight Gain" -> tdee + 500  // 500 cal surplus
        else -> tdee                 // Maintenance
    }
    
    return dailyCalories
}
```

---

## 🎨 UI/UX Details

### Welcome Screen (MainActivity)
- Gradient background with app logo
- "Start" button to begin
- Auto-login logic runs on launch

### Login Screen
- Email and password fields
- "Sign In" button
- "Sign Up" link for new users
- Error messages for invalid credentials

### Signup Screen
- Email, password, confirm password fields
- Validation:
  - Valid email format
  - Password min 6 characters
  - Passwords match
- Creates Firebase account

### UserInfoActivity (Step 1 of 3)
- Name input
- Age number picker
- Gender selection (Male/Female/Other cards)
- Height in cm
- Weight in kg
- "Next" button

### InsertGoalActivity (Step 2 of 3)
- Four goal cards:
  - Weight Loss
  - Maintain Weight
  - Weight Gain
  - No Goal (Stay Healthy)
- Visual selection with highlighting
- "Next" button

### LifestyleActivity (Step 3 of 3)
- Activity level cards (4 options)
- Diet preference cards (3 options)
- Wake/Sleep time pickers
- Available exercise time (4 durations)
- Preferred exercise time (4 times of day)
- Shows calculated daily calorie target
- "Complete Profile" button → Homepage

---

## 🔐 Security

### Authentication Rules
```javascript
// firestore.rules
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    match /users/{userId} {
      // Users can only read/write their own data
      allow read, write: if request.auth != null && request.auth.uid == userId;
      
      // Subcollections inherit same rules
      match /{document=**} {
        allow read, write: if request.auth != null && request.auth.uid == userId;
      }
    }
  }
}
```

### SharedPreferences (Local Storage)
```kotlin
// Stored locally for auto-login
val sharedPrefs = getSharedPreferences("UserPreferences", MODE_PRIVATE)
sharedPrefs.edit().apply {
    putString("USER_ID", userId)
    putBoolean("PROFILE_COMPLETED", true)
    apply()
}
```

---

## 🔧 How It Works

### 1. App Launch
```kotlin
// MainActivity.onCreate()
val sharedPrefs = getSharedPreferences("UserPreferences", MODE_PRIVATE)
val userId = sharedPrefs.getString("USER_ID", "")
val profileCompleted = sharedPrefs.getBoolean("PROFILE_COMPLETED", false)

if (userId.isEmpty()) {
    // Not logged in → Show welcome screen
    showWelcomeScreen()
} else if (profileCompleted) {
    // Logged in + complete → Go to homepage
    navigateToHomePage(userId)
} else {
    // Logged in but incomplete → Check what's missing
    checkProfileOnServer(userId)
}
```

### 2. Profile Completion Check
```kotlin
suspend fun checkProfileOnServer(userId: String) {
    val userData = authHelper.getUserData(userId)
    val hasGoal = authHelper.hasUserGoal(userId)
    val hasLifestyle = authHelper.hasLifestyleData(userId)
    
    when {
        !userData.success -> navigateToUserInfo(userId)
        !hasGoal -> navigateToInsertGoal(userId)
        !hasLifestyle -> navigateToLifestyle(userId)
        else -> {
            setProfileCompleted()
            navigateToHomePage(userId)
        }
    }
}
```

### 3. BMR/TDEE Calculation
**Formulas Used**:
- **BMR** (Basal Metabolic Rate): Mifflin-St Jeor Equation
  - Men: BMR = (10 × weight in kg) + (6.25 × height in cm) - (5 × age) + 5
  - Women: BMR = (10 × weight in kg) + (6.25 × height in cm) - (5 × age) - 161

- **TDEE** (Total Daily Energy Expenditure): BMR × Activity Factor
  - Sedentary: BMR × 1.2
  - Lightly Active: BMR × 1.375
  - Moderately Active: BMR × 1.55
  - Very Active: BMR × 1.725

- **Daily Calories** (based on goal):
  - Weight Loss: TDEE - 500
  - Weight Gain: TDEE + 500
  - Maintain: TDEE

---

## 🎯 How to Use (User Guide)

### First Time Setup
1. **Open App** → See welcome screen
2. **Click "Start"** → Opens login
3. **Click "Sign Up"** → Create account
   - Enter email
   - Create password (min 6 characters)
   - Confirm password
   - Click "Sign Up"

4. **Enter Personal Info** (Step 1)
   - Full name
   - Age
   - Select gender
   - Height in cm
   - Weight in kg
   - Click "Next"

5. **Select Health Goal** (Step 2)
   - Choose: Weight Loss/Gain/Maintain/No Goal
   - Click "Next"

6. **Set Lifestyle** (Step 3)
   - Activity level (how active you are)
   - Diet preference (Veg/Non-Veg/Vegan)
   - Wake and sleep times
   - Exercise time available
   - Preferred exercise time
   - Click "Complete Profile"

7. **Start Using!** → Homepage opens
   - Your profile is now complete
   - Daily calorie target calculated
   - Ready to log food, exercise, etc.

### Logging In Again
1. **Open App** → Auto-login happens
2. **Goes directly to Homepage** (no need to login again)
3. **Logout**: Profile → Settings → Logout

### Updating Profile
1. Homepage → Profile icon
2. Edit any field
3. Changes save automatically

---

## 🐛 Troubleshooting

### Login Fails
**Problem**: "Invalid email or password"  
**Solution**: 
- Check email format is valid
- Password is correct
- Try "Forgot Password" (if implemented)
- Check internet connection

### Signup Fails
**Problem**: "Email already in use"  
**Solution**: Email is registered, use Login instead

**Problem**: "Weak password"  
**Solution**: Use at least 6 characters

### Profile Not Saving
**Problem**: Data doesn't persist  
**Solution**:
- Check Firebase rules are deployed
- Verify internet connection
- Check Firestore console for saved data

### Auto-login Not Working
**Problem**: Always shows login screen  
**Solution**:
- Check PROFILE_COMPLETED flag is set after onboarding
- Verify USER_ID is saved in SharedPreferences
- Clear app data and re-onboard

### Calorie Calculation Seems Wrong
**Problem**: Unexpected daily calorie target  
**Solution**:
- Verify height/weight/age are correct
- Check activity level matches your lifestyle
- BMR/TDEE formulas are standard medical equations
- Consult with healthcare provider for personalized needs

---

## 📊 Analytics

Track these metrics:
- **Signup Rate**: New users per day
- **Completion Rate**: Users who finish onboarding
- **Drop-off Points**: Where users abandon onboarding
- **Auto-login Success**: Returning users who auto-login
- **Profile Updates**: How often users edit profile

---

## 🔄 Related Features

- **Profile Management**: View/edit profile later
- **Settings**: Logout, notifications, preferences
- **Avatar Customization**: Change profile picture

---

## 📱 Technology Used

- **Firebase Authentication**: Email/password auth
- **Cloud Firestore**: User data storage
- **SharedPreferences**: Local storage for auto-login
- **Kotlin Coroutines**: Asynchronous operations
- **Material Design**: UI components
- **View Binding**: Type-safe view access

---

*Last Updated: February 12, 2026*

