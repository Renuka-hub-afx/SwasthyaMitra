package com.example.swasthyamitra.ai

// Data models for AI-generated meal plans and individual meal recommendations

// Complete daily meal plan containing all four major meal slots
data class MealPlan(
    val breakfast: MealRec,     // Morning meal recommendation (6-10 AM)
    val lunch: MealRec,         // Midday meal recommendation (12-2 PM)
    val snack: MealRec,         // Light snack recommendation (4-6 PM)
    val dinner: MealRec,        // Evening meal recommendation (7-9 PM)
    val postWorkout: MealRec? = null,  // Optional post-exercise nutrition (protein-focused)
    val dailyTip: String = ""   // AI-generated daily nutritional advice or health tip
)

// Individual meal recommendation with nutritional data and AI reasoning
data class MealRec(
    val item: String,           // Name of the recommended food item or dish
    val calories: Int,          // Total calorie content per serving
    val protein: String,        // Protein content (usually in grams with 'g' suffix)
    val reason: String,         // AI explanation for why this meal was recommended
    val tip: String = ""        // Optional cooking tip or nutritional advice for this meal
)
