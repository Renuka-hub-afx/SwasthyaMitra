package com.example.swasthyamitra.ml

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

/**
 * Enhanced Progress Analyzer with Multi-Period Support
 * Analyzes user progress across 7 days, 15 days, and 1 month
 * Provides data points for smart graph visualization
 */
class EnhancedProgressAnalyzer(private val userId: String) {

    private val db = FirebaseFirestore.getInstance("renu")
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    enum class TimePeriod(val days: Int, val label: String) {
        WEEK(7, "7 Days"),
        TWO_WEEKS(15, "15 Days"),
        MONTH(30, "1 Month")
    }

    // ==================== WEIGHT ANALYSIS ====================

    suspend fun analyzeWeightProgress(period: TimePeriod): WeightProgressData {
        return try {
            val startDate = getDateBefore(period.days)
            val logs = db.collection("users")
                .document(userId)
                .collection("weightLogs")
                .whereGreaterThanOrEqualTo("date", startDate)
                .orderBy("date")
                .get()
                .await()

            val dataPoints = logs.documents.mapNotNull { doc ->
                val weight = (doc.get("weightKg") as? Number)?.toDouble()
                val date = doc.getString("date")
                if (weight != null && date != null) {
                    GraphDataPoint(date, weight.toFloat(), "Weight")
                } else null
            }

            val weights = dataPoints.map { it.value.toDouble() }

            if (weights.isEmpty()) {
                return WeightProgressData(
                    currentWeight = 0.0,
                    startingWeight = 0.0,
                    change = 0.0,
                    trend = "No Data",
                    predictedNextWeek = 0.0,
                    dataPoints = emptyList(),
                    period = period
                )
            }

            val current = weights.last()
            val starting = weights.first()
            val change = current - starting

            WeightProgressData(
                currentWeight = current,
                startingWeight = starting,
                change = change,
                trend = calculateWeightTrend(change),
                predictedNextWeek = predictWeight(weights),
                dataPoints = dataPoints,
                period = period
            )
        } catch (e: Exception) {
            android.util.Log.e("EnhancedAnalyzer", "Weight analysis error: ${e.message}")
            WeightProgressData(0.0, 0.0, 0.0, "Error", 0.0, emptyList(), period)
        }
    }

    // ==================== NUTRITION ANALYSIS ====================

    suspend fun analyzeNutritionProgress(period: TimePeriod): NutritionProgressData {
        return try {
            val startDate = getDateBefore(period.days)
            val logs = db.collection("users")
                .document(userId)
                .collection("foodLogs")
                .whereGreaterThanOrEqualTo("date", startDate)
                .get()
                .await()

            // Group by date for daily calories
            val dailyCalories = mutableMapOf<String, Int>()
            val dailyMacros = mutableMapOf<String, Macros>()

            logs.documents.forEach { doc ->
                val date = doc.getString("date") ?: return@forEach
                val calories = (doc.get("calories") as? Number)?.toInt() ?: 0
                val protein = (doc.get("protein") as? Number)?.toDouble() ?: 0.0
                val carbs = (doc.get("carbs") as? Number)?.toDouble() ?: 0.0
                val fat = (doc.get("fat") as? Number)?.toDouble() ?: 0.0

                dailyCalories[date] = (dailyCalories[date] ?: 0) + calories
                val current = dailyMacros[date] ?: Macros(0.0, 0.0, 0.0)
                dailyMacros[date] = Macros(
                    current.protein + protein,
                    current.carbs + carbs,
                    current.fat + fat
                )
            }

            val dataPoints = dailyCalories.entries.sortedBy { it.key }.map {
                GraphDataPoint(it.key, it.value.toFloat(), "Calories")
            }

            val avgCalories = if (dailyCalories.isNotEmpty()) {
                dailyCalories.values.average().toInt()
            } else 0

            val consistency = (dailyCalories.size.toDouble() / period.days * 100).toInt()

            NutritionProgressData(
                averageCalories = avgCalories,
                totalDays = dailyCalories.size,
                consistency = consistency,
                trend = analyzeCaloricTrend(avgCalories),
                dataPoints = dataPoints,
                recommendations = generateNutritionRecs(avgCalories, consistency),
                period = period
            )
        } catch (e: Exception) {
            android.util.Log.e("EnhancedAnalyzer", "Nutrition analysis error: ${e.message}")
            NutritionProgressData(0, 0, 0, "Error", emptyList(), emptyList(), period)
        }
    }

    // ==================== HYDRATION ANALYSIS ====================

    suspend fun analyzeHydrationProgress(period: TimePeriod): HydrationProgressData {
        return try {
            val startDate = getDateBefore(period.days)
            val logs = db.collection("users")
                .document(userId)
                .collection("waterLogs")
                .whereGreaterThanOrEqualTo("date", startDate)
                .get()
                .await()

            val dailyIntake = mutableMapOf<String, Int>()

            logs.documents.forEach { doc ->
                val date = doc.getString("date") ?: return@forEach
                val amount = (doc.get("amountML") as? Number)?.toInt() ?: 0
                dailyIntake[date] = (dailyIntake[date] ?: 0) + amount
            }

            val dataPoints = dailyIntake.entries.sortedBy { it.key }.map {
                GraphDataPoint(it.key, it.value.toFloat(), "Water (ml)")
            }

            val avgDaily = if (dailyIntake.isNotEmpty()) {
                dailyIntake.values.average().toInt()
            } else 0

            val goalAchievement = (avgDaily.toDouble() / 2500 * 100).toInt().coerceIn(0, 100)

            HydrationProgressData(
                averageDailyIntake = avgDaily,
                totalDays = dailyIntake.size,
                goalAchievement = goalAchievement,
                trend = if (avgDaily >= 2500) "Excellent" else if (avgDaily >= 2000) "Good" else "Needs Improvement",
                dataPoints = dataPoints,
                recommendations = generateHydrationRecs(avgDaily),
                period = period
            )
        } catch (e: Exception) {
            android.util.Log.e("EnhancedAnalyzer", "Hydration analysis error: ${e.message}")
            HydrationProgressData(0, 0, 0, "Error", emptyList(), emptyList(), period)
        }
    }

    // ==================== EXERCISE ANALYSIS ====================

    suspend fun analyzeExerciseProgress(period: TimePeriod): ExerciseProgressData {
        return try {
            val startDate = getDateBefore(period.days)
            val logs = db.collection("users")
                .document(userId)
                .collection("exercise_logs")
                .whereGreaterThanOrEqualTo("date", startDate)
                .get()
                .await()

            val dailyMinutes = mutableMapOf<String, Int>()

            logs.documents.forEach { doc ->
                val date = doc.getString("date") ?: return@forEach
                val minutes = (doc.get("durationMinutes") as? Number)?.toInt() ?: 0
                dailyMinutes[date] = (dailyMinutes[date] ?: 0) + minutes
            }

            val dataPoints = dailyMinutes.entries.sortedBy { it.key }.map {
                GraphDataPoint(it.key, it.value.toFloat(), "Minutes")
            }

            val totalMinutes = dailyMinutes.values.sum()
            val avgDaily = if (dailyMinutes.isNotEmpty()) {
                totalMinutes / dailyMinutes.size
            } else 0

            val activeDays = dailyMinutes.filter { it.value >= 15 }.size

            ExerciseProgressData(
                totalMinutes = totalMinutes,
                averageDailyMinutes = avgDaily,
                activeDays = activeDays,
                consistency = (activeDays.toDouble() / period.days * 100).toInt(),
                trend = if (activeDays >= period.days * 0.7) "Excellent" else if (activeDays >= period.days * 0.4) "Good" else "Needs Improvement",
                dataPoints = dataPoints,
                recommendations = generateExerciseRecs(avgDaily, activeDays),
                period = period
            )
        } catch (e: Exception) {
            android.util.Log.e("EnhancedAnalyzer", "Exercise analysis error: ${e.message}")
            ExerciseProgressData(0, 0, 0, 0, "Error", emptyList(), emptyList(), period)
        }
    }

    // ==================== HELPER FUNCTIONS ====================

    private fun getDateBefore(days: Int): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -days)
        return dateFormat.format(calendar.time)
    }

    private fun calculateWeightTrend(change: Double): String {
        return when {
            abs(change) < 0.5 -> "Stable →"
            change < 0 -> "Losing ${String.format("%.1f", abs(change))}kg ↓"
            else -> "Gaining ${String.format("%.1f", change)}kg ↑"
        }
    }

    private fun predictWeight(weights: List<Double>): Double {
        if (weights.size < 3) return weights.lastOrNull() ?: 0.0

        try {
            val n = weights.size
            val x = (0 until n).map { it.toDouble() }
            val y = weights
            val xMean = x.average()
            val yMean = y.average()

            val numerator = x.zip(y).sumOf { (xi, yi) -> (xi - xMean) * (yi - yMean) }
            val denominator = x.sumOf { (it - xMean) * (it - xMean) }

            if (denominator == 0.0) return weights.last()

            val slope = numerator / denominator
            val intercept = yMean - slope * xMean

            return slope * (n + 7) + intercept
        } catch (e: Exception) {
            return weights.last()
        }
    }

    private fun analyzeCaloricTrend(avg: Int): String {
        return when {
            avg < 1500 -> "Low Intake"
            avg in 1500..2200 -> "Balanced"
            else -> "High Intake"
        }
    }

    private fun generateNutritionRecs(avg: Int, consistency: Int): List<String> {
        val recs = mutableListOf<String>()
        when {
            avg < 1500 -> recs.add("⚠️ Increase calorie intake")
            avg > 2500 -> recs.add("⚠️ Consider portion control")
            else -> recs.add("✅ Good calorie balance")
        }
        if (consistency < 70) recs.add("📝 Log meals more consistently")
        return recs
    }

    private fun generateHydrationRecs(avg: Int): List<String> {
        return when {
            avg < 1500 -> listOf("💧 Drink ${2500 - avg}ml more daily", "Set hourly reminders")
            avg < 2500 -> listOf("💧 Increase by ${2500 - avg}ml", "Keep water bottle nearby")
            else -> listOf("✅ Excellent hydration!")
        }
    }

    private fun generateExerciseRecs(avg: Int, activeDays: Int): List<String> {
        val recs = mutableListOf<String>()
        when {
            avg < 15 -> recs.add("💪 Start with 15-min walks")
            avg < 30 -> recs.add("📈 Aim for 30 min daily")
            else -> recs.add("✅ Great activity level!")
        }
        if (activeDays < 3) recs.add("🎯 Be active 3+ days/week")
        return recs
    }

    // ==================== DATA CLASSES ====================

    data class GraphDataPoint(
        val date: String,
        val value: Float,
        val label: String
    )

    data class Macros(
        val protein: Double,
        val carbs: Double,
        val fat: Double
    )

    data class WeightProgressData(
        val currentWeight: Double,
        val startingWeight: Double,
        val change: Double,
        val trend: String,
        val predictedNextWeek: Double,
        val dataPoints: List<GraphDataPoint>,
        val period: TimePeriod
    )

    data class NutritionProgressData(
        val averageCalories: Int,
        val totalDays: Int,
        val consistency: Int,
        val trend: String,
        val dataPoints: List<GraphDataPoint>,
        val recommendations: List<String>,
        val period: TimePeriod
    )

    data class HydrationProgressData(
        val averageDailyIntake: Int,
        val totalDays: Int,
        val goalAchievement: Int,
        val trend: String,
        val dataPoints: List<GraphDataPoint>,
        val recommendations: List<String>,
        val period: TimePeriod
    )

    data class ExerciseProgressData(
        val totalMinutes: Int,
        val averageDailyMinutes: Int,
        val activeDays: Int,
        val consistency: Int,
        val trend: String,
        val dataPoints: List<GraphDataPoint>,
        val recommendations: List<String>,
        val period: TimePeriod
    )
}

