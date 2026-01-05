package com.example.swasthyamitra

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class ProfileActivity : AppCompatActivity() {

    private lateinit var authHelper: FirebaseAuthHelper
    private var userId: String = ""
    
    private lateinit var userNameText: TextView
    private lateinit var userEmailText: TextView
    private lateinit var userAgeText: TextView
    private lateinit var userGenderText: TextView
    private lateinit var userHeightText: TextView
    private lateinit var userWeightText: TextView
    private lateinit var userBmiText: TextView
    private lateinit var goalWeightText: TextView
    private lateinit var goalCaloriesText: TextView
    private lateinit var activityLevelText: TextView
    private lateinit var editProfileButton: MaterialButton
    private lateinit var logoutButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        authHelper = FirebaseAuthHelper(this)
        userId = authHelper.getCurrentUser()?.uid ?: ""

        if (userId.isEmpty()) {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        initializeViews()
        loadUserProfile()
    }

    private fun initializeViews() {
        userNameText = findViewById(R.id.userNameText)
        userEmailText = findViewById(R.id.userEmailText)
        userAgeText = findViewById(R.id.userAgeText)
        userGenderText = findViewById(R.id.userGenderText)
        userHeightText = findViewById(R.id.userHeightText)
        userWeightText = findViewById(R.id.userWeightText)
        userBmiText = findViewById(R.id.userBmiText)
        goalWeightText = findViewById(R.id.goalWeightText)
        goalCaloriesText = findViewById(R.id.goalCaloriesText)
        activityLevelText = findViewById(R.id.activityLevelText)
        editProfileButton = findViewById(R.id.editProfileButton)
        logoutButton = findViewById(R.id.logoutButton)

        findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar).apply {
            setNavigationOnClickListener { finish() }
        }

        editProfileButton.setOnClickListener {
            Toast.makeText(this, "Edit profile - Coming in Week 2", Toast.LENGTH_SHORT).show()
            // TODO: Navigate to edit profile screen
        }

        logoutButton.setOnClickListener {
            handleLogout()
        }
    }

    private fun loadUserProfile() {
        lifecycleScope.launch {
            try {
                val currentUser = authHelper.getCurrentUser()
                if (currentUser != null) {
                    userEmailText.text = currentUser.email ?: "No email"
                    
                    // Load user data from Firestore
                    FirebaseFirestore.getInstance().collection("users")
                        .document(userId)
                        .get()
                        .addOnSuccessListener { document ->
                            if (document.exists()) {
                                val name = document.getString("name") ?: "User"
                                val age = document.getLong("age")?.toString() ?: "N/A"
                                val gender = document.getString("gender") ?: "N/A"
                                val height = document.getDouble("height")?.toString() ?: "N/A"
                                val weight = document.getDouble("weight")?.toString() ?: "N/A"
                                val activityLevel = document.getString("activityLevel") ?: "N/A"
                                
                                userNameText.text = name
                                userAgeText.text = "$age years"
                                userGenderText.text = gender
                                userHeightText.text = "$height cm"
                                userWeightText.text = "$weight kg"
                                activityLevelText.text = activityLevel
                                
                                // Calculate BMI
                                val heightValue = height.toDoubleOrNull()
                                val weightValue = weight.toDoubleOrNull()
                                if (heightValue != null && weightValue != null && heightValue > 0) {
                                    val bmi = weightValue / ((heightValue / 100) * (heightValue / 100))
                                    userBmiText.text = String.format("%.1f", bmi)
                                } else {
                                    userBmiText.text = "N/A"
                                }
                            }
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(this@ProfileActivity, "Error loading profile: ${e.message}", Toast.LENGTH_SHORT).show()
                        }

                    // Load goals from Firestore
                    FirebaseFirestore.getInstance().collection("goals")
                        .document(userId)
                        .get()
                        .addOnSuccessListener { document ->
                            if (document.exists()) {
                                val goalWeight = document.getDouble("goalWeight")?.toString() ?: "N/A"
                                val goalCalories = document.getLong("goalCalories")?.toString() ?: "N/A"
                                
                                goalWeightText.text = "$goalWeight kg"
                                goalCaloriesText.text = "$goalCalories kcal/day"
                            } else {
                                goalWeightText.text = "Not set"
                                goalCaloriesText.text = "Not set"
                            }
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(this@ProfileActivity, "Error loading goals: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                }
            } catch (e: Exception) {
                Toast.makeText(this@ProfileActivity, "Error loading profile: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun handleLogout() {
        FirebaseAuth.getInstance().signOut()
        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()
        
        // Navigate back to login screen
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
