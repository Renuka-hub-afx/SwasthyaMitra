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
        if (phoneNumber.isEmpty() || phoneNumber.length < 10) { binding.phoneInput.error = "Enter valid phone (with country code)"; return }
        if (dob.isEmpty()) { Toast.makeText(this, "Select birth date", Toast.LENGTH_SHORT).show(); return }
        if (pass.length < 6) { binding.passwordInput.error = "Min 6 chars"; return }
        if (pass != confirm) { binding.confirmPasswordInput.error = "Mismatch"; return }

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
}