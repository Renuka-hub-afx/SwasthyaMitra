package com.example.swasthyamitra

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.swasthyamitra.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
//import com.example.swasthyamitra.UserApplication.UserViewModel
import com.example.swasthyamitra.UserViewModel.UserViewModelFactory

class LoginActivity : AppCompatActivity() {

    // Declare views
    private lateinit var emailInput: TextInputEditText
    private lateinit var passwordInput: TextInputEditText
    private lateinit var loginButton: MaterialButton
    private lateinit var signupLink: TextView
    private lateinit var forgotPasswordLink: TextView

    // ViewModel
    private lateinit var userViewModel: UserViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize ViewModel
        val application = application as UserApplication
        val factory = UserViewModelFactory(application.repository)
        userViewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)

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
            userViewModel.getUserByEmail(email).observe(this) { user ->
                if (user != null) {
                    if (user.password == password) {
                        Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
                        saveUserId(user.id)

                        // --- BULLETPROOF LOGIC START ---
                        // 1. Get the values safely. If they are null, treat them as 0f.
                        val currentBmi = user.bmi ?: 0f
                        val currentHeight = user.height ?: 0f

                        // 2. Check if EITHER is 0. If so, profile is incomplete.
                        if (currentBmi == 0f || currentHeight == 0f) {
                            Log.d("FlowCheck", "Incomplete Profile. Going to UserInfo.")
                            navigateToUserInfo(user.id, user.birthDate)
                        } else {
                            Log.d("FlowCheck", "Complete Profile. Going to Homepage.")
                            navigateToHomePage(user.id)
                        }
                        // --- BULLETPROOF LOGIC END ---

                    } else {
                        Toast.makeText(this, "Incorrect password!", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "User not found! Please sign up.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun saveUserId(userId: Long) {
        val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
        sharedPreferences.edit().putLong("USER_ID", userId).apply()
        Log.d("LoginActivity", "USER_ID saved: $userId")
    }

    private fun navigateToHomePage(userId: Long) {
        val intent = Intent(this, homepage::class.java)
        intent.putExtra("USER_ID", userId)
        startActivity(intent)
        finish()
    }

    private fun navigateToUserInfo(userId: Long, dob: String?) {
        val intent = Intent(this, UserInfoActivity::class.java)
        intent.putExtra("USER_ID", userId)
        intent.putExtra("USER_DOB", dob)
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
