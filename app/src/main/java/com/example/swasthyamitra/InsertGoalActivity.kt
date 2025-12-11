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

class InsertGoalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInsertGoalBinding
    private lateinit var authHelper: FirebaseAuthHelper
    private var userId: String = ""
    private var selectedGoalType: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertGoalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val application = application as UserApplication
        authHelper = application.authHelper

        userId = intent.getStringExtra("USER_ID") ?: ""

        binding.cardWeightLoss.setOnClickListener { highlightCard(binding.cardWeightLoss, "Lose Weight") }
        binding.cardMaintain.setOnClickListener { highlightCard(binding.cardMaintain, "Maintain Weight") }
        binding.cardWeightGain.setOnClickListener { highlightCard(binding.cardWeightGain, "Gain Muscle") }
        binding.cardNoGoal.setOnClickListener { highlightCard(binding.cardNoGoal, "General Health") }

        binding.btnNext.setOnClickListener {
            if (selectedGoalType.isEmpty()) Toast.makeText(this, "Select a mission!", Toast.LENGTH_SHORT).show()
            else saveGoalAndFinish()
        }
    }

    private fun highlightCard(selectedCard: MaterialCardView, goalType: String) {
        selectedGoalType = goalType
        val cards = listOf(binding.cardWeightLoss, binding.cardMaintain, binding.cardWeightGain, binding.cardNoGoal)

        for (card in cards) {
            card.setCardBackgroundColor(Color.WHITE)
            card.strokeWidth = 0
            card.elevation = 4f
        }

        selectedCard.setCardBackgroundColor(Color.parseColor("#FCE4EC"))
        selectedCard.strokeColor = Color.parseColor("#E91E63")
        selectedCard.strokeWidth = 6
        selectedCard.elevation = 12f
    }

    private fun saveGoalAndFinish() {
        // Check if user already has a goal
        lifecycleScope.launch {
            val hasGoalResult = authHelper.hasUserGoal(userId)
            hasGoalResult.onSuccess { hasGoal ->
                if (hasGoal) {
                    // User already has a goal, don't create another one
                    Toast.makeText(this@InsertGoalActivity, "Goal already exists!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@InsertGoalActivity, homepage::class.java)
                    intent.putExtra("USER_ID", userId)
                    startActivity(intent)
                    finishAffinity()
                } else {
                    // User has no goal, create one
                    val result = authHelper.insertGoal(userId, selectedGoalType, 2000.0, 0.0)
                    result.onSuccess {
                        Toast.makeText(this@InsertGoalActivity, "Goal Set!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@InsertGoalActivity, homepage::class.java)
                        intent.putExtra("USER_ID", userId)
                        startActivity(intent)
                        finishAffinity()
                    }.onFailure { e ->
                        Toast.makeText(this@InsertGoalActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }.onFailure { e ->
                Toast.makeText(this@InsertGoalActivity, "Error checking goal: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}