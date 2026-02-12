package com.example.swasthyamitra.ui.hydration

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.swasthyamitra.databinding.ActivityHydrationBinding
import com.example.swasthyamitra.data.repository.HydrationRepository
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.example.swasthyamitra.notifications.WaterReminderManager
import com.example.swasthyamitra.utils.WaterGoalCalculator
import kotlinx.coroutines.launch
import java.util.Calendar

class HydrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHydrationBinding
    private lateinit var hydrationRepo: HydrationRepository
    private lateinit var authHelper: FirebaseAuthHelper
    private lateinit var adapter: WaterLogAdapter
    private var userId: String = ""
    private var dailyGoal: Int = 2500
    private var currentIntake: Int = 0
    private var selectedDate: Calendar = Calendar.getInstance()
    private val dateFormat = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US)
    private val displayFormat = java.text.SimpleDateFormat("MMMM dd, yyyy", java.util.Locale.US)
    private lateinit var reminderManager: WaterReminderManager
    private var userWeight: Double = 70.0
    private var userWakeTime: String = "07:00"
    private var userSleepTime: String = "23:00"
    private val prefs by lazy { getSharedPreferences("HydrationPrefs", MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHydrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hydrationRepo = HydrationRepository()
        authHelper = FirebaseAuthHelper(this)
        userId = intent.getStringExtra("USER_ID") ?: authHelper.getCurrentUser()?.uid ?: ""

        if (userId.isEmpty()) {
            Toast.makeText(this, "Error: User not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        reminderManager = WaterReminderManager(this)
        Toast.makeText(this, "User ID: $userId", Toast.LENGTH_LONG).show()
        
        // Handle quick log from notification
        val quickLogAmount = intent.getIntExtra("QUICK_LOG_AMOUNT", 0)
        if (quickLogAmount > 0) {
            addWater(quickLogAmount)
        }

        setupUI()
        loadData()
    }

    private fun setupUI() {
        binding.btnBack.setOnClickListener { finish() }

        binding.btnEditGoal.setOnClickListener { showEditGoalDialog() }
        
        // Goal info button
        binding.tvDailyGoal.setOnClickListener { showGoalInfoDialog() }
        
        binding.btnPrevDay.setOnClickListener {
            selectedDate.add(Calendar.DAY_OF_YEAR, -1)
            updateDateDisplayUI()
            loadData()
        }

        binding.btnNextDay.setOnClickListener {
            selectedDate.add(Calendar.DAY_OF_YEAR, 1)
            updateDateDisplayUI()
            loadData()
        }

        binding.btnAdd250.setOnClickListener { addWater(250) }
        binding.btnAdd500.setOnClickListener { addWater(500) }
        binding.btnAddCustom.setOnClickListener { showCustomAddDialog() }

        adapter = WaterLogAdapter(emptyList()) { log ->
            deleteWaterLog(log)
        }
        binding.rvWaterHistory.layoutManager = LinearLayoutManager(this)
        binding.rvWaterHistory.adapter = adapter

        updateDateDisplayUI()
        
        // Setup Reminder Switch
        binding.switchReminders.isChecked = prefs.getBoolean("reminders_enabled", false)
        binding.switchReminders.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                setupReminders(userWakeTime, userSleepTime)
                prefs.edit().putBoolean("reminders_enabled", true).apply()
            } else {
                cancelReminders()
                prefs.edit().putBoolean("reminders_enabled", false).apply()
            }
        }
    }

    private fun updateDateDisplayUI() {
        binding.tvDisplayDate.text = displayFormat.format(selectedDate.time)
        // Disable "Next Day" if it's today
        val today = Calendar.getInstance()
        binding.btnNextDay.isEnabled = selectedDate.before(today)
    }

    private fun loadData() {
        lifecycleScope.launch {
            // Load user weight for goal calculation
            hydrationRepo.getUserWeight(userId).onSuccess { weight ->
                userWeight = weight
            }
            
            // Load user goal with automatic calculation
            hydrationRepo.getWaterGoalWithCalculation(userId).onSuccess { goal ->
                dailyGoal = goal
                updateProgressUI()
            }

            val dateStr = dateFormat.format(selectedDate.time)

            // Load intake for selected date
            hydrationRepo.getWaterTotalForDate(userId, dateStr).onSuccess { total ->
                currentIntake = total
                updateProgressUI()
            }

            // Load history for selected date in parallel
            lifecycleScope.launch {
                hydrationRepo.getWaterLogs(userId, dateStr).onSuccess { logs ->
                    adapter.updateLogs(logs)
                    if (logs.isNotEmpty()) {
                        Toast.makeText(this@HydrationActivity, "Loaded ${logs.size} history logs", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@HydrationActivity, "No logs found for $dateStr", Toast.LENGTH_SHORT).show()
                    }
                }.onFailure { e ->
                    Toast.makeText(this@HydrationActivity, "History Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
            
            // Load user profile for wake/sleep times
            authHelper.getUserData(userId).onSuccess { data ->
                val wake = data["wakeTime"] as? String
                val sleep = data["sleepTime"] as? String
                if (!wake.isNullOrEmpty()) userWakeTime = wake
                if (!sleep.isNullOrEmpty()) userSleepTime = sleep
            }
        }
    }

    private fun addWater(amount: Int) {
        val targetDateStr = dateFormat.format(selectedDate.time)
        lifecycleScope.launch {
            hydrationRepo.addWaterLog(userId, amount, targetDateStr).onSuccess {
                // Refresh data for the current view
                currentIntake += amount
                updateProgressUI()
                loadHistory()
                
                Toast.makeText(this@HydrationActivity, "Added $amount ml", Toast.LENGTH_SHORT).show()
            }.onFailure {
                Toast.makeText(this@HydrationActivity, "Failed to add water", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadHistory() {
        val dateStr = dateFormat.format(selectedDate.time)
        lifecycleScope.launch {
            hydrationRepo.getWaterLogs(userId, dateStr).onSuccess { logs ->
                android.util.Log.d("HydrationActivity", "Updating logs: ${logs.size}")
                adapter.updateLogs(logs)
            }.onFailure {
                Toast.makeText(this@HydrationActivity, "Failed to refresh history", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteWaterLog(log: com.example.swasthyamitra.data.model.WaterLog) {
        lifecycleScope.launch {
            hydrationRepo.deleteWaterLog(log.logId).onSuccess {
                currentIntake -= log.amountML
                updateProgressUI()
                loadHistory()
                Toast.makeText(this@HydrationActivity, "Log deleted", Toast.LENGTH_SHORT).show()
            }.onFailure {
                Toast.makeText(this@HydrationActivity, "Failed to delete", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateProgressUI() {
        binding.tvCurrentIntake.text = "$currentIntake ml"
        binding.tvDailyGoal.text = "Goal: $dailyGoal ml"
        
        val progress = if (dailyGoal > 0) (currentIntake * 100 / dailyGoal) else 0
        binding.waterProgressBar.progress = progress.coerceAtMost(100)
        binding.tvPercentage.text = "$progress% of daily goal"
    }

    private fun showEditGoalDialog() {
        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Set Daily Goal (ml)")
        val input = android.widget.EditText(this)
        input.inputType = android.text.InputType.TYPE_CLASS_NUMBER
        input.setText(dailyGoal.toString())
        builder.setView(input)
        builder.setPositiveButton("Save") { _, _ ->
            val newGoal = input.text.toString().toIntOrNull() ?: 2500
            if (newGoal > 0) {
                lifecycleScope.launch {
                    hydrationRepo.setUserWaterGoal(userId, newGoal).onSuccess {
                        dailyGoal = newGoal
                        updateProgressUI()
                        Toast.makeText(this@HydrationActivity, "Goal updated!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
        builder.show()
    }

    private fun showCustomAddDialog() {
        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Custom Amount (ml)")
        val input = android.widget.EditText(this)
        input.inputType = android.text.InputType.TYPE_CLASS_NUMBER
        builder.setView(input)
        builder.setPositiveButton("Add") { _, _ ->
            val amount = input.text.toString().toIntOrNull() ?: 0
            if (amount > 0) addWater(amount)
        }
        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
        builder.show()
    }
    
    /**
     * Show goal calculation explanation dialog
     */
    private fun showGoalInfoDialog() {
        val explanation = WaterGoalCalculator.getGoalExplanation(userWeight, dailyGoal)
        val range = WaterGoalCalculator.getRecommendedIntakeRange(userWeight)
        
        val message = """
            $explanation
            
            Recommended Range: ${WaterGoalCalculator.formatWaterAmount(range.first)} - ${WaterGoalCalculator.formatWaterAmount(range.second)}
            
            Tap the edit icon to customize your goal.
        """.trimIndent()
        
        android.app.AlertDialog.Builder(this)
            .setTitle("💧 Your Water Goal")
            .setMessage(message)
            .setPositiveButton("Got it") { dialog, _ -> dialog.dismiss() }
            .setNeutralButton("Edit Goal") { _, _ -> showEditGoalDialog() }
            .show()
    }
    
    /**
     * Setup water reminders
     */
    fun setupReminders(wakeTime: String = "07:00", sleepTime: String = "23:00") {
        lifecycleScope.launch {
            try {
                // Save schedule to Firestore
                hydrationRepo.updateUserWaterSchedule(userId, wakeTime, sleepTime).onSuccess {
                    // Schedule reminders
                    reminderManager.scheduleReminders(wakeTime, sleepTime)
                    Toast.makeText(
                        this@HydrationActivity,
                        "Water reminders scheduled!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                Toast.makeText(
                    this@HydrationActivity,
                    "Failed to setup reminders",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    
    /**
     * Cancel all water reminders
     */
    fun cancelReminders() {
        reminderManager.cancelAllReminders()
        Toast.makeText(this, "Reminders cancelled", Toast.LENGTH_SHORT).show()
    }
}
