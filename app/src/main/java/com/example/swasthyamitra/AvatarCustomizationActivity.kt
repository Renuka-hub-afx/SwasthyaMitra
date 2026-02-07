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
    AVATAR_FULL
}

data class AvatarItem(
    val id: String,
    val resId: Int,
    val category: AvatarCategory
)

class AvatarCustomizationActivity : AppCompatActivity() {

    // ImageViews
    private lateinit var ivBase: ImageView
    private lateinit var ivHair: ImageView
    private lateinit var ivEyes: ImageView
    private lateinit var ivOutfit: ImageView
    
    private lateinit var recyclerItems: RecyclerView
    private lateinit var avatarManager: AvatarManager
    private lateinit var authHelper: FirebaseAuthHelper

    // Category Views
    private lateinit var catGallery: TextView
    private lateinit var catAvatar: TextView
    private lateinit var tvCategoryTitle: TextView
    
    // Undo/Redo Buttons
    private lateinit var btnUndo: ImageButton
    private lateinit var btnRedo: ImageButton

    // State
    private var selectedGalleryUri: Uri? = null
    private var currentAvatarResId: Int = R.drawable.avatar1 
    
    // History
    data class AvatarState(val galleryUri: Uri?, val avatarResId: Int)
    private val historyStack = mutableListOf<AvatarState>()
    private var historyPointer = -1

    // Avatar Items List
    private val avatarItems by lazy {
        val list = mutableListOf<AvatarItem>()
        try {
            list.add(AvatarItem("avatar1", R.drawable.avatar1, AvatarCategory.AVATAR_FULL))
            list.add(AvatarItem("avatar2", R.drawable.avatar2, AvatarCategory.AVATAR_FULL))
            list.add(AvatarItem("avatar3", R.drawable.avatar3, AvatarCategory.AVATAR_FULL))
            list.add(AvatarItem("avatar4", R.drawable.avatar4, AvatarCategory.AVATAR_FULL))
            list.add(AvatarItem("avatar5", R.drawable.avatar5, AvatarCategory.AVATAR_FULL))
            list.add(AvatarItem("avatar6", R.drawable.avatar6, AvatarCategory.AVATAR_FULL))
            list.add(AvatarItem("avatar7", R.drawable.avatar7, AvatarCategory.AVATAR_FULL))
            list.add(AvatarItem("avatar8", R.drawable.avatar8, AvatarCategory.AVATAR_FULL))
            list.add(AvatarItem("avatar9", R.drawable.avatar9, AvatarCategory.AVATAR_FULL))
            list.add(AvatarItem("avatar10", R.drawable.avatar10, AvatarCategory.AVATAR_FULL))
            list.add(AvatarItem("avatar11", R.drawable.avatar11, AvatarCategory.AVATAR_FULL))
            list.add(AvatarItem("avatar12", R.drawable.avatar12, AvatarCategory.AVATAR_FULL))
            list.add(AvatarItem("avatar13", R.drawable.avatar13, AvatarCategory.AVATAR_FULL))
        } catch (e: Exception) {
            Log.e("Avatar", "Error loading avatar resources: ${e.message}")
        }
        list
    }

    private lateinit var adapter: AvatarAdapter
    
    // Gallery Picker
    private val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            Log.d("PhotoPicker", "Selected URI: $uri")
            val flag = android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
            contentResolver.takePersistableUriPermission(uri, flag)
            
            // Update State
            selectedGalleryUri = uri
            
            // UI Update
            showGalleryImage(uri)
            tvCategoryTitle.text = "From Gallery"
            selectCategoryUIOnly(catGallery, "Gallery")
            adapter.updateData(emptyList()) 
            
            addToHistory()
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
        
        // Initial History
        addToHistory()
        
        // Default to Avatar Mode
        clickAvatarCategory()
    }

    private fun setupViews() {
        ivBase = findViewById(R.id.iv_base)
        ivHair = findViewById(R.id.iv_hair)
        ivEyes = findViewById(R.id.iv_eyes)
        ivOutfit = findViewById(R.id.iv_outfit)
        
        recyclerItems = findViewById(R.id.recycler_items)
        
        catGallery = findViewById(R.id.cat_gallery)
        catAvatar = findViewById(R.id.cat_avatar)
        
        tvCategoryTitle = findViewById(R.id.tv_category_title)
        
        btnUndo = findViewById(R.id.btn_undo)
        btnRedo = findViewById(R.id.btn_redo)
    }

    private fun setupRecyclerView() {
        recyclerItems.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        
        adapter = AvatarAdapter(emptyList()) { item ->
            applySelection(item)
        }
        recyclerItems.adapter = adapter
    }
    
    private fun applySelection(item: AvatarItem) {
        if (item.resId != null) {
            selectedGalleryUri = null
            currentAvatarResId = item.resId
            showFullAvatar(currentAvatarResId)
            addToHistory()
        }
    }
    
    private fun addToHistory() {
        // Remove future states if we are in the middle
        while (historyStack.size > historyPointer + 1) {
            historyStack.removeAt(historyStack.lastIndex)
        }
        
        val newState = AvatarState(selectedGalleryUri, currentAvatarResId)
        
        // Don't add if identical to current top (optional optimization)
        if (historyStack.isNotEmpty() && historyStack.last() == newState) {
            return
        }
        
        historyStack.add(newState)
        historyPointer++
        updateUndoRedoButtons()
    }
    
    private fun undo() {
        if (historyPointer > 0) {
            historyPointer--
            restoreState(historyStack[historyPointer])
        }
    }
    
    private fun redo() {
        if (historyPointer < historyStack.size - 1) {
            historyPointer++
            restoreState(historyStack[historyPointer])
        }
    }
    
    private fun restoreState(state: AvatarState) {
        selectedGalleryUri = state.galleryUri
        currentAvatarResId = state.avatarResId
        
        if (selectedGalleryUri != null) {
            showGalleryImage(selectedGalleryUri!!)
            tvCategoryTitle.text = "From Gallery"
            selectCategoryUIOnly(catGallery, "Gallery")
            adapter.updateData(emptyList())
        } else {
            showFullAvatar(currentAvatarResId)
            tvCategoryTitle.text = "Choose Avatar"
            selectCategoryUIOnly(catAvatar, "Choose Avatar")
            adapter.updateData(avatarItems) 
        }
        updateUndoRedoButtons()
    }
    
    private fun updateUndoRedoButtons() {
        btnUndo.isEnabled = historyPointer > 0
        btnUndo.alpha = if (btnUndo.isEnabled) 1.0f else 0.5f
        
        btnRedo.isEnabled = historyPointer < historyStack.size - 1
        btnRedo.alpha = if (btnRedo.isEnabled) 1.0f else 0.5f
    }
    
    // Updated to show full avatar logic
    private fun showFullAvatar(resId: Int) {
        ivBase.scaleType = ImageView.ScaleType.FIT_CENTER
        ivBase.imageTintList = null
        
        // Show Full Avatar on Base Layer
        ivBase.setImageResource(resId)
        ivBase.visibility = View.VISIBLE
        
        // Hide Layers
        ivHair.visibility = View.GONE
        ivEyes.visibility = View.GONE
        ivOutfit.visibility = View.GONE
    }
    
    private fun showGalleryImage(uri: Uri) {
        // Hide Layers
        ivHair.visibility = View.GONE
        ivEyes.visibility = View.GONE
        ivOutfit.visibility = View.GONE
        
        // Show Image
        ivBase.setImageURI(uri)
        ivBase.scaleType = ImageView.ScaleType.FIT_CENTER
        ivBase.visibility = View.VISIBLE
    }

    private fun setupListeners() {
        findViewById<View>(R.id.btn_back).setOnClickListener { finish() }
        
        findViewById<View>(R.id.btn_reset).setOnClickListener {
             currentAvatarResId = R.drawable.avatar1
             selectedGalleryUri = null
             showFullAvatar(currentAvatarResId)
             addToHistory()
             Toast.makeText(this, "Reset to default", Toast.LENGTH_SHORT).show()
        }

        findViewById<MaterialButton>(R.id.btn_save).setOnClickListener {
            saveSelection()
        }
        
        catGallery.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
        
        catAvatar.setOnClickListener { 
            clickAvatarCategory()
        }
        
        btnUndo.setOnClickListener { undo() }
        btnRedo.setOnClickListener { redo() }
    }
    
    private fun clickAvatarCategory() {
        selectCategoryUIOnly(catAvatar, "Choose Avatar")
        adapter.updateData(avatarItems)
        // Ensure preview reflects current selection if switching back from gallery
        if (selectedGalleryUri == null) {
            showFullAvatar(currentAvatarResId)
        }
    }

    private fun selectCategoryUIOnly(selectedView: TextView, title: String) {
        val allCats = listOf(catGallery, catAvatar)
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
            // Save selection (using resource name as ID)
            val resName = getResName(currentAvatarResId)
            avatarManager.saveAvatarId(resName)
            updateMap["selected_avatar_id"] = resName
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
