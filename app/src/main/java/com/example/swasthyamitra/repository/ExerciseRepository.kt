package com.example.swasthyamitra.repository

import android.content.Context
import android.util.Log
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Repository for loading and searching exercises from all available datasets.
 * Provides search capability for the ExerciseLogActivity search dialog.
 * 
 * Datasets loaded:
 * 1. exercisedb_v1_sample/exercises.json (gym exercises with GIFs)
 * 2. exercise 2/ folders (yoga poses with PNGs)
 * 3. combined_exercises.json (enhanced metadata with gender/age/period tags)
 * 4. exercise3.csv (statistical data — exercise types with avg calories/duration)
 */
class ExerciseRepository(private val context: Context) {

    private var exerciseCache: List<ExerciseItem> = emptyList()
    private var isLoading = false

    companion object {
        private const val TAG = "ExerciseRepository"
    }

    /**
     * Represents a searchable exercise item from any dataset.
     */
    data class ExerciseItem(
        val name: String,
        val targetMuscle: String = "",
        val bodyPart: String = "",
        val equipment: String = "body weight",
        val gifPath: String = "", // Asset path for GIF/PNG
        val estimatedCalories: Int = 100, // Per 15 min session
        val isPeriodSafe: Boolean = false,
        val intensityLevel: String = "moderate",
        val instructions: List<String> = emptyList(),
        val source: String = "" // "gym", "yoga", "combined", "csv"
    )

    /**
     * Load all exercise datasets. Call this once in a background coroutine.
     */
    fun loadExerciseDatabase() {
        if (exerciseCache.isNotEmpty() || isLoading) return
        isLoading = true

        val exercises = mutableListOf<ExerciseItem>()
        val nameSet = mutableSetOf<String>() // Dedup by lowercase name

        try {
            // 1. Load combined_exercises.json FIRST (richest metadata, has gifUrl)
            loadCombinedExercises(exercises, nameSet)

            // 2. Load gym exercises (exercisedb_v1_sample/exercises.json)
            loadGymExercises(exercises, nameSet)

            // 3. Load yoga poses (exercise 2/ folders)
            loadYogaExercises(exercises, nameSet)

            // 4. Load exercise3.csv (statistical data, no images)
            loadCsvExercises(exercises, nameSet)

        } catch (e: Exception) {
            Log.e(TAG, "Error loading exercise database", e)
        } finally {
            isLoading = false
        }

        if (exercises.isNotEmpty()) {
            exerciseCache = exercises.sortedBy { it.name.lowercase() }
        }

        Log.d(TAG, "Loaded ${exerciseCache.size} exercises total")
    }

    private fun loadCombinedExercises(
        exercises: MutableList<ExerciseItem>,
        nameSet: MutableSet<String>
    ) {
        try {
            val jsonString = context.assets.open("combined_exercises.json")
                .bufferedReader().use { it.readText() }
            val jsonArray = JSONArray(jsonString)

            for (i in 0 until jsonArray.length()) {
                val obj = jsonArray.getJSONObject(i)
                val name = obj.optString("name", "").trim()
                if (name.isEmpty() || nameSet.contains(name.lowercase())) continue

                val gifUrl = obj.optString("gifUrl", "")
                val instructions = mutableListOf<String>()
                val instrArr = obj.optJSONArray("instructions")
                if (instrArr != null) {
                    for (j in 0 until instrArr.length()) {
                        instructions.add(instrArr.getString(j))
                    }
                }

                val targetMuscles = mutableListOf<String>()
                val tmArr = obj.optJSONArray("targetMuscles")
                if (tmArr != null) {
                    for (j in 0 until tmArr.length()) targetMuscles.add(tmArr.getString(j))
                }

                val bodyParts = mutableListOf<String>()
                val bpArr = obj.optJSONArray("bodyParts")
                if (bpArr != null) {
                    for (j in 0 until bpArr.length()) bodyParts.add(bpArr.getString(j))
                }

                val equipments = mutableListOf<String>()
                val eqArr = obj.optJSONArray("equipments")
                if (eqArr != null) {
                    for (j in 0 until eqArr.length()) equipments.add(eqArr.getString(j))
                }

                exercises.add(
                    ExerciseItem(
                        name = name,
                        targetMuscle = targetMuscles.joinToString(", "),
                        bodyPart = bodyParts.joinToString(", "),
                        equipment = equipments.joinToString(", ").ifEmpty { "body weight" },
                        gifPath = gifUrl,
                        estimatedCalories = 100,
                        isPeriodSafe = obj.optBoolean("periodModeSafe", false),
                        intensityLevel = obj.optString("intensityLevel", "moderate"),
                        instructions = instructions,
                        source = "combined"
                    )
                )
                nameSet.add(name.lowercase())
            }
            Log.d(TAG, "Loaded ${nameSet.size} exercises from combined_exercises.json")
        } catch (e: Exception) {
            Log.e(TAG, "Error loading combined_exercises.json", e)
        }
    }

    private fun loadGymExercises(
        exercises: MutableList<ExerciseItem>,
        nameSet: MutableSet<String>
    ) {
        try {
            val jsonString = context.assets.open("exercisedb_v1_sample/exercises.json")
                .bufferedReader().use { it.readText() }
            val jsonArray = JSONArray(jsonString)

            for (i in 0 until jsonArray.length()) {
                val obj = jsonArray.getJSONObject(i)
                val name = obj.optString("name", "").trim()
                if (name.isEmpty() || nameSet.contains(name.lowercase())) continue

                val gifPath = "exercisedb_v1_sample/gifs_360x360/" + obj.optString("gifUrl", "")

                val instructions = mutableListOf<String>()
                val instrArr = obj.optJSONArray("instructions")
                if (instrArr != null) {
                    for (j in 0 until instrArr.length()) {
                        instructions.add(instrArr.getString(j))
                    }
                }

                exercises.add(
                    ExerciseItem(
                        name = name,
                        targetMuscle = obj.optString("target", ""),
                        bodyPart = obj.optString("bodyPart", ""),
                        equipment = obj.optString("equipment", "body weight"),
                        gifPath = gifPath,
                        estimatedCalories = 120,
                        isPeriodSafe = false, // Gym exercises are generally not period-safe
                        intensityLevel = "moderate",
                        instructions = instructions,
                        source = "gym"
                    )
                )
                nameSet.add(name.lowercase())
            }
            Log.d(TAG, "Loaded gym exercises, total now: ${nameSet.size}")
        } catch (e: Exception) {
            Log.e(TAG, "Error loading gym exercises", e)
        }
    }

    private fun loadYogaExercises(
        exercises: MutableList<ExerciseItem>,
        nameSet: MutableSet<String>
    ) {
        try {
            val yogaPoses = context.assets.list("exercise 2") ?: emptyArray()
            for (pose in yogaPoses) {
                if (nameSet.contains(pose.lowercase())) continue

                val files = context.assets.list("exercise 2/$pose") ?: emptyArray()
                val imageFile = files.firstOrNull {
                    it.endsWith(".png") || it.endsWith(".jpg") || it.endsWith(".gif")
                }
                if (imageFile != null) {
                    val imagePath = "exercise 2/$pose/$imageFile"
                    exercises.add(
                        ExerciseItem(
                            name = pose,
                            targetMuscle = "Full Body",
                            bodyPart = "Multiple",
                            equipment = "body weight",
                            gifPath = imagePath,
                            estimatedCalories = 80,
                            isPeriodSafe = true, // Yoga is period-safe
                            intensityLevel = "light",
                            instructions = listOf(
                                "Follow the pose demonstration",
                                "Hold for 30-60 seconds",
                                "Breathe deeply and maintain form"
                            ),
                            source = "yoga"
                        )
                    )
                    nameSet.add(pose.lowercase())
                }
            }
            Log.d(TAG, "Loaded yoga exercises, total now: ${nameSet.size}")
        } catch (e: Exception) {
            Log.e(TAG, "Error loading yoga exercises", e)
        }
    }

    private fun loadCsvExercises(
        exercises: MutableList<ExerciseItem>,
        nameSet: MutableSet<String>
    ) {
        try {
            val csvStream = context.assets.open("exercise3.csv")
            val csvReader = BufferedReader(InputStreamReader(csvStream))
            val uniqueExercises = mutableMapOf<String, Pair<Int, Int>>() // name -> (avgDuration, avgCalories)

            csvReader.readLine() // Skip header
            csvReader.forEachLine { line ->
                val parts = line.split(",")
                if (parts.size >= 5) {
                    val exerciseType = parts[2].trim()
                    val duration = parts[3].toIntOrNull() ?: 0
                    val calories = parts[4].toIntOrNull() ?: 0

                    if (exerciseType.isNotEmpty() && !nameSet.contains(exerciseType.lowercase())) {
                        val existing = uniqueExercises[exerciseType]
                        if (existing == null) {
                            uniqueExercises[exerciseType] = Pair(duration, calories)
                        } else {
                            uniqueExercises[exerciseType] = Pair(
                                (existing.first + duration) / 2,
                                (existing.second + calories) / 2
                            )
                        }
                    }
                }
            }
            csvReader.close()

            for ((exerciseName, stats) in uniqueExercises) {
                val lowerName = exerciseName.lowercase()
                val isSafe = lowerName.contains("yoga") ||
                        lowerName.contains("pilates") ||
                        lowerName.contains("walking") ||
                        lowerName.contains("stretching") ||
                        lowerName.contains("meditation")

                exercises.add(
                    ExerciseItem(
                        name = exerciseName,
                        targetMuscle = "Full Body",
                        bodyPart = "Multiple",
                        equipment = "body weight",
                        gifPath = "", // No image available for CSV entries
                        estimatedCalories = stats.second,
                        isPeriodSafe = isSafe,
                        intensityLevel = when {
                            stats.second < 100 -> "light"
                            stats.second < 200 -> "moderate"
                            else -> "high"
                        },
                        instructions = listOf(
                            "Perform at a comfortable pace",
                            "Duration: ~${stats.first} mins",
                            "Estimated burn: ~${stats.second} kcal"
                        ),
                        source = "csv"
                    )
                )
                nameSet.add(lowerName)
            }
            Log.d(TAG, "Loaded CSV exercises, total now: ${nameSet.size}")
        } catch (e: Exception) {
            Log.e(TAG, "Error loading exercise3.csv", e)
        }
    }

    /**
     * Search exercises by name, target muscle, body part, or equipment.
     * Returns matching exercises, prioritizing those with images.
     */
    fun searchExercises(query: String): List<ExerciseItem> {
        if (exerciseCache.isEmpty() && !isLoading) loadExerciseDatabase()

        val q = query.trim().lowercase()
        if (q.length < 2) return emptyList()

        return exerciseCache.filter { exercise ->
            exercise.name.lowercase().contains(q) ||
                    exercise.targetMuscle.lowercase().contains(q) ||
                    exercise.bodyPart.lowercase().contains(q) ||
                    exercise.equipment.lowercase().contains(q)
        }.sortedByDescending { it.gifPath.isNotEmpty() } // Prioritize items with images
    }

    /**
     * Get all exercises (for browsing).
     */
    fun getAllExercises(): List<ExerciseItem> {
        if (exerciseCache.isEmpty() && !isLoading) loadExerciseDatabase()
        return exerciseCache
    }

    /**
     * Get exercises filtered by category.
     */
    fun getExercisesBySource(source: String): List<ExerciseItem> {
        if (exerciseCache.isEmpty() && !isLoading) loadExerciseDatabase()
        return exerciseCache.filter { it.source == source }
    }
}
