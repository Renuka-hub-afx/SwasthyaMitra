# Feature: Smart Utility Tools

## 🛠️ Overview

The Smart Utility Tools in SwasthyaMitra are designed to reduce the friction of manual data entry and provide real-time health coaching. This module leverages hardware sensors and machine learning to automate logging and improve daily habits.

---

## 🏗️ Technical Architecture

### **1. AI Barcode Scanner (`BarcodeScannerActivity.kt`)**

This utility allows users to scan packaged foods to instantly retrieve nutritional data.

- **Engine**: Google ML Kit (Vision API).
- **Camera Layer**: Android CameraX (LifeCycle-aware).
- **Data Source**: OpenFoodFacts API (Global Database).

### **2. Posture & Hydration Coach (`CoachActivity.kt`)**

A specialized utility that acts as a real-time monitor for physical well-being.

- **Posture Control**: Uses the device's **Accelerometer** to detect slouching.
- **Hydration Math**: Dynamic logic that suggests water intake based on ambient temperature and physical activity.

### **3. Photo Logging Asset (`FoodPhotoCaptureActivity.kt`)**

Ensures every food photo is captured with standard metadata for potential later analysis.

---

## 🧠 Core Logic & Implementation

### **1. Real-time Barcode Analysis**

The app uses a non-blocking `ImageAnalysis.Analyzer` to process camera frames in real-time.

```kotlin
// From BarcodeScannerActivity.kt
private class BarcodeAnalyzer(private val listener: (List<Barcode>) -> Unit) : ImageAnalysis.Analyzer {
    private val scanner = BarcodeScanning.getClient()

    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image ?: return
        val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

        scanner.process(image)
            .addOnSuccessListener { barcodes ->
                if (barcodes.isNotEmpty()) listener(barcodes)
            }
            .finally { imageProxy.close() }
    }
}
```

### **2. Accelerometer Posture Detection**

The coach monitors the gravity vector on the Y-axis to determine how the user is holding their device or sitting.

```kotlin
// From CoachActivity.kt
private fun checkPosture(event: SensorEvent) {
    val y = event.values[1] // Gravity on Y-axis
    val x = event.values[0]

    // High Y-value with low X-value indicates phone tilt suggesting slouching
    if (y > 7.0 && Math.abs(x) < 2.0) {
        tvPostureAlert.text = "⚠️ Slouching Detected! Straighten up."
    } else {
        tvPostureAlert.text = "Posture: Good ✅"
    }
}
```

### **3. Contextual Hydration Prompts**

A conditional logic engine that evaluates external environmental factors.

```kotlin
// From CoachActivity.kt
private fun checkHydrationLogic(temperature: Int, distanceKm: Double) {
    if (temperature > 30 && distanceKm >= 5) {
        tvHydration.text = "It's hot ($temperature°C). Drink 500ml water now!"
    } else {
        tvHydration.text = "Hydration: Remember to drink water regularly."
    }
}
```

---

## ✅ Feature Capabilities

- **Global Search**: Supports EAN/UPC barcodes for international products.
- **Auto-Logging**: Once a product is found via scan, it is automatically saved to the user's `foodLogs` in Firestore.
- **Hardware Agnostic**: Degrades gracefully if sensors like the Accelerometer are missing.
- **Battery Efficient**: Uses `SENSOR_DELAY_UI` and throttled posture checks (every 5 seconds) to save energy.
