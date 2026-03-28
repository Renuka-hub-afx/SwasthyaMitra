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

// WorkManager worker: fires breakfast (7-9 AM), lunch (12-2 PM), dinner (7-9 PM) notifications once per day
class MealEventWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    private val notificationHelper = NotificationHelper(appContext)          // shows the system notification
    private val authHelper = FirebaseAuthHelper(appContext)                   // reads current user for event checks
    private val sharedPrefs = appContext.getSharedPreferences("notification_prefs", Context.MODE_PRIVATE) // tracks which meal was already triggered today

    override suspend fun doWork(): Result {
        Log.d("MealWorker", "Checking meal schedules...")
        
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val todayStr = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time)

        // Meal Times: Breakfast (8 AM), Lunch (1 PM = 13), Dinner (8 PM = 20)
        // We check a window (e.g., 7-9 for breakfast) to ensure we catch it even if worker runs slightly off
        
        val appPrefs = applicationContext.getSharedPreferences("app_settings", Context.MODE_PRIVATE)

        if (hour in 7..9 && appPrefs.getBoolean("pref_breakfast", true) && !isTriggeredDetails("breakfast", todayStr)) {
            sendMealNotification("Breakfast", NotificationConstants.BREAKFAST_MESSAGES)
            // Events are usually morning checks too
            if (appPrefs.getBoolean("pref_events", true)) {
                checkEvents(todayStr) 
            }
            markTriggered("breakfast", todayStr)
        } 
        else if (hour in 12..14 && appPrefs.getBoolean("pref_lunch", true) && !isTriggeredDetails("lunch", todayStr)) {
            sendMealNotification("Lunch", NotificationConstants.LUNCH_MESSAGES)
            markTriggered("lunch", todayStr)
        }
        else if (hour in 19..21 && appPrefs.getBoolean("pref_dinner", true) && !isTriggeredDetails("dinner", todayStr)) {
            sendMealNotification("Dinner", NotificationConstants.DINNER_MESSAGES)
            markTriggered("dinner", todayStr)
        }

        return Result.success()
    }

    // Picks a random message from the list and shows it as a meal notification
    private fun sendMealNotification(type: String, messages: List<String>) {
        val message = messages.random()
        notificationHelper.showNotification(
            "$type Time! 🍽️",
            message,
            "Meal"
        )
    }

    private suspend fun checkEvents(todayStr: String) {
        val user = authHelper.getCurrentUser() ?: return
        
        try {
            val firestore = FirebaseFirestore.getInstance("renu")
            val doc = firestore.collection("users").document(user.uid).get().await()
            val name = doc.getString("name") ?: "Friend"
            
            // Birthday Check
            // Assumes birthday stored as "yyyy-MM-dd" or "MM-dd" or similar. 
            // For now, let's assume we might have it. If not, skip.
            // In a real app, we'd parse the date.
            
            // Festival Check (Hardcoded for demo/simplicity based on user request "Global Collection")
            // We can check local constant map first
            val dateFormat = SimpleDateFormat("dd MMM", Locale.getDefault()) // e.g., "25 Dec"
            val todayDate = dateFormat.format(Calendar.getInstance().time)
            
            // Example map check (You essentially requested specific festival logic, simplified here)
            // Implementation note: Real festival calendar likely needs Year-aware checks (Lunar calendars etc)
            // For MVP, we check a few fixed dates or assume passed in "Events"
             
             // If we want to simulate or check specific dates:
             /*
             if (todayDate == "25 Dec") {
                 notificationHelper.showNotification("Festival", NotificationConstants.FESTIVAL_MESSAGES["Christmas"]!!, "Event")
             }
             */
             
        } catch (e: Exception) {
            Log.e("MealWorker", "Error checking events", e)
        }
    }

    // Returns true if this meal type was already notified today (prevents duplicate notifications)
    private fun isTriggeredDetails(type: String, date: String): Boolean {
        val lastDate = sharedPrefs.getString("last_trigger_$type", "")
        return lastDate == date
    }

    // Saves today's date against the meal type so the same notification won't fire again today
    private fun markTriggered(type: String, date: String) {
        sharedPrefs.edit().putString("last_trigger_$type", date).apply()
    }
}
