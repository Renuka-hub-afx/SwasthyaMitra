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
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers

class AISmartDietActivity : AppCompatActivity() {

    private lateinit var aiService: AIDietPlanService
    private lateinit var authHelper: FirebaseAuthHelper
    private lateinit var progressBar: ProgressBar
    private lateinit var tvDailyTip: TextView
    private var isGenerating = false
    private var currentPlan: AIDietPlanService.MealPlan? = null

    private lateinit var tvMetabolicStatus: TextView
    private lateinit var tvIntensityAlert: TextView

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
            tvMetabolicStatus = findViewById(R.id.tvMetabolicStatus)
            tvIntensityAlert = findViewById(R.id.tvIntensityAlert)

            checkMetabolicStatus()
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
            Log.d(TAG, "🔘 Breakfast Ate button clicked")
            handleMealAction("Breakfast", currentPlan?.breakfast, "Ate")
        }
        findViewById<Button>(R.id.btnBreakfastSkipped).setOnClickListener {
            Log.d(TAG, "🔘 Breakfast Skip button clicked")
            handleMealAction("Breakfast", currentPlan?.breakfast, "Skipped")
        }
        findViewById<Button>(R.id.btnBreakfastRegenerate).setOnClickListener {
            Log.d(TAG, "🔘 Breakfast New button clicked")
            regenerateMeal("Breakfast", currentPlan?.breakfast?.item)
        }

        // Lunch Actions
        findViewById<Button>(R.id.btnLunchAte).setOnClickListener {
            Log.d(TAG, "🔘 Lunch Ate button clicked")
            handleMealAction("Lunch", currentPlan?.lunch, "Ate")
        }
        findViewById<Button>(R.id.btnLunchSkipped).setOnClickListener {
            Log.d(TAG, "🔘 Lunch Skip button clicked")
            handleMealAction("Lunch", currentPlan?.lunch, "Skipped")
        }
        findViewById<Button>(R.id.btnLunchRegenerate).setOnClickListener {
            Log.d(TAG, "🔘 Lunch New button clicked")
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
        Log.d(TAG, "🎯 handleMealAction called: mealType=$mealType, action=$action, meal=${meal?.item}")

        if (meal == null) {
            Toast.makeText(this, "Please generate a meal first", Toast.LENGTH_SHORT).show()
            Log.w(TAG, "⚠️ Meal is null, cannot perform action")
            return
        }

        val userId = authHelper.getCurrentUser()?.uid
        if (userId == null) {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show()
            Log.e(TAG, "❌ User ID is null")
            return
        }

        Log.d(TAG, "✅ User ID: $userId, Processing action: $action for ${meal.item}")

        lifecycleScope.launch {
            try {
                // Track feedback in meal_feedback collection
                Log.d(TAG, "📝 Tracking feedback...")
                aiService.trackFeedback(userId, meal.item, mealType, action)
                Log.d(TAG, "✅ Feedback tracked successfully")

                when (action) {
                    "Ate" -> {
                        Log.d(TAG, "🍽️ Processing 'Ate' action - logging meal to food diary")
                        // Log meal to food diary
                        logMealToFoodLog(meal, mealType)
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@AISmartDietActivity, "✅ Logged: ${meal.item}", Toast.LENGTH_SHORT).show()
                        }
                    }
                    "Skipped" -> {
                        Log.d(TAG, "⏭️ Processing 'Skipped' action - adding to disliked foods")
                        // Add to disliked foods in user preferences
                        addToDislikedFoods(userId, meal.item)
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@AISmartDietActivity, "⏭️ Skipped: ${meal.item}. Generating new...", Toast.LENGTH_SHORT).show()
                        }

                        Log.d(TAG, "🔄 Regenerating meal after skip...")
                        // Automatically regenerate a new meal after skipping
                        regenerateMeal(mealType, meal.item)
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "❌ Error in handleMealAction: ${e.message}", e)
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@AISmartDietActivity, "❌ Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private suspend fun logMealToFoodLog(meal: AIDietPlanService.MealRec, mealType: String) {
        try {
            val userId = authHelper.getCurrentUser()?.uid
            if (userId == null) {
                Log.e(TAG, "❌ Cannot log meal: User ID is null")
                return
            }

            Log.d(TAG, "📊 Logging meal to foodLogs: ${meal.item}")

            val dateFormat = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
            val currentDate = dateFormat.format(java.util.Date())
            
            // Parse protein value (remove 'g' suffix if present)
            val proteinValue = meal.protein.replace("g", "", ignoreCase = true).trim().toDoubleOrNull() ?: 0.0
            
            Log.d(TAG, "📝 Creating FoodLog: userId=$userId, foodName=${meal.item}, calories=${meal.calories}, protein=$proteinValue, mealType=$mealType, date=$currentDate")

            val foodLog = com.example.swasthyamitra.models.FoodLog(
                userId = userId,
                foodName = meal.item,
                calories = meal.calories,
                protein = proteinValue,
                carbs = 0.0, // AI doesn't provide carbs/fat reliably yet - defaulting to 0
                fat = 0.0,   // AI doesn't provide carbs/fat reliably yet - defaulting to 0
                mealType = mealType,
                timestamp = System.currentTimeMillis(),
                date = currentDate,
                servingSize = "1 serving",
                barcode = null,
                photoUrl = null
            )
            
            Log.d(TAG, "🔄 Calling authHelper.logFood()...")
            val result = authHelper.logFood(foodLog)
            
            result.onSuccess { docId ->
                Log.d(TAG, "✅ SUCCESS: Meal logged to foodLogs with ID: $docId - ${meal.item}")
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@AISmartDietActivity, "✅ Saved to Food Log!", Toast.LENGTH_SHORT).show()
                }
            }.onFailure { e ->
                Log.e(TAG, "❌ FAILED: Error logging meal to foodLogs: ${e.message}", e)
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@AISmartDietActivity, "❌ Failed to log: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "❌ Exception in logMealToFoodLog: ${e.message}", e)
            e.printStackTrace()
            withContext(Dispatchers.Main) {
                Toast.makeText(this@AISmartDietActivity, "❌ Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun checkMetabolicStatus() {
        val userId = authHelper.getCurrentUser()?.uid ?: return
        lifecycleScope.launch {
            try {
                // Fetch status from the engine
                val exerciseLogs = authHelper.getRecentExerciseLogs(userId, 3)
                val weightLogs = authHelper.getRecentWeightLogs(userId, 14)
                
                val hadHighIntensity = exerciseLogs.any { 
                    (it["intensity"] as? String)?.contains("High", ignoreCase = true) == true || 
                    (it["type"] as? String)?.contains("HIIT", ignoreCase = true) == true 
                }
                
                // Simplified plateau check
                val isPlateau = weightLogs.size >= 5 && weightLogs.mapNotNull { it["weight"] as? Double }.distinct().size <= 1

                runOnUiThread {
                    tvIntensityAlert.text = if (hadHighIntensity) "Recent Intensity: HIGH 🔥" else "Recent Intensity: Normal"
                    tvMetabolicStatus.text = if (isPlateau) "Metabolic Status: PLATEAU DETECTED ⚖️" else "Metabolic Status: Active Meta"
                }

            } catch (e: Exception) {
                Log.e(TAG, "Error checking metabolic status: ${e.message}")
            }
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
                    checkMetabolicStatus() // Refresh status
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
        Log.d(TAG, "🔄 regenerateMeal called: mealType=$mealType, currentItem=$currentItem")

        if (currentItem == null) {
            Toast.makeText(this, "Please generate a meal first", Toast.LENGTH_SHORT).show()
            Log.w(TAG, "⚠️ Current item is null")
            return
        }

        lifecycleScope.launch {
            try {
                progressBar.visibility = View.VISIBLE
                Log.d(TAG, "🎲 Generating new $mealType (excluding: $currentItem)")

                val result = aiService.regenerateMeal(mealType, listOf(currentItem))
                
                result.onSuccess { newMeal ->
                    Log.d(TAG, "✅ New meal generated: ${newMeal.item}")

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

                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@AISmartDietActivity, "🔁 New $mealType: ${newMeal.item}", Toast.LENGTH_SHORT).show()
                    }
                }.onFailure { e ->
                    Log.e(TAG, "❌ Failed to regenerate: ${e.message}", e)
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@AISmartDietActivity, "❌ Failed to regenerate: ${e.message}", Toast.LENGTH_LONG).show()
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "❌ Error regenerating meal: ${e.message}", e)
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@AISmartDietActivity, "❌ Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
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
    
    /**
     * Add skipped meal to user's disliked foods in Firestore user_preferences
     */
    private suspend fun addToDislikedFoods(userId: String, foodName: String) {
        try {
            Log.d(TAG, "📝 Adding '$foodName' to disliked foods for user: $userId")
            val firestore = com.google.firebase.firestore.FirebaseFirestore.getInstance("renu") // Using RENU database instance
            val prefsRef = firestore.collection("user_preferences").document(userId)
            
            // Get current preferences
            val snapshot = prefsRef.get().await()
            val currentDislikes = snapshot.get("dislikedFoods") as? List<String> ?: emptyList()
            
            Log.d(TAG, "📋 Current disliked foods count: ${currentDislikes.size}")
            
            // Add new food if not already in list
            if (!currentDislikes.contains(foodName)) {
                val updatedDislikes = currentDislikes + foodName
                prefsRef.set(
                    mapOf(
                        "dislikedFoods" to updatedDislikes,
                        "lastUpdated" to com.google.firebase.firestore.FieldValue.serverTimestamp()
                    ),
                    com.google.firebase.firestore.SetOptions.merge()
                ).await()
                
                Log.d(TAG, "✅ SUCCESS: Added '$foodName' to disliked foods. Total: ${updatedDislikes.size}")
            } else {
                Log.d(TAG, "ℹ️ '$foodName' already in disliked foods")
            }
        } catch (e: Exception) {
            Log.e(TAG, "❌ FAILED: Error adding to disliked foods: ${e.message}", e)
            withContext(kotlinx.coroutines.Dispatchers.Main) {
                Toast.makeText(this@AISmartDietActivity, "Failed to save preference: ${e.message}", Toast.LENGTH_LONG).show()
            }
            throw e
        }
    }
}
