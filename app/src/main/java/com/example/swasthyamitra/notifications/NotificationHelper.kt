package com.example.swasthyamitra.notifications

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.swasthyamitra.MainActivity
import com.example.swasthyamitra.R

class NotificationHelper(private val context: Context) {

    companion object {
        const val CHANNEL_ID_WATER = "channel_water_reminders"
        const val CHANNEL_ID_MEALS = "channel_meal_reminders"
        const val CHANNEL_ID_EVENTS = "channel_events"
        
        const val NOTIFICATION_ID_WATER = 1001
        const val NOTIFICATION_ID_MEAL = 1002
        const val NOTIFICATION_ID_EVENT = 1003
    }

    init {
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val waterChannel = NotificationChannel(
                CHANNEL_ID_WATER,
                "Hydration Reminders",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Reminds you to drink water regularly"
            }

            val mealChannel = NotificationChannel(
                CHANNEL_ID_MEALS,
                "Meal Reminders",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Reminds you to eat breakfast, lunch, and dinner"
            }
            
            val eventChannel = NotificationChannel(
                CHANNEL_ID_EVENTS,
                "Events & Wishes",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Birthday wishes and festival greetings"
            }

            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(waterChannel)
            manager.createNotificationChannel(mealChannel)
            manager.createNotificationChannel(eventChannel)
        }
    }

    fun showNotification(title: String, message: String, type: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                // Permission not granted, cannot show notification
                return
            }
        }

        val channelId = when (type) {
            "Water" -> CHANNEL_ID_WATER
            "Meal" -> CHANNEL_ID_MEALS
            "Event" -> CHANNEL_ID_EVENTS
            else -> CHANNEL_ID_WATER
        }
        
        val notificationId = when (type) {
            "Water" -> NOTIFICATION_ID_WATER
            "Meal" -> NOTIFICATION_ID_MEAL
            "Event" -> NOTIFICATION_ID_EVENT
            else -> System.currentTimeMillis().toInt()
        }

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground) // Make sure this icon exists or use a generic one
            .setContentTitle(title)
            .setContentText(message)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        try {
            NotificationManagerCompat.from(context).notify(notificationId, builder.build())
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }
}
