package com.example.swasthyamitra

import com.google.firebase.database.DatabaseReference
import java.text.SimpleDateFormat
import java.util.*

class GamificationRepository(
    private val database: DatabaseReference,
    private val userId: String
) {
    
    fun validateAndFixStreak(data: FitnessData): FitnessData {
        // Simple validation logic
        return data
    }
    
    fun checkIn(data: FitnessData, callback: (FitnessData) -> Unit) {
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val updatedData = data.copy(lastActiveDate = today)
        
        database.child("users").child(userId).setValue(updatedData)
            .addOnSuccessListener { callback(updatedData) }
            .addOnFailureListener { callback(data) }
    }
    
    fun updateSteps(data: FitnessData, steps: Int, callback: (FitnessData) -> Unit) {
        val updatedData = data.copy(steps = steps)
        
        database.child("users").child(userId).setValue(updatedData)
            .addOnSuccessListener { callback(updatedData) }
            .addOnFailureListener { callback(data) }
    }
}
