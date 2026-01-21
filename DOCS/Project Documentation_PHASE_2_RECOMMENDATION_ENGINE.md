# **Phase 2: Intelligent Recommendation Engine Documentation**

Date: January 19, 2026  
Project Name: SwasthyaMitra  
Focus: Backend Architecture, Machine Learning Integration, and Data Logic

## **1\. Executive Summary**

The primary objective of Phase 2 is to evolve SwasthyaMitra from a passive data recording tool into an active, intelligent health assistant. While Phase 1 focused on data ingestion (logging foods, setting goals), Phase 2 focuses on data utility—using the stored information to guide user behavior.

By leveraging **Firebase Cloud Functions** for server-side logic and **Google Vertex AI** for predictive modeling, the system will analyze user habits to provide proactive value. This includes personalized meal suggestions based on dietary preferences, real-time nutritional warnings when a user deviates from their goals, and insightful daily summaries. The ultimate goal is to reduce the cognitive load of dieting by answering the question, *"What should I eat next?"* before the user even asks.

## **2\. System Architecture**

The recommendation engine operates on a robust "Push-Pull" architecture designed to handle both real-time user actions and scheduled background processing:

1. **Data Layer (Firestore):**  
   * Acts as the single source of truth, storing raw input data (User Profile, Goals, Logs).  
   * Leverages Firestore's real-time capabilities to ensure that any update (e.g., logging a snack) is immediately available for processing.  
2. **Trigger Layer (Cloud Functions):**  
   * **Reactive Triggers:** Functions that execute immediately in response to database events (e.g., onWrite to foodLogs triggers a macro calculation).  
   * **Proactive Triggers:** Functions scheduled via Cloud Scheduler (e.g., running every morning at 7:00 AM to generate a daily meal plan).  
3. **Intelligence Layer (Vertex AI / Logic):**  
   * **Deterministic Logic:** Code-based rules for immediate math (e.g., "If calories \< 0, send alert").  
   * **Probabilistic Logic (ML):** Vertex AI models that infer preferences and predict future needs based on historical patterns.  
4. **Presentation Layer (Android App):**  
   * The Android client observes the recommendations collection. When the backend generates a new suggestion, the UI updates automatically without requiring a manual refresh.

### **High-Level Data Flow Diagram**

\[Android App\] \--(Write Action)--\> \[Firestore (foodLogs)\] \--(Event Trigger)--\> \[Cloud Function\] \--(API Request)--\> \[Vertex AI Endpoint\] \--(Inference)--\> \[Predictions/Suggestions\] \--(Write Result)--\> \[Firestore (recommendations)\] \--(Real-time Listener)--\> \[Android App UI\]

## **3\. Data Inputs & Schema Analysis**

The recommendation engine relies on three core collections already defined in Phase 1\. Below is a detailed analysis of how specific fields dictate the engine's logic.

### **A. Users Collection (users)**

This collection establishes the biological constraints and lifestyle context for every recommendation.

* **allergies (List):** *Hard Constraint.* This is a safety-critical field. Any food item identified by the recommendation engine must be cross-referenced against this list. If a user is allergic to "Peanuts," any generated suggestion containing peanuts is strictly discarded.  
* **preference (String \- Veg/Non-Veg):** *Hard Constraint.* Determines the eligible pool of foods. For a vegetarian user, the system filters the IndianFoodRepository dataset to exclude meat-based proteins immediately, optimizing search performance.  
* **bmr / tdee (Double):** Used to calculate the "Base Baseline." These figures allow the system to understand the user's metabolic floor, ensuring recommendations never suggest dangerously low calorie intakes.  
* **activityLevel (String):** Used to adjust the "Calorie Buffer." A "Very Active" user might get snack suggestions of 300-400 calories, whereas a "Sedentary" user might get suggestions capped at 150 calories.

### **B. Goals Collection (goals)**

This collection provides the dynamic targets that change as the user progresses.

* **targetCalories (Double):** The daily "Budget." Recommendations are essentially financial advice for this calorie budget—helping the user spend their calories wisely.  
* **dietPreference (String \- Keto/Low Carb/Balanced):** *Soft Constraint.* This adjusts the ranking weights of food items.  
  * *Example:* If dietPreference \== "Keto", a High-Fat/Low-Carb food like "Butter Chicken" is ranked higher than "Dal Rice," even if both fit the calorie budget.  
* **dailyCalories (Double \- Current Intake):**  
  * **Logic:** Remaining Budget \= targetCalories \- dailyCalories.  
  * **Trigger:** If Remaining \< 200 (Low Budget) and Time \< 2:00 PM (Early in the day), the system triggers "Volume Eating" strategies, suggesting foods with low caloric density (e.g., salads, soups) to keep the user full.

### **C. Food Logs Collection (foodLogs)**

This collection is the historical record used for training ML models.

* **timestamp / date:** Crucial for Temporal Pattern Recognition. The engine learns that "User A usually drinks Chai at 4:30 PM" and can preemptively suggest it or a healthier alternative.  
* **foodName:** Used for **Content-Based Filtering**.  
  * *Logic:* "User eats 'Paneer' 4 times a week." \-\> The system tags 'Paneer' as a 'Preferred Ingredient' and prioritizes recipes containing it.  
* **Macros (protein, carbs, fat):** Used to calculate real-time deficiencies. The accumulation of these values triggers the "Macro Balancer" logic (e.g., "You are low on protein today").

## **4\. The Output: Recommendations Schema**

To decouple the logic from the UI, we introduce a dedicated recommendations collection. The App simply displays what it finds here.

### **New Collection: recommendations**

Document ID: Auto-generated  
Fields:

* userId: String (Foreign Key linking to users)  
* type: String (Enum)  
  * "Meal\_Suggestion": Specific food items to eat.  
  * "Macro\_Alert": Warning about nutritional imbalance.  
  * "Daily\_Plan": A full day's eating itinerary.  
* mealTime: String ("Breakfast", "Lunch", "Dinner", "Snack", "Anytime") \- Context for when this should be shown.  
* suggestedFoods: Array of Maps (The core payload)  
  * name: String  
  * calories: Int  
  * servingSize: String  
  * reason: String (Explainable AI: e.g., "Suggested because it is high in protein and fits your 300 kcal remaining budget.")  
  * macros: Map (Protein, Carbs, Fat)  
* createdAt: Timestamp (TTL: Recommendations typically expire after 12-24 hours).  
* status: String ("Pending", "Accepted", "Dismissed", "Consumed")  
  * *Purpose:* This field is the feedback loop for Reinforcement Learning. If a user constantly "Dismisses" salads, the model learns to stop suggesting them.

## **5\. Recommendation Logic Levels**

We will implement this intelligence in two distinct tiers, starting with deterministic rules and moving to probabilistic AI.

### **Tier 1: Rule-Based Engine (Cloud Functions)**

*Immediate, deterministic implementation using standard conditional logic.*

1. **The "Macro Balancer" (Deficiency Corrector):**  
   * **Trigger:** onWrite event to the foodLogs collection.  
   * **Logic:**  
     1. Sum user's total protein/carbs/fat for the current date.  
     2. Compare against goals.targetProtein etc.  
     3. **Condition:** If CurrentProtein \< (TargetProtein \* 0.4) AND Time \> 6:00 PM (User is behind on protein and day is ending).  
     4. **Action:** Query IndianFoodRepository for foods tagged High\_Protein and Dinner\_Appropriate.  
     5. **Output:** Suggest "Grilled Chicken Breast" or "Rajma Masala" specifically to close the gap.  
2. **The "Calorie Smart-Fill" (Budget Optimizer):**  
   * **Scenario:** User searches for a snack. Remaining Calories \= 150\.  
   * **Logic:** A standard search might return a 300-calorie Samosa. The Smart-Fill logic filters the database: SELECT \* FROM foods WHERE calories \<= 150 AND preference \== user.preference.  
   * **Output:** Returns a curated list: "Masala Papad (120 cal)", "Buttermilk (80 cal)", "Apple (95 cal)".

### **Tier 2: AI-Based Engine (Vertex AI / ML)**

*Advanced, probabilistic implementation leveraging Google Cloud's ML capabilities.*

1. **Collaborative Filtering (Similar User Clustering):**  
   * **Concept:** "Lookalike Audiences."  
   * **Logic:** The model identifies users with similar vectors (BMI 28, Sedentary, Goal: Weight Loss). It looks at what the *successful* users in this cluster (those who met their goals) ate.  
   * **Prediction:** "Users like you found success eating 'Oats Upma' for breakfast."  
2. **Predictive Meal Planning (Context Awareness):**  
   * **Concept:** Sequence Prediction.  
   * **Input Features:** Time of day, Day of week (Weekend vs Weekday), Previous meal, User History.  
   * **Inference:** It's Monday, 8:00 AM. History shows user usually logs 'Eggs'.  
   * **Output:** Pre-populate the "Add Breakfast" screen with 'Eggs' and 'Toast' as "Quick Add" suggestions, reducing clicks and friction.

## **6\. Implementation Roadmap**

### **Phase 2.1: The Foundation (Weeks 1-2)**

*Focus: Infrastructure and Rule-Based Logic.*

1. **Environment Setup:** Initialize Firebase Cloud Functions and set up the local emulator for testing triggers.  
2. **Backend Logic:** Develop the "Macro Calculator" function. This triggers on every log entry, recalculates daily totals, and updates the goals document.  
3. **UI Development:** Create a RecommendationAdapter and RecyclerView in the Android app to render cards from the recommendations collection.  
4. **Rule Implementation:** Write the first Cloud Function to generate a "Dinner Suggestion" if calories remain \> 500 at 7 PM.

### **Phase 2.2: The Intelligence (Weeks 3-4)**

*Focus: Machine Learning Pipeline.*

1. **Data Engineering:** Write a Firestore export script to dump foodLogs into Cloud Storage as CSV/JSON for training.  
2. **Model Training (AutoML):** Use Vertex AI AutoML Tabular training.  
   * *Target:* Predict foodName.  
   * *Features:* time\_of\_day, day\_of\_week, user\_preference, remaining\_calories.  
3. **Deployment:** Deploy the trained model to a Vertex AI Endpoint.  
4. **Integration:** Update Cloud Functions to call this Vertex AI Endpoint for complex queries instead of using simple if/else logic.

## **7\. Example Workflow Scenarios**

**Scenario A: The Lunchtime Nudge (Proactive)**

1. **Trigger (1:00 PM):** Cloud Scheduler triggers a "Lunch Check" cron job.  
2. **Validation:** The function checks Firestore: *Has userId: 123 logged a meal with type 'Lunch' today?* \-\> **No.**  
3. **Analysis:** Function reads User Profile.  
   * *Calories Remaining:* 400\.  
   * *Preference:* Vegetarian.  
   * *Goal:* Weight Loss.  
4. **Selection:** The engine searches for meals fitting: Vegetarian \+ 350-400 cal \+ High Satiety.  
5. **Action:** A document is created in recommendations collection.  
   * *Message:* "How about **Palak Paneer and 1 Roti** (380 cal) for lunch? It fits your budget perfectly."  
6. **Notification:** A Firebase Cloud Messaging (FCM) push notification is sent to the device.

**Scenario B: The Protein Alert (Reactive)**

1. **User Action:** User logs "Butter Naan" and "Butter Chicken" for lunch.  
2. **Trigger:** onWrite triggers the calculateMacros Cloud Function.  
3. **Computation:**  
   * *Fat:* High (User used 60% of daily fat limit).  
   * *Carbs:* High.  
   * *Protein:* Moderate.  
4. **Logic Evaluation:** The system detects that the user only has 15g of fat left for the entire day (Dinner \+ Snacks).  
5. **Recommendation Generation:** The system proactively generates a suggestion for **Dinner**.  
   * *Constraint:* Must be Low Fat (\<5g).  
   * *Suggestion:* "Light Dinner Suggestion: **Moong Dal Khichdi** or **Lentil Soup**."  
   * *Reason:* "You had a heavy lunch. A low-fat dinner will keep you on track."