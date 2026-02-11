package com.example.swasthyamitra

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.swasthyamitra.ai.AIPantryService
import com.example.swasthyamitra.databinding.ActivitySmartPantryBinding
import kotlinx.coroutines.launch
import java.io.InputStream

class SmartPantryActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySmartPantryBinding
    private var selectedBitmap: Bitmap? = null
    
    companion object {
        private const val CAMERA_PERMISSION_CODE = 200
        private const val CAMERA_REQUEST_CODE = 201
        private const val GALLERY_REQUEST_CODE = 202
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySmartPantryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        // Navigation Logic
        binding.menuHome.setOnClickListener {
            // Return to Homepage
            finish()
        }
        
        binding.menuDashboard.setOnClickListener {
            // Already on a feature, maybe go to logs? For now, just finish to home
            finish()
        }
        
        binding.menuProfile.setOnClickListener {
            // Navigate to Profile
             val intent = Intent(this, UserInfoActivity::class.java)
             startActivity(intent)
        }

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

        binding.btnGenerateRecipe.setOnClickListener {
            if (selectedBitmap != null) {
                generateRecipe()
            } else {
                Toast.makeText(this, "Please select an image first! 📸", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun generateRecipe() {
        val bitmap = selectedBitmap ?: return
        
        // UI State: Loading
        binding.btnGenerateRecipe.isEnabled = false
        binding.layoutLoading.visibility = View.VISIBLE
        binding.cardRecipeResult.visibility = View.GONE
        binding.tvLoadingText.text = "Chef is analyzing your ingredients... 👨‍🍳"

        lifecycleScope.launch {
            val service = AIPantryService.getInstance(this@SmartPantryActivity)
            val result = service.generateRecipeFromImage(bitmap)

            result.onSuccess { recipe ->
                runOnUiThread {
                    binding.layoutLoading.visibility = View.GONE
                    binding.btnGenerateRecipe.isEnabled = true
                    showRecipeResult(recipe)
                }
            }.onFailure { e ->
                runOnUiThread {
                    binding.layoutLoading.visibility = View.GONE
                    binding.btnGenerateRecipe.isEnabled = true
                    Toast.makeText(this@SmartPantryActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun showRecipeResult(recipe: AIPantryService.RecipeResult) {
        binding.cardRecipeResult.visibility = View.VISIBLE
        
        binding.tvRecipeTitle.text = recipe.title
        binding.chipCalories.text = "${recipe.calories} kcal"
        binding.tvIngredientsDetected.text = recipe.ingredientsDetected.joinToString(", ")
        
        val instructionsText = StringBuilder()
        recipe.instructions.forEachIndexed { index, step ->
            instructionsText.append("${index + 1}. $step\n")
        }
        binding.tvInstructions.text = instructionsText.toString()

        // Set up Log Button
        binding.btnLogRecipe.setOnClickListener {
            logRecipeToDiary(recipe)
        }
        
        // Scroll to bottom to show result
        binding.root.post {
            binding.cardRecipeResult.requestFocus()
        }
    }

    private fun logRecipeToDiary(recipe: AIPantryService.RecipeResult) {
        val application = application as? UserApplication
        if (application == null) {
            Toast.makeText(this, "Error: App not initialized", Toast.LENGTH_SHORT).show()
            return
        }
        val authHelper = application.authHelper
        val user = authHelper.getCurrentUser()
        
        if (user == null) {
            Toast.makeText(this, "Please verify you are logged in", Toast.LENGTH_SHORT).show()
            return
        }

        binding.btnLogRecipe.isEnabled = false
        binding.btnLogRecipe.text = "Logging... ⏳"

        lifecycleScope.launch {
            try {
                 val foodLog = com.example.swasthyamitra.models.FoodLog(
                    userId = user.uid,
                    foodName = recipe.title,
                    calories = recipe.calories,
                    protein = recipe.protein.toDouble(),
                    carbs = recipe.carbs.toDouble(),
                    fat = recipe.fat.toDouble(),
                    mealType = "Snack", // Defaulting to Snack for pantry recipes
                    timestamp = System.currentTimeMillis(),
                    date = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(java.util.Date()),
                    servingSize = "1 serving",
                    barcode = null,
                    photoUrl = null
                )

                val result = authHelper.logFood(foodLog)
                
                result.onSuccess {
                    binding.btnLogRecipe.text = "Logged ✅"
                    Toast.makeText(this@SmartPantryActivity, "Logged to Diary! ✅", Toast.LENGTH_SHORT).show()
                    
                    // Return to home after delay
                    binding.root.postDelayed({ finish() }, 1500)
                }.onFailure { e ->
                    binding.btnLogRecipe.isEnabled = true
                    binding.btnLogRecipe.text = "Log to Diary 📖"
                    Toast.makeText(this@SmartPantryActivity, "Failed to log: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                binding.btnLogRecipe.isEnabled = true
                binding.btnLogRecipe.text = "Log to Diary 📖"
                Toast.makeText(this@SmartPantryActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateCompletionHistory(userId: String) {
        val today = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(java.util.Date())
        // Just a simple touch to realtime DB to signify activity if needed, 
        // or we rely on the foodLog existence for other stats.
    }

    // --- Camera & Gallery Logic ---

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
                        setBitmap(photo)
                    }
                }
                GALLERY_REQUEST_CODE -> {
                    val uri = data?.data
                    if (uri != null) {
                        val bitmap = uriToBitmap(uri)
                        if (bitmap != null) {
                            setBitmap(bitmap)
                        }
                    }
                }
            }
        }
    }

    private fun setBitmap(bitmap: Bitmap) {
        selectedBitmap = bitmap
        binding.ivPantryPreview.setImageBitmap(bitmap)
        binding.ivPantryPreview.imageTintList = null // Clear tint so photo is visible
        binding.ivPantryPreview.setPadding(0, 0, 0, 0) // Remove padding to confirm full image
        binding.tvPlaceholderHint.visibility = View.GONE
        binding.btnGenerateRecipe.isEnabled = true
        binding.btnGenerateRecipe.alpha = 1.0f
        
        // Hide previous results if any
        binding.cardRecipeResult.visibility = View.GONE
    }

    private fun uriToBitmap(uri: Uri): Bitmap? {
        return try {
            val inputStream: InputStream? = contentResolver.openInputStream(uri)
            BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
