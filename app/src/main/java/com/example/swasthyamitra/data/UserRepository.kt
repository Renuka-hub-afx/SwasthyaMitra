package com.example.swasthyamitra.data

import androidx.lifecycle.LiveData

// We now pass both DAOs to the repository
class UserRepository(private val userDao: UserDao, private val goalDao: GoalDao) {

    // --- User Auth ---
    suspend fun insertUser(user: User): Long {
        return userDao.insertUser(user)
    }

    fun getUserByEmail(email: String): LiveData<User?> {
        return userDao.getUserByEmail(email)
    }

    // --- User Physical Stats (Height/Weight/Gender/BMI) ---
    suspend fun updateUserPhysicalStats(uid: Long, h: Float, w: Float, g: String, b: Float) {
        userDao.updateUserPhysicalStats(uid, h, w, g, b)
    }

    // --- Goals ---
    suspend fun insertGoal(goal: Goal) {
        // Correctly using the goalDao here
        goalDao.insertGoal(goal)
    }
}