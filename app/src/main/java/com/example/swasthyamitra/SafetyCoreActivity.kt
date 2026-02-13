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

class SafetyCoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySafetyCoreBinding
    private lateinit var contactManager: EmergencyContactManager

    private val contactPickerLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val contactUri: Uri? = result.data?.data
            contactUri?.let { handleContactResult(it) }
        } else {
            // Check if we already have a contact, if not, we must force selection
            if (contactManager.getLocalContact() == null) {
                Toast.makeText(this, "Emergency contact is MANDATORY to proceed.", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

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
        
        // Setup countdown overlay
        TrackingService.countdownLive.observe(this) { count ->
            if (count >= 0) {
                binding.tvCountdownTimer.text = count.toString()
                binding.flCountdownOverlay.visibility = View.VISIBLE
            } else {
                binding.flCountdownOverlay.visibility = View.GONE
            }
        }

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

    private fun checkEmergencyContact() {
        if (contactManager.getLocalContact() == null) {
            launchContactPicker()
        }
    }

    private fun launchContactPicker() {
        val intent = Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
        contactPickerLauncher.launch(intent)
    }

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
                
                // Switch to dashboard if was on map and just set contact
                binding.viewPager.currentItem = 0
            }
        }
    }

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

    private fun updateIndicators(position: Int) {
        binding.dot1.setBackgroundResource(
            if (position == 0) R.drawable.indicator_dot_active else R.drawable.indicator_dot_inactive
        )
        binding.dot2.setBackgroundResource(
            if (position == 1) R.drawable.indicator_dot_active else R.drawable.indicator_dot_inactive
        )
    }
}
