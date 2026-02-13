package com.example.swasthyamitra

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.example.swasthyamitra.databinding.ActivityForgotPasswordBinding
import com.google.firebase.firestore.FirebaseFirestore

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.sendLinkButton.setOnClickListener {
            val email = binding.emailInput.text.toString().trim()
            if (isValidEmail(email)) {
                sendPasswordResetEmail(email)
            } else {
                binding.emailInput.error = "Enter a valid email"
            }
        }
        
        binding.backToSignIn.setOnClickListener {
            finish()
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun sendPasswordResetEmail(email: String) {
        // specific generic message to avoid user enumeration
        // but for now, let's keep it helpful for the user as per original code context
        Toast.makeText(this, "Sending reset link...", Toast.LENGTH_SHORT).show()
        
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("ForgotPassword", "Reset email sent to $email")
                    Toast.makeText(
                        this,
                        "✅ Password reset link sent to $email. Check your inbox!",
                        Toast.LENGTH_LONG
                    ).show()
                    finish() // Close activity on success
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
