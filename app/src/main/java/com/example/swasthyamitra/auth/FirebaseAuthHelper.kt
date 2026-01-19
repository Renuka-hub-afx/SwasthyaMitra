package com.example.swasthyamitra.auth

import android.content.Context
import com.example.swasthyamitra.models.ExerciseLog
import com.example.swasthyamitra.models.FestivalEntry
import com.example.swasthyamitra.models.FoodLog
import com.example.swasthyamitra.models.MealHistory
import com.example.swasthyamitra.models.UserGoalData
import com.example.swasthyamitra.models.UserProfileData
import com.example.swasthyamitra.models.WeightLog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
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
                    "bmi" to 0.0,
                    "bmr" to 0.0,
                    "tdee" to 0.0,
                    "activityLevel" to "",
                    "preference" to "",
                    "allergies" to emptyList<String>(),
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
    suspend fun updateGoalWithCalories(
        userId: String,
        activityLevel: String,
        dietPreference: String,
        targetWeight: Double,
        dailyCalories: Double,
        bmr: Double,
        tdee: Double
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
                    "dailyCalories" to dailyCalories,
                    "bmr" to bmr,
                    "tdee" to tdee,
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
    
    // Get total calories for a specific date
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

    // ==================== EXERCISE LOGS ====================

    suspend fun logExercise(exerciseLog: ExerciseLog): Result<String> {
        val timestamp = if (exerciseLog.timestamp == 0L) System.currentTimeMillis() else exerciseLog.timestamp
        val dateFormat = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
        val dateValue = if (exerciseLog.date.isBlank()) dateFormat.format(java.util.Date(timestamp)) else exerciseLog.date

        return try {
            val data = hashMapOf(
                "userId" to exerciseLog.userId,
                "exerciseType" to exerciseLog.exerciseType,
                "durationMinutes" to exerciseLog.durationMinutes,
                "intensity" to exerciseLog.intensity,
                "caloriesBurned" to exerciseLog.caloriesBurned,
                "timestamp" to timestamp,
                "date" to dateValue
            )

            val docRef = firestore.collection("exerciseLogs")
                .add(data)
                .await()

            Result.success(docRef.id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getRecentExerciseLogs(userId: String, limit: Int = 30): Result<List<ExerciseLog>> {
        return try {
            val snapshot = firestore.collection("exerciseLogs")
                .whereEqualTo("userId", userId)
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .limit(limit.toLong())
                .get()
                .await()

            val logs = snapshot.documents.map { doc ->
                ExerciseLog(
                    logId = doc.id,
                    userId = doc.getString("userId") ?: "",
                    exerciseType = doc.getString("exerciseType") ?: "",
                    durationMinutes = (doc.getLong("durationMinutes") ?: 0L).toInt(),
                    intensity = doc.getString("intensity") ?: "",
                    caloriesBurned = (doc.getLong("caloriesBurned") ?: 0L).toInt(),
                    timestamp = doc.getLong("timestamp") ?: 0L,
                    date = doc.getString("date") ?: ""
                )
            }

            Result.success(logs)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // ==================== WEIGHT LOGS ====================

    suspend fun logWeight(weightLog: WeightLog): Result<String> {
        val timestamp = if (weightLog.timestamp == 0L) System.currentTimeMillis() else weightLog.timestamp
        val dateFormat = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
        val dateValue = if (weightLog.date.isBlank()) dateFormat.format(java.util.Date(timestamp)) else weightLog.date

        return try {
            val data = hashMapOf(
                "userId" to weightLog.userId,
                "weight" to weightLog.weight,
                "timestamp" to timestamp,
                "date" to dateValue
            )

            val docRef = firestore.collection("weightLogs")
                .add(data)
                .await()

            Result.success(docRef.id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getWeightHistory(userId: String, limit: Int = 30): Result<List<WeightLog>> {
        return try {
            val snapshot = firestore.collection("weightLogs")
                .whereEqualTo("userId", userId)
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .limit(limit.toLong())
                .get()
                .await()

            val logs = snapshot.documents.map { doc ->
                WeightLog(
                    logId = doc.id,
                    userId = doc.getString("userId") ?: "",
                    weight = doc.getDouble("weight") ?: 0.0,
                    timestamp = doc.getLong("timestamp") ?: 0L,
                    date = doc.getString("date") ?: ""
                )
            }

            Result.success(logs)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getLatestWeight(userId: String): Result<WeightLog?> {
        return try {
            val snapshot = firestore.collection("weightLogs")
                .whereEqualTo("userId", userId)
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .limit(1)
                .get()
                .await()

            if (!snapshot.isEmpty) {
                val doc = snapshot.documents[0]
                Result.success(
                    WeightLog(
                        logId = doc.id,
                        userId = doc.getString("userId") ?: "",
                        weight = doc.getDouble("weight") ?: 0.0,
                        timestamp = doc.getLong("timestamp") ?: 0L,
                        date = doc.getString("date") ?: ""
                    )
                )
            } else {
                Result.success(null)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // ==================== MEAL HISTORY ====================

    suspend fun saveMealHistory(mealHistory: MealHistory): Result<String> {
        val timestamp = if (mealHistory.timestamp == 0L) System.currentTimeMillis() else mealHistory.timestamp
        val docId = "${mealHistory.userId}_${mealHistory.date}"

        return try {
            val data = hashMapOf(
                "userId" to mealHistory.userId,
                "date" to mealHistory.date,
                "breakfast" to mealHistory.breakfast,
                "lunch" to mealHistory.lunch,
                "snack" to mealHistory.snack,
                "dinner" to mealHistory.dinner,
                "postWorkout" to mealHistory.postWorkout,
                "hydrationTips" to mealHistory.hydrationTips,
                "festivalSpecial" to mealHistory.festivalSpecial,
                "totalCalories" to mealHistory.totalCalories,
                "protein" to mealHistory.protein,
                "carbs" to mealHistory.carbs,
                "fats" to mealHistory.fats,
                "timestamp" to timestamp
            )

            firestore.collection("mealHistory")
                .document(docId)
                .set(data)
                .await()

            Result.success(docId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getRecentMealHistory(userId: String, days: Int = 5): Result<List<MealHistory>> {
        return try {
            val snapshot = firestore.collection("mealHistory")
                .whereEqualTo("userId", userId)
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .limit(days.toLong())
                .get()
                .await()

            val meals = snapshot.documents.map { doc ->
                MealHistory(
                    userId = doc.getString("userId") ?: "",
                    date = doc.getString("date") ?: "",
                    breakfast = doc.get("breakfast") as? List<String> ?: emptyList(),
                    lunch = doc.get("lunch") as? List<String> ?: emptyList(),
                    snack = doc.get("snack") as? List<String> ?: emptyList(),
                    dinner = doc.get("dinner") as? List<String> ?: emptyList(),
                    postWorkout = doc.get("postWorkout") as? List<String> ?: emptyList(),
                    hydrationTips = doc.get("hydrationTips") as? List<String> ?: emptyList(),
                    festivalSpecial = doc.getString("festivalSpecial"),
                    totalCalories = (doc.getLong("totalCalories") ?: 0L).toInt(),
                    protein = doc.getDouble("protein") ?: 0.0,
                    carbs = doc.getDouble("carbs") ?: 0.0,
                    fats = doc.getDouble("fats") ?: 0.0,
                    timestamp = doc.getLong("timestamp") ?: 0L
                )
            }

            Result.success(meals)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // ==================== FESTIVAL CALENDAR ====================

    suspend fun upsertFestivalEntry(entry: FestivalEntry): Result<Unit> {
        return try {
            val data = hashMapOf(
                "date" to entry.date,
                "name" to entry.name,
                "region" to entry.region
            )

            firestore.collection("festivalCalendar")
                .document(entry.date)
                .set(data)
                .await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getFestivalForDate(date: String): Result<FestivalEntry?> {
        return try {
            val doc = firestore.collection("festivalCalendar")
                .document(date)
                .get()
                .await()

            if (doc.exists()) {
                Result.success(
                    FestivalEntry(
                        date = doc.getString("date") ?: date,
                        name = doc.getString("name") ?: "",
                        region = doc.getString("region")
                    )
                )
            } else {
                Result.success(null)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // ==================== USER & GOAL MODELS ====================

    suspend fun getUserProfileData(userId: String): Result<UserProfileData> {
        return try {
            val document = firestore.collection("users")
                .document(userId)
                .get()
                .await()

            if (!document.exists()) return Result.failure(Exception("User not found"))

            val data = UserProfileData(
                userId = userId,
                name = document.getString("name") ?: "",
                email = document.getString("email") ?: "",
                phoneNumber = document.getString("phoneNumber") ?: "",
                age = (document.getLong("age") ?: 0L).toInt(),
                height = document.getDouble("height") ?: 0.0,
                weight = document.getDouble("weight") ?: 0.0,
                gender = document.getString("gender") ?: "",
                bmi = document.getDouble("bmi") ?: 0.0,
                bmr = document.getDouble("bmr") ?: 0.0,
                tdee = document.getDouble("tdee") ?: 0.0,
                activityLevel = document.getString("activityLevel") ?: "",
                preference = document.getString("preference") ?: document.getString("eatingPreference") ?: "",
                allergies = document.get("allergies") as? List<String> ?: emptyList()
            )

            Result.success(data)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateUserMetrics(
        userId: String,
        bmi: Double,
        bmr: Double,
        tdee: Double,
        activityLevel: String,
        preference: String,
        allergies: List<String> = emptyList()
    ): Result<Unit> {
        return try {
            val updates = hashMapOf<String, Any>(
                "bmi" to bmi,
                "bmr" to bmr,
                "tdee" to tdee,
                "activityLevel" to activityLevel,
                "preference" to preference,
                "allergies" to allergies,
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

    suspend fun getUserGoalData(userId: String): Result<UserGoalData> {
        return try {
            val snapshot = firestore.collection("goals")
                .whereEqualTo("userId", userId)
                .limit(1)
                .get()
                .await()

            if (snapshot.isEmpty) return Result.failure(Exception("No goal found"))

            val doc = snapshot.documents[0]
            val goalData = UserGoalData(
                documentId = doc.id,
                userId = doc.getString("userId") ?: userId,
                goalType = doc.getString("goalType") ?: "",
                targetValue = doc.getDouble("targetValue") ?: 0.0,
                currentValue = doc.getDouble("currentValue") ?: 0.0,
                targetWeight = doc.getDouble("targetWeight") ?: 0.0,
                targetCalories = doc.getDouble("targetCalories") ?: 0.0,
                dailyCalories = doc.getDouble("dailyCalories") ?: 0.0,
                activityLevel = doc.getString("activityLevel") ?: "",
                dietPreference = doc.getString("dietPreference") ?: "",
                bmr = doc.getDouble("bmr") ?: 0.0,
                tdee = doc.getDouble("tdee") ?: 0.0,
                createdAt = doc.getLong("createdAt") ?: 0L,
                updatedAt = doc.getLong("updatedAt") ?: 0L
            )

            Result.success(goalData)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
