package com.example.swasthyamitra.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.swasthyamitra.databinding.ActivityEnhancedProgressDashboardBinding
import com.example.swasthyamitra.ml.EnhancedProgressAnalyzer
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.formatter.ValueFormatter
import kotlinx.coroutines.tasks.await

/**
 * Enhanced Progress Dashboard with Multi-Period Support and Smart Graphs
 * Shows progress across 7 days, 15 days, and 1 month
 */
class EnhancedProgressDashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEnhancedProgressDashboardBinding
    private lateinit var analyzer: EnhancedProgressAnalyzer
    private lateinit var auth: FirebaseAuth

    private var currentPeriod = EnhancedProgressAnalyzer.TimePeriod.WEEK

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnhancedProgressDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        val userId = auth.currentUser?.uid

        if (userId == null) {
            Toast.makeText(this, "Please login first", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        analyzer = EnhancedProgressAnalyzer(userId)

        setupUI()
        loadProgress(currentPeriod)
        loadStageProgress(userId)
    }

    private fun setupUI() {
        binding.btnBack.setOnClickListener { finish() }
        binding.btnRefresh.setOnClickListener {
            loadProgress(currentPeriod)
            val userId = auth.currentUser?.uid
            if (userId != null) {
                loadStageProgress(userId)
            }
        }

        // Insights button
        binding.btnViewInsights.setOnClickListener {
            val intent = android.content.Intent(this, com.example.swasthyamitra.InsightsActivity::class.java)
            startActivity(intent)
        }

        // Add activity button
        binding.fabAddActivity.setOnClickListener {
            Toast.makeText(this, "Log your activities to unlock stages!", Toast.LENGTH_SHORT).show()
        }

        // Stage card click listeners
        binding.cardStage1.setOnClickListener { showStageDetails(1, "Hydration Hero", "Track 7 days of water intake") }
        binding.cardStage2.setOnClickListener { showStageDetails(2, "Step Master", "Reach 10k steps for 7 days") }
        binding.cardStage3.setOnClickListener { showStageDetails(3, "Sleep Saint", "Track 7 nights of good sleep") }
        binding.cardStage4.setOnClickListener { showStageDetails(4, "Zen Master", "Log 7 days of mood tracking") }
        binding.cardStage5.setOnClickListener { showStageDetails(5, "Nutrition Ninja", "Log 21 meals (7 days × 3)") }
        binding.cardStage6.setOnClickListener { showStageDetails(6, "Iron Legend", "Complete 7 workouts") }

        // Period selection chips
        binding.chip7Days.setOnClickListener {
            currentPeriod = EnhancedProgressAnalyzer.TimePeriod.WEEK
            updateChipSelection()
            loadProgress(currentPeriod)
        }

        binding.chip15Days.setOnClickListener {
            currentPeriod = EnhancedProgressAnalyzer.TimePeriod.TWO_WEEKS
            updateChipSelection()
            loadProgress(currentPeriod)
        }

        binding.chip1Month.setOnClickListener {
            currentPeriod = EnhancedProgressAnalyzer.TimePeriod.MONTH
            updateChipSelection()
            loadProgress(currentPeriod)
        }

        updateChipSelection()
        setupGraphs()
    }

    private fun updateChipSelection() {
        binding.chip7Days.isChecked = currentPeriod == EnhancedProgressAnalyzer.TimePeriod.WEEK
        binding.chip15Days.isChecked = currentPeriod == EnhancedProgressAnalyzer.TimePeriod.TWO_WEEKS
        binding.chip1Month.isChecked = currentPeriod == EnhancedProgressAnalyzer.TimePeriod.MONTH
    }

    private fun setupGraphs() {
        // Configure all graphs
        listOf(
            binding.weightGraph,
            binding.nutritionGraph,
            binding.hydrationGraph,
            binding.exerciseGraph
        ).forEach { chart ->
            chart.apply {
                description.isEnabled = false
                setTouchEnabled(true)
                isDragEnabled = true
                setScaleEnabled(false)
                setPinchZoom(false)
                setDrawGridBackground(false)

                xAxis.apply {
                    position = XAxis.XAxisPosition.BOTTOM
                    setDrawGridLines(false)
                    textColor = Color.WHITE
                }

                axisLeft.apply {
                    setDrawGridLines(true)
                    gridColor = Color.parseColor("#30FFFFFF")
                    textColor = Color.WHITE
                }

                axisRight.isEnabled = false
                legend.textColor = Color.WHITE
            }
        }
    }

    private fun loadProgress(period: EnhancedProgressAnalyzer.TimePeriod) {
        binding.progressBar.visibility = View.VISIBLE

        lifecycleScope.launch {
            try {
                // Load all analytics in parallel
                val weightData = analyzer.analyzeWeightProgress(period)
                val nutritionData = analyzer.analyzeNutritionProgress(period)
                val hydrationData = analyzer.analyzeHydrationProgress(period)
                val exerciseData = analyzer.analyzeExerciseProgress(period)

                runOnUiThread {
                    // Update Weight Card
                    updateWeightCard(weightData)

                    // Update Nutrition Card
                    updateNutritionCard(nutritionData)

                    // Update Hydration Card
                    updateHydrationCard(hydrationData)

                    // Update Exercise Card
                    updateExerciseCard(exerciseData)

                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        this@EnhancedProgressDashboardActivity,
                        "Updated for ${period.label}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } catch (e: Exception) {
                runOnUiThread {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        this@EnhancedProgressDashboardActivity,
                        "Error: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun updateWeightCard(data: EnhancedProgressAnalyzer.WeightProgressData) {
        binding.tvCurrentWeight.text = String.format("%.1f kg", data.currentWeight)
        binding.tvWeightChange.text = String.format("Change: %.1f kg", data.change)
        binding.tvWeightTrend.text = data.trend
        binding.tvPredictedWeight.text = String.format("Predicted: %.1f kg", data.predictedNextWeek)

        // Set trend color
        val trendColor = when {
            data.trend.contains("Losing") -> Color.parseColor("#4CAF50")
            data.trend.contains("Gaining") -> Color.parseColor("#FF9800")
            else -> Color.parseColor("#2196F3")
        }
        binding.tvWeightTrend.setTextColor(trendColor)

        // Update graph
        if (data.dataPoints.isNotEmpty()) {
            val entries = data.dataPoints.mapIndexed { index, point ->
                Entry(index.toFloat(), point.value)
            }

            val dataSet = LineDataSet(entries, "Weight (kg)").apply {
                color = Color.parseColor("#7B2CBF")
                setCircleColor(Color.parseColor("#7B2CBF"))
                lineWidth = 3f
                circleRadius = 5f
                setDrawCircleHole(false)
                valueTextColor = Color.WHITE
                valueTextSize = 10f
                mode = LineDataSet.Mode.CUBIC_BEZIER
                setDrawFilled(true)
                fillColor = Color.parseColor("#507B2CBF")
            }

            binding.weightGraph.apply {
                this.data = LineData(dataSet)
                xAxis.valueFormatter = object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String {
                        val index = value.toInt()
                        return if (index in data.dataPoints.indices) {
                            data.dataPoints[index].date.substring(5) // MM-DD
                        } else ""
                    }
                }
                animateX(1000)
                invalidate()
            }
        }
    }

    private fun updateNutritionCard(data: EnhancedProgressAnalyzer.NutritionProgressData) {
        binding.tvAvgCalories.text = "${data.averageCalories} kcal/day"
        binding.tvNutritionTrend.text = data.trend
        binding.tvDaysLogged.text = "${data.totalDays}/${currentPeriod.days} days"
        binding.progressNutrition.progress = data.consistency
        binding.tvNutritionRecommendations.text = data.recommendations.joinToString("\n")

        // Update graph
        if (data.dataPoints.isNotEmpty()) {
            val entries = data.dataPoints.mapIndexed { index, point ->
                Entry(index.toFloat(), point.value)
            }

            val dataSet = LineDataSet(entries, "Calories").apply {
                color = Color.parseColor("#FF6D00")
                setCircleColor(Color.parseColor("#FF6D00"))
                lineWidth = 3f
                circleRadius = 5f
                setDrawCircleHole(false)
                valueTextColor = Color.WHITE
                valueTextSize = 10f
                mode = LineDataSet.Mode.CUBIC_BEZIER
                setDrawFilled(true)
                fillColor = Color.parseColor("#50FF6D00")
            }

            binding.nutritionGraph.apply {
                this.data = LineData(dataSet)
                xAxis.valueFormatter = object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String {
                        val index = value.toInt()
                        return if (index in data.dataPoints.indices) {
                            data.dataPoints[index].date.substring(5)
                        } else ""
                    }
                }
                animateX(1000)
                invalidate()
            }
        }
    }

    private fun updateHydrationCard(data: EnhancedProgressAnalyzer.HydrationProgressData) {
        binding.tvAvgHydration.text = "${data.averageDailyIntake} ml/day"
        binding.tvHydrationTrend.text = data.trend
        binding.tvDaysTracked.text = "${data.totalDays}/${currentPeriod.days} days"
        binding.progressHydration.progress = data.goalAchievement
        binding.tvHydrationRecommendations.text = data.recommendations.joinToString("\n")

        // Update graph
        if (data.dataPoints.isNotEmpty()) {
            val entries = data.dataPoints.mapIndexed { index, point ->
                Entry(index.toFloat(), point.value)
            }

            val dataSet = LineDataSet(entries, "Water (ml)").apply {
                color = Color.parseColor("#2196F3")
                setCircleColor(Color.parseColor("#2196F3"))
                lineWidth = 3f
                circleRadius = 5f
                setDrawCircleHole(false)
                valueTextColor = Color.WHITE
                valueTextSize = 10f
                mode = LineDataSet.Mode.CUBIC_BEZIER
                setDrawFilled(true)
                fillColor = Color.parseColor("#502196F3")
            }

            binding.hydrationGraph.apply {
                this.data = LineData(dataSet)
                xAxis.valueFormatter = object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String {
                        val index = value.toInt()
                        return if (index in data.dataPoints.indices) {
                            data.dataPoints[index].date.substring(5)
                        } else ""
                    }
                }
                animateX(1000)
                invalidate()
            }
        }
    }

    private fun updateExerciseCard(data: EnhancedProgressAnalyzer.ExerciseProgressData) {
        binding.tvTotalExercise.text = "${data.totalMinutes} min in ${currentPeriod.label}"
        binding.tvAvgExercise.text = "${data.averageDailyMinutes} min/day"
        binding.tvActiveDays.text = "${data.activeDays}/${currentPeriod.days} days"
        binding.tvExerciseTrend.text = data.trend
        binding.progressExercise.progress = data.consistency
        binding.tvExerciseRecommendations.text = data.recommendations.joinToString("\n")

        // Update graph
        if (data.dataPoints.isNotEmpty()) {
            val entries = data.dataPoints.mapIndexed { index, point ->
                Entry(index.toFloat(), point.value)
            }

            val dataSet = LineDataSet(entries, "Minutes").apply {
                color = Color.parseColor("#FF5722")
                setCircleColor(Color.parseColor("#FF5722"))
                lineWidth = 3f
                circleRadius = 5f
                setDrawCircleHole(false)
                valueTextColor = Color.WHITE
                valueTextSize = 10f
                mode = LineDataSet.Mode.CUBIC_BEZIER
                setDrawFilled(true)
                fillColor = Color.parseColor("#50FF5722")
            }

            binding.exerciseGraph.apply {
                this.data = LineData(dataSet)
                xAxis.valueFormatter = object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String {
                        val index = value.toInt()
                        return if (index in data.dataPoints.indices) {
                            data.dataPoints[index].date.substring(5)
                        } else ""
                    }
                }
                animateX(1000)
                invalidate()
            }
        }
    }

    // Stage Unlock System
    private fun loadStageProgress(userId: String) {
        lifecycleScope.launch {
            try {
                val firestore = com.google.firebase.firestore.FirebaseFirestore.getInstance("renu")

                // Get user's activity data
                val hydrationDays = getActivityCount(firestore, userId, "hydration_logs", 7)
                val stepDays = getStepDays(firestore, userId, 10000, 7)
                val mealCount = getActivityCount(firestore, userId, "foodLogs", 7)
                val workoutCount = getActivityCount(firestore, userId, "exercise_logs", 7)

                // Calculate current stage
                var currentStage = 1
                var unlockedStages = 0

                // Stage 1: Hydration Hero (7 days of water tracking)
                if (hydrationDays >= 7) {
                    unlockStage(1, "💧", "#FFFFFF")
                    unlockedStages++
                    currentStage = 2
                } else {
                    binding.tvCurrentActivity.text = "$hydrationDays / 7 days"
                }

                // Stage 2: Step Master (7 days of 10k steps)
                if (stepDays >= 7 && unlockedStages >= 1) {
                    unlockStage(2, "👟", "#FFFFFF")
                    unlockedStages++
                    currentStage = 3
                }

                // Stage 3: Sleep Saint (unlock after stage 2)
                if (unlockedStages >= 2) {
                    unlockStage(3, "😴", "#FFFFFF")
                    unlockedStages++
                    currentStage = 4
                }

                // Stage 4: Zen Master (7 days of mood tracking)
                if (unlockedStages >= 3) {
                    unlockStage(4, "🧘", "#E1BEE7")
                    unlockedStages++
                    currentStage = 5
                }

                // Stage 5: Nutrition Ninja (21 meals logged)
                if (mealCount >= 21 && unlockedStages >= 4) {
                    unlockStage(5, "✓", "#FFFFFF")
                    unlockedStages++
                    currentStage = 6
                }

                // Stage 6: Iron Legend (7 workouts)
                if (workoutCount >= 7 && unlockedStages >= 5) {
                    unlockStage(6, "H", "#E1BEE7")
                    unlockedStages++
                }

                // Update journey progress
                binding.tvJourneyStage.text = "Stage $currentStage / 6"
                binding.progressJourney.progress = (unlockedStages * 100) / 6

            } catch (e: Exception) {
                android.util.Log.e("StageUnlock", "Error loading stage progress: ${e.message}", e)
            }
        }
    }

    private suspend fun getActivityCount(
        firestore: com.google.firebase.firestore.FirebaseFirestore,
        userId: String,
        collection: String,
        days: Int
    ): Int {
        return try {
            val calendar = java.util.Calendar.getInstance()
            calendar.add(java.util.Calendar.DAY_OF_YEAR, -days)
            val startDate = String.format(
                "%04d-%02d-%02d",
                calendar.get(java.util.Calendar.YEAR),
                calendar.get(java.util.Calendar.MONTH) + 1,
                calendar.get(java.util.Calendar.DAY_OF_MONTH)
            )

            val snapshot = firestore.collection("users")
                .document(userId)
                .collection(collection)
                .whereGreaterThanOrEqualTo("date", startDate)
                .get()
                .await()

            // Count unique dates
            snapshot.documents.mapNotNull { it.getString("date") }.distinct().size
        } catch (e: Exception) {
            0
        }
    }

    private suspend fun getStepDays(
        firestore: com.google.firebase.firestore.FirebaseFirestore,
        userId: String,
        targetSteps: Int,
        days: Int
    ): Int {
        return try {
            // Simplified: return 0 for now, can be implemented with step tracking data
            0
        } catch (e: Exception) {
            0
        }
    }

    private fun unlockStage(stage: Int, emoji: String, bgColor: String) {
        val cardView = when (stage) {
            1 -> binding.cardStage1
            2 -> binding.cardStage2
            3 -> binding.cardStage3
            4 -> binding.cardStage4
            5 -> binding.cardStage5
            6 -> binding.cardStage6
            else -> return
        }

        val statusView = when (stage) {
            1 -> binding.tvStage1Status
            2 -> binding.tvStage2Status
            3 -> binding.tvStage3Status
            4 -> binding.tvStage4Status
            5 -> binding.tvStage5Status
            6 -> binding.tvStage6Status
            else -> return
        }

        cardView.setCardBackgroundColor(Color.parseColor(bgColor))
        statusView.text = "UNLOCKED"
        statusView.setTextColor(Color.parseColor("#4CAF50"))

        // Add checkmark icon
        val emojiView = cardView.getChildAt(0) as? android.widget.LinearLayout
        val iconView = emojiView?.getChildAt(0) as? android.widget.TextView
        if (stage == 5) {
            iconView?.text = "✓"
        } else if (stage == 6) {
            iconView?.text = "H"
        } else {
            iconView?.text = emoji
        }
    }

    private fun showStageDetails(stage: Int, title: String, description: String) {
        val message = "Stage $stage: $title\n\n$description"
        android.app.AlertDialog.Builder(this)
            .setTitle("🎯 $title")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }
}



