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
        
        // Try multiple possible file names
        val fileNames = listOf(
            "Indian_Food_Nutrition_Processed.xlsx",
            "Indian_Food_Nutrition_Processed (1).xlsx"
        )
        
        var loadedFromExcel = false
        
        for (fileName in fileNames) {
            try {
                android.util.Log.d("FoodRepo", "Trying to load: $fileName")
                val inputStream: InputStream = context.assets.open(fileName)
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
                
                if (foods.isNotEmpty()) {
                    foodDatabase = foods
                    isLoaded = true
                    loadedFromExcel = true
                    android.util.Log.d("FoodRepo", "Loaded ${foods.size} foods from $fileName")
                    return
                }
            } catch (e: Exception) {
                android.util.Log.e("FoodRepo", "Failed to load $fileName: ${e.message}")
                // Try next file
                continue
            }
        }
        
        // If no Excel file worked, use comprehensive default
        if (!loadedFromExcel) {
            android.util.Log.d("FoodRepo", "Using default food items")
            foodDatabase = getComprehensiveDefaultFoodItems()
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
    /**
     * Returns all food items in the database
     * If database is empty, loads comprehensive defaults
     */
    fun getAllFoods(): List<IndianFood> {
        if (foodDatabase.isEmpty()) {
            android.util.Log.d("FoodRepo", "getAllFoods: database empty, loading defaults")
            foodDatabase = getComprehensiveDefaultFoodItems()
            isLoaded = true
        }
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
     * Comprehensive list of Indian foods for all meal types
     */
    private fun getComprehensiveDefaultFoodItems(): List<IndianFood> {
        return listOf(
            // ===== BREAKFAST =====
            IndianFood("Idli", "2 pieces (80g)", 116, 4.0, 24.0, 0.4, 0.8, "Breakfast"),
            IndianFood("Dosa (Plain)", "1 piece (120g)", 168, 4.2, 29.0, 3.8, 1.5, "Breakfast"),
            IndianFood("Masala Dosa", "1 piece (180g)", 226, 5.6, 35.0, 7.2, 2.2, "Breakfast"),
            IndianFood("Upma", "1 cup (200g)", 210, 5.0, 32.0, 7.0, 3.0, "Breakfast"),
            IndianFood("Poha", "1 cup (180g)", 196, 4.0, 34.0, 5.0, 2.0, "Breakfast"),
            IndianFood("Paratha (Plain)", "1 piece (80g)", 260, 6.0, 32.0, 12.0, 2.0, "Breakfast"),
            IndianFood("Aloo Paratha", "1 piece (120g)", 320, 7.0, 40.0, 14.0, 3.0, "Breakfast"),
            IndianFood("Uttapam", "1 piece (150g)", 180, 5.0, 28.0, 5.0, 2.5, "Breakfast"),
            IndianFood("Puri", "2 pieces (60g)", 210, 3.5, 22.0, 12.0, 1.0, "Breakfast"),
            IndianFood("Vada", "2 pieces (80g)", 280, 6.0, 24.0, 17.0, 3.0, "Breakfast"),
            IndianFood("Oats Porridge", "1 cup (200g)", 150, 6.0, 27.0, 3.0, 4.0, "Breakfast"),
            IndianFood("Besan Chilla", "2 pieces (100g)", 180, 8.0, 18.0, 8.0, 3.0, "Breakfast"),
            
            // ===== GRAINS & STAPLES =====
            IndianFood("Rice (Steamed)", "1 cup (150g)", 205, 4.3, 45.0, 0.4, 0.6, "Grains"),
            IndianFood("Brown Rice", "1 cup (150g)", 218, 5.0, 45.0, 1.8, 3.5, "Grains"),
            IndianFood("Chapati/Roti", "1 piece (40g)", 104, 3.1, 18.0, 2.4, 2.7, "Bread"),
            IndianFood("Naan", "1 piece (90g)", 260, 9.0, 40.0, 7.0, 2.0, "Bread"),
            IndianFood("Pulao", "1 cup (180g)", 270, 6.0, 42.0, 8.0, 2.0, "Rice Dishes"),
            IndianFood("Biryani (Veg)", "1 cup (200g)", 290, 7.0, 48.0, 8.0, 2.5, "Rice Dishes"),
            IndianFood("Jeera Rice", "1 cup (180g)", 220, 4.5, 44.0, 3.0, 1.0, "Rice Dishes"),
            IndianFood("Lemon Rice", "1 cup (180g)", 240, 4.0, 46.0, 5.0, 1.5, "Rice Dishes"),
            
            // ===== DAL & LEGUMES =====
            IndianFood("Dal Tadka", "1 cup (200g)", 198, 14.0, 35.0, 1.0, 15.6, "Legumes"),
            IndianFood("Dal Fry", "1 cup (200g)", 180, 12.0, 28.0, 4.0, 8.0, "Legumes"),
            IndianFood("Chana Masala", "1 cup (200g)", 269, 15.0, 45.0, 4.0, 12.0, "Legumes"),
            IndianFood("Rajma", "1 cup (200g)", 225, 15.0, 40.0, 1.0, 13.0, "Legumes"),
            IndianFood("Chole", "1 cup (200g)", 269, 15.0, 45.0, 5.0, 12.0, "Legumes"),
            IndianFood("Sambar", "1 cup (200g)", 82, 4.0, 15.0, 1.0, 5.0, "Curry"),
            IndianFood("Rasam", "1 cup (200g)", 40, 2.0, 8.0, 0.5, 1.0, "Soup"),
            IndianFood("Moong Dal", "1 cup (200g)", 147, 10.0, 25.0, 0.5, 7.0, "Legumes"),
            
            // ===== VEGETABLES =====
            IndianFood("Aloo Gobi", "1 cup (200g)", 150, 4.0, 20.0, 6.0, 4.0, "Vegetables"),
            IndianFood("Palak Paneer", "1 cup (200g)", 280, 14.0, 10.0, 21.0, 4.0, "Vegetables"),
            IndianFood("Paneer Butter Masala", "1 cup (200g)", 350, 16.0, 14.0, 26.0, 2.0, "Curry"),
            IndianFood("Bhindi Masala", "1 cup (150g)", 110, 3.0, 12.0, 6.0, 4.0, "Vegetables"),
            IndianFood("Baingan Bharta", "1 cup (200g)", 140, 4.0, 16.0, 7.0, 5.0, "Vegetables"),
            IndianFood("Mixed Vegetable Curry", "1 cup (200g)", 120, 4.0, 15.0, 5.0, 4.0, "Vegetables"),
            IndianFood("Matar Paneer", "1 cup (200g)", 310, 14.0, 18.0, 20.0, 5.0, "Curry"),
            IndianFood("Kadai Paneer", "1 cup (200g)", 320, 16.0, 12.0, 24.0, 3.0, "Curry"),
            
            // ===== NON-VEG (for non-vegetarian users) =====
            IndianFood("Chicken Curry", "1 cup (200g)", 189, 28.0, 5.0, 6.0, 1.0, "Meat"),
            IndianFood("Butter Chicken", "1 cup (200g)", 320, 24.0, 10.0, 20.0, 1.0, "Meat"),
            IndianFood("Tandoori Chicken", "1 piece (150g)", 180, 26.0, 4.0, 7.0, 1.0, "Meat"),
            IndianFood("Fish Curry", "1 cup (200g)", 160, 22.0, 6.0, 5.0, 1.0, "Seafood"),
            IndianFood("Egg Curry", "1 cup (200g)", 180, 14.0, 8.0, 11.0, 2.0, "Eggs"),
            IndianFood("Boiled Egg", "2 eggs (100g)", 155, 13.0, 1.0, 11.0, 0.0, "Eggs"),
            IndianFood("Egg Bhurji", "1 serving (150g)", 200, 14.0, 4.0, 15.0, 1.0, "Eggs"),
            IndianFood("Chicken Biryani", "1 cup (250g)", 350, 18.0, 45.0, 12.0, 2.0, "Rice Dishes"),
            
            // ===== DAIRY =====
            IndianFood("Paneer", "100g", 265, 18.3, 3.6, 20.8, 0.0, "Dairy"),
            IndianFood("Curd/Dahi", "1 cup (250g)", 154, 11.0, 17.0, 4.0, 0.0, "Dairy"),
            IndianFood("Lassi (Sweet)", "1 glass (250ml)", 180, 6.0, 28.0, 5.0, 0.0, "Beverages"),
            IndianFood("Buttermilk/Chaas", "1 glass (250ml)", 40, 3.0, 5.0, 1.0, 0.0, "Beverages"),
            IndianFood("Milk", "1 cup (250ml)", 150, 8.0, 12.0, 8.0, 0.0, "Dairy"),
            IndianFood("Paneer Tikka", "6 pieces (120g)", 280, 18.0, 6.0, 20.0, 1.0, "Snacks"),
            
            // ===== SNACKS =====
            IndianFood("Samosa", "1 piece (50g)", 150, 3.0, 18.0, 7.0, 1.0, "Snacks"),
            IndianFood("Pakora/Bhajiya", "5 pieces (80g)", 200, 4.0, 20.0, 12.0, 2.0, "Snacks"),
            IndianFood("Dhokla", "4 pieces (100g)", 160, 7.0, 25.0, 4.0, 3.0, "Snacks"),
            IndianFood("Khakhra", "2 pieces (40g)", 140, 4.0, 24.0, 3.0, 2.0, "Snacks"),
            IndianFood("Makhana (Roasted)", "1 cup (30g)", 105, 4.0, 18.0, 0.5, 2.0, "Snacks"),
            IndianFood("Sprouts Chaat", "1 cup (150g)", 120, 8.0, 18.0, 2.0, 6.0, "Snacks"),
            IndianFood("Fruit Chaat", "1 cup (150g)", 100, 2.0, 24.0, 0.5, 3.0, "Snacks"),
            IndianFood("Roasted Chana", "1/4 cup (30g)", 110, 7.0, 18.0, 2.0, 5.0, "Snacks"),
            IndianFood("Almonds", "10 pieces (15g)", 87, 3.0, 3.0, 7.5, 1.5, "Nuts"),
            IndianFood("Walnuts", "5 halves (15g)", 98, 2.0, 2.0, 9.5, 1.0, "Nuts"),
            
            // ===== FRUITS =====
            IndianFood("Banana", "1 medium (100g)", 89, 1.1, 23.0, 0.3, 2.6, "Fruits"),
            IndianFood("Apple", "1 medium (150g)", 78, 0.4, 21.0, 0.2, 3.6, "Fruits"),
            IndianFood("Papaya", "1 cup (140g)", 62, 0.7, 16.0, 0.3, 2.5, "Fruits"),
            IndianFood("Mango", "1 medium (200g)", 135, 1.4, 35.0, 0.6, 3.0, "Fruits"),
            IndianFood("Orange", "1 medium (150g)", 62, 1.2, 15.0, 0.2, 3.0, "Fruits"),
            IndianFood("Watermelon", "1 cup (150g)", 46, 0.9, 12.0, 0.2, 0.6, "Fruits"),
            IndianFood("Pomegranate", "1/2 cup seeds (90g)", 83, 1.7, 19.0, 1.2, 4.0, "Fruits"),
            IndianFood("Guava", "1 medium (100g)", 68, 2.5, 14.0, 1.0, 5.4, "Fruits"),
            
            // ===== SALADS =====
            IndianFood("Cucumber Raita", "1 cup (200g)", 80, 4.0, 10.0, 3.0, 1.0, "Salads"),
            IndianFood("Green Salad", "1 cup (100g)", 25, 1.5, 5.0, 0.2, 2.0, "Salads"),
            IndianFood("Kachumber", "1 cup (150g)", 40, 1.5, 8.0, 0.5, 2.0, "Salads"),
            
            // ===== SWEETS (occasional) =====
            IndianFood("Gulab Jamun", "2 pieces (60g)", 180, 2.0, 24.0, 9.0, 0.5, "Sweets"),
            IndianFood("Kheer", "1/2 cup (120g)", 150, 4.0, 22.0, 5.0, 0.5, "Sweets"),
            IndianFood("Jalebi", "2 pieces (50g)", 200, 2.0, 36.0, 6.0, 0.0, "Sweets"),
            IndianFood("Ladoo (Besan)", "1 piece (40g)", 180, 3.0, 18.0, 11.0, 1.0, "Sweets")
        )
    }
}
