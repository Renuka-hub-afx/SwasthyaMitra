package com.example.swasthyamitra.reminders

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ExerciseReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.d("ExerciseReminder", "Alarm triggered for exercise notification - DISABLED")
        /*
        val auth = FirebaseAuth.getInstance()
        val userId = auth.currentUser?.uid ?: return
        val firestore = FirebaseFirestore.getInstance("renu") // Using RENU database instance

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

                // 1. Check if user already exercised today (Smart Reminder)
                val logs = firestore.collection("exercise_logs")
                    .whereEqualTo("userId", userId)
                    .whereEqualTo("date", today)
                    .limit(1)
                    .get()
                    .await()

                if (!logs.isEmpty) {
                    Log.d("ExerciseReminder", "User already exercised today ($today). Skipping reminder.")
                    return@launch
                }

                val document = firestore.collection("users").document(userId).get().await()
                
                val lastDate = document.getString("lastExerciseDate")
                val exerciseMap = document.get("currentDailyExercise") as? Map<String, Any>
                val exerciseName = if (lastDate == today && exerciseMap != null) {
                    exerciseMap["name"] as? String
                } else {
                    null
                }

                val notificationHelper = ExerciseNotificationHelper(context)
                notificationHelper.showExerciseNotification(exerciseName)
                
            } catch (e: Exception) {
                Log.e("ExerciseReminder", "Error fetching daily exercise", e)
                // Fallback to generic notification
                val notificationHelper = ExerciseNotificationHelper(context)
                notificationHelper.showExerciseNotification()
            }
        }
        */
    }
}
