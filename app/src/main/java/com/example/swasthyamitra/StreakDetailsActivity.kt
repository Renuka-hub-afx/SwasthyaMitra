package com.example.swasthyamitra

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class StreakDetailsActivity : AppCompatActivity() {

    private lateinit var tvTitle: TextView
    private lateinit var scrollView: android.widget.ScrollView
    
    // Containers
    private lateinit var llShieldSection: android.widget.LinearLayout
    private lateinit var llStreakHistoryPlaceholder: android.widget.LinearLayout
    
    private lateinit var tvCurrentStreak: TextView
    private lateinit var tvAvailableXP: TextView
    private lateinit var btnBuyFreeze: Button
    private lateinit var btnBuyRepair: Button
    
    private var currentData: FitnessData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_streak_details)

        currentData = intent.getSerializableExtra("FITNESS_DATA") as? FitnessData
        val viewMode = intent.getStringExtra("VIEW_MODE") ?: "STREAK"
        
        initViews()
        updateUI()
        setupListeners()
        
        handleViewMode(viewMode)
    }

    private fun initViews() {
        tvTitle = findViewById(R.id.tvTitle)
        tvCurrentStreak = findViewById(R.id.tvCurrentStreak)
        tvAvailableXP = findViewById(R.id.tvAvailableXP)
        btnBuyFreeze = findViewById(R.id.btnBuyFreeze)
        btnBuyRepair = findViewById(R.id.btnBuyRepair)
        scrollView = findViewById(R.id.scrollView)
        
        llShieldSection = findViewById(R.id.llShieldSection)
        llStreakHistoryPlaceholder = findViewById(R.id.llStreakHistoryPlaceholder)

        findViewById<android.view.View>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }
    
    private fun handleViewMode(mode: String) {
        if (mode == "SHIELD") {
            tvTitle.text = "Shield Store 🛡️"
            llShieldSection.visibility = android.view.View.VISIBLE
            llStreakHistoryPlaceholder.visibility = android.view.View.GONE
        } else {
             tvTitle.text = "Streak Details 🔥"
             llShieldSection.visibility = android.view.View.GONE
             llStreakHistoryPlaceholder.visibility = android.view.View.VISIBLE
        }
    }

    private fun updateUI() {
        val data = currentData ?: FitnessData()
        tvCurrentStreak.text = "${data.streak} days"
        tvAvailableXP.text = "${data.xp} XP"
        
        generateCalendar(data)
    }

    private fun generateCalendar(data: FitnessData) {
        val calendarGrid = findViewById<android.widget.GridLayout>(R.id.calendarGrid)
        val tvCalendarMonth = findViewById<TextView>(R.id.tvCalendarMonth)
        
        calendarGrid.removeAllViews()
        
        val calendar = java.util.Calendar.getInstance()
        val currentDay = calendar.get(java.util.Calendar.DAY_OF_MONTH)
        
        // Set to first day of month
        calendar.set(java.util.Calendar.DAY_OF_MONTH, 1)
        val daysInMonth = calendar.getActualMaximum(java.util.Calendar.DAY_OF_MONTH)
        val firstDayOfWeek = calendar.get(java.util.Calendar.DAY_OF_WEEK) // Sun=1
        
        // Month Title
        val monthFormat = java.text.SimpleDateFormat("MMMM yyyy", java.util.Locale.getDefault())
        tvCalendarMonth.text = monthFormat.format(calendar.time)
        
        // Empty slots before 1st day
        for (i in 1 until firstDayOfWeek) {
            val emptyView = android.view.View(this)
            val params = android.widget.GridLayout.LayoutParams()
            params.width = 0
            params.height = 0
            params.columnSpec = android.widget.GridLayout.spec(android.widget.GridLayout.UNDEFINED, 1f)
            calendarGrid.addView(emptyView, params)
        }
        
        val dateFormat = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
        
        for (day in 1..daysInMonth) {
            calendar.set(java.util.Calendar.DAY_OF_MONTH, day)
            val dateString = dateFormat.format(calendar.time)
            val isCompleted = data.completionHistory[dateString] == true
            val isToday = day == currentDay
            
            val dayView = TextView(this)
            dayView.text = day.toString()
            dayView.gravity = android.view.Gravity.CENTER
            dayView.textSize = 14f
            
            val params = android.widget.GridLayout.LayoutParams()
            params.width = 0
            params.height = 100 // Fixed height for square-ish look
            params.columnSpec = android.widget.GridLayout.spec(android.widget.GridLayout.UNDEFINED, 1f)
            params.setMargins(4, 4, 4, 4)
            dayView.layoutParams = params
            
            // Styling
            val shape = android.graphics.drawable.GradientDrawable()
            shape.shape = android.graphics.drawable.GradientDrawable.OVAL
            
            if (isCompleted) {
                shape.colors = intArrayOf(0xFF7B2CBF.toInt(), 0xFFE91E63.toInt()) // Button Gradient colors
                shape.orientation = android.graphics.drawable.GradientDrawable.Orientation.TL_BR
                dayView.setTextColor(0xFFFFFFFF.toInt())
                dayView.typeface = android.graphics.Typeface.DEFAULT_BOLD
                dayView.background = shape
            } else if (isToday) {
                shape.setColor(0x00FFFFFF.toInt()) // Transparent
                shape.setStroke(4, 0xFF7B2CBF.toInt()) // Purple Border
                dayView.setTextColor(0xFF333333.toInt())
                dayView.typeface = android.graphics.Typeface.DEFAULT_BOLD
                dayView.background = shape
            } else {
                 dayView.setTextColor(0xFF757575.toInt())
            }
            
            calendarGrid.addView(dayView)
        }
    }

    private fun setupListeners() {
        btnBuyFreeze.setOnClickListener {
             // Mock purchase
             val cost = 500
             if ((currentData?.xp ?: 0) >= cost) {
                 Toast.makeText(this, "Freeze Shield Purchased! 🛡️", Toast.LENGTH_SHORT).show()
                 // In a real app, we would update Firebase or pass result back
             } else {
                 Toast.makeText(this, "Not enough XP! keep walking 🚶", Toast.LENGTH_SHORT).show()
             }
        }

        btnBuyRepair.setOnClickListener {
            val cost = 1000
             if ((currentData?.xp ?: 0) >= cost) {
                 Toast.makeText(this, "Repair Shield Purchased! 🔧", Toast.LENGTH_SHORT).show()
             } else {
                 Toast.makeText(this, "Not enough XP! Need more sweating 💦", Toast.LENGTH_SHORT).show()
             }
        }
    }
}
