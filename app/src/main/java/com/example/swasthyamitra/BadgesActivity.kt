package com.example.swasthyamitra

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.example.swasthyamitra.data.repository.HydrationRepository
import com.example.swasthyamitra.models.WorkoutVideo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Stage(
    val id: Int,
    val title: String,
    val description: String,
    val iconRes: Int,
    val status: StageStatus
)

enum class StageStatus {
    LOCKED, ACTIVE, COMPLETED
}

class BadgesActivity : AppCompatActivity() {

    private lateinit var stagesRecyclerView: RecyclerView
    private lateinit var stageAdapter: StageAdapter
    private val stages = mutableListOf<Stage>()
    
    // Repositories
    private lateinit var authHelper: FirebaseAuthHelper
    private lateinit var hydrationRepo: HydrationRepository
    
    // UI Elements
    private lateinit var tvStageProgress: TextView
    private lateinit var journeyProgressBar: ProgressBar
    private lateinit var tvCurrentSteps: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_badges)

        authHelper = FirebaseAuthHelper(this)
        hydrationRepo = HydrationRepository()

        initViews()
        setupRecyclerView()
        loadRealStageData()
    }

    private fun initViews() {
        stagesRecyclerView = findViewById(R.id.stagesRecyclerView)
        tvStageProgress = findViewById(R.id.tvStageProgress)
        journeyProgressBar = findViewById(R.id.journeyProgressBar)
        tvCurrentSteps = findViewById(R.id.tvCurrentSteps)

        findViewById<View>(R.id.btnBack).setOnClickListener {
            finish()
        }
        
        findViewById<View>(R.id.btnQuickAdd).setOnClickListener {
             Toast.makeText(this, "Quick Add Feature coming soon!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupRecyclerView() {
        stageAdapter = StageAdapter(stages) { stage ->
            val statusMsg = when(stage.status) {
                StageStatus.LOCKED -> "Locked 🔒"
                StageStatus.ACTIVE -> "In Progress 🏃"
                StageStatus.COMPLETED -> "Completed! 🎉"
            }
            Toast.makeText(this, "${stage.title}: $statusMsg", Toast.LENGTH_SHORT).show()
        }
        stagesRecyclerView.layoutManager = GridLayoutManager(this, 2)
        stagesRecyclerView.adapter = stageAdapter
    }
    
    private fun loadRealStageData() {
        val userId = authHelper.getCurrentUser()?.uid ?: return
        
        lifecycleScope.launch {
            // 1. Hydration Data
            val waterResult = hydrationRepo.getTodayWaterTotal(userId)
            val waterTotal = waterResult.getOrDefault(0)
            val isHydrationDone = waterTotal >= 2000

            // 2. Food Data (Nutrition)
            val foodResult = authHelper.getTodayFoodLogs(userId)
            val foodCount = foodResult.getOrNull()?.size ?: 0
            val isNutritionDone = foodCount >= 3

            // 3. Fetch RTDB for Steps and Workouts
            val db = FirebaseDatabase.getInstance("https://swasthyamitra-ded44-default-rtdb.asia-southeast1.firebasedatabase.app").reference
            val userRef = db.child("users").child(userId)
            
            userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val fitnessData = snapshot.getValue(FitnessData::class.java) ?: FitnessData()
                    
                    // Steps
                    val currentSteps = fitnessData.steps
                    val isStepsDone = currentSteps >= 10000
                    
                    // Sleep (Placeholder)
                    val isSleepDone = false 
                    
                    // Meditation
                    val hasMeditation = fitnessData.workoutHistory.values.any { 
                        (it.category.equals("Yoga", ignoreCase = true) || 
                         it.category.equals("Meditation", ignoreCase = true) ||
                         it.category.equals("AI Comp.", ignoreCase = true))
                    }
                    val isMeditationDone = hasMeditation
                    
                    // Workouts (Strength)
                    val workoutCount = fitnessData.workoutHistory.size
                    val isStrengthDone = workoutCount >= 5

                    // Build Stages List
                    updateStagesList(
                        isHydrationDone, 
                        isStepsDone, 
                        isSleepDone, 
                        isMeditationDone, 
                        isNutritionDone, 
                        isStrengthDone,
                        currentSteps
                    )
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle error
                }
            })
        }
    }

    private fun updateStagesList(
        hydration: Boolean, 
        steps: Boolean, 
        sleep: Boolean,
        meditation: Boolean,
        nutrition: Boolean,
        strength: Boolean,
        stepCount: Int
    ) {
        val newStages = mutableListOf<Stage>()
        var isPreviousStageComplete = true // Stage 1 is always unlocked

        // Define Stages with Criteria and Emojis
        // Logic:
        // - If previous is Complete -> Check this stage's criteria.
        //     - If met -> COMPLETED, previous=true
        //     - If not met -> ACTIVE, previous=false
        // - If previous is NOT Complete -> LOCKED, previous=false

        // Stage 1: Hydration 💧
        val stage1Status = if (isPreviousStageComplete) {
            if (hydration) StageStatus.COMPLETED else { isPreviousStageComplete = false; StageStatus.ACTIVE }
        } else StageStatus.LOCKED
        newStages.add(Stage(1, "Hydration Hero 💧", "Drink 2000ml", R.drawable.ic_stage_lotus, stage1Status))

        // Stage 2: Steps 👣
        val stage2Status = if (isPreviousStageComplete) {
            if (steps) StageStatus.COMPLETED else { isPreviousStageComplete = false; StageStatus.ACTIVE }
        } else StageStatus.LOCKED
        newStages.add(Stage(2, "Step Master 👣", "10,000 Steps", R.drawable.ic_shoes_icon, stage2Status))

        // Stage 3: Sleep 😴
        val stage3Status = if (isPreviousStageComplete) {
            if (sleep) StageStatus.COMPLETED else { isPreviousStageComplete = false; StageStatus.ACTIVE }
        } else StageStatus.LOCKED
        newStages.add(Stage(3, "Sleep Saint 😴", "8 Hours Sleep", R.drawable.ic_stage_moon, stage3Status))

        // Stage 4: Meditation 🧘
        val stage4Status = if (isPreviousStageComplete) {
            if (meditation) StageStatus.COMPLETED else { isPreviousStageComplete = false; StageStatus.ACTIVE }
        } else StageStatus.LOCKED
        newStages.add(Stage(4, "Zen Master 🧘", "15m Meditation", R.drawable.ic_stage_lotus, stage4Status))
        
        // Stage 5: Nutrition 🍎
        val stage5Status = if (isPreviousStageComplete) {
            if (nutrition) StageStatus.COMPLETED else { isPreviousStageComplete = false; StageStatus.ACTIVE }
        } else StageStatus.LOCKED
        newStages.add(Stage(5, "Nutrition Ninja 🍎", "Log 3 Meals", R.drawable.ic_check_circle, stage5Status)) 
        
        // Stage 6: Strength 🏋️
        val stage6Status = if (isPreviousStageComplete) {
            if (strength) StageStatus.COMPLETED else { isPreviousStageComplete = false; StageStatus.ACTIVE }
        } else StageStatus.LOCKED
        newStages.add(Stage(6, "Iron Legend 🏋️", "5 Workouts", R.drawable.ic_stage_dumbbell, stage6Status))

        stages.clear()
        stages.addAll(newStages)
        stageAdapter.notifyDataSetChanged()
        
        // Update Header Progress
        val completedCount = newStages.count { it.status == StageStatus.COMPLETED }
        val activeStageIndex = newStages.indexOfFirst { it.status == StageStatus.ACTIVE }
        val displayStage = if (activeStageIndex != -1) activeStageIndex + 1 else if (completedCount == 6) 6 else 1
        
        tvStageProgress.text = "Stage $displayStage / 6"
        journeyProgressBar.progress = (completedCount.toFloat() / 6f * 100).toInt()
        
        // Update Bottom Sheet Stats
        tvCurrentSteps.text = String.format("%,d", stepCount)
    }

    inner class StageAdapter(
        private val stages: List<Stage>,
        private val onItemClick: (Stage) -> Unit
    ) : RecyclerView.Adapter<StageAdapter.ViewHolder>() {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val contentLayout: View = view.findViewById(R.id.contentLayout)
            val icon: ImageView = view.findViewById(R.id.ivStageIcon)
            val stageLabel: TextView = view.findViewById(R.id.tvStageLabel)
            val title: TextView = view.findViewById(R.id.tvStageTitle)
            val activeBadge: TextView = view.findViewById(R.id.tvActiveBadge)
            val statusIcon: ImageView = view.findViewById(R.id.ivStatusIcon)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_stage_card, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val stage = stages[position]

            holder.stageLabel.text = "STAGE ${stage.id}"
            holder.title.text = stage.title
            
            // Icon
            holder.icon.setImageResource(stage.iconRes)
            
            holder.itemView.setOnClickListener {
                onItemClick(stage)
            }

            when (stage.status) {
                StageStatus.COMPLETED -> {
                    holder.contentLayout.setBackgroundResource(R.drawable.bg_stage_card_completed)
                    holder.icon.alpha = 1.0f
                    holder.icon.setColorFilter(Color.parseColor("#7B2CBF"))
                    holder.statusIcon.visibility = View.VISIBLE
                    holder.activeBadge.visibility = View.GONE
                    holder.stageLabel.setTextColor(Color.parseColor("#7B2CBF"))
                    holder.title.setTextColor(Color.parseColor("#4A148C")) // Darker Purple
                }
                StageStatus.ACTIVE -> {
                    holder.contentLayout.setBackgroundResource(R.drawable.bg_stage_card_active)
                    holder.icon.alpha = 1.0f
                    holder.icon.setColorFilter(Color.parseColor("#7B2CBF"))
                    holder.statusIcon.visibility = View.GONE
                    holder.activeBadge.visibility = View.VISIBLE
                    holder.stageLabel.setTextColor(Color.parseColor("#7B2CBF"))
                    holder.title.setTextColor(Color.parseColor("#212121"))
                }
                StageStatus.LOCKED -> {
                    holder.contentLayout.setBackgroundResource(R.drawable.bg_stage_card_locked)
                    holder.icon.alpha = 0.5f
                    holder.icon.setColorFilter(Color.GRAY)
                    holder.icon.setImageResource(R.drawable.ic_lock)
                    
                    holder.statusIcon.visibility = View.GONE
                    holder.activeBadge.visibility = View.GONE
                    holder.stageLabel.setTextColor(Color.parseColor("#9E9E9E"))
                    holder.title.setTextColor(Color.parseColor("#9E9E9E"))
                }
            }
        }

        override fun getItemCount() = stages.size
    }
}
