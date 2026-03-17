package com.example.swasthyamitra

import android.util.Log
import com.example.swasthyamitra.utils.Constants
import com.example.swasthyamitra.utils.DateTimeHelper
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/**
 * GamificationRepository - Manages streaks, shields, and daily check-ins (Firestore version).
 *
 * Key fix: completionHistory (Map<date, Boolean>) is now persisted in Firestore so that
 * the streak calendar can accurately display past active days.
 */
/**
 * @param userId     Firebase Auth UID of the current user
 * @param userName   Display name used for the public RTDB stats mirror
 * @param userEmail  Email address used to build the public email→uid lookup index
 */
class GamificationRepository(
    private val userId: String,
    private val userName: String = "User",
    private val userEmail: String = ""
) {

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
        private val SDF = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    }

    /**
     * RTDB reference — used ONLY for writing the public userStats mirror.
     * Challenge data is already stored here; we just add a userStats node.
     */
    private val rtdb by lazy {
        try {
            FirebaseDatabase.getInstance(
                "https://swasthyamitra-ded44-default-rtdb.asia-southeast1.firebasedatabase.app"
            ).reference
        } catch (e: Exception) {
            FirebaseDatabase.getInstance().reference
        }
    }

    /**
     * Data class representing gamification data in Firestore.
     * completionHistory maps "yyyy-MM-dd" → true for every day the user was active.
     */
    data class GamificationData(
        val xp: Int = 0,
        val level: Int = 1,
        val streak: Int = 0,
        val shields: Int = 0,
        val lastActiveDate: String = "",
        val steps: Int = 0,
        val updatedAt: String = DateTimeHelper.currentISO8601(),
        val completionHistory: Map<String, Boolean> = emptyMap()
    )

    // ── Public API ────────────────────────────────────────────────────────────────

    /**
     * Validates and fixes streak based on last active date.
     *  • If the gap is exactly 1 day → streak continues normally (checkIn handles the increment).
     *  • If gap > 1 day and user has enough shields → consume shields, fill missed days in history.
     *  • Otherwise → reset streak to 0, clear completion history for the broken period.
     */
    suspend fun validateAndFixStreak(): GamificationData {
        return try {
            val snapshot = gamificationRef.get().await()
            if (!snapshot.exists()) return GamificationData()

            val lastActiveDate = snapshot.getString("lastActiveDate") ?: ""
            if (lastActiveDate.isEmpty()) return snapshotToData(snapshot)

            val today = DateTimeHelper.currentSimpleDate()
            if (lastActiveDate == today) return snapshotToData(snapshot)

            val daysDiff = calculateDaysDifference(lastActiveDate, today)

            if (daysDiff > 1) {
                val missedDays     = (daysDiff - 1).toInt()
                val currentShields = snapshot.getLong("shields")?.toInt() ?: 0
                val currentStreak  = snapshot.getLong("streak")?.toInt() ?: 0
                @Suppress("UNCHECKED_CAST")
                val oldHistory     = (snapshot.get("completionHistory") as? Map<String, Boolean>) ?: emptyMap()

                if (currentShields >= missedDays) {
                    // ── Shields absorb the gap: fill missed days as active ──────────
                    Log.d(TAG, "Streak protected by $missedDays shield(s). Remaining: ${currentShields - missedDays}")

                    val updatedHistory = oldHistory.toMutableMap()
                    for (i in 1..missedDays) {
                        val missedDate = offsetDate(lastActiveDate, i)
                        updatedHistory[missedDate] = true
                    }

                    val updates = hashMapOf<String, Any>(
                        "shields"           to (currentShields - missedDays),
                        "completionHistory" to updatedHistory,
                        "updatedAt"         to DateTimeHelper.currentISO8601()
                    )
                    gamificationRef.set(updates, SetOptions.merge()).await()

                } else {
                    // ── Streak broken ─────────────────────────────────────────────
                    Log.d(TAG, "Streak broken after $missedDays missed day(s). Resetting.")
                    val updates = hashMapOf<String, Any>(
                        "streak"            to 0,
                        "completionHistory" to emptyMap<String, Boolean>(),
                        "updatedAt"         to DateTimeHelper.currentISO8601()
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
     * Checks in user for today.
     *  • Increments streak by 1.
     *  • Marks today as active in completionHistory.
     *  • Awards a shield at every 7-day milestone.
     *  • Awards XP for streak maintenance.
     */
    suspend fun checkIn(): GamificationData {
        val today = DateTimeHelper.currentSimpleDate()

        return try {
            val snapshot       = gamificationRef.get().await()
            val lastActiveDate = snapshot.getString("lastActiveDate") ?: ""

            // Already checked in today — just return current data
            if (lastActiveDate == today) return snapshotToData(snapshot)

            val currentStreak  = snapshot.getLong("streak")?.toInt() ?: 0
            val currentShields = snapshot.getLong("shields")?.toInt() ?: 0

            @Suppress("UNCHECKED_CAST")
            val oldHistory = (snapshot.get("completionHistory") as? Map<String, Boolean>)
                ?: emptyMap()

            val newStreak = currentStreak + 1
            var newShields = currentShields

            // Award shield at every 7-day milestone
            if (newStreak % Constants.XP.MILESTONE_DAYS == 0 && newStreak > 0) {
                newShields += Constants.XP.SHIELDS_PER_MILESTONE
                Log.d(TAG, "🛡️ Shield awarded at $newStreak-day streak!")
            }

            // Mark today active in history
            val updatedHistory = oldHistory.toMutableMap().also { it[today] = true }

            val updates = hashMapOf<String, Any>(
                "lastActiveDate"    to today,
                "streak"            to newStreak,
                "shields"           to newShields,
                "completionHistory" to updatedHistory,
                "updatedAt"         to DateTimeHelper.currentISO8601()
            )

            gamificationRef.set(updates, SetOptions.merge()).await()

            // ── Mirror to RTDB userStats so challenge participants can read it
            //    without needing cross-user Firestore permission ──────────────
            try {
                rtdb.child("userStats").child(userId).updateChildren(
                    mapOf(
                        "streak"         to newStreak,
                        "shields"        to newShields,
                        "lastActiveDate" to today,
                        "name"           to userName,
                        "updatedAt"      to System.currentTimeMillis()
                    )
                )
            } catch (e: Exception) {
                Log.e(TAG, "Failed to mirror stats to RTDB: ${e.message}")
            }

            // ── Publish email→uid index so friends can be looked up by email ─
            //    without any Firestore cross-user query permission needed.
            if (userEmail.isNotEmpty()) {
                try { publishUserIndex() } catch (e: Exception) {
                    Log.e(TAG, "Failed to publish user index: ${e.message}")
                }
            }

            // ── Claim any pending challenge shield earned by winning a challenge ─
            try {
                val statsSnap = rtdb.child("userStats").child(userId).get().await()
                val hasPending = statsSnap.child("pendingChallengeShield").getValue(Boolean::class.java) ?: false
                if (hasPending) {
                    val updatedShields = newShields + 1
                    // Write to Firestore
                    gamificationRef.set(hashMapOf<String, Any>("shields" to updatedShields), SetOptions.merge()).await()
                    // Clear the flag from RTDB
                    rtdb.child("userStats").child(userId).child("pendingChallengeShield").removeValue()
                    Log.d(TAG, "🏆 Claimed pending challenge-win shield! Total: $updatedShields")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Failed to claim pending shield: ${e.message}")
            }

            // Award XP for maintaining streak (streak > 1 to avoid first-day inflation)
            if (newStreak > 1) {
                try {
                    val xpManager = com.example.swasthyamitra.gamification.XPManager(userId)
                    xpManager.awardXPSuspend(Constants.XPSource.MAINTAIN_STREAK)
                } catch (e: Exception) {
                    Log.e(TAG, "Failed to award streak XP: ${e.message}")
                }
            }

            gamificationRef.get().await().let { snapshotToData(it) }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to check in: ${e.message}")
            GamificationData()
        }
    }

    /**
     * Gets current gamification data from Firestore.
     * Creates a default record if none exists yet.
     */
    suspend fun getCurrentData(): GamificationData {
        return try {
            val snapshot = gamificationRef.get().await()
            if (snapshot.exists()) {
                snapshotToData(snapshot)
            } else {
                val initialData = GamificationData()
                gamificationRef.set(initialData).await()
                initialData
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to get current data: ${e.message}")
            GamificationData()
        }
    }

    /** Updates streak count directly (for manual adjustments). */
    suspend fun updateStreak(newStreak: Int) {
        try {
            val updates = hashMapOf<String, Any>(
                "streak"    to newStreak,
                "updatedAt" to DateTimeHelper.currentISO8601()
            )
            gamificationRef.set(updates, SetOptions.merge()).await()
        } catch (e: Exception) {
            Log.e(TAG, "Failed to update streak: ${e.message}")
        }
    }

    /** Adds shields to user account. */
    suspend fun addShields(count: Int) {
        try {
            val snapshot       = gamificationRef.get().await()
            val currentShields = snapshot.getLong("shields")?.toInt() ?: 0
            val updates = hashMapOf<String, Any>(
                "shields"   to (currentShields + count),
                "updatedAt" to DateTimeHelper.currentISO8601()
            )
            gamificationRef.set(updates, SetOptions.merge()).await()
        } catch (e: Exception) {
            Log.e(TAG, "Failed to add shields: ${e.message}")
        }
    }

    /**
     * Migration helper: Migrate data from Firebase RTDB FitnessData to Firestore.
     * Preserves the completionHistory that was stored in RTDB.
     */
    suspend fun migrateFromRTDB(rtdbData: FitnessData) {
        try {
            val firestoreData = hashMapOf<String, Any>(
                "xp"                to rtdbData.xp,
                "level"             to rtdbData.level,
                "streak"            to rtdbData.streak,
                "shields"           to rtdbData.shields,
                "lastActiveDate"    to rtdbData.lastActiveDate,
                "steps"             to rtdbData.steps,
                "completionHistory" to rtdbData.completionHistory,
                "updatedAt"         to DateTimeHelper.currentISO8601(),
                "migratedAt"        to DateTimeHelper.currentISO8601()
            )
            gamificationRef.set(firestoreData).await()
            Log.d(TAG, "Successfully migrated RTDB data to Firestore for user $userId")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to migrate RTDB data: ${e.message}")
        }
    }

    // ── Private helpers ───────────────────────────────────────────────────────────

    /** Converts a Firestore snapshot to GamificationData, including completionHistory. */
    private fun snapshotToData(snapshot: com.google.firebase.firestore.DocumentSnapshot): GamificationData {
        @Suppress("UNCHECKED_CAST")
        val rawHistory    = snapshot.get("completionHistory") as? Map<String, Boolean> ?: emptyMap()
        return GamificationData(
            xp                = snapshot.getLong("xp")?.toInt() ?: 0,
            level             = snapshot.getLong("level")?.toInt() ?: 1,
            streak            = snapshot.getLong("streak")?.toInt() ?: 0,
            shields           = snapshot.getLong("shields")?.toInt() ?: 0,
            lastActiveDate    = snapshot.getString("lastActiveDate") ?: "",
            steps             = snapshot.getLong("steps")?.toInt() ?: 0,
            updatedAt         = snapshot.getString("updatedAt") ?: DateTimeHelper.currentISO8601(),
            completionHistory = rawHistory
        )
    }

    /** Calculates whole days between two "yyyy-MM-dd" strings. */
    private fun calculateDaysDifference(startDate: String, endDate: String): Long {
        val start = DateTimeHelper.parseSimpleDate(startDate) ?: return 0
        val end   = DateTimeHelper.parseSimpleDate(endDate) ?: return 0
        return DateTimeHelper.daysBetween(start, end)
    }

    /** Returns a "yyyy-MM-dd" string that is [days] after [baseDate]. */
    private fun offsetDate(baseDate: String, days: Int): String {
        val cal = Calendar.getInstance()
        cal.time = SDF.parse(baseDate) ?: return baseDate
        cal.add(Calendar.DAY_OF_YEAR, days)
        return SDF.format(cal.time)
    }

    /**
     * Writes a public email → uid lookup entry to RTDB.
     * Path: userEmailIndex/<email_with_dots_as_commas>
     *
     * RTDB keys cannot contain "." so we encode "." → "," for storage.
     * ChallengeSetupActivity decodes the same way when querying.
     *
     * This is the RTDB equivalent of a Firestore collection query —
     * but readable by any authenticated user, no permission issues.
     */
    fun publishUserIndex() {
        if (userEmail.isEmpty() || userId.isEmpty()) return
        // Encode: "." → "," (reversible; RTDB forbids dots in keys)
        val encodedEmail = userEmail.replace(".", ",")
        rtdb.child("userEmailIndex").child(encodedEmail).setValue(
            mapOf(
                "uid"  to userId,
                "name" to userName,
                "email" to userEmail
            )
        )
        Log.d(TAG, "Published userEmailIndex for $userEmail → $userId")
    }
}
