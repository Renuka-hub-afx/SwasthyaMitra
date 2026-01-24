package com.example.swasthyamitra

import android.content.Intent
import android.os.Bundle
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

    // CHANGED: These are LinearLayouts in your XML, not TextViews
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

        // Validate UserApplication
        val application = application as? UserApplication
        if (application == null) {
            Toast.makeText(this, "App initialization error", Toast.LENGTH_LONG).show()
            navigateToLogin()
            return
        }
        authHelper = application.authHelper

        // Get and validate User ID from Intent
        userId = intent.getStringExtra("USER_ID") ?: ""

        if (userId.isEmpty()) {
            Toast.makeText(this, "User ID missing. Please log in again.", Toast.LENGTH_LONG).show()
            navigateToLogin()
            return
        }

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

        // These will now work correctly because the variable types match the XML types
        menuHome = findViewById(R.id.menu_home)
        menuProgress = findViewById(R.id.menu_progress)
        menuProfile = findViewById(R.id.menu_profile)

        cardFood = findViewById(R.id.card_food)
        cardWorkout = findViewById(R.id.card_workout)
        cardMealPlan = findViewById(R.id.card_meal_plan)

        // Load User Data
        loadUserData()

        // Set Click Listeners for Menu
        menuHome.setOnClickListener {
            Toast.makeText(this, "You are on Home", Toast.LENGTH_SHORT).show()
        }

        menuProgress.setOnClickListener {
            val intent = Intent(this, ProgressActivity::class.java)
            startActivity(intent)
        }

        menuProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        cardFood.setOnClickListener {
            // Navigate to Food Log Activity
            val intent = Intent(this, FoodLogActivity::class.java)
            startActivity(intent)
        }

        cardWorkout.setOnClickListener {
            Toast.makeText(this, "Workout - Coming Soon!", Toast.LENGTH_SHORT).show()
        }

        cardMealPlan.setOnClickListener {
            // Navigate to Meal Plan Activity
            val intent = Intent(this, MealPlanActivity::class.java)
            startActivity(intent)
        }

        // Setup AI Diet Button
        val cardAiDiet: MaterialButton = findViewById(R.id.card_ai_diet)
        cardAiDiet.setOnClickListener {
            val intent = Intent(this, AISmartDietActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadUserData() {
        lifecycleScope.launch {
            try {
                // Fetch user data
                val userDataResult = authHelper.getUserData(userId)
                userDataResult.onSuccess { userData ->
                    userName = userData["name"] as? String ?: "User"
                    tvUserName.text = userName
                }

                // Fetch user goal
                val goalsResult = authHelper.getUserGoal(userId)
                goalsResult.onSuccess { goal ->
                    goalType = goal["goalType"] as? String ?: "No Goal Set"
                    tvGoalType.text = "Your Goal: $goalType"
                }.onFailure {
                    tvGoalType.text = "Your Goal: --"
                }

                // Load today's calories and nutrition
                displayTodayCalories()
                displayNutritionBreakdown()

                // Show AI coach message
                tvCoachMessage.text = generateSmartCoachMessage()

                tvSteps.text = "0"
                tvWorkouts.text = "0"

            } catch (e: Exception) {
                Toast.makeText(this@homepage, "Error loading data: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun generateSmartCoachMessage(): String {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val greeting = when (hour) {
            in 5..11 -> "Good morning"
            in 12..16 -> "Good afternoon"
            in 17..20 -> "Good evening"
            else -> "Hello"
        }

        return "$greeting $userName! 🌟\nStay consistent with your logging to reach your $goalType goal!"
    }

    private fun displayTodayCalories() {
        lifecycleScope.launch {
            try {
                val result = authHelper.getTodayCalories(userId)
                result.onSuccess { calories ->
                    tvCalories.text = calories.toString()
                }.onFailure {
                    tvCalories.text = "0"
                }
            } catch (e: Exception) {
                tvCalories.text = "0"
            }
        }
    }

    private fun displayNutritionBreakdown() {
        lifecycleScope.launch {
            try {
                val result = authHelper.getTodayFoodLogs(userId)
                result.onSuccess { logs ->
                    if (logs.isNotEmpty()) {
                        val totalProtein = logs.sumOf { it.protein }
                        val totalCarbs = logs.sumOf { it.carbs }
                        val totalFat = logs.sumOf { it.fat }

                        // Set target values (you can adjust these based on user goals)
                        val proteinTarget = 120
                        val carbsTarget = 200
                        val fatsTarget = 65

                        // Update TextViews
                        tvProteinValue.text = "${totalProtein.toInt()}g / ${proteinTarget}g"
                        tvCarbsValue.text = "${totalCarbs.toInt()}g / ${carbsTarget}g"
                        tvFatsValue.text = "${totalFat.toInt()}g / ${fatsTarget}g"

                        // Update ProgressBars
                        pbProtein.progress = ((totalProtein / proteinTarget) * 100).toInt().coerceIn(0, 100)
                        pbCarbs.progress = ((totalCarbs / carbsTarget) * 100).toInt().coerceIn(0, 100)
                        pbFats.progress = ((totalFat / fatsTarget) * 100).toInt().coerceIn(0, 100)
                    } else {
                        // Reset to default values when no food logged
                        tvProteinValue.text = "0g / 120g"
                        tvCarbsValue.text = "0g / 200g"
                        tvFatsValue.text = "0g / 65g"
                        pbProtein.progress = 0
                        pbCarbs.progress = 0
                        pbFats.progress = 0
                    }
                }.onFailure {
                    // Reset on failure
                    tvProteinValue.text = "0g / 120g"
                    tvCarbsValue.text = "0g / 200g"
                    tvFatsValue.text = "0g / 65g"
                    pbProtein.progress = 0
                    pbCarbs.progress = 0
                    pbFats.progress = 0
                }
            } catch (e: Exception) {
                // Reset on exception
                tvProteinValue.text = "0g / 120g"
                tvCarbsValue.text = "0g / 200g"
                tvFatsValue.text = "0g / 65g"
                pbProtein.progress = 0
                pbCarbs.progress = 0
                pbFats.progress = 0
            }
        }
    }

    private fun handleLogout() {
        authHelper.signOut()

        // Clear SharedPreferences
        val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()

        navigateToLogin()
    }

    private fun navigateToLogin() {
        // Navigate to Login
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}