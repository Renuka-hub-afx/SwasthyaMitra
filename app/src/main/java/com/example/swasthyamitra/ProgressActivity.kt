package com.example.swasthyamitra

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import com.example.swasthyamitra.services.TelegramService

class ProgressActivity : AppCompatActivity() {

    private lateinit var authHelper: FirebaseAuthHelper
    private var userId: String = ""
    
    private lateinit var weeklyCaloriesText: TextView
    private lateinit var weeklyWorkoutsText: TextView
    private lateinit var currentStreakText: TextView
    private lateinit var longestStreakText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)

        authHelper = FirebaseAuthHelper(this)
        userId = authHelper.getCurrentUser()?.uid ?: ""

        if (userId.isEmpty()) {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        initializeViews()
        loadProgressData()
    }

    private fun initializeViews() {
        weeklyCaloriesText = findViewById(R.id.weeklyCaloriesText)
        weeklyWorkoutsText = findViewById(R.id.weeklyWorkoutsText)
        currentStreakText = findViewById(R.id.currentStreakText)
        longestStreakText = findViewById(R.id.longestStreakText)

        findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar).apply {
            setNavigationOnClickListener { finish() }
        }
        
        // Setup click listeners
        findViewById<android.view.View>(R.id.viewDetailedReportButton)?.setOnClickListener { showChartsView() }
        
        // Progress Roadmap Click Listeners
        findViewById<android.view.View>(R.id.roadmapItemWeight)?.setOnClickListener { 
            startActivity(android.content.Intent(this, WeightProgressActivity::class.java))
        }
        findViewById<android.view.View>(R.id.roadmapItemBadges)?.setOnClickListener { 
            startActivity(android.content.Intent(this, BadgesActivity::class.java))
        }
        findViewById<android.view.View>(R.id.roadmapItemHistory)?.setOnClickListener { 
            startActivity(android.content.Intent(this, HistoryActivity::class.java))
        }
        
        // Telegram button
        findViewById<com.google.android.material.button.MaterialButton>(R.id.btn_send_telegram_progress)?.setOnClickListener {
            sendProgressToTelegram()
        }
    }

    private fun loadProgressData() {
        // 1. Load User Statistics (Streaks, Workouts, Calories) from Firebase Realtime Database
        val db = com.google.firebase.database.FirebaseDatabase.getInstance("https://swasthyamitra-c0899-default-rtdb.asia-southeast1.firebasedatabase.app").reference
        val userRef = db.child("users").child(userId)

        // Use addValueEventListener for real-time updates
        userRef.addValueEventListener(object : com.google.firebase.database.ValueEventListener {
            override fun onDataChange(snapshot: com.google.firebase.database.DataSnapshot) {
                val data = snapshot.getValue(FitnessData::class.java) ?: FitnessData()
                
                runOnUiThread {
                    // Update Streak
                    currentStreakText.text = "${data.streak} days"
                    longestStreakText.text = "${data.streak} days" 

                    // Calculate Weekly Stats (Workouts & Calories Burned)
                    val (workouts, calories) = calculateWeeklyStats(data.workoutHistory)
                    
                    weeklyWorkoutsText.text = "$workouts workouts"
                    weeklyCaloriesText.text = "$calories kcal"
                }
            }

            override fun onCancelled(error: com.google.firebase.database.DatabaseError) {
                Toast.makeText(this@ProgressActivity, "Failed to load stats: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun calculateWeeklyStats(history: Map<String, WorkoutSession>): Pair<Int, Int> {
        val calendar = Calendar.getInstance()
        // Reset to start of current week (e.g., last 7 days)
        calendar.add(Calendar.DAY_OF_YEAR, -7)
        val oneWeekAgo = calendar.timeInMillis
        
        var workoutCount = 0
        var caloriesBurned = 0
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        for (session in history.values) {
             try {
                // If session has a timestamp, use it. If not, parse the date string.
                val sessionTime = if (session.timestamp > 0) {
                    session.timestamp
                } else {
                    dateFormat.parse(session.date)?.time ?: 0L
                }

                if (sessionTime > oneWeekAgo && session.completed) {
                    workoutCount++
                    // Fix for legacy data: If calories not saved, estimate based on duration (~6 kcal/min)
                    val burned = if (session.caloriesBurned > 0) {
                        session.caloriesBurned
                    } else {
                        session.duration * 6
                    }
                    caloriesBurned += burned
                }
            } catch (e: Exception) {
                continue
            }
        }
        return Pair(workoutCount, caloriesBurned)
    }

    private fun showChartsView() {
    startActivity(android.content.Intent(this, DetailedReportActivity::class.java))
}



    private fun showHistoryView() {
        // Placeholder
    }

    private fun showAchievementsView() {
        // Placeholder
    }
    
    private fun sendProgressToTelegram() {
        lifecycleScope.launch {
            try {
                runOnUiThread {
                    Toast.makeText(this@ProgressActivity, "Sending to Telegram... 📤", Toast.LENGTH_SHORT).show()
                }
                
                // Get current values from UI
                val weeklyCalories = weeklyCaloriesText.text.toString().replace(Regex("[^0-9]"), "").toIntOrNull() ?: 0
                val weeklyWorkouts = weeklyWorkoutsText.text.toString().replace(Regex("[^0-9]"), "").toIntOrNull() ?: 0
                val currentStreak = currentStreakText.text.toString().replace(Regex("[^0-9]"), "").toIntOrNull() ?: 0
                
                val result = TelegramService.sendExerciseSummaryToTelegram(
                    exerciseName = "Weekly Progress Report",
                    duration = "$weeklyWorkouts workouts",
                    caloriesBurned = weeklyCalories,
                    exerciseType = "Weekly",
                    notes = "Current streak: $currentStreak days 🔥"
                )
                
                runOnUiThread {
                    if (result.isSuccess) {
                        Toast.makeText(this@ProgressActivity, "✅ Sent to Telegram!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@ProgressActivity, "❌ Failed to send", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(this@ProgressActivity, "❌ Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
