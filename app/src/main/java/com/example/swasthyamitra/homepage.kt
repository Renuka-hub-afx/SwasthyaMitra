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

    // AI Recommendation UI
    private lateinit var cardAiExercise: androidx.cardview.widget.CardView
    private lateinit var tvRecExerciseName: TextView
    private lateinit var tvRecTargetMuscle: TextView
    private lateinit var tvRecReason: TextView
    private lateinit var btnViewExerciseDetails: MaterialButton
    private lateinit var btnLogRecExercise: MaterialButton
    private lateinit var btnRegenerateExercise: MaterialButton
    private lateinit var ivExerciseGif: android.widget.ImageView
    private lateinit var tvAgeExplanation: TextView
    private lateinit var tvGenderNote: TextView
    private lateinit var tvMotivationalMessage: TextView
    private lateinit var tvExerciseCalories: TextView
    private lateinit var tvExerciseDuration: TextView
    private var currentRecommendedExercise: com.example.swasthyamitra.ai.AIExerciseRecommendationService.ExerciseRec? = null

    private lateinit var menuHome: LinearLayout
    private lateinit var menuProgress: LinearLayout
    private lateinit var menuProfile: LinearLayout

    private lateinit var cardFood: MaterialButton
    private lateinit var cardWorkout: MaterialButton
    private lateinit var cardWater: MaterialButton

    private lateinit var tvWaterTotal: TextView
    private lateinit var cardWaterSummary: View
    private lateinit var tvDate: TextView
    private lateinit var chipPeriodMode: com.google.android.material.chip.Chip
    
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
        setContentView(R.layout.activity_homepage)

        val application = application as? UserApplication
        if (application == null) {
            navigateToLogin()
            return
        }
        authHelper = application.authHelper
        firestore = FirebaseFirestore.getInstance()

        userId = intent.getStringExtra("USER_ID") ?: ""

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
        cardWater = findViewById(R.id.card_water)
        cardWaterSummary = findViewById(R.id.card_water_summary)
        tvWaterTotal = findViewById(R.id.tv_water_total)

        cardAiExercise = findViewById(R.id.card_ai_exercise)
        tvRecExerciseName = findViewById(R.id.tv_rec_exercise_name)
        tvRecTargetMuscle = findViewById(R.id.tv_rec_target_muscle)
        tvRecReason = findViewById(R.id.tv_rec_reason)
        btnViewExerciseDetails = findViewById(R.id.btn_view_exercise_details)
        btnLogRecExercise = findViewById(R.id.btn_log_rec_exercise)
        btnRegenerateExercise = findViewById(R.id.btn_regenerate_exercise)
        chipPeriodMode = findViewById(R.id.chip_period_mode)
        ivExerciseGif = findViewById(R.id.iv_exercise_gif)
        tvAgeExplanation = findViewById(R.id.tv_age_explanation)
        tvGenderNote = findViewById(R.id.tv_gender_note)
        tvMotivationalMessage = findViewById(R.id.tv_motivational_message)
        tvExerciseCalories = findViewById(R.id.tv_exercise_calories)
        tvExerciseDuration = findViewById(R.id.tv_exercise_duration)
        
        // Initialize calorie balance UI
        tvCaloriesIn = findViewById(R.id.tv_calories_in)
        tvCaloriesOut = findViewById(R.id.tv_calories_out)
        pbCaloriesIn = findViewById(R.id.pb_calories_in)
        pbCaloriesOut = findViewById(R.id.pb_calories_out)
        tvNetBalance = findViewById(R.id.tv_net_balance)
        tvCalorieGoal = findViewById(R.id.tv_calorie_goal)
        tvCalorieStatus = findViewById(R.id.tv_calorie_status)
        
        // Initialize History UI


        updateDateDisplay()
        loadUserData()

        // Initialize Step Tracking
        stepManager = StepManager(this) { steps, _ ->
            runOnUiThread {
                tvSteps.text = steps.toString()
            }
        }
        stepManager.start()

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

        chipPeriodMode.setOnCheckedChangeListener { _, isChecked ->
            isOnPeriod = isChecked
            lifecycleScope.launch {
                try {
                    firestore.collection("users").document(userId)
                        .update("isOnPeriod", isChecked)
                        .await()
                    
                    // Trigger AI refreshes for specialized mode
                    updateAICoachMessage()
                    updateAIExerciseRecommendation()
                    
                    val status = if (isChecked) "activated 🌸" else "deactivated"
                    Toast.makeText(this@homepage, "Period Mode $status", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Log.e("Homepage", "Error updating period mode", e)
                }
            }
        }


        
        menuHome.setOnClickListener {
            Toast.makeText(this, "You are on Home", Toast.LENGTH_SHORT).show()
        }

        menuProgress.setOnClickListener {
            startActivity(Intent(this, ProgressActivity::class.java))
        }

        menuProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        // Setup AI Diet Button
        val cardAiDiet: MaterialButton = findViewById(R.id.card_ai_diet)
        cardAiDiet.setOnClickListener {
            val intent = Intent(this, AISmartDietActivity::class.java)
            startActivity(intent)
        }

        btnViewExerciseDetails.setOnClickListener {
            showExerciseGuideDialog()
        }

        btnLogRecExercise.setOnClickListener {
            logRecommendedExercise()
        }

        btnRegenerateExercise.setOnClickListener {
            lifecycleScope.launch {
                try {
                    // Clear existing recommendation to force new generation
                    firestore.collection("users").document(userId)
                        .update("currentDailyExercise", null, "lastExerciseDate", null)
                        .await()
                    updateAIExerciseRecommendation()
                } catch (e: Exception) {
                    Log.e("Homepage", "Error skipping exercise", e)
                }
            }
        }
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
            updateAIExerciseRecommendation()
            updateCalorieBalance()
            updateCalorieBalance()
        }
    }

    private fun loadUserData() {
        lifecycleScope.launch {
            try {
                val userDataResult = authHelper.getUserData(userId)
                userDataResult.onSuccess { userData ->
                    userName = userData["name"] as? String ?: "User"
                    tvUserName.text = "Hello, $userName!"
                    
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
                    
                    // Get current weight - first try from goal, then from user profile
                    var currentWeight = when {
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
                    
                    // Debug logging
                    android.util.Log.d("Homepage", "===== WEIGHT CALCULATION DEBUG =====")
                    android.util.Log.d("Homepage", "Goal Type: $goalType")
                    android.util.Log.d("Homepage", "Current Weight (from goal): $currentWeight kg")
                    android.util.Log.d("Homepage", "Target Weight: $targetWeight kg")
                    
                    // If current weight is 0, try to get it from user profile
                    if (currentWeight == 0.0) {
                        lifecycleScope.launch {
                            authHelper.getUserData(userId).onSuccess { userData ->
                                val userWeight = when (val w = userData["weight"]) {
                                    is Number -> w.toDouble()
                                    is String -> w.toDoubleOrNull() ?: 0.0
                                    else -> 0.0
                                }
                                if (userWeight > 0) {
                                    currentWeight = userWeight
                                    android.util.Log.d("Homepage", "Current Weight (from user profile): $currentWeight kg")
                                    updateWeightRemainingDisplay(goalType, currentWeight, targetWeight)
                                }
                            }
                        }
                    } else {
                        updateWeightRemainingDisplay(goalType, currentWeight, targetWeight)
                    }
                    
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
                
                // Count all exercises logged today from exercise_logs collection
                val exerciseLogs = firestore.collection("exercise_logs")
                    .whereEqualTo("userId", userId)
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
                
                // Format: "1250 / 2500 ml"
                 tvWaterTotal.text = "$total / $goal ml"
            } else {
                 tvWaterTotal.text = "0 ml"
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

    private fun updateAIExerciseRecommendation() {
        lifecycleScope.launch {
            try {
                // 0. Check if it's time to show the recommendation
                val userId = authHelper.getCurrentUser()?.uid ?: return@launch
                val profile = firestore.collection("users").document(userId).get().await()
                val preferredTime = profile.getString("preferredExerciseTime") ?: "Morning"
                
                val calendar = java.util.Calendar.getInstance()
                val currentHour = calendar.get(java.util.Calendar.HOUR_OF_DAY)
                
                val isTimeMatch = when(preferredTime) {
                    "Morning" -> currentHour in 5..11
                    "Afternoon" -> currentHour in 12..16
                    "Evening" -> currentHour in 17..20
                    "Night" -> currentHour in 21..23 || currentHour in 0..4
                    else -> true
                }

                if (!isTimeMatch) {
                    runOnUiThread {
                        cardAiExercise.visibility = View.GONE
                    }
                    return@launch
                }

                // 0.1 Check if already completed today
                val db = com.google.firebase.database.FirebaseDatabase.getInstance("https://swasthyamitra-c0899-default-rtdb.asia-southeast1.firebasedatabase.app").reference
                val today = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(java.util.Date())
                val completedToday = db.child("users").child(userId).child("completionHistory").child(today).get().await().getValue(Boolean::class.java) ?: false
                
                if (completedToday) {
                    runOnUiThread {
                        cardAiExercise.visibility = View.GONE
                    }
                    return@launch
                }

                // 0.2 Check if we already have a sticky recommendation for today
                val lastDate = profile.getString("lastExerciseDate") ?: ""
                if (lastDate == today) {
                    val savedExercise = profile.get("currentDailyExercise") as? Map<String, Any>
                    if (savedExercise != null) {
                        val rec = com.example.swasthyamitra.ai.AIExerciseRecommendationService.ExerciseRec(
                            name = savedExercise["name"] as? String ?: "",
                            targetMuscle = savedExercise["targetMuscle"] as? String ?: "",
                            bodyPart = savedExercise["bodyPart"] as? String ?: "",
                            equipment = savedExercise["equipment"] as? String ?: "",
                            instructions = (savedExercise["instructions"] as? List<String>) ?: emptyList(),
                            reason = savedExercise["reason"] as? String ?: "",
                            estimatedCalories = (savedExercise["estimatedCalories"] as? Number)?.toInt() ?: 100,
                            recommendedDuration = savedExercise["recommendedDuration"] as? String ?: "15 mins"
                        )
                        currentRecommendedExercise = rec
                        runOnUiThread {
                            tvRecExerciseName.text = rec.name
                            tvRecTargetMuscle.text = "Target: ${rec.targetMuscle}"
                            tvRecReason.text = rec.reason
                            tvExerciseCalories.text = "🔥 ~${rec.estimatedCalories} kcal"
                            tvExerciseDuration.text = "⏱️ ${rec.recommendedDuration}"
                            cardAiExercise.visibility = View.VISIBLE
                            btnRegenerateExercise.isEnabled = true
                        }
                        return@launch
                    }
                }

                // 1. Calculate burned calories from steps
                val steps = if (::stepManager.isInitialized) stepManager.dailySteps else 0
                val burnedFromSteps = (steps * 0.04).toInt()

                // 2. Refresh UI to show loading state
                runOnUiThread {
                    tvRecExerciseName.text = "Consulting Coach... 🧠"
                    btnRegenerateExercise.isEnabled = false
                }

                val service = com.example.swasthyamitra.ai.AIExerciseRecommendationService.getInstance(this@homepage)
                val result = service.getExerciseRecommendation(burnedFromSteps)
                
                result.onSuccess { rec ->
                    currentRecommendedExercise = rec

                    // Save recommendation for stickiness
                    val exerciseMap = hashMapOf(
                        "name" to rec.name,
                        "targetMuscle" to rec.targetMuscle,
                        "bodyPart" to rec.bodyPart,
                        "equipment" to rec.equipment,
                        "instructions" to rec.instructions,
                        "reason" to rec.reason,
                        "estimatedCalories" to rec.estimatedCalories,
                        "recommendedDuration" to rec.recommendedDuration
                    )
                    
                    firestore.collection("users").document(userId)
                        .update("currentDailyExercise", exerciseMap, "lastExerciseDate", today)
                        .await()

                    runOnUiThread {
                        tvRecExerciseName.text = rec.name
                        tvRecTargetMuscle.text = "Target: ${rec.targetMuscle}"
                        tvRecReason.text = rec.reason
                        tvExerciseCalories.text = "🔥 ~${rec.estimatedCalories} kcal"
                        tvExerciseDuration.text = "⏱️ ${rec.recommendedDuration}"
                        
                        // Display GIF if available
                        if (rec.gifUrl.isNotEmpty()) {
                            try {
                                com.bumptech.glide.Glide.with(this@homepage)
                                    .load("file:///android_asset/${rec.gifUrl}")
                                    .into(ivExerciseGif)
                                ivExerciseGif.visibility = android.view.View.VISIBLE
                            } catch (e: Exception) {
                                Log.e("Homepage", "Error loading GIF: ${e.message}")
                                ivExerciseGif.visibility = android.view.View.GONE
                            }
                        } else {
                            ivExerciseGif.visibility = android.view.View.GONE
                        }
                        
                        // Display age explanation
                        if (rec.ageExplanation.isNotEmpty()) {
                            tvAgeExplanation.text = "💡 Age ${profile["age"]}: ${rec.ageExplanation}"
                            tvAgeExplanation.visibility = android.view.View.VISIBLE
                        } else {
                            tvAgeExplanation.visibility = android.view.View.GONE
                        }
                        
                        // Display gender-specific benefits
                        if (rec.genderNote.isNotEmpty()) {
                            tvGenderNote.text = "✨ ${rec.genderNote}"
                            tvGenderNote.visibility = android.view.View.VISIBLE
                        } else {
                            tvGenderNote.visibility = android.view.View.GONE
                        }
                        
                        // Display motivational message (Period Mode)
                        if (rec.motivationalMessage.isNotEmpty()) {
                            tvMotivationalMessage.text = rec.motivationalMessage
                            tvMotivationalMessage.visibility = android.view.View.VISIBLE
                        } else {
                            tvMotivationalMessage.visibility = android.view.View.GONE
                        }
                        
                        cardAiExercise.visibility = View.VISIBLE
                        btnRegenerateExercise.isEnabled = true
                    }
                }.onFailure {
                    currentRecommendedExercise = null
                    runOnUiThread {
                        cardAiExercise.visibility = View.GONE
                        btnRegenerateExercise.isEnabled = true
                    }
                }
            } catch (e: Exception) {
                Log.e("Homepage", "Error updating exercise rec", e)
            }
        }
    }

    private fun logRecommendedExercise() {
        val exercise = currentRecommendedExercise ?: return
        lifecycleScope.launch {
            try {
                val db = com.google.firebase.database.FirebaseDatabase.getInstance("https://swasthyamitra-c0899-default-rtdb.asia-southeast1.firebasedatabase.app").reference
                val today = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(java.util.Date())
                
                // 1. Mark as completed in Realtime DB (for homepage counter)
                db.child("users").child(userId).child("completionHistory").child(today).setValue(true)
                
                // 2. Log full details in Firestore
                val firestore = com.google.firebase.firestore.FirebaseFirestore.getInstance()
                val logData = hashMapOf(
                    "userId" to userId,
                    "exerciseName" to exercise.name,
                    "targetMuscle" to exercise.targetMuscle,
                    "bodyPart" to exercise.bodyPart,
                    "equipment" to exercise.equipment,
                    "caloriesBurned" to exercise.estimatedCalories,
                    "duration" to exercise.recommendedDuration,
                    "timestamp" to System.currentTimeMillis(),
                    "date" to today,
                    "source" to "AI_Recommendation"
                )
                firestore.collection("exerciseLogs").add(logData)
                
                runOnUiThread {
                    Toast.makeText(this@homepage, "Great job! Exercise logged successfully ✅", Toast.LENGTH_SHORT).show()
                    displayWorkoutStatus() // Refresh the counter
                    cardAiExercise.visibility = View.GONE // Hide after logging
                }
            } catch (e: Exception) {
                Log.e("Homepage", "Error logging exercise", e)
                Toast.makeText(this@homepage, "Failed to log exercise", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showExerciseGuideDialog() {
        val exercise = currentRecommendedExercise ?: run {
            Toast.makeText(this, "No exercise recommended yet", Toast.LENGTH_SHORT).show()
            return
        }

        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("${exercise.name.uppercase()} 💪")
        
        val message = StringBuilder()
        message.append("🎯 Target: ${exercise.targetMuscle}\n")
        message.append("📦 Equipment: ${exercise.equipment}\n\n")
        
        // Add age explanation if available
        if (exercise.ageExplanation.isNotEmpty()) {
            message.append("💡 Why for your age:\n${exercise.ageExplanation}\n\n")
        }
        
        // Add gender benefits if available
        if (exercise.genderNote.isNotEmpty()) {
            message.append("✨ Benefits for you:\n${exercise.genderNote}\n\n")
        }
        
        // Add motivational message if available
        if (exercise.motivationalMessage.isNotEmpty()) {
            message.append("🌸 ${exercise.motivationalMessage}\n\n")
        }
        
        message.append("📝 HOW TO DO IT:\n")
        exercise.instructions.forEachIndexed { index, step ->
            message.append("${index + 1}. $step\n")
        }
        
        builder.setMessage(message.toString())
        builder.setPositiveButton("Got it! 👍") { dialog, _ -> dialog.dismiss() }
        builder.show()
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
            val foodLogs = firestore.collection("foodLogs")
                .whereEqualTo("userId", userId)
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
            val workoutLogs = firestore.collection("exercise_logs")
                .whereEqualTo("userId", userId)
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
