# 🚀 Firebase Migration - Complete Step-by-Step Guide

## 📍 You Are Here: Step 1 - Create New Firebase Project

![Current Screen](C:/Users/renuk/.gemini/antigravity/brain/11829a2c-82e8-42c6-a2b8-6cc3abc9209d/uploaded_media_1770305253992.png)

---

## STEP 1: Create New Firebase Project (5 minutes)

### What You See Now:
You're on the "Create a project" screen in Firebase Console.

### What to Do RIGHT NOW:

#### 1.1 Enter Project Name
In the text box that says "Enter your project name":
```
Type: SwasthyaMitra-Blaze
```
(Or any name you prefer, like "SwasthyaMitra-New" or "SwasthyaMitra-2026")

**Note:** The project ID below will auto-generate (like `swasthyamitra-blaze-abc123`)

#### 1.2 Google Developer Program (Optional)
- ✅ **Keep the checkbox CHECKED** (it's already checked)
- This gives you access to AI assistance and resources
- **No cost, just benefits**

#### 1.3 Click Continue
- Click the **"Continue"** button at the bottom right

---

## STEP 2: Google Analytics (Next Screen - 30 seconds)

### What You'll See:
Screen asking "Enable Google Analytics for this project?"

### What to Do:

#### Option A: Disable Analytics (Recommended for Now)
- **Toggle OFF** the "Enable Google Analytics" switch
- Click **"Create Project"**
- **Why:** Simpler setup, you can enable later if needed

#### Option B: Enable Analytics (If You Want Tracking)
- Keep toggle ON
- Select your Google Analytics account
- Click **"Create Project"**

**Recommendation:** Choose Option A (disable) for faster setup.

---

## STEP 3: Wait for Project Creation (1 minute)

### What You'll See:
- Loading screen: "Creating your project..."
- Progress indicator

### What to Do:
- **Wait** (takes 30-60 seconds)
- Don't close the browser tab

### When Complete:
- You'll see: "Your new project is ready"
- Click **"Continue"**

---

## STEP 4: Welcome to Firebase Console (You're In!)

### What You'll See:
Firebase Console dashboard with:
- Project name at top
- Menu on left side
- "Get started by adding Firebase to your app" in center

### What to Do Next:
**DON'T add app yet!** First, we need to upgrade to Blaze plan.

---

## STEP 5: Upgrade to Blaze Plan (5 minutes)

### 5.1 Open Settings
- Click the **⚙️ gear icon** (top left, next to "Project Overview")
- Click **"Project settings"**

### 5.2 Go to Billing
- Click **"Usage and billing"** tab (top of page)
- You'll see current plan: "Spark (No-cost)"

### 5.3 Modify Plan
- Click **"Modify plan"** button (or "Details & settings")
- You'll see two options:
  - **Spark** (Current - Free)
  - **Blaze** (Pay as you go)

### 5.4 Select Blaze Plan
- Click **"Select plan"** under **Blaze**
- A dialog will appear

### 5.5 Link Billing Account

**CRITICAL STEP - This is where you apply your credits!**

You'll see: "Select a billing account"

**If you have existing billing account with credits:**
- Select the account that has your $300 trial credits or travel credits
- Click **"Continue"**

**If you need to create new billing account:**
- Click **"Create billing account"**
- Enter billing information
- **Important:** Use the same Google account that has your credits
- Complete setup
- Click **"Continue"**

### 5.6 Confirm Purchase
- Review the plan details
- Click **"Purchase"** or **"Confirm"**

### 5.7 Verify Upgrade
- You should see: "Blaze plan activated"
- Current plan now shows: **"Blaze"**

---

## STEP 6: Verify Credits Are Applied (5 minutes)

### 6.1 Open Google Cloud Console
- Open new tab: https://console.cloud.google.com/
- Select your new Firebase project from dropdown (top left)

### 6.2 Check Billing
- Click **☰ menu** (top left)
- Go to **Billing → Overview**

### 6.3 Verify Credits
- Look for **"Credits"** section
- You should see:
  - **Available credits:** $300.00 (or your amount)
  - **Status:** Active
  - **Expiration:** (date)

**If you DON'T see credits:**
- You may need to link the correct billing account
- Go back to Firebase Console → Settings → Usage and billing
- Click "Modify" and select the account with credits

---

## STEP 7: Enable Firestore Database (10 minutes)

### 7.1 Go to Firestore
- Back in Firebase Console
- Click **"Build"** in left menu
- Click **"Firestore Database"**

### 7.2 Create Database
- Click **"Create database"** button

### 7.3 Choose Security Mode
You'll see two options:

**Select: Production mode** ✅
- Radio button: **"Start in production mode"**
- Click **"Next"**

(We'll add security rules in next step)

### 7.4 Choose Location
**IMPORTANT:** Choose closest location to your users

**Recommended for India:**
- Select: **"asia-south1 (Mumbai)"**

**Other options:**
- asia-south2 (Delhi)
- asia-southeast1 (Singapore)

**Note:** Location CANNOT be changed later!

Click **"Enable"**

### 7.5 Wait for Creation
- Loading: "Creating Cloud Firestore..."
- Takes 1-2 minutes
- **Don't close the tab**

### 7.6 Database Ready
- You'll see the Firestore console
- Tabs: Data, Rules, Indexes, Usage
- Currently empty (no collections)

---

## STEP 8: Add Security Rules (5 minutes)

### 8.1 Go to Rules Tab
- Click **"Rules"** tab (top of Firestore page)

### 8.2 Clear Default Rules
- You'll see default rules in the editor
- **Select all** (Ctrl+A)
- **Delete** all text

### 8.3 Copy Your Rules
- Open your project folder
- Open file: `SwasthyaMitra/firestore.rules`
- **Copy entire content** (all 116 lines)

### 8.4 Paste Rules
- Back in Firebase Console Rules editor
- **Paste** your rules
- Should start with: `rules_version = '2';`

### 8.5 Publish Rules
- Click **"Publish"** button (top right)
- Confirm: "Are you sure?" → Click **"Publish"**
- You'll see: "Rules published successfully"

### 8.6 Verify Rules
- Rules should now show your custom rules
- Status: "Published" with timestamp

---

## STEP 9: Enable Authentication (5 minutes)

### 9.1 Go to Authentication
- Click **"Build"** in left menu
- Click **"Authentication"**

### 9.2 Get Started
- Click **"Get started"** button

### 9.3 Enable Email/Password
- Click **"Sign-in method"** tab (top)
- You'll see list of providers
- Find **"Email/Password"**
- Click on it

### 9.4 Enable Provider
- Toggle **"Enable"** switch to ON
- **Email/Password:** Enabled ✅
- **Email link (passwordless sign-in):** Leave OFF
- Click **"Save"**

### 9.5 Verify
- "Email/Password" should now show **"Enabled"** status

---

## STEP 10: Enable Vertex AI (Firebase AI) (3 minutes)

### 10.1 Go to Vertex AI
- Click **"Build"** in left menu
- Scroll down to **"Vertex AI in Firebase"**
- Click it

### 10.2 Get Started
- Click **"Get started"** button

### 10.3 Accept Terms
- Read the terms (or scroll down)
- Check: ☑️ "I accept the terms"
- Click **"Continue"**

### 10.4 Wait for Activation
- Loading: "Enabling Vertex AI..."
- Takes 30-60 seconds

### 10.5 Verify
- You'll see the Vertex AI dashboard
- Shows available models: Gemini 1.5 Flash, Gemini 1.5 Pro, etc.
- Status: **Enabled** ✅

---

## STEP 11: Download Configuration File (5 minutes)

### 11.1 Go to Project Settings
- Click **⚙️ gear icon** (top left)
- Click **"Project settings"**

### 11.2 Scroll to Your Apps
- Scroll down to **"Your apps"** section
- Currently empty

### 11.3 Add Android App
- Click the **Android icon** (</> symbol)

### 11.4 Register App
You'll see a form:

**Android package name:**
```
com.example.swasthyamitra
```
(Must match exactly!)

**App nickname (optional):**
```
SwasthyaMitra Android
```

**Debug signing certificate SHA-1 (optional):**
- Leave blank for now
- Can add later if needed

Click **"Register app"**

### 11.5 Download google-services.json
- Click **"Download google-services.json"** button
- File will download to your Downloads folder
- **IMPORTANT:** Save this file! You'll need it soon

Click **"Next"**

### 11.6 Skip SDK Instructions
- Click **"Next"** (we already have Firebase SDK)
- Click **"Next"** again
- Click **"Continue to console"**

### 11.7 Verify App Added
- You should see your app listed under "Your apps"
- Package name: com.example.swasthyamitra
- Status: Active

---

## STEP 12: Update Your Android App (15 minutes)

### 12.1 Backup Current Configuration
Open Command Prompt / PowerShell:

```bash
cd C:\Users\renuk\OneDrive\Desktop\project\SwasthyaMitra

# Backup old google-services.json
copy app\google-services.json app\google-services.json.backup

# Backup .firebaserc
copy .firebaserc .firebaserc.backup
```

### 12.2 Replace google-services.json

**Step 1:** Delete old file
```bash
del app\google-services.json
```

**Step 2:** Copy new file
- Go to your **Downloads** folder
- Find **google-services.json** (just downloaded)
- **Copy** this file
- **Paste** into: `C:\Users\renuk\OneDrive\Desktop\project\SwasthyaMitra\app\`

**Verify:**
- File location: `SwasthyaMitra\app\google-services.json`
- File size: ~2-3 KB
- Open in Notepad - should show your new project ID

### 12.3 Update .firebaserc

**Step 1:** Open file
- Open: `SwasthyaMitra\.firebaserc`
- Use Notepad or VS Code

**Step 2:** Change project ID
Current content:
```json
{
  "projects": {
    "default": "swasthyamitra-c0899"
  },
  "targets": {},
  "etags": {}
}
```

Change to (use YOUR new project ID):
```json
{
  "projects": {
    "default": "swasthyamitra-blaze-abc123"
  },
  "targets": {},
  "etags": {}
}
```

**How to find your project ID:**
- Open the `google-services.json` file you just downloaded
- Look for: `"project_id": "your-project-id-here"`
- Copy that exact ID

**Step 3:** Save file

---

## STEP 13: Build and Test (30 minutes)

### 13.1 Clean Project
Open Android Studio:
- **Build → Clean Project**
- Wait for completion

### 13.2 Rebuild Project
- **Build → Rebuild Project**
- Wait for completion (2-3 minutes)
- Check for errors in Build output

**If you see errors:**
- Most common: "google-services.json not found"
- Solution: Verify file is in correct location (`app/` folder)

### 13.3 Sync Gradle
- Click **"Sync Now"** if banner appears
- Or: **File → Sync Project with Gradle Files**

### 13.4 Build APK
```bash
# In terminal/PowerShell
cd C:\Users\renuk\OneDrive\Desktop\project\SwasthyaMitra

# Build debug APK
.\gradlew assembleDebug
```

Wait for build to complete (3-5 minutes)

### 13.5 Install on Device

**Connect your Android device:**
- Enable USB debugging
- Connect via USB

**Install APK:**
```bash
adb install -r app\build\outputs\apk\debug\app-debug.apk
```

### 13.6 Test App

**Test 1: App Launches**
- [ ] Open SwasthyaMitra app
- [ ] App launches without crashing
- [ ] No Firebase errors in logcat

**Test 2: Create New Account**
- [ ] Click "Sign Up"
- [ ] Enter email: test@example.com
- [ ] Enter password: Test123456
- [ ] Click "Sign Up"
- [ ] Account created successfully

**Test 3: Verify in Firebase Console**
- [ ] Go to Firebase Console → Authentication → Users
- [ ] You should see your test user
- [ ] Email: test@example.com

**Test 4: Save Data**
- [ ] Complete onboarding
- [ ] Set a goal
- [ ] Log some food
- [ ] Go to Firebase Console → Firestore Database → Data
- [ ] You should see collections: users, goals, foodLogs

**Test 5: AI Features**
- [ ] Generate AI diet plan
- [ ] Generate AI exercise recommendations
- [ ] Both should work without errors

---

## STEP 14: Monitor Billing (Ongoing)

### 14.1 Set Up Budget Alert

**Go to Google Cloud Console:**
- https://console.cloud.google.com/
- Select your project

**Create Budget:**
- **☰ Menu → Billing → Budgets & alerts**
- Click **"Create budget"**

**Configure:**
- Name: "SwasthyaMitra Monthly Budget"
- Budget amount: $10 (or your preference)
- Alert thresholds: 50%, 90%, 100%
- Add your email
- Click **"Finish"**

### 14.2 Check Usage Daily (First Week)

**Firebase Console:**
- Go to **Settings → Usage and billing**
- Check: Firestore reads/writes, AI calls

**Google Cloud Console:**
- Go to **Billing → Reports**
- Filter by: Last 7 days
- Check: Vertex AI usage

### 14.3 Verify Credits Being Used

**Check Credits:**
- **Billing → Overview**
- Look for "Credits applied"
- Should show: -$X.XX (negative = credit used)

**If NOT using credits:**
- Check billing account is correct
- Verify credits are active
- Contact Google Cloud support

---

## ✅ COMPLETION CHECKLIST

### Firebase Setup
- [ ] Created new Firebase project
- [ ] Upgraded to Blaze plan
- [ ] Verified credits applied
- [ ] Created Firestore database
- [ ] Added security rules
- [ ] Enabled Authentication (Email/Password)
- [ ] Enabled Vertex AI
- [ ] Downloaded google-services.json

### App Update
- [ ] Backed up old configuration
- [ ] Replaced google-services.json
- [ ] Updated .firebaserc
- [ ] Cleaned and rebuilt project
- [ ] Built APK successfully

### Testing
- [ ] App launches without errors
- [ ] New user signup works
- [ ] User appears in Firebase Console
- [ ] Data saves to Firestore
- [ ] AI features work
- [ ] No billing errors

### Monitoring
- [ ] Set up budget alerts
- [ ] Checked usage dashboard
- [ ] Verified credits being used

---

## 🎯 What to Do After 1 Week

### If Everything Works:
- [ ] Delete old Firebase project (swasthyamitra-c0899)
- [ ] Remove backup files (.backup)
- [ ] Update production app

### If Issues:
- [ ] Rollback to old configuration
- [ ] Restore backup files
- [ ] Debug issues
- [ ] Try migration again

---

## 🆘 Troubleshooting

### App Crashes on Launch
**Error:** "Default FirebaseApp is not initialized"
**Solution:** 
- Verify google-services.json is in app/ folder
- Clean and rebuild project
- Sync Gradle files

### Authentication Fails
**Error:** "Firebase Auth not enabled"
**Solution:**
- Go to Firebase Console → Authentication
- Verify Email/Password is enabled
- Check security rules allow user creation

### Firestore Save Fails
**Error:** "PERMISSION_DENIED"
**Solution:**
- Go to Firestore → Rules
- Verify rules are published
- Check rules allow authenticated writes

### AI Features Not Working
**Error:** "Vertex AI not enabled"
**Solution:**
- Go to Firebase Console → Vertex AI
- Click "Get started" if not enabled
- Verify Blaze plan is active

### Getting Billed Despite Credits
**Solution:**
- Check Google Cloud Console → Billing → Credits
- Verify credits are "Active"
- Ensure correct billing account linked
- Contact Google Cloud support

---

## 📞 Need Help?

**Firebase Support:**
- https://firebase.google.com/support

**Billing Questions:**
- https://cloud.google.com/billing/docs/how-to/get-support

**Community:**
- Stack Overflow: https://stackoverflow.com/questions/tagged/firebase

---

**You're all set! Follow these steps in order and you'll successfully migrate to your new Firebase project with Blaze plan and credits.** 🚀

**Current Step:** You're at Step 1 - Enter your project name and click Continue!
