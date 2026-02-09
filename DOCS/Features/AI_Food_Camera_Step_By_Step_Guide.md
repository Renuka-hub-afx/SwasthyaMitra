# 🚀 AI Food Camera - Complete Step-by-Step Implementation Guide

## 📋 Table of Contents
1. [Prerequisites Check](#prerequisites-check)
2. [Step 1: Add ML Kit Dependency](#step-1-add-ml-kit-dependency)
3. [Step 2: Create AIFoodRecognitionService](#step-2-create-aifoodrecognitionservice)
4. [Step 3: Update FoodPhotoCaptureActivity](#step-3-update-foodphotocaptureactivity)
5. [Step 4: Update Layout XML](#step-4-update-layout-xml)
6. [Step 5: Integrate with FoodLogActivity](#step-5-integrate-with-foodlogactivity)
7. [Step 6: Add Homepage Quick Action](#step-6-add-homepage-quick-action)
8. [Step 7: Test the Feature](#step-7-test-the-feature)
9. [Step 8: Deploy and Monitor](#step-8-deploy-and-monitor)

---

## Prerequisites Check

Before starting, verify you have:
- ✅ Android Studio installed
- ✅ SwasthyaMitra project opened
- ✅ Firebase project configured
- ✅ Device/emulator running Android 7.0+ (API 26+)
- ✅ Camera permissions in AndroidManifest.xml

**Estimated Total Time:** 8-10 hours

---

# STEP 1: Add ML Kit Dependency

## 📍 Location
**File:** `app/build.gradle`

## ⏱️ Time Required
5 minutes

## 📝 Instructions

### 1.1 Open build.gradle
Navigate to: `SwasthyaMitra/app/build.gradle`

### 1.2 Add ML Kit Dependency
Find the `dependencies` block (around line 78) and add this line **after line 106** (after the Guava dependency):

```gradle
dependencies {
    // ... existing dependencies ...
    
    // ML Kit for Image Labeling (Food Recognition)
    implementation 'com.google.mlkit:image-labeling:17.0.8'
}
```

**Complete dependencies block should look like:**
```gradle
dependencies {
    // AndroidX Core & UI
    implementation libs.androidx.core.ktx
    implementation libs.androidx.appcompat
    implementation libs.material
    implementation libs.androidx.activity
    implementation libs.androidx.constraintlayout

    // Firebase platform - using BoM for library compatibility
    implementation platform('com.google.firebase:firebase-bom:34.8.0')
    
    // Firebase Features (versions managed by BoM)
    implementation 'com.google.firebase:firebase-auth'
    implementation 'com.google.firebase:firebase-firestore'
    implementation libs.firebase.database
    implementation 'com.google.firebase:firebase-ai'
    implementation libs.firebase.appcheck.playintegrity
    implementation libs.firebase.appcheck.debug

    // Play Services
    implementation libs.play.services.location

    // ML Kit & CameraX (Stable versions)
    implementation 'com.google.mlkit:barcode-scanning:17.3.0'
    implementation 'androidx.camera:camera-core:1.5.2'
    implementation 'androidx.camera:camera-camera2:1.5.2'
    implementation 'androidx.camera:camera-lifecycle:1.5.2'
    implementation 'androidx.camera:camera-view:1.5.2'
    implementation 'com.google.guava:guava:33.3.0-android'
    
    // ✨ NEW: ML Kit for Image Labeling (Food Recognition)
    implementation 'com.google.mlkit:image-labeling:17.0.8'

    // Coroutines & Lifecycle
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.10.2'
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.10.0'
    implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.10.0'

    // Networking & AI Stability
    implementation 'com.squareup.retrofit2:retrofit:3.0.0'
    implementation 'com.squareup.retrofit2:converter-gson:3.0.0'
    implementation "io.ktor:ktor-client-android:2.3.12"

    // Image Loading & Utilities
    implementation 'com.github.bumptech.glide:glide:5.0.5'
    implementation 'org.apache.poi:poi:5.5.1'
    implementation 'org.apache.poi:poi-ooxml:5.5.1'

    // Charting
    implementation libs.mp.android.chart

    // Testing
    testImplementation libs.junit
    androidTestImplementation libs.androidx.junit
    androidTestImplementation libs.androidx.espresso.core
}
```

### 1.3 Sync Gradle
1. Click **"Sync Now"** in the banner that appears
2. Wait for Gradle sync to complete (1-2 minutes)
3. Verify no errors in the Build output

### 1.4 Verify Installation
Run this command in Terminal:
```bash
./gradlew app:dependencies | grep "image-labeling"
```

**Expected Output:**
```
+--- com.google.mlkit:image-labeling:17.0.8
```

## ✅ Checkpoint
- [ ] ML Kit dependency added to build.gradle
- [ ] Gradle sync completed successfully
- [ ] No build errors

---

# STEP 2: Create AIFoodRecognitionService

## 📍 Location
**New File:** `app/src/main/java/com/example/swasthyamitra/ai/AIFoodRecognitionService.kt`

## ⏱️ Time Required
1.5 hours

## 📝 Instructions

### 2.1 Create the File
1. Right-click on `app/src/main/java/com/example/swasthyamitra/ai/`
2. Select **New → Kotlin Class/File**
3. Name: `AIFoodRecognitionService`
4. Type: **Class**

### 2.2 Add Complete Code

Copy and paste this **complete implementation**:

```kotlin
package com.example.swasthyamitra.ai

import android.content.Context
import android.net.Uri
import com.example.swasthyamitra.api.OpenFoodFactsApi
import com.example.swasthyamitra.repository.IndianFoodRepository
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabel
import com.google.mlkit.vision.label.ImageLabeler
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import kotlinx.coroutines.tasks.await
import java.io.IOException

/**
 * AI-powered food recognition service using Google ML Kit
 * 
 * Features:
 * - On-device image labeling
 * - Food-specific label filtering
 * - Nutrition data lookup from multiple sources
 * - Calorie estimation fallback
 */
class AIFoodRecognitionService(private val context: Context) {

    private val labeler: ImageLabeler
    private val foodRepository: IndianFoodRepository
    
    // Confidence threshold for food detection (60%)
    private val CONFIDENCE_THRESHOLD = 0.6f
    
    // Food-related keywords to filter ML Kit labels
    private val FOOD_KEYWORDS = setOf(
        "food", "dish", "cuisine", "meal", "breakfast", "lunch", "dinner",
        "snack", "dessert", "vegetable", "fruit", "meat", "rice", "bread",
        "curry", "salad", "soup", "noodle", "pasta", "pizza", "burger",
        "sandwich", "chicken", "fish", "egg", "milk", "cheese", "yogurt",
        "dal", "roti", "chapati", "paratha", "dosa", "idli", "samosa",
        "biryani", "paneer", "sabzi", "chutney", "raita", "sweet", "cake"
    )

    init {
        // Initialize ML Kit Image Labeler with confidence threshold
        val options = ImageLabelerOptions.Builder()
            .setConfidenceThreshold(CONFIDENCE_THRESHOLD)
            .build()
        
        labeler = ImageLabeling.getClient(options)
        
        // Initialize Indian food repository
        foodRepository = IndianFoodRepository(context)
    }

    /**
     * Main function: Analyze food image and return detected foods with nutrition
     * 
     * @param imageUri URI of the captured/selected image
     * @return Result containing list of detected foods or error
     */
    suspend fun analyzeFoodImage(imageUri: Uri): Result<List<DetectedFood>> {
        return try {
            // Step 1: Load food database if not already loaded
            foodRepository.loadFoodDatabase()
            
            // Step 2: Create InputImage from URI
            val image = InputImage.fromFilePath(context, imageUri)
            
            // Step 3: Process image with ML Kit
            val labels = labeler.process(image).await()
            
            // Step 4: Filter food-related labels
            val foodLabels = labels.filter { label ->
                isFoodRelated(label.text) && label.confidence >= CONFIDENCE_THRESHOLD
            }
            
            // Step 5: Check if any food detected
            if (foodLabels.isEmpty()) {
                return Result.success(emptyList())
            }
            
            // Step 6: Map labels to food items with nutrition data
            val detectedFoods = mapLabelsToFoods(foodLabels)
            
            Result.success(detectedFoods)
            
        } catch (e: IOException) {
            Result.failure(Exception("Failed to load image: ${e.message}"))
        } catch (e: Exception) {
            Result.failure(Exception("Food recognition failed: ${e.message}"))
        }
    }

    /**
     * Check if ML Kit label text is food-related
     */
    private fun isFoodRelated(labelText: String): Boolean {
        val lowerText = labelText.lowercase()
        return FOOD_KEYWORDS.any { keyword ->
            lowerText.contains(keyword)
        }
    }

    /**
     * Map ML Kit labels to DetectedFood objects with nutrition data
     * Uses waterfall approach: Indian DB → OpenFoodFacts → Estimation
     */
    private suspend fun mapLabelsToFoods(labels: List<ImageLabel>): List<DetectedFood> {
        val detectedFoods = mutableListOf<DetectedFood>()
        
        for (label in labels) {
            // Search nutrition data from multiple sources
            val nutritionInfo = searchNutritionData(label.text)
            
            if (nutritionInfo != null) {
                // Found in database
                detectedFoods.add(
                    DetectedFood(
                        name = nutritionInfo.name,
                        confidence = label.confidence,
                        calories = nutritionInfo.calories,
                        protein = nutritionInfo.protein,
                        carbs = nutritionInfo.carbs,
                        fat = nutritionInfo.fat,
                        servingSize = nutritionInfo.servingSize
                    )
                )
            } else {
                // Fallback: Use estimated values
                detectedFoods.add(
                    DetectedFood(
                        name = label.text,
                        confidence = label.confidence,
                        calories = estimateCalories(label.text),
                        protein = estimateProtein(label.text),
                        carbs = estimateCarbs(label.text),
                        fat = estimateFat(label.text),
                        servingSize = "1 serving"
                    )
                )
            }
        }
        
        return detectedFoods
    }

    /**
     * Search nutrition data from Indian food database and OpenFoodFacts API
     */
    private suspend fun searchNutritionData(foodName: String): NutritionInfo? {
        try {
            // Priority 1: Search in local Indian food database
            val results = foodRepository.searchFood(foodName)
            
            if (results.isNotEmpty()) {
                val food = results.first()
                return NutritionInfo(
                    name = food.foodName,
                    calories = food.calories,
                    protein = food.protein,
                    carbs = food.carbs,
                    fat = food.fat,
                    servingSize = food.servingSize
                )
            }
            
            // Priority 2: Fallback to OpenFoodFacts API
            val api = OpenFoodFactsApi.create()
            val response = api.searchProducts(foodName)
            
            if (response.isSuccessful && response.body() != null) {
                val products = response.body()!!.products
                if (!products.isNullOrEmpty()) {
                    val product = products.first()
                    val nutriments = product.nutriments
                    
                    if (nutriments != null) {
                        return NutritionInfo(
                            name = product.product_name ?: foodName,
                            calories = (nutriments.`energy-kcal_100g` ?: 0.0).toInt(),
                            protein = nutriments.proteins_100g ?: 0.0,
                            carbs = nutriments.carbohydrates_100g ?: 0.0,
                            fat = nutriments.fat_100g ?: 0.0,
                            servingSize = product.serving_size ?: "100g"
                        )
                    }
                }
            }
            
        } catch (e: Exception) {
            e.printStackTrace()
        }
        
        return null
    }

    /**
     * Estimate calories based on food category (fallback)
     */
    private fun estimateCalories(foodName: String): Int {
        val lowerName = foodName.lowercase()
        
        return when {
            // High calorie foods (500+ kcal)
            lowerName.contains("pizza") || lowerName.contains("burger") ||
            lowerName.contains("fried") || lowerName.contains("biryani") ||
            lowerName.contains("paratha") -> 500
            
            // Medium-high calorie foods (300-400 kcal)
            lowerName.contains("rice") || lowerName.contains("bread") ||
            lowerName.contains("pasta") || lowerName.contains("roti") ||
            lowerName.contains("noodle") -> 300
            
            // Medium calorie foods (200-250 kcal)
            lowerName.contains("chicken") || lowerName.contains("fish") ||
            lowerName.contains("egg") || lowerName.contains("paneer") ||
            lowerName.contains("dal") -> 250
            
            // Low calorie foods (100-150 kcal)
            lowerName.contains("salad") || lowerName.contains("vegetable") ||
            lowerName.contains("fruit") || lowerName.contains("soup") ||
            lowerName.contains("sabzi") -> 150
            
            // Very low calorie (50-100 kcal)
            lowerName.contains("yogurt") || lowerName.contains("raita") ||
            lowerName.contains("chutney") -> 80
            
            // Default estimate
            else -> 200
        }
    }

    /**
     * Estimate protein based on food category
     */
    private fun estimateProtein(foodName: String): Double {
        val lowerName = foodName.lowercase()
        
        return when {
            // High protein foods
            lowerName.contains("chicken") || lowerName.contains("fish") ||
            lowerName.contains("egg") || lowerName.contains("paneer") ||
            lowerName.contains("dal") -> 20.0
            
            // Medium protein
            lowerName.contains("rice") || lowerName.contains("bread") ||
            lowerName.contains("roti") -> 8.0
            
            // Low protein
            else -> 5.0
        }
    }

    /**
     * Estimate carbs based on food category
     */
    private fun estimateCarbs(foodName: String): Double {
        val lowerName = foodName.lowercase()
        
        return when {
            // High carb foods
            lowerName.contains("rice") || lowerName.contains("bread") ||
            lowerName.contains("pasta") || lowerName.contains("roti") ||
            lowerName.contains("noodle") || lowerName.contains("biryani") -> 50.0
            
            // Medium carb
            lowerName.contains("vegetable") || lowerName.contains("sabzi") ||
            lowerName.contains("dal") -> 20.0
            
            // Low carb
            lowerName.contains("chicken") || lowerName.contains("fish") ||
            lowerName.contains("egg") || lowerName.contains("paneer") -> 5.0
            
            else -> 25.0
        }
    }

    /**
     * Estimate fat based on food category
     */
    private fun estimateFat(foodName: String): Double {
        val lowerName = foodName.lowercase()
        
        return when {
            // High fat foods
            lowerName.contains("fried") || lowerName.contains("burger") ||
            lowerName.contains("pizza") || lowerName.contains("paratha") -> 20.0
            
            // Medium fat
            lowerName.contains("chicken") || lowerName.contains("paneer") ||
            lowerName.contains("dal") -> 10.0
            
            // Low fat
            lowerName.contains("rice") || lowerName.contains("vegetable") ||
            lowerName.contains("fruit") || lowerName.contains("salad") -> 3.0
            
            else -> 8.0
        }
    }

    /**
     * Calculate total nutrition from all detected foods
     */
    fun calculateTotalNutrition(foods: List<DetectedFood>): TotalNutrition {
        var totalCalories = 0
        var totalProtein = 0.0
        var totalCarbs = 0.0
        var totalFat = 0.0
        val detectedItems = mutableListOf<String>()
        
        foods.forEach { food ->
            totalCalories += food.calories
            totalProtein += food.protein
            totalCarbs += food.carbs
            totalFat += food.fat
            detectedItems.add(food.name)
        }
        
        return TotalNutrition(
            totalCalories = totalCalories,
            totalProtein = totalProtein,
            totalCarbs = totalCarbs,
            totalFat = totalFat,
            detectedItems = detectedItems
        )
    }
}

/**
 * Data class for detected food item
 */
data class DetectedFood(
    val name: String,
    val confidence: Float,
    val calories: Int,
    val protein: Double,
    val carbs: Double,
    val fat: Double,
    val servingSize: String
)

/**
 * Data class for total nutrition summary
 */
data class TotalNutrition(
    val totalCalories: Int,
    val totalProtein: Double,
    val totalCarbs: Double,
    val totalFat: Double,
    val detectedItems: List<String>
)

/**
 * Internal data class for nutrition lookup
 */
private data class NutritionInfo(
    val name: String,
    val calories: Int,
    val protein: Double,
    val carbs: Double,
    val fat: Double,
    val servingSize: String
)
```

### 2.3 Verify Code
1. Check for any red underlines (import errors)
2. If any imports are missing, press **Alt+Enter** and select "Import"
3. Build the project: **Build → Make Project**

## ✅ Checkpoint
- [ ] AIFoodRecognitionService.kt created
- [ ] All imports resolved
- [ ] No compilation errors
- [ ] Code builds successfully

---

# STEP 3: Update FoodPhotoCaptureActivity

## 📍 Location
**File:** `app/src/main/java/com/example/swasthyamitra/FoodPhotoCaptureActivity.kt`

## ⏱️ Time Required
2 hours

## 📝 Instructions

### 3.1 Add Imports
Add these imports at the top of the file (after existing imports):

```kotlin
import com.example.swasthyamitra.ai.AIFoodRecognitionService
import com.example.swasthyamitra.ai.DetectedFood
import com.example.swasthyamitra.ai.TotalNutrition
import com.example.swasthyamitra.models.FoodLog
import android.app.AlertDialog
import android.view.View
import java.text.SimpleDateFormat
import java.util.*
```

### 3.2 Add Service Property
Add this property after `capturedImageUri` (around line 23):

```kotlin
private var capturedImageUri: Uri? = null
private lateinit var foodRecognitionService: AIFoodRecognitionService  // NEW
```

### 3.3 Initialize Service in onCreate
Update the `onCreate` method to initialize the service (around line 31):

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityFoodPhotoCaptureBinding.inflate(layoutInflater)
    setContentView(binding.root)
    
    // Initialize AI Food Recognition Service
    foodRecognitionService = AIFoodRecognitionService(this)

    binding.btnTakePhoto.setOnClickListener {
        if (checkCameraPermission()) {
            openCamera()
        } else {
            requestCameraPermission()
        }
    }

    binding.btnChooseGallery.setOnClickListener {
        openGallery()
    }

    binding.btnAnalyzeFood.setOnClickListener {
        if (capturedImageUri != null) {
            analyzeFoodImage()
        } else {
            Toast.makeText(this, "Please capture or select a photo first", Toast.LENGTH_SHORT).show()
        }
    }
}
```

### 3.4 Replace analyzeFoodImage Function
**REPLACE** the entire `analyzeFoodImage()` function (lines 115-133) with this new implementation:

```kotlin
private fun analyzeFoodImage() {
    binding.tvAnalysisResult.text = "🔍 Analyzing food with AI..."
    binding.btnAnalyzeFood.isEnabled = false
    binding.progressBar.visibility = View.VISIBLE
    
    lifecycleScope.launch {
        try {
            val result = foodRecognitionService.analyzeFoodImage(capturedImageUri!!)
            
            result.onSuccess { detectedFoods ->
                runOnUiThread {
                    binding.progressBar.visibility = View.GONE
                    binding.btnAnalyzeFood.isEnabled = true
                    
                    if (detectedFoods.isEmpty()) {
                        showNoFoodDetectedDialog()
                    } else {
                        showDetectionResults(detectedFoods)
                    }
                }
            }
            
            result.onFailure { error ->
                runOnUiThread {
                    binding.progressBar.visibility = View.GONE
                    binding.btnAnalyzeFood.isEnabled = true
                    Toast.makeText(
                        this@FoodPhotoCaptureActivity,
                        "❌ Analysis failed: ${error.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        } catch (e: Exception) {
            runOnUiThread {
                binding.progressBar.visibility = View.GONE
                binding.btnAnalyzeFood.isEnabled = true
                Toast.makeText(
                    this@FoodPhotoCaptureActivity,
                    "Error: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
```

### 3.5 Add New Helper Functions
Add these **NEW functions** at the end of the class (before the closing brace):

```kotlin
/**
 * Display detection results to user
 */
private fun showDetectionResults(detectedFoods: List<DetectedFood>) {
    val totalNutrition = foodRecognitionService.calculateTotalNutrition(detectedFoods)
    
    val resultText = buildString {
        appendLine("✅ Detected ${detectedFoods.size} food item(s):\n")
        
        detectedFoods.forEachIndexed { index, food ->
            appendLine("${index + 1}. ${food.name} (${(food.confidence * 100).toInt()}% confident)")
            appendLine("   📊 ${food.calories} kcal | P: ${food.protein.toInt()}g | C: ${food.carbs.toInt()}g | F: ${food.fat.toInt()}g")
            appendLine()
        }
        
        appendLine("━━━━━━━━━━━━━━━━━━━━")
        appendLine("🔥 Total Nutrition:")
        appendLine("• Calories: ${totalNutrition.totalCalories} kcal")
        appendLine("• Protein: ${totalNutrition.totalProtein.toInt()}g")
        appendLine("• Carbs: ${totalNutrition.totalCarbs.toInt()}g")
        appendLine("• Fat: ${totalNutrition.totalFat.toInt()}g")
    }
    
    binding.tvAnalysisResult.text = resultText
    
    // Show "Log to Diary" button
    binding.btnLogToDiary.visibility = View.VISIBLE
    binding.btnLogToDiary.setOnClickListener {
        logDetectedFoodsToDiary(detectedFoods, totalNutrition)
    }
}

/**
 * Handle case when no food is detected
 */
private fun showNoFoodDetectedDialog() {
    AlertDialog.Builder(this)
        .setTitle("No Food Detected")
        .setMessage("We couldn't identify any food in this image. Would you like to:")
        .setPositiveButton("Manual Entry") { _, _ ->
            // Navigate to manual food entry
            val intent = Intent(this, FoodLogActivity::class.java)
            intent.putExtra("openManualEntry", true)
            startActivity(intent)
            finish()
        }
        .setNegativeButton("Try Again") { dialog, _ ->
            dialog.dismiss()
            binding.tvAnalysisResult.text = ""
        }
        .show()
}

/**
 * Log detected foods to Firestore
 */
private fun logDetectedFoodsToDiary(
    detectedFoods: List<DetectedFood>,
    totalNutrition: TotalNutrition
) {
    val application = application as? UserApplication
    if (application == null) {
        Toast.makeText(this, "App initialization error", Toast.LENGTH_SHORT).show()
        return
    }
    
    val userId = application.authHelper.getCurrentUser()?.uid
    if (userId == null) {
        Toast.makeText(this, "Please log in first", Toast.LENGTH_SHORT).show()
        return
    }
    
    val foodLog = FoodLog(
        logId = "",
        userId = userId,
        foodName = totalNutrition.detectedItems.joinToString(", "),
        barcode = null,
        photoUrl = capturedImageUri.toString(),
        calories = totalNutrition.totalCalories,
        protein = totalNutrition.totalProtein,
        carbs = totalNutrition.totalCarbs,
        fat = totalNutrition.totalFat,
        servingSize = "1 meal",
        mealType = suggestMealType(),
        timestamp = System.currentTimeMillis(),
        date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
    )
    
    binding.progressBar.visibility = View.VISIBLE
    
    lifecycleScope.launch {
        try {
            val result = application.authHelper.logFood(foodLog)
            runOnUiThread {
                binding.progressBar.visibility = View.GONE
                result.onSuccess {
                    Toast.makeText(
                        this@FoodPhotoCaptureActivity,
                        "✅ Logged to food diary!",
                        Toast.LENGTH_SHORT
                    ).show()
                    
                    // Navigate back to homepage
                    val intent = Intent(this@FoodPhotoCaptureActivity, homepage::class.java)
                    startActivity(intent)
                    finish()
                }
                result.onFailure { e ->
                    Toast.makeText(
                        this@FoodPhotoCaptureActivity,
                        "❌ Failed to log: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        } catch (e: Exception) {
            runOnUiThread {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(
                    this@FoodPhotoCaptureActivity,
                    "Error: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}

/**
 * Suggest meal type based on current time
 */
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
```

### 3.6 Verify Code
1. Check for compilation errors
2. Resolve any missing imports (Alt+Enter)
3. Build project: **Build → Make Project**

## ✅ Checkpoint
- [ ] All imports added
- [ ] Service initialized in onCreate
- [ ] analyzeFoodImage() function replaced
- [ ] New helper functions added
- [ ] No compilation errors

---

# STEP 4: Update Layout XML

## 📍 Location
**File:** `app/src/main/res/layout/activity_food_photo_capture.xml`

## ⏱️ Time Required
30 minutes

## 📝 Instructions

### 4.1 Open Layout File
Navigate to: `app/src/main/res/layout/activity_food_photo_capture.xml`

### 4.2 Add ProgressBar
Find the existing layout and add a ProgressBar (typically after the analysis result TextView):

```xml
<ProgressBar
    android:id="@+id/progressBar"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_gravity="center"
    android:visibility="gone"
    android:indeterminate="true"
    android:layout_marginTop="16dp" />
```

### 4.3 Add "Log to Diary" Button
Add this button after the "Analyze Food" button:

```xml
<Button
    android:id="@+id/btnLogToDiary"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="📝 Log to Food Diary"
    android:visibility="gone"
    android:backgroundTint="@color/green_500"
    android:textColor="@android:color/white"
    android:textSize="16sp"
    android:padding="16dp"
    android:layout_marginTop="16dp"
    android:layout_marginStart="16dp"
    android:layout_marginEnd="16dp" />
```

### 4.4 Add Color Resource (if needed)
If `@color/green_500` doesn't exist, add it to `app/src/main/res/values/colors.xml`:

```xml
<color name="green_500">#4CAF50</color>
```

### 4.5 Preview Layout
1. Open the layout in Design view
2. Verify all elements are visible
3. Check spacing and alignment

## ✅ Checkpoint
- [ ] ProgressBar added
- [ ] "Log to Diary" button added
- [ ] Color resources defined
- [ ] Layout preview looks good

---

# STEP 5: Integrate with FoodLogActivity

## 📍 Location
**File:** `app/src/main/java/com/example/swasthyamitra/FoodLogActivity.kt`

## ⏱️ Time Required
30 minutes

## 📝 Instructions

### 5.1 Find showAddFoodOptionsDialog Function
Locate the `showAddFoodOptionsDialog()` function (around line 159)

### 5.2 Update Options Array
**REPLACE** the existing function with this updated version:

```kotlin
private fun showAddFoodOptionsDialog() {
    val options = arrayOf(
        "🍛 Search Indian Foods",
        "📝 Manual Entry",
        "📷 Scan Barcode",
        "📸 Capture Food Photo"  // NEW OPTION
    )
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
                3 -> {  // NEW: Open camera for food photo
                    val intent = Intent(this, FoodPhotoCaptureActivity::class.java)
                    startActivity(intent)
                }
            }
        }
        .show()
}
```

### 5.3 Verify Integration
1. Build the project
2. Check for errors
3. Verify the dialog now has 4 options

## ✅ Checkpoint
- [ ] showAddFoodOptionsDialog() updated
- [ ] Camera option added
- [ ] No compilation errors

---

# STEP 6: Add Homepage Quick Action

## 📍 Location
**File:** `app/src/main/java/com/example/swasthyamitra/homepage.kt`

## ⏱️ Time Required
45 minutes

## 📝 Instructions

### 6.1 Option A: Add FAB (Floating Action Button)

#### 6.1.1 Update Layout XML
Open `app/src/main/res/layout/activity_homepage.xml` and add:

```xml
<com.google.android.material.floatingactionbutton.FloatingActionButton
    android:id="@+id/fabCaptureFood"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_gravity="bottom|end"
    android:layout_margin="16dp"
    android:src="@android:drawable/ic_menu_camera"
    android:contentDescription="Capture food photo"
    app:backgroundTint="@color/purple_500" />
```

#### 6.1.2 Add Click Listener in homepage.kt
Find the `onCreate` or `setupUI` function and add:

```kotlin
binding.fabCaptureFood.setOnClickListener {
    val intent = Intent(this, FoodPhotoCaptureActivity::class.java)
    startActivity(intent)
}
```

### 6.2 Option B: Add Quick Action Card

#### 6.2.1 Add Card to Layout
Add this card in your quick actions section:

```xml
<androidx.cardview.widget.CardView
    android:id="@+id/cardCaptureFood"
    android:layout_width="0dp"
    android:layout_height="wrap_content"
    android:layout_weight="1"
    android:layout_margin="8dp"
    app:cardCornerRadius="12dp"
    app:cardElevation="4dp">
    
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:padding="16dp"
        android:gravity="center">
        
        <ImageView
            android:layout_width="48dp"
            android:layout_height="48dp"
            android:src="@android:drawable/ic_menu_camera"
            android:tint="@color/purple_500" />
        
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Capture Food"
            android:textSize="14sp"
            android:textStyle="bold"
            android:layout_marginTop="8dp" />
    </LinearLayout>
</androidx.cardview.widget.CardView>
```

#### 6.2.2 Add Click Listener
```kotlin
binding.cardCaptureFood.setOnClickListener {
    val intent = Intent(this, FoodPhotoCaptureActivity::class.java)
    startActivity(intent)
}
```

### 6.3 Choose Your Preference
- **FAB:** Quick, always visible, modern UI
- **Card:** Integrated with other quick actions, more discoverable

## ✅ Checkpoint
- [ ] Quick action added to homepage
- [ ] Click listener implemented
- [ ] Navigation to camera works

---

# STEP 7: Test the Feature

## ⏱️ Time Required
3 hours

## 📝 Instructions

### 7.1 Build and Install App

```bash
# Clean build
./gradlew clean

# Build debug APK
./gradlew assembleDebug

# Install on device
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

### 7.2 Basic Functionality Tests

#### Test 1: Camera Capture
- [ ] Open app
- [ ] Navigate to Food Log → Add Food → Capture Food Photo
- [ ] Grant camera permission if prompted
- [ ] Take photo of a meal
- [ ] Verify photo preview displays

#### Test 2: Gallery Selection
- [ ] Click "Choose from Gallery"
- [ ] Select existing food photo
- [ ] Verify photo preview displays

#### Test 3: ML Kit Analysis
- [ ] Click "Analyze Food"
- [ ] Wait for processing (2-3 seconds)
- [ ] Verify detected food items display
- [ ] Check confidence scores (should be >60%)
- [ ] Verify calorie calculations

#### Test 4: Log to Diary
- [ ] Click "Log to Food Diary"
- [ ] Verify success message
- [ ] Navigate to Food Log
- [ ] Confirm entry appears in list
- [ ] Check homepage graph updates

### 7.3 Edge Case Tests

#### Test 5: No Food Detected
- [ ] Take photo of non-food item (book, table, etc.)
- [ ] Click "Analyze Food"
- [ ] Verify "No Food Detected" dialog appears
- [ ] Test "Manual Entry" option
- [ ] Test "Try Again" option

#### Test 6: Poor Image Quality
- [ ] Take blurry photo
- [ ] Take dark/low-light photo
- [ ] Verify graceful handling

#### Test 7: Multiple Foods
- [ ] Take photo of Indian thali (rice, dal, sabzi, roti)
- [ ] Verify multiple items detected
- [ ] Check total calorie calculation

#### Test 8: Offline Mode
- [ ] Turn off WiFi and mobile data
- [ ] Take photo and analyze
- [ ] Verify ML Kit still works (on-device)
- [ ] Verify nutrition lookup uses cached data

### 7.4 Real Food Tests

Test with these common foods:

- [ ] **Indian Thali** (rice, dal, sabzi, roti)
- [ ] **Pizza** slice
- [ ] **Salad** bowl
- [ ] **Biryani**
- [ ] **Sandwich**
- [ ] **Fruit** plate
- [ ] **Breakfast** (eggs, toast, etc.)
- [ ] **Snacks** (samosa, pakora, etc.)

### 7.5 Performance Tests

#### Test 9: Processing Speed
- [ ] Measure time from "Analyze" click to results
- [ ] Target: < 3 seconds
- [ ] Record actual time: _____ seconds

#### Test 10: Memory Usage
- [ ] Open Android Studio Profiler
- [ ] Monitor memory during analysis
- [ ] Target: < 50 MB increase
- [ ] Record actual usage: _____ MB

### 7.6 User Experience Tests

#### Test 11: Complete Flow
Time the complete user journey:
1. Open app
2. Navigate to camera
3. Take photo
4. Analyze
5. Log to diary
6. View on homepage

**Target:** < 30 seconds total
**Actual:** _____ seconds

### 7.7 Error Handling Tests

#### Test 12: Permission Denied
- [ ] Deny camera permission
- [ ] Verify error message displays
- [ ] Verify redirect to settings works

#### Test 13: Network Error
- [ ] Turn off internet during OpenFoodFacts lookup
- [ ] Verify fallback to estimation works
- [ ] Verify no crashes

#### Test 14: Firestore Error
- [ ] Simulate Firestore offline
- [ ] Attempt to log food
- [ ] Verify error message
- [ ] Verify data cached locally

## ✅ Testing Checkpoint
- [ ] All basic functionality tests passed
- [ ] Edge cases handled gracefully
- [ ] Real food detection works
- [ ] Performance meets targets
- [ ] User experience is smooth
- [ ] Error handling is robust

---

# STEP 8: Deploy and Monitor

## ⏱️ Time Required
1 hour

## 📝 Instructions

### 8.1 Add Firebase Analytics Events

Add tracking to `FoodPhotoCaptureActivity.kt`:

```kotlin
// In analyzeFoodImage() success block
FirebaseAnalytics.getInstance(this).logEvent("food_photo_analyzed") {
    param("num_foods_detected", detectedFoods.size.toLong())
    param("total_calories", totalNutrition.totalCalories.toLong())
}

// In logDetectedFoodsToDiary() success block
FirebaseAnalytics.getInstance(this).logEvent("food_photo_logged") {
    param("num_foods", detectedFoods.size.toLong())
    param("total_calories", totalNutrition.totalCalories.toLong())
}
```

### 8.2 Add Crashlytics Error Logging

Wrap ML Kit calls with error logging:

```kotlin
try {
    val result = foodRecognitionService.analyzeFoodImage(capturedImageUri!!)
    // ... existing code ...
} catch (e: Exception) {
    FirebaseCrashlytics.getInstance().recordException(e)
    // ... existing error handling ...
}
```

### 8.3 Create Release Build

```bash
# Build release APK
./gradlew assembleRelease

# Sign APK (if configured)
# Upload to Play Store or distribute
```

### 8.4 Monitor Metrics

Track these in Firebase Console:

#### Adoption Metrics
- `food_photo_analyzed` event count
- Unique users using feature
- Daily active users trend

#### Performance Metrics
- Average processing time
- Success rate (analyzed vs errors)
- Detection accuracy (user edits)

#### User Behavior
- Most detected foods
- Average calories per meal
- Time of day usage patterns

### 8.5 Set Up Alerts

Configure Firebase Alerts for:
- Crash rate > 1%
- Error rate > 5%
- Processing time > 5 seconds

## ✅ Deployment Checkpoint
- [ ] Analytics events added
- [ ] Crashlytics configured
- [ ] Release build created
- [ ] Monitoring dashboard set up
- [ ] Alerts configured

---

# 🎉 COMPLETION CHECKLIST

## Core Implementation
- [ ] Step 1: ML Kit dependency added
- [ ] Step 2: AIFoodRecognitionService created
- [ ] Step 3: FoodPhotoCaptureActivity updated
- [ ] Step 4: Layout XML updated
- [ ] Step 5: FoodLogActivity integrated
- [ ] Step 6: Homepage quick action added

## Testing
- [ ] Basic functionality tests passed
- [ ] Edge case tests passed
- [ ] Real food tests completed
- [ ] Performance benchmarks met
- [ ] Error handling verified

## Deployment
- [ ] Analytics configured
- [ ] Crashlytics set up
- [ ] Release build created
- [ ] Monitoring active

## Documentation
- [ ] Code commented
- [ ] README updated
- [ ] User guide created (optional)

---

# 📊 Success Metrics

After 1 week of deployment, check:

| Metric | Target | Actual |
|--------|--------|--------|
| Feature Adoption | 40% of users | ___% |
| Detection Accuracy | 75% accepted | ___% |
| Processing Speed | < 3 seconds | ___ sec |
| User Satisfaction | 4+ stars | ___ stars |
| Crash Rate | < 1% | ___% |

---

# 🚀 Next Steps (Future Enhancements)

### Phase 2 (Month 2-3)
- [ ] Add portion size adjustment (1x, 2x, 0.5x)
- [ ] Allow users to edit detected items
- [ ] Build user correction learning system
- [ ] Expand Indian food database

### Phase 3 (Month 4-6)
- [ ] Train custom TensorFlow Lite model
- [ ] Add real-time camera detection
- [ ] Implement portion size estimation
- [ ] Add restaurant menu scanning

### Phase 4 (Month 7-12)
- [ ] Social sharing of meals
- [ ] Community food database
- [ ] Nutrition coaching AI
- [ ] Meal planning integration

---

# 🆘 Troubleshooting Guide

## Issue: ML Kit not detecting food
**Solution:**
- Ensure good lighting
- Take photo from directly above
- Fill frame with food
- Avoid cluttered backgrounds

## Issue: Build errors after adding dependency
**Solution:**
```bash
./gradlew clean
./gradlew build --refresh-dependencies
```

## Issue: Camera permission crashes
**Solution:**
- Check AndroidManifest.xml has camera permission
- Verify runtime permission handling
- Test on Android 6.0+ device

## Issue: Slow processing (>5 seconds)
**Solution:**
- Compress image before processing
- Check device has sufficient RAM
- Verify ML Kit model downloaded

## Issue: Firestore save fails
**Solution:**
- Check internet connection
- Verify Firebase configuration
- Check Firestore security rules
- Implement local caching

---

# 📞 Support Resources

- **ML Kit Documentation:** https://developers.google.com/ml-kit/vision/image-labeling
- **CameraX Guide:** https://developer.android.com/training/camerax
- **Firebase Firestore:** https://firebase.google.com/docs/firestore
- **Project Documentation:** See `DOCS/` folder

---

**🎯 You're Ready to Build!**

Start with Step 1 and work through each step sequentially. Take breaks between major steps. Test thoroughly at each checkpoint.

**Estimated Completion Time:** 8-10 hours

**Good luck! 🚀**
