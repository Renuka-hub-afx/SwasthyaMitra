package com.example.swasthyamitra.safety

import android.content.Context
import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

data class EmergencyContact(
    val name: String = "",
    val phoneNumber: String = "",
    val photoUri: String? = null
)

class EmergencyContactManager(private val context: Context) {
    private val prefs = context.getSharedPreferences("SafetyPrefs", Context.MODE_PRIVATE)
    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    fun saveContactLocally(contact: EmergencyContact) {
        prefs.edit().apply {
            putString("contact_name", contact.name)
            putString("contact_number", contact.phoneNumber)
            putString("contact_photo", contact.photoUri)
            apply()
        }
    }

    fun getLocalContact(): EmergencyContact? {
        val name = prefs.getString("contact_name", null)
        val number = prefs.getString("contact_number", null)
        val photo = prefs.getString("contact_photo", null)
        
        return if (name != null && number != null) {
            EmergencyContact(name, number, photo)
        } else null
    }

    suspend fun syncContactWithFirebase(contact: EmergencyContact) {
        val userId = auth.currentUser?.uid ?: return
        try {
            firestore.collection("users")
                .document(userId)
                .update("emergencyContact", contact)
                .await()
        } catch (e: Exception) {
            // Document might not have the field yet, try set with merge
            firestore.collection("users")
                .document(userId)
                .set(mapOf("emergencyContact" to contact), com.google.firebase.firestore.SetOptions.merge())
                .await()
        }
    }

    suspend fun getFirebaseContact(): EmergencyContact? {
        val userId = auth.currentUser?.uid ?: return null
        return try {
            val doc = firestore.collection("users").document(userId).get().await()
            val contactMap = doc.get("emergencyContact") as? Map<String, Any>
            if (contactMap != null) {
                EmergencyContact(
                    name = contactMap["name"] as? String ?: "",
                    phoneNumber = contactMap["phoneNumber"] as? String ?: "",
                    photoUri = contactMap["photoUri"] as? String
                )
            } else null
        } catch (e: Exception) {
            null
        }
    }
}
