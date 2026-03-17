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
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

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
    private lateinit var cvJoinedChallenges: androidx.cardview.widget.CardView
    private lateinit var challengeListContainer: LinearLayout

    private val DAILY_GOAL = 5000 
    private val LEVEL_XP_THRESHOLD = 100  // Must match XPManager.XP_PER_LEVEL

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
                val currentUser = authHelper.getCurrentUser()
                val displayName = currentUser?.displayName
                    ?: currentUser?.email?.substringBefore("@")
                    ?: "User"
                val userEmail = currentUser?.email ?: ""
                repository = GamificationRepository(userId, displayName, userEmail)
                // Immediately publish the index so friends can find this user right away
                repository.publishUserIndex()
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
        cvJoinedChallenges = findViewById(R.id.cvJoinedChallenges)
        challengeListContainer = findViewById(R.id.challengeListContainer)
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
                    // Keep the RTDB data as baseline (steps, xp, level, workoutHistory)
                    currentData = it
                    updateUI(currentData.steps)
                    updateStreakUI()

                    // Trigger daily check-in via coroutine (suspended Firestore calls)
                    lifecycleScope.launch {
                        if (::repository.isInitialized) {
                            try {
                                // Step 1: validate / apply shield if missed a day
                                repository.validateAndFixStreak()
                                // Step 2: mark today active, increment streak
                                val updated = repository.checkIn()

                                // Step 3: merge fresh Firestore streak data into currentData
                                // completionHistory, streak, shields now come from Firestore
                                currentData = currentData.copy(
                                    streak             = updated.streak,
                                    shields            = updated.shields,
                                    lastActiveDate     = updated.lastActiveDate,
                                    completionHistory  = updated.completionHistory
                                )

                                runOnUiThread {
                                    updateStreakUI()
                                    saveLocalData()
                                }
                            } catch (e: Exception) {
                                android.util.Log.e("GamificationActivity", "Sync error: ${e.message}")
                            }
                        }
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }

    private fun startStepTracking() {
        stepManager = StepManager(this) { steps, burned ->
            currentData = currentData.copy(steps = steps)
            runOnUiThread {
                updateUI(steps)
                updateStreakUI()
                checkLevelUp()
                saveLocalData()
            }
        }
        // Enable hybrid validation for accurate step counting
        stepManager.start(enableHybridValidation = true)
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
        return try {
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            val parsed = sdf.parse(date) ?: return false
            val diffMs = System.currentTimeMillis() - parsed.time
            val diffDays = diffMs / (24 * 60 * 60 * 1000)
            diffDays <= 3 // Consider "recent" if within 3 days
        } catch (e: Exception) {
            false
        }
    }

    private fun generateCalendar() {
        streakCalendarContainer.removeAllViews()

        val sdf       = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val dayFormat = SimpleDateFormat("EEE", Locale.US)
        val today     = sdf.format(Date())

        // Build a mutable view of the history so we can add today optimistically
        val history = currentData.completionHistory.toMutableMap()

        // Always mark today as active in the calendar when we're on the streak screen
        // (the user is actively using the app right now)
        if (currentData.lastActiveDate == today || currentData.streak > 0) {
            history[today] = true
        }

        // Show 7 days: 3 days ago → today + 3 future placeholders
        val cal = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_YEAR, -3)

        for (i in 0 until 7) {
            val dateStr  = sdf.format(cal.time)
            val dayName  = dayFormat.format(cal.time).uppercase()
            val dayNum   = cal.get(Calendar.DAY_OF_MONTH).toString()
            val isToday  = dateStr == today
            val isFuture = dateStr > today

            // A day is "completed" if it's in the history map OR it's today and we have an active streak
            val isCompleted = history[dateStr] == true

            streakCalendarContainer.addView(
                createDayView(dayName, dayNum, isCompleted, isToday, isFuture)
            )
            cal.add(Calendar.DAY_OF_YEAR, 1)
        }
    }
    
    private fun createDayView(
        day: String,
        date: String,
        completed: Boolean,
        isToday: Boolean,
        isFuture: Boolean = false
    ): View {
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.gravity     = Gravity.CENTER

        val sizePx = 140
        layout.layoutParams = LinearLayout.LayoutParams(sizePx, sizePx).apply {
            weight = 1f
            setMargins(4, 0, 4, 0)
        }
        layout.setPadding(0, 0, 0, 0)

        // ── Background colour logic ──────────────────────────────────────────────
        // Today (active)  → purple
        // Past completed  → green
        // Future / blank  → light grey
        val shape = android.graphics.drawable.GradientDrawable().apply {
            shape = android.graphics.drawable.GradientDrawable.OVAL
            setColor(
                when {
                    isToday && completed -> 0xFF7B2CBF.toInt()  // purple — today + active
                    isToday             -> 0xFF9C4DCC.toInt()  // lighter purple — today, not yet counted
                    completed           -> 0xFF388E3C.toInt()  // green — past active day
                    isFuture            -> 0xFFEEEEEE.toInt()  // very light — future
                    else                -> 0xFFE0E0E0.toInt()  // grey — missed day
                }
            )
        }
        layout.background = shape

        // ── Day label (MON, TUE …) ───────────────────────────────────────────────
        val tvDay = TextView(this).apply {
            text      = day
            textSize  = 10f
            gravity   = Gravity.CENTER
            setTextColor(
                when {
                    isToday   -> 0xCCFFFFFF.toInt()
                    completed -> 0xFFFFFFFF.toInt()
                    else      -> 0xFF888888.toInt()
                }
            )
        }

        // ── Date number ─────────────────────────────────────────────────────────
        val tvDate = TextView(this).apply {
            text    = date
            textSize = 16f
            gravity  = Gravity.CENTER
            setTypeface(null, android.graphics.Typeface.BOLD)
            setPadding(0, 0, 0, 4)
            setTextColor(
                when {
                    isToday || completed -> 0xFFFFFFFF.toInt()
                    else                 -> 0xFF555555.toInt()
                }
            )
        }

        // ── Status icon ─────────────────────────────────────────────────────────
        val statusView = TextView(this).apply {
            textSize = 12f
            gravity  = Gravity.CENTER
            when {
                completed && isToday -> { text = "🔥"; setTextColor(0xFFFFD700.toInt()) }
                completed            -> { text = "★";  setTextColor(0xFFFFFFFF.toInt()) }
                isToday              -> { text = "•";  setTextColor(0xFFFFFFFF.toInt()) }
                else                 ->   text = ""
            }
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
        // Date check: only use saved steps if they're from today
        val savedDate = stepPrefs.getString("last_date", "")
        val today = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(java.util.Date())
        val steps = if (savedDate == today) stepPrefs.getInt("daily_steps", 0) else 0
        
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
                0 -> startActivityForResult(
                    Intent(this, ChallengeSetupActivity::class.java),
                    REQUEST_CREATE_CHALLENGE
                )
                1 -> startActivityForResult(
                    android.content.Intent(this, JoinChallengeActivity::class.java),
                    REQUEST_JOIN_CHALLENGE
                )
            }
        }
        builder.show()
    }

    companion object {
        private const val REQUEST_JOIN_CHALLENGE   = 1001
        private const val REQUEST_CREATE_CHALLENGE = 1002
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: android.content.Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && (
                requestCode == REQUEST_JOIN_CHALLENGE ||
                requestCode == REQUEST_CREATE_CHALLENGE)
        ) {
            loadJoinedChallenges()
        }
    }

    override fun onResume() {
        super.onResume()
        if (userId.isNotEmpty()) {
            claimPendingChallengeUpdate() // claim any challenge results written while we were away
            loadJoinedChallenges()
        }
    }

    /**
     * Checks RTDB for a pending challenge status update left by ChallengeDetailActivity.endChallenge().
     * The opponent of a challenge cannot write to our joined_challenges (RTDB rules), so they leave
     * a flag at userStats/<uid>/pendingChallengeUpdate — we claim it here.
     */
    private fun claimPendingChallengeUpdate() {
        val db = database ?: return
        db.child("userStats").child(userId).child("pendingChallengeUpdate")
            .addListenerForSingleValueEvent(object : com.google.firebase.database.ValueEventListener {
                override fun onDataChange(snap: com.google.firebase.database.DataSnapshot) {
                    if (!snap.exists()) return

                    val code     = snap.child("challengeCode").getValue(String::class.java) ?: return
                    val status   = snap.child("status").getValue(String::class.java) ?: "completed"
                    val winnerId = snap.child("winnerId").getValue(String::class.java) ?: ""

                    // Write to our OWN joined_challenges — we have permission for this
                    val updates = mutableMapOf<String, Any>(
                        "users/$userId/joined_challenges/$code/status" to status
                    )
                    if (winnerId.isNotEmpty()) {
                        updates["users/$userId/joined_challenges/$code/winnerId"] = winnerId
                    }

                    db.updateChildren(updates).addOnSuccessListener {
                        // Clear the flag now that we've claimed it
                        db.child("userStats").child(userId).child("pendingChallengeUpdate").removeValue()
                        android.util.Log.d("GamificationActivity", "Claimed pending challenge update for code=$code")
                        loadJoinedChallenges() // refresh UI
                    }
                }
                override fun onCancelled(error: com.google.firebase.database.DatabaseError) {}
            })
    }

    /**
     * Loads joined challenges from RTDB (users/<uid>/joined_challenges)
     * and shows them in the cvJoinedChallenges card.
     * Each row is tappable → opens ChallengeDetailActivity.
     */
    private fun loadJoinedChallenges() {
        if (userId.isEmpty()) return
        val db = database ?: return

        db.child("users").child(userId).child("joined_challenges")
            .addListenerForSingleValueEvent(object : com.google.firebase.database.ValueEventListener {
                override fun onDataChange(snapshot: com.google.firebase.database.DataSnapshot) {
                    challengeListContainer.removeAllViews()

                    if (!snapshot.exists() || snapshot.childrenCount == 0L) {
                        cvJoinedChallenges.visibility = android.view.View.GONE
                        return
                    }

                    cvJoinedChallenges.visibility = android.view.View.VISIBLE

                    for (child in snapshot.children) {
                        val name     = child.child("challengeName").getValue(String::class.java) ?: "Challenge"
                        val code     = child.child("challengeId").getValue(String::class.java) ?: child.key ?: ""
                        val status   = child.child("status").getValue(String::class.java) ?: "active"
                        val winnerId = child.child("winnerId").getValue(String::class.java)
                        val isWinner = winnerId == userId

                        // ── Row container ──────────────────────────────────────────
                        val row = android.widget.LinearLayout(this@GamificationActivity).apply {
                            orientation  = android.widget.LinearLayout.HORIZONTAL
                            gravity      = android.view.Gravity.CENTER_VERTICAL
                            setPadding(0, 12, 0, 12)
                            isClickable  = true
                            isFocusable  = true
                            background   = android.util.TypedValue().also { tv ->
                                theme.resolveAttribute(android.R.attr.selectableItemBackground, tv, true)
                            }.resourceId.let { resId ->
                                if (resId != 0) getDrawable(resId) else null
                            }
                        }

                        // Icon
                        val tvIcon = android.widget.TextView(this@GamificationActivity).apply {
                            text     = when {
                                status == "completed" && isWinner -> "🏆"
                                status == "completed"             -> "🏁"
                                else                             -> "⚔️"
                            }
                            textSize = 20f
                            setPadding(0, 0, 12, 0)
                        }

                        // Name + code
                        val tvInfo = android.widget.TextView(this@GamificationActivity).apply {
                            text = "$name\nCode: $code"
                            textSize = 13f
                            setTextColor(0xFF333333.toInt())
                            layoutParams = android.widget.LinearLayout.LayoutParams(
                                0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1f
                            )
                        }

                        // Status label
                        val tvStatus = android.widget.TextView(this@GamificationActivity).apply {
                            text = when {
                                status == "completed" && isWinner -> "🏆 Won!"
                                status == "completed"             -> "😔 Lost"
                                else                             -> "🟢 Active\nTap to view"
                            }
                            textSize = 12f
                            gravity  = android.view.Gravity.END
                            setTextColor(
                                when {
                                    status == "completed" && isWinner -> 0xFFFF8F00.toInt()
                                    status == "completed"             -> 0xFFD32F2F.toInt()
                                    else                             -> 0xFF388E3C.toInt()
                                }
                            )
                        }

                        row.addView(tvIcon)
                        row.addView(tvInfo)
                        row.addView(tvStatus)

                        // ── Click → open detail ────────────────────────────────────
                        if (code.isNotEmpty()) {
                            row.setOnClickListener {
                                val intent = Intent(this@GamificationActivity, ChallengeDetailActivity::class.java)
                                intent.putExtra("CHALLENGE_CODE", code)
                                startActivity(intent)
                            }
                        }

                        challengeListContainer.addView(row)

                        // Divider
                        val divider = android.view.View(this@GamificationActivity).apply {
                            layoutParams = android.widget.LinearLayout.LayoutParams(
                                android.view.ViewGroup.LayoutParams.MATCH_PARENT, 1
                            ).also { it.setMargins(0, 4, 0, 4) }
                            setBackgroundColor(0xFFEEEEEE.toInt())
                        }
                        challengeListContainer.addView(divider)
                    }
                }

                override fun onCancelled(error: com.google.firebase.database.DatabaseError) {
                    android.util.Log.e("GamificationActivity", "Failed to load challenges: ${error.message}")
                }
            })
    }
}
