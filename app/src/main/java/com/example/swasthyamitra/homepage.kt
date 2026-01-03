package com.example.swasthyamitra

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.launch
import java.util.Calendar
import java.time.LocalDate

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
    private lateinit var tvNutritionBreakdown: TextView
    private lateinit var menuHome: TextView
    private lateinit var menuProgress: TextView
    private lateinit var menuProfile: TextView
    private lateinit var cardFood: MaterialButton
    private lateinit var cardWorkout: MaterialButton
    
    private var goalType: String = ""
    private var userName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        val application = application as UserApplication
        authHelper = application.authHelper

        // Get User ID from Intent
        userId = intent.getStringExtra("USER_ID") ?: ""

        // Initialize UI Elements
        tvUserName = findViewById(R.id.tv_user_name)
        tvGoalType = findViewById(R.id.tv_goal_type)
        tvCalories = findViewById(R.id.tv_calories)
        tvSteps = findViewById(R.id.tv_steps)
        tvWorkouts = findViewById(R.id.tv_workouts)
        tvCoachMessage = findViewById(R.id.tv_coach_message)
        tvNutritionBreakdown = findViewById(R.id.tv_nutrition_breakdown)
        menuHome = findViewById(R.id.menu_home)
        menuProgress = findViewById(R.id.menu_progress)
        menuProfile = findViewById(R.id.menu_profile)
        cardFood = findViewById(R.id.card_food)
        cardWorkout = findViewById(R.id.card_workout)

        // Load User Data
        loadUserData()

        // Set Click Listeners for Menu
        menuHome.setOnClickListener {
            Toast.makeText(this, "You are on Home", Toast.LENGTH_SHORT).show()
        }

        menuProgress.setOnClickListener {
            Toast.makeText(this, "Progress - Coming Soon!", Toast.LENGTH_SHORT).show()
        }

        menuProfile.setOnClickListener {
            Toast.makeText(this, "Profile - Coming Soon!", Toast.LENGTH_SHORT).show()
        }

        cardFood.setOnClickListener {
            // Navigate to Food Log Activity
            val intent = Intent(this, FoodLogActivity::class.java)
            startActivity(intent)
        }

        cardWorkout.setOnClickListener {
            Toast.makeText(this, "Workout - Coming Soon!", Toast.LENGTH_SHORT).show()
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
                        
                        tvNutritionBreakdown.text = """
                            🥩 Protein: ${totalProtein.toInt()}g
                            🍞 Carbs: ${totalCarbs.toInt()}g
                            🥑 Fat: ${totalFat.toInt()}g
                        """.trimIndent()
                    } else {
                        tvNutritionBreakdown.text = "No food logged yet today\nStart tracking your meals! 🍽️"
                    }
                }.onFailure {
                    tvNutritionBreakdown.text = "Track your nutrition here"
                }
            } catch (e: Exception) {
                tvNutritionBreakdown.text = "Track your nutrition here"
            }
        }
    }

    private fun handleLogout() {
        authHelper.signOut()
        
        // Clear SharedPreferences
        val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()

        // Navigate to Login
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}