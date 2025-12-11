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
        setContentView(R.layout.activity_login)

        val application = application as UserApplication
        authHelper = application.authHelper

        emailInput = findViewById(R.id.email_input)
        passwordInput = findViewById(R.id.password_input)
        loginButton = findViewById(R.id.login_button)
        signupLink = findViewById(R.id.signup_link)
        forgotPasswordLink = findViewById(R.id.forgot_password_link)

        loginButton.setOnClickListener { handleLogin() }

        // Go to Signup
        signupLink.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

        forgotPasswordLink.setOnClickListener {
            Toast.makeText(this, "Coming soon!", Toast.LENGTH_SHORT).show()
        }
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
                    Toast.makeText(this@LoginActivity, "Login failed: ${e.message}", Toast.LENGTH_SHORT).show()
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
                        when {
                            // User has completed everything - go to homepage
                            height > 0 && weight > 0 && gender.isNotEmpty() && hasGoal -> {
                                navigateToHomePage(userId)
                            }
                            // User has profile but no goal - go to InsertGoal
                            height > 0 && weight > 0 && gender.isNotEmpty() && !hasGoal -> {
                                navigateToInsertGoal(userId)
                            }
                            // User has no profile - go to UserInfo
                            else -> {
                                navigateToUserInfo(userId)
                            }
                        }
                    }.onFailure {
                        // If goal check fails, assume no goal and redirect to UserInfo
                        navigateToUserInfo(userId)
                    }
                }.onFailure {
                    // If user data fetch fails, start from beginning
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

    private fun validateInputs(email: String, password: String): Boolean {
        if (email.isEmpty()) { emailInput.error = "Required"; return false }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) { emailInput.error = "Invalid Email"; return false }
        if (password.isEmpty()) { passwordInput.error = "Required"; return false }
        if (password.length < 8) { passwordInput.error = "Min 8 chars"; return false }
        return true
    }
}