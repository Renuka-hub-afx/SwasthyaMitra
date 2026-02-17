# Barcode Scanner for Nutrition

## 📋 Overview

The Barcode Scanner feature uses ML Kit to scan product barcodes, retrieve nutritional information, and quickly log foods to the user's diary.

---

## 🎯 Purpose & Importance

- **Speed**: Log packaged foods in seconds
- **Accuracy**: Exact nutritional data from product database
- **Convenience**: No manual entry required
- **Education**: Learn about food nutrition

---

## 🔄 How It Works

```
User Opens BarcodeScannerActivity
    ↓
Request Camera Permission
    ↓
Start Camera Preview
    ↓
User Points Camera at Barcode
    ↓
ML Kit Detects Barcode
    ↓
Extract Barcode Number
    ↓
Query Nutrition Database (OpenFoodFacts API)
    ↓
Retrieve Product Info:
  - Name
  - Brand
  - Calories
  - Protein, Carbs, Fats
  - Serving Size
    ↓
Display Nutrition Information
    ↓
User Clicks "Log Food"
    ↓
Save to Firestore with barcode reference
```

---

## 💻 Technical Implementation

### ML Kit Barcode Scanning

```kotlin
class BarcodeScannerActivity : AppCompatActivity() {
    
    private val scanner = BarcodeScanning.getClient()
    
    private fun processImage(image: InputImage) {
        scanner.process(image)
            .addOnSuccessListener { barcodes ->
                for (barcode in barcodes) {
                    val rawValue = barcode.rawValue
                    when (barcode.valueType) {
                        Barcode.TYPE_PRODUCT -> {
                            lookupNutrition(rawValue)
                        }
                    }
                }
            }
            .addOnFailureListener { e ->
                Log.e("BarcodeScanner", "Scanning failed", e)
            }
    }
    
    private fun lookupNutrition(barcode: String) {
        // Call OpenFoodFacts API
        val url = "https://world.openfoodfacts.org/api/v0/product/$barcode.json"
        
        lifecycleScope.launch {
            try {
                val response = makeApiCall(url)
                val product = parseProductData(response)
                displayNutritionInfo(product)
            } catch (e: Exception) {
                Toast.makeText(this@BarcodeScannerActivity, 
                    "Product not found", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    private fun displayNutritionInfo(product: Product) {
        productNameText.text = product.name
        brandText.text = product.brand
        caloriesText.text = "${product.calories} kcal"
        proteinText.text = "${product.protein}g protein"
        carbsText.text = "${product.carbs}g carbs"
        fatsText.text = "${product.fats}g fats"
        
        logFoodButton.setOnClickListener {
            logFoodFromBarcode(product)
        }
    }
}
```

### Data Models

```kotlin
data class Product(
    val barcode: String,
    val name: String,
    val brand: String,
    val calories: Int,
    val protein: Int,
    val carbs: Int,
    val fats: Int,
    val servingSize: String
)
```

---

## 🔌 APIs Used

### OpenFoodFacts API
- **Endpoint**: `https://world.openfoodfacts.org/api/v0/product/{barcode}.json`
- **Free**: No API key required
- **Database**: 2+ million products worldwide

### ML Kit Barcode Scanning
- **Formats Supported**: EAN-13, UPC-A, QR Code, etc.
- **On-device**: Works offline
- **Fast**: Real-time detection

---

## 🚀 Future Improvements

1. **Custom Database**: Build local Indian product database
2. **Manual Entry**: Add products not in database
3. **History**: View previously scanned items
4. **Favorites**: Save frequently scanned products
5. **Batch Scanning**: Scan multiple items at once

---

**[← Back to Main README](../README.md)**
