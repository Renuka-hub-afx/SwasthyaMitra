package com.example.swasthyamitra

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class JoinChallengeActivity : AppCompatActivity() {

    private lateinit var etChallengeCode: EditText
    private lateinit var btnJoin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_challenge)

        etChallengeCode = findViewById(R.id.etChallengeCode)
        btnJoin = findViewById(R.id.btnJoin)

        findViewById<View>(R.id.btnBack).setOnClickListener {
            finish()
        }

        btnJoin.setOnClickListener {
            val code = etChallengeCode.text.toString()
            if (code.isNotEmpty()) {
                Toast.makeText(this, "Joined Challenge $code Successfully! ⚔️", Toast.LENGTH_LONG).show()
                finish()
            } else {
                 Toast.makeText(this, "Please enter a code", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
