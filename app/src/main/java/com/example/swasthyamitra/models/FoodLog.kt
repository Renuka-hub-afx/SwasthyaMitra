package com.example.swasthyamitra.models

// Represents a single food item the user has logged (Breakfast/Lunch/Dinner/Snack)
data class FoodLog(
    val logId: String = "",
    val userId: String = "",
    val foodName: String = "",
    val barcode: String? = null,      // filled when food was scanned via barcode
    val photoUrl: String? = null,     // Smart Pantry photo URL if logged from camera
    val calories: Int = 0,
    val protein: Double = 0.0,
    val carbs: Double = 0.0,
    val fat: Double = 0.0,
    val servingSize: String = "",
    val mealType: String = "",        // "Breakfast", "Lunch", "Dinner", "Snack"
    val timestamp: Long = 0L,
    val date: String = ""             // YYYY-MM-DD format for date-based queries
)
