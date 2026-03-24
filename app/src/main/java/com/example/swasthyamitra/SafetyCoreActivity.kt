package com.example.swasthyamitra

import android.Manifest
import android.content.*
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.swasthyamitra.databinding.ActivitySafetyCoreBinding
import com.example.swasthyamitra.fragments.LiveMapFragment
import com.example.swasthyamitra.fragments.SafetyDashboardFragment
import com.example.swasthyamitra.safety.EmergencyContact
import com.example.swasthyamitra.safety.EmergencyContactManager
import com.example.swasthyamitra.services.TrackingService
import com.example.swasthyamitra.services.TrackingService.Companion.ACTION_CANCEL_SOS
import com.example.swasthyamitra.services.TrackingService.Companion.ACTION_START_COUNTDOWN

/**
 * SafetyCoreActivity is the central hub for the app's security features.
 * It hosts the Safety Dashboard and Live Map, manages emergency contacts, 
 * and controls the "Panic Button" countdown overlay.
 */
class SafetyCoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySafetyCoreBinding
    private lateinit var contactManager: EmergencyContactManager

    /**
     * Launcher for the system contact picker. 
     * Ensures an emergency contact is selected and saved locally.
     */
    private val contactPickerLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val contactUri: Uri? = result.data?.data
            contactUri?.let { handleContactResult(it) }
        } else {
            // Enforcement: The safety features require a contact to function.
            if (contactManager.getLocalContact() == null) {
                Toast.makeText(this, "Emergency contact is MANDATORY to proceed.", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

    /**
     * Receiver for SOS state changes broadcast by the TrackingService.
     * Toggles the critical "Countdown" overlay visibility.
     */
    private val safetyReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                ACTION_START_COUNTDOWN -> {
                    binding.flCountdownOverlay.visibility = View.VISIBLE
                }
                ACTION_CANCEL_SOS -> {
                    binding.flCountdownOverlay.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySafetyCoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contactManager = EmergencyContactManager(this)

        setupViewPager()
        checkEmergencyContact()
        
        // ---- SOS COUNTDOWN SYNC ----
        // Observes the LiveData from TrackingService to show real-time seconds remaining 
        // before the SOS message is automatically dispatched.
        TrackingService.countdownLive.observe(this) { count ->
            if (count >= 0) {
                binding.tvCountdownTimer.text = count.toString()
                binding.flCountdownOverlay.visibility = View.VISIBLE
            } else {
                binding.flCountdownOverlay.visibility = View.GONE
            }
        }

        // Cancel button allows the user to stop a false alarm during the countdown.
        binding.btnCancelSOS.setOnClickListener {
            val intent = Intent(this, TrackingService::class.java).apply {
                action = ACTION_CANCEL_SOS
            }
            startService(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        val filter = IntentFilter().apply {
            addAction(ACTION_START_COUNTDOWN)
            addAction(ACTION_CANCEL_SOS)
        }
        androidx.core.content.ContextCompat.registerReceiver(
            this,
            safetyReceiver,
            filter,
            androidx.core.content.ContextCompat.RECEIVER_NOT_EXPORTED
        )
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(safetyReceiver)
    }

    /**
     * Checks if a contact exists; if not, triggers the picker.
     */
    private fun checkEmergencyContact() {
        if (contactManager.getLocalContact() == null) {
            launchContactPicker()
        }
    }

    /**
     * Opens the standard Android contact selector.
     */
    private fun launchContactPicker() {
        val intent = Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
        contactPickerLauncher.launch(intent)
    }

    /**
     * Extracts name, phone, and photo from the URI returned by the contact picker.
     */
    private fun handleContactResult(contactUri: Uri) {
        val projection = arrayOf(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone.PHOTO_URI
        )
        
        contentResolver.query(contactUri, projection, null, null, null)?.use { cursor ->
            if (cursor.moveToFirst()) {
                val name = cursor.getString(0)
                val number = cursor.getString(1)
                val photo = cursor.getString(2)
                
                val contact = EmergencyContact(name, number, photo)
                contactManager.saveContactLocally(contact)
                Toast.makeText(this, "Emergency Contact Set: $name", Toast.LENGTH_SHORT).show()
                
                // Return to dashboard after setting contact
                binding.viewPager.currentItem = 0
            }
        }
    }

    /**
     * Configures the ViewPager2 to switch between:
     * 1. Safety Dashboard (Controls/Trigger)
     * 2. Live Map (Location Tracking)
     */
    private fun setupViewPager() {
        val adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 2
            override fun createFragment(position: Int): Fragment {
                return if (position == 0) SafetyDashboardFragment() else LiveMapFragment()
            }
        }
        binding.viewPager.adapter = adapter
        
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateIndicators(position)
            }
        })
    }

    /**
     * Updates the custom dot indicators at the bottom to reflect the current page.
     */
    private fun updateIndicators(position: Int) {
        binding.dot1.setBackgroundResource(
            if (position == 0) R.drawable.indicator_dot_active else R.drawable.indicator_dot_inactive
        )
        binding.dot2.setBackgroundResource(
            if (position == 1) R.drawable.indicator_dot_active else R.drawable.indicator_dot_inactive
        )
    }
}

