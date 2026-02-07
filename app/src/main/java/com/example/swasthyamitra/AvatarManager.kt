package com.example.swasthyamitra

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri

enum class ProfileMode {
    GALLERY_PHOTO,
    PRESET_AVATAR
}

class AvatarManager(private val context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("avatar_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_GALLERY_URI = "gallery_uri"
        private const val KEY_AVATAR_ID = "avatar_id"
        private const val KEY_MODE = "profile_mode"
    }

    fun saveGalleryUri(uri: Uri) {
        prefs.edit()
            .putString(KEY_GALLERY_URI, uri.toString())
            .putString(KEY_MODE, ProfileMode.GALLERY_PHOTO.name)
            .apply()
    }

    fun getGalleryUri(): Uri? {
        val uriString = prefs.getString(KEY_GALLERY_URI, null)
        return if (uriString != null) Uri.parse(uriString) else null
    }

    fun saveAvatarId(avatarId: String) {
        prefs.edit()
            .putString(KEY_AVATAR_ID, avatarId)
            .putString(KEY_MODE, ProfileMode.PRESET_AVATAR.name)
            .apply()
    }

    fun getAvatarId(): String? {
        return prefs.getString(KEY_AVATAR_ID, null)
    }
    
    fun getProfileMode(): ProfileMode {
        val modeStr = prefs.getString(KEY_MODE, ProfileMode.GALLERY_PHOTO.name)
        return try {
            ProfileMode.valueOf(modeStr ?: ProfileMode.GALLERY_PHOTO.name)
        } catch (e: Exception) {
            ProfileMode.GALLERY_PHOTO
        }
    }

    fun getDrawableId(resName: String): Int {
        if (resName == "none") return 0
        return context.resources.getIdentifier(resName, "drawable", context.packageName)
    }
}
