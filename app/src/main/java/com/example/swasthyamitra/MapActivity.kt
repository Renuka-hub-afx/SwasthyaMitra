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
import com.example.swasthyamitra.R
import com.example.swasthyamitra.databinding.ActivityMapBinding
import com.example.swasthyamitra.services.TrackingService
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMapBinding
    private var googleMap: GoogleMap? = null
    private var isTracking = false
    private var isGhostMode = false

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true) {
                enableUserLocation()
            } else {
                Toast.makeText(this, "Location permission is required", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        setupListeners()
        observeTrackingData()
        
        // Handle entry from Ghost Mode button
        if (intent.getBooleanExtra("START_GHOST", false)) {
            sendCommandToService("ACTION_TOGGLE_GHOST")
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
            sendCommandToService("ACTION_TOGGLE_GHOST")
        }

        binding.btnSOS.setOnClickListener {
            triggerSOS()
        }
        
        binding.etEmergencyContact.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) saveEmergencyContact()
        }
    }

    private fun triggerSOS() {
        val contact = binding.etEmergencyContact.text.toString()
        if (contact.isEmpty()) {
            Toast.makeText(this, "Set emergency contact first!", Toast.LENGTH_SHORT).show()
            return
        }
        
        Toast.makeText(this, "SOS Alert Sent to $contact!", Toast.LENGTH_LONG).show()
        // In a real app, this would use SMS or API. 
        // We'll simulate by calling the service if available or just toast.
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

    private fun toggleTracking() {
        if (isTracking) {
            sendCommandToService("ACTION_STOP")
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
            sendCommandToService("ACTION_START")
        } else {
            requestPermissionLauncher.launch(missing.toTypedArray())
        }
    }

    private fun sendCommandToService(action: String) {
        val intent = Intent(this, TrackingService::class.java).apply {
            this.action = action
        }
        startService(intent)
    }

    private fun observeTrackingData() {
        TrackingService.isTrackingLive.observe(this) { tracking ->
            isTracking = tracking
            binding.btnToggleTracking.text = if (isTracking) "STOP" else "START"
            binding.btnToggleTracking.setBackgroundColor(if (isTracking) Color.RED else Color.parseColor("#6200EE"))
        }

        TrackingService.pathPointsLive.observe(this) { points ->
            drawPolyline(points)
            if (points.isNotEmpty()) {
                val lastPoint = points.last()
                googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(lastPoint, 16f))
            }
        }

        TrackingService.distanceLive.observe(this) { distance ->
            val km = distance / 1000.0
            binding.tvDistance.text = String.format("%.2f", km)
        }

        TrackingService.paceLive.observe(this) { pace ->
            binding.tvPace.text = pace
        }

        TrackingService.stepsLive.observe(this) { steps ->
            binding.tvSteps.text = steps.toString()
        }

        TrackingService.isGhostModeLive.observe(this) { ghost ->
            isGhostMode = ghost
            binding.btnToggleGhost.alpha = if (isGhostMode) 1.0f else 0.5f
            binding.btnSOS.visibility = if (isGhostMode && isTracking) View.VISIBLE else View.GONE
            binding.llEmergencyContact.visibility = if (isGhostMode) View.VISIBLE else View.GONE
            
            if (isGhostMode) loadEmergencyContact()
        }
    }

    override fun onStart() {
        super.onStart()
        // Listen for safety alerts
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

    private fun drawPolyline(points: List<LatLng>) {
        // googleMap?.clear() // Removing clear to avoid flickering
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
    }

    private fun enableUserLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            googleMap?.isMyLocationEnabled = true
        }
    }
}
