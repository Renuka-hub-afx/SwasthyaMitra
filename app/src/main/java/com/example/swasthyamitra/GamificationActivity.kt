package com.example.swasthyamitra

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.Date
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import java.util.Calendar
import java.util.Locale
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GamificationActivity : AppCompatActivity() {

    private lateinit var stepManager: StepManager
    private var database: DatabaseReference? = null
    private var userId: String = ""
    private lateinit var repository: GamificationRepository
    private lateinit var authHelper: FirebaseAuthHelper
    
    // Data
    private var currentData = FitnessData()
    
    // UI Extras
    private lateinit var streakCalendarContainer: LinearLayout
    private lateinit var tvStreakMessage: TextView
    private lateinit var comebackBonusContainer: FrameLayout

    private val DAILY_GOAL = 5000 
    private val LEVEL_XP_THRESHOLD = 1000

    private lateinit var tvSteps: TextView
    private lateinit var tvStepMain: TextView
    private lateinit var stepProgressBar: ProgressBar
    private lateinit var tvStreakValue: TextView
    private lateinit var tvShieldValue: TextView
    private lateinit var tvLevelName: TextView
    private lateinit var xpProgress: ProgressBar

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val isActivityRecognitionGranted = permissions[Manifest.permission.ACTIVITY_RECOGNITION] ?: false
            val isNotificationGranted = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                permissions[Manifest.permission.POST_NOTIFICATIONS] ?: false
            } else true
            
            if (isActivityRecognitionGranted) {
                startStepTracking()
            } else {
                 Toast.makeText(this, "Permission required for step counting", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gamification)

        val app = application as? UserApplication
        authHelper = app?.authHelper ?: FirebaseAuthHelper(this)
        userId = authHelper.getCurrentUser()?.uid ?: ""

        if (userId.isEmpty()) {
            Toast.makeText(this, "Please log in to track progress", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        initializeUI()
        setupListeners()
        
        try {
            if (FirebaseApp.getApps(this).isNotEmpty()) {
                database = FirebaseDatabase.getInstance("https://swasthyamitra-ded44-default-rtdb.asia-southeast1.firebasedatabase.app").reference
                repository = GamificationRepository(database!!, userId)
                syncWithFirebase()
            } else {
                loadLocalData()
            }
        } catch (e: Exception) {
            loadLocalData()
        }
        
        updateStreakUI()
        checkAndRequestPermissions()
        updateUI(currentData.steps)
    }

    private fun initializeUI() {
        tvSteps = findViewById(R.id.tvSteps)
        tvStepMain = findViewById(R.id.tvStepMain)
        stepProgressBar = findViewById(R.id.stepProgressBar)
        tvStreakValue = findViewById(R.id.tvStreakValue)
        tvShieldValue = findViewById(R.id.tvShieldValue)
        tvLevelName = findViewById(R.id.tvLevelName)
        xpProgress = findViewById(R.id.xpProgress)
        streakCalendarContainer = findViewById(R.id.streakCalendarContainer)
        tvStreakMessage = findViewById(R.id.tvStreakMessage)
        comebackBonusContainer = findViewById(R.id.comebackBonusContainer)
    }

    private fun setupListeners() {
        findViewById<CardView>(R.id.cvLevel).setOnClickListener {
            Toast.makeText(this, "Level details coming soon!", Toast.LENGTH_SHORT).show()
        }
        findViewById<CardView>(R.id.cvStreak).setOnClickListener {
            val intent = Intent(this, StreakDetailsActivity::class.java)
            intent.putExtra("FITNESS_DATA", currentData)
            intent.putExtra("VIEW_MODE", "STREAK")
            startActivity(intent)
        }
        findViewById<CardView>(R.id.cvShield).setOnClickListener {
            val intent = Intent(this, StreakDetailsActivity::class.java)
            intent.putExtra("FITNESS_DATA", currentData)
            intent.putExtra("VIEW_MODE", "SHIELD")
            startActivity(intent)
        }
        findViewById<View>(R.id.navProgress).setOnClickListener {
            showChallengeOptions()
        }
        
        findViewById<View>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }

    private fun syncWithFirebase() {
        database?.child("users")?.child(userId)?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.getValue(FitnessData::class.java)
                data?.let {
                    // Auto-fix streak if corrupted
                    val checkedData = if (::repository.isInitialized) {
                         repository.validateAndFixStreak(it)
                    } else it
                    
                    currentData = checkedData
                    updateUI(currentData.steps)
                    updateStreakUI()
                    
                    // Trigger daily check-in
                    repository.checkIn(currentData) { updated ->
                         currentData = updated
                         runOnUiThread {
                             updateStreakUI()
                             saveLocalData()
                         }
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }

    private fun startStepTracking() {
        stepManager = StepManager(this) { steps, burned ->
            if (::repository.isInitialized) {
                repository.updateSteps(currentData, steps) { updated ->
                    currentData = updated
                    runOnUiThread {
                        updateUI(updated.steps)
                        updateStreakUI()
                        checkLevelUp()
                        saveLocalData()
                    }
                }
            } else {
                 currentData = currentData.copy(steps = steps)
                 runOnUiThread { updateUI(steps) }
            }
        }
        stepManager.start()
    }

    private fun updateUI(steps: Int) {
        val stepsText = String.format("%,d", steps)
        tvSteps.text = stepsText
        tvStepMain.text = stepsText
        stepProgressBar.progress = steps
    }

    private fun checkLevelUp() {
        if (currentData.xp >= LEVEL_XP_THRESHOLD * currentData.level) {
             Toast.makeText(this, "LEVEL UP! You are now a Level ${currentData.level + 1} Adventurer!", Toast.LENGTH_LONG).show()
        }
    }

    private fun updateStreakUI() {
        tvStreakValue.text = "${currentData.streak} days"
        tvShieldValue.text = currentData.shields.toString()
        tvLevelName.text = "Level ${currentData.level} Explorer"
        xpProgress.progress = (currentData.xp.toFloat() / (LEVEL_XP_THRESHOLD * currentData.level) * 100).toInt()
        
        generateCalendar()
        
        if (currentData.lastStreakBreakDate.isNotEmpty() && isRecent(currentData.lastStreakBreakDate)) {
             comebackBonusContainer.visibility = View.VISIBLE
        } else {
             comebackBonusContainer.visibility = View.GONE
        }
    }

    private fun isRecent(date: String): Boolean {
        return true 
    }

    private fun generateCalendar() {
        streakCalendarContainer.removeAllViews()
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -3)
        
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val dayFormat = SimpleDateFormat("EEE", Locale.getDefault())
        val todayStr = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        
        for (i in 0 until 7) {
             val dateStr = sdf.format(calendar.time)
             val dayName = dayFormat.format(calendar.time).uppercase()
             val dayNumber = calendar.get(Calendar.DAY_OF_MONTH).toString()
             val isCompleted = currentData.completionHistory[dateStr] == true
             val isToday = dateStr == todayStr
             
             val dayView = createDayView(dayName, dayNumber, isCompleted, isToday)
             streakCalendarContainer.addView(dayView)
             
             calendar.add(Calendar.DAY_OF_YEAR, 1)
        }
    }
    
    private fun createDayView(day: String, date: String, completed: Boolean, isToday: Boolean): View {
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.gravity = Gravity.CENTER
        val params = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
        params.setMargins(4, 0, 4, 0)
        layout.layoutParams = params
        layout.setPadding(8, 16, 8, 16)
        
        val shape = android.graphics.drawable.GradientDrawable()
        shape.shape = android.graphics.drawable.GradientDrawable.OVAL
        
        if (isToday) {
            shape.setColor(0xFF7B2CBF.toInt()) // Purple for Today
        } else {
            shape.setColor(0xFFF5F5F5.toInt()) // Light Grey for others
        }
        layout.background = shape
        
        val sizePx = 140 
        layout.layoutParams = LinearLayout.LayoutParams(sizePx, sizePx).apply {
             weight = 1f
             setMargins(4, 0, 4, 0)
        }
        layout.setPadding(0, 0, 0, 0)
        
        val tvDay = TextView(this)
        tvDay.text = day
        tvDay.textSize = 10f
        tvDay.setTextColor(if (isToday) 0xCCFFFFFF.toInt() else 0xFF666666.toInt())
        tvDay.gravity = Gravity.CENTER
        
        val tvDate = TextView(this)
        tvDate.text = date
        tvDate.textSize = 16f
        tvDate.gravity = Gravity.CENTER
        tvDate.setTypeface(null, android.graphics.Typeface.BOLD)
        tvDate.setTextColor(if (isToday) 0xFFFFFFFF.toInt() else 0xFF333333.toInt())
        tvDate.setPadding(0, 0, 0, 4)
        
        val statusView = TextView(this)
        statusView.textSize = 10f
        statusView.gravity = Gravity.CENTER
        
        if (completed) {
            statusView.text = "★" 
            statusView.setTextColor(0xFFFFD700.toInt())
        } else if (isToday) {
             statusView.text = "•" 
             statusView.setTextColor(0xFFFFFFFF.toInt())
        } else {
             statusView.text = "" 
        }

        layout.addView(tvDay)
        layout.addView(tvDate)
        layout.addView(statusView)
        return layout
    }

    private fun saveData() {
        saveLocalData()
        saveToFirebase()
    }

    private fun saveToFirebase() {
        database?.child("users")?.child(userId)?.setValue(currentData)
    }

    private fun saveLocalData() {
        val prefs = getSharedPreferences("FitnessQuestPrefs", Context.MODE_PRIVATE)
        val historyJson = Gson().toJson(currentData.completionHistory)
        
        prefs.edit().apply {
            putInt("steps", currentData.steps)
            putInt("streak", currentData.streak)
            putInt("shields", currentData.shields)
            putInt("xp", currentData.xp)
            putInt("level", currentData.level)
            putString("lastDate", currentData.lastActiveDate)
            putString("completionHistory", historyJson)
            apply()
        }
    }

    private fun loadLocalData() {
        val prefs = getSharedPreferences("FitnessQuestPrefs", Context.MODE_PRIVATE)
        
        val stepPrefs = getSharedPreferences("StepCounterPrefs", Context.MODE_PRIVATE)
        val steps = stepPrefs.getInt("daily_steps", 0)
        
        val streak = prefs.getInt("streak", 0)
        val shields = prefs.getInt("shields", 0)
        val xp = prefs.getInt("xp", 0)
        val level = prefs.getInt("level", 1)
        val lastDate = prefs.getString("lastDate", "") ?: ""
        
        val historyJson = prefs.getString("completionHistory", "{}")
        val type = object : TypeToken<Map<String, Boolean>>() {}.type
        val history: Map<String, Boolean> = try {
            Gson().fromJson(historyJson, type)
        } catch (e: Exception) {
            emptyMap()
        }
        
        currentData = FitnessData(
            steps = steps,
            streak = streak,
            shields = shields,
            xp = xp,
            level = level,
            lastActiveDate = lastDate,
            completionHistory = history
        )
        updateUI(steps)
        updateStreakUI()
    }

    private fun checkAndRequestPermissions() {
        val permissionsToRequest = mutableListOf<String>()
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION) != PackageManager.PERMISSION_GRANTED) {
                permissionsToRequest.add(Manifest.permission.ACTIVITY_RECOGNITION)
            }
        }
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                permissionsToRequest.add(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
        
        if (permissionsToRequest.isNotEmpty()) {
            requestPermissionLauncher.launch(permissionsToRequest.toTypedArray())
        } else {
            startStepTracking()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::stepManager.isInitialized) stepManager.stop()
    }


    private fun showChallengeOptions() {
        val options = arrayOf("Create New Challenge", "Join with Code")
        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
        builder.setTitle("⚔️ Step Duel")
        builder.setItems(options) { _, which ->
            when (which) {
                0 -> startActivity(Intent(this, ChallengeSetupActivity::class.java))
                1 -> startActivity(Intent(this, JoinChallengeActivity::class.java))
            }
        }
        builder.show()
    }
}
