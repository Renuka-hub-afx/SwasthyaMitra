package com.example.swasthyamitra

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
// import com.example.swasthyamitra.ai.AIMoodRecommendationService
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.example.swasthyamitra.models.MoodData
import com.example.swasthyamitra.repository.MoodRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import android.view.ViewGroup
import java.text.SimpleDateFormat
import java.util.Locale
import android.graphics.Color
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.components.Legend

class MoodRecommendationActivity : AppCompatActivity() {

    private lateinit var moodData: MoodData
    private lateinit var repo: MoodRepository
    // private lateinit var aiService: AIMoodRecommendationService // Removed
    private lateinit var authHelper: FirebaseAuthHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood_recommendation)

        repo = MoodRepository()
        // aiService = AIMoodRecommendationService(this) // Removed
        authHelper = FirebaseAuthHelper(this)

        val moodJson = intent.getStringExtra("MOOD_DATA")
        if (moodJson != null) {
            moodData = Gson().fromJson(moodJson, MoodData::class.java)
            setupUI()
            fetchRecommendations()
            setupHistory()
        } else {
            // If no mood data passed, try to fetch the latest mood
            fetchLatestMood()
        }

        findViewById<ImageButton>(R.id.btn_back).setOnClickListener {
            finish()
        }
    }

    private fun fetchLatestMood() {
        val user = authHelper.getCurrentUser()
        if (user != null) {
            lifecycleScope.launch {
                findViewById<LinearLayout>(R.id.layout_loading).visibility = View.VISIBLE
                repo.getRecentMoods(user.uid, 1).onSuccess { moods ->
                    if (moods.isNotEmpty()) {
                        moodData = moods[0]
                        setupUI()
                        fetchRecommendations() // This might be redundant if we just want history, but good for "current state"
                        setupHistory()
                    } else {
                        Toast.makeText(this@MoodRecommendationActivity, "No recent mood history found.", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }.onFailure { e ->
                    Toast.makeText(this@MoodRecommendationActivity, "Failed to load: ${e.message}", Toast.LENGTH_LONG).show()
                    Log.e("MoodRec", "Load Error", e)
                    finish()
                }
            }
        } else {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun setupUI() {
        val emojiMap = mapOf(
            "happy" to "😄", "excited" to "😄",
            "calm" to "😌", "relaxed" to "😌",
            "tired" to "😴", "exhausted" to "😴",
            "sad" to "😢", "down" to "😢",
            "stressed" to "😫", "anxious" to "😫"
        )

        findViewById<TextView>(R.id.tv_current_mood_emoji).text = emojiMap[moodData.mood.lowercase()] ?: "😐"
        findViewById<TextView>(R.id.tv_current_mood_text).text = "Feeling ${moodData.mood}"
        findViewById<TextView>(R.id.tv_mood_suggestion).text = moodData.suggestion
        
        val dateFormat = SimpleDateFormat("EEEE, MMM dd, HH:mm", Locale.getDefault())
        findViewById<TextView>(R.id.tv_mood_date).text = dateFormat.format(java.util.Date(moodData.timestamp))
    }

    private fun fetchRecommendations() {
       // AI Recommendations have been moved to Workout Dashboard
       // Hiding the section
       findViewById<LinearLayout>(R.id.layout_loading).visibility = View.GONE
       findViewById<LinearLayout>(R.id.layout_recommendations).visibility = View.GONE
    }

    private fun setupHistory() {
        // 1. Initialize immediately with current mood (so it's never empty)
        setupPieChart(listOf(moodData))

        // 2. Fetch history to enrich the data
        lifecycleScope.launch {
            repo.getRecentMoods(moodData.userId)
                .onSuccess { fetchedMoods ->
                    val allMoods = (listOf(moodData) + fetchedMoods).distinctBy { it.timestamp }
                    Log.d("MoodRec", "Updating with history: ${allMoods.size} moods")
                    
                    // Update chart and list
                    setupPieChart(allMoods)
                    updateHistoryList(allMoods)
                }
                .onFailure { e ->
                    Log.e("MoodRec", "Failed to fetch history", e)
                    // Chart is already showing current mood, so just ensure list shows it too
                    updateHistoryList(listOf(moodData)) 
                }
        }
    }

    private fun updateHistoryList(moods: List<MoodData>) {
        val rv = findViewById<RecyclerView>(R.id.rv_mood_history)
        rv.layoutManager = LinearLayoutManager(this@MoodRecommendationActivity)
        
        while (rv.itemDecorationCount > 0) {
            rv.removeItemDecorationAt(0)
        }
        
        rv.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: android.graphics.Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                outRect.bottom = 16
            }
        })
        
        if (moods.isNotEmpty()) {
            rv.adapter = MoodHistoryAdapter(moods)
        }
    }

    private fun setupPieChart(moods: List<MoodData>) {
        val pieChart = findViewById<PieChart>(R.id.pieChart)
        
        pieChart.isDrawHoleEnabled = true
        pieChart.setUsePercentValues(true)
        pieChart.setEntryLabelTextSize(0f) // Hide text on chart slices (too cluttered)
        pieChart.setEntryLabelColor(Color.BLACK)
        pieChart.centerText = if (moods.isNotEmpty()) "${moods.size}\nEntries" else "No Data"
        pieChart.setCenterTextSize(16f)
        pieChart.description.isEnabled = false
        
        val legend = pieChart.legend
        legend.verticalAlignment = Legend.LegendVerticalAlignment.CENTER
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend.orientation = Legend.LegendOrientation.VERTICAL
        legend.setDrawInside(false)
        legend.isEnabled = true
        legend.textSize = 12f

        val entries = ArrayList<PieEntry>()
        val colors = ArrayList<Int>()
        
        // Define Mood Colors
        val moodColors = mapOf(
            "Happy" to Color.parseColor("#FFD700"),     // Gold
            "Excited" to Color.parseColor("#FFD700"),   // Gold
            "Calm" to Color.parseColor("#87CEEB"),      // SkyBlue
            "Relaxed" to Color.parseColor("#87CEEB"),   // SkyBlue
            "Tired" to Color.parseColor("#D3D3D3"),     // LightGray
            "Exhausted" to Color.parseColor("#A9A9A9"), // DarkGray
            "Sad" to Color.parseColor("#4682B4"),       // SteelBlue
            "Down" to Color.parseColor("#4682B4"),      // SteelBlue
            "Stressed" to Color.parseColor("#FF6347"),  // Tomato
            "Anxious" to Color.parseColor("#FF6347")    // Tomato
        )

        if (moods.isEmpty()) {
            // Empty State
            entries.add(PieEntry(1f, "No Data"))
            colors.add(Color.parseColor("#E0E0E0")) // Very Light Gray
        } else {
            // Calculate distribution
            val distribution = moods.groupingBy { 
                 // Capitalize for consistency
                 it.mood.replaceFirstChar { char -> if (char.isLowerCase()) char.titlecase(Locale.getDefault()) else char.toString() } 
            }.eachCount()
            
            distribution.forEach { (mood, count) ->
                entries.add(PieEntry(count.toFloat(), mood))
                colors.add(moodColors[mood] ?: ColorTemplate.JOYFUL_COLORS[0])
            }
        }

        val dataSet = PieDataSet(entries, "")
        dataSet.colors = colors
        dataSet.sliceSpace = 3f // Space between slices
        dataSet.selectionShift = 5f
        
        val data = PieData(dataSet)
        data.setDrawValues(moods.isNotEmpty()) // Hide values if empty
        data.setValueFormatter(PercentFormatter(pieChart))
        data.setValueTextSize(12f)
        data.setValueTextColor(Color.BLACK)
        
        pieChart.data = data
        pieChart.animateY(1400, com.github.mikephil.charting.animation.Easing.EaseInOutQuad)
        pieChart.invalidate()
    }

    class MoodHistoryAdapter(private val moods: List<MoodData>) : RecyclerView.Adapter<MoodHistoryAdapter.ViewHolder>() {
        
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val emoji: TextView = view.findViewById(R.id.tv_history_emoji)
            val moodName: TextView = view.findViewById(R.id.tv_history_mood)
            val date: TextView = view.findViewById(R.id.tv_history_date)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = android.view.LayoutInflater.from(parent.context)
                .inflate(R.layout.item_mood_history, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val mood = moods[position]
             val emojiMap = mapOf(
                "happy" to "😄", "calm" to "😌", "tired" to "😴", 
                "sad" to "😢", "stressed" to "😫"
            )
            
            holder.emoji.text = emojiMap[mood.mood.lowercase()] ?: "😐"
            holder.moodName.text = keyToLabel(mood.mood)
            
            val sdf = SimpleDateFormat("MMM dd, HH:mm", Locale.getDefault())
            holder.date.text = sdf.format(java.util.Date(mood.timestamp))
        }

        private fun keyToLabel(key: String): String {
             return key.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        }

        override fun getItemCount() = moods.size
    }
}
