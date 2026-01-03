package com.example.swasthyamitra.models

data class IndianFood(
    val foodName: String,
    val servingSize: String = "100g",
    val calories: Int,
    val protein: Double,
    val carbs: Double,
    val fat: Double,
    val fiber: Double = 0.0,
    val category: String = ""
)
