package com.example.swasthyamitra

import android.app.Application
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.google.firebase.FirebaseApp

class UserApplication : Application() {

    // Firebase Auth Helper
    lateinit var authHelper: FirebaseAuthHelper
        private set

    override fun onCreate() {
        super.onCreate()

        // Initialize Firebase
        FirebaseApp.initializeApp(this)

        // Initialize Firebase Auth Helper
        authHelper = FirebaseAuthHelper(this)
    }
}