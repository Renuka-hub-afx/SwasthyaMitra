package com.example.swasthyamitra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class WeightProgressActivity : AppCompatActivity() {

    private lateinit var authHelper: FirebaseAuthHelper
    private lateinit var chart: LineChart
    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    private var userId: String = ""

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

        findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            finish()
        }

        recyclerView.layoutManager = LinearLayoutManager(this)

        fab.setOnClickListener {
            showAddWeightDialog()
        }
        
        setupChart()
    }
    
    private fun setupChart() {
        chart.description.isEnabled = false
        chart.setTouchEnabled(true)
        chart.isDragEnabled = true
        chart.setScaleEnabled(true)
        chart.setPinchZoom(true)
        chart.setDrawGridBackground(false)
    }

    private fun loadData() {
        lifecycleScope.launch {
            // Fetch last 30 days
            val logs = authHelper.getRecentWeightLogs(userId, 30)
            
            // Sort by timestamp
            val sortedLogs = logs.sortedBy { (it["timestamp"] as? Long) ?: 0L }
            
            updateChart(sortedLogs)
            updateList(sortedLogs.sortedByDescending { (it["timestamp"] as? Long) ?: 0L })
        }
    }

    private fun updateChart(logs: List<Map<String, Any>>) {
        val entries = ArrayList<Entry>()
        val labels = ArrayList<String>()
        val dateFormat = SimpleDateFormat("MM/dd", Locale.getDefault())

        logs.forEachIndexed { index, log ->
            val weight = (log["weight"] as? Number)?.toFloat() ?: return@forEachIndexed
            entries.add(Entry(index.toFloat(), weight))
            
            // Create label
            val timestamp = (log["timestamp"] as? Long) ?: 0L
            if (timestamp > 0) {
                labels.add(dateFormat.format(Date(timestamp)))
            } else {
                labels.add((log["date"] as? String)?.substring(5) ?: "") // try to show MM-DD
            }
        }

        if (entries.isEmpty()) return

        val dataSet = LineDataSet(entries, "Weight (kg)")
        dataSet.color = androidx.core.content.ContextCompat.getColor(this, android.R.color.holo_purple)
        dataSet.valueTextColor = androidx.core.content.ContextCompat.getColor(this, android.R.color.black)
        dataSet.lineWidth = 2f
        dataSet.circleRadius = 4f
        dataSet.setCircleColor(androidx.core.content.ContextCompat.getColor(this, android.R.color.holo_purple))
        
        // Fix X-Axis
        chart.xAxis.valueFormatter = com.github.mikephil.charting.formatter.IndexAxisValueFormatter(labels)
        chart.xAxis.granularity = 1f
        chart.xAxis.position = com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM

        chart.data = LineData(dataSet)
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
            holder.tvWeight.text = "$weight kg"
        }

        override fun getItemCount() = logs.size
    }
}
