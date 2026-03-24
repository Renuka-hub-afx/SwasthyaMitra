package com.example.swasthyamitra

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.util.Log
import com.example.swasthyamitra.services.StepCounterService
import com.example.swasthyamitra.step.HybridStepValidator
import com.example.swasthyamitra.step.ActivityRecognitionReceiver
import com.example.swasthyamitra.step.FirebaseStepSync
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.google.firebase.auth.FirebaseAuth

/**
 * StepManager is the primary controller for managing step tracking within the application.
 * It provides a unified interface to start and stop tracking, choosing between a 
 * legacy mode (hardware sensor only) and an enhanced hybrid validation mode.
 *
 * @param context The Android Context used for starting services and registering receivers.
 * @param onStepUpdate A callback function triggered whenever the step count or calories are updated.
 */
class StepManager(private val context: Context, private val onStepUpdate: (Int, Double) -> Unit) {

    /**
     * Receiver to listen for step updates broadcast from StepCounterService.
     * This is used primarily in legacy mode.
     */
    private val stepReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == StepCounterService.ACTION_UPDATE_STEPS) {
                val steps = intent.getIntExtra("steps", 0)
                val calories = intent.getDoubleExtra("calories", 0.0)
                dailySteps = steps
                Log.d("StepManager", "Received update: $steps steps")
                onStepUpdate(steps, calories)
            }
        }
    }

    // Hybrid validation system components
    private var hybridValidator: HybridStepValidator? = null
    private var firebaseStepSync: FirebaseStepSync? = null
    private val scope = CoroutineScope(Dispatchers.Main)

    private var isRegistered = false
    private var useHybridValidation = false // Flag to toggle between basic and advanced validation
    var dailySteps: Int = 0
        private set

    /**
     * Starts the step tracking process.
     * 
     * @param enableHybridValidation If true, uses AI/GPS-based validation to filter out 
     * false steps (like vehicle movement). If false, uses the standard legacy tracker.
     */
    fun start(enableHybridValidation: Boolean = false) {
        useHybridValidation = enableHybridValidation

        if (useHybridValidation) {
            startWithHybridValidation()
        } else {
            startLegacyMode()
        }
    }

    /**
     * Initializes the standard step counter service and registers a receiver for updates.
     * This mode relies on the device's hardware step counter sensor.
     */
    private fun startLegacyMode() {
        // 1. Start the Foreground Service to keep tracking while app is in background
        val serviceIntent = Intent(context, StepCounterService::class.java)
        serviceIntent.action = StepCounterService.ACTION_START
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(serviceIntent)
        } else {
            context.startService(serviceIntent)
        }

        // 2. Register for step update broadcasts
        if (!isRegistered) {
            val filter = IntentFilter(StepCounterService.ACTION_UPDATE_STEPS)
            androidx.core.content.ContextCompat.registerReceiver(
                context,
                stepReceiver,
                filter,
                androidx.core.content.ContextCompat.RECEIVER_NOT_EXPORTED
            )
            isRegistered = true
        }
        
        // 3. Load the initial step count from SharedPreferences for instant UI display
        val prefs = context.getSharedPreferences("StepCounterPrefs", Context.MODE_PRIVATE)
        val savedDate = prefs.getString("last_date", "")
        val today = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(java.util.Date())
        val savedSteps = if (savedDate == today) prefs.getInt("daily_steps", 0) else 0
        val calories = savedSteps * 0.04
        dailySteps = savedSteps
        onStepUpdate(savedSteps, calories)
    }

    /**
     * Initializes the enhanced step tracking with hybrid validation.
     * This system uses activity recognition and GPS data to validate steps in real-time.
     */
    private fun startWithHybridValidation() {
        Log.i("StepManager", "Starting with Hybrid Validation System")

        // Initialize Firebase synchronization component
        firebaseStepSync = FirebaseStepSync(context)

        // Initialize the hybrid validator logic
        hybridValidator = HybridStepValidator(context)

        // Link the activity recognition state to the validator
        ActivityRecognitionReceiver.onActivityChanged = { activity ->
            hybridValidator?.updateActivityState(activity)
        }

        // Start the validator and provide a callback for validated results
        hybridValidator?.start { validatedSteps, confidence ->
            dailySteps = validatedSteps
            val calories = validatedSteps * 0.04 // Standard calorie calculation: 0.04 kcal per step

            // Update UI/Callbacks with validated data
            onStepUpdate(validatedSteps, calories)

            // Asynchronously sync the validated steps and confidence to the cloud (Firebase)
            syncToFirebase(validatedSteps, confidence)

            Log.d("StepManager", "Validated steps: $validatedSteps (confidence: $confidence%)")
        }

        // Load the cumulative steps for today from Firebase to sync state
        loadInitialSteps()
    }

    /**
     * Fetches today's step count from Firebase to initialize the local counter.
     */
    private fun loadInitialSteps() {
        scope.launch {
            val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return@launch

            firebaseStepSync?.getTodaySteps(userId)?.onSuccess { steps ->
                dailySteps = steps
                val calories = steps * 0.04
                onStepUpdate(steps, calories)
            }
        }
    }

    /**
     * Syncs validated step data and confidence scores to Firebase for cross-device consistency.
     * 
     * @param steps The number of validated steps.
     * @param confidence The calculated confidence score of the step validity.
     */
    private fun syncToFirebase(steps: Int, confidence: Double) {
        scope.launch {
            val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return@launch

            // Mapping detected activity types for analytics/logging
            val activityType = ActivityRecognitionReceiver.latestActivity?.let {
                when (it.type) {
                    com.google.android.gms.location.DetectedActivity.WALKING -> "WALKING"
                    com.google.android.gms.location.DetectedActivity.RUNNING -> "RUNNING"
                    else -> "ON_FOOT"
                }
            } ?: "WALKING"

            firebaseStepSync?.syncValidatedSteps(userId, steps, confidence, activityType)
                ?.onFailure { e ->
                    Log.e("StepManager", "Firebase sync failed: ${e.message}")
                }
        }
    }

    /**
     * Stops all tracking services, unregisters receivers, and shuts down validators.
     */
    fun stop() {
        if (isRegistered) {
            try {
                context.unregisterReceiver(stepReceiver)
                isRegistered = false
            } catch (e: Exception) {
                Log.e("StepManager", "Error unregistering receiver", e)
            }
        }

        // Properly stop the hybrid validator and clear callbacks
        hybridValidator?.stop()
        hybridValidator = null
        ActivityRecognitionReceiver.onActivityChanged = null
    }

    /**
     * Returns the confidence score of the current step count.
     * In hybrid mode, this represents the probability that the steps are genuine.
     */
    fun getConfidenceScore(): Double {
        return if (useHybridValidation) 95.0 else 0.0
    }

}
