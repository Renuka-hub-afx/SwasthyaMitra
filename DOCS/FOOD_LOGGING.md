# Food Logging & Nutrition

## Overview
The Food Logging feature (`FoodLogActivity.kt`) allows users to track their daily nutritional intake. It supports manual entry, searching a local database of Indian foods, and querying an external API for global products.

## Key Components

### 1. Data Sources
*   **Indian Food Database:** A local repository (`IndianFoodRepository`) containing common Indian dishes with preset nutritional values.
*   **OpenFoodFacts API:** Integrated to search for packaged products by barcode or name (`OpenFoodFactsApi`).
*   **Manual Entry:** Users can input custom calories and macros.

### 2. Database Structure
*   **Collection:** `users/{userId}/foodLogs`
*   **Document Structure:**
    *   `logId`: Unique ID
    *   `foodName`: String
    *   `calories`: Number
    *   `protein`, `carbs`, `fat`: Number
    *   `mealType`: "Breakfast", "Lunch", "Dinner", "Snack"
    *   `date`: "YYYY-MM-DD"
    *   `timestamp`: Long

### 3. Features
*   **Search:** Unified search adapter combining local and API results.
*   **Meal Type Suggestion:** Automatically suggests meal type based on time of day.
*   **Daily Summary:** Calculates total calories and macro distribution for the current day.
*   **History:** Users can view food logs from previous dates (currently loads all history sorted by date).

## API Integration
*   **OpenFoodFacts:** Used for barcode scanning (via `BarcodeScannerActivity`) and text search.
