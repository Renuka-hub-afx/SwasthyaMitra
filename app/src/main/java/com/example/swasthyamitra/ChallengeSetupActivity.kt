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

    // ── Write challenge to RTDB — only creator's own paths ───────────────────────

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
            "participants" to mapOf(userId to true),   // only creator for now
            "status"       to "active",
            "durationDays" to 7
        )

        // ── Only write paths the creator OWNS ─────────────────────────────────────
        val updates = mapOf<String, Any>(
            // The shared challenge node — readable/writable by all authenticated users
            "challenges/$challengeCode" to challengeData,

            // Creator's own joined_challenges entry — they own this path
            "users/$userId/joined_challenges/$challengeCode" to mapOf(
                "challengeId"   to challengeCode,
                "challengeName" to name,
                "joinedAt"      to now,
                "status"        to "active",
                "role"          to "creator"
            )
        )
        // NOTE: We intentionally do NOT write to users/<friendUid>/joined_challenges.
        // That would require knowing the friend's UID AND writing to their path — both
        // are blocked by RTDB security rules. The friend joins via JoinChallengeActivity.

        rtdb.updateChildren(updates)
            .addOnSuccessListener {
                setLoading(false)
                // Immediately seed creator's userStats in RTDB so ChallengeDetailActivity
                // has real streak data from day 1. We read our OWN Firestore doc (always allowed).
                seedCreatorStatsToRTDB(userId)
                showSuccessDialog(name, challengeCode, friendEmail)
            }
            .addOnFailureListener { e ->
                setLoading(false)
                Toast.makeText(this, "Failed to create challenge: ${e.message}", Toast.LENGTH_LONG).show()
            }
    }

    /**
     * Reads the creator's OWN Firestore gamificationData (permitted) and mirrors it
     * to RTDB userStats/<uid> so ChallengeDetailActivity can read it without permission issues.
     * Falls back to a minimal entry (name only) if Firestore read fails.
     */
    private fun seedCreatorStatsToRTDB(userId: String) {
        val displayName = authHelper.getCurrentUser()?.displayName
            ?: authHelper.getCurrentUser()?.email?.substringBefore("@")
            ?: "User"
        val userEmail   = authHelper.getCurrentUser()?.email ?: ""

        // Try to read our own Firestore gamificationData
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
                // Also publish email index
                if (userEmail.isNotEmpty()) {
                    val encodedEmail = userEmail.replace(".", ",")
                    rtdb.child("userEmailIndex").child(encodedEmail).setValue(
                        mapOf("uid" to userId, "name" to displayName, "email" to userEmail)
                    )
                }
            }
            .addOnFailureListener {
                // Fallback — write minimal entry so name at least shows up
                rtdb.child("userStats").child(userId).updateChildren(
                    mapOf(
                        "uid"  to userId,
                        "name" to displayName,
                        "email" to userEmail
                        // lastActiveDate intentionally omitted = empty = treated as "active (no data yet)"
                    )
                )
            }
    }

    // ── Success dialog + share ────────────────────────────────────────────────────

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
