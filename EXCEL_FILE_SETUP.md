# ⚠️ IMPORTANT: Excel File Setup

## Required Action

Please place the **Indian_Food_Nutrition_Processed.xlsx** file in the following directory:

```
SwasthyaMitra/
  app/
    src/
      main/
        assets/
          Indian_Food_Nutrition_Processed.xlsx  ← PUT FILE HERE
```

## Steps:

1. Create the `assets` folder if it doesn't exist:
   - Navigate to `app/src/main/`
   - Create a new folder named `assets`

2. Copy the Excel file:
   - Place `Indian_Food_Nutrition_Processed.xlsx` inside the `assets` folder

3. Expected Excel Format:

| Column A | Column B | Column C | Column D | Column E | Column F | Column G | Column H |
|----------|----------|----------|----------|----------|----------|----------|----------|
| Food Name | Serving Size | Calories | Protein | Carbs | Fat | Fiber | Category |
| Rice | 1 cup (150g) | 205 | 4.3 | 45.0 | 0.4 | 0.6 | Grains |
| Dal | 1 cup (200g) | 198 | 14.0 | 35.0 | 1.0 | 15.6 | Legumes |

**Note**: Row 1 should be headers, data starts from Row 2

## What Happens If File Is Missing?

The app will fall back to 10 default Indian food items:
- Rice, Chapati, Dal, Paneer, Chicken Curry
- Idli, Dosa, Sambar, Curd, Banana

## Testing

After placing the file:
1. Build and run the app
2. Go to Food Diary
3. Click the + button
4. Select "🔍 Search Indian Foods"
5. Type "rice" - you should see search results

---

**Status**: ✅ Code ready | ⏳ Waiting for Excel file placement
