package com.example.swasthyamitra
import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swasthyamitra.auth.FirebaseAuthHelper
import com.google.android.material.button.MaterialButton
import com.google.firebase.firestore.FirebaseFirestore

enum class AvatarCategory {
    HAIR, EYES, OUTFIT, EXTRAS
}

data class AvatarItem(
    val id: String,
    val resId: Int,
    val category: AvatarCategory
)

class AvatarCustomizationActivity : AppCompatActivity() {

    // Layered ImageView references
    private lateinit var ivBase: ImageView
    private lateinit var ivHair: ImageView
    private lateinit var ivEyes: ImageView
    private lateinit var ivOutfit: ImageView
    
    // Legacy single preview reference (optional, used for gallery fallback)
    // We can reuse one of the layers or hide others.
    
    private lateinit var recyclerItems: RecyclerView
    private lateinit var avatarManager: AvatarManager
    private lateinit var authHelper: FirebaseAuthHelper

    // Category Views
    private lateinit var catGallery: TextView
    private lateinit var catHair: TextView
    private lateinit var catEyes: TextView
    private lateinit var catOutfit: TextView
    private lateinit var catExtras: TextView
    private lateinit var tvCategoryTitle: TextView
    
    // Buttons
    private lateinit var btnZoom: ImageButton
    private lateinit var btnRotate: ImageButton

    // Current State
    private var selectedGalleryUri: Uri? = null
    
    // Default Configuration matching "Avatar 1" style
    private var currentHairRes = R.drawable.vector_hair_messy_brown
    private var currentEyesRes = R.drawable.vector_eyes_glasses
    private var currentOutfitRes = R.drawable.vector_outfit_kimono
    private var currentExtrasRes = 0 

    // Assets - Pointing to NEW Vector Drawables
    private val hairItems = listOf(
        AvatarItem("hair_messy", R.drawable.vector_hair_messy_brown, AvatarCategory.HAIR),
        AvatarItem("hair_long", R.drawable.vector_hair_long_black, AvatarCategory.HAIR)
    )
    private val eyesItems = listOf(
        AvatarItem("eyes_glasses", R.drawable.vector_eyes_glasses, AvatarCategory.EYES),
        AvatarItem("eyes_blue", R.drawable.vector_eyes_blue, AvatarCategory.EYES)
    )
    private val outfitItems = listOf(
        AvatarItem("outfit_kimono", R.drawable.vector_outfit_kimono, AvatarCategory.OUTFIT),
        AvatarItem("outfit_green", R.drawable.vector_outfit_green, AvatarCategory.OUTFIT)
    )
    
    // extras can be empty or reused
    private val extrasItems = listOf<AvatarItem>()

    private lateinit var adapter: AvatarAdapter
    
    // Gallery Picker
    private val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            Log.d("PhotoPicker", "Selected URI: $uri")
            val flag = android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
            contentResolver.takePersistableUriPermission(uri, flag)
            selectedGalleryUri = uri
            showGalleryImage(uri)
            tvCategoryTitle.text = "From Gallery"
            adapter.updateData(emptyList()) 
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avatar_customization)

        avatarManager = AvatarManager(this)
        authHelper = FirebaseAuthHelper(this)
        
        setupViews()
        setupRecyclerView()
        setupListeners() 
        
        // Initial Render
        updateLayers()
        
        // Select Hair by default
        selectCategory(catHair, "Hairstyles", hairItems)
    }

    private fun setupViews() {
        ivBase = findViewById(R.id.iv_base)
        ivHair = findViewById(R.id.iv_hair)
        ivEyes = findViewById(R.id.iv_eyes)
        ivOutfit = findViewById(R.id.iv_outfit)
        
        recyclerItems = findViewById(R.id.recycler_items)
        
        catGallery = findViewById(R.id.cat_gallery)
        catHair = findViewById(R.id.cat_hair)
        catEyes = findViewById(R.id.cat_eyes)
        catOutfit = findViewById(R.id.cat_outfit)
        catExtras = findViewById(R.id.cat_extras)
        
        tvCategoryTitle = findViewById(R.id.tv_category_title)
        btnZoom = findViewById(R.id.btn_zoom)
        btnRotate = findViewById(R.id.btn_rotate)
    }

    private fun setupRecyclerView() {
        recyclerItems.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        
        adapter = AvatarAdapter(emptyList()) { item ->
            applySelection(item)
        }
        recyclerItems.adapter = adapter
    }
    
    private fun applySelection(item: AvatarItem) {
        selectedGalleryUri = null // Switch back to avatar mode
        
        when (item.category) {
            AvatarCategory.HAIR -> currentHairRes = item.resId
            AvatarCategory.EYES -> currentEyesRes = item.resId
            AvatarCategory.OUTFIT -> currentOutfitRes = item.resId
            AvatarCategory.EXTRAS -> currentExtrasRes = item.resId
        }
        updateLayers()
    }
    
    private fun updateLayers() {
        ivBase.scaleType = ImageView.ScaleType.FIT_CENTER
        ivBase.imageTintList = null // Reset tint if any
        
        // Base Layer (Face/Skin) always visible
        ivBase.setImageResource(R.drawable.vector_base_girl)
        ivBase.visibility = View.VISIBLE
        
        // Stack other layers
        ivHair.setImageResource(currentHairRes)
        ivHair.visibility = View.VISIBLE
        
        ivEyes.setImageResource(currentEyesRes)
        ivEyes.visibility = View.VISIBLE
        
        ivOutfit.setImageResource(currentOutfitRes)
        ivOutfit.visibility = View.VISIBLE
    }
    
    private fun showGalleryImage(uri: Uri) {
        // Hide overlay layers
        ivHair.visibility = View.GONE
        ivEyes.visibility = View.GONE
        ivOutfit.visibility = View.GONE
        
        // Use Base layer as the main image holder
        ivBase.setImageURI(uri)
        ivBase.scaleType = ImageView.ScaleType.FIT_CENTER
        ivBase.visibility = View.VISIBLE
    }

    private fun setupListeners() {
        findViewById<View>(R.id.btn_back).setOnClickListener { finish() }
        
        findViewById<View>(R.id.btn_reset).setOnClickListener {
             currentHairRes = R.drawable.vector_hair_messy_brown
             currentEyesRes = R.drawable.vector_eyes_glasses
             currentOutfitRes = R.drawable.vector_outfit_kimono
             selectedGalleryUri = null
             updateLayers()
             Toast.makeText(this, "Reset to default", Toast.LENGTH_SHORT).show()
        }

        findViewById<MaterialButton>(R.id.btn_save).setOnClickListener {
            saveSelection()
        }
        
        catGallery.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            selectCategoryUIOnly(catGallery, "Gallery")
        }
        
        catHair.setOnClickListener { selectCategory(catHair, "Hairstyles", hairItems) }
        catEyes.setOnClickListener { selectCategory(catEyes, "Eyes", eyesItems) }
        catOutfit.setOnClickListener { selectCategory(catOutfit, "Outfits", outfitItems) }
        catExtras.setOnClickListener { selectCategory(catExtras, "Extras", extrasItems) }
        
        val simpleToast = { msg: String -> Toast.makeText(this, msg, Toast.LENGTH_SHORT).show() }
        findViewById<View>(R.id.btn_undo).setOnClickListener { simpleToast("Undo") }
        findViewById<View>(R.id.btn_redo).setOnClickListener { simpleToast("Redo") }
    }

    private fun selectCategory(selectedView: TextView, title: String, items: List<AvatarItem>) {
        selectCategoryUIOnly(selectedView, title)
        adapter.updateData(items)
    }
    
    private fun selectCategoryUIOnly(selectedView: TextView, title: String) {
        val allCats = listOf(catGallery, catHair, catEyes, catOutfit, catExtras)
        val inactiveColor = Color.parseColor("#757575")
        val activeColor = Color.parseColor("#D500F9")
        
        for (cat in allCats) {
             cat.setTextColor(inactiveColor)
             TextViewCompat.setCompoundDrawableTintList(cat, ColorStateList.valueOf(inactiveColor))
        }
        
        selectedView.setTextColor(activeColor)
        TextViewCompat.setCompoundDrawableTintList(selectedView, ColorStateList.valueOf(activeColor))
        
        tvCategoryTitle.text = title
    }

    private fun saveSelection() {
        val userId = authHelper.getCurrentUser()?.uid ?: return

        val updateMap = hashMapOf<String, Any>()

        if (selectedGalleryUri != null) {
            avatarManager.saveGalleryUri(selectedGalleryUri!!)
            updateMap["selected_avatar_id"] = "gallery_selection"
        } else {
            // Save composite config
            updateMap["selected_avatar_id"] = "custom_layered"
            updateMap["avatar_config"] = mapOf(
                "hair" to getResName(currentHairRes),
                "eyes" to getResName(currentEyesRes),
                "outfit" to getResName(currentOutfitRes)
            )
        }

        FirebaseFirestore.getInstance().collection("users").document(userId)
            .update(updateMap)
            .addOnSuccessListener {
                Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
    }
    
    private fun getResName(resId: Int): String {
        return try { resources.getResourceEntryName(resId) } catch (e: Exception) { "" }
    }
}
