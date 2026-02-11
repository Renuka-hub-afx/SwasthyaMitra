package com.example.swasthyamitra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.example.swasthyamitra.FitnessData
import com.example.swasthyamitra.WorkoutSession
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.Locale

class HistoryActivity : AppCompatActivity() {

    private lateinit var authHelper: FirebaseAuthHelper
    private lateinit var recyclerView: RecyclerView
    private var userId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        authHelper = FirebaseAuthHelper(this)
        userId = authHelper.getCurrentUser()?.uid ?: ""

        setupViews()
        loadData()
    }

    private fun setupViews() {
        recyclerView = findViewById(R.id.historyRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            finish()
        }
    }

    private fun loadData() {
        if (userId.isEmpty()) return

        val db = FirebaseDatabase.getInstance("https://swasthyamitra-c0899-default-rtdb.asia-southeast1.firebasedatabase.app").reference
        val userRef = db.child("users").child(userId)

        userRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.getValue(FitnessData::class.java) ?: FitnessData()
                updateUI(data.workoutHistory)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@HistoryActivity, "Failed to load history", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun updateUI(history: Map<String, WorkoutSession>) {
        // Sort by timestamp descending
        val sortedHistory = history.values.sortedByDescending { session ->
            if (session.timestamp > 0) session.timestamp 
            else SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(session.date)?.time ?: 0L
        }

        recyclerView.adapter = HistoryAdapter(sortedHistory)
    }

    inner class HistoryAdapter(private val sessions: List<WorkoutSession>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

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
}
