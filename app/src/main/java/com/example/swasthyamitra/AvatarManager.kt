package com.example.swasthyamitra

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri

enum class ProfileMode {
    GALLERY_PHOTO
}

class AvatarManager(private val context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("avatar_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_GALLERY_URI = "gallery_uri"
    }

    fun saveGalleryUri(uri: Uri) {
        prefs.edit().putString(KEY_GALLERY_URI, uri.toString()).apply()
    }

    fun getGalleryUri(): Uri? {
        val uriString = prefs.getString(KEY_GALLERY_URI, null)
        return if (uriString != null) Uri.parse(uriString) else null
    }
    
    fun getProfileMode(): ProfileMode {
        return ProfileMode.GALLERY_PHOTO
    }

    fun getDrawableId(resName: String): Int {
        if (resName == "none") return 0
        return context.resources.getIdentifier(resName, "drawable", context.packageName)
    }
}
