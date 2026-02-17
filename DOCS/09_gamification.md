# Gamification System

## 📋 Overview

The Gamification System transforms health tracking into an engaging game-like experience through XP points, levels, achievement badges, daily streaks, and leaderboards to motivate consistent healthy behaviors.

---

## 🎯 Purpose & Importance

### Why Gamification Matters
- **Motivation**: Game mechanics make health goals more engaging
- **Habit Formation**: Rewards encourage daily consistency
- **Progress Visualization**: Levels and badges show achievement
- **Social Competition**: Leaderboards drive friendly competition
- **Long-term Engagement**: Keeps users active in the app

---

## 🔄 How It Works

### XP and Leveling System

```kotlin
// XP awarded for actions
val xpRewards = mapOf(
    "daily_login" to 10,
    "log_food" to 5,
    "log_exercise" to 15,
    "complete_workout" to 25,
    "achieve_step_goal" to 30,
    "log_sleep" to 10,
    "maintain_streak" to 50,
    "complete_challenge" to 100
)

// Level calculation
fun calculateLevel(totalXP: Int): Int {
    return (sqrt(totalXP / 100.0)).toInt() + 1
}

// XP needed for next level
fun xpForNextLevel(currentLevel: Int): Int {
    return (currentLevel * currentLevel) * 100
}
```

### Badge System

```kotlin
data class Badge(
    val id: String,
    val name: String,
    val description: String,
    val tier: BadgeTier,  // BRONZE, SILVER, GOLD, PLATINUM
    val requirement: Int,
    val icon: String,
    val earnedAt: Timestamp?
)

val availableBadges = listOf(
    Badge("step_master_bronze", "Step Starter", "Walk 10,000 steps", BRONZE, 10000),
    Badge("step_master_silver", "Step Champion", "Walk 50,000 total steps", SILVER, 50000),
    Badge("step_master_gold", "Step Legend", "Walk 100,000 total steps", GOLD, 100000),
    Badge("calorie_burner", "Calorie Crusher", "Burn 5,000 calories", GOLD, 5000),
    Badge("streak_warrior", "Streak Master", "30-day streak", PLATINUM, 30),
    Badge("early_bird", "Early Riser", "Log breakfast 7 days in a row", SILVER, 7)
)
```

### Streak Tracking

```kotlin
fun updateStreak(userId: String) {
    val today = LocalDate.now()
    val yesterday = today.minusDays(1)
    
    // Check if user was active yesterday
    val wasActiveYesterday = checkActivity(userId, yesterday)
    val isActiveToday = checkActivity(userId, today)
    
    if (isActiveToday) {
        if (wasActiveYesterday) {
            // Continue streak
            incrementStreak(userId)
        } else {
            // Streak broken, restart
            resetStreak(userId)
        }
    }
}
```

---

## 💻 Technical Implementation

### GamificationRepository.kt

```kotlin
class GamificationRepository(private val db: FirebaseFirestore) {
    
    fun awardXP(userId: String, action: String, callback: (Int) -> Unit) {
        val xp = xpRewards[action] ?: 0
        
        val gamificationRef = db.collection("users").document(userId)
            .collection("gamification").document("xp_level")
        
        db.runTransaction { transaction ->
            val snapshot = transaction.get(gamificationRef)
            val currentXP = snapshot.getLong("totalXP")?.toInt() ?: 0
            val newXP = currentXP + xp
            val newLevel = calculateLevel(newXP)
            
            transaction.set(gamificationRef, mapOf(
                "totalXP" to newXP,
                "currentLevel" to newLevel,
                "xpForNextLevel" to xpForNextLevel(newLevel),
                "lastUpdated" to FieldValue.serverTimestamp()
            ), SetOptions.merge())
            
            newLevel
        }.addOnSuccessListener { newLevel ->
            callback(newLevel)
            checkForBadges(userId, action)
        }
    }
    
    private fun checkForBadges(userId: String, action: String) {
        // Check if any badges should be unlocked
        when (action) {
            "achieve_step_goal" -> checkStepBadges(userId)
            "maintain_streak" -> checkStreakBadges(userId)
            "log_exercise" -> checkExerciseBadges(userId)
        }
    }
}
```

---

## 🎨 UI Components

### BadgesActivity.kt
- Grid layout showing all badges
- Locked badges shown in grayscale
- Unlocked badges in full color
- Progress bars for badges in progress

### GamificationActivity.kt
- Current level and XP display
- Progress bar to next level
- Recent achievements
- Leaderboard integration

---

## 📊 Firestore Structure

```json
{
  "gamification": {
    "xp_level": {
      "totalXP": 2500,
      "currentLevel": 5,
      "xpForNextLevel": 2500
    },
    "badges": {
      "badges": [
        {
          "badgeId": "step_master_bronze",
          "earnedAt": "2026-02-10T10:00:00Z"
        }
      ]
    },
    "streaks": {
      "currentStreak": 15,
      "longestStreak": 30,
      "lastActivityDate": "2026-02-16"
    }
  }
}
```

---

## 🚀 Future Improvements

1. **Challenges**: Weekly/monthly challenges
2. **Leaderboards**: Global and friend leaderboards
3. **Rewards**: Unlock themes, avatars with XP
4. **Achievements**: More diverse achievement types
5. **Social**: Share achievements on social media

---

**[← Back to Main README](../README.md)** | **[Next: Features Overview →](features_overview.md)**
