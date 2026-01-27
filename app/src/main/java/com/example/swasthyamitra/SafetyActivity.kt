package com.example.swasthyamitra

import android.Manifest
import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.swasthyamitra.services.SafetyMonitorService

class SafetyActivity : AppCompatActivity() {

    private var safetyService: SafetyMonitorService? = null
    private var isBound = false
    private var isTracking = false

    private lateinit var tvDistance: TextView
    private lateinit var tvSafetyStatus: TextView
    private lateinit var btnStartRun: Button
    private lateinit var btnSOS: Button
    private lateinit var etContactNumber: EditText

    private val uiHandler = Handler(Looper.getMainLooper())
    private val uiRunnable = object : Runnable {
        override fun run() {
            if (isBound && safetyService != null) {
                updateDistanceUI(safetyService!!.totalDistance)
            }
            uiHandler.postDelayed(this, 1000)
        }
    }

    private val safetyReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                SafetyMonitorService.ACTION_INACTIVITY_WARNING -> showSafetyCheckDialog()
                SafetyMonitorService.ACTION_SOS_TRIGGERED -> {
                    tvSafetyStatus.text = "Status: SOS SENT 🚨"
                    tvSafetyStatus.setTextColor(ContextCompat.getColor(this@SafetyActivity, android.R.color.holo_red_dark))
                    Toast.makeText(this@SafetyActivity, "SOS Alert Sent!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as SafetyMonitorService.LocalBinder
            safetyService = binder.getService()
            isBound = true
            uiHandler.post(uiRunnable)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            safetyService = null
            isBound = false
            uiHandler.removeCallbacks(uiRunnable)
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val allGranted = permissions.entries.all { it.value }
            if (allGranted) {
                startServiceTracking()
            } else {
                Toast.makeText(this, "Permissions needed for safety features", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_safety)

        tvDistance = findViewById(R.id.tvDistance)
        tvSafetyStatus = findViewById(R.id.tvSafetyStatus)
        btnStartRun = findViewById(R.id.btnStartRun)
        btnSOS = findViewById(R.id.btnSOS)
        etContactNumber = findViewById(R.id.etContactNumber)

        loadContactNumber()
        
        findViewById<View>(R.id.btnBack).setOnClickListener {
            finish()
        }

        btnStartRun.setOnClickListener { toggleRunTracking() }
        
        btnSOS.setOnLongClickListener {
            triggerManualSOS()
            true
        }

        LocalBroadcastManager.getInstance(this).registerReceiver(
            safetyReceiver,
            IntentFilter().apply {
                addAction(SafetyMonitorService.ACTION_INACTIVITY_WARNING)
                addAction(SafetyMonitorService.ACTION_SOS_TRIGGERED)
            }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isBound) {
            unbindService(serviceConnection)
            isBound = false
        }
        uiHandler.removeCallbacks(uiRunnable)
        LocalBroadcastManager.getInstance(this).unregisterReceiver(safetyReceiver)
    }

    private fun toggleRunTracking() {
        if (isTracking) {
            stopRun()
        } else {
            if (etContactNumber.text.isNullOrEmpty()) {
                Toast.makeText(this, "Please enter an emergency contact first", Toast.LENGTH_SHORT).show()
                return
            }
            saveContactNumber()
            checkPermissionsAndStart()
        }
    }

    private fun checkPermissionsAndStart() {
        val permissions = mutableListOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.SEND_SMS
        )
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            permissions.add(Manifest.permission.POST_NOTIFICATIONS)
        }

        val missing = permissions.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }

        if (missing.isEmpty()) {
            startServiceTracking()
        } else {
            requestPermissionLauncher.launch(missing.toTypedArray())
        }
    }

    private fun startServiceTracking() {
        val intent = Intent(this, SafetyMonitorService::class.java).apply {
            action = "START_TRACKING"
        }
        startService(intent)
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        
        isTracking = true
        btnStartRun.text = "Stop Run"
        btnStartRun.backgroundTintList = ContextCompat.getColorStateList(this, android.R.color.holo_red_dark)
        tvSafetyStatus.text = "Safety Ghost: 👻 Active"
        tvSafetyStatus.setTextColor(ContextCompat.getColor(this, R.color.purple_500))
    }

    private fun stopRun() {
        if (isBound) {
            unbindService(serviceConnection)
            isBound = false
        }
        val intent = Intent(this, SafetyMonitorService::class.java).apply {
            action = "STOP_TRACKING"
        }
        startService(intent) // Deliver stop command
        
        isTracking = false
        btnStartRun.text = "Start Run"
        btnStartRun.backgroundTintList = ContextCompat.getColorStateList(this, android.R.color.holo_green_dark)
        tvSafetyStatus.text = "Safety Ghost: Inactive"
        tvSafetyStatus.setTextColor(ContextCompat.getColor(this, android.R.color.darker_gray))
    }

    private fun updateDistanceUI(distanceMeters: Double) {
        tvDistance.text = "Distance: ${String.format("%.2f", distanceMeters / 1000)} km"
    }

    private fun showSafetyCheckDialog() {
        val dialogView = layoutInflater.inflate(android.R.layout.simple_list_item_1, null) // Simpler for now
        
        val dialog = AlertDialog.Builder(this)
            .setTitle("Safety Check ⚠️")
            .setMessage("No movement detected. Are you safe? SOS will be sent in 30s.")
            .setPositiveButton("I'm Safe") { d, _ -> d.dismiss() }
            .setCancelable(false)
            .create()

        dialog.show()

        object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                if (dialog.isShowing) {
                    dialog.setMessage("No movement detected. Are you safe? SOS will be sent in ${millisUntilFinished / 1000}s.")
                } else {
                    cancel()
                }
            }

            override fun onFinish() {
                if (dialog.isShowing) {
                    dialog.dismiss()
                    triggerAutomatedSOS("Emergency: User inactivity detected during a run.")
                }
            }
        }.start()
    }

    private fun triggerManualSOS() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            checkPermissionsAndStart()
            return
        }

        val contact = etContactNumber.text.toString()
        if (contact.isEmpty()) {
            Toast.makeText(this, "Please enter a contact number", Toast.LENGTH_SHORT).show()
            return
        }

        Toast.makeText(this, "Acquiring location & transmitting SOS...", Toast.LENGTH_SHORT).show()

        // direct fetch location to ensure it's not "Pending"
        try {
           val fusedLocationClient = com.google.android.gms.location.LocationServices.getFusedLocationProviderClient(this)
           fusedLocationClient.lastLocation.addOnSuccessListener { location ->
               val locationUrl = if (location != null) 
                   "http://maps.google.com/maps?q=${location.latitude},${location.longitude}"
               else "Location unavailable (GPS weak)"
               
               sendDirectSMS(contact, "SOS! Manual Emergency Alert.\nLocation: $locationUrl")
           }.addOnFailureListener {
               sendDirectSMS(contact, "SOS! Manual Emergency Alert.\nLocation: Unknown")
           }
        } catch (e: SecurityException) {
             sendDirectSMS(contact, "SOS! Manual Emergency Alert.\nLocation: Permission Denied")
        }
    }

    private fun sendDirectSMS(phone: String, message: String) {
        try {
            val smsManager = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                getSystemService(android.telephony.SmsManager::class.java)
            } else {
                android.telephony.SmsManager.getDefault()
            }
            smsManager.sendTextMessage(phone, null, message, null, null)
            
            tvSafetyStatus.text = "Status: SOS SENT 🚨"
            tvSafetyStatus.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
            Toast.makeText(this, "SOS Message Sent!", Toast.LENGTH_LONG).show()
            
        } catch (e: Exception) {
            Toast.makeText(this, "SMS Failed: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun triggerAutomatedSOS(reason: String) {
        val contact = etContactNumber.text.toString()
        if (contact.isNotEmpty() && isBound) {
            safetyService?.sendSOS(contact, reason)
        }
    }

    private fun saveContactNumber() {
        val prefs = getSharedPreferences("SafetyPrefs", Context.MODE_PRIVATE)
        prefs.edit().putString("contact_number", etContactNumber.text.toString()).apply()
    }

    private fun loadContactNumber() {
        val prefs = getSharedPreferences("SafetyPrefs", Context.MODE_PRIVATE)
        etContactNumber.setText(prefs.getString("contact_number", ""))
    }
}
