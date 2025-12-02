package com.example.swasthyamitra

import android.content.Intent
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.swasthyamitra.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Ensure this XML file exists and has the button

        // Apply visual effects
        applyGradientToText()

        // 1. Find the start button
        val startButton = findViewById<Button>(R.id.button_start)

        // 2. Set the click listener to open LoginActivity
        startButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun applyGradientToText() {
        // Wrap in try-catch to prevent crashes if the ID is missing
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

                    val shader = LinearGradient(
                        0f, 0f, textWidth, textHeight,
                        gradientColors,
                        null,
                        Shader.TileMode.CLAMP
                    )
                    appNameTextView.paint.shader = shader
                    appNameTextView.invalidate()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}