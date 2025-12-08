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
import kotlin.text.isEmpty
import kotlin.text.toDouble

class UserInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserInfoBinding
    private lateinit var authHelper: FirebaseAuthHelper

    // Variables to store data
    private var selectedGender: String = ""
    private var userId: String = ""
    private var userAge: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth Helper
        val application = application as UserApplication
        authHelper = application.authHelper

        // Get Data passed from Login/Signup Activity
        userId = intent.getStringExtra("USER_ID") ?: ""

        // Get user data to calculate age if available
        lifecycleScope.launch {
            val result = authHelper.getUserData(userId)
            result.onSuccess { userData ->
                userAge = (userData["age"] as? Long)?.toInt() ?: 0
                if (userAge > 0) {
                    binding.tvAgeDisplay.text = "Age: $userAge"
                } else {
                    binding.tvAgeDisplay.text = "Age: --"
                }
            }
        }

        // Handle Gender Card Clicks
        binding.cardMale.setOnClickListener { selectGender("Male") }
        binding.cardFemale.setOnClickListener { selectGender("Female") }

        // "Proceed" Button Click
        binding.btnContinue.setOnClickListener { validateAndGeneratePlan() }
    }

    private fun selectGender(gender: String) {
        selectedGender = gender

        val activeStrokeColor = Color.parseColor("#E91E63")
        val activeBackgroundColor = Color.parseColor("#FCE4EC")
        val inactiveBackgroundColor = Color.WHITE

        if (gender == "Male") {
            binding.cardMale.setCardBackgroundColor(activeBackgroundColor)
            binding.cardMale.strokeColor = activeStrokeColor
            binding.cardMale.strokeWidth = 4
            binding.cardFemale.setCardBackgroundColor(inactiveBackgroundColor)
            binding.cardFemale.strokeColor = Color.TRANSPARENT
            binding.cardFemale.strokeWidth = 0
        } else {
            binding.cardFemale.setCardBackgroundColor(activeBackgroundColor)
            binding.cardFemale.strokeColor = activeStrokeColor
            binding.cardFemale.strokeWidth = 4
            binding.cardMale.setCardBackgroundColor(inactiveBackgroundColor)
            binding.cardMale.strokeColor = Color.TRANSPARENT
            binding.cardMale.strokeWidth = 0
        }
    }

    private fun validateAndGeneratePlan() {
        val heightStr = binding.etHeight.text.toString()
        val weightStr = binding.etWeight.text.toString()

        if (selectedGender.isEmpty()) {
            Toast.makeText(this, "Please select your gender", Toast.LENGTH_SHORT).show()
            return
        }
        if (heightStr.isEmpty() || weightStr.isEmpty()) {
            Toast.makeText(this, "Please enter height and weight", Toast.LENGTH_SHORT).show()
            return
        }

        val height = heightStr.toDouble()
        val weight = weightStr.toDouble()

        lifecycleScope.launch {
            // Update user physical stats in Firestore
            val result = authHelper.updateUserPhysicalStats(userId, height, weight, selectedGender, userAge)
            
            result.onSuccess {
                Toast.makeText(this@UserInfoActivity, "Profile Updated!", Toast.LENGTH_SHORT).show()
                
                // Navigate to InsertGoalActivity
                val intent = Intent(this@UserInfoActivity, InsertGoalActivity::class.java)
                intent.putExtra("USER_ID", userId)
                startActivity(intent)
                finish()
            }.onFailure { e ->
                Toast.makeText(this@UserInfoActivity, "Failed to update profile: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
