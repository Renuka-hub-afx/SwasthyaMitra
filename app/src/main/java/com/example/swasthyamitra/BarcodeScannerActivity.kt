package com.example.swasthyamitra

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.swasthyamitra.api.OpenFoodFactsApi
import com.example.swasthyamitra.api.Product
import com.example.swasthyamitra.databinding.ActivityBarcodeScannerBinding
import com.example.swasthyamitra.models.FoodLog
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import kotlinx.coroutines.launch
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.Calendar

class BarcodeScannerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBarcodeScannerBinding
    private lateinit var cameraExecutor: ExecutorService
    private var camera: Camera? = null
    
    companion object {
        private const val CAMERA_PERMISSION_CODE = 100
        private const val TAG = "BarcodeScanner"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBarcodeScannerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cameraExecutor = Executors.newSingleThreadExecutor()

        // Check camera permission
        if (checkCameraPermission()) {
            startCamera()
        } else {
            requestCameraPermission()
        }

        binding.btnClose.setOnClickListener {
            finish()
        }
    }

    private fun checkCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA),
            CAMERA_PERMISSION_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCamera()
            } else {
                Toast.makeText(this, "Camera permission required", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.previewView.surfaceProvider)
                }

            // Image Analysis for barcode scanning
            val imageAnalyzer = ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()
                .also {
                    it.setAnalyzer(cameraExecutor, BarcodeAnalyzer { barcodes ->
                        processBarcode(barcodes)
                    })
                }

            // Select back camera
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                // Unbind all use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                camera = cameraProvider.bindToLifecycle(
                    this,
                    cameraSelector,
                    preview,
                    imageAnalyzer
                )

            } catch (e: Exception) {
                Log.e(TAG, "Camera binding failed", e)
            }

        }, ContextCompat.getMainExecutor(this))
    }


    private fun processBarcode(barcodes: List<Barcode>) {
        if (barcodes.isNotEmpty()) {
            val barcode = barcodes[0]
            val barcodeValue = barcode.displayValue ?: return

            runOnUiThread {
                binding.tvBarcodeResult.text = "Barcode: $barcodeValue"
                Toast.makeText(this, "Scanned: $barcodeValue", Toast.LENGTH_SHORT).show()
            }

            // Fetch food data from API
            fetchFoodDataFromBarcode(barcodeValue)
        }
    }

    private fun fetchFoodDataFromBarcode(barcode: String) {
        lifecycleScope.launch {
            try {
                runOnUiThread {
                    binding.tvFoodInfo.text = "Searching for food..."
                }
                
                val api = OpenFoodFactsApi.create()
                val response = api.getProductByBarcode(barcode)
                
                if (response.isSuccessful && response.body()?.status == 1) {
                    val product = response.body()?.product
                    if (product != null) {
                        val foodInfo = """
                            ${product.product_name ?: "Unknown"}
                            Brand: ${product.brands ?: "N/A"}
                            
                            Nutrition (per 100g):
                            • Calories: ${product.nutriments?.`energy-kcal_100g` ?: 0} kcal
                            • Protein: ${product.nutriments?.proteins_100g ?: 0}g
                            • Carbs: ${product.nutriments?.carbohydrates_100g ?: 0}g
                            • Fat: ${product.nutriments?.fat_100g ?: 0}g
                        """.trimIndent()
                        
                        runOnUiThread {
                            binding.tvFoodInfo.text = foodInfo
                        }
                        
                        // Save to database
                        saveFoodLog(barcode, product)
                    }
                } else {
                    runOnUiThread {
                        binding.tvFoodInfo.text = "Food not found in database"
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "API call failed", e)
                runOnUiThread {
                    binding.tvFoodInfo.text = "Error fetching food data: ${e.message}"
                }
            }
        }
    }

    private fun saveFoodLog(barcode: String, product: Product) {
        val authHelper = (application as UserApplication).authHelper
        val userId = authHelper.getCurrentUser()?.uid
        
        if (userId == null) {
            Toast.makeText(this, "Error: User not logged in", Toast.LENGTH_SHORT).show()
            return
        }
        
        val dateFormat = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
        val today = dateFormat.format(java.util.Date())
        
        val foodLog = FoodLog(
            logId = "",
            userId = userId,
            foodName = product.product_name ?: "Unknown",
            barcode = barcode,
            photoUrl = null,
            calories = (product.nutriments?.`energy-kcal_100g` ?: 0.0).toInt(),
            protein = product.nutriments?.proteins_100g ?: 0.0,
            carbs = product.nutriments?.carbohydrates_100g ?: 0.0,
            fat = product.nutriments?.fat_100g ?: 0.0,
            servingSize = product.serving_size ?: "100g",
            mealType = suggestMealType(),
            date = today,
            timestamp = System.currentTimeMillis()
        )
        
        lifecycleScope.launch {
            try {
                val result = authHelper.logFood(foodLog)
                runOnUiThread {
                    result.onSuccess {
                        Toast.makeText(this@BarcodeScannerActivity, "✅ Food logged successfully!", Toast.LENGTH_SHORT).show()
                        finish() // Return to previous screen
                    }
                    result.onFailure { e ->
                        Toast.makeText(this@BarcodeScannerActivity, "❌ Failed: ${e.message}", Toast.LENGTH_LONG).show()
                        Log.e(TAG, "Failed to log food", e)
                    }
                }
            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(this@BarcodeScannerActivity, "❌ Error: ${e.message}", Toast.LENGTH_LONG).show()
                    Log.e(TAG, "Exception logging food", e)
                }
            }
        }
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

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    // Barcode Analyzer Class
    private class BarcodeAnalyzer(
        private val barcodeListener: (List<Barcode>) -> Unit
    ) : ImageAnalysis.Analyzer {

        private val scanner = BarcodeScanning.getClient()

        @androidx.camera.core.ExperimentalGetImage
        override fun analyze(imageProxy: ImageProxy) {
            val mediaImage = imageProxy.image
            if (mediaImage != null) {
                val image = InputImage.fromMediaImage(
                    mediaImage,
                    imageProxy.imageInfo.rotationDegrees
                )

                scanner.process(image)
                    .addOnSuccessListener { barcodes ->
                        if (barcodes.isNotEmpty()) {
                            barcodeListener(barcodes)
                        }
                    }
                    .addOnFailureListener {
                        Log.e(TAG, "Barcode scanning failed", it)
                    }
                    .addOnCompleteListener {
                        imageProxy.close()
                    }
            } else {
                imageProxy.close()
            }
        }
    }
}
