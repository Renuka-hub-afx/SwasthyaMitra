# BMR/TDEE Calorie Calculation Feature

## Overview
The app now automatically calculates personalized daily calorie needs for each user based on scientifically validated metabolic formulas. This calculation happens during the user onboarding process when they complete their profile in the LifestyleActivity.

## Mathematical Formulas Used

### Step 1: Calculate BMR (Basal Metabolic Rate)
BMR represents the energy your body burns at rest. We use the **Mifflin-St Jeor Equation**, which is the industry standard:

**For Men:**
```
BMR = (10 × weight_kg) + (6.25 × height_cm) - (5 × age_years) + 5
```

**For Women:**
```
BMR = (10 × weight_kg) + (6.25 × height_cm) - (5 × age_years) - 161
```

### Step 2: Calculate TDEE (Total Daily Energy Expenditure)
TDEE is calculated by multiplying BMR by an activity factor:

| Activity Level | Multiplier | Description |
|---------------|-----------|-------------|
| Sedentary | 1.2 | Little or no exercise |
| Lightly Active | 1.375 | Light exercise 1-3 days/week |
| Moderately Active | 1.55 | Moderate exercise 3-5 days/week |
| Very Active | 1.725 | Hard exercise 6-7 days/week |

**Formula:**
```
TDEE = BMR × Activity Factor
```

### Step 3: Adjust for Weight Goals
Based on the user's goal, we adjust the TDEE:

| Goal | Adjustment | Reasoning |
|------|-----------|-----------|
| Lose Weight | -500 kcal/day | Safe 0.5kg loss per week |
| Gain Muscle | +400 kcal/day | Healthy muscle gain |
| Maintain Weight | 0 kcal | Keep TDEE as is |
| General Health | 0 kcal | Keep TDEE as is |

**Formula:**
```
Daily Calorie Target = TDEE + Goal Adjustment
```

## Implementation Details

### Files Modified

1. **LifestyleActivity.kt**
   - Added `calculateBMR()` method implementing Mifflin-St Jeor equation
   - Added `calculateTDEE()` method with activity factor multipliers
   - Added `adjustCaloriesForGoal()` method for goal-based adjustments
   - Updated `validateAndSave()` to fetch user data and calculate calories before saving

2. **FirebaseAuthHelper.kt**
   - Added `updateGoalWithCalories()` method to store:
     - `dailyCalories` - Final calculated calorie target
     - `bmr` - User's Basal Metabolic Rate
     - `tdee` - User's Total Daily Energy Expenditure
     - `activityLevel` - Selected activity level
     - `dietPreference` - Selected diet preference
     - `targetWeight` - User's target weight goal

### Data Flow

```
User completes profile:
├── UserInfoActivity (height, weight, age, gender)
├── InsertGoalActivity (goal type: lose/gain/maintain)
└── LifestyleActivity (activity level, diet, target weight)
    ├── Fetch user data (weight, height, age, gender)
    ├── Fetch goal data (goalType)
    ├── Calculate BMR using Mifflin-St Jeor
    ├── Calculate TDEE = BMR × Activity Factor
    ├── Adjust for goal: dailyCalories = TDEE ± adjustment
    └── Save to Firebase goals collection
        ├── dailyCalories (e.g., 1800 kcal)
        ├── bmr (e.g., 1600 kcal)
        ├── tdee (e.g., 2300 kcal)
        └── Show toast: "Daily Target: 1800 kcal"
```

### Firebase Storage Structure

**Collection: `goals`**
```json
{
  "userId": "user123",
  "goalType": "Lose Weight",
  "activityLevel": "Moderately Active",
  "dietPreference": "Non-Vegetarian",
  "targetWeight": 75.0,
  "bmr": 1634.5,
  "tdee": 2533.5,
  "dailyCalories": 2033.5,
  "updatedAt": 1736640000000
}
```

## Example Calculation

### User Profile:
- **Gender:** Male
- **Age:** 30 years
- **Weight:** 80 kg
- **Height:** 175 cm
- **Activity Level:** Moderately Active
- **Goal:** Lose Weight

### Calculation Steps:

1. **BMR (Mifflin-St Jeor for Men):**
   ```
   BMR = (10 × 80) + (6.25 × 175) - (5 × 30) + 5
   BMR = 800 + 1093.75 - 150 + 5
   BMR = 1748.75 kcal
   ```

2. **TDEE (Moderately Active = 1.55):**
   ```
   TDEE = 1748.75 × 1.55
   TDEE = 2710.56 kcal
   ```

3. **Daily Calorie Target (Weight Loss = -500):**
   ```
   Daily Calories = 2710.56 - 500
   Daily Calories = 2210.56 kcal ≈ 2211 kcal
   ```

### Result:
User sees: **"Profile Complete! Daily Target: 2211 kcal 🎉"**

## User Experience

### Before (Old Flow):
1. User enters physical stats
2. User selects goal
3. User enters lifestyle data
4. System saves with generic calorie value (e.g., 2000 kcal)
5. User has no personalized guidance

### After (New Flow):
1. User enters physical stats (height, weight, age, gender)
2. User selects goal (lose/gain/maintain)
3. User enters lifestyle (activity level, diet, target weight)
4. **System automatically calculates:**
   - BMR using validated formula
   - TDEE based on activity level
   - Personalized daily calorie target
5. User receives specific calorie target: **"Daily Target: 2211 kcal"**
6. This target is used throughout the app for meal planning and food logging

## Benefits

1. **Scientifically Accurate:** Uses validated Mifflin-St Jeor equation
2. **Personalized:** Accounts for individual body composition and activity
3. **Goal-Oriented:** Adjusts calories for weight loss/gain/maintenance
4. **Transparent:** Stores BMR and TDEE for future reference
5. **Automated:** No manual calorie calculation needed
6. **Integrated:** Works seamlessly with existing meal planning feature

## Testing Checklist

- [ ] New user signs up and completes profile
- [ ] BMR calculated correctly for male users
- [ ] BMR calculated correctly for female users
- [ ] TDEE adjusted properly for each activity level
- [ ] Daily calories adjusted correctly for "Lose Weight" goal (-500)
- [ ] Daily calories adjusted correctly for "Gain Muscle" goal (+400)
- [ ] Daily calories stay same for "Maintain Weight" goal
- [ ] Toast message shows calculated calorie target
- [ ] Data saved to Firebase goals collection
- [ ] Meal plan recommendations use the calculated calories

## Future Enhancements

1. Add macro distribution calculation (protein/carbs/fat percentages)
2. Allow users to view their BMR/TDEE breakdown in profile
3. Implement weekly calorie adjustment based on progress
4. Add Harris-Benedict equation as alternative formula option
5. Include body fat percentage in calculations for more accuracy
