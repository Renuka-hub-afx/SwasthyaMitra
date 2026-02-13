package com.example.swasthyamitra.repository

import com.example.swasthyamitra.models.FitnessData
import com.google.firebase.database.DatabaseReference
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class GamificationRepository(private val db: DatabaseReference, private val userId: String) {

    fun validateAndFixStreak(data: FitnessData): FitnessData {
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
        if (data.lastCheckInDate.isEmpty()) return data

        val lastDate = SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(data.lastCheckInDate)
        val todayDate = SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(today)

        val diff = (todayDate.time - lastDate.time) / (1000 * 60 * 60 * 24)

        return if (diff > 1) {
            // Missed a day, reset streak
            data.copy(streak = 0)
        } else {
            data
        }
    }

    fun checkIn(data: FitnessData, onComplete: (FitnessData) -> Unit) {
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
        
        if (data.lastCheckInDate == today) {
            onComplete(data)
            return
        }

        val newData = data.copy(
            streak = data.streak + 1,
            lastCheckInDate = today,
            totalPoints = data.totalPoints + 10 // Daily login points
        )

        db.child("users").child(userId).setValue(newData)
            .addOnSuccessListener { onComplete(newData) }
            .addOnFailureListener { onComplete(data) }
    }
}
