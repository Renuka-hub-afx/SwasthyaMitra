package com.example.swasthyamitra.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.swasthyamitra.R
import com.example.swasthyamitra.models.Recommendation
import com.example.swasthyamitra.models.SuggestedFood
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

/**
 * RecyclerView Adapter for displaying AI-generated meal recommendations.
 * Each recommendation card shows suggested foods with accept/dismiss actions.
 */
class RecommendationAdapter(
    private val onAcceptClicked: (Recommendation, SuggestedFood?) -> Unit,
    private val onDismissClicked: (Recommendation) -> Unit
) : RecyclerView.Adapter<RecommendationAdapter.RecommendationViewHolder>() {

    private val recommendations = mutableListOf<Recommendation>()

    fun submitList(newList: List<Recommendation>) {
        recommendations.clear()
        recommendations.addAll(newList)
        notifyDataSetChanged()
    }

    fun removeItem(recommendation: Recommendation) {
        val index = recommendations.indexOfFirst { it.documentId == recommendation.documentId }
        if (index != -1) {
            recommendations.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ai_recommendation, parent, false)
        return RecommendationViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecommendationViewHolder, position: Int) {
        holder.bind(recommendations[position])
    }

    override fun getItemCount(): Int = recommendations.size

    inner class RecommendationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvRecommendationType: TextView = itemView.findViewById(R.id.tvRecommendationType)
        private val tvMealTime: TextView = itemView.findViewById(R.id.tvMealTime)
        private val tvMessage: TextView = itemView.findViewById(R.id.tvMessage)
        private val layoutSuggestedFoods: LinearLayout = itemView.findViewById(R.id.layoutSuggestedFoods)
        private val btnDismiss: ImageButton = itemView.findViewById(R.id.btnDismiss)
        private val btnIgnore: MaterialButton = itemView.findViewById(R.id.btnIgnore)
        private val btnAccept: MaterialButton = itemView.findViewById(R.id.btnAccept)
        private val card: MaterialCardView = itemView.findViewById(R.id.cardRecommendation)

        fun bind(recommendation: Recommendation) {
            // Set recommendation type with appropriate color
            tvRecommendationType.text = when (recommendation.type) {
                "Meal_Suggestion" -> "🍽️ Meal Suggestion"
                "Macro_Alert" -> "⚠️ Nutrition Alert"
                "Daily_Plan" -> "📅 Daily Plan"
                else -> recommendation.type
            }

            // Set meal time
            tvMealTime.text = recommendation.mealTime

            // Set message
            tvMessage.text = recommendation.message

            // Clear previous food suggestions
            layoutSuggestedFoods.removeAllViews()

            // Add food suggestions dynamically
            var selectedFood: SuggestedFood? = null
            recommendation.suggestedFoods.forEachIndexed { index, food ->
                val foodView = createFoodSuggestionView(food, index == 0) { selected ->
                    selectedFood = selected
                }
                layoutSuggestedFoods.addView(foodView)
            }

            // Handle dismiss button (X icon)
            btnDismiss.setOnClickListener {
                onDismissClicked(recommendation)
            }

            // Handle "Maybe Later" button
            btnIgnore.setOnClickListener {
                onDismissClicked(recommendation)
            }

            // Handle "Add to Log" button
            btnAccept.setOnClickListener {
                onAcceptClicked(recommendation, selectedFood ?: recommendation.suggestedFoods.firstOrNull())
            }
        }

        private fun createFoodSuggestionView(
            food: SuggestedFood,
            isSelected: Boolean,
            onSelect: (SuggestedFood) -> Unit
        ): View {
            val context = itemView.context
            
            val container = LinearLayout(context).apply {
                orientation = LinearLayout.VERTICAL
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    topMargin = 8
                }
                setPadding(12, 12, 12, 12)
                setBackgroundResource(if (isSelected) R.drawable.food_suggestion_selected else R.drawable.food_suggestion_background)
                isClickable = true
                isFocusable = true
                setOnClickListener { onSelect(food) }
            }

            // Food name
            val nameView = TextView(context).apply {
                text = food.name
                textSize = 15f
                setTextColor(context.getColor(android.R.color.black))
                setTypeface(null, android.graphics.Typeface.BOLD)
            }
            container.addView(nameView)

            // Calories and macros
            val macrosView = TextView(context).apply {
                text = "${food.calories} kcal • P: ${food.protein.toInt()}g • C: ${food.carbs.toInt()}g • F: ${food.fat.toInt()}g"
                textSize = 13f
                setTextColor(context.getColor(android.R.color.darker_gray))
            }
            container.addView(macrosView)

            // Reason (why this food is suggested)
            if (food.reason.isNotEmpty()) {
                val reasonView = TextView(context).apply {
                    text = "💡 ${food.reason}"
                    textSize = 12f
                    setTextColor(context.getColor(R.color.purple_500))
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ).apply { topMargin = 4 }
                }
                container.addView(reasonView)
            }

            return container
        }
    }
}
