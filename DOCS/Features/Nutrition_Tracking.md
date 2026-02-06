# Feature: Nutrition Tracking (Indian Food Focus)

## 🍱 Overview

The Nutrition Tracking module is the data-logging heart of the application. It provides a seamless way for users to log meals with high accuracy for Indian dishes while maintaining a global product database as a fallback.

---

## 🛠️ File Architecture

### **1. The Interaction Layer**

- **`FoodLogActivity.kt`**: The central UI where users view their today's logs and access various logging modes.
- **`activity_food_log.xml`**: Layout featuring the daily macros dashboard and history list.
- **`FoodLogAdapter.kt`**: Renders the daily log list with meal-specific emojis.

### **2. The Discovery Layer (Search & Scanning)**

- **`IndianFoodRepository.kt`**: Manages the local cache of 1,000+ Indian food items.
- **`OpenFoodFactsApi.kt`**: Integration with a global API for packaged product lookup.
- **`BarcodeScannerActivity.kt`**: ML Kit-powered scanning that identifies packaged items instantly.

### **3. The Data Layer**

- **`FoodLog.kt`**: Model used for individual entries saved to Firestore.
- **`IndianFood.kt`**: Model for internal database items.

---

## 🧠 Core Logic & Implementation

### **1. Unified Search Strategy**

The app implements a "First Local, then Global" search strategy. It prioritizes the local `IndianFoodRepository` to ensure users find local dishes like "Paneer Tikka" with accurate cultural macros.

```kotlin
// From FoodLogActivity.kt - Unified Search Logic
searchJob = lifecycleScope.launch {
    // 1. Search local 🇮🇳 database
    val indianResults = foodRepository.searchFood(query)
    
    // 2. Search global 🌍 OpenFoodFacts API
    val openFoodResults = searchOpenFoodFacts(query)
    
    // 3. Combine and display results
    searchResults.clear()
    searchResults.addAll(indianResults)
    searchResults.addAll(openFoodResults)
}
```

### **2. Real-time Nutritional Aggregation**

As food logs are fetched from Firestore, the app performs real-time summation of macros to provide instant feedback.

```kotlin
// Macro totaling logic
fun updateSummary() {
    var totalCalories = 0.0
    var totalProtein = 0.0
    // ...
    foodLogs.forEach { log ->
        totalCalories += log.calories
        totalProtein += log.protein
        // ...
    }
}
```

### **3. Local Data Grounding**

The repository uses a recursive guard to load data from JSON or CSV assets into a memory-efficient cache. This data is also used as **Grounding Context** for the AI Diet System.

```kotlin
// From IndianFoodRepository.kt
fun loadFoodDatabase() {
    if (foodCache.isNotEmpty() || isLoading) return
    val inputStream = context.assets.open("food_data.json")
    val jsonString = reader.use { it.readText() }
    val jsonArray = JSONArray(jsonString)
    // Parse objects into IndianFood model...
}
```

---

## ✅ Feature Set

- **Multi-Mode Logging**: Search, Scan, or Manual Entry.
- **Context-Aware Suggestions**: The app suggests the meal type (Breakfast/Lunch/Dinner) based on the current system time.
- **Macro Breakdown**: Tracks Calories, Protein, Carbs, and Fat for every entry.
- **History Management**: Browse through today's meal sequence and remove accidental entries with ease.
