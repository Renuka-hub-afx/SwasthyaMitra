package com.example.swasthyamitra

import android.app.Application
import android.content.Intent
import android.os.Build
import android.util.Log
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.example.swasthyamitra.services.UserBehaviorTracker
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

//            // 2. Initialize App Check
//            val firebaseAppCheck = FirebaseAppCheck.getInstance()
//            
//            // Use debug provider when in debug mode to allow testing on emulators
//            if (BuildConfig.DEBUG) {
//                firebaseAppCheck.installAppCheckProviderFactory(
//                    com.google.firebase.appcheck.debug.DebugAppCheckProviderFactory.getInstance()
//                )
//                Log.d("UserApplication", "App Check initialized with Debug Provider")
//            } else {
//                firebaseAppCheck.installAppCheckProviderFactory(
//                    PlayIntegrityAppCheckProviderFactory.getInstance()
//                )
//                Log.d("UserApplication", "App Check initialized with Play Integrity")
//            }


            // Trigger authHelper initialization
            val helper = authHelper
            Log.d("UserApplication", "FirebaseAuthHelper initialized successfully")

            // DISABLED: Auto-tracking service (requires health permissions)
            // Start auto-tracking service if user is logged in
            // com.google.firebase.auth.FirebaseAuth.getInstance().currentUser?.let {
            //     startAutoTrackingService()
            // }

        } catch (e: Exception) {
            Log.e("UserApplication", "Error initializing Firebase: ${e.message}", e)
        }
    }

    fun startAutoTrackingService() {
        // DISABLED: Foreground service requires FOREGROUND_SERVICE_HEALTH permission
        // and additional health-related permissions which need runtime permission handling
        Log.d("UserApplication", "Auto-tracking service disabled (requires health permissions)")

        /* COMMENTED OUT - Enable after adding proper permission handling
        try {
            val serviceIntent = Intent(this, UserBehaviorTracker::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(serviceIntent)
            } else {
                startService(serviceIntent)
            }
            Log.d("UserApplication", "Auto-tracking service started")
        } catch (e: Exception) {
            Log.e("UserApplication", "Error starting tracking service: ${e.message}", e)
        }
        */
    }

    fun stopAutoTrackingService() {
        // DISABLED: Auto-tracking service not running
        Log.d("UserApplication", "Auto-tracking service stop called (service disabled)")

        /* COMMENTED OUT
        try {
            stopService(Intent(this, UserBehaviorTracker::class.java))
            Log.d("UserApplication", "Auto-tracking service stopped")
        } catch (e: Exception) {
            Log.e("UserApplication", "Error stopping tracking service: ${e.message}", e)
        }
        */
    }
}