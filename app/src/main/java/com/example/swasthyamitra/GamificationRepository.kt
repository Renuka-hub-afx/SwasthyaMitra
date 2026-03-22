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
 * Modified to include a one-time boost: Ensure at least 15 days streak and 3 shields.
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

    private val rtdb by lazy {
        try {
            FirebaseDatabase.getInstance(
                "https://swasthyamitra-ded44-default-rtdb.asia-southeast1.firebasedatabase.app"
            ).reference
        } catch (e: Exception) {
            FirebaseDatabase.getInstance().reference
        }
    }

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
     * Includes Boost Logic: Ensures at least 15 days streak and 3 shields.
     */
    suspend fun validateAndFixStreak(): GamificationData {
        return try {
            val snapshot = gamificationRef.get().await()
            val baseData = if (snapshot.exists()) snapshotToData(snapshot) else GamificationData()
            
            var streak = baseData.streak
            var shields = baseData.shields
            var history = baseData.completionHistory.toMutableMap()
            var needsUpdate = false

            val today = DateTimeHelper.currentSimpleDate()

            // Jumpstart logic: Ensure at least 15 days streak and 3 shields
            if (streak < 15) {
                streak = 15
                needsUpdate = true
            }
            
            // Deep backfill: Ensure ALL of last 30 days are marked TRUE
            // This fixes gaps like the 18th and 20th even if the map size is > 15
            for (i in 0..30) {
                val date = offsetDate(today, -i)
                if (history[date] != true) {
                    history[date] = true
                    needsUpdate = true
                }
            }
            
            if (shields < 3) {
                shields = 3
                needsUpdate = true
            }

            if (needsUpdate) {
                val updates = hashMapOf<String, Any>(
                    "streak" to streak,
                    "shields" to shields,
                    "lastActiveDate" to today, // Update lastActiveDate to avoid immediate streak break
                    "completionHistory" to history,
                    "updatedAt" to DateTimeHelper.currentISO8601()
                )
                gamificationRef.set(updates, SetOptions.merge()).await()

                val rtdbPayload = mapOf(
                    "streak" to streak,
                    "shields" to shields,
                    "completionHistory" to history,
                    "lastActiveDate" to today,
                    "updatedAt" to System.currentTimeMillis()
                )
                try {
                    rtdb.child("users").child(userId).updateChildren(rtdbPayload).await()
                    rtdb.child("userStats").child(userId).updateChildren(rtdbPayload + ("name" to userName)).await()
                } catch (e: Exception) {
                    Log.e(TAG, "Failed to mirror boost to RTDB: ${e.message}")
                }
            }

            val lastActiveDate = if (needsUpdate) today else baseData.lastActiveDate
            if (lastActiveDate.isEmpty()) return snapshotToData(gamificationRef.get().await())

            if (lastActiveDate == today) return snapshotToData(gamificationRef.get().await())

            val daysDiff = calculateDaysDifference(lastActiveDate, today)

            if (daysDiff > 1) {
                val missedDays     = (daysDiff - 1).toInt()
                val currentShields = shields // Use boosted/current shields
                val currentStreak  = streak  // Use boosted/current streak
                
                @Suppress("UNCHECKED_CAST")
                val oldHistory     = baseData.completionHistory

                if (currentShields >= missedDays) {
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

    suspend fun checkIn(): GamificationData {
        val today = DateTimeHelper.currentSimpleDate()

        return try {
            val snapshot       = gamificationRef.get().await()
            val baseData       = snapshotToData(snapshot)
            val lastActiveDate = baseData.lastActiveDate

            if (lastActiveDate == today) return baseData

            var currentStreak  = baseData.streak
            var currentShields = baseData.shields
            var history        = baseData.completionHistory.toMutableMap()
            var needsUpdate    = false

            // Ensure minimum 15 days streak and backfilled history during check-in
            if (currentStreak < 15) currentStreak = 15
            
            // Deep backfill again in check-in to be absolutely sure
            for (i in 1..30) {
                val date = offsetDate(today, -i)
                if (history[date] != true) {
                    history[date] = true
                }
            }
            
            val updatedHistory = history.also { it[today] = true }
            if (currentShields < 3) {
                currentShields = 3
                needsUpdate = true
            }

            val newStreak = currentStreak + 1
            var newShields = currentShields

            if (newStreak % Constants.XP.MILESTONE_DAYS == 0 && newStreak > 0) {
                newShields += Constants.XP.SHIELDS_PER_MILESTONE
                Log.d(TAG, "🛡️ Shield awarded at $newStreak-day streak!")
            }

            val updates = hashMapOf<String, Any>(
                "lastActiveDate"    to today,
                "streak"            to newStreak,
                "shields"           to newShields,
                "completionHistory" to updatedHistory,
                "updatedAt"         to DateTimeHelper.currentISO8601()
            )

            gamificationRef.set(updates, SetOptions.merge()).await()

            // Mirror to BOTH RTDB nodes for UI stability
            val rtdbPayload = mapOf(
                "streak"            to newStreak,
                "shields"           to newShields,
                "completionHistory" to updatedHistory,
                "lastActiveDate"    to today,
                "updatedAt"         to System.currentTimeMillis()
            )
            try {
                rtdb.child("users").child(userId).updateChildren(rtdbPayload)
                rtdb.child("userStats").child(userId).updateChildren(rtdbPayload)
            } catch (e: Exception) {
                Log.e(TAG, "Failed to mirror stats to RTDB: ${e.message}")
            }

            if (userEmail.isNotEmpty()) {
                try { publishUserIndex() } catch (e: Exception) {
                    Log.e(TAG, "Failed to publish user index: ${e.message}")
                }
            }

            try {
                val statsSnap = rtdb.child("userStats").child(userId).get().await()
                val hasPending = statsSnap.child("pendingChallengeShield").getValue(Boolean::class.java) ?: false
                if (hasPending) {
                    val updatedShields = newShields + 1
                    gamificationRef.set(hashMapOf<String, Any>("shields" to updatedShields), SetOptions.merge()).await()
                    rtdb.child("userStats").child(userId).child("pendingChallengeShield").removeValue()
                    Log.d(TAG, "🏆 Claimed pending challenge-win shield! Total: $updatedShields")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Failed to claim pending shield: ${e.message}")
            }

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

    private fun calculateDaysDifference(startDate: String, endDate: String): Long {
        val start = DateTimeHelper.parseSimpleDate(startDate) ?: return 0
        val end   = DateTimeHelper.parseSimpleDate(endDate) ?: return 0
        return DateTimeHelper.daysBetween(start, end)
    }

    private fun offsetDate(baseDate: String, days: Int): String {
        val cal = Calendar.getInstance()
        cal.time = SDF.parse(baseDate) ?: return baseDate
        cal.add(Calendar.DAY_OF_YEAR, days)
        return SDF.format(cal.time)
    }

    fun publishUserIndex() {
        if (userEmail.isEmpty() || userId.isEmpty()) return
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
