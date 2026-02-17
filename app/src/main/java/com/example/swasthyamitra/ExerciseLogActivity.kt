package com.example.swasthyamitra

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.example.swasthyamitra.models.ExerciseLog
import com.example.swasthyamitra.repository.ExerciseRepository
import com.example.swasthyamitra.databinding.ActivityExerciseLogBinding
import com.example.swasthyamitra.gamification.XPManager
import com.google.android.material.chip.ChipGroup
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ExerciseLogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExerciseLogBinding
    private lateinit var authHelper: FirebaseAuthHelper
    private lateinit var exerciseRepository: ExerciseRepository
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

        // Initialize exercise repository
        exerciseRepository = ExerciseRepository(this)
        lifecycleScope.launch {
            try {
                exerciseRepository.loadExerciseDatabase()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

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

        // FAB to add exercise
        binding.fabAddExercise.setOnClickListener {
            showAddExerciseOptions()
        }

        // Observe step count from StepCounterService
        com.example.swasthyamitra.services.StepCounterService.stepsLive.observe(this) { steps ->
            binding.tvStepCount.text = "$steps"
        }
    }
    
    private fun showAddExerciseOptions() {
        val options = arrayOf("🔍 Search Exercises", "🏋️ Workout Dashboard (AI & Videos)", "📝 Manual Entry")
        AlertDialog.Builder(this)
            .setTitle("Log Exercise")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> showExerciseSearchDialog()
                    1 -> startActivity(Intent(this, WorkoutDashboardActivity::class.java))
                    2 -> {
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

    // ==================== EXERCISE SEARCH DIALOG ====================
    
    private fun showExerciseSearchDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_exercise_search, null)
        val etSearch = dialogView.findViewById<TextInputEditText>(R.id.et_search_exercise)
        val rvResults = dialogView.findViewById<RecyclerView>(R.id.rv_exercise_results)
        val tvNoResults = dialogView.findViewById<TextView>(R.id.tv_no_results)
        val pbLoading = dialogView.findViewById<android.widget.ProgressBar>(R.id.pb_loading)
        val btnManualEntry = dialogView.findViewById<Button>(R.id.btn_manual_entry)
        val btnCancel = dialogView.findViewById<Button>(R.id.btn_cancel)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        // Setup RecyclerView for search results
        val searchResults = mutableListOf<ExerciseRepository.ExerciseItem>()
        val searchAdapter = ExerciseSearchAdapter(searchResults) { selectedExercise ->
            dialog.dismiss()
            showExerciseConfirmationDialog(selectedExercise)
        }
        rvResults.apply {
            layoutManager = LinearLayoutManager(this@ExerciseLogActivity)
            adapter = searchAdapter
        }

        // Debounced search
        var searchJob: Job? = null
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString().trim()
                searchJob?.cancel()

                if (query.length < 2) {
                    searchResults.clear()
                    searchAdapter.notifyDataSetChanged()
                    tvNoResults.visibility = View.VISIBLE
                    tvNoResults.text = "Type at least 2 characters to search"
                    rvResults.visibility = View.GONE
                    pbLoading.visibility = View.GONE
                    return
                }

                searchJob = lifecycleScope.launch {
                    delay(400) // Debounce 400ms

                    runOnUiThread {
                        pbLoading.visibility = View.VISIBLE
                        tvNoResults.visibility = View.GONE
                        rvResults.visibility = View.GONE
                    }

                    try {
                        val results = exerciseRepository.searchExercises(query)

                        runOnUiThread {
                            pbLoading.visibility = View.GONE
                            searchResults.clear()
                            searchResults.addAll(results.take(30)) // Limit to 30 results

                            if (searchResults.isEmpty()) {
                                tvNoResults.visibility = View.VISIBLE
                                tvNoResults.text = "No results for \"$query\". Try 'push up', 'yoga', or 'chest'."
                                rvResults.visibility = View.GONE
                            } else {
                                tvNoResults.visibility = View.GONE
                                rvResults.visibility = View.VISIBLE
                                searchAdapter.notifyDataSetChanged()
                            }
                        }
                    } catch (e: Exception) {
                        runOnUiThread {
                            pbLoading.visibility = View.GONE
                            tvNoResults.visibility = View.VISIBLE
                            tvNoResults.text = "Search error: ${e.message}"
                        }
                    }
                }
            }
        })

        btnManualEntry.setOnClickListener {
            dialog.dismiss()
            try {
                startActivity(Intent(this, Class.forName("com.example.swasthyamitra.ManualExerciseActivity")))
            } catch (e: ClassNotFoundException) {
                startActivity(Intent(this, WorkoutDashboardActivity::class.java))
            }
        }

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    // ==================== EXERCISE CONFIRMATION DIALOG ====================
    
    private fun showExerciseConfirmationDialog(exercise: ExerciseRepository.ExerciseItem) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_exercise_confirm, null)
        val etName = dialogView.findViewById<TextInputEditText>(R.id.et_exercise_name)
        val etDuration = dialogView.findViewById<TextInputEditText>(R.id.et_duration)
        val etCalories = dialogView.findViewById<TextInputEditText>(R.id.et_calories)
        val chipGroup = dialogView.findViewById<ChipGroup>(R.id.chip_group_intensity)
        val tvTargetInfo = dialogView.findViewById<TextView>(R.id.tv_target_info)
        val ivPreview = dialogView.findViewById<ImageView>(R.id.iv_exercise_preview)
        val flGifContainer = dialogView.findViewById<View>(R.id.fl_gif_container)
        val btnSave = dialogView.findViewById<Button>(R.id.btn_save_exercise)
        val btnCancel = dialogView.findViewById<Button>(R.id.btn_cancel_exercise)

        // Pre-fill data
        etName.setText(exercise.name)
        etDuration.setText("15")
        etCalories.setText(exercise.estimatedCalories.toString())
        tvTargetInfo.text = "Target: ${exercise.targetMuscle} | Body Part: ${exercise.bodyPart}"

        // Set intensity chip
        when (exercise.intensityLevel.lowercase()) {
            "light", "gentle" -> chipGroup.check(R.id.chip_light)
            "moderate" -> chipGroup.check(R.id.chip_moderate)
            "high" -> chipGroup.check(R.id.chip_high)
            else -> chipGroup.check(R.id.chip_moderate)
        }

        // Load GIF preview if available
        if (exercise.gifPath.isNotEmpty()) {
            flGifContainer.visibility = View.VISIBLE
            Glide.with(this)
                .load("file:///android_asset/${exercise.gifPath}")
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.ic_menu_gallery)
                .into(ivPreview)
        } else {
            flGifContainer.visibility = View.GONE
        }

        // Auto-recalculate calories when duration changes
        etDuration.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val duration = etDuration.text.toString().toIntOrNull() ?: 15
                val intensityMultiplier = when (chipGroup.checkedChipId) {
                    R.id.chip_light -> 4
                    R.id.chip_moderate -> 6
                    R.id.chip_high -> 8
                    else -> 6
                }
                etCalories.setText((duration * intensityMultiplier).toString())
            }
        }

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        btnSave.setOnClickListener {
            val duration = etDuration.text.toString().toIntOrNull()
            val calories = etCalories.text.toString().toIntOrNull()

            if (duration == null || duration <= 0) {
                Toast.makeText(this, "Please enter valid duration", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (calories == null || calories < 0) {
                Toast.makeText(this, "Please enter valid calories", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intensity = when (chipGroup.checkedChipId) {
                R.id.chip_light -> "Light"
                R.id.chip_moderate -> "Moderate"
                R.id.chip_high -> "High"
                else -> "Moderate"
            }

            dialog.dismiss()
            saveSearchedExercise(exercise, duration, calories, intensity)
        }

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    // ==================== SAVE SEARCHED EXERCISE ====================

    private fun saveSearchedExercise(
        exercise: ExerciseRepository.ExerciseItem,
        duration: Int,
        calories: Int,
        intensity: String
    ) {
        binding.progressBar.visibility = View.VISIBLE

        val userId = authHelper.getCurrentUser()?.uid ?: return
        val today = dateFormat.format(Date())
        val timestamp = System.currentTimeMillis()

        val exerciseLog = ExerciseLog(
            userId = userId,
            exerciseName = exercise.name,
            caloriesBurned = calories,
            duration = duration,
            timestamp = timestamp,
            date = today,
            intensity = intensity,
            source = "Search",
            notes = "",
            targetMuscle = exercise.targetMuscle,
            bodyPart = exercise.bodyPart
        )

        lifecycleScope.launch {
            try {
                val result = authHelper.logExercise(exerciseLog)
                result.onSuccess {
                    // Also update RTDB for gamification stats (same pattern as Manual/AI)
                    updateRTDBStats(userId, exercise.name, duration, calories, today)

                    runOnUiThread {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(
                            this@ExerciseLogActivity,
                            "✅ ${exercise.name} logged! +100 XP & $calories kcal",
                            Toast.LENGTH_SHORT
                        ).show()
                        loadAllExerciseLogs()
                    }
                }
                result.onFailure { e ->
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

    private fun updateRTDBStats(userId: String, exerciseName: String, duration: Int, calories: Int, today: String) {
        val db = FirebaseDatabase.getInstance("https://swasthyamitra-ded44-default-rtdb.asia-southeast1.firebasedatabase.app").reference
        val userRef = db.child("users").child(userId)

        userRef.get().addOnSuccessListener { snapshot ->
            val data = snapshot.getValue(FitnessData::class.java) ?: FitnessData()

            val sessionId = UUID.randomUUID().toString()
            val session = WorkoutSession(
                id = sessionId,
                date = today,
                category = "Search",
                videoId = "search_${System.currentTimeMillis()}",
                duration = duration,
                completed = true,
                timestamp = System.currentTimeMillis(),
                caloriesBurned = calories
            )

            val updatedHistory = data.workoutHistory.toMutableMap()
            updatedHistory[sessionId] = session
            val updatedCompletion = data.completionHistory.toMutableMap()
            updatedCompletion[today] = true

            val updatedData = data.copy(
                xp = data.xp + 100,
                completionHistory = updatedCompletion,
                workoutHistory = updatedHistory,
                totalWorkoutMinutes = data.totalWorkoutMinutes + duration,
                lastActiveDate = today
            )

            userRef.setValue(updatedData)
                .addOnSuccessListener {
                    // Award XP
                    val xpManager = XPManager(userId)
                    xpManager.awardXP(XPManager.XPSource.COMPLETE_WORKOUT) { leveledUp, newLevel ->
                        if (leveledUp) {
                            runOnUiThread {
                                Toast.makeText(this, "🎉 Level Up! Level $newLevel", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
                .addOnFailureListener { e ->
                    android.util.Log.e("ExerciseLogActivity", "RTDB save failed: ${e.message}")
                }
        }
    }

    // ==================== LOAD & DISPLAY ====================

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
    }

    private fun showExerciseDetailsDialog(log: ExerciseLog) {
        val time = if (log.timestamp > 0L) timeFormat.format(Date(log.timestamp)) else "N/A"
        val sourceInfo = if (log.source.isNotEmpty()) "\n📋 Source: ${log.source}" else ""
        val muscleInfo = if (log.targetMuscle.isNotEmpty()) "\n💪 Target: ${log.targetMuscle}" else ""
        val intensityInfo = if (log.intensity.isNotEmpty()) "\n⚡ Intensity: ${log.intensity}" else ""
        
        val message = """
            🕐 Time: $time
            ⏱️ Duration: ${log.duration} mins
            🔥 Calories: ${log.caloriesBurned} kcal$sourceInfo$muscleInfo$intensityInfo
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

// ==================== EXERCISE LOG ADAPTER (list display) ====================

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

        // Handle timestamp=0 gracefully (corrupted data from old Firestore Timestamp bug)
        if (log.timestamp > 0L) {
            val timestamp = Date(log.timestamp)
            val today = todayFormat.format(Date())
            val logDate = todayFormat.format(timestamp)

            holder.tvTime.text = if (logDate == today) {
                timeFormat.format(timestamp)
            } else {
                "${dateFormat.format(timestamp)} • ${timeFormat.format(timestamp)}"
            }
        } else {
            // Fallback: use date string if available
            holder.tvTime.text = if (log.date.isNotEmpty()) log.date else "Unknown time"
        }

        holder.itemView.setOnClickListener {
            onItemClick(log)
        }
    }

    override fun getItemCount() = logs.size
}

// ==================== EXERCISE SEARCH ADAPTER ====================

class ExerciseSearchAdapter(
    private val exercises: List<ExerciseRepository.ExerciseItem>,
    private val onItemClick: (ExerciseRepository.ExerciseItem) -> Unit
) : RecyclerView.Adapter<ExerciseSearchAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivThumb: ImageView = view.findViewById(R.id.iv_exercise_thumb)
        val tvName: TextView = view.findViewById(R.id.tv_exercise_name)
        val tvDetails: TextView = view.findViewById(R.id.tv_exercise_details)
        val tvCalories: TextView = view.findViewById(R.id.tv_exercise_calories)
        val tvSource: TextView = view.findViewById(R.id.tv_exercise_source)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_exercise_search_result, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val exercise = exercises[position]

        holder.tvName.text = exercise.name
        holder.tvDetails.text = buildString {
            if (exercise.targetMuscle.isNotEmpty()) append(exercise.targetMuscle)
            if (exercise.equipment.isNotEmpty()) {
                if (isNotEmpty()) append(" • ")
                append(exercise.equipment)
            }
        }.ifEmpty { exercise.bodyPart }
        
        holder.tvCalories.text = "🔥 ${exercise.estimatedCalories} kcal"
        holder.tvSource.text = when (exercise.source) {
            "gym" -> "Gym"
            "yoga" -> "Yoga"
            "combined" -> "Featured"
            "csv" -> "Cardio"
            else -> exercise.source
        }

        // Load thumbnail if available
        if (exercise.gifPath.isNotEmpty()) {
            Glide.with(holder.itemView.context)
                .load("file:///android_asset/${exercise.gifPath}")
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.ic_menu_gallery)
                .centerCrop()
                .into(holder.ivThumb)
        } else {
            holder.ivThumb.setImageResource(android.R.drawable.ic_menu_gallery)
        }

        holder.itemView.setOnClickListener {
            onItemClick(exercise)
        }
    }

    override fun getItemCount() = exercises.size
}
