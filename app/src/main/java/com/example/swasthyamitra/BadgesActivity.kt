package com.example.swasthyamitra

import android.os.Bundle
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

data class Badge(
    val id: String,
    val title: String,
    val description: String,
    val icon: String,
    val isUnlocked: Boolean
)

class BadgesActivity : AppCompatActivity() {

    private lateinit var authHelper: FirebaseAuthHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var tvTotalXp: TextView
    private var userId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_badges)

        authHelper = FirebaseAuthHelper(this)
        userId = authHelper.getCurrentUser()?.uid ?: ""

        setupViews()
        loadData()
    }

    private fun setupViews() {
        tvTotalXp = findViewById(R.id.tvTotalXp)
        recyclerView = findViewById(R.id.badgesRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            finish()
        }
    }

    private fun loadData() {
        val db = FirebaseDatabase.getInstance("https://swasthyamitra-c0899-default-rtdb.asia-southeast1.firebasedatabase.app").reference
        val userRef = db.child("users").child(userId)

        userRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.getValue(FitnessData::class.java) ?: FitnessData()
                updateUI(data)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@BadgesActivity, "Failed to load badges", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun updateUI(data: FitnessData) {
        tvTotalXp.text = "${data.xp} XP"

        val workoutCount = data.workoutHistory.size
        // Simple logic to count unique days could be better but size is okay proxy for now
        
        val allBadges = listOf(
            Badge("1", "First Step", "Complete your first workout", "👟", workoutCount >= 1),
            Badge("2", "On Fire", "Reach a 3-day streak", "🔥", data.streak >= 3),
            Badge("3", "Unstoppable", "Reach a 7-day streak", "🚀", data.streak >= 7),
            Badge("4", "Dedicated", "Complete 10 workouts", "💪", workoutCount >= 10),
            Badge("5", "Warrior", "Complete 30 workouts", "⚔️", workoutCount >= 30),
            Badge("6", "Centurion", "Earn 1000 XP", "💯", data.xp >= 1000),
            Badge("7", "Master", "Earn 5000 XP", "👑", data.xp >= 5000),
            Badge("8", "Consistent", "Reach a 'High' calorie status", "⚖️", false) // Placeholder logic
        )

        recyclerView.adapter = BadgeAdapter(allBadges)
    }

    inner class BadgeAdapter(private val badges: List<Badge>) : RecyclerView.Adapter<BadgeAdapter.ViewHolder>() {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val container: View = view.findViewById(R.id.badgeContainer)
            val icon: TextView = view.findViewById(R.id.tvBadgeIcon)
            val title: TextView = view.findViewById(R.id.tvBadgeTitle)
            val desc: TextView = view.findViewById(R.id.tvBadgeDesc)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_badge, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val badge = badges[position]
            
            holder.title.text = badge.title
            holder.desc.text = badge.description
            
            if (badge.isUnlocked) {
                holder.icon.text = badge.icon
                holder.icon.alpha = 1.0f
                holder.title.setTextColor(Color.parseColor("#212121"))
                holder.container.setBackgroundColor(Color.WHITE)
            } else {
                holder.icon.text = "🔒"
                holder.icon.alpha = 0.5f
                holder.title.setTextColor(Color.parseColor("#9E9E9E"))
                holder.desc.text = "Locked"
                holder.container.setBackgroundColor(Color.parseColor("#F5F5F5"))
            }
        }

        override fun getItemCount() = badges.size
    }
}
