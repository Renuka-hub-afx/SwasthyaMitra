package com.example.swasthyamitra.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.swasthyamitra.R
import com.example.swasthyamitra.ui.hydration.HydrationActivity

/**
 * Broadcast receiver for water reminder notifications
 */
class WaterReminderReceiver : BroadcastReceiver() {
    
    companion object {
        const val CHANNEL_ID = "water_reminder_channel"
        const val CHANNEL_NAME = "Water Reminders"
        const val NOTIFICATION_ID_BASE = 3000
        
        // Notification action keys
        const val ACTION_LOG_WATER = "com.example.swasthyamitra.LOG_WATER"
        const val ACTION_DISMISS = "com.example.swasthyamitra.DISMISS_REMINDER"
        const val EXTRA_AMOUNT_ML = "amount_ml"
        
        // Reminder messages
        private val REMINDER_TITLES = arrayOf(
            "💧 Time to Hydrate!",
            "🌊 Water Break",
            "💦 Stay Hydrated",
            "🥤 Drink Water",
            "💧 Hydration Reminder"
        )
        
        private val REMINDER_MESSAGES = arrayOf(
            "Your body needs water! Take a moment to hydrate.",
            "Staying hydrated keeps you healthy and energized.",
            "A glass of water can boost your focus and mood.",
            "Don't forget to drink water regularly throughout the day.",
            "Keep your body happy with regular hydration!",
            "Water is essential for your health. Drink up!",
            "Time for a refreshing glass of water!"
        )
    }
    
    override fun onReceive(context: Context, intent: Intent?) {
        Log.d("WaterReminderReceiver", "Received water reminder broadcast")
        
        when (intent?.action) {
            ACTION_LOG_WATER -> {
                handleLogWater(context, intent)
            }
            ACTION_DISMISS -> {
                handleDismiss(context, intent)
            }
            else -> {
                showWaterReminderNotification(context, intent)
            }
        }
    }
    
    /**
     * Show water reminder notification
     */
    private fun showWaterReminderNotification(context: Context, intent: Intent?) {
        createNotificationChannel(context)
        
        val reminderId = intent?.getIntExtra("REMINDER_ID", 0) ?: 0
        val notificationId = NOTIFICATION_ID_BASE + reminderId
        
        // Intent to open HydrationActivity
        val openIntent = Intent(context, HydrationActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val openPendingIntent = PendingIntent.getActivity(
            context,
            notificationId,
            openIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        // Action: Log 250ml
        val log250Intent = Intent(context, WaterReminderReceiver::class.java).apply {
            action = ACTION_LOG_WATER
            putExtra(EXTRA_AMOUNT_ML, 250)
            putExtra("NOTIFICATION_ID", notificationId)
        }
        val log250PendingIntent = PendingIntent.getBroadcast(
            context,
            notificationId * 10 + 1,
            log250Intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        // Action: Log 500ml
        val log500Intent = Intent(context, WaterReminderReceiver::class.java).apply {
            action = ACTION_LOG_WATER
            putExtra(EXTRA_AMOUNT_ML, 500)
            putExtra("NOTIFICATION_ID", notificationId)
        }
        val log500PendingIntent = PendingIntent.getBroadcast(
            context,
            notificationId * 10 + 2,
            log500Intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        // Get random title and message
        val title = REMINDER_TITLES.random()
        val message = REMINDER_MESSAGES.random()
        
        // Build notification
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_water_drop)
            .setContentTitle(title)
            .setContentText(message)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_REMINDER)
            .setAutoCancel(true)
            .setContentIntent(openPendingIntent)
            .addAction(R.drawable.ic_water_drop, "250ml", log250PendingIntent)
            .addAction(R.drawable.ic_water_drop, "500ml", log500PendingIntent)
            .build()
        
        // Show notification
        try {
            NotificationManagerCompat.from(context).notify(notificationId, notification)
            Log.d("WaterReminderReceiver", "Notification shown with ID: $notificationId")
        } catch (e: SecurityException) {
            Log.e("WaterReminderReceiver", "Permission denied for notification", e)
        }
    }
    
    /**
     * Handle quick log water action from notification
     */
    private fun handleLogWater(context: Context, intent: Intent) {
        val amountMl = intent.getIntExtra(EXTRA_AMOUNT_ML, 250)
        val notificationId = intent.getIntExtra("NOTIFICATION_ID", NOTIFICATION_ID_BASE)
        
        Log.d("WaterReminderReceiver", "Logging $amountMl ml from notification")
        
        // Dismiss the notification
        NotificationManagerCompat.from(context).cancel(notificationId)
        
        // Open HydrationActivity with the amount to log
        val openIntent = Intent(context, HydrationActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra("QUICK_LOG_AMOUNT", amountMl)
        }
        context.startActivity(openIntent)
    }
    
    /**
     * Handle dismiss action
     */
    private fun handleDismiss(context: Context, intent: Intent) {
        val notificationId = intent.getIntExtra("NOTIFICATION_ID", NOTIFICATION_ID_BASE)
        NotificationManagerCompat.from(context).cancel(notificationId)
    }
    
    /**
     * Create notification channel for water reminders
     */
    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Reminders to drink water throughout the day"
                enableVibration(true)
                enableLights(true)
            }
            
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
