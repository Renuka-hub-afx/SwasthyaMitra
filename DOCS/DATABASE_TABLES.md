# DATABASE TABLES

SwasthyaMitra uses two Firebase database services: **Cloud Firestore** (primary, document-oriented NoSQL database) and **Firebase Realtime Database** (for gamification and challenges). The following tables describe the complete database schema.

---

## A. CLOUD FIRESTORE DATABASE

---

**Table 1: Users Collection — User Profile Document**
**Path:** `/users/{userId}`

| Sr. No. | Field Name | Data Type | Description |
| :---: | :--- | :---: | :--- |
| 1 | userId | String | Firebase Auth UID (unique identifier) |
| 2 | email | String | User's registered email address |
| 3 | name | String | Full name of the user |
| 4 | age | Number | Age in years |
| 5 | gender | String | Male / Female / Other |
| 6 | height | Number | Height in centimetres |
| 7 | weight | Number | Weight in kilograms |
| 8 | activityLevel | String | Sedentary / Light / Moderate / Active / Very Active |
| 9 | goal | String | Lose Weight / Maintain / Gain Weight |
| 10 | bmi | Number | Calculated Body Mass Index |
| 11 | calorieTarget | Number | Daily calorie target (kcal) |
| 12 | proteinTarget | Number | Daily protein target (grams) |
| 13 | carbsTarget | Number | Daily carbohydrate target (grams) |
| 14 | fatsTarget | Number | Daily fat target (grams) |
| 15 | waterGoal | Number | Daily water intake goal (mL) |
| 16 | createdAt | Timestamp | Account creation date and time |
| 17 | lastLogin | Timestamp | Most recent login date and time |
| 18 | periodMode | Boolean | Women's health mode toggle |
| 19 | avatar.skinTone | Number | Avatar skin tone selection |
| 20 | avatar.hairStyle | Number | Avatar hair style selection |
| 21 | avatar.outfit | Number | Avatar outfit selection |
| 22 | preferences.dietaryRestrictions | Array | List of dietary restrictions |
| 23 | preferences.favoriteExercises | Array | List of preferred exercises |
| 24 | preferences.notificationsEnabled | Boolean | Notification preference toggle |

---

**Table 2: Daily Steps Subcollection**
**Path:** `/users/{userId}/daily_steps/{date}`

| Sr. No. | Field Name | Data Type | Description |
| :---: | :--- | :---: | :--- |
| 1 | date | String | Date in yyyy-MM-dd format |
| 2 | steps | Number | Total validated step count for the day |
| 3 | calories | Number | Estimated calories burned from steps |
| 4 | lastUpdated | Timestamp | Last time the record was updated |
| 5 | sessions | Array | List of step session objects |
| 6 | sessions[].startTime | Number | Session start time (milliseconds) |
| 7 | sessions[].endTime | Number | Session end time (milliseconds) |
| 8 | sessions[].steps | Number | Steps counted in the session |
| 9 | sessions[].calories | Number | Calories burned in the session |
| 10 | goal | Number | Daily step goal |
| 11 | goalAchieved | Boolean | Whether daily step goal was met |

---

**Table 3: Exercise Logs Subcollection**
**Path:** `/users/{userId}/exercise_logs/{logId}`

| Sr. No. | Field Name | Data Type | Description |
| :---: | :--- | :---: | :--- |
| 1 | exerciseName | String | Name of the exercise performed |
| 2 | category | String | Cardio / Strength / Flexibility / Sports |
| 3 | duration | Number | Duration in minutes |
| 4 | caloriesBurned | Number | Estimated calories burned |
| 5 | intensity | String | Low / Moderate / High |
| 6 | timestamp | Timestamp | Exact date and time of log entry |
| 7 | date | String | Date in yyyy-MM-dd format |
| 8 | notes | String | Optional user notes |
| 9 | source | String | manual / google_fit |

---

**Table 4: Food Logs Subcollection**
**Path:** `/users/{userId}/foodLogs/{logId}`

| Sr. No. | Field Name | Data Type | Description |
| :---: | :--- | :---: | :--- |
| 1 | foodName | String | Name of the food item |
| 2 | calories | Number | Calorie content (kcal) |
| 3 | protein | Number | Protein content in grams |
| 4 | carbs | Number | Carbohydrate content in grams |
| 5 | fats | Number | Fat content in grams |
| 6 | servingSize | String | Serving size description |
| 7 | mealType | String | Breakfast / Morning Snack / Lunch / Evening Snack / Dinner |
| 8 | timestamp | Timestamp | Exact date and time of log entry |
| 9 | date | String | Date in yyyy-MM-dd format |
| 10 | source | String | ai_generated / manual / barcode |
| 11 | barcode | String | Barcode number (optional, for scanned items) |

---

**Table 5: Sleep Logs Subcollection**
**Path:** `/users/{userId}/sleep_logs/{logId}`

| Sr. No. | Field Name | Data Type | Description |
| :---: | :--- | :---: | :--- |
| 1 | sleepDate | String | Date in yyyy-MM-dd format |
| 2 | bedTime | String | Bedtime in HH:mm format |
| 3 | wakeTime | String | Wake time in HH:mm format |
| 4 | duration | Number | Sleep duration in hours |
| 5 | quality | String | Poor / Fair / Good / Excellent |
| 6 | notes | String | Optional user notes |
| 7 | timestamp | Timestamp | Exact date and time of log entry |

---

**Table 6: Weight Logs Subcollection**
**Path:** `/users/{userId}/weight_logs/{logId}`

| Sr. No. | Field Name | Data Type | Description |
| :---: | :--- | :---: | :--- |
| 1 | weight | Number | Recorded weight in kilograms |
| 2 | date | String | Date in yyyy-MM-dd format |
| 3 | timestamp | Timestamp | Exact date and time of log entry |
| 4 | bmi | Number | Calculated BMI at time of entry |
| 5 | notes | String | Optional user notes |

---

**Table 7: Mood Logs Subcollection**
**Path:** `/users/{userId}/mood_logs/{logId}`

| Sr. No. | Field Name | Data Type | Description |
| :---: | :--- | :---: | :--- |
| 1 | mood | String | Happy / Sad / Stressed / Anxious / Energetic / Tired |
| 2 | intensity | Number | Mood intensity on a 1–10 scale |
| 3 | timestamp | Timestamp | Exact date and time of log entry |
| 4 | date | String | Date in yyyy-MM-dd format |
| 5 | notes | String | Optional user notes |
| 6 | recommendations | Array | AI-generated wellness suggestions |

---

**Table 8: Water Logs Subcollection (Hydration Tracking)**
**Path:** `/users/{userId}/waterLogs/{logId}`

| Sr. No. | Field Name | Data Type | Description |
| :---: | :--- | :---: | :--- |
| 1 | logId | String | Unique identifier for the water log |
| 2 | userId | String | Firebase Auth UID of the user |
| 3 | amountML | Number | Water intake amount in millilitres |
| 4 | timestamp | Number | Time of log entry (milliseconds) |
| 5 | date | String | Date in yyyy-MM-dd format |

---

**Table 9: Pantry Items Subcollection**
**Path:** `/users/{userId}/pantry_items/{itemId}`

| Sr. No. | Field Name | Data Type | Description |
| :---: | :--- | :---: | :--- |
| 1 | itemName | String | Name of the pantry item |
| 2 | category | String | Vegetables / Fruits / Grains / Dairy / Meat / Other |
| 3 | quantity | Number | Quantity of the item |
| 4 | unit | String | Unit of measurement (e.g. kg, pieces) |
| 5 | expiryDate | String | Expiry date in yyyy-MM-dd format |
| 6 | addedDate | Timestamp | Date the item was added |
| 7 | isExpired | Boolean | Whether the item has expired |

---

**Table 10: Safety Contacts Subcollection**
**Path:** `/users/{userId}/safety_contacts/{contactId}`

| Sr. No. | Field Name | Data Type | Description |
| :---: | :--- | :---: | :--- |
| 1 | name | String | Name of the emergency contact |
| 2 | phoneNumber | String | Contact phone number |
| 3 | relationship | String | Relationship to the user |
| 4 | isPrimary | Boolean | Whether this is the primary emergency contact |
| 5 | addedAt | Timestamp | Date the contact was added |

---

**Table 11: Step Sessions Subcollection**
**Path:** `/users/{userId}/step_sessions/{sessionId}`

| Sr. No. | Field Name | Data Type | Description |
| :---: | :--- | :---: | :--- |
| 1 | startTime | Number | Session start time (milliseconds) |
| 2 | endTime | Number | Session end time (milliseconds) |
| 3 | steps | Number | Total steps recorded in the session |
| 4 | calories | Number | Estimated calories burned in the session |

---

## B. FIREBASE REALTIME DATABASE

---

**Table 12: Gamification — User Fitness Data**
**Path:** `users/{userId}`

| Sr. No. | Field Name | Data Type | Description |
| :---: | :--- | :---: | :--- |
| 1 | userId | String | Firebase Auth UID |
| 2 | streak | Number | Current consecutive day streak |
| 3 | lastCheckInDate | String | Last check-in date in yyyy-MM-dd format |
| 4 | totalPoints | Number | Total gamification points earned |
| 5 | lastCompletionDate | String | Date of last completed activity (yyyy-MM-dd) |

---

**Table 13: Gamification — Daily Activity Log**
**Path:** `dailyActivity/{userId}/{date}`

| Sr. No. | Field Name | Data Type | Description |
| :---: | :--- | :---: | :--- |
| 1 | date | String | Date in yyyy-MM-dd format |
| 2 | steps | Number | Steps recorded on this date |
| 3 | calories | Number | Approximate calories burned (steps × 0.04) |
| 4 | workout | String | Workout completion status ("Completed" or empty) |

---

**Table 14: Challenges**
**Path:** `challenges/{challengeCode}`

| Sr. No. | Field Name | Data Type | Description |
| :---: | :--- | :---: | :--- |
| 1 | id | String | Unique 6-character alphanumeric challenge code |
| 2 | name | String | Name of the challenge |
| 3 | creatorId | String | Firebase Auth UID of the challenge creator |
| 4 | createdAt | Number | Challenge creation time (milliseconds) |
| 5 | participants | Map | Map of participant user IDs to join status |

---

## LIST OF TABLES

| Table No. | Table Name | Database | Path |
| :---: | :--- | :---: | :--- |
| Table 1 | Users Collection — User Profile Document | Firestore | /users/{userId} |
| Table 2 | Daily Steps Subcollection | Firestore | /users/{userId}/daily_steps/{date} |
| Table 3 | Exercise Logs Subcollection | Firestore | /users/{userId}/exercise_logs/{logId} |
| Table 4 | Food Logs Subcollection | Firestore | /users/{userId}/foodLogs/{logId} |
| Table 5 | Sleep Logs Subcollection | Firestore | /users/{userId}/sleep_logs/{logId} |
| Table 6 | Weight Logs Subcollection | Firestore | /users/{userId}/weight_logs/{logId} |
| Table 7 | Mood Logs Subcollection | Firestore | /users/{userId}/mood_logs/{logId} |
| Table 8 | Water Logs — Hydration Tracking | Firestore | /users/{userId}/waterLogs/{logId} |
| Table 9 | Pantry Items Subcollection | Firestore | /users/{userId}/pantry_items/{itemId} |
| Table 10 | Safety Contacts Subcollection | Firestore | /users/{userId}/safety_contacts/{contactId} |
| Table 11 | Step Sessions Subcollection | Firestore | /users/{userId}/step_sessions/{sessionId} |
| Table 12 | Gamification — User Fitness Data | Realtime DB | users/{userId} |
| Table 13 | Daily Activity Log | Realtime DB | dailyActivity/{userId}/{date} |
| Table 14 | Challenges | Realtime DB | challenges/{challengeCode} |
