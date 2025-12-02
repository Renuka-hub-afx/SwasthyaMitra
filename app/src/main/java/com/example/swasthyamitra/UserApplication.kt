package com.example.swasthyamitra

import android.app.Application
import com.example.swasthyamitra.data.AppDatabase
import com.example.swasthyamitra.data.UserRepository

class UserApplication : Application() {

    // We keep the repository accessible to the Activities
    lateinit var repository: UserRepository
        private set

    override fun onCreate() {
        super.onCreate()

        // 1. Get the Database Instance (AppDatabase)
        val database = AppDatabase.getDatabase(this)

        // 2. Initialize Repository with BOTH DAOs
        repository = UserRepository(database.userDao(), database.goalDao())
    }
}