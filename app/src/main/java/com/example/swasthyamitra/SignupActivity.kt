package com.example.swasthyamitra

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.swasthyamitra.databinding.ActivitySignupBinding
import com.example.swasthyamitra.data.User
import com.example.swasthyamitra.UserViewModel
import com.example.swasthyamitra.UserViewModel.UserViewModelFactory
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.jvm.java
import kotlin.text.isEmpty
import kotlin.text.trim
import kotlin.toString

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup ViewModel
        val application = application as UserApplication
        val factory = UserViewModelFactory(application.repository)
        userViewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)

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

        val user = User(
            userName = name,
            email = email,
            password = pass,
            birthDate = dob
        )

        userViewModel.insertUser(user) { id ->
            runOnUiThread {
                Toast.makeText(this, "Signup Successful! Please log in.", Toast.LENGTH_LONG).show()

                // CORRECTED FLOW: Navigate back to LoginActivity
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
