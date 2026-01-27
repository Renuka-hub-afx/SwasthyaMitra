import pandas as pd
import os

# Paths
xlsx_path = r'c:\Users\renuk\OneDrive\Desktop\project\SwasthyaMitra\app\src\main\assets\Indian_Food_Nutrition_Processed (1).xlsx'
csv_path = r'c:\Users\renuk\OneDrive\Desktop\project\SwasthyaMitra\app\src\main\assets\food_data.csv'

try:
    print(f"Reading {xlsx_path}...")
    df = pd.read_excel(xlsx_path)
    
    # Let's see what columns we have
    print(f"Columns found: {df.columns.tolist()}")
    
    # We need Food Name and Calories at minimum.
    # We'll try to find columns that look like names and calories.
    # Based on the file name, it likely has 'Food', 'Protein', 'Calories', etc.
    
    # Assuming columns like 'Food', 'Calories', 'Protein' exist.
    # We'll save all of them to CSV.
    df.to_csv(csv_path, index=False)
    print(f"Successfully converted to {csv_path}")
    
except Exception as e:
    print(f"Error: {e}")
