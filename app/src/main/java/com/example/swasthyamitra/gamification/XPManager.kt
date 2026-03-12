package com.example.swasthyamitra.gamification

import android.util.Log
import com.example.swasthyamitra.utils.Constants
import com.example.swasthyamitra.utils.DateTimeHelper
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await

/**
 * XPManager - Handles experience point awards and level calculations
 *
 * Phase 2.1: XP & Leveling System (Migrated to Firestore)
 *
 * Features:
 * - Award XP for various activities (workouts, meals, steps, etc.)
 * - Automatic level calculation (every 100 XP = 1 level)
 * - Level-up detection
 * - Firestore sync with user-centric architecture
 *
 * Usage:
 * ```
 * val xpManager = XPManager(userId)
 * xpManager.awardXP(Constants.XPSource.COMPLETE_WORKOUT) { leveledUp, newLevel ->
 *     if (leveledUp) {
 *         showLevelUpDialog(newLevel)
 *     }
 * }
 * ```
 */
class XPManager(private val userId: String) {

    private val db: FirebaseFirestore by lazy {
        try {
            FirebaseFirestore.getInstance(Constants.Database.FIRESTORE_INSTANCE_NAME)
        } catch (e: Exception) {
            FirebaseFirestore.getInstance()
        }
    }

    private val gamificationRef by lazy {
        db.collection(Constants.Collections.USERS)
            .document(userId)
            .collection(Constants.Collections.GAMIFICATION_DATA)
            .document("current")
    }

    companion object {
        private const val TAG = "XPManager"
    }

    /**
     * Award XP using XPSource from Constants
     * @param source XP source from Constants.XPSource enum
     * @param customAmount Optional custom amount (overrides enum value)
     * @param callback Returns (leveledUp: Boolean, newLevel: Int)
     */
    fun awardXP(source: Constants.XPSource, customAmount: Int? = null, callback: (leveledUp: Boolean, newLevel: Int) -> Unit) {
        val xpAmount = customAmount ?: source.xpValue
        Log.d(TAG, "Awarding XP: ${source.name} (+$xpAmount XP)")

        gamificationRef.get().addOnSuccessListener { snapshot ->
            val oldXP = snapshot.getLong("xp")?.toInt() ?: 0
            val oldLevel = snapshot.getLong("level")?.toInt() ?: 1

            val newXP = oldXP + xpAmount
            val newLevel = calculateLevel(newXP)
            val leveledUp = newLevel > oldLevel

            Log.d(TAG, "XP Update: $oldXP → $newXP (Level: $oldLevel → $newLevel)")

            val updates = hashMapOf<String, Any>(
                "xp" to newXP,
                "level" to newLevel,
                "lastXPAward" to DateTimeHelper.currentISO8601(),
                "lastXPSource" to source.name
            )

            gamificationRef.set(updates, SetOptions.merge())
                .addOnSuccessListener {
                    Log.d(TAG, "✅ XP awarded successfully")
                    callback(leveledUp, newLevel)
                }
                .addOnFailureListener { e ->
                    Log.e(TAG, "❌ Failed to award XP: ${e.message}", e)
                    callback(false, oldLevel)
                }
        }.addOnFailureListener { e ->
            Log.e(TAG, "❌ Failed to fetch gamification data: ${e.message}", e)
            callback(false, 1)
        }
    }

    /**
     * Suspending version of awardXP for coroutine contexts
     */
    suspend fun awardXPSuspend(source: Constants.XPSource, customAmount: Int? = null): Pair<Boolean, Int> {
        val xpAmount = customAmount ?: source.xpValue
        Log.d(TAG, "Awarding XP (suspend): ${source.name} (+$xpAmount XP)")

        return try {
            val snapshot = gamificationRef.get().await()
            val oldXP = snapshot.getLong("xp")?.toInt() ?: 0
            val oldLevel = snapshot.getLong("level")?.toInt() ?: 1

            val newXP = oldXP + xpAmount
            val newLevel = calculateLevel(newXP)
            val leveledUp = newLevel > oldLevel

            val updates = hashMapOf<String, Any>(
                "xp" to newXP,
                "level" to newLevel,
                "lastXPAward" to DateTimeHelper.currentISO8601(),
                "lastXPSource" to source.name
            )

            gamificationRef.set(updates, SetOptions.merge()).await()
            Log.d(TAG, "✅ XP awarded successfully: $oldXP → $newXP")
            Pair(leveledUp, newLevel)
        } catch (e: Exception) {
            Log.e(TAG, "❌ Failed to award XP: ${e.message}", e)
            Pair(false, 1)
        }
    }

    /**
     * Calculate level based on total XP
     * Formula: Level = (Total XP / 100) + 1
     */
    private fun calculateLevel(totalXP: Int): Int {
        return (totalXP / Constants.XP.XP_PER_LEVEL) + 1
    }

    /**
     * Calculate XP needed for next level
     */
    fun getXPForNextLevel(currentXP: Int): Int {
        val currentLevel = calculateLevel(currentXP)
        val nextLevelThreshold = currentLevel * Constants.XP.XP_PER_LEVEL
        return nextLevelThreshold - currentXP
    }

    /**
     * Calculate progress percentage within current level
     */
    fun getLevelProgress(currentXP: Int): Int {
        val currentLevelStartXP = (calculateLevel(currentXP) - 1) * Constants.XP.XP_PER_LEVEL
        val xpInCurrentLevel = currentXP - currentLevelStartXP
        return ((xpInCurrentLevel.toFloat() / Constants.XP.XP_PER_LEVEL) * 100).toInt()
    }

    /**
     * Get current XP and level from Firestore
     */
    suspend fun getCurrentStats(): Triple<Int, Int, Int> {
        return try {
            val snapshot = gamificationRef.get().await()
            val xp = snapshot.getLong("xp")?.toInt() ?: 0
            val level = snapshot.getLong("level")?.toInt() ?: 1
            val streak = snapshot.getLong("streak")?.toInt() ?: 0
            Triple(xp, level, streak)
        } catch (e: Exception) {
            Log.e(TAG, "Failed to get current stats: ${e.message}")
            Triple(0, 1, 0)
        }
    }
}
