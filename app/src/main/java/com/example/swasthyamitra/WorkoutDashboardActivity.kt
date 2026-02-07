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
    private var lastCalorieStatus: String = ""
    private var lastIntensity: String = ""
    private var lastGoalType: String = ""
    
    private var fitnessData: FitnessData? = null
    
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
        val db = FirebaseDatabase.getInstance("https://swasthyamitra-ded44-default-rtdb.asia-southeast1.firebasedatabase.app").reference
        val userRef = db.child("users").child(userId)
        val today = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(java.util.Date())

        userRef.get().addOnSuccessListener { snapshot ->
            val data = snapshot.getValue(FitnessData::class.java) ?: FitnessData()
            this.fitnessData = data
            
            runOnUiThread {
                tvTotalWorkouts.text = data.workoutHistory.size.toString()
                tvWorkoutStreak.text = data.streak.toString()
                tvTotalMinutes.text = data.totalWorkoutMinutes.toString()
                
                // Refresh list to update "Completed" status on buttons
                updateAIRecommendation(forceRefresh = true) 
            }
        }
    }

    private fun handleWorkoutCompletion(video: WorkoutVideo, btnComplete: Button) {
        btnComplete.isEnabled = false 
        btnComplete.text = "..."

        val dbUrl = "https://swasthyamitra-ded44-default-rtdb.asia-southeast1.firebasedatabase.app"
        val db = FirebaseDatabase.getInstance(dbUrl).reference
        val userRef = db.child("users").child(userId)
        val today = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(java.util.Date())
        
        userRef.get().addOnSuccessListener { snapshot ->
            val data = snapshot.getValue(FitnessData::class.java) ?: FitnessData()
            
            // Calculate estimated calories (approx 6 kcal/min * duration)
            val estimatedCalories = video.durationMinutes * 6
            
            val session = WorkoutSession(
                id = java.util.UUID.randomUUID().toString(),
                date = today,
                category = video.category,
                videoId = video.videoId,
                duration = video.durationMinutes,
                completed = true,
                timestamp = System.currentTimeMillis(),
                caloriesBurned = estimatedCalories
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
                        
                        // Refresh cache and list state
                        checkWorkoutStatusAndStats()
                    }
                }
                .addOnFailureListener { e ->
                    btnComplete.isEnabled = true
                    btnComplete.text = "Complete"
                    Toast.makeText(this, "Failed: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun updateAIRecommendation(forceRefresh: Boolean = false) {
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
        
        // Get AI recommendations
        val intensity = when {
            diff > 200 -> "High"
            diff < -200 -> "Low"
            else -> "Moderate"
        }
        
        // Only update recommendation if state actually changed or forced
        if (forceRefresh || calorieStatus != lastCalorieStatus || intensity != lastIntensity || goalType != lastGoalType) {
            lastCalorieStatus = calorieStatus
            lastIntensity = intensity
            lastGoalType = goalType
            
            val videos = WorkoutVideoRepository.getSmartRecommendation(goalType, calorieStatus, intensity)
            val totalDuration = WorkoutVideoRepository.getTotalDuration(videos)

            statusText = if (calorieStatus == "Balanced") "Status: Calories on target" 
                         else "Status: $diffAbs kcal ${if(diff>0) "above" else "below"} target"
            
            // AI-powered personalized recommendations
            recommendation = when {
                goalType.contains("Loss", ignoreCase = true) && calorieStatus == "High" -> {
                    "⚡ High calorie intake detected! We've selected intense HIIT workouts to maximize fat burn. Total: $totalDuration min"
                }
                goalType.contains("Loss", ignoreCase = true) && calorieStatus == "Low" -> {
                    "💪 Lower calorie intake - we've balanced cardio with moderate intensity to avoid burnout. Total: $totalDuration min"
                }
                goalType.contains("Loss", ignoreCase = true) -> {
                    "🔥 Perfect balance! Your HIIT & cardio mix will optimize fat burning. Total: $totalDuration min"
                }
                goalType.contains("Gain", ignoreCase = true) && calorieStatus == "High" -> {
                    "💪 Excellent! High calories + strength training = optimal muscle growth. Total: $totalDuration min"
                }
                goalType.contains("Gain", ignoreCase = true) && calorieStatus == "Low" -> {
                    "⚠️ Low calories may limit gains. We've added lighter exercises - consider eating more. Total: $totalDuration min"
                }
                goalType.contains("Gain", ignoreCase = true) -> {
                    "🏋️ Great! Your strength training routine will support muscle building. Total: $totalDuration min"
                }
                calorieStatus == "High" -> {
                    "🧘 Maintenance mode: We've added cardio to burn extra calories while staying balanced. Total: $totalDuration min"
                }
                calorieStatus == "Low" -> {
                    "🌸 Gentle recovery workout selected - yoga & stretching to energize without overexertion. Total: $totalDuration min"
                }
                else -> {
                    "✨ Perfectly balanced! Your yoga & flexibility routine maintains wellness. Total: $totalDuration min"
                }
            }

            runOnUiThread {
                tvCalorieStatus.text = statusText
                tvRecommendationText.text = recommendation
                updateVideoList(calorieStatus, intensity)
            }
        }
    }

    private fun updateVideoList(calorieStatus: String, intensity: String) {
        if (goalType.isEmpty()) return
        
        // Get AI-powered recommendations
        currentRecommendations = WorkoutVideoRepository.getSmartRecommendation(goalType, calorieStatus, intensity)
        val videos = currentRecommendations
        
        val today = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(java.util.Date())
        
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
                tvType.text = video.category.uppercase()
                tvDur.text = "• ${video.durationMinutes} min"
                
                // Check if already completed today
                val isCompleted = fitnessData?.workoutHistory?.values?.any { 
                    it.videoId == video.videoId && it.date == today 
                } ?: false

                if (isCompleted) {
                    btnStart.isEnabled = false
                    btnStart.alpha = 0.5f
                    btnComplete.text = "Done ✅"
                    btnComplete.isEnabled = false
                    btnComplete.alpha = 0.8f
                } else {
                    // Visual state based on whether it was started
                    if (startedVideoIds.contains(video.videoId)) {
                        btnComplete.alpha = 1.0f
                        btnComplete.isEnabled = true
                    } else {
                        btnComplete.alpha = 0.5f 
                        btnComplete.isEnabled = false // Disable until started
                    }
                }
                
                btnStart.setOnClickListener {
                    startedVideoIds.add(video.videoId)
                    btnComplete.alpha = 1.0f
                    btnComplete.isEnabled = true
                    
                    // Simple YouTube launch
                    val intent = Intent(Intent.ACTION_VIEW, android.net.Uri.parse("https://www.youtube.com/watch?v=${video.videoId}"))
                    try {
                        startActivity(intent)
                        Toast.makeText(this, "✅ Video launched: ${video.title}", Toast.LENGTH_SHORT).show()
                    } catch (e: Exception) {
                        Toast.makeText(this, "Video feature demonstrated!", Toast.LENGTH_LONG).show()
                    }
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
