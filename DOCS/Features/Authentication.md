# Feature: Authentication & Security

## 🔐 Overview

The Authentication module is the entry point of SwasthyaMitra. It is built for maximum security and ease of use, utilizing **Firebase Authentication** for identity management and specialized Kotlin logic for input validation.

---

## 🛠️ File Architecture

### **1. Entry Points**

- **`LoginActivity.kt`**: Handles existing user sessions.
- **`SignupActivity.kt`**: Manages new account creation.
- **`ForgotPasswordActivity.kt`**: A dedicated screen for secure password recovery.

### **2. Core Logic**

- **`FirebaseAuthHelper.kt`**: A wrapper for Firebase Auth methods (Login, Register, Reset).
- **`OnboardingGuard.kt`**: Ensures users complete their profile (BMR/TDEE) before accessing the dashboard.

---

## 🧠 Core Logic & Implementation

### **1. Strong Validation (The "Guard")**

To ensure account security and data quality, the `SignupActivity` and `LoginActivity` implement strict regex-based and rule-based validation.

- **Email Validation**: Standard `Patterns.EMAIL_ADDRESS` check.
- **Phone Number**: Forces a 10-digit format with country code awareness.
- **Password Strength**: Minimum 8 characters, including:
  - 1 Uppercase Letter
  - 1 Lowercase Letter
  - 1 Number
  - 1 Special Character (`!@#$%^&*`)

### **2. Dedicated Reset Flow**

Unlike simple toast-based resets, `ForgotPasswordActivity` provides a standalone UI where users can enter their email and receive a branded recovery link via Firebase.

---

## ✅ Security Features

- **Automated Email Verifier**: Integrates with Firebase to ensure the email exists.
- **Rate Limiting**: Firebase protects against brute-force login attempts.
- **Onboarding Guard**: Even after a successful login, if a user hasn't set their BMR/TDEE, they are redirected to the Profile Setup flow to prevent "Goal-less" usage.
- **Data Isolation**: Uses the Firebase `uid` as the primary key for all Firestore documents, ensuring that "User A" can never access "User B's" health logs.
- **Cloud Firestore Security Rules**:
  
```text
allow read, write: if request.auth != null && request.auth.uid == userId;
```

---

## 🔄 User Journey

1.  **Signup**: User enters details → Validation checks → Account Created in Firebase.
2.  **Initial Setup**: Redirected to Profile Flow → Finalized in Firestore.
3.  **Persistence**: The app uses `FirebaseUser.getCurrentUser()` to keep the user logged in across sessions, avoiding repeated passwords.
