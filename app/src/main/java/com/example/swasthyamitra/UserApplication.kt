package com.example.swasthyamitra

import android.app.Application
import android.util.Log
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.google.firebase.FirebaseApp
import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory
import com.example.swasthyamitra.BuildConfig

class UserApplication : Application() {

    val authHelper: FirebaseAuthHelper by lazy {
        FirebaseAuthHelper(this)
    }

    override fun onCreate() {
        super.onCreate()

        try {
            // 1. Initialize Firebase
            FirebaseApp.initializeApp(this)
            Log.d("UserApplication", "Firebase initialized successfully")

            // 2. Initialize App Check
            val firebaseAppCheck = FirebaseAppCheck.getInstance()
            
            // Use debug provider when in debug mode to allow testing on emulators
            if (BuildConfig.DEBUG) {
                firebaseAppCheck.installAppCheckProviderFactory(
                    com.google.firebase.appcheck.debug.DebugAppCheckProviderFactory.getInstance()
                )
                Log.d("UserApplication", "App Check initialized with Debug Provider")
            } else {
                firebaseAppCheck.installAppCheckProviderFactory(
                    PlayIntegrityAppCheckProviderFactory.getInstance()
                )
                Log.d("UserApplication", "App Check initialized with Play Integrity")
            }

            // Trigger authHelper initialization
            val helper = authHelper
            Log.d("UserApplication", "FirebaseAuthHelper initialized successfully")
        } catch (e: Exception) {
            Log.e("UserApplication", "Error initializing Firebase: ${e.message}", e)
        }
    }
}