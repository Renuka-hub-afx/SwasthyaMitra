package com.example.swasthyamitra.auth

import android.content.Context
import com.example.swasthyamitra.models.FoodLog
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

    // Update goal with calculated BMR, TDEE, and daily calories


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
    
    // ==================== FOOD LOGGING METHODS ====================
    
    // Log food entry
    suspend fun logFood(foodLog: FoodLog): Result<String> {
        return try {
            val foodData = hashMapOf(
                "userId" to foodLog.userId,
                "foodName" to foodLog.foodName,
                "barcode" to foodLog.barcode,
                "photoUrl" to foodLog.photoUrl,
                "calories" to foodLog.calories,
                "protein" to foodLog.protein,
                "carbs" to foodLog.carbs,
                "fat" to foodLog.fat,
                "servingSize" to foodLog.servingSize,
                "mealType" to foodLog.mealType,
                "date" to foodLog.date,
                "timestamp" to foodLog.timestamp
            )
            
            val docRef = firestore.collection("foodLogs")
                .add(foodData)
                .await()
            
            Result.success(docRef.id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    // Get today's food logs
    suspend fun getTodayFoodLogs(userId: String): Result<List<FoodLog>> {
        return try {
            val dateFormat = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
            val today = dateFormat.format(java.util.Date())
            
            val querySnapshot = firestore.collection("foodLogs")
                .whereEqualTo("userId", userId)
                .whereEqualTo("date", today)
                .get()
                .await()
            
            val logs = querySnapshot.documents.mapNotNull { doc ->
                FoodLog(
                    logId = doc.id,
                    userId = doc.getString("userId") ?: "",
                    foodName = doc.getString("foodName") ?: "",
                    barcode = doc.getString("barcode"),
                    photoUrl = doc.getString("photoUrl"),
                    calories = (doc.getLong("calories") ?: 0).toInt(),
                    protein = doc.getDouble("protein") ?: 0.0,
                    carbs = doc.getDouble("carbs") ?: 0.0,
                    fat = doc.getDouble("fat") ?: 0.0,
                    servingSize = doc.getString("servingSize") ?: "",
                    mealType = doc.getString("mealType") ?: "",
                    timestamp = doc.getLong("timestamp") ?: 0L,
                    date = doc.getString("date") ?: ""
                )
            }
            
            Result.success(logs)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    // Get total calories for today
    suspend fun getTodayCalories(userId: String): Result<Int> {
        return try {
            val logsResult = getTodayFoodLogs(userId)
            logsResult.onSuccess { logs ->
                val totalCalories = logs.sumOf { it.calories }
                return Result.success(totalCalories)
            }
            Result.failure(Exception("Failed to calculate calories"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    // Get food logs for a list of dates
    suspend fun getFoodLogsForDates(userId: String, dates: List<String>): Result<List<FoodLog>> {
        return try {
            val querySnapshot = firestore.collection("foodLogs")
                .whereEqualTo("userId", userId)
                .whereIn("date", dates)
                .get()
                .await()
            
            val logs = querySnapshot.documents.mapNotNull { doc ->
                FoodLog(
                    logId = doc.id,
                    userId = doc.getString("userId") ?: "",
                    foodName = doc.getString("foodName") ?: "",
                    barcode = doc.getString("barcode"),
                    photoUrl = doc.getString("photoUrl"),
                    calories = (doc.getLong("calories") ?: 0).toInt(),
                    protein = doc.getDouble("protein") ?: 0.0,
                    carbs = doc.getDouble("carbs") ?: 0.0,
                    fat = doc.getDouble("fat") ?: 0.0,
                    servingSize = doc.getString("servingSize") ?: "",
                    mealType = doc.getString("mealType") ?: "",
                    timestamp = doc.getLong("timestamp") ?: 0L,
                    date = doc.getString("date") ?: ""
                )
            }
            
            Result.success(logs)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Get total calories for a specific date (Legacy - used by ProgressActivity)
    suspend fun getDailyCalories(userId: String, date: String): Int {
        return try {
            val logs = firestore.collection("foodLogs")
                .whereEqualTo("userId", userId)
                .whereEqualTo("date", date)
                .get()
                .await()
            
            logs.documents.sumOf { doc ->
                (doc.getLong("calories") ?: 0).toInt()
            }
        } catch (e: Exception) {
            0
        }
    }

    // ==================== METRIC LOGGING METHODS ====================

    // Get recent exercise logs for intensity detection
    suspend fun getRecentExerciseLogs(userId: String, days: Int = 3): List<Map<String, Any>> {
        return try {
            val cutoff = System.currentTimeMillis() - (days * 24 * 60 * 60 * 1000L)
            val querySnapshot = firestore.collection("exerciseLogs")
                .whereEqualTo("userId", userId)
                .whereGreaterThanOrEqualTo("timestamp", cutoff)
                .get()
                .await()
            
            querySnapshot.documents.map { it.data ?: emptyMap() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    // Get recent weight logs for plateau detection
    suspend fun getRecentWeightLogs(userId: String, days: Int = 14): List<Map<String, Any>> {
        return try {
            val cutoff = System.currentTimeMillis() - (days * 24 * 60 * 60 * 1000L)
            val querySnapshot = firestore.collection("weightLogs")
                .whereEqualTo("userId", userId)
                .whereGreaterThanOrEqualTo("timestamp", cutoff)
                .get()
                .await()
            
            querySnapshot.documents.map { it.data ?: emptyMap() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    /**
     * Get food logs for the last X days
     */
    suspend fun getRecentFoodLogs(userId: String, days: Int = 3): List<FoodLog> {
        return try {
            val cutoff = System.currentTimeMillis() - (days * 24 * 60 * 60 * 1000L)
            val querySnapshot = firestore.collection("foodLogs")
                .whereEqualTo("userId", userId)
                .whereGreaterThanOrEqualTo("timestamp", cutoff)
                .get()
                .await()
            
            querySnapshot.documents.mapNotNull { doc ->
                FoodLog(
                    logId = doc.id,
                    userId = doc.getString("userId") ?: "",
                    foodName = doc.getString("foodName") ?: "",
                    barcode = doc.getString("barcode"),
                    photoUrl = doc.getString("photoUrl"),
                    calories = (doc.getLong("calories") ?: 0).toInt(),
                    protein = doc.getDouble("protein") ?: 0.0,
                    carbs = doc.getDouble("carbs") ?: 0.0,
                    fat = doc.getDouble("fat") ?: 0.0,
                    servingSize = doc.getString("servingSize") ?: "",
                    mealType = doc.getString("mealType") ?: "",
                    timestamp = doc.getLong("timestamp") ?: 0L,
                    date = doc.getString("date") ?: ""
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    /**
     * Update goal with calculated calories and lifestyle data
     */
    suspend fun updateGoalWithCalories(
        userId: String,
        activityLevel: String,
        dietPreference: String,
        targetWeight: Double,
        dailyCalories: Double,
        bmr: Double,
        tdee: Double,
        wakeTime: String? = null,
        sleepTime: String? = null
    ): Result<Unit> {
        return try {
            val updates = hashMapOf<String, Any>(
                "activityLevel" to activityLevel,
                "dietPreference" to dietPreference,
                "targetWeight" to targetWeight,
                "dailyCalories" to dailyCalories,
                "bmr" to bmr,
                "tdee" to tdee,
                "updatedAt" to System.currentTimeMillis()
            )

            // Update user collection with lifestyle data
            val userUpdates = hashMapOf<String, Any>(
                "activityLevel" to activityLevel,
                "targetWeight" to targetWeight
            )
            
            // Add sleep/wake times if provided
            if (wakeTime != null) userUpdates["wakeTime"] = wakeTime
            if (sleepTime != null) userUpdates["sleepTime"] = sleepTime
            
            firestore.collection("users").document(userId).update(userUpdates).await()
            
            // Find goal doc to update
             val querySnapshot = firestore.collection("goals")
                .whereEqualTo("userId", userId)
                .limit(1)
                .get()
                .await()
            
            if (!querySnapshot.isEmpty) {
                val goalDocId = querySnapshot.documents[0].id
                firestore.collection("goals").document(goalDocId).update(updates).await()
            } else {
                // If no goal exists, create one? For now just return success as user update worked
            }
            
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
