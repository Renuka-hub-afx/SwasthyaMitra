package com.example.swasthyamitra.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.swasthyamitra.databinding.ActivityProgressDashboardBinding
import com.example.swasthyamitra.ml.ProgressAnalyzer
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class ProgressDashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProgressDashboardBinding
    private lateinit var analyzer: ProgressAnalyzer
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgressDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        val userId = auth.currentUser?.uid

        if (userId == null) {
            Toast.makeText(this, "Please login first", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        analyzer = ProgressAnalyzer(userId)

        setupUI()
        loadProgress()
    }

    private fun setupUI() {
        binding.btnBack.setOnClickListener { finish() }
        binding.btnRefresh.setOnClickListener { loadProgress() }
    }

    private fun loadProgress() {
        binding.progressBar.visibility = View.VISIBLE

        lifecycleScope.launch {
            try {
                // Weight Progress
                val weightProgress = analyzer.analyzeWeightProgress()
                binding.tvCurrentWeight.text = String.format("%.1f kg", weightProgress.currentWeight)
                binding.tvWeightTrend.text = weightProgress.trend
                binding.tvPredictedWeight.text = String.format("Predicted: %.1f kg", weightProgress.predictedWeightNextWeek)
                binding.progressWeight.progress = weightProgress.achievement

                // Set color based on trend
                val trendColor = when {
                    weightProgress.trend.contains("Losing") -> Color.parseColor("#4CAF50")
                    weightProgress.trend.contains("Gaining") -> Color.parseColor("#FF9800")
                    else -> Color.parseColor("#2196F3")
                }
                binding.tvWeightTrend.setTextColor(trendColor)

                // Nutrition Progress
                val nutritionProgress = analyzer.analyzeNutritionProgress()
                binding.tvAvgCalories.text = "${nutritionProgress.averageCalories} kcal/day"
                binding.tvNutritionTrend.text = nutritionProgress.trend
                binding.progressNutrition.progress = nutritionProgress.consistency
                binding.tvNutritionRecommendations.text = nutritionProgress.recommendations.joinToString("\n")

                // Hydration Progress
                val hydrationProgress = analyzer.analyzeHydrationProgress()
                binding.tvAvgHydration.text = "${hydrationProgress.averageDailyIntake} ml/day"
                binding.tvHydrationTrend.text = hydrationProgress.trend
                binding.progressHydration.progress = hydrationProgress.goalAchievement
                binding.tvHydrationRecommendations.text = hydrationProgress.recommendations.joinToString("\n")

                // Exercise Progress
                val exerciseProgress = analyzer.analyzeExerciseProgress()
                binding.tvTotalExercise.text = "${exerciseProgress.totalMinutesWeek} min this week"
                binding.tvAvgExercise.text = "${exerciseProgress.averageDailyMinutes} min/day"
                binding.tvExerciseTrend.text = exerciseProgress.trend
                binding.progressExercise.progress = exerciseProgress.consistency
                binding.tvExerciseRecommendations.text = exerciseProgress.recommendations.joinToString("\n")

                binding.progressBar.visibility = View.GONE
                Toast.makeText(this@ProgressDashboardActivity, "Progress updated!", Toast.LENGTH_SHORT).show()

            } catch (e: Exception) {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(this@ProgressDashboardActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

