package com.example.swasthyamitra

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.swasthyamitra.ai.AIDietPlanService
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AISmartDietActivity : AppCompatActivity() {

    private lateinit var aiService: AIDietPlanService
    private lateinit var authHelper: FirebaseAuthHelper
    private lateinit var progressBar: ProgressBar
    private lateinit var tvDailyTip: TextView
    private var isGenerating = false
    private var currentPlan: AIDietPlanService.MealPlan? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ai_smart_diet)

        try {
            // Initialize Services
            aiService = AIDietPlanService.getInstance(this)
            authHelper = FirebaseAuthHelper(this)
            
            // Initialize Views
            progressBar = findViewById(R.id.progressBar)
            tvDailyTip = findViewById(R.id.tvDailyTip)

            // Back button
            findViewById<android.widget.ImageButton>(R.id.btnBack).setOnClickListener {
                finish()
            }
            
            // Individual meal generation buttons
            findViewById<Button>(R.id.btnGenerateBreakfast).setOnClickListener {
                generateSingleMeal("Breakfast")
            }
            findViewById<Button>(R.id.btnGenerateLunch).setOnClickListener {
                generateSingleMeal("Lunch")
            }
            findViewById<Button>(R.id.btnGenerateDinner).setOnClickListener {
                generateSingleMeal("Dinner")
            }
            findViewById<Button>(R.id.btnGenerateSnack).setOnClickListener {
                generateSingleMeal("Snack")
            }


            // Status Views



            setupMealActionButtons()
            setupShareButton()
        } catch (e: Throwable) {
             e.printStackTrace()
             AlertDialog.Builder(this)
                .setTitle("Startup Error")
                .setMessage(e.toString() + "\n" + e.stackTraceToString())
                .setPositiveButton("Close") { _, _ -> finish() }
                .show()
        }
    }

    private val TAG = "AI_DIET_ACTIVITY"

    private fun setupMealActionButtons() {
        // Breakfast Actions
        findViewById<Button>(R.id.btnBreakfastAte).setOnClickListener {
            handleMealAction("Breakfast", currentPlan?.breakfast, "Ate")
        }
        findViewById<Button>(R.id.btnBreakfastSkipped).setOnClickListener {
            handleMealAction("Breakfast", currentPlan?.breakfast, "Skipped")
        }
        findViewById<Button>(R.id.btnBreakfastRegenerate).setOnClickListener {
            regenerateMeal("Breakfast", currentPlan?.breakfast?.item)
        }

        // Lunch Actions
        findViewById<Button>(R.id.btnLunchAte).setOnClickListener {
            handleMealAction("Lunch", currentPlan?.lunch, "Ate")
        }
        findViewById<Button>(R.id.btnLunchSkipped).setOnClickListener {
            handleMealAction("Lunch", currentPlan?.lunch, "Skipped")
        }
        findViewById<Button>(R.id.btnLunchRegenerate).setOnClickListener {
            regenerateMeal("Lunch", currentPlan?.lunch?.item)
        }

        // Dinner Actions
        findViewById<Button>(R.id.btnDinnerAte).setOnClickListener {
            handleMealAction("Dinner", currentPlan?.dinner, "Ate")
        }
        findViewById<Button>(R.id.btnDinnerSkipped).setOnClickListener {
            handleMealAction("Dinner", currentPlan?.dinner, "Skipped")
        }
        findViewById<Button>(R.id.btnDinnerRegenerate).setOnClickListener {
            regenerateMeal("Dinner", currentPlan?.dinner?.item)
        }

        // Snacks Actions
        findViewById<Button>(R.id.btnSnacksAte).setOnClickListener {
            handleMealAction("Snack", currentPlan?.snack, "Ate")
        }
        findViewById<Button>(R.id.btnSnacksSkipped).setOnClickListener {
            handleMealAction("Snack", currentPlan?.snack, "Skipped")
        }
        findViewById<Button>(R.id.btnSnacksRegenerate).setOnClickListener {
            regenerateMeal("Snack", currentPlan?.snack?.item)
        }
    }

    private fun handleMealAction(mealType: String, meal: AIDietPlanService.MealRec?, action: String) {
        if (meal == null) {
            Toast.makeText(this, "Please generate a plan first", Toast.LENGTH_SHORT).show()
            return
        }

        val userId = authHelper.getCurrentUser()?.uid ?: return
        
        lifecycleScope.launch {
            try {
                // Track feedback in meal_feedback collection
                aiService.trackFeedback(userId, meal.item, mealType, action)
                
                // If user ate the meal, also log it to foodLogs collection
                if (action == "Ate") {
                    logMealToFoodLog(meal, mealType)
                }
                
                val emoji = if (action == "Ate") "✅" else "⏭️"
                Toast.makeText(this@AISmartDietActivity, "$emoji $action: ${meal.item}", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Log.e(TAG, "Error tracking feedback: ${e.message}", e)
            }
        }
    }

    private suspend fun logMealToFoodLog(meal: AIDietPlanService.MealRec, mealType: String) {
        try {
            val userId = authHelper.getCurrentUser()?.uid ?: return
            val firestore = com.google.firebase.firestore.FirebaseFirestore.getInstance("renu")
            
            val dateFormat = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US)
            val currentDate = dateFormat.format(java.util.Date())
            
            // Parse protein value (remove 'g' suffix if present)
            val proteinValue = meal.protein.replace("g", "").trim().toDoubleOrNull() ?: 0.0
            
            val foodLogData = hashMapOf(
                "userId" to userId,
                "foodName" to meal.item,
                "calories" to meal.calories,
                "protein" to proteinValue,
                "carbs" to 0.0,  // Not provided by AI, default to 0
                "fat" to 0.0,     // Not provided by AI, default to 0
                "mealType" to mealType,
                "timestamp" to System.currentTimeMillis(),
                "date" to currentDate,
                "source" to "AI_Recommendation"  // Tag to identify AI-suggested meals
            )
            
            firestore.collection("users").document(userId).collection("foodLogs").add(foodLogData).await()
            Log.d(TAG, "Meal logged to foodLogs: ${meal.item}")
        } catch (e: Exception) {
            Log.e(TAG, "Error logging meal to foodLogs: ${e.message}", e)
        }
    }



    private fun generateFullSmartPlan() {
        if (isGenerating) return
        isGenerating = true
        progressBar.visibility = View.VISIBLE
        
        lifecycleScope.launch {
            try {
                val result = aiService.generateSmartDietPlan()
                result.onSuccess { plan ->
                    currentPlan = plan
                    updateUI(plan)
                    showActionButtons()

                    Toast.makeText(this@AISmartDietActivity, "✨ Personalized Smarter Plan Generated!", Toast.LENGTH_LONG).show()
                }.onFailure { e ->
                    Toast.makeText(this@AISmartDietActivity, "Generation failed: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error in full plan generation: ${e.message}")
            } finally {
                isGenerating = false
                progressBar.visibility = View.GONE
            }
        }
    }

    private fun regenerateMeal(mealType: String, currentItem: String?) {
        if (currentItem == null) {
            Toast.makeText(this, "Please generate a plan first", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch {
            try {
                progressBar.visibility = View.VISIBLE
                val result = aiService.regenerateMeal(mealType, listOf(currentItem))
                
                result.onSuccess { newMeal ->
                    when (mealType) {
                        "Breakfast" -> {
                            currentPlan = currentPlan?.copy(breakfast = newMeal)
                            updateMealCard(R.id.tvBreakfastName, R.id.tvBreakfastDetails, newMeal)
                        }
                        "Lunch" -> {
                            currentPlan = currentPlan?.copy(lunch = newMeal)
                            updateMealCard(R.id.tvLunchName, R.id.tvLunchDetails, newMeal)
                        }
                        "Dinner" -> {
                            currentPlan = currentPlan?.copy(dinner = newMeal)
                            updateMealCard(R.id.tvDinnerName, R.id.tvDinnerDetails, newMeal)
                        }
                        "Snack" -> {
                            currentPlan = currentPlan?.copy(snack = newMeal)
                            updateMealCard(R.id.tvSnacksName, R.id.tvSnacksDetails, newMeal)
                        }
                    }
                    tvDailyTip.text = "💡 Tip: ${newMeal.tip}"
                    Toast.makeText(this@AISmartDietActivity, "🔁 New $mealType: ${newMeal.item}", Toast.LENGTH_SHORT).show()
                }.onFailure { e ->
                    Toast.makeText(this@AISmartDietActivity, "Failed to regenerate: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error regenerating meal: ${e.message}", e)
            } finally {
                progressBar.visibility = View.GONE
            }
        }
    }

    private fun generateSingleMeal(mealType: String) {
        if (isGenerating) {
            Toast.makeText(this, "Already generating, please wait...", Toast.LENGTH_SHORT).show()
            return
        }

        isGenerating = true
        progressBar.visibility = View.VISIBLE

        lifecycleScope.launch {
            try {
                val result = aiService.regenerateMeal(mealType, emptyList())
                
                result.onSuccess { newMeal ->
                    when (mealType) {
                        "Breakfast" -> {
                            currentPlan = currentPlan?.copy(breakfast = newMeal) ?: AIDietPlanService.MealPlan(
                                breakfast = newMeal,
                                lunch = AIDietPlanService.MealRec("", 0, "", ""),
                                snack = AIDietPlanService.MealRec("", 0, "", ""),
                                dinner = AIDietPlanService.MealRec("", 0, "", "")
                            )
                            updateMealCard(R.id.tvBreakfastName, R.id.tvBreakfastDetails, newMeal)
                            findViewById<LinearLayout>(R.id.layoutBreakfastActions).visibility = View.VISIBLE
                        }
                        "Lunch" -> {
                            currentPlan = currentPlan?.copy(lunch = newMeal) ?: AIDietPlanService.MealPlan(
                                breakfast = AIDietPlanService.MealRec("", 0, "", ""),
                                lunch = newMeal,
                                snack = AIDietPlanService.MealRec("", 0, "", ""),
                                dinner = AIDietPlanService.MealRec("", 0, "", "")
                            )
                            updateMealCard(R.id.tvLunchName, R.id.tvLunchDetails, newMeal)
                            findViewById<LinearLayout>(R.id.layoutLunchActions).visibility = View.VISIBLE
                        }
                        "Dinner" -> {
                            currentPlan = currentPlan?.copy(dinner = newMeal) ?: AIDietPlanService.MealPlan(
                                breakfast = AIDietPlanService.MealRec("", 0, "", ""),
                                lunch = AIDietPlanService.MealRec("", 0, "", ""),
                                snack = AIDietPlanService.MealRec("", 0, "", ""),
                                dinner = newMeal
                            )
                            updateMealCard(R.id.tvDinnerName, R.id.tvDinnerDetails, newMeal)
                            findViewById<LinearLayout>(R.id.layoutDinnerActions).visibility = View.VISIBLE
                        }
                        "Snack" -> {
                            currentPlan = currentPlan?.copy(snack = newMeal) ?: AIDietPlanService.MealPlan(
                                breakfast = AIDietPlanService.MealRec("", 0, "", ""),
                                lunch = AIDietPlanService.MealRec("", 0, "", ""),
                                snack = newMeal,
                                dinner = AIDietPlanService.MealRec("", 0, "", "")
                            )
                            updateMealCard(R.id.tvSnacksName, R.id.tvSnacksDetails, newMeal)
                            findViewById<LinearLayout>(R.id.layoutSnacksActions).visibility = View.VISIBLE
                        }
                    }
                    tvDailyTip.text = "💡 Tip: ${newMeal.tip}"
                    Toast.makeText(this@AISmartDietActivity, "✨ $mealType: ${newMeal.item}", Toast.LENGTH_SHORT).show()
                }.onFailure { e ->
                    Toast.makeText(this@AISmartDietActivity, "Failed: ${e.message}", Toast.LENGTH_SHORT).show()
                    Log.e(TAG, "Error generating $mealType: ${e.message}", e)
                }
            } catch (e: Exception) {
                Log.e(TAG, "Unexpected error: ${e.message}", e)
            } finally {
                isGenerating = false
                progressBar.visibility = View.GONE
            }
        }
    }

    private fun updateUI(plan: AIDietPlanService.MealPlan) {
        tvDailyTip.text = "💡 Tip: ${plan.dailyTip}"

        updateMealCard(R.id.tvBreakfastName, R.id.tvBreakfastDetails, plan.breakfast)
        updateMealCard(R.id.tvLunchName, R.id.tvLunchDetails, plan.lunch)
        updateMealCard(R.id.tvDinnerName, R.id.tvDinnerDetails, plan.dinner)
        updateMealCard(R.id.tvSnacksName, R.id.tvSnacksDetails, plan.snack)
    }

    private fun updateMealCard(nameResId: Int, detailsResId: Int, meal: AIDietPlanService.MealRec) {
        findViewById<TextView>(nameResId).text = meal.item
        findViewById<TextView>(detailsResId).text = "${meal.calories} kcal | ${meal.protein} protein"
    }

    private fun showActionButtons() {
        findViewById<LinearLayout>(R.id.layoutBreakfastActions).visibility = View.VISIBLE
        findViewById<LinearLayout>(R.id.layoutLunchActions).visibility = View.VISIBLE
        findViewById<LinearLayout>(R.id.layoutDinnerActions).visibility = View.VISIBLE
        findViewById<LinearLayout>(R.id.layoutSnacksActions).visibility = View.VISIBLE
    }

    private fun setupShareButton() {
        findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.fabShare).setOnClickListener {
            shareDietPlanToWhatsApp()
        }
    }

    private fun shareDietPlanToWhatsApp() {
        val plan = currentPlan
        if (plan == null) {
            Toast.makeText(this, "Please generate a diet plan first!", Toast.LENGTH_SHORT).show()
            return
        }

        val sb = StringBuilder()
        sb.append("📅 *My Diet Plan for Today*\n\n")

        sb.append("🍳 *Breakfast*\n")
        sb.append("• ${plan.breakfast.item}\n")
        sb.append("• ${plan.breakfast.calories} kcal | ${plan.breakfast.protein} protein\n\n")

        sb.append("🥗 *Lunch*\n")
        sb.append("• ${plan.lunch.item}\n")
        sb.append("• ${plan.lunch.calories} kcal | ${plan.lunch.protein} protein\n\n")

        sb.append("🍽️ *Dinner*\n")
        sb.append("• ${plan.dinner.item}\n")
        sb.append("• ${plan.dinner.calories} kcal | ${plan.dinner.protein} protein\n\n")
        
        sb.append("🍎 *Snack*\n")
        sb.append("• ${plan.snack.item}\n")
        sb.append("• ${plan.snack.calories} kcal | ${plan.snack.protein} protein\n\n")

        sb.append("💡 *Tip:* ${plan.dailyTip}\n\n")
        sb.append("Sent via SwasthyaMitra 🌿")

        val message = sb.toString()
        
        try {
            val url = "https://wa.me/?text=${java.net.URLEncoder.encode(message, "UTF-8")}"
            val intent = android.content.Intent(android.content.Intent.ACTION_VIEW)
            intent.data = android.net.Uri.parse(url)
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "WhatsApp not installed or error opening", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }
}
