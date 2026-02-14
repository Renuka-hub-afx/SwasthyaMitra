package com.example.swasthyamitra

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.google.android.material.button.MaterialButton
import android.util.Log
import kotlinx.coroutines.launch
import java.util.Calendar
import androidx.cardview.widget.CardView
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await


class homepage : AppCompatActivity() {

    private lateinit var authHelper: FirebaseAuthHelper
    private lateinit var stepManager: StepManager
    private var userId: String = ""
    private lateinit var firestore: FirebaseFirestore

    // UI Elements
    private lateinit var tvUserName: TextView
    private lateinit var tvGoalType: TextView
    private lateinit var tvSteps: TextView
    private lateinit var tvWorkouts: TextView
    private lateinit var tvCoachMessage: TextView
    private lateinit var tvProteinValue: TextView
    private lateinit var tvCarbsValue: TextView
    private lateinit var tvFatsValue: TextView
    private lateinit var pbProtein: ProgressBar
    private lateinit var pbCarbs: ProgressBar
    private lateinit var pbFats: ProgressBar

    // AI Recommendation UI - REMOVED: Now only in Workout Dashboard
    // All AI exercise functionality moved to WorkoutDashboardActivity

    private lateinit var menuHome: LinearLayout
    private lateinit var menuProgress: LinearLayout
    private lateinit var menuProfile: LinearLayout

    private lateinit var cardFood: MaterialButton
    private lateinit var cardWorkout: MaterialButton
    private lateinit var cardExerciseLog: MaterialButton
    private lateinit var cardWater: MaterialButton

    private lateinit var tvWaterTotal: TextView
    private lateinit var waterProgressBar: ProgressBar
    private lateinit var cardWaterSummary: View
    private lateinit var tvDate: TextView
    private lateinit var chipPeriodMode: com.google.android.material.chip.Chip
    
    // Sleep Tracking Button
    private lateinit var btnTrackSleep: MaterialButton

    // Calorie Balance UI
    private lateinit var tvCaloriesIn: TextView
    private lateinit var tvCaloriesOut: TextView
    private lateinit var pbCaloriesIn: ProgressBar
    private lateinit var pbCaloriesOut: ProgressBar
    private lateinit var tvNetBalance: TextView
    private lateinit var tvCalorieStatus: TextView
    
    // History Log UI
    private lateinit var tvCalorieGoal: TextView

    private var goalType: String = ""
    private var userName: String = ""
    private var isOnPeriod: Boolean = false
    private val hydrationRepo = com.example.swasthyamitra.data.repository.HydrationRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            setContentView(R.layout.activity_homepage)
            Log.d("Homepage", "Layout inflated successfully")

            val application = application as? UserApplication
            if (application == null) {
                Log.e("Homepage", "UserApplication is null")
                Toast.makeText(this, "Application initialization error", Toast.LENGTH_SHORT).show()
                navigateToLogin()
                return
            }

            authHelper = application.authHelper
            Log.d("Homepage", "AuthHelper initialized successfully")

            // Initialize Firestore with error handling
            try {
                firestore = FirebaseFirestore.getInstance("renu")
                Log.d("Homepage", "Firestore 'renu' database initialized successfully")
            } catch (e: Exception) {
                Log.e("Homepage", "Error initializing named Firestore database: ${e.message}", e)
                // Fallback to default instance
                firestore = FirebaseFirestore.getInstance()
                Log.d("Homepage", "Using default Firestore instance as fallback")
            }

            // Get userId from intent or from current Firebase user
            userId = intent.getStringExtra("USER_ID") ?: authHelper.getCurrentUser()?.uid ?: ""

            if (userId.isEmpty()) {
                Log.e("Homepage", "No user ID found, redirecting to login")
                Toast.makeText(this, "Please login first", Toast.LENGTH_SHORT).show()
                navigateToLogin()
                return
            }

            Log.d("Homepage", "Homepage initialized for user: $userId")

            // DISABLED: Auto-tracking service (requires health permissions)
            // Start auto-tracking service for this user
            // try {
            //     (application as? UserApplication)?.startAutoTrackingService()
            //     Log.d("Homepage", "Auto-tracking service started")
            // } catch (e: Exception) {
            //     Log.e("Homepage", "Error starting auto-tracking service: ${e.message}")
            // }

            // Initialize UI Elements
            tvDate = findViewById(R.id.tv_date)
            tvUserName = findViewById(R.id.tv_user_name)
            tvGoalType = findViewById(R.id.tv_goal_type)
            tvSteps = findViewById(R.id.tv_steps)
            tvWorkouts = findViewById(R.id.tv_workouts)
            tvCoachMessage = findViewById(R.id.tv_coach_message)
            tvProteinValue = findViewById(R.id.tv_protein_value)
            tvCarbsValue = findViewById(R.id.tv_carbs_value)
            tvFatsValue = findViewById(R.id.tv_fats_value)
            pbProtein = findViewById(R.id.pb_protein)
            pbCarbs = findViewById(R.id.pb_carbs)
            pbFats = findViewById(R.id.pb_fats)

            menuHome = findViewById(R.id.menu_home)
            menuProgress = findViewById(R.id.menu_progress)
            menuProfile = findViewById(R.id.menu_profile)

            cardFood = findViewById(R.id.card_food)
            cardWorkout = findViewById(R.id.card_workout)
            cardExerciseLog = findViewById(R.id.card_exercise_log)
            cardWater = findViewById(R.id.card_water)
            cardWaterSummary = findViewById(R.id.card_water_summary)
            tvWaterTotal = findViewById(R.id.tv_water_total)
            waterProgressBar = findViewById(R.id.waterProgressBar)

            // AI Exercise views removed - functionality now only in Workout Dashboard
            // To use AI Exercise, tap "Workout" card to go to WorkoutDashboardActivity

            // Food details view (optional)
            try {
                val tvViewDetails: TextView? = findViewById(R.id.tv_view_details)
                tvViewDetails?.setOnClickListener {
                    startActivity(Intent(this, FoodLogActivity::class.java))
                }
            } catch (e: Exception) {
                Log.w("Homepage", "tv_view_details not found (optional): ${e.message}")
            }

            // Period mode chip (optional)
            try {
                chipPeriodMode = findViewById(R.id.chip_period_mode)
                Log.d("Homepage", "Period mode chip initialized")
            } catch (e: Exception) {
                Log.w("Homepage", "chip_period_mode not found (optional): ${e.message}")
            }

            // Initialize calorie balance UI
            tvCaloriesIn = findViewById(R.id.tv_calories_in)
            tvCaloriesOut = findViewById(R.id.tv_calories_out)
            pbCaloriesIn = findViewById(R.id.pb_calories_in)
            pbCaloriesOut = findViewById(R.id.pb_calories_out)
            tvNetBalance = findViewById(R.id.tv_net_balance)
            tvCalorieGoal = findViewById(R.id.tv_calorie_goal)
            tvCalorieStatus = findViewById(R.id.tv_calorie_status)

            // Initialize Sleep Tracker Button (optional)
            try {
                btnTrackSleep = findViewById(R.id.btn_track_sleep)
                Log.d("Homepage", "Sleep tracker button initialized")
            } catch (e: Exception) {
                Log.w("Homepage", "btn_track_sleep not found (optional): ${e.message}")
            }

            Log.d("Homepage", "All views initialized successfully")

            // Initialize History UI

            updateDateDisplay()
            loadUserData()

            // Initialize Step Tracking
            try {
                stepManager = StepManager(this) { steps, _ ->
                    runOnUiThread {
                        tvSteps.text = steps.toString()
                    }
                }
                // Enable hybrid validation for accurate step counting
                stepManager.start(enableHybridValidation = true)
                Log.d("Homepage", "Step manager started with hybrid validation")
            } catch (e: Exception) {
                Log.e("Homepage", "Error starting step manager: ${e.message}", e)
                // Set default value if step manager fails
                tvSteps.text = "0"
            }

            cardWorkout.setOnClickListener {
                val intent = Intent(this, WorkoutDashboardActivity::class.java)
                startActivity(intent)
            }

            cardFood.setOnClickListener {
                startActivity(Intent(this, FoodLogActivity::class.java))
            }

            cardWater.setOnClickListener {
                val intent = Intent(this, com.example.swasthyamitra.ui.hydration.HydrationActivity::class.java)
                intent.putExtra("USER_ID", userId)
                startActivity(intent)
            }

            cardExerciseLog.setOnClickListener {
                val intent = Intent(this, ExerciseLogActivity::class.java)
                startActivity(intent)
            }

            // Period mode listener (only if chip exists)
            if (::chipPeriodMode.isInitialized) {
                chipPeriodMode.setOnCheckedChangeListener { _, isChecked ->
                    isOnPeriod = isChecked
                    lifecycleScope.launch {
                        try {
                            firestore.collection("users").document(userId)
                                .update("isOnPeriod", isChecked)
                                .await()

                            // Trigger AI refreshes for specialized mode
                            updateAICoachMessage()
                            // AI Exercise removed from homepage - now only in WorkoutDashboardActivity

                            val status = if (isChecked) "activated 🌸" else "deactivated"
                            Toast.makeText(this@homepage, "Period Mode $status", Toast.LENGTH_SHORT).show()
                        } catch (e: Exception) {
                            Log.e("Homepage", "Error updating period mode", e)
                        }
                    }
                }
            }

            menuHome.setOnClickListener {
                Toast.makeText(this, "You are on Home", Toast.LENGTH_SHORT).show()
            }

            menuProgress.setOnClickListener {
                // Open Enhanced Progress Dashboard with Graphs
                startActivity(Intent(this, com.example.swasthyamitra.ui.EnhancedProgressDashboardActivity::class.java))
            }

            menuProfile.setOnClickListener {
                startActivity(Intent(this, ProfileActivity::class.java))
            }

            // Setup AI Diet Button (optional)
            try {
                val cardAiDiet: MaterialButton? = findViewById(R.id.card_ai_diet)
                cardAiDiet?.setOnClickListener {
                    val intent = Intent(this, AISmartDietActivity::class.java)
                    startActivity(intent)
                }
            } catch (e: Exception) {
                Log.w("Homepage", "card_ai_diet not found (optional): ${e.message}")
            }

            // Setup AI Rasoi (Smart Pantry)
            val cardAiRasoi: MaterialButton? = findViewById(R.id.card_ai_pantry)
            cardAiRasoi?.setOnClickListener {
                val intent = Intent(this, SmartPantryActivity::class.java)
                startActivity(intent)
            }

            // Setup Sleep Tracking Button (optional)
            if (::btnTrackSleep.isInitialized) {
                btnTrackSleep.setOnClickListener {
                    val intent = Intent(this, SleepTrackerActivity::class.java)
                    startActivity(intent)
                }
            }

            // REMOVED: Progress Dashboard and Insights buttons
            // These are now only accessible from the dashboard (bottom navigation "Progress" menu)
            // Users should click the "Progress" icon in bottom navigation to access:
            // - Enhanced Progress Dashboard with graphs (7/15/30 days)
            // - Insights and analytics

            // AI Exercise functionality removed from homepage
            // To access AI Exercise: Tap "Workout" card → WorkoutDashboardActivity → "AI Exercise 🤖" button

            // Mood details link (optional)
            try {
                findViewById<TextView>(R.id.tv_view_mood_details)?.setOnClickListener {
                    val intent = Intent(this, MoodRecommendationActivity::class.java)
                    startActivity(intent)
                }
            } catch (e: Exception) {
                Log.w("Homepage", "tv_view_mood_details not found (optional): ${e.message}")
            }

            setupMoodTracking()

        } catch (e: Exception) {
            Log.e("Homepage", "FATAL ERROR in onCreate: ${e.message}", e)
            e.printStackTrace()
            Toast.makeText(this, "Failed to load homepage: ${e.message}", Toast.LENGTH_LONG).show()
            // Don't crash - navigate back to login
            try {
                navigateToLogin()
            } catch (ex: Exception) {
                Log.e("Homepage", "Even navigation failed: ${ex.message}")
                finish()
            }
        }
    }

    private fun setupMoodTracking() {
        val moods: Map<Int, String> = mapOf(
            R.id.btn_mood_happy to "Happy",
            R.id.btn_mood_calm to "Calm",
            R.id.btn_mood_tired to "Tired",
            R.id.btn_mood_sad to "Sad",
            R.id.btn_mood_stressed to "Stressed"
        )

        moods.forEach { (id, mood) ->
            findViewById<LinearLayout>(id).setOnClickListener {
                handleMoodSelection(mood)
            }
        }
        

    }

    private fun handleMoodSelection(mood: String) {
        // 1. Local Analysis
        val analyzer = com.example.swasthyamitra.ai.LocalMoodAnalyzer()
        val analysis = analyzer.analyze(mood)

        // 2. Create Data Object
        val moodData = com.example.swasthyamitra.models.MoodData(
            userId = userId,
            mood = mood,
            intensity = analysis.intensity,
            energy = analysis.energy,
            suggestion = analysis.suggestion,
            timestamp = System.currentTimeMillis(),
            date = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(java.util.Date())
        )

        // 3. Save to Repository (Background)
        val repo = com.example.swasthyamitra.repository.MoodRepository()
        lifecycleScope.launch {
            repo.saveMood(userId, moodData)
            
            // 4. Update Coach Message Immediately (Offline Fallback)
            runOnUiThread {
                tvCoachMessage.text = "I notice you're feeling $mood. ${analysis.suggestion}"
            }
        }

        // 5. Navigate to Recommendation Page
        val intent = Intent(this, MoodRecommendationActivity::class.java)
        intent.putExtra("MOOD_DATA", com.google.gson.Gson().toJson(moodData))
        startActivity(intent)
    }

    private fun showCustomWaterAddDialog() {
        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Add Water (ml)")
        
        val input = android.widget.EditText(this)
        input.inputType = android.text.InputType.TYPE_CLASS_NUMBER
        input.hint = "Enter amount in ml"
        builder.setView(input)
        
        builder.setPositiveButton("Add") { _, _ ->
            val amount = input.text.toString().toIntOrNull() ?: 0
            if (amount > 0) {
                 lifecycleScope.launch {
                    hydrationRepo.addWaterLog(userId, amount).onSuccess {
                        Toast.makeText(this@homepage, "Added $amount ml", Toast.LENGTH_SHORT).show()
                         displayWaterStatus() // Update the display immediately
                    }.onFailure {
                        Toast.makeText(this@homepage, "Failed to add water", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
        builder.show()
    }

    private fun updateDateDisplay() {
        val calendar = Calendar.getInstance()
        val dateFormat = java.text.SimpleDateFormat("EEEE, MMM dd", java.util.Locale.getDefault())
        val currentDate = dateFormat.format(calendar.time)
        tvDate.text = currentDate
    }

    override fun onResume() {
        super.onResume()
        updateDateDisplay() // Refresh date every time screen is shown
        if (userId.isNotEmpty()) {
            displayNutritionBreakdown()
            displayWorkoutStatus()
            displayWaterStatus()
            updateAICoachMessage()
            // AI Exercise removed from homepage - now only in WorkoutDashboardActivity
            updateCalorieBalance()
        }
    }

    private fun loadUserData() {
        lifecycleScope.launch {
            try {
                var userProfileWeight = 0.0

                val userDataResult = authHelper.getUserData(userId)
                userDataResult.onSuccess { userData ->
                    userName = userData["name"] as? String ?: "User"
                    tvUserName.text = "Hello, $userName!"
                    
                    val avatarId = userData["selected_avatar_id"] as? String
                    if (avatarId != null) {
                        val avatarResId = getAvatarDrawable(avatarId)
                        if (avatarResId != 0) {
                            findViewById<android.widget.ImageView>(R.id.iv_user_profile).setImageResource(avatarResId)
                        }
                    }

                    // Load Period Mode status
                    isOnPeriod = userData["isOnPeriod"] as? Boolean ?: false
                    chipPeriodMode.isChecked = isOnPeriod

                    // Restrict visibility to Female users only
                    val gender = userData["gender"] as? String ?: "Other"
                    if (gender.equals("Female", ignoreCase = true)) {
                        chipPeriodMode.visibility = android.view.View.VISIBLE
                    } else {
                        chipPeriodMode.visibility = android.view.View.GONE
                    }
                    
                    // Capture dynamic weight from profile
                    userProfileWeight = when (val w = userData["weight"]) {
                        is Number -> w.toDouble()
                        is String -> w.toDoubleOrNull() ?: 0.0
                        else -> 0.0
                    }
                }

                val goalsResult = authHelper.getUserGoal(userId)
                goalsResult.onSuccess { goal ->
                    goalType = goal["goalType"] as? String ?: "Stay Healthy"
                    tvGoalType.text = goalType
                    
                    // Get target weight from goals - check multiple possible fieldnames
                    val targetWeight = when {
                        goal["targetWeight"] != null -> when (val tw = goal["targetWeight"]) {
                            is Number -> tw.toDouble()
                            is String -> tw.toDoubleOrNull() ?: 0.0
                            else -> 0.0
                        }
                        goal["targetValue"] != null -> when (val tv = goal["targetValue"]) {
                            is Number -> tv.toDouble()
                            is String -> tv.toDoubleOrNull() ?: 0.0
                            else -> 0.0
                        }
                        else -> 0.0
                    }
                    
                    // Get current weight - PRIORITIZE USER PROFILE WEIGHT (DYNAMIC)
                    var currentWeight = userProfileWeight
                    
                    // Fallback to goal's initial weight if profile weight is missing/zero
                    if (currentWeight <= 0.0) {
                        currentWeight = when {
                            goal["currentWeight"] != null -> when (val cw = goal["currentWeight"]) {
                                is Number -> cw.toDouble()
                                is String -> cw.toDoubleOrNull() ?: 0.0
                                else -> 0.0
                            }
                            goal["currentValue"] != null -> when (val cv = goal["currentValue"]) {
                                is Number -> cv.toDouble()
                                is String -> cv.toDoubleOrNull() ?: 0.0
                                else -> 0.0
                            }
                            else -> 0.0
                        }
                    }
                    
                    // Debug logging
                    android.util.Log.d("Homepage", "===== WEIGHT CALCULATION DEBUG =====")
                    android.util.Log.d("Homepage", "Goal Type: $goalType")
                    android.util.Log.d("Homepage", "User Profile Weight (Dynamic): $userProfileWeight kg")
                    android.util.Log.d("Homepage", "Target Weight: $targetWeight kg")
                    android.util.Log.d("Homepage", "Used Current Weight: $currentWeight kg")
                    
                    updateWeightRemainingDisplay(goalType, currentWeight, targetWeight)
                    
                    val greeting = getGreeting()
                    val emoji = when(greeting) {
                        "Good Morning" -> "☀️"
                        "Good Afternoon" -> "🌟"
                        else -> "🌙"
                    }
                    
                    // Coach message without repeating goal
                    tvCoachMessage.text = "${greeting.lowercase()} $userName! $emoji\nStay consistent with your logging to reach your goals!"
                }

            } catch (e: Exception) {
                Log.e("Homepage", "Error loading user data", e)
            }
        }
    }

    private fun getGreeting(): String {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        return when (hour) {
            in 0..11 -> "Good Morning"
            in 12..16 -> "Good Afternoon"
            else -> "Good Evening"
        }
    }

    private fun updateAICoachMessage() {
        lifecycleScope.launch {
            try {
                // Show loading state
                runOnUiThread {
                    tvCoachMessage.text = "Coach is analyzing your progress... 🔍"
                }

                val steps = if (::stepManager.isInitialized) stepManager.dailySteps else 0
                val service = com.example.swasthyamitra.ai.AICoachMessageService.getInstance(this@homepage)
                val result = service.getCoachMessage(userId, steps)
                
                result.onSuccess { message ->
                    runOnUiThread {
                        tvCoachMessage.text = message
                    }
                }.onFailure {
                    // Fallback to basic greeting if AI fails
                    val greeting = getGreeting()
                    val goalsResult = authHelper.getUserGoal(userId)
                    var currentGoal = "Health"
                    goalsResult.onSuccess { goal ->
                        currentGoal = goal["goalType"] as? String ?: "Health"
                    }
                    
                    runOnUiThread {
                        tvCoachMessage.text = "$greeting $userName! Keep staying consistent with your $currentGoal goal!"
                    }
                }
                
            } catch (e: Exception) {
                Log.e("Homepage", "Error updating coach message", e)
            }
        }
    }

    private fun updateWeightRemainingDisplay(goalType: String, currentWeight: Double, targetWeight: Double) {
        val tvGoalRemaining: TextView = findViewById(R.id.tv_goal_remaining)
        
        // Only show if both weights are valid (> 0)
        if (currentWeight > 0 && targetWeight > 0) {
            val weightDiff = when {
                // For "Gain Muscle" or "Weight Gain" - show how much MORE to gain
                goalType.contains("Gain", ignoreCase = true) || 
                goalType.contains("Muscle", ignoreCase = true) -> {
                    targetWeight - currentWeight  // e.g., 68 - 55 = 13 kg to gain
                }
                // For "Weight Loss" or "Lose Weight" - show how much to lose
                goalType.contains("Loss", ignoreCase = true) || 
                goalType.contains("Lose", ignoreCase = true) -> {
                    currentWeight - targetWeight  // e.g., 70 - 65 = 5 kg to lose
                }
                // For "Maintain" - show difference (usually small)
                else -> {
                    kotlin.math.abs(currentWeight - targetWeight)
                }
            }
            
            android.util.Log.d("Homepage", "Weight Difference: $weightDiff kg")
            
            // Only show if there's actually weight to gain/lose (> 0.1 kg)
            if (weightDiff > 0.1) {
                tvGoalRemaining.text = String.format("%.1f kg remaining", weightDiff)
                tvGoalRemaining.visibility = View.VISIBLE
                android.util.Log.d("Homepage", "Displaying: ${tvGoalRemaining.text}")
            } else {
                // Goal achieved!
                tvGoalRemaining.visibility = View.GONE
                android.util.Log.d("Homepage", "Goal achieved - hiding remaining")
            }
        } else {
            // Missing weight data
            tvGoalRemaining.visibility = View.GONE
            android.util.Log.d("Homepage", "Invalid weights (current: $currentWeight, target: $targetWeight) - hiding remaining")
        }
    }


    private fun displayWorkoutStatus() {
        lifecycleScope.launch {
            try {
                val today = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(java.util.Date())
                
                // Count all exercises logged today from exerciseLogs subcollection
                val exerciseLogs = firestore.collection("users")
                    .document(userId)
                    .collection("exercise_logs")
                    .whereEqualTo("date", today)
                    .get()
                    .await()
                
                val workoutCount = exerciseLogs.documents.size
                
                runOnUiThread {
                    tvWorkouts.text = workoutCount.toString()
                }
            } catch (e: Exception) {
                Log.e("Homepage", "Error fetching workout count", e)
                runOnUiThread {
                    tvWorkouts.text = "0"
                }
            }
        }
    }

    private fun displayWaterStatus() {
        lifecycleScope.launch {
            // Get total intake
            val totalResult = hydrationRepo.getTodayWaterTotal(userId)
            val goalResult = hydrationRepo.getWaterGoalWithCalculation(userId)
            
            if (totalResult.isSuccess && goalResult.isSuccess) {
                val total = totalResult.getOrDefault(0)
                val goal = goalResult.getOrDefault(2500)
                
                runOnUiThread {
                    // Update Progress Bar
                    val progress = if (goal > 0) ((total.toFloat() / goal.toFloat()) * 100).toInt() else 0
                    waterProgressBar.progress = progress.coerceIn(0, 100)
                    
                    // Update Text
                    tvWaterTotal.text = "$total / $goal ml"
                }
            } else {
                runOnUiThread {
                    waterProgressBar.progress = 0
                    tvWaterTotal.text = "0 ml"
                }
            }
        }
    }

    private fun displayNutritionBreakdown() {
        lifecycleScope.launch {
            authHelper.getTodayFoodLogs(userId).onSuccess { logs ->
                if (logs.isNotEmpty()) {
                    val totalProtein = logs.sumOf { it.protein }
                    val totalCarbs = logs.sumOf { it.carbs }
                    val totalFat = logs.sumOf { it.fat }
                    
                    tvProteinValue.text = "${totalProtein.toInt()}g / 120g"
                    tvCarbsValue.text = "${totalCarbs.toInt()}g / 200g"
                    tvFatsValue.text = "${totalFat.toInt()}g / 65g"
                    
                    pbProtein.progress = ((totalProtein / 120) * 100).toInt().coerceIn(0, 100)
                    pbCarbs.progress = ((totalCarbs / 200) * 100).toInt().coerceIn(0, 100)
                    pbFats.progress = ((totalFat / 65) * 100).toInt().coerceIn(0, 100)
                } else {
                    // No food logged - show zeros
                    tvProteinValue.text = "0g / 120g"
                    tvCarbsValue.text = "0g / 200g"
                    tvFatsValue.text = "0g / 65g"
                    
                    pbProtein.progress = 0
                    pbCarbs.progress = 0
                    pbFats.progress = 0
                }
            }.onFailure {
                // Error fetching logs - show zeros
                tvProteinValue.text = "0g / 120g"
                tvCarbsValue.text = "0g / 200g"
                tvFatsValue.text = "0g / 65g"
                
                pbProtein.progress = 0
                pbCarbs.progress = 0
                pbFats.progress = 0
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::stepManager.isInitialized) {
            stepManager.stop()
        }
    }

    // AI Exercise recommendation function removed
    // All AI exercise functionality is now exclusively in WorkoutDashboardActivity
    // Users access it by: Homepage → Tap "Workout" card → WorkoutDashboardActivity → "AI Exercise 🤖" button

    // AI Exercise functions removed - All functionality now in WorkoutDashboardActivity
    // Users tap "Workout" card → WorkoutDashboardActivity → "AI Exercise 🤖" button

    private fun getAvatarDrawable(avatarId: String): Int {
        val resName = avatarId.replace("_", "")
        return try {
            resources.getIdentifier(resName, "drawable", packageName)
        } catch (e: Exception) {
            0
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }


    
    // ========== Calorie Balance Tracking ==========
    
    private fun updateCalorieBalance() {
        lifecycleScope.launch {
            try {
                val dateFormat = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
                val today = dateFormat.format(java.util.Date())
                
                // 1. Get target calories from goals
                val goalsResult = authHelper.getUserGoal(userId)
                var targetCalories = 2000
                goalsResult.onSuccess { goal ->
                    targetCalories = (goal["dailyCalories"] as? Number)?.toInt() ?: 2000
                }
                
                // 2. Calculate Calories In (from food logs)
                val caloriesIn = calculateCaloriesIn(today)
                
                // 3. Calculate Calories Out (steps + workouts)
                val caloriesOut = calculateCaloriesOut(today)
                
                // 4. Calculate net balance
                val netBalance = caloriesIn - caloriesOut
                
                // 5. Update UI
                runOnUiThread {
                    updateCalorieBalanceUI(caloriesIn, caloriesOut, netBalance, targetCalories)
                }
                
            } catch (e: Exception) {
                Log.e("Homepage", "Error updating calorie balance", e)
            }
        }
    }
    
    private suspend fun calculateCaloriesIn(date: String): Int {
        return try {
            val foodLogs = firestore.collection("users")
                .document(userId)
                .collection("foodLogs")
                .whereEqualTo("date", date)
                .get()
                .await()
            
            var totalCalories = 0
            for (doc in foodLogs.documents) {
                val calories = (doc.get("calories") as? Number)?.toInt() ?: 0
                totalCalories += calories
            }
            totalCalories
        } catch (e: Exception) {
            Log.e("Homepage", "Error calculating calories in", e)
            0
        }
    }
    
    private suspend fun calculateCaloriesOut(date: String): Int {
        return try {
            var totalCalories = 0
            
            // 1. Calories from steps
            val steps = if (::stepManager.isInitialized) stepManager.dailySteps else 0
            val stepCalories = (steps * 0.04).toInt() // ~0.04 kcal per step
            totalCalories += stepCalories
            
            // 2. Calories from logged workouts
            val workoutLogs = firestore.collection("users").document(userId).collection("exercise_logs")
                .whereEqualTo("date", date)
                .get()
                .await()
            
            for (doc in workoutLogs.documents) {
                val calories = (doc.get("caloriesBurned") as? Number)?.toInt() ?: 0
                totalCalories += calories
            }
            
            totalCalories
        } catch (e: Exception) {
            Log.e("Homepage", "Error calculating calories out", e)
            0
        }
    }
    
    private fun updateCalorieBalanceUI(caloriesIn: Int, caloriesOut: Int, netBalance: Int, targetCalories: Int) {
        // Update Calories In
        tvCaloriesIn.text = caloriesIn.toString()
        val percentIn = ((caloriesIn.toFloat() / targetCalories) * 100).toInt().coerceIn(0, 100)
        pbCaloriesIn.progress = percentIn
        
        // Update Calories Out
        tvCaloriesOut.text = caloriesOut.toString()
        val percentOut = ((caloriesOut.toFloat() / targetCalories) * 100).toInt().coerceIn(0, 100)
        pbCaloriesOut.progress = percentOut
        
        // Update Net Balance with color coding
        val balanceText = if (netBalance >= 0) "+$netBalance" else "$netBalance"
        tvNetBalance.text = "$balanceText kcal"
        
        // Color code based on goal type
        val goalTypeLower = goalType.lowercase()
        val balanceColor = when {
            goalTypeLower.contains("loss") && netBalance < 0 -> android.graphics.Color.parseColor("#4CAF50") // Deficit is good for weight loss
            goalTypeLower.contains("gain") && netBalance > 0 -> android.graphics.Color.parseColor("#4CAF50") // Surplus is good for weight gain
            goalTypeLower.contains("maintain") && kotlin.math.abs(netBalance) < 200 -> android.graphics.Color.parseColor("#4CAF50") // Near balance
            netBalance > 0 -> android.graphics.Color.parseColor("#E91E63") // Surplus (pink)
            else -> android.graphics.Color.parseColor("#2196F3") // Deficit (blue)
        }
        tvNetBalance.setTextColor(balanceColor)
        
        // Update Goal with comma formatting
        val formattedGoal = String.format("%,d", targetCalories)
        tvCalorieGoal.text = "$formattedGoal kcal"
        
        // Update Status Message
        val percentConsumed = ((caloriesIn.toFloat() / targetCalories) * 100).toInt()
        val statusMessage = when {
            percentConsumed < 50 -> "You've consumed $percentConsumed% of your daily goal. Keep going! 💪"
            percentConsumed in 50..80 -> "Great progress! You've consumed $percentConsumed% of your daily goal 🎯"
            percentConsumed in 81..100 -> "Almost there! You've consumed $percentConsumed% of your daily goal ✨"
            percentConsumed in 101..120 -> "You've reached your daily goal! ($percentConsumed%) 🎉"
            else -> "You've exceeded your daily goal ($percentConsumed%). Monitor your intake 📊"
        }
        tvCalorieStatus.text = statusMessage
    }



}

