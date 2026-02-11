package com.example.swasthyamitra

import android.content.Intent
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var authHelper: FirebaseAuthHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            val application = application as? UserApplication
            if (application != null) {
                authHelper = application.authHelper
                // PERSISTENT LOGIN: Check if user is already logged in
                // If logged in, skip welcome screen and go directly to homepage
                // This ensures users don't need to login repeatedly
                checkAutoLogin()
            } else {
                // UserApplication not initialized, show welcome screen
                showWelcomeScreen()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            // If Firebase fails, still show welcome screen
            showWelcomeScreen()
        }
    }

    private fun checkAutoLogin() {
        try {
            if (::authHelper.isInitialized && authHelper.isUserLoggedIn()) {
                // User is logged in, check profile completion status
                val userId = authHelper.getCurrentUser()?.uid
                if (userId != null) {
                    Log.d("MainActivity", "User already logged in with ID: $userId, checking profile completion")
                    // Use the existing validation logic to determine navigation
                    checkProfileAndNavigate(userId)
                } else {
                    Log.d("MainActivity", "User logged in but no UID found")
                    showWelcomeScreen()
                }
            } else {
                Log.d("MainActivity", "No user logged in, showing welcome screen")
                showWelcomeScreen()
            }
        } catch (e: Exception) {
            Log.e("MainActivity", "Error in checkAutoLogin: ${e.message}", e)
            showWelcomeScreen()
        }
    }

    private fun checkProfileAndNavigate(userId: String) {
        lifecycleScope.launch {
            try {
                // Check if user has completed profile (height, weight, gender)
                val userDataResult = authHelper.getUserData(userId)
                userDataResult.onSuccess { userData ->
                    val height = (userData["height"] as? Number)?.toDouble() ?: 0.0
                    val weight = (userData["weight"] as? Number)?.toDouble() ?: 0.0
                    val gender = userData["gender"] as? String ?: ""

                    // Check if user has set a goal
                    val hasGoalResult = authHelper.hasUserGoal(userId)
                    hasGoalResult.onSuccess { hasGoal ->
                        // Check if user has lifestyle data
                        val hasLifestyleResult = authHelper.hasLifestyleData(userId)
                        hasLifestyleResult.onSuccess { hasLifestyle ->
                            when {
                                // Profile complete: has height, weight, gender, goal, and lifestyle
                                height > 0 && weight > 0 && gender.isNotEmpty() && hasGoal && hasLifestyle -> {
                                    Log.d("MainActivity", "Profile complete, going to homepage")
                                    navigateToHomePage(userId)
                                }
                                // Has profile and goal but no lifestyle
                                height > 0 && weight > 0 && gender.isNotEmpty() && hasGoal && !hasLifestyle -> {
                                    Log.d("MainActivity", "Missing lifestyle, going to LifestyleActivity")
                                    navigateToLifestyle(userId)
                                }
                                // Has profile but no goal
                                height > 0 && weight > 0 && gender.isNotEmpty() && !hasGoal -> {
                                    Log.d("MainActivity", "Missing goal, going to InsertGoalActivity")
                                    navigateToInsertGoal(userId)
                                }
                                // No profile data - start from beginning
                                else -> {
                                    Log.d("MainActivity", "No profile, going to UserInfoActivity")
                                    navigateToUserInfo(userId)
                                }
                            }
                        }.onFailure {
                            // If lifestyle check fails, assume no lifestyle and go to UserInfo
                            navigateToUserInfo(userId)
                        }
                    }.onFailure {
                        // If goal check fails, assume no goal and go to UserInfo
                        navigateToUserInfo(userId)
                    }
                }.onFailure {
                    // If user data fetch fails, start from beginning
                    navigateToUserInfo(userId)
                }
            } catch (e: Exception) {
                Log.e("MainActivity", "Error checking profile: ${e.message}")
                navigateToUserInfo(userId)
            }
        }
    }

    private fun showWelcomeScreen() {
        applyGradientToText()

        val startButton = findViewById<Button>(R.id.button_start)

        // NAVIGATE TO LOGIN
        startButton.setOnClickListener {
            try {
                Log.d("MainActivity", "Start button clicked, navigating to LoginActivity")
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                Log.d("MainActivity", "LoginActivity started successfully")
            } catch (e: Exception) {
                Log.e("MainActivity", "Error starting LoginActivity: ${e.message}", e)
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun navigateToHomePage(userId: String) {
        val intent = Intent(this, homepage::class.java)
        intent.putExtra("USER_ID", userId)
        startActivity(intent)
        finish()
    }

    private fun navigateToUserInfo(userId: String) {
        val intent = Intent(this, UserInfoActivity::class.java)
        intent.putExtra("USER_ID", userId)
        startActivity(intent)
        finish()
    }

    private fun navigateToInsertGoal(userId: String) {
        val intent = Intent(this, InsertGoalActivity::class.java)
        intent.putExtra("USER_ID", userId)
        startActivity(intent)
        finish()
    }

    private fun navigateToLifestyle(userId: String) {
        val intent = Intent(this, LifestyleActivity::class.java)
        intent.putExtra("USER_ID", userId)
        startActivity(intent)
        finish()
    }

    private fun applyGradientToText() {
        try {
            val appNameTextView = findViewById<TextView>(R.id.text_app_name)
            if (appNameTextView != null) {
                val gradientColors = intArrayOf(
                    ContextCompat.getColor(this, R.color.text_gradient_start),
                    ContextCompat.getColor(this, R.color.text_gradient_middle),
                    ContextCompat.getColor(this, R.color.text_gradient_end)
                )

                appNameTextView.post {
                    val textWidth = appNameTextView.measuredWidth.toFloat()
                    val textHeight = appNameTextView.textSize
                    val shader = LinearGradient(0f, 0f, textWidth, textHeight, gradientColors, null, Shader.TileMode.CLAMP)
                    appNameTextView.paint.shader = shader
                    appNameTextView.invalidate()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}