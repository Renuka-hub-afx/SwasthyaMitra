package com.example.swasthyamitra

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.swasthyamitra.databinding.ActivityUserInfoBinding
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import kotlinx.coroutines.launch
import kotlin.math.pow
import kotlin.math.round

class UserInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserInfoBinding
    private lateinit var authHelper: FirebaseAuthHelper
    private var selectedGender: String = ""
    private var userId: String = ""
    private var userAge: Int = 0
    private var calculatedBMI: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val application = application as? UserApplication
        if (application == null) {
            Toast.makeText(this, "App initialization error. Restarting...", Toast.LENGTH_LONG).show()
            finish()
            return
        }
        authHelper = application.authHelper

        // Retrieve User ID passed from LoginActivity or from current session
        userId = intent.getStringExtra("USER_ID") ?: authHelper.getCurrentUser()?.uid ?: ""
        if (userId.isEmpty()) {
            Toast.makeText(this, "User ID missing. Please login again.", Toast.LENGTH_LONG).show()
            finish()
            return
        }

        // Fetch existing age if available
        lifecycleScope.launch {
            val result = authHelper.getUserData(userId)
            result.onSuccess { userData ->
                userAge = (userData["age"] as? Number)?.toInt() ?: 0
                binding.tvAgeDisplay.text = if (userAge > 0) "Age: $userAge" else "Age: --"
            }
        }

        // Gender Selection Listeners
        binding.cardMale.setOnClickListener { selectGender("Male") }
        binding.cardFemale.setOnClickListener { selectGender("Female") }

        // BMI Calculate Button Listener
        binding.btnCalculateBMI.setOnClickListener { calculateAndDisplayBMI() }

        // "Proceed" / "Continue" Button Listener
        binding.btnContinue.setOnClickListener { validateAndGeneratePlan() }
    }

    /**
     * Calculate BMI using the formula: BMI = weight(kg) / height(m)²
     */
    private fun calculateAndDisplayBMI() {
        val heightStr = binding.etHeight.text.toString()
        val weightStr = binding.etWeight.text.toString()

        if (heightStr.isEmpty() || weightStr.isEmpty()) {
            Toast.makeText(this, "Please enter height and weight", Toast.LENGTH_SHORT).show()
            return
        }

        val heightCm = heightStr.toDoubleOrNull()
        val weightKg = weightStr.toDoubleOrNull()

        if (heightCm == null || weightKg == null || heightCm <= 0 || weightKg <= 0) {
            Toast.makeText(this, "Please enter valid height and weight", Toast.LENGTH_SHORT).show()
            return
        }

        // Convert height from cm to meters
        val heightM = heightCm / 100.0

        // BMI Formula: weight(kg) / height(m)²
        calculatedBMI = weightKg / heightM.pow(2)

        // Round to 1 decimal place
        val bmiRounded = round(calculatedBMI * 10) / 10

        // Get BMI category and color
        val (category, color) = getBMICategory(calculatedBMI)

        // Display BMI
        binding.cardBMI.visibility = View.VISIBLE
        binding.tvBMIValue.text = bmiRounded.toString()
        binding.tvBMICategory.text = category
        binding.tvBMICategory.setTextColor(color)
        
        // Update card border color based on category
        binding.cardBMI.strokeColor = color
    }

    /**
     * Get BMI category and corresponding color
     * Underweight: < 18.5
     * Normal: 18.5 - 24.9
     * Overweight: 25 - 29.9
     * Obese: >= 30
     */
    private fun getBMICategory(bmi: Double): Pair<String, Int> {
        return when {
            bmi < 18.5 -> Pair("Underweight", Color.parseColor("#2196F3"))  // Blue
            bmi < 25.0 -> Pair("Normal ✓", Color.parseColor("#4CAF50"))     // Green
            bmi < 30.0 -> Pair("Overweight", Color.parseColor("#FF9800"))   // Orange
            else -> Pair("Obese", Color.parseColor("#F44336"))              // Red
        }
    }

    private fun selectGender(gender: String) {
        selectedGender = gender
        val activeColor = Color.parseColor("#FCE4EC")
        val activeStroke = Color.parseColor("#E91E63")

        binding.cardMale.setCardBackgroundColor(if (gender == "Male") activeColor else Color.WHITE)
        binding.cardMale.strokeColor = if (gender == "Male") activeStroke else Color.TRANSPARENT
        binding.cardMale.strokeWidth = if (gender == "Male") 4 else 0

        binding.cardFemale.setCardBackgroundColor(if (gender == "Female") activeColor else Color.WHITE)
        binding.cardFemale.strokeColor = if (gender == "Female") activeStroke else Color.TRANSPARENT
        binding.cardFemale.strokeWidth = if (gender == "Female") 4 else 0
    }

    private fun validateAndGeneratePlan() {
        val heightStr = binding.etHeight.text.toString()
        val weightStr = binding.etWeight.text.toString()

        // Validation
        if (selectedGender.isEmpty() || heightStr.isEmpty() || weightStr.isEmpty()) {
            Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show()
            return
        }

        val height = heightStr.toDouble()
        val weight = weightStr.toDouble()

        // Save data and wait for completion before navigating
        lifecycleScope.launch {
            val result = authHelper.updateUserPhysicalStats(userId, height, weight, selectedGender, userAge)
            result.onSuccess {
                // Check if user already has a goal before navigating
                val hasGoalResult = authHelper.hasUserGoal(userId)
                hasGoalResult.onSuccess { hasGoal ->
                    if (hasGoal) {
                        // User already has a goal, go directly to homepage
                        Toast.makeText(this@UserInfoActivity, "Profile Updated!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@UserInfoActivity, homepage::class.java)
                        intent.putExtra("USER_ID", userId)
                        startActivity(intent)
                        finish()
                    } else {
                        // User has no goal, go to InsertGoalActivity
                        Toast.makeText(this@UserInfoActivity, "Profile Updated!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@UserInfoActivity, InsertGoalActivity::class.java)
                        intent.putExtra("USER_ID", userId)
                        startActivity(intent)
                        finish()
                    }
                }.onFailure {
                    // If check fails, assume no goal and go to InsertGoalActivity
                    Toast.makeText(this@UserInfoActivity, "Profile Updated!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@UserInfoActivity, InsertGoalActivity::class.java)
                    intent.putExtra("USER_ID", userId)
                    startActivity(intent)
                    finish()
                }
            }.onFailure { e ->
                Toast.makeText(this@UserInfoActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
