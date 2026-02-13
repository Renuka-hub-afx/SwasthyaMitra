package com.example.swasthyamitra

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.android.material.chip.ChipGroup
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class DetailedReportActivity : AppCompatActivity() {

    private lateinit var authHelper: FirebaseAuthHelper
    private var userId: String = ""
    
    // UI
    private lateinit var chipGroup: ChipGroup
    private lateinit var tvCurrentWeight: TextView
    private lateinit var tvConsistencyScore: TextView
    private lateinit var tvStreak: TextView
    private lateinit var chart: LineChart
    private lateinit var tvInsight: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnShare: View
    
    // Data
    private var daysToLoad = 7
    private var fitnessData: FitnessData? = null
    private var weightLogs: List<Map<String, Any>> = emptyList()
    private var walkingSessions: List<Map<String, Any>> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_report)

        authHelper = FirebaseAuthHelper(this)
        userId = authHelper.getCurrentUser()?.uid ?: ""

        setupViews()
        loadData()
    }

    private fun setupViews() {
        chipGroup = findViewById(R.id.dateRangeChipGroup)
        tvCurrentWeight = findViewById(R.id.tvCurrentWeight)
        tvConsistencyScore = findViewById(R.id.tvConsistencyScore)
        tvStreak = findViewById(R.id.tvStreak)
        chart = findViewById(R.id.reportWeightChart)
        tvInsight = findViewById(R.id.tvInsight)
        recyclerView = findViewById(R.id.reportHistoryRecyclerView)
        btnShare = findViewById(R.id.btnShareReport)

        recyclerView.layoutManager = LinearLayoutManager(this)

        findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            finish()
        }

        chipGroup.setOnCheckedChangeListener { _, checkedId ->
            daysToLoad = if (checkedId == R.id.chip30Days) 30 else 7
            loadData()
        }
        
        btnShare.setOnClickListener {
            shareReport()
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
        chart.legend.isEnabled = false
    }

    private fun loadData() {
        if (userId.isEmpty()) return

        // 1. Load Fitness Data (Realtime DB)
        val db = FirebaseDatabase.getInstance("https://swasthyamitra-ded44-default-rtdb.asia-southeast1.firebasedatabase.app").reference
        db.child("users").child(userId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                fitnessData = snapshot.getValue(FitnessData::class.java)
                calculateStats()
            }
            override fun onCancelled(error: DatabaseError) {}
        })

        // 2. Load Weight Logs & Walking Sessions (Firestore)
        lifecycleScope.launch {
            weightLogs = authHelper.getRecentWeightLogs(userId, daysToLoad)
            walkingSessions = authHelper.getRecentWalkingSessions(userId, daysToLoad)
            
            updateWeightUI()
            refreshCombinedHistory()
        }
    }

    private fun calculateStats() {
        val data = fitnessData ?: return
        
        // Streak
        tvStreak.text = "${data.streak} \uD83D\uDD25"
        
        // Consistency Calculation
        // Count active days in the last N days
        val activeDays = countActiveDays(data.workoutHistory, daysToLoad)
        val consistency = (activeDays.toFloat() / daysToLoad.toFloat()) * 100
        tvConsistencyScore.text = "${consistency.toInt()}%"
        
        refreshCombinedHistory()
    }

    private fun updateWeightUI() {
        if (weightLogs.isEmpty()) {
            tvCurrentWeight.text = "-- kg"
            chart.clear()
            tvInsight.text = "No weight data logged yet. Start logging to see insights!"
            return
        }

        // 1. Current Weight
        val latestLog = weightLogs.maxByOrNull { (it["timestamp"] as? Long) ?: 0L }
        val latestWeight = latestLog?.get("weight").toString().toDoubleOrNull() ?: 0.0
        tvCurrentWeight.text = "$latestWeight kg"
        
        // 2. Chart
        val sortedLogs = weightLogs.sortedBy { (it["timestamp"] as? Long) ?: 0L }
        val entries = ArrayList<Entry>()
        sortedLogs.forEachIndexed { index, log ->
             val w = log["weight"].toString().toDoubleOrNull()?.toFloat() ?: return@forEachIndexed
             entries.add(Entry(index.toFloat(), w))
        }
        
        val dataSet = LineDataSet(entries, "Weight")
        dataSet.color = Color.parseColor("#9C27B0")
        dataSet.valueTextColor = Color.BLACK
        dataSet.lineWidth = 2f
        dataSet.circleRadius = 4f
        dataSet.setCircleColor(Color.parseColor("#9C27B0"))
        
        chart.data = LineData(dataSet)
        chart.invalidate()
        
        // 3. Insight
        val firstWeight = sortedLogs.first()["weight"].toString().toDoubleOrNull() ?: 0.0
        val diff = latestWeight - firstWeight
        tvInsight.text = when {
            diff < -0.5 -> "Great job! You've lost ${String.format("%.1f", kotlin.math.abs(diff))} kg in the last period. Keep it up!"
            diff > 0.5 -> "You've gained ${String.format("%.1f", diff)} kg. Check your calorie intake or muscle gain goals."
            else -> "Your weight has remained stable. Consistency is key!"
        }
        
        // Add weight logs to history list
        val weightItems = sortedLogs.map { log ->
            HistoryItem(
                date = (log["timestamp"] as? Long) ?: 0L,
                title = "Weight Log",
                details = "${log["weight"]} kg",
                type = "WEIGHT"
            )
        }
        
        // Refresh list
        refreshCombinedHistory()
    }

    private fun refreshCombinedHistory() {
        val historyItems = mutableListOf<HistoryItem>()
        
        // 1. Add workouts
        fitnessData?.workoutHistory?.values?.forEach { session ->
            if (isWithinRange(session.timestamp, daysToLoad)) {
                historyItems.add(HistoryItem(
                    date = session.timestamp,
                    title = "${session.category.replaceFirstChar { it.uppercase() }} Workout",
                    details = "${session.duration} min • ${if(session.caloriesBurned>0) session.caloriesBurned else session.duration*6} kcal",
                    type = "WORKOUT"
                ))
            }
        }

        // 2. Add Weight Logs
        weightLogs.forEach { log ->
            val ts = (log["timestamp"] as? Long) ?: 0L
            if (isWithinRange(ts, daysToLoad)) {
                historyItems.add(HistoryItem(
                    date = ts,
                    title = "Weight Log",
                    details = "${log["weight"]} kg",
                    type = "WEIGHT"
                ) )
            }
        }

        // 3. Add Walking Sessions
        walkingSessions.forEach { session ->
            val ts = (session["startTime"] as? Long) ?: 0L
            if (isWithinRange(ts, daysToLoad)) {
                val distanceM = (session["totalDistanceMeters"] as? Number)?.toDouble() ?: 0.0
                val steps = (session["totalSteps"] as? Number)?.toInt() ?: 0
                val km = String.format("%.2f", distanceM / 1000.0)
                
                historyItems.add(HistoryItem(
                    date = ts,
                    title = "GPS Walk \uD83D\uDCCD",
                    details = "$km km • $steps steps",
                    type = "WALK"
                ))
            }
        }

        updateList(historyItems)
    }
    
    private fun updateList(items: List<HistoryItem>) {
        val sorted = items.sortedByDescending { it.date }
        recyclerView.adapter = ReportAdapter(sorted)
    }

    private fun countActiveDays(history: Map<String, WorkoutSession>, days: Int): Int {
        val cutoff = System.currentTimeMillis() - (days * 24 * 60 * 60 * 1000L)
        val uniqueDays = mutableSetOf<String>()
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        
        history.values.forEach { session ->
             val time = if (session.timestamp > 0) session.timestamp 
                        else sdf.parse(session.date)?.time ?: 0L
                        
             if (time > cutoff) {
                 uniqueDays.add(session.date)
             }
        }
        return uniqueDays.size
    }

    private fun isWithinRange(timestamp: Long, days: Int): Boolean {
        if (timestamp == 0L) return false // Legacy data without timestamp might be tricky? defaults to no
        val cutoff = System.currentTimeMillis() - (days * 24 * 60 * 60 * 1000L)
        return timestamp > cutoff
    }
    
    // Share Functionality
    private fun shareReport() {
        // Screenshot the ScrollView content
        val scrollView = findViewById<View>(R.id.dateRangeChipGroup).parent.parent as View // ScrollView
        val bitmap = getScreenshotFromView(scrollView)

        if (bitmap != null) {
            saveAndShare(bitmap)
        } else {
            Toast.makeText(this, "Failed to capture report", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun getScreenshotFromView(view: View): Bitmap? {
        // Find the direct child (LinearLayout) to capture full height
        val child = (view as ViewGroup).getChildAt(0)
        val bitmap = Bitmap.createBitmap(child.width, child.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        child.draw(canvas)
        return bitmap
    }
    
    private fun saveAndShare(bitmap: Bitmap) {
        try {
            val file = File(externalCacheDir, "health_report.png")
            val fOut = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut)
            fOut.flush()
            fOut.close()
            
            val uri = FileProvider.getUriForFile(this, "$packageName.provider", file)
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "image/png"
            intent.putExtra(Intent.EXTRA_STREAM, uri)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            startActivity(Intent.createChooser(intent, "Share Report via"))
            
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Error sharing: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    data class HistoryItem(
        val date: Long,
        val title: String,
        val details: String,
        val type: String
    )

    inner class ReportAdapter(private val items: List<HistoryItem>) : RecyclerView.Adapter<ReportAdapter.ViewHolder>() {
        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val title: TextView = view.findViewById(android.R.id.text1)
            val subtitle: TextView = view.findViewById(android.R.id.text2)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_2, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = items[position]
            val dateStr = SimpleDateFormat("MMM dd", Locale.US).format(Date(item.date))
            holder.title.text = "$dateStr - ${item.title}"
            holder.subtitle.text = item.details
        }

        override fun getItemCount() = items.size
    }
}
