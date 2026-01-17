# SwasthyaMitra - Setup Guide

## Getting Started

Welcome to SwasthyaMitra! This guide will help you set up the project locally and configure the necessary Firebase credentials.

## Prerequisites

- Android Studio (latest version recommended)
- Java Development Kit (JDK) 8 or higher
- Android SDK with API level 30+
- Firebase project already created and configured

## Initial Setup

### 1. Firebase Configuration (REQUIRED)

The real `google-services.json` file is **intentionally excluded from Git** for security reasons. You need to add it manually:

#### Steps:

1. **Get your Firebase credentials:**
   - Go to [Firebase Console](https://console.firebase.google.com/)
   - Select your SwasthyaMitra project
   - Navigate to **Project Settings** → **General**
   - Scroll down and click the **Google-Services.json** download link for Android

2. **Place the file in the correct location:**
   ```
   Copy the downloaded google-services.json to:
   
   app/google-services.json
   ```

3. **Reference file (for documentation only):**
   - See `app/google-services-example.json` for the expected structure
   - Never commit the actual credentials file

### 2. Local Properties (OPTIONAL)

If you don't have `local.properties`, it will be auto-generated. This file contains:
- SDK locations
- API keys (local only)

The `local.properties` file is also excluded from Git. Create it if needed:

```properties
sdk.dir=/path/to/your/Android/SDK
# Add other local properties as needed
```

### 3. Build the Project

```bash
# Using Gradle wrapper
./gradlew build

# Or clean and build
./gradlew clean build
```

### 4. Run the App

```bash
# Install debug APK on connected device/emulator
./gradlew installDebug

# Or run directly from Android Studio
# Press Shift + F10 (Windows/Linux) or Ctrl + R (Mac)
```

## Project Structure

```
SwasthyaMitra/
├── app/
│   ├── src/
│   │   ├── main/          # Main application source
│   │   ├── androidTest/   # Instrumented tests
│   │   └── test/          # Unit tests
│   ├── google-services.json         (⚠️ IGNORED by Git - Add your own!)
│   ├── google-services-example.json (📋 Reference structure)
│   └── build.gradle       # App-level dependencies
├── gradle/
│   └── libs.versions.toml # Gradle version catalog
├── build.gradle           # Project-level configuration
├── settings.gradle        # Project structure
├── BMR_TDEE_CALCULATION.md       # Feature documentation
├── FIREBASE_RULES_FINAL.md       # Firebase security rules
├── FIREBASE_SECURITY_RULES.md    # Security implementation
├── SECURITY.md                    # Security best practices
└── .gitignore             # Git exclusions (sensitive files protected)
```

## Security & Git Configuration

### Protected Files (Not in Git)

The following files contain sensitive information and are **automatically excluded** by `.gitignore`:

- `app/google-services.json` - Firebase credentials
- `local.properties` - Local build configuration
- Any `.keystore` or `.jks` files - Signing keys
- Any `.env` or `.key` files - API keys

### Safety Checks

Before committing, verify these files are not staged:

```bash
git status
```

Should NOT show:
- `app/google-services.json`
- `local.properties`
- Any API keys or credentials

## Firebase Setup

### Database Rules

The Firebase Firestore security rules are documented in:
- `FIREBASE_RULES_FINAL.md` - Final production-ready rules
- `FIREBASE_SECURITY_RULES.md` - Implementation details

Apply these rules in the Firebase Console under **Firestore Database** → **Rules**.

## Documentation

- **BMR/TDEE Calculations:** See `BMR_TDEE_CALCULATION.md`
- **Security Practices:** See `SECURITY.md`
- **Firebase Configuration:** See `FIREBASE_RULES_FINAL.md`

## Troubleshooting

### Build fails with "google-services.json not found"

**Solution:** You need to add the real file at `app/google-services.json`

```bash
# Verify the file exists
ls -la app/google-services.json  # Mac/Linux
dir app\google-services.json     # Windows
```

### "google-services.json is being tracked by Git"

This should not happen. If it does, remove it from Git history:

```bash
git rm --cached app/google-services.json
git commit -m "Stop tracking sensitive files"
```

### Gradle build fails

Try clean rebuild:

```bash
./gradlew clean build --refresh-dependencies
```

## Development Workflow

1. **Pull changes:** `git pull origin main`
2. **Create feature branch:** `git checkout -b feature/your-feature`
3. **Make changes** and test locally
4. **Verify no credentials are staged:** `git status`
5. **Commit:** `git commit -m "Your message"`
6. **Push:** `git push origin feature/your-feature`
7. **Create Pull Request** on GitHub

## Contributing

When contributing to this project:

- ✅ Always verify sensitive files are NOT in your commits
- ✅ Use the example files as reference
- ✅ Keep documentation updated
- ✅ Follow security best practices from `SECURITY.md`

## Support

For Firebase issues, refer to the [official Firebase documentation](https://firebase.google.com/docs/).

For Android development, check [Android Developer Documentation](https://developer.android.com/).

---

**Last Updated:** January 17, 2026
**Security Status:** ✅ Production-Ready
