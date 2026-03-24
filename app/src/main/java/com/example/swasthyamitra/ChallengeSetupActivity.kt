package com.example.swasthyamitra

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.google.firebase.database.FirebaseDatabase

/**
 * ChallengeSetupActivity — Creates a new challenge.
 *
 * ── Permission-safe design ──────────────────────────────────────────────────────
 * We ONLY write to paths the creator owns:
 *   • challenges/<code>                              (shared, all authenticated users can write)
 *   • users/<creatorUid>/joined_challenges/<code>   (our own path, we have write permission)
 *
 * We do NOT attempt to write to the friend's users/<friendUid>/... path — that
 * would cause "Permission denied". Instead, the friend joins via the code using
 * JoinChallengeActivity, which writes to their own paths only.
 *
 * The "Friend's Email" field is used ONLY to pre-fill the share message.
 * No RTDB or Firestore lookup is performed — no permission issues at all.
 * ────────────────────────────────────────────────────────────────────────────────
 */
class ChallengeSetupActivity : AppCompatActivity() {

    private lateinit var authHelper: FirebaseAuthHelper
    private lateinit var etChallengeName: EditText
    private lateinit var etFriendEmail: EditText
    private lateinit var btnCreateChallenge: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var tvFriendStatus: TextView

    private val rtdb by lazy {
        FirebaseDatabase.getInstance(
            "https://swasthyamitra-ded44-default-rtdb.asia-southeast1.firebasedatabase.app"
        ).reference
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge_setup)

        authHelper = FirebaseAuthHelper(this)

        findViewById<View>(R.id.btnBack).setOnClickListener { finish() }

        etChallengeName    = findViewById(R.id.etChallengeName)
        etFriendEmail      = findViewById(R.id.etFriendEmail)
        btnCreateChallenge = findViewById(R.id.btnCreateChallenge)
        progressBar        = findViewById(R.id.progressBarSetup)
        tvFriendStatus     = findViewById(R.id.tvFriendStatus)

        // Explain what the email field actually does
        tvFriendStatus.text = "💡 The challenge code will be shared with this email."
        tvFriendStatus.setTextColor(0xFF666666.toInt())
        tvFriendStatus.visibility = View.VISIBLE

        // Show the hint text as the user types an email
        etFriendEmail.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val email = etFriendEmail.text.toString().trim()
                if (email.isNotEmpty()) {
                    tvFriendStatus.text = "📨 Code will be shared with: $email"
                    tvFriendStatus.setTextColor(0xFF388E3C.toInt())
                } else {
                    tvFriendStatus.text = "💡 The challenge code will be shared with this email."
                    tvFriendStatus.setTextColor(0xFF666666.toInt())
                }
            }
        }

        btnCreateChallenge.setOnClickListener { createChallenge() }
    }

    // ── Create challenge — no cross-user reads or writes ─────────────────────────

    /**
     * Entry point for challenge creation. Validates input and initiates the 
     * multi-database sync process.
     */
    private fun createChallenge() {
        val name        = etChallengeName.text.toString().trim()
        val friendEmail = etFriendEmail.text.toString().trim()

        if (name.isEmpty()) {
            etChallengeName.error = "Please enter a challenge name"
            return
        }

        val userId = authHelper.getCurrentUser()?.uid
        if (userId == null) {
            Toast.makeText(this, "Please log in first", Toast.LENGTH_SHORT).show()
            return
        }

        setLoading(true)
        doCreateChallenge(name, userId, friendEmail.ifEmpty { null })
    }

    /**
     * Executes the challenge creation logic.
     * 1. Generates a unique 6-character alphanumeric code.
     * 2. Performs an atomic update to two RTDB paths:
     *    - 'challenges/$code': Metadata for the challenge itself.
     *    - 'users/$uid/joined_challenges/$code': Creator's membership record.
     */
    private fun doCreateChallenge(name: String, userId: String, friendEmail: String?) {
        val challengeCode = java.util.UUID.randomUUID().toString()
            .filter { it.isLetterOrDigit() }
            .take(6)
            .uppercase()

        val now = System.currentTimeMillis()

        val challengeData = hashMapOf<String, Any>(
            "id"           to challengeCode,
            "name"         to name,
            "creatorId"    to userId,
            "createdAt"    to now,
            "participants" to mapOf(userId to true), 
            "status"       to "active",
            "durationDays" to 7
        )

        val updates = mapOf<String, Any>(
            "challenges/$challengeCode" to challengeData,
            "users/$userId/joined_challenges/$challengeCode" to mapOf(
                "challengeId"   to challengeCode,
                "challengeName" to name,
                "joinedAt"      to now,
                "status"        to "active",
                "role"          to "creator"
            )
        )

        rtdb.updateChildren(updates)
            .addOnSuccessListener {
                setLoading(false)
                // Mirrors data from Firestore to RTDB to enable real-time competition
                seedCreatorStatsToRTDB(userId)
                showSuccessDialog(name, challengeCode, friendEmail)
            }
            .addOnFailureListener { e ->
                setLoading(false)
                Toast.makeText(this, "Failed to create challenge: ${e.message}", Toast.LENGTH_LONG).show()
            }
    }

    /**
     * Performance Optimization Service:
     * Reads the creator's current streak/shields from Firestore (Source of Truth) 
     * and seeds them into RTDB ('userStats/$uid').
     * 
     * This allows ChallengeDetailActivity to listen to a single RTDB path for 
     * LIVE updates of all participants, avoiding expensive and slow Firestore 
     * cross-document polling.
     */
    private fun seedCreatorStatsToRTDB(userId: String) {
        val displayName = authHelper.getCurrentUser()?.displayName
            ?: authHelper.getCurrentUser()?.email?.substringBefore("@")
            ?: "User"
        val userEmail   = authHelper.getCurrentUser()?.email ?: ""

        val db = try {
            com.google.firebase.firestore.FirebaseFirestore.getInstance("renu")
        } catch (e: Exception) {
            com.google.firebase.firestore.FirebaseFirestore.getInstance()
        }

        db.collection("users").document(userId)
            .collection("gamificationData").document("current")
            .get()
            .addOnSuccessListener { doc ->
                val streak     = doc.getLong("streak")?.toInt() ?: 0
                val lastActive = doc.getString("lastActiveDate") ?: ""
                val shields    = doc.getLong("shields")?.toInt() ?: 0

                rtdb.child("userStats").child(userId).setValue(
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
                // Creates an index for friends to find this user by email
                if (userEmail.isNotEmpty()) {
                    val encodedEmail = userEmail.replace(".", ",")
                    rtdb.child("userEmailIndex").child(encodedEmail).setValue(
                        mapOf("uid" to userId, "name" to displayName, "email" to userEmail)
                    )
                }
            }
            .addOnFailureListener {
                rtdb.child("userStats").child(userId).updateChildren(
                    mapOf(
                        "uid"  to userId,
                        "name" to displayName,
                        "email" to userEmail
                    )
                )
            }
    }

    /**
     * UI Helper: Shows the confirmation dialog with the challenge code 
     * and explains the "Survivor" game rules.
     */
    private fun showSuccessDialog(name: String, code: String, friendEmail: String?) {
        val message = buildString {
            append("Your challenge code is:\n\n")
            append("🔑  $code  🔑\n\n")
            if (!friendEmail.isNullOrEmpty()) {
                append("Share this code with $friendEmail so they can join!\n\n")
            } else {
                append("Share this code with a friend so they can join!\n\n")
            }
            append("📋 Challenge Rules:\n")
            append("• Maintain your daily streak every day\n")
            append("• Missing a day (without a shield) = you lose\n")
            append("• If both break, the one who lasted longer wins\n")
            append("• 🏆 Winner earns a FREE Shield!")
        }

        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
        builder.setTitle("🎉 Challenge Created!")
        builder.setMessage(message)
        builder.setCancelable(false)

        builder.setPositiveButton("📤 Share Code") { _, _ ->
            shareCode(name, code, friendEmail)
            setResult(RESULT_OK)
            finish()
        }

        builder.setNeutralButton("📋 Copy") { _, _ ->
            val clipboard = getSystemService(android.content.Context.CLIPBOARD_SERVICE)
                    as android.content.ClipboardManager
            val clip = android.content.ClipData.newPlainText("Challenge Code", code)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "✅ Code Copied: $code", Toast.LENGTH_LONG).show()
            setResult(RESULT_OK)
            finish()
        }

        builder.show()
    }

    /**
     * Triggers the system Share Sheet with a pre-written invite message 
     * containing the challenge code.
     */
    private fun shareCode(name: String, code: String, friendEmail: String?) {
        val message = buildString {
            append("Hey! Join my fitness challenge \"$name\" on SwasthyaMitra! 💪\n\n")
            append("Enter this code to join:\n")
            append("👉  $code\n\n")
            append("Let's see who maintains their streak longer! 🔥")
        }
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, message)
            if (!friendEmail.isNullOrEmpty()) {
                putExtra(Intent.EXTRA_EMAIL, arrayOf(friendEmail))
            }
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Share Challenge Code"))
    }


    // ── Helpers ──────────────────────────────────────────────────────────────────

    private fun setLoading(loading: Boolean) {
        btnCreateChallenge.isEnabled = !loading
        btnCreateChallenge.text      = if (loading) "Creating…" else "Create Challenge"
        progressBar.visibility       = if (loading) View.VISIBLE else View.GONE
    }
}
