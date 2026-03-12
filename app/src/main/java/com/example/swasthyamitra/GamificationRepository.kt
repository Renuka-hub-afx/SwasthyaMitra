package com.example.swasthyamitra

import android.util.Log
import com.example.swasthyamitra.utils.Constants
import com.example.swasthyamitra.utils.DateTimeHelper
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await

/**
 * GamificationRepository - Manages streaks, shields, and daily check-ins (Firestore version)
 * Migrated from Firebase RTDB to Firestore for user-centric architecture.
 */
class GamificationRepository(private val userId: String) {

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
        private const val TAG = "GamificationRepository"
    }

    /**
     * Data class representing gamification data in Firestore
     */
    data class GamificationData(
        val xp: Int = 0,
        val level: Int = 1,
        val streak: Int = 0,
        val shields: Int = 0,
        val lastActiveDate: String = "",
        val steps: Int = 0,
        val updatedAt: String = DateTimeHelper.currentISO8601()
    )

    /**
     * Validates and fixes streak based on last active date.
     * Applies shield protection if available, resets streak if broken.
     */
    suspend fun validateAndFixStreak(): GamificationData {
        return try {
            val snapshot = gamificationRef.get().await()
            if (!snapshot.exists()) {
                return GamificationData()
            }

            val lastActiveDate = snapshot.getString("lastActiveDate") ?: ""
            if (lastActiveDate.isEmpty()) return snapshotToData(snapshot)

            val today = DateTimeHelper.currentSimpleDate()
            if (lastActiveDate == today) return snapshotToData(snapshot)

            val daysDiff = calculateDaysDifference(lastActiveDate, today)

            if (daysDiff > 1) {
                val missedDays = (daysDiff - 1).toInt()
                val currentShields = snapshot.getLong("shields")?.toInt() ?: 0
                val currentStreak = snapshot.getLong("streak")?.toInt() ?: 0

                if (currentShields >= missedDays) {
                    // Streak protected by shields
                    Log.d(TAG, "Streak protected! Used $missedDays shields")
                    val updates = hashMapOf<String, Any>(
                        "shields" to (currentShields - missedDays),
                        "updatedAt" to DateTimeHelper.currentISO8601()
                    )
                    gamificationRef.set(updates, SetOptions.merge()).await()
                } else {
                    // Streak broken
                    Log.d(TAG, "Streak broken! Resetting to 0")
                    val updates = hashMapOf<String, Any>(
                        "streak" to 0,
                        "updatedAt" to DateTimeHelper.currentISO8601()
                    )
                    gamificationRef.set(updates, SetOptions.merge()).await()
                }
            }

            gamificationRef.get().await().let { snapshotToData(it) }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to validate streak: ${e.message}")
            GamificationData()
        }
    }

    /**
     * Checks in user for the day, increments streak, awards shields at milestones.
     */
    suspend fun checkIn(): GamificationData {
        val today = DateTimeHelper.currentSimpleDate()

        return try {
            val snapshot = gamificationRef.get().await()
            val lastActiveDate = snapshot.getString("lastActiveDate") ?: ""

            // Already checked in today
            if (lastActiveDate == today) {
                return snapshotToData(snapshot)
            }

            val currentStreak = snapshot.getLong("streak")?.toInt() ?: 0
            val currentShields = snapshot.getLong("shields")?.toInt() ?: 0

            val newStreak = currentStreak + 1
            var newShields = currentShields

            // Award shield every 7 days
            if (newStreak % Constants.XP.MILESTONE_DAYS == 0 && newStreak > 0) {
                newShields += Constants.XP.SHIELDS_PER_MILESTONE
                Log.d(TAG, "Milestone reached! Awarded shield at $newStreak day streak")
            }

            val updates = hashMapOf<String, Any>(
                "lastActiveDate" to today,
                "streak" to newStreak,
                "shields" to newShields,
                "updatedAt" to DateTimeHelper.currentISO8601()
            )

            gamificationRef.set(updates, SetOptions.merge()).await()
            
            // Award XP for maintaining streak if streak > 0
            if (newStreak > 1) {
                val xpManager = com.example.swasthyamitra.gamification.XPManager(userId)
                xpManager.awardXPSuspend(Constants.XPSource.MAINTAIN_STREAK)
            }

            gamificationRef.get().await().let { snapshotToData(it) }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to check in: ${e.message}")
            GamificationData()
        }
    }

    /**
     * Gets current gamification data from Firestore
     */
    suspend fun getCurrentData(): GamificationData {
        return try {
            val snapshot = gamificationRef.get().await()
            if (snapshot.exists()) {
                snapshotToData(snapshot)
            } else {
                // Initialize with defaults
                val initialData = GamificationData()
                gamificationRef.set(initialData).await()
                initialData
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to get current data: ${e.message}")
            GamificationData()
        }
    }

    /**
     * Updates streak count directly (for manual adjustments)
     */
    suspend fun updateStreak(newStreak: Int) {
        try {
            val updates = hashMapOf<String, Any>(
                "streak" to newStreak,
                "updatedAt" to DateTimeHelper.currentISO8601()
            )
            gamificationRef.set(updates, SetOptions.merge()).await()
        } catch (e: Exception) {
            Log.e(TAG, "Failed to update streak: ${e.message}")
        }
    }

    /**
     * Adds shields to user account
     */
    suspend fun addShields(count: Int) {
        try {
            val snapshot = gamificationRef.get().await()
            val currentShields = snapshot.getLong("shields")?.toInt() ?: 0
            val updates = hashMapOf<String, Any>(
                "shields" to (currentShields + count),
                "updatedAt" to DateTimeHelper.currentISO8601()
            )
            gamificationRef.set(updates, SetOptions.merge()).await()
        } catch (e: Exception) {
            Log.e(TAG, "Failed to add shields: ${e.message}")
        }
    }

    /**
     * Helper: Calculate days difference between two date strings
     */
    private fun calculateDaysDifference(startDate: String, endDate: String): Long {
        val start = DateTimeHelper.parseSimpleDate(startDate) ?: return 0
        val end = DateTimeHelper.parseSimpleDate(endDate) ?: return 0
        return DateTimeHelper.daysBetween(start, end)
    }

    /**
     * Helper: Convert Firestore snapshot to GamificationData
     */
    private fun snapshotToData(snapshot: com.google.firebase.firestore.DocumentSnapshot): GamificationData {
        return GamificationData(
            xp = snapshot.getLong("xp")?.toInt() ?: 0,
            level = snapshot.getLong("level")?.toInt() ?: 1,
            streak = snapshot.getLong("streak")?.toInt() ?: 0,
            shields = snapshot.getLong("shields")?.toInt() ?: 0,
            lastActiveDate = snapshot.getString("lastActiveDate") ?: "",
            steps = snapshot.getLong("steps")?.toInt() ?: 0,
            updatedAt = snapshot.getString("updatedAt") ?: DateTimeHelper.currentISO8601()
        )
    }

    /**
     * Migration helper: Migrate data from Firebase RTDB to Firestore
     * Call this once for existing users to migrate their gamification data
     */
    suspend fun migrateFromRTDB(rtdbData: FitnessData) {
        try {
            val firestoreData = hashMapOf<String, Any>(
                "xp" to rtdbData.xp,
                "level" to rtdbData.level,
                "streak" to rtdbData.streak,
                "shields" to rtdbData.shields,
                "lastActiveDate" to rtdbData.lastActiveDate,
                "steps" to rtdbData.steps,
                "updatedAt" to DateTimeHelper.currentISO8601(),
                "migratedAt" to DateTimeHelper.currentISO8601()
            )
            gamificationRef.set(firestoreData).await()
            Log.d(TAG, "Successfully migrated RTDB data to Firestore for user $userId")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to migrate RTDB data: ${e.message}")
        }
    }
}

