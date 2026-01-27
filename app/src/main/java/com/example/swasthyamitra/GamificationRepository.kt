package com.example.swasthyamitra

import com.google.firebase.database.DatabaseReference
import java.text.SimpleDateFormat
import java.util.*

class GamificationRepository(
    private val database: DatabaseReference,
    private val userId: String
) {
    
    fun validateAndFixStreak(callback: (Map<String, Any>) -> Unit) {
        database.child("users").child(userId).child("fitnessData").get()
            .addOnSuccessListener { snapshot ->
                val data = snapshot.value as? Map<String, Any> ?: emptyMap()
                callback(data)
            }
    }
    
    fun checkIn(callback: (Boolean) -> Unit) {
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        
        database.child("users").child(userId).child("fitnessData").child("lastActiveDate")
            .setValue(today)
            .addOnSuccessListener { callback(true) }
            .addOnFailureListener { callback(false) }
    }
    
    fun updateSteps(steps: Int, callback: (Boolean) -> Unit) {
        val updates = mapOf(
            "steps" to steps,
            "lastUpdated" to System.currentTimeMillis()
        )
        
        database.child("users").child(userId).child("fitnessData")
            .updateChildren(updates)
            .addOnSuccessListener { callback(true) }
            .addOnFailureListener { callback(false) }
    }
    
    private fun getYesterdayDate(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -1)
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time)
    }
}
