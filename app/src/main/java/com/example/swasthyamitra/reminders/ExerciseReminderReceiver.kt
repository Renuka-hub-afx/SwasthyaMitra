package com.example.swasthyamitra.reminders

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class ExerciseReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.d("ExerciseReminder", "Alarm triggered for exercise notification")
        val notificationHelper = ExerciseNotificationHelper(context)
        notificationHelper.showExerciseNotification()
    }
}
