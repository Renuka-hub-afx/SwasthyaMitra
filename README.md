# SwasthyaMitra - Complete Health & Fitness Companion

## 📱 Project Overview

**SwasthyaMitra** (Sanskrit: स्वास्थ्यमित्र - "Health Friend") is a comprehensive Android health and fitness application designed to help users achieve their wellness goals through AI-powered recommendations, gamification, and real-time tracking.

### 🎯 Vision
To provide a holistic, intelligent, and engaging platform that makes health management accessible, personalized, and enjoyable for everyone.

---

## ✨ Key Features

### 1. **User Management & Authentication**
- Secure Firebase Authentication
- User profile management with customizable avatars
- Personalized health metrics tracking
- [📖 Detailed Documentation](docs/01_authentication.md)

### 2. **AI-Powered Smart Diet Planning**
- Gemini AI integration for personalized meal recommendations
- Calorie and macro-nutrient tracking
- Indian cuisine-focused meal plans
- Quick food logging from meal cards
- [📖 Detailed Documentation](docs/02_smart_diet.md)

### 3. **Exercise & Workout Management**
- Manual exercise logging with 800+ exercises database
- Real-time calorie burn calculation
- Exercise history and analytics
- Workout dashboard with progress tracking
- [📖 Detailed Documentation](docs/03_exercise_tracking.md)

### 4. **Step Counter & Activity Tracking**
- Real-time step counting using accelerometer
- Hybrid validation system for accuracy
- Foreground service for continuous tracking
- Daily step goals and calorie burn tracking
- [📖 Detailed Documentation](docs/04_step_counter.md)

### 5. **Sleep Tracker**
- Sleep duration tracking
- Sleep quality analysis
- Historical sleep patterns
- Personalized sleep recommendations
- [📖 Detailed Documentation](docs/05_sleep_tracker.md)

### 6. **Weight Progress Monitoring**
- Weight logging and trend analysis
- BMI calculation and tracking
- Predictive weight projection using linear regression
- Visual progress charts
- [📖 Detailed Documentation](docs/06_weight_progress.md)

### 7. **Mood-Based Recommendations**
- Mood tracking and analysis
- AI-powered activity suggestions based on mood
- Emotional wellness support
- [📖 Detailed Documentation](docs/07_mood_recommendations.md)

### 8. **Barcode Scanner for Nutrition**
- Real-time barcode scanning
- Nutrition information lookup
- Quick food logging
- [📖 Detailed Documentation](docs/08_barcode_scanner.md)

### 9. **Gamification System**
- Points and XP system
- Achievement badges (Bronze, Silver, Gold, Platinum)
- Daily streaks tracking
- Leaderboards and challenges
- [📖 Detailed Documentation](docs/09_gamification.md)

### 10. **AI Coach**
- Personalized health coaching
- Context-aware recommendations
- Women's health mode support
- [📖 Detailed Documentation](docs/10_ai_coach.md)

### 11. **Safety & Emergency Features**
- SOS emergency contacts
- Location sharing
- Safety check-ins
- [📖 Detailed Documentation](docs/11_safety_features.md)

### 12. **Smart Pantry Management**
- Ingredient inventory tracking
- Expiry date monitoring
- Recipe suggestions based on available ingredients
- [📖 Detailed Documentation](docs/12_smart_pantry.md)

---

## 🏗️ Technical Architecture

### Technology Stack

#### **Frontend**
- **Language**: Kotlin
- **UI Framework**: Android XML Layouts, Material Design Components
- **Architecture**: MVVM (Model-View-ViewModel)
- **Navigation**: Intent-based navigation

#### **Backend & Services**
- **Authentication**: Firebase Authentication
- **Database**: Cloud Firestore (NoSQL)
- **Storage**: Firebase Storage
- **AI/ML**: Google Gemini AI API
- **Maps**: Google Maps SDK
- **Barcode**: ML Kit Barcode Scanning

#### **Sensors & Hardware**
- Accelerometer (Step counting)
- Camera (Barcode scanning)
- GPS (Location tracking)

#### **Key Libraries**
- MPAndroidChart (Data visualization)
- Glide (Image loading)
- Gson (JSON parsing)
- Kotlin Coroutines (Asynchronous operations)
- Material Components (UI)

### Project Structure

```
SwasthyaMitra/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/swasthyamitra/
│   │   │   │   ├── activities/          # All activity classes
│   │   │   │   ├── adapters/            # RecyclerView adapters
│   │   │   │   ├── ai/                  # AI service classes
│   │   │   │   ├── auth/                # Authentication logic
│   │   │   │   ├── data/                # Data models
│   │   │   │   ├── fragments/           # Fragment classes
│   │   │   │   ├── models/              # Data classes
│   │   │   │   ├── notifications/       # Notification handlers
│   │   │   │   ├── receivers/           # Broadcast receivers
│   │   │   │   ├── repository/          # Data repositories
│   │   │   │   ├── services/            # Background services
│   │   │   │   ├── step/                # Step tracking logic
│   │   │   │   ├── utils/               # Utility classes
│   │   │   │   └── ui/                  # Custom UI components
│   │   │   ├── res/
│   │   │   │   ├── layout/              # XML layouts
│   │   │   │   ├── drawable/            # Images & drawables
│   │   │   │   ├── values/              # Colors, strings, themes
│   │   │   │   └── mipmap/              # App icons
│   │   │   └── assets/                  # CSV data files
│   │   └── AndroidManifest.xml
│   └── build.gradle
├── docs/                                # Feature documentation
├── firestore.rules                      # Firestore security rules
├── firebase.json                        # Firebase configuration
└── README.md                            # This file
```

---

## 🚀 Getting Started

### Prerequisites
- Android Studio Arctic Fox or later
- JDK 11 or higher
- Android SDK (API 26+)
- Firebase project setup
- Google Gemini API key

### Installation Steps

1. **Clone the Repository**
   ```bash
   git clone https://github.com/yourusername/SwasthyaMitra.git
   cd SwasthyaMitra
   ```

2. **Firebase Setup**
   - Create a Firebase project at [Firebase Console](https://console.firebase.google.com/)
   - Download `google-services.json` and place it in `app/` directory
   - Enable Authentication (Email/Password)
   - Enable Cloud Firestore
   - Enable Firebase Storage

3. **API Keys Configuration**
   - Get Gemini API key from [Google AI Studio](https://makersuite.google.com/app/apikey)
   - Add to `local.properties`:
     ```
     GEMINI_API_KEY=your_api_key_here
     ```

4. **Build and Run**
   ```bash
   ./gradlew assembleDebug
   ```
   Or use Android Studio's Run button

---

## 📊 Database Schema

### Firestore Collections Structure

```
users/
  └── {userId}/
      ├── profile (document)
      ├── daily_steps/ (subcollection)
      ├── exercise_logs/ (subcollection)
      ├── food_logs/ (subcollection)
      ├── sleep_logs/ (subcollection)
      ├── weight_logs/ (subcollection)
      ├── mood_logs/ (subcollection)
      ├── gamification/ (subcollection)
      └── pantry_items/ (subcollection)
```

[📖 Complete Database Documentation](docs/database_schema.md)

---

## 🎨 Design System

### Color Palette
- **Primary**: Purple (#7B2CBF)
- **Secondary**: Pink (#E91E63)
- **Background**: Light Gray (#FAFAFA)
- **Success**: Green (#388E3C)
- **Warning**: Orange (#F57C00)

### Typography
- **Headers**: Bold, 20-24sp
- **Body**: Regular, 14-16sp
- **Captions**: 12sp

### UI Components
- Material Design 3 components
- Custom gradient buttons
- Card-based layouts
- Bottom navigation

---

## 🔒 Security & Privacy

### Data Protection
- All user data encrypted in transit (HTTPS)
- Firebase Authentication for secure access
- Firestore security rules for data access control
- No third-party data sharing

### Permissions Required
- **Camera**: Barcode scanning
- **Location**: Safety features, map tracking
- **Activity Recognition**: Step counting
- **Foreground Service**: Continuous step tracking
- **Boot Completed**: Auto-start step counter

---

## 📈 Performance Optimizations

1. **Lazy Loading**: RecyclerViews with pagination
2. **Image Caching**: Glide for efficient image loading
3. **Background Processing**: Coroutines for async operations
4. **Foreground Services**: Efficient step counting
5. **Data Batching**: Periodic Firestore writes to reduce costs

---

## 🧪 Testing

### Unit Tests
- Repository layer tests
- Utility function tests
- Data validation tests

### Integration Tests
- Firebase integration tests
- API integration tests

### UI Tests
- Espresso UI tests for critical flows

---

## 🤝 Contributing

We welcome contributions! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👥 Team

- **Developer**: [Your Name]
- **Project Guide**: [Guide Name]
- **Institution**: [Your Institution]

---

## 📞 Contact & Support

- **Email**: support@swasthyamitra.com
- **GitHub Issues**: [Report a bug](https://github.com/yourusername/SwasthyaMitra/issues)
- **Documentation**: [Full Docs](docs/)

---

## 🙏 Acknowledgments

- Google Gemini AI for intelligent recommendations
- Firebase for backend infrastructure
- Material Design for UI components
- Open-source community for libraries and tools

---

## 📚 Additional Resources

- [User Manual](docs/user_manual.md)
- [API Documentation](docs/api_documentation.md)
- [Troubleshooting Guide](docs/troubleshooting.md)
- [FAQ](docs/faq.md)

---

**Made with ❤️ for a healthier tomorrow**
