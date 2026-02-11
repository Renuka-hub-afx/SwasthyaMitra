package com.example.swasthyamitra

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var emailInput: TextInputEditText
    private lateinit var passwordInput: TextInputEditText
    private lateinit var loginButton: MaterialButton
    private lateinit var signupLink: TextView
    private lateinit var forgotPasswordLink: TextView
    private lateinit var authHelper: FirebaseAuthHelper

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        Log.d("LoginActivity", "onCreate started")
        
        try {
            setContentView(R.layout.activity_login)
            Log.d("LoginActivity", "Layout inflated successfully")
        } catch (e: Exception) {
            Log.e("LoginActivity", "Error inflating layout: ${e.message}", e)
            Toast.makeText(this, "Error loading login screen: ${e.message}", Toast.LENGTH_LONG).show()
            finish()
            return
        }

        try {
            val application = application as? UserApplication
            if (application == null) {
                Log.e("LoginActivity", "UserApplication is null")
                Toast.makeText(this, "App initialization error. Please restart app.", Toast.LENGTH_LONG).show()
                finish()
                return
            }
            Log.d("LoginActivity", "UserApplication retrieved successfully")
            
            authHelper = application.authHelper
            Log.d("LoginActivity", "AuthHelper retrieved successfully")
        } catch (e: Exception) {
            Log.e("LoginActivity", "Error getting authHelper: ${e.message}", e)
            Toast.makeText(this, "Authentication error: ${e.message}", Toast.LENGTH_LONG).show()
            finish()
            return
        }

        try {
            emailInput = findViewById(R.id.email_input)
            passwordInput = findViewById(R.id.password_input)
            loginButton = findViewById(R.id.login_button)
            signupLink = findViewById(R.id.signup_link)
            forgotPasswordLink = findViewById(R.id.forgot_password_link)
            Log.d("LoginActivity", "All views found successfully")
        } catch (e: Exception) {
            Log.e("LoginActivity", "Error finding views: ${e.message}", e)
            Toast.makeText(this, "UI error: ${e.message}", Toast.LENGTH_LONG).show()
            finish()
            return
        }

        loginButton.setOnClickListener { handleLogin() }

        // Go to Signup
        signupLink.setOnClickListener {
            try {
                startActivity(Intent(this, SignupActivity::class.java))
            } catch (e: Exception) {
                Log.e("LoginActivity", "Error opening signup: ${e.message}", e)
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }

        forgotPasswordLink.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
        
        Log.d("LoginActivity", "onCreate completed successfully")
    }
    
    private fun handleLogin() {
        val email = emailInput.text.toString().trim()
        val password = passwordInput.text.toString()

        if (validateInputs(email, password)) {
            lifecycleScope.launch {
                val result = authHelper.signInWithEmail(email, password)
                result.onSuccess { user ->
                    Toast.makeText(this@LoginActivity, "Login Successful!", Toast.LENGTH_SHORT).show()
                    saveUserId(user.uid)
                    
                    // Check user onboarding status before navigation
                    checkUserProfileAndNavigate(user.uid)
                }.onFailure { e ->
                    Log.e("LoginActivity", "Login failed: ${e.message}", e)
                    val errorMessage = when (e) {
                        is com.google.firebase.FirebaseNetworkException -> 
                            "Network Error: Please check your internet connection or try a different network (hotspot)."
                        is com.google.firebase.auth.FirebaseAuthInvalidUserException ->
                            "Account not found. Please sign up or check the email."
                        is com.google.firebase.auth.FirebaseAuthInvalidCredentialsException ->
                            "Invalid password or email format."
                        else -> "Login failed: ${e.localizedMessage ?: "Unknown error"}"
                    }
                    Toast.makeText(this@LoginActivity, errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun checkUserProfileAndNavigate(userId: String) {
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
                                    Log.d("LoginActivity", "Profile complete, going to homepage")
                                    navigateToHomePage(userId)
                                }
                                // Has profile and goal but no lifestyle
                                height > 0 && weight > 0 && gender.isNotEmpty() && hasGoal && !hasLifestyle -> {
                                    Log.d("LoginActivity", "Missing lifestyle, going to LifestyleActivity")
                                    navigateToLifestyle(userId)
                                }
                                // Has profile but no goal
                                height > 0 && weight > 0 && gender.isNotEmpty() && !hasGoal -> {
                                    Log.d("LoginActivity", "Missing goal, going to InsertGoalActivity")
                                    navigateToInsertGoal(userId)
                                }
                                // No profile data - start from beginning
                                else -> {
                                    Log.d("LoginActivity", "No profile, going to UserInfoActivity")
                                    navigateToUserInfo(userId)
                                }
                            }
                        }.onFailure {
                            Log.e("LoginActivity", "Lifestyle check failed, going to UserInfoActivity")
                            navigateToUserInfo(userId)
                        }
                    }.onFailure {
                        Log.e("LoginActivity", "Goal check failed, going to UserInfoActivity")
                        navigateToUserInfo(userId)
                    }
                }.onFailure {
                    Log.e("LoginActivity", "User data fetch failed, going to UserInfoActivity")
                    navigateToUserInfo(userId)
                }
            } catch (e: Exception) {
                Log.e("LoginActivity", "Error checking profile: ${e.message}")
                navigateToUserInfo(userId)
            }
        }
    }

    private fun saveUserId(userId: String) {
        val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
        sharedPreferences.edit().putString("USER_ID", userId).apply()
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

    private fun validateInputs(email: String, password: String): Boolean {
        if (email.isEmpty()) { emailInput.error = "Required"; return false }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) { emailInput.error = "Invalid Email"; return false }
        if (password.isEmpty()) { passwordInput.error = "Required"; return false }
        if (password.length < 8) { passwordInput.error = "Min 8 chars"; return false }
        return true
    }
}