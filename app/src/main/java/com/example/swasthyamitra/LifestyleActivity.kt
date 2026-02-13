package com.example.swasthyamitra

import android.app.TimePickerDialog
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
import java.util.Calendar
import java.util.Locale

class LifestyleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLifestyleBinding
    private lateinit var authHelper: FirebaseAuthHelper
    private var userId: String = ""
    
    private var selectedActivityLevel: String = ""
    private var selectedDietPreference: String = ""
    private var selectedWorkoutTime: String = "30m" // Default
    private var selectedPreferredTime: String = "Morning" // Default

    // Card lists for easy management
    private lateinit var activityCards: List<MaterialCardView>
    private lateinit var dietCards: List<MaterialCardView>
    private lateinit var workoutTimeCards: List<MaterialCardView>
    private lateinit var preferredTimeCards: List<MaterialCardView>

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

        userId = intent.getStringExtra("USER_ID") ?: authHelper.getCurrentUser()?.uid ?: ""
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

        workoutTimeCards = listOf(
            binding.card15min,
            binding.card30min,
            binding.card45min,
            binding.card60min
        )

        preferredTimeCards = listOf(
            binding.cardMorning,
            binding.cardAfternoon,
            binding.cardEvening,
            binding.cardNight
        )
        
        // Default selection
        selectWorkoutTime(binding.card30min, "30m")
        selectPreferredTime(binding.cardMorning, "Morning")

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

        // Workout Time Card Listeners
        binding.card15min.setOnClickListener { selectWorkoutTime(binding.card15min, "15m") }
        binding.card30min.setOnClickListener { selectWorkoutTime(binding.card30min, "30m") }
        binding.card45min.setOnClickListener { selectWorkoutTime(binding.card45min, "45m") }
        binding.card60min.setOnClickListener { selectWorkoutTime(binding.card60min, "60m+") }

        // Preferred Time Card Listeners
        binding.cardMorning.setOnClickListener { selectPreferredTime(binding.cardMorning, "Morning") }
        binding.cardAfternoon.setOnClickListener { selectPreferredTime(binding.cardAfternoon, "Afternoon") }
        binding.cardEvening.setOnClickListener { selectPreferredTime(binding.cardEvening, "Evening") }
        binding.cardNight.setOnClickListener { selectPreferredTime(binding.cardNight, "Night") }

        // Submit Button Listener
        binding.btnSubmit.setOnClickListener { validateAndSave() }
        
        // Time Pickers
        binding.etWakeTime.setOnClickListener { showTimePicker(true) }
        binding.inputLayoutWakeTime.setEndIconOnClickListener { showTimePicker(true) }
        
        binding.etSleepTime.setOnClickListener { showTimePicker(false) }
        binding.inputLayoutSleepTime.setEndIconOnClickListener { showTimePicker(false) }
    }

    private fun showTimePicker(isWakeTime: Boolean) {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        TimePickerDialog(this, { _, selectedHour, selectedMinute ->
            val timeStr = String.format(Locale.US, "%02d:%02d", selectedHour, selectedMinute)
            if (isWakeTime) {
                binding.etWakeTime.setText(timeStr)
            } else {
                binding.etSleepTime.setText(timeStr)
            }
        }, hour, minute, true).show()
    }

    private fun selectActivityLevel(selectedCard: MaterialCardView, activityLevel: String) {
        selectedActivityLevel = activityLevel
        highlightCard(selectedCard, activityCards)
        
        // Calculate and display BMR/TDEE when activity level is selected
        calculateAndDisplayMetrics()
    }

    private fun selectDietPreference(selectedCard: MaterialCardView, dietPreference: String) {
        selectedDietPreference = dietPreference
        highlightCard(selectedCard, dietCards)
    }

    private fun selectWorkoutTime(selectedCard: MaterialCardView, workoutTime: String) {
        selectedWorkoutTime = workoutTime
        highlightCard(selectedCard, workoutTimeCards)
    }

    private fun selectPreferredTime(selectedCard: MaterialCardView, preferredTime: String) {
        selectedPreferredTime = preferredTime
        highlightCard(selectedCard, preferredTimeCards)
    }
    
    private fun calculateAndDisplayMetrics() {
        // Only calculate if activity level is selected
        if (selectedActivityLevel.isEmpty()) {
            return
        }
        
        lifecycleScope.launch {
            try {
                // Get user physical stats
                val userDataResult = authHelper.getUserData(userId)
                val goalResult = authHelper.getUserGoal(userId)
                
                userDataResult.onSuccess { userData ->
                    goalResult.onSuccess { goal ->
                        val weight = (userData["weight"] as? Number)?.toDouble() ?: 70.0
                        val height = (userData["height"] as? Number)?.toDouble() ?: 170.0
                        val age = (userData["age"] as? Number)?.toInt() ?: 25
                        val gender = userData["gender"] as? String ?: "Male"
                        val goalType = goal["goalType"] as? String ?: "Maintain Weight"
                        
                        // Calculate BMR
                        val bmr = calculateBMR(weight, height, age, gender)
                        
                        // Calculate TDEE
                        val tdee = calculateTDEE(bmr, selectedActivityLevel)
                        
                        // Adjust for goal
                        val dailyTarget = adjustCaloriesForGoal(tdee, goalType)
                        
                        // Update UI
                        binding.cardMetabolicInfo.visibility = android.view.View.VISIBLE
                        binding.tvBmrValue.text = bmr.toInt().toString()
                        binding.tvTdeeValue.text = tdee.toInt().toString()
                        binding.tvDailyTargetValue.text = dailyTarget.toInt().toString()
                        
                        // Update goal description
                        val goalDesc = when (goalType) {
                            "Lose Weight" -> "Weight Loss (-500 kcal)"
                            "Gain Muscle" -> "Muscle Gain (+400 kcal)"
                            else -> "Weight Maintenance"
                        }
                        binding.tvGoalDescription.text = goalDesc
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(this@LifestyleActivity, "Unable to calculate metrics", Toast.LENGTH_SHORT).show()
            }
        }
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

        // Get user data and calculate BMR/TDEE
        lifecycleScope.launch {
            try {
                // Get user physical stats
                val userDataResult = authHelper.getUserData(userId)
                val goalResult = authHelper.getUserGoal(userId)
                
                userDataResult.onSuccess { userData ->
                    goalResult.onSuccess { goal ->
                        val weight = (userData["weight"] as? Number)?.toDouble() ?: 70.0
                        val height = (userData["height"] as? Number)?.toDouble() ?: 170.0
                        val age = (userData["age"] as? Number)?.toInt() ?: 25
                        val gender = userData["gender"] as? String ?: "Male"
                        val goalType = goal["goalType"] as? String ?: "Maintain Weight"
                        
                        // Calculate BMR using Mifflin-St Jeor Equation
                        val bmr = calculateBMR(weight, height, age, gender)
                        
                        // Calculate TDEE (Total Daily Energy Expenditure)
                        val tdee = calculateTDEE(bmr, selectedActivityLevel)
                        
                        // Adjust calories based on goal
                        val dailyCalories = adjustCaloriesForGoal(tdee, goalType)
                        
                        // Save to Firebase with calculated calories
                        val result = authHelper.updateGoalWithCalories(
                            userId = userId,
                            activityLevel = selectedActivityLevel,
                            dietPreference = selectedDietPreference,
                            targetWeight = targetWeight,
                            dailyCalories = dailyCalories,
                            bmr = bmr,
                            tdee = tdee,
                            wakeTime = binding.etWakeTime.text.toString(),
                            sleepTime = binding.etSleepTime.text.toString(),
                            availableExerciseTime = selectedWorkoutTime,
                            preferredExerciseTime = selectedPreferredTime
                        )

                        result.onSuccess {
                            // Save local flag that profile is complete
                            val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
                            sharedPreferences.edit().putBoolean("PROFILE_COMPLETED", true).apply()

                            // Schedule exercise reminder - DISABLED
                            // scheduleExerciseReminder(selectedPreferredTime)

                            Toast.makeText(
                                this@LifestyleActivity, 
                                "Profile Complete! Daily Target: ${dailyCalories.toInt()} kcal 🎉", 
                                Toast.LENGTH_LONG
                            ).show()
                            
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
            } catch (e: Exception) {
                Toast.makeText(this@LifestyleActivity, "Error calculating calories: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    /**
     * Calculate BMR (Basal Metabolic Rate) using Mifflin-St Jeor Equation
     * Men: BMR = (10 × weight_kg) + (6.25 × height_cm) - (5 × age) + 5
     * Women: BMR = (10 × weight_kg) + (6.25 × height_cm) - (5 × age) - 161
     */
    private fun calculateBMR(weight: Double, height: Double, age: Int, gender: String): Double {
        val baseBMR = (10 * weight) + (6.25 * height) - (5 * age)
        return if (gender.equals("Male", ignoreCase = true)) {
            baseBMR + 5
        } else {
            baseBMR - 161
        }
    }
    
    /**
     * Calculate TDEE (Total Daily Energy Expenditure)
     * Multiply BMR by activity factor
     */
    private fun calculateTDEE(bmr: Double, activityLevel: String): Double {
        val activityFactor = when (activityLevel) {
            "Sedentary" -> 1.2
            "Lightly Active" -> 1.375
            "Moderately Active" -> 1.55
            "Very Active" -> 1.725
            else -> 1.2
        }
        return bmr * activityFactor
    }
    
    /**
     * Adjust calories based on weight goal
     * Weight Loss: -500 kcal/day (0.5kg loss per week)
     * Weight Gain: +300-500 kcal/day
     * Maintenance: Keep TDEE as is
     */
    private fun adjustCaloriesForGoal(tdee: Double, goalType: String): Double {
        return when (goalType) {
            "Lose Weight" -> tdee - 500
            "Gain Muscle" -> tdee + 400
            "Maintain Weight" -> tdee
            "General Health" -> tdee
            else -> tdee
        }
    }


}
