package com.example.swasthyamitra.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.swasthyamitra.data.User

@Entity(
    tableName = "user_goals",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE // If User is deleted, delete their goals too
        )
    ],
    indices = [Index(value = ["userId"])]
)
data class Goal(
    @PrimaryKey(autoGenerate = true)
    val goalId: Long = 0,

    val userId: Long,

    // Stores: "Lose Weight", "Maintain Weight", "Gain Muscle", "General Health"
    val goalType: String,

    // Calculated values from UserInfoActivity
    val dailyCalorieTarget: Int,
    val waterGoalMl: Int,

    // "Active", "Completed", "Abandoned"
    val status: String = "Active",

    // Good for tracking history
    val startDate: Long = System.currentTimeMillis()
)
