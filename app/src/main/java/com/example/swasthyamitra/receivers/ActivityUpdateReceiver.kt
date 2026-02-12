package com.example.swasthyamitra.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.swasthyamitra.services.StepCounterService
import com.google.android.gms.location.ActivityRecognitionResult
import com.google.android.gms.location.DetectedActivity

class ActivityUpdateReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (ActivityRecognitionResult.hasResult(intent)) {
            val result = ActivityRecognitionResult.extractResult(intent)
            result?.let {
                val mostProbableActivity = it.mostProbableActivity
                
                Log.d("ActivityUpdateReceiver", "Detected activity: ${getActivityString(mostProbableActivity.type)}, Confidence: ${mostProbableActivity.confidence}")
                
                // Forward to Service
                val serviceIntent = Intent(StepCounterService.ACTION_ACTIVITY_UPDATE)
                serviceIntent.putExtra("activity_type", mostProbableActivity.type)
                serviceIntent.putExtra("confidence", mostProbableActivity.confidence)
                context.sendBroadcast(serviceIntent)
            }
        }
    }

    private fun getActivityString(type: Int): String {
        return when (type) {
            DetectedActivity.IN_VEHICLE -> "In Vehicle"
            DetectedActivity.ON_BICYCLE -> "On Bicycle"
            DetectedActivity.ON_FOOT -> "On Foot"
            DetectedActivity.RUNNING -> "Running"
            DetectedActivity.STILL -> "Still"
            DetectedActivity.TILTING -> "Tilting"
            DetectedActivity.UNKNOWN -> "Unknown"
            DetectedActivity.WALKING -> "Walking"
            else -> "Unknown ($type)"
        }
    }
}
