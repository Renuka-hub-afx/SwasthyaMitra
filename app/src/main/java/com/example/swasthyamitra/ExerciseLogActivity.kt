package com.example.swasthyamitra

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.example.swasthyamitra.models.ExerciseLog
import com.example.swasthyamitra.databinding.ActivityExerciseLogBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ExerciseLogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExerciseLogBinding
    private lateinit var authHelper: FirebaseAuthHelper
    private lateinit var exerciseAdapter: ExerciseLogAdapter
    private val exerciseLogs = mutableListOf<ExerciseLog>()
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
    private var selectedDate = Date()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseLogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val application = application as? UserApplication
        if (application == null) {
            Toast.makeText(this, "App initialization error. Restarting...", Toast.LENGTH_LONG).show()
            finish()
            return
        }
        authHelper = application.authHelper

        setupUI()
        loadAllExerciseLogs()
    }

    private fun setupUI() {
        // Back button
        binding.btnBack.setOnClickListener {
            finish()
        }

        // Date display
        binding.tvDate.text = SimpleDateFormat("EEEE, MMMM dd, yyyy", Locale.getDefault()).format(selectedDate)

        // RecyclerView setup
        exerciseAdapter = ExerciseLogAdapter(exerciseLogs) { log ->
            showExerciseDetailsDialog(log)
        }
        binding.rvExerciseLogs.apply {
            layoutManager = LinearLayoutManager(this@ExerciseLogActivity)
            adapter = exerciseAdapter
        }

        // FAB to add exercise - Navigates to Workout Dashboard for now as the hub
        binding.fabAddExercise.setOnClickListener {
            // Option to go to Dashboard or Manual Entry directly?
            // Let's show a dialog or just go to Dashboard which has both manual and AI
            showAddExerciseOptions()
        }
    }
    
    private fun showAddExerciseOptions() {
        val options = arrayOf("🏋️ Workout Dashboard (AI & Videos)", "📝 Manual Entry")
        AlertDialog.Builder(this)
            .setTitle("Log Exercise")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> {
                        startActivity(Intent(this, WorkoutDashboardActivity::class.java))
                    }
                    1 -> {
                        // Assuming ManualExerciseActivity exists or we can create a simple dialog here?
                        // For now, let's point to ManualExerciseActivity if it exists, otherwise Dashboard
                        try {
                            startActivity(Intent(this, Class.forName("com.example.swasthyamitra.ManualExerciseActivity")))
                        } catch (e: ClassNotFoundException) {
                            startActivity(Intent(this, WorkoutDashboardActivity::class.java))
                        }
                    }
                }
            }
            .show()
    }

    private fun loadAllExerciseLogs() {
        binding.progressBar.visibility = View.VISIBLE
        binding.tvEmptyState.visibility = View.GONE

        val userId = authHelper.getCurrentUser()?.uid ?: return

        lifecycleScope.launch {
            try {
                val result = authHelper.getAllExerciseLogs(userId)
                result.onSuccess { logs ->
                    exerciseLogs.clear()
                    exerciseLogs.addAll(logs)

                    runOnUiThread {
                        binding.progressBar.visibility = View.GONE

                        if (exerciseLogs.isEmpty()) {
                            binding.tvEmptyState.visibility = View.VISIBLE
                            binding.rvExerciseLogs.visibility = View.GONE
                        } else {
                            binding.tvEmptyState.visibility = View.GONE
                            binding.rvExerciseLogs.visibility = View.VISIBLE
                            exerciseAdapter.notifyDataSetChanged()
                            updateSummary()
                        }
                    }
                }
                result.onFailure { e ->
                    runOnUiThread {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this@ExerciseLogActivity, "Error loading logs: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                runOnUiThread {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this@ExerciseLogActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun updateSummary() {
        var totalCalories = 0
        var totalDuration = 0
        
        // Only sum today's logs for the summary
        val today = dateFormat.format(Date())
        exerciseLogs.filter { it.date == today }.forEach { log ->
            totalCalories += log.caloriesBurned
            totalDuration += log.duration
        }

        binding.tvTotalCaloriesBurned.text = "$totalCalories"
        binding.tvActiveMinutes.text = "${totalDuration}m"
        
        // Update steps from StepManager? 
        // For now, we can't easily get steps here without StepManager instance or passing it.
        // We'll leave steps as 0 or try to fetch if we had a repository for it.
        // Let's just hide the steps or keep it 0 as "Steps" might be tracked separately.
        // Actually, let's fetch step count if possible, or just remove that part from calculation if complex.
        // We'll leave it as is for now.
    }

    private fun showExerciseDetailsDialog(log: ExerciseLog) {
        val time = timeFormat.format(Date(log.timestamp))
        val message = """
            🕐 Time: $time
            ⏱️ Duration: ${log.duration} mins
            🔥 Calories: ${log.caloriesBurned} kcal
        """.trimIndent()

        AlertDialog.Builder(this)
            .setTitle(log.exerciseName)
            .setMessage(message)
            .setPositiveButton("OK", null)
            .setNegativeButton("Delete") { _, _ ->
                deleteExerciseLog(log)
            }
            .show()
    }

    private fun deleteExerciseLog(log: ExerciseLog) {
        AlertDialog.Builder(this)
            .setTitle("Delete Entry")
            .setMessage("Are you sure you want to delete this workout?")
            .setPositiveButton("Delete") { _, _ ->
                binding.progressBar.visibility = View.VISIBLE
                lifecycleScope.launch {
                    try {
                        val result = authHelper.deleteExerciseLog(log.userId, log.logId)
                        result.onSuccess {
                            runOnUiThread {
                                Toast.makeText(this@ExerciseLogActivity, "Deleted successfully", Toast.LENGTH_SHORT).show()
                                loadAllExerciseLogs()
                            }
                        }.onFailure { e ->
                            runOnUiThread {
                                binding.progressBar.visibility = View.GONE
                                Toast.makeText(this@ExerciseLogActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } catch (e: Exception) {
                        runOnUiThread {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this@ExerciseLogActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
    
    override fun onResume() {
        super.onResume()
        loadAllExerciseLogs()
    }
}

class ExerciseLogAdapter(
    private val logs: List<ExerciseLog>,
    private val onItemClick: (ExerciseLog) -> Unit
) : RecyclerView.Adapter<ExerciseLogAdapter.ViewHolder>() {

    private val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
    private val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
    private val todayFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tv_exercise_name)
        val tvDuration: TextView = view.findViewById(R.id.tv_duration)
        val tvTime: TextView = view.findViewById(R.id.tv_time)
        val tvCalories: TextView = view.findViewById(R.id.tv_calories)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_exercise_log, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val log = logs[position]

        holder.tvName.text = log.exerciseName
        holder.tvDuration.text = "${log.duration} mins"
        holder.tvCalories.text = log.caloriesBurned.toString()

        val timestamp = Date(log.timestamp)
        val today = todayFormat.format(Date())
        val logDate = todayFormat.format(timestamp)

        holder.tvTime.text = if (logDate == today) {
            timeFormat.format(timestamp)
        } else {
            "${dateFormat.format(timestamp)} • ${timeFormat.format(timestamp)}"
        }

        holder.itemView.setOnClickListener {
            onItemClick(log)
        }
    }

    override fun getItemCount() = logs.size
}
