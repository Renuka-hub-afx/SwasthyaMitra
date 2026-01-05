# Critical Setup Instructions - Day 1-2

## ✅ STEP 1: Apply Firebase Security Rules (CRITICAL - 5 minutes)

**This is blocking all food logging functionality!**

### Instructions:

1. **Open Firebase Console**
   - Go to https://console.firebase.google.com/
   - Select your project: **SwasthyaMitra**

2. **Navigate to Firestore Database**
   - Click on **Build** in left sidebar
   - Click on **Firestore Database**
   - Click on **Rules** tab at the top

3. **Copy Security Rules**
   - Open the file: `FIREBASE_SECURITY_RULES.md` (in your project root)
   - Copy the complete rules from that file (starting with `rules_version = '2';`)

4. **Paste and Publish**
   - Delete all existing rules in the Firebase Console editor
   - Paste the new rules
   - Click **Publish** button
   - Wait 1-2 minutes for rules to propagate

5. **Verify Rules Applied**
   - You should see rules for collections: `foodLogs`, `users`, `goals`, `workouts`
   - Each rule should have authentication checks (`request.auth != null`)
   - Each rule should have ownership validation (`request.auth.uid == userId`)

### Expected Result:
✅ Food logging should work immediately after rules are applied
✅ No more "PERMISSION_DENIED" errors
✅ Users can only read/write their own data

---

## ✅ STEP 2: Add Indian Food Database Excel File (HIGH PRIORITY - 10 minutes)

**Current Status:** App uses fallback data (only 10 foods)
**Target:** 1000+ Indian food items with accurate nutrition data

### Option A: Use Sample Dataset (Quick Test)

I'll create a sample Excel file for you to test with:

1. Create a new Excel file named: `Indian_Food_Nutrition_Processed.xlsx`
2. Add these columns: `Food Name`, `Serving Size`, `Calories`, `Protein (g)`, `Carbs (g)`, `Fat (g)`, `Fiber (g)`, `Category`
3. Add some sample Indian foods (10-20 rows for testing)
4. Save the file

### Option B: Use Complete Dataset (Recommended)

**Where to find Indian food nutrition database:**

1. **IFCT 2017 (Indian Food Composition Tables)**
   - Source: National Institute of Nutrition, Hyderabad
   - Official Indian Government data
   - Download from: https://www.nin.res.in/nutrition2020/
   - Contains 500+ Indian foods with detailed nutrition

2. **USDA FoodData Central (includes Indian foods)**
   - Source: https://fdc.nal.usda.gov/
   - Search for "Indian" to filter
   - Export as Excel/CSV

3. **MyFitnessPal Database Export**
   - Many Indian foods with crowdsourced data
   - Can be exported via API or manual collection

### File Format Required:

```
| Food Name          | Serving Size | Calories | Protein (g) | Carbs (g) | Fat (g) | Fiber (g) | Category    |
|-------------------|--------------|----------|-------------|-----------|---------|-----------|-------------|
| Roti (Whole Wheat)| 1 medium     | 71       | 3.0         | 15.0      | 0.4     | 2.7       | Grains      |
| Dal Tadka         | 1 cup        | 184      | 9.0         | 30.0      | 3.0     | 8.0       | Lentils     |
| Chicken Curry     | 1 cup        | 250      | 28.0        | 12.0      | 10.0    | 2.0       | Non-Veg     |
```

### Add File to Project:

1. **Copy Excel file** to: `SwasthyaMitra/app/src/main/assets/`
2. **File name must be:** `Indian_Food_Nutrition_Processed.xlsx`
3. **Rebuild project:** In Android Studio, click Build → Rebuild Project
4. **Test:** Open food search, type "roti" - should see database results

### Verify File Loaded:

Open `FoodLogActivity` and search for any Indian food name. If file is loaded correctly:
- Search results will show 10+ items (not just fallback data)
- Categories will display correctly
- All nutrition values will be populated

---

## ✅ STEP 3: Testing Checklist (After Steps 1-2 Complete)

### Test Food Logging End-to-End:

**Test Case 1: Manual Food Entry**
1. Open app → Homepage → Click "Add Food"
2. Search for "roti" or "dal"
3. Select a food from results
4. Enter serving size: 1
5. Select meal type: Lunch
6. Click "Add Food Log"
7. ✅ Should see success message
8. ✅ Should return to homepage with updated calories

**Test Case 2: Barcode Scanning**
1. Homepage → Click "Scan Barcode"
2. Scan any packaged food barcode
3. Review auto-filled nutrition data
4. Select meal type
5. Click "Add Food Log"
6. ✅ Should save successfully
7. ✅ Homepage should update

**Test Case 3: View Food History**
1. Open FoodLogActivity
2. Scroll down to "Today's Food History" section
3. ✅ Should see all logged foods for today
4. ✅ Should see total nutrition summary
5. Try deleting a food entry
6. ✅ Should remove from list and update totals

**Test Case 4: Search Both Sources**
1. FoodLogActivity → Search for "paneer"
2. ✅ Should see results from Indian DB (with "India" label)
3. Search for "pizza"
4. ✅ Should see results from OpenFoodFacts API (with "Global" label)

---

## ✅ STEP 4: New Activities Testing

### Test Progress Activity:
1. Homepage → Click "Progress" button
2. ✅ Should see weekly statistics
3. ✅ Tabs: Charts, History, Achievements
4. ✅ Shows weekly calories and workouts

### Test Profile Activity:
1. Homepage → Click "Profile" button (add button if missing)
2. ✅ Should see user info (name, email, age, gender, height, weight)
3. ✅ Should see calculated BMI
4. ✅ Should see goals (goal weight, daily calorie target)
5. Click "Logout" button
6. ✅ Should return to login screen

---

## 🎯 Success Criteria for Day 1-2:

- [x] Firebase rules applied and working
- [x] Indian food database file added and loaded
- [x] Food logging works (manual, barcode, photo)
- [x] No unused imports in code
- [x] ProgressActivity created and accessible
- [x] ProfileActivity created and accessible
- [x] All activities registered in AndroidManifest
- [x] User can view food history
- [x] User can see nutrition summary
- [x] User can logout from profile

---

## 🚨 Common Issues & Solutions:

**Issue:** "PERMISSION_DENIED" error when adding food
**Solution:** Apply Firebase Security Rules (Step 1)

**Issue:** "No foods found" when searching Indian foods
**Solution:** Add Excel file to assets folder (Step 2)

**Issue:** App crashes when opening Progress/Profile
**Solution:** Check AndroidManifest.xml has both activities registered

**Issue:** Barcode scanner doesn't open camera
**Solution:** Grant camera permission in Android Settings → Apps → SwasthyaMitra → Permissions

**Issue:** Food history shows "0 kcal" even after logging foods
**Solution:** Verify Firebase rules applied and data is being written to Firestore

---

## 📋 Next Steps After Day 1-2:

Once all tests pass, proceed to **Day 3-4: Exercise Tracking**
- Implement WorkoutActivity for manual workout logging
- Add step counter service
- Calculate calories burned
- Connect workout data to homepage

