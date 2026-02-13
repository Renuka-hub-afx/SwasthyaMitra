# Sleep Tracking Feature Documentation

## Overview
The Sleep Tracking feature in SwasthyaMitra allows users to monitor their sleep patterns, track sleep quality, and maintain healthy sleep habits. The system automatically calculates sleep duration, quality metrics, and provides insights to help users achieve their sleep goals.

## Features

### 1. **Homepage Sleep Card**
Located on the main homepage, displaying:
- **Last Night's Sleep**: Duration of the most recent sleep session
- **7-Day Average**: Average sleep duration over the past week
- **Sleep Quality**: Categorized as Good, Fair, or Poor based on duration
- **Goal Progress**: Visual progress bar showing percentage of 8-hour goal achieved
- **Sleep Streak**: Consecutive days of achieving 7+ hours of sleep
- **Quick Access**: Direct button to open detailed Sleep Tracker

### 2. **Sleep Tracker Activity**
Dedicated activity for logging sleep with:

#### Manual Sleep Logging
- **Sleep Time Picker**: Select when you went to bed
- **Wake Time Picker**: Select when you woke up
- **Automatic Duration Calculation**: Calculates hours and minutes slept
- **Quality Rating**: Choose from Poor, Fair, Good, or Excellent
- **Overnight Detection**: Automatically handles sleep sessions crossing midnight
- **Date Adjustment**: Smart handling of past times

#### Quick Log Options
- **7-Hour Button**: Instantly log 7 hours of sleep
- **8-Hour Button**: Instantly log 8 hours of sleep
- Auto-assigns quality based on duration

#### Sleep Statistics
- **Total Nights Tracked**: Number of sleep sessions in last 7 days
- **Average Duration**: Mean sleep hours per night
- **Good Nights Counter**: Days with 7+ hours of sleep
- **Progress Tracking**: Visual progress toward "Sleep Saint" achievement
- **Achievement System**: "🎉 Sleep Saint achieved!" when 7/7 good nights completed

### 3. **Automatic Sleep Insights**
The system provides:
- **Quality Recommendations**: Based on duration
  - < 5 hours: "⚠️ Too short - Poor quality likely"
  - 5-6 hours: "😴 Fair - Could be better"
  - 7-9 hours: "✅ Optimal - Good quality"
  - > 9 hours: "😴 Long - Check if oversleeping"
- **Motivational Messages**: Encouragement based on streaks
- **Color-Coded Indicators**: Visual feedback on sleep quality

## Technical Implementation

### Data Storage
Sleep logs are stored in Firestore under:
```
users/{userId}/sleep_logs/{logId}
```

#### Sleep Log Structure
```kotlin
{
    "userId": String,
    "sleepTime": String,          // Formatted time (e.g., "11:30 PM")
    "wakeTime": String,           // Formatted time (e.g., "7:00 AM")
    "sleepTimeMillis": Long,      // Timestamp in milliseconds
    "wakeTimeMillis": Long,       // Timestamp in milliseconds
    "durationHours": Double,      // Duration in hours (e.g., 7.5)
    "durationMinutes": Double,    // Duration in minutes (e.g., 450.0)
    "quality": String,            // "poor", "fair", "good", "excellent"
    "date": String,               // Date of sleep start (YYYY-MM-DD)
    "timestamp": Timestamp,       // Firestore timestamp
    "source": String              // "manual", "manual_quick", or "auto"
}
```

### Firebase Rules
Sleep logs are user-specific and protected:
```javascript
match /users/{userId}/sleep_logs/{logId} {
    allow read, write: if request.auth.uid == userId;
}
```

### Key Classes and Functions

#### Homepage.kt
- `loadSleepData()`: Fetches and displays sleep statistics
  - Retrieves last 7 days of sleep data
  - Calculates averages and streaks
  - Updates UI with formatted data
  - Color-codes quality indicators

- UI Components:
  ```kotlin
  tvLastSleepDuration: TextView      // Last night's sleep
  tvAvgSleepDuration: TextView       // 7-day average
  tvSleepQuality: TextView           // Quality indicator
  tvSleepGoalPercentage: TextView    // Percentage of goal
  pbSleepGoal: ProgressBar           // Visual goal progress
  btnTrackSleep: MaterialButton      // Opens tracker
  tvSleepStreak: TextView            // Streak counter
  ```

#### SleepTrackerActivity.kt
- `logSleep()`: Saves manual sleep entry
  - Validates sleep/wake times
  - Handles overnight sessions
  - Calculates duration
  - Stores in Firestore

- `quickLogSleep(hours)`: Quick logging
  - Calculates retroactive sleep time
  - Auto-assigns quality
  - Instant feedback

- `loadSleepData()`: Updates statistics
  - Fetches recent logs
  - Calculates averages
  - Updates progress bars
  - Checks achievements

## Sleep Quality Metrics

### Quality Categories
- **Good**: 7-9 hours of sleep
- **Fair**: 6-7 hours of sleep
- **Poor**: < 6 hours of sleep

### Sleep Goal
- **Target**: 8 hours (480 minutes)
- **Good Sleep Threshold**: 7+ hours (420 minutes)
- **Progress Calculation**: `(actual / target) * 100%`

### Streak Tracking
- Counts consecutive days with ≥7 hours of sleep
- Resets if a day has <7 hours
- Displayed with fire emoji (🔥) on homepage
- Motivates consistency

## User Benefits

1. **Health Awareness**: Visual feedback on sleep patterns
2. **Goal Setting**: 8-hour sleep goal with progress tracking
3. **Consistency**: Streak system encourages regular sleep
4. **Easy Logging**: Multiple input methods (manual, quick buttons)
5. **Historical Data**: 7-day rolling analysis
6. **Quality Insights**: Automatic quality assessment
7. **Motivational**: Achievements and encouraging messages

## Usage Flow

### First-Time User
1. User sees sleep card on homepage (shows "--" for no data)
2. Clicks "Track Sleep" button
3. Opens SleepTrackerActivity
4. Logs first sleep session (manual or quick button)
5. Returns to homepage to see updated statistics

### Regular User
1. User wakes up
2. Opens app (directly to homepage)
3. Sees last night's sleep already displayed
4. Can click "Track Sleep" to log if needed
5. Views 7-day average and streak
6. Motivated by progress toward goals

### Quick Logging
1. User opens Sleep Tracker
2. Clicks "7 Hours" or "8 Hours" button
3. Instantly logged with auto-assigned quality
4. Returns to homepage to see updated card

## Design Decisions

### Why 7+ Hours = "Good Sleep"?
- Based on medical recommendations (7-9 hours for adults)
- Provides achievable goal for most users
- Aligns with health guidelines

### Why 7-Day Rolling Window?
- Recent enough to be relevant
- Long enough to show patterns
- Matches weekly lifestyle cycles
- Manageable data volume

### Why Manual + Quick Buttons?
- **Manual**: Accurate tracking for detailed users
- **Quick**: Fast logging for busy users
- **Flexibility**: Accommodates different user preferences

### Why Show on Homepage?
- **Visibility**: Sleep is core health metric
- **Engagement**: Constant reminder to track
- **Motivation**: Seeing progress encourages consistency
- **Convenience**: No need to navigate deep into app

## Future Enhancements

### Planned Features
1. **Automatic Detection**: Use device sensors to detect sleep/wake
2. **Smart Alarms**: Optimal wake time suggestions
3. **Sleep Trends**: Monthly/yearly analytics
4. **Sleep Debt Tracking**: Cumulative sleep deficit
5. **Bedtime Reminders**: Notifications for consistent schedule
6. **Sleep Environment**: Tips for better sleep quality
7. **Integration**: Sync with fitness trackers
8. **AI Insights**: Personalized sleep recommendations

### Automatic Detection (Coming Soon)
```kotlin
// Planned implementation
- Use device motion sensors
- Detect reduced activity periods
- Auto-log sleep sessions
- Confirm with user in morning
```

## Accessibility

- **Color-Coded**: Green (good), Orange (fair), Red (poor)
- **Text Labels**: Clear descriptions for all metrics
- **Large Touch Targets**: Easy-to-tap buttons
- **Clear Hierarchy**: Logical information layout
- **Consistent Icons**: 😴 💤 🔥 for quick recognition

## Performance Considerations

- **Lazy Loading**: Only loads last 7 days
- **Caching**: Reduces Firestore reads
- **Efficient Queries**: Indexed by timestamp
- **Minimal UI Updates**: Only on data change
- **Background Processing**: Non-blocking calculations

## Testing Scenarios

### Test Cases
1. **No Data**: Verify "--" displayed
2. **Single Entry**: Correct calculation and display
3. **7 Days**: Proper averaging
4. **Overnight Sleep**: Midnight crossing handled
5. **Quick Log**: Instant update
6. **Quality**: Correct color coding
7. **Streak**: Accurate counting
8. **Goal Progress**: Percentage calculation

## Support & Troubleshooting

### Common Issues
- **"--" displayed**: No sleep data yet - log first session
- **Incorrect duration**: Check time picker selections
- **Missing streak**: Ensure 7+ hours consistently
- **Data not loading**: Check internet connection

## Conclusion

The Sleep Tracking feature provides comprehensive sleep monitoring with:
- ✅ Easy manual and quick logging
- ✅ Automatic calculations and insights
- ✅ Visual progress tracking
- ✅ Motivational streak system
- ✅ Integration with homepage dashboard
- ✅ User-friendly interface
- ✅ Health-focused recommendations

**Goal**: Help users achieve consistent, quality sleep for better health and wellness.

