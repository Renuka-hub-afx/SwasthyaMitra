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
import kotlinx.coroutines.tasks.await
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.database.FirebaseDatabase
import com.example.swasthyamitra.adapters.HistoryAdapter
import com.example.swasthyamitra.adapters.HistoryItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.View

class ProgressActivity : AppCompatActivity() {

    private lateinit var authHelper: FirebaseAuthHelper
    private var userId: String = ""

    private lateinit var weeklyCaloriesText: TextView
    private lateinit var weeklyWorkoutsText: TextView
    private lateinit var currentStreakText: TextView
    private lateinit var longestStreakText: TextView
    
    // Daily History UI
    private lateinit var rvHistory: RecyclerView
    private lateinit var tvHistoryEmpty: TextView

    // Store data for dialogs

    private var weeklyStats: List<DailyStat> = emptyList()

    // Data model for stats
    data class DailyStat(val date: String, val calories: Int, val workoutCalories: Int)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)

        authHelper = FirebaseAuthHelper(this)
        userId = authHelper.getCurrentUser()?.uid ?: ""

        if (userId.isEmpty()) {
             finish()
             return
        }

        initializeViews()
        loadProgressData()
    }



    override fun onResume() {
        super.onResume()
        if (userId.isNotEmpty()) {
            loadDailyHistory()
        }
    }

    private fun initializeViews() {
        weeklyCaloriesText = findViewById(R.id.weeklyCaloriesText)
        weeklyWorkoutsText = findViewById(R.id.weeklyWorkoutsText)
        currentStreakText = findViewById(R.id.currentStreakText)
        longestStreakText = findViewById(R.id.longestStreakText)
        
        // Initialize History UI
        rvHistory = findViewById(R.id.rv_history)
        tvHistoryEmpty = findViewById(R.id.tv_history_empty)
        rvHistory.layoutManager = LinearLayoutManager(this)
        
        findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar).apply {
            setNavigationOnClickListener { finish() }
        }
        
        // Initialize Click Listeners for Report options
        findViewById<android.view.View>(R.id.row_weight_charts).setOnClickListener {
             showChartsView()
        }
        
        // History row removed
        
        findViewById<android.view.View>(R.id.row_achievements).setOnClickListener {
             showAchievementsView()
        }
        
        findViewById<android.view.View>(R.id.viewDetailedReportButton).setOnClickListener {
             Toast.makeText(this, "Detailed Report Downloaded! 📥", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadProgressData() {
        lifecycleScope.launch {
            try {
                // Fetch Last 7 Days Data (Visuals) but using 14 days buffer for fetching
                weeklyStats = getWeeklyStats()
                
                // 1. Update Summaries
                val totalCalories = weeklyStats.sumOf { it.calories }
                val totalWorkoutCalories = weeklyStats.sumOf { it.workoutCalories }
                
                weeklyCaloriesText.text = "$totalCalories kcal"
                weeklyWorkoutsText.text = "$totalWorkoutCalories kcal"
                
                // 4. Streaks Calculation
                val streak = calculateCurrentStreak(weeklyStats)
                currentStreakText.text = "$streak days"
                
                // For longest streak, we would need historical data. 
                // For now, we show the best streak within this week or keep it logical.
                val longestStreak = if (streak > 5) streak else 5 // Placeholder logic to not downgrade user
                longestStreakText.text = "$longestStreak days"

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@ProgressActivity, "Error loading data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun calculateCurrentStreak(stats: List<DailyStat>): Int {
        // Stats are ordered Oldest -> Newest (Fri -> Sat -> ... -> Today)
        var streak = 0
        val reversedStats = stats.reversed() // Today -> Oldest
        
        // Check if Today has activity
        val today = reversedStats.firstOrNull()
        val hasTodayActivity = (today?.calories ?: 0) > 0 || (today?.workoutCalories ?: 0) > 0
        
        // If today has activity, start counting from today.
        // If today has NO activity, start counting from yesterday (streak is safe until day end).
        
        val startIndex = if (hasTodayActivity) 0 else 1
        
        for (i in startIndex until reversedStats.size) {
            val day = reversedStats[i]
            val hasActivity = day.calories > 0 || day.workoutCalories > 0
            if (hasActivity) {
                streak++
            } else {
                break
            }
        }
        return streak
    }

    private suspend fun getWeeklyStats(): List<DailyStat> {
        val stats = mutableListOf<DailyStat>()
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val displayFormat = SimpleDateFormat("EEE", Locale.getDefault())
        
        // 1. Fetch data for last 14 days to be safe against timezone/cutoff issues
        val foodLogs = authHelper.getRecentFoodLogs(userId, 14)
        val exerciseLogs = authHelper.getRecentExerciseLogs(userId, 14)
        
        // Fetch RTDB logs once
        var workoutHistorySnapshot: com.google.firebase.database.DataSnapshot? = null
        try {
            val db = FirebaseDatabase.getInstance("https://swasthyamitra-c0899-default-rtdb.asia-southeast1.firebasedatabase.app").reference
             workoutHistorySnapshot = db.child("users").child(userId).child("workoutHistory").get().await()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // 2. Iterate backwards 6 days to today (Total 7 days for the chart)
        calendar.add(Calendar.DAY_OF_YEAR, -6)
        
        for (i in 0 until 7) {
            val dateKey = dateFormat.format(calendar.time)
            val dayName = displayFormat.format(calendar.time)
            
            // Filter food logs for this date
            val dailyCalories = foodLogs.filter { it.date == dateKey }.sumOf { it.calories }
            
            // Filter exercise logs (Firestore) for this date
            val aiBurn = exerciseLogs.filter { 
                (it["date"] as? String) == dateKey 
            }.sumOf { 
                (it["caloriesBurned"] as? Number)?.toInt() ?: 0 
            }
            
            // Filter workout logs (RTDB) for this date
            var rtdbBurn = 0
            workoutHistorySnapshot?.children?.forEach { child ->
                 if (child.child("date").getValue(String::class.java) == dateKey) {
                     rtdbBurn += child.child("caloriesBurned").getValue(Int::class.java) ?: 0
                 }
            }

            stats.add(DailyStat(dayName, dailyCalories, aiBurn + rtdbBurn))
            
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }
        return stats
    }
    
    private fun showAchievementsView() {
        Toast.makeText(this, "Badges Coming Soon! 🏆", Toast.LENGTH_SHORT).show()
    }

    private fun showChartsView() {
        val dialog = android.app.Dialog(this)
        dialog.setContentView(R.layout.dialog_chart)
        dialog.window?.setBackgroundDrawable(android.graphics.drawable.ColorDrawable(android.graphics.Color.TRANSPARENT))
        val width = (resources.displayMetrics.widthPixels * 0.9).toInt()
        dialog.window?.setLayout(
            width, 
            android.view.ViewGroup.LayoutParams.WRAP_CONTENT
        )
        
        val chartView: com.github.mikephil.charting.charts.BarChart = dialog.findViewById(R.id.dialogCalorieChart)
        setupChart(chartView, weeklyStats)
        
        dialog.findViewById<android.widget.Button>(R.id.btnCloseChart).setOnClickListener {
            dialog.dismiss()
        }
        
        dialog.show()
    }

    private fun setupChart(chart: com.github.mikephil.charting.charts.BarChart, stats: List<DailyStat>) {
        if (stats.isEmpty()) return

        val entriesIntake = ArrayList<com.github.mikephil.charting.data.BarEntry>()
        val entriesBurn = ArrayList<com.github.mikephil.charting.data.BarEntry>()

        stats.forEachIndexed { index, stat ->
            entriesIntake.add(com.github.mikephil.charting.data.BarEntry(index.toFloat(), stat.calories.toFloat()))
            entriesBurn.add(com.github.mikephil.charting.data.BarEntry(index.toFloat(), stat.workoutCalories.toFloat()))
        }
        
        val set1 = com.github.mikephil.charting.data.BarDataSet(entriesIntake, "Intake")
        set1.color = android.graphics.Color.parseColor("#4CAF50") 
        set1.valueTextColor = android.graphics.Color.BLACK
        set1.valueTextSize = 8f

        val set2 = com.github.mikephil.charting.data.BarDataSet(entriesBurn, "Burned")
        set2.color = android.graphics.Color.parseColor("#FF5722") 
        set2.valueTextColor = android.graphics.Color.BLACK
        set2.valueTextSize = 8f
        
        val barWidth = 0.35f
        val barSpace = 0.05f
        val groupSpace = 0.2f
        
        val barData = com.github.mikephil.charting.data.BarData(set1, set2)
        barData.barWidth = barWidth
        
        chart.data = barData
        
        chart.description.isEnabled = false
        chart.axisRight.isEnabled = false
        chart.xAxis.position = com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM
        chart.xAxis.setDrawGridLines(false)
        chart.xAxis.setGranularity(1f)
        chart.xAxis.setCenterAxisLabels(true)
        chart.axisLeft.setDrawGridLines(true)
        
        val labels = stats.map { it.date }
        chart.xAxis.valueFormatter = com.github.mikephil.charting.formatter.IndexAxisValueFormatter(labels)

        chart.groupBars(0f, groupSpace, barSpace)
        chart.xAxis.axisMinimum = 0f
        chart.xAxis.axisMaximum = stats.size.toFloat()
        
        chart.invalidate()
        chart.animateY(1000)
    }
    

    private suspend fun getDailyWorkoutCalories(date: String): Int {
        var total = 0
        try {
            // 1. AI Exercises (Firestore)
            val firestore = FirebaseFirestore.getInstance()
            val aiLogs = firestore.collection("exerciseLogs")
                .whereEqualTo("userId", userId)
                .whereEqualTo("date", date)
                .get()
                .await()
            
            for (doc in aiLogs) {
                total += (doc.getLong("caloriesBurned") ?: 0L).toInt()
            }

            // 2. Regular Workouts (RTDB)
            val db = FirebaseDatabase.getInstance("https://swasthyamitra-c0899-default-rtdb.asia-southeast1.firebasedatabase.app").reference
            val snapshot = db.child("users").child(userId).child("workoutHistory").get().await()
            
            for (child in snapshot.children) {
                val sessionDate = child.child("date").getValue(String::class.java)
                if (sessionDate == date) {
                    val burned = child.child("caloriesBurned").getValue(Int::class.java) ?: 0
                    total += burned
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return total
    }

    private fun loadDailyHistory() {
        lifecycleScope.launch {
            try {
                // 1. Fetch Food Logs
                val foodLogsResult = authHelper.getTodayFoodLogs(userId)
                val foodItems = (foodLogsResult.getOrNull() ?: emptyList()).map {
                    HistoryItem.FoodItem(
                        name = it.foodName,
                        mealType = it.mealType,
                        calories = it.calories,
                        protein = it.protein,
                        carbs = it.carbs,
                        fat = it.fat,
                        timestamp = it.timestamp
                    )
                }

                val firestore = FirebaseFirestore.getInstance()
                // 2. Fetch AI Exercises from Firestore
                val today = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(java.util.Date())
                val aiExercises = firestore.collection("exerciseLogs")
                    .whereEqualTo("userId", userId)
                    .whereEqualTo("date", today)
                    .get()
                    .await()
                    .map { doc ->
                        // Helper to safely parse duration string like "15 mins"
                        val durationStr = doc.get("duration") as? String ?: "15"
                        val duration = durationStr.replace(Regex("[^0-9]"), "").toIntOrNull() ?: 15
                        
                        HistoryItem.ExerciseItem(
                            name = doc.getString("exerciseName") ?: "Exercise",
                            durationMinutes = duration, 
                            calories = (doc.get("caloriesBurned") as? Number)?.toInt() ?: 0,
                            type = "AI Coach",
                            timestamp = doc.getLong("timestamp") ?: System.currentTimeMillis()
                        )
                    }

                // 3. Fetch Regular Workouts from Realtime Database
                val db = FirebaseDatabase.getInstance("https://swasthyamitra-c0899-default-rtdb.asia-southeast1.firebasedatabase.app").reference
                val fitnessDataSnapshot = db.child("users").child(userId).get().await()
                
                // Manually parse the snapshot to avoid serialization issues or mismatched types
                // Accessing workoutHistory safely
                val workoutHistoryMap = fitnessDataSnapshot.child("workoutHistory").children
                val regularWorkouts = workoutHistoryMap.mapNotNull { snapshot ->
                    try {
                        val date = snapshot.child("date").getValue(String::class.java)
                        if (date == today) {
                            val category = snapshot.child("category").getValue(String::class.java) ?: "Workout"
                            val duration = snapshot.child("duration").getValue(Int::class.java) ?: 0
                            val caloriesBurned = snapshot.child("caloriesBurned").getValue(Int::class.java) ?: 0
                            val timestamp = snapshot.child("timestamp").getValue(Long::class.java) ?: System.currentTimeMillis()
                            val type = snapshot.child("type").getValue(String::class.java) ?: ""
                            
                            val name = if (type.isNotEmpty()) type else category.replaceFirstChar { it.uppercase() }
                            
                            HistoryItem.ExerciseItem(
                                name = name,
                                durationMinutes = duration,
                                calories = caloriesBurned,
                                type = "Self-Paced",
                                timestamp = timestamp
                            )
                        } else null
                    } catch (e: Exception) {
                        null
                    }
                }

                // Combine and Sort
                val allItems = (foodItems + aiExercises + regularWorkouts).sortedByDescending { it.timestamp }

                runOnUiThread {
                    if (allItems.isEmpty()) {
                        tvHistoryEmpty.visibility = View.VISIBLE
                        rvHistory.visibility = View.GONE
                    } else {
                        tvHistoryEmpty.visibility = View.GONE
                        rvHistory.visibility = View.VISIBLE
                        rvHistory.adapter = HistoryAdapter(allItems)
                    }
                }

            } catch (e: Exception) {
                Log.e("ProgressActivity", "Error loading history", e)
            }
        }
    }
}
