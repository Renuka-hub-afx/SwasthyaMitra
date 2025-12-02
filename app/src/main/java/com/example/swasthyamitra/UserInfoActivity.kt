package com.example.swasthyamitra

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.swasthyamitra.data.Goal
import com.example.swasthyamitra.databinding.ActivityUserInfoBinding
import com.example.swasthyamitra.UserViewModel
import com.example.swasthyamitra.UserViewModel.UserViewModelFactory
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.apply
import kotlin.jvm.java
import kotlin.text.isEmpty
import kotlin.text.isNotEmpty
import kotlin.text.toFloat
import kotlin.toString

class UserInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserInfoBinding
    private lateinit var userViewModel: UserViewModel

    // Variables to store data
    private var selectedGender: String = ""
    private var userId: Long = -1L
    private var userAge: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Setup ViewModel
        val application = application as UserApplication
        val factory = UserViewModelFactory(application.repository)
        userViewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)

        // 2. Get Data passed from Login/Signup Activity
        userId = intent.getLongExtra("USER_ID", -1L)
        val dobString = intent.getStringExtra("USER_DOB") ?: ""

        // 3. Calculate Age automatically and display it
        if (dobString.isNotEmpty()) {
            userAge = calculateAge(dobString)
            binding.tvAgeDisplay.text = "Age: $userAge"
        } else {
            binding.tvAgeDisplay.text = "Age: --"
        }

        // 4. Handle Gender Card Clicks (Modern Selection)
        binding.cardMale.setOnClickListener { selectGender("Male") }
        binding.cardFemale.setOnClickListener { selectGender("Female") }

        // 5. "Proceed" Button Click
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

        val height = heightStr.toFloat()
        val weight = weightStr.toFloat()

        // --- MATH ---
        val heightMeters = height / 100
        val bmi = weight / (heightMeters * heightMeters)
        val baseBmr = (10 * weight) + (6.25 * height) - (5 * userAge)
        val bmr = if (selectedGender == "Male") (baseBmr + 5) else (baseBmr - 161)
        val maintenanceCalories = (bmr * 1.55).toInt()
        val waterGoal = (weight * 35).toInt()

        // --- DATABASE SAVE ---
        lifecycleScope.launch {
            // A. Update User Physical Stats
            userViewModel.updateUserPhysicalStats(userId, height, weight, selectedGender, bmi)

            // B. Create Goal Object
            val newGoal = Goal(
                userId = userId,
                goalType = "General Health",
                dailyCalorieTarget = maintenanceCalories,
                waterGoalMl = waterGoal,
                status = "Active"
            )

            // C. Insert Goal
            userViewModel.insertGoal(newGoal)

            // --- NAVIGATION ---
            Toast.makeText(this@UserInfoActivity, "Plan Generated!", Toast.LENGTH_SHORT).show()

            // *** FIXED: Navigate to homepage instead of InsertGoalActivity ***
            val intent = Intent(this@UserInfoActivity, InsertGoalActivity::class.java)
            intent.putExtra("USER_ID", userId) // Pass ID so homepage knows who is logged in
            startActivity(intent)
            finish()
        }
    }

    private fun calculateAge(dobString: String): Int {
        return try {
            val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            val dobDate = sdf.parse(dobString) ?: return 0
            val dobCal = Calendar.getInstance().apply { time = dobDate }
            val today = Calendar.getInstance()
            var age = today.get(Calendar.YEAR) - dobCal.get(Calendar.YEAR)
            if (today.get(Calendar.DAY_OF_YEAR) < dobCal.get(Calendar.DAY_OF_YEAR)) {
                age--
            }
            age
        } catch (e: Exception) { 0 }
    }
}