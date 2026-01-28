package com.example.swasthyamitra

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ChallengeSetupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge_setup)
        
        val btnBack = findViewById<android.view.View>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }
        
        // Additional setup logic can be added here
    }
}
