package com.example.swasthyamitra

// Android framework imports for activity lifecycle and UI components
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
// Custom imports for AI diet planning and authentication
import com.example.swasthyamitra.ai.AIDietPlanService
import com.example.swasthyamitra.auth.FirebaseAuthHelper
// Kotlin coroutines for async operations
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

// Main activity class for AI-powered diet planning and meal recommendations
class AISmartDietActivity : AppCompatActivity() {

    // AI service instance for generating personalized meal recommendations
    private lateinit var aiService: AIDietPlanService
    // Firebase authentication helper for user login verification
    private lateinit var authHelper: FirebaseAuthHelper
    // Progress indicator shown during AI meal generation
    private lateinit var progressBar: ProgressBar
    // Text view displaying daily nutrition tips from AI
    private lateinit var tvDailyTip: TextView
    // Flag to prevent multiple simultaneous meal generation requests
    private var isGenerating = false
    // Currently generated meal plan containing breakfast, lunch, dinner, snack
    private var currentPlan: AIDietPlanService.MealPlan? = null



    // Activity initialization method called when screen is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the layout file for this activity's user interface
        setContentView(R.layout.activity_ai_smart_diet)

        try {
            // Verify app was properly initialized with UserApplication singleton pattern
            val application = application as? UserApplication
            if (application == null) {
                // Show error and close activity if initialization failed
                Toast.makeText(this, "App initialization error. Restarting...", Toast.LENGTH_LONG).show()
                finish()
                return
            }

            // Initialize AI service singleton for meal generation using Gemini AI
            aiService = AIDietPlanService.getInstance(this)
            // Get authenticated Firebase helper from global application context
            authHelper = application.authHelper

            // Find and initialize UI components from layout file
            progressBar = findViewById(R.id.progressBar)  // Loading spinner for AI requests
            tvDailyTip = findViewById(R.id.tvDailyTip)     // Daily nutrition tip display

            // Set click listener for back navigation button
            findViewById<android.widget.ImageButton>(R.id.btnBack).setOnClickListener {
                finish()  // Close current activity and return to previous screen
            }

            // Set up individual meal generation buttons for each meal type
            findViewById<Button>(R.id.btnGenerateBreakfast).setOnClickListener {
                generateSingleMeal("Breakfast")  // Generate AI breakfast recommendation
            }
            findViewById<Button>(R.id.btnGenerateLunch).setOnClickListener {
                generateSingleMeal("Lunch")      // Generate AI lunch recommendation
            }
            findViewById<Button>(R.id.btnGenerateDinner).setOnClickListener {
                generateSingleMeal("Dinner")     // Generate AI dinner recommendation
            }
            findViewById<Button>(R.id.btnGenerateSnack).setOnClickListener {
                generateSingleMeal("Snack")      // Generate AI snack recommendation
            }

            // Initialize meal action buttons (Ate, Skipped, Regenerate) for user feedback
            setupMealActionButtons()
            // Initialize WhatsApp sharing functionality for diet plans
            setupShareButton()
        } catch (e: Throwable) {
             // Log any startup errors for debugging
             e.printStackTrace()
             // Show error dialog with full stack trace details
             AlertDialog.Builder(this)
                .setTitle("Startup Error")
                .setMessage(e.toString() + "\n" + e.stackTraceToString())
                .setPositiveButton("Close") { _, _ -> finish() }
                .show()
        }
    }

    // Logging tag for debugging and error tracking
    private val TAG = "AI_DIET_ACTIVITY"

    // Configure click listeners for all meal interaction buttons (Ate/Skipped/Regenerate)
    private fun setupMealActionButtons() {
        // Breakfast action buttons setup
        findViewById<Button>(R.id.btnBreakfastAte).setOnClickListener {
            // Record that user consumed the recommended breakfast meal
            handleMealAction("Breakfast", currentPlan?.breakfast, "Ate")
        }
        findViewById<Button>(R.id.btnBreakfastSkipped).setOnClickListener {
            // Record that user skipped the recommended breakfast meal
            handleMealAction("Breakfast", currentPlan?.breakfast, "Skipped")
        }
        findViewById<Button>(R.id.btnBreakfastRegenerate).setOnClickListener {
            // Generate new breakfast recommendation excluding current meal
            regenerateMeal("Breakfast", currentPlan?.breakfast?.item)
        }

        // Lunch action buttons setup
        findViewById<Button>(R.id.btnLunchAte).setOnClickListener {
            // Record that user consumed the recommended lunch meal
            handleMealAction("Lunch", currentPlan?.lunch, "Ate")
        }
        findViewById<Button>(R.id.btnLunchSkipped).setOnClickListener {
            // Record that user skipped the recommended lunch meal
            handleMealAction("Lunch", currentPlan?.lunch, "Skipped")
        }
        findViewById<Button>(R.id.btnLunchRegenerate).setOnClickListener {
            // Generate new lunch recommendation excluding current meal
            regenerateMeal("Lunch", currentPlan?.lunch?.item)
        }

        // Dinner action buttons setup
        findViewById<Button>(R.id.btnDinnerAte).setOnClickListener {
            // Record that user consumed the recommended dinner meal
            handleMealAction("Dinner", currentPlan?.dinner, "Ate")
        }
        findViewById<Button>(R.id.btnDinnerSkipped).setOnClickListener {
            // Record that user skipped the recommended dinner meal
            handleMealAction("Dinner", currentPlan?.dinner, "Skipped")
        }
        findViewById<Button>(R.id.btnDinnerRegenerate).setOnClickListener {
            // Generate new dinner recommendation excluding current meal
            regenerateMeal("Dinner", currentPlan?.dinner?.item)
        }

        // Snack action buttons setup
        findViewById<Button>(R.id.btnSnacksAte).setOnClickListener {
            // Record that user consumed the recommended snack
            handleMealAction("Snack", currentPlan?.snack, "Ate")
        }
        findViewById<Button>(R.id.btnSnacksSkipped).setOnClickListener {
            // Record that user skipped the recommended snack
            handleMealAction("Snack", currentPlan?.snack, "Skipped")
        }
        findViewById<Button>(R.id.btnSnacksRegenerate).setOnClickListener {
            // Generate new snack recommendation excluding current meal
            regenerateMeal("Snack", currentPlan?.snack?.item)
        }
    }

    // Process user's action on a recommended meal (Ate/Skipped) and update data
    private fun handleMealAction(mealType: String, meal: AIDietPlanService.MealRec?, action: String) {
        // Validate that a meal plan exists before processing action
        if (meal == null) {
            Toast.makeText(this, "Please generate a plan first", Toast.LENGTH_SHORT).show()
            return
        }

        // Get current user's ID from Firebase authentication
        val userId = authHelper.getCurrentUser()?.uid ?: return

        // Execute meal action processing in background coroutine
        lifecycleScope.launch {
            try {
                // Save user feedback to Firebase for AI learning purposes
                aiService.trackFeedback(userId, meal.item, mealType, action)

                // If user ate the meal, automatically log it to their food diary
                if (action == "Ate") {
                    logMealToFoodLog(meal, mealType)
                }

                // Show confirmation message with appropriate emoji
                val emoji = if (action == "Ate") "✅" else "⏭️"
                Toast.makeText(this@AISmartDietActivity, "$emoji $action: ${meal.item}", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                // Log any errors during feedback tracking
                Log.e(TAG, "Error tracking feedback: ${e.message}", e)
            }
        }
    }

    // Convert AI-recommended meal into food log entry when user marks meal as "Ate"
    private suspend fun logMealToFoodLog(meal: AIDietPlanService.MealRec, mealType: String) {
        try {
            // Get current user's Firebase ID for data ownership
            val userId = authHelper.getCurrentUser()?.uid ?: return
            // Get Firestore instance using named "renu" database
            val firestore = com.google.firebase.firestore.FirebaseFirestore.getInstance("renu")

            // Create date formatter for consistent date strings (yyyy-MM-dd format)
            val dateFormat = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US)
            // Get current date string for daily nutrition tracking
            val currentDate = dateFormat.format(java.util.Date())

            // Clean protein value by removing 'g' suffix and converting to double
            val proteinValue = meal.protein.replace("g", "").trim().toDoubleOrNull() ?: 0.0

            // Calculate estimated macronutrients from total calories using nutrition science
            // Standard calorie conversions: Protein = 4 cal/g, Carbs = 4 cal/g, Fat = 9 cal/g
            val proteinCalories = proteinValue * 4
            // Calculate remaining calories after accounting for protein
            val remainingCalories = (meal.calories - proteinCalories).coerceAtLeast(0.0)
            // Estimate carbs as 60% of remaining calories (4 calories per gram)
            val estimatedCarbs = (remainingCalories * 0.60 / 4)
            // Estimate fat as 40% of remaining calories (9 calories per gram)
            val estimatedFat = (remainingCalories * 0.40 / 9)

            // Create comprehensive food log entry data structure
            val foodLogData = hashMapOf(
                "userId" to userId,                          // Owner of the food log entry
                "foodName" to meal.item,                     // Name of the consumed meal
                "calories" to meal.calories,                 // Total calorie content
                "protein" to proteinValue,                   // Protein content in grams
                "carbs" to estimatedCarbs,                   // Estimated carbohydrates in grams
                "fat" to estimatedFat,                       // Estimated fat content in grams
                "mealType" to mealType,                      // Breakfast/Lunch/Dinner/Snack
                "timestamp" to System.currentTimeMillis(),  // Unix timestamp for sorting
                "date" to currentDate,                       // Date string for daily grouping
                "source" to "AI_Recommendation"              // Tag to identify AI-suggested meals
            )

            // Save food log entry to user's foodLogs subcollection in Firestore
            firestore.collection("users").document(userId).collection("foodLogs").add(foodLogData).await()
            Log.d(TAG, "Meal logged to foodLogs: ${meal.item}")
        } catch (e: Exception) {
            // Log any errors during food log entry creation
            Log.e(TAG, "Error logging meal to foodLogs: ${e.message}", e)
        }
    }



    // Generate complete daily meal plan with all 4 meals using AI recommendations
    private fun generateFullSmartPlan() {
        // Prevent multiple simultaneous AI requests
        if (isGenerating) return
        isGenerating = true
        // Show loading spinner during AI meal generation
        progressBar.visibility = View.VISIBLE

        // Execute AI meal plan generation in background coroutine
        lifecycleScope.launch {
            try {
                // Request complete meal plan from AI service (uses Gemini API)
                val result = aiService.generateSmartDietPlan()
                result.onSuccess { plan ->
                    // Store generated plan for user interaction
                    currentPlan = plan
                    // Update UI with generated breakfast, lunch, dinner, snack
                    updateUI(plan)
                    // Show action buttons (Ate/Skipped/Regenerate) for each meal
                    showActionButtons()

                    // Confirm successful plan generation to user
                    Toast.makeText(this@AISmartDietActivity, "✨ Personalized Smarter Plan Generated!", Toast.LENGTH_LONG).show()
                }.onFailure { e ->
                    // Show error message if AI generation fails
                    Toast.makeText(this@AISmartDietActivity, "Generation failed: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                // Log unexpected errors during full plan generation
                Log.e(TAG, "Error in full plan generation: ${e.message}", e)
                runOnUiThread {
                    // Show detailed network error message to user
                    Toast.makeText(this@AISmartDietActivity, "Generation failed: ${e.message ?: "Network connection error"}", Toast.LENGTH_LONG).show()
                }
            } finally {
                // Reset generation state and hide loading spinner
                isGenerating = false
                progressBar.visibility = View.GONE
            }
        }
    }

    // Generate new meal recommendation excluding current meal (for variety)
    private fun regenerateMeal(mealType: String, currentItem: String?) {
        // Validate that a current meal exists to regenerate
        if (currentItem == null) {
            Toast.makeText(this, "Please generate a plan first", Toast.LENGTH_SHORT).show()
            return
        }

        // Execute meal regeneration in background coroutine
        lifecycleScope.launch {
            try {
                // Show loading indicator during AI meal regeneration
                progressBar.visibility = View.VISIBLE
                // Request new meal excluding current one from AI service
                val result = aiService.regenerateMeal(mealType, listOf(currentItem))

                result.onSuccess { newMeal ->
                    // Update current plan with newly generated meal based on type
                    when (mealType) {
                        "Breakfast" -> {
                            // Replace breakfast in current plan and update breakfast card UI
                            currentPlan = currentPlan?.copy(breakfast = newMeal)
                            updateMealCard(R.id.tvBreakfastName, R.id.tvBreakfastDetails, newMeal)
                        }
                        "Lunch" -> {
                            // Replace lunch in current plan and update lunch card UI
                            currentPlan = currentPlan?.copy(lunch = newMeal)
                            updateMealCard(R.id.tvLunchName, R.id.tvLunchDetails, newMeal)
                        }
                        "Dinner" -> {
                            // Replace dinner in current plan and update dinner card UI
                            currentPlan = currentPlan?.copy(dinner = newMeal)
                            updateMealCard(R.id.tvDinnerName, R.id.tvDinnerDetails, newMeal)
                        }
                        "Snack" -> {
                            // Replace snack in current plan and update snack card UI
                            currentPlan = currentPlan?.copy(snack = newMeal)
                            updateMealCard(R.id.tvSnacksName, R.id.tvSnacksDetails, newMeal)
                        }
                    }
                    // Update daily tip with new meal's nutritional advice
                    tvDailyTip.text = "💡 Tip: ${newMeal.tip}"
                    // Confirm successful regeneration to user
                    Toast.makeText(this@AISmartDietActivity, "🔁 New $mealType: ${newMeal.item}", Toast.LENGTH_SHORT).show()
                }.onFailure { e ->
                    // Show error message if regeneration fails
                    Toast.makeText(this@AISmartDietActivity, "Failed to regenerate: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                // Log any errors during meal regeneration
                Log.e(TAG, "Error regenerating meal: ${e.message}", e)
            } finally {
                // Hide loading spinner when regeneration completes
                progressBar.visibility = View.GONE
            }
        }
    }

    // Generate individual meal recommendation (breakfast, lunch, dinner, or snack)
    private fun generateSingleMeal(mealType: String) {
        // Check if AI is already generating a meal to prevent overlap
        if (isGenerating) {
            Toast.makeText(this, "Already generating, please wait...", Toast.LENGTH_SHORT).show()
            return
        }

        // Set generation state and show loading indicator
        isGenerating = true
        progressBar.visibility = View.VISIBLE

        // Execute single meal generation in background coroutine
        lifecycleScope.launch {
            try {
                // Request single meal from AI service without exclusions (empty list)
                val result = aiService.regenerateMeal(mealType, emptyList())

                result.onSuccess { newMeal ->
                    // Update current plan with new meal based on type requested
                    when (mealType) {
                        "Breakfast" -> {
                            // Update or create plan with new breakfast meal
                            currentPlan = currentPlan?.copy(breakfast = newMeal) ?: AIDietPlanService.MealPlan(
                                breakfast = newMeal,
                                lunch = AIDietPlanService.MealRec("", 0, "", ""),      // Empty placeholder meal
                                snack = AIDietPlanService.MealRec("", 0, "", ""),      // Empty placeholder meal
                                dinner = AIDietPlanService.MealRec("", 0, "", "")     // Empty placeholder meal
                            )
                            // Update breakfast UI card with new meal info
                            updateMealCard(R.id.tvBreakfastName, R.id.tvBreakfastDetails, newMeal)
                            // Show breakfast action buttons (Ate/Skipped/Regenerate)
                            findViewById<LinearLayout>(R.id.layoutBreakfastActions).visibility = View.VISIBLE
                        }
                        "Lunch" -> {
                            // Update or create plan with new lunch meal
                            currentPlan = currentPlan?.copy(lunch = newMeal) ?: AIDietPlanService.MealPlan(
                                breakfast = AIDietPlanService.MealRec("", 0, "", ""),  // Empty placeholder meal
                                lunch = newMeal,
                                snack = AIDietPlanService.MealRec("", 0, "", ""),      // Empty placeholder meal
                                dinner = AIDietPlanService.MealRec("", 0, "", "")     // Empty placeholder meal
                            )
                            // Update lunch UI card with new meal info
                            updateMealCard(R.id.tvLunchName, R.id.tvLunchDetails, newMeal)
                            // Show lunch action buttons (Ate/Skipped/Regenerate)
                            findViewById<LinearLayout>(R.id.layoutLunchActions).visibility = View.VISIBLE
                        }
                        "Dinner" -> {
                            // Update or create plan with new dinner meal
                            currentPlan = currentPlan?.copy(dinner = newMeal) ?: AIDietPlanService.MealPlan(
                                breakfast = AIDietPlanService.MealRec("", 0, "", ""),  // Empty placeholder meal
                                lunch = AIDietPlanService.MealRec("", 0, "", ""),      // Empty placeholder meal
                                snack = AIDietPlanService.MealRec("", 0, "", ""),      // Empty placeholder meal
                                dinner = newMeal
                            )
                            // Update dinner UI card with new meal info
                            updateMealCard(R.id.tvDinnerName, R.id.tvDinnerDetails, newMeal)
                            // Show dinner action buttons (Ate/Skipped/Regenerate)
                            findViewById<LinearLayout>(R.id.layoutDinnerActions).visibility = View.VISIBLE
                        }
                        "Snack" -> {
                            // Update or create plan with new snack meal
                            currentPlan = currentPlan?.copy(snack = newMeal) ?: AIDietPlanService.MealPlan(
                                breakfast = AIDietPlanService.MealRec("", 0, "", ""),  // Empty placeholder meal
                                lunch = AIDietPlanService.MealRec("", 0, "", ""),      // Empty placeholder meal
                                snack = newMeal,
                                dinner = AIDietPlanService.MealRec("", 0, "", "")     // Empty placeholder meal
                            )
                            // Update snack UI card with new meal info
                            updateMealCard(R.id.tvSnacksName, R.id.tvSnacksDetails, newMeal)
                            // Show snack action buttons (Ate/Skipped/Regenerate)
                            findViewById<LinearLayout>(R.id.layoutSnacksActions).visibility = View.VISIBLE
                        }
                    }
                    // Update daily tip display with AI-generated nutritional advice
                    tvDailyTip.text = "💡 Tip: ${newMeal.tip}"
                    // Show success message with generated meal name
                    Toast.makeText(this@AISmartDietActivity, "✨ $mealType: ${newMeal.item}", Toast.LENGTH_SHORT).show()
                }.onFailure { e ->
                    // Show error message if meal generation fails
                    Toast.makeText(this@AISmartDietActivity, "Failed: ${e.message}", Toast.LENGTH_SHORT).show()
                    Log.e(TAG, "Error generating $mealType: ${e.message}", e)
                }
            } catch (e: Exception) {
                // Log unexpected errors during single meal generation
                Log.e(TAG, "Unexpected error: ${e.message}", e)
                runOnUiThread {
                    // Show detailed network connectivity error message
                    Toast.makeText(this@AISmartDietActivity, "Generation failed: ${e.message ?: "Please check your internet connection"}", Toast.LENGTH_LONG).show()
                }
            } finally {
                // Reset generation state and hide loading spinner
                isGenerating = false
                progressBar.visibility = View.GONE
            }
        }
    }

    // Update entire UI with complete meal plan data (all 4 meals)
    private fun updateUI(plan: AIDietPlanService.MealPlan) {
        // Display AI-generated daily nutritional tip at top of screen
        tvDailyTip.text = "💡 Tip: ${plan.dailyTip}"

        // Update each meal card with plan data (name, calories, protein)
        updateMealCard(R.id.tvBreakfastName, R.id.tvBreakfastDetails, plan.breakfast)
        updateMealCard(R.id.tvLunchName, R.id.tvLunchDetails, plan.lunch)
        updateMealCard(R.id.tvDinnerName, R.id.tvDinnerDetails, plan.dinner)
        updateMealCard(R.id.tvSnacksName, R.id.tvSnacksDetails, plan.snack)
    }

    // Update individual meal card UI with meal name and nutritional information
    private fun updateMealCard(nameResId: Int, detailsResId: Int, meal: AIDietPlanService.MealRec) {
        // Set meal name in the title text view
        findViewById<TextView>(nameResId).text = meal.item
        // Set nutritional info (calories and protein) in details text view
        findViewById<TextView>(detailsResId).text = "${meal.calories} kcal | ${meal.protein} protein"
    }

    // Make all meal action buttons visible after successful plan generation
    private fun showActionButtons() {
        // Show breakfast action buttons (Ate/Skipped/Regenerate)
        findViewById<LinearLayout>(R.id.layoutBreakfastActions).visibility = View.VISIBLE
        // Show lunch action buttons (Ate/Skipped/Regenerate)
        findViewById<LinearLayout>(R.id.layoutLunchActions).visibility = View.VISIBLE
        // Show dinner action buttons (Ate/Skipped/Regenerate)
        findViewById<LinearLayout>(R.id.layoutDinnerActions).visibility = View.VISIBLE
        // Show snack action buttons (Ate/Skipped/Regenerate)
        findViewById<LinearLayout>(R.id.layoutSnacksActions).visibility = View.VISIBLE
    }

    // Initialize WhatsApp sharing button for diet plan sharing
    private fun setupShareButton() {
        // Set click listener on floating action button for WhatsApp sharing
        findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.fabShare).setOnClickListener {
            shareDietPlanToWhatsApp()  // Launch WhatsApp sharing functionality
        }
    }

    // Share complete diet plan via WhatsApp with formatted text
    private fun shareDietPlanToWhatsApp() {
        // Get current meal plan for sharing
        val plan = currentPlan
        // Validate that a plan exists before sharing
        if (plan == null) {
            Toast.makeText(this, "Please generate a diet plan first!", Toast.LENGTH_SHORT).show()
            return
        }

        // Build formatted WhatsApp message with meal plan details
        val sb = StringBuilder()
        sb.append("📅 *My Diet Plan for Today*\n\n")  // WhatsApp bold formatting

        // Add breakfast section with meal name and nutrition
        sb.append("🍳 *Breakfast*\n")
        sb.append("• ${plan.breakfast.item}\n")
        sb.append("• ${plan.breakfast.calories} kcal | ${plan.breakfast.protein} protein\n\n")

        // Add lunch section with meal name and nutrition
        sb.append("🥗 *Lunch*\n")
        sb.append("• ${plan.lunch.item}\n")
        sb.append("• ${plan.lunch.calories} kcal | ${plan.lunch.protein} protein\n\n")

        // Add dinner section with meal name and nutrition
        sb.append("🍽️ *Dinner*\n")
        sb.append("• ${plan.dinner.item}\n")
        sb.append("• ${plan.dinner.calories} kcal | ${plan.dinner.protein} protein\n\n")

        // Add snack section with meal name and nutrition
        sb.append("🍎 *Snack*\n")
        sb.append("• ${plan.snack.item}\n")
        sb.append("• ${plan.snack.calories} kcal | ${plan.snack.protein} protein\n\n")

        // Add daily nutritional tip from AI
        sb.append("💡 *Tip:* ${plan.dailyTip}\n\n")
        // Add app branding footer
        sb.append("Sent via SwasthyaMitra 🌿")

        // Convert StringBuilder to string for sharing
        val message = sb.toString()

        try {
            // Create WhatsApp share URL with URL-encoded message
            val url = "https://wa.me/?text=${java.net.URLEncoder.encode(message, "UTF-8")}"
            // Create intent to open WhatsApp with pre-filled message
            val intent = android.content.Intent(android.content.Intent.ACTION_VIEW)
            intent.data = android.net.Uri.parse(url)
            // Launch WhatsApp sharing intent
            startActivity(intent)
        } catch (e: Exception) {
            // Show error if WhatsApp is not installed or sharing fails
            Toast.makeText(this, "WhatsApp not installed or error opening", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }
}
