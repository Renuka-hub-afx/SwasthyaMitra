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
    // Button type changed in XML to MaterialButton/Button, keeping reference generic or casting safely
    private lateinit var btnAiExerciseSkip: com.google.android.material.button.MaterialButton
    private lateinit var tvExerciseCounter: TextView

    // New AI UI Elements
    private lateinit var tvAiExerciseTarget: TextView
    private lateinit var tvAiExerciseEquipment: TextView
    private lateinit var tvAiExerciseAgeExplanation: TextView
    private lateinit var tvAiExerciseGenderNote: TextView
    private lateinit var tvAiExerciseMotivation: TextView
    private lateinit var tvAiExerciseGoalAlignment: TextView
    private lateinit var llInstructions: android.widget.LinearLayout
    private lateinit var llTips: android.widget.LinearLayout
    private lateinit var llCommonMistakes: android.widget.LinearLayout
    
    // Exercise Action Buttons
    private lateinit var btnAiExerciseRecommendation: com.google.android.material.button.MaterialButton
    private lateinit var btnManualExercise: com.google.android.material.button.MaterialButton

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
        
        // Buttons
        btnAiExerciseDone = findViewById<View>(R.id.btnAiExerciseDone) as com.google.android.material.button.MaterialButton // Safe cast if XML is Button but we treat as View or correct type
        // Actually in XML it is <Button> which is AppCompatButton, but commonly castable. 
        // Let's fix the type in member variable if needed, or just finding by ID is enough.
        // In XML: btnAiExerciseDone is <Button>, btnAiExerciseSkip is <MaterialButton>
        // Use findViewById simply
        
        btnAiExerciseSkip = findViewById(R.id.btnAiExerciseSkip)
        tvExerciseCounter = findViewById(R.id.tvExerciseCounter)

        // Enhanced Fields
        tvAiExerciseTarget = findViewById(R.id.tvAiExerciseTarget)
        tvAiExerciseEquipment = findViewById(R.id.tvAiExerciseEquipment)
        tvAiExerciseAgeExplanation = findViewById(R.id.tvAiExerciseAgeExplanation)
        tvAiExerciseGenderNote = findViewById(R.id.tvAiExerciseGenderNote)
        tvAiExerciseMotivation = findViewById(R.id.tvAiExerciseMotivation)
        tvAiExerciseGoalAlignment = findViewById(R.id.tvAiExerciseGoalAlignment)
        llInstructions = findViewById(R.id.llInstructions)
        llTips = findViewById(R.id.llTips)
        llCommonMistakes = findViewById(R.id.llCommonMistakes)

        // Exercise Action Buttons
        btnAiExerciseRecommendation = findViewById(R.id.btnAiExerciseRecommendation)
        btnManualExercise = findViewById(R.id.btnManualExercise)
    }

    private fun setupListeners() {
        findViewById<View>(R.id.cvGamification).setOnClickListener {
            startActivity(Intent(this, GamificationActivity::class.java))
        }
        // ... (rest of listeners)
        
        findViewById<View>(R.id.cvInsights).setOnClickListener {
            startActivity(Intent(this, InsightsActivity::class.java))
        }

        findViewById<View>(R.id.cvSafety).setOnClickListener {
            startActivity(Intent(this, SafetyActivity::class.java))
        }

        btnBackWorkout.setOnClickListener {
            finish()
        }
        
        findViewById<View>(R.id.btnAiExerciseDone).setOnClickListener {
            markAiExerciseComplete()
        }
        
        btnAiExerciseSkip.setOnClickListener {
            skipToNextExercise()
        }

        btnAiExerciseRecommendation.setOnClickListener {
            loadAiRecommendation()
        }

        btnManualExercise.setOnClickListener {
            startActivity(Intent(this, ManualExerciseActivity::class.java))
        }
    }
    
    // ... (rest of methods until displayCurrentExercise)

    private fun displayCurrentExercise() {
        if (currentExerciseIndex >= currentAiExerciseList.size) {
            // End of list
             cardAiExercise.visibility = View.GONE
             Toast.makeText(this, "All recommended exercises completed! Great job!", Toast.LENGTH_LONG).show()
             return
        }
        
        val rec = currentAiExerciseList[currentExerciseIndex]
        
        tvAiExerciseName.text = rec.name
        
        // Basic Fields
        tvAiExerciseReason.text = rec.reason
        tvAiExerciseCalories.text = "🔥 ~${rec.estimatedCalories} kcal"
        tvAiExerciseDuration.text = "⏱️ ${rec.recommendedDuration}"
        tvExerciseCounter.text = "Exercise ${currentExerciseIndex + 1} of ${currentAiExerciseList.size}"
        
        // Enhanced Fields
        tvAiExerciseTarget.text = "🎯 Target: ${rec.targetMuscle}"
        tvAiExerciseEquipment.text = "🏋️ Equipment: ${rec.equipment}"
        
        // Age Explanation
        if (rec.ageExplanation.isNotEmpty()) {
            tvAiExerciseAgeExplanation.text = "💡 Age Insight: ${rec.ageExplanation}"
            (tvAiExerciseAgeExplanation.parent as View).visibility = View.VISIBLE
        } else {
            (tvAiExerciseAgeExplanation.parent as View).visibility = View.GONE
        }
        
        // Gender Note
        if (rec.genderNote.isNotEmpty()) {
            tvAiExerciseGenderNote.text = "🌸 For You: ${rec.genderNote}"
            (tvAiExerciseGenderNote.parent as View).visibility = View.VISIBLE
        } else {
            (tvAiExerciseGenderNote.parent as View).visibility = View.GONE
        }
        
        // Motivation
        if (rec.motivationalMessage.isNotEmpty()) {
            tvAiExerciseMotivation.text = "💕 ${rec.motivationalMessage}"
            (tvAiExerciseMotivation.parent as View).visibility = View.VISIBLE
        } else {
            (tvAiExerciseMotivation.parent as View).visibility = View.GONE
        }
        
        // Goal Alignment
        if (rec.goalAlignment.isNotEmpty()) {
            tvAiExerciseGoalAlignment.text = rec.goalAlignment
            (tvAiExerciseGoalAlignment.parent as View).visibility = View.VISIBLE
        } else {
            (tvAiExerciseGoalAlignment.parent as View).visibility = View.GONE
        }
        
        // Populate Instructions
        llInstructions.removeAllViews()
        rec.instructions.forEachIndexed { index, step ->
            val stepView = TextView(this)
            stepView.text = "${index + 1}. $step"
            stepView.textSize = 14f
            stepView.setTextColor(android.graphics.Color.parseColor("#333333"))
            stepView.setPadding(0, 0, 0, 16)
            llInstructions.addView(stepView)
        }
        
        // Populate Tips
        llTips.removeAllViews()
        if (rec.tips.isNotEmpty()) {
            (llTips.parent as View).visibility = View.VISIBLE // Ensure section header is visible implicitly if wrapped
            // Actually header text is separate. We might want to toggle header visibility too.
            // For now, assuming always show section if we have data.
            
            rec.tips.forEach { tip ->
                val tipView = TextView(this)
                tipView.text = "• $tip"
                tipView.textSize = 13f
                tipView.setTextColor(android.graphics.Color.parseColor("#2E7D32")) // Dark Green
                tipView.setPadding(0, 0, 0, 12)
                llTips.addView(tipView)
            }
        } else {
            // Locate the header "PRO TIPS" and hide it? 
            // Implementation shortcut: Tips should be there from AI. If empty, just empty list.
        }

        // Populate Mistakes
        llCommonMistakes.removeAllViews()
        if (rec.commonMistakes.isNotEmpty()) {
             rec.commonMistakes.forEach { mistake ->
                val mistakeView = TextView(this)
                mistakeView.text = "• $mistake"
                mistakeView.textSize = 13f
                mistakeView.setTextColor(android.graphics.Color.parseColor("#C62828")) // Dark Red
                mistakeView.setPadding(0, 0, 0, 12)
                llCommonMistakes.addView(mistakeView)
            }
        }
        
        // Reset Done button state
        val btnDone = findViewById<Button>(R.id.btnAiExerciseDone)
        btnDone.isEnabled = true
        btnDone.text = "I DID IT! 💪"
        btnDone.alpha = 1.0f

        // Load GIF/Image from assets - hide if no image available
        if (rec.gifUrl.isNotEmpty()) {
            ivAiExerciseGif.visibility = View.VISIBLE
            
            // URL-encode the path for spaces and special characters
            val encodedPath = rec.gifUrl.replace(" ", "%20")
            val fullPath = "file:///android_asset/$encodedPath"
            
            try {
                 // Use Glide to load
                 com.bumptech.glide.Glide.with(this)
                    .load(fullPath)
                    .into(ivAiExerciseGif)
            } catch (e: Exception) {
                ivAiExerciseGif.visibility = View.GONE
            }
        } else {
             ivAiExerciseGif.visibility = View.GONE
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
        
        com.google.firebase.firestore.FirebaseFirestore.getInstance("renu") // Using RENU database instance
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
                // AI recommendation is now loaded only when button is clicked
            }
            
            checkWorkoutStatusAndStats()
        }
    }
    
    private fun loadAiRecommendation() {
        // Show loading state
        btnAiExerciseRecommendation.isEnabled = false
        btnAiExerciseRecommendation.text = "Loading AI..."

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
                           btnAiExerciseRecommendation.isEnabled = true
                           btnAiExerciseRecommendation.text = "Refresh AI 🔄"
                           Toast.makeText(this@WorkoutDashboardActivity, "AI exercises loaded! ${recList.size} exercises ready", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        runOnUiThread {
                            cardAiExercise.visibility = View.GONE
                            btnAiExerciseRecommendation.isEnabled = true
                            btnAiExerciseRecommendation.text = "AI Exercise 🤖"
                            Toast.makeText(this@WorkoutDashboardActivity, "No exercises available", Toast.LENGTH_SHORT).show()
                        }
                    }

                }.onFailure { error ->
                    runOnUiThread {
                        cardAiExercise.visibility = View.GONE
                        btnAiExerciseRecommendation.isEnabled = true
                        btnAiExerciseRecommendation.text = "AI Exercise 🤖"
                        Toast.makeText(this@WorkoutDashboardActivity, "AI failed: ${error.message}", Toast.LENGTH_LONG).show()
                    }
                }
            } catch (e: Exception) {
               runOnUiThread {
                   cardAiExercise.visibility = View.GONE
                   btnAiExerciseRecommendation.isEnabled = true
                   btnAiExerciseRecommendation.text = "AI Exercise 🤖"
                   Toast.makeText(this@WorkoutDashboardActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
               }
            }
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
