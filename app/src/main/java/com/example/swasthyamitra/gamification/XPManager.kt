package com.example.swasthyamitra.gamification

import android.util.Log
import com.example.swasthyamitra.FitnessData
import com.google.firebase.database.FirebaseDatabase

/**
 * XPManager - Handles experience point awards and level calculations
 *
 * Phase 2.1: XP & Leveling System
 *
 * Features:
 * - Award XP for various activities (workouts, meals, steps, etc.)
 * - Automatic level calculation (every 100 XP = 1 level)
 * - Level-up detection
 * - Firebase RTDB sync
 *
 * Usage:
 * ```
 * val xpManager = XPManager(userId)
 * xpManager.awardXP(XPSource.COMPLETE_WORKOUT) { leveledUp, newLevel ->
 *     if (leveledUp) {
 *         showLevelUpDialog(newLevel)
 *     }
 * }
 * ```
 */
class XPManager(private val userId: String) {

    private val db = FirebaseDatabase.getInstance(
        "https://swasthyamitra-ded44-default-rtdb.asia-southeast1.firebasedatabase.app"
    ).reference

    companion object {
        private const val TAG = "XPManager"
        private const val XP_PER_LEVEL = 100  // Every 100 XP = 1 level
    }

    /**
     * XP reward amounts for different activities
     */
    enum class XPSource(val xpAmount: Int, val description: String) {
        COMPLETE_WORKOUT(50, "Complete a workout"),
        LOG_MEAL(10, "Log a meal"),
        REACH_STEP_GOAL(30, "Reach 5,000 steps"),
        MAINTAIN_STREAK(20, "Maintain daily streak"),
        AI_EXERCISE(75, "Complete AI exercise"),
        GHOST_MODE_USE(40, "Use Ghost Mode"),
        COMPLETE_CHALLENGE(100, "Win a challenge")
    }

    /**
     * Award XP to user for completing an activity
     *
     * @param source The activity type that earned XP
     * @param callback Returns (leveledUp: Boolean, newLevel: Int)
     */
    fun awardXP(source: XPSource, callback: (leveledUp: Boolean, newLevel: Int) -> Unit) {
        Log.d(TAG, "Awarding XP: ${source.description} (+${source.xpAmount} XP)")

        db.child("users").child(userId).get().addOnSuccessListener { snapshot ->
            val data = snapshot.getValue(FitnessData::class.java) ?: FitnessData(userId = userId)

            val oldXP = data.xp
            val oldLevel = data.level

            val newXP = oldXP + source.xpAmount
            val newLevel = calculateLevel(newXP)
            val leveledUp = newLevel > oldLevel

            Log.d(TAG, "XP Update: $oldXP → $newXP (Level: $oldLevel → $newLevel)")

            // Update Firebase
            db.child("users").child(userId).updateChildren(mapOf(
                "xp" to newXP,
                "level" to newLevel
            )).addOnSuccessListener {
                Log.d(TAG, "✅ XP awarded successfully")
                callback(leveledUp, newLevel)
            }.addOnFailureListener { e ->
                Log.e(TAG, "❌ Failed to award XP: ${e.message}", e)
                callback(false, oldLevel)
            }
        }.addOnFailureListener { e ->
            Log.e(TAG, "❌ Failed to fetch user data: ${e.message}", e)
            callback(false, 1)
        }
    }

    /**
     * Calculate level based on total XP
     * Formula: Level = (Total XP / 100) + 1
     *
     * Examples:
     * - 0-99 XP → Level 1
     * - 100-199 XP → Level 2
     * - 500-599 XP → Level 6
     */
    private fun calculateLevel(totalXP: Int): Int {
        return (totalXP / XP_PER_LEVEL) + 1
    }

    /**
     * Calculate XP needed for next level
     */
    fun getXPForNextLevel(currentXP: Int): Int {
        val currentLevel = calculateLevel(currentXP)
        val nextLevelThreshold = currentLevel * XP_PER_LEVEL
        return nextLevelThreshold - currentXP
    }

    /**
     * Calculate progress percentage within current level
     */
    fun getLevelProgress(currentXP: Int): Int {
        val currentLevelStartXP = (calculateLevel(currentXP) - 1) * XP_PER_LEVEL
        val xpInCurrentLevel = currentXP - currentLevelStartXP
        return ((xpInCurrentLevel.toFloat() / XP_PER_LEVEL) * 100).toInt()
    }
}

