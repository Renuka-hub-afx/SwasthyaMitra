package com.example.swasthyamitra

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.google.firebase.database.FirebaseDatabase
import java.util.UUID

class ChallengeSetupActivity : AppCompatActivity() {

    private lateinit var authHelper: FirebaseAuthHelper
    private lateinit var etChallengeName: EditText
    private lateinit var btnCreateChallenge: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge_setup)
        
        authHelper = FirebaseAuthHelper(this)

        val btnBack = findViewById<android.view.View>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }
        
        etChallengeName = findViewById(R.id.etChallengeName)
        btnCreateChallenge = findViewById(R.id.btnCreateChallenge)
        
        btnCreateChallenge.setOnClickListener {
            createChallenge()
        }
    }

    private fun createChallenge() {
        val name = etChallengeName.text.toString().trim()
        if (name.isEmpty()) {
            etChallengeName.error = "Please enter a name"
            return
        }

        val userId = authHelper.getCurrentUser()?.uid
        if (userId == null) {
            Toast.makeText(this, "User not authenticated", Toast.LENGTH_SHORT).show()
            return
        }
        
        btnCreateChallenge.isEnabled = false
        btnCreateChallenge.text = "Creating..."

        // Generate 6-char alphanumeric code
        val challengeCode = java.util.UUID.randomUUID().toString().substring(0, 6).uppercase()
        val database = FirebaseDatabase.getInstance("https://swasthyamitra-c0899-default-rtdb.asia-southeast1.firebasedatabase.app").reference

        val challengeData = hashMapOf(
            "id" to challengeCode,
            "name" to name,
            "creatorId" to userId,
            "createdAt" to System.currentTimeMillis(),
            "participants" to mapOf(userId to true)
        )

        // Save using code as key for easy lookup
        database.child("challenges").child(challengeCode).setValue(challengeData)
            .addOnSuccessListener {
                showSuccessDialog(name, challengeCode)
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed to create: ${e.message}", Toast.LENGTH_SHORT).show()
                btnCreateChallenge.isEnabled = true
                btnCreateChallenge.text = "Create Challenge"
            }
    }

    private fun showSuccessDialog(name: String, code: String) {
        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
        builder.setTitle("Challenge Created! 🎉")
        builder.setMessage("Your Challenge Code is:\n\n$code\n\nShare this code with friends to join.")
        builder.setCancelable(false)
        
        builder.setPositiveButton("Share Code") { _, _ ->
            shareCode(name, code)
            finish()
        }
        
        builder.setNeutralButton("Copy") { _, _ ->
            val clipboard = getSystemService(android.content.Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
            val clip = android.content.ClipData.newPlainText("Challenge Code", code)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "Code Copied!", Toast.LENGTH_SHORT).show()
            finish()
        }
        
        builder.show()
    }

    private fun shareCode(name: String, code: String) {
        val message = "Hey! Join my fitness challenge '$name' on SwasthyaMitra! 💪\n\nEnter Code: $code"

        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, message)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, "Invite Friends")
        startActivity(shareIntent)
    }
}
