package com.example.swasthyamitra.models

// Nutritional data for one Indian food item loaded from food_data.json/csv assets
data class IndianFood(
    val foodName: String,
    val servingSize: String = "100g",
    val calories: Int,
    val protein: Double,
    val carbs: Double,
    val fat: Double,
    val fiber: Double = 0.0,
    val category: String = ""   // "Veg", "NonVeg", "Vegan" etc.
)
