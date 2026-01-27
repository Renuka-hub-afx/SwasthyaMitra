package com.example.swasthyamitra

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class ChallengeSetupActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge_setup)
        
        val etChallengeName = findViewById<EditText>(R.id.etChallengeName)
        val etTargetSteps = findViewById<EditText>(R.id.etTargetSteps)
        val btnCreateChallenge = findViewById<Button>(R.id.btnCreateChallenge)
        
        btnCreateChallenge?.setOnClickListener {
            val name = etChallengeName?.text?.toString() ?: ""
            val target = etTargetSteps?.text?.toString()?.toIntOrNull() ?: 0
            
            if (name.isEmpty() || target <= 0) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            createChallenge(name, target)
        }
        
        findViewById<android.view.View>(R.id.btnBack)?.setOnClickListener {
            finish()
        }
    }
    
    private fun createChallenge(name: String, targetSteps: Int) {
        val challengeId = UUID.randomUUID().toString().substring(0, 6).uppercase()
        val database = FirebaseDatabase.getInstance("https://swasthyamitra-c0899-default-rtdb.asia-southeast1.firebasedatabase.app").reference
        
        val challengeData = mapOf(
            "id" to challengeId,
            "name" to name,
            "targetSteps" to targetSteps,
            "createdAt" to System.currentTimeMillis()
        )
        
        database.child("challenges").child(challengeId).setValue(challengeData)
            .addOnSuccessListener {
                Toast.makeText(this, "Challenge created! Code: $challengeId", Toast.LENGTH_LONG).show()
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to create challenge", Toast.LENGTH_SHORT).show()
            }
    }
}
