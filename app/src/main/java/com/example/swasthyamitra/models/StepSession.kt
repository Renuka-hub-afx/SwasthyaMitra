package com.example.swasthyamitra.models

import com.google.firebase.firestore.GeoPoint

/**
 * Enhanced walking/step session model that extends the basic WalkingSession
 * with GPS-validated step data, stride calibration, and confidence scoring.
 *
 * Stored in Firestore: users/{uid}/step_sessions/{sessionId}
 */
data class StepSession(
    val id: String = "",
    val userId: String = "",
    val startTime: Long = 0L,
    var endTime: Long = 0L,

    // Steps
    var validatedSteps: Int = 0,
    var rawSteps: Int = 0,

    // Distance & movement
    var totalDistanceMeters: Double = 0.0,
    var routePoints: List<GeoPoint> = emptyList(),
    var averagePace: Double = 0.0,           // minutes per km
    var averageSpeedKmh: Double = 0.0,

    // Calibration
    var averageStrideM: Double = 0.72,

    // Quality
    var confidenceScore: Double = 0.0,

    // Calories
    var caloriesBurned: Double = 0.0,

    // Activity breakdown (seconds spent in each)
    var walkingSeconds: Long = 0L,
    var runningSeconds: Long = 0L,
    var stillSeconds: Long = 0L,

    // Hourly step breakdown
    var hourlySteps: Map<String, Int> = emptyMap()  // "HH" -> steps
)
