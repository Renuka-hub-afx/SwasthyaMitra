package com.example.swasthyamitra.repository

import android.content.Context
import com.example.swasthyamitra.models.IndianFood
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.InputStream

class IndianFoodRepository(private val context: Context) {
    
    private var foodDatabase: List<IndianFood> = emptyList()
    private var isLoaded = false
    
    /**
     * Loads the Indian food nutrition dataset from assets folder
     * Reads the Excel file and converts it to a list of IndianFood objects
     */
    suspend fun loadFoodDatabase() {
        if (isLoaded) return
        
        try {
            val inputStream: InputStream = context.assets.open("Indian_Food_Nutrition_Processed.xlsx")
            val workbook = WorkbookFactory.create(inputStream)
            val sheet = workbook.getSheetAt(0)
            
            val foods = mutableListOf<IndianFood>()
            
            // Skip header row (row 0), start from row 1
            for (rowIndex in 1 until sheet.physicalNumberOfRows) {
                val row = sheet.getRow(rowIndex) ?: continue
                
                try {
                    val foodName = row.getCell(0)?.stringCellValue ?: continue
                    val servingSize = row.getCell(1)?.stringCellValue ?: "100g"
                    val calories = row.getCell(2)?.numericCellValue?.toInt() ?: 0
                    val protein = row.getCell(3)?.numericCellValue ?: 0.0
                    val carbs = row.getCell(4)?.numericCellValue ?: 0.0
                    val fat = row.getCell(5)?.numericCellValue ?: 0.0
                    val fiber = row.getCell(6)?.numericCellValue ?: 0.0
                    val category = row.getCell(7)?.stringCellValue ?: ""
                    
                    foods.add(
                        IndianFood(
                            foodName = foodName,
                            servingSize = servingSize,
                            calories = calories,
                            protein = protein,
                            carbs = carbs,
                            fat = fat,
                            fiber = fiber,
                            category = category
                        )
                    )
                } catch (e: Exception) {
                    // Skip malformed rows
                    continue
                }
            }
            
            workbook.close()
            inputStream.close()
            
            foodDatabase = foods
            isLoaded = true
            
        } catch (e: Exception) {
            e.printStackTrace()
            foodDatabase = getDefaultFoodItems()
            isLoaded = true
        }
    }
    
    /**
     * Searches for food items matching the query
     * Uses fuzzy matching to find close matches
     */
    fun searchFood(query: String): List<IndianFood> {
        if (query.isBlank()) return emptyList()
        
        val normalizedQuery = query.trim().lowercase()
        
        return foodDatabase.filter { food ->
            food.foodName.lowercase().contains(normalizedQuery)
        }.sortedBy { food ->
            // Sort by relevance - exact matches first
            when {
                food.foodName.lowercase() == normalizedQuery -> 0
                food.foodName.lowercase().startsWith(normalizedQuery) -> 1
                else -> 2
            }
        }.take(20) // Limit to top 20 results
    }
    
    /**
     * Gets a specific food item by exact name
     */
    fun getFoodByName(name: String): IndianFood? {
        return foodDatabase.find { it.foodName.equals(name, ignoreCase = true) }
    }
    
    /**
     * Returns all food items in the database
     */
    fun getAllFoods(): List<IndianFood> {
        return foodDatabase
    }
    
    /**
     * Returns food items by category
     */
    fun getFoodsByCategory(category: String): List<IndianFood> {
        return foodDatabase.filter { it.category.equals(category, ignoreCase = true) }
    }
    
    /**
     * Default fallback food items if Excel loading fails
     */
    private fun getDefaultFoodItems(): List<IndianFood> {
        return listOf(
            IndianFood("Rice", "1 cup (150g)", 205, 4.3, 45.0, 0.4, 0.6, "Grains"),
            IndianFood("Chapati", "1 piece (40g)", 104, 3.1, 18.0, 2.4, 2.7, "Bread"),
            IndianFood("Dal", "1 cup (200g)", 198, 14.0, 35.0, 1.0, 15.6, "Legumes"),
            IndianFood("Paneer", "100g", 265, 18.3, 3.6, 20.8, 0.0, "Dairy"),
            IndianFood("Chicken Curry", "1 cup (200g)", 189, 28.0, 5.0, 6.0, 1.0, "Meat"),
            IndianFood("Idli", "1 piece (40g)", 58, 2.0, 12.0, 0.2, 0.4, "Breakfast"),
            IndianFood("Dosa", "1 piece (120g)", 168, 4.2, 29.0, 3.8, 1.5, "Breakfast"),
            IndianFood("Sambar", "1 cup (200g)", 82, 4.0, 15.0, 1.0, 5.0, "Curry"),
            IndianFood("Curd", "1 cup (250g)", 154, 11.0, 17.0, 4.0, 0.0, "Dairy"),
            IndianFood("Banana", "1 medium (100g)", 89, 1.1, 23.0, 0.3, 2.6, "Fruits")
        )
    }
}
