package com.example.swasthyamitra

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.swasthyamitra.data.Goal
// IMPORTANT: This import MUST match your XML file name.
// If your XML is activity_insert_goal.xml, this is correct.
import com.example.swasthyamitra.databinding.ActivityInsertGoalBinding
import com.example.swasthyamitra.UserViewModel
import com.example.swasthyamitra.UserViewModel.UserViewModelFactory
import com.google.android.material.card.MaterialCardView
import kotlinx.coroutines.launch
import kotlin.jvm.java
import kotlin.text.isEmpty

class InsertGoalActivity : AppCompatActivity() {

    // Binding variable to access XML views
    private lateinit var binding: ActivityInsertGoalBinding

    // ViewModel to save data
    private lateinit var userViewModel: UserViewModel

    // Data variables
    private var userId: Long = -1L
    private var selectedGoalType: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Inflate Layout
        binding = ActivityInsertGoalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. Setup ViewModel
        val application = application as UserApplication
        val factory = UserViewModelFactory(application.repository)
        userViewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)

        // 3. Get User ID passed from previous screen
        userId = intent.getLongExtra("USER_ID", -1L)

        // 4. Setup Click Listeners for Cards
        binding.cardWeightLoss.setOnClickListener { highlightCard(binding.cardWeightLoss, "Lose Weight") }
        binding.cardMaintain.setOnClickListener { highlightCard(binding.cardMaintain, "Maintain Weight") }
        binding.cardWeightGain.setOnClickListener { highlightCard(binding.cardWeightGain, "Gain Muscle") }
        binding.cardNoGoal.setOnClickListener { highlightCard(binding.cardNoGoal, "General Health") }

        // 5. "Start My Journey" Button Logic
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

        // 1. Reset ALL cards to default look
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

        // 2. Highlight ONLY the selected card
        selectedCard.setCardBackgroundColor(activeColor)
        selectedCard.strokeColor = activeStroke
        selectedCard.strokeWidth = 6 // Thickness of border
        selectedCard.elevation = 12f // Make it pop up
    }

    // Function to save data and go to Home
    private fun saveGoalAndFinish() {
        lifecycleScope.launch {
            // Create the Goal object
            val goal = Goal(
                userId = userId,
                goalType = selectedGoalType,
                dailyCalorieTarget = 2000, // You can refine this logic later based on BMR
                waterGoalMl = 2500
            )

            // Save to Database
            userViewModel.insertGoal(goal)

            Toast.makeText(this@InsertGoalActivity, "Mission Set: $selectedGoalType", Toast.LENGTH_SHORT).show()

            // Navigate to Homepage
            val intent = Intent(this@InsertGoalActivity, homepage::class.java)
            intent.putExtra("USER_ID", userId)
            startActivity(intent)

            // Clear back stack so user cannot go back to setup screens
            finishAffinity()
        }
    }
}