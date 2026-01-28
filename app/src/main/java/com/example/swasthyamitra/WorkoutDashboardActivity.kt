package com.example.swasthyamitra

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.example.swasthyamitra.models.WorkoutVideoRepository
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.launch
import com.example.swasthyamitra.models.WorkoutVideo

class WorkoutDashboardActivity : AppCompatActivity() {

    private lateinit var authHelper: FirebaseAuthHelper
    private lateinit var stepManager: StepManager
    private var userId: String = ""
    
    private lateinit var tvSteps: TextView
    private lateinit var tvCaloriesBurned: TextView
    private lateinit var tvRecommendationText: TextView
    private lateinit var tvCalorieStatus: TextView
    private lateinit var cvVideoRecommendation: CardView
    private lateinit var tvVideoTitle: TextView
    private lateinit var btnBackWorkout: View 

    private lateinit var llVideoListContainer: android.widget.LinearLayout
    
    // Stats UI
    private lateinit var tvTotalWorkouts: TextView
    private lateinit var tvWorkoutStreak: TextView
    private lateinit var tvTotalMinutes: TextView
    
    private val startedVideoIds = mutableSetOf<String>()
    
    // Cache for recommendations to prevent refresh on Resume
    private var currentRecommendations: List<WorkoutVideo> = emptyList()
        
    private var consumedCalories: Int = 0
    private var burnedCalories: Double = 0.0
    private var currentSteps: Int = 0
    private var goalType: String = ""
    private var targetBase: Int = 2200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_dashboard)

        val application = application as UserApplication
        authHelper = application.authHelper
        userId = authHelper.getCurrentUser()?.uid ?: ""

        initViews()
        
        stepManager = StepManager(this) { steps, burned ->
            runOnUiThread {
                tvSteps.text = steps.toString()
                tvCaloriesBurned.text = String.format("%.1f kcal burned", burned)
                burnedCalories = burned
                currentSteps = steps
                updateAIRecommendation()
            }
        }
        stepManager.start()

        fetchUserData()
        setupListeners()
    }

    private fun initViews() {
        tvSteps = findViewById(R.id.tvStepsWorkout)
        tvCaloriesBurned = findViewById(R.id.tvCaloriesBurned)
        tvRecommendationText = findViewById(R.id.tvRecommendationText)
        tvCalorieStatus = findViewById(R.id.tvCalorieStatus)
        
        llVideoListContainer = findViewById(R.id.llVideoListContainer)
        btnBackWorkout = findViewById(R.id.btnBackWorkout)
        
        tvTotalWorkouts = findViewById(R.id.tvTotalWorkouts)
        tvWorkoutStreak = findViewById(R.id.tvWorkoutStreak)
        tvTotalMinutes = findViewById(R.id.tvTotalMinutes)
    }

    private fun setupListeners() {
        findViewById<View>(R.id.cvGamification).setOnClickListener {
            startActivity(Intent(this, GamificationActivity::class.java))
        }

        findViewById<View>(R.id.cvInsights).setOnClickListener {
            startActivity(Intent(this, InsightsActivity::class.java))
        }

        findViewById<View>(R.id.cvSafety).setOnClickListener {
            startActivity(Intent(this, SafetyActivity::class.java))
        }

        btnBackWorkout.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        if (userId.isNotEmpty()) {
            fetchUserData()
        }
    }

    private fun fetchUserData() {
        lifecycleScope.launch {
            authHelper.getUserGoal(userId).onSuccess { goal ->
                goalType = goal["goalType"] as? String ?: "Maintenance"
                val dailyTarget = goal["dailyCalories"] as? Double
                if (dailyTarget != null && dailyTarget > 0) {
                    targetBase = dailyTarget.toInt()
                } else {
                    targetBase = when (goalType.lowercase()) {
                        "weight loss" -> 1800
                        "weight gain" -> 2800
                        else -> 2200
                    }
                }
                updateAIRecommendation()
            }

            authHelper.getTodayCalories(userId).onSuccess { calories ->
                consumedCalories = calories
                updateAIRecommendation()
            }
            
            checkWorkoutStatusAndStats()
        }
    }

    private fun checkWorkoutStatusAndStats() {
        val db = FirebaseDatabase.getInstance("https://swasthyamitra-c0899-default-rtdb.asia-southeast1.firebasedatabase.app").reference
        val userRef = db.child("users").child(userId)
        val today = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(java.util.Date())

        userRef.get().addOnSuccessListener { snapshot ->
            val data = snapshot.getValue(FitnessData::class.java) ?: FitnessData()
            
            runOnUiThread {
                tvTotalWorkouts.text = data.workoutHistory.size.toString()
                tvWorkoutStreak.text = data.streak.toString()
                tvTotalMinutes.text = data.totalWorkoutMinutes.toString()
                
                // Refresh list to update "Completed" status on buttons
                updateAIRecommendation() 
            }
        }
    }

    private fun handleWorkoutCompletion(video: WorkoutVideo, btnComplete: Button) {
        btnComplete.isEnabled = false 
        btnComplete.text = "..."

        val dbUrl = "https://swasthyamitra-c0899-default-rtdb.asia-southeast1.firebasedatabase.app"
        val db = FirebaseDatabase.getInstance(dbUrl).reference
        val userRef = db.child("users").child(userId)
        val today = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(java.util.Date())
        
        userRef.get().addOnSuccessListener { snapshot ->
            val data = snapshot.getValue(FitnessData::class.java) ?: FitnessData()
            
            val session = WorkoutSession(
                id = java.util.UUID.randomUUID().toString(),
                date = today,
                category = video.category,
                videoId = video.videoId,
                duration = video.durationMinutes,
                completed = true,
                timestamp = System.currentTimeMillis()
            )

            val updatedHistory = data.workoutHistory.toMutableMap()
            updatedHistory[session.id] = session
            val updatedCompletion = data.completionHistory.toMutableMap()
            updatedCompletion[today] = true

            val updatedData = data.copy(
                xp = data.xp + 100,
                completionHistory = updatedCompletion,
                workoutHistory = updatedHistory,
                totalWorkoutMinutes = data.totalWorkoutMinutes + session.duration,
                lastActiveDate = today
            )
            
            userRef.setValue(updatedData)
                .addOnSuccessListener {
                    runOnUiThread {
                        btnComplete.text = "Done ✅"
                        btnComplete.alpha = 0.6f
                        tvTotalWorkouts.text = updatedHistory.size.toString()
                        tvTotalMinutes.text = updatedData.totalWorkoutMinutes.toString()
                        Toast.makeText(this, "Workout saved! +100 XP", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener { e ->
                    btnComplete.isEnabled = true
                    btnComplete.text = "Complete"
                    Toast.makeText(this, "Failed: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun updateAIRecommendation() {
        val netCalories = consumedCalories - burnedCalories
        val diff = netCalories - targetBase

        val statusText: String
        val recommendation: String

        val diffAbs = Math.abs(diff).toInt()
        val calorieStatus = when {
            diff > 100 -> "High"
            diff < -100 -> "Low"
            else -> "Balanced"
        }

        statusText = if (calorieStatus == "Balanced") "Status: Calories on target" 
                     else "Status: $diffAbs kcal ${if(diff>0) "above" else "below"} target"
        
        recommendation = "Based on your goal, we have selected these workouts for you."

        tvCalorieStatus.text = statusText
        tvRecommendationText.text = recommendation
        
        updateVideoList(calorieStatus)
    }

    private fun updateVideoList(calorieStatus: String) {
        if (goalType.isEmpty()) return
        
        // Only generate new recommendations if we don't have any yet
        if (currentRecommendations.isEmpty()) {
            val intensity = "Moderate" 
            currentRecommendations = WorkoutVideoRepository.getSmartRecommendation(goalType, calorieStatus, intensity)
            // Only clear started IDs when we GENUINELY generate a new set of videos
            startedVideoIds.clear()
        }
        
        val videos = currentRecommendations
        
        runOnUiThread {
            llVideoListContainer.removeAllViews()
            
            val inflater = android.view.LayoutInflater.from(this)
            
            for (video in videos) {
                val itemView = inflater.inflate(R.layout.item_workout_video_card, llVideoListContainer, false)
                
                val tvTitle = itemView.findViewById<TextView>(R.id.tvVideoTitle)
                val tvType = itemView.findViewById<TextView>(R.id.tvObjType)
                val tvDur = itemView.findViewById<TextView>(R.id.tvDuration)
                val btnStart = itemView.findViewById<Button>(R.id.btnStartWorkout)
                val btnComplete = itemView.findViewById<Button>(R.id.btnCompleteWorkout)
                
                tvTitle.text = video.title
                // explanation removed
                tvType.text = video.category.uppercase()
                tvDur.text = "• ${video.durationMinutes} min"
                
                // Visual state based on whether it was started
                if (startedVideoIds.contains(video.videoId)) {
                    btnComplete.alpha = 1.0f
                    btnComplete.isEnabled = true
                } else {
                    btnComplete.alpha = 0.5f 
                    // We don't disable isEnabled here to let them click and see the Toast
                }
                
                btnStart.setOnClickListener {
                    startedVideoIds.add(video.videoId)
                    btnComplete.alpha = 1.0f // Enable visually
                    btnComplete.isEnabled = true
                    
                    val intent = Intent(Intent.ACTION_VIEW, android.net.Uri.parse("https://www.youtube.com/watch?v=${video.videoId}"))
                    startActivity(intent)
                }
                
                btnComplete.setOnClickListener {
                    if (startedVideoIds.contains(video.videoId)) {
                         handleWorkoutCompletion(video, btnComplete)
                    } else {
                        Toast.makeText(this, "Please start the video properly 🎥", Toast.LENGTH_SHORT).show()
                    }
                }
                
                llVideoListContainer.addView(itemView)
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        stepManager.stop()
    }
}
