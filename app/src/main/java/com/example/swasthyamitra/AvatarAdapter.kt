package com.example.swasthyamitra

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class AvatarAdapter(
    private var items: List<AvatarItem>,
    private val onAvatarSelected: (AvatarItem) -> Unit
) : RecyclerView.Adapter<AvatarAdapter.AvatarViewHolder>() {

    private var selectedPosition = -1

    inner class AvatarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imgAvatar)
        val selectionBorder: View = itemView.findViewById(R.id.selectionBorder)
    }

    fun updateData(newItems: List<AvatarItem>) {
        items = newItems
        selectedPosition = -1 // Reset selection when category changes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvatarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_avatar, parent, false)
        return AvatarViewHolder(view)
    }

    override fun onBindViewHolder(holder: AvatarViewHolder, position: Int) {
        val item = items[position]
        holder.imageView.setImageResource(item.resId)

        // Highlight selection
        if (selectedPosition == position) {
            holder.selectionBorder.visibility = View.VISIBLE
        } else {
            holder.selectionBorder.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            val previous = selectedPosition
            selectedPosition = holder.adapterPosition
            notifyItemChanged(previous)
            notifyItemChanged(selectedPosition)
            
            onAvatarSelected(item)
        }
    }

    override fun getItemCount(): Int = items.size
}
