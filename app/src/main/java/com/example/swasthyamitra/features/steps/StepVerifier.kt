package com.example.swasthyamitra.features.steps

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.util.Log

/**
 * Validates step events to filter out false positives from shaking,
 * impossible cadences, or non-walking activities.
 */
class StepVerifier {

    private var lastStepTime = 0L
    private val MIN_STEP_DELAY_MS = 300L // Humans can't walk faster than ~3.3 steps/sec continuously
    private val STEP_BUFFER_SIZE = 5
    private var stepBufferCount = 0
    private var isBufferFlushed = false
    
    // Confidence threshold for activity recognition (0-100)
    private val ACTIVITY_CONFIDENCE_THRESHOLD = 50
    
    // Current activity state
    var isWalkingOrRunning = true // Default to true to allow steps if AR is unavailable

    /**
     * Verifies if a step event is valid based on timing and current activity.
     * @return Number of steps to add (0, 1, or buffer flush amount)
     */
    fun verify(event: SensorEvent): Int {
        val currentTime = System.currentTimeMillis()
        
        // 1. Cadence Filter (Debouncing)
        if (currentTime - lastStepTime < MIN_STEP_DELAY_MS) {
            Log.d("StepVerifier", "Step rejected: Too fast (${currentTime - lastStepTime}ms)")
            return 0
        }
        
        // 2. Activity Gate
        if (!isWalkingOrRunning) {
            Log.d("StepVerifier", "Step rejected: Not walking/running")
            return 0
        }

        lastStepTime = currentTime

        // 3. Step Buffering (Anti-bump)
        // Require N steps in rhythm before counting them to reject random phone bumps
        if (!isBufferFlushed) {
            stepBufferCount++
            if (stepBufferCount >= STEP_BUFFER_SIZE) {
                isBufferFlushed = true
                return stepBufferCount // Flush buffer
            }
            return 0 // Buffer step
        }

        return 1
    }

    /**
     * Resets the buffer logic if too much time has passed since the last step.
     * Call this periodically or on sensor timeout.
     */
    fun checkResetBuffer() {
        if (System.currentTimeMillis() - lastStepTime > 2000) { // 2 seconds of inactivity
            isBufferFlushed = false
            stepBufferCount = 0
        }
    }
    
    /**
     * Validates a batch of steps based on average cadence.
     * Useful for TYPE_STEP_COUNTER which reports cumulative updates.
     */
    fun validateBatch(steps: Int, timeDeltaMs: Long): Boolean {
        if (timeDeltaMs <= 0) return false // Invalid time
        
        // Calculate steps per second
        val stepsPerSec = (steps.toFloat() / timeDeltaMs) * 1000
        
        // Max human sprinting cadence is ~4-5 steps/sec. 
        // Hand shaking can easily exceed 8-10 steps/sec.
        // We set a generous limit of 6.0 to allow for some sensor burstiness but reject shaking.
        if (stepsPerSec > 6.0) {
            Log.d("StepVerifier", "Batch rejected: Too fast ($stepsPerSec steps/sec)")
            return false
        }
        
        return true
    }
}
