package com.example.swasthyamitra

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.swasthyamitra.databinding.ActivityInsertGoalBinding
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.google.android.material.card.MaterialCardView
import kotlinx.coroutines.launch
import kotlin.text.isEmpty

class InsertGoalActivity : AppCompatActivity() {

    // Binding variable to access XML views
    private lateinit var binding: ActivityInsertGoalBinding

    // Firebase Auth Helper
    private lateinit var authHelper: FirebaseAuthHelper

    // Data variables
    private var userId: String = ""
    private var selectedGoalType: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate Layout
        binding = ActivityInsertGoalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth Helper
        val application = application as UserApplication
        authHelper = application.authHelper

        // Get User ID passed from previous screen
        userId = intent.getStringExtra("USER_ID") ?: ""

        // Setup Click Listeners for Cards
        binding.cardWeightLoss.setOnClickListener { highlightCard(binding.cardWeightLoss, "Lose Weight") }
        binding.cardMaintain.setOnClickListener { highlightCard(binding.cardMaintain, "Maintain Weight") }
        binding.cardWeightGain.setOnClickListener { highlightCard(binding.cardWeightGain, "Gain Muscle") }
        binding.cardNoGoal.setOnClickListener { highlightCard(binding.cardNoGoal, "General Health") }

        // "Start My Journey" Button Logic
        binding.btnNext.setOnClickListener {
            if (selectedGoalType.isEmpty()) {
                Toast.makeText(this, "Please select a mission!", Toast.LENGTH_SHORT).show()
            } else {
                saveGoalAndFinish()
            }
        }
    }

    // Function to handle the visual highlighting of cards
    private fun highlightCard(selectedCard: MaterialCardView, goalType: String) {
        selectedGoalType = goalType

        // Define Colors
        val activeColor = Color.parseColor("#FCE4EC") // Light Pink Background
        val activeStroke = Color.parseColor("#E91E63") // Dark Pink Border
        val inactiveColor = Color.WHITE

        // Reset ALL cards to default look
        val cards = listOf(
            binding.cardWeightLoss,
            binding.cardMaintain,
            binding.cardWeightGain,
            binding.cardNoGoal
        )

        for (card in cards) {
            card.setCardBackgroundColor(inactiveColor)
            card.strokeWidth = 0
            card.elevation = 4f
        }

        // Highlight ONLY the selected card
        selectedCard.setCardBackgroundColor(activeColor)
        selectedCard.strokeColor = activeStroke
        selectedCard.strokeWidth = 6 // Thickness of border
        selectedCard.elevation = 12f // Make it pop up
    }

    // Function to save data and go to Home
    private fun saveGoalAndFinish() {
        lifecycleScope.launch {
            // Save goal to Firestore
            val result = authHelper.insertGoal(
                userId = userId,
                goalType = selectedGoalType,
                targetValue = 2000.0, // Can be refined based on user data
                currentValue = 0.0
            )

            result.onSuccess {
                Toast.makeText(this@InsertGoalActivity, "Mission Set: $selectedGoalType", Toast.LENGTH_SHORT).show()

                // Navigate to Homepage
                val intent = Intent(this@InsertGoalActivity, homepage::class.java)
                intent.putExtra("USER_ID", userId)
                startActivity(intent)

                // Clear back stack so user cannot go back to setup screens
                finishAffinity()
            }.onFailure { e ->
                Toast.makeText(this@InsertGoalActivity, "Failed to set goal: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}