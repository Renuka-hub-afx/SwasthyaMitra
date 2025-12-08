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

    // Declare views
    private lateinit var emailInput: TextInputEditText
    private lateinit var passwordInput: TextInputEditText
    private lateinit var loginButton: MaterialButton
    private lateinit var signupLink: TextView
    private lateinit var forgotPasswordLink: TextView

    // Firebase Auth Helper
    private lateinit var authHelper: FirebaseAuthHelper

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize Firebase Auth Helper
        val application = application as UserApplication
        authHelper = application.authHelper

        // Initialize Views
        emailInput = findViewById(R.id.email_input)
        passwordInput = findViewById(R.id.password_input)
        loginButton = findViewById(R.id.login_button)
        signupLink = findViewById(R.id.signup_link)
        forgotPasswordLink = findViewById(R.id.forgot_password_link)

        // Login button
        loginButton.setOnClickListener {
            handleLogin()
        }

        // Signup link
        signupLink.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

        // Forgot password link
        forgotPasswordLink.setOnClickListener {
            Toast.makeText(this, "Forgot Password feature coming soon!", Toast.LENGTH_SHORT).show()
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
                    checkUserProfileAndNavigate(user.uid)
                }.onFailure { e ->
                    Toast.makeText(this@LoginActivity, "Login failed: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun checkUserProfileAndNavigate(userId: String) {
        lifecycleScope.launch {
            val result = authHelper.getUserData(userId)
            result.onSuccess { userData ->
                val height = userData["height"] as? Double ?: 0.0
                val weight = userData["weight"] as? Double ?: 0.0
                
                if (height == 0.0 || weight == 0.0) {
                    Log.d("FlowCheck", "Incomplete Profile. Going to UserInfo.")
                    navigateToUserInfo(userId)
                } else {
                    Log.d("FlowCheck", "Complete Profile. Going to Homepage.")
                    navigateToHomePage(userId)
                }
            }.onFailure {
                navigateToUserInfo(userId)
            }
        }
    }

    private fun saveUserId(userId: String) {
        val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
        sharedPreferences.edit().putString("USER_ID", userId).apply()
        Log.d("LoginActivity", "USER_ID saved: $userId")
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

    private fun validateInputs(email: String, password: String): Boolean {
        var valid = true

        if (email.isEmpty()) {
            emailInput.error = "Email cannot be empty"
            valid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInput.error = "Enter valid email"
            valid = false
        }

        if (password.isEmpty()) {
            passwordInput.error = "Password cannot be empty"
            valid = false
        } else if (password.length < 8) {
            passwordInput.error = "Password must be 8+ characters"
            valid = false
        }

        return valid
    }
}
