package com.example.swasthyamitra.ui.hydration

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swasthyamitra.data.model.WaterLog
import com.example.swasthyamitra.databinding.ItemWaterLogBinding
import java.text.SimpleDateFormat
import java.util.*

class WaterLogAdapter(
    private var logs: List<WaterLog>,
    private val onDelete: (WaterLog) -> Unit
) : RecyclerView.Adapter<WaterLogAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemWaterLogBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemWaterLogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val log = logs[position]
        holder.binding.tvLogAmount.text = "${log.amountML} ml"
        
        val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
        holder.binding.tvLogTime.text = sdf.format(Date(log.timestamp))

        holder.binding.btnDeleteLog.setOnClickListener {
            onDelete(log)
        }
    }

    override fun getItemCount() = logs.size

    fun updateLogs(newLogs: List<WaterLog>) {
        logs = newLogs
        notifyDataSetChanged()
    }
}
