package com.example.swasthyamitra.recommendation

import com.example.swasthyamitra.models.IndianFood
import kotlin.math.roundToInt

/**
 * Food Recommendation Engine
 * Generates personalized meal plans based on user goals
 */
class FoodRecommendationEngine {

    // User profile data structure
    data class UserProfile(
        val weight: Double,      // kg
        val height: Double,      // cm
        val age: Int,
        val gender: String,      // "Male"/"Female"
        val activityLevel: String,
        val goalType: String,    // "Lose Weight"/"Gain Muscle"/"Maintain Weight"
        val dietPreference: String
    )

    // Daily nutrition targets
    data class DailyNutritionTarget(
        val calories: Int,
        val protein: Int,  // grams
        val carbs: Int,    // grams
        val fat: Int,      // grams
        val explanation: String
    )

    // Complete meal plan
    data class MealPlan(
        val breakfast: List<FoodRecommendation>,
        val morningSnack: List<FoodRecommendation>,
        val lunch: List<FoodRecommendation>,
        val eveningSnack: List<FoodRecommendation>,
        val dinner: List<FoodRecommendation>,
        val totalCalories: Int,
        val totalProtein: Int,
        val totalCarbs: Int,
        val totalFat: Int
    )

    // Individual food recommendation
    data class FoodRecommendation(
        val foodName: String,
        val servingSize: String,
        val calories: Int,
        val protein: Double,
        val carbs: Double,
        val fat: Double,
        val reason: String
    )

    /**
     * Calculate BMR using Mifflin-St Jeor Equation
     * Most accurate for modern populations
     */
    fun calculateBMR(weight: Double, height: Double, age: Int, gender: String): Double {
        return if (gender.equals("Male", ignoreCase = true)) {
            (10 * weight) + (6.25 * height) - (5 * age) + 5
        } else {
            (10 * weight) + (6.25 * height) - (5 * age) - 161
        }
    }

    /**
     * Calculate TDEE (Total Daily Energy Expenditure)
     */
    fun calculateTDEE(bmr: Double, activityLevel: String): Double {
        val multiplier = when (activityLevel) {
            "Sedentary" -> 1.2
            "Lightly Active" -> 1.375
            "Moderately Active" -> 1.55
            "Very Active" -> 1.725
            else -> 1.2
        }
        return bmr * multiplier
    }

    /**
     * Calculate target calories based on goal
     */
    fun calculateTargetCalories(tdee: Double, goalType: String): Int {
        return when (goalType) {
            "Lose Weight" -> (tdee - 500).roundToInt()  // 500 cal deficit
            "Gain Muscle" -> (tdee + 300).roundToInt()  // 300 cal surplus
            else -> tdee.roundToInt()  // Maintenance
        }
    }

    /**
     * Calculate macro distribution
     */
    fun calculateMacros(targetCalories: Int, goalType: String): DailyNutritionTarget {
        val (proteinPercent, carbsPercent, fatPercent, explanation) = when (goalType) {
            "Lose Weight" -> Quadruple(0.35, 0.35, 0.30, "High protein preserves muscle")
            "Gain Muscle" -> Quadruple(0.30, 0.45, 0.25, "High protein for muscle growth")
            else -> Quadruple(0.30, 0.40, 0.30, "Balanced nutrition")
        }

        val proteinGrams = ((targetCalories * proteinPercent) / 4).roundToInt()
        val carbsGrams = ((targetCalories * carbsPercent) / 4).roundToInt()
        val fatGrams = ((targetCalories * fatPercent) / 9).roundToInt()

        return DailyNutritionTarget(
            calories = targetCalories,
            protein = proteinGrams,
            carbs = carbsGrams,
            fat = fatGrams,
            explanation = explanation
        )
    }

    /**
     * Generate complete nutrition target
     */
    fun generateNutritionTarget(profile: UserProfile): DailyNutritionTarget {
        val bmr = calculateBMR(profile.weight, profile.height, profile.age, profile.gender)
        val tdee = calculateTDEE(bmr, profile.activityLevel)
        val targetCalories = calculateTargetCalories(tdee, profile.goalType)
        return calculateMacros(targetCalories, profile.goalType)
    }

    /**
     * Generate full day meal plan
     */
    fun generateMealPlan(
        profile: UserProfile,
        availableFoods: List<IndianFood>
    ): MealPlan {
        val target = generateNutritionTarget(profile)
        
        // Filter by diet preference
        val filteredFoods = availableFoods.filter { food ->
            when (profile.dietPreference) {
                "Vegetarian" -> food.category.equals("vegetarian", ignoreCase = true)
                "Vegan" -> food.category.equals("vegan", ignoreCase = true)
                else -> true
            }
        }

        // Distribute calories: Breakfast 25%, Lunch 30%, Dinner 25%, Snacks 20%
        val breakfastCals = (target.calories * 0.25).roundToInt()
        val morningSnackCals = (target.calories * 0.10).roundToInt()
        val lunchCals = (target.calories * 0.30).roundToInt()
        val eveningSnackCals = (target.calories * 0.10).roundToInt()
        val dinnerCals = (target.calories * 0.25).roundToInt()

        val breakfast = selectFoodsForMeal(filteredFoods, breakfastCals, "Breakfast", profile.goalType)
        val morningSnack = selectFoodsForMeal(filteredFoods, morningSnackCals, "Snack", profile.goalType)
        val lunch = selectFoodsForMeal(filteredFoods, lunchCals, "Lunch", profile.goalType)
        val eveningSnack = selectFoodsForMeal(filteredFoods, eveningSnackCals, "Snack", profile.goalType)
        val dinner = selectFoodsForMeal(filteredFoods, dinnerCals, "Dinner", profile.goalType)

        val allMeals = breakfast + morningSnack + lunch + eveningSnack + dinner
        
        return MealPlan(
            breakfast = breakfast,
            morningSnack = morningSnack,
            lunch = lunch,
            eveningSnack = eveningSnack,
            dinner = dinner,
            totalCalories = allMeals.sumOf { it.calories },
            totalProtein = allMeals.sumOf { it.protein }.roundToInt(),
            totalCarbs = allMeals.sumOf { it.carbs }.roundToInt(),
            totalFat = allMeals.sumOf { it.fat }.roundToInt()
        )
    }

    /**
     * Select foods for specific meal
     */
    private fun selectFoodsForMeal(
        foods: List<IndianFood>,
        targetCalories: Int,
        mealType: String,
        goalType: String
    ): List<FoodRecommendation> {
        val recommendations = mutableListOf<FoodRecommendation>()
        var remainingCalories = targetCalories

        // Filter foods by meal type
        val mealFoods = when (mealType) {
            "Breakfast" -> foods.filter { 
                it.foodName.contains(Regex("idli|dosa|poha|upma|paratha|egg|bread|oats", RegexOption.IGNORE_CASE))
            }
            "Lunch", "Dinner" -> foods.filter {
                it.foodName.contains(Regex("rice|roti|dal|curry|sabzi|chicken|fish|paneer", RegexOption.IGNORE_CASE))
            }
            "Snack" -> foods.filter {
                it.foodName.contains(Regex("fruit|nuts|salad|sprouts", RegexOption.IGNORE_CASE)) || 
                it.calories in 50..200
            }
            else -> foods
        }

        // Sort by goal
        val sortedFoods = when (goalType) {
            "Lose Weight" -> mealFoods.sortedByDescending { it.protein / (it.calories + 1) }
            "Gain Muscle" -> mealFoods.sortedByDescending { it.protein }
            else -> mealFoods.shuffled()
        }.take(20)

        // Select 2-3 foods per meal
        sortedFoods.take(3).forEach { food ->
            if (remainingCalories > 0) {
                val portion = (remainingCalories.toDouble() / food.calories).coerceAtMost(2.0)
                val adjustedCalories = (food.calories * portion).roundToInt()

                recommendations.add(
                    FoodRecommendation(
                        foodName = food.foodName,
                        servingSize = if (portion >= 1) "${portion.roundToInt()} serving" else "½ serving",
                        calories = adjustedCalories,
                        protein = food.protein * portion,
                        carbs = food.carbs * portion,
                        fat = food.fat * portion,
                        reason = generateReason(food, goalType)
                    )
                )
                remainingCalories -= adjustedCalories
            }
        }

        return recommendations
    }

    private fun generateReason(food: IndianFood, goalType: String): String {
        return when (goalType) {
            "Lose Weight" -> when {
                food.protein > 15 -> "High protein keeps you full"
                food.calories < 200 -> "Low calorie option"
                else -> "Nutritious choice"
            }
            "Gain Muscle" -> when {
                food.protein > 20 -> "Excellent protein for muscle"
                food.carbs > 40 -> "Energy for workouts"
                else -> "Good for muscle growth"
            }
            else -> "Balanced nutrition"
        }
    }

    private data class Quadruple<A, B, C, D>(val first: A, val second: B, val third: C, val fourth: D)
}
