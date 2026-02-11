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
            // Fetch last 60 days to have enough data for month view
            val logs = authHelper.getRecentWeightLogs(userId, 60)
            
            // Sort by timestamp for processing
            allLogs = logs.onEach { 
                // Ensure timestamp exists if missing (fallback to simple parsing if needed)
                if (it["timestamp"] == null) {
                    // Logic to parse date string if needed, or assume 0
                }
            }.sortedBy { (it["timestamp"] as? Long) ?: 0L }
            
            // Default to Weekly view
            updateChartMode(R.id.btnWeekly)
            updateList(allLogs.sortedByDescending { (it["timestamp"] as? Long) ?: 0L })
        }
    }
    
    private fun updateChartMode(checkedId: Int) {
        val days = if (checkedId == R.id.btnWeekly) 7 else 30
        val filteredLogs = allLogs.takeLast(days) // Assuming allLogs is sorted ascending
        updateChart(filteredLogs)
        
        // Update Button Styles
        val btnWeekly = findViewById<MaterialButton>(R.id.btnWeekly)
        val btnMonthly = findViewById<MaterialButton>(R.id.btnMonthly)
        
        if (checkedId == R.id.btnWeekly) {
            btnWeekly.setTextColor(Color.parseColor("#7B2CBF"))
            btnMonthly.setTextColor(Color.parseColor("#757575"))
        } else {
            btnWeekly.setTextColor(Color.parseColor("#757575"))
            btnMonthly.setTextColor(Color.parseColor("#7B2CBF"))
        }
    }

    private fun updateChart(logs: List<Map<String, Any>>) {
        val entries = ArrayList<Entry>()
        val labels = ArrayList<String>()
        val dateFormat = SimpleDateFormat("MM/dd", Locale.getDefault())

        logs.forEachIndexed { index, log ->
            val weight = (log["weight"] as? Number)?.toFloat() ?: return@forEachIndexed
            entries.add(Entry(index.toFloat(), weight))
            
            val timestamp = (log["timestamp"] as? Long) ?: 0L
            if (timestamp > 0) {
                labels.add(dateFormat.format(Date(timestamp)))
            } else {
                labels.add((log["date"] as? String)?.takeLast(5) ?: "")
            }
        }

        if (entries.isEmpty()) {
            chart.clear()
            return 
        }

        val dataSet = LineDataSet(entries, "Weight")
        dataSet.color = Color.parseColor("#7B2CBF") // Purple Line
        dataSet.lineWidth = 3f
        dataSet.mode = LineDataSet.Mode.CUBIC_BEZIER // Smooth curves
        
        dataSet.setDrawCircles(true)
        dataSet.setCircleColor(Color.parseColor("#7B2CBF"))
        dataSet.circleRadius = 5f
        dataSet.setDrawCircleHole(true)
        dataSet.circleHoleColor = Color.WHITE
        
        dataSet.setDrawValues(false)
        dataSet.setDrawFilled(true)
        // Ideally set a gradient drawable here, for now solid alpha
        dataSet.fillColor = Color.parseColor("#7B2CBF")
        dataSet.fillAlpha = 50

        chart.xAxis.valueFormatter = IndexAxisValueFormatter(labels)
        chart.xAxis.granularity = 1f
        chart.xAxis.labelCount = if (logs.size > 7) 5 else logs.size 
        
        chart.data = LineData(dataSet)
        chart.animateY(1000)
        chart.invalidate()
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
