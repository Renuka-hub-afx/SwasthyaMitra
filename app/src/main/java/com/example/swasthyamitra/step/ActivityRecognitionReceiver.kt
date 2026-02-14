package com.example.swasthyamitra.step

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.location.ActivityRecognitionResult
import com.google.android.gms.location.DetectedActivity

/**
 * Broadcast receiver for Google Activity Recognition API updates
 */
class ActivityRecognitionReceiver : BroadcastReceiver() {

    companion object {
        private const val TAG = "ActivityRecognition"
        var latestActivity: DetectedActivity? = null
        var onActivityChanged: ((DetectedActivity) -> Unit)? = null
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (ActivityRecognitionResult.hasResult(intent)) {
            val result = ActivityRecognitionResult.extractResult(intent!!)
            val mostProbableActivity = result?.mostProbableActivity

            mostProbableActivity?.let { activity ->
                latestActivity = activity
                onActivityChanged?.invoke(activity)

                Log.d(TAG, "Activity detected: ${getActivityName(activity.type)} " +
                          "(confidence: ${activity.confidence}%)")
            }
        }
    }

    private fun getActivityName(activityType: Int): String {
        return when (activityType) {
            DetectedActivity.WALKING -> "WALKING"
            DetectedActivity.RUNNING -> "RUNNING"
            DetectedActivity.ON_FOOT -> "ON_FOOT"
            DetectedActivity.STILL -> "STILL"
            DetectedActivity.IN_VEHICLE -> "IN_VEHICLE"
            DetectedActivity.ON_BICYCLE -> "ON_BICYCLE"
            DetectedActivity.TILTING -> "TILTING"
            DetectedActivity.UNKNOWN -> "UNKNOWN"
            else -> "OTHER ($activityType)"
        }
    }
}

