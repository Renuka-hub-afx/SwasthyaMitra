package com.example.swasthyamitra.step

import android.content.Context
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.*

/**
 * Firebase Step Sync Manager
 * Handles secure storage and anomaly detection for validated steps
 */
class FirebaseStepSync(private val context: Context) {

    private val firestore = FirebaseFirestore.getInstance()
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    companion object {
        private const val TAG = "FirebaseStepSync"
        private const val COLLECTION_STEPS = "daily_steps"
        private const val MAX_DAILY_STEPS = 100000 // Anomaly threshold
        private const val MAX_STEPS_PER_HOUR = 15000 // Hourly anomaly threshold
    }

    /**
     * Sync validated steps to Firebase with anomaly detection
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

            // Fetch current data
            val docRef = firestore.collection("users")
                .document(userId)
                .collection(COLLECTION_STEPS)
                .document(today)

            val snapshot = docRef.get().await()
            val currentSteps = snapshot.getLong("totalSteps")?.toInt() ?: 0
            val hourlySteps = snapshot.get("hourlySteps") as? Map<String, Int> ?: emptyMap()

            // Anomaly Detection Layer 1: Daily limit check
            if (validatedSteps > MAX_DAILY_STEPS) {
                Log.w(TAG, "Anomaly detected: Daily steps exceed threshold ($validatedSteps)")
                return Result.failure(Exception("Step count exceeds daily maximum"))
            }

            // Anomaly Detection Layer 2: Hourly rate check
            val stepsThisHour = hourlySteps[currentHour.toString()] ?: 0
            if (stepsThisHour + (validatedSteps - currentSteps) > MAX_STEPS_PER_HOUR) {
                Log.w(TAG, "Anomaly detected: Hourly steps exceed threshold")
                return Result.failure(Exception("Step rate exceeds hourly maximum"))
            }

            // Anomaly Detection Layer 3: Sudden spike check
            if (validatedSteps - currentSteps > 5000) {
                Log.w(TAG, "Anomaly detected: Sudden step spike (+${validatedSteps - currentSteps})")
                return Result.failure(Exception("Sudden step increase detected"))
            }

            // Update hourly breakdown
            val updatedHourlySteps = hourlySteps.toMutableMap()
            updatedHourlySteps[currentHour.toString()] =
                (updatedHourlySteps[currentHour.toString()] ?: 0) + (validatedSteps - currentSteps)

            // Prepare data
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

            // Store with merge to preserve other fields
            docRef.set(stepData, SetOptions.merge()).await()

            Log.i(TAG, "Steps synced: $validatedSteps (confidence: $confidence%)")
            Result.success(Unit)

        } catch (e: Exception) {
            Log.e(TAG, "Failed to sync steps: ${e.message}")
            Result.failure(e)
        }
    }

    /**
     * Get today's validated steps from Firebase
     */
    suspend fun getTodaySteps(userId: String): Result<Int> {
        return try {
            val today = dateFormat.format(Date())

            val snapshot = firestore.collection("users")
                .document(userId)
                .collection(COLLECTION_STEPS)
                .document(today)
                .get()
                .await()

            val steps = snapshot.getLong("totalSteps")?.toInt() ?: 0
            Result.success(steps)

        } catch (e: Exception) {
            Log.e(TAG, "Failed to fetch today's steps: ${e.message}")
            Result.failure(e)
        }
    }

    /**
     * Get step history for analytics
     */
    suspend fun getStepHistory(
        userId: String,
        days: Int = 7
    ): Result<List<DailyStepData>> {
        return try {
            val calendar = Calendar.getInstance()
            val endDate = dateFormat.format(calendar.time)
            calendar.add(Calendar.DAY_OF_YEAR, -days)
            val startDate = dateFormat.format(calendar.time)

            val querySnapshot = firestore.collection("users")
                .document(userId)
                .collection(COLLECTION_STEPS)
                .whereGreaterThanOrEqualTo("date", startDate)
                .whereLessThanOrEqualTo("date", endDate)
                .orderBy("date")
                .get()
                .await()

            val history = querySnapshot.documents.mapNotNull { doc ->
                try {
                    DailyStepData(
                        date = doc.getString("date") ?: "",
                        steps = doc.getLong("totalSteps")?.toInt() ?: 0,
                        confidence = doc.getDouble("confidence") ?: 0.0,
                        activityType = doc.getString("activityType") ?: "UNKNOWN"
                    )
                } catch (e: Exception) {
                    null
                }
            }

            Result.success(history)

        } catch (e: Exception) {
            Log.e(TAG, "Failed to fetch step history: ${e.message}")
            Result.failure(e)
        }
    }

    private fun getDeviceId(): String {
        return android.provider.Settings.Secure.getString(
            context.contentResolver,
            android.provider.Settings.Secure.ANDROID_ID
        )
    }
}

data class DailyStepData(
    val date: String,
    val steps: Int,
    val confidence: Double,
    val activityType: String
)

