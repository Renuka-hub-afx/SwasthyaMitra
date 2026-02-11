# AI Smart Diet - Button Testing Checklist

## Pre-Testing Setup
- [ ] Ensure Firebase is connected and rules are deployed
- [ ] Open Logcat and filter for `AI_DIET_ACTIVITY` tag
- [ ] Clear app data (optional - for fresh start)
- [ ] Login to the app

---

## Test 1: Generate Individual Meals
### Steps:
1. [ ] Tap "Generate Breakfast" button
2. [ ] Verify breakfast name and calories appear
3. [ ] Verify action buttons (Ate, Skip, New) appear below
4. [ ] Repeat for Lunch, Dinner, and Snack

### Expected Results:
- Each meal generates successfully
- Different meals for each type
- Action buttons visible after generation

---

## Test 2: "Ate" Button Functionality

### Steps:
1. [ ] Generate a Breakfast meal
2. [ ] Note the meal name (e.g., "Idli Sambar")
3. [ ] Tap "Ate" button
4. [ ] Check for success toast: "✅ Logged: [Meal Name]"
5. [ ] Open Food Log Activity from homepage
6. [ ] Verify meal appears in today's logs

### Expected Logcat Output:
```
🎯 handleMealAction called: mealType=Breakfast, action=Ate
📝 Tracking feedback...
✅ Feedback tracked successfully
🍽️ Processing 'Ate' action - logging meal to food diary
📊 Logging meal to foodLogs: [Meal Name]
✅ SUCCESS: Meal logged to foodLogs with ID: [docId]
```

### Firebase Verification:
- [ ] Open Firebase Console → Firestore
- [ ] Check `foodLogs` collection for new entry
- [ ] Verify userId, foodName, calories, protein are correct
- [ ] Check `meal_feedback` collection for "Ate" action
- [ ] Check `user_preferences` → favoriteFoods array

---

## Test 3: "Skip" Button Functionality

### Steps:
1. [ ] Generate a Lunch meal
2. [ ] Note the meal name (e.g., "Paneer Butter Masala")
3. [ ] Tap "Skip" button
4. [ ] Check for toast: "⏭️ Skipped: [Meal Name]. Generating new..."
5. [ ] Wait for new meal to auto-generate
6. [ ] Verify NEW meal is DIFFERENT from skipped one
7. [ ] Tap "Skip" on new meal again
8. [ ] Verify third meal is different from both previous ones

### Expected Logcat Output:
```
🎯 handleMealAction called: mealType=Lunch, action=Skipped
📝 Tracking feedback...
✅ Feedback tracked successfully
⏭️ Processing 'Skipped' action - adding to disliked foods
📝 Adding '[Meal Name]' to disliked foods for user: [userId]
✅ SUCCESS: Added '[Meal Name]' to disliked foods. Total: X
🔄 Regenerating meal after skip...
🔄 regenerateMeal called: mealType=Lunch, currentItem=[Meal Name]
🔄 Generating new Lunch (excluding: [Meal Name])
🚫 Total exclusions (X): [list of foods]
✅ New meal generated: [New Meal Name]
```

### Firebase Verification:
- [ ] Open Firebase Console → Firestore
- [ ] Go to `user_preferences` → [your userId]
- [ ] Check `dislikedFoods` array contains skipped meal
- [ ] Verify `meal_feedback` shows "Skipped" action
- [ ] Confirm skipped meal does NOT appear in foodLogs

---

## Test 4: "New" Button Functionality

### Steps:
1. [ ] Generate a Dinner meal
2. [ ] Note the meal name (e.g., "Roti with Dal")
3. [ ] Tap "New" button (do NOT tap Skip)
4. [ ] Check for toast: "🔁 New Dinner: [New Meal Name]"
5. [ ] Verify new meal is different
6. [ ] Tap "New" button 2-3 more times
7. [ ] Verify each meal is unique

### Expected Logcat Output:
```
🔄 regenerateMeal called: mealType=Dinner, currentItem=[Current Meal]
🎲 Generating new Dinner (excluding: [Current Meal])
🚫 User's disliked foods: [previously skipped foods]
🚫 Total exclusions (X): [current + all disliked]
✅ New meal generated: [New Meal Name]
```

### Firebase Verification:
- [ ] Check `meal_feedback` for "New" actions
- [ ] Verify current meal is NOT added to dislikedFoods
- [ ] Confirm new meals avoid all previously disliked foods

---

## Test 5: Disliked Foods Persistence

### Steps:
1. [ ] Skip 3-4 different meals across different meal types
2. [ ] Force close the app
3. [ ] Reopen the app and login
4. [ ] Generate new meals for each type
5. [ ] Verify NONE of the previously skipped meals appear
6. [ ] Try generating multiple times - skipped meals should NEVER return

### Expected Behavior:
- Disliked foods persist across app sessions
- AI never suggests disliked foods again
- Disliked list grows over time

### Firebase Verification:
- [ ] Check `user_preferences` → dislikedFoods
- [ ] Verify all skipped meals are in the array
- [ ] Check `lastUpdated` timestamp is recent

---

## Test 6: Food Log Retention (7 Days)

### Steps:
1. [ ] Tap "Ate" for multiple meals
2. [ ] Open Food Log Activity
3. [ ] Verify all eaten meals appear
4. [ ] Check calories, protein values are correct
5. [ ] Verify meal type (Breakfast/Lunch/Dinner/Snack) is shown

### Expected Results:
- All "Ate" meals appear in Food Log
- Data persists for at least 7 days
- Can view by date and meal type

---

## Test 7: Combined Actions

### Steps:
1. [ ] Generate Breakfast → Tap "Ate"
2. [ ] Generate Lunch → Tap "Skip" → Wait for new → Tap "Ate"
3. [ ] Generate Dinner → Tap "New" → Tap "New" → Tap "Ate"
4. [ ] Generate Snack → Tap "Skip" → Tap "Skip" → Tap "Ate"

### Expected Results:
- Only final "Ate" meals appear in Food Log
- All skipped meals are in dislikedFoods
- All actions tracked in meal_feedback

---

## Test 8: Error Handling

### Test Scenarios:
1. [ ] **No Internet**: Try generating meal offline
   - Should show timeout error after 30 seconds
   - Should use fallback meal
   
2. [ ] **Empty Meal**: Try action on null meal
   - Should show: "Please generate a meal first"
   
3. [ ] **Not Logged In**: Logout and try actions
   - Should show: "User not logged in"

---

## Test 9: AI Variety Check

### Steps:
1. [ ] Generate 10 different breakfast meals (using "New" button)
2. [ ] Record all meal names
3. [ ] Count unique meals

### Expected Results:
- At least 8-9 unique meals out of 10
- Good variety (not just 2-3 meals repeating)
- Different cooking styles and ingredients

---

## Test 10: Full Day Plan

### Steps:
1. [ ] Generate all 4 meals (Breakfast, Lunch, Dinner, Snack)
2. [ ] Tap "Ate" for each meal
3. [ ] Open Food Log Activity
4. [ ] Verify total calories for the day
5. [ ] Check all meals appear with correct times

### Expected Results:
- All 4 meals logged
- Total calories match user's daily target
- Meal types correctly labeled
- Timestamps show correct order

---

## Success Criteria

✅ **All Tests Pass If:**
1. Ate button logs to foodLogs ✅
2. Skip button adds to dislikedFoods + regenerates ✅
3. New button generates different meal ✅
4. Disliked foods never suggested again ✅
5. Food logs persist for 7+ days ✅
6. No crashes or errors ✅
7. Firebase data correctly stored ✅
8. All logging shows success messages ✅

---

## Common Issues & Solutions

### Issue: "Ate" button doesn't log
**Solution**: Check Logcat for error. Verify Firebase rules allow writing to foodLogs.

### Issue: Skip doesn't regenerate
**Solution**: Check handleMealAction() is calling regenerateMeal(). Check Logcat.

### Issue: Same meals keep appearing
**Solution**: Verify dislikedFoods is being saved in user_preferences. Check Firebase Console.

### Issue: Toast messages not showing
**Solution**: Ensure withContext(Dispatchers.Main) is used for UI updates.

---

## Final Verification

After all tests:
- [ ] Check Firebase Console → Firestore → All collections
- [ ] Review Logcat for any error messages
- [ ] Verify app performance (no lag or crashes)
- [ ] Test with different user accounts
- [ ] Test across multiple days

---

**Date Tested**: ___________________  
**Tester**: ___________________  
**Build Version**: ___________________  
**Result**: ☐ PASS  ☐ FAIL  

**Notes**:
_____________________________________________
_____________________________________________
_____________________________________________

