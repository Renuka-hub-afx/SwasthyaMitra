package com.example.swasthyamitra.safety

import android.util.Log

// Detects prolonged inactivity during a workout session; triggers SOS if no movement for 45+ seconds
class SafetyMonitorManager(
    private val inactivityThresholdMs: Long = 45000L // 45 seconds of no movement = potential emergency
) {
    private var lastMovementTime: Long = System.currentTimeMillis()
    private var lastSteps: Int = 0
    private var lastLat: Double = 0.0
    private var lastLon: Double = 0.0

    private var isCurrentlyStill: Boolean = false

    // Called periodically with the latest sensor data; resets timer if movement is detected
    fun updateData(steps: Int, lat: Double, lon: Double, isStill: Boolean) {
        val currentTime = System.currentTimeMillis()
        
        // Detect movement
        val movedBySteps = steps > lastSteps
        val movedByLocation = Math.abs(lat - lastLat) > 0.0001 || Math.abs(lon - lastLon) > 0.0001
        val isNotStill = !isStill

        if (movedBySteps || movedByLocation || isNotStill) {
            lastMovementTime = currentTime
            lastSteps = steps
            lastLat = lat
            lastLon = lon
            isCurrentlyStill = isStill
            Log.d("SafetyMonitor", "Movement detected. Resetting timer.")
        } else {
            isCurrentlyStill = true
        }
    }

    // Returns true when user is still AND has been stationary longer than the threshold → SOS should fire
    fun isThresholdExceeded(): Boolean {
        val timeSinceLastMovement = System.currentTimeMillis() - lastMovementTime
        return isCurrentlyStill && timeSinceLastMovement >= inactivityThresholdMs
    }

    // Resets the inactivity timer (called after SOS fires or user resumes movement)
    fun reset() {
        lastMovementTime = System.currentTimeMillis()
        isCurrentlyStill = false
    }
}
