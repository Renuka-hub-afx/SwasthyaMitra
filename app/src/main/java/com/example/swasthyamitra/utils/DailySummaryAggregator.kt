package com.example.swasthyamitra.utils

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await

/**
 * Handles automatic aggregation of daily health data into DailySummary collection.
 * Updates progress percentages and maintains daily totals for all health metrics.
 */
class DailySummaryAggregator(private val userId: String) {
    
    private val db: FirebaseFirestore by lazy {
        try {
            FirebaseFirestore.getInstance(Constants.Database.FIRESTORE_INSTANCE_NAME)
        } catch (e: Exception) {
            FirebaseFirestore.getInstance()
        }
    }
    
    private fun getDailySummaryRef(date: String) =
        db.collection(Constants.Collections.USERS)
            .document(userId)
            .collection(Constants.Collections.DAILY_SUMMARY)
            .document(date)
    
    /**
     * Updates the lastActive timestamp in the user's profile.
     * Called automatically by all aggregation methods.
     */
    private suspend fun updateLastActive() {
        try {
            db.collection(Constants.Collections.USERS)
                .document(userId)
                .update("lastActive", DateTimeHelper.currentISO8601())
                .await()
        } catch (e: Exception) {
            android.util.Log.e("DailySummaryAggregator", "Failed to update lastActive: ${e.message}")
        }
    }
    
    /**
     * Updates food-related metrics in daily summary.
     */
    suspend fun updateFoodMetrics(
        date: String,
        calories: Int,
        protein: Double,
        carbs: Double,
        fat: Double
    ) {
        val summaryRef = getDailySummaryRef(date)
        
        try {
            val snapshot = summaryRef.get().await()
            val currentCalories = snapshot.getLong("totalCaloriesConsumed")?.toInt() ?: 0
            val currentProtein = snapshot.getDouble("totalProtein") ?: 0.0
            val currentCarbs = snapshot.getDouble("totalCarbs") ?: 0.0
            val currentFat = snapshot.getDouble("totalFat") ?: 0.0
            
            val updates = hashMapOf<String, Any>(
                "totalCaloriesConsumed" to (currentCalories + calories),
                "totalProtein" to (currentProtein + protein),
                "totalCarbs" to (currentCarbs + carbs),
                "totalFat" to (currentFat + fat),
                "updatedAt" to DateTimeHelper.currentISO8601()
            )
            
            summaryRef.set(updates, SetOptions.merge()).await()
        } catch (e: Exception) {
            android.util.Log.e("DailySummaryAggregator", "Failed to update food metrics: ${e.message}")
        }
    }
    
    /**
     * Updates exercise-related metrics in daily summary.
     */
    suspend fun updateExerciseMetrics(
        date: String,
        caloriesBurned: Int,
        minutes: Int
    ) {
        val summaryRef = getDailySummaryRef(date)
        
        try {
            val snapshot = summaryRef.get().await()
            val currentCaloriesBurned = snapshot.getLong("totalCaloriesBurned")?.toInt() ?: 0
            val currentMinutes = snapshot.getLong("exerciseMinutes")?.toInt() ?: 0
            
            val updates = hashMapOf<String, Any>(
                "totalCaloriesBurned" to (currentCaloriesBurned + caloriesBurned),
                "exerciseMinutes" to (currentMinutes + minutes),
                "updatedAt" to DateTimeHelper.currentISO8601()
            )
            
            summaryRef.set(updates, SetOptions.merge()).await()
            updateLastActive()
        } catch (e: Exception) {
            android.util.Log.e("DailySummaryAggregator", "Failed to update exercise metrics: ${e.message}")
        }
    }
    
    /**
     * Updates water intake metrics and calculates progress percentage.
     */
    suspend fun updateWaterMetrics(
        date: String,
        amountML: Int,
        goalML: Int
    ) {
        val summaryRef = getDailySummaryRef(date)
        
        try {
            val snapshot = summaryRef.get().await()
            val currentWater = snapshot.getLong("waterIntakeML")?.toInt() ?: 0
            val newWaterTotal = currentWater + amountML
            val progress = calculateProgressPercentage(newWaterTotal, goalML)
            
            val updates = hashMapOf<String, Any>(
                "waterIntakeML" to newWaterTotal,
                "waterGoalML" to goalML,
                "waterProgress" to progress,
                "updatedAt" to DateTimeHelper.currentISO8601()
            )
            
            summaryRef.set(updates, SetOptions.merge()).await()
            updateLastActive()
        } catch (e: Exception) {
            android.util.Log.e("DailySummaryAggregator", "Failed to update water metrics: ${e.message}")
        }
    }
    
    /**
     * Updates step metrics and calculates progress percentage.
     */
    suspend fun updateStepMetrics(
        date: String,
        steps: Int,
        stepGoal: Int,
        distanceMeters: Double = 0.0
    ) {
        val summaryRef = getDailySummaryRef(date)
        
        try {
            val progress = calculateProgressPercentage(steps, stepGoal)
            
            val updates = hashMapOf<String, Any>(
                "steps" to steps,
                "stepGoal" to stepGoal,
                "stepProgress" to progress,
                "distanceMeters" to distanceMeters,
                "updatedAt" to DateTimeHelper.currentISO8601()
            )
            
            summaryRef.set(updates, SetOptions.merge()).await()
            updateLastActive()
        } catch (e: Exception) {
            android.util.Log.e("DailySummaryAggregator", "Failed to update step metrics: ${e.message}")
        }
    }
    
    /**
     * Updates sleep metrics.
     */
    suspend fun updateSleepMetrics(
        date: String,
        sleepMinutes: Int
    ) {
        val summaryRef = getDailySummaryRef(date)
        
        try {
            val updates = hashMapOf<String, Any>(
                "sleepMinutes" to sleepMinutes,
                "updatedAt" to DateTimeHelper.currentISO8601()
            )
            
            summaryRef.set(updates, SetOptions.merge()).await()
            updateLastActive()
        } catch (e: Exception) {
            android.util.Log.e("DailySummaryAggregator", "Failed to update sleep metrics: ${e.message}")
        }
    }
    
    /**
     * Marks that mood was logged for the day.
     */
    suspend fun markMoodLogged(date: String) {
        val summaryRef = getDailySummaryRef(date)
        
        try {
            val updates = hashMapOf<String, Any>(
                "moodLogged" to true,
                "updatedAt" to DateTimeHelper.currentISO8601()
            )
            
            summaryRef.set(updates, SetOptions.merge()).await()
            updateLastActive()
        } catch (e: Exception) {
            android.util.Log.e("DailySummaryAggregator", "Failed to mark mood logged: ${e.message}")
        }
    }
    
    /**
     * Marks that weight was logged for the day.
     */
    suspend fun markWeightLogged(date: String) {
        val summaryRef = getDailySummaryRef(date)
        
        try {
            val updates = hashMapOf<String, Any>(
                "weightLogged" to true,
                "updatedAt" to DateTimeHelper.currentISO8601()
            )
            
            summaryRef.set(updates, SetOptions.merge()).await()
            updateLastActive()
        } catch (e: Exception) {
            android.util.Log.e("DailySummaryAggregator", "Failed to mark weight logged: ${e.message}")
        }
    }
    
    /**
     * Retrieves daily summary for a specific date.
     */
    suspend fun getDailySummary(date: String): Map<String, Any>? {
        return try {
            val snapshot = getDailySummaryRef(date).get().await()
            if (snapshot.exists()) {
                snapshot.data
            } else {
                null
            }
        } catch (e: Exception) {
            android.util.Log.e("DailySummaryAggregator", "Failed to get daily summary: ${e.message}")
            null
        }
    }
    
    /**
     * Calculates progress percentage with cap at 100%.
     */
    fun calculateProgressPercentage(actual: Int, target: Int): Int {
        if (target <= 0) return 0
        val percentage = (actual * 100) / target
        return minOf(percentage, 100)
    }
    
    /**
     * Calculates progress percentage with cap at 100% (double version).
     */
    fun calculateProgressPercentage(actual: Double, target: Double): Int {
        if (target <= 0.0) return 0
        val percentage = ((actual * 100.0) / target).toInt()
        return minOf(percentage, 100)
    }
}
