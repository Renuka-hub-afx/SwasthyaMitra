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

    /**
     * Core joining logic. 
     * Validates the code, ensures the user isn't the creator, and performs 
     * a 2-step write process to formalize the competition.
     */
    private fun joinChallenge(code: String) {
        val userId = authHelper.getCurrentUser()?.uid
        if (userId == null) {
            Toast.makeText(this, "Please log in first", Toast.LENGTH_SHORT).show()
            return
        }

        btnJoinChallenge.isEnabled = false
        btnJoinChallenge.text = "Joining..."

        // Step 1: Verify the challenge exists in the Realtime Database
        database.child("challenges").child(code)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (!snapshot.exists()) {
                        resetButton("❌ Challenge not found.")
                        return
                    }

                    val challengeName = snapshot.child("name").getValue(String::class.java) ?: "Unnamed Challenge"
                    val creatorId = snapshot.child("creatorId").getValue(String::class.java) ?: ""

                    // ── SAFETY CHECK: Prevent self-joining ─────────────────────────────────
                    if (creatorId == userId) {
                        resetButton("You created this! Share the code with a friend.")
                        return
                    }

                    // ── SAFETY CHECK: Prevent duplicate joining ────────────────────────────
                    val alreadyJoined = snapshot.child("participants").child(userId).exists()
                    if (alreadyJoined) {
                        resetButton(null)
                        showSuccessDialog(challengeName, code, alreadyMember = true)
                        return
                    }

                    val now = System.currentTimeMillis()

                    // Step 2: Register as a participant on the SHARED challenge node
                    database.child("challenges").child(code)
                        .child("participants").child(userId).setValue(true)
                        .addOnSuccessListener {
                            
                            // Step 3: Register the challenge in the user's PRIVATE dashboard
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
                                    // Step 4: Mirror Firestore stats to RTDB userStats
                                    // This makes the joiner's current streak visible to the creator
                                    seedJoinerStatsToRTDB(userId)
                                    resetButton(null)
                                    showSuccessDialog(challengeName, code, alreadyMember = false)
                                }
                                .addOnFailureListener { e ->
                                    resetButton("Failed to save challenge: ${e.message}")
                                }
                        }
                        .addOnFailureListener { e ->
                            resetButton("Failed to join: ${e.message}")
                        }
                }

                override fun onCancelled(error: DatabaseError) {
                    resetButton("Error: ${error.message}")
                }
            })
    }

    private fun resetButton(error: String?) {
        runOnUiThread {
            btnJoinChallenge.isEnabled = true
            btnJoinChallenge.text = "Join Challenge"
            if (error != null) {
                Toast.makeText(this@JoinChallengeActivity, error, Toast.LENGTH_LONG).show()
            }
        }
    }

    /**
     * UI Helper: Shows a victory dialog upon successful joining.
     */
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
     * Cross-User Sync:
     * Reads the joiner's Firestore streak data and uploads it to 'userStats/$uid' in RTDB.
     * This is necessary because the ChallengeDetailActivity can only read from RTDB 
     * to avoid triggering Firebase security permission errors.
     */
    private fun seedJoinerStatsToRTDB(userId: String) {
        val currentUser = authHelper.getCurrentUser() ?: return
        val displayName = currentUser.displayName ?: currentUser.email?.substringBefore("@") ?: "User"
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

                database.child("userStats").child(userId).updateChildren(
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

                // Indexing for friend retrieval
                if (userEmail.isNotEmpty()) {
                    val encodedEmail = userEmail.replace(".", ",")
                    database.child("userEmailIndex").child(encodedEmail).setValue(
                        mapOf("uid" to userId, "name" to displayName, "email" to userEmail)
                    )
                }
            }
            .addOnFailureListener {
                database.child("userStats").child(userId).updateChildren(
                    mapOf("uid" to userId, "name" to displayName, "email" to userEmail)
                )
            }
    }
}
