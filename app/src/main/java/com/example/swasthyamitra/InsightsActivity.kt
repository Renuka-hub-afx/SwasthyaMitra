package com.example.swasthyamitra

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.launch

class InsightsActivity : AppCompatActivity() {

    private lateinit var combinedChart: CombinedChart
    private lateinit var tvInsightNarrative: TextView
    private lateinit var tvBalanceScore: TextView
    private lateinit var tvScoreCategory: TextView
    private lateinit var tvMicroGoal: TextView
    
    private lateinit var repository: InsightsRepository
    private lateinit var authHelper: FirebaseAuthHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insights)

        initViews()
        
        val userApp = application as UserApplication
        authHelper = userApp.authHelper
        val userId = authHelper.getCurrentUser()?.uid
        if (userId == null) {
            Log.e("InsightsActivity", "User not logged in!")
            tvInsightNarrative.text = "Please log in to see your weekly insights."
            return
        }
        
        Log.d("InsightsActivity", "Loading insights for user: $userId")
        repository = InsightsRepository(authHelper, userId)
        loadInsights()
    }

    private fun initViews() {
        combinedChart = findViewById(R.id.combinedChart)
        tvInsightNarrative = findViewById(R.id.tvInsightNarrative)
        tvBalanceScore = findViewById(R.id.tvBalanceScore)
        tvScoreCategory = findViewById(R.id.tvScoreCategory)
        tvMicroGoal = findViewById(R.id.tvMicroGoal)

        findViewById<android.view.View>(R.id.btnScoreInfo).setOnClickListener {
            showScoreInfoDialog()
        }
        
        findViewById<android.view.View>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }

    private fun showScoreInfoDialog() {
        android.app.AlertDialog.Builder(this)
            .setTitle("Score Calculation 📊")
            .setMessage("Your Workout Balance Score (0-100) is calculated based on:\n\n" +
                    "1. Consistency (40%): Frequency of workouts per week (target: 4 days).\n" +
                    "2. Activity (30%): Average daily steps compared to your 5,000 step goal.\n" +
                    "3. Balance (30%): How close you are to your daily calorie target.\n\n" +
                    "Aim for consistent movement and balanced nutrition to boost your score!")
            .setPositiveButton("Got it") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun loadInsights() {
        lifecycleScope.launch {
            try {
                Log.d("InsightsActivity", "Fetching metrics from repository...")
                val metrics = repository.getWeeklyMetrics()
                Log.d("InsightsActivity", "Metrics received successfully: Score=${metrics.balanceScore}")
                updateUI(metrics)
            } catch (e: Exception) {
                Log.e("InsightsActivity", "Error loading insights", e)
                tvInsightNarrative.text = "Error loading insights: ${e.message}"
            }
        }
    }

    private fun updateUI(metrics: WeeklyMetrics) {
        tvBalanceScore.text = metrics.balanceScore.toString()
        tvScoreCategory.text = metrics.category
        tvInsightNarrative.text = metrics.narrative
        tvMicroGoal.text = metrics.microGoal

        setupCombinedChart(metrics.insights)
    }

    private fun setupCombinedChart(insights: List<DailyInsight>) {
        val labels = insights.map { it.dayName }
        
        combinedChart.description.isEnabled = false
        combinedChart.setDrawGridBackground(false)
        combinedChart.setDrawBarShadow(false)
        combinedChart.isHighlightFullBarEnabled = false

        // Draw order: bars behind lines
        combinedChart.drawOrder = arrayOf(
            CombinedChart.DrawOrder.BAR,
            CombinedChart.DrawOrder.LINE
        )

        val data = CombinedData()
        data.setData(generateBarData(insights))
        data.setData(generateLineData(insights))

        combinedChart.data = data
        
        val xAxis = combinedChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.valueFormatter = IndexAxisValueFormatter(labels)
        xAxis.granularity = 1f
        xAxis.setDrawGridLines(false)

        combinedChart.axisRight.isEnabled = true
        combinedChart.axisRight.setDrawGridLines(false)
        combinedChart.axisRight.textColor = Color.parseColor("#FB8C00") // Steps Color
        combinedChart.axisRight.axisMinimum = 0f
        combinedChart.axisRight.granularity = 1f // Integer steps only
        
        combinedChart.axisLeft.setDrawGridLines(false)
        combinedChart.axisLeft.textColor = Color.parseColor("#7B2CBF") // Calories Color
        combinedChart.axisLeft.axisMinimum = 0f

        combinedChart.notifyDataSetChanged()
        combinedChart.animateY(1000)
        combinedChart.invalidate()
        
        Log.d("InsightsActivity", "Chart invalidated and updated.")
    }

    private fun generateBarData(insights: List<DailyInsight>): BarData {
        val entries = insights.mapIndexed { index, insight ->
            BarEntry(index.toFloat(), insight.caloriesConsumed.toFloat())
        }
        val set = BarDataSet(entries, "Calories")
        set.color = Color.parseColor("#7B2CBF")
        set.valueTextColor = Color.BLACK
        set.valueTextSize = 10f
        set.axisDependency = YAxis.AxisDependency.LEFT
        
        return BarData(set)
    }

    private fun generateLineData(insights: List<DailyInsight>): LineData {
        val entries = insights.mapIndexed { index, insight ->
            Entry(index.toFloat(), insight.steps.toFloat())
        }
        val set = LineDataSet(entries, "Steps")
        set.color = Color.parseColor("#FB8C00")
        set.setCircleColor(Color.parseColor("#FB8C00"))
        set.lineWidth = 2.5f
        set.circleRadius = 4f
        set.setDrawValues(false)
        set.mode = LineDataSet.Mode.CUBIC_BEZIER
        set.axisDependency = YAxis.AxisDependency.RIGHT
        
        return LineData(set)
    }
}
