# **SwasthyaMitra \- Project Documentation (Phase 1\)**

Date: October 26, 2023  
Project Name: SwasthyaMitra  
Platform: Android (Native)  
Language: Kotlin

## **1\. Project Overview**

SwasthyaMitra is a comprehensive healthcare and nutrition tracking application designed to help users achieve their weight goals (loss, gain, or maintenance). The app is specifically tailored to recognize Indian food items and habits, utilizing a combination of local databases and external APIs to track calorie and macronutrient intake.

## **2\. Technical Stack**

* **Language:** Kotlin  
* **UI Framework:** XML (Android View System)  
* **Architecture:** MVVM (Model-View-ViewModel) pattern with Repository layer.  
* **Backend / Database:** \* **Firebase Authentication:** User management (Email/Password, Google Sign-in).  
  * **Firebase Firestore:** Storing user profiles, goals, and daily logs.  
* **External APIs:**  
  * **Edamam API:** For general food nutrition data.  
  * **OpenFoodFacts API:** For barcode product lookup.  
* **Local Data:** CSV-based local database for specific Indian food items (Indian\_Food\_Nutrition\_Processed.csv).

## **3\. Implemented Modules & Features**

### **A. Authentication & User Management**

* **Sign Up (SignupActivity):** Allows new users to register using email and password.  
* **Login (LoginActivity):** Secure login for existing users.  
* **Google Auth:** Integrated Google Sign-In support via FirebaseAuthHelper.  
* **Profile Management (ProfileActivity):** View and edit user details.

### **B. Onboarding & Goal Setting**

This flow captures the critical data needed for BMR (Basal Metabolic Rate) and TDEE (Total Daily Energy Expenditure) calculations.

* **User Info (UserInfoActivity):** Captures Age, Gender, Height, and Current Weight.  
* **Lifestyle Selection (LifestyleActivity):** Users select their activity level (Sedentary, Lightly Active, Moderately Active, Very Active).  
* **Goal Definition (InsertGoalActivity):** \* Users define their target weight.  
  * Users select the pace of progress (e.g., 0.5kg/week).  
  * **Calculation Logic:** The app calculates daily calorie needs based on these inputs.

### **C. The Dashboard (homepage.kt / MainActivity.kt)**

* Central hub displaying:  
  * Current Calorie intake vs. Daily Goal.  
  * Progress visualization (ProgressActivity).  
  * Quick access to logging and tools.

### **D. Nutrition Tracking Engine**

* **Food Logging (FoodLogActivity):** Interface for users to add breakfast, lunch, dinner, and snacks.  
* **Search Functionality:**  
  * **Local Search:** Queries the local CSV of Indian foods (IndianFoodRepository).  
  * **API Search:** Fallback/Alternative search using Edamam for international items.  
* **Meal Planning (MealPlanActivity):** (In Progress) Interface for viewing or generating meal plans.

### **E. Smart Input Methods**

* **Barcode Scanner (BarcodeScannerActivity):** Uses the camera to scan food product barcodes and fetch nutrition data via OpenFoodFacts.  
* **Food Photo Capture (FoodPhotoCaptureActivity):** Interface to capture images of meals (Analysis logic pending integration).

### **F. Recommendations**

* **Diet Recommendation (DietRecommendationActivity):** Provides suggestions based on user goals and preferences using the FoodRecommendationEngine.

## **4\. Data Flow & Architecture**

### **Repository Layer**

* **IndianFoodRepository:** Acts as the single source of truth for food data. It parses the local CSV assets (Indian\_Food\_Nutrition\_Processed.xlsx \- Sheet1.csv) to provide offline-first access to Indian food calories.

### **Data Models**

* **FoodLog:** Represents a single entry of food consumed by the user.  
* **IndianFood:** Represents the nutritional information of a standard Indian dish.

## **5\. Current User Flow (Implemented)**

1. **Launch:** User opens app.  
2. **Auth:** If not logged in \-\> Login/Signup.  
3. **Onboarding (First Time):**  
   * Input Physical Stats \-\> Select Activity Level \-\> Set Weight Goal.  
   * App calculates Calories/Macros and saves to Firebase.  
4. **Home:** User sees "Calories Remaining".  
5. **Action:** User clicks "Add Food".  
6. **Search:** User types "Paneer Butter Masala".  
7. **Select:** App pulls data from Local Repository.  
8. **Log:** User enters quantity, app saves to FoodLog in Firestore.

## **6\. Future Planning (Template)**

*Use this section to define the next steps based on your future goals.*

### **Phase 2 Goal: \[e.g., Enhanced AI Analysis\]**

* **Objective:**  
* Key Features to Add:  
  1\.  
  2\.  
* **Technical Requirements:**

### **Phase 3 Goal: \[e.g., Social Features / Coach Integration\]**

* **Objective:**  
* Key Features to Add:  
  1\.  
  2\.