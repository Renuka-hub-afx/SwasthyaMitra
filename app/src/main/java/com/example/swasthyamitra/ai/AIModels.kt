package com.example.swasthyamitra.ai

data class MealRec(
    val item: String,
    val calories: Int,
    val protein: String,
    val reason: String,
    val tip: String = ""
)

data class MealPlan(
    val breakfast: MealRec,
    val lunch: MealRec,
    val snack: MealRec,
    val dinner: MealRec,
    val postWorkout: MealRec? = null,
    val dailyTip: String = ""
)
