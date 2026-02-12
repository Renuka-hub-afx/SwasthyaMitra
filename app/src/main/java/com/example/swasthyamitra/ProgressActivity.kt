package com.example.swasthyamitra

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.*


class ProgressActivity : AppCompatActivity() {

    private lateinit var authHelper: FirebaseAuthHelper
    private var userId: String = ""

    // UI Components
    private lateinit var weeklyCaloriesText: TextView
    private lateinit var weeklyWorkoutsText: TextView
    private lateinit var currentStreakText: TextView
    private lateinit var longestStreakText: TextView
    private lateinit var rvHistory: RecyclerView
    private lateinit var tvHistoryEmpty: TextView

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

    private fun initializeViews() {
        weeklyCaloriesText = findViewById(R.id.weeklyCaloriesText)
        weeklyWorkoutsText = findViewById(R.id.weeklyWorkoutsText)
        currentStreakText = findViewById(R.id.currentStreakText)
        longestStreakText = findViewById(R.id.longestStreakText)
        
        rvHistory = findViewById(R.id.rv_history)
        tvHistoryEmpty = findViewById(R.id.tv_history_empty)
        rvHistory.layoutManager = LinearLayoutManager(this)

        findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar).setNavigationOnClickListener { finish() }
        
        findViewById<View>(R.id.viewDetailedReportButton)?.setOnClickListener { showChartsView() }
        
        // Progress Roadmap Click Listeners
        findViewById<View>(R.id.roadmapItemWeight)?.setOnClickListener { 
            startActivity(Intent(this, WeightProgressActivity::class.java))
        }
        findViewById<View>(R.id.roadmapItemBadges)?.setOnClickListener { 
            startActivity(Intent(this, BadgesActivity::class.java))
        }
        findViewById<View>(R.id.roadmapItemHistory)?.setOnClickListener { 
            startActivity(Intent(this, HistoryActivity::class.java))
        }
        findViewById<View>(R.id.historyCard)?.setOnClickListener { 
            startActivity(Intent(this, HistoryActivity::class.java))
        }
    }

    private fun loadProgressData() {
        // 1. Load User Statistics (Streaks, Workouts, Calories) from Firebase Realtime Database
        val db = FirebaseDatabase.getInstance("https://swasthyamitra-ded44-default-rtdb.asia-southeast1.firebasedatabase.app").reference
        val userRef = db.child("users").child(userId)

        // Use addValueEventListener for real-time updates
        userRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.getValue(FitnessData::class.java) ?: FitnessData()
                
                runOnUiThread {
                    // Update Streak
                    currentStreakText.text = "${data.streak} days"
                    longestStreakText.text = "${data.streak} days" 

                    // Calculate Weekly Stats (Workouts & Calories Burned)
                    val (workouts, calories) = calculateWeeklyStats(data.workoutHistory)
                    
                    weeklyWorkoutsText.text = "$workouts"
                    weeklyCaloriesText.text = "$calories"

                    // Update History List
                    updateHistoryUI(data.workoutHistory)
                }
            }

            override fun onCancelled(error: DatabaseError) {
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
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

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

    private fun updateHistoryUI(history: Map<String, WorkoutSession>) {
        if (history.isEmpty()) {
            rvHistory.visibility = View.GONE
            tvHistoryEmpty.visibility = View.VISIBLE
            return
        }

        // Sort by timestamp descending (last 30 days logic is implicit if data is recent)
        val sortedHistory = history.values.sortedByDescending { session ->
            if (session.timestamp > 0) session.timestamp 
            else SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(session.date)?.time ?: 0L
        }

        rvHistory.visibility = View.VISIBLE
        tvHistoryEmpty.visibility = View.GONE
        rvHistory.adapter = HistoryItemAdapter(sortedHistory)
    }

    private inner class HistoryItemAdapter(private val sessions: List<WorkoutSession>) : RecyclerView.Adapter<HistoryItemAdapter.ViewHolder>() {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val date: TextView = view.findViewById(R.id.tvHistoryDate)
            val title: TextView = view.findViewById(R.id.tvHistoryTitle)
            val details: TextView = view.findViewById(R.id.tvHistoryDetails)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val session = sessions[position]
            
            // Format Date
            val dateObj = if (session.timestamp > 0) java.util.Date(session.timestamp) 
                          else SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(session.date)
            
            val dateStr = if (dateObj != null) {
                SimpleDateFormat("EEEE, MMM dd", Locale.getDefault()).format(dateObj)
            } else {
                session.date
            }
            
            holder.date.text = dateStr
            holder.title.text = session.category.replaceFirstChar { it.uppercase() } + " Workout"
            
            // Calculate calories if missing
            val calories = if (session.caloriesBurned > 0) session.caloriesBurned else session.duration * 6
            
            holder.details.text = "${session.duration} min • $calories kcal"
        }

        override fun getItemCount() = sessions.size
    }

    private fun showChartsView() {
        // Check if DetailedReportActivity exists, else fix or use placeholder
        try {
            startActivity(Intent(this, DetailedReportActivity::class.java))
        } catch (e: Exception) {
            Toast.makeText(this, "Detailed Report coming soon!", Toast.LENGTH_SHORT).show()
        }
    }
}
