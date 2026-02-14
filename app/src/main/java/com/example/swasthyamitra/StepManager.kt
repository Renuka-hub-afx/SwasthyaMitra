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
 * Enhanced StepManager with Hybrid Validation System
 * Integrates hardware step sensor with multi-layer validation
 * Maintains backward compatibility with existing StepCounterService
 */
class StepManager(private val context: Context, private val onStepUpdate: (Int, Double) -> Unit) {

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

    // Hybrid validation system (optional, can be enabled)
    private var hybridValidator: HybridStepValidator? = null
    private var firebaseStepSync: FirebaseStepSync? = null
    private val scope = CoroutineScope(Dispatchers.Main)

    private var isRegistered = false
    private var useHybridValidation = false // Set to true to enable advanced validation
    var dailySteps: Int = 0
        private set

    /**
     * Start step tracking with optional hybrid validation
     * @param enableHybridValidation - Enable multi-layer validation (default: false for backward compatibility)
     */
    fun start(enableHybridValidation: Boolean = false) {
        useHybridValidation = enableHybridValidation

        if (useHybridValidation) {
            startWithHybridValidation()
        } else {
            startLegacyMode()
        }
    }

    private fun startLegacyMode() {
        // Original implementation - backward compatible
        // 1. Start the Foreground Service (if not running)
        val serviceIntent = Intent(context, StepCounterService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(serviceIntent)
        } else {
            context.startService(serviceIntent)
        }

        // 2. Register for updates
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
        
        // 3. Trigger initial fetch from Prefs for instant UI
        val prefs = context.getSharedPreferences("StepCounterPrefs", Context.MODE_PRIVATE)
        val savedSteps = prefs.getInt("daily_steps", 0)
        val calories = savedSteps * 0.04
        dailySteps = savedSteps
        onStepUpdate(savedSteps, calories)
    }

    private fun startWithHybridValidation() {
        Log.i("StepManager", "Starting with Hybrid Validation System")

        // Initialize Firebase sync
        firebaseStepSync = FirebaseStepSync(context)

        // Initialize hybrid validator
        hybridValidator = HybridStepValidator(context)

        // Setup activity recognition callback
        ActivityRecognitionReceiver.onActivityChanged = { activity ->
            hybridValidator?.updateActivityState(activity)
        }

        // Start validation with callback
        hybridValidator?.start { validatedSteps, confidence ->
            dailySteps = validatedSteps
            val calories = validatedSteps * 0.04 // 0.04 kcal per step

            // Update UI
            onStepUpdate(validatedSteps, calories)

            // Sync to Firebase (async)
            syncToFirebase(validatedSteps, confidence)

            Log.d("StepManager", "Validated steps: $validatedSteps (confidence: $confidence%)")
        }

        // Load initial data from Firebase
        loadInitialSteps()
    }

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

    private fun syncToFirebase(steps: Int, confidence: Double) {
        scope.launch {
            val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return@launch

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

    fun stop() {
        if (isRegistered) {
            try {
                context.unregisterReceiver(stepReceiver)
                isRegistered = false
            } catch (e: Exception) {
                Log.e("StepManager", "Error unregistering receiver", e)
            }
        }

        // Stop hybrid validator if running
        hybridValidator?.stop()
        hybridValidator = null
        ActivityRecognitionReceiver.onActivityChanged = null
    }

    /**
     * Get step confidence score (only available in hybrid mode)
     */
    fun getConfidenceScore(): Double {
        // Can be extended to return actual confidence from validator
        return if (useHybridValidation) 95.0 else 0.0
    }
}
