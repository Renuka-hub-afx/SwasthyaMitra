# Indian Food Search & Nutrition Auto-Fill Guide

## 🎯 Overview
This feature allows users to search from a comprehensive database of **1000+ Indian food items** and automatically fetch accurate nutrition data, eliminating the need for manual entry.

---

## 📋 How It Works

### 1. **Food Database Loading**
- **What happens**: When the app opens FoodLogActivity, it loads the Excel file `Indian_Food_Nutrition_Processed.xlsx` from the assets folder
- **Process**:
  - Uses Apache POI library to read the Excel file
  - Parses each row into an `IndianFood` object
  - Stores all food items in memory for fast searching
  - Falls back to 10 default foods if Excel loading fails

- **Data Structure** (IndianFood model):
  ```kotlin
  data class IndianFood(
      val foodName: String,        // e.g., "Rice", "Dal", "Paneer"
      val servingSize: String,     // e.g., "1 cup (150g)", "100g"
      val calories: Int,           // Total calories
      val protein: Double,         // Protein in grams
      val carbs: Double,           // Carbohydrates in grams
      val fat: Double,             // Fat in grams
      val fiber: Double,           // Fiber in grams
      val category: String         // e.g., "Grains", "Dairy", "Breakfast"
  )
  ```

### 2. **Food Search Functionality**
- **What happens**: User types food name and gets instant search results
- **Process**:
  - Real-time search as user types (TextWatcher)
  - Case-insensitive partial matching
  - Sorts results by relevance:
    1. Exact matches first
    2. Starts-with matches second
    3. Contains matches last
  - Limits to top 20 results for performance

- **Search Algorithm**:
  ```
  Input: "rice"
  
  Step 1: Normalize query → "rice" (lowercase, trim)
  Step 2: Filter database → Foods containing "rice"
  Step 3: Sort by relevance:
     - "Rice" (exact match) → Priority 0
     - "Rice Pudding" (starts with) → Priority 1
     - "Fried Rice" (contains) → Priority 2
  Step 4: Return top 20 results
  ```

### 3. **Nutrition Value Calculation**
- **What happens**: Selected food's nutrition data is automatically populated
- **Process**:
  - When user selects a food from search results, the app:
    1. Retrieves the `IndianFood` object
    2. Pre-fills all nutrition fields in the manual entry dialog
    3. Shows the standard serving size from database
    4. User can adjust serving size if needed
    5. Nutrition values are recalculated proportionally (if serving changed)

- **Example**:
  ```
  Selected: "Rice"
  Database Values (per 1 cup / 150g):
    - Calories: 205
    - Protein: 4.3g
    - Carbs: 45.0g
    - Fat: 0.4g
  
  If user changes serving to "2 cups":
    - Calories: 205 × 2 = 410
    - Protein: 4.3 × 2 = 8.6g
    - Carbs: 45.0 × 2 = 90.0g
    - Fat: 0.4 × 2 = 0.8g
  ```

### 4. **Data Storage in Firebase**
- **What happens**: Food log is saved to Firestore with all details
- **Process**:
  - Creates `FoodLog` object with:
    ```kotlin
    FoodLog(
        logId = auto-generated UUID
        userId = current user's Firebase UID
        foodName = "Rice" (from selected food)
        barcode = null (not from barcode)
        photoUrl = null (not from photo)
        calories = 205 (Int, from IndianFood)
        protein = 4.3 (Double, from IndianFood)
        carbs = 45.0 (Double, from IndianFood)
        fat = 0.4 (Double, from IndianFood)
        servingSize = "1 cup (150g)" (from IndianFood)
        mealType = "Breakfast" (auto-suggested based on time)
        timestamp = System.currentTimeMillis() (Long)
        date = "2024-01-15" (String, yyyy-MM-dd)
    )
    ```
  - Saves to Firestore collection: `foodLogs`
  - Document ID = `logId`
  - Refreshes the daily history view

---

## 🔄 Complete User Flow

```
1. User clicks FAB (+) on Food Diary screen
   ↓
2. Dialog appears with 3 options:
   - 🔍 Search Indian Foods ← NEW FEATURE
   - 📝 Manual Entry
   - 📷 Scan Barcode
   ↓
3. User selects "Search Indian Foods"
   ↓
4. Search dialog opens with input field
   ↓
5. User types "dal" → Search results appear instantly:
   - Dal (Legumes) - 198 cal, 14g P, 35g C, 1g F
   - Dal Makhani (Legumes) - 285 cal, 18g P, 30g C, 12g F
   - Dal Tadka (Legumes) - 180 cal, 12g P, 28g C, 5g F
   ↓
6. User taps "Dal"
   ↓
7. Confirmation dialog opens with PRE-FILLED data:
   - Food Name: Dal ✓
   - Calories: 198 ✓
   - Protein: 14.0g ✓
   - Carbs: 35.0g ✓
   - Fat: 1.0g ✓
   - Serving: 1 cup (200g) ✓
   - Meal Type: Lunch (auto-selected based on time)
   ↓
8. User can:
   - Adjust serving size if needed
   - Change meal type
   - Edit any field if needed
   - Click "Save"
   ↓
9. Food log saved to Firebase Firestore
   ↓
10. Daily history refreshes showing new entry
    ↓
11. Summary card updates with total calories/macros
```

---

## 💾 Excel File Structure

### Expected Format:
| Column | Name | Type | Example |
|--------|------|------|---------|
| A | Food Name | String | "Rice" |
| B | Serving Size | String | "1 cup (150g)" |
| C | Calories | Number | 205 |
| D | Protein | Number | 4.3 |
| E | Carbs | Number | 45.0 |
| F | Fat | Number | 0.4 |
| G | Fiber | Number | 0.6 |
| H | Category | String | "Grains" |

### File Location:
```
SwasthyaMitra/
  app/
    src/
      main/
        assets/
          Indian_Food_Nutrition_Processed.xlsx ← Place file here
```

---

## 🛠️ Technical Implementation

### Key Classes:

1. **IndianFoodRepository** (`repository/IndianFoodRepository.kt`)
   - Loads Excel file using Apache POI
   - Provides search functionality
   - Caches food database in memory

2. **FoodLogActivity** (`FoodLogActivity.kt`)
   - Initializes repository on startup
   - Loads database in background (coroutine)
   - Handles search dialog and confirmation

3. **FoodSearchAdapter** (`FoodLogActivity.kt`)
   - RecyclerView adapter for search results
   - Displays food cards with nutrition info
   - Handles item selection

### Dependencies Added:
```gradle
// Apache POI for Excel file reading
implementation 'org.apache.poi:poi:5.2.3'
implementation 'org.apache.poi:poi-ooxml:5.2.3'
```

---

## 🎨 UI Components

### Search Dialog (`dialog_food_search.xml`)
- Search input with Material Design
- RecyclerView for results (scrollable, 300dp height)
- Loading indicator
- Empty state message
- "Manual Entry" fallback button

### Search Result Card (`item_food_search.xml`)
- Food name (bold, purple)
- Category tag
- Nutrition grid: Calories (pink), Protein (red), Carbs (blue), Fat (orange)
- Serving size display
- Clickable card with ripple effect

---

## ⚡ Performance Optimizations

1. **Lazy Loading**: Database loaded only when FoodLogActivity opens
2. **Memory Cache**: Foods stored in List for fast access (no disk I/O after first load)
3. **Result Limiting**: Max 20 search results to avoid UI lag
4. **Async Operations**: Excel parsing in coroutine (non-blocking)
5. **Fallback Data**: 10 default foods if Excel missing

---

## 🔍 Search Examples

| User Input | Top Results |
|------------|-------------|
| `rice` | Rice, Fried Rice, Rice Pudding, Basmati Rice |
| `dal` | Dal, Dal Makhani, Dal Tadka, Moong Dal |
| `paneer` | Paneer, Paneer Butter Masala, Palak Paneer |
| `chicken` | Chicken Curry, Chicken Biryani, Butter Chicken |
| `idli` | Idli, Rava Idli |
| `roti` | Roti, Butter Roti |

---

## 🐛 Error Handling

1. **Excel Not Found**: Falls back to 10 default Indian foods
2. **Malformed Excel Row**: Skips row, continues parsing
3. **Empty Search**: Hides results, shows hint
4. **No Results**: Shows "No results found" message
5. **Network Issues**: Not applicable (offline-first, local database)

---

## 📱 User Benefits

✅ **Speed**: Search + select faster than manual typing  
✅ **Accuracy**: Professional nutrition data from authentic source  
✅ **Ease**: No need to remember calories/macros  
✅ **Offline**: Works without internet (local database)  
✅ **Comprehensive**: 1000+ Indian food items  
✅ **Flexible**: Can still edit values if needed  

---

## 🔄 Future Enhancements (Optional)

- Barcode scanning integration with Indian food database
- Recent/favorite foods quick access
- Multiple serving size presets (e.g., small/medium/large)
- Nutrition value recalculation on serving size change
- Food categories filter (show only Breakfast, Dairy, etc.)
- User-contributed foods (crowd-sourced database expansion)

---

## 📝 Summary

The Indian Food Search feature transforms food logging from a tedious manual process to an instant, accurate, and user-friendly experience. By integrating a comprehensive offline database with smart search and auto-fill capabilities, users can log their meals in seconds while maintaining nutrition tracking accuracy.

**Key Innovation**: Combining offline Excel database, real-time search, and Firebase cloud storage creates a seamless experience that works anywhere, anytime.
