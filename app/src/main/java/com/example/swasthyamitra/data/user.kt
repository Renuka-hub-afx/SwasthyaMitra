package com.example.swasthyamitra.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val userName: String,
    val email: String,
    val password: String,
    val birthDate: String, // Stored as "dd MMM yyyy"

    // Physical Stats (Updated in Screen 2)
    val gender: String = "",
    val height: Float = 0f,
    val weight: Float = 0f,
    val bmi: Float = 0f     // <--- ADDED BMI HERE
) {
    // --- AUTOMATIC AGE CALCULATION ---
    // This variable 'age' is calculated instantly whenever you use 'user.age'.
    // It is NOT stored in the database, so it never gets outdated.
    val age: Int
        get() {
            return try {
                val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                val dobDate = sdf.parse(birthDate) ?: return 0

                val dobCal = Calendar.getInstance()
                dobCal.time = dobDate

                val today = Calendar.getInstance()

                var calculatedAge = today.get(Calendar.YEAR) - dobCal.get(Calendar.YEAR)

                // If birthday hasn't happened yet this year, subtract 1
                if (today.get(Calendar.DAY_OF_YEAR) < dobCal.get(Calendar.DAY_OF_YEAR)) {
                    calculatedAge--
                }
                calculatedAge
            } catch (e: Exception) {
                0 // Return 0 if date is invalid
            }
        }
}