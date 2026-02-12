package com.example.swasthyamitra.utils

import android.content.Context
import android.widget.TextView
import android.view.View
import com.example.swasthyamitra.R
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CustomMarkerView(context: Context, layoutResource: Int, private val points: List<WeightPoint>) : MarkerView(context, layoutResource) {

    private val tvDate: TextView = findViewById(R.id.tvDate)
    private val tvContent: TextView = findViewById(R.id.tvContent)
    private val tvDetails: TextView = findViewById(R.id.tvDetails)
    private val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())

    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        if (e == null) return

        val index = e.x.toInt()
        if (index >= 0 && index < points.size) {
            val point = points[index]
            
            tvDate.text = dateFormat.format(Date(point.timestamp))
            tvContent.text = "${point.weight} kg"
            
            val moodText = if (point.mood.isNotEmpty()) "Mood: ${point.mood}" else ""
            val foodText = if (point.caloriesIn > 0) "In: ${point.caloriesIn} kcal" else ""
            val burnText = if (point.caloriesOut > 0) "Out: ${point.caloriesOut} kcal" else ""
            
            val details = listOf(moodText, foodText, burnText).filter { it.isNotEmpty() }.joinToString("\n")
            
            if (details.isNotEmpty()) {
                tvDetails.text = details
                tvDetails.visibility = View.VISIBLE
            } else {
                tvDetails.visibility = View.GONE
            }
        }

        super.refreshContent(e, highlight)
    }

    override fun getOffset(): MPPointF {
        return MPPointF(-(width / 2).toFloat(), -height.toFloat())
    }
}
