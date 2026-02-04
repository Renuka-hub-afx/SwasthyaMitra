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
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val application = application as? UserApplication
        if (application == null) {
            Toast.makeText(this, "App initialization error. Restarting...", Toast.LENGTH_LONG).show()
            finish()
            return
        }
        authHelper = application.authHelper

        binding.datePicker1?.visibility = View.GONE
        binding.textView8.setOnClickListener { showDatePicker() }
        binding.signupButton.setOnClickListener { validateAndSave() }
    }

    private fun showDatePicker() {
        val cal = Calendar.getInstance()
        DatePickerDialog(
            this,
            { _, year, month, day ->
                val selected = Calendar.getInstance()
                selected.set(year, month, day)
                val dob = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(selected.time)
                binding.textView8.text = "BirthDate: $dob"
                binding.textView8.tag = dob
            },
            cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun validateAndSave() {
        val name = binding.nameInput.text.toString().trim()
        val email = binding.emailInput.text.toString().trim()
        val phoneNumber = binding.phoneInput.text.toString().trim()
        val dob = binding.textView8.tag?.toString() ?: ""
        val pass = binding.passwordInput.text.toString()
        val confirm = binding.confirmPasswordInput.text.toString()

        if (name.isEmpty()) { binding.nameInput.error = "Enter name"; return }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) { binding.emailInput.error = "Valid email required"; return }
        
        // Phone validation - 10-15 digits, optional country code
        if (!isValidPhone(phoneNumber)) { 
            binding.phoneInput.error = "Enter valid phone (10-15 digits, with country code)"
            return 
        }
        
        if (dob.isEmpty()) { Toast.makeText(this, "Select birth date", Toast.LENGTH_SHORT).show(); return }
        
        // Strong password validation
        val passwordError = getPasswordError(pass)
        if (passwordError != null) {
            binding.passwordInput.error = passwordError
            return
        }
        
        if (pass != confirm) { binding.confirmPasswordInput.error = "Passwords don't match"; return }

        val age = calculateAge(dob)

        lifecycleScope.launch {
            val result = authHelper.signUpWithEmail(email, pass, name, phoneNumber, age)
            result.onSuccess {
                runOnUiThread {
                    Toast.makeText(this@SignupActivity, "Signup Successful! Please log in.", Toast.LENGTH_LONG).show()
                    // NAVIGATE BACK TO LOGIN
                    startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
                    finish()
                }
            }.onFailure { e ->
                runOnUiThread { Toast.makeText(this@SignupActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show() }
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
            if (today.get(Calendar.DAY_OF_YEAR) < birth.get(Calendar.DAY_OF_YEAR)) age--
            age
        } catch (e: Exception) { 0 }
    }
    
    /**
     * Validates phone number: 10-15 digits, optional + prefix for country code
     */
    private fun isValidPhone(phone: String): Boolean {
        val cleanPhone = phone.replace(Regex("[\\s\\-()]"), "") // Remove spaces, dashes, parentheses
        return when {
            cleanPhone.isEmpty() -> false
            cleanPhone.startsWith("+") -> cleanPhone.length in 11..16 && cleanPhone.drop(1).all { it.isDigit() }
            else -> cleanPhone.length in 10..15 && cleanPhone.all { it.isDigit() }
        }
    }
    
    /**
     * Returns null if password is strong, or an error message if weak
     */
    private fun getPasswordError(password: String): String? {
        if (password.length < 8) return "Password must be at least 8 characters"
        if (!password.any { it.isUpperCase() }) return "Password must contain an uppercase letter"
        if (!password.any { it.isLowerCase() }) return "Password must contain a lowercase letter"
        if (!password.any { it.isDigit() }) return "Password must contain a number"
        if (!password.any { !it.isLetterOrDigit() }) return "Password must contain a special character (!@#\$%)"
        return null
    }
}