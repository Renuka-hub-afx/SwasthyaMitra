package com.example.swasthyamitra

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class JoinChallengeActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_challenge)
        
        val etChallengeCode = findViewById<EditText>(R.id.etChallengeCode)
        val btnJoinChallenge = findViewById<Button>(R.id.btnJoinChallenge)
        
        btnJoinChallenge?.setOnClickListener {
            val code = etChallengeCode?.text?.toString()?.uppercase() ?: ""
            
            if (code.isEmpty()) {
                Toast.makeText(this, "Please enter a challenge code", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            joinChallenge(code)
        }
        
        findViewById<android.view.View>(R.id.btnBack)?.setOnClickListener {
            finish()
        }
    }
    
    private fun joinChallenge(code: String) {
        val database = FirebaseDatabase.getInstance("https://swasthyamitra-c0899-default-rtdb.asia-southeast1.firebasedatabase.app").reference
        
        database.child("challenges").child(code)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        Toast.makeText(
                            this@JoinChallengeActivity,
                            "Joined challenge: ${snapshot.child("name").value}",
                            Toast.LENGTH_LONG
                        ).show()
                        finish()
                    } else {
                        Toast.makeText(
                            this@JoinChallengeActivity,
                            "Challenge not found",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(
                        this@JoinChallengeActivity,
                        "Error: ${error.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }
}
