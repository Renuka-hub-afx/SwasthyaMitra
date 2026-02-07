package com.example.swasthyamitra.ai

/**
 * Top-level data classes for Diet Plans
 */
data class MealPlan(
    val breakfast: MealRec,
    val lunch: MealRec,
    val snack: MealRec,
    val dinner: MealRec,
    val postWorkout: MealRec? = null,
    val dailyTip: String = ""
)

data class MealRec(
    val item: String,
    val calories: Int,
    val protein: String,
    val reason: String,
    val tip: String = ""
)
