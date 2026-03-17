package com.example.swasthyamitra

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class JoinChallengeActivity : AppCompatActivity() {

    private lateinit var authHelper: FirebaseAuthHelper
    private lateinit var etChallengeCode: TextInputEditText
    private lateinit var btnJoinChallenge: Button
    private lateinit var progressBar: ProgressBar

    private val database by lazy {
        FirebaseDatabase.getInstance(
            "https://swasthyamitra-ded44-default-rtdb.asia-southeast1.firebasedatabase.app"
        ).reference
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_challenge)

        authHelper = FirebaseAuthHelper(this)

        // Back button
        findViewById<View>(R.id.btnBack).setOnClickListener { finish() }

        etChallengeCode = findViewById(R.id.etChallengeCode)
        btnJoinChallenge = findViewById(R.id.btnJoinChallenge)

        // Add a ProgressBar programmatically if not in layout
        progressBar = ProgressBar(this, null, android.R.attr.progressBarStyle).apply {
            isIndeterminate = true
            visibility = View.GONE
        }

        btnJoinChallenge.setOnClickListener {
            val code = etChallengeCode.text?.toString()?.trim()?.uppercase() ?: ""
            if (code.length != 6) {
                etChallengeCode.error = "Enter a valid 6-character code"
                return@setOnClickListener
            }
            joinChallenge(code)
        }
    }

    private fun joinChallenge(code: String) {
        val userId = authHelper.getCurrentUser()?.uid
        if (userId == null) {
            Toast.makeText(this, "Please log in first", Toast.LENGTH_SHORT).show()
            return
        }

        // Disable button while loading
        btnJoinChallenge.isEnabled = false
        btnJoinChallenge.text = "Joining..."

        // Look up the challenge code in RTDB
        database.child("challenges").child(code)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (!snapshot.exists()) {
                        runOnUiThread {
                            btnJoinChallenge.isEnabled = true
                            btnJoinChallenge.text = "Join Challenge"
                            Toast.makeText(
                                this@JoinChallengeActivity,
                                "❌ Challenge not found. Check the code and try again.",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        return
                    }

                    val challengeName = snapshot.child("name").getValue(String::class.java) ?: "Unnamed Challenge"
                    val creatorId = snapshot.child("creatorId").getValue(String::class.java) ?: ""

                    // Prevent joining your own challenge
                    if (creatorId == userId) {
                        runOnUiThread {
                            btnJoinChallenge.isEnabled = true
                            btnJoinChallenge.text = "Join Challenge"
                            Toast.makeText(
                                this@JoinChallengeActivity,
                                "You created this challenge! Share the code with a friend.",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        return
                    }

                    // Check if already joined
                    val alreadyJoined = snapshot.child("participants").child(userId).exists()
                    if (alreadyJoined) {
                        runOnUiThread {
                            btnJoinChallenge.isEnabled = true
                            btnJoinChallenge.text = "Join Challenge"
                            showSuccessDialog(challengeName, code, alreadyMember = true)
                        }
                        return
                    }

                    // ── Write only paths the joiner owns ─────────────────────────────────
                    // DO NOT write to users/<creatorId>/... — RTDB rules block cross-user writes.
                    // The creator's joined_challenges entry was already created when they made
                    // the challenge in ChallengeSetupActivity (atomic multi-path write).
                    val now = System.currentTimeMillis()

                    // Step A: mark joiner as a participant on the central challenge node
                    // (challenges/<code>/participants/<userId> = true)
                    database.child("challenges").child(code)
                        .child("participants").child(userId).setValue(true)
                        .addOnSuccessListener {
                            // Step B: write joiner's own joined_challenges entry — they OWN this path
                            database.child("users").child(userId)
                                .child("joined_challenges").child(code)
                                .setValue(mapOf(
                                    "challengeId"   to code,
                                    "challengeName" to challengeName,
                                    "joinedAt"      to now,
                                    "status"        to "active",
                                    "role"          to "participant"
                                ))
                                .addOnSuccessListener {
                                    // Seed joiner's stats to RTDB userStats so creator can
                                    // see their real streak in ChallengeDetailActivity
                                    seedJoinerStatsToRTDB(userId)
                                    runOnUiThread {
                                        btnJoinChallenge.isEnabled = true
                                        btnJoinChallenge.text = "Join Challenge"
                                        showSuccessDialog(challengeName, code, alreadyMember = false)
                                    }
                                }
                                .addOnFailureListener { e ->
                                    runOnUiThread {
                                        btnJoinChallenge.isEnabled = true
                                        btnJoinChallenge.text = "Join Challenge"
                                        Toast.makeText(
                                            this@JoinChallengeActivity,
                                            "Failed to save challenge: ${e.message}",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                        }
                        .addOnFailureListener { e ->
                            runOnUiThread {
                                btnJoinChallenge.isEnabled = true
                                btnJoinChallenge.text = "Join Challenge"
                                Toast.makeText(
                                    this@JoinChallengeActivity,
                                    "Failed to join: ${e.message}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                }

                override fun onCancelled(error: DatabaseError) {
                    runOnUiThread {
                        btnJoinChallenge.isEnabled = true
                        btnJoinChallenge.text = "Join Challenge"
                        Toast.makeText(
                            this@JoinChallengeActivity,
                            "Error: ${error.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })
    }

    private fun showSuccessDialog(name: String, code: String, alreadyMember: Boolean) {
        val title = if (alreadyMember) "Already Joined! ⚔️" else "Challenge Joined! ⚔️🎉"
        val message = if (alreadyMember) {
            "You're already part of \"$name\".\n\nCode: $code"
        } else {
            "You've joined \"$name\"!\n\nCode: $code\n\n📋 Rules:\n• Maintain your daily streak\n• Missing a day (without a shield) = you lose\n• 🏆 Winner earns a FREE Shield!"
        }

        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton("Let's Go! 💪") { _, _ ->
                setResult(RESULT_OK)
                finish()
            }
            .show()
    }

    /**
     * Seeds the joiner's own Firestore gamificationData into RTDB userStats.
     * Reads from OWN Firestore doc (always permitted) so the creator can immediately
     * see real streak data in ChallengeDetailActivity without waiting for a GamificationActivity visit.
     */
    private fun seedJoinerStatsToRTDB(userId: String) {
        val currentUser = authHelper.getCurrentUser() ?: return
        val displayName = currentUser.displayName
            ?: currentUser.email?.substringBefore("@")
            ?: "User"
        val userEmail = currentUser.email ?: ""

        val firestore = try {
            com.google.firebase.firestore.FirebaseFirestore.getInstance("renu")
        } catch (e: Exception) {
            com.google.firebase.firestore.FirebaseFirestore.getInstance()
        }

        firestore.collection("users").document(userId)
            .collection("gamificationData").document("current")
            .get()
            .addOnSuccessListener { doc ->
                val streak     = doc.getLong("streak")?.toInt() ?: 0
                val lastActive = doc.getString("lastActiveDate") ?: ""
                val shields    = doc.getLong("shields")?.toInt() ?: 0

                database.child("userStats").child(userId).setValue(
                    mapOf(
                        "uid"            to userId,
                        "name"           to displayName,
                        "email"          to userEmail,
                        "streak"         to streak,
                        "shields"        to shields,
                        "lastActiveDate" to lastActive,
                        "updatedAt"      to System.currentTimeMillis()
                    )
                )

                // Also publish email→uid index
                if (userEmail.isNotEmpty()) {
                    val encodedEmail = userEmail.replace(".", ",")
                    database.child("userEmailIndex").child(encodedEmail).setValue(
                        mapOf("uid" to userId, "name" to displayName, "email" to userEmail)
                    )
                }
            }
            .addOnFailureListener {
                // Fallback — write name so at least the display works
                // Omit lastActiveDate → treated as active (benefit of doubt)
                database.child("userStats").child(userId).updateChildren(
                    mapOf(
                        "uid"   to userId,
                        "name"  to displayName,
                        "email" to userEmail
                    )
                )
            }
    }
}
