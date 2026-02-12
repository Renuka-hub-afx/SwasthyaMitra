# Social & Challenges

## Overview
SwasthyaMitra allows users to compete with friends through private Challenges.

## 1. Creating Challenges (`ChallengeSetupActivity.kt`)
*   **Workflow:**
    1.  User enters a Challenge Name.
    2.  App generates a unique **6-character Code** (e.g., "A7X92B").
    3.  User shares this code via system share sheet (WhatsApp, etc.).
*   **Database:** Firebase Realtime Database.
*   **Structure:**
    *   `challenges/{challengeCode}`:
        *   `name`: Name of the challenge.
        *   `creatorId`: UID of the creator.
        *   `participants`: Map of `{userId: true}`.

## 2. Joining Challenges (`JoinChallengeActivity.kt`)
*   **Workflow:** Users enter the 6-character code to join an existing challenge.
*   *Note: Current implementation is a UI shell; logic integration is pending.*
