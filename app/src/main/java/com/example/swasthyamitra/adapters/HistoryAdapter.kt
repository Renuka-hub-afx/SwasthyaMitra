package com.example.swasthyamitra.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.swasthyamitra.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

sealed class HistoryItem {
    abstract val timestamp: Long
    abstract val calories: Int

    data class HeaderItem(
        val dateString: String,
        override val timestamp: Long,
        override val calories: Int = 0
    ) : HistoryItem()

    data class FoodItem(
        val name: String,
        val mealType: String,
        override val calories: Int,
        val protein: Double,
        val carbs: Double,
        val fat: Double,
        override val timestamp: Long
    ) : HistoryItem()

    data class ExerciseItem(
        val name: String,
        val durationMinutes: Int,
        override val calories: Int,
        val type: String, // "AI" or "Regular"
        override val timestamp: Long
    ) : HistoryItem()
}

class HistoryAdapter(
    private val items: List<HistoryItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_FOOD = 1
        private const val TYPE_EXERCISE = 2
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is HistoryItem.HeaderItem -> TYPE_HEADER
            is HistoryItem.FoodItem -> TYPE_FOOD
            is HistoryItem.ExerciseItem -> TYPE_EXERCISE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history_header, parent, false)
                HeaderViewHolder(view)
            }
            TYPE_FOOD -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history_food, parent, false)
                FoodViewHolder(view)
            }
            TYPE_EXERCISE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history_exercise, parent, false)
                ExerciseViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is HistoryItem.HeaderItem -> (holder as HeaderViewHolder).bind(item)
            is HistoryItem.FoodItem -> (holder as FoodViewHolder).bind(item)
            is HistoryItem.ExerciseItem -> (holder as ExerciseViewHolder).bind(item)
        }
    }

    override fun getItemCount(): Int = items.size

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvDate: TextView = itemView.findViewById(R.id.tv_header_date)
        fun bind(item: HistoryItem.HeaderItem) {
            tvDate.text = item.dateString
        }
    }

    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tv_history_name)
        private val tvDetails: TextView = itemView.findViewById(R.id.tv_history_details)
        private val tvCalories: TextView = itemView.findViewById(R.id.tv_history_calories)
        private val tvTime: TextView = itemView.findViewById(R.id.tv_history_time)

        fun bind(item: HistoryItem.FoodItem) {
            tvName.text = item.name
            
            val details = StringBuilder()
            details.append(item.mealType)
            if (item.protein > 0 || item.carbs > 0 || item.fat > 0) {
                details.append(" • P:${item.protein.toInt()} C:${item.carbs.toInt()} F:${item.fat.toInt()}")
            }
            tvDetails.text = details.toString()
            
            tvCalories.text = "+${item.calories} kcal"
            try {
                tvCalories.setTextColor(android.graphics.Color.parseColor("#388E3C")) 
            } catch (e: Exception) {}
            
            val timeFormat = SimpleDateFormat("h:mm a", Locale.US)
            tvTime.text = timeFormat.format(Date(item.timestamp))
        }
    }

    class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tv_history_name)
        private val tvDetails: TextView = itemView.findViewById(R.id.tv_history_details)
        private val tvCalories: TextView = itemView.findViewById(R.id.tv_history_calories)
        private val tvTime: TextView = itemView.findViewById(R.id.tv_history_time)

        fun bind(item: HistoryItem.ExerciseItem) {
            tvName.text = item.name
            
            tvDetails.text = "${item.durationMinutes} mins • ${item.type}"
            
            if (item.calories > 0) {
                tvCalories.text = "-${item.calories} kcal"
                try {
                     tvCalories.setTextColor(android.graphics.Color.parseColor("#E64A19"))
                } catch (e: Exception) {}
            } else {
                tvCalories.text = "Workout"
                 try {
                     tvCalories.setTextColor(android.graphics.Color.GRAY)
                } catch (e: Exception) {}
            }
            
            val timeFormat = SimpleDateFormat("h:mm a", Locale.US)
            tvTime.text = timeFormat.format(Date(item.timestamp))
        }
    }
}
