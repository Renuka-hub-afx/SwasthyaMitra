# Firebase Firestore Collection Schema

This document outlines the database structure for SwasthyaMitra. All collections are designed for efficiency with specific document IDs and optimized field names.

---

## 1. Collection: `users`

**Purpose:** Stores persistent user profile information.
**Document ID:** User's Firebase Auth UID.

| Field Name | Data Type | Example Value | Description |
| :--- | :--- | :--- | :--- |
| `name` | String | "Sarah Smith" | User's display name. |
| `email` | String | "sarah@example.com" | User's registered email address. |
| `phoneNumber` | String | "+919876543210" | Validated phone number. |
| `age` | Number | 28 | User's age (calculated from DOB). |
| `gender` | String | "Female" | "Male" or "Female". |
| `height` | Number | 165 | Height in cm. |
| `weight` | Number | 60.5 | Current weight in kg. |
| `eatingPreference`| String | "Vegetarian" | Used for AI diet filtering. |
| `allergies` | Array | ["Nuts", "Dairy"] | Exclusions for AI planning. |
| `periodMode` | Boolean | true | Active only for females; modifies AI tone/diet. |
| `wakeTime` | String | "07:00" | HH:mm (24h) format. |
| `sleepTime` | String | "23:00" | HH:mm (24h) format. |
| `waterGoal` | Number | 2200 | Daily target in ml. |
| `createdAt` | Number | 1738640000 | Account creation timestamp. |

---

## 2. Collection: `goals`

**Purpose:** Stores the results of metabolic calculations (BMR/TDEE).
**Document ID:** Auto-generated.

| Field Name | Data Type | Example Value | Description |
| :--- | :--- | :--- | :--- |
| `userId` | String | "abc12345" | Reference to user. |
| `goalType` | String | "Lose Weight" | The primary objective. |
| `bmr` | Number | 1540.5 | Calculated basal metabolism. |
| `tdee` | Number | 2100.0 | Daily energy expenditure. |
| `dailyCalories` | Number | 1600.0 | Final target (TDEE ± goal). |
| `activityLevel` | String | "Moderate" | Lifestyle activity factor. |

---

## 3. Collection: `foodLogs`

**Purpose:** Stores every food entry consumed by the user.
**Document ID:** Auto-generated.

| Field Name | Data Type | Example Value | Description |
| :--- | :--- | :--- | :--- |
| `userId` | String | "abc12345" | Reference to user. |
| `foodName` | String | "Paneer Poha" | Descriptive name. |
| `calories` | Number | 350 | Total for this log. |
| `protein`/`carbs`/`fat`| Number | 15.0 / 45.0 / 8.0 | Macros in grams. |
| `mealType` | String | "Breakfast" | Categorization. |
| `date` | String | "2026-01-20" | YYYY-MM-DD format. |
| `timestamp` | Number | 1736640000 | Unix timestamp. |

---

## 4. Collection: `waterLogs`

**Purpose:** Individual records of hydration intake.
**Document ID:** Auto-generated.

| Field Name | Data Type | Example Value | Description |
| :--- | :--- | :--- | :--- |
| `userId` | String | "abc12345" | Reference to user. |
| `amountML` | Number | 250 | Amount in ml. |
| `date` | String | "2026-01-20" | YYYY-MM-DD format. |
| `timestamp` | Number | 1736640000 | Unix timestamp. |

---

## 5. Collection: `exercise_logs` & `weight_logs`

**Purpose:** Tracking physical trends for adaptive AI.
**Attributes:** Standard `userId`, `type`/`weight`, `timestamp`, and `intensity` fields.

---

## 6. AI Management Collections

- **`ai_generated_plans`**: Complete JSON objects of recommended days.
- **`meal_feedback`**: Tracks "Ate", "Skipped", or "Regenerated" actions.
- **`user_preferences`**: Learned dislikes and favorites based on feedback.
