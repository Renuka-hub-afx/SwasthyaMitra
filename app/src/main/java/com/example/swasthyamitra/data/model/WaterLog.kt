package com.example.swasthyamitra.data.model

// Firebase Firestore annotation for property name mapping
import com.google.firebase.firestore.PropertyName

// Data model for water intake logging entries in the hydration tracking system
data class WaterLog(
    val logId: String = "",                           // Unique identifier for this water log entry
    val userId: String = "",                          // Firebase user ID for data ownership
    val amountML: Int = 0,                           // Water amount consumed in milliliters
    val timestamp: Long = System.currentTimeMillis(), // Unix timestamp when water was logged
    val date: String = ""                            // Date string in YYYY-MM-DD format for daily aggregation
)
