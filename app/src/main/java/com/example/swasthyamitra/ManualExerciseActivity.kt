package com.example.swasthyamitra

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class ManualExerciseActivity : AppCompatActivity() {

    private lateinit var authHelper: FirebaseAuthHelper
    private lateinit var userId: String

    private lateinit var etExerciseName: EditText
    private lateinit var etDuration: EditText
    private lateinit var etCalories: EditText
    private lateinit var etNotes: EditText
    private lateinit var tvSelectedTime: TextView
    private lateinit var btnSelectTime: Button
    private lateinit var btnSaveExercise: Button
    private lateinit var btnCancel: Button
    private lateinit var spinnerIntensity: Spinner

    private var selectedHour: Int = 0
    private var selectedMinute: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manual_exercise)

        val application = application as UserApplication
        authHelper = application.authHelper
        userId = authHelper.getCurrentUser()?.uid ?: ""

        initViews()
        setupListeners()
        setCurrentTime()
    }

    private fun initViews() {
        etExerciseName = findViewById(R.id.etExerciseName)
        etDuration = findViewById(R.id.etDuration)
        etCalories = findViewById(R.id.etCalories)
        etNotes = findViewById(R.id.etNotes)
        tvSelectedTime = findViewById(R.id.tvSelectedTime)
        btnSelectTime = findViewById(R.id.btnSelectTime)
        btnSaveExercise = findViewById(R.id.btnSaveExercise)
        btnCancel = findViewById(R.id.btnCancel)
        spinnerIntensity = findViewById(R.id.spinnerIntensity)

        // Setup intensity spinner
        val intensities = arrayOf("Light", "Moderate", "High", "Very High")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, intensities)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerIntensity.adapter = adapter
        spinnerIntensity.setSelection(1) // Default to Moderate
    }

    private fun setupListeners() {
        findViewById<ImageButton>(R.id.btnBackManualExercise).setOnClickListener {
            finish()
        }

        btnSelectTime.setOnClickListener {
            showTimePicker()
        }

        btnSaveExercise.setOnClickListener {
            saveExercise()
        }

        btnCancel.setOnClickListener {
            finish()
        }

        // Auto-calculate calories based on duration
        etDuration.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                calculateCalories()
            }
        }
    }

    private fun setCurrentTime() {
        val calendar = Calendar.getInstance()
        selectedHour = calendar.get(Calendar.HOUR_OF_DAY)
        selectedMinute = calendar.get(Calendar.MINUTE)
        updateTimeDisplay()
    }

    private fun showTimePicker() {
        TimePickerDialog(this, { _, hour, minute ->
            selectedHour = hour
            selectedMinute = minute
            updateTimeDisplay()
        }, selectedHour, selectedMinute, true).show()
    }

    private fun updateTimeDisplay() {
        val timeStr = String.format("%02d:%02d", selectedHour, selectedMinute)
        tvSelectedTime.text = timeStr
    }

    private fun calculateCalories() {
        val duration = etDuration.text.toString().toIntOrNull() ?: 0
        val intensity = spinnerIntensity.selectedItem.toString()

        // Rough estimation: calories per minute based on intensity
        val caloriesPerMinute = when (intensity) {
            "Light" -> 4
            "Moderate" -> 6
            "High" -> 8
            "Very High" -> 10
            else -> 6
        }

        val estimatedCalories = duration * caloriesPerMinute
        etCalories.setText(estimatedCalories.toString())
    }

    private fun saveExercise() {
        val exerciseName = etExerciseName.text.toString().trim()
        val duration = etDuration.text.toString().toIntOrNull()
        val calories = etCalories.text.toString().toIntOrNull()
        val notes = etNotes.text.toString().trim()
        val intensity = spinnerIntensity.selectedItem.toString()

        // Validation
        if (exerciseName.isEmpty()) {
            Toast.makeText(this, "Please enter exercise name", Toast.LENGTH_SHORT).show()
            etExerciseName.requestFocus()
            return
        }

        if (duration == null || duration <= 0) {
            Toast.makeText(this, "Please enter valid duration", Toast.LENGTH_SHORT).show()
            etDuration.requestFocus()
            return
        }

        if (calories == null || calories < 0) {
            Toast.makeText(this, "Please enter valid calories", Toast.LENGTH_SHORT).show()
            etCalories.requestFocus()
            return
        }

        btnSaveExercise.isEnabled = false
        btnSaveExercise.text = "Saving..."

        val today = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
        val timeStr = String.format("%02d:%02d", selectedHour, selectedMinute)
        val timestamp = System.currentTimeMillis()

        // 1. Save to Firestore (for 7-day history)
        val logData = hashMapOf(
            "userId" to userId,
            "date" to today,
            "time" to timeStr,
            "exerciseName" to exerciseName,
            "duration" to duration,
            "caloriesBurned" to calories,
            "intensity" to intensity,
            "notes" to notes,
            "timestamp" to timestamp,
            "isManual" to true
        )

        FirebaseFirestore.getInstance("renu")
            .collection("exercise_logs")
            .add(logData)
            .addOnSuccessListener {
                // 2. Update RTDB for workout stats
                updateRTDBStats(exerciseName, duration, calories, today)
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed to save: ${e.message}", Toast.LENGTH_SHORT).show()
                btnSaveExercise.isEnabled = true
                btnSaveExercise.text = "Save Exercise"
            }
    }

    private fun updateRTDBStats(exerciseName: String, duration: Int, calories: Int, today: String) {
        val db = FirebaseDatabase.getInstance("https://swasthyamitra-ded44-default-rtdb.asia-southeast1.firebasedatabase.app").reference
        val userRef = db.child("users").child(userId)

        userRef.get().addOnSuccessListener { snapshot ->
            val data = snapshot.getValue(FitnessData::class.java) ?: FitnessData()

            val sessionId = UUID.randomUUID().toString()
            val session = WorkoutSession(
                id = sessionId,
                date = today,
                category = "Manual",
                videoId = "manual_${System.currentTimeMillis()}",
                duration = duration,
                completed = true,
                timestamp = System.currentTimeMillis(),
                caloriesBurned = calories
            )

            val updatedHistory = data.workoutHistory.toMutableMap()
            updatedHistory[sessionId] = session
            val updatedCompletion = data.completionHistory.toMutableMap()
            updatedCompletion[today] = true

            val updatedData = data.copy(
                xp = data.xp + 100,
                completionHistory = updatedCompletion,
                workoutHistory = updatedHistory,
                totalWorkoutMinutes = data.totalWorkoutMinutes + duration,
                lastActiveDate = today
            )

            userRef.setValue(updatedData)
                .addOnSuccessListener {
                    runOnUiThread {
                        Toast.makeText(this, "Exercise logged! +100 XP & $calories kcal", Toast.LENGTH_LONG).show()
                        finish()
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Failed to update stats: ${e.message}", Toast.LENGTH_SHORT).show()
                    btnSaveExercise.isEnabled = true
                    btnSaveExercise.text = "Save Exercise"
                }
        }
    }
}

