package com.example.swasthyamitra.models

data class UserProfileData(
    val userId: String = "",
    val name: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val age: Int = 0,
    val height: Double = 0.0,
    val weight: Double = 0.0,
    val gender: String = "",
    val bmi: Double = 0.0,
    val bmr: Double = 0.0,
    val tdee: Double = 0.0,
    val activityLevel: String = "",
    val preference: String = "",
    val allergies: List<String> = emptyList()
)
