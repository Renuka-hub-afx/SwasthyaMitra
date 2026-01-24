package com.example.swasthyamitra

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.swasthyamitra.ai.AIDietPlanService
import kotlinx.coroutines.launch

class AISmartDietActivity : AppCompatActivity() {

    private lateinit var aiService: AIDietPlanService
    private lateinit var progressBar: ProgressBar
    private lateinit var btnGenerate: Button
    private lateinit var tvDailyTip: TextView
    private var isGenerating = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ai_smart_diet)

        try {
            // Initialize Views
            aiService = AIDietPlanService(this)
            progressBar = findViewById(R.id.progressBar)
            btnGenerate = findViewById(R.id.btnGenerate)
            tvDailyTip = findViewById(R.id.tvDailyTip)

            btnGenerate.setOnClickListener {
                generatePlan()
            }
        } catch (e: Throwable) {
             e.printStackTrace()
             androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Startup Error")
                .setMessage(e.toString() + "\n" + e.stackTraceToString())
                .setPositiveButton("Close") { _, _ -> finish() }
                .show()
        }
    }

    private val TAG = "AI_DIET_ACTIVITY"

    private fun generatePlan() {
        if (isGenerating) {
            Log.d(TAG, "Already generating, ignoring request")
            return
        }

        Log.d(TAG, "Generate Plan button clicked")
        isGenerating = true
        progressBar.visibility = View.VISIBLE
        btnGenerate.isEnabled = false
        btnGenerate.text = "Generating..."

        lifecycleScope.launch {
            try {
                Log.d(TAG, "Requesting diet plan from service")
                val plan = aiService.generateDietPlan()
                Log.d(TAG, "Plan received successfully")
                updateUI(plan)
                Toast.makeText(this@AISmartDietActivity, "Plan Generated!", Toast.LENGTH_SHORT).show()
            } catch (e: Throwable) {
                Log.e(TAG, "Error generating plan: ${e.message}", e)
                showErrorDialog(e)
            } finally {
                isGenerating = false
                progressBar.visibility = View.GONE
                btnGenerate.isEnabled = true
                btnGenerate.text = "Generate New AI Plan"
                Log.d(TAG, "Generation process finished")
            }
        }
    }

    private fun updateUI(plan: AIDietPlanService.DietPlan) {
        tvDailyTip.text = "💡 Tip: ${plan.dailyTip}"

        updateMealCard(R.id.tvBreakfastName, R.id.tvBreakfastDetails, plan.breakfast)
        updateMealCard(R.id.tvLunchName, R.id.tvLunchDetails, plan.lunch)
        updateMealCard(R.id.tvDinnerName, R.id.tvDinnerDetails, plan.dinner)
        updateMealCard(R.id.tvSnacksName, R.id.tvSnacksDetails, plan.snacks)
    }

    private fun updateMealCard(nameResId: Int, detailsResId: Int, meal: AIDietPlanService.MealRec) {
        findViewById<TextView>(nameResId).text = meal.name
        findViewById<TextView>(detailsResId).text = "${meal.calories} kcal | ${meal.protein}g protein"
    }

    private fun showErrorDialog(e: Throwable) {
        AlertDialog.Builder(this)
            .setTitle("Generation Failed")
            .setMessage("Could not fetch diet plan. Please check your internet connection.\n\nError: ${e.localizedMessage}")
            .setPositiveButton("Retry") { _, _ -> generatePlan() }
            .setNegativeButton("Cancel", null)
            .show()
    }
}