package com.example.swasthyamitra

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.swasthyamitra.databinding.ActivityMapBinding
import com.example.swasthyamitra.safety.EmergencyContact
import com.example.swasthyamitra.safety.SOSManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.example.swasthyamitra.services.TrackingService
import com.example.swasthyamitra.services.UnifiedStepTrackingService
import com.google.android.gms.location.DetectedActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions

/**
 * Unified Map + Step Tracker Activity.
 *
 * Shows a live Google Map with:
 *  - Real-time position (blue dot)
 *  - Route polyline as the user walks
 *  - Live stats overlay: steps, distance, pace, calories, speed, confidence
 *  - Stride length chip
 *  - Ghost mode + SOS (safety features from TrackingService)
 *  - Session history button
 *
 * Drives [UnifiedStepTrackingService] for combined GPS + step sensor tracking.
 */
class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMapBinding
    private var googleMap: GoogleMap? = null
    private var isTracking = false
    private var isGhostMode = false

    // SOS support
    private lateinit var sosManager: SOSManager
    private lateinit var fusedLocationClientForSOS: FusedLocationProviderClient

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true) {
                enableUserLocation()
                startUnifiedService()
            } else {
                Toast.makeText(this, "Location permission is required for step tracking", Toast.LENGTH_SHORT).show()
            }
        }

    // SMS permission launcher for SOS
    private val smsPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) {
                performSOSSend()
            } else {
                Toast.makeText(this, "SMS permission is required to send SOS alerts", Toast.LENGTH_LONG).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sosManager = SOSManager(this)
        fusedLocationClientForSOS = LocationServices.getFusedLocationProviderClient(this)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        setupListeners()
        observeUnifiedService()
        observeGhostMode()
        
        // Auto-start if launched with that intent
        if (intent.getBooleanExtra("AUTO_START", false)) {
            // Will start after map is ready and permissions checked
        }
        
        // Handle entry from Ghost Mode button
        if (intent.getBooleanExtra("START_GHOST", false)) {
            sendGhostCommand()
        }
    }

    private fun setupListeners() {
        binding.btnToggleTracking.setOnClickListener {
            toggleTracking()
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnToggleGhost.setOnClickListener {
            sendGhostCommand()
        }

        binding.btnSOS.setOnClickListener {
            triggerSOS()
        }
        
        binding.etEmergencyContact.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) saveEmergencyContact()
        }

        binding.btnHistory.setOnClickListener {
            startActivity(Intent(this, StepSessionHistoryActivity::class.java))
        }
    }

    // -------- Tracking control --------

    private fun toggleTracking() {
        if (isTracking) {
            stopUnifiedService()
        } else {
            checkPermissionsAndStart()
        }
    }

    private fun checkPermissionsAndStart() {
        val permissions = mutableListOf(Manifest.permission.ACCESS_FINE_LOCATION)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            permissions.add(Manifest.permission.ACTIVITY_RECOGNITION)
        }

        val missing = permissions.filter {
            ActivityCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }

        if (missing.isEmpty()) {
            startUnifiedService()
        } else {
            requestPermissionLauncher.launch(missing.toTypedArray())
        }
    }

    private fun startUnifiedService() {
        val intent = Intent(this, UnifiedStepTrackingService::class.java).apply {
            action = UnifiedStepTrackingService.ACTION_START
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            startForegroundService(intent)
        } else {
            startService(intent)
        }
    }

    private fun stopUnifiedService() {
        val intent = Intent(this, UnifiedStepTrackingService::class.java).apply {
            action = UnifiedStepTrackingService.ACTION_STOP
        }
        startService(intent)
    }

    private fun sendGhostCommand() {
        // Ghost mode still handled by TrackingService for safety
        val intent = Intent(this, TrackingService::class.java).apply {
            action = "ACTION_TOGGLE_GHOST"
        }
        startService(intent)
    }

    // -------- Observe UnifiedStepTrackingService --------

    private fun observeUnifiedService() {
        UnifiedStepTrackingService.isTrackingLive.observe(this) { tracking ->
            isTracking = tracking
            binding.btnToggleTracking.text = if (isTracking) "STOP" else "START"
            binding.btnToggleTracking.setBackgroundColor(
                if (isTracking) Color.parseColor("#F44336") else Color.parseColor("#4CAF50")
            )
        }

        UnifiedStepTrackingService.pathPointsLive.observe(this) { points ->
            drawPolyline(points)
            if (points.isNotEmpty()) {
                googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(points.last(), 16f))
            }
        }

        UnifiedStepTrackingService.distanceLive.observe(this) { distance ->
            binding.tvDistance.text = String.format("%.2f", distance / 1000.0)
        }

        UnifiedStepTrackingService.paceLive.observe(this) { pace ->
            binding.tvPace.text = pace
        }

        UnifiedStepTrackingService.stepsLive.observe(this) { steps ->
            binding.tvSteps.text = steps.toString()
        }

        UnifiedStepTrackingService.caloriesLive.observe(this) { calories ->
            binding.tvCalories.text = calories.toString()
        }

        UnifiedStepTrackingService.speedLive.observe(this) { speedKmh ->
            binding.tvSpeed.text = String.format("%.1f", speedKmh)
        }

        UnifiedStepTrackingService.confidenceLive.observe(this) { confidence ->
            binding.tvConfidence.text = "${confidence.toInt()}%"
            // Color the confidence dot: green >= 70, yellow >= 40, red < 40
            val color = when {
                confidence >= 70 -> "#4CAF50"  // green
                confidence >= 40 -> "#FF9800"  // orange
                else -> "#F44336"              // red
            }
            binding.viewConfidenceDot.backgroundTintList =
                android.content.res.ColorStateList.valueOf(Color.parseColor(color))
        }

        UnifiedStepTrackingService.strideLengthLive.observe(this) { stride ->
            binding.tvStride.text = String.format("%.2fm", stride)
        }
    }

    private fun observeGhostMode() {
        TrackingService.isGhostModeLive.observe(this) { ghost ->
            isGhostMode = ghost
            binding.btnToggleGhost.alpha = if (isGhostMode) 1.0f else 0.5f
            binding.btnSOS.visibility = if (isGhostMode && isTracking) View.VISIBLE else View.GONE
            binding.llEmergencyContact.visibility = if (isGhostMode) View.VISIBLE else View.GONE

            if (isGhostMode) loadEmergencyContact()
        }
    }

    // -------- SOS / Safety --------

    private fun triggerSOS() {
        // Build emergency contact from either EmergencyContactManager or the text field
        val contactNumber = binding.etEmergencyContact.text.toString().trim()
        if (contactNumber.isEmpty()) {
            Toast.makeText(this, "Set emergency contact first!", Toast.LENGTH_SHORT).show()
            return
        }
        
        // Check SMS permission before sending
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            smsPermissionLauncher.launch(Manifest.permission.SEND_SMS)
            return
        }
        
        performSOSSend()
    }
    
    /**
     * Actually sends the SOS SMS via SOSManager after permission is confirmed.
     */
    private fun performSOSSend() {
        val contactNumber = binding.etEmergencyContact.text.toString().trim()
        if (contactNumber.isEmpty()) return
        
        // Build an EmergencyContact — use saved name if available, otherwise just the number
        val prefs = getSharedPreferences("SafetyPrefs", Context.MODE_PRIVATE)
        val contactName = prefs.getString("contact_name", null) ?: "Emergency Contact"
        val contact = EmergencyContact(name = contactName, phoneNumber = contactNumber)
        
        // Get last known location and send SOS
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClientForSOS.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    sosManager.sendSOS(contact, location.latitude, location.longitude, "Manual SOS - Step Tracking")
                    Toast.makeText(this, "\u26A0\uFE0F SOS Alert sent to $contactNumber with your location!", Toast.LENGTH_LONG).show()
                } else {
                    // Send SOS with default coordinates (0,0) — SOSManager will still include timestamp & battery
                    sosManager.sendSOS(contact, 0.0, 0.0, "Manual SOS - Step Tracking (location unavailable)")
                    Toast.makeText(this, "\u26A0\uFE0F SOS Alert sent to $contactNumber (location unavailable)", Toast.LENGTH_LONG).show()
                }
            }.addOnFailureListener {
                sosManager.sendSOS(contact, 0.0, 0.0, "Manual SOS - Step Tracking (location error)")
                Toast.makeText(this, "\u26A0\uFE0F SOS Alert sent to $contactNumber", Toast.LENGTH_LONG).show()
            }
        } else {
            // No location permission — send without location
            sosManager.sendSOS(contact, 0.0, 0.0, "Manual SOS - Step Tracking (no location permission)")
            Toast.makeText(this, "\u26A0\uFE0F SOS Alert sent to $contactNumber (no location)", Toast.LENGTH_LONG).show()
        }
    }

    private fun saveEmergencyContact() {
        val contact = binding.etEmergencyContact.text.toString()
        getSharedPreferences("SafetyPrefs", Context.MODE_PRIVATE)
            .edit().putString("contact_number", contact).apply()
    }

    private fun loadEmergencyContact() {
        val contact = getSharedPreferences("SafetyPrefs", Context.MODE_PRIVATE)
            .getString("contact_number", "")
        binding.etEmergencyContact.setText(contact)
    }

    // -------- Map --------

    private fun drawPolyline(points: List<LatLng>) {
        googleMap?.clear()
        if (points.size < 2) return
        val polylineOptions = PolylineOptions()
            .color(Color.parseColor("#9C27B0"))
            .width(12f)
            .addAll(points)
        googleMap?.addPolyline(polylineOptions)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        googleMap?.uiSettings?.isZoomControlsEnabled = true
        googleMap?.uiSettings?.isCompassEnabled = true
        enableUserLocation()

        // Auto-start tracking if requested
        if (intent.getBooleanExtra("AUTO_START", false) && !isTracking) {
            checkPermissionsAndStart()
        }
    }

    private fun enableUserLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            googleMap?.isMyLocationEnabled = true
        }
    }

    // -------- Safety receiver --------

    override fun onStart() {
        super.onStart()
        val intentFilter = IntentFilter(TrackingService.ACTION_SAFETY_ALERT)
        androidx.core.content.ContextCompat.registerReceiver(
            this,
            safetyReceiver,
            intentFilter,
            androidx.core.content.ContextCompat.RECEIVER_NOT_EXPORTED
        )
    }

    private val safetyReceiver = object : android.content.BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            showSafetyCheckDialog()
        }
    }

    private fun showSafetyCheckDialog() {
        androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("Safety Check \u26A0\uFE0F")
            .setMessage("No movement detected in Ghost Mode. Are you safe?")
            .setPositiveButton("I'm Safe") { d, _ -> d.dismiss() }
            .setCancelable(false)
            .show()
    }
}
