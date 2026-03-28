package com.example.swasthyamitra.fragments

// Android framework imports for permissions, system services, and UI components
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
// App-specific imports for resources, binding, and tracking services
import com.example.swasthyamitra.R
import com.example.swasthyamitra.databinding.FragmentLiveMapBinding
import com.example.swasthyamitra.services.TrackingService
import com.example.swasthyamitra.services.TrackingService.Companion.ACTION_TRIGGER_SOS
import com.example.swasthyamitra.services.TrackingService.Companion.isTrackingLive
import com.example.swasthyamitra.services.TrackingService.Companion.pathPointsLive
import com.example.swasthyamitra.services.TrackingService.Companion.distanceLive
import com.example.swasthyamitra.services.TrackingService.Companion.paceLive
import com.example.swasthyamitra.services.TrackingService.Companion.stepsLive
// Google Maps SDK imports for map functionality and location visualization
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions

// Fragment for displaying live GPS tracking with Google Maps integration and real-time metrics
class LiveMapFragment : Fragment(), OnMapReadyCallback {

    // View binding for type-safe access to layout elements
    private var _binding: FragmentLiveMapBinding? = null
    private val binding get() = _binding!! // Non-null assertion for binding lifecycle
    // Google Maps instance for displaying location, route, and tracking visualization
    private var googleMap: GoogleMap? = null

    // Fragment lifecycle method - create and return the view hierarchy
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate layout using view binding for type-safe UI access
        _binding = FragmentLiveMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Fragment lifecycle method - called after view is created, setup map and listeners
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize Google Maps fragment and request map instance asynchronously
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment?
        mapFragment?.getMapAsync(this) // Callback will be onMapReady

        // Setup button click listeners for tracking controls
        setupListeners()
        // Setup observers for live tracking data from TrackingService
        observeData()
    }

    private fun setupListeners() {
        binding.btnStopTracking.setOnClickListener {
            val intent = Intent(requireContext(), TrackingService::class.java).apply {
                action = "ACTION_STOP"
            }
            requireContext().startService(intent)
            requireActivity().finish()
        }

        binding.btnMapSOS.setOnClickListener {
            val intent = Intent(requireContext(), TrackingService::class.java).apply {
                action = ACTION_TRIGGER_SOS
                putExtra("reason", "Manual SOS from Map")
            }
            requireContext().startService(intent)
        }
    }

    private fun observeData() {
        isTrackingLive.observe(viewLifecycleOwner) { isTracking ->
            binding.btnStopTracking.visibility = if (isTracking) View.VISIBLE else View.GONE
        }

        pathPointsLive.observe(viewLifecycleOwner) { points ->
            drawPolyline(points)
            if (points.isNotEmpty()) {
                googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(points.last(), 16f))
            }
        }

        distanceLive.observe(viewLifecycleOwner) { distance ->
            binding.tvDistance.text = String.format("%.2f", distance / 1000.0)
        }

        paceLive.observe(viewLifecycleOwner) { pace ->
            binding.tvPace.text = pace
        }

        stepsLive.observe(viewLifecycleOwner) { steps ->
            binding.tvSteps.text = steps.toString()
        }
    }

    private fun drawPolyline(points: List<LatLng>) {
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
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            googleMap?.isMyLocationEnabled = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
