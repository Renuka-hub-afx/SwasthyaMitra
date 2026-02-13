package com.example.swasthyamitra.safety

import android.content.Context
import android.os.BatteryManager
import android.telephony.SmsManager
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class SOSManager(private val context: Context) {
    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    fun sendSOS(contact: EmergencyContact, latitude: Double, longitude: Double, reason: String) {
        val batteryPercentage = getBatteryPercentage()
        val timestamp = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
        val locationUrl = "https://maps.google.com/maps?q=$latitude,$longitude"
        
        val message = "EMERGENCY ALERT: $reason\n" +
                "Location: $locationUrl\n" +
                "Time: $timestamp\n" +
                "Battery: $batteryPercentage%\n" +
                "User may be unsafe."

        sendSMS(contact.phoneNumber, message)
        logEmergencyToFirebase(latitude, longitude, reason, batteryPercentage)
    }

    private fun sendSMS(phoneNumber: String, message: String) {
        try {
            val smsManager = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                context.getSystemService(SmsManager::class.java)
            } else {
                SmsManager.getDefault()
            }
            
            // For longer messages, use divideMessage and sendMultipartTextMessage
            val parts = smsManager.divideMessage(message)
            smsManager.sendMultipartTextMessage(phoneNumber, null, parts, null, null)
            
            Log.d("SOSManager", "SOS SMS sent to $phoneNumber")
        } catch (e: Exception) {
            Log.e("SOSManager", "Failed to send SOS SMS", e)
        }
    }

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
                Log.e("SOSManager", "Failed to log emergency to Firebase", e)
            }
    }

    private fun getBatteryPercentage(): Int {
        val bm = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
        return bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
    }
}
