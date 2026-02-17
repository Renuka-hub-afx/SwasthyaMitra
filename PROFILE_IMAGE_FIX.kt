// Add this function to homepage.kt before the closing brace of the class

/**
 * Load profile image from AvatarManager
 * Supports both gallery images and preset avatars
 */
private fun loadProfileImage() {
    try {
        // Initialize AvatarManager if not already initialized
        if (!::avatarManager.isInitialized) {
            avatarManager = AvatarManager(this)
        }
        
        val profileImageView = findViewById<android.widget.ImageView>(R.id.iv_user_profile)
        val mode = avatarManager.getProfileMode()
        val galleryUri = avatarManager.getGalleryUri()
        val avatarId = avatarManager.getAvatarId()
        
        Log.d("Homepage", "Loading profile image - Mode: $mode, GalleryUri: $galleryUri, AvatarId: $avatarId")
        
        when (mode) {
            ProfileMode.GALLERY_PHOTO -> {
                if (galleryUri != null) {
                    try {
                        profileImageView.setImageURI(galleryUri)
                        profileImageView.scaleType = android.widget.ImageView.ScaleType.CENTER_CROP
                        Log.d("Homepage", "Gallery image loaded successfully")
                    } catch (e: Exception) {
                        Log.e("Homepage", "Failed to load gallery image: ${e.message}")
                        // Fall back to default avatar
                        profileImageView.setImageResource(R.drawable.coach)
                    }
                } else {
                    Log.w("Homepage", "Gallery mode but no URI found, using default")
                    profileImageView.setImageResource(R.drawable.coach)
                }
            }
            ProfileMode.PRESET_AVATAR -> {
                if (avatarId != null) {
                    val avatarResId = getAvatarDrawable(avatarId)
                    if (avatarResId != 0) {
                        profileImageView.setImageResource(avatarResId)
                        profileImageView.scaleType = android.widget.ImageView.ScaleType.FIT_CENTER
                        Log.d("Homepage", "Avatar loaded successfully: $avatarId")
                    } else {
                        Log.w("Homepage", "Avatar ID not found: $avatarId, using default")
                        profileImageView.setImageResource(R.drawable.coach)
                    }
                } else {
                    Log.w("Homepage", "Avatar mode but no ID found, using default")
                    profileImageView.setImageResource(R.drawable.coach)
                }
            }
        }
    } catch (e: Exception) {
        Log.e("Homepage", "Error loading profile image: ${e.message}")
        // Set default image on error
        findViewById<android.widget.ImageView>(R.id.iv_user_profile).setImageResource(R.drawable.coach)
    }
}

// Also add this to onResume() to refresh the profile image when returning to the homepage:
// loadProfileImage()
