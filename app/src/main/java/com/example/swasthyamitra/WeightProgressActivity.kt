package com.example.swasthyamitra

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class WeightProgressActivity : AppCompatActivity() {

    private lateinit var authHelper: FirebaseAuthHelper
    private lateinit var chart: LineChart
    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    private lateinit var toggleGroup: MaterialButtonToggleGroup
    private var userId: String = ""
    private var allLogs: List<Map<String, Any>> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight_progress)

        authHelper = FirebaseAuthHelper(this)
        userId = authHelper.getCurrentUser()?.uid ?: ""

        setupViews()
        loadData()
    }

    private fun setupViews() {
        chart = findViewById(R.id.weightChart)
        recyclerView = findViewById(R.id.weightHistoryRecyclerView)
        fab = findViewById(R.id.addWeightFab)
        toggleGroup = findViewById(R.id.toggleGroup)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        recyclerView.layoutManager = LinearLayoutManager(this)

        fab.setOnClickListener {
            showAddWeightDialog()
        }
        
        setupChart()
        
        toggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                updateChartMode(checkedId)
            }
        }
    }
    
    private fun setupChart() {
        chart.description.isEnabled = false
        chart.setTouchEnabled(true)
        chart.isDragEnabled = true
        chart.setScaleEnabled(false)
        chart.setPinchZoom(false)
        chart.setDrawGridBackground(false)
        
        chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        chart.xAxis.setDrawGridLines(false)
        chart.xAxis.textColor = Color.parseColor("#757575")
        
        chart.axisLeft.textColor = Color.parseColor("#757575")
        chart.axisRight.isEnabled = false
        
        chart.legend.isEnabled = false
    }

    private fun loadData() {
        lifecycleScope.launch {
            // Use the Helper to get the Full Trend (Actual + Projected)
            val helper = com.example.swasthyamitra.utils.WeightProjectionHelper(authHelper)
            
            // Determine days based on toggle (or just fetch max)
            // Ideally we fetch based on current view, but let's fetch enough for Monthly
            val trendPoints = helper.getProjectedWeightTrend(userId, 60)
            
            // For list view, we still want the raw logs or maybe the trend points?
            // User request: "right and actual progress". List usually shows logs.
            // Let's keep list as "Actual Logs" for clarity.
            val rawLogs = authHelper.getRecentWeightLogs(userId, 60)
            allLogs = rawLogs.sortedBy { (it["timestamp"] as? Long) ?: 0L }
            
            // Store trend points for chart
            currentTrendData = trendPoints
            
            // Default to current view
            updateChartMode(toggleGroup.checkedButtonId)
            
            // Update List with actual logs only
            updateList(allLogs.sortedByDescending { (it["timestamp"] as? Long) ?: 0L })
        }
    }
    
    // Store trend data at class level
    private var currentTrendData: List<com.example.swasthyamitra.utils.WeightPoint> = emptyList()

    private fun updateChartMode(checkedId: Int) {
        val days = when (checkedId) {
            R.id.btnWeekly -> 7
            R.id.btnBiWeekly -> 15
            else -> 30
        }
        
        val cutoff = System.currentTimeMillis() - (days * 24 * 60 * 60 * 1000L)
        
        // Filter the TREND data
        val filteredTrend = currentTrendData.filter { it.timestamp >= cutoff }
        
        // Set Custom Marker
        val markerView = com.example.swasthyamitra.utils.CustomMarkerView(this, R.layout.custom_marker_view, filteredTrend)
        chart.marker = markerView

        updateChart(filteredTrend)
        
        // Update Button Styles
        val btnWeekly = findViewById<MaterialButton>(R.id.btnWeekly)
        val btnBiWeekly = findViewById<MaterialButton>(R.id.btnBiWeekly)
        val btnMonthly = findViewById<MaterialButton>(R.id.btnMonthly)
        
        val activeColor = Color.parseColor("#7B2CBF")
        val inactiveColor = Color.parseColor("#757575")

        btnWeekly.setTextColor(if (checkedId == R.id.btnWeekly) activeColor else inactiveColor)
        btnBiWeekly.setTextColor(if (checkedId == R.id.btnBiWeekly) activeColor else inactiveColor)
        btnMonthly.setTextColor(if (checkedId == R.id.btnMonthly) activeColor else inactiveColor)
    }

    private fun updateChart(points: List<com.example.swasthyamitra.utils.WeightPoint>) {
        if (points.isEmpty()) {
            chart.clear()
            chart.setNoDataText("No data available.")
            chart.invalidate()
            return 
        }

        val entriesTrend = ArrayList<Entry>()
        val entriesActual = ArrayList<Entry>()
        val actualColors = ArrayList<Int>() // Colors for actual points
        
        val labels = ArrayList<String>()
        val dateFormat = SimpleDateFormat("MM/dd", Locale.getDefault())

        // Create entries
        points.forEachIndexed { index, point ->
            // Add to Continuous Trend Line
            entriesTrend.add(Entry(index.toFloat(), point.weight))
            
            // Add to Actual Points (Scatter) if not projected
            if (!point.isProjected) {
                entriesActual.add(Entry(index.toFloat(), point.weight))
                actualColors.add(getMoodColor(point.mood))
            }
            
            labels.add(dateFormat.format(Date(point.timestamp)))
        }

        // DataSet 1: The Continuous Trend Line (Projected + Actual)
        val setTrend = LineDataSet(entriesTrend, "Projected Trend")
        setTrend.color = Color.parseColor("#E1BEE7") // Lighter Purple
        setTrend.lineWidth = 2f
        setTrend.setDrawCircles(false) // No circles on the trend line (cleaner)
        setTrend.setDrawValues(false)
        setTrend.mode = LineDataSet.Mode.CUBIC_BEZIER
        setTrend.enableDashedLine(10f, 5f, 0f) // Optional: Dash it? Or solid? User said "Direct progress". Let's stick to solid light line.
        setTrend.disableDashedLine() 
        
        // DataSet 2: Actual Measured Points
        val setActual = LineDataSet(entriesActual, "Actual Weight")
        setActual.color = Color.TRANSPARENT // Invisible line
        setActual.setDrawCircles(true)
        setActual.circleRadius = 6f
        
        // Use individual colors for circles
        setActual.circleColors = actualColors
        
        setActual.setDrawCircleHole(true)
        setActual.circleHoleColor = Color.WHITE
        setActual.setDrawValues(false) // Hide values on chart to reduce clutter, rely on marker
        setActual.mode = LineDataSet.Mode.LINEAR // Doesn't matter as line is invisible

        val data = LineData(setTrend, setActual)
        
        chart.xAxis.valueFormatter = IndexAxisValueFormatter(labels)
        chart.xAxis.granularity = 1f
        chart.xAxis.labelCount = if (labels.size > 7) 5 else labels.size
        
        chart.data = data
        chart.animateY(1000)
        chart.invalidate()
    }
    
    private fun getMoodColor(mood: String): Int {
        return when (mood.lowercase(Locale.getDefault())) {
            // Positive - Green
            "happy", "energetic", "calm", "focused", "grateful", "loved", "confident", "proud", "excited" -> Color.parseColor("#4CAF50")
            // Negative - Red
            "sad", "anxious", "tired", "stressed", "angry", "lonely", "bored", "depressed", "overwhelmed" -> Color.parseColor("#F44336")
            // Neutral/Data Missing - Default Purple
            else -> Color.parseColor("#7B2CBF")
        }
    }

    private fun updateList(logs: List<Map<String, Any>>) {
        recyclerView.adapter = WeightAdapter(logs)
    }

    private fun showAddWeightDialog() {
        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
        builder.setTitle("Log Weight")

        val input = android.widget.EditText(this)
        input.inputType = android.text.InputType.TYPE_CLASS_NUMBER or android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL
        input.hint = "Enter weight in kg"
        builder.setView(input)

        builder.setPositiveButton("Log") { _, _ ->
            val weightText = input.text.toString()
            if (weightText.isNotEmpty()) {
                val weight = weightText.toDoubleOrNull()
                if (weight != null) {
                    saveWeight(weight)
                }
            }
        }
        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }

        builder.show()
    }

    private fun saveWeight(weight: Double) {
        lifecycleScope.launch {
            authHelper.logWeight(userId, weight).onSuccess {
                Toast.makeText(this@WeightProgressActivity, "Logged!", Toast.LENGTH_SHORT).show()
                loadData()
            }.onFailure {
                Toast.makeText(this@WeightProgressActivity, "Failed: ${it.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    inner class WeightAdapter(private val logs: List<Map<String, Any>>) : RecyclerView.Adapter<WeightAdapter.ViewHolder>() {
        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvDate: TextView = view.findViewById(android.R.id.text1)
            val tvWeight: TextView = view.findViewById(android.R.id.text2)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_2, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val log = logs[position]
            val weight = log["weight"]
            val date = log["date"] as? String ?: ""
            
            holder.tvDate.text = date
            holder.tvDate.setTextColor(Color.parseColor("#212121"))
            holder.tvWeight.text = "$weight kg"
            holder.tvWeight.setTextColor(Color.parseColor("#757575"))
        }

        override fun getItemCount() = logs.size
    }
}
