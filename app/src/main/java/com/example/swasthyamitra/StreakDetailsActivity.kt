package com.example.swasthyamitra

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StreakDetailsActivity : AppCompatActivity() {
    
    private var viewMode: String = "STREAK"
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_streak_details)
        
        viewMode = intent.getStringExtra("VIEW_MODE") ?: "STREAK"
        val streakCount = intent.getIntExtra("STREAK_COUNT", 0)
        val shieldCount = intent.getIntExtra("SHIELD_COUNT", 0)
        
        setupUI(streakCount, shieldCount)
        
        findViewById<android.view.View>(R.id.btnBack)?.setOnClickListener {
            finish()
        }
    }
    
    private fun setupUI(streakCount: Int, shieldCount: Int) {
        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val tvDescription = findViewById<TextView>(R.id.tvDescription)
        val tvMainValue = findViewById<TextView>(R.id.tvMainValue)
        
        when (viewMode) {
            "STREAK" -> {
                tvTitle?.text = "🔥 Streak Details"
                tvMainValue?.text = "$streakCount days"
                tvDescription?.text = "Keep your daily goals to maintain your streak!"
            }
            "SHIELD" -> {
                tvTitle?.text = "🛡️ Shield Details"
                tvMainValue?.text = "$shieldCount shields"
                tvDescription?.text = "Shields protect your streak when you miss a day. Earn shields by maintaining streaks!"
            }
        }
    }
}
