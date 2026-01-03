package com.example.swasthyamitra

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.swasthyamitra.databinding.ActivityFoodPhotoCaptureBinding
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

class FoodPhotoCaptureActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFoodPhotoCaptureBinding
    private var capturedImageUri: Uri? = null
    
    companion object {
        private const val CAMERA_PERMISSION_CODE = 100
        private const val CAMERA_REQUEST_CODE = 101
        private const val GALLERY_REQUEST_CODE = 102
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodPhotoCaptureBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA_REQUEST_CODE)
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                CAMERA_REQUEST_CODE -> {
                    val photo = data?.extras?.get("data") as? Bitmap
                    if (photo != null) {
                        binding.ivFoodPreview.setImageBitmap(photo)
                        capturedImageUri = saveBitmapToFile(photo)
                        binding.btnAnalyzeFood.isEnabled = true
                    }
                }
                GALLERY_REQUEST_CODE -> {
                    val selectedImageUri = data?.data
                    if (selectedImageUri != null) {
                        binding.ivFoodPreview.setImageURI(selectedImageUri)
                        capturedImageUri = selectedImageUri
                        binding.btnAnalyzeFood.isEnabled = true
                    }
                }
            }
        }
    }

    private fun saveBitmapToFile(bitmap: Bitmap): Uri {
        val file = File(cacheDir, "food_image_${System.currentTimeMillis()}.jpg")
        FileOutputStream(file).use { out ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
        }
        return Uri.fromFile(file)
    }

    private fun analyzeFoodImage() {
        binding.tvAnalysisResult.text = "Analyzing food..."
        
        lifecycleScope.launch {
            // TODO: Send image to ML model or API for food recognition
            // For now, show placeholder
            binding.tvAnalysisResult.text = """
                Detected: Grilled Chicken Salad
                
                Estimated Nutrition:
                • Calories: 350 kcal
                • Protein: 35g
                • Carbs: 20g
                • Fat: 15g
                
                Note: ML-based food recognition coming soon!
            """.trimIndent()
        }
    }
}
