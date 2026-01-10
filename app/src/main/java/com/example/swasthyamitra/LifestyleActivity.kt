package com.example.swasthyamitra

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.swasthyamitra.databinding.ActivityLifestyleBinding
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.google.android.material.card.MaterialCardView
import kotlinx.coroutines.launch

class LifestyleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLifestyleBinding
    private lateinit var authHelper: FirebaseAuthHelper
    private var userId: String = ""
    
    private var selectedActivityLevel: String = ""
    private var selectedDietPreference: String = ""

    // Card lists for easy management
    private lateinit var activityCards: List<MaterialCardView>
    private lateinit var dietCards: List<MaterialCardView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLifestyleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val application = application as? UserApplication
        if (application == null) {
            Toast.makeText(this, "App initialization error. Restarting...", Toast.LENGTH_LONG).show()
            finish()
            return
        }
        authHelper = application.authHelper

        userId = intent.getStringExtra("USER_ID") ?: ""
        if (userId.isEmpty()) {
            Toast.makeText(this, "User ID missing. Please login again.", Toast.LENGTH_LONG).show()
            finish()
            return
        }

        // Initialize card lists
        activityCards = listOf(
            binding.cardSedentary,
            binding.cardLightlyActive,
            binding.cardModeratelyActive,
            binding.cardVeryActive
        )

        dietCards = listOf(
            binding.cardVegetarian,
            binding.cardNonVegetarian,
            binding.cardVegan,
            binding.cardEggetarian
        )

        // Activity Level Card Listeners
        binding.cardSedentary.setOnClickListener { 
            selectActivityLevel(binding.cardSedentary, "Sedentary") 
        }
        binding.cardLightlyActive.setOnClickListener { 
            selectActivityLevel(binding.cardLightlyActive, "Lightly Active") 
        }
        binding.cardModeratelyActive.setOnClickListener { 
            selectActivityLevel(binding.cardModeratelyActive, "Moderately Active") 
        }
        binding.cardVeryActive.setOnClickListener { 
            selectActivityLevel(binding.cardVeryActive, "Very Active") 
        }

        // Diet Preference Card Listeners
        binding.cardVegetarian.setOnClickListener { 
            selectDietPreference(binding.cardVegetarian, "Vegetarian") 
        }
        binding.cardNonVegetarian.setOnClickListener { 
            selectDietPreference(binding.cardNonVegetarian, "Non-Vegetarian") 
        }
        binding.cardVegan.setOnClickListener { 
            selectDietPreference(binding.cardVegan, "Vegan") 
        }
        binding.cardEggetarian.setOnClickListener { 
            selectDietPreference(binding.cardEggetarian, "Eggetarian") 
        }

        // Submit Button Listener
        binding.btnSubmit.setOnClickListener { validateAndSave() }
    }

    private fun selectActivityLevel(selectedCard: MaterialCardView, activityLevel: String) {
        selectedActivityLevel = activityLevel
        highlightCard(selectedCard, activityCards)
    }

    private fun selectDietPreference(selectedCard: MaterialCardView, dietPreference: String) {
        selectedDietPreference = dietPreference
        highlightCard(selectedCard, dietCards)
    }

    private fun highlightCard(selectedCard: MaterialCardView, cardList: List<MaterialCardView>) {
        val activeColor = Color.parseColor("#FCE4EC")
        val activeStroke = Color.parseColor("#E91E63")

        // Reset all cards in the list
        for (card in cardList) {
            card.setCardBackgroundColor(Color.WHITE)
            card.strokeColor = Color.TRANSPARENT
            card.strokeWidth = 0
            card.elevation = 4f
        }

        // Highlight selected card
        selectedCard.setCardBackgroundColor(activeColor)
        selectedCard.strokeColor = activeStroke
        selectedCard.strokeWidth = 6
        selectedCard.elevation = 12f
    }

    private fun validateAndSave() {
        val targetWeightStr = binding.etTargetWeight.text.toString()

        // Validation
        if (selectedActivityLevel.isEmpty()) {
            Toast.makeText(this, "Please select your activity level", Toast.LENGTH_SHORT).show()
            return
        }

        if (selectedDietPreference.isEmpty()) {
            Toast.makeText(this, "Please select your dietary preference", Toast.LENGTH_SHORT).show()
            return
        }

        if (targetWeightStr.isEmpty()) {
            Toast.makeText(this, "Please enter your target weight", Toast.LENGTH_SHORT).show()
            return
        }

        val targetWeight = targetWeightStr.toDoubleOrNull()
        if (targetWeight == null || targetWeight <= 0) {
            Toast.makeText(this, "Please enter a valid target weight", Toast.LENGTH_SHORT).show()
            return
        }

        // Save to Firebase Goal document
        lifecycleScope.launch {
            val result = authHelper.updateGoalLifestyleData(
                userId = userId,
                activityLevel = selectedActivityLevel,
                dietPreference = selectedDietPreference,
                targetWeight = targetWeight
            )

            result.onSuccess {
                Toast.makeText(this@LifestyleActivity, "Profile Complete! 🎉", Toast.LENGTH_SHORT).show()
                
                // Navigate to Homepage
                val intent = Intent(this@LifestyleActivity, homepage::class.java)
                intent.putExtra("USER_ID", userId)
                startActivity(intent)
                finishAffinity()
            }.onFailure { e ->
                Toast.makeText(this@LifestyleActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
