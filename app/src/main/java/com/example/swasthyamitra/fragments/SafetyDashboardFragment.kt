package com.example.swasthyamitra.fragments

// Android framework imports for fragment lifecycle, UI, and system services
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
// Third-party library for efficient image loading
import com.bumptech.glide.Glide
// App-specific imports for resources, binding, and safety features
import com.example.swasthyamitra.R
import com.example.swasthyamitra.databinding.FragmentSafetyDashboardBinding
import com.example.swasthyamitra.safety.EmergencyContactManager
import com.example.swasthyamitra.services.TrackingService
import com.example.swasthyamitra.services.TrackingService.Companion.ACTION_TRIGGER_SOS

// Fragment for safety dashboard with emergency SOS button and contact management
class SafetyDashboardFragment : Fragment() {

    // View binding for type-safe access to layout elements
    private var _binding: FragmentSafetyDashboardBinding? = null
    private val binding get() = _binding!! // Non-null assertion since binding is valid between onCreateView and onDestroyView
    // Emergency contact management for adding/removing trusted contacts
    private lateinit var contactManager: EmergencyContactManager

    // Handler for managing SOS button hold timer on main UI thread
    private val handler = Handler(Looper.getMainLooper())
    // Timestamp when user started holding SOS button for duration tracking
    private var sosHoldStartTime = 0L
    // Required hold duration (3 seconds) to prevent accidental SOS activation
    private val SOS_HOLD_DURATION = 3000L // 3 seconds

    // Runnable for updating SOS button progress and triggering emergency after hold duration
    private val sosHoldRunnable = object : Runnable {
        override fun run() {
            // Calculate elapsed time since user started holding SOS button
            val elapsed = System.currentTimeMillis() - sosHoldStartTime
            if (elapsed < SOS_HOLD_DURATION) {
                // Update progress bar to show hold progress (visual feedback)
                binding.pbSOSHold.progress = elapsed.toInt()
                handler.postDelayed(this, 50) // Update every 50ms for smooth progress animation
            } else {
                // Hold duration completed - set progress to max and trigger SOS
                binding.pbSOSHold.progress = 3000
                triggerSOS() // Activate emergency protocol
            }
        }
    }

    // Fragment lifecycle method - create and return the view hierarchy for this fragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate layout using view binding for type-safe UI access
        _binding = FragmentSafetyDashboardBinding.inflate(inflater, container, false)
        return binding.root // Return root view of the inflated layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contactManager = EmergencyContactManager(requireContext())

        setupListeners()
        observeData()
        updateContactUi()
    }

    override fun onResume() {
        super.onResume()
        updateContactUi()
    }

    private fun updateContactUi() {
        val contact = contactManager.getLocalContact()
        if (contact != null) {
            binding.tvContactName.text = contact.name
            binding.tvContactPhone.text = contact.phoneNumber
            
            if (contact.photoUri != null) {
                Glide.with(this)
                    .load(Uri.parse(contact.photoUri))
                    .placeholder(R.drawable.ic_phone)
                    .into(binding.ivContactPhoto)
            } else {
                binding.ivContactPhoto.setImageResource(R.drawable.ic_phone)
            }
        }
    }

    private fun observeData() {
        TrackingService.isSOSActiveLive.observe(viewLifecycleOwner) { sosActive ->
            if (sosActive) {
                binding.btnSOS.text = "SOS\nACTIVE"
                binding.btnSOS.setBackgroundColor(resources.getColor(android.R.color.black, null))
            } else {
                binding.btnSOS.text = "SOS"
                binding.btnSOS.setBackgroundColor(resources.getColor(android.R.color.holo_red_dark, null))
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupListeners() {
        binding.btnSOS.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    startSOSHold()
                    true
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    cancelSOSHold()
                    true
                }
                else -> false
            }
        }
    }

    private fun startSOSHold() {
        sosHoldStartTime = System.currentTimeMillis()
        handler.post(sosHoldRunnable)
    }

    private fun cancelSOSHold() {
        handler.removeCallbacks(sosHoldRunnable)
        binding.pbSOSHold.progress = 0
    }

    private fun triggerSOS() {
        val intent = Intent(requireContext(), TrackingService::class.java).apply {
            action = ACTION_TRIGGER_SOS
            putExtra("reason", "Manual SOS - High Priority")
        }
        requireContext().startService(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
