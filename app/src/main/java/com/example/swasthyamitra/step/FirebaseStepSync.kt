package com.example.swasthyamitra.step

import android.content.Context
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.*

/**
 * FirebaseStepSync handles the secure transmission of validated step data to the cloud.
 * It includes a specialized "Anomaly Detection" subsystem that identifies and filters 
 * unrealistic step counts (e.g., from cheating or device malfunctions).
 */
class FirebaseStepSync(private val context: Context) {

    private val firestore = FirebaseFirestore.getInstance()
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    companion object {
        private const val TAG = "FirebaseStepSync"
        private const val COLLECTION_STEPS = "daily_steps"
        
        // Anti-cheating thresholds:
        private const val MAX_DAILY_STEPS = 100000    // Unlikely for any human to exceed 100k
        private const val MAX_STEPS_PER_HOUR = 15000  // Maximum physically possible running cadence
    }

    /**
     * Synchronizes steps with Firestore while performing 3 layers of validation.
     * @param userId The current authenticated user ID.
     * @param validatedSteps Steps that have passed the local GPS/Sensor validation.
     * @param confidence The local validation's confidence score (0-100).
     */
    suspend fun syncValidatedSteps(
        userId: String,
        validatedSteps: Int,
        confidence: Double,
        activityType: String = "WALKING"
    ): Result<Unit> {
        return try {
            val today = dateFormat.format(Date())
            val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

            val docRef = firestore.collection("users").document(userId)
                .collection(COLLECTION_STEPS).document(today)

            val snapshot = docRef.get().await()
            val currentSteps = snapshot.getLong("totalSteps")?.toInt() ?: 0
            val hourlySteps = snapshot.get("hourlySteps") as? Map<String, Int> ?: emptyMap()

            // ---- ANOMALY DETECTION LAYER 1: DAILY CAPACITY ----
            // Prevents massive bulk uploads.
            if (validatedSteps > MAX_DAILY_STEPS) {
                return Result.failure(Exception("Daily limit exceeded"))
            }

            // ---- ANOMALY DETECTION LAYER 2: HOURLY CADENCE ----
            // Checks if the new steps since last sync are physically possible within an hour.
            val stepsThisHour = hourlySteps[currentHour.toString()] ?: 0
            val stepIncrement = validatedSteps - currentSteps
            if (stepsThisHour + stepIncrement > MAX_STEPS_PER_HOUR) {
                return Result.failure(Exception("Step rate too high"))
            }

            // ---- ANOMALY DETECTION LAYER 3: VELOCITY SPIKE ----
            // Prevents single large injections of data.
            if (stepIncrement > 5000) {
                return Result.failure(Exception("Sudden spike detected"))
            }

            // Update the hourly breakdown map
            val updatedHourlySteps = hourlySteps.toMutableMap()
            updatedHourlySteps[currentHour.toString()] = (updatedHourlySteps[currentHour.toString()] ?: 0) + stepIncrement

            // Bundle metadata for debugging and trust-score calculation
            val stepData = hashMapOf(
                "userId" to userId,
                "date" to today,
                "totalSteps" to validatedSteps,
                "confidence" to confidence,
                "activityType" to activityType,
                "hourlySteps" to updatedHourlySteps,
                "lastUpdated" to com.google.firebase.Timestamp.now(),
                "deviceId" to getDeviceId(),
                "validationLayers" to listOf(
                    "HardwareStepSensor",
                    "ActivityRecognition",
                    "MotionPattern",
                    "GestureFilter",
                    "CadenceValidation"
                )
            )

            // Merge ensures we don't overwrite other daily fields (like food/water logs)
            docRef.set(stepData, SetOptions.merge()).await()
            Result.success(Unit)

        } catch (e: Exception) {
            Log.e(TAG, "Sync error: ${e.message}")
            Result.failure(e)
        }
    }

    /**
     * Retrieves today's final validated count for local UI consistency.
     */
    suspend fun getTodaySteps(userId: String): Result<Int> {
        return try {
            val today = dateFormat.format(Date())
            val snapshot = firestore.collection("users").document(userId)
                .collection(COLLECTION_STEPS).document(today).get().await()

            Result.success(snapshot.getLong("totalSteps")?.toInt() ?: 0)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Fetches historical data (e.g., last 7 days) for progress charts.
     */
    suspend fun getStepHistory(userId: String, days: Int = 7): Result<List<DailyStepData>> {
        return try {
            val calendar = Calendar.getInstance()
            val endDate = dateFormat.format(calendar.time)
            calendar.add(Calendar.DAY_OF_YEAR, -days)
            val startDate = dateFormat.format(calendar.time)

            val querySnapshot = firestore.collection("users").document(userId)
                .collection(COLLECTION_STEPS)
                .whereGreaterThanOrEqualTo("date", startDate)
                .whereLessThanOrEqualTo("date", endDate)
                .orderBy("date").get().await()

            val history = querySnapshot.documents.mapNotNull { doc ->
                DailyStepData(
                    date = doc.getString("date") ?: "",
                    steps = doc.getLong("totalSteps")?.toInt() ?: 0,
                    confidence = doc.getDouble("confidence") ?: 0.0,
                    activityType = doc.getString("activityType") ?: "UNKNOWN"
                )
            }
            Result.success(history)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Captures a unique device ID to prevent multiple devices from syncing to one account 
     * simultaneously (which could double steps).
     */
    private fun getDeviceId(): String {
        return android.provider.Settings.Secure.getString(context.contentResolver, android.provider.Settings.Secure.ANDROID_ID)
    }
}


data class DailyStepData(
    val date: String,
    val steps: Int,
    val confidence: Double,
    val activityType: String
)

