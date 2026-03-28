package com.example.swasthyamitra.models

import com.google.firebase.firestore.GeoPoint

// Basic walking session — a lighter version of StepSession without GPS validation
data class WalkingSession(
    val id: String = "",
    val userId: String = "",
    val startTime: Long = 0L,
    var endTime: Long = 0L,
    var totalSteps: Int = 0,
    var totalDistanceMeters: Double = 0.0,
    var routePoints: List<GeoPoint> = emptyList(), // GPS coordinates for map route
    var averagePace: Double = 0.0                  // minutes per km
)
