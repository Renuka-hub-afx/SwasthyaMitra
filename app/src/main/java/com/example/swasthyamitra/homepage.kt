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
    private lateinit var cardMealPlan: MaterialButton

    private var goalType: String = ""
    private var userName: String = ""

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
        cardMealPlan = findViewById(R.id.card_meal_plan)

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

        cardMealPlan.setOnClickListener {
            startActivity(Intent(this, MealPlanActivity::class.java))
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
    }

    override fun onResume() {
        super.onResume()
        if (userId.isNotEmpty()) {
            displayTodayCalories()
            displayNutritionBreakdown()
            displayWorkoutStatus()
        }
    }

    private fun loadUserData() {
        lifecycleScope.launch {
            try {
                val userDataResult = authHelper.getUserData(userId)
                userDataResult.onSuccess { userData ->
                    userName = userData["name"] as? String ?: "User"
                    tvUserName.text = userName
                }

                val goalsResult = authHelper.getUserGoal(userId)
                goalsResult.onSuccess { goal ->
                    goalType = goal["goalType"] as? String ?: "No Goal Set"
                    tvGoalType.text = "Your Goal: $goalType"
                }
                
                tvCoachMessage.text = "Check the Workout tab for your activity recommendations! 🔥"

            } catch (e: Exception) { }
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
