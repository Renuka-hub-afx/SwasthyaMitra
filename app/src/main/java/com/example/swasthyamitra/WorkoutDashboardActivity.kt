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

    // AI Exercise UI
    private lateinit var cardAiExercise: androidx.cardview.widget.CardView
    private lateinit var tvAiExerciseName: TextView
    private lateinit var ivAiExerciseGif: android.widget.ImageView
    private lateinit var tvAiExerciseReason: TextView
    private lateinit var tvAiExerciseCalories: TextView
    private lateinit var tvAiExerciseDuration: TextView
    private lateinit var btnAiExerciseDone: com.google.android.material.button.MaterialButton
    private lateinit var btnAiExerciseSkip: com.google.android.material.button.MaterialButton
    private lateinit var tvExerciseCounter: TextView
    
    private var currentAiExerciseList: List<com.example.swasthyamitra.ai.AIExerciseRecommendationService.ExerciseRec> = emptyList()
    private var currentExerciseIndex = 0

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
            }
        }
        stepManager.start()

        fetchUserData()
        setupListeners()
    }

    private fun initViews() {
        tvSteps = findViewById(R.id.tvStepsWorkout)
        tvCaloriesBurned = findViewById(R.id.tvCaloriesBurned)
        // tvRecommendationText = findViewById(R.id.tvRecommendationText) // Old
        // tvCalorieStatus = findViewById(R.id.tvCalorieStatus) // Old
        
        llVideoListContainer = findViewById(R.id.llVideoListContainer)
        btnBackWorkout = findViewById(R.id.btnBackWorkout)
        
        tvTotalWorkouts = findViewById(R.id.tvTotalWorkouts)
        tvWorkoutStreak = findViewById(R.id.tvWorkoutStreak)
        tvTotalMinutes = findViewById(R.id.tvTotalMinutes)

        // New AI Views
        cardAiExercise = findViewById(R.id.cardAiExercise)
        tvAiExerciseName = findViewById(R.id.tvAiExerciseName)
        ivAiExerciseGif = findViewById(R.id.ivAiExerciseGif)
        tvAiExerciseReason = findViewById(R.id.tvAiExerciseReason)
        tvAiExerciseCalories = findViewById(R.id.tvAiExerciseCalories)
        tvAiExerciseDuration = findViewById(R.id.tvAiExerciseDuration)
        btnAiExerciseDone = findViewById(R.id.btnAiExerciseDone)
        btnAiExerciseSkip = findViewById(R.id.btnAiExerciseSkip)
        tvExerciseCounter = findViewById(R.id.tvExerciseCounter)
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
        
        btnAiExerciseDone.setOnClickListener {
            markAiExerciseComplete()
        }
        
        btnAiExerciseSkip.setOnClickListener {
            skipToNextExercise()
        }
    }

    private fun markAiExerciseComplete() {
        val exercise = currentAiExerciseList[currentExerciseIndex] ?: return
        btnAiExerciseDone.isEnabled = false
        btnAiExerciseDone.text = "Saving..."

        val db = FirebaseDatabase.getInstance("https://swasthyamitra-ded44-default-rtdb.asia-southeast1.firebasedatabase.app").reference
        val userRef = db.child("users").child(userId)
        val today = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(java.util.Date())

        // 1. Log to Firestore (for Homepage stats)
        val logData = hashMapOf(
            "userId" to userId,
            "date" to today,
            "exerciseName" to exercise.name,
            "caloriesBurned" to exercise.estimatedCalories,
            "duration" to 15, // Default or parse from string
            "timestamp" to System.currentTimeMillis()
        )
        
        com.google.firebase.firestore.FirebaseFirestore.getInstance("renu")
            .collection("exercise_logs")
            .add(logData)
            .addOnSuccessListener {
                 // 2. Update RTDB (for Workout Dashboard stats)
                 updateRTDBStats(exercise, userRef, today)
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed to log: ${e.message}", Toast.LENGTH_SHORT).show()
                btnAiExerciseDone.isEnabled = true
                btnAiExerciseDone.text = "Mark as Done ✅"
            }
    }

    private fun updateRTDBStats(exercise: com.example.swasthyamitra.ai.AIExerciseRecommendationService.ExerciseRec, userRef: com.google.firebase.database.DatabaseReference, today: String) {
        userRef.get().addOnSuccessListener { snapshot ->
            val data = snapshot.getValue(FitnessData::class.java) ?: FitnessData()
            
            // Create a pseudo-session for RTDB consistency
            val sessionId = java.util.UUID.randomUUID().toString()
            val session = WorkoutSession(
                id = sessionId,
                date = today,
                category = "AI Comp.",
                videoId = "ai_${System.currentTimeMillis()}", // Dummy ID
                duration = 15,
                completed = true,
                timestamp = System.currentTimeMillis(),
                caloriesBurned = exercise.estimatedCalories
            )

            val updatedHistory = data.workoutHistory.toMutableMap()
            updatedHistory[sessionId] = session
            val updatedCompletion = data.completionHistory.toMutableMap()
            updatedCompletion[today] = true

            val updatedData = data.copy(
                xp = data.xp + 150, // Bonus XP for AI task
                completionHistory = updatedCompletion,
                workoutHistory = updatedHistory,
                totalWorkoutMinutes = data.totalWorkoutMinutes + session.duration,
                lastActiveDate = today
            )
            
            userRef.setValue(updatedData)
                .addOnSuccessListener {
                    runOnUiThread {
                        btnAiExerciseDone.text = "Completed! 🎉"
                        Toast.makeText(this, "Logged! +150 XP & ${exercise.estimatedCalories} kcal", Toast.LENGTH_SHORT).show()
                        
                        // Update UI Stats
                        tvTotalWorkouts.text = updatedHistory.size.toString()
                        tvTotalMinutes.text = updatedData.totalWorkoutMinutes.toString()
                        
                        // Disable button permanently for this session
                        btnAiExerciseDone.alpha = 0.6f
                        
                        // Refresh cache
                        this.fitnessData = updatedData
                        
                        // Auto-advance to next exercise after 1s delay
                        android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                            currentExerciseIndex++
                            displayCurrentExercise()
                        }, 1200)
                    }
                }
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
                updateVideoList("Balanced", "Moderate") // Load generic first
                loadAiRecommendation() // Load AI specific
            }
            
            checkWorkoutStatusAndStats()
        }
    }
    
    private fun loadAiRecommendation() {
        lifecycleScope.launch {
            try {
                // Get mood from Repo
                val moodRepo = com.example.swasthyamitra.repository.MoodRepository()
                var moodText = "Neutral"
                
                // Fetch recent mood safely
                val moodResult = moodRepo.getRecentMoods(userId ?: "", 1)
                moodResult.onSuccess { moods ->
                    if (moods.isNotEmpty()) {
                        moodText = moods[0].mood
                    }
                }

                val service = com.example.swasthyamitra.ai.AIExerciseRecommendationService.getInstance(this@WorkoutDashboardActivity)
                val result = service.getExerciseRecommendation(mood = moodText)
                
                result.onSuccess { recList ->
                    currentAiExerciseList = recList
                    currentExerciseIndex = 0
                    
                    if (recList.isNotEmpty()) {
                        runOnUiThread {
                           displayCurrentExercise()
                           cardAiExercise.visibility = View.VISIBLE 
                        }
                    } else {
                        runOnUiThread { cardAiExercise.visibility = View.GONE }
                    }

                }.onFailure {
                    runOnUiThread {
                        cardAiExercise.visibility = View.GONE
                    }
                }
            } catch (e: Exception) {
               // Ignore
            }
        }
    }
    
    private fun displayCurrentExercise() {
        if (currentExerciseIndex >= currentAiExerciseList.size) {
            // End of list
             cardAiExercise.visibility = View.GONE
             Toast.makeText(this, "All recommended exercises completed! Great job!", Toast.LENGTH_LONG).show()
             return
        }
        
        val rec = currentAiExerciseList[currentExerciseIndex]
        
        tvAiExerciseName.text = rec.name
        
        // Combine Reason + Benefits for a richer description with proper HTML formatting
        val richDescription = """
            ${rec.reason}
            <br/><br/>
            💡 <b>Why this is important:</b><br/>
            ${rec.benefits}
        """.trimIndent()
        
        // Use Html.fromHtml to render bold text properly
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            tvAiExerciseReason.text = android.text.Html.fromHtml(richDescription, android.text.Html.FROM_HTML_MODE_COMPACT)
        } else {
            @Suppress("DEPRECATION")
            tvAiExerciseReason.text = android.text.Html.fromHtml(richDescription)
        }
        
        tvAiExerciseCalories.text = "🔥 ~${rec.estimatedCalories} kcal"
        tvAiExerciseDuration.text = "⏱️ ${rec.recommendedDuration}"
        tvExerciseCounter.text = "Exercise ${currentExerciseIndex + 1} of ${currentAiExerciseList.size}"
        
        // Reset Done button state
        btnAiExerciseDone.isEnabled = true
        btnAiExerciseDone.text = "Mark as Done ✅"
        btnAiExerciseDone.alpha = 1.0f

        // Load GIF/Image from assets - hide if no image available
        if (rec.gifUrl.isNotEmpty()) {
            ivAiExerciseGif.visibility = View.VISIBLE
            
            // URL-encode the path for spaces and special characters
            val encodedPath = rec.gifUrl.replace(" ", "%20")
            val fullPath = "file:///android_asset/$encodedPath"
            
            android.util.Log.d("WorkoutDashboard", "Loading image: $fullPath (original: ${rec.gifUrl})")
            
            try {
                // Use different Glide request based on file type
                if (rec.gifUrl.endsWith(".gif", ignoreCase = true)) {
                    // Animated GIF
                    com.bumptech.glide.Glide.with(this)
                        .asGif()
                        .load(fullPath)
                        .listener(object : com.bumptech.glide.request.RequestListener<com.bumptech.glide.load.resource.gif.GifDrawable> {
                            override fun onLoadFailed(
                                e: com.bumptech.glide.load.engine.GlideException?,
                                model: Any?,
                                target: com.bumptech.glide.request.target.Target<com.bumptech.glide.load.resource.gif.GifDrawable>,
                                isFirstResource: Boolean
                            ): Boolean {
                                android.util.Log.e("WorkoutDashboard", "GIF load failed: $fullPath", e)
                                ivAiExerciseGif.visibility = View.GONE
                                return true
                            }
                            override fun onResourceReady(
                                resource: com.bumptech.glide.load.resource.gif.GifDrawable,
                                model: Any,
                                target: com.bumptech.glide.request.target.Target<com.bumptech.glide.load.resource.gif.GifDrawable>?,
                                dataSource: com.bumptech.glide.load.DataSource,
                                isFirstResource: Boolean
                            ): Boolean {
                                android.util.Log.d("WorkoutDashboard", "GIF loaded successfully: $fullPath")
                                return false
                            }
                        })
                        .into(ivAiExerciseGif)
                } else {
                    // Static image (PNG/JPG)
                    com.bumptech.glide.Glide.with(this)
                        .load(fullPath)
                        .listener(object : com.bumptech.glide.request.RequestListener<android.graphics.drawable.Drawable> {
                            override fun onLoadFailed(
                                e: com.bumptech.glide.load.engine.GlideException?,
                                model: Any?,
                                target: com.bumptech.glide.request.target.Target<android.graphics.drawable.Drawable>,
                                isFirstResource: Boolean
                            ): Boolean {
                                android.util.Log.e("WorkoutDashboard", "Image load failed: $fullPath", e)
                                ivAiExerciseGif.visibility = View.GONE
                                return true
                            }
                            override fun onResourceReady(
                                resource: android.graphics.drawable.Drawable,
                                model: Any,
                                target: com.bumptech.glide.request.target.Target<android.graphics.drawable.Drawable>?,
                                dataSource: com.bumptech.glide.load.DataSource,
                                isFirstResource: Boolean
                            ): Boolean {
                                android.util.Log.d("WorkoutDashboard", "Image loaded successfully: $fullPath")
                                return false
                            }
                        })
                        .into(ivAiExerciseGif)
                }
            } catch (e: Exception) {
                android.util.Log.e("WorkoutDashboard", "Exception loading image: ${e.message}", e)
                ivAiExerciseGif.visibility = View.GONE
            }
        } else {
            android.util.Log.d("WorkoutDashboard", "No gifUrl for exercise: ${rec.name}")
            // No image available - hide the ImageView completely
            ivAiExerciseGif.visibility = View.GONE
        }
    }

    private fun skipToNextExercise() {
        currentExerciseIndex++
        displayCurrentExercise()
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
                
                // Check if already completed today (simple check)
                 val isDoneToday = data.workoutHistory.values.any { it.date == today && it.videoId.startsWith("ai_") }
                 if (isDoneToday) {
                     btnAiExerciseDone.isEnabled = false
                     btnAiExerciseDone.text = "Completed Today ✅"
                     btnAiExerciseDone.alpha = 0.6f
                 }
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
