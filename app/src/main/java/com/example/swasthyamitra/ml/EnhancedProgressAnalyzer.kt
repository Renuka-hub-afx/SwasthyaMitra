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
    private val rtdb = com.google.firebase.database.FirebaseDatabase.getInstance("https://swasthyamitra-ded44-default-rtdb.asia-southeast1.firebasedatabase.app").reference
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    private var cachedHistory: Map<String, Boolean>? = null

    fun setCompletionHistory(history: Map<String, Boolean>) {
        this.cachedHistory = history
    }

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

            // Fetch actual user weight and goal if missing from logs
            val userDoc = db.collection("users").document(userId).get().await()
            val profileWeight = (userDoc.get("weight") as? Number)?.toDouble() ?: 70.0
            
            val goalSnapshot = db.collection("users").document(userId).collection("goals").limit(1).get().await()
            val targetWeightVal = if (!goalSnapshot.isEmpty) goalSnapshot.documents[0].getDouble("targetWeight") ?: 55.0 else 55.0

            val dataPoints = logs.documents.mapNotNull { doc ->
                val weight = (doc.get("weight") as? Number)?.toDouble()
                val date = doc.getString("date")
                if (weight != null && date != null) {
                    GraphDataPoint(date, weight.toFloat(), "Weight")
                } else null
            }

            val weights = dataPoints.map { it.value.toDouble() }

            if (weights.isEmpty()) {
                val rtdbHistory = fetchCompletionHistory()
                if (rtdbHistory.isNotEmpty()) {
                    // Use profile weight as starting point if no logs exist
                    val dataPointsBackfilled = rtdbHistory.filter { it.value && isWithinPeriod(it.key, period) }
                        .map { (date, _) -> GraphDataPoint(date, profileWeight.toFloat(), "Weight (est)") }
                        .sortedBy { it.date }
                    
                    if (dataPointsBackfilled.isNotEmpty()) {
                        return WeightProgressData(
                            currentWeight = profileWeight,
                            startingWeight = profileWeight,
                            targetWeight = targetWeightVal,
                            change = 0.0,
                            trend = "Stable",
                            predictedNextWeek = profileWeight,
                            dataPoints = dataPointsBackfilled,
                            period = period
                        )
                    }
                }
                return WeightProgressData(
                    currentWeight = profileWeight,
                    startingWeight = profileWeight,
                    targetWeight = targetWeightVal,
                    change = 0.0,
                    trend = "No Data",
                    predictedNextWeek = profileWeight,
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
                targetWeight = targetWeightVal,
                change = change,
                trend = calculateWeightTrend(change),
                predictedNextWeek = predictWeight(weights),
                dataPoints = dataPoints,
                period = period
            )
        } catch (e: Exception) {
            android.util.Log.e("EnhancedAnalyzer", "Weight analysis error: ${e.message}")
            WeightProgressData(0.0, 0.0, 55.0, 0.0, "Error", 0.0, emptyList(), period)
        }
    }

    // ==================== NUTRITION ANALYSIS ====================

    suspend fun analyzeNutritionProgress(period: TimePeriod): NutritionProgressData {
        return try {
            val startDate = getDateBefore(period.days)
            val logs = db.collection("users")
                .document(userId)
                .collection("foodLogs")
                .get()
                .await()

            val rtdbHistory = fetchCompletionHistory()
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

            // Augment with boosted data - Use MAX to allow boost to override partial logs
            rtdbHistory.forEach { (date, completed) ->
                if (completed) {
                    if (isWithinPeriod(date, period)) {
                        val current = dailyCalories[date] ?: 0
                        dailyCalories[date] = Math.max(current, 1800) // Ensure at least 1800 if boosted
                    }
                }
            }

            // Final filter for dailyCalories to ensure only requested period is included
            val filteredCalories = dailyCalories.filter { isWithinPeriod(it.key, period) }

            val dataPoints = filteredCalories.entries.sortedBy { it.key }.map {
                GraphDataPoint(it.key, it.value.toFloat(), "Calories")
            }

            val avgCalories = if (filteredCalories.isNotEmpty()) {
                filteredCalories.values.average().toInt()
            } else 0

            val consistency = (filteredCalories.size.toDouble() / period.days * 100).toInt()

            NutritionProgressData(
                averageCalories = avgCalories,
                totalDays = filteredCalories.size,
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
                .get()
                .await()

            val rtdbHistory = fetchCompletionHistory()
            val dailyIntake = mutableMapOf<String, Int>()

            logs.documents.forEach { doc ->
                val date = doc.getString("date") ?: return@forEach
                val amount = (doc.get("amountML") as? Number)?.toInt() ?: 0
                dailyIntake[date] = (dailyIntake[date] ?: 0) + amount
            }

            rtdbHistory.forEach { (date, completed) ->
                if (completed) {
                   if (isWithinPeriod(date, period)) {
                       val current = dailyIntake[date] ?: 0
                       dailyIntake[date] = Math.max(current, 2500) // Ensure full goal if boosted
                   }
                }
            }

            val filteredIntake = dailyIntake.filter { isWithinPeriod(it.key, period) }
            val dataPoints = filteredIntake.entries.sortedBy { it.key }.map {
                GraphDataPoint(it.key, it.value.toFloat(), "Water (ml)")
            }

            val avgDaily = if (filteredIntake.isNotEmpty()) {
                filteredIntake.values.average().toInt()
            } else 0

            val goalAchievement = (avgDaily.toDouble() / 2500 * 100).toInt().coerceIn(0, 100)

            HydrationProgressData(
                averageDailyIntake = avgDaily,
                totalDays = filteredIntake.size,
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
                .get()
                .await()

            val rtdbHistory = fetchCompletionHistory()
            val dailyMinutes = mutableMapOf<String, Int>()

            logs.documents.forEach { doc ->
                val date = doc.getString("date") ?: return@forEach
                val minutes = (doc.get("duration") as? Number)?.toInt() ?: 0
                dailyMinutes[date] = (dailyMinutes[date] ?: 0) + minutes
            }

            rtdbHistory.forEach { (date, completed) ->
                if (completed) {
                    if (isWithinPeriod(date, period)) {
                        val current = dailyMinutes[date] ?: 0
                        dailyMinutes[date] = Math.max(current, 45) // Ensure active minutes if boosted
                    }
                }
            }

            val filteredMinutes = dailyMinutes.filter { isWithinPeriod(it.key, period) }
            val dataPoints = filteredMinutes.entries.sortedBy { it.key }.map {
                GraphDataPoint(it.key, it.value.toFloat(), "Minutes")
            }

            val totalMinutes = filteredMinutes.values.sum()
            val avgDaily = if (filteredMinutes.isNotEmpty()) {
                totalMinutes / filteredMinutes.size
            } else 0

            val activeDays = filteredMinutes.filter { it.value >= 15 }.size

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

    private suspend fun fetchCompletionHistory(): Map<String, Boolean> {
        cachedHistory?.let { return it }
        return try {
            val snapshot = rtdb.child("users").child(userId).child("completionHistory").get().await()
            val history = mutableMapOf<String, Boolean>()
            snapshot.children.forEach { child ->
                val date = child.key ?: return@forEach
                val completed = child.getValue(Boolean::class.java) ?: false
                history[date] = completed
            }
            cachedHistory = history
            history
        } catch (e: Exception) {
            emptyMap()
        }
    }

    private fun isWithinPeriod(dateStr: String, period: TimePeriod): Boolean {
        return try {
            val date = dateFormat.parse(dateStr) ?: return false
            val cutoff = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
                add(Calendar.DAY_OF_YEAR, -period.days)
            }
            !date.before(cutoff.time)
        } catch (e: Exception) {
            false
        }
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
        val targetWeight: Double,
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

