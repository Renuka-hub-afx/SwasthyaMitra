# Authentication Feature

## Overview
The Authentication feature manages user sign-up, login, and session persistence using Firebase Authentication. It also handles the creation of the initial user document in Firestore upon successful registration.

## Key Components

### 1. `FirebaseAuthHelper.kt`
*   **Location:** `com.example.swasthyamitra.auth`
*   **Purpose:** Central helper class wrapping all Firebase Auth interactions.
*   **Key Methods:**
    *   `signUpWithEmail(...)`: Creates a Firebase Auth user and a corresponding Firestore document in `users/{userId}`.
    *   `signInWithEmail(...)`: Logs existing users in.
    *   `isUserLoggedIn()`: Checks current session status.
    *   `signOut()`: Clears the session.

### 2. Activities
*   **`LoginActivity.kt`**: UI for returning users.
*   **`SignupActivity.kt`**: UI for new registration.
*   **`UserInfoActivity.kt`**: Onboarding flow to collect initial physical stats (age, weight, height) after signup.
*   **`ForgotPasswordActivity.kt`**: Handles password reset emails.

## Database Interactions

### Firestore Collection: `users`
When a new user signs up, a document is created at `users/{userId}` with the following initial fields:
*   `userId` (String)
*   `name` (String)
*   `email` (String)
*   `phoneNumber` (String)
*   `age` (Number)
*   `createdAt` (Timestamp)

## Flow
1.  **Sign Up**: User enters credentials -> Firebase Auth creates account -> `FirebaseAuthHelper` creates Firestore document -> User redirected to `UserInfoActivity` (if additional details needed) or `MainActivity`.
2.  **Login**: User enters credentials -> `FirebaseAuthHelper` authenticates -> User redirected to `MainActivity`.
