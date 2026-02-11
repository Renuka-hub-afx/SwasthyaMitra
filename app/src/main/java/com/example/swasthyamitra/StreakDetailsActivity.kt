package com.example.swasthyamitra

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class StreakDetailsActivity : AppCompatActivity() {

    private lateinit var tvCurrentStreak: TextView
    private lateinit var tvMonthYear: TextView
    private lateinit var calendarGrid: GridLayout
    private lateinit var btnPrevMonth: android.widget.ImageButton
    private lateinit var btnNextMonth: android.widget.ImageButton
    
    private var fitnessData: FitnessData? = null
    private var viewMode: String = "STREAK" // Default to streak view
    private var currentCalendar: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_streak_details)
        
        // Retrieve Data
        fitnessData = intent.getSerializableExtra("FITNESS_DATA") as? FitnessData
        viewMode = intent.getStringExtra("VIEW_MODE") ?: "STREAK"

        val btnBack = findViewById<android.view.View>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }
        
        initViews()
        setupUI()
    }

    private fun initViews() {
        tvCurrentStreak = findViewById(R.id.tvCurrentStreak)
        tvMonthYear = findViewById(R.id.tvMonthYear)
        calendarGrid = findViewById(R.id.calendarGrid)
        btnPrevMonth = findViewById(R.id.btnPrevMonth)
        btnNextMonth = findViewById(R.id.btnNextMonth)
    }

    private fun setupUI() {
        if (viewMode == "SHIELD") {
            setupShieldUI()
        } else {
            setupStreakUI()
        }
    }

    private fun setupStreakUI() {
        // Update header to show "Streak Details"
        findViewById<TextView>(R.id.tvTitle)?.text = "Streak Details"
        findViewById<TextView>(R.id.tvSubtitle)?.text = "Your Current Streak"
        
        val streak = fitnessData?.streak ?: 0
        tvCurrentStreak.text = "$streak Days"
        
        setupCalendarNavigation()
        generateCalendarForCurrentMonth()
    }
    
    private fun setupCalendarNavigation() {
        btnPrevMonth.setOnClickListener {
            currentCalendar.add(Calendar.MONTH, -1)
            generateCalendarForCurrentMonth()
        }
        
        btnNextMonth.setOnClickListener {
            val today = Calendar.getInstance()
            // Check if adding a month would exceed current month
            val nextMonthCheck = currentCalendar.clone() as Calendar
            nextMonthCheck.add(Calendar.MONTH, 1)
            
            if (nextMonthCheck.get(Calendar.YEAR) < today.get(Calendar.YEAR) ||
                (nextMonthCheck.get(Calendar.YEAR) == today.get(Calendar.YEAR) && 
                 nextMonthCheck.get(Calendar.MONTH) <= today.get(Calendar.MONTH))) {
                 
                currentCalendar.add(Calendar.MONTH, 1)
                generateCalendarForCurrentMonth()
            }
        }
    }

    private fun setupShieldUI() {
        // Update header to show "Shield Details"
        findViewById<TextView>(R.id.tvTitle)?.text = "Shield Details"
        findViewById<TextView>(R.id.tvSubtitle)?.text = "Your Available Shields"
        
        val shields = fitnessData?.shields ?: 0
        tvCurrentStreak.text = "$shields Shields"
        
        // Hide arrows for Shield view
        btnPrevMonth.visibility = android.view.View.GONE
        btnNextMonth.visibility = android.view.View.GONE
        
        // Show shield information instead of calendar
        generateShieldInfo()
    }

    private fun generateShieldInfo() {
        calendarGrid.removeAllViews()
        calendarGrid.columnCount = 1 // Single column for shield info
        
        val shields = fitnessData?.shields ?: 0
        
        // Shield description
        val description = TextView(this)
        
        val shieldText = if (shields > 0) {
            "Your streak is protected for $shields more missed days!"
        } else {
            "No protection active. Start earning shields!"
        }
        
        description.text = "WHAT ARE SHIELDS?\n\n" +
                "Shields protect your streak if you miss a day.\n\n" +
                "YOU HAVE: $shields shield" + (if (shields != 1) "s" else "") + "\n\n" +
                shieldText + "\n\n" +
                "HOW TO EARN SHIELDS:\n\n" +
                "Complete ALL daily goals:\n" +
                "- Meet step goal (5,000 steps)\n" +
                "- Hit calorie target\n" +
                "- Complete a workout\n\n" +
                "Earn 1 shield per day!\n" +
                "BONUS: 7-day streak = extra shield!"
        
        description.textSize = 14f
        description.setTextColor(Color.parseColor("#333333"))
        description.setPadding(32, 32, 32, 32)
        description.gravity = Gravity.START
        description.setLineSpacing(4f, 1.0f)
        
        val params = GridLayout.LayoutParams()
        params.width = GridLayout.LayoutParams.MATCH_PARENT
        params.height = GridLayout.LayoutParams.WRAP_CONTENT
        description.layoutParams = params
        
        calendarGrid.addView(description)
        
        // Update month/year to show shield max
        tvMonthYear.text = "Maximum: 10 shields"
    }

    private fun generateCalendarForCurrentMonth() {
        calendarGrid.removeAllViews()

        val calendar = currentCalendar.clone() as Calendar
        // Set to first day of the month
        calendar.set(Calendar.DAY_OF_MONTH, 1)

        val currentMonth = calendar.get(Calendar.MONTH)
        val currentYear = calendar.get(Calendar.YEAR)
        
        // Disable next button if future
        val today = Calendar.getInstance()
        val isCurrentMonth = (currentYear == today.get(Calendar.YEAR) && currentMonth == today.get(Calendar.MONTH))
        btnNextMonth.alpha = if (isCurrentMonth) 0.3f else 1.0f
        btnNextMonth.isEnabled = !isCurrentMonth
        
        // Update Title
        val monthFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
        tvMonthYear.text = monthFormat.format(calendar.time)

        // Get day of week for the 1st (Sun=1, Mon=2, ...)
        val firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        // Offset (if Sun is 1, and we want 0 empty cells before Sun, offset is 0. If Mon=2, offset is 1)
        val offset = firstDayOfWeek - 1

        val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        // Add empty views for offset
        for (i in 0 until offset) {
            val emptyView = TextView(this)
            val params = GridLayout.LayoutParams()
            params.width = 0
            params.height = GridLayout.LayoutParams.WRAP_CONTENT
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
            emptyView.layoutParams = params
            calendarGrid.addView(emptyView)
        }

        val todayDateStr = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val history = fitnessData?.completionHistory ?: emptyMap()

        // Generate Day Views
        for (day in 1..daysInMonth) {
            // Reconstruct full date string for this day
            calendar.set(Calendar.DAY_OF_MONTH, day)
            val dateStr = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time)
            
            val isCompleted = history[dateStr] == true
            val isToday = dateStr == todayDateStr
            // Check if future
            val isFuture = calendar.time.after(Date()) && !isToday

            val dayView = createDayView(day, isCompleted, isToday, isFuture)
            calendarGrid.addView(dayView)
        }
    }

    private fun createDayView(day: Int, isCompleted: Boolean, isToday: Boolean, isFuture: Boolean): TextView {
        val textView = TextView(this)
        textView.text = day.toString()
        textView.gravity = Gravity.CENTER
        textView.textSize = 14f
        textView.setTypeface(null, Typeface.BOLD)

        // Layout Params
        val size = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40f, resources.displayMetrics).toInt()
        val params = GridLayout.LayoutParams()
        params.width = 0
        params.height = size
        params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
        params.setMargins(8, 8, 8, 8) // Optional margins
        textView.layoutParams = params

        // Background
        val shape = GradientDrawable()
        shape.shape = GradientDrawable.OVAL

        when {
            isToday -> {
                shape.setColor(Color.parseColor("#7B2CBF")) // Purple
                textView.setTextColor(Color.WHITE)
            }
            isCompleted -> {
                shape.setColor(Color.parseColor("#FFD700")) // Gold
                textView.setTextColor(Color.BLACK)
            }
            isFuture -> {
                shape.setColor(Color.TRANSPARENT)
                textView.setTextColor(Color.parseColor("#CCCCCC")) // Greyed out text
            }
            else -> {
                // Past but not completed
                shape.setColor(Color.parseColor("#F0F0F0")) // Light Grey
                textView.setTextColor(Color.DKGRAY)
            }
        }
        
        textView.background = shape

        return textView
    }
}
