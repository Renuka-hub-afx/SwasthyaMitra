package com.example.swasthyamitra

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import kotlinx.coroutines.launch

class homepage : AppCompatActivity() {

    private lateinit var authHelper: FirebaseAuthHelper
    private var userId: String = ""

    // UI Elements
    private lateinit var tvUserName: TextView
    private lateinit var tvGoalType: TextView
    private lateinit var tvCalories: TextView
    private lateinit var tvSteps: TextView
    private lateinit var tvWorkouts: TextView
    private lateinit var cardDashboard: MaterialCardView
    private lateinit var cardFood: MaterialCardView
    private lateinit var cardWorkout: MaterialCardView
    private lateinit var cardProfile: MaterialCardView
    private lateinit var btnLogout: MaterialButton

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
        cardDashboard = findViewById(R.id.card_dashboard)
        cardFood = findViewById(R.id.card_food)
        cardWorkout = findViewById(R.id.card_workout)
        cardProfile = findViewById(R.id.card_profile)
        btnLogout = findViewById(R.id.btn_logout)

        // Load User Data
        loadUserData()

        // Set Click Listeners
        cardDashboard.setOnClickListener {
            Toast.makeText(this, "Dashboard - Coming Soon!", Toast.LENGTH_SHORT).show()
        }

        cardFood.setOnClickListener {
            Toast.makeText(this, "Food Log - Coming Soon!", Toast.LENGTH_SHORT).show()
        }

        cardWorkout.setOnClickListener {
            Toast.makeText(this, "Workout - Coming Soon!", Toast.LENGTH_SHORT).show()
        }

        cardProfile.setOnClickListener {
            Toast.makeText(this, "Profile - Coming Soon!", Toast.LENGTH_SHORT).show()
        }

        btnLogout.setOnClickListener {
            handleLogout()
        }
    }

    private fun loadUserData() {
        lifecycleScope.launch {
            try {
                // Fetch user data
                val userDataResult = authHelper.getUserData(userId)
                userDataResult.onSuccess { userData ->
                    val name = userData["name"] as? String ?: "User"
                    tvUserName.text = name
                }

                // Fetch user goal
                val goalsResult = authHelper.getUserGoal(userId)
                goalsResult.onSuccess { goal ->
                    val goalType = goal["goalType"] as? String ?: "No Goal Set"
                    tvGoalType.text = "Your Goal: $goalType"
                }.onFailure {
                    tvGoalType.text = "Your Goal: --"
                }

                // TODO: Load today's stats from Firestore
                // For now, showing placeholders
                tvCalories.text = "0"
                tvSteps.text = "0"
                tvWorkouts.text = "0"

            } catch (e: Exception) {
                Toast.makeText(this@homepage, "Error loading data: ${e.message}", Toast.LENGTH_SHORT).show()
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