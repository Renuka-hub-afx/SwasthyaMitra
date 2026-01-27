package com.example.swasthyamitra.repository

import android.content.Context
import com.example.swasthyamitra.models.IndianFood
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

class IndianFoodRepository(private val context: Context) {

    private var foodCache: List<IndianFood> = emptyList()
    private var isLoading = false

    // Load data from JSON (Blocking or Suspend - keeping it simple for now)
    fun loadFoodDatabase() {
        // Recursion Guard: If already loading, stop to prevent StackOverflow
        if (foodCache.isNotEmpty() || isLoading) return
        
        isLoading = true
        val foodList = mutableListOf<IndianFood>()
        try {
            // Read food_data.json
            val inputStream = context.assets.open("food_data.json")
            val reader = BufferedReader(InputStreamReader(inputStream))
            val jsonString = reader.use { it.readText() }
            val jsonArray = JSONArray(jsonString)

            for (i in 0 until jsonArray.length()) {
                val obj = jsonArray.getJSONObject(i)
                try {
                    val name = obj.optString("Food") ?: obj.optString("name") ?: obj.optString("Name")
                    if (name.isBlank()) continue

                    val calories = obj.optDouble("Calories", 0.0).takeIf { !it.isNaN() } ?: obj.optDouble("cal", 0.0)
                    val protein = obj.optDouble("Protein", 0.0).takeIf { !it.isNaN() } ?: obj.optDouble("protein", 0.0)
                    val fat = obj.optDouble("Fat", 0.0).takeIf { !it.isNaN() } ?: obj.optDouble("fat", 0.0)
                    val carbs = obj.optDouble("Carbs", 0.0).takeIf { !it.isNaN() } ?: obj.optDouble("carbs", 0.0)
                    val type = obj.optString("DietType") ?: obj.optString("type") ?: "Veg"
                    
                    // Specific fields
                    val servingSize = obj.optString("ServingSize", "100g")
                    val category = type // Use diet type as category for now

                    foodList.add(IndianFood(
                        foodName = name,
                        calories = calories.toInt(),
                        protein = protein,
                        fat = fat,
                        carbs = carbs,
                        servingSize = servingSize,
                        category = category
                    ))
                } catch (e: Exception) {
                    // Skip bad items
                }
            }
        } catch (e: Exception) {
            // JSON Failed or Missing -> Try CSV
            try {
                val inputStream = context.assets.open("food_data.csv")
                val reader = BufferedReader(InputStreamReader(inputStream))
                
                // Skip header
                reader.readLine()
                
                reader.forEachLine { line ->
                    try {
                        // Simple CSV parser (assumes comma delimiter)
                        val tokens = line.split(",")
                        if (tokens.size >= 5) {
                            val name = tokens[0].trim()
                            if (name.isNotEmpty()) {
                                // Fallback parsing
                                val calories = tokens[1].trim().toDoubleOrNull() ?: 0.0
                                val protein = tokens[2].trim().toDoubleOrNull() ?: 0.0
                                val fat = tokens.getOrNull(3)?.trim()?.toDoubleOrNull() ?: 0.0
                                val carbs = tokens.getOrNull(4)?.trim()?.toDoubleOrNull() ?: 0.0
                                val type = tokens.getOrNull(5)?.trim() ?: "Veg"
                                
                                foodList.add(IndianFood(
                                    foodName = name,
                                    calories = calories.toInt(),
                                    protein = protein,
                                    fat = fat,
                                    carbs = carbs,
                                    servingSize = "100g",
                                    category = type
                                ))
                            }
                        }
                    } catch (parseError: Exception) {
                        // Skip bad line
                    }
                }
            } catch (csvError: Exception) {
                csvError.printStackTrace()
                // Both Failed -> Mock
                foodList.add(IndianFood("MOCK: Add food_data.csv or .json", "100g", 0, 0.0, 0.0, 0.0, 0.0, "Veg"))
            }
        } finally {
            isLoading = false
        }

        // Assign result
        if (foodList.isNotEmpty()) {
            foodCache = foodList
        }
    }

    fun getAllFoods(): List<IndianFood> {
        if (foodCache.isEmpty() && !isLoading) loadFoodDatabase()
        return foodCache
    }

    fun searchFood(query: String): List<IndianFood> {
        if (foodCache.isEmpty() && !isLoading) loadFoodDatabase()
        
        return foodCache.filter { 
            it.foodName.contains(query, ignoreCase = true) 
        }
    }
}
