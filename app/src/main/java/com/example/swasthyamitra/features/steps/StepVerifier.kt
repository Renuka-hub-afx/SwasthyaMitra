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
    private val MIN_STEP_DELAY_MS = 300L // Fast walking
    private val MAX_STEP_DELAY_MS = 2000L // Slow walking/Stop detection
    
    private val STEP_BUFFER_SIZE = 5
    private var stepBufferCount = 0
    private var isBufferFlushed = false
    
    // Confidence threshold for activity recognition (0-100)
    private val ACTIVITY_CONFIDENCE_THRESHOLD = 70
    
    // Current activity state (updated by Service via Activity Recognition)
    var currentActivityType: Int = -1 // DetectedActivity type
    var currentConfidence: Int = 0

    /**
     * Verifies if a step event is valid based on:
     * 1. Activity Type (WALKING/RUNNING)
     * 2. Activity Confidence (>= 70%)
     * 3. Cadence (300ms - 2000ms)
     * 4. Continuity (5 steps buffer)
     * 
     * @return Number of steps to increment in UI
     */
    fun verify(event: SensorEvent): Int {
        val currentTime = System.currentTimeMillis()
        val timeDelta = currentTime - lastStepTime

        // 1. Activity Validation (Google Activity Recognition) - DISABLED
        // val isValidActivity = isWalkingOrRunning(currentActivityType)
        // 
        // if (!isValidActivity || currentConfidence < ACTIVITY_CONFIDENCE_THRESHOLD) {
        //     Log.d("StepVerifier", "Step rejected: Activity=$currentActivityType, Conf=$currentConfidence")
        //     resetBuffer()
        //     return 0
        // }

        // 2. Cadence Filter (Debouncing)
        // 300ms is the "sprint" limit, 2000ms is the "standing still" limit
        if (timeDelta < MIN_STEP_DELAY_MS || (lastStepTime != 0L && timeDelta > MAX_STEP_DELAY_MS)) {
            Log.d("StepVerifier", "Step rejected: Cadence out of range (${timeDelta}ms)")
            // If it's too slow (>2s), reset buffer as movement is no longer continuous
            if (timeDelta > MAX_STEP_DELAY_MS) resetBuffer()
            lastStepTime = currentTime
            return 0
        }

        lastStepTime = currentTime

        // 3. Continuity Threshold (Anti-bump)
        // Require 5 rhythmic steps before starting the official count
        if (!isBufferFlushed) {
            stepBufferCount++
            if (stepBufferCount >= STEP_BUFFER_SIZE) {
                isBufferFlushed = true
                return stepBufferCount // Flush initial 5 steps at once
            }
            return 0 // Still buffering
        }

        return 1
    }

    private fun isWalkingOrRunning(type: Int): Boolean {
        // DetectedActivity.WALKING = 7, RUNNING = 8, ON_FOOT = 2
        return type == 7 || type == 8 || type == 2
    }

    private fun resetBuffer() {
        stepBufferCount = 0
        isBufferFlushed = false
    }

    fun checkResetBuffer() {
        if (System.currentTimeMillis() - lastStepTime > MAX_STEP_DELAY_MS) {
            resetBuffer()
        }
    }
    
    fun validateBatch(steps: Int, timeDeltaMs: Long): Boolean {
        if (timeDeltaMs <= 0) return false
        
        // 1. Batch Activity Check
        // if (!isWalkingOrRunning(currentActivityType) || currentConfidence < ACTIVITY_CONFIDENCE_THRESHOLD) {
        //     return false
        // }

        // 2. Batch Cadence Check
        val stepsPerSec = (steps.toFloat() / timeDeltaMs) * 1000
        // Reject if cadence is physically impossible (> 5 steps/sec) or too slow (< 0.5 steps/sec)
        if (stepsPerSec > 5.0 || stepsPerSec < 0.5) {
            Log.d("StepVerifier", "Batch rejected: Impossible cadence ($stepsPerSec steps/sec)")
            return false
        }
        
        return true
    }
}
