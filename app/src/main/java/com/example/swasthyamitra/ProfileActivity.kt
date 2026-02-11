package com.example.swasthyamitra

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.ArrayAdapter
import android.widget.Spinner
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
    private lateinit var sharedPrefs: android.content.SharedPreferences

    // Edit Views
    private lateinit var userAgeEdit: EditText
    private lateinit var userHeightEdit: EditText
    private lateinit var userWeightEdit: EditText
    private lateinit var userGenderSpinner: Spinner


    private var isEditMode = false
    private val genderOptions = listOf("Male", "Female", "Other", "Prefer not to say")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        authHelper = FirebaseAuthHelper(this)
        avatarManager = AvatarManager(this)
        sharedPrefs = getSharedPreferences("user_profile", android.content.Context.MODE_PRIVATE)
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

        userAgeEdit = findViewById(R.id.userAgeEdit)
        userHeightEdit = findViewById(R.id.userHeightEdit)
        userWeightEdit = findViewById(R.id.userWeightEdit)
        userGenderSpinner = findViewById(R.id.userGenderSpinner)


        setupGenderSpinner()

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
            if (isEditMode) {
                saveProfileChanges()
            } else {
                toggleEditMode(true)
            }
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

                    // Load user data from SharedPreferences first (offline/immediate)
                    val localAge = sharedPrefs.getInt("age", -1)
                    val localGender = sharedPrefs.getString("gender", null)
                    val localHeight = sharedPrefs.getString("height", null)
                    val localWeight = sharedPrefs.getString("weight", null)

                    if (localAge != -1 && localGender != null && localHeight != null && localWeight != null) {
                        userAgeText.text = "$localAge years"
                        userGenderText.text = localGender
                        userHeightText.text = "$localHeight cm"
                        userWeightText.text = "$localWeight kg"
                        
                        val h = localHeight.toDoubleOrNull() ?: 0.0
                        val w = localWeight.toDoubleOrNull() ?: 0.0
                        if (h > 0) {
                            val bmi = w / ((h / 100) * (h / 100))
                            userBmiText.text = String.format("%.1f", bmi)
                        }
                    }

                    // Load user data from Firestore to keep it synced
                    FirebaseFirestore.getInstance("renu").collection("users") // Using RENU database instance
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
                                
                                // Only update text views if local data is not already present or if we want Firestore to be ultimate truth
                                // For now, let's update them anyway to sync from server
                                userAgeText.text = if (age != "null") "$age years" else userAgeText.text
                                userGenderText.text = gender
                                userHeightText.text = if (height != "null") "$height cm" else userHeightText.text
                                userWeightText.text = if (weight != "null") "$weight kg" else userWeightText.text

                                val heightValue = height.toDoubleOrNull()
                                val weightValue = weight.toDoubleOrNull()
                                if (heightValue != null && weightValue != null && heightValue > 0) {
                                    val bmi = weightValue / ((heightValue / 100) * (heightValue / 100))
                                    userBmiText.text = String.format("%.1f", bmi)
                                }
                            }
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(this@ProfileActivity, "Error loading profile: ${e.message}", Toast.LENGTH_SHORT).show()
                        }

                    FirebaseFirestore.getInstance("renu").collection("goals") // Using RENU database instance
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
        // Clear Firebase session
        FirebaseAuth.getInstance().signOut()
        
        // Clear any cached user data from SharedPreferences
        val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
        
        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()

        // Navigate to MainActivity (which will show login)
        val intent = Intent(this, MainActivity::class.java)
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
        FirebaseFirestore.getInstance("renu").collection("goals") // Using RENU database instance
            .whereEqualTo("userId", userId)
            .limit(1)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    val goalDocId = querySnapshot.documents[0].id
                    FirebaseFirestore.getInstance("renu").collection("goals") // Using RENU database instance
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
    
    private fun setupGenderSpinner() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genderOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        userGenderSpinner.adapter = adapter
    }

    private fun toggleEditMode(edit: Boolean) {
        isEditMode = edit
        
        // Toggle Visibility
        val viewVisibility = if (edit) View.GONE else View.VISIBLE
        val editVisibility = if (edit) View.VISIBLE else View.GONE

        userAgeText.visibility = viewVisibility
        userAgeEdit.visibility = editVisibility
        userGenderText.visibility = viewVisibility
        userGenderSpinner.visibility = editVisibility
        userHeightText.visibility = viewVisibility
        userHeightEdit.visibility = editVisibility
        userWeightText.visibility = viewVisibility
        userWeightEdit.visibility = editVisibility

        editProfileButton.text = if (edit) "✅ Save Changes" else "✏️ Edit Profile"

        if (edit) {
            // Populate fields with current values
            userAgeEdit.setText(userAgeText.text.toString().filter { it.isDigit() })
            userHeightEdit.setText(userHeightText.text.toString().filter { it.isDigit() || it == '.' })
            userWeightEdit.setText(userWeightText.text.toString().filter { it.isDigit() || it == '.' })
            val genderIndex = genderOptions.indexOf(userGenderText.text.toString())
            if (genderIndex != -1) userGenderSpinner.setSelection(genderIndex)
        }
    }

    private fun saveProfileChanges() {
        val ageStr = userAgeEdit.text.toString()
        val heightStr = userHeightEdit.text.toString()
        val weightStr = userWeightEdit.text.toString()
        val gender = userGenderSpinner.selectedItem.toString()

        // Validation
        if (ageStr.isEmpty() || heightStr.isEmpty() || weightStr.isEmpty()) {
            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }

        val age = ageStr.toIntOrNull() ?: 0
        val height = heightStr.toDoubleOrNull() ?: 0.0
        val weight = weightStr.toDoubleOrNull() ?: 0.0

        if (age !in 1..120) {
            Toast.makeText(this, "Please enter a realistic age", Toast.LENGTH_SHORT).show()
            return
        }
        if (height !in 50.0..250.0) {
            Toast.makeText(this, "Please enter a realistic height (50-250 cm)", Toast.LENGTH_SHORT).show()
            return
        }
        if (weight !in 2.0..300.0) {
            Toast.makeText(this, "Please enter a realistic weight (2-300 kg)", Toast.LENGTH_SHORT).show()
            return
        }

        // Persistence: SharedPreferences
        sharedPrefs.edit().apply {
            putInt("age", age)
            putString("gender", gender)
            putString("height", height.toString())
            putString("weight", weight.toString())
            apply()
        }

        // Sync with Firestore
        FirebaseFirestore.getInstance().collection("users")
            .document(userId)
            .update(mapOf(
                "age" to age,
                "gender" to gender,
                "height" to height,
                "weight" to weight
            ))
            .addOnSuccessListener {
                Toast.makeText(this, "Profile updated successfully!", Toast.LENGTH_SHORT).show()
                toggleEditMode(false)
                loadUserProfile() // Refresh UI from data
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Sync failed: ${e.message}, saved locally", Toast.LENGTH_SHORT).show()
                toggleEditMode(false)
                loadUserProfile()
            }
    }

    private fun updateAvatarDisplay() {
        val mode = avatarManager.getProfileMode()
        val galleryUri = avatarManager.getGalleryUri()
        val avatarId = avatarManager.getAvatarId()

        avatarContainer.removeAllViews()

        val imageView = ImageView(this)
        imageView.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        
        if (mode == ProfileMode.PRESET_AVATAR && avatarId != null) {
            val resId = getAvatarDrawable(avatarId)
            if (resId != 0) {
                imageView.setImageResource(resId)
            } else {
                imageView.setImageResource(R.drawable.circular_background)
            }
        } else if (mode == ProfileMode.GALLERY_PHOTO && galleryUri != null) {
            imageView.setImageURI(galleryUri)
        } else {
            imageView.setImageResource(R.drawable.circular_background) // Default
        }
        avatarContainer.addView(imageView)
    }

    private fun getAvatarDrawable(avatarId: String): Int {
        val resName = avatarId.replace("_", "")
        return try {
            resources.getIdentifier(resName, "drawable", packageName)
        } catch (e: Exception) {
            0
        }
    }
}