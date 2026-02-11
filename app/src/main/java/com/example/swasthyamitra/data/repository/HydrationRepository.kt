package com.example.swasthyamitra.data.repository

import com.example.swasthyamitra.data.model.WaterLog
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.*

class HydrationRepository {
    private val firestore = FirebaseFirestore.getInstance("renu") // Using RENU database instance
    private val waterLogsCollection = firestore.collection("waterLogs")

    suspend fun addWaterLog(userId: String, amountML: Int, targetDate: String? = null): Result<Unit> {
        return try {
            val dateStr = targetDate ?: SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
            
            val log = WaterLog(
                userId = userId,
                amountML = amountML,
                timestamp = System.currentTimeMillis(),
                date = dateStr
            )
            
            waterLogsCollection.add(log).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getWaterTotalForDate(userId: String, date: String): Result<Int> {
        return try {
            val snapshot = waterLogsCollection
                .whereEqualTo("userId", userId)
                .whereEqualTo("date", date)
                .get()
                .await()
            
            val total = snapshot.documents.sumOf { (it.get("amountML") as? Number)?.toInt() ?: 0 }
            Result.success(total)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getTodayWaterTotal(userId: String): Result<Int> {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return getWaterTotalForDate(userId, dateFormat.format(Date()))
    }

    suspend fun getWaterLogs(userId: String, date: String? = null, limit: Int = 20): Result<List<WaterLog>> {
        return try {
            android.util.Log.d("HydrationRepo", "Fetching logs for userId: $userId, date: $date")
            var query = waterLogsCollection
                .whereEqualTo("userId", userId)
            
            if (date != null) {
                query = query.whereEqualTo("date", date)
            }
            
            val snapshot = query
                .limit(limit.toLong())
                .get()
                .await()
            
            android.util.Log.d("HydrationRepo", "Found ${snapshot.documents.size} documents")
            
            val logs = snapshot.documents
                .mapNotNull { doc ->
                    val log = doc.toObject(WaterLog::class.java)
                    if (log == null) {
                        try {
                            WaterLog(
                                logId = doc.id,
                                userId = doc.getString("userId") ?: "",
                                amountML = (doc.get("amountML") as? Number)?.toInt() ?: 0,
                                timestamp = (doc.get("timestamp") as? Number)?.toLong() ?: doc.getTimestamp("timestamp")?.toDate()?.time ?: 0L,
                                date = doc.getString("date") ?: ""
                            )
                        } catch (e: Exception) {
                            null
                        }
                    } else {
                        log.copy(logId = doc.id)
                    }
                }
                .sortedByDescending { it.timestamp }
            
            Result.success(logs)
        } catch (e: Exception) {
            android.util.Log.e("HydrationRepo", "Error fetching logs", e)
            Result.failure(e)
        }
    }

    suspend fun deleteWaterLog(logId: String): Result<Unit> {
        return try {
            waterLogsCollection.document(logId).delete().await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getUserWaterGoal(userId: String): Result<Int> {
        return try {
            val snapshot = firestore.collection("users").document(userId).get().await()
            val goal = (snapshot.get("waterGoal") as? Number)?.toInt() ?: 2500
            Result.success(goal)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun setUserWaterGoal(userId: String, goal: Int): Result<Unit> {
        return try {
            firestore.collection("users").document(userId)
                .update("waterGoal", goal).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Calculate and save personalized water goal based on user's weight
     */
    suspend fun calculateAndSaveGoal(userId: String, weightKg: Double, activityLevel: String = "moderate"): Result<Int> {
        return try {
            val goal = com.example.swasthyamitra.utils.WaterGoalCalculator.calculateDailyGoalWithActivity(weightKg, activityLevel)
            firestore.collection("users").document(userId)
                .update(mapOf(
                    "waterGoal" to goal,
                    "weight" to weightKg,
                    "activityLevel" to activityLevel
                )).await()
            Result.success(goal)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Get water goal with automatic calculation if not set
     */
    suspend fun getWaterGoalWithCalculation(userId: String): Result<Int> {
        return try {
            val userDoc = firestore.collection("users").document(userId).get().await()
            
            // Try to get saved goal first
            val savedGoal = (userDoc.get("waterGoal") as? Number)?.toInt()
            if (savedGoal != null && savedGoal > 0) {
                return Result.success(savedGoal)
            }
            
            // Calculate based on weight if available
            val weight = (userDoc.get("weight") as? Number)?.toDouble()
            if (weight != null && weight > 0) {
                val activityLevel = userDoc.getString("activityLevel") ?: "moderate"
                val calculatedGoal = com.example.swasthyamitra.utils.WaterGoalCalculator.calculateDailyGoalWithActivity(weight, activityLevel)
                
                // Save the calculated goal
                firestore.collection("users").document(userId)
                    .update("waterGoal", calculatedGoal).await()
                
                Result.success(calculatedGoal)
            } else {
                // Return default if no weight available
                Result.success(2500)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Update user's water reminder schedule
     */
    suspend fun updateUserWaterSchedule(userId: String, wakeTime: String, sleepTime: String): Result<Unit> {
        return try {
            firestore.collection("users").document(userId)
                .update(mapOf(
                    "wakeTime" to wakeTime,
                    "sleepTime" to sleepTime
                )).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Get user's water schedule
     */
    suspend fun getUserWaterSchedule(userId: String): Result<Pair<String, String>> {
        return try {
            val userDoc = firestore.collection("users").document(userId).get().await()
            val wakeTime = userDoc.getString("wakeTime") ?: "07:00"
            val sleepTime = userDoc.getString("sleepTime") ?: "23:00"
            Result.success(Pair(wakeTime, sleepTime))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Get user's weight for goal calculation
     */
    suspend fun getUserWeight(userId: String): Result<Double> {
        return try {
            val userDoc = firestore.collection("users").document(userId).get().await()
            val weight = (userDoc.get("weight") as? Number)?.toDouble() ?: 70.0
            Result.success(weight)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Get today's progress percentage
     */
    suspend fun getTodayProgress(userId: String): Result<Int> {
        return try {
            val totalResult = getTodayWaterTotal(userId)
            val goalResult = getWaterGoalWithCalculation(userId)
            
            if (totalResult.isSuccess && goalResult.isSuccess) {
                val total = totalResult.getOrDefault(0)
                val goal = goalResult.getOrDefault(2500)
                val progress = com.example.swasthyamitra.utils.WaterGoalCalculator.calculateProgress(total, goal)
                Result.success(progress)
            } else {
                Result.success(0)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
