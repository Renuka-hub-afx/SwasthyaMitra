package com.example.swasthyamitra

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.swasthyamitra.databinding.ActivityUserInfoBinding
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import kotlinx.coroutines.launch

class UserInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserInfoBinding
    private lateinit var authHelper: FirebaseAuthHelper
    private var selectedGender: String = ""
    private var userId: String = ""
    private var userAge: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val application = application as UserApplication
        authHelper = application.authHelper

        // Retrieve User ID passed from LoginActivity
        userId = intent.getStringExtra("USER_ID") ?: ""

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

        // "Proceed" / "Continue" Button Listener
        binding.btnContinue.setOnClickListener { validateAndGeneratePlan() }
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