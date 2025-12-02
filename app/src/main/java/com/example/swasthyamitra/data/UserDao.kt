package com.example.swasthyamitra.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    // 1. Initial Signup
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: User): Long

    // 2. Check for login
    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    fun getUserByEmail(email: String): LiveData<User?>

    // 3. User Info Screen: Update Physical Stats
    @Query("UPDATE users SET height = :h, weight = :w, gender = :g, bmi = :b WHERE id = :uid")
    suspend fun updateUserPhysicalStats(uid: Long, h: Float, w: Float, g: String, b: Float)

    // 4. THIS IS THE MISSING PART CAUSING YOUR ERROR vvv
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoal(goal: Goal)
}