package com.example.swasthyamitra

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri

// Determines whether the user's profile picture is a gallery photo or a preset avatar illustration
enum class ProfileMode {
    GALLERY_PHOTO,   // user picked from device gallery
    PRESET_AVATAR    // user selected one of the built-in avatar drawables
}

// Manages local persistence (SharedPreferences) of the selected avatar or gallery URI
class AvatarManager(private val context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("avatar_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_GALLERY_URI = "gallery_uri"
        private const val KEY_AVATAR_ID = "avatar_id"
        private const val KEY_MODE = "profile_mode"
    }

    // Saves a gallery photo URI to prefs and sets mode to GALLERY_PHOTO
    fun saveGalleryUri(uri: Uri) {
        prefs.edit()
            .putString(KEY_GALLERY_URI, uri.toString())
            .putString(KEY_MODE, ProfileMode.GALLERY_PHOTO.name)
            .apply()
    }

    // Restores the saved gallery URI (null if none saved or user switched to preset avatar)
    fun getGalleryUri(): Uri? {
        val uriString = prefs.getString(KEY_GALLERY_URI, null)
        return if (uriString != null) Uri.parse(uriString) else null
    }

    // Saves the resource name of the chosen preset avatar (e.g. "avatar3") and sets mode to PRESET_AVATAR
    fun saveAvatarId(avatarId: String) {
        prefs.edit()
            .putString(KEY_AVATAR_ID, avatarId)
            .putString(KEY_MODE, ProfileMode.PRESET_AVATAR.name)
            .apply()
    }

    // Returns the saved avatar resource name, or null if none was selected
    fun getAvatarId(): String? {
        return prefs.getString(KEY_AVATAR_ID, null)
    }
    
    // Returns whether the app should show a gallery photo or a preset avatar on the profile screen
    fun getProfileMode(): ProfileMode {
        val modeStr = prefs.getString(KEY_MODE, ProfileMode.GALLERY_PHOTO.name)
        return try {
            ProfileMode.valueOf(modeStr ?: ProfileMode.GALLERY_PHOTO.name)
        } catch (e: Exception) {
            ProfileMode.GALLERY_PHOTO  // default if corrupted pref value
        }
    }

    // Resolves a drawable resource name (e.g. "avatar3") to its int ID; returns 0 for "none"
    fun getDrawableId(resName: String): Int {
        if (resName == "none") return 0
        return context.resources.getIdentifier(resName, "drawable", context.packageName)
    }
}
