# AI-Powered Workout Recommendation System

## Overview
SwasthyaMitra features an intelligent AI-based exercise recommendation engine that generates personalized 15-minute workout plans tailored to each user's unique fitness profile.

## How It Works

### User Input Parameters
The AI analyzes three key factors:

1. **Fitness Goal**
   - Weight Loss
   - Weight Gain
   - Maintain Weight

2. **Calorie Balance** (Automatically calculated)
   - **High**: Consumed >100 kcal above target
   - **Balanced**: Within ±100 kcal of target
   - **Low**: Consumed >100 kcal below target

3. **Intensity Level** (Automatically determined)
   - **High**: Calorie surplus >200 kcal
   - **Moderate**: Within ±200 kcal
   - **Low**: Calorie deficit >200 kcal

### Video Library Categories

#### Weight Loss Videos (HIIT & Cardio)
- 15 Min Fat Burning HIIT
- 15 Min Standing HIIT  
- 15 Min Full Body HIIT
- 15 Min Cardio Fat Burn
- 15 Min Dance Cardio

#### Weight Gain Videos (Strength Training)
- 15 Min Full Body Strength
- 15 Min Lower Body Strength
- 15 Min Upper Body Workout
- 15 Min No Equipment Strength

#### Maintenance Videos (Balanced Workouts)
- 15 Min Yoga Flow
- 15 Min Morning Yoga
- 15 Min Pilates Core
- 15 Min Stretching Routine

## AI Recommendation Logic

### Weight Loss Goal

| Calorie Status | Recommendation Strategy | Videos Provided |
|---------------|------------------------|-----------------|
| **High** | Maximum intensity fat burning | 3x HIIT (45 min total) |
| **Low** | Moderate cardio to avoid burnout | 2x Cardio + 1x HIIT (45 min) |
| **Balanced** | Optimal HIIT & cardio mix | 1x HIIT + 1x Cardio + 1x HIIT (45 min) |

**Why?**
- High calorie intake requires intensive exercise to create calorie deficit
- Low calorie intake needs gentler exercise to prevent metabolic slowdown
- Balanced intake supports optimal fat-burning regime

### Weight Gain Goal

| Calorie Status | Recommendation Strategy | Videos Provided |
|---------------|------------------------|-----------------|
| **High** | Perfect for muscle building | 3x Strength (random) (45 min) |
| **Low** | Light strength + recovery | 2x Strength + 1x Yoga (45 min) |
| **Balanced** | Optimal strength training | 3x Strength (sequential) (45 min) |

**Why?**
- High calories provide fuel for muscle growth during strength training
- Low calories limit muscle building - system recommends recovery
- Balanced calories support consistent muscle development

### Maintenance Goal

| Calorie Status | Recommendation Strategy | Videos Provided |
|---------------|------------------------|-----------------|
| **High** | Need calorie burn | 2x Cardio + 1x Yoga (45 min) |
| **Low** | Gentle recovery focus | 3x Yoga/Flexibility (45 min) |
| **Balanced** | Perfect balance | Yoga + Pilates + Flexibility (45 min) |

**Why?**
- High intake requires some calorie expenditure
- Low intake needs energy-preserving gentle exercises
- Balanced intake maintains wellness without extreme effort

## Personalized AI Messages

The system provides contextual feedback:

### Weight Loss Examples
- ⚡ **High Calories**: "High calorie intake detected! We've selected intense HIIT workouts to maximize fat burn."
- 🔥 **Balanced**: "Perfect balance! Your HIIT & cardio mix will optimize fat burning."
- 💪 **Low Calories**: "Lower calorie intake - we've balanced cardio with moderate intensity to avoid burnout."

### Weight Gain Examples
- 💪 **High Calories**: "Excellent! High calories + strength training = optimal muscle growth."
- 🏋️ **Balanced**: "Great! Your strength training routine will support muscle building."
- ⚠️ **Low Calories**: "Low calories may limit gains. We've added lighter exercises - consider eating more."

### Maintenance Examples
- 🧘 **High Calories**: "Maintenance mode: We've added cardio to burn extra calories while staying balanced."
- ✨ **Balanced**: "Perfectly balanced! Your yoga & flexibility routine maintains wellness."
- 🌸 **Low Calories**: "Gentle recovery workout selected - yoga & stretching to energize without overexertion."

## Safety Features

1. **Fallback System**: If recommendation logic fails, system defaults to safe balanced maintenance workouts
2. **Duration Guarantee**: Every workout is exactly 15 minutes (total: 45 minutes for 3 videos)
3. **Intensity Matching**: Exercise intensity never exceeds user's current energy availability
4. **Progressive Loading**: Videos can be completed individually (15 min sessions)

## Technical Implementation

### Repository Pattern
```kotlin
WorkoutVideoRepository.getSmartRecommendation(
    goalType: String,      // "Weight Loss", "Weight Gain", "Maintenance"
    calorieStatus: String, // "High", "Low", "Balanced"
    intensity: String      // "High", "Moderate", "Low"
): List<WorkoutVideo>
```

### Video Model
```kotlin
data class WorkoutVideo(
    val videoId: String,        // YouTube video ID
    val title: String,          // Display name
    val category: String,       // HIIT, Cardio, Strength, Yoga, etc.
    val durationMinutes: Int    // Always 15 minutes
)
```

### Total Duration Calculation
```kotlin
WorkoutVideoRepository.getTotalDuration(videos: List<WorkoutVideo>): Int
// Returns: Sum of all video durations (always 45 for 3 videos)
```

## Video Selection Criteria

Every video in the library meets these standards:
- ✅ **Duration**: Exactly 15 minutes
- ✅ **Quality**: From verified fitness professionals
- ✅ **Accessibility**: Publicly available on YouTube
- ✅ **Relevance**: Directly supports the associated fitness goal
- ✅ **Safety**: No advanced moves that risk injury
- ✅ **Equipment**: Minimal or no equipment required

## Future Enhancements

1. **User Preferences**: Allow users to favorite/block specific video types
2. **Progress Tracking**: Suggest progression to harder workouts over time
3. **Variety Algorithm**: Ensure diverse recommendations across weeks
4. **Custom Duration**: Support 10, 20, or 30-minute preferences
5. **Multi-Platform**: Support videos from Vimeo, self-hosted sources
6. **Offline Mode**: Download videos for offline workout sessions
