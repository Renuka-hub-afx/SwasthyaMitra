# Mood-Based Recommendations

## 📋 Overview

The Mood-Based Recommendations feature allows users to track their emotional state and receive AI-powered personalized suggestions to improve mood and mental wellness.

---

## 🎯 Purpose & Importance

- **Mental Health**: Track emotional patterns over time
- **Holistic Wellness**: Connect mood with physical health
- **Personalized Support**: AI suggests activities based on mood
- **Self-Awareness**: Understand mood triggers and patterns

---

## 🔄 How It Works

```
User Opens MoodRecommendationActivity
    ↓
Select Current Mood:
  - Happy 😊
  - Sad 😢
  - Stressed 😰
  - Anxious 😟
  - Energetic ⚡
  - Tired 😴
    ↓
Rate Intensity (1-10 slider)
    ↓
Optional: Add notes
    ↓
System Builds AI Prompt:
  "User is feeling [mood] with intensity [X]/10"
    ↓
Call Gemini AI for Recommendations
    ↓
AI Returns 5 Suggestions:
  1. Physical activity
  2. Dietary recommendation
  3. Mindfulness technique
  4. Social activity
  5. Self-care practice
    ↓
Display Recommendations
    ↓
Save Mood Log to Firestore
```

---

## 💻 Technical Implementation

### AI Prompt Engineering

```kotlin
fun buildMoodPrompt(mood: String, intensity: Int, recentActivities: String): String {
    return """
    User is currently feeling $mood with intensity $intensity out of 10.
    
    Recent activities: $recentActivities
    
    Provide 5 specific, actionable recommendations to improve their mood:
    1. A physical activity or exercise (be specific)
    2. A dietary suggestion (specific food or drink)
    3. A mindfulness or relaxation technique
    4. A social activity or connection
    5. A self-care practice
    
    Keep suggestions:
    - Practical and achievable within 30 minutes
    - Specific (not generic advice)
    - Supportive and non-judgmental
    - Culturally appropriate for Indian context
    
    Return as JSON array of strings.
    """.trimIndent()
}
```

### MoodRecommendationActivity.kt

```kotlin
class MoodRecommendationActivity : AppCompatActivity() {
    
    private var selectedMood: String = ""
    private var intensity: Int = 5
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupMoodSelector()
        setupIntensitySlider()
    }
    
    private fun generateRecommendations() {
        showLoading()
        
        lifecycleScope.launch {
            try {
                // Fetch recent activities for context
                val recentActivities = fetchRecentActivities()
                
                // Build AI prompt
                val prompt = buildMoodPrompt(selectedMood, intensity, recentActivities)
                
                // Call Gemini AI
                val response = geminiService.generateRecommendations(prompt)
                val recommendations = parseRecommendations(response)
                
                // Display recommendations
                displayRecommendations(recommendations)
                
                // Save mood log
                saveMoodLog(selectedMood, intensity, recommendations)
                
            } catch (e: Exception) {
                showError("Failed to generate recommendations")
            } finally {
                hideLoading()
            }
        }
    }
    
    private fun saveMoodLog(mood: String, intensity: Int, recommendations: List<String>) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val today = SimpleDateFormat("yyyy-MM-dd").format(Date())
        
        val moodData = hashMapOf(
            "mood" to mood,
            "intensity" to intensity,
            "date" to today,
            "timestamp" to FieldValue.serverTimestamp(),
            "recommendations" to recommendations,
            "notes" to notesInput.text.toString()
        )
        
        db.collection("users").document(userId)
            .collection("mood_logs")
            .add(moodData)
    }
}
```

---

## 🎨 UI Design

### Mood Selection
- Grid of emoji buttons for each mood
- Visual feedback on selection
- Color-coded moods

### Intensity Slider
- 1-10 scale with emoji indicators
- Real-time value display

### Recommendations Display
- Card-based layout
- Icon for each recommendation type
- Expandable for more details

---

## 📊 Firestore Structure

```json
{
  "mood": "Stressed",
  "intensity": 7,
  "date": "2026-02-16",
  "timestamp": "2026-02-16T15:30:00Z",
  "recommendations": [
    "Take a 10-minute walk outdoors",
    "Drink chamomile tea with honey",
    "Practice 5 minutes of deep breathing",
    "Call a friend or family member",
    "Take a warm shower"
  ],
  "notes": "Work deadline stress"
}
```

---

## 🚀 Future Improvements

1. **Mood Patterns**: Identify triggers and patterns
2. **Mood-Exercise Correlation**: Link mood to activity levels
3. **Journaling**: Detailed mood journal entries
4. **Mood Reminders**: Check-in notifications
5. **Therapist Integration**: Share mood data with professionals

---

**[← Back to Main README](../README.md)**
