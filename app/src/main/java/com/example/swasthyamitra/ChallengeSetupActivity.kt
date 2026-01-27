package com.example.swasthyamitra

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.UUID

class ChallengeSetupActivity : AppCompatActivity() {

    private lateinit var etChallengeName: EditText
    private lateinit var etDuration: EditText
    private lateinit var etWager: EditText
    private lateinit var btnCreateChallenge: Button
    private lateinit var tvCodeDisplay: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge_setup)

        initViews()
        setupListeners()
    }

    private fun initViews() {
        etChallengeName = findViewById(R.id.etChallengeName)
        etDuration = findViewById(R.id.etDuration)
        etWager = findViewById(R.id.etWager)
        btnCreateChallenge = findViewById(R.id.btnCreateChallenge)
        tvCodeDisplay = findViewById(R.id.tvCodeDisplay)

        findViewById<View>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }

    private fun setupListeners() {
        btnCreateChallenge.setOnClickListener {
            val name = etChallengeName.text.toString()
            if (name.isEmpty()) {
                Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Generate Mock Code
            val code = "DUEL-" + UUID.randomUUID().toString().take(4).uppercase()
            
            tvCodeDisplay.text = "Code: $code"
            tvCodeDisplay.visibility = View.VISIBLE
            
            btnCreateChallenge.isEnabled = false
            Toast.makeText(this, "Challenge Created! Share this code.", Toast.LENGTH_LONG).show()
        }
    }
}
