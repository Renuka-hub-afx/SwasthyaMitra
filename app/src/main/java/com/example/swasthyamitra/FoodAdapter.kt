package com.example.swasthyamitra

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.swasthyamitra.api.Recipe
import kotlin.math.roundToInt

/**
 * Adapter for displaying food recommendations in RecyclerView
 */
class FoodAdapter(
    private var recipes: List<Recipe> = emptyList()
) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_food_recommendation, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount(): Int = recipes.size

    fun updateRecipes(newRecipes: List<Recipe>) {
        recipes = newRecipes
        notifyDataSetChanged()
    }

    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivFoodImage: ImageView = itemView.findViewById(R.id.ivFoodImage)
        private val tvFoodLabel: TextView = itemView.findViewById(R.id.tvFoodLabel)
        private val tvCalories: TextView = itemView.findViewById(R.id.tvCalories)
        private val tvSource: TextView = itemView.findViewById(R.id.tvSource)

        fun bind(recipe: Recipe) {
            tvFoodLabel.text = recipe.label
            tvCalories.text = "${recipe.calories.roundToInt()} kcal"
            tvSource.text = "Source: ${recipe.source}"

            // Load image using Glide
            Glide.with(itemView.context)
                .load(recipe.image)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.ic_menu_gallery)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ivFoodImage)
        }
    }
}
