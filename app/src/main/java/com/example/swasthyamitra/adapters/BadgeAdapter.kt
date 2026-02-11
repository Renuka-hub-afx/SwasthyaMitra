package com.example.swasthyamitra.adapters

import android.graphics.Color
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.swasthyamitra.R

data class Badge(
    val id: String,
    val name: String,
    val iconEmoji: String, // Using Emoji for simplicity as requested icons might not exist
    val isUnlocked: Boolean,
    val description: String
)

class BadgeAdapter(
    private val badges: List<Badge>,
    private val onBadgeClick: (Badge) -> Unit
) : RecyclerView.Adapter<BadgeAdapter.BadgeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BadgeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_badge, parent, false)
        return BadgeViewHolder(view)
    }

    override fun onBindViewHolder(holder: BadgeViewHolder, position: Int) {
        holder.bind(badges[position])
    }

    override fun getItemCount(): Int = badges.size

    inner class BadgeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvIcon: TextView = itemView.findViewById(R.id.tv_badge_icon)
        private val tvName: TextView = itemView.findViewById(R.id.tv_badge_name)
        private val bgView: View = itemView.findViewById(R.id.view_badge_bg)
        private val lockOverlay: ImageView = itemView.findViewById(R.id.iv_lock_overlay)

        fun bind(badge: Badge) {
            tvIcon.text = badge.iconEmoji
            tvName.text = badge.name
            
            if (badge.isUnlocked) {
                lockOverlay.visibility = View.GONE
                tvIcon.alpha = 1.0f
                bgView.background.setTint(Color.parseColor("#E1BEE7")) // Purple tint
            } else {
                lockOverlay.visibility = View.VISIBLE
                tvIcon.alpha = 0.3f
                bgView.background.setTint(Color.parseColor("#F5F5F5")) // Grey tint
            }
            
            itemView.setOnClickListener {
                onBadgeClick(badge)
            }
        }
    }
}
