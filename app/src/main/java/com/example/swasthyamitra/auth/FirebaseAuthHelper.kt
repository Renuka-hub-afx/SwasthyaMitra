package com.example.swasthyamitra.auth

import android.app.Activity
import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirebaseAuthHelper(private val context: Context) {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var googleSignInClient: GoogleSignInClient? = null

    // Initialize Google Sign-In
    fun initializeGoogleSignIn(webClientId: String) {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(webClientId)
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(context, gso)
    }

    // Get Google Sign-In Client
    fun getGoogleSignInClient(): GoogleSignInClient? = googleSignInClient

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

    // Sign in with Google
    suspend fun signInWithGoogle(account: GoogleSignInAccount): Result<FirebaseUser> {
        return try {
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            val result = auth.signInWithCredential(credential).await()
            val user = result.user
            
            if (user != null) {
                // Check if user data exists in Firestore
                val userDoc = firestore.collection("users").document(user.uid).get().await()
                
                if (!userDoc.exists()) {
                    // Create user data if it doesn't exist
                    val userData = hashMapOf(
                        "userId" to user.uid,
                        "name" to (user.displayName ?: ""),
                        "email" to (user.email ?: ""),
                        "phoneNumber" to (user.phoneNumber ?: ""),
                        "age" to 0,
                        "height" to 0.0,
                        "weight" to 0.0,
                        "gender" to "",
                        "createdAt" to System.currentTimeMillis()
                    )
                    
                    firestore.collection("users")
                        .document(user.uid)
                        .set(userData)
                        .await()
                }
                
                Result.success(user)
            } else {
                Result.failure(Exception("Google sign in failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Sign in anonymously
    suspend fun signInAnonymously(): Result<FirebaseUser> {
        return try {
            val result = auth.signInAnonymously().await()
            val user = result.user
            
            if (user != null) {
                // Create anonymous user data
                val userData = hashMapOf(
                    "userId" to user.uid,
                    "name" to "Guest User",
                    "email" to "",
                    "phoneNumber" to "",
                    "age" to 0,
                    "height" to 0.0,
                    "weight" to 0.0,
                    "gender" to "",
                    "isAnonymous" to true,
                    "createdAt" to System.currentTimeMillis()
                )
                
                firestore.collection("users")
                    .document(user.uid)
                    .set(userData)
                    .await()
                
                Result.success(user)
            } else {
                Result.failure(Exception("Anonymous sign in failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Sign out
    fun signOut() {
        auth.signOut()
        googleSignInClient?.signOut()
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
}
