package com.example.swasthyamitra

enum class ShieldType {
    FREEZE, REPAIR, GUARDIAN
}

data class ShieldInstance(
    val id: String = "",
    val type: ShieldType = ShieldType.FREEZE,
    val acquiredDate: String = "",
    val expiresAt: Long = 0L
) : java.io.Serializable
