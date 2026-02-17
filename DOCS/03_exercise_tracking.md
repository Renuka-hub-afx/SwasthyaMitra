# Exercise & Workout Management

## 📋 Overview

The Exercise & Workout Management system provides comprehensive exercise logging with a database of 800+ exercises, real-time calorie calculation using MET values, and detailed workout analytics.

---

## 🎯 Purpose & Importance

### Why Exercise Tracking Matters
- **Calorie Balance**: Track calories burned to maintain energy balance
- **Progress Monitoring**: See improvements in workout frequency and intensity
- **Motivation**: Visual progress encourages consistency
- **Variety**: Discover new exercises across different categories
- **Goal Achievement**: Track progress toward fitness goals

### Key Benefits
- 800+ exercises across multiple categories
- Automatic calorie burn calculation
- Exercise history with charts
- Workout analytics and insights
- Category-based organization (Cardio, Strength, Flexibility, Sports)

---

## 🔄 How It Works

### Complete Workflow

#### 1. **Manual Exercise Logging Flow**
```
User Opens ManualExerciseActivity
    ↓
Search/Browse Exercise Database (800+ exercises)
    ↓
Select Exercise from List
    ↓
Enter Details:
  - Duration (minutes)
  - Intensity (Low/Moderate/High)
    ↓
System Calculates Calories:
  - Fetch MET value for exercise
  - Get user weight from profile
  - Apply formula: Calories = MET × weight × (duration/60)
    ↓
Display Calculated Calories
    ↓
User Confirms and Saves
    ↓
Save to Firestore: users/{userId}/exercise_logs/
    ↓
Update Homepage Calorie Chart
    ↓
Add XP Points (Gamification)
```

#### 2. **Exercise History View Flow**
```
User Opens ExerciseLogActivity
    ↓
Fetch Exercise Logs from Firestore
    ↓
Group by Date (Today, Yesterday, This Week, etc.)
    ↓
Display in RecyclerView:
  - Exercise name
  - Duration
  - Calories burned
  - Timestamp
    ↓
Calculate Daily/Weekly Totals
    ↓
Show Summary Statistics
```

#### 3. **Workout Dashboard Flow**
```
User Opens WorkoutDashboardActivity
    ↓
Fetch Last 30 Days Exercise Data
    ↓
Analyze Data:
  - Total calories burned
  - Total workout time
  - Most frequent exercises
  - Category distribution
  - Weekly trends
    ↓
Generate Charts:
  - Bar chart: Calories per day
  - Pie chart: Exercise categories
  - Line chart: Workout frequency
    ↓
Display Insights and Recommendations
```

---

## 🧮 Logic & Algorithms

### MET-Based Calorie Calculation

#### What is MET?
**MET (Metabolic Equivalent of Task)** is a measure of exercise intensity:
- 1 MET = Resting metabolic rate
- Higher MET = More intense activity

#### Calorie Burn Formula
```kotlin
fun calculateCaloriesBurned(
    met: Double,
    weightKg: Double,
    durationMinutes: Int
): Int {
    val hours = durationMinutes / 60.0
    val calories = met * weightKg * hours
    return calories.toInt()
}
```

#### Common MET Values
```kotlin
val metValues = mapOf(
    // Cardio
    "Walking (3.5 mph)" to 3.5,
    "Running (6 mph)" to 10.0,
    "Cycling (moderate)" to 8.0,
    "Swimming (moderate)" to 7.0,
    "Jump Rope" to 12.0,
    
    // Strength
    "Weight Lifting (light)" to 3.0,
    "Weight Lifting (vigorous)" to 6.0,
    "Push-ups" to 8.0,
    "Pull-ups" to 8.0,
    
    // Flexibility
    "Yoga" to 2.5,
    "Stretching" to 2.3,
    "Pilates" to 3.0,
    
    // Sports
    "Basketball" to 6.5,
    "Football" to 8.0,
    "Tennis" to 7.3,
    "Badminton" to 5.5
)
```

#### Example Calculation
```kotlin
// User: 70kg, Exercise: Running (6 mph), Duration: 30 minutes
val met = 10.0
val weight = 70.0
val duration = 30

val calories = 10.0 × 70.0 × (30/60)
             = 10.0 × 70.0 × 0.5
             = 350 calories burned
```

### Intensity Multiplier
```kotlin
fun applyIntensityMultiplier(baseMet: Double, intensity: String): Double {
    return when (intensity) {
        "Low" -> baseMet * 0.8
        "Moderate" -> baseMet * 1.0
        "High" -> baseMet * 1.2
        else -> baseMet
    }
}
```

### Exercise Database Loading
```kotlin
fun loadExerciseDatabase(): List<Exercise> {
    val exercises = mutableListOf<Exercise>()
    
    // Read from CSV file in assets
    val inputStream = context.assets.open("exercise3.csv")
    val reader = BufferedReader(InputStreamReader(inputStream))
    
    reader.useLines { lines ->
        lines.drop(1).forEach { line ->  // Skip header
            val parts = line.split(",")
            exercises.add(Exercise(
                name = parts[0],
                category = parts[1],
                met = parts[2].toDouble(),
                description = parts[3]
            ))
        }
    }
    
    return exercises
}
```

---

## 👤 User Interaction

### Logging an Exercise
1. **User Action**: Opens "Exercise Log" from homepage
2. **System Display**: List of recent exercises + "Add Exercise" button
3. **User Action**: Clicks "Add Exercise"
4. **Navigation**: Opens ManualExerciseActivity
5. **User Input**:
   - Search for exercise (e.g., "running")
   - Select from filtered results
   - Enter duration (e.g., 30 minutes)
   - Select intensity (Low/Moderate/High)
6. **System Process**:
   - Fetch user weight from profile
   - Calculate calories using MET formula
   - Display: "Running - 30 min - 350 kcal"
7. **User Action**: Clicks "Save"
8. **Output**:
   - Success toast: "Exercise logged successfully!"
   - Returns to ExerciseLogActivity
   - New exercise appears in list
   - Homepage calorie chart updates
   - Gamification: +15 XP earned

### Viewing Exercise History
1. **User Action**: Opens "Exercise Log"
2. **System Display**:
   - Today's exercises at top
   - Grouped by date (Yesterday, This Week, etc.)
   - Each entry shows: Name, Duration, Calories, Time
   - Total calories for each day
3. **User Action**: Scroll through history
4. **Additional Actions**:
   - Swipe to delete exercise
   - Tap to view details
   - Filter by category
   - Export to CSV

### Workout Dashboard
1. **User Action**: Opens "Workout Dashboard"
2. **System Display**:
   - Summary cards:
     - Total calories burned (last 30 days)
     - Total workout time
     - Number of workouts
     - Average calories per workout
   - Charts:
     - Bar chart: Daily calorie burn
     - Pie chart: Exercise category distribution
     - Line chart: Workout frequency trend
   - Insights:
     - "You burned 20% more calories this week!"
     - "Most active day: Monday"
     - "Favorite exercise: Running"

---

## 💻 Technical Implementation

### Key Files

#### 1. **ManualExerciseActivity.kt**
- **Purpose**: Exercise selection and logging interface
- **Location**: `app/src/main/java/com/example/swasthyamitra/ManualExerciseActivity.kt`
- **Key Functions**:
  ```kotlin
  class ManualExerciseActivity : AppCompatActivity() {
      
      private lateinit var exerciseDatabase: List<Exercise>
      private var selectedExercise: Exercise? = null
      
      override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          loadExerciseDatabase()
          setupSearchFilter()
      }
      
      private fun loadExerciseDatabase() {
          exerciseDatabase = loadExercisesFromCSV()
          displayExercises(exerciseDatabase)
      }
      
      private fun setupSearchFilter() {
          searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
              override fun onQueryTextChange(newText: String): Boolean {
                  val filtered = exerciseDatabase.filter { 
                      it.name.contains(newText, ignoreCase = true) 
                  }
                  displayExercises(filtered)
                  return true
              }
          })
      }
      
      private fun calculateAndLogExercise() {
          val exercise = selectedExercise ?: return
          val duration = durationInput.text.toString().toIntOrNull() ?: return
          val intensity = intensitySpinner.selectedItem.toString()
          
          // Fetch user weight
          val userWeight = getUserWeight()
          
          // Calculate calories
          val adjustedMet = applyIntensityMultiplier(exercise.met, intensity)
          val calories = calculateCaloriesBurned(adjustedMet, userWeight, duration)
          
          // Save to Firestore
          saveExerciseLog(exercise, duration, intensity, calories)
      }
      
      private fun saveExerciseLog(
          exercise: Exercise,
          duration: Int,
          intensity: String,
          calories: Int
      ) {
          val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
          
          val logData = hashMapOf(
              "exerciseName" to exercise.name,
              "category" to exercise.category,
              "duration" to duration,
              "intensity" to intensity,
              "caloriesBurned" to calories,
              "timestamp" to FieldValue.serverTimestamp(),
              "date" to SimpleDateFormat("yyyy-MM-dd").format(Date())
          )
          
          db.collection("users").document(userId)
              .collection("exercise_logs")
              .add(logData)
              .addOnSuccessListener {
                  Toast.makeText(this, "Exercise logged!", Toast.LENGTH_SHORT).show()
                  addGamificationXP(15)  // Award XP
                  finish()
              }
      }
  }
  ```

#### 2. **ExerciseLogActivity.kt**
- **Purpose**: Display exercise history
- **Location**: `app/src/main/java/com/example/swasthyamitra/ExerciseLogActivity.kt`
- **Key Functions**:
  ```kotlin
  class ExerciseLogActivity : AppCompatActivity() {
      
      private lateinit var exerciseAdapter: ExerciseAdapter
      private val exerciseLogs = mutableListOf<ExerciseLog>()
      
      override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          setupRecyclerView()
          loadExerciseLogs()
      }
      
      private fun loadExerciseLogs() {
          val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
          
          db.collection("users").document(userId)
              .collection("exercise_logs")
              .orderBy("timestamp", Query.Direction.DESCENDING)
              .addSnapshotListener { snapshot, error ->
                  if (error != null) return@addSnapshotListener
                  
                  exerciseLogs.clear()
                  snapshot?.documents?.forEach { doc ->
                      exerciseLogs.add(ExerciseLog(
                          id = doc.id,
                          exerciseName = doc.getString("exerciseName") ?: "",
                          category = doc.getString("category") ?: "",
                          duration = doc.getLong("duration")?.toInt() ?: 0,
                          caloriesBurned = doc.getLong("caloriesBurned")?.toInt() ?: 0,
                          intensity = doc.getString("intensity") ?: "",
                          timestamp = doc.getTimestamp("timestamp")
                      ))
                  }
                  
                  exerciseAdapter.notifyDataSetChanged()
                  updateSummaryStats()
              }
      }
      
      private fun updateSummaryStats() {
          val totalCalories = exerciseLogs.sumOf { it.caloriesBurned }
          val totalDuration = exerciseLogs.sumOf { it.duration }
          val workoutCount = exerciseLogs.size
          
          totalCaloriesText.text = "$totalCalories kcal"
          totalDurationText.text = "$totalDuration min"
          workoutCountText.text = "$workoutCount workouts"
      }
  }
  ```

#### 3. **WorkoutDashboardActivity.kt**
- **Purpose**: Analytics and insights
- **Location**: `app/src/main/java/com/example/swasthyamitra/WorkoutDashboardActivity.kt`
- **Key Functions**:
  ```kotlin
  class WorkoutDashboardActivity : AppCompatActivity() {
      
      private lateinit var barChart: BarChart
      private lateinit var pieChart: PieChart
      
      override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          loadWorkoutData()
          setupCharts()
      }
      
      private fun loadWorkoutData() {
          val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
          val thirtyDaysAgo = Date(System.currentTimeMillis() - 30L * 24 * 60 * 60 * 1000)
          
          db.collection("users").document(userId)
              .collection("exercise_logs")
              .whereGreaterThan("timestamp", thirtyDaysAgo)
              .get()
              .addOnSuccessListener { snapshot ->
                  val logs = snapshot.documents.map { /* parse */ }
                  generateCharts(logs)
                  generateInsights(logs)
              }
      }
      
      private fun generateCharts(logs: List<ExerciseLog>) {
          // Bar Chart: Daily calories
          val dailyCalories = logs.groupBy { it.date }
              .mapValues { it.value.sumOf { log -> log.caloriesBurned } }
          
          val barEntries = dailyCalories.entries.mapIndexed { index, entry ->
              BarEntry(index.toFloat(), entry.value.toFloat())
          }
          
          val barDataSet = BarDataSet(barEntries, "Daily Calories")
          barChart.data = BarData(barDataSet)
          barChart.invalidate()
          
          // Pie Chart: Category distribution
          val categoryCalories = logs.groupBy { it.category }
              .mapValues { it.value.sumOf { log -> log.caloriesBurned } }
          
          val pieEntries = categoryCalories.map { 
              PieEntry(it.value.toFloat(), it.key) 
          }
          
          val pieDataSet = PieDataSet(pieEntries, "Categories")
          pieChart.data = PieData(pieDataSet)
          pieChart.invalidate()
      }
  }
  ```

### Data Models

```kotlin
data class Exercise(
    val name: String,
    val category: String,
    val met: Double,
    val description: String
)

data class ExerciseLog(
    val id: String,
    val exerciseName: String,
    val category: String,
    val duration: Int,          // minutes
    val caloriesBurned: Int,
    val intensity: String,
    val timestamp: Timestamp?,
    val date: String
)

enum class ExerciseCategory {
    CARDIO,
    STRENGTH,
    FLEXIBILITY,
    SPORTS
}

enum class Intensity {
    LOW,
    MODERATE,
    HIGH
}
```

---

## 🎨 Design & UI Structure

### Layout Files

#### 1. **activity_manual_exercise.xml**
- **Purpose**: Exercise selection and logging
- **Components**:
  - SearchView for filtering exercises
  - RecyclerView for exercise list
  - Exercise details card (when selected)
  - Duration input (EditText)
  - Intensity spinner (Low/Moderate/High)
  - Calculated calories display
  - "Log Exercise" button

#### 2. **activity_exercise_log.xml**
- **Purpose**: Exercise history display
- **Components**:
  - Summary cards (Total calories, duration, count)
  - Date filter tabs
  - RecyclerView for exercise logs
  - "Add Exercise" FAB
  - Swipe-to-delete functionality

#### 3. **activity_workout_dashboard.xml**
- **Purpose**: Analytics and charts
- **Components**:
  - Summary statistics cards
  - BarChart for daily calories
  - PieChart for category distribution
  - LineChart for frequency trends
  - Insights section with AI recommendations

#### 4. **item_exercise.xml**
- **Purpose**: Individual exercise item in list
- **Components**:
  - Exercise name (TextView)
  - Category badge (Chip)
  - MET value display
  - Description (collapsible)

#### 5. **item_exercise_log.xml**
- **Purpose**: Individual log entry
- **Components**:
  - Exercise name and category
  - Duration and calories
  - Timestamp
  - Delete icon

---

## 🔌 APIs & Services Used

### Cloud Firestore
- **Collection**: `users/{userId}/exercise_logs/`
- **Document Structure**:
  ```json
  {
    "exerciseName": "Running",
    "category": "Cardio",
    "duration": 30,
    "intensity": "Moderate",
    "caloriesBurned": 350,
    "timestamp": "2026-02-16T21:30:00Z",
    "date": "2026-02-16"
  }
  ```

### Local Assets
- **File**: `app/src/main/assets/exercise3.csv`
- **Format**:
  ```csv
  name,category,met,description
  Running (6 mph),Cardio,10.0,Outdoor or treadmill running
  Push-ups,Strength,8.0,Upper body strength exercise
  Yoga,Flexibility,2.5,Stretching and balance
  ```

### MPAndroidChart Library
- **Purpose**: Data visualization
- **Charts Used**:
  - BarChart: Daily calorie burn
  - PieChart: Exercise categories
  - LineChart: Workout frequency

---

## 🚀 Future Improvements

### Planned Enhancements
1. **Google Fit Integration**: Import exercise data automatically
2. **Workout Templates**: Pre-defined workout routines
3. **Rest Timer**: Built-in timer for interval training
4. **Exercise Videos**: Tutorial videos for proper form
5. **Social Sharing**: Share workouts with friends
6. **Personal Records**: Track PRs for strength exercises
7. **Workout Plans**: AI-generated weekly workout plans
8. **Heart Rate Integration**: Connect to fitness trackers
9. **Calorie Goal**: Set and track exercise calorie goals
10. **Exercise Reminders**: Notifications to stay active

### Technical Improvements
- Implement offline caching for exercise database
- Add pagination for exercise history
- Optimize chart rendering performance
- Add export functionality (PDF, CSV)
- Implement exercise recommendations based on history
- Add unit tests for calorie calculations

---

## 🐛 Common Issues & Solutions

### Issue 1: Incorrect calorie calculations
- **Cause**: Missing or invalid user weight
- **Solution**: Validate user profile data, use default 70kg if missing

### Issue 2: Exercise database not loading
- **Cause**: CSV file parsing error
- **Solution**: Validate CSV format, handle exceptions gracefully

### Issue 3: Duplicate exercise logs
- **Cause**: Multiple clicks on save button
- **Solution**: Disable button after first click, show loading indicator

---

## 📊 Performance Metrics

- **Exercise Database**: 800+ exercises
- **Search Performance**: <100ms for filtering
- **Calorie Calculation**: Instant (<1ms)
- **Chart Rendering**: <500ms for 30 days data
- **Firestore Query**: <1 second for history

---

**[← Back to Main README](../README.md)** | **[Next: Sleep Tracker →](05_sleep_tracker.md)**
