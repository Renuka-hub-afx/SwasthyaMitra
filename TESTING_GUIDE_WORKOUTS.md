# Testing Guide: AI Workout Recommendation System

## Prerequisites
✅ Code compiled successfully
✅ All 14 workout videos added to repository
✅ AI logic implemented in WorkoutDashboardActivity

## Test Scenarios

### Scenario 1: Weight Loss + High Calories
**Setup:**
1. Set user goal to "Weight Loss" in profile
2. Log meals totaling >100 kcal above your daily target
   - Example: If target is 2000 kcal, log 2150+ kcal

**Expected Results:**
- ⚡ AI Message: "High calorie intake detected! We've selected intense HIIT workouts to maximize fat burn. Total: 45 min"
- **3 HIIT videos** displayed:
  - "15 Min Fat Burning HIIT"
  - "15 Min Standing HIIT"
  - "15 Min Full Body HIIT"

**Why:** High calorie surplus requires maximum intensity to create deficit

---

### Scenario 2: Weight Loss + Low Calories
**Setup:**
1. Keep goal as "Weight Loss"
2. Log meals totaling >100 kcal below your daily target
   - Example: If target is 2000 kcal, log 1850- kcal

**Expected Results:**
- 💪 AI Message: "Lower calorie intake - we've balanced cardio with moderate intensity to avoid burnout. Total: 45 min"
- **2 Cardio + 1 HIIT** videos:
  - "15 Min Cardio Fat Burn"
  - "15 Min Dance Cardio"
  - "15 Min Fat Burning HIIT"

**Why:** Low calories need gentler exercise to prevent metabolic slowdown

---

### Scenario 3: Weight Loss + Balanced Calories
**Setup:**
1. Keep goal as "Weight Loss"
2. Log meals within ±100 kcal of target
   - Example: If target is 2000 kcal, log 1900-2100 kcal

**Expected Results:**
- 🔥 AI Message: "Perfect balance! Your HIIT & cardio mix will optimize fat burning. Total: 45 min"
- **Mixed HIIT & Cardio**:
  - "15 Min Fat Burning HIIT"
  - "15 Min Cardio Fat Burn"
  - "15 Min Full Body HIIT"

**Why:** Balanced intake supports optimal fat-burning regime

---

### Scenario 4: Weight Gain + High Calories
**Setup:**
1. Change goal to "Weight Gain" in profile
2. Log meals >100 kcal above target
   - Example: If target is 2800 kcal, log 2950+ kcal

**Expected Results:**
- 💪 AI Message: "Excellent! High calories + strength training = optimal muscle growth. Total: 45 min"
- **3 Strength videos** (random order):
  - "15 Min Full Body Strength"
  - "15 Min Lower Body Strength"
  - "15 Min Upper Body Workout"
  - OR "15 Min No Equipment Strength"

**Why:** High calories provide perfect fuel for muscle building

---

### Scenario 5: Weight Gain + Low Calories
**Setup:**
1. Keep goal as "Weight Gain"
2. Log meals >100 kcal below target
   - Example: If target is 2800 kcal, log 2650- kcal

**Expected Results:**
- ⚠️ AI Message: "Low calories may limit gains. We've added lighter exercises - consider eating more. Total: 45 min"
- **2 Strength + 1 Yoga**:
  - "15 Min Full Body Strength"
  - "15 Min Lower Body Strength"
  - "15 Min Yoga Flow"

**Why:** Insufficient calories limit muscle growth - system suggests recovery

---

### Scenario 6: Maintenance + High Calories
**Setup:**
1. Change goal to "Maintain Weight"
2. Log meals >100 kcal above target

**Expected Results:**
- 🧘 AI Message: "Maintenance mode: We've added cardio to burn extra calories while staying balanced. Total: 45 min"
- **2 Cardio + 1 Maintenance**:
  - "15 Min Cardio Fat Burn"
  - "15 Min Dance Cardio"
  - "15 Min Yoga Flow"

**Why:** Extra calories need some expenditure without intensive training

---

### Scenario 7: Maintenance + Balanced Calories
**Setup:**
1. Keep goal as "Maintain Weight"
2. Log meals within ±100 kcal of target

**Expected Results:**
- ✨ AI Message: "Perfectly balanced! Your yoga & flexibility routine maintains wellness. Total: 45 min"
- **Balanced wellness**:
  - "15 Min Yoga Flow"
  - "15 Min Pilates Core"
  - "15 Min Stretching Routine"

**Why:** Perfect balance supports gentle wellness maintenance

---

## Video Playback Testing

### Test Video Launch
1. Click "Start" on any video
2. **Expected:** YouTube app or browser opens
3. **Expected:** Correct 15-minute workout video plays
4. **Expected:** Video matches the displayed title

### Test Complete Button
1. Click "Start" on a video
2. Return to app (don't close YouTube)
3. **Expected:** "Complete" button is now enabled
4. Click "Complete"
5. **Expected:** Success message appears
6. **Expected:** Workout logged to Firebase

---

## Edge Cases to Test

### Test 1: Empty Meal Log
- Don't log any meals
- **Expected:** System defaults to balanced recommendations

### Test 2: First-Time User
- New user with no history
- **Expected:** Gentle maintenance workouts suggested

### Test 3: Goal Change
- Complete a workout
- Change goal (Loss → Gain)
- Refresh workout dashboard
- **Expected:** New video recommendations appear immediately

### Test 4: Video Availability
- Test each of the 14 videos
- **Expected:** All videos play correctly
- **If unavailable:** Note which video ID and we'll replace it

---

## Quick Testing Checklist

- [ ] Weight Loss + High Calories → 3 HIIT videos
- [ ] Weight Loss + Low Calories → 2 Cardio + 1 HIIT
- [ ] Weight Loss + Balanced → Mixed HIIT & Cardio
- [ ] Weight Gain + High Calories → 3 Strength videos
- [ ] Weight Gain + Low Calories → 2 Strength + 1 Yoga
- [ ] Weight Gain + Balanced → 3 Strength videos
- [ ] Maintenance + High Calories → 2 Cardio + 1 Yoga
- [ ] Maintenance + Low Calories → 3 Gentle exercises
- [ ] Maintenance + Balanced → Yoga + Pilates + Flexibility
- [ ] All videos are 15 minutes
- [ ] Total duration shows "45 min"
- [ ] Personalized AI messages display correctly
- [ ] Videos play when "Start" clicked
- [ ] "Complete" button works after starting video

---

## How to Log Different Calorie Amounts

**To Test High Calories:**
```
Breakfast: Oatmeal (300 kcal)
Lunch: Chicken Rice Bowl (600 kcal)
Snack: Protein Shake (250 kcal)
Dinner: Salmon & Veggies (500 kcal)
Snack: Greek Yogurt (150 kcal)
Total: 1800 kcal (if target is 1600 = High)
```

**To Test Low Calories:**
```
Breakfast: Fruit Bowl (150 kcal)
Lunch: Salad (250 kcal)
Dinner: Grilled Chicken (350 kcal)
Total: 750 kcal (if target is 1600 = Low)
```

**To Test Balanced:**
```
Log exactly at or near your target calories
```

---

## Troubleshooting

**Problem:** AI message doesn't change
- **Solution:** Force-close app and reopen

**Problem:** Videos not updating
- **Solution:** Clean & Rebuild project

**Problem:** "Video Unavailable" error
- **Solution:** Let me know which video ID - I'll replace it

**Problem:** Wrong number of videos
- **Solution:** Check logs for filtering logic errors

---

## Performance Metrics

After testing, verify:
- ✅ App responds within 2 seconds of logging meals
- ✅ Video recommendations update immediately
- ✅ No lag when scrolling workout list
- ✅ Videos load within 3-5 seconds
- ✅ Navigation smooth between screens

---

## Next Steps After Testing

1. Report any issues found
2. Confirm all 14 videos work
3. Verify AI messages are helpful
4. Suggest any improvements
5. Ready for production deployment!
