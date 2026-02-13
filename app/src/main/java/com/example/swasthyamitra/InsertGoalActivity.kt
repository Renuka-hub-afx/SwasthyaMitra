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

        val application = application as? UserApplication
        val helper = application?.authHelper
        if (helper == null) {
            Toast.makeText(this, "Error: App not initialized properly", Toast.LENGTH_LONG).show()
            finish()
            return
        }
        authHelper = helper

        userId = intent.getStringExtra("USER_ID") ?: authHelper.getCurrentUser()?.uid ?: ""
        if (userId.isEmpty()) {
            Toast.makeText(this, "Error: User ID missing", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        binding.cardWeightLoss.setOnClickListener { highlightCard(binding.cardWeightLoss, "Lose Weight") }
        binding.cardMaintain.setOnClickListener { highlightCard(binding.cardMaintain, "Maintain Weight") }
        binding.cardWeightGain.setOnClickListener { highlightCard(binding.cardWeightGain, "Gain Muscle") }
        binding.cardNoGoal.setOnClickListener { highlightCard(binding.cardNoGoal, "General Health") }

        binding.btnNext.setOnClickListener {
            if (selectedGoalType.isEmpty()) {
                Toast.makeText(this, "Please select a mission!", Toast.LENGTH_SHORT).show()
            } else {
                saveGoalAndFinish()
            }
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
                    // User already has a goal, check if lifestyle data exists
                    checkLifestyleAndNavigate()
                } else {
                    // User has no goal, create one
                    val result = authHelper.insertGoal(userId, selectedGoalType, 2000.0, 0.0)
                    result.onSuccess {
                        Toast.makeText(this@InsertGoalActivity, "Goal Set!", Toast.LENGTH_SHORT).show()
                        // Navigate to LifestyleActivity for additional profile data
                        val intent = Intent(this@InsertGoalActivity, LifestyleActivity::class.java)
                        intent.putExtra("USER_ID", userId)
                        startActivity(intent)
                        finish()
                    }.onFailure { e ->
                        Toast.makeText(this@InsertGoalActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }.onFailure { e ->
                Toast.makeText(this@InsertGoalActivity, "Error checking goal: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkLifestyleAndNavigate() {
        lifecycleScope.launch {
            val hasLifestyleResult = authHelper.hasLifestyleData(userId)
            hasLifestyleResult.onSuccess { hasLifestyle ->
                if (hasLifestyle) {
                    // User has both goal and lifestyle data, go to homepage
                    Toast.makeText(this@InsertGoalActivity, "Welcome back!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@InsertGoalActivity, homepage::class.java)
                    intent.putExtra("USER_ID", userId)
                    startActivity(intent)
                    finishAffinity()
                } else {
                    // User has goal but no lifestyle data, go to LifestyleActivity
                    Toast.makeText(this@InsertGoalActivity, "Complete your profile!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@InsertGoalActivity, LifestyleActivity::class.java)
                    intent.putExtra("USER_ID", userId)
                    startActivity(intent)
                    finish()
                }
            }.onFailure {
                // If check fails, go to LifestyleActivity to be safe
                val intent = Intent(this@InsertGoalActivity, LifestyleActivity::class.java)
                intent.putExtra("USER_ID", userId)
                startActivity(intent)
                finish()
            }
        }
    }
    
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        // Prevent going back to login without completing profile
        android.widget.Toast.makeText(this, "Please complete your goal selection first", android.widget.Toast.LENGTH_SHORT).show()
        // Call super to satisfy lint, but we are effectively blocking it by not allowing finish() if we want.
        // Actually super.onBackPressed() will call finish(). 
        // If we want to block it, we must NOT call super, but lint will complain.
        // Use @SuppressLint("MissingSuperCall") if we really want to block it.
        // super.onBackPressed() 
    }
}
