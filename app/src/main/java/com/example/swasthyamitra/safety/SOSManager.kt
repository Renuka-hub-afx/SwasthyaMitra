package com.example.swasthyamitra.safety

import android.content.Context
import android.os.BatteryManager
import android.telephony.SmsManager
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

/**
 * SOSManager is responsible for the execution of emergency alerts.
 * It handles the construction and delivery of SOS messages via SMS and 
 * ensures every emergency event is logged to the cloud for secondary tracking.
 */
class SOSManager(private val context: Context) {
    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    /**
     * Primary function to trigger an SOS alert.
     * @param contact The emergency contact to notify.
     * @param latitude Current latitude for the Google Maps link.
     * @param longitude Current longitude for the Google Maps link.
     * @param reason The trigger (e.g., "Manual Trigger", "Fall Detected").
     */
    fun sendSOS(contact: EmergencyContact, latitude: Double, longitude: Double, reason: String) {
        val batteryPercentage = getBatteryPercentage()
        val timestamp = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
        
        // Construct a Google Maps link for easy navigation by the recipient
        val locationUrl = "https://maps.google.com/maps?q=$latitude,$longitude"
        
        val message = "EMERGENCY ALERT: $reason\n" +
                "Location: $locationUrl\n" +
                "Time: $timestamp\n" +
                "Battery: $batteryPercentage%\n" +
                "User may be unsafe."

        // 1. Send immediate SMS (Off-grid communication)
        sendSMS(contact.phoneNumber, message)
        
        // 2. Log to Firebase (On-grid persistence and history)
        logEmergencyToFirebase(latitude, longitude, reason, batteryPercentage)
    }

    /**
     * Sends a multipart SMS to handle long messages that exceed the standard 160-character limit.
     */
    private fun sendSMS(phoneNumber: String, message: String) {
        try {
            val smsManager = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                context.getSystemService(SmsManager::class.java)
            } else {
                SmsManager.getDefault()
            }
            
            // Divide the message into chunks if it is too long
            val parts = smsManager.divideMessage(message)
            smsManager.sendMultipartTextMessage(phoneNumber, null, parts, null, null)
            
            Log.d("SOSManager", "SOS SMS successfully dispatched to $phoneNumber")
        } catch (e: Exception) {
            Log.e("SOSManager", "Critical failure sending SOS SMS", e)
        }
    }

    /**
     * Records the emergency event in the 'emergency_events' collection in Firestore.
     */
    private fun logEmergencyToFirebase(lat: Double, lon: Double, reason: String, battery: Int) {
        val userId = auth.currentUser?.uid ?: return
        val event = mapOf(
            "userId" to userId,
            "latitude" to lat,
            "longitude" to lon,
            "reason" to reason,
            "batteryPercentage" to battery,
            "timestamp" to System.currentTimeMillis()
        )

        firestore.collection("emergency_events")
            .add(event)
            .addOnFailureListener { e ->
                Log.e("SOSManager", "Failed to sync emergency log to cloud", e)
            }
    }

    /**
     * Retrieves the current device battery percentage to include in the SOS message.
     * This helps the recipient understand how much time they might have to reach the user.
     */
    private fun getBatteryPercentage(): Int {
        val bm = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
        return bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
    }
}

