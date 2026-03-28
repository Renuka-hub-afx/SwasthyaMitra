package com.example.swasthyamitra.models

import com.google.firebase.firestore.GeoPoint

// Enhanced GPS-validated step session stored in Firestore: users/{uid}/step_sessions/{sessionId}
data class StepSession(
    val id: String = "",
    val userId: String = "",
    val startTime: Long = 0L,
    var endTime: Long = 0L,

    // Steps — validatedSteps < rawSteps because false positives are filtered out
    var validatedSteps: Int = 0,
    var rawSteps: Int = 0,

    // Distance & movement
    var totalDistanceMeters: Double = 0.0,
    var routePoints: List<GeoPoint> = emptyList(), // GPS coordinates for route drawing
    var averagePace: Double = 0.0,                 // minutes per km
    var averageSpeedKmh: Double = 0.0,

    // Calibration — user's stride length used to convert steps → distance
    var averageStrideM: Double = 0.72,

    // Quality — higher score = more reliable step count (GPS confirmed movement)
    var confidenceScore: Double = 0.0,

    // Calories
    var caloriesBurned: Double = 0.0,

    // Activity breakdown (seconds spent in each mode)
    var walkingSeconds: Long = 0L,
    var runningSeconds: Long = 0L,
    var stillSeconds: Long = 0L,

    // Hourly breakdown — e.g. {"08" -> 1200, "09" -> 800}
    var hourlySteps: Map<String, Int> = emptyMap()
)
