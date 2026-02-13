package com.example.swasthyamitra.ml

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.*

class ProgressAnalyzer(private val userId: String) {

    private val db = FirebaseFirestore.getInstance("renu")
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    // Time period enum for analysis
    enum class TimePeriod(val days: Int, val label: String) {
        WEEK(7, "7 Days"),
        TWO_WEEKS(15, "15 Days"),
        MONTH(30, "1 Month")
    }

    suspend fun analyzeWeightProgress(period: TimePeriod = TimePeriod.MONTH): WeightProgress {
        return try {
            val logs = db.collection("users")
                .document(userId)
                .collection("weightLogs")
                .orderBy("timestamp")
                .limit(30)
                .get()
                .await()

            val weights = logs.documents.mapNotNull {
                (it.get("weightKg") as? Number)?.toDouble()
            }

            if (weights.isEmpty()) {
                return WeightProgress(0.0, 0.0, "No Data", 0.0, 0)
            }

            WeightProgress(
                currentWeight = weights.lastOrNull() ?: 0.0,
                startingWeight = weights.firstOrNull() ?: 0.0,
                trend = calculateTrend(weights),
                predictedWeightNextWeek = predictWeight(weights),
                achievement = calculateAchievement(weights)
            )
        } catch (e: Exception) {
            android.util.Log.e("ProgressAnalyzer", "Error analyzing weight: ${e.message}")
            WeightProgress(0.0, 0.0, "Error", 0.0, 0)
        }
    }

    suspend fun analyzeNutritionProgress(): NutritionProgress {
        return try {
            val logs = db.collection("users")
                .document(userId)
                .collection("foodLogs")
                .whereGreaterThanOrEqualTo("date", getLast7DaysDate())
                .get()
                .await()

            val caloriesList = logs.documents.mapNotNull {
                (it.get("calories") as? Number)?.toInt()
            }

            if (caloriesList.isEmpty()) {
                return NutritionProgress(0, "No Data", 0, listOf("Start logging meals"))
            }

            val avgCalories = caloriesList.average().toInt()

            NutritionProgress(
                averageCalories = avgCalories,
                trend = if (avgCalories < 2000) "Deficit" else "Surplus",
                consistency = calculateConsistency(logs.size(), 21), // 3 meals * 7 days
                recommendations = generateNutritionRecommendations(avgCalories)
            )
        } catch (e: Exception) {
            android.util.Log.e("ProgressAnalyzer", "Error analyzing nutrition: ${e.message}")
            NutritionProgress(0, "Error", 0, emptyList())
        }
    }

    suspend fun analyzeHydrationProgress(): HydrationProgress {
        return try {
            val logs = db.collection("users")
                .document(userId)
                .collection("waterLogs")
                .whereGreaterThanOrEqualTo("date", getLast7DaysDate())
                .get()
                .await()

            val totalWater = logs.documents.sumOf {
                (it.get("amountML") as? Number)?.toLong() ?: 0
            }
            val avgDaily = totalWater / 7

            HydrationProgress(
                averageDailyIntake = avgDaily.toInt(),
                goalAchievement = (avgDaily / 2500.0 * 100).toInt(), // 2.5L goal
                trend = if (avgDaily >= 2500) "Good" else "Need Improvement",
                recommendations = if (avgDaily < 2500)
                    listOf("Increase water intake by ${2500 - avgDaily}ml daily")
                else
                    listOf("Great hydration! Keep it up")
            )
        } catch (e: Exception) {
            android.util.Log.e("ProgressAnalyzer", "Error analyzing hydration: ${e.message}")
            HydrationProgress(0, 0, "Error", emptyList())
        }
    }

    suspend fun analyzeExerciseProgress(): ExerciseProgress {
        return try {
            val logs = db.collection("users")
                .document(userId)
                .collection("exercise_logs")
                .whereGreaterThanOrEqualTo("date", getLast7DaysDate())
                .get()
                .await()

            val totalMinutes = logs.documents.sumOf {
                (it.get("durationMinutes") as? Number)?.toInt() ?: 0
            }
            val totalCalories = logs.documents.sumOf {
                (it.get("caloriesBurned") as? Number)?.toInt() ?: 0
            }
            val avgDaily = totalMinutes / 7
            val daysExercised = logs.documents.map { it.getString("date") }.distinct().size

            ExerciseProgress(
                totalMinutesWeek = totalMinutes,
                averageDailyMinutes = avgDaily,
                totalCaloriesBurned = totalCalories,
                daysActive = daysExercised,
                consistency = (daysExercised * 100 / 7),
                trend = if (avgDaily >= 30) "Excellent" else "Need Improvement",
                recommendations = generateExerciseRecommendations(avgDaily, daysExercised)
            )
        } catch (e: Exception) {
            android.util.Log.e("ProgressAnalyzer", "Error analyzing exercise: ${e.message}")
            ExerciseProgress(0, 0, 0, 0, 0, "Error", emptyList())
        }
    }

    private fun calculateTrend(weights: List<Double>): String {
        if (weights.size < 2) return "Insufficient Data"
        val firstHalf = weights.take(weights.size / 2).average()
        val secondHalf = weights.takeLast(weights.size / 2).average()
        val diff = firstHalf - secondHalf
        return when {
            diff > 0.5 -> "Losing Weight ↓"
            diff < -0.5 -> "Gaining Weight ↑"
            else -> "Stable →"
        }
    }

    private fun predictWeight(weights: List<Double>): Double {
        if (weights.size < 3) return weights.lastOrNull() ?: 0.0

        try {
            // Simple linear regression
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

            return slope * (n + 7) + intercept // Predict for next week
        } catch (e: Exception) {
            return weights.last()
        }
    }

    private fun calculateAchievement(weights: List<Double>): Int {
        if (weights.size < 2) return 0
        val progress = weights.first() - weights.last()
        val target = weights.first() * 0.05 // 5% weight loss target
        return ((progress / target) * 100).toInt().coerceIn(0, 100)
    }

    private fun calculateConsistency(actual: Int, expected: Int): Int {
        if (expected == 0) return 0
        return (actual.toDouble() / expected * 100).toInt().coerceIn(0, 100)
    }

    private fun generateNutritionRecommendations(avgCalories: Int): List<String> {
        return when {
            avgCalories < 1500 -> listOf(
                "⚠️ Calorie intake is low",
                "Add protein-rich foods",
                "Include healthy fats"
            )
            avgCalories > 2500 -> listOf(
                "⚠️ Calorie intake is high",
                "Focus on portion control",
                "Increase vegetables"
            )
            else -> listOf(
                "✅ Good calorie balance",
                "Maintain consistency",
                "Stay hydrated"
            )
        }
    }

    private fun generateExerciseRecommendations(avgMinutes: Int, daysActive: Int): List<String> {
        return when {
            avgMinutes < 15 -> listOf(
                "⚠️ Try to exercise more",
                "Start with 15 min walks",
                "Set small daily goals"
            )
            avgMinutes < 30 -> listOf(
                "📈 Good progress!",
                "Aim for 30 min daily",
                "Try varied exercises"
            )
            else -> listOf(
                "✅ Excellent activity!",
                "Keep up the momentum",
                "Track your improvements"
            )
        }
    }

    private fun getLast7DaysDate(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -7)
        return String.format(
            "%04d-%02d-%02d",
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH) + 1,
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    data class WeightProgress(
        val currentWeight: Double,
        val startingWeight: Double,
        val trend: String,
        val predictedWeightNextWeek: Double,
        val achievement: Int
    )

    data class NutritionProgress(
        val averageCalories: Int,
        val trend: String,
        val consistency: Int,
        val recommendations: List<String>
    )

    data class HydrationProgress(
        val averageDailyIntake: Int,
        val goalAchievement: Int,
        val trend: String,
        val recommendations: List<String>
    )

    data class ExerciseProgress(
        val totalMinutesWeek: Int,
        val averageDailyMinutes: Int,
        val totalCaloriesBurned: Int,
        val daysActive: Int,
        val consistency: Int,
        val trend: String,
        val recommendations: List<String>
    )
}

