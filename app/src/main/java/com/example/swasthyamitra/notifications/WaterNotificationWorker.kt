package com.example.swasthyamitra.notifications

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.example.swasthyamitra.notifications.NotificationConstants
import com.example.swasthyamitra.notifications.NotificationHelper
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class WaterNotificationWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    private val notificationHelper = NotificationHelper(appContext)
    private val authHelper = FirebaseAuthHelper(appContext)

    override suspend fun doWork(): Result {
        Log.d("WaterWorker", "Checking hydration schedule...")
        
        val user = authHelper.getCurrentUser()
        if (user == null) {
            Log.d("WaterWorker", "User not logged in, skipping.")
            return Result.failure()
        }

        try {
            // Fetch User Profile for Wake/Sleep times
            val db = FirebaseFirestore.getInstance("renu")
            val doc = db.collection("users").document(user.uid).get().await()
            
            val wakeTimeStr = doc.getString("wakeTime") ?: "07:00 AM"
            val sleepTimeStr = doc.getString("sleepTime") ?: "10:00 PM"

            if (isWithInActiveHours(wakeTimeStr, sleepTimeStr)) {
                Log.d("WaterWorker", "User is active. Sending notification.")
                val message = NotificationConstants.WATER_MESSAGES.random()
                notificationHelper.showNotification(
                    "Hydration Check 💧",
                    message,
                    "Water"
                )
            } else {
                Log.d("WaterWorker", "User is sleeping. No notification.")
            }
            
            return Result.success()

        } catch (e: Exception) {
            Log.e("WaterWorker", "Error in water worker", e)
            return Result.retry() 
        }
    }

    private fun isWithInActiveHours(wakeStr: String, sleepStr: String): Boolean {
        return try {
            val format = SimpleDateFormat("hh:mm a", Locale.US)
            val now = Calendar.getInstance()
            
            val wakeTime = Calendar.getInstance().apply {
                time = format.parse(wakeStr) ?: return false
                set(Calendar.YEAR, now.get(Calendar.YEAR))
                set(Calendar.MONTH, now.get(Calendar.MONTH))
                set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH))
            }
            
            val sleepTime = Calendar.getInstance().apply {
                time = format.parse(sleepStr) ?: return false
                set(Calendar.YEAR, now.get(Calendar.YEAR))
                set(Calendar.MONTH, now.get(Calendar.MONTH))
                set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH))
                
                // If sleep time is earlier than wake time (e.g. 1 AM), add 1 day
                if (before(wakeTime)) {
                    add(Calendar.DAY_OF_MONTH, 1)
                }
            }

            // Adjust 'now' if it's past midnight but before sleep time (for night owls)
            // e.g., Now is 1 AM, Wake 7 AM, Sleep 2 AM. 
            // We need to compare carefully. Simpler approach:
            
            val currentTimeVal = now.timeInMillis
            val wakeTimeVal = wakeTime.timeInMillis
            val sleepTimeVal = sleepTime.timeInMillis
            
            currentTimeVal in wakeTimeVal..sleepTimeVal
            
        } catch (e: Exception) {
            Log.e("WaterWorker", "Date parse error: ${e.message}")
            true // Default to true if parsing fails to avoid missing alarms
        }
    }
}
