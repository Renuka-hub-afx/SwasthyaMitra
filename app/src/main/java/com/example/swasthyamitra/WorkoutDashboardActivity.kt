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
import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import android.util.Log

import com.google.firebase.database.FirebaseDatabase
import com.example.swasthyamitra.gamification.XPManager
import kotlinx.coroutines.launch


class WorkoutDashboardActivity : AppCompatActivity() {

    private lateinit var authHelper: FirebaseAuthHelper
    private lateinit var stepManager: StepManager
    private var userId: String = ""
    
    private lateinit var tvSteps: TextView
    private lateinit var tvCaloriesBurned: TextView
    // private lateinit var tvRecommendationText: TextView
    // private lateinit var tvCalorieStatus: TextView
    // private lateinit var cvVideoRecommendation: CardView
    // private lateinit var tvVideoTitle: TextView
    private lateinit var btnBackWorkout: View 


    
    // Stats UI
    private lateinit var tvTotalWorkouts: TextView
    private lateinit var tvWorkoutStreak: TextView
    private lateinit var tvTotalMinutes: TextView
    

    
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
    private lateinit var tvExerciseCounterBottom: TextView

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

    // Flag to track if step manager is started
    private var isStepManagerStarted = false

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val activityRecognitionGranted = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                permissions[Manifest.permission.ACTIVITY_RECOGNITION] ?: false
            } else {
                true
            }

            if (activityRecognitionGranted) {
                Log.i("StepCounter", "✅ Activity recognition permission granted")
                startStepCounterWithHybridValidation()
            } else {
                Log.e("StepCounter", "❌ Activity recognition permission denied")
                Toast.makeText(this,
                    "⚠️ Step counting requires activity recognition permission for best accuracy",
                    Toast.LENGTH_LONG).show()
                // Fall back to legacy mode
                if (!isStepManagerStarted) {
                    stepManager.start(enableHybridValidation = false)
                    isStepManagerStarted = true
                }
            }
        }

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
        
        // Check permission and start with appropriate mode
        checkAndRequestPermissions()

        fetchUserData()
        setupListeners()
    }
    
    private fun startStepCounterWithHybridValidation() {
        if (!isStepManagerStarted) {
            Log.i("StepCounter", "Starting StepManager with hybrid validation")
            stepManager.start(enableHybridValidation = true)
            isStepManagerStarted = true

            Toast.makeText(this,
                "✅ Advanced step counter activated - High accuracy mode!",
                Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkAndRequestPermissions() {
        val permissionsToRequest = mutableListOf<String>()
        
        var hasActivityRecognition = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION) != PackageManager.PERMISSION_GRANTED) {
                permissionsToRequest.add(Manifest.permission.ACTIVITY_RECOGNITION)
                hasActivityRecognition = false
            }
        }
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                permissionsToRequest.add(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
        
        if (permissionsToRequest.isNotEmpty()) {
            requestPermissionLauncher.launch(permissionsToRequest.toTypedArray())
        } else {
            // All permissions granted - start with hybrid validation
            if (hasActivityRecognition) {
                startStepCounterWithHybridValidation()
            } else {
                // No permission, use legacy mode
                if (!isStepManagerStarted) {
                    stepManager.start(enableHybridValidation = false)
                    isStepManagerStarted = true
                }
            }
        }
    }

    private fun initViews() {
        tvSteps = findViewById(R.id.tvStepsWorkout)
        tvCaloriesBurned = findViewById(R.id.tvCaloriesBurned)
        // tvRecommendationText = findViewById(R.id.tvRecommendationText) // Old
        // tvCalorieStatus = findViewById(R.id.tvCalorieStatus) // Old
        

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
        btnAiExerciseDone = findViewById(R.id.btnAiExerciseDone)
        // Actually in XML it is <Button> which is AppCompatButton, but commonly castable. 
        // Let's fix the type in member variable if needed, or just finding by ID is enough.
        // In XML: btnAiExerciseDone is <Button>, btnAiExerciseSkip is <MaterialButton>
        // Use findViewById simply
        
        btnAiExerciseSkip = findViewById(R.id.btnAiExerciseSkip)
        tvExerciseCounter = findViewById(R.id.tvExerciseCounter)
        tvExerciseCounterBottom = findViewById(R.id.tvExerciseCounterBottom)

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
        
        findViewById<View>(R.id.cvInsights).setOnClickListener {
            startActivity(Intent(this, InsightsActivity::class.java))
        }


        findViewById<View>(R.id.cvSafety).setOnClickListener {
            startActivity(Intent(this, SafetyCoreActivity::class.java))
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
        
        // Basic Info - Always show
        tvAiExerciseName.text = rec.name
        tvAiExerciseTarget.text = "Target: ${rec.targetMuscle}"
        tvAiExerciseReason.text = rec.reason
        tvAiExerciseCalories.text = "🔥 ~${rec.estimatedCalories} kcal"
        tvAiExerciseDuration.text = "⏱️ ${rec.recommendedDuration}"
        val counterText = "Exercise ${currentExerciseIndex + 1} of ${currentAiExerciseList.size}"
        tvExerciseCounter.text = counterText
        tvExerciseCounterBottom.text = counterText
        
        // Hidden fields
        tvAiExerciseEquipment.text = "Equipment: ${rec.equipment}"
        tvAiExerciseGoalAlignment.text = rec.goalAlignment

        // Get user period status
        lifecycleScope.launch {
            val userData = authHelper.getUserData(userId).getOrNull()
            val isOnPeriod = userData?.get("isOnPeriod") as? Boolean ?: false

            runOnUiThread {
                // Age Explanation - Always show if available
                val layoutAgeInsight = findViewById<View>(R.id.layoutAgeInsight)
                if (rec.ageExplanation.isNotEmpty()) {
                    tvAiExerciseAgeExplanation.text = "💡 Age Insight: ${rec.ageExplanation}"
                    layoutAgeInsight.visibility = View.VISIBLE
                } else {
                    layoutAgeInsight.visibility = View.GONE
                }

                // Gender Note - Always show if available
                val layoutGenderNote = findViewById<View>(R.id.layoutGenderNote)
                if (rec.genderNote.isNotEmpty()) {
                    tvAiExerciseGenderNote.text = "✨ For You: ${rec.genderNote}"
                    layoutGenderNote.visibility = View.VISIBLE
                } else {
                    layoutGenderNote.visibility = View.GONE
                }

                // Motivational Message - ONLY show if period mode is active
                val layoutMotivation = findViewById<View>(R.id.layoutMotivation)
                if (isOnPeriod && rec.motivationalMessage.isNotEmpty()) {
                    tvAiExerciseMotivation.text = "💕 ${rec.motivationalMessage}"
                    layoutMotivation.visibility = View.VISIBLE
                } else {
                    layoutMotivation.visibility = View.GONE
                }
            }
        }
        
        // Pro Tips - Always show if available
        llTips.removeAllViews()
        val layoutProTips = findViewById<View>(R.id.layoutProTips)
        if (rec.tips.isNotEmpty()) {
            layoutProTips.visibility = View.VISIBLE
            rec.tips.forEach { tip ->
                val tipView = TextView(this)
                tipView.text = tip
                tipView.textSize = 13f
                tipView.setTextColor(android.graphics.Color.parseColor("#2E7D32"))
                tipView.setPadding(0, 0, 0, 8)
                llTips.addView(tipView)
            }
        } else {
            layoutProTips.visibility = View.GONE
        }

        // Common Mistakes - Always show if available
        llCommonMistakes.removeAllViews()
        val layoutAvoidMistakes = findViewById<View>(R.id.layoutAvoidMistakes)
        if (rec.commonMistakes.isNotEmpty()) {
            layoutAvoidMistakes.visibility = View.VISIBLE
            rec.commonMistakes.forEach { mistake ->
                val mistakeView = TextView(this)
                mistakeView.text = mistake
                mistakeView.textSize = 13f
                mistakeView.setTextColor(android.graphics.Color.parseColor("#C62828"))
                mistakeView.setPadding(0, 0, 0, 8)
                llCommonMistakes.addView(mistakeView)
            }
        } else {
            layoutAvoidMistakes.visibility = View.GONE
        }

        // Instructions - Keep hidden
        llInstructions.removeAllViews()
        llInstructions.visibility = View.GONE
        rec.instructions.forEachIndexed { index, step ->
            val stepView = TextView(this)
            stepView.text = "${index + 1}. $step"
            stepView.textSize = 14f
            stepView.setTextColor(android.graphics.Color.parseColor("#333333"))
            stepView.setPadding(0, 0, 0, 16)
            llInstructions.addView(stepView)
        }
        
        // Reset Done button state
        val btnDone = findViewById<Button>(R.id.btnAiExerciseDone)
        btnDone.isEnabled = true
        btnDone.text = "I DID IT! 💪"
        btnDone.alpha = 1.0f

        // Load GIF/Image from assets - ALWAYS TRY TO SHOW
        if (rec.gifUrl.isNotEmpty()) {
            ivAiExerciseGif.visibility = View.VISIBLE
            
            // URL-encode the path for spaces and special characters
            val encodedPath = rec.gifUrl.replace(" ", "%20")
            val fullPath = "file:///android_asset/$encodedPath"
            
            Log.d("WorkoutDashboard", "Loading GIF from: $fullPath")

            try {
                 // Use Glide to load
                 com.bumptech.glide.Glide.with(this)
                    .load(fullPath)
                    .placeholder(R.drawable.ic_workout_placeholder)
                    .error(R.drawable.ic_workout_placeholder)
                    .into(ivAiExerciseGif)
            } catch (e: Exception) {
                ivAiExerciseGif.visibility = View.GONE
            }
        } else {
             ivAiExerciseGif.visibility = View.GONE
        }

    }

    private fun markAiExerciseComplete() {
        val exercise = currentAiExerciseList.getOrNull(currentExerciseIndex) ?: return
        btnAiExerciseDone.isEnabled = false
        btnAiExerciseDone.text = "Saving..."

        val db = FirebaseDatabase.getInstance("https://swasthyamitra-ded44-default-rtdb.asia-southeast1.firebasedatabase.app").reference
        val userRef = db.child("users").child(userId)
        val today = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US).format(java.util.Date())

        // 1. Log to Firestore "renu" database (for Homepage stats)
        val logData = hashMapOf(
            "userId" to userId,
            "date" to today,
            "exerciseName" to exercise.name,
            "targetMuscle" to exercise.targetMuscle,
            "bodyPart" to exercise.bodyPart,
            "caloriesBurned" to exercise.estimatedCalories,
            "duration" to 15, // Default or parse from string
            "timestamp" to com.google.firebase.Timestamp.now(),
            "source" to "AI_Recommendation"
        )
        
        // Use "renu" Firestore database
        com.google.firebase.firestore.FirebaseFirestore.getInstance("renu")
            .collection("users")
            .document(userId)
            .collection("exercise_logs")
            .add(logData)
            .addOnSuccessListener { documentReference ->
                 Log.d("WorkoutDashboard", "Exercise logged to Firestore (renu): ${documentReference.id}")
                 // 2. Update RTDB (for Workout Dashboard stats)
                 updateRTDBStats(exercise, userRef, today)
            }
            .addOnFailureListener { e ->
                Log.e("WorkoutDashboard", "Failed to log to Firestore: ${e.message}", e)
                Toast.makeText(this, "Failed to log: ${e.message}", Toast.LENGTH_SHORT).show()
                btnAiExerciseDone.isEnabled = true
                btnAiExerciseDone.text = "I DID IT! 💪"
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
                category = "AI Exercise",
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
                    Log.d("WorkoutDashboard", "Exercise saved to RTDB: ${exercise.name}")

                    // PHASE 2.2: Award XP for AI Exercise (+75 XP)
                    val xpManager = XPManager(userId)
                    xpManager.awardXP(XPManager.XPSource.AI_EXERCISE) { leveledUp, newLevel ->
                        runOnUiThread {
                            if (leveledUp) {
                                showLevelUpToast(newLevel)
                            }
                            Toast.makeText(this, "✅ Saved! +150 XP & ${exercise.estimatedCalories} kcal | +75 XP!", Toast.LENGTH_SHORT).show()
                        }
                    }

                    runOnUiThread {
                        btnAiExerciseDone.text = "Completed! 🎉"

                        // Update UI Stats
                        tvTotalWorkouts.text = updatedHistory.size.toString()
                        tvTotalMinutes.text = updatedData.totalWorkoutMinutes.toString()
                        
                        // Disable button permanently for this session
                        btnAiExerciseDone.alpha = 0.6f
                        
                        // Refresh cache
                        this.fitnessData = updatedData
                        
                        // Auto-advance to next exercise after 1.2s delay
                        android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                            currentExerciseIndex++
                            displayCurrentExercise()
                        }, 1200)
                    }
                }
                .addOnFailureListener { e ->
                    Log.e("WorkoutDashboard", "Failed to save to RTDB: ${e.message}", e)
                    runOnUiThread {
                        Toast.makeText(this, "RTDB save failed: ${e.message}", Toast.LENGTH_SHORT).show()
                        btnAiExerciseDone.isEnabled = true
                        btnAiExerciseDone.text = "I DID IT! 💪"
                    }
                }
        }.addOnFailureListener { e ->
            Log.e("WorkoutDashboard", "Failed to fetch RTDB data: ${e.message}", e)
            runOnUiThread {
                Toast.makeText(this, "Failed to fetch data: ${e.message}", Toast.LENGTH_SHORT).show()
                btnAiExerciseDone.isEnabled = true
                btnAiExerciseDone.text = "I DID IT! 💪"
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

    // PHASE 2.2: Level-Up Toast Notification
    private fun showLevelUpToast(newLevel: Int) {
        Toast.makeText(
            this,
            "🎉 LEVEL UP! You're now Level $newLevel! 🎉",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun checkWorkoutStatusAndStats() {
        val db = FirebaseDatabase.getInstance("https://swasthyamitra-ded44-default-rtdb.asia-southeast1.firebasedatabase.app").reference
        val userRef = db.child("users").child(userId)
        val today = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US).format(java.util.Date())

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
    override fun onDestroy() {
        super.onDestroy()
        stepManager.stop()
    }
}
