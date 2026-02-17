package com.example.swasthyamitra

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swasthyamitra.models.StepSession
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Displays a history of GPS-enhanced step tracking sessions.
 * Reads from Firestore: users/{uid}/step_sessions
 */
class StepSessionHistoryActivity : AppCompatActivity() {

    private lateinit var rvSessions: RecyclerView
    private lateinit var tvEmptyState: TextView
    private lateinit var tvTotalSessions: TextView
    private lateinit var tvTotalStepsHistory: TextView
    private lateinit var tvTotalDistanceHistory: TextView

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val sessions = mutableListOf<StepSession>()
    private lateinit var adapter: SessionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step_session_history)

        initViews()
        adapter = SessionAdapter(sessions)
        rvSessions.layoutManager = LinearLayoutManager(this)
        rvSessions.adapter = adapter

        loadSessions()
    }

    private fun initViews() {
        rvSessions = findViewById(R.id.rvSessions)
        tvEmptyState = findViewById(R.id.tvEmptyState)
        tvTotalSessions = findViewById(R.id.tvTotalSessions)
        tvTotalStepsHistory = findViewById(R.id.tvTotalStepsHistory)
        tvTotalDistanceHistory = findViewById(R.id.tvTotalDistanceHistory)

        findViewById<View>(R.id.btnBackHistory).setOnClickListener { finish() }
    }

    private fun loadSessions() {
        val userId = auth.currentUser?.uid ?: return

        db.collection("users").document(userId)
            .collection("step_sessions")
            .orderBy("startTime", Query.Direction.DESCENDING)
            .limit(50)
            .get()
            .addOnSuccessListener { snapshot ->
                sessions.clear()
                var totalSteps = 0
                var totalDist = 0.0

                for (doc in snapshot.documents) {
                    val session = doc.toObject(StepSession::class.java) ?: continue
                    sessions.add(session.copy(id = doc.id))
                    totalSteps += session.validatedSteps
                    totalDist += session.totalDistanceMeters
                }

                adapter.notifyDataSetChanged()

                // Summary
                tvTotalSessions.text = sessions.size.toString()
                tvTotalStepsHistory.text = NumberFormat.getInstance().format(totalSteps)
                tvTotalDistanceHistory.text = String.format("%.1f", totalDist / 1000.0)

                tvEmptyState.visibility = if (sessions.isEmpty()) View.VISIBLE else View.GONE
                rvSessions.visibility = if (sessions.isEmpty()) View.GONE else View.VISIBLE

                Log.d("SessionHistory", "Loaded ${sessions.size} sessions")
            }
            .addOnFailureListener { e ->
                Log.e("SessionHistory", "Failed to load sessions", e)
                tvEmptyState.visibility = View.VISIBLE
                tvEmptyState.text = "Failed to load sessions"
            }
    }

    // -------- Adapter --------

    inner class SessionAdapter(
        private val items: List<StepSession>
    ) : RecyclerView.Adapter<SessionAdapter.VH>() {

        inner class VH(view: View) : RecyclerView.ViewHolder(view) {
            val tvDate: TextView = view.findViewById(R.id.tvSessionDate)
            val tvDuration: TextView = view.findViewById(R.id.tvSessionDuration)
            val tvSteps: TextView = view.findViewById(R.id.tvSessionSteps)
            val tvDistance: TextView = view.findViewById(R.id.tvSessionDistance)
            val tvCalories: TextView = view.findViewById(R.id.tvSessionCalories)
            val tvConfidence: TextView = view.findViewById(R.id.tvSessionConfidence)
            val viewConfDot: View = view.findViewById(R.id.viewSessionConfDot)
            val tvPace: TextView = view.findViewById(R.id.tvSessionPace)
            val tvSpeed: TextView = view.findViewById(R.id.tvSessionSpeed)
            val tvStride: TextView = view.findViewById(R.id.tvSessionStride)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_step_session, parent, false)
            return VH(view)
        }

        override fun onBindViewHolder(holder: VH, position: Int) {
            val s = items[position]

            // Date
            val dateFormat = SimpleDateFormat("MMM dd, yyyy · h:mm a", Locale.getDefault())
            holder.tvDate.text = dateFormat.format(Date(s.startTime))

            // Duration
            val durationMin = ((s.endTime - s.startTime) / 60_000).toInt()
            holder.tvDuration.text = if (durationMin >= 60) {
                "${durationMin / 60}h ${durationMin % 60}m"
            } else {
                "${durationMin} min"
            }

            // Steps
            holder.tvSteps.text = NumberFormat.getInstance().format(s.validatedSteps)

            // Distance
            val distKm = s.totalDistanceMeters / 1000.0
            holder.tvDistance.text = String.format("%.2f km", distKm)

            // Calories
            holder.tvCalories.text = s.caloriesBurned.toInt().toString()

            // Confidence
            val conf = s.confidenceScore.toInt()
            holder.tvConfidence.text = "$conf%"
            val confColor = when {
                conf >= 70 -> "#4CAF50"
                conf >= 40 -> "#FF9800"
                else -> "#F44336"
            }
            holder.viewConfDot.backgroundTintList = ColorStateList.valueOf(Color.parseColor(confColor))

            // Pace
            if (s.averagePace > 0 && s.averagePace < 100) {
                val paceMin = s.averagePace.toInt()
                val paceSec = ((s.averagePace - paceMin) * 60).toInt()
                holder.tvPace.text = "\u23F1 ${paceMin}'${String.format("%02d", paceSec)} /km"
            } else {
                holder.tvPace.text = "\u23F1 --"
            }

            // Speed
            holder.tvSpeed.text = "\uD83C\uDFC3 ${String.format("%.1f", s.averageSpeedKmh)} km/h"

            // Stride
            holder.tvStride.text = "\uD83D\uDC63 ${String.format("%.2f", s.averageStrideM)}m"
        }

        override fun getItemCount() = items.size
    }
}
