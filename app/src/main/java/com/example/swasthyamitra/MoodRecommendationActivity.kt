package com.example.swasthyamitra

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swasthyamitra.ai.AIMoodRecommendationService
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
    private lateinit var aiService: AIMoodRecommendationService
    private lateinit var authHelper: FirebaseAuthHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood_recommendation)

        repo       = MoodRepository()
        aiService  = AIMoodRecommendationService(this)
        authHelper = FirebaseAuthHelper(this)

        val moodJson = intent.getStringExtra("MOOD_DATA")
        if (moodJson != null) {
            moodData = Gson().fromJson(moodJson, MoodData::class.java)
            setupUI()
            fetchMindfulnessTip()
            setupHistory()
        } else {
            fetchLatestMood()
        }

        findViewById<ImageButton>(R.id.btn_back).setOnClickListener {
            finish()
        }
    }

    // ── Fetch latest mood when none is passed via Intent ─────────────────────
    private fun fetchLatestMood() {
        val user = authHelper.getCurrentUser()
        if (user != null) {
            lifecycleScope.launch {
                findViewById<LinearLayout>(R.id.layout_loading).visibility = View.VISIBLE
                repo.getRecentMoods(user.uid, 1).onSuccess { moods ->
                    findViewById<LinearLayout>(R.id.layout_loading).visibility = View.GONE
                    if (moods.isNotEmpty()) {
                        moodData = moods[0]
                        setupUI()
                        fetchMindfulnessTip()
                        setupHistory()
                    } else {
                        Toast.makeText(this@MoodRecommendationActivity, "No recent mood history found.", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }.onFailure { e ->
                    findViewById<LinearLayout>(R.id.layout_loading).visibility = View.GONE
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

    // ── Populate the current mood card ────────────────────────────────────────
    private fun setupUI() {
        val emojiMap = mapOf(
            "happy"    to "😄", "excited"  to "😄",
            "calm"     to "😌", "relaxed"  to "😌",
            "tired"    to "😴", "exhausted" to "😴",
            "sad"      to "😢", "down"     to "😢",
            "stressed" to "😫", "anxious"  to "😫"
        )

        findViewById<TextView>(R.id.tv_current_mood_emoji).text =
            emojiMap[moodData.mood.lowercase()] ?: "😐"
        findViewById<TextView>(R.id.tv_current_mood_text).text =
            "Feeling ${moodData.mood}"
        findViewById<TextView>(R.id.tv_mood_suggestion).text =
            moodData.suggestion

        val dateFormat = SimpleDateFormat("EEEE, MMM dd, HH:mm", Locale.US)
        findViewById<TextView>(R.id.tv_mood_date).text =
            dateFormat.format(java.util.Date(moodData.timestamp))
    }

    // ── Fetch AI mindfulness tip and display it ───────────────────────────────
    private fun fetchMindfulnessTip() {
        val tipLoadingView = findViewById<LinearLayout>(R.id.layout_tip_loading)
        val tipCard        = findViewById<CardView>(R.id.card_mindfulness_tip)
        val tvTip          = findViewById<TextView>(R.id.tv_tip_rec)

        // Show the loading spinner
        tipLoadingView.visibility = View.VISIBLE
        tipCard.visibility = View.GONE

        lifecycleScope.launch {
            // Get the user profile to personalise the tip with the user's name
            val userId  = authHelper.getCurrentUser()?.uid ?: run {
                tipLoadingView.visibility = View.GONE
                return@launch
            }
            val profile = authHelper.getUserData(userId).getOrNull() ?: emptyMap<String, Any>()

            aiService.getMoodBasedRecommendations(moodData, profile)
                .onSuccess { result ->
                    tipLoadingView.visibility = View.GONE
                    tvTip.text = result.mindfulnessTip
                    tipCard.visibility = View.VISIBLE
                    // Gentle fade-in animation
                    tipCard.alpha = 0f
                    tipCard.animate().alpha(1f).setDuration(400).start()
                }
                .onFailure { e ->
                    Log.e("MoodRec", "AI tip failed", e)
                    tipLoadingView.visibility = View.GONE
                    // Show a sensible fallback tip without crashing
                    tvTip.text = getFallbackTip(moodData.mood)
                    tipCard.visibility = View.VISIBLE
                    tipCard.alpha = 0f
                    tipCard.animate().alpha(1f).setDuration(400).start()
                }
        }
    }

    // ── Offline / fallback mindfulness tips ──────────────────────────────────
    private fun getFallbackTip(mood: String): String {
        return when (mood.lowercase()) {
            "stressed", "anxious" ->
                "Try box breathing: inhale for 4 counts, hold for 4, exhale for 4. Repeat 3 times. 🌿"
            "sad", "down" ->
                "Write down 3 things you're grateful for today — even small ones count. 💜"
            "tired", "exhausted" ->
                "Give yourself permission to rest. Try a slow 4-7-8 breath to reset your energy. 🌙"
            "happy", "excited" ->
                "Savour this moment! Notice 3 things around you that make you smile right now. ✨"
            "calm", "relaxed" ->
                "Anchor this calm. Take 1 minute to observe your surroundings with full attention. 🍃"
            else ->
                "Take a few slow, deep breaths and check in with how your body feels right now. 🌸"
        }
    }

    // ── Load mood history chart + list ───────────────────────────────────────
    private fun setupHistory() {
        // Show current mood immediately so the chart is never blank
        setupPieChart(listOf(moodData))

        lifecycleScope.launch {
            repo.getRecentMoods(moodData.userId)
                .onSuccess { fetchedMoods ->
                    val allMoods = (listOf(moodData) + fetchedMoods).distinctBy { it.timestamp }
                    Log.d("MoodRec", "Updating with history: ${allMoods.size} moods")
                    setupPieChart(allMoods)
                    updateHistoryList(allMoods)
                }
                .onFailure { e ->
                    Log.e("MoodRec", "Failed to fetch history", e)
                    updateHistoryList(listOf(moodData))
                }
        }
    }

    // ── RecyclerView helper ───────────────────────────────────────────────────
    private fun updateHistoryList(moods: List<MoodData>) {
        val rv = findViewById<RecyclerView>(R.id.rv_mood_history)
        rv.layoutManager = LinearLayoutManager(this@MoodRecommendationActivity)

        while (rv.itemDecorationCount > 0) rv.removeItemDecorationAt(0)

        rv.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: android.graphics.Rect, view: View,
                parent: RecyclerView, state: RecyclerView.State
            ) { outRect.bottom = 16 }
        })

        if (moods.isNotEmpty()) rv.adapter = MoodHistoryAdapter(moods)
    }

    // ── Pie chart ─────────────────────────────────────────────────────────────
    private fun setupPieChart(moods: List<MoodData>) {
        val pieChart = findViewById<PieChart>(R.id.pieChart)

        pieChart.isDrawHoleEnabled  = true
        pieChart.setUsePercentValues(true)
        pieChart.setEntryLabelTextSize(0f)
        pieChart.setEntryLabelColor(Color.BLACK)
        pieChart.centerText       = if (moods.isNotEmpty()) "${moods.size}\nEntries" else "No Data"
        pieChart.setCenterTextSize(16f)
        pieChart.description.isEnabled = false

        val legend = pieChart.legend
        legend.verticalAlignment   = Legend.LegendVerticalAlignment.CENTER
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend.orientation         = Legend.LegendOrientation.VERTICAL
        legend.setDrawInside(false)
        legend.isEnabled  = true
        legend.textSize   = 12f

        val entries = ArrayList<PieEntry>()
        val colors  = ArrayList<Int>()

        val moodColors = mapOf(
            "Happy"    to Color.parseColor("#FFD700"),
            "Excited"  to Color.parseColor("#FFD700"),
            "Calm"     to Color.parseColor("#87CEEB"),
            "Relaxed"  to Color.parseColor("#87CEEB"),
            "Tired"    to Color.parseColor("#D3D3D3"),
            "Exhausted" to Color.parseColor("#A9A9A9"),
            "Sad"      to Color.parseColor("#4682B4"),
            "Down"     to Color.parseColor("#4682B4"),
            "Stressed" to Color.parseColor("#FF6347"),
            "Anxious"  to Color.parseColor("#FF6347")
        )

        if (moods.isEmpty()) {
            entries.add(PieEntry(1f, "No Data"))
            colors.add(Color.parseColor("#E0E0E0"))
        } else {
            val distribution = moods.groupingBy {
                it.mood.replaceFirstChar { c ->
                    if (c.isLowerCase()) c.titlecase(Locale.US) else c.toString()
                }
            }.eachCount()

            distribution.forEach { (mood, count) ->
                entries.add(PieEntry(count.toFloat(), mood))
                colors.add(moodColors[mood] ?: ColorTemplate.JOYFUL_COLORS[0])
            }
        }

        val dataSet = PieDataSet(entries, "")
        dataSet.colors        = colors
        dataSet.sliceSpace    = 3f
        dataSet.selectionShift = 5f

        val data = PieData(dataSet)
        data.setDrawValues(moods.isNotEmpty())
        data.setValueFormatter(PercentFormatter(pieChart))
        data.setValueTextSize(12f)
        data.setValueTextColor(Color.BLACK)

        pieChart.data = data
        pieChart.animateY(1400, com.github.mikephil.charting.animation.Easing.EaseInOutQuad)
        pieChart.invalidate()
    }

    // ── Mood History RecyclerView Adapter ────────────────────────────────────
    class MoodHistoryAdapter(private val moods: List<MoodData>) :
        RecyclerView.Adapter<MoodHistoryAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val emoji:    TextView = view.findViewById(R.id.tv_history_emoji)
            val moodName: TextView = view.findViewById(R.id.tv_history_mood)
            val date:     TextView = view.findViewById(R.id.tv_history_date)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = android.view.LayoutInflater.from(parent.context)
                .inflate(R.layout.item_mood_history, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val mood = moods[position]
            val emojiMap = mapOf(
                "happy"    to "😄", "calm"    to "😌",
                "tired"    to "😴", "sad"     to "😢",
                "stressed" to "😫", "excited" to "😄",
                "relaxed"  to "😌", "exhausted" to "😴",
                "down"     to "😢", "anxious" to "😫"
            )

            holder.emoji.text    = emojiMap[mood.mood.lowercase()] ?: "😐"
            holder.moodName.text = mood.mood.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.US) else it.toString()
            }

            val sdf = SimpleDateFormat("MMM dd, HH:mm", Locale.US)
            holder.date.text = sdf.format(java.util.Date(mood.timestamp))
        }

        override fun getItemCount() = moods.size
    }
}
