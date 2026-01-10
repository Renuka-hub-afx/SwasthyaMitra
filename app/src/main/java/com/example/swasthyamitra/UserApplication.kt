package com.example.swasthyamitra

import android.app.Application
import android.util.Log
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.google.firebase.FirebaseApp

class UserApplication : Application() {

    // Firebase Auth Helper - using lazy initialization to prevent lateinit crashes
    val authHelper: FirebaseAuthHelper by lazy {
        FirebaseAuthHelper(this)
    }

    override fun onCreate() {
        super.onCreate()

        try {
            // Initialize Firebase
            FirebaseApp.initializeApp(this)
            Log.d("UserApplication", "Firebase initialized successfully")

            // Trigger authHelper initialization
            val helper = authHelper
            Log.d("UserApplication", "FirebaseAuthHelper initialized successfully")
        } catch (e: Exception) {
            Log.e("UserApplication", "Error initializing Firebase: ${e.message}", e)
            e.printStackTrace()
        }
    }
}