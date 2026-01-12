package com.example.swasthyamitra

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swasthyamitra.api.EdamamApi
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import java.util.*
import kotlin.math.roundToInt

/**
 * DietRecommendationActivity
 * Calculates BMR/TDEE and fetches personalized food recommendations from Edamam API
 */
class DietRecommendationActivity : AppCompatActivity() {

    // UI Components
    private lateinit var etWeight: TextInputEditText
    private lateinit var etHeight: TextInputEditText
    private lateinit var etAge: TextInputEditText
    private lateinit var rgGender: RadioGroup
    private lateinit var rbMale: RadioButton
    private lateinit var rbFemale: RadioButton
    private lateinit var rgGoal: RadioGroup
    private lateinit var rbLose: RadioButton
    private lateinit var rbMaintain: RadioButton
    private lateinit var rbGain: RadioButton
    private lateinit var btnGetPlan: Button
    private lateinit var rvFoodRecommendations: RecyclerView
    private lateinit var cardResults: View
    private lateinit var tvBmr: TextView
    private lateinit var tvTargetCalories: TextView
    private lateinit var tvRecommendationsTitle: TextView

    // Adapter
    private lateinit var foodAdapter: FoodAdapter

    // API
    private val edamamApi = EdamamApi.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diet_recommendation)

        initializeViews()
        setupRecyclerView()
        setupListeners()
    }

    private fun initializeViews() {
        etWeight = findViewById(R.id.etWeight)
        etHeight = findViewById(R.id.etHeight)
        etAge = findViewById(R.id.etAge)
        rgGender = findViewById(R.id.rgGender)
        rbMale = findViewById(R.id.rbMale)
        rbFemale = findViewById(R.id.rbFemale)
        rgGoal = findViewById(R.id.rgGoal)
        rbLose = findViewById(R.id.rbLose)
        rbMaintain = findViewById(R.id.rbMaintain)
        rbGain = findViewById(R.id.rbGain)
        btnGetPlan = findViewById(R.id.btnGetPlan)
        rvFoodRecommendations = findViewById(R.id.rvFoodRecommendations)
        cardResults = findViewById(R.id.cardResults)
        tvBmr = findViewById(R.id.tvBmr)
        tvTargetCalories = findViewById(R.id.tvTargetCalories)
        tvRecommendationsTitle = findViewById(R.id.tvRecommendationsTitle)

        // Setup toolbar
        findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar).apply {
            setNavigationOnClickListener { finish() }
        }
    }

    private fun setupRecyclerView() {
        foodAdapter = FoodAdapter()
        rvFoodRecommendations.apply {
            layoutManager = LinearLayoutManager(this@DietRecommendationActivity)
            adapter = foodAdapter
        }
    }

    private fun setupListeners() {
        btnGetPlan.setOnClickListener {
            if (validateInputs()) {
                calculateAndFetchRecommendations()
            }
        }
    }

    private fun validateInputs(): Boolean {
        val weight = etWeight.text.toString()
        val height = etHeight.text.toString()
        val age = etAge.text.toString()

        if (weight.isEmpty()) {
            etWeight.error = "Please enter weight"
            return false
        }
        if (height.isEmpty()) {
            etHeight.error = "Please enter height"
            return false
        }
        if (age.isEmpty()) {
            etAge.error = "Please enter age"
            return false
        }

        return true
    }

    private fun calculateAndFetchRecommendations() {
        // Get input values
        val weight = etWeight.text.toString().toDouble()
        val height = etHeight.text.toString().toDouble()
        val age = etAge.text.toString().toInt()
        val isMale = rbMale.isChecked
        val goal = when {
            rbLose.isChecked -> "lose"
            rbGain.isChecked -> "gain"
            else -> "maintain"
        }

        // Calculate BMR using Mifflin-St Jeor Equation
        val bmr = if (isMale) {
            (10 * weight) + (6.25 * height) - (5 * age) + 5
        } else {
            (10 * weight) + (6.25 * height) - (5 * age) - 161
        }

        // Calculate TDEE (assuming moderate activity level)
        val tdee = bmr * 1.55

        // Adjust for goal
        val targetCalories = when (goal) {
            "lose" -> tdee - 500
            "gain" -> tdee + 500
            else -> tdee
        }

        // Update UI
        tvBmr.text = "${bmr.roundToInt()} kcal"
        tvTargetCalories.text = "${targetCalories.roundToInt()} kcal"
        cardResults.visibility = View.VISIBLE

        // Fetch food recommendations
        fetchFoodRecommendations(targetCalories, goal)
    }

    private fun fetchFoodRecommendations(targetCalories: Double, goal: String) {
        lifecycleScope.launch {
            try {
                // Determine search query based on time of day and goal
                val query = getSearchQuery(goal)
                
                // Calculate calorie range per meal (assuming 3 meals)
                val caloriesPerMeal = targetCalories / 3
                val minCalories = (caloriesPerMeal * 0.8).roundToInt()
                val maxCalories = (caloriesPerMeal * 1.2).roundToInt()
                val calorieRange = "$minCalories-$maxCalories"

                // Call Edamam API
                val response = edamamApi.searchRecipes(
                    query = query,
                    appId = EdamamApi.APP_ID,
                    appKey = EdamamApi.APP_KEY,
                    calories = calorieRange,
                    from = 0,
                    to = 20
                )

                if (response.isSuccessful) {
                    val recipes = response.body()?.hits?.map { it.recipe } ?: emptyList()
                    
                    // Update RecyclerView
                    foodAdapter.updateRecipes(recipes)
                    rvFoodRecommendations.visibility = View.VISIBLE
                    tvRecommendationsTitle.visibility = View.VISIBLE

                    if (recipes.isEmpty()) {
                        Toast.makeText(
                            this@DietRecommendationActivity,
                            "No recommendations found. Try adjusting your inputs.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@DietRecommendationActivity,
                        "Error: ${response.message()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                Toast.makeText(
                    this@DietRecommendationActivity,
                    "Error: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
                e.printStackTrace()
            }
        }
    }

    /**
     * Determine search query based on goal and time of day
     */
    private fun getSearchQuery(goal: String): String {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        
        return when {
            hour in 6..10 -> "healthy breakfast"
            hour in 11..14 -> when (goal) {
                "lose" -> "healthy low calorie lunch"
                "gain" -> "high protein lunch"
                else -> "healthy lunch"
            }
            hour in 15..17 -> "healthy snack"
            hour in 18..21 -> when (goal) {
                "lose" -> "healthy low calorie dinner"
                "gain" -> "high protein dinner"
                else -> "healthy dinner"
            }
            else -> when (goal) {
                "lose" -> "healthy low calorie"
                "gain" -> "high protein"
                else -> "healthy"
            }
        }
    }
}
