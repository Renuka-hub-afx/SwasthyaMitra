package com.example.swasthyamitra

data class ShieldInstance(
    val id: String = "",
    val type: String = "STANDARD", // STANDARD, PREMIUM, etc.
    val activatedDate: String = "",
    val expiryDate: String = "",
    val isActive: Boolean = true
) : java.io.Serializable
