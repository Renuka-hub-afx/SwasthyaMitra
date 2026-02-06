package com.example.swasthyamitra

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.swasthyamitra.databinding.ActivityForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sendLinkButton.setOnClickListener {
            handleResetPassword()
        }

        binding.backToSignIn.setOnClickListener {
            finish()
        }
    }

    private fun handleResetPassword() {
        val email = binding.emailInput.text.toString().trim()

        if (email.isEmpty()) {
            binding.emailInput.error = "Enter your email address"
            binding.emailInput.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailInput.error = "Enter a valid email address"
            binding.emailInput.requestFocus()
            return
        }

        sendPasswordResetEmail(email)
    }

    private fun sendPasswordResetEmail(email: String) {
        Toast.makeText(this, "Sending reset link...", Toast.LENGTH_SHORT).show()
        
        val auth = FirebaseAuth.getInstance()
        
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("ForgotPassword", "Reset email sent to $email")
                    Toast.makeText(
                        this,
                        "✅ Password reset link sent to $email. Check your inbox!",
                        Toast.LENGTH_LONG
                    ).show()
                    // Optionally finish and go back after success
                    // finish() 
                } else {
                    val exception = task.exception
                    Log.e("ForgotPassword", "Reset failed: ${exception?.message}")
                    
                    val errorMessage = when {
                        exception is com.google.firebase.FirebaseNetworkException ->
                            "Network Error: Please check your internet or try a mobile hotspot."
                        exception?.message?.contains("no user record", ignoreCase = true) == true ||
                        exception?.message?.contains("user-not-found", ignoreCase = true) == true ->
                            "No account found with this email."
                        exception?.message?.contains("too-many-requests", ignoreCase = true) == true ->
                            "Too many attempts. Please try again soon."
                        else -> "Failed: ${exception?.localizedMessage ?: "Unknown error"}"
                    }
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
                }
            }
    }
}
