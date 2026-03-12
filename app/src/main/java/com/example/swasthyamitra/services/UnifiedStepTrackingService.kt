package com.example.swasthyamitra.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.os.Build
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.example.swasthyamitra.R
import com.example.swasthyamitra.homepage
import com.example.swasthyamitra.models.StepSession
import com.example.swasthyamitra.step.StepGpsValidator
import com.example.swasthyamitra.utils.CalorieCalculator
import com.example.swasthyamitra.utils.DailySummaryAggregator
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.SetOptions
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.fixedRateTimer
import kotlin.math.sqrt
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Unified Step Tracking Service — combines GPS location tracking
 * with hardware step sensor and multi-layer validation.
 *
 * Replaces the need to run StepCounterService + TrackingService
 * simultaneously for step counting sessions.
 *
 * Features:
 *  - Hardware TYPE_STEP_COUNTER sensor (primary)
 *  - Accelerometer fallback for devices without step counter
 *  - FusedLocationProviderClient (HIGH_ACCURACY, 3-second intervals)
 *  - Activity Recognition for filtering
 *  - StepGpsValidator for 5-layer cross-validation
 *  - Dynamic stride calibration
 *  - Route polyline tracking
 *  - Real-time speed, pace, distance, calorie calculation
 *  - Saves to Firestore default instance
 */
class UnifiedStepTrackingService : Service(), SensorEventListener {

    // ---------- Services ----------
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var activityRecognitionClient: ActivityRecognitionClient
    private lateinit var sensorManager: SensorManager
    private var activityPendingIntent: PendingIntent? = null

    // ---------- Sensors ----------
    private var stepCounterSensor: Sensor? = null
    private var accelerometerSensor: Sensor? = null
    private var useHardwareStepCounter = false

    // Accelerometer-based step detection (fallback)
    private var lastAccelMagnitude = 0.0
    private var accelStepCount = 0
    private var lastAccelStepTime = 0L
    private val ACCEL_STEP_THRESHOLD = 12.0
    private val ACCEL_STEP_DEBOUNCE_MS = 300L

    // Hardware step counter baseline
    private var initialHwStepCount = -1f

    // ---------- GPS Validator ----------
    private val stepGpsValidator = StepGpsValidator()

    // ---------- State ----------
    private var isTracking = false
    private var sessionStartTime = 0L
    private var sessionStartDate = ""  // Track the date when session started
    private var currentRawSteps = 0
    private var baselineStepsFromFirestore = 0
    private var totalGpsDistance = 0.0
    private var lastLocation: Location? = null
    private val pathPoints = mutableListOf<LatLng>()
    private var saveTimer: Timer? = null

    // Activity time tracking
    private var currentActivityType = DetectedActivity.UNKNOWN
    private var activityChangeTime = 0L
    private var walkingSeconds = 0L
    private var runningSeconds = 0L
    private var stillSeconds = 0L

    // ---------- Firebase ----------
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    // ---------- Companion / LiveData ----------
    companion object {
        private const val TAG = "UnifiedStepService"
        private const val NOTIFICATION_ID = 1002
        private const val CHANNEL_ID = "unified_step_channel"

        // Actions
        const val ACTION_START = "UNIFIED_ACTION_START"
        const val ACTION_STOP = "UNIFIED_ACTION_STOP"

        // Broadcast
        const val ACTION_UPDATE = "com.example.swasthyamitra.unified.UPDATE"

        // LiveData — observed by MapActivity, WorkoutDashboard, homepage
        val stepsLive = MutableLiveData<Int>(0)
        val caloriesLive = MutableLiveData<Int>(0)
        val distanceLive = MutableLiveData<Double>(0.0)
        val speedLive = MutableLiveData<Double>(0.0)        // km/h
        val paceLive = MutableLiveData<String>("0'00")
        val strideLengthLive = MutableLiveData<Double>(0.72)
        val pathPointsLive = MutableLiveData<List<LatLng>>(emptyList())
        val confidenceLive = MutableLiveData<Double>(0.0)
        val activityTypeLive = MutableLiveData<Int>(DetectedActivity.UNKNOWN)
        val isTrackingLive = MutableLiveData<Boolean>(false)
        val rawStepsLive = MutableLiveData<Int>(0)
    }

    // ---------- Service lifecycle ----------

    override fun onCreate() {
        super.onCreate()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        activityRecognitionClient = ActivityRecognition.getClient(this)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        createNotificationChannel()
        isTrackingLive.postValue(false)

        // Register activity transition receiver
        val filter = IntentFilter("com.example.swasthyamitra.UNIFIED_ACTIVITY_TRANSITION")
        ContextCompat.registerReceiver(this, activityReceiver, filter, ContextCompat.RECEIVER_NOT_EXPORTED)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START -> startTracking()
            ACTION_STOP -> stopTracking()
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        try { unregisterReceiver(activityReceiver) } catch (_: Exception) {}
        if (isTracking) stopTracking()
    }

    // ---------- Start / Stop ----------

    private fun startTracking() {
        if (isTracking) {
            Log.d(TAG, "Already tracking")
            return
        }

        // CRITICAL: startForeground within 5 seconds
        startForeground(NOTIFICATION_ID, createNotification("Starting step tracker..."))

        isTracking = true
        isTrackingLive.postValue(true)
        sessionStartTime = System.currentTimeMillis()
        sessionStartDate = dateFormatter.format(Date())
        activityChangeTime = sessionStartTime

        // Reset
        currentRawSteps = 0
        totalGpsDistance = 0.0
        lastLocation = null
        pathPoints.clear()
        initialHwStepCount = -1f
        accelStepCount = 0
        walkingSeconds = 0
        runningSeconds = 0
        stillSeconds = 0
        stepGpsValidator.reset()

        // Reset LiveData
        stepsLive.postValue(0)
        caloriesLive.postValue(0)
        distanceLive.postValue(0.0)
        speedLive.postValue(0.0)
        paceLive.postValue("0'00")
        pathPointsLive.postValue(emptyList())
        confidenceLive.postValue(0.0)
        rawStepsLive.postValue(0)

        // Fetch today's existing steps + user height for stride calibration
        fetchUserDataAndStart()
    }

    private fun fetchUserDataAndStart() {
        val userId = auth.currentUser?.uid
        if (userId == null) {
            Log.w(TAG, "No user logged in")
            initSensorsAndLocation(0, 0.0)
            return
        }

        // Fetch today's steps
        val today = dateFormatter.format(Date())
        db.collection("users").document(userId)
            .collection("daily_steps").document(today)
            .get()
            .addOnSuccessListener { doc ->
                val existingSteps = doc.getLong("steps")?.toInt() ?: 0
                baselineStepsFromFirestore = existingSteps
                Log.d(TAG, "Resuming from $existingSteps steps today")

                // Also fetch user height for stride calibration
                db.collection("users").document(userId).get()
                    .addOnSuccessListener { userDoc ->
                        val height = userDoc.getDouble("height") ?: 0.0
                        initSensorsAndLocation(existingSteps, height)
                    }
                    .addOnFailureListener { initSensorsAndLocation(existingSteps, 0.0) }
            }
            .addOnFailureListener { initSensorsAndLocation(0, 0.0) }
    }

    private fun initSensorsAndLocation(existingSteps: Int, heightCm: Double) {
        baselineStepsFromFirestore = existingSteps
        currentRawSteps = existingSteps

        // Update UI with existing
        stepsLive.postValue(existingSteps)
        caloriesLive.postValue(CalorieCalculator.calculateFromStepsInt(existingSteps))

        // Set user height for stride calibration
        if (heightCm > 0) {
            stepGpsValidator.setUserHeight(heightCm)
        }

        // Register step sensor
        if (stepCounterSensor != null) {
            sensorManager.registerListener(this, stepCounterSensor, SensorManager.SENSOR_DELAY_FASTEST)
            useHardwareStepCounter = true
            Log.d(TAG, "Using hardware TYPE_STEP_COUNTER")
        } else if (accelerometerSensor != null) {
            sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_GAME)
            useHardwareStepCounter = false
            Log.d(TAG, "Fallback: Using accelerometer-based step detection")
        } else {
            Log.e(TAG, "No step sensor available!")
        }

        // Start GPS
        requestLocationUpdates()

        // Start activity recognition
        requestActivityUpdates()

        // Start periodic Firestore save (every 3 minutes)
        saveTimer = fixedRateTimer("UnifiedSave", false, 3 * 60 * 1000L, 3 * 60 * 1000L) {
            saveToFirestore()
        }

        // Also sync to SharedPreferences for offline access
        saveToSharedPreferences(existingSteps, CalorieCalculator.calculateFromStepsInt(existingSteps))

        updateNotification()
        Log.i(TAG, "Unified step tracking started (existing: $existingSteps steps)")
    }

    private fun stopTracking() {
        if (!isTracking) return
        isTracking = false

        // Accumulate final activity time
        accumulateActivityTime()

        // Unregister sensors
        sensorManager.unregisterListener(this)

        // Stop GPS
        fusedLocationClient.removeLocationUpdates(locationCallback)

        // Stop activity recognition
        removeActivityUpdates()

        // Save final data
        saveToFirestore()
        saveSessionToFirestore()

        // Stop timer
        saveTimer?.cancel()
        saveTimer = null

        isTrackingLive.postValue(false)
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()

        Log.i(TAG, "Unified step tracking stopped. Final: ${stepsLive.value} steps")
    }

    // ---------- Sensor callbacks ----------

    override fun onSensorChanged(event: SensorEvent?) {
        event ?: return
        if (!isTracking) return

        when (event.sensor.type) {
            Sensor.TYPE_STEP_COUNTER -> handleHardwareStep(event)
            Sensor.TYPE_ACCELEROMETER -> handleAccelerometerStep(event)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    private fun handleHardwareStep(event: SensorEvent) {
        val totalDeviceSteps = event.values[0]

        if (initialHwStepCount < 0) {
            initialHwStepCount = totalDeviceSteps
            Log.d(TAG, "HW step counter baseline: $totalDeviceSteps")
            return
        }

        val sessionSteps = (totalDeviceSteps - initialHwStepCount).toInt()
        val totalSteps = baselineStepsFromFirestore + sessionSteps

        processStepUpdate(totalSteps)
    }

    private fun handleAccelerometerStep(event: SensorEvent) {
        val x = event.values[0].toDouble()
        val y = event.values[1].toDouble()
        val z = event.values[2].toDouble()
        val magnitude = sqrt(x * x + y * y + z * z)

        val now = System.currentTimeMillis()

        // Peak detection
        if (magnitude > ACCEL_STEP_THRESHOLD &&
            lastAccelMagnitude <= ACCEL_STEP_THRESHOLD &&
            now - lastAccelStepTime > ACCEL_STEP_DEBOUNCE_MS) {

            accelStepCount++
            lastAccelStepTime = now
            val totalSteps = baselineStepsFromFirestore + accelStepCount
            processStepUpdate(totalSteps)
        }

        lastAccelMagnitude = magnitude
    }

    private fun processStepUpdate(totalSteps: Int) {
        val now = System.currentTimeMillis()
        currentRawSteps = totalSteps
        rawStepsLive.postValue(totalSteps)

        // Run through GPS validator
        val result = stepGpsValidator.onStepsDetected(totalSteps, now)

        val validatedSteps = result.validatedSteps
        val calories = CalorieCalculator.calculateMETBasedCalories(
            steps = validatedSteps,
            speedMs = result.speedMs,
            durationMs = now - sessionStartTime,
            weightKg = 65.0  // default, could fetch from user profile
        )

        // Update LiveData
        stepsLive.postValue(validatedSteps)
        caloriesLive.postValue(calories)
        confidenceLive.postValue(result.confidence)
        strideLengthLive.postValue(result.strideLengthM)

        // Save to SharedPreferences for offline access
        saveToSharedPreferences(validatedSteps, calories)

        // Broadcast for backward compatibility with StepManager / TrackingService
        val broadcastIntent = Intent(ACTION_UPDATE).apply {
            putExtra("steps", validatedSteps)
            putExtra("raw_steps", totalSteps)
            putExtra("calories", calories.toDouble())
            putExtra("confidence", result.confidence)
        }
        sendBroadcast(broadcastIntent)

        // Also broadcast as StepCounterService format for TrackingService compatibility
        val legacyBroadcast = Intent(StepCounterService.ACTION_UPDATE_STEPS).apply {
            putExtra("steps", validatedSteps)
            putExtra("calories", calories.toDouble())
        }
        sendBroadcast(legacyBroadcast)

        updateNotification()

        if (result.rejectionReason != null) {
            Log.d(TAG, "Steps filtered: ${result.rejectionReason}")
        }
    }

    // ---------- GPS ----------

    private fun requestLocationUpdates() {
        val request = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 3000L)
            .setMinUpdateIntervalMillis(1500L)
            .setMaxUpdateDelayMillis(5000L)
            .build()

        try {
            fusedLocationClient.requestLocationUpdates(request, locationCallback, Looper.getMainLooper())
            Log.d(TAG, "GPS location updates requested (HIGH_ACCURACY, 3s)")
        } catch (e: SecurityException) {
            Log.e(TAG, "Location permission missing", e)
        }
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult) {
            if (!isTracking) return
            val location = result.lastLocation ?: return
            val now = System.currentTimeMillis()

            // Feed GPS to validator
            stepGpsValidator.onLocationUpdate(location, now)

            // Accumulate distance
            lastLocation?.let { prev ->
                val dist = prev.distanceTo(location).toDouble()
                if (dist > 1.0) { // filter GPS jitter
                    totalGpsDistance += dist
                }
            }
            lastLocation = location

            // Update path
            val newPoint = LatLng(location.latitude, location.longitude)
            pathPoints.add(newPoint)
            pathPointsLive.postValue(pathPoints.toList())

            // Distance
            distanceLive.postValue(totalGpsDistance)

            // Speed
            val speedKmh = stepGpsValidator.getCurrentSpeedKmh()
            speedLive.postValue(speedKmh)

            // Pace
            val timeMinutes = (now - sessionStartTime) / 60_000.0
            val distKm = totalGpsDistance / 1000.0
            if (distKm > 0.01) {
                val paceMinPerKm = timeMinutes / distKm
                val mins = paceMinPerKm.toInt()
                val secs = ((paceMinPerKm - mins) * 60).toInt()
                paceLive.postValue(String.format("%d'%02d", mins, secs))
            }

            // Activity type
            activityTypeLive.postValue(stepGpsValidator.getActivityType())

            updateNotification()
        }
    }

    // ---------- Activity Recognition ----------

    private fun requestActivityUpdates() {
        val intent = Intent("com.example.swasthyamitra.UNIFIED_ACTIVITY_TRANSITION").apply {
            setPackage(packageName)
        }
        activityPendingIntent = PendingIntent.getBroadcast(
            this, 100, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
        )

        val transitions = mutableListOf(
            ActivityTransition.Builder()
                .setActivityType(DetectedActivity.STILL)
                .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER).build(),
            ActivityTransition.Builder()
                .setActivityType(DetectedActivity.WALKING)
                .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER).build(),
            ActivityTransition.Builder()
                .setActivityType(DetectedActivity.RUNNING)
                .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER).build(),
            ActivityTransition.Builder()
                .setActivityType(DetectedActivity.IN_VEHICLE)
                .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER).build(),
            ActivityTransition.Builder()
                .setActivityType(DetectedActivity.ON_BICYCLE)
                .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER).build()
        )

        val request = ActivityTransitionRequest(transitions)
        try {
            activityRecognitionClient.requestActivityTransitionUpdates(request, activityPendingIntent!!)
            Log.d(TAG, "Activity recognition started")
        } catch (e: SecurityException) {
            Log.e(TAG, "Activity Recognition permission missing", e)
        }
    }

    private fun removeActivityUpdates() {
        activityPendingIntent?.let {
            try {
                activityRecognitionClient.removeActivityTransitionUpdates(it)
            } catch (_: Exception) {}
        }
    }

    private val activityReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent ?: return
            if (ActivityTransitionResult.hasResult(intent)) {
                val result = ActivityTransitionResult.extractResult(intent) ?: return
                for (event in result.transitionEvents) {
                    if (event.transitionType == ActivityTransition.ACTIVITY_TRANSITION_ENTER) {
                        handleActivityChange(event.activityType)
                    }
                }
            }
        }
    }

    private fun handleActivityChange(activityType: Int) {
        accumulateActivityTime()
        currentActivityType = activityType
        activityChangeTime = System.currentTimeMillis()

        // Feed to GPS validator — use 85% confidence for transition events
        stepGpsValidator.onActivityDetected(activityType, 85)
        activityTypeLive.postValue(activityType)

        Log.d(TAG, "Activity changed: $activityType")
    }

    private fun accumulateActivityTime() {
        val now = System.currentTimeMillis()
        val elapsed = (now - activityChangeTime) / 1000L
        when (currentActivityType) {
            DetectedActivity.WALKING, DetectedActivity.ON_FOOT -> walkingSeconds += elapsed
            DetectedActivity.RUNNING -> runningSeconds += elapsed
            DetectedActivity.STILL -> stillSeconds += elapsed
        }
        activityChangeTime = now
    }

    // ---------- Firestore ----------

    private fun saveToFirestore() {
        val userId = auth.currentUser?.uid ?: return
        val today = dateFormatter.format(Date())
        val steps = stepsLive.value ?: 0
        if (steps == 0) return

        val calories = caloriesLive.value ?: 0

        // Handle cross-midnight: if the date has changed since session started,
        // save old day's data and reset for new day.
        if (sessionStartDate.isNotEmpty() && today != sessionStartDate) {
            Log.d(TAG, "Date changed from $sessionStartDate to $today — resetting for new day")
            // Save final count for old day
            saveForDate(userId, sessionStartDate, steps, calories)
            // Reset counters for new day
            baselineStepsFromFirestore = 0
            currentRawSteps = 0
            initialHwStepCount = -1f
            accelStepCount = 0
            sessionStartDate = today
            sessionStartTime = System.currentTimeMillis()
            stepsLive.postValue(0)
            caloriesLive.postValue(0)
            saveToSharedPreferences(0, 0)
            return
        }

        saveForDate(userId, today, steps, calories)
        saveToSharedPreferences(steps, calories)
    }

    private fun saveForDate(userId: String, date: String, steps: Int, calories: Int) {
        val hour = SimpleDateFormat("HH", Locale.getDefault()).format(Date())

        val data = hashMapOf(
            "date" to date,
            "steps" to steps,
            "calories" to calories,
            "lastUpdated" to FieldValue.serverTimestamp(),
            "confidence" to (confidenceLive.value ?: 0.0),
            "strideLength" to (strideLengthLive.value ?: 0.72),
            "gpsDistance" to totalGpsDistance,
            "source" to "unified_gps"
        )

        db.collection("users").document(userId)
            .collection("daily_steps").document(date)
            .set(data, SetOptions.merge())
            .addOnSuccessListener {
                // Update hourly breakdown
                db.collection("users").document(userId)
                    .collection("daily_steps").document(date)
                    .update("hourlySteps.$hour", steps)

                // Update DailySummary with step metrics
                val stepGoal = 10000 // Default, should be fetched from user preferences
                try {
                    val aggregator = DailySummaryAggregator(userId)
                    GlobalScope.launch {
                        aggregator.updateStepMetrics(
                            date = date,
                            steps = steps,
                            stepGoal = stepGoal,
                            distanceMeters = totalGpsDistance
                        )
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Failed to update daily summary: ${e.message}")
                }
                
                // Award XP if step goal reached
                if (steps >= stepGoal) {
                    try {
                        val xpManager = com.example.swasthyamitra.gamification.XPManager(userId)
                        xpManager.awardXP(com.example.swasthyamitra.utils.Constants.XPSource.REACH_STEP_GOAL) { leveledUp, newLevel ->
                            if (leveledUp) {
                                Log.d(TAG, "User leveled up to $newLevel!")
                            }
                        }
                    } catch (e: Exception) {
                        Log.e(TAG, "Failed to award XP: ${e.message}")
                    }
                }

                Log.d(TAG, "Saved ($date): $steps steps, $calories cal")
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "Save failed", e)
            }
    }

    /**
     * Save current step data to SharedPreferences with date tracking.
     * Used by StepManager, InsightsRepository, and GamificationActivity for instant offline access.
     */
    private fun saveToSharedPreferences(steps: Int, calories: Int) {
        val today = dateFormatter.format(Date())
        val prefs = getSharedPreferences("StepCounterPrefs", Context.MODE_PRIVATE)
        prefs.edit().apply {
            putInt("daily_steps", steps)
            putInt("daily_calories", calories)
            putString("last_date", today)
            putLong("last_updated", System.currentTimeMillis())
            apply()
        }
    }

    private fun saveSessionToFirestore() {
        val userId = auth.currentUser?.uid ?: return
        val endTime = System.currentTimeMillis()
        val durationMs = endTime - sessionStartTime
        val distKm = totalGpsDistance / 1000.0
        val durationMin = durationMs / 60_000.0
        val pace = if (distKm > 0) durationMin / distKm else 0.0
        val speedKmh = if (durationMin > 0) distKm / (durationMin / 60.0) else 0.0

        val session = StepSession(
            userId = userId,
            startTime = sessionStartTime,
            endTime = endTime,
            validatedSteps = stepsLive.value ?: 0,
            rawSteps = rawStepsLive.value ?: 0,
            totalDistanceMeters = totalGpsDistance,
            routePoints = pathPoints.map { GeoPoint(it.latitude, it.longitude) },
            averagePace = pace,
            averageSpeedKmh = speedKmh,
            averageStrideM = stepGpsValidator.getStrideLengthM(),
            confidenceScore = stepGpsValidator.getConfidence(),
            caloriesBurned = (caloriesLive.value ?: 0).toDouble(),
            walkingSeconds = walkingSeconds,
            runningSeconds = runningSeconds,
            stillSeconds = stillSeconds,
            hourlySteps = emptyMap() // could populate from daily_steps doc
        )

        db.collection("users").document(userId)
            .collection("step_sessions")
            .add(session)
            .addOnSuccessListener { ref ->
                Log.d(TAG, "Session saved: ${ref.id}")
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "Session save failed", e)
            }
    }

    // ---------- Notification ----------

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Live Step Tracker",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "GPS-enhanced live step tracking"
                setShowBadge(false)
            }
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    private fun createNotification(text: String? = null): Notification {
        val intent = Intent(this, homepage::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val steps = stepsLive.value ?: 0
        val calories = caloriesLive.value ?: 0
        val distKm = (distanceLive.value ?: 0.0) / 1000.0
        val content = text ?: "🚶 $steps steps | 🔥 $calories kcal | 📍 ${String.format("%.2f", distKm)} km"

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Live Step Tracker")
            .setContentText(content)
            .setSmallIcon(R.drawable.ic_walk)
            .setOngoing(true)
            .setContentIntent(pendingIntent)
            .build()
    }

    private fun updateNotification() {
        val manager = getSystemService(NotificationManager::class.java)
        manager.notify(NOTIFICATION_ID, createNotification())
    }
}
