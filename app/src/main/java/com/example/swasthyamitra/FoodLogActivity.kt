package com.example.swasthyamitra

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swasthyamitra.api.OpenFoodFactsApi
import com.example.swasthyamitra.api.Product
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.example.swasthyamitra.databinding.ActivityFoodLogBinding
import com.example.swasthyamitra.models.FoodLog
import com.example.swasthyamitra.models.IndianFood
import com.example.swasthyamitra.repository.IndianFoodRepository
import com.google.android.material.chip.ChipGroup
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import com.example.swasthyamitra.services.TelegramService

class FoodLogActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityFoodLogBinding
    private lateinit var authHelper: FirebaseAuthHelper
    private lateinit var foodAdapter: FoodLogAdapter
    private lateinit var foodRepository: IndianFoodRepository
    private val foodLogs = mutableListOf<FoodLog>()
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
    private var selectedDate = Date()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodLogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        val application = application as? UserApplication
        if (application == null) {
            Toast.makeText(this, "App initialization error. Restarting...", Toast.LENGTH_LONG).show()
            finish()
            return
        }
        authHelper = application.authHelper
        
        // Initialize food repository
        foodRepository = IndianFoodRepository(this)
        
        // Load food database in background
        lifecycleScope.launch {
            try {
                foodRepository.loadFoodDatabase()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        
        setupUI()
        loadTodayFoodLogs()
    }
    
    private fun setupUI() {
        // Back button
        binding.btnBack.setOnClickListener {
            finish()
        }
        
        // Date display
        binding.tvDate.text = SimpleDateFormat("EEEE, MMMM dd, yyyy", Locale.getDefault()).format(selectedDate)
        
        // RecyclerView setup
        foodAdapter = FoodLogAdapter(foodLogs) { foodLog ->
            showFoodDetailsDialog(foodLog)
        }
        binding.rvFoodLogs.apply {
            layoutManager = LinearLayoutManager(this@FoodLogActivity)
            adapter = foodAdapter
        }
        
        // FAB to add food
        binding.fabAddFood.setOnClickListener {
            showAddFoodOptionsDialog()
        }
        
        // Telegram button
        binding.btnSendTelegramMeals.setOnClickListener {
            sendMealsToTelegram()
        }
    }
    
    private fun loadTodayFoodLogs() {
        binding.progressBar.visibility = View.VISIBLE
        binding.tvEmptyState.visibility = View.GONE
        
        val userId = authHelper.getCurrentUser()?.uid ?: return
        
        lifecycleScope.launch {
            try {
                val result = authHelper.getTodayFoodLogs(userId)
                result.onSuccess { logs ->
                    foodLogs.clear()
                    foodLogs.addAll(logs.sortedByDescending { it.timestamp })
                
                    runOnUiThread {
                        binding.progressBar.visibility = View.GONE
                        
                        if (foodLogs.isEmpty()) {
                            binding.tvEmptyState.visibility = View.VISIBLE
                            binding.rvFoodLogs.visibility = View.GONE
                        } else {
                            binding.tvEmptyState.visibility = View.GONE
                            binding.rvFoodLogs.visibility = View.VISIBLE
                            foodAdapter.notifyDataSetChanged()
                            updateSummary()
                        }
                    }
                }
                result.onFailure { e ->
                    runOnUiThread {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this@FoodLogActivity, "Error loading logs: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                runOnUiThread {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this@FoodLogActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    
    private fun updateSummary() {
        var totalCalories = 0.0
        var totalProtein = 0.0
        var totalCarbs = 0.0
        var totalFat = 0.0
        
        foodLogs.forEach { log ->
            totalCalories += log.calories
            totalProtein += log.protein
            totalCarbs += log.carbs
            totalFat += log.fat
        }
        
        binding.tvTotalCalories.text = "${totalCalories.toInt()}"
        binding.tvTotalProtein.text = "${totalProtein.toInt()}g"
        binding.tvTotalCarbs.text = "${totalCarbs.toInt()}g"
        binding.tvTotalFat.text = "${totalFat.toInt()}g"
    }
    
    private fun showAddFoodOptionsDialog() {
        val options = arrayOf("� Search Indian Foods", "📝 Manual Entry", "📷 Scan Barcode")
        AlertDialog.Builder(this)
            .setTitle("Add Food")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> showFoodSearchDialog()
                    1 -> showManualEntryDialog()
                    2 -> {
                        val intent = Intent(this, BarcodeScannerActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
            .show()
    }
    
    private fun showManualEntryDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_manual_food_entry, null)
        val etFoodName = dialogView.findViewById<EditText>(R.id.et_food_name)
        val etCalories = dialogView.findViewById<EditText>(R.id.et_calories)
        val etProtein = dialogView.findViewById<EditText>(R.id.et_protein)
        val etCarbs = dialogView.findViewById<EditText>(R.id.et_carbs)
        val etFat = dialogView.findViewById<EditText>(R.id.et_fat)
        val etServingSize = dialogView.findViewById<EditText>(R.id.et_serving_size)
        val chipGroup = dialogView.findViewById<ChipGroup>(R.id.chip_group_meal_type)
        
        // Auto-select meal type based on current time
        val suggestedMealType = suggestMealType()
        val chipIds = mapOf(
            "Breakfast" to R.id.chip_breakfast,
            "Lunch" to R.id.chip_lunch,
            "Dinner" to R.id.chip_dinner,
            "Snack" to R.id.chip_snack
        )
        chipGroup.check(chipIds[suggestedMealType] ?: R.id.chip_snack)
        
        val dialog = AlertDialog.Builder(this)
            .setTitle("Manual Food Entry")
            .setView(dialogView)
            .create()

        dialogView.findViewById<Button>(R.id.btn_save_manual).setOnClickListener {
            val foodName = etFoodName.text.toString().trim()
            val calories = etCalories.text.toString().toDoubleOrNull() ?: 0.0
            val protein = etProtein.text.toString().toDoubleOrNull() ?: 0.0
            val carbs = etCarbs.text.toString().toDoubleOrNull() ?: 0.0
            val fat = etFat.text.toString().toDoubleOrNull() ?: 0.0
            val servingSize = etServingSize.text.toString().trim().ifEmpty { "1 serving" }
            
            val selectedChipId = chipGroup.checkedChipId
            val mealType = when (selectedChipId) {
                R.id.chip_breakfast -> "Breakfast"
                R.id.chip_lunch -> "Lunch"
                R.id.chip_dinner -> "Dinner"
                R.id.chip_snack -> "Snack"
                else -> suggestedMealType
            }
            
            if (foodName.isEmpty() || calories == 0.0) {
                Toast.makeText(this, "Please enter food name and calories", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            saveManualFoodLog(foodName, calories, protein, carbs, fat, servingSize, mealType)
            dialog.dismiss()
        }

        dialogView.findViewById<Button>(R.id.btn_cancel_manual).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
    
    private fun suggestMealType(): String {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        return when (hour) {
            in 5..10 -> "Breakfast"
            in 11..14 -> "Lunch"
            in 15..17 -> "Snack"
            in 18..22 -> "Dinner"
            else -> "Snack"
        }
    }
    
    private fun saveManualFoodLog(
        foodName: String,
        calories: Double,
        protein: Double,
        carbs: Double,
        fat: Double,
        servingSize: String,
        mealType: String
    ) {
        binding.progressBar.visibility = View.VISIBLE
        
        val userId = authHelper.getCurrentUser()?.uid ?: return
        val foodLog = FoodLog(
            logId = "",
            userId = userId,
            foodName = foodName,
            barcode = null,
            photoUrl = null,
            calories = calories.toInt(),
            protein = protein,
            carbs = carbs,
            fat = fat,
            servingSize = servingSize,
            mealType = mealType,
            timestamp = System.currentTimeMillis(),
            date = dateFormat.format(Date())
        )
        
        lifecycleScope.launch {
            try {
                val result = authHelper.logFood(foodLog)
                runOnUiThread {
                    binding.progressBar.visibility = View.GONE
                    result.onSuccess {
                        Toast.makeText(this@FoodLogActivity, "✅ Food logged successfully!", Toast.LENGTH_SHORT).show()
                        loadTodayFoodLogs()
                    }
                    result.onFailure { e ->
                        Toast.makeText(this@FoodLogActivity, "❌ Error: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                runOnUiThread {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this@FoodLogActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    
    private fun showFoodDetailsDialog(foodLog: FoodLog) {
        val time = timeFormat.format(Date(foodLog.timestamp))
        val message = """
            🕐 Time: $time
            🍽️ Meal Type: ${foodLog.mealType}
            📦 Serving: ${foodLog.servingSize}
            
            Nutrition per serving:
            🔥 Calories: ${foodLog.calories.toInt()} kcal
            🥩 Protein: ${foodLog.protein.toInt()}g
            🍞 Carbs: ${foodLog.carbs.toInt()}g
            🥑 Fat: ${foodLog.fat.toInt()}g
        """.trimIndent()
        
        AlertDialog.Builder(this)
            .setTitle(foodLog.foodName)
            .setMessage(message)
            .setPositiveButton("OK", null)
            .setNegativeButton("Delete") { _, _ ->
                deleteFoodLog(foodLog)
            }
            .show()
    }
    
    private fun showFoodSearchDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_food_search, null)
        val etSearch = dialogView.findViewById<TextInputEditText>(R.id.et_search_food)
        val rvResults = dialogView.findViewById<RecyclerView>(R.id.rv_food_results)
        val tvNoResults = dialogView.findViewById<TextView>(R.id.tv_no_results)
        val pbLoading = dialogView.findViewById<android.widget.ProgressBar>(R.id.pb_loading)
        val btnManualEntry = dialogView.findViewById<android.widget.Button>(R.id.btn_manual_entry)
        val btnCancel = dialogView.findViewById<android.widget.Button>(R.id.btn_cancel)
        
        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()
        
        // Setup RecyclerView for search results (handles both IndianFood and Product)
        val searchResults = mutableListOf<Any>()
        val searchAdapter = UnifiedFoodSearchAdapter(searchResults) { selectedFood ->
            dialog.dismiss()
            when (selectedFood) {
                is IndianFood -> showFoodConfirmationDialog(selectedFood)
                is Product -> showProductConfirmationDialog(selectedFood)
            }
        }
        rvResults.apply {
            layoutManager = LinearLayoutManager(this@FoodLogActivity)
            adapter = searchAdapter
        }
        
        // Search functionality with debouncing
        var searchJob: Job? = null
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString().trim()
                
                searchJob?.cancel()
                
                if (query.length < 2) {
                    searchResults.clear()
                    searchAdapter.notifyDataSetChanged()
                    tvNoResults.visibility = View.VISIBLE
                    tvNoResults.text = "Type at least 2 characters to search"
                    rvResults.visibility = View.GONE
                    pbLoading.visibility = View.GONE
                    return
                }
                
                searchJob = lifecycleScope.launch {
                    delay(500) // Debounce 500ms
                    
                    runOnUiThread {
                        pbLoading.visibility = View.VISIBLE
                        tvNoResults.visibility = View.GONE
                        rvResults.visibility = View.GONE
                    }
                    
                    try {
                        // Search both Indian database and OpenFoodFacts
                        val indianResults = foodRepository.searchFood(query)
                        val openFoodResults = searchOpenFoodFacts(query)
                        
                        runOnUiThread {
                            pbLoading.visibility = View.GONE
                            searchResults.clear()
                            
                            // Add Indian foods first (local database)
                            searchResults.addAll(indianResults)
                            
                            // Add OpenFoodFacts results
                            searchResults.addAll(openFoodResults)
                            
                            if (searchResults.isEmpty()) {
                                tvNoResults.visibility = View.VISIBLE
                                tvNoResults.text = "No results found for \"$query\""
                                rvResults.visibility = View.GONE
                            } else {
                                tvNoResults.visibility = View.GONE
                                rvResults.visibility = View.VISIBLE
                                searchAdapter.notifyDataSetChanged()
                            }
                        }
                    } catch (e: Exception) {
                        runOnUiThread {
                            pbLoading.visibility = View.GONE
                            tvNoResults.visibility = View.VISIBLE
                            tvNoResults.text = "Search error: ${e.message}"
                        }
                    }
                }
            }
        })
        
        btnManualEntry.setOnClickListener {
            dialog.dismiss()
            showManualEntryDialog()
        }
        
        btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        
        dialog.show()
    }
    
    private suspend fun searchOpenFoodFacts(query: String): List<Product> {
        return try {
            val api = OpenFoodFactsApi.create()
            val response = api.searchProducts(query)
            
            if (response.isSuccessful && response.body() != null) {
                response.body()!!.products?.filter {
                    it.product_name != null && 
                    it.nutriments != null &&
                    (it.nutriments.`energy-kcal_100g` != null || it.nutriments.`energy-kcal_serving` != null)
                } ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
    
    private fun showProductConfirmationDialog(product: Product) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_manual_food_entry, null)
        val etFoodName = dialogView.findViewById<EditText>(R.id.et_food_name)
        val etCalories = dialogView.findViewById<EditText>(R.id.et_calories)
        val etProtein = dialogView.findViewById<EditText>(R.id.et_protein)
        val etCarbs = dialogView.findViewById<EditText>(R.id.et_carbs)
        val etFat = dialogView.findViewById<EditText>(R.id.et_fat)
        val etServingSize = dialogView.findViewById<EditText>(R.id.et_serving_size)
        val chipGroup = dialogView.findViewById<ChipGroup>(R.id.chip_group_meal_type)
        
        // Pre-fill with product data
        val displayName = if (product.brands != null) {
            "${product.product_name} (${product.brands})"
        } else {
            product.product_name ?: "Unknown Product"
        }
        etFoodName.setText(displayName)
        
        val nutriments = product.nutriments!!
        val calories = nutriments.`energy-kcal_serving` ?: nutriments.`energy-kcal_100g` ?: 0.0
        val protein = nutriments.proteins_serving ?: nutriments.proteins_100g ?: 0.0
        val carbs = nutriments.carbohydrates_serving ?: nutriments.carbohydrates_100g ?: 0.0
        val fat = nutriments.fat_serving ?: nutriments.fat_100g ?: 0.0
        
        etCalories.setText(calories.toInt().toString())
        etProtein.setText(String.format("%.1f", protein))
        etCarbs.setText(String.format("%.1f", carbs))
        etFat.setText(String.format("%.1f", fat))
        etServingSize.setText(product.serving_size ?: "100g")
        
        // Auto-select meal type based on current time
        val suggestedMealType = suggestMealType()
        val chipIds = mapOf(
            "Breakfast" to R.id.chip_breakfast,
            "Lunch" to R.id.chip_lunch,
            "Dinner" to R.id.chip_dinner,
            "Snack" to R.id.chip_snack
        )
        chipGroup.check(chipIds[suggestedMealType] ?: R.id.chip_snack)
        
        val dialog = AlertDialog.Builder(this)
            .setTitle("Confirm Food Details")
            .setView(dialogView)
            .create()

        dialogView.findViewById<Button>(R.id.btn_save_manual).setOnClickListener {
            val foodName = etFoodName.text.toString().trim()
            val finalCalories = etCalories.text.toString().toDoubleOrNull() ?: 0.0
            val finalProtein = etProtein.text.toString().toDoubleOrNull() ?: 0.0
            val finalCarbs = etCarbs.text.toString().toDoubleOrNull() ?: 0.0
            val finalFat = etFat.text.toString().toDoubleOrNull() ?: 0.0
            val servingSize = etServingSize.text.toString().trim()
            
            val selectedChipId = chipGroup.checkedChipId
            val mealType = when (selectedChipId) {
                R.id.chip_breakfast -> "Breakfast"
                R.id.chip_lunch -> "Lunch"
                R.id.chip_dinner -> "Dinner"
                R.id.chip_snack -> "Snack"
                else -> "Snack"
            }
            
            if (foodName.isNotEmpty() && finalCalories > 0) {
                saveManualFoodLog(foodName, finalCalories, finalProtein, finalCarbs, finalFat, servingSize, mealType)
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Please enter food name and calories", Toast.LENGTH_SHORT).show()
            }
        }

        dialogView.findViewById<Button>(R.id.btn_cancel_manual).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
    
    private fun showFoodConfirmationDialog(food: IndianFood) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_manual_food_entry, null)
        val etFoodName = dialogView.findViewById<EditText>(R.id.et_food_name)
        val etCalories = dialogView.findViewById<EditText>(R.id.et_calories)
        val etProtein = dialogView.findViewById<EditText>(R.id.et_protein)
        val etCarbs = dialogView.findViewById<EditText>(R.id.et_carbs)
        val etFat = dialogView.findViewById<EditText>(R.id.et_fat)
        val etServingSize = dialogView.findViewById<EditText>(R.id.et_serving_size)
        val chipGroup = dialogView.findViewById<ChipGroup>(R.id.chip_group_meal_type)
        
        // Pre-fill with food data
        etFoodName.setText(food.foodName)
        etCalories.setText(food.calories.toString())
        etProtein.setText(food.protein.toString())
        etCarbs.setText(food.carbs.toString())
        etFat.setText(food.fat.toString())
        etServingSize.setText(food.servingSize)
        
        // Auto-select meal type based on current time
        val suggestedMealType = suggestMealType()
        val chipIds = mapOf(
            "Breakfast" to R.id.chip_breakfast,
            "Lunch" to R.id.chip_lunch,
            "Dinner" to R.id.chip_dinner,
            "Snack" to R.id.chip_snack
        )
        chipGroup.check(chipIds[suggestedMealType] ?: R.id.chip_snack)
        
        val dialog = AlertDialog.Builder(this)
            .setTitle("Confirm Food Details")
            .setView(dialogView)
            .create()

        dialogView.findViewById<Button>(R.id.btn_save_manual).setOnClickListener {
            val foodName = etFoodName.text.toString().trim()
            val calories = etCalories.text.toString().toDoubleOrNull() ?: 0.0
            val protein = etProtein.text.toString().toDoubleOrNull() ?: 0.0
            val carbs = etCarbs.text.toString().toDoubleOrNull() ?: 0.0
            val fat = etFat.text.toString().toDoubleOrNull() ?: 0.0
            val servingSize = etServingSize.text.toString().trim()
            
            val selectedChipId = chipGroup.checkedChipId
            val mealType = when (selectedChipId) {
                R.id.chip_breakfast -> "Breakfast"
                R.id.chip_lunch -> "Lunch"
                R.id.chip_dinner -> "Dinner"
                R.id.chip_snack -> "Snack"
                else -> "Snack"
            }
            
            if (foodName.isNotEmpty() && calories > 0) {
                saveManualFoodLog(foodName, calories, protein, carbs, fat, servingSize, mealType)
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Please enter food name and calories", Toast.LENGTH_SHORT).show()
            }
        }

        dialogView.findViewById<Button>(R.id.btn_cancel_manual).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
    
    private fun deleteFoodLog(foodLog: FoodLog) {
        AlertDialog.Builder(this)
            .setTitle("Delete Food Log")
            .setMessage("Are you sure you want to delete this entry?")
            .setPositiveButton("Delete") { _, _ ->
                binding.progressBar.visibility = View.VISIBLE
                lifecycleScope.launch {
                    try {
                        com.google.firebase.firestore.FirebaseFirestore.getInstance("renu")
                            .collection("foodLogs")
                            .document(foodLog.logId)
                            .delete()
                            .addOnSuccessListener {
                                runOnUiThread {
                                    Toast.makeText(this@FoodLogActivity, "Deleted successfully", Toast.LENGTH_SHORT).show()
                                    loadTodayFoodLogs()
                                }
                            }
                            .addOnFailureListener { e ->
                                runOnUiThread {
                                    binding.progressBar.visibility = View.GONE
                                    Toast.makeText(this@FoodLogActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                                }
                            }
                    } catch (e: Exception) {
                        runOnUiThread {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this@FoodLogActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
    
    override fun onResume() {
        super.onResume()
        loadTodayFoodLogs()
    }
    
    private fun sendMealsToTelegram() {
        if (foodLogs.isEmpty()) {
            Toast.makeText(this, "No meals to send", Toast.LENGTH_SHORT).show()
            return
        }
        
        lifecycleScope.launch {
            try {
                runOnUiThread {
                    Toast.makeText(this@FoodLogActivity, "Sending to Telegram... 📤", Toast.LENGTH_SHORT).show()
                }
                
                val userName = authHelper.getCurrentUser()?.displayName ?: "User"
                val today = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date())
                
                // Build meal list
                val meals = foodLogs.map { log ->
                    TelegramService.MealInfo(
                        name = log.foodName,
                        mealType = log.mealType,
                        calories = log.calories.toInt()
                    )
                }
                
                // Calculate totals
                val totalCalories = foodLogs.sumOf { it.calories.toInt() }
                val totalProtein = foodLogs.sumOf { it.protein.toInt() }
                val totalCarbs = foodLogs.sumOf { it.carbs.toInt() }
                val totalFat = foodLogs.sumOf { it.fat.toInt() }
                
                val result = TelegramService.sendMealPlanToTelegram(
                    userName = userName,
                    date = today,
                    meals = meals,
                    totalCalories = totalCalories,
                    protein = totalProtein,
                    carbs = totalCarbs,
                    fat = totalFat
                )
                
                runOnUiThread {
                    if (result.isSuccess) {
                        Toast.makeText(this@FoodLogActivity, "✅ Sent to Telegram!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@FoodLogActivity, "❌ Failed to send", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(this@FoodLogActivity, "❌ Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

// Adapter for RecyclerView
class FoodLogAdapter(
    private val foodLogs: List<FoodLog>,
    private val onItemClick: (FoodLog) -> Unit
) : RecyclerView.Adapter<FoodLogAdapter.FoodLogViewHolder>() {
    
    private val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
    
    class FoodLogViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvFoodName: TextView = view.findViewById(R.id.tv_food_name)
        val tvMealType: TextView = view.findViewById(R.id.tv_meal_type)
        val tvTime: TextView = view.findViewById(R.id.tv_time)
        val tvCalories: TextView = view.findViewById(R.id.tv_calories)
        val tvMacros: TextView = view.findViewById(R.id.tv_macros)
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodLogViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_food_log, parent, false)
        return FoodLogViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: FoodLogViewHolder, position: Int) {
        val foodLog = foodLogs[position]
        
        holder.tvFoodName.text = foodLog.foodName
        holder.tvMealType.text = getMealEmoji(foodLog.mealType) + " " + foodLog.mealType
        holder.tvTime.text = timeFormat.format(Date(foodLog.timestamp))
        holder.tvCalories.text = "${foodLog.calories.toInt()} kcal"
        holder.tvMacros.text = "P: ${foodLog.protein.toInt()}g  C: ${foodLog.carbs.toInt()}g  F: ${foodLog.fat.toInt()}g"
        
        holder.itemView.setOnClickListener {
            onItemClick(foodLog)
        }
    }
    
    override fun getItemCount() = foodLogs.size
    
    private fun getMealEmoji(mealType: String): String {
        return when (mealType) {
            "Breakfast" -> "🌅"
            "Lunch" -> "☀️"
            "Dinner" -> "🌙"
            "Snack" -> "🍪"
            else -> "🍽️"
        }
    }
}

// Unified Adapter for Food Search Results (handles both IndianFood and OpenFoodFacts Product)
class UnifiedFoodSearchAdapter(
    private val foods: List<Any>,
    private val onItemClick: (Any) -> Unit
) : RecyclerView.Adapter<UnifiedFoodSearchAdapter.ViewHolder>() {
    
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvFoodName: TextView = view.findViewById(R.id.tv_food_name)
        val tvCategory: TextView = view.findViewById(R.id.tv_food_category)
        val tvCalories: TextView = view.findViewById(R.id.tv_calories)
        val tvProtein: TextView = view.findViewById(R.id.tv_protein)
        val tvCarbs: TextView = view.findViewById(R.id.tv_carbs)
        val tvFat: TextView = view.findViewById(R.id.tv_fat)
        val tvServingSize: TextView = view.findViewById(R.id.tv_serving_size)
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_food_search, parent, false)
        return ViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = foods[position]
        
        when (food) {
            is IndianFood -> {
                holder.tvFoodName.text = food.foodName
                holder.tvCategory.text = "🇮🇳 ${food.category.ifEmpty { "Indian Food" }}"
                holder.tvCalories.text = food.calories.toString()
                holder.tvProtein.text = "%.1fg".format(food.protein)
                holder.tvCarbs.text = "%.1fg".format(food.carbs)
                holder.tvFat.text = "%.1fg".format(food.fat)
                holder.tvServingSize.text = "Serving: ${food.servingSize}"
                holder.tvCategory.setTextColor(holder.itemView.context.getColor(R.color.purple_500))
            }
            is Product -> {
                holder.tvFoodName.text = food.product_name ?: "Unknown"
                val categoryText = if (food.brands != null) {
                    "🌍 ${food.brands}"
                } else {
                    "🌍 OpenFoodFacts"
                }
                holder.tvCategory.text = categoryText
                
                val nutriments = food.nutriments!!
                val calories = (nutriments.`energy-kcal_serving` ?: nutriments.`energy-kcal_100g` ?: 0.0).toInt()
                val protein = nutriments.proteins_serving ?: nutriments.proteins_100g ?: 0.0
                val carbs = nutriments.carbohydrates_serving ?: nutriments.carbohydrates_100g ?: 0.0
                val fat = nutriments.fat_serving ?: nutriments.fat_100g ?: 0.0
                
                holder.tvCalories.text = calories.toString()
                holder.tvProtein.text = "%.1fg".format(protein)
                holder.tvCarbs.text = "%.1fg".format(carbs)
                holder.tvFat.text = "%.1fg".format(fat)
                holder.tvServingSize.text = "Serving: ${food.serving_size ?: "100g"}"
                holder.tvCategory.setTextColor(holder.itemView.context.getColor(R.color.pink_500))
            }
        }
        
        holder.itemView.setOnClickListener {
            onItemClick(food)
        }
    }
    
    override fun getItemCount() = foods.size
}
