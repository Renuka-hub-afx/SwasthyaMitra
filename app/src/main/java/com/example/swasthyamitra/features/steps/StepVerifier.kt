package com.example.swasthyamitra.features.steps

import android.util.Log
import java.util.*
import kotlin.math.abs

/**
 * Validates step events to filter out false positives from shaking,
 * impossible cadences, or non-walking activities.
 */
class StepVerifier {

    private var lastStepTime = 0L
    private val MIN_STEP_DELAY_MS = 350L  // User requirement: 350ms
    private val MAX_STEP_DELAY_MS = 1500L // User requirement: 1500ms
    
    private val STEP_BUFFER_SIZE = 5
    private var stepBufferCount = 0
    private var isBufferFlushed = false
    
    // Interval history for rhythmic consistency
    private val intervalHistory = LinkedList<Long>()
    private val RHYTHM_TOLERANCE_PERCENT = 0.25 // 25% tolerance for rhythm

    // Accelerometer magnitude range
    private val MIN_MAGNITUDE = 9.0
    private val MAX_MAGNITUDE = 25.0
    var currentMagnitude: Double = 9.8 // Default gravity

    // Confidence threshold for activity recognition (0-100)
    private val ACTIVITY_CONFIDENCE_THRESHOLD = 70
    
    // Current activity state (updated by Service via Activity Recognition)
    var currentActivityType: Int = -1 // DetectedActivity type
    var currentConfidence: Int = 0

    /**
     * Verifies if a step event is valid based on the workflow:
     * 1. Check interval (350–1500ms)
     * 2. Check ActivityRecognition (WALKING/RUNNING + confidence >= 70)
     * 3. Check accelerometer magnitude range
     * 4. Check rhythmic consistency (last 5 intervals)
     * 
     * @return Number of steps to increment in UI
     */
    fun verifyStep(): Int {
        val currentTime = System.currentTimeMillis()
        
        // 1. Check interval (350–1500ms)
        val timeDelta = if (lastStepTime == 0L) 1000L else currentTime - lastStepTime
        
        if (timeDelta < MIN_STEP_DELAY_MS || timeDelta > MAX_STEP_DELAY_MS) {
            Log.d("StepVerifier", "Rejected: Interval out of range (${timeDelta}ms)")
            if (timeDelta > MAX_STEP_DELAY_MS) resetBuffer()
            lastStepTime = currentTime
            return 0
        }

        // 2. Check ActivityRecognition (WALKING=7, RUNNING=8 + confidence >= 70)
        if (currentActivityType != -1) {
            val isWalkingOrRunning = currentActivityType == 7 || currentActivityType == 8
            if (!isWalkingOrRunning || currentConfidence < ACTIVITY_CONFIDENCE_THRESHOLD) {
                Log.d("StepVerifier", "Rejected: Activity=$currentActivityType, Conf=$currentConfidence")
                return 0
            }
        }

        // 3. Check accelerometer magnitude range
        if (currentMagnitude < MIN_MAGNITUDE || currentMagnitude > MAX_MAGNITUDE) {
            Log.d("StepVerifier", "Rejected: Magnitude out of range ($currentMagnitude)")
            return 0
        }

        // 4. Check rhythmic consistency (last 5 intervals)
        if (lastStepTime != 0L) {
            intervalHistory.add(timeDelta)
            if (intervalHistory.size > 5) {
                intervalHistory.removeFirst()
            }
        }
        
        if (intervalHistory.size >= 5) {
            if (!isRhythmic()) {
                Log.d("StepVerifier", "Rejected: Not rhythmic $intervalHistory")
                return 0
            }
        }

        lastStepTime = currentTime

        // 5. If all pass → increment buffer count
        // 6. If buffer >= 5 → update UI + Firebase
        if (!isBufferFlushed) {
            stepBufferCount++
            if (stepBufferCount >= STEP_BUFFER_SIZE) {
                isBufferFlushed = true
                return stepBufferCount // Flush initial 5 steps
            }
            return 0 // Still buffering
        }

        return 1
    }

    private fun isRhythmic(): Boolean {
        if (intervalHistory.size < 2) return true
        val avg = intervalHistory.average()
        for (interval in intervalHistory) {
            if (abs(interval - avg) > (avg * RHYTHM_TOLERANCE_PERCENT)) {
                return false
            }
        }
        return true
    }

    private fun resetBuffer() {
        stepBufferCount = 0
        isBufferFlushed = false
        intervalHistory.clear()
    }

    fun validateBatch(steps: Int, timeDeltaMs: Long): Boolean {
        // This is a fallback for batched updates, but we prefer verifyStep()
        if (timeDeltaMs <= 0) return false
        
        // 1. Activity Check
        if (currentActivityType != -1) {
            val isWalkingOrRunning = currentActivityType == 7 || currentActivityType == 8
            if (!isWalkingOrRunning || currentConfidence < ACTIVITY_CONFIDENCE_THRESHOLD) return false
        }

        // 2. Cadence Check
        val stepsPerSec = (steps.toFloat() / timeDeltaMs) * 1000
        // 350ms per step = 2.85 steps/sec. 1500ms per step = 0.66 steps/sec.
        if (stepsPerSec > 3.0 || stepsPerSec < 0.5) return false
        
        return true
    }
}
