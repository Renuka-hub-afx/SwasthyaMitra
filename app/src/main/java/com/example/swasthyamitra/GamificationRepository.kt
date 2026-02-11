package com.example.swasthyamitra

import com.google.firebase.database.DatabaseReference
import com.example.swasthyamitra.models.DailyActivity

class GamificationRepository(private val database: DatabaseReference, private val userId: String) {

    private val dateFormat = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())

    fun validateAndFixStreak(data: FitnessData): FitnessData {
        if (data.lastActiveDate.isEmpty()) return data
        
        val today = dateFormat.format(java.util.Date())
        if (data.lastActiveDate == today) return data

        val lastDate = try {
            dateFormat.parse(data.lastActiveDate)
        } catch (e: Exception) {
            return data
        } ?: return data

        val todayDate = dateFormat.parse(today) ?: return data
        
        // Calculate difference in days
        val diff = todayDate.time - lastDate.time
        val daysDiff = java.util.concurrent.TimeUnit.MILLISECONDS.toDays(diff)
        
        // Logic:
        // daysDiff = 1 -> Yesterday (Consecutive) - Good
        // daysDiff > 1 -> Missed one or more days - Bad
        
        if (daysDiff > 1) {
            val missedDays = (daysDiff - 1).toInt()
            
            if (data.shields >= missedDays) {
                // Streak Protected by Shields!
                // We keep the streak count but deduct shields.
                // Note: We don't update lastActiveDate here; checkIn will set it to today.
                return data.copy(
                    shields = data.shields - missedDays
                )
            } else {
                // Streak Broken :(
                return data.copy(
                    streak = 0
                )
            }
        }
        
        return data
    }



    fun checkIn(data: FitnessData, callback: (FitnessData) -> Unit) {
        val today = dateFormat.format(java.util.Date())
        
        // If already checked in today, just return existing data
        if (data.lastActiveDate == today) {
            callback(data)
            return
        }

        val newStreak = data.streak + 1
        
        val updatedData = data.copy(
            lastActiveDate = today,
            streak = newStreak,
            steps = 0 // Reset steps for new day logic if needed, but here we just init
        )
        
        saveData(updatedData)
        callback(updatedData)
    }



    fun updateSteps(data: FitnessData, steps: Int, callback: (FitnessData) -> Unit) {
        val today = dateFormat.format(java.util.Date())
        var currentData = data.copy(steps = steps)
        
        // Logic: Earn Shield if Steps >= 5000
        if (steps >= 5000) {
            // Check if already earned a shield today
            val earnedToday = currentData.activeShields.any { it.acquiredDate == today }
            
            if (!earnedToday) {
                // Award Shield!
                val newShield = ShieldInstance(
                    id = java.util.UUID.randomUUID().toString(),
                    type = ShieldType.FREEZE,
                    acquiredDate = today,
                    expiresAt = System.currentTimeMillis() + (30L * 24 * 60 * 60 * 1000) // 30 days validity
                )
                
                val newActiveShields = currentData.activeShields + newShield
                currentData = currentData.copy(
                    shields = currentData.shields + 1,
                    activeShields = newActiveShields
                )
            }
        }
        
        saveData(currentData)
        callback(currentData)
    }
    
    private fun saveData(data: FitnessData) {
        val today = dateFormat.format(java.util.Date())
        
        // 1. Save User Stats (Streak, Level, XP etc) to 'users'
        // We exclude daily specific transient data if we want strict separation, 
        // but for now we update the whole object to `users` to maintain backward compat
        // AND write specific daily data to `dailyActivity`.
        database.child("users").child(userId).setValue(data)
        
        // 2. Save Daily Activity (Steps, Calories) to 'dailyActivity'
        val dailyActivity = DailyActivity(
            date = today,
            steps = data.steps,
            calories = (data.steps * 0.04).toInt(), // Approx calories
            workout = if (data.completionHistory[today] == true) "Completed" else ""
        )
        
        database.child("dailyActivity").child(userId).child(today).setValue(dailyActivity)
    }
}
