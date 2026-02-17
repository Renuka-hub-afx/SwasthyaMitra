# SwasthyaMitra — Comprehensive Bug Audit Report

> **Scope:** All Activity/Fragment Kotlin files. Excludes previously-fixed items (exercise logging, sleep tracking, step tracking, UI redesign).

---

## CRITICAL (App crash, data loss, or major feature completely broken)

### 1. `logWeight()` silently wipes user profile data
- **File:** `auth/FirebaseAuthHelper.kt` — lines 573–576
- **Description:** `logWeight()` calls `updateUserPhysicalStats(userId, 0.0, weight, "", 0)` before the corrective `.update("weight", weight)`. `updateUserPhysicalStats` unconditionally writes `height: 0.0`, `gender: ""`, `age: 0` to the user document. Even though the next line patches the weight, the other three fields are already overwritten to zero/empty. **Every weight log wipes the user's height, gender, and age.**
- **Impact:** Complete user profile corruption on every weight entry.

### 2. `getStepDays()` hardcoded to return 0 — Stage 2 permanently locked
- **File:** `ui/EnhancedProgressDashboardActivity.kt` — lines 492–501
- **Description:** The function body is `return try { 0 } catch (e: Exception) { 0 }` with a comment "Simplified: return 0 for now". It is used (line 380) to determine if the user has achieved 10,000 steps on 7 days. Since it always returns 0, Stage 2 "Step Master" can **never** be unlocked, permanently blocking progress to stages 3–6.
- **Impact:** Entire gamification stage-unlock progression is broken.

### 3. Barcode scanner fires multiple times per frame — duplicate food logs
- **File:** `BarcodeScannerActivity.kt` — lines 136–148
- **Description:** `processBarcode()` is called by the CameraX image analyzer on **every frame** that detects a barcode. There is no debounce flag, cooldown, or `isProcessing` lock. Each call triggers a network API request to OpenFoodFacts and calls `saveFoodLog()`, resulting in multiple duplicate food entries and API calls within seconds.
- **Impact:** Duplicate data in `foodLogs`, incorrect calorie totals, excessive API usage.

### 4. XP double-counting in WorkoutDashboard
- **File:** `WorkoutDashboardActivity.kt` — lines 551 + 562–564
- **Description:** When completing an AI exercise: (a) line 551 directly writes `data.xp + 150` to RTDB, (b) line 563–564 calls `XPManager.awardXP(AI_EXERCISE)` which adds another +75 XP to the same RTDB node. This results in **+225 XP** per exercise when the intended award is either 150 or 75. The toast even says "+150 XP … | +75 XP!" confirming the double-write is intentional but the two systems conflict.
- **Impact:** Inflated XP, premature level-ups, gamification economy broken.

### 5. XP threshold mismatch: `GamificationActivity` vs `XPManager`
- **File:** `GamificationActivity.kt` line 211 vs `gamification/XPManager.kt`
- **Description:** `GamificationActivity.checkLevelUp()` uses `LEVEL_XP_THRESHOLD = 1000` (1000 XP per level). `XPManager` uses `XP_PER_LEVEL = 100` (100 XP per level). The two systems disagree by 10×. XPManager will show level-ups 10× faster than GamificationActivity recognizes them.
- **Impact:** Inconsistent level display across screens; confusing user experience.

### 6. `JoinChallengeActivity` is a complete stub
- **File:** `JoinChallengeActivity.kt` — all 19 lines
- **Description:** The entire activity is:
  ```kotlin
  class JoinChallengeActivity : AppCompatActivity() {
      override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          setContentView(R.layout.activity_join_challenge)
          findViewById<ImageButton>(R.id.btnBack).setOnClickListener { finish() }
      }
  }
  ```
  No code input, no Firebase query, no join logic. Users navigate here from the gamification screen but cannot do anything.
- **Impact:** Dead-end screen; social challenges feature is non-functional.

---

## HIGH (Major feature degraded or shows wrong data)

### 7. `CoachActivity` is entirely hardcoded — no real data
- **File:** `CoachActivity.kt` — lines 31, 78–87
- **Description:** `checkHydrationLogic(28, 6.0)` passes hardcoded temperature = 28°C and distance = 6.0 km. There is no Firebase data fetch, no GPS, no weather API. The hydration advice is always based on fictitious values.
- **Impact:** Coach feature provides misleading guidance.

### 8. `CoachActivity` sensor listener never unregistered
- **File:** `CoachActivity.kt` — line 40
- **Description:** `sensorManager.registerListener()` is called in `setupSensors()` but `onPause()` / `onDestroy()` are **not overridden** to call `sensorManager.unregisterListener()`. The accelerometer keeps running after the activity is backgrounded, draining battery.
- **Impact:** Battery drain; continued sensor callbacks to a paused/stopped activity.

### 9. `GamificationActivity.isRecent()` always returns `true`
- **File:** `GamificationActivity.kt` — lines 232–234
- **Description:** `private fun isRecent(date: String): Boolean { return true }`. The comeback-bonus container is shown to every user on every visit, regardless of the actual `lastStreakBreakDate` value.
- **Impact:** Misleading UI; comeback bonus always visible.

### 10. Stage 4 "Zen Master" auto-unlocks without mood data check
- **File:** `ui/EnhancedProgressDashboardActivity.kt` — lines 440–443
- **Description:** Stage 4 only checks `if (unlockedStages >= 3)` and immediately unlocks. It never queries the `mood_logs` collection to verify the user actually tracked 7 days of mood. Every other stage has a real data check except this one.
- **Impact:** Unearned stage unlock; gamification integrity compromised.

### 11. Dual Firestore instance split — step data vs. all other data
- **Files:** `services/UnifiedStepTrackingService.kt` line 104, `services/StepCounterService.kt` line 35, `step/FirebaseStepSync.kt` line 17, `StepSessionHistoryActivity.kt` line 34, `InsightsRepository.kt` line 133 — all use `FirebaseFirestore.getInstance()` (DEFAULT).  
  Every other feature uses `FirebaseFirestore.getInstance("renu")`.
- **Description:** Step data (`daily_steps`, `step_sessions`, `walking_sessions`) is written to the **default** Firestore instance while nutrition, exercise, sleep, mood, weight, hydration data is in the **"renu"** instance. These are separate databases. Any cross-feature query (e.g., homepage calorie balance including step burns, insights combining steps with food) may silently return no data or query the wrong DB.
- **Impact:** Steps may not appear in cross-feature dashboards; daily calorie balance could show 0 steps burned even if user walked.

### 12. `ManualExerciseActivity` bypasses XPManager for XP awards
- **File:** `ManualExerciseActivity.kt` — line 214
- **Description:** Directly writes `data.xp + 100` to RTDB without going through `XPManager`. This means no level-up check occurs, no level-up toast is shown, and the XP total can desync from the XPManager's own tracking.
- **Impact:** Inconsistent XP accounting; missed level-up notifications.

### 13. AI diet plan logs meals with `carbs: 0.0` and `fat: 0.0`
- **File:** `AISmartDietActivity.kt` — lines 169–170
- **Description:** `logMealToFoodLog()` hardcodes `"carbs" to 0.0` and `"fat" to 0.0` with comments "Not provided by AI, default to 0". The AI model (`AIDietPlanService`) likely returns these values in its response but they are not parsed/forwarded.
- **Impact:** Macro tracking on homepage nutrition breakdown is wrong for all AI-recommended meals. Only protein and calories are recorded.

### 14. `N8nConfig` contains hardcoded ngrok URL that expires
- **File:** `ai/N8nConfig.kt`
- **Description:** `BASE_URL = "https://disconsolate-arie-nondiscriminatively.ngrok-free.dev"`. Ngrok free-tier URLs expire after ~2 hours, breaking all n8n webhook AI calls when the tunnel dies.
- **Impact:** AI Coach, diet recommendations, and any n8n-dependent feature silently fail after the URL expires.

### 15. `MoodRecommendationActivity.fetchRecommendations()` is empty
- **File:** `MoodRecommendationActivity.kt` — lines 107–112
- **Description:** Function body only hides the loading and recommendations layouts. Comment says "AI Recommendations have been moved to Workout Dashboard". Users see mood history but no actionable recommendations.
- **Impact:** Feature advertised (recommendations section exists in layout) but never shows content.

### 16. Homepage nutrition macro targets are hardcoded
- **File:** `homepage.kt` — lines 697–703, 706–708, 716–718
- **Description:** Protein target = 120g, carbs = 200g, fat = 65g are **hardcoded literals** in `displayNutritionBreakdown()`. They do not come from the user's goal, profile, or calorie target. A 50kg woman and a 100kg man both see the same macro targets.
- **Impact:** Misleading progress indicators; macros don't align with individual goals.

---

## MEDIUM (Functional but suboptimal, potential data issues)

### 17. Homepage duplicate `updateDateDisplay()` + `loadUserData()` calls
- **File:** `homepage.kt` — lines 208–214
- **Description:** Both functions are called twice in sequence during `onCreate`:
  ```kotlin
  updateDateDisplay()
  loadUserData()
  
  updateDateDisplay()
  loadUserData()
  ```
  This fires two identical Firestore reads on every homepage open.
- **Impact:** Wasted network/Firestore reads; potential UI flicker.

### 18. `updateUserPhysicalStats()` is an all-or-nothing overwrite
- **File:** `auth/FirebaseAuthHelper.kt` — lines 105–130
- **Description:** The function always writes **all four fields** (height, weight, gender, age) even when updating just one. Any caller must pass the correct current values for every param or risk overwriting them. The `logWeight()` bug (#1) is a direct consequence of this design.
- **Impact:** Any future caller that passes defaults for fields it doesn't intend to change will cause data loss.

### 19. `GamificationActivity` creates unused Firestore instance
- **File:** `GamificationActivity.kt` — line ~108
- **Description:** `val firestore = FirebaseFirestore.getInstance("renu")` is assigned but never used in the activity. All gamification data flows through RTDB.
- **Impact:** Unnecessary Firestore initialization; minor memory waste; confusing code.

### 20. `SmartPantryActivity` uses deprecated `startActivityForResult`
- **File:** `SmartPantryActivity.kt`
- **Description:** Uses `startActivityForResult()` for camera/gallery intents. This API is deprecated since Android 11 (API 30). Should use `ActivityResultContracts`.
- **Impact:** Compiler warnings; will eventually be removed from the framework.

### 21. `SmartPantryActivity` camera intent only returns thumbnail bitmap
- **File:** `SmartPantryActivity.kt`
- **Description:** The `ACTION_IMAGE_CAPTURE` intent without `EXTRA_OUTPUT` URI returns only a small ~160×160 thumbnail from `data.extras["data"]`. This low-resolution image is passed to Gemini AI for ingredient recognition.
- **Impact:** Poor ingredient detection accuracy due to low image quality.

### 22. `HistoryActivity` only shows RTDB workout history
- **File:** `HistoryActivity.kt`
- **Description:** Reads only from `users/{uid}/workoutHistory` in Realtime Database. Firestore `exercise_logs` (from `ExerciseLogActivity`, `ManualExerciseActivity`) are not shown.
- **Impact:** Users who log exercises outside the AI workout flow see an incomplete history.

### 23. `WeightProgressActivity` uses system layout for RecyclerView
- **File:** `WeightProgressActivity.kt`
- **Description:** `WeightAdapter` inflates `android.R.layout.simple_list_item_2` — a generic Android system layout with two text lines. No custom styling or weight-specific formatting.
- **Impact:** Visually inconsistent with the rest of the app's redesigned UI.

### 24. `InsightsRepository.getStepsForDate()` uses default Firestore
- **File:** `InsightsRepository.kt` — line 133
- **Description:** Explicitly uses `FirebaseFirestore.getInstance()` (default) for `daily_steps`, while the rest of the repository uses the `"renu"` instance. This is intentional to match step services, but creates a dependency split.
- **Impact:** If step data is ever migrated to "renu", this query silently breaks.

### 25. Safety module (`EmergencyContactManager`, `SOSManager`) uses default Firestore
- **Files:** `safety/EmergencyContactManager.kt` line 17, `safety/SOSManager.kt` line 13
- **Description:** Both use `FirebaseFirestore.getInstance()` (default) instead of `"renu"`. Emergency contacts and SOS data are stored in a different database than the user profile.
- **Impact:** Safety data isolated from main app data; admin/support tools looking at "renu" won't find it.

### 26. `homepage.kt` Firestore fallback silently degrades
- **File:** `homepage.kt` — lines 111–115
- **Description:** If `FirebaseFirestore.getInstance("renu")` throws, it silently falls back to `FirebaseFirestore.getInstance()`. Since step data is in default and everything else is in "renu", this fallback would read step data but miss all food/exercise/sleep/mood data without any user-visible error.
- **Impact:** Silent data loss on homepage if "renu" initialization fails.

---

## LOW (Minor issues, code quality, cosmetic)

### 27. `AIDietPlanService` MealRec has protein as String but calories as Int
- **File:** `ai/AIDietPlanService.kt` (referenced in `AISmartDietActivity.kt` line 162)
- **Description:** `meal.protein` is a String (e.g. "15g") requiring `.replace("g","").toDoubleOrNull()` parsing at the call site. Other numeric fields are already parsed as numbers. Inconsistent data model.
- **Impact:** Fragile parsing; could break if AI returns "15 grams" instead of "15g".

### 28. `ChallengeSetupActivity` creates challenges but no discovery mechanism
- **File:** `ChallengeSetupActivity.kt` — 113 lines
- **Description:** Creates a challenge document in RTDB with a share code, but `JoinChallengeActivity` (bug #6) has no search/join logic. Created challenges are effectively orphaned.
- **Impact:** Challenge creation works but nobody can join.

### 29. `homepage.kt` `calculateCaloriesOut()` references `TrackingService.stepsLive`
- **File:** `homepage.kt`
- **Description:** References a static LiveData from the step tracking service. If the service isn't running (user hasn't started tracking), this returns 0 regardless of actual steps stored in Firestore.
- **Impact:** Calories burned may show 0 on homepage when step data exists in the database.

### 30. `LifestyleActivity` fallback values for weight/height
- **File:** `LifestyleActivity.kt` — lines 190–191, 279–280
- **Description:** Falls back to `weight = 70.0` and `height = 170.0` if profile data is missing. These are reasonable defaults but not personalized.
- **Impact:** Minor; BMI/calorie calculations slightly off for new users before profile setup.

---

## Summary Table

| # | Severity | File | Bug |
|---|----------|------|-----|
| 1 | **CRITICAL** | FirebaseAuthHelper.kt:573 | logWeight() wipes height/gender/age |
| 2 | **CRITICAL** | EnhancedProgressDashboardActivity.kt:492 | getStepDays() hardcoded to 0 |
| 3 | **CRITICAL** | BarcodeScannerActivity.kt:136 | No debounce — duplicate food logs |
| 4 | **CRITICAL** | WorkoutDashboardActivity.kt:551+563 | XP double-counted (+225 instead of intended) |
| 5 | **CRITICAL** | GamificationActivity.kt:211 vs XPManager.kt | XP threshold mismatch (1000 vs 100) |
| 6 | **CRITICAL** | JoinChallengeActivity.kt | Complete stub — no functionality |
| 7 | HIGH | CoachActivity.kt:31 | Entirely hardcoded data |
| 8 | HIGH | CoachActivity.kt:40 | Sensor listener never unregistered |
| 9 | HIGH | GamificationActivity.kt:232 | isRecent() always returns true |
| 10 | HIGH | EnhancedProgressDashboardActivity.kt:440 | Stage 4 unlocks without mood check |
| 11 | HIGH | Multiple step files | Dual Firestore instance split |
| 12 | HIGH | ManualExerciseActivity.kt:214 | Direct XP write bypasses XPManager |
| 13 | HIGH | AISmartDietActivity.kt:169 | carbs/fat always logged as 0 |
| 14 | HIGH | N8nConfig.kt | Hardcoded expiring ngrok URL |
| 15 | HIGH | MoodRecommendationActivity.kt:107 | fetchRecommendations() is empty |
| 16 | HIGH | homepage.kt:697 | Hardcoded macro targets |
| 17 | MEDIUM | homepage.kt:208 | Duplicate function calls |
| 18 | MEDIUM | FirebaseAuthHelper.kt:105 | All-or-nothing stat overwrite |
| 19 | MEDIUM | GamificationActivity.kt:108 | Unused Firestore instance |
| 20 | MEDIUM | SmartPantryActivity.kt | Deprecated startActivityForResult |
| 21 | MEDIUM | SmartPantryActivity.kt | Camera returns only thumbnail |
| 22 | MEDIUM | HistoryActivity.kt | Only shows RTDB workouts |
| 23 | MEDIUM | WeightProgressActivity.kt | Uses generic system layout |
| 24 | MEDIUM | InsightsRepository.kt:133 | Default Firestore for steps |
| 25 | MEDIUM | EmergencyContactManager/SOSManager | Default Firestore for safety |
| 26 | MEDIUM | homepage.kt:111 | Silent Firestore fallback |
| 27 | LOW | AIDietPlanService | Protein as String, inconsistent model |
| 28 | LOW | ChallengeSetupActivity.kt | Created challenges unreachable |
| 29 | LOW | homepage.kt | CaloriesOut from LiveData only |
| 30 | LOW | LifestyleActivity.kt:190 | Hardcoded fallback weight/height |

**Total: 6 Critical · 10 High · 10 Medium · 4 Low**
