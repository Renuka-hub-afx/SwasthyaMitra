package com.example.swasthyamitra.repository

import android.util.Log
import com.example.swasthyamitra.models.MoodData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

class MoodRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val TAG = "MoodRepository"

    suspend fun saveMood(userId: String, moodData: MoodData): Result<Boolean> {
        return try {
            firestore.collection("users").document(userId)
                .collection("mood_logs")
                .add(moodData)
                .await()
            
            // Also update the latest mood in user profile for quick access
            firestore.collection("users").document(userId)
                .update("lastMood", moodData.mood, 
                        "lastMoodDate", moodData.date,
                        "lastMoodIntensity", moodData.intensity)
                .await()
                
            Result.success(true)
        } catch (e: Exception) {
            Log.e(TAG, "Error saving mood", e)
            Result.failure(e)
        }
    }

    suspend fun getRecentMoods(userId: String, limit: Int = 7): Result<List<MoodData>> {
        return try {
            val snapshot = firestore.collection("users").document(userId)
                .collection("mood_logs")
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .limit(limit.toLong())
                .get()
                .await()

            val moods = snapshot.documents.mapNotNull { doc ->
                try {
                    MoodData(
                        userId = doc.getString("userId") ?: "",
                        mood = doc.getString("mood") ?: "",
                        // Firestore stores numbers as Double or Long. Safely cast.
                        intensity = (doc.getDouble("intensity") ?: 0.5).toFloat(),
                        energy = (doc.getDouble("energy") ?: 0.5).toFloat(),
                        suggestion = doc.getString("suggestion") ?: "",
                        timestamp = doc.getLong("timestamp") ?: 0L,
                        date = doc.getString("date") ?: ""
                    )
                } catch (e: Exception) {
                    Log.e(TAG, "Error parsing mood doc: ${doc.id}", e)
                    null
                }
            }
            Result.success(moods)
        } catch (e: Exception) {
            Log.e(TAG, "Error fetching moods", e)
            Result.failure(e)
        }
    }
}
