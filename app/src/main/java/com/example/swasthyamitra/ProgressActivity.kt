package com.example.swasthyamitra

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swasthyamitra.adapters.HistoryAdapter
import com.example.swasthyamitra.adapters.HistoryItem
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.example.swasthyamitra.GamificationActivity
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ProgressActivity : AppCompatActivity() {

    private lateinit var authHelper: FirebaseAuthHelper
    private var userId: String = ""

    // UI Components for Original Dashboard
    private lateinit var weeklyCaloriesText: TextView
    private lateinit var weeklyWorkoutsText: TextView
    private lateinit var currentStreakText: TextView
    private lateinit var longestStreakText: TextView
    private lateinit var rvHistory: RecyclerView
    private lateinit var tvHistoryEmpty: TextView

    // Data
    private var weeklyStats: List<DailyStat> = emptyList()

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
        setupListeners()
        loadData()
    }

    private fun initializeViews() {
        weeklyCaloriesText = findViewById(R.id.weeklyCaloriesText)
        weeklyWorkoutsText = findViewById(R.id.weeklyWorkoutsText)
        currentStreakText = findViewById(R.id.currentStreakText)
        longestStreakText = findViewById(R.id.longestStreakText)
        
        rvHistory = findViewById(R.id.rv_history)
        tvHistoryEmpty = findViewById(R.id.tv_history_empty)
        rvHistory.layoutManager = LinearLayoutManager(this)

        findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar).apply {
            setNavigationOnClickListener { finish() }
        }
    }

    private fun setupListeners() {
        findViewById<View>(R.id.row_weight_charts).setOnClickListener {
             // Open Insights Activity as it is the new home for charts
             try {
                startActivity(Intent(this, InsightsActivity::class.java))
             } catch (e: Exception) {
                 Toast.makeText(this, "Charts not ready", Toast.LENGTH_SHORT).show()
             }
        }
        
        findViewById<View>(R.id.row_achievements).setOnClickListener {
             startActivity(Intent(this, GamificationActivity::class.java))
        }

        findViewById<View>(R.id.viewDetailedReportButton).setOnClickListener {
             showHistoryDialog()
        }
    }

    private fun loadData() {
        lifecycleScope.launch {
            try {
                weeklyStats = getWeeklyStats()
                updateDashboardUI(weeklyStats)
                
                // Load Daily History list for the bottom card
                val historyItems = loadRecentHistoryItems(1) // Just today for the main screen log
                setupHistoryList(historyItems)
                
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@ProgressActivity, "Error loading data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateDashboardUI(stats: List<DailyStat>) {
        val totalCalories = stats.sumOf { it.calories }
        val sessions = stats.count { it.workoutCalories > 0 }
        
        weeklyCaloriesText.text = "$totalCalories kcal"
        weeklyWorkoutsText.text = "$sessions"
        
        // Calculate streak
        val streak = calculateStreak(stats)
        currentStreakText.text = "$streak days"
        
        // Best Streak (Placeholder or fetch from DB)
        longestStreakText.text = "$streak days" // Using current for now
    }

    private fun calculateStreak(stats: List<DailyStat>): Int {
        var streak = 0
        // Iterate reversed (Today -> Backwards)
        for (i in stats.indices.reversed()) {
            if (stats[i].calories > 0 || stats[i].workoutCalories > 0) {
                streak++
            } else {
                // If it's today and 0, dont break yet? Logic: strictly consecutive days
                // If today is empty, streak is 0 unless we count yesterday.
                // Simple logic: consecutive days with activity ending at today or yesterday
                if (i == stats.lastIndex) continue // Skip today if empty? No, strict streak.
                break 
            }
        }
        return streak
    }

    private fun setupHistoryList(items: List<HistoryItem>) {
        if (items.isEmpty()) {
            tvHistoryEmpty.visibility = View.VISIBLE
            rvHistory.visibility = View.GONE
        } else {
            tvHistoryEmpty.visibility = View.GONE
            rvHistory.visibility = View.VISIBLE
            rvHistory.adapter = HistoryAdapter(items)
        }
    }

    // Data Structures
    data class DailyStat(val date: String, val calories: Int, val workoutCalories: Int)

    // Data Fetching Helper
    private suspend fun getWeeklyStats(): List<DailyStat> {
        return withContext(Dispatchers.IO) {
            val stats = mutableListOf<DailyStat>()
            val calendar = Calendar.getInstance()
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            
            // 1. Prepare dates
            val dates = mutableListOf<String>()
            val cal = Calendar.getInstance()
            cal.add(Calendar.DAY_OF_YEAR, -6)
            for (i in 0 until 7) {
                dates.add(dateFormat.format(cal.time))
                cal.add(Calendar.DAY_OF_YEAR, 1)
            }
            
            val foodLogs = authHelper.getRecentFoodLogs(userId, 7)
            
            dates.forEach { dateKey ->
                val dailyCalories = foodLogs.filter { it.date == dateKey }.sumOf { it.calories }
                stats.add(DailyStat(dateKey, dailyCalories, 0)) // Placeholder 0 for workout cal for now
            }
            stats
        }
    }

    private suspend fun loadRecentHistoryItems(days: Int): List<HistoryItem> {
        return withContext(Dispatchers.IO) {
            val allItems = mutableListOf<HistoryItem>()
            val groupedItems = mutableListOf<HistoryItem>()
            
            val foodLogs = authHelper.getRecentFoodLogs(userId, days)
            foodLogs.forEach { log ->
                allItems.add(HistoryItem.FoodItem(
                    name = log.foodName,
                    mealType = log.mealType,
                    calories = log.calories,
                    protein = log.protein,
                    carbs = log.carbs,
                    fat = log.fat,
                    timestamp = log.timestamp
                ))
            }
            
            // Fetch Workouts (from RTDB)
            try {
               val db = FirebaseDatabase.getInstance("https://swasthyamitra-c0899-default-rtdb.asia-southeast1.firebasedatabase.app").reference
               val snapshot = db.child("users").child(userId).child("workoutHistory").get().await()
               val cutoff = System.currentTimeMillis() - (days * 24 * 60 * 60 * 1000L)
               
               snapshot.children.forEach { child ->
                   val ts = child.child("timestamp").getValue(Long::class.java) ?: 0L
                   if (ts >= cutoff) {
                       val category = child.child("category").getValue(String::class.java) ?: "Workout"
                       allItems.add(HistoryItem.ExerciseItem(
                           name = category.replaceFirstChar { it.uppercase() },
                           durationMinutes = child.child("duration").getValue(Int::class.java) ?: 0,
                           calories = child.child("caloriesBurned").getValue(Int::class.java) ?: 0,
                           type = "Workout",
                           timestamp = ts
                       ))
                   }
               }
            } catch (e: Exception) {}

            val sortedList = allItems.sortedByDescending { it.timestamp }
            
            if (sortedList.isNotEmpty()) {
                val dateFormat = SimpleDateFormat("EEEE, d MMM", Locale.getDefault())
                var currentDate = ""
                
                sortedList.forEach { item ->
                    val date = dateFormat.format(Date(item.timestamp))
                    if (date != currentDate) {
                        currentDate = date
                        groupedItems.add(HistoryItem.HeaderItem(date, item.timestamp))
                    }
                    groupedItems.add(item)
                }
            }
            groupedItems
        }
    }
    
    private fun showHistoryDialog() {
        val dialog = android.app.Dialog(this)
        dialog.setContentView(R.layout.dialog_history)
        dialog.window?.setBackgroundDrawable(android.graphics.drawable.ColorDrawable(android.graphics.Color.TRANSPARENT))
        val width = (resources.displayMetrics.widthPixels * 0.95).toInt()
        dialog.window?.setLayout(width, android.view.ViewGroup.LayoutParams.WRAP_CONTENT)
        
        val recyclerView: RecyclerView = dialog.findViewById(R.id.dialogHistoryRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        
        lifecycleScope.launch {
            try {
                val items = loadRecentHistoryItems(7) // Load last 7 days for report
                if (items.isEmpty()) {
                    Toast.makeText(this@ProgressActivity, "No recent history found", Toast.LENGTH_SHORT).show()
                } else {
                    recyclerView.adapter = HistoryAdapter(items)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@ProgressActivity, "Error fetching report details", Toast.LENGTH_SHORT).show()
            }
        }
        
        dialog.findViewById<android.widget.Button>(R.id.btnCloseHistory).setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}
