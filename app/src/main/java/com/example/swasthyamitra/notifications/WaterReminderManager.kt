package com.example.swasthyamitra.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import java.util.Calendar

/**
 * Manages water reminder alarms with smart scheduling based on user's wake/sleep times
 */
class WaterReminderManager(private val context: Context) {
    
    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    private val TAG = "WaterReminderManager"
    
    companion object {
        const val REMINDER_REQUEST_CODE_BASE = 2000
        const val DEFAULT_WAKE_HOUR = 7  // 7 AM
        const val DEFAULT_SLEEP_HOUR = 23 // 11 PM
    }
    
    /**
     * Schedule water reminders based on wake and sleep times
     * 
     * @param wakeTime Wake time in "HH:mm" format (e.g., "07:00")
     * @param sleepTime Sleep time in "HH:mm" format (e.g., "23:00")
     * @param intervalMinutes Interval between reminders in minutes (default: 120 = 2 hours)
     */
    fun scheduleReminders(
        wakeTime: String = "07:00",
        sleepTime: String = "23:00",
        intervalMinutes: Long = 120
    ) {
        try {
            // Cancel existing reminders first
            cancelAllReminders()
            
            // Parse wake and sleep times
            val (wakeHour, wakeMinute) = parseTime(wakeTime)
            val (sleepHour, sleepMinute) = parseTime(sleepTime)
            
            // Calculate number of reminders needed
            val activeHours = calculateActiveHours(wakeHour, sleepHour)
            val reminderCount = (activeHours * 60 / intervalMinutes).toInt().coerceIn(4, 12)
            
            Log.d(TAG, "Scheduling $reminderCount reminders between $wakeTime and $sleepTime")
            
            // Schedule each reminder
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, wakeHour)
            calendar.set(Calendar.MINUTE, wakeMinute)
            calendar.set(Calendar.SECOND, 0)
            
            // If the first reminder time has passed today, start from tomorrow
            if (calendar.timeInMillis <= System.currentTimeMillis()) {
                calendar.add(Calendar.DAY_OF_YEAR, 1)
            }
            
            for (i in 0 until reminderCount) {
                val requestCode = REMINDER_REQUEST_CODE_BASE + i
                scheduleAlarm(calendar.timeInMillis, requestCode, intervalMinutes)
                
                Log.d(TAG, "Scheduled reminder #$i at ${calendar.time}")
                
                // Move to next reminder time
                calendar.add(Calendar.MINUTE, intervalMinutes.toInt())
            }
            
            Log.d(TAG, "Successfully scheduled $reminderCount water reminders")
            
        } catch (e: Exception) {
            Log.e(TAG, "Error scheduling reminders", e)
        }
    }
    
    /**
     * Schedule a single alarm
     */
    private fun scheduleAlarm(triggerAtMillis: Long, requestCode: Int, intervalMillis: Long) {
        val intent = Intent(context, WaterReminderReceiver::class.java).apply {
            putExtra("REMINDER_ID", requestCode)
        }
        
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            requestCode,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        // Use setRepeating for regular intervals
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                triggerAtMillis,
                pendingIntent
            )
        } else {
            alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                triggerAtMillis,
                pendingIntent
            )
        }
    }
    
    /**
     * Cancel all water reminders
     */
    fun cancelAllReminders() {
        try {
            // Cancel up to 12 possible reminders
            for (i in 0 until 12) {
                val requestCode = REMINDER_REQUEST_CODE_BASE + i
                val intent = Intent(context, WaterReminderReceiver::class.java)
                val pendingIntent = PendingIntent.getBroadcast(
                    context,
                    requestCode,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
                alarmManager.cancel(pendingIntent)
                pendingIntent.cancel()
            }
            Log.d(TAG, "Cancelled all water reminders")
        } catch (e: Exception) {
            Log.e(TAG, "Error cancelling reminders", e)
        }
    }
    
    /**
     * Check if exact alarm permission is granted (Android 12+)
     */
    fun canScheduleExactAlarms(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            alarmManager.canScheduleExactAlarms()
        } else {
            true
        }
    }
    
    /**
     * Parse time string "HH:mm" to hour and minute
     */
    private fun parseTime(time: String): Pair<Int, Int> {
        return try {
            val parts = time.split(":")
            Pair(parts[0].toInt(), parts[1].toInt())
        } catch (e: Exception) {
            Log.e(TAG, "Error parsing time: $time", e)
            Pair(DEFAULT_WAKE_HOUR, 0)
        }
    }
    
    /**
     * Calculate active hours between wake and sleep time
     */
    private fun calculateActiveHours(wakeHour: Int, sleepHour: Int): Int {
        return if (sleepHour > wakeHour) {
            sleepHour - wakeHour
        } else {
            (24 - wakeHour) + sleepHour
        }
    }
    
    /**
     * Get next reminder time
     */
    fun getNextReminderTime(): Long? {
        // This would require storing scheduled times
        // For now, return null
        return null
    }
}
