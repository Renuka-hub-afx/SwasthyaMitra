package com.example.swasthyamitra

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import android.view.ViewGroup
import android.widget.FrameLayout

class ProfileActivity : AppCompatActivity() {

    private lateinit var authHelper: FirebaseAuthHelper
    private var userId: String = ""

    // Views
    private lateinit var userNameText: TextView
    private lateinit var userEmailText: TextView
    private lateinit var userAgeText: TextView
    private lateinit var userGenderText: TextView
    private lateinit var userHeightText: TextView
    private lateinit var userWeightText: TextView
    private lateinit var userBmiText: TextView
    private lateinit var bmrText: TextView
    private lateinit var tdeeText: TextView
    private lateinit var goalWeightText: TextView
    private lateinit var goalCaloriesText: TextView
    private lateinit var activityLevelText: TextView
    private lateinit var editProfileButton: MaterialButton
    private lateinit var logoutButton: MaterialButton
    private lateinit var editGoalWeightButton: ImageView
    private lateinit var btnBack: ImageView
    private lateinit var btnSettings: ImageView
    private lateinit var avatarContainer: ViewGroup
    private lateinit var avatarManager: AvatarManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        authHelper = FirebaseAuthHelper(this)
        avatarManager = AvatarManager(this)
        userId = authHelper.getCurrentUser()?.uid ?: ""

        if (userId.isEmpty()) {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        initializeViews()
        loadUserProfile()
    }

    override fun onResume() {
        super.onResume()
        updateAvatarDisplay()
    }

    private fun initializeViews() {
        userNameText = findViewById(R.id.userNameText)
        userEmailText = findViewById(R.id.userEmailText)
        userAgeText = findViewById(R.id.userAgeText)
        userGenderText = findViewById(R.id.userGenderText)
        userHeightText = findViewById(R.id.userHeightText)
        userWeightText = findViewById(R.id.userWeightText)
        userBmiText = findViewById(R.id.userBmiText)
        bmrText = findViewById(R.id.bmrText)
        tdeeText = findViewById(R.id.tdeeText)
        goalWeightText = findViewById(R.id.goalWeightText)
        goalCaloriesText = findViewById(R.id.goalCaloriesText)
        activityLevelText = findViewById(R.id.activityLevelText)
        editProfileButton = findViewById(R.id.editProfileButton)
        logoutButton = findViewById(R.id.logoutButton)
        editGoalWeightButton = findViewById(R.id.editGoalWeightButton)
        btnBack = findViewById(R.id.btnBack)
        btnSettings = findViewById(R.id.btnSettings)
        avatarContainer = findViewById(R.id.avatarContainer)

        // Set up click listeners
        avatarContainer.setOnClickListener {
            startActivity(Intent(this, AvatarCustomizationActivity::class.java))
        }
        btnBack.setOnClickListener {
            finish()
        }

        btnSettings.setOnClickListener {
            Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show()
        }

        editProfileButton.setOnClickListener {
            Toast.makeText(this, "Edit profile - Coming in Week 2", Toast.LENGTH_SHORT).show()
        }

        logoutButton.setOnClickListener {
            handleLogout()
        }

        editGoalWeightButton.setOnClickListener {
            showEditGoalWeightDialog()
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
                                val age = document.get("age").toString()
                                val gender = document.getString("gender") ?: "N/A"
                                val height = document.get("height").toString()
                                val weight = document.get("weight").toString()

                                userNameText.text = name
                                userAgeText.text = "$age years"
                                userGenderText.text = gender
                                userHeightText.text = "$height cm"
                                userWeightText.text = "$weight kg"

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

                    FirebaseFirestore.getInstance().collection("goals")
                        .whereEqualTo("userId", userId)
                        .limit(1)
                        .get()
                        .addOnSuccessListener { querySnapshot ->
                            if (!querySnapshot.isEmpty) {
                                val document = querySnapshot.documents[0]
                                val targetWeight = document.getDouble("targetWeight")
                                val dailyCalories = document.getDouble("dailyCalories")
                                val activityLevel = document.getString("activityLevel") ?: "N/A"
                                val bmr = document.getDouble("bmr")
                                val tdee = document.getDouble("tdee")

                                goalWeightText.text = if (targetWeight != null) "${targetWeight.toInt()} kg" else "Not set"
                                goalCaloriesText.text = if (dailyCalories != null) "${dailyCalories.toInt()} kcal" else "Not set"
                                activityLevelText.text = activityLevel
                                bmrText.text = if (bmr != null) "${bmr.toInt()} kcal" else "N/A"
                                tdeeText.text = if (tdee != null) "${tdee.toInt()} kcal" else "N/A"
                            } else {
                                goalWeightText.text = "Not set"
                                goalCaloriesText.text = "Not set"
                                activityLevelText.text = "N/A"
                                bmrText.text = "N/A"
                                tdeeText.text = "N/A"
                            }
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

        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun showEditGoalWeightDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_edit_goal_weight, null)
        val editTextGoalWeight = dialogView.findViewById<EditText>(R.id.editGoalWeight)

        AlertDialog.Builder(this)
            .setTitle("Update Goal Weight")
            .setView(dialogView)
            .setPositiveButton("Save") { _, _ ->
                val newGoalWeight = editTextGoalWeight.text.toString().toDoubleOrNull()
                if (newGoalWeight != null && newGoalWeight > 0) {
                    updateGoalWeight(newGoalWeight)
                } else {
                    Toast.makeText(this, "Please enter a valid weight", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .create()
            .show()
    }

    private fun updateGoalWeight(newGoalWeight: Double) {
        FirebaseFirestore.getInstance().collection("goals")
            .whereEqualTo("userId", userId)
            .limit(1)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    val goalDocId = querySnapshot.documents[0].id
                    FirebaseFirestore.getInstance().collection("goals")
                        .document(goalDocId)
                        .update("targetWeight", newGoalWeight)
                        .addOnSuccessListener {
                            Toast.makeText(this@ProfileActivity, "Goal weight updated!", Toast.LENGTH_SHORT).show()
                            loadUserProfile()
                        }
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this@ProfileActivity, "Error updating goal weight: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
    
    private fun updateAvatarDisplay() {
        val galleryUri = avatarManager.getGalleryUri()

        avatarContainer.removeAllViews()

        val imageView = ImageView(this)
        imageView.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        
        if (galleryUri != null) {
            imageView.setImageURI(galleryUri)
        } else {
            imageView.setImageResource(R.drawable.circular_background) // Default
        }
        avatarContainer.addView(imageView)
    }
}