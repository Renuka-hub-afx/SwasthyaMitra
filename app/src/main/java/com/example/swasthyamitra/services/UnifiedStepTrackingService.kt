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
/**
 * UnifiedStepTrackingService is an advanced background service that combines multiple 
 * data sources to provide highly accurate step tracking and route mapping.
 *
 * It integrates:
 * 1. Hardware Step Counter (Primary sensor)
 * 2. Accelerometer (Fallback sensor for older devices)
 * 3. GPS (FusedLocationProvider for distance, speed, and path tracking)
 * 4. Activity Recognition (To filter out non-walking movements like driving)
 * 5. StepGpsValidator (A 5-layer logic engine that cross-references steps with GPS speed)
 */
class UnifiedStepTrackingService : Service(), SensorEventListener {

    // Service clients for location and activity data
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var activityRecognitionClient: ActivityRecognitionClient
    private lateinit var sensorManager: SensorManager
    private var activityPendingIntent: PendingIntent? = null

    // Sensor references
    private var stepCounterSensor: Sensor? = null
    private var accelerometerSensor: Sensor? = null
    private var useHardwareStepCounter = false

    // Accelerometer processing state (Fallback detection)
    private var lastAccelMagnitude = 0.0
    private var accelStepCount = 0
    private var lastAccelStepTime = 0L
    private val ACCEL_STEP_THRESHOLD = 12.0 // Magnitude threshold for a step
    private val ACCEL_STEP_DEBOUNCE_MS = 300L // Minimum time between steps

    // Hardware sensor baseline (it reports total steps since last boot)
    private var initialHwStepCount = -1f

    // Logic engine for cross-validating steps with GPS movement
    private val stepGpsValidator = StepGpsValidator()

    // Tracking state
    private var isTracking = false
    private var sessionStartTime = 0L
    private var sessionStartDate = ""  // Used for midnight rollover logic
    private var currentRawSteps = 0
    private var baselineStepsFromFirestore = 0 // Steps already taken before this session
    private var totalGpsDistance = 0.0
    private var lastLocation: Location? = null
    private val pathPoints = mutableListOf<LatLng>() // Coordinates of the current walk/run
    private var saveTimer: Timer? = null

    // Time-based activity metrics
    private var currentActivityType = DetectedActivity.UNKNOWN
    private var activityChangeTime = 0L
    private var walkingSeconds = 0L
    private var runningSeconds = 0L
    private var stillSeconds = 0L

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    companion object {
        private const val TAG = "UnifiedStepService"
        private const val NOTIFICATION_ID = 1002
        private const val CHANNEL_ID = "unified_step_channel"

        // Actions for service control
        const val ACTION_START = "UNIFIED_ACTION_START"
        const val ACTION_STOP = "UNIFIED_ACTION_STOP"

        // Broadcast action for UI updates
        const val ACTION_UPDATE = "com.example.swasthyamitra.unified.UPDATE"

        // LiveData: Real-time data streams consumed by the UI (Maps, Dashboards, Home)
        val stepsLive = MutableLiveData<Int>(0)
        val caloriesLive = MutableLiveData<Int>(0)
        val distanceLive = MutableLiveData<Double>(0.0)
        val speedLive = MutableLiveData<Double>(0.0)        // in km/h
        val paceLive = MutableLiveData<String>("0'00")      // in min/km
        val strideLengthLive = MutableLiveData<Double>(0.72) // dynamic calibration
        val pathPointsLive = MutableLiveData<List<LatLng>>(emptyList())
        val confidenceLive = MutableLiveData<Double>(0.0)
        val activityTypeLive = MutableLiveData<Int>(DetectedActivity.UNKNOWN)
        val isTrackingLive = MutableLiveData<Boolean>(false)
        val rawStepsLive = MutableLiveData<Int>(0)
    }

    override fun onCreate() {
        super.onCreate()
        // Initialize location and sensor managers
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        activityRecognitionClient = ActivityRecognition.getClient(this)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        createNotificationChannel()
        isTrackingLive.postValue(false)

        // Listen for activity transition events (walking, running, still)
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

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    /**
     * Initializes a new tracking session. 
     * Resets local counters and starts foreground mode.
     */
    private fun startTracking() {
        if (isTracking) return

        // Start Foreground Service with a persistent notification
        startForeground(NOTIFICATION_ID, createNotification("Starting step tracker..."))

        isTracking = true
        isTrackingLive.postValue(true)
        sessionStartTime = System.currentTimeMillis()
        sessionStartDate = dateFormatter.format(Date())
        activityChangeTime = sessionStartTime

        // Reset all session metrics
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

        // Reset UI observers
        stepsLive.postValue(0)
        caloriesLive.postValue(0)
        distanceLive.postValue(0.0)
        speedLive.postValue(0.0)
        paceLive.postValue("0'00")
        pathPointsLive.postValue(emptyList())
        confidenceLive.postValue(0.0)
        rawStepsLive.postValue(0)

        // Synchronize with existing daily totals before starting
        fetchUserDataAndStart()
    }

    /**
     * Fetches the user's current daily step total and height from Firestore.
     * Height is used to calibrate stride length for more accurate distance estimation.
     */
    private fun fetchUserDataAndStart() {
        val userId = auth.currentUser?.uid ?: return

        val today = dateFormatter.format(Date())
        db.collection("users").document(userId)
            .collection("daily_steps").document(today)
            .get()
            .addOnSuccessListener { doc ->
                val existingSteps = doc.getLong("steps")?.toInt() ?: 0
                baselineStepsFromFirestore = existingSteps

                // Fetch height for stride length calibration
                db.collection("users").document(userId).get()
                    .addOnSuccessListener { userDoc ->
                        val height = userDoc.getDouble("height") ?: 0.0
                        initSensorsAndLocation(existingSteps, height)
                    }
                    .addOnFailureListener { initSensorsAndLocation(existingSteps, 0.0) }
            }
            .addOnFailureListener { initSensorsAndLocation(0, 0.0) }
    }

    /**
     * Configures the sensors and GPS once initial data is loaded.
     */
    private fun initSensorsAndLocation(existingSteps: Int, heightCm: Double) {
        baselineStepsFromFirestore = existingSteps
        currentRawSteps = existingSteps

        stepsLive.postValue(existingSteps)
        caloriesLive.postValue(CalorieCalculator.calculateFromStepsInt(existingSteps))

        if (heightCm > 0) {
            stepGpsValidator.setUserHeight(heightCm)
        }

        // Use Hardware Step Counter if available (best for battery), otherwise fall back to Accelerometer
        if (stepCounterSensor != null) {
            sensorManager.registerListener(this, stepCounterSensor, SensorManager.SENSOR_DELAY_FASTEST)
            useHardwareStepCounter = true
        } else if (accelerometerSensor != null) {
            sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_GAME)
            useHardwareStepCounter = false
        }

        requestLocationUpdates()
        requestActivityUpdates()

        // Start periodic cloud synchronization
        saveTimer = fixedRateTimer("UnifiedSave", false, 3 * 60 * 1000L, 3 * 60 * 1000L) {
            saveToFirestore()
        }

        saveToSharedPreferences(existingSteps, CalorieCalculator.calculateFromStepsInt(existingSteps))
        updateNotification()
    }

    /**
     * Stops the tracking session, cleans up resources, and saves final results to Firestore.
     */
    private fun stopTracking() {
        if (!isTracking) return
        isTracking = false

        accumulateActivityTime()
        sensorManager.unregisterListener(this)
        fusedLocationClient.removeLocationUpdates(locationCallback)
        removeActivityUpdates()

        // Persistent save of both the daily total and the specific session data
        saveToFirestore()
        saveSessionToFirestore()

        saveTimer?.cancel()
        saveTimer = null

        isTrackingLive.postValue(false)
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

    /**
     * Primary sensor callback. Routes data based on the active sensor type.
     */
    override fun onSensorChanged(event: SensorEvent?) {
        event ?: return
        if (!isTracking) return

        when (event.sensor.type) {
            Sensor.TYPE_STEP_COUNTER -> handleHardwareStep(event)
            Sensor.TYPE_ACCELEROMETER -> handleAccelerometerStep(event)
        }
    }

    /**
     * Logic for the standard Step Counter sensor (reports absolute counts).
     */
    private fun handleHardwareStep(event: SensorEvent) {
        val totalDeviceSteps = event.values[0]

        // Record the sensor value at the start of the session to calculate "steps during session"
        if (initialHwStepCount < 0) {
            initialHwStepCount = totalDeviceSteps
            return
        }

        val sessionSteps = (totalDeviceSteps - initialHwStepCount).toInt()
        val totalSteps = baselineStepsFromFirestore + sessionSteps
        processStepUpdate(totalSteps)
    }

    /**
     * Fallback logic for devices without a step counter.
     * Uses 3-axis magnitude and peak detection to estimate steps.
     */
    private fun handleAccelerometerStep(event: SensorEvent) {
        val x = event.values[0].toDouble()
        val y = event.values[1].toDouble()
        val z = event.values[2].toDouble()
        val magnitude = sqrt(x * x + y * y + z * z)

        val now = System.currentTimeMillis()

        // Detection: Magnitude crosses threshold + debounce to prevent double-counting
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

    /**
     * The core processing pipe for every detected step.
     * Passes the raw count through the GPS validator to filter out false positives.
     */
    private fun processStepUpdate(totalSteps: Int) {
        val now = System.currentTimeMillis()
        currentRawSteps = totalSteps
        rawStepsLive.postValue(totalSteps)

        // The Validator cross-references the step rate with GPS speed
        // e.g., If the user is moving at 60km/h, these steps are likely false (driving).
        val result = stepGpsValidator.onStepsDetected(totalSteps, now)

        val validatedSteps = result.validatedSteps
        val calories = CalorieCalculator.calculateMETBasedCalories(
            steps = validatedSteps,
            speedMs = result.speedMs,
            durationMs = now - sessionStartTime,
            weightKg = 65.0 
        )

        // Update real-time UI streams
        stepsLive.postValue(validatedSteps)
        caloriesLive.postValue(calories)
        confidenceLive.postValue(result.confidence)
        strideLengthLive.postValue(result.strideLengthM)

        saveToSharedPreferences(validatedSteps, calories)

        // Broadcast updates for backward compatibility and internal listeners
        val broadcastIntent = Intent(ACTION_UPDATE).apply {
            putExtra("steps", validatedSteps)
            putExtra("raw_steps", totalSteps)
            putExtra("calories", calories.toDouble())
            putExtra("confidence", result.confidence)
        }
        sendBroadcast(broadcastIntent)

        updateNotification()
    }

    /**
     * configures location updates at 3-second intervals for path tracking.
     */
    private fun requestLocationUpdates() {
        val request = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 3000L).build()
        try {
            fusedLocationClient.requestLocationUpdates(request, locationCallback, Looper.getMainLooper())
        } catch (e: SecurityException) {
            Log.e(TAG, "Location permission missing", e)
        }
    }

    /**
     * Location callback that feeds GPS data into the validator and updates the route path.
     */
    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult) {
            if (!isTracking) return
            val location = result.lastLocation ?: return
            val now = System.currentTimeMillis()

            // Feed GPS data to the step validator for confidence calculations
            stepGpsValidator.onLocationUpdate(location, now)

            // Calculate total distance traveled, filtering out small GPS jitter
            lastLocation?.let { prev ->
                val dist = prev.distanceTo(location).toDouble()
                if (dist > 1.0) { 
                    totalGpsDistance += dist
                }
            }
            lastLocation = location

            // Update the graphical path on the map
            val newPoint = LatLng(location.latitude, location.longitude)
            pathPoints.add(newPoint)
            pathPointsLive.postValue(pathPoints.toList())

            distanceLive.postValue(totalGpsDistance)
            speedLive.postValue(stepGpsValidator.getCurrentSpeedKmh())

            // Update pace (minutes per kilometer)
            val timeMinutes = (now - sessionStartTime) / 60_000.0
            val distKm = totalGpsDistance / 1000.0
            if (distKm > 0.01) {
                val paceMinPerKm = timeMinutes / distKm
                paceLive.postValue(String.format("%d'%02d", paceMinPerKm.toInt(), ((paceMinPerKm % 1) * 60).toInt()))
            }

            updateNotification()
        }
    }

    /**
     * Uses Google's Activity Recognition API to detect if the user is Walking/Running.
     */
    private fun requestActivityUpdates() {
        val intent = Intent("com.example.swasthyamitra.UNIFIED_ACTIVITY_TRANSITION").apply { setPackage(packageName) }
        activityPendingIntent = PendingIntent.getBroadcast(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE)

        val transitions = mutableListOf(
            ActivityTransition.Builder().setActivityType(DetectedActivity.STILL).setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER).build(),
            ActivityTransition.Builder().setActivityType(DetectedActivity.WALKING).setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER).build(),
            ActivityTransition.Builder().setActivityType(DetectedActivity.RUNNING).setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER).build()
        )

        try { activityRecognitionClient.requestActivityTransitionUpdates(ActivityTransitionRequest(transitions), activityPendingIntent!!) } catch (_: Exception) {}
    }

    private val activityReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (ActivityTransitionResult.hasResult(intent!!)) {
                val result = ActivityTransitionResult.extractResult(intent) ?: return
                for (event in result.transitionEvents) {
                    if (event.transitionType == ActivityTransition.ACTIVITY_TRANSITION_ENTER) {
                        handleActivityChange(event.activityType)
                    }
                }
            }
        }
    }

    /**
     * Updates internal timers and logic based on user activity state changes.
     */
    private fun handleActivityChange(activityType: Int) {
        accumulateActivityTime()
        currentActivityType = activityType
        activityChangeTime = System.currentTimeMillis()

        stepGpsValidator.onActivityDetected(activityType, 85)
        activityTypeLive.postValue(activityType)
    }

    /**
     * Accumulates time spent in different activity states (walking, running, still).
     */
    private fun accumulateActivityTime() {
        val elapsed = (System.currentTimeMillis() - activityChangeTime) / 1000L
        when (currentActivityType) {
            DetectedActivity.WALKING, DetectedActivity.ON_FOOT -> walkingSeconds += elapsed
            DetectedActivity.RUNNING -> runningSeconds += elapsed
            DetectedActivity.STILL -> stillSeconds += elapsed
        }
    }

    /**
     * Periodic task to save cumulative steps and calories for the day.
     */
    private fun saveToFirestore() {
        val userId = auth.currentUser?.uid ?: return
        val today = dateFormatter.format(Date())
        val steps = stepsLive.value ?: 0
        if (steps == 0) return

        // Midnight check: if the day has rolled over, finalize and reset.
        if (sessionStartDate.isNotEmpty() && today != sessionStartDate) {
            saveForDate(userId, sessionStartDate, steps, caloriesLive.value ?: 0)
            baselineStepsFromFirestore = 0
            currentRawSteps = 0
            initialHwStepCount = -1f
            sessionStartDate = today
            sessionStartTime = System.currentTimeMillis()
            return
        }

        saveForDate(userId, today, steps, caloriesLive.value ?: 0)
    }

    /**
     * Updates the daily total document and increments XP if goals are met.
     */
    private fun saveForDate(userId: String, date: String, steps: Int, calories: Int) {
        val hour = SimpleDateFormat("HH", Locale.getDefault()).format(Date())
        val data = hashMapOf(
            "date" to date,
            "steps" to steps,
            "calories" to calories,
            "gpsDistance" to totalGpsDistance
        )

        db.collection("users").document(userId).collection("daily_steps").document(date)
            .set(data, SetOptions.merge())
            .addOnSuccessListener {
                db.collection("users").document(userId).collection("daily_steps").document(date)
                    .update("hourlySteps.$hour", steps)
                
                // Trigger gamification logic if step goal achieved
                if (steps >= 10000) {
                    val xpManager = com.example.swasthyamitra.gamification.XPManager(userId)
                    xpManager.awardXP(com.example.swasthyamitra.utils.Constants.XPSource.REACH_STEP_GOAL) { _, _ -> }
                }
            }
    }

    /**
     * Saves a detailed session object summarizing this specific workout period.
     */
    private fun saveSessionToFirestore() {
        val userId = auth.currentUser?.uid ?: return
        val session = StepSession(
            userId = userId,
            startTime = sessionStartTime,
            endTime = System.currentTimeMillis(),
            validatedSteps = stepsLive.value ?: 0,
            rawSteps = rawStepsLive.value ?: 0,
            totalDistanceMeters = totalGpsDistance,
            routePoints = pathPoints.map { GeoPoint(it.latitude, it.longitude) },
            averageStrideM = stepGpsValidator.getStrideLengthM(),
            confidenceScore = stepGpsValidator.getConfidence(),
            caloriesBurned = (caloriesLive.value ?: 0).toDouble(),
            walkingSeconds = walkingSeconds,
            runningSeconds = runningSeconds,
            stillSeconds = stillSeconds
        )

        db.collection("users").document(userId).collection("step_sessions").add(session)
    }

    /**
     * Notification management for the foreground service state.
     */
    private fun updateNotification() {
        val notification = createNotification()
        val manager = getSystemService(NotificationManager::class.java)
        manager.notify(NOTIFICATION_ID, notification)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, "Live Step Tracker", NotificationManager.IMPORTANCE_LOW)
            getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        }
    }

    private fun createNotification(text: String? = null): Notification {
        val pendingIntent = PendingIntent.getActivity(this, 0, Intent(this, homepage::class.java), PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        val content = text ?: "🚶 ${stepsLive.value ?: 0} steps | 🔥 ${caloriesLive.value ?: 0} kcal | 📍 ${String.format("%.2f", totalGpsDistance / 1000.0)} km"
        return NotificationCompat.Builder(this, CHANNEL_ID).setContentTitle("Live Step Tracker").setContentText(content).setSmallIcon(R.drawable.ic_walk).setOngoing(true).setContentIntent(pendingIntent).build()
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    private fun saveToSharedPreferences(steps: Int, calories: Int) {
        val prefs = getSharedPreferences("StepCounterPrefs", Context.MODE_PRIVATE)
        prefs.edit().apply {
            putInt("daily_steps", steps)
            putFloat("daily_calories", calories.toFloat())
            putString("last_date", dateFormatter.format(Date()))
            apply()
        }
    }

    private fun removeActivityUpdates() {
        activityPendingIntent?.let {
            activityRecognitionClient.removeActivityTransitionUpdates(it)
        }
    }
}

