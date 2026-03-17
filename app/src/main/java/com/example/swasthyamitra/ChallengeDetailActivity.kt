package com.example.swasthyamitra

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

/**
 * Shows the live progress of a challenge between two participants.
 *
 * ── Permission-safe design ────────────────────────────────────────────────────────
 * All participant data is read from RTDB (not Firestore), specifically:
 *   • challenges/<code>  — challenge metadata + participants
 *   • userStats/<uid>    — mirrored streak/lastActiveDate/name written by GamificationRepository
 *
 * This avoids the Firestore cross-user permission-denied error entirely.
 * ─────────────────────────────────────────────────────────────────────────────────
 */
class ChallengeDetailActivity : AppCompatActivity() {

    private lateinit var authHelper: FirebaseAuthHelper
    private lateinit var tvChallengeName: TextView
    private lateinit var tvChallengeCode: TextView
    private lateinit var tvStatus: TextView
    private lateinit var tvDaysLeft: TextView
    private lateinit var participantsContainer: LinearLayout
    private lateinit var tvRules: TextView
    private lateinit var btnLeave: Button
    private lateinit var progressBar: ProgressBar

    private val rtdb by lazy {
        FirebaseDatabase.getInstance(
            "https://swasthyamitra-ded44-default-rtdb.asia-southeast1.firebasedatabase.app"
        ).reference
    }

    // Firestore is used ONLY to award shield to the CURRENT user (whom we own)
    private val firestore by lazy {
        try { com.google.firebase.firestore.FirebaseFirestore.getInstance("renu") }
        catch (e: Exception) { com.google.firebase.firestore.FirebaseFirestore.getInstance() }
    }

    private val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    private var challengeCode = ""
    private var myUserId = ""

    // ── Lifecycle ────────────────────────────────────────────────────────────────

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge_detail)

        authHelper    = FirebaseAuthHelper(this)
        myUserId      = authHelper.getCurrentUser()?.uid ?: ""
        challengeCode = intent.getStringExtra("CHALLENGE_CODE") ?: ""

        tvChallengeName       = findViewById(R.id.tvChallengeName)
        tvChallengeCode       = findViewById(R.id.tvChallengeCode)
        tvStatus              = findViewById(R.id.tvChallengeStatus)
        tvDaysLeft            = findViewById(R.id.tvDaysLeft)
        participantsContainer = findViewById(R.id.participantsContainer)
        tvRules               = findViewById(R.id.tvRules)
        btnLeave              = findViewById(R.id.btnLeaveChallenge)
        progressBar           = findViewById(R.id.progressBarDetail)

        findViewById<View>(R.id.btnBack).setOnClickListener { finish() }

        tvRules.text = "📋 Rules:\n" +
                "• Maintain your daily streak every day\n" +
                "• Missing a day without a shield = you lose\n" +
                "• If both participants break, highest streak wins\n" +
                "• 🏆 Winner earns a FREE Shield!"

        btnLeave.setOnClickListener { confirmLeave() }

        if (challengeCode.isEmpty() || myUserId.isEmpty()) {
            Toast.makeText(this, "Error loading challenge", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        loadChallenge()
    }

    // ── Step 1: Load challenge metadata from RTDB ────────────────────────────────

    private fun loadChallenge() {
        progressBar.visibility = View.VISIBLE

        rtdb.child("challenges").child(challengeCode)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snap: DataSnapshot) {
                    progressBar.visibility = View.GONE

                    if (!snap.exists()) {
                        Toast.makeText(this@ChallengeDetailActivity, "Challenge not found", Toast.LENGTH_SHORT).show()
                        finish()
                        return
                    }

                    val name         = snap.child("name").getValue(String::class.java) ?: "Challenge"
                    val status       = snap.child("status").getValue(String::class.java) ?: "active"
                    val createdAt    = snap.child("createdAt").getValue(Long::class.java) ?: 0L
                    val durationDays = snap.child("durationDays").getValue(Long::class.java)?.toInt() ?: 7
                    val winnerId     = snap.child("winnerId").getValue(String::class.java)

                    tvChallengeName.text = name
                    tvChallengeCode.text = "Code: $challengeCode"

                    // Compute time remaining
                    // SAFETY: if createdAt is 0 (field missing in old challenges), treat as
                    // "just created now" so we never get a false daysLeft=-20000 result.
                    val safeCreatedAt = if (createdAt > 0L) createdAt else System.currentTimeMillis()
                    val endMs    = safeCreatedAt + TimeUnit.DAYS.toMillis(durationDays.toLong())
                    val daysLeft = TimeUnit.MILLISECONDS.toDays(endMs - System.currentTimeMillis())

                    when {
                        status == "completed" -> {
                            val iWon = winnerId == myUserId
                            tvStatus.text = if (iWon) "🏆 You Won!" else "😔 You Lost"
                            tvStatus.setTextColor(if (iWon) 0xFF388E3C.toInt() else 0xFFD32F2F.toInt())
                            tvDaysLeft.text = "Challenge ended"
                            btnLeave.visibility = View.GONE
                        }
                        daysLeft <= 0 -> {
                            tvStatus.text = "⏰ Resolving…"
                            tvStatus.setTextColor(0xFFFF9800.toInt())
                            tvDaysLeft.text = "Challenge ended — calculating winner"
                        }
                        else -> {
                            tvStatus.text = "⚔️ Active"
                            tvStatus.setTextColor(0xFF388E3C.toInt())
                            tvDaysLeft.text = "$daysLeft day${if (daysLeft == 1L) "" else "s"} remaining"
                        }
                    }

                    // Participant UIDs from challenge
                    val participantIds = snap.child("participants")
                        .children
                        .mapNotNull { it.key }
                        .filter { it.isNotEmpty() }

                    // Step 2: load their public stats from RTDB userStats/<uid>
                    loadParticipantStatsFromRTDB(participantIds, winnerId, status, daysLeft, safeCreatedAt)
                }

                override fun onCancelled(error: DatabaseError) {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this@ChallengeDetailActivity, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }

    // ── Step 2: Read userStats from RTDB (permission-safe) ───────────────────────

    private fun loadParticipantStatsFromRTDB(
        participantIds: List<String>,
        winnerId: String?,
        status: String,
        daysLeft: Long,
        createdAt: Long
    ) {
        participantsContainer.removeAllViews()

        if (participantIds.isEmpty()) {
            addParticipantRow("No participants yet", 0, "", isWinner = false, isActive = true)
            return
        }

        val statsMap = mutableMapOf<String, ParticipantInfo>()
        var loaded   = 0

        for (uid in participantIds) {
            rtdb.child("userStats").child(uid)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snap: DataSnapshot) {
                        val name       = snap.child("name").getValue(String::class.java) ?: "User"
                        val streak     = snap.child("streak").getValue(Long::class.java)?.toInt() ?: 0
                        val lastActive = snap.child("lastActiveDate").getValue(String::class.java) ?: ""
                        statsMap[uid]  = ParticipantInfo(uid, name, streak, lastActive)
                        onParticipantLoaded()
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.w("ChallengeDetail", "userStats read failed for $uid: ${error.message}")
                        // Leave lastActiveDate empty so the grace-period logic treats them as active
                        statsMap[uid] = ParticipantInfo(uid, "Participant", 0, "")
                        onParticipantLoaded()
                    }

                    fun onParticipantLoaded() {
                        loaded++
                        if (loaded == participantIds.size) {
                            renderParticipants(statsMap, winnerId)

                            // Only evaluate if challenge is still active
                            if (status != "active") return

                            // ── Resolution policy ──────────────────────────────────────────
                            // We do NOT auto-end the challenge based on RTDB snapshot streak
                            // data because that data comes from Firestore history. A user who
                            // hasn't opened the app in days would have streak=0 + old date,
                            // which would incorrectly trigger a "loss" the moment they join.
                            //
                            // Instead:
                            //   • While daysLeft > 0: show streaks, do nothing else.
                            //   • When daysLeft <= 0: whoever has the HIGHER current streak wins.
                            //   • Users can manually leave at any time (= forfeit).
                            // ──────────────────────────────────────────────────────────────

                            if (daysLeft <= 0) {
                                // Duration expired — resolve by highest current streak
                                // Prefer participants with real RTDB data over fallback zeros
                                val winner = statsMap
                                    .filter { it.value.lastActiveDate.isNotEmpty() }
                                    .maxByOrNull { it.value.streak }?.key
                                    ?: statsMap.maxByOrNull { it.value.streak }?.key
                                Log.d("ChallengeDetail", "Duration expired — winner: $winner")
                                endChallenge(winnerId = winner)
                            } else {
                                Log.d("ChallengeDetail", "Challenge active — $daysLeft days left. No auto-evaluation.")
                            }
                        }
                    }
                })
        }
    }

    // ── Step 3: Render participant rows ──────────────────────────────────────────

    private fun renderParticipants(
        statsMap: Map<String, ParticipantInfo>,
        winnerId: String?
    ) {
        participantsContainer.removeAllViews()

        for ((uid, info) in statsMap) {
            val isWinner = uid == winnerId
            // Show as active if they have no RTDB data yet (benefit of doubt during challenge)
            val isActive = info.lastActiveDate.isEmpty() || isStreakAlive(info.lastActiveDate)
            addParticipantRow(
                displayName = if (uid == myUserId) "${info.name} (You)" else info.name,
                streak      = info.streak,
                lastActive  = info.lastActiveDate,
                isWinner    = isWinner,
                isActive    = isActive
            )
        }
    }

    private fun addParticipantRow(
        displayName: String,
        streak: Int,
        lastActive: String,
        isWinner: Boolean,
        isActive: Boolean
    ) {
        val row = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(0, 12, 0, 12)
        }

        val topRow = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity     = android.view.Gravity.CENTER_VERTICAL
        }

        val tvName = TextView(this).apply {
            text      = displayName
            textSize  = 15f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setTextColor(0xFF333333.toInt())
            layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
        }

        val tvStreakInfo = TextView(this).apply {
            text = when {
                isWinner            -> "🏆 Winner • 🔥 $streak day${if (streak == 1) "" else "s"}"
                streak > 0          -> "🔥 $streak day${if (streak == 1) "" else "s"} streak"
                isActive            -> "⏳ Awaiting check-in"
                else                -> "💔 Streak broken"
            }
            textSize = 13f
            setTextColor(
                when {
                    isWinner -> 0xFFFF8F00.toInt()
                    streak > 0 || isActive -> 0xFF388E3C.toInt()
                    else     -> 0xFFD32F2F.toInt()
                }
            )
        }

        val tvLastSeen = TextView(this).apply {
            text      = if (lastActive.isNotEmpty()) "Last active: $lastActive" else "Not yet active"
            textSize  = 11f
            setTextColor(0xFF888888.toInt())
            setPadding(0, 2, 0, 0)
        }

        topRow.addView(tvName)
        topRow.addView(tvStreakInfo)
        row.addView(topRow)
        row.addView(tvLastSeen)

        participantsContainer.addView(row)

        val divider = View(this).apply {
            layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1)
                .also { it.setMargins(0, 6, 0, 6) }
            setBackgroundColor(0xFFEEEEEE.toInt())
        }
        participantsContainer.addView(divider)
    }

    // ── (Streak-break auto-evaluation intentionally removed) ──────────────────────
    //
    // Reason: evaluateStreakBreaks() was using *historical* Firestore streak data
    // seeded into RTDB. A user whose account streak=0 (they haven't opened the app
    // in days) would be marked as "broken" the moment 1 day elapsed — even if they
    // join the challenge and then immediately do a check-in. This was fundamentally
    // unfair and caused the joiner to always win right after joining.
    //
    // The correct resolution policy is now:
    //   • daysLeft > 0 → show streaks live, no auto-end
    //   • daysLeft = 0 → resolve by highest current streak
    //   • Manual leave  → forfeit (opponent wins)


    // ── isStreakAlive ─────────────────────────────────────────────────────────────
    // Returns TRUE if:
    //   • lastActiveDate is empty   → no RTDB data yet, give benefit of doubt
    //   • lastActiveDate == today   → checked in today
    //   • lastActiveDate == yesterday → checked in yesterday (still within window)
    // Returns FALSE only if lastActiveDate is non-empty AND older than yesterday.

    private fun isStreakAlive(lastActiveDate: String): Boolean {
        if (lastActiveDate.isEmpty()) return true  // No RTDB mirror data yet — assume active
        val today     = sdf.format(Date())
        val yesterday = offsetDate(today, -1)
        return lastActiveDate == today || lastActiveDate == yesterday
    }

    private fun offsetDate(base: String, days: Int): String {
        return try {
            val cal = Calendar.getInstance()
            cal.time = sdf.parse(base) ?: return base
            cal.add(Calendar.DAY_OF_YEAR, days)
            sdf.format(cal.time)
        } catch (e: Exception) { base }
    }

    // ── Write result to RTDB ─────────────────────────────────────────────────────
    //
    // PERMISSION RULES:
    //  • challenges/<code>/*          — writable by any authenticated user (shared node)
    //  • users/<myUserId>/*           — writable only by the current user (we own this)
    //  • users/<opponentId>/*         — NOT writable by us (permission denied)
    //
    // Strategy:
    //  1. Write challenge status/winnerId to the shared challenges node (allowed)
    //  2. Update only our own joined_challenges entry (allowed)
    //  3. Leave a pendingStatusUpdate flag under the opponent's userStats in RTDB
    //     → They claim it on next GamificationActivity.onResume → loadJoinedChallenges()

    private fun endChallenge(winnerId: String?) {
        val iAmWinner = winnerId == myUserId

        // Step 1 — update shared challenge node (all authenticated users can write here)
        val challengeUpdates = mutableMapOf<String, Any>(
            "challenges/$challengeCode/status"  to "completed",
            "challenges/$challengeCode/endedAt" to System.currentTimeMillis()
        )
        if (winnerId != null) {
            challengeUpdates["challenges/$challengeCode/winnerId"] = winnerId
        }

        rtdb.updateChildren(challengeUpdates)
            .addOnSuccessListener {
                // Step 2 — update our own joined_challenges entry (we own this path)
                val myUpdate = mutableMapOf<String, Any>(
                    "users/$myUserId/joined_challenges/$challengeCode/status" to "completed"
                )
                if (winnerId != null) {
                    myUpdate["users/$myUserId/joined_challenges/$challengeCode/winnerId"] = winnerId
                }
                rtdb.updateChildren(myUpdate)

                // Step 3 — leave a flag so the opponent updates their entry next time they open the app
                rtdb.child("challenges").child(challengeCode).child("participants")
                    .addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snap: DataSnapshot) {
                            for (child in snap.children) {
                                val uid = child.key ?: continue
                                if (uid == myUserId) continue // already updated above

                                // Write a pending status update flag that the opponent claims on next open
                                rtdb.child("userStats").child(uid)
                                    .child("pendingChallengeUpdate")
                                    .setValue(mapOf(
                                        "challengeCode" to challengeCode,
                                        "status"        to "completed",
                                        "winnerId"      to (winnerId ?: "")
                                    ))
                            }
                        }
                        override fun onCancelled(error: DatabaseError) {}
                    })

                // Award shield to winner
                if (iAmWinner) {
                    awardShieldToWinner(myUserId)
                } else if (winnerId != null && winnerId != myUserId) {
                    // Opponent won — set pendingChallengeShield flag (claimed on their next check-in)
                    rtdb.child("userStats").child(winnerId).child("pendingChallengeShield")
                        .setValue(true)
                }

                runOnUiThread { loadChallenge() } // Refresh UI
            }
            .addOnFailureListener { e ->
                Log.e("ChallengeDetail", "Failed to end challenge: ${e.message}")
                runOnUiThread {
                    Toast.makeText(this, "Could not resolve challenge: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
    }

    /**
     * Awards a shield to the winner.
     * We can only write to Firestore for the current user (our own UID).
     * For the opponent's shield, we write a pending_shield flag to RTDB —
     * they'll claim it the next time they open GamificationActivity.
     */
    private fun awardShieldToWinner(uid: String) {
        if (uid == myUserId) {
            // Current user is the winner — write directly to OUR Firestore
            val gamRef = firestore.collection("users").document(uid)
                .collection("gamificationData").document("current")
            gamRef.get().addOnSuccessListener { doc ->
                val current = doc.getLong("shields")?.toInt() ?: 0
                gamRef.update("shields", current + 1).addOnSuccessListener {
                    // Also update RTDB mirror
                    rtdb.child("userStats").child(uid).child("shields")
                        .setValue(current + 1)
                    runOnUiThread {
                        Toast.makeText(
                            this,
                            "🛡️ You won! A Shield has been added to your account!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        } else {
            // Opponent is the winner — write a pending shield claim to RTDB
            // GamificationRepository.checkIn() will claim this on next app open
            rtdb.child("userStats").child(uid).child("pendingChallengeShield")
                .setValue(true)
        }
    }

    // ── Leave challenge ──────────────────────────────────────────────────────────

    private fun confirmLeave() {
        AlertDialog.Builder(this)
            .setTitle("Leave Challenge?")
            .setMessage("Leaving counts as forfeiting. Your opponent will be declared the winner.")
            .setPositiveButton("Leave") { _, _ ->
                // Find the opponent and make them the winner
                rtdb.child("challenges").child(challengeCode).child("participants")
                    .addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snap: DataSnapshot) {
                            val opponent = snap.children
                                .mapNotNull { it.key }
                                .firstOrNull { it != myUserId }
                            endChallenge(winnerId = opponent)
                            rtdb.child("users/$myUserId/joined_challenges/$challengeCode").removeValue()
                            Toast.makeText(this@ChallengeDetailActivity, "You left the challenge.", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                        override fun onCancelled(error: DatabaseError) { finish() }
                    })
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    // ── Data class ───────────────────────────────────────────────────────────────

    private data class ParticipantInfo(
        val uid: String,
        val name: String,
        val streak: Int,
        val lastActiveDate: String
    )
}

// Import DateTimeHelper at file level
private object DateTimeHelper {
    private val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    fun parseSimpleDate(date: String): Long? = try { sdf.parse(date)?.time } catch (e: Exception) { null }
}
