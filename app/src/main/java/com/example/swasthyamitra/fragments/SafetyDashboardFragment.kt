package com.example.swasthyamitra.fragments

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
import com.bumptech.glide.Glide
import com.example.swasthyamitra.R
import com.example.swasthyamitra.databinding.FragmentSafetyDashboardBinding
import com.example.swasthyamitra.safety.EmergencyContactManager
import com.example.swasthyamitra.services.TrackingService
import com.example.swasthyamitra.services.TrackingService.Companion.ACTION_TRIGGER_SOS

class SafetyDashboardFragment : Fragment() {

    private var _binding: FragmentSafetyDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var contactManager: EmergencyContactManager

    private val handler = Handler(Looper.getMainLooper())
    private var sosHoldStartTime = 0L
    private val SOS_HOLD_DURATION = 3000L // 3 seconds

    private val sosHoldRunnable = object : Runnable {
        override fun run() {
            val elapsed = System.currentTimeMillis() - sosHoldStartTime
            if (elapsed < SOS_HOLD_DURATION) {
                binding.pbSOSHold.progress = elapsed.toInt()
                handler.postDelayed(this, 50)
            } else {
                binding.pbSOSHold.progress = 3000
                triggerSOS()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSafetyDashboardBinding.inflate(inflater, container, false)
        return binding.root
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
        TrackingService.isGhostModeLive.observe(viewLifecycleOwner) { active ->
            binding.tvGhostStatus.text = if (active) "ACTIVE" else "INACTIVE"
            binding.tvGhostStatus.setTextColor(
                if (active) resources.getColor(R.color.purple_500, null) 
                else resources.getColor(android.R.color.darker_gray, null)
            )
        }

        TrackingService.isSOSActiveLive.observe(viewLifecycleOwner) { sosActive ->
            if (sosActive) {
                binding.btnSOS.text = "SOS\nACTIVE"
                binding.btnSOS.setBackgroundColor(resources.getColor(android.R.color.black, null))
            } else {
                binding.btnSOS.text = "SOS"
                binding.btnSOS.setBackgroundColor(resources.getColor(R.color.purple_500, null)) 
                // Wait, SOS is red in layout, so I'll stick to that or logic:
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
