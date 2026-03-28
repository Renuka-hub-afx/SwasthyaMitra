package com.example.swasthyamitra

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

// RecyclerView adapter for the horizontal avatar picker; shows a selection border on the chosen avatar
class AvatarAdapter(
    private var items: List<AvatarItem>,
    private val onAvatarSelected: (AvatarItem) -> Unit  // callback fires when user taps an avatar
) : RecyclerView.Adapter<AvatarAdapter.AvatarViewHolder>() {

    private var selectedPosition = -1  // -1 = none selected yet

    inner class AvatarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imgAvatar)
        val selectionBorder: View = itemView.findViewById(R.id.selectionBorder)
    }

    // Swaps the avatar list (e.g., when switching categories) and clears the previous selection highlight
    fun updateData(newItems: List<AvatarItem>) {
        items = newItems
        selectedPosition = -1 // reset highlight when category changes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvatarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_avatar, parent, false)
        return AvatarViewHolder(view)
    }

    // Binds avatar drawable, shows/hides selection border, and triggers callback + selective refresh on tap
    override fun onBindViewHolder(holder: AvatarViewHolder, position: Int) {
        val item = items[position]
        holder.imageView.setImageResource(item.resId)

        // Show purple border only on the currently selected avatar
        if (selectedPosition == position) {
            holder.selectionBorder.visibility = View.VISIBLE
        } else {
            holder.selectionBorder.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            val previous = selectedPosition
            selectedPosition = holder.adapterPosition   // update selection
            notifyItemChanged(previous)                 // remove old border
            notifyItemChanged(selectedPosition)         // add new border
            onAvatarSelected(item)
        }
    }

    override fun getItemCount(): Int = items.size
}
