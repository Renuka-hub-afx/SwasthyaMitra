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
     * Generate full day meal plan with variety
     */
    fun generateMealPlan(
        profile: UserProfile,
        availableFoods: List<IndianFood>
    ): MealPlan {
        val target = generateNutritionTarget(profile)
        
        // Define non-vegetarian categories/foods
        val nonVegCategories = listOf("meat", "seafood", "eggs")
        val nonVegKeywords = listOf("chicken", "mutton", "fish", "egg", "prawn", "lamb", "beef", "pork")
        
        // Filter by diet preference
        val filteredFoods = availableFoods.filter { food ->
            when (profile.dietPreference.lowercase()) {
                "vegetarian", "veg" -> {
                    val categoryOk = !nonVegCategories.any { food.category.equals(it, ignoreCase = true) }
                    val nameOk = !nonVegKeywords.any { food.foodName.contains(it, ignoreCase = true) }
                    categoryOk && nameOk
                }
                "vegan" -> {
                    val dairyKeywords = listOf("paneer", "curd", "dahi", "milk", "lassi", "butter", "ghee", "cheese", "cream", "kheer")
                    val categoryOk = !nonVegCategories.any { food.category.equals(it, ignoreCase = true) }
                    val notDairy = !food.category.equals("dairy", ignoreCase = true)
                    val nameOk = !nonVegKeywords.any { food.foodName.contains(it, ignoreCase = true) }
                    val dairyNameOk = !dairyKeywords.any { food.foodName.contains(it, ignoreCase = true) }
                    categoryOk && notDairy && nameOk && dairyNameOk
                }
                else -> true
            }
        }
        
        val foodsToUse = if (filteredFoods.isEmpty()) availableFoods else filteredFoods
        
        // Track used foods to ensure variety
        val usedFoods = mutableSetOf<String>()

        // Distribute calories: Breakfast 25%, Lunch 30%, Dinner 25%, Snacks 20%
        val breakfastCals = (target.calories * 0.25).roundToInt()
        val morningSnackCals = (target.calories * 0.10).roundToInt()
        val lunchCals = (target.calories * 0.30).roundToInt()
        val eveningSnackCals = (target.calories * 0.10).roundToInt()
        val dinnerCals = (target.calories * 0.25).roundToInt()

        // Generate each meal, passing usedFoods to avoid repetition
        val breakfast = selectFoodsForMealWithVariety(foodsToUse, breakfastCals, "Breakfast", profile.goalType, usedFoods)
        val morningSnack = selectFoodsForMealWithVariety(foodsToUse, morningSnackCals, "MorningSnack", profile.goalType, usedFoods)
        val lunch = selectFoodsForMealWithVariety(foodsToUse, lunchCals, "Lunch", profile.goalType, usedFoods)
        val eveningSnack = selectFoodsForMealWithVariety(foodsToUse, eveningSnackCals, "EveningSnack", profile.goalType, usedFoods)
        val dinner = selectFoodsForMealWithVariety(foodsToUse, dinnerCals, "Dinner", profile.goalType, usedFoods)

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
     * Select foods for specific meal with variety (no repetition)
     */
    private fun selectFoodsForMealWithVariety(
        foods: List<IndianFood>,
        targetCalories: Int,
        mealType: String,
        goalType: String,
        usedFoods: MutableSet<String>
    ): List<FoodRecommendation> {
        val recommendations = mutableListOf<FoodRecommendation>()
        var remainingCalories = targetCalories

        // Filter foods by meal type using category and keywords
        val mealFoods = when (mealType) {
            "Breakfast" -> foods.filter { food ->
                food.category.equals("Breakfast", ignoreCase = true) ||
                food.foodName.contains(Regex("idli|dosa|poha|upma|paratha|bread|oats|uttapam|vada|puri|chilla|porridge|besan", RegexOption.IGNORE_CASE))
            }
            "Lunch" -> foods.filter { food ->
                food.category.lowercase() in listOf("grains", "legumes", "vegetables", "curry", "meat", "seafood", "rice dishes", "bread") ||
                food.foodName.contains(Regex("rice|roti|chapati|dal|curry|sabzi|chicken|fish|rajma|chole|biryani|pulao|masala|gobi|palak|bhindi|matar|sambar", RegexOption.IGNORE_CASE))
            }
            "Dinner" -> foods.filter { food ->
                food.category.lowercase() in listOf("grains", "legumes", "vegetables", "curry", "meat", "seafood", "bread") ||
                food.foodName.contains(Regex("roti|chapati|dal|curry|sabzi|chicken|fish|khichdi|soup|salad|paneer|palak|gobi", RegexOption.IGNORE_CASE))
            }
            "MorningSnack" -> foods.filter { food ->
                food.category.lowercase() in listOf("fruits", "dairy", "beverages", "snacks") ||
                food.foodName.contains(Regex("fruit|banana|apple|orange|lassi|buttermilk|sprouts|chaat|dhokla|idli", RegexOption.IGNORE_CASE)) ||
                food.calories in 80..200
            }
            "EveningSnack" -> foods.filter { food ->
                food.category.lowercase() in listOf("snacks", "nuts", "fruits", "beverages") ||
                food.foodName.contains(Regex("nuts|almonds|walnuts|makhana|pakora|samosa|chaat|tea|coffee|biscuit|roasted", RegexOption.IGNORE_CASE)) ||
                food.calories in 80..250
            }
            else -> foods
        }
        
        // Exclude already used foods for variety
        val availableMealFoods = mealFoods.filter { it.foodName !in usedFoods }
            .ifEmpty { foods.filter { it.foodName !in usedFoods } }
            .ifEmpty { foods } // Last resort: allow repeats if everything is used

        // Sort and randomize based on goal
        val sortedFoods = when (goalType) {
            "Lose Weight" -> {
                // Prioritize high protein, low calorie foods with some randomization
                availableMealFoods
                    .sortedByDescending { (it.protein * 2) / (it.calories + 1).toDouble() + kotlin.random.Random.nextDouble(0.0, 0.3) }
            }
            "Gain Muscle" -> {
                // Prioritize protein and carbs with some randomization
                availableMealFoods
                    .sortedByDescending { it.protein + (it.carbs * 0.3) + kotlin.random.Random.nextDouble(0.0, 5.0) }
            }
            else -> availableMealFoods.shuffled()
        }

        // Select 2-3 foods per meal
        val foodsToSelect = sortedFoods.take(10).shuffled().take(3)
        
        foodsToSelect.forEach { food ->
            if (remainingCalories > 50) {
                val portion = (remainingCalories.toDouble() / food.calories).coerceIn(0.5, 2.0)
                val adjustedCalories = (food.calories * portion).roundToInt()

                recommendations.add(
                    FoodRecommendation(
                        foodName = food.foodName,
                        servingSize = when {
                            portion >= 1.5 -> "2 servings"
                            portion >= 0.9 -> "1 serving"
                            else -> "½ serving"
                        },
                        calories = adjustedCalories,
                        protein = food.protein * portion,
                        carbs = food.carbs * portion,
                        fat = food.fat * portion,
                        reason = generateReasonDetailed(food, goalType, mealType)
                    )
                )
                
                // Mark as used
                usedFoods.add(food.foodName)
                remainingCalories -= adjustedCalories
            }
        }

        return recommendations
    }
    
    private fun generateReasonDetailed(food: IndianFood, goalType: String, mealType: String): String {
        val mealContext = when (mealType) {
            "Breakfast" -> "Great way to start your day"
            "Lunch" -> "Perfect for sustained energy"
            "Dinner" -> "Light yet satisfying"
            "MorningSnack" -> "Keeps hunger at bay"
            "EveningSnack" -> "Healthy evening option"
            else -> "Nutritious choice"
        }
        
        return when (goalType) {
            "Lose Weight" -> when {
                food.protein > 12 -> "High protein keeps you full longer"
                food.calories < 150 -> "Low calorie, guilt-free choice"
                food.fiber > 3 -> "High fiber aids digestion"
                else -> mealContext
            }
            "Gain Muscle" -> when {
                food.protein > 15 -> "Excellent protein source for muscle building"
                food.carbs > 30 -> "Complex carbs fuel your workouts"
                food.calories > 250 -> "Calorie-dense for muscle gain"
                else -> mealContext
            }
            else -> when {
                food.protein > 10 && food.carbs > 20 -> "Balanced macros for overall health"
                else -> mealContext
            }
        }
    }

    private data class Quadruple<A, B, C, D>(val first: A, val second: B, val third: C, val fourth: D)
}
