package com.example.swasthyamitra.ui.hydration

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swasthyamitra.data.model.WaterLog
import com.example.swasthyamitra.databinding.ItemWaterLogBinding
import java.text.SimpleDateFormat
import java.util.*

// RecyclerView adapter displaying each WaterLog entry with its ml amount, timestamp, and a delete button
class WaterLogAdapter(
    private var logs: List<WaterLog>,
    private val onDelete: (WaterLog) -> Unit   // callback fires when user taps delete on a log row
) : RecyclerView.Adapter<WaterLogAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemWaterLogBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemWaterLogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    // Binds a WaterLog to the row: shows ml amount and formatted time (e.g. "08:30 AM"), wires delete button
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val log = logs[position]
        holder.binding.tvLogAmount.text = "${log.amountML} ml"
        
        val sdf = SimpleDateFormat("hh:mm a", Locale.US)
        holder.binding.tvLogTime.text = sdf.format(Date(log.timestamp))

        holder.binding.btnDeleteLog.setOnClickListener {
            onDelete(log)
        }
    }

    override fun getItemCount() = logs.size

    // Swaps the dataset and refreshes the whole list (called after add/delete operations)
    fun updateLogs(newLogs: List<WaterLog>) {
        logs = newLogs
        notifyDataSetChanged()
    }
}
