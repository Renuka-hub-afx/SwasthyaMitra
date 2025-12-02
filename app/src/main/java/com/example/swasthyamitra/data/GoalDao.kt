package com.example.swasthyamitra.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface GoalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoal(goal: Goal)

    @Update
    suspend fun updateGoal(goal: Goal)

    // Get the latest active goal for a specific user
    @Query("SELECT * FROM user_goals WHERE userId = :userId AND status = 'Active' ORDER BY goalId DESC LIMIT 1")
    fun getActiveGoal(userId: Long): Flow<Goal?>

    // Get all goals for history
    @Query("SELECT * FROM user_goals WHERE userId = :userId")
    fun getAllGoals(userId: Long): Flow<List<Goal>>
}