# AI Exercise Recommendation - Technical Implementation Guide

## 🏗️ Architecture Overview

```
┌─────────────────────────────────────────────────────────────┐
│                    Workout Dashboard UI                      │
│  (WorkoutDashboardActivity.kt)                              │
└──────────────────┬──────────────────────────────────────────┘
                   │
                   │ User clicks "AI Exercise 🤖"
                   │
                   ▼
┌─────────────────────────────────────────────────────────────┐
│          AI Exercise Recommendation Service                  │
│  (AIExerciseRecommendationService.kt)                       │
│                                                              │
│  ┌────────────────────────────────────────────────────┐    │
│  │  1. Load Exercise Database (3 sources)             │    │
│  │     - JSON (Gym exercises)                         │    │
│  │     - Folder (Yoga poses)                          │    │
│  │     - CSV (Cardio activities)                      │    │
│  └────────────────────────────────────────────────────┘    │
│                   │                                          │
│                   ▼                                          │
│  ┌────────────────────────────────────────────────────┐    │
│  │  2. Fetch User Context                             │    │
│  │     - Firebase Auth (userId)                       │    │
│  │     - Firestore (profile, goals, period status)    │    │
│  │     - RTDB (workout history)                       │    │
│  │     - Food logs (calorie balance)                  │    │
│  │     - Mood tracking                                │    │
│  └────────────────────────────────────────────────────┘    │
│                   │                                          │
│                   ▼                                          │
│  ┌────────────────────────────────────────────────────┐    │
│  │  3. Filter Exercises                               │    │
│  │     - Period mode: Only gentle exercises           │    │
│  │     - Age appropriate: Safety filters              │    │
│  │     - Has images: Quality control                  │    │
│  └────────────────────────────────────────────────────┘    │
│                   │                                          │
│                   ▼                                          │
│  ┌────────────────────────────────────────────────────┐    │
│  │  4. Generate AI Prompt                             │    │
│  │     - User details (age, gender, weight, goals)    │    │
│  │     - Environmental (time, mood, calories)         │    │
│  │     - Available exercises list                     │    │
│  │     - Safety constraints                           │    │
│  └────────────────────────────────────────────────────┘    │
│                   │                                          │
│                   ▼                                          │
│  ┌────────────────────────────────────────────────────┐    │
│  │  5. Call Gemini 2.0 Flash                          │    │
│  │     - Firebase AI SDK                              │    │
│  │     - Timeout: 45 seconds                          │    │
│  │     - Response format: JSON                        │    │
│  └────────────────────────────────────────────────────┘    │
│                   │                                          │
│                   ▼                                          │
│  ┌────────────────────────────────────────────────────┐    │
│  │  6. Parse & Validate Response                      │    │
│  │     - JSON parsing with error handling             │    │
│  │     - Map exercise names to GIF paths              │    │
│  │     - Ensure minimum 3 exercises                   │    │
│  │     - Add fallback exercises if needed             │    │
│  └────────────────────────────────────────────────────┘    │
│                   │                                          │
└───────────────────┼──────────────────────────────────────────┘
                    │
                    │ Return List<ExerciseRec>
                    │
                    ▼
┌─────────────────────────────────────────────────────────────┐
│              Display Exercise Card (UI)                      │
│  - Show exercise details with GIF                           │
│  - Display instructions, tips, mistakes                     │
│  - Handle "Done" and "Skip" actions                         │
└──────────────────┬──────────────────────────────────────────┘
                   │
                   │ User clicks "I DID IT!"
                   │
                   ▼
┌─────────────────────────────────────────────────────────────┐
│              Firebase Logging (Dual Write)                   │
│                                                              │
│  ┌────────────────────┐    ┌─────────────────────────┐    │
│  │   Firestore (renu)  │    │  Realtime Database      │    │
│  │  exercise_logs      │    │  workoutHistory         │    │
│  │  (for homepage)     │    │  (for workout stats)    │    │
│  └────────────────────┘    └─────────────────────────┘    │
└─────────────────────────────────────────────────────────────┘
```

---

## 📂 File Structure

```
SwasthyaMitra/
├── app/src/main/
│   ├── java/com/example/swasthyamitra/
│   │   ├── WorkoutDashboardActivity.kt       # Main UI for workout section
│   │   ├── ai/
│   │   │   └── AIExerciseRecommendationService.kt  # AI logic and exercise generation
│   │   ├── auth/
│   │   │   └── FirebaseAuthHelper.kt          # User data retrieval
│   │   └── repository/
│   │       └── MoodRepository.kt              # Mood tracking integration
│   ├── res/
│   │   ├── layout/
│   │   │   └── activity_workout_dashboard.xml  # Workout UI layout
│   │   ├── drawable/
│   │   │   ├── ic_ai.xml                      # AI button icon
│   │   │   ├── button_background.xml          # Gradient button background
│   │   │   └── background_main.xml            # App background
│   │   └── values/
│   │       ├── colors.xml                     # Theme colors
│   │       └── strings.xml                    # Text resources
│   └── assets/
│       ├── exercisedb_v1_sample/
│       │   ├── exercises.json                 # Gym exercises database
│       │   └── gifs_360x360/                  # Exercise GIFs
│       ├── exercise 2/                        # Yoga poses with images
│       └── exercise3.csv                      # Cardio/sports activities
└── DOCS/FEATURES/
    └── AI_EXERCISE_RECOMMENDATION.md          # User-facing documentation
```

---

## 🔧 Core Components

### 1. AIExerciseRecommendationService.kt

**Purpose**: Singleton service that handles exercise database loading, AI prompt generation, and response parsing.

**Key Methods**:

```kotlin
suspend fun getExerciseRecommendation(
    mood: String = "Neutral", 
    stepCalories: Int = 0
): Result<List<ExerciseRec>>
```
- Fetches user data from Firebase
- Loads and filters exercise database
- Generates AI prompt with user context
- Calls Gemini 2.0 Flash API
- Parses JSON response into ExerciseRec objects
- Maps exercise names to local GIF paths
- Returns minimum 3 exercises with fallback

```kotlin
private suspend fun loadAllExercises(): List<ExerciseData>
```
- Loads exercises from 3 sources (JSON, folders, CSV)
- Tags exercises with safety properties (isPeriodSafe)
- Builds GIF path mapping cache
- Returns unified exercise list

```kotlin
private fun parseExerciseJson(json: JSONObject): ExerciseRec
```
- Parses JSON object to ExerciseRec data class
- Smart GIF path resolution (exact match, partial match, fuzzy match)
- Extracts all exercise fields including enhanced properties

**Data Classes**:

```kotlin
data class ExerciseRec(
    val name: String,
    val targetMuscle: String,
    val bodyPart: String,
    val equipment: String,
    val instructions: List<String>,
    val reason: String,
    val benefits: String,
    val gifUrl: String,
    val ageExplanation: String,
    val genderNote: String,
    val motivationalMessage: String,
    val estimatedCalories: Int,
    val recommendedDuration: String,
    val intensity: String,
    val goalAlignment: String,
    val tips: List<String>,
    val commonMistakes: List<String>
)
```

---

### 2. WorkoutDashboardActivity.kt

**Purpose**: Main UI controller for the workout section, handles exercise display and user interactions.

**Key Methods**:

```kotlin
private fun loadAiRecommendation()
```
- Disables button and shows loading state
- Fetches user mood from MoodRepository
- Calls AIExerciseRecommendationService
- Handles success/failure cases
- Updates UI and shows exercise card

```kotlin
private fun displayCurrentExercise()
```
- Renders exercise details on screen
- Loads GIF using Glide
- Populates instructions, tips, mistakes
- Shows/hides sections based on available data
- Updates exercise counter

```kotlin
private fun markAiExerciseComplete()
```
- Disables "Done" button
- Logs to Firestore (exercise_logs collection)
- Updates RTDB (workoutHistory, XP, stats)
- Shows success message
- Auto-advances to next exercise after 1.2s delay

```kotlin
private fun skipToNextExercise()
```
- Increments exercise index
- Calls displayCurrentExercise()

**UI Binding**:

```kotlin
private lateinit var cardAiExercise: CardView
private lateinit var tvAiExerciseName: TextView
private lateinit var ivAiExerciseGif: ImageView
private lateinit var tvAiExerciseReason: TextView
private lateinit var tvAiExerciseCalories: TextView
private lateinit var tvAiExerciseDuration: TextView
private lateinit var btnAiExerciseDone: MaterialButton
private lateinit var btnAiExerciseSkip: MaterialButton
private lateinit var tvExerciseCounter: TextView
private lateinit var llInstructions: LinearLayout
private lateinit var llTips: LinearLayout
private lateinit var llCommonMistakes: LinearLayout
```

---

## 🔥 Firebase Integration

### Firestore Structure

**Database Name**: `renu`

**Collections**:

```
users/{userId}
├── profile
│   ├── age: number
│   ├── gender: string
│   ├── weight: number
│   ├── isOnPeriod: boolean
│   └── ...
├── goals
│   ├── goalType: string (Weight Loss, Muscle Gain, etc.)
│   ├── dailyCalories: number
│   └── ...
├── foodLogs (from food tracking)
│   ├── date: string
│   ├── calories: number
│   └── ...
└── exercise_logs (AI + manual exercises)
    ├── {logId}
    │   ├── userId: string
    │   ├── date: string (YYYY-MM-DD)
    │   ├── exerciseName: string
    │   ├── caloriesBurned: number
    │   ├── duration: number (minutes)
    │   └── timestamp: number (epoch)
    └── ...
```

### Realtime Database Structure

**URL**: `https://swasthyamitra-ded44-default-rtdb.asia-southeast1.firebasedatabase.app`

**Structure**:

```json
{
  "users": {
    "{userId}": {
      "xp": 1500,
      "streak": 7,
      "totalWorkoutMinutes": 120,
      "lastActiveDate": "2026-02-14",
      "completionHistory": {
        "2026-02-14": true
      },
      "workoutHistory": {
        "{sessionId}": {
          "id": "uuid",
          "date": "2026-02-14",
          "category": "AI Comp.",
          "videoId": "ai_1739472000000",
          "duration": 15,
          "completed": true,
          "timestamp": 1739472000000,
          "caloriesBurned": 150
        }
      }
    }
  }
}
```

### Security Rules (Firestore)

```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    match /users/{userId} {
      allow read, write: if request.auth != null && request.auth.uid == userId;
      
      match /exercise_logs/{logId} {
        allow read, write: if request.auth != null && request.auth.uid == userId;
      }
    }
  }
}
```

### Security Rules (RTDB)

```json
{
  "rules": {
    "users": {
      "$uid": {
        ".read": "$uid === auth.uid",
        ".write": "$uid === auth.uid"
      }
    }
  }
}
```

---

## 🤖 AI Prompt Engineering

### Prompt Structure

The AI prompt follows this template:

```
### ✅ Final AI Prompt – Closed-Loop Exercise Recommendation System

You are a certified **Sports Scientist and Fitness Coach AI**.

**User Details:**
* Age: {age}
* Gender: {gender}
* Weight: {weight} kg
* Fitness Goal: {goalType}
* Current Mood: {mood}
* Period Status: {isOnPeriod ? "Active" : "Inactive"}
* Today's Calorie Intake: {consumed} kcal / Target: {targetCalories}

**Available Exercises (Filtered Local Database):**
{filtered exercise list}

---

### 🎯 Task
Generate a **complete workout session of 3 DISTINCT exercises** (~15 minutes each), 
arranged logically in sequence (Warm-up → Main Exercise → Finisher/Stretch).

---

### 🧠 Closed-Loop Logic Rules (MANDATORY)
1. If Period Mode is ACTIVE, suggest ONLY gentle, restorative, low-impact movements
2. Adjust intensity based on Mood
3. Consider calorie balance
4. Align with fitness goal
5. Age-appropriate safety
6. Gender-specific benefits

---

### 📌 Output Format (Strict JSON Array)
[
  {
    "name": "Exercise name from database",
    "targetMuscle": "...",
    "bodyPart": "...",
    "equipment": "...",
    "instructions": ["Step 1", "Step 2"],
    "reason": "Why this exercise now",
    "benefits": "Personalized benefits",
    "ageExplanation": "Age-specific note",
    "genderNote": "Gender-specific benefit",
    "motivationalMessage": "Motivation",
    "estimatedCalories": 150,
    "recommendedDuration": "15 mins",
    "intensity": "light/moderate/high",
    "goalAlignment": "How it helps goal",
    "tips": ["Tip 1", "Tip 2"],
    "commonMistakes": ["Mistake 1", "Mistake 2"]
  },
  ... (Total 3 items)
]
```

### Key Prompt Features

1. **Context-Rich**: Includes all relevant user data
2. **Constraint-Based**: Clear rules for safety and appropriateness
3. **Structured Output**: JSON schema ensures parseability
4. **Validation Friendly**: Exercise names match database entries
5. **Personalization**: Age, gender, mood, and goal integration

---

## 🎨 UI Component Breakdown

### Exercise Card Layout (activity_workout_dashboard.xml)

```xml
<androidx.cardview.widget.CardView
    android:id="@+id/cardAiExercise"
    android:visibility="gone">
    
    <ScrollView>
        <LinearLayout>
            
            <!-- Header with Gradient -->
            <LinearLayout background="@drawable/button_background">
                <TextView text="Your Personalized Exercise Today 💪"/>
            </LinearLayout>
            
            <!-- Exercise Counter -->
            <TextView android:id="@+id/tvExerciseCounter"/>
            
            <!-- Exercise Name -->
            <TextView android:id="@+id/tvAiExerciseName"/>
            
            <!-- Exercise GIF -->
            <ImageView android:id="@+id/ivAiExerciseGif"/>
            
            <!-- Quick Stats -->
            <LinearLayout>
                <TextView android:id="@+id/tvAiExerciseCalories"/>
                <TextView android:id="@+id/tvAiExerciseDuration"/>
            </LinearLayout>
            
            <!-- Reason & Benefits -->
            <TextView android:id="@+id/tvAiExerciseReason"/>
            
            <!-- Additional Details -->
            <TextView android:id="@+id/tvAiExerciseTarget"/>
            <TextView android:id="@+id/tvAiExerciseEquipment"/>
            <TextView android:id="@+id/tvAiExerciseAgeExplanation"/>
            <TextView android:id="@+id/tvAiExerciseGenderNote"/>
            <TextView android:id="@+id/tvAiExerciseMotivation"/>
            <TextView android:id="@+id/tvAiExerciseGoalAlignment"/>
            
            <!-- Instructions Section -->
            <TextView text="HOW TO PERFORM"/>
            <LinearLayout android:id="@+id/llInstructions">
                <!-- Dynamically populated -->
            </LinearLayout>
            
            <!-- Tips Section -->
            <TextView text="PRO TIPS"/>
            <LinearLayout android:id="@+id/llTips">
                <!-- Dynamically populated -->
            </LinearLayout>
            
            <!-- Common Mistakes Section -->
            <TextView text="COMMON MISTAKES TO AVOID"/>
            <LinearLayout android:id="@+id/llCommonMistakes">
                <!-- Dynamically populated -->
            </LinearLayout>
            
            <!-- Action Buttons -->
            <LinearLayout>
                <Button android:id="@+id/btnAiExerciseDone"
                    text="I DID IT! 💪"/>
                <MaterialButton android:id="@+id/btnAiExerciseSkip"
                    text="SKIP ⏭️"/>
            </LinearLayout>
            
        </LinearLayout>
    </ScrollView>
</androidx.cardview.widget.CardView>
```

### Button States

```kotlin
// Initial State
btnAiExerciseRecommendation.text = "AI Exercise 🤖"
btnAiExerciseRecommendation.isEnabled = true

// Loading State
btnAiExerciseRecommendation.text = "Loading AI..."
btnAiExerciseRecommendation.isEnabled = false

// Refresh State (after load)
btnAiExerciseRecommendation.text = "Refresh AI 🔄"
btnAiExerciseRecommendation.isEnabled = true

// Done Button States
btnAiExerciseDone.text = "I DID IT! 💪"  // Initial
btnAiExerciseDone.text = "Saving..."      // Processing
btnAiExerciseDone.text = "Completed! 🎉"  // Success
btnAiExerciseDone.isEnabled = false       // After completion
```

---

## 🖼️ Image Loading (Glide)

### Configuration

```kotlin
// In displayCurrentExercise()
if (rec.gifUrl.isNotEmpty()) {
    ivAiExerciseGif.visibility = View.VISIBLE
    
    // URL-encode for spaces
    val encodedPath = rec.gifUrl.replace(" ", "%20")
    val fullPath = "file:///android_asset/$encodedPath"
    
    Glide.with(this)
        .load(fullPath)
        .into(ivAiExerciseGif)
} else {
    ivAiExerciseGif.visibility = View.GONE
}
```

### Asset Path Examples

```
file:///android_asset/exercisedb_v1_sample/gifs_360x360/pushup.gif
file:///android_asset/exercise 2/Cat/cat_pose.png
```

### Error Handling

```kotlin
try {
    Glide.with(this).load(fullPath).into(ivAiExerciseGif)
} catch (e: Exception) {
    ivAiExerciseGif.visibility = View.GONE
    Log.e(TAG, "Error loading GIF: ${e.message}")
}
```

---

## ⚡ Performance Considerations

### 1. Lazy Loading
- Exercise database loaded only when AI button is clicked
- GIFs loaded on-demand with Glide caching

### 2. Coroutines
- All Firebase calls wrapped in `withContext(Dispatchers.IO)`
- UI updates on main thread with `runOnUiThread`

### 3. Caching
- Exercise GIF map cached after first load
- User data cached in activity to avoid repeated fetches

### 4. Timeout Handling
```kotlin
val response = try {
    kotlinx.coroutines.withTimeout(45000) {
        generativeModel.generateContent(promptText)
    }
} catch (e: Exception) {
    throw Exception("Coach is busy planning your session. Please try again.")
}
```

### 5. Memory Management
- Glide automatically handles image caching and memory
- Old exercise list cleared before new load
- ScrollView recycles views automatically

---

## 🧪 Testing Scenarios

### Unit Tests (to be implemented)

```kotlin
class AIExerciseRecommendationServiceTest {
    @Test
    fun testExerciseFiltering_PeriodMode() {
        // Verify only gentle exercises returned when isOnPeriod = true
    }
    
    @Test
    fun testGifPathResolution() {
        // Verify exercise names map to correct GIF paths
    }
    
    @Test
    fun testMinimumExerciseCount() {
        // Verify fallback ensures at least 3 exercises
    }
}
```

### Integration Tests

```kotlin
class WorkoutDashboardActivityTest {
    @Test
    fun testAIButtonClick_ShowsLoading() {
        // Click AI button, verify loading state
    }
    
    @Test
    fun testExerciseCompletion_LogsToFirebase() {
        // Complete exercise, verify Firestore/RTDB updated
    }
    
    @Test
    fun testSkipButton_AdvancesToNext() {
        // Skip exercise, verify next one displayed
    }
}
```

### Manual Testing Checklist

- [ ] Click AI button → Card appears with 3 exercises
- [ ] Verify GIF loads correctly
- [ ] Check all text fields populated
- [ ] Complete exercise → XP increases, stats update
- [ ] Skip exercise → Next one displays
- [ ] Complete all 3 → Card hides, success message
- [ ] Test in period mode → Only gentle exercises
- [ ] Test with different moods → Appropriate intensity
- [ ] Test network failure → Error message shown
- [ ] Test with no internet → Fallback exercises

---

## 🚨 Error Handling

### Network Errors
```kotlin
catch (e: Exception) {
    runOnUiThread {
        cardAiExercise.visibility = View.GONE
        btnAiExerciseRecommendation.isEnabled = true
        btnAiExerciseRecommendation.text = "AI Exercise 🤖"
        Toast.makeText(this, "AI failed: ${e.message}", Toast.LENGTH_LONG).show()
    }
}
```

### Parsing Errors
```kotlin
try {
    val jsonArray = JSONArray(cleanJson)
    // Parse exercises
} catch (e: Exception) {
    // Fallback to single object parsing
    try {
        val json = JSONObject(cleanJson)
        val rec = parseExerciseJson(json)
        results.add(rec)
    } catch (e2: Exception) {
        // Use fallback exercises
    }
}
```

### Firebase Errors
```kotlin
.addOnFailureListener { e ->
    Toast.makeText(this, "Failed to log: ${e.message}", Toast.LENGTH_SHORT).show()
    btnAiExerciseDone.isEnabled = true
    btnAiExerciseDone.text = "Mark as Done ✅"
}
```

---

## 📊 Monitoring & Analytics

### Key Metrics to Track

1. **AI Success Rate**: % of successful generations vs errors
2. **Average Response Time**: Time from button click to display
3. **User Engagement**: Completion rate per session
4. **Exercise Popularity**: Most completed exercises
5. **Period Mode Usage**: % of users in period mode
6. **Mood Distribution**: Most common moods during workouts

### Implementation (Future)

```kotlin
// Log to Firebase Analytics
FirebaseAnalytics.getInstance(this).logEvent("ai_exercise_generated") {
    param("mood", mood)
    param("period_mode", isOnPeriod)
    param("exercise_count", results.size)
    param("response_time_ms", responseTime)
}

FirebaseAnalytics.getInstance(this).logEvent("exercise_completed") {
    param("exercise_name", exercise.name)
    param("calories", exercise.estimatedCalories)
    param("source", "ai")
}
```

---

## 🔐 Security Best Practices

### API Key Protection
- Firebase AI API key stored in `google-services.json`
- Never expose in code or version control
- Use Firebase App Check for production

### User Data Privacy
- All queries scoped to authenticated user
- No cross-user data access
- Exercise logs private to user

### Input Validation
- User age clamped to reasonable range (10-100)
- Exercise names sanitized before database lookup
- Calorie values validated (non-negative)

---

## 📦 Deployment Checklist

### Pre-Production
- [ ] Test with real user data (sanitized)
- [ ] Verify Firebase quotas sufficient
- [ ] Check asset file sizes (< 50MB total)
- [ ] Optimize GIF sizes if needed
- [ ] Test on multiple device sizes
- [ ] Verify network failure handling
- [ ] Check period mode functionality
- [ ] Validate calorie calculations

### Production
- [ ] Enable Firebase App Check
- [ ] Set up monitoring alerts
- [ ] Configure backup exercise database
- [ ] Document API rate limits
- [ ] Train support team on common issues
- [ ] Prepare user tutorial/onboarding

---

## 🔄 Maintenance & Updates

### Regular Tasks
- **Weekly**: Check AI error logs, review user feedback
- **Monthly**: Update exercise database with new exercises
- **Quarterly**: Optimize AI prompt based on success rate
- **Yearly**: Review and update safety guidelines

### Exercise Database Updates
1. Add new exercise JSON/images to assets folder
2. Test locally to verify GIF loading
3. Update exercise count in documentation
4. Deploy via Play Store update

### AI Prompt Updates
1. Analyze user feedback and completion rates
2. Refine prompt for better personalization
3. A/B test new prompt variations
4. Deploy with feature flag for gradual rollout

---

## 📚 Additional Resources

### Dependencies
- **Firebase AI**: [Documentation](https://firebase.google.com/docs/ai)
- **Glide**: [GitHub](https://github.com/bumptech/glide)
- **Kotlin Coroutines**: [Guide](https://kotlinlang.org/docs/coroutines-guide.html)

### Datasets
- **ExerciseDB**: [API Docs](https://rapidapi.com/justin-WFnsXH_t6/api/exercisedb)
- **Yoga Poses**: Custom curated dataset
- **Calorie Data**: Research-based estimates

---

**Last Updated**: February 14, 2026  
**Author**: SwasthyaMitra Development Team  
**Version**: 1.0

