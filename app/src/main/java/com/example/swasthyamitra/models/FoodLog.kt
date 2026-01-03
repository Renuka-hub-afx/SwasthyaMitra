package com.example.swasthyamitra.models

data class FoodLog(
    val logId: String = "",
    val userId: String = "",
    val foodName: String = "",
    val barcode: String? = null,
    val photoUrl: String? = null,
    val calories: Int = 0,
    val protein: Double = 0.0,
    val carbs: Double = 0.0,
    val fat: Double = 0.0,
    val servingSize: String = "",
    val mealType: String = "",  // Breakfast, Lunch, Dinner, Snack
    val timestamp: Long = 0L,
    val date: String = ""  // YYYY-MM-DD format
)
