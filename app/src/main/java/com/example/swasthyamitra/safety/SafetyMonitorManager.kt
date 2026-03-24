package com.example.swasthyamitra.safety

import android.util.Log

/**
 * SafetyMonitorManager acts as a passive "Watchdog" for user safety.
 * It monitors incoming motion data (steps and location) and maintains an 
 * inactivity timer. If the user stops moving for longer than the threshold, 
 * it flags a potential emergency (e.g., a fall followed by unconsciousness).
 */
class SafetyMonitorManager(
    private val inactivityThresholdMs: Long = 45000L // Default: 45 seconds of no motion
) {
    private var lastMovementTime: Long = System.currentTimeMillis()
    private var lastSteps: Int = 0
    private var lastLat: Double = 0.0
    private var lastLon: Double = 0.0

    private var isCurrentlyStill: Boolean = false

    /**
     * Updates the monitor with the latest sensor data.
     * @param steps Total steps from the validated counter.
     * @param lat Current latitude.
     * @param lon Current longitude.
     * @param isStill Boolean from Activity Recognition indicating a stationary state.
     */
    fun updateData(steps: Int, lat: Double, lon: Double, isStill: Boolean) {
        val currentTime = System.currentTimeMillis()
        
        // Check for any sign of human movement:
        // 1. Have steps increased?
        val movedBySteps = steps > lastSteps
        // 2. Has the location changed significantly (> ~10 meters)?
        val movedByLocation = Math.abs(lat - lastLat) > 0.0001 || Math.abs(lon - lastLon) > 0.0001
        // 3. Does the system AI say the user is NOT still?
        val isNotStill = !isStill

        if (movedBySteps || movedByLocation || isNotStill) {
            // Movement detected: Reset the "Safety Clock"
            lastMovementTime = currentTime
            lastSteps = steps
            lastLat = lat
            lastLon = lon
            isCurrentlyStill = isStill
            Log.d("SafetyMonitor", "Watchdog: Movement verified. Safety timer reset.")
        } else {
            // No movement detected: The Watchdog remains alert
            isCurrentlyStill = true
        }
    }

    /**
     * Checks if the user has been inactive long enough to trigger a safety check.
     * @return True if the user is 'Still' AND the inactivity threshold has passed.
     */
    fun isThresholdExceeded(): Boolean {
        val timeSinceLastMovement = System.currentTimeMillis() - lastMovementTime
        return isCurrentlyStill && timeSinceLastMovement >= inactivityThresholdMs
    }

    /**
     * Manually resets the timer (e.g., when user confirms they are okay).
     */
    fun reset() {
        lastMovementTime = System.currentTimeMillis()
        isCurrentlyStill = false
    }
}

