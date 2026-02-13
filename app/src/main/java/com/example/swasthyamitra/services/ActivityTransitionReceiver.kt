package com.example.swasthyamitra.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.google.android.gms.location.ActivityTransitionResult
import com.google.android.gms.location.DetectedActivity

class ActivityTransitionReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (ActivityTransitionResult.hasResult(intent)) {
            val result = ActivityTransitionResult.extractResult(intent!!)
            for (event in result!!.transitionEvents) {
                // We only care about the type
                val type = event.activityType
                
                // Communicate to service
                val serviceIntent = Intent(context, TrackingService::class.java).apply {
                    action = "ACTION_ACTIVITY_TRANSITION"
                    putExtra("activity_type", type)
                }
                context?.startService(serviceIntent)
            }
        }
    }
}
