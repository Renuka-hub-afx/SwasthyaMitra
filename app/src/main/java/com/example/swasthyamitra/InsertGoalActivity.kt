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
        // Save goal in background
        lifecycleScope.launch {
            authHelper.insertGoal(userId, selectedGoalType, 2000.0, 0.0)
        }

        // Navigate immediately without waiting
        Toast.makeText(this, "Goal Set!", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, homepage::class.java)
        intent.putExtra("USER_ID", userId)
        startActivity(intent)
        finishAffinity()
    }
}