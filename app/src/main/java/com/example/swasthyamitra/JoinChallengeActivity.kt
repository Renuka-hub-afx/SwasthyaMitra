package com.example.swasthyamitra

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class JoinChallengeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_challenge)
        
        val btnBack = findViewById<android.view.View>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }
    }
}
