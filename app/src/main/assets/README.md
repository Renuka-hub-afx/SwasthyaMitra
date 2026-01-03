# Assets Folder

This folder contains app resources that are packaged with the APK.

## Current Contents:

### Required Files:
- **Indian_Food_Nutrition_Processed.xlsx** ← Place this file here

This Excel file contains the Indian food nutrition database used for food search functionality.

## Excel File Structure:
- Column A: Food Name (String)
- Column B: Serving Size (String)
- Column C: Calories (Number)
- Column D: Protein (Number, grams)
- Column E: Carbs (Number, grams)
- Column F: Fat (Number, grams)
- Column G: Fiber (Number, grams)
- Column H: Category (String)

Row 1 = Headers, Data starts from Row 2

---

**Note**: If the Excel file is missing, the app will use 10 default fallback foods.
