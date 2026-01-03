package com.example.swasthyamitra

import android.content.Intent
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var authHelper: FirebaseAuthHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val application = application as UserApplication
        authHelper = application.authHelper

        // Check if user is already logged in
        checkAutoLogin()
    }

    private fun checkAutoLogin() {
        if (authHelper.isUserLoggedIn()) {
            // User is logged in, get user ID and navigate to homepage
            val userId = authHelper.getCurrentUser()?.uid
            if (userId != null) {
                navigateToHomePage(userId)
            } else {
                showWelcomeScreen()
            }
        } else {
            showWelcomeScreen()
        }
    }

    private fun showWelcomeScreen() {
        setContentView(R.layout.activity_main)
        applyGradientToText()

        val startButton = findViewById<Button>(R.id.button_start)

        // NAVIGATE TO LOGIN
        startButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun navigateToHomePage(userId: String) {
        val intent = Intent(this, homepage::class.java)
        intent.putExtra("USER_ID", userId)
        startActivity(intent)
        finish()
    }

    private fun applyGradientToText() {
        try {
            val appNameTextView = findViewById<TextView>(R.id.text_app_name)
            if (appNameTextView != null) {
                val gradientColors = intArrayOf(
                    ContextCompat.getColor(this, R.color.text_gradient_start),
                    ContextCompat.getColor(this, R.color.text_gradient_middle),
                    ContextCompat.getColor(this, R.color.text_gradient_end)
                )

                appNameTextView.post {
                    val textWidth = appNameTextView.measuredWidth.toFloat()
                    val textHeight = appNameTextView.textSize
                    val shader = LinearGradient(0f, 0f, textWidth, textHeight, gradientColors, null, Shader.TileMode.CLAMP)
                    appNameTextView.paint.shader = shader
                    appNameTextView.invalidate()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}