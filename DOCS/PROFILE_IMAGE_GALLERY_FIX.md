# Profile Image Gallery Fix - Implementation Guide

## 🐛 **Problem Identified**

The gallery image selection works but is not being saved/displayed correctly because:

1. **URI Permission Issue**: The persistent URI permission is taken but not properly error-handled
2. **Homepage Not Loading Gallery Images**: The homepage only loads avatar drawables, not gallery URIs
3. **Missing AvatarManager Integration**: Homepage doesn't use AvatarManager to check for gallery images

---

## ✅ **Fixes Applied**

### 1. **AvatarCustomizationActivity.kt** - Enhanced Gallery Picker

**File**: `c:/Users/renuk/OneDrive/Desktop/project/SwasthyaMitra/app/src/main/java/com/example/swasthyamitra/AvatarCustomizationActivity.kt`

**Changes**:
- Added try-catch around `takePersistableUriPermission()` to handle SecurityException
- Added logging for debugging
- Added null check for when no image is selected

```kotlin
// Gallery Picker (Lines 87-113)
private val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
    if (uri != null) {
        Log.d("PhotoPicker", "Selected URI: $uri")
        
        try {
            // Take persistable URI permission
            val flag = android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
            contentResolver.takePersistableUriPermission(uri, flag)
            Log.d("PhotoPicker", "Persistable permission granted for: $uri")
        } catch (e: SecurityException) {
            Log.e("PhotoPicker", "Failed to take persistable permission: ${e.message}")
            // Continue anyway - the URI might still work in the current session
        }
        
        // Update State
        selectedGalleryUri = uri
        
        // UI Update
        showGalleryImage(uri)
        tvCategoryTitle.text = "From Gallery"
        selectCategoryUIOnly(catGallery, "Gallery")
        adapter.updateData(emptyList()) 
        
        addToHistory()
    } else {
        Log.d("PhotoPicker", "No image selected")
    }
}
```

---

### 2. **homepage.kt** - Added Gallery Image Support

**File**: `c:/Users/renuk/OneDrive/Desktop/project/SwasthyaMitra/app/src/main/java/com/example/swasthyamitra/homepage.kt`

**Changes Made**:

#### A. Added AvatarManager Variable (Line 84)
```kotlin
private lateinit var avatarManager: AvatarManager
```

#### B. Replaced Avatar Loading Logic (Lines 442-445)
**OLD CODE** (Lines 445-451 - REMOVED):
```kotlin
val avatarId = userData["selected_avatar_id"] as? String
if (avatarId != null) {
    val avatarResId = getAvatarDrawable(avatarId)
    if (avatarResId != 0) {
        findViewById<android.widget.ImageView>(R.id.iv_user_profile).setImageResource(avatarResId)
    }
}
```

**NEW CODE**:
```kotlin
// Load profile image from AvatarManager (supports both gallery and avatar)
loadProfileImage()
```

#### C. Added loadProfileImage() Function
**Location**: Add before the closing brace of the homepage class (before line 919)

```kotlin
/**
 * Load profile image from AvatarManager
 * Supports both gallery images and preset avatars
 */
private fun loadProfileImage() {
    try {
        // Initialize AvatarManager if not already initialized
        if (!::avatarManager.isInitialized) {
            avatarManager = AvatarManager(this)
        }
        
        val profileImageView = findViewById<android.widget.ImageView>(R.id.iv_user_profile)
        val mode = avatarManager.getProfileMode()
        val galleryUri = avatarManager.getGalleryUri()
        val avatarId = avatarManager.getAvatarId()
        
        Log.d("Homepage", "Loading profile image - Mode: $mode, GalleryUri: $galleryUri, AvatarId: $avatarId")
        
        when (mode) {
            ProfileMode.GALLERY_PHOTO -> {
                if (galleryUri != null) {
                    try {
                        profileImageView.setImageURI(galleryUri)
                        profileImageView.scaleType = android.widget.ImageView.ScaleType.CENTER_CROP
                        Log.d("Homepage", "Gallery image loaded successfully")
                    } catch (e: Exception) {
                        Log.e("Homepage", "Failed to load gallery image: ${e.message}")
                        // Fall back to default avatar
                        profileImageView.setImageResource(R.drawable.coach)
                    }
                } else {
                    Log.w("Homepage", "Gallery mode but no URI found, using default")
                    profileImageView.setImageResource(R.drawable.coach)
                }
            }
            ProfileMode.PRESET_AVATAR -> {
                if (avatarId != null) {
                    val avatarResId = getAvatarDrawable(avatarId)
                    if (avatarResId != 0) {
                        profileImageView.setImageResource(avatarResId)
                        profileImageView.scaleType = android.widget.ImageView.ScaleType.FIT_CENTER
                        Log.d("Homepage", "Avatar loaded successfully: $avatarId")
                    } else {
                        Log.w("Homepage", "Avatar ID not found: $avatarId, using default")
                        profileImageView.setImageResource(R.drawable.coach)
                    }
                } else {
                    Log.w("Homepage", "Avatar mode but no ID found, using default")
                    profileImageView.setImageResource(R.drawable.coach)
                }
            }
        }
    } catch (e: Exception) {
        Log.e("Homepage", "Error loading profile image: ${e.message}")
        // Set default image on error
        findViewById<android.widget.ImageView>(R.id.iv_user_profile).setImageResource(R.drawable.coach)
    }
}
```

#### D. Add to onResume() (Optional but Recommended)
**Location**: In the `onResume()` function (around line 432)

Add this line to refresh the profile image when returning to the homepage:
```kotlin
override fun onResume() {
    super.onResume()
    updateDateDisplay() // Existing
    loadProfileImage() // ADD THIS LINE
    if (userId.isNotEmpty()) {
        displayNutritionBreakdown()
        // ... rest of the code
    }
}
```

---

## 📋 **Manual Integration Steps**

Since automated file editing encountered issues, please manually apply these changes:

### Step 1: Update homepage.kt

1. **Add AvatarManager variable** (Line 84):
   - Find: `private val hydrationRepo = com.example.swasthyamitra.data.repository.HydrationRepository()`
   - Add after it: `private lateinit var avatarManager: AvatarManager`

2. **Replace avatar loading logic** (Lines 445-451):
   - Find the block starting with `val avatarId = userData["selected_avatar_id"]`
   - Replace entire block with: `loadProfileImage()`

3. **Add loadProfileImage() function** (Before line 919):
   - Copy the entire `loadProfileImage()` function from `PROFILE_IMAGE_FIX.kt`
   - Paste it before the closing brace `}` of the homepage class

4. **Update onResume()** (Line 432):
   - Add `loadProfileImage()` after `updateDateDisplay()`

### Step 2: Verify AvatarCustomizationActivity.kt

The gallery picker has already been updated with proper error handling. No manual changes needed.

---

## 🔍 **How It Works**

### Flow Diagram

```
User selects image from gallery
         ↓
AvatarCustomizationActivity receives URI
         ↓
Takes persistable URI permission (with error handling)
         ↓
Saves URI to SharedPreferences via AvatarManager
         ↓
Sets mode to GALLERY_PHOTO
         ↓
User clicks Save
         ↓
Returns to ProfileActivity/Homepage
         ↓
Homepage.onResume() calls loadProfileImage()
         ↓
AvatarManager checks mode
         ↓
If GALLERY_PHOTO: loads URI
If PRESET_AVATAR: loads drawable
         ↓
Image displayed in iv_user_profile
```

### Storage Mechanism

**SharedPreferences** (`avatar_prefs`):
- `gallery_uri`: String representation of the gallery image URI
- `avatar_id`: String ID of the preset avatar
- `profile_mode`: Enum (GALLERY_PHOTO or PRESET_AVATAR)

**Firestore** (`users/{userId}`):
- `selected_avatar_id`: Either "gallery_selection" or avatar resource name

---

## 🧪 **Testing Checklist**

### Test Case 1: Gallery Image Selection
- [ ] Open Profile → Click avatar → Select "Gallery"
- [ ] Choose an image from gallery
- [ ] Image appears in preview
- [ ] Click "Save"
- [ ] Return to homepage
- [ ] **Expected**: Gallery image appears in homepage profile picture

### Test Case 2: Avatar Selection
- [ ] Open Profile → Click avatar → Select "Avatar"
- [ ] Choose a preset avatar
- [ ] Avatar appears in preview
- [ ] Click "Save"
- [ ] Return to homepage
- [ ] **Expected**: Selected avatar appears in homepage profile picture

### Test Case 3: App Restart
- [ ] Select gallery image and save
- [ ] Close app completely
- [ ] Reopen app
- [ ] **Expected**: Gallery image still appears (persistent)

### Test Case 4: Switch Between Gallery and Avatar
- [ ] Select gallery image → Save
- [ ] Return to avatar customization
- [ ] Select preset avatar → Save
- [ ] **Expected**: Avatar replaces gallery image
- [ ] Select gallery again → Save
- [ ] **Expected**: Gallery image replaces avatar

### Test Case 5: Profile Activity
- [ ] Open ProfileActivity
- [ ] **Expected**: Same image as homepage (gallery or avatar)
- [ ] Change avatar in customization
- [ ] Return to ProfileActivity
- [ ] **Expected**: Updated image appears

---

## 🐛 **Troubleshooting**

### Issue: Gallery image not appearing after app restart

**Cause**: URI permission lost after app restart

**Solution**: The `takePersistableUriPermission()` should handle this, but if it fails:
1. Check Logcat for "Failed to take persistable permission"
2. Verify the photo picker is using `ActivityResultContracts.PickVisualMedia()` (not old Intent)
3. Ensure the URI is being saved correctly in SharedPreferences

### Issue: "Failed to load gallery image" in logs

**Cause**: URI no longer valid or permission revoked

**Solution**: 
1. The code already falls back to default avatar
2. User needs to re-select the image
3. Consider adding a "Refresh Image" option

### Issue: Avatar not loading on homepage

**Cause**: `loadProfileImage()` not being called

**Solution**:
1. Verify `loadProfileImage()` is called in `loadUserData()`
2. Verify `loadProfileImage()` is called in `onResume()`
3. Check Logcat for "Loading profile image" log

---

## 📝 **Code Files Modified**

1. ✅ `AvatarCustomizationActivity.kt` - Gallery picker error handling
2. ⏳ `homepage.kt` - Needs manual integration of `loadProfileImage()`
3. ✅ `AvatarManager.kt` - No changes needed (already supports both modes)
4. ✅ `ProfileActivity.kt` - Already has `updateAvatarDisplay()` working correctly

---

## 🚀 **Next Steps**

1. **Manually integrate** the `loadProfileImage()` function into `homepage.kt`
2. **Build and test** the application
3. **Verify** all test cases pass
4. **Check Logcat** for any errors during image loading
5. **Test on multiple devices** (different Android versions)

---

**Status**: ⏳ Awaiting Manual Integration
**Priority**: High
**Estimated Time**: 10-15 minutes

---

## 📚 **Additional Notes**

- The `AvatarManager` uses `SharedPreferences` for local storage (fast, persistent)
- Firestore sync is optional and happens in the background
- The system gracefully falls back to default avatar on any error
- All image loading is logged for easy debugging
- The solution supports both gallery images and preset avatars seamlessly

