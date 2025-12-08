package com.example.swasthyamitra

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.swasthyamitra.databinding.ActivitySignupBinding
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var authHelper: FirebaseAuthHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth Helper
        val application = application as UserApplication
        authHelper = application.authHelper

        // Hide inline DatePicker if present (optional cleanup)
        binding.datePicker1?.visibility = View.GONE

        // Open popup DatePicker
        binding.textView8.setOnClickListener { showDatePicker() }

        // Signup button
        binding.signupButton.setOnClickListener { validateAndSave() }
    }

    private fun showDatePicker() {
        val cal = Calendar.getInstance()

        DatePickerDialog(
            this,
            { _, year, month, day ->
                val selected = Calendar.getInstance()
                selected.set(year, month, day)

                val dob = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                    .format(selected.time)

                binding.textView8.text = "BirthDate: $dob"
                binding.textView8.tag = dob
            },
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun validateAndSave() {
        val name = binding.nameInput.text.toString().trim()
        val email = binding.emailInput.text.toString().trim()
        val dob = binding.textView8.tag?.toString() ?: ""
        val pass = binding.passwordInput.text.toString()
        val confirm = binding.confirmPasswordInput.text.toString()

        when {
            name.isEmpty() -> {
                binding.nameInput.error = "Enter your name"
                return
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                binding.emailInput.error = "Enter valid email"
                return
            }
            dob.isEmpty() -> {
                Toast.makeText(this, "Select birth date", Toast.LENGTH_SHORT).show()
                return
            }
            pass.length < 6 -> {
                binding.passwordInput.error = "Minimum 6 characters"
                return
            }
            pass != confirm -> {
                binding.confirmPasswordInput.error = "Passwords do not match"
                return
            }
        }

        // Calculate age from DOB
        val age = calculateAge(dob)

        lifecycleScope.launch {
            val result = authHelper.signUpWithEmail(
                email = email,
                password = pass,
                name = name,
                phoneNumber = "", // Can add phone number field if needed
                age = age
            )
            
            result.onSuccess {
                runOnUiThread {
                    Toast.makeText(this@SignupActivity, "Signup Successful! Please log in.", Toast.LENGTH_LONG).show()
                    
                    // Navigate back to LoginActivity
                    val intent = Intent(this@SignupActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }.onFailure { e ->
                runOnUiThread {
                    Toast.makeText(this@SignupActivity, "Signup failed: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun calculateAge(dob: String): Int {
        return try {
            val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            val birthDate = sdf.parse(dob)
            val today = Calendar.getInstance()
            val birth = Calendar.getInstance()
            birth.time = birthDate
            
            var age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR)
            if (today.get(Calendar.DAY_OF_YEAR) < birth.get(Calendar.DAY_OF_YEAR)) {
                age--
            }
            age
        } catch (e: Exception) {
            0
        }
    }
}
