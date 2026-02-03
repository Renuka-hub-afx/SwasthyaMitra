package com.example.swasthyamitra

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class AvatarCustomizationActivity : AppCompatActivity() {

    private lateinit var avatarContainer: FrameLayout
    private lateinit var avatarManager: AvatarManager
    
    private var selectedGalleryUri: Uri? = null

    // Photo Picker Launcher
    private val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            selectedGalleryUri = uri
            renderProfileIdentity()
            
            // Grant persistable permission to URI
            val contentResolver = applicationContext.contentResolver
            val takeFlags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION
            try {
                contentResolver.takePersistableUriPermission(uri, takeFlags)
            } catch (e: Exception) {
                // Not always possible for external media via Photo Picker
            }
        } else {
            Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avatar_customization)

        avatarManager = AvatarManager(this)
        avatarContainer = findViewById(R.id.avatarContainer)
        
        // Initial setup from saved state
        if (avatarManager.getProfileMode() == ProfileMode.GALLERY_PHOTO) {
            selectedGalleryUri = avatarManager.getGalleryUri()
        }
        
        renderProfileIdentity()

        findViewById<View>(R.id.btn_back).setOnClickListener { finish() }
        
        findViewById<MaterialButton>(R.id.btn_upload_gallery).setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        findViewById<MaterialButton>(R.id.btn_save).setOnClickListener {
            if (selectedGalleryUri != null) {
                avatarManager.saveGalleryUri(selectedGalleryUri!!)
                Toast.makeText(this, "Profile identity saved!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Please select a photo from gallery first", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Renders the profile identity (Gallery Photo only).
     */
    private fun renderProfileIdentity() {
        avatarContainer.removeAllViews()
        
        if (selectedGalleryUri != null) {
            val imageView = ImageView(this)
            imageView.layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView.setImageURI(selectedGalleryUri)
            avatarContainer.addView(imageView)
        } else {
            // Default placeholder if no image selected
            val imageView = ImageView(this)
            imageView.layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView.setImageResource(R.drawable.circular_background) // fallback
            avatarContainer.addView(imageView)
        }
    }
}
