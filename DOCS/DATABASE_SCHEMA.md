# SwasthyaMitra Database Schema

This document outlines the complete Firestore database schema for the SwasthyaMitra application.

## Core Structure
The database follows a **user-centric, nested hierarchy**. All personal user data is encapsulated within the `users` collection to ensure data isolation, security, and scalability.

### 1. Users Collection
**Path:** `/users/{userId}`
**Description:** Stores the user's core profile, physical attributes, and app-wide settings.

| Field | Type | Description |
| :--- | :--- | :--- |
| `uid` | String | Unique Firebase Auth ID. |
| `name` | String | User's full name. |
| `email` | String | User's email address. |
| `age` | Number | User's age in years. |
| `gender` | String | "Male", "Female", "Other". |
| `weight` | Number | Current weight in kg. |
| `height` | Number | Height in cm. |
| `activityLevel` | String | e.g., "Sedentary", "Moderately Active". |
| `eatingPreference` | String | "Vegetarian", "Vegan", "Non-Veg", etc. |
| `allergies` | List<String> | List of food allergies. |
| `isOnPeriod` | Boolean | Women's health tracking flag. |
| `wakeTime` | String | "HH:MM" format. |
| `sleepTime` | String | "HH:MM" format. |

---

### 2. User Subcollections
The following collections are nested *under* each user document.

#### A. Goals
**Path:** `/users/{userId}/goals/{goalId}`
**Description:** Stores current health targets and calculated metabolic metrics.
*Note: Typically contains a single active document or historical goals.*

| Field | Type | Description |
| :--- | :--- | :--- |
| `goalType` | String | e.g., "Weight Loss", "Maintenance". |
| `targetWeight` | Number | Target weight in kg. |
| `currentValue` | Number | Starting/Current metric value. |
| `dailyCalories` | Number | Calculated daily calorie target. |
| `bmr` | Number | Basal Metabolic Rate. |
| `tdee` | Number | Total Daily Energy Expenditure. |
| `startDate` | Timestamp | Goal start time. |
| `isCompleted` | Boolean | Status of the goal. |

#### B. Exercise Logs
**Path:** `/users/{userId}/exercise_logs/{logId}`
**Description:** Records of completed workouts.

| Field | Type | Description |
| :--- | :--- | :--- |
| `logId` | String | UUID. |
| `userId` | String | Redundant ID for easy querying. |
| `exerciseName` | String | Name of the activity. |
| `caloriesBurned` | Number | Calories expended. |
| `duration` | Number | Duration in minutes. |
| `date` | String | "YYYY-MM-DD" for easy date filtering. |
| `timestamp` | Timestamp | Exact time of log. |

#### C. Food Logs
**Path:** `/users/{userId}/foodLogs/{logId}`
**Description:** Daily food intake records.

| Field | Type | Description |
| :--- | :--- | :--- |
| `foodName` | String | Name of the food item. |
| `calories` | Number | Energy content. |
| `protein` | Number | Protein in grams. |
| `carbs` | Number | Carbohydrates in grams. |
| `fat` | Number | Fat in grams. |
| `mealType` | String | "Breakfast", "Lunch", "Dinner", "Snack". |
| `date` | String | "YYYY-MM-DD". |
| `source` | String | Origin (e.g., "AI_Recommendation", "Manual"). |

#### D. Water Logs
**Path:** `/users/{userId}/waterLogs/{logId}`
**Description:** Individual hydration entries.

| Field | Type | Description |
| :--- | :--- | :--- |
| `amountML` | Number | Water amount in milliliters. |
| `date` | String | "YYYY-MM-DD". |
| `timestamp` | Timestamp | Exact time of drink. |

#### E. Weight Logs
**Path:** `/users/{userId}/weightLogs/{logId}`
**Description:** Historical weight entries for progress tracking.

| Field | Type | Description |
| :--- | :--- | :--- |
| `weight` | Number | Recorded weight in kg. |
| `date` | String | "YYYY-MM-DD". |
| `timestamp` | Timestamp | Entry time. |

#### F. AI Generated Plans
**Path:** `/users/{userId}/ai_generated_plans/{planId}`
**Description:** Daily diet plans created by the AI service.

| Field | Type | Description |
| :--- | :--- | :--- |
| `date` | String | Target date for the plan. |
| `dailyTip` | String | AI-generated health tip. |
| `totalCalories` | Number | Sum of all meal calories. |
| `breakfast` | Map | `{ item, calories, protein, reason }` |
| `lunch` | Map | `{ item, calories, protein, reason }` |
| `dinner` | Map | `{ item, calories, protein, reason }` |
| `snack` | Map | `{ item, calories, protein, reason }` |

#### G. Meal Feedback
**Path:** `/users/{userId}/meal_feedback/{feedbackId}`
**Description:** User interactions with AI suggestions (used for learning).

| Field | Type | Description |
| :--- | :--- | :--- |
| `mealName` | String | Name of the food. |
| `action` | String | "Ate", "Skipped", "New". |
| `timestamp` | Timestamp | Time of feedback. |

#### H. User Preferences
**Path:** `/users/{userId}/user_preferences/general`
**Description:** Learned preferences from user behavior.

| Field | Type | Description |
| :--- | :--- | :--- |
| `dislikedFoods` | List<String> | Foods the user frequently skips. |
| `favoriteFoods` | List<String> | Foods the user frequently eats. |

---

### 3. Root Collections
Collections that exist at the root level (outside `/users`).

#### Recommendations
**Path:** `/recommendations/{recommendationId}`
**Description:** System-generated alerts or suggestions (likely from Cloud Functions).
*Note: These are kept at the root to allow independent microservices to write to them easily, but are secured so only the owner can read/write.*

| Field | Type | Description |
| :--- | :--- | :--- |
| `userId` | String | ID of the target user. |
| `type` | String | "Meal_Suggestion", etc. |
| `status` | String | "Pending", "Accepted". |
| `suggestedFoods` | List<Map> | Details of suggested items. |

---

## Schema Evaluation

### âœ… Strengths
1.  **Isolation & Security:** Nesting under `/users/{userId}` is the gold standard for multi-tenant apps. It drastically simplifies security rules (`matches /users/{userId}/{document=**}`).
2.  **Scalability:** High-volume data (logs) are kept in subcollections, preventing the main user document from hitting the 1MB size limit.
3.  **Organization:** Logic is clean; all data pertaining to a user is found in one place. Deleting a user (and their data) is straightforward.

### âš ï¸  Considerations (Not necessarily "Wrong", but worth noting)
1.  **Querying Across Users:** If you ever need a "Global Leaderboard" (e.g., "Who walked the most today?"), the nested structure requires a "Collection Group Query" (`firestore.collectionGroup('exercise_logs')`). You must ensure your security rules allow this specifically if needed (currently they do not, which is **good** for privacy).
2.  **Redundancy:** You often store `userId` inside the nested documents (e.g., inside `FoodLog`). This is redundant since the parent document is the user, but it is **harmless** and actually helpful if you ever export data or use Collection Group Queries.
3.  **Realtime Database vs Firestore:** The `challenges` feature seems to use Realtime Database. This is a valid hybrid approach (RTDB is often better for ephemeral, high-frequency synchronization like live challenges), but maintainers should be aware of the two distinct database systems.

### Conclusion
**This schema is correct and optimal for the current SwasthyaMitra project.** It supports all implemented features (AI plans, logging, tracking) while adhering to Firebase best practices for security and structure.
