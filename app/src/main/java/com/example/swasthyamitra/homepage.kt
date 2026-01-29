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

class homepage : AppCompatActivity() {

    private lateinit var authHelper: FirebaseAuthHelper
    private lateinit var stepManager: StepManager
    private var userId: String = ""

    // UI Elements
    private lateinit var tvUserName: TextView
    private lateinit var tvGoalType: TextView
    private lateinit var tvCalories: TextView
    private lateinit var tvSteps: TextView
    private lateinit var tvWorkouts: TextView
    private lateinit var tvCoachMessage: TextView
    private lateinit var tvProteinValue: TextView
    private lateinit var tvCarbsValue: TextView
    private lateinit var tvFatsValue: TextView
    private lateinit var pbProtein: ProgressBar
    private lateinit var pbCarbs: ProgressBar
    private lateinit var pbFats: ProgressBar

    private lateinit var menuHome: LinearLayout
    private lateinit var menuProgress: LinearLayout
    private lateinit var menuProfile: LinearLayout

    private lateinit var cardFood: MaterialButton
    private lateinit var cardWorkout: MaterialButton
    private lateinit var cardWater: MaterialButton

    private lateinit var tvWaterTotal: TextView
    private lateinit var cardWaterSummary: View
    private lateinit var tvDate: TextView

    private var goalType: String = ""
    private var userName: String = ""
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

        userId = intent.getStringExtra("USER_ID") ?: ""

        // Initialize UI Elements
        tvDate = findViewById(R.id.tv_date)
        tvUserName = findViewById(R.id.tv_user_name)
        tvGoalType = findViewById(R.id.tv_goal_type)
        tvCalories = findViewById(R.id.tv_calories)
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
        tvDate.text = dateFormat.format(calendar.time)
    }

    override fun onResume() {
        super.onResume()
        if (userId.isNotEmpty()) {
            displayTodayCalories()
            displayNutritionBreakdown()
            displayWorkoutStatus()
            displayWaterStatus()
            updateAICoachMessage()
        }
    }

    private fun loadUserData() {
        lifecycleScope.launch {
            try {
                val userDataResult = authHelper.getUserData(userId)
                userDataResult.onSuccess { userData ->
                    userName = userData["name"] as? String ?: "User"
                    tvUserName.text = "Hello, $userName!"
                }

                val goalsResult = authHelper.getUserGoal(userId)
                goalsResult.onSuccess { goal ->
                    goalType = goal["goalType"] as? String ?: "Stay Healthy"
                    tvGoalType.text = "Your Goal: $goalType"
                    
                    val greeting = getGreeting()
                    val emoji = when(greeting) {
                        "Good Morning" -> "☀️"
                        "Good Afternoon" -> "🌟"
                        else -> "🌙"
                    }
                    
                    tvCoachMessage.text = "${greeting.lowercase()} $userName! $emoji\nStay consistent with your logging to reach your $goalType goal!"
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
                // 1. Fetch all necessary data
                val goalsResult = authHelper.getUserGoal(userId)
                val caloriesResult = authHelper.getTodayCalories(userId)
                val exerciseLogs = authHelper.getRecentExerciseLogs(userId, 2) // Check last 2 days
                val steps = if (::stepManager.isInitialized) stepManager.dailySteps else 0
                
                var targetCalories = 2000
                var currentGoal = "Wellness"
                
                goalsResult.onSuccess { goal ->
                    targetCalories = (goal["dailyCalories"] as? Number)?.toInt() ?: 2000
                    currentGoal = goal["goalType"] as? String ?: "Wellness"
                }
                
                val consumed = caloriesResult.getOrDefault(0)
                val burnedFromSteps = (steps * 0.04).toInt()
                val netCalories = consumed - burnedFromSteps
                
                // 2. Logic for high intensity exercise
                val hadHighIntensity = exerciseLogs.any { 
                    (it["intensity"] as? String)?.contains("High", ignoreCase = true) == true || 
                    (it["type"] as? String)?.contains("HIIT", ignoreCase = true) == true 
                }
                
                // 3. Generate specific coach message
                val message = when {
                    hadHighIntensity && consumed < targetCalories -> {
                        "🔥 Great high-intensity work! Your body needs recovery fuel. Check the AI Diet Plan for a protein-rich post-workout meal."
                    }
                    netCalories > targetCalories + 200 -> {
                        "⚖️ Caloric surplus detected! You've eaten ${netCalories - targetCalories} kcal above your $currentGoal goal. Let's burn it off with a quick Cardio session!"
                    }
                    steps < 3000 && Calendar.getInstance().get(Calendar.HOUR_OF_DAY) > 16 -> {
                        "👟 Activity Alert: You're below 3000 steps for today. A 15-minute evening walk will keep your metabolism active!"
                    }
                    consumed > 0 && Math.abs(netCalories - targetCalories) < 150 -> {
                        "🎯 Perfect Balance! You're exactly on track with your $currentGoal nutrition. Keep this consistency up!"
                    }
                    else -> {
                        val greeting = getGreeting()
                        "$greeting $userName! Stay consistent with your logging to reach your $currentGoal goal!"
                    }
                }
                
                runOnUiThread {
                    tvCoachMessage.text = message
                }
                
            } catch (e: Exception) {
                Log.e("Homepage", "Error updating coach message", e)
            }
        }
    }


    private fun displayWorkoutStatus() {
        val db = com.google.firebase.database.FirebaseDatabase.getInstance("https://swasthyamitra-c0899-default-rtdb.asia-southeast1.firebasedatabase.app").reference
        val today = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(java.util.Date())
        
        db.child("users").child(userId).child("completionHistory").child(today).get()
            .addOnSuccessListener { snapshot ->
                if (snapshot.exists() && snapshot.value == true) {
                    tvWorkouts.text = "1"
                } else {
                    tvWorkouts.text = "0"
                }
            }
            .addOnFailureListener {
                tvWorkouts.text = "0" 
            }
    }

    private fun displayTodayCalories() {
        lifecycleScope.launch {
            authHelper.getTodayCalories(userId).onSuccess { calories ->
                tvCalories.text = calories.toString()
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
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::stepManager.isInitialized) {
            stepManager.stop()
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
