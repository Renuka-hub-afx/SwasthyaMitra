package com.example.swasthyamitra.models

import com.google.firebase.firestore.GeoPoint

data class WalkingSession(
    val id: String = "",
    val userId: String = "",
    val startTime: Long = 0L,
    var endTime: Long = 0L,
    var totalSteps: Int = 0,
    var totalDistanceMeters: Double = 0.0,
    var routePoints: List<GeoPoint> = emptyList(),
    var averagePace: Double = 0.0 // minutes per km
)
