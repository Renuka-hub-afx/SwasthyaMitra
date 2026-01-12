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
    }



    private fun loadProgressData() {
        lifecycleScope.launch {
            try {
                // Load weekly statistics
                val weeklyCalories = getWeeklyCalories()
                val weeklyWorkouts = getWeeklyWorkouts()
                val streaks = calculateStreaks()

                weeklyCaloriesText.text = "$weeklyCalories kcal"
                weeklyWorkoutsText.text = "$weeklyWorkouts workouts"
                currentStreakText.text = "${streaks.first} days"
                longestStreakText.text = "${streaks.second} days"

            } catch (e: Exception) {
                Toast.makeText(this@ProgressActivity, "Error loading progress: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private suspend fun getWeeklyCalories(): Int {
        // Get total calories consumed in the last 7 days
        val calendar = Calendar.getInstance()
        var totalCalories = 0
        
        for (i in 0 until 7) {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date = dateFormat.format(calendar.time)
            
            val dailyCalories = authHelper.getDailyCalories(userId, date)
            totalCalories += dailyCalories
            
            calendar.add(Calendar.DAY_OF_YEAR, -1)
        }
        
        return totalCalories
    }

    private suspend fun getWeeklyWorkouts(): Int {
        // TODO: Implement workout counting once exercise tracking is added
        // For now, return mock data
        return 0
    }

    private fun calculateStreaks(): Pair<Int, Int> {
        // TODO: Implement streak calculation based on food logs and workouts
        // For now, return mock data
        return Pair(0, 0)
    }

    private fun showChartsView() {
        Toast.makeText(this, "Charts view - Coming in Week 2", Toast.LENGTH_SHORT).show()
        // TODO: Show weight/calorie/workout charts using MPAndroidChart
    }

    private fun showHistoryView() {
        Toast.makeText(this, "History view - Coming in Week 2", Toast.LENGTH_SHORT).show()
        // TODO: Show daily logs history
    }

    private fun showAchievementsView() {
        Toast.makeText(this, "Achievements view - Coming in Week 2", Toast.LENGTH_SHORT).show()
        // TODO: Show badges, levels, and tasks
    }
}
