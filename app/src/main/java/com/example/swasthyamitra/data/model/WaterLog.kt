package com.example.swasthyamitra.data.model

import com.google.firebase.firestore.PropertyName

data class WaterLog(
    val logId: String = "",
    val userId: String = "",
    val amountML: Int = 0,
    val timestamp: Long = System.currentTimeMillis(),
    val date: String = "" // YYYY-MM-DD
)
