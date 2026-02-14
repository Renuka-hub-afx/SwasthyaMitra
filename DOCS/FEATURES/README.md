# Feature Documentation Index

This directory contains detailed documentation for all SwasthyaMitra features.

## 📚 Feature Guides

### 🆕 Latest Addition: AI Exercise Recommendation System
**Comprehensive 3-Part Documentation**:
- **[User Guide](AI_EXERCISE_USER_GUIDE.md)** - How to use AI workouts with screenshots and tips
- **[Feature Documentation](AI_EXERCISE_RECOMMENDATION.md)** - Complete overview, technology stack, and capabilities
- **[Technical Guide](AI_EXERCISE_TECHNICAL_GUIDE.md)** - Implementation, architecture, Firebase integration

**Status**: ✅ **Production Ready** | **Location**: Workout Dashboard Only

**Key Features**:
- 🤖 Gemini 2.0 Flash AI personalization
- 📊 3500+ exercises with GIFs
- 🌸 Period mode safety filtering
- 😊 Mood-based intensity adjustment
- 🔥 Calorie balance integration
- 📈 Real-time Firebase logging
- 🏆 XP rewards & gamification

---

### Core Features
1. **[Authentication & Onboarding](01_AUTHENTICATION.md)** - User registration, login, profile setup
2. **[Food Logging System](02_FOOD_LOGGING.md)** - Search, scan, log meals
3. **[AI Diet Planning](03_AI_DIET.md)** - Gemini-powered meal recommendations
4. **[Exercise Tracking](04_EXERCISE.md)** - Workouts, AI suggestions, manual logging
5. **[Progress Dashboard](05_PROGRESS.md)** - Graphs, analytics, insights
6. **[Gamification System](06_GAMIFICATION.md)** - 6-stage achievements, streaks
7. **[Women's Health](07_WOMENS_HEALTH.md)** - Period tracking, cycle insights
8. **[Safety Features](08_SAFETY.md)** - SOS alerts, run tracking
9. **[Hydration Tracking](09_HYDRATION.md)** - Water intake, smart reminders
10. **[Smart Pantry](10_SMART_PANTRY.md)** - Recipe from ingredients photo

### Additional Features
11. **[Mood & Wellness](11_MOOD_WELLNESS.md)** - Mental health tracking
12. **[Profile & Settings](12_PROFILE.md)** - User customization, preferences
13. **[Notifications](13_NOTIFICATIONS.md)** - Smart reminders, alerts
14. **[Social Features](14_SOCIAL.md)** - Challenges, leaderboards

---

## 📖 How to Use This Documentation

Each feature document includes:
- **Overview**: What the feature does
- **How It Works**: Technical implementation
- **User Flow**: Step-by-step usage
- **Code Files**: Related source files
- **Database**: Firestore collections used
- **UI Components**: Screens and layouts
- **Technology**: Libraries and APIs used
- **How to Use**: User guide
- **Troubleshooting**: Common issues

---

## 🎯 Quick Reference

### For Users
- **NEW!** AI personalized workouts? → See [AI Exercise User Guide](AI_EXERCISE_USER_GUIDE.md)
- Want to log food? → See [Food Logging](02_FOOD_LOGGING.md)
- Need meal ideas? → See [AI Diet Planning](03_AI_DIET.md)
- Track progress? → See [Progress Dashboard](05_PROGRESS.md)
- Period tracking? → See [Women's Health](07_WOMENS_HEALTH.md)

### For Developers
- Authentication flow? → See [Authentication](01_AUTHENTICATION.md)
- AI integration? → See [AI Diet Planning](03_AI_DIET.md)
- Database structure? → See individual feature docs
- Adding new features? → Follow existing patterns

---

## 📱 Feature Matrix

| Feature | Activity/Service | Database Collection | AI Used | Status |
|---------|------------------|---------------------|---------|--------|
| Authentication | LoginActivity, SignupActivity | users/ | ❌ | ✅ Complete |
| Food Logging | FoodLogActivity | foodLogs/ | ❌ | ✅ Complete |
| AI Diet | AISmartDietActivity | meal_feedback/ | ✅ Gemini | ✅ Complete |
| Exercise | ExerciseLogActivity | exercise_logs/ | ✅ Gemini | ✅ Complete |
| Progress | EnhancedProgressDashboardActivity | Multiple | ❌ | ✅ Complete |
| Gamification | GamificationActivity | Realtime DB | ❌ | ✅ Complete |
| Women's Health | PeriodTrackingActivity | period_logs/ | ✅ Gemini | ✅ Complete |
| Safety | SafetyActivity | - | ❌ | ✅ Complete |
| Hydration | HydrationActivity | hydration_logs/ | ❌ | ✅ Complete |
| Smart Pantry | SmartPantryActivity | - | ✅ Gemini | ✅ Complete |

---

## 🛠️ Technology Stack by Feature

### AI-Powered Features
- **AI Diet Planning**: Firebase AI + Gemini 2.0 Flash
- **Smart Pantry**: Firebase AI + Gemini 2.0 Flash + Image Analysis
- **AI Coach**: Firebase AI + Gemini 2.0 Flash
- **Exercise Recommendations**: Firebase AI + Gemini 2.0 Flash

### Data Storage
- **Cloud Firestore**: Primary database ("renu" instance)
- **Realtime Database**: Legacy gamification data
- **SharedPreferences**: Local user settings

### UI/UX
- **Material Design 3**: All UI components
- **MPAndroidChart**: Progress graphs
- **Glide**: Image loading
- **CameraX**: Camera features

---

## 📊 Feature Usage Analytics

Track these metrics for each feature:
- Total users who used feature
- Daily active users per feature
- Average session time per feature
- User retention per feature
- Error rate per feature

Access via Firebase Console → Analytics

---

## 🔄 Feature Dependencies

```
Authentication
    ↓
Onboarding (UserInfo → Goal → Lifestyle)
    ↓
Homepage
    ├── Food Logging → AI Diet Plans
    ├── Exercise → AI Recommendations
    ├── Hydration Tracking
    ├── Progress Dashboard → Gamification
    └── Women's Health → Period Mode
```

---

## 🎨 UI/UX Guidelines

All features follow these design principles:
1. **Consistency**: Same color scheme and components
2. **Simplicity**: Minimal steps to complete actions
3. **Feedback**: Clear success/error messages
4. **Accessibility**: Large touch targets, readable fonts
5. **Offline Graceful**: Inform users when online required

---

## 🐛 Common Issues Across Features

### Data Not Saving
- **Cause**: Firestore rules or network issue
- **Solution**: Check Firebase Console, deploy rules
- **File**: firestore.rules

### AI Not Responding
- **Cause**: API quota exceeded or network timeout
- **Solution**: Check Firebase AI usage, handle timeouts
- **Files**: ai/ directory services

### UI Elements Missing
- **Cause**: Layout file missing IDs
- **Solution**: Verify findViewById calls match layout
- **Files**: res/layout/*.xml

---

## 📱 Testing Checklist

For each feature, test:
- [ ] Happy path (normal usage)
- [ ] Error handling (network failure)
- [ ] Edge cases (empty data, max limits)
- [ ] UI responsiveness
- [ ] Data persistence
- [ ] Navigation flow

---

## 🚀 Feature Request Process

To request a new feature:
1. Document use case
2. Design user flow
3. Identify required collections
4. List dependencies
5. Estimate complexity
6. Create implementation plan

---

*Last Updated: February 12, 2026*

