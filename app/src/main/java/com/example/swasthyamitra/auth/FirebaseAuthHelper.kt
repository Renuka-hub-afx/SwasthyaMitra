package com.example.swasthyamitra.auth

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirebaseAuthHelper(private val context: Context) {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    // Get current user
    fun getCurrentUser(): FirebaseUser? = auth.currentUser

    // Check if user is logged in
    fun isUserLoggedIn(): Boolean = auth.currentUser != null

    // Sign up with email and password
    suspend fun signUpWithEmail(
        email: String,
        password: String,
        name: String,
        phoneNumber: String,
        age: Int
    ): Result<FirebaseUser> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            val user = result.user
            
            if (user != null) {
                // Store user data in Firestore
                val userData = hashMapOf(
                    "userId" to user.uid,
                    "name" to name,
                    "email" to email,
                    "phoneNumber" to phoneNumber,
                    "age" to age,
                    "height" to 0.0,
                    "weight" to 0.0,
                    "gender" to "",
                    "createdAt" to System.currentTimeMillis()
                )
                
                firestore.collection("users")
                    .document(user.uid)
                    .set(userData)
                    .await()
                
                Result.success(user)
            } else {
                Result.failure(Exception("User creation failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Sign in with email and password
    suspend fun signInWithEmail(email: String, password: String): Result<FirebaseUser> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            val user = result.user
            
            if (user != null) {
                Result.success(user)
            } else {
                Result.failure(Exception("Sign in failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Sign out
    fun signOut() {
        auth.signOut()
    }

    // Update user physical stats
    suspend fun updateUserPhysicalStats(
        userId: String,
        height: Double,
        weight: Double,
        gender: String,
        age: Int
    ): Result<Unit> {
        return try {
            val updates = hashMapOf<String, Any>(
                "height" to height,
                "weight" to weight,
                "gender" to gender,
                "age" to age,
                "updatedAt" to System.currentTimeMillis()
            )
            
            firestore.collection("users")
                .document(userId)
                .update(updates)
                .await()
            
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Update eating preference
    suspend fun updateEatingPreference(
        userId: String,
        eatingPreference: String
    ): Result<Unit> {
        return try {
            val updates = hashMapOf<String, Any>(
                "eatingPreference" to eatingPreference,
                "updatedAt" to System.currentTimeMillis()
            )
            
            firestore.collection("users")
                .document(userId)
                .update(updates)
                .await()
            
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Insert goal
    suspend fun insertGoal(
        userId: String,
        goalType: String,
        targetValue: Double = 0.0,
        currentValue: Double = 0.0
    ): Result<String> {
        return try {
            val goalData = hashMapOf(
                "userId" to userId,
                "goalType" to goalType,
                "targetValue" to targetValue,
                "currentValue" to currentValue,
                "startDate" to System.currentTimeMillis(),
                "endDate" to 0L,
                "isCompleted" to false,
                "createdAt" to System.currentTimeMillis()
            )
            
            val docRef = firestore.collection("goals")
                .add(goalData)
                .await()
            
            Result.success(docRef.id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Get user data
    suspend fun getUserData(userId: String): Result<Map<String, Any>> {
        return try {
            val document = firestore.collection("users")
                .document(userId)
                .get()
                .await()
            
            if (document.exists()) {
                Result.success(document.data ?: emptyMap())
            } else {
                Result.failure(Exception("User not found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Check if user has a goal
    suspend fun hasUserGoal(userId: String): Result<Boolean> {
        return try {
            val querySnapshot = firestore.collection("goals")
                .whereEqualTo("userId", userId)
                .limit(1)
                .get()
                .await()
            
            Result.success(!querySnapshot.isEmpty)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Get user goal data
    suspend fun getUserGoal(userId: String): Result<Map<String, Any>> {
        return try {
            val querySnapshot = firestore.collection("goals")
                .whereEqualTo("userId", userId)
                .limit(1)
                .get()
                .await()
            
            if (!querySnapshot.isEmpty) {
                Result.success(querySnapshot.documents[0].data ?: emptyMap())
            } else {
                Result.failure(Exception("No goal found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Update lifestyle data (activity level, diet preference, target weight) in goal document
    suspend fun updateGoalLifestyleData(
        userId: String,
        activityLevel: String,
        dietPreference: String,
        targetWeight: Double
    ): Result<Unit> {
        return try {
            // First, find the user's goal document
            val querySnapshot = firestore.collection("goals")
                .whereEqualTo("userId", userId)
                .limit(1)
                .get()
                .await()
            
            if (!querySnapshot.isEmpty) {
                val goalDocId = querySnapshot.documents[0].id
                
                val updates = hashMapOf<String, Any>(
                    "activityLevel" to activityLevel,
                    "dietPreference" to dietPreference,
                    "targetWeight" to targetWeight,
                    "updatedAt" to System.currentTimeMillis()
                )
                
                firestore.collection("goals")
                    .document(goalDocId)
                    .update(updates)
                    .await()
                
                Result.success(Unit)
            } else {
                Result.failure(Exception("No goal found for user"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Check if user has completed lifestyle data in goal document
    suspend fun hasLifestyleData(userId: String): Result<Boolean> {
        return try {
            val querySnapshot = firestore.collection("goals")
                .whereEqualTo("userId", userId)
                .limit(1)
                .get()
                .await()
            
            if (!querySnapshot.isEmpty) {
                val goalDoc = querySnapshot.documents[0]
                val activityLevel = goalDoc.getString("activityLevel")
                val dietPreference = goalDoc.getString("dietPreference")
                val targetWeight = goalDoc.getDouble("targetWeight")
                
                // Check if all lifestyle fields are filled
                val hasData = !activityLevel.isNullOrEmpty() && 
                              !dietPreference.isNullOrEmpty() && 
                              targetWeight != null && targetWeight > 0
                
                Result.success(hasData)
            } else {
                Result.success(false)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
