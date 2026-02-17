package com.example.swasthyamitra

import android.app.TimePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.swasthyamitra.databinding.ActivitySleepTrackerBinding
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.*

class SleepTrackerActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySleepTrackerBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var sleepPrefs: SharedPreferences
    private var selectedSleepTime: Calendar? = null
    private var selectedWakeTime: Calendar? = null
    private var hasLoggedToday = false

    companion object {
        private const val TAG = "SleepTracker"
        private const val PREFS_NAME = "sleep_tracker_prefs"
        private const val KEY_LAST_LOGGED_DATE = "last_logged_date"
        private const val KEY_SLEEP_STREAK = "sleep_streak"
        private const val KEY_STREAK_LAST_DATE = "streak_last_date"
        private const val KEY_STREAK_UNLOCKED = "streak_unlocked"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySleepTrackerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance("renu")
        sleepPrefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        setupUI()
        checkIfAlreadyLoggedToday()
        loadSleepData()
    }

    private fun setupUI() {
        binding.btnBack.setOnClickListener { finish() }

        binding.btnSelectSleepTime.setOnClickListener { showTimePicker(true) }
        binding.btnSelectWakeTime.setOnClickListener { showTimePicker(false) }

        binding.chipPoor.setOnClickListener { selectQuality("poor") }
        binding.chipFair.setOnClickListener { selectQuality("fair") }
        binding.chipGood.setOnClickListener { selectQuality("good") }
        binding.chipExcellent.setOnClickListener { selectQuality("excellent") }

        binding.btnLogSleep.setOnClickListener { logSleep() }
        binding.btnQuick7Hours.setOnClickListener { quickLogSleep(7.0) }
        binding.btnQuick8Hours.setOnClickListener { quickLogSleep(8.0) }
    }

    // ==================== ONCE-PER-DAY GUARD ====================

    /**
     * Check Firestore for today's existing sleep log AND sync with SharedPrefs.
     * This ensures the guard survives app restarts while staying accurate.
     */
    private fun checkIfAlreadyLoggedToday() {
        val userId = auth.currentUser?.uid ?: return
        val today = getCurrentDate()

        // Quick check from SharedPreferences first (instant)
        val lastLoggedDate = sleepPrefs.getString(KEY_LAST_LOGGED_DATE, "")
        if (lastLoggedDate == today) {
            hasLoggedToday = true
            disableLoggingUI()
        }

        // Also verify against Firestore (authoritative source)
        lifecycleScope.launch {
            try {
                val existing = db.collection("users")
                    .document(userId)
                    .collection("sleep_logs")
                    .whereEqualTo("date", today)
                    .limit(1)
                    .get()
                    .await()

                if (!existing.isEmpty) {
                    hasLoggedToday = true
                    sleepPrefs.edit().putString(KEY_LAST_LOGGED_DATE, today).apply()
                    runOnUiThread { disableLoggingUI() }
                } else {
                    // Firestore says no log today — reset local flag if stale
                    if (lastLoggedDate != today) {
                        hasLoggedToday = false
                        runOnUiThread { enableLoggingUI() }
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error checking today's log", e)
                // Rely on SharedPrefs fallback
            }
        }
    }

    private fun disableLoggingUI() {
        binding.btnLogSleep.isEnabled = false
        binding.btnLogSleep.alpha = 0.5f
        binding.btnLogSleep.text = "✅ Already Logged Today"
        binding.btnQuick7Hours.isEnabled = false
        binding.btnQuick7Hours.alpha = 0.5f
        binding.btnQuick8Hours.isEnabled = false
        binding.btnQuick8Hours.alpha = 0.5f
    }

    private fun enableLoggingUI() {
        binding.btnLogSleep.isEnabled = true
        binding.btnLogSleep.alpha = 1.0f
        binding.btnLogSleep.text = "LOG SLEEP 💤"
        binding.btnQuick7Hours.isEnabled = true
        binding.btnQuick7Hours.alpha = 1.0f
        binding.btnQuick8Hours.isEnabled = true
        binding.btnQuick8Hours.alpha = 1.0f
    }

    // ==================== STREAK LOGIC ====================

    /**
     * Update the sleep streak after a successful log.
     * Streak increments if today is exactly 1 day after the last streak date.
     * Streak resets to 1 if there's a gap > 1 day.
     * Streak unlocks at 7 consecutive days and stays visible.
     */
    private fun updateStreak() {
        val today = getCurrentDate()
        val lastStreakDate = sleepPrefs.getString(KEY_STREAK_LAST_DATE, "") ?: ""
        var currentStreak = sleepPrefs.getInt(KEY_SLEEP_STREAK, 0)

        if (lastStreakDate.isEmpty()) {
            // First ever log
            currentStreak = 1
        } else {
            val daysDiff = daysBetween(lastStreakDate, today)
            currentStreak = when {
                daysDiff == 0L -> currentStreak // Same day, don't increment again
                daysDiff == 1L -> currentStreak + 1 // Consecutive day
                else -> 1 // Gap — reset streak
            }
        }

        val streakUnlocked = currentStreak >= 7

        sleepPrefs.edit()
            .putInt(KEY_SLEEP_STREAK, currentStreak)
            .putString(KEY_STREAK_LAST_DATE, today)
            .putBoolean(KEY_STREAK_UNLOCKED, streakUnlocked || sleepPrefs.getBoolean(KEY_STREAK_UNLOCKED, false))
            .apply()

        Log.d(TAG, "Streak updated: $currentStreak days, unlocked=$streakUnlocked")
        updateStreakUI(currentStreak, streakUnlocked || sleepPrefs.getBoolean(KEY_STREAK_UNLOCKED, false))
    }

    private fun loadStreakUI() {
        val currentStreak = sleepPrefs.getInt(KEY_SLEEP_STREAK, 0)
        val lastStreakDate = sleepPrefs.getString(KEY_STREAK_LAST_DATE, "") ?: ""
        val today = getCurrentDate()

        // Check if streak is still valid (not broken by skipping yesterday)
        val validStreak = if (lastStreakDate.isNotEmpty()) {
            val daysDiff = daysBetween(lastStreakDate, today)
            when {
                daysDiff <= 1L -> currentStreak // Today or yesterday — still valid
                else -> {
                    // Streak broken, reset in prefs
                    sleepPrefs.edit().putInt(KEY_SLEEP_STREAK, 0).apply()
                    0
                }
            }
        } else {
            0
        }

        val streakUnlocked = sleepPrefs.getBoolean(KEY_STREAK_UNLOCKED, false)
        updateStreakUI(validStreak, streakUnlocked)
    }

    private fun updateStreakUI(streak: Int, unlocked: Boolean) {
        if (unlocked) {
            binding.tvStageStatus.text = "🔥 Sleep Streak: $streak days! 🏆 Streak Unlocked!"
            binding.tvStageStatus.setTextColor(getColor(android.R.color.holo_green_dark))
        } else if (streak > 0) {
            binding.tvStageStatus.text = "🔥 Sleep Streak: $streak/7 days — Keep going!"
            binding.tvStageStatus.setTextColor(getColor(R.color.purple_500))
        } else {
            binding.tvStageStatus.text = "🎯 Track 7 nights to become a Sleep Saint"
            binding.tvStageStatus.setTextColor(getColor(R.color.purple_500))
        }
    }

    /**
     * Calculate the number of days between two "yyyy-MM-dd" date strings.
     */
    private fun daysBetween(dateStr1: String, dateStr2: String): Long {
        return try {
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val d1 = sdf.parse(dateStr1)
            val d2 = sdf.parse(dateStr2)
            if (d1 != null && d2 != null) {
                val diffMs = d2.time - d1.time
                diffMs / (24 * 60 * 60 * 1000)
            } else 999L
        } catch (e: Exception) {
            999L // If parsing fails, treat as broken streak
        }
    }

    // ==================== TIME PICKERS ====================

    private fun showTimePicker(isSleepTime: Boolean) {
        val currentTime = Calendar.getInstance()
        val hour = currentTime.get(Calendar.HOUR_OF_DAY)
        val minute = currentTime.get(Calendar.MINUTE)

        TimePickerDialog(this, { _, selectedHour, selectedMinute ->
            val time = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, selectedHour)
                set(Calendar.MINUTE, selectedMinute)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }

            if (isSleepTime) {
                selectedSleepTime = time
                binding.tvSleepTime.text = formatTime(time)
            } else {
                selectedWakeTime = time
                binding.tvWakeTime.text = formatTime(time)
            }

            calculateDuration()
        }, hour, minute, false).show()
    }

    private fun formatTime(calendar: Calendar): String {
        val format = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return format.format(calendar.time)
    }

    private fun calculateDuration() {
        if (selectedSleepTime != null && selectedWakeTime != null) {
            var durationMillis = selectedWakeTime!!.timeInMillis - selectedSleepTime!!.timeInMillis

            // If wake time is earlier than sleep time, add 24 hours (overnight)
            if (durationMillis < 0) {
                durationMillis += 24 * 60 * 60 * 1000
            }

            val hours = durationMillis / (1000.0 * 60 * 60)
            binding.tvDuration.text = String.format("%.1f hours", hours)
            showQualityRecommendation(hours)
        }
    }

    private fun showQualityRecommendation(hours: Double) {
        val recommendation = when {
            hours < 5 -> "⚠️ Too short - Poor quality likely"
            hours < 6 -> "😴 Below average - Try to get more rest"
            hours < 7 -> "😐 Fair - Close to optimal"
            hours in 7.0..9.0 -> "✅ Optimal - Good quality"
            hours > 9 -> "😴 Long - Check if oversleeping"
            else -> ""
        }
        binding.tvQualityHint.text = recommendation
    }

    // ==================== QUALITY SELECTION ====================

    private fun selectQuality(quality: String) {
        binding.chipPoor.isChecked = false
        binding.chipFair.isChecked = false
        binding.chipGood.isChecked = false
        binding.chipExcellent.isChecked = false

        when (quality) {
            "poor" -> binding.chipPoor.isChecked = true
            "fair" -> binding.chipFair.isChecked = true
            "good" -> binding.chipGood.isChecked = true
            "excellent" -> binding.chipExcellent.isChecked = true
        }
    }

    private fun getSelectedQuality(): String? {
        return when {
            binding.chipPoor.isChecked -> "poor"
            binding.chipFair.isChecked -> "fair"
            binding.chipGood.isChecked -> "good"
            binding.chipExcellent.isChecked -> "excellent"
            else -> null
        }
    }

    // ==================== QUICK LOG ====================

    private fun quickLogSleep(hours: Double) {
        if (hasLoggedToday) {
            Toast.makeText(this, "You've already logged sleep today! Come back tomorrow 🌙", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch {
            try {
                val userId = auth.currentUser?.uid ?: return@launch
                val today = getCurrentDate()

                // Double-check Firestore before saving (race-condition guard)
                val existing = db.collection("users")
                    .document(userId)
                    .collection("sleep_logs")
                    .whereEqualTo("date", today)
                    .limit(1)
                    .get()
                    .await()

                if (!existing.isEmpty) {
                    hasLoggedToday = true
                    sleepPrefs.edit().putString(KEY_LAST_LOGGED_DATE, today).apply()
                    runOnUiThread {
                        disableLoggingUI()
                        Toast.makeText(this@SleepTrackerActivity, "Already logged today!", Toast.LENGTH_SHORT).show()
                    }
                    return@launch
                }

                val currentTime = System.currentTimeMillis()
                val sleepTime = currentTime - (hours * 60 * 60 * 1000).toLong()

                val quality = when {
                    hours < 6 -> "poor"
                    hours < 7 -> "fair"
                    hours in 7.0..9.0 -> "good"
                    else -> "fair"
                }

                val sleepLog = hashMapOf(
                    "userId" to userId,
                    "sleepTime" to SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date(sleepTime)),
                    "wakeTime" to SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date(currentTime)),
                    "sleepTimeMillis" to sleepTime,
                    "wakeTimeMillis" to currentTime,
                    "durationHours" to hours,
                    "durationMinutes" to (hours * 60),
                    "quality" to quality,
                    "date" to today,
                    "timestamp" to System.currentTimeMillis(),
                    "source" to "manual_quick"
                )

                db.collection("users")
                    .document(userId)
                    .collection("sleep_logs")
                    .add(sleepLog)
                    .await()

                // Mark as logged today
                hasLoggedToday = true
                sleepPrefs.edit().putString(KEY_LAST_LOGGED_DATE, today).apply()

                runOnUiThread {
                    Toast.makeText(this@SleepTrackerActivity, "✅ $hours hours logged!", Toast.LENGTH_SHORT).show()
                    disableLoggingUI()
                    updateStreak()
                    loadSleepData()
                }

            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(this@SleepTrackerActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // ==================== DETAILED LOG ====================

    private fun logSleep() {
        if (hasLoggedToday) {
            Toast.makeText(this, "You've already logged sleep today! Come back tomorrow 🌙", Toast.LENGTH_SHORT).show()
            return
        }

        if (selectedSleepTime == null || selectedWakeTime == null) {
            Toast.makeText(this, "Please select both sleep and wake times", Toast.LENGTH_SHORT).show()
            return
        }

        val quality = getSelectedQuality()
        if (quality == null) {
            Toast.makeText(this, "Please rate your sleep quality", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch {
            try {
                val userId = auth.currentUser?.uid ?: return@launch
                val today = getCurrentDate()

                // Double-check Firestore before saving
                val existing = db.collection("users")
                    .document(userId)
                    .collection("sleep_logs")
                    .whereEqualTo("date", today)
                    .limit(1)
                    .get()
                    .await()

                if (!existing.isEmpty) {
                    hasLoggedToday = true
                    sleepPrefs.edit().putString(KEY_LAST_LOGGED_DATE, today).apply()
                    runOnUiThread {
                        disableLoggingUI()
                        Toast.makeText(this@SleepTrackerActivity, "Already logged today!", Toast.LENGTH_SHORT).show()
                    }
                    return@launch
                }

                val now = Calendar.getInstance()

                // Clone calendars to avoid mutating the originals
                val sleepCal = selectedSleepTime!!.clone() as Calendar
                val wakeCal = selectedWakeTime!!.clone() as Calendar

                // Handle future times (assume past)
                if (sleepCal.after(now)) {
                    sleepCal.add(Calendar.DAY_OF_YEAR, -1)
                }
                if (wakeCal.after(now)) {
                    wakeCal.add(Calendar.DAY_OF_YEAR, -1)
                }

                // Handle overnight crossing (wake < sleep means next day)
                if (wakeCal.before(sleepCal)) {
                    wakeCal.add(Calendar.DAY_OF_YEAR, 1)
                }

                val durationMillis = wakeCal.timeInMillis - sleepCal.timeInMillis
                val durationHours = durationMillis / (1000.0 * 60 * 60)

                if (durationHours > 24) {
                    runOnUiThread {
                        Toast.makeText(this@SleepTrackerActivity, "Duration > 24 hours? Please check times.", Toast.LENGTH_LONG).show()
                    }
                    return@launch
                }

                Log.d(TAG, "Logging Sleep: ${SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(sleepCal.time)} to ${SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(wakeCal.time)} ($durationHours hrs)")

                val sleepLog = hashMapOf(
                    "userId" to userId,
                    "sleepTime" to formatTime(sleepCal),
                    "wakeTime" to formatTime(wakeCal),
                    "sleepTimeMillis" to sleepCal.timeInMillis,
                    "wakeTimeMillis" to wakeCal.timeInMillis,
                    "durationHours" to durationHours,
                    "durationMinutes" to (durationHours * 60),
                    "quality" to quality,
                    "date" to today,
                    "timestamp" to System.currentTimeMillis(),
                    "source" to "manual"
                )

                db.collection("users")
                    .document(userId)
                    .collection("sleep_logs")
                    .add(sleepLog)
                    .await()

                // Mark as logged today
                hasLoggedToday = true
                sleepPrefs.edit().putString(KEY_LAST_LOGGED_DATE, today).apply()

                runOnUiThread {
                    Toast.makeText(this@SleepTrackerActivity, "✅ Sleep logged successfully!", Toast.LENGTH_SHORT).show()

                    // Reset form
                    selectedSleepTime = null
                    selectedWakeTime = null
                    binding.tvSleepTime.text = "Select Time"
                    binding.tvWakeTime.text = "Select Time"
                    binding.tvDuration.text = "0 hours"
                    binding.tvQualityHint.text = ""
                    selectQuality("good")

                    disableLoggingUI()
                    updateStreak()
                    loadSleepData()
                }

            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(this@SleepTrackerActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
                Log.e(TAG, "Error logging sleep", e)
            }
        }
    }

    // ==================== LOAD DATA & STATS ====================

    private fun loadSleepData() {
        lifecycleScope.launch {
            try {
                val userId = auth.currentUser?.uid ?: return@launch

                // Build a proper Firestore Timestamp for 7 days ago
                val sevenDaysAgoCal = Calendar.getInstance().apply {
                    add(Calendar.DAY_OF_YEAR, -7)
                }
                val sevenDaysAgoTs = sevenDaysAgoCal.timeInMillis

                // Query using Long timestamp (we now save timestamp as Long)
                // Also handle legacy Firestore Timestamp objects by querying on "date" field
                val logs = db.collection("users")
                    .document(userId)
                    .collection("sleep_logs")
                    .orderBy("timestamp", Query.Direction.DESCENDING)
                    .limit(30) // Get recent logs
                    .get()
                    .await()

                // Filter to last 7 days client-side (handles both Long and Firestore Timestamp)
                val sevenDayDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val sevenDaysAgoDate = sevenDayDateFormat.format(sevenDaysAgoCal.time)

                val recentLogs = logs.documents.filter { doc ->
                    val date = doc.getString("date") ?: ""
                    date >= sevenDaysAgoDate
                }

                // Deduplicate by date (in case of legacy duplicates) — keep first/latest per day
                val uniqueDayLogs = recentLogs.groupBy { it.getString("date") ?: "" }
                    .mapValues { it.value.first() }
                    .values.toList()

                val sleepNights = uniqueDayLogs.size
                val avgDuration = if (uniqueDayLogs.isNotEmpty()) {
                    uniqueDayLogs.mapNotNull { it.getDouble("durationHours") }.average()
                } else 0.0

                val goodNights = uniqueDayLogs.count {
                    val quality = it.getString("quality")
                    quality == "good" || quality == "excellent"
                }

                runOnUiThread {
                    binding.tvTotalNights.text = "$sleepNights"
                    binding.tvAvgDuration.text = String.format("%.1fh", avgDuration)
                    binding.tvGoodNights.text = "$goodNights/7"

                    val progress = (sleepNights.toFloat() / 7f * 100).toInt().coerceAtMost(100)
                    binding.progressSleep.progress = progress
                    binding.tvProgressText.text = "$sleepNights/7 nights tracked"

                    if (goodNights >= 7) {
                        binding.tvStageStatus.text = "🎉 Sleep Saint achieved!"
                        binding.tvStageStatus.setTextColor(getColor(android.R.color.holo_green_dark))
                    }

                    // Load streak UI after data loads
                    loadStreakUI()
                }

            } catch (e: Exception) {
                Log.e(TAG, "Error loading sleep data", e)
                runOnUiThread {
                    Toast.makeText(this@SleepTrackerActivity, "Error loading data: ${e.message}", Toast.LENGTH_SHORT).show()
                    loadStreakUI() // Still show streak from SharedPrefs
                }
            }
        }
    }

    private fun getCurrentDate(): String {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return format.format(Date())
    }
}

