package com.example.swasthyamitra

import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.swasthyamitra.databinding.ActivitySleepTrackerBinding
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
    private var selectedSleepTime: Calendar? = null
    private var selectedWakeTime: Calendar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySleepTrackerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance("renu") // Use correct instance

        setupUI()
        loadSleepData()
    }

private fun setupUI() {
        // Back button
        binding.btnBack.setOnClickListener { finish() }

        // Sleep time picker
        binding.btnSelectSleepTime.setOnClickListener {
            showTimePicker(true)
        }

        // Wake time picker
        binding.btnSelectWakeTime.setOnClickListener {
            showTimePicker(false)
        }

        // Quality rating
        binding.chipPoor.setOnClickListener { selectQuality("poor") }
        binding.chipFair.setOnClickListener { selectQuality("fair") }
        binding.chipGood.setOnClickListener { selectQuality("good") }
        binding.chipExcellent.setOnClickListener { selectQuality("excellent") }

        // Log sleep button
        binding.btnLogSleep.setOnClickListener {
            logSleep()
        }

        // Quick log buttons
        binding.btnQuick7Hours.setOnClickListener { quickLogSleep(7.0) }
        binding.btnQuick8Hours.setOnClickListener { quickLogSleep(8.0) }
    }

    private fun showTimePicker(isSleepTime: Boolean) {
        val currentTime = Calendar.getInstance()
        val hour = currentTime.get(Calendar.HOUR_OF_DAY)
        val minute = currentTime.get(Calendar.MINUTE)

        TimePickerDialog(this, { _, selectedHour, selectedMinute ->
            val time = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, selectedHour)
                set(Calendar.MINUTE, selectedMinute)
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

            // If wake time is earlier than sleep time, add 24 hours
            if (durationMillis < 0) {
                durationMillis += 24 * 60 * 60 * 1000
            }

            val hours = durationMillis / (1000.0 * 60 * 60)
            binding.tvDuration.text = String.format("%.1f hours", hours)

            // Show quality based on duration
            showQualityRecommendation(hours)
        }
    }

    private fun showQualityRecommendation(hours: Double) {
        val recommendation = when {
            hours < 5 -> "⚠️ Too short - Poor quality likely"
            hours < 6 -> "😴 Fair - Could be better"
            hours in 7.0..9.0 -> "✅ Optimal - Good quality"
            hours > 9 -> "😴 Long - Check if oversleeping"
            else -> ""
        }
        binding.tvQualityHint.text = recommendation
    }

    private fun selectQuality(quality: String) {
        // Reset all chips
        binding.chipPoor.isChecked = false
        binding.chipFair.isChecked = false
        binding.chipGood.isChecked = false
        binding.chipExcellent.isChecked = false

        // Select the chosen one
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

    private fun quickLogSleep(hours: Double) {
        lifecycleScope.launch {
            try {
                val userId = auth.currentUser?.uid ?: return@launch
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
                    "sleepTime" to sleepTime,
                    "wakeTime" to currentTime,
                    "durationHours" to hours,
                    "quality" to quality,
                    "date" to getCurrentDate(),
                    "timestamp" to currentTime,
                    "source" to "manual_quick"
                )

                db.collection("users")
                    .document(userId)
                    .collection("sleep_logs")
                    .add(sleepLog)
                    .await()

                Toast.makeText(this@SleepTrackerActivity, "✅ $hours hours logged!", Toast.LENGTH_SHORT).show()
                loadSleepData()

            } catch (e: Exception) {
                Toast.makeText(this@SleepTrackerActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun logSleep() {
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
                val now = Calendar.getInstance()

                // Logic 1: Handle future times (assume past)
                if (selectedSleepTime!!.after(now)) {
                    selectedSleepTime!!.add(Calendar.DAY_OF_YEAR, -1)
                }
                if (selectedWakeTime!!.after(now)) {
                    selectedWakeTime!!.add(Calendar.DAY_OF_YEAR, -1)
                }

                // Logic 2: Handle overnight crossing (wake < sleep means next day relative to sleep)
                if (selectedWakeTime!!.before(selectedSleepTime!!)) {
                     selectedWakeTime!!.add(Calendar.DAY_OF_YEAR, 1)
                }

                val durationMillis = selectedWakeTime!!.timeInMillis - selectedSleepTime!!.timeInMillis
                val durationHours = durationMillis / (1000.0 * 60 * 60)

                if (durationHours > 24) {
                    Toast.makeText(this@SleepTrackerActivity, "Duration > 24 hours? Please check dates.", Toast.LENGTH_LONG).show()
                    return@launch
                }
                
                // Add robust logging for debugging
                val sleepTimeFormatted = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(selectedSleepTime!!.time)
                val wakeTimeFormatted = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(selectedWakeTime!!.time)
                android.util.Log.d("SleepTracker", "Logging Sleep: $sleepTimeFormatted to $wakeTimeFormatted ($durationHours hrs)")

                val sleepLog = hashMapOf(
                    "userId" to userId,
                    "sleepTime" to selectedSleepTime!!.timeInMillis,
                    "wakeTime" to selectedWakeTime!!.timeInMillis,
                    "durationHours" to durationHours,
                    "quality" to quality,
                    "date" to SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(selectedSleepTime!!.time), // Use sleep start date
                    "timestamp" to System.currentTimeMillis(),
                    "source" to "manual"
                )

                db.collection("users")
                    .document(userId)
                    .collection("sleep_logs")
                    .add(sleepLog)
                    .await()

                Toast.makeText(this@SleepTrackerActivity, "✅ Sleep logged successfully!", Toast.LENGTH_SHORT).show()

                // Reset form
                selectedSleepTime = null
                selectedWakeTime = null
                binding.tvSleepTime.text = "Select Time"
                binding.tvWakeTime.text = "Select Time"
                binding.tvDuration.text = "0 hours"
                binding.tvQualityHint.text = ""
                selectQuality("good")

                loadSleepData()

            } catch (e: Exception) {
                Toast.makeText(this@SleepTrackerActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                android.util.Log.e("SleepTracker", "Error logging sleep", e)
            }
        }
    }

    private fun loadSleepData() {
        lifecycleScope.launch {
            try {
                val userId = auth.currentUser?.uid ?: return@launch

                // Get last 7 days of sleep logs
                val sevenDaysAgo = Calendar.getInstance().apply {
                    add(Calendar.DAY_OF_YEAR, -7)
                }.timeInMillis

                val logs = db.collection("users")
                    .document(userId)
                    .collection("sleep_logs")
                    .whereGreaterThan("timestamp", sevenDaysAgo)
                    .orderBy("timestamp", Query.Direction.DESCENDING)
                    .get()
                    .await()

                val sleepNights = logs.documents.size
                val avgDuration = if (logs.documents.isNotEmpty()) {
                    logs.documents.mapNotNull {
                        it.getDouble("durationHours")
                    }.average()
                } else 0.0

                val goodNights = logs.documents.count {
                    val quality = it.getString("quality")
                    quality == "good" || quality == "excellent"
                }

                // Update UI
                binding.tvTotalNights.text = "$sleepNights"
                binding.tvAvgDuration.text = String.format("%.1fh", avgDuration)
                binding.tvGoodNights.text = "$goodNights/7"

                // Update progress
                val progress = (sleepNights.toFloat() / 7f * 100).toInt()
                binding.progressSleep.progress = progress
                binding.tvProgressText.text = "$sleepNights/7 nights tracked"

                // Check if stage is complete
                if (goodNights >= 7) {
                    binding.tvStageStatus.text = "🎉 Sleep Saint achieved!"
                    binding.tvStageStatus.setTextColor(getColor(android.R.color.holo_green_dark))
                }

            } catch (e: Exception) {
                Toast.makeText(this@SleepTrackerActivity, "Error loading data: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getCurrentDate(): String {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return format.format(Date())
    }
}

