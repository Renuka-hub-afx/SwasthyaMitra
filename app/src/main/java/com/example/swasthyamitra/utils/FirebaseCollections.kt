package com.example.swasthyamitra.utils

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

/**
 * ============================================================
 *  FirebaseCollections.kt — Single Source of Truth for Firebase
 * ============================================================
 *
 *  This file centralises EVERY Firebase path used across SwasthyaMitra.
 *  There are two databases in use:
 *
 *  ┌─────────────────────────────────────────────────────────┐
 *  │  FIRESTORE  (named instance "renu")                     │
 *  │  • Structured user health data (logs, goals, profile)   │
 *  │  • Queried by the repository / auth layer               │
 *  ├─────────────────────────────────────────────────────────┤
 *  │  REALTIME DATABASE (default instance)                   │
 *  │  • Real-time gamification, challenges, leaderboards     │
 *  │  • Low-latency live data (steps sync, coach flags)      │
 *  └─────────────────────────────────────────────────────────┘
 *
 *  HOW TO USE
 *  ----------
 *  Firestore:
 *      val db = FirebaseCollections.Firestore.instance
 *      db.collection(FirebaseCollections.Firestore.USERS)
 *        .document(userId)
 *        .collection(FirebaseCollections.Firestore.Sub.FOOD_LOGS)
 *
 *  Realtime Database:
 *      val db = FirebaseCollections.RealtimeDB.root
 *      db.child(FirebaseCollections.RealtimeDB.CHALLENGES)
 *        .child(challengeCode)
 *
 *  Adding a new collection:
 *      1. Add the constant in the appropriate block below.
 *      2. Use it via FirebaseCollections.Firestore.Sub.YOUR_CONST
 *         or FirebaseCollections.RealtimeDB.YOUR_CONST.
 *      3. Do NOT hard-code strings anywhere else in the project.
 * ============================================================
 */
object FirebaseCollections {

    // =========================================================
    // SECTION 1: FIRESTORE  (named instance → "renu")
    // =========================================================
    //
    //  Data structure overview:
    //
    //  users/{userId}                       → User profile document
    //    └── goals/{goalId}                 → Health goal sub-collection
    //    └── foodLogs/{logId}               → Daily food / meal entries
    //    └── exercise_logs/{logId}          → Workout & exercise logs
    //    └── sleep_logs/{logId}             → Sleep session records
    //    └── mood_logs/{logId}              → Mood tracking entries
    //    └── weightLogs/{logId}             → Body weight history
    //    └── waterLogs/{logId}              → Water / hydration entries
    //    └── daily_steps/{date}             → Per-day step count snapshot
    //    └── step_sessions/{sessionId}      → Individual step walk sessions
    //    └── walking_sessions/{sessionId}   → GPS-tracked outdoor walks
    //    └── dailySummary/{date}            → Aggregated daily health summary
    //    └── gamificationData/{docId}       → XP, level, badges, streaks
    //
    //  recommendations/{docId}              → AI-generated recommendations (top-level)
    //  emergency_events/{eventId}           → SOS / emergency alerts (top-level)
    //
    // ---------------------------------------------------------
    object Firestore {

        /**
         * Lazily initialised Firestore instance (named "renu").
         * Falls back to the default instance if the named instance
         * is not available (e.g., during unit tests or misconfiguration).
         */
        val instance: FirebaseFirestore by lazy {
            try {
                FirebaseFirestore.getInstance("renu")
            } catch (e: Exception) {
                FirebaseFirestore.getInstance()
            }
        }

        // ----- TOP-LEVEL COLLECTIONS -----

        /** Root collection holding every user's profile document. */
        const val USERS = "users"

        /**
         * AI-generated diet / exercise recommendations.
         * Top-level; not scoped per user in Firestore.
         * (Used by RecommendationRepository)
         */
        const val RECOMMENDATIONS = "recommendations"

        /**
         * Emergency / SOS events triggered by the safety module.
         * Top-level; stores alert metadata for emergency contacts.
         * (Used by SOSManager)
         */
        const val EMERGENCY_EVENTS = "emergency_events"

        // ----- USER SUB-COLLECTIONS -----
        //  All paths below are sub-collections under users/{userId}.

        object Sub {

            // ── Health Goals ──────────────────────────────────────────
            /**
             * User's health goals (e.g., weight-loss, muscle gain).
             * Path: users/{userId}/goals/{goalId}
             */
            const val GOALS = "goals"

            // ── Nutrition & Food ──────────────────────────────────────
            /**
             * Individual food / meal log entries for calorie tracking.
             * Path: users/{userId}/foodLogs/{logId}
             */
            const val FOOD_LOGS = "foodLogs"

            // ── Exercise ─────────────────────────────────────────────
            /**
             * Workout sessions — AI-generated or manually added exercises.
             * Path: users/{userId}/exercise_logs/{logId}
             */
            const val EXERCISE_LOGS = "exercise_logs"

            // ── Sleep ─────────────────────────────────────────────────
            /**
             * Sleep sessions including duration and quality scores.
             * Path: users/{userId}/sleep_logs/{logId}
             */
            const val SLEEP_LOGS = "sleep_logs"

            // ── Mood ──────────────────────────────────────────────────
            /**
             * Mood check-in entries (happy, sad, stressed, etc.).
             * Path: users/{userId}/mood_logs/{logId}
             */
            const val MOOD_LOGS = "mood_logs"

            // ── Body Weight ───────────────────────────────────────────
            /**
             * Body weight measurements over time (for trend charts).
             * Path: users/{userId}/weightLogs/{logId}
             */
            const val WEIGHT_LOGS = "weightLogs"

            // ── Hydration / Water ─────────────────────────────────────
            /**
             * Individual water intake log entries throughout the day.
             * Path: users/{userId}/waterLogs/{logId}
             */
            const val WATER_LOGS = "waterLogs"

            // ── Steps & Activity ──────────────────────────────────────
            /**
             * Daily step count snapshot; document ID is the date string "yyyy-MM-dd".
             * Path: users/{userId}/daily_steps/{date}
             */
            const val DAILY_STEPS = "daily_steps"

            /**
             * Individual step-counter walk sessions (start / end time, step count).
             * Path: users/{userId}/step_sessions/{sessionId}
             */
            const val STEP_SESSIONS = "step_sessions"

            /**
             * GPS-tracked outdoor walk sessions saved by TrackingService.
             * Path: users/{userId}/walking_sessions/{sessionId}
             */
            const val WALKING_SESSIONS = "walking_sessions"

            // ── Daily Aggregates ──────────────────────────────────────
            /**
             * Aggregated daily health summary (meals, steps, water, sleep, mood logged flags).
             * Document ID is the date string "yyyy-MM-dd".
             * Path: users/{userId}/dailySummary/{date}
             */
            const val DAILY_SUMMARY = "dailySummary"

            // ── Gamification ──────────────────────────────────────────
            /**
             * Gamification data — XP, level, streak, badges earned.
             * Path: users/{userId}/gamificationData/{docId}
             */
            const val GAMIFICATION_DATA = "gamificationData"
        }

        // ----- CONVENIENCE HELPER FUNCTIONS -----

        /**
         * Returns the Firestore CollectionReference for a user's sub-collection.
         *
         * Usage:
         *   val foodRef = FirebaseCollections.Firestore.userSub(userId, Sub.FOOD_LOGS)
         */
        fun userSub(userId: String, subCollection: String) =
            instance.collection(USERS).document(userId).collection(subCollection)

        /**
         * Returns the DocumentReference for a user's profile document.
         *
         * Usage:
         *   val profileRef = FirebaseCollections.Firestore.userDoc(userId)
         */
        fun userDoc(userId: String) =
            instance.collection(USERS).document(userId)
    }

    // =========================================================
    // SECTION 2: REALTIME DATABASE  (default instance)
    // =========================================================
    //
    //  Data structure overview:
    //
    //  users/{userId}                       → Gamification profile snapshot
    //    └── completionHistory/{date}       → Boolean flag: exercise done today?
    //    └── joined_challenges/{code}       → Challenges the user has joined
    //
    //  challenges/{challengeCode}           → Challenge room metadata
    //    └── participants/{userId}          → Boolean: user has joined
    //    └── name, status, durationDays, createdAt, winnerId
    //
    //  userStats/{userId}                   → Live leaderboard stats
    //    ├── name, streak, lastActiveDate   → Public leaderboard fields
    //    ├── pendingChallengeShield         → Flag: shield reward pending
    //    └── pendingChallengeUpdate         → Pending challenge result object
    //
    //  userEmailIndex/{encodedEmail}        → Maps e-mail → userId for search
    //
    // ---------------------------------------------------------
    object RealtimeDB {

        /** The Realtime Database URL for this project. */
        private const val DB_URL =
            "https://swasthyamitra-ded44-default-rtdb.asia-southeast1.firebasedatabase.app"

        /**
         * Lazily initialised root DatabaseReference.
         * All child paths should be resolved from this reference.
         */
        val root by lazy {
            FirebaseDatabase.getInstance(DB_URL).reference
        }

        // ----- TOP-LEVEL NODES -----

        /**
         * Gamification profile snapshots for each user.
         * Stores XP, level, avatar, joined challenge references.
         * Path: users/{userId}
         * (Separate from Firestore users — this is the RTDB mirror)
         */
        const val USERS = "users"

        /**
         * Challenge rooms created via ChallengeSetupActivity.
         * Each document is keyed by a short invite code.
         * Path: challenges/{challengeCode}
         */
        const val CHALLENGES = "challenges"

        /**
         * Live leaderboard statistics for all users.
         * Updated on every login, streak update, or step goal completion.
         * Path: userStats/{userId}
         */
        const val USER_STATS = "userStats"

        /**
         * Reverse lookup index: encoded e-mail → userId.
         * Used by ChallengeSetupActivity to invite users by e-mail.
         * Path: userEmailIndex/{encodedEmail}
         */
        const val USER_EMAIL_INDEX = "userEmailIndex"

        // ----- USER SUB-NODES -----
        //  All paths below are children under users/{userId} in RTDB.

        object UserSub {

            /**
             * Daily exercise completion flags.
             * Value is a Boolean (true = completed).
             * Path: users/{userId}/completionHistory/{date}
             */
            const val COMPLETION_HISTORY = "completionHistory"

            /**
             * Challenges the user has joined, keyed by challenge code.
             * Path: users/{userId}/joined_challenges/{challengeCode}
             */
            const val JOINED_CHALLENGES = "joined_challenges"
        }

        // ----- CHALLENGE SUB-NODES -----
        //  All paths below are children under challenges/{challengeCode} in RTDB.

        object ChallengeSub {

            /**
             * Participants map: userId → true.
             * Path: challenges/{challengeCode}/participants/{userId}
             */
            const val PARTICIPANTS = "participants"
        }

        // ----- USER STATS SUB-NODES -----
        //  Pending update flags stored under userStats/{userId} in RTDB.

        object UserStatsSub {

            /**
             * Flag set to true when a shield reward is pending for the user.
             * Path: userStats/{userId}/pendingChallengeShield
             */
            const val PENDING_CHALLENGE_SHIELD = "pendingChallengeShield"

            /**
             * Pending challenge result data (challengeCode, status, winnerId).
             * Written by the challenge evaluation logic, consumed by GamificationActivity.
             * Path: userStats/{userId}/pendingChallengeUpdate
             */
            const val PENDING_CHALLENGE_UPDATE = "pendingChallengeUpdate"
        }

        // ----- CONVENIENCE HELPER FUNCTIONS -----

        /**
         * Returns a DatabaseReference for a user's RTDB node.
         *
         * Usage:
         *   val ref = FirebaseCollections.RealtimeDB.userRef(userId)
         */
        fun userRef(userId: String) = root.child(USERS).child(userId)

        /**
         * Returns a DatabaseReference for a specific challenge.
         *
         * Usage:
         *   val ref = FirebaseCollections.RealtimeDB.challengeRef(code)
         */
        fun challengeRef(code: String) = root.child(CHALLENGES).child(code)

        /**
         * Returns a DatabaseReference for a user's live stats (leaderboard).
         *
         * Usage:
         *   val ref = FirebaseCollections.RealtimeDB.userStatsRef(userId)
         */
        fun userStatsRef(userId: String) = root.child(USER_STATS).child(userId)
    }
}
