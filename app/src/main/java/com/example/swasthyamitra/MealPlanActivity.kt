package com.example.swasthyamitra

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.example.swasthyamitra.recommendation.FoodRecommendationEngine
import com.example.swasthyamitra.repository.IndianFoodRepository
import com.google.android.material.card.MaterialCardView
import kotlinx.coroutines.launch

class MealPlanActivity : AppCompatActivity() {

    private lateinit var authHelper: FirebaseAuthHelper
    private lateinit var foodRepository: IndianFoodRepository
    private lateinit var recommendationEngine: FoodRecommendationEngine
    private var userId: String = ""

    // Views
    private lateinit var btnBack: ImageView
    private lateinit var ivProfile: ImageView
    private lateinit var profileSummaryCard: MaterialCardView
    private lateinit var tvUserInfo: TextView
    private lateinit var tvCalorieTarget: TextView
    private lateinit var tvProteinTarget: TextView
    private lateinit var tvCarbsTarget: TextView
    private lateinit var tvFatTarget: TextView
    private lateinit var tvExplanation: TextView
    private lateinit var btnGeneratePlan: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var mealPlanContainer: LinearLayout
    private lateinit var tvTotalSummary: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_plan)

        val application = application as? UserApplication
        if (application == null) {
            Toast.makeText(this, "App error", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        authHelper = application.authHelper
        userId = authHelper.getCurrentUser()?.uid ?: ""

        if (userId.isEmpty()) {
            Toast.makeText(this, "Please log in", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        recommendationEngine = FoodRecommendationEngine()
        foodRepository = IndianFoodRepository(this)

        initViews()
        setupListeners()
        loadUserDataAndShowSummary()
    }

    private fun initViews() {
        btnBack = findViewById(R.id.btn_back)
        ivProfile = findViewById(R.id.iv_profile)
        profileSummaryCard = findViewById(R.id.profile_summary_card)
        tvUserInfo = findViewById(R.id.tv_user_info)
        tvCalorieTarget = findViewById(R.id.tv_calorie_target)
        tvProteinTarget = findViewById(R.id.tv_protein_target)
        tvCarbsTarget = findViewById(R.id.tv_carbs_target)
        tvFatTarget = findViewById(R.id.tv_fat_target)
        tvExplanation = findViewById(R.id.tv_explanation)
        btnGeneratePlan = findViewById(R.id.btn_generate_plan)
        progressBar = findViewById(R.id.progress_bar)
        mealPlanContainer = findViewById(R.id.meal_plan_container)
        tvTotalSummary = findViewById(R.id.tv_total_summary)
    }

    private fun setupListeners() {
        btnBack.setOnClickListener { finish() }
        ivProfile.setOnClickListener {
            // Toggle profile summary visibility
            if (profileSummaryCard.visibility == View.VISIBLE) {
                profileSummaryCard.visibility = View.GONE
            } else {
                profileSummaryCard.visibility = View.VISIBLE
            }
        }
        btnGeneratePlan.setOnClickListener { generateMealPlan() }
    }

    private fun loadUserDataAndShowSummary() {
        lifecycleScope.launch {
            try {
                progressBar.visibility = View.VISIBLE
                
                // Load user data
                val userDataResult = authHelper.getUserData(userId)
                val goalResult = authHelper.getUserGoal(userId)

                userDataResult.onSuccess { userData ->
                    goalResult.onSuccess { goal ->
                        val weight = (userData["weight"] as? Number)?.toDouble() ?: 70.0
                        val height = (userData["height"] as? Number)?.toDouble() ?: 170.0
                        val age = (userData["age"] as? Number)?.toInt() ?: 25
                        val gender = userData["gender"] as? String ?: "Male"
                        val activityLevel = goal["activityLevel"] as? String ?: "Sedentary"
                        val goalType = goal["goalType"] as? String ?: "Maintain Weight"
                        val dietPreference = goal["dietPreference"] as? String ?: "Non-Vegetarian"

                        // Create profile
                        val profile = FoodRecommendationEngine.UserProfile(
                            weight = weight,
                            height = height,
                            age = age,
                            gender = gender,
                            activityLevel = activityLevel,
                            goalType = goalType,
                            dietPreference = dietPreference
                        )

                        // Calculate and show targets
                        val target = recommendationEngine.generateNutritionTarget(profile)
                        showNutritionSummary(profile, target)
                    }
                }

                progressBar.visibility = View.GONE
            } catch (e: Exception) {
                progressBar.visibility = View.GONE
                Toast.makeText(this@MealPlanActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showNutritionSummary(
        profile: FoodRecommendationEngine.UserProfile,
        target: FoodRecommendationEngine.DailyNutritionTarget
    ) {
        tvUserInfo.text = """
            Weight: ${profile.weight}kg | Height: ${profile.height}cm
            Goal: ${profile.goalType}
            Activity: ${profile.activityLevel}
        """.trimIndent()

        tvCalorieTarget.text = "${target.calories} kcal/day"
        tvProteinTarget.text = "${target.protein}g protein"
        tvCarbsTarget.text = "${target.carbs}g carbs"
        tvFatTarget.text = "${target.fat}g fat"
        tvExplanation.text = target.explanation
    }

    private fun generateMealPlan() {
        lifecycleScope.launch {
            try {
                progressBar.visibility = View.VISIBLE
                btnGeneratePlan.isEnabled = false

                // Load food database
                foodRepository.loadFoodDatabase()
                val allFoods = foodRepository.getAllFoods()

                if (allFoods.isEmpty()) {
                    Toast.makeText(this@MealPlanActivity, "No food data available", Toast.LENGTH_SHORT).show()
                    progressBar.visibility = View.GONE
                    btnGeneratePlan.isEnabled = true
                    return@launch
                }

                // Get user profile
                val userDataResult = authHelper.getUserData(userId)
                val goalResult = authHelper.getUserGoal(userId)

                userDataResult.onSuccess { userData ->
                    goalResult.onSuccess { goal ->
                        val profile = FoodRecommendationEngine.UserProfile(
                            weight = (userData["weight"] as? Number)?.toDouble() ?: 70.0,
                            height = (userData["height"] as? Number)?.toDouble() ?: 170.0,
                            age = (userData["age"] as? Number)?.toInt() ?: 25,
                            gender = userData["gender"] as? String ?: "Male",
                            activityLevel = goal["activityLevel"] as? String ?: "Sedentary",
                            goalType = goal["goalType"] as? String ?: "Maintain Weight",
                            dietPreference = goal["dietPreference"] as? String ?: "Non-Vegetarian"
                        )

                        // Generate meal plan
                        val mealPlan = recommendationEngine.generateMealPlan(profile, allFoods)
                        displayMealPlan(mealPlan)
                    }
                }

                progressBar.visibility = View.GONE
                btnGeneratePlan.isEnabled = true

            } catch (e: Exception) {
                progressBar.visibility = View.GONE
                btnGeneratePlan.isEnabled = true
                Toast.makeText(this@MealPlanActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun displayMealPlan(mealPlan: FoodRecommendationEngine.MealPlan) {
        // Show meal plan container
        mealPlanContainer.visibility = View.VISIBLE

        // Breakfast
        val tvBreakfastTitle: TextView = findViewById(R.id.tv_breakfast_title)
        val tvBreakfastFoodName: TextView = findViewById(R.id.tv_breakfast_food_name)
        val tvBreakfastFoods: TextView = findViewById(R.id.tv_breakfast_foods)
        val tvBreakfastProtein: TextView = findViewById(R.id.tv_breakfast_protein)
        val tvBreakfastCarbs: TextView = findViewById(R.id.tv_breakfast_carbs)
        val tvBreakfastFat: TextView = findViewById(R.id.tv_breakfast_fat)
        
        tvBreakfastTitle.text = "${mealPlan.breakfast.sumOf { it.calories }} kcal"
        tvBreakfastFoodName.text = mealPlan.breakfast.firstOrNull()?.foodName ?: "No food selected"
        tvBreakfastFoods.text = mealPlan.breakfast.firstOrNull()?.let { 
            "${it.servingSize} serving. ${it.reason}"
        } ?: "No description available"
        tvBreakfastProtein.text = "${mealPlan.breakfast.sumOf { it.protein }.toInt()}.0 P"
        tvBreakfastCarbs.text = "${mealPlan.breakfast.sumOf { it.carbs }.toInt()}.0 C"
        tvBreakfastFat.text = "${mealPlan.breakfast.sumOf { it.fat }.toInt()}.0 F"

        // Morning Snack
        val tvSnack1Title: TextView = findViewById(R.id.tv_morning_snack_title)
        val tvSnack1FoodName: TextView = findViewById(R.id.tv_snack1_food_name)
        val tvMorningSnackFoods: TextView = findViewById(R.id.tv_morning_snack_foods)
        val tvSnack1Protein: TextView = findViewById(R.id.tv_snack1_protein)
        val tvSnack1Carbs: TextView = findViewById(R.id.tv_snack1_carbs)
        val tvSnack1Fat: TextView = findViewById(R.id.tv_snack1_fat)
        
        tvSnack1Title.text = "${mealPlan.morningSnack.sumOf { it.calories }} kcal"
        tvSnack1FoodName.text = mealPlan.morningSnack.firstOrNull()?.foodName ?: "No snack selected"
        tvMorningSnackFoods.text = mealPlan.morningSnack.firstOrNull()?.let { 
            "${it.servingSize} serving. ${it.reason}"
        } ?: "No description available"
        tvSnack1Protein.text = "${mealPlan.morningSnack.sumOf { it.protein }.toInt()}.0 P"
        tvSnack1Carbs.text = "${mealPlan.morningSnack.sumOf { it.carbs }.toInt()}.0 C"
        tvSnack1Fat.text = "${mealPlan.morningSnack.sumOf { it.fat }.toInt()}.0 F"

        // Lunch
        val tvLunchTitle: TextView = findViewById(R.id.tv_lunch_title)
        val tvLunchFoodName: TextView = findViewById(R.id.tv_lunch_food_name)
        val tvLunchFoods: TextView = findViewById(R.id.tv_lunch_foods)
        val tvLunchProtein: TextView = findViewById(R.id.tv_lunch_protein)
        val tvLunchCarbs: TextView = findViewById(R.id.tv_lunch_carbs)
        val tvLunchFat: TextView = findViewById(R.id.tv_lunch_fat)
        
        tvLunchTitle.text = "${mealPlan.lunch.sumOf { it.calories }} kcal"
        tvLunchFoodName.text = mealPlan.lunch.firstOrNull()?.foodName ?: "No food selected"
        tvLunchFoods.text = mealPlan.lunch.firstOrNull()?.let { 
            "${it.servingSize} serving. ${it.reason}"
        } ?: "No description available"
        tvLunchProtein.text = "${mealPlan.lunch.sumOf { it.protein }.toInt()}.0 P"
        tvLunchCarbs.text = "${mealPlan.lunch.sumOf { it.carbs }.toInt()}.0 C"
        tvLunchFat.text = "${mealPlan.lunch.sumOf { it.fat }.toInt()}.0 F"

        // Evening Snack
        val tvSnack2Title: TextView = findViewById(R.id.tv_evening_snack_title)
        val tvSnack2FoodName: TextView = findViewById(R.id.tv_snack2_food_name)
        val tvEveningSnackFoods: TextView = findViewById(R.id.tv_evening_snack_foods)
        val tvSnack2Protein: TextView = findViewById(R.id.tv_snack2_protein)
        val tvSnack2Carbs: TextView = findViewById(R.id.tv_snack2_carbs)
        val tvSnack2Fat: TextView = findViewById(R.id.tv_snack2_fat)
        
        tvSnack2Title.text = "${mealPlan.eveningSnack.sumOf { it.calories }} kcal"
        tvSnack2FoodName.text = mealPlan.eveningSnack.firstOrNull()?.foodName ?: "No snack selected"
        tvEveningSnackFoods.text = mealPlan.eveningSnack.firstOrNull()?.let { 
            "${it.servingSize} serving. ${it.reason}"
        } ?: "No description available"
        tvSnack2Protein.text = "${mealPlan.eveningSnack.sumOf { it.protein }.toInt()}.0 P"
        tvSnack2Carbs.text = "${mealPlan.eveningSnack.sumOf { it.carbs }.toInt()}.0 C"
        tvSnack2Fat.text = "${mealPlan.eveningSnack.sumOf { it.fat }.toInt()}.0 F"

        // Dinner
        val tvDinnerTitle: TextView = findViewById(R.id.tv_dinner_title)
        val tvDinnerFoodName: TextView = findViewById(R.id.tv_dinner_food_name)
        val tvDinnerFoods: TextView = findViewById(R.id.tv_dinner_foods)
        val tvDinnerProtein: TextView = findViewById(R.id.tv_dinner_protein)
        val tvDinnerCarbs: TextView = findViewById(R.id.tv_dinner_carbs)
        val tvDinnerFat: TextView = findViewById(R.id.tv_dinner_fat)
        
        tvDinnerTitle.text = "${mealPlan.dinner.sumOf { it.calories }} kcal"
        tvDinnerFoodName.text = mealPlan.dinner.firstOrNull()?.foodName ?: "No food selected"
        tvDinnerFoods.text = mealPlan.dinner.firstOrNull()?.let { 
            "${it.servingSize} serving. ${it.reason}"
        } ?: "No description available"
        tvDinnerProtein.text = "${mealPlan.dinner.sumOf { it.protein }.toInt()}.0 P"
        tvDinnerCarbs.text = "${mealPlan.dinner.sumOf { it.carbs }.toInt()}.0 C"
        tvDinnerFat.text = "${mealPlan.dinner.sumOf { it.fat }.toInt()}.0 F"

        // Total Summary
        tvTotalSummary.text = """
            📊 Daily Total:
            ${mealPlan.totalCalories} kcal | ${mealPlan.totalProtein}g protein
            ${mealPlan.totalCarbs}g carbs | ${mealPlan.totalFat}g fat
            
            🎯 You're on track to meet your daily nutrition goals!
        """.trimIndent()
    }
}
