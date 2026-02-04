# Women’s Health and Wellness: Period-Aware AI Coaching 🌸

SwasthyaMitra includes a specialized "Period Mode" logic for our AI Coach. This feature ensures that during menstrual days, the app shifts its tone and recommendations to be gentle, safe, and supportive.

> [!NOTE]
> This mode is automatically restricted to users who identify as **Female** in their health profile. The toggle will not appear for other users.

## 🎯 Core Principles
- **Body First**: Emphasize listening to the body over hitting strict targets.
- **Empathetic AI**: Use supportive, warm language instead of standard high-performance coaching.
- **Safe Guidelines**: Shift from high-intensity to restorative activities.

---

## 🧘 Exercise Recommendations
When the user indicates she is on her period, the AI shifts its recommendation logic:

### Recommended (Restorative)
- **Yoga & Stretching**: Focus on pelvic relaxation and child’s pose for cramp relief.
- **Light Walking**: Low-intensity movement to improve blood flow.
- **Breathing Exercises**: To manage mood swings and discomfort.

### Activities to Avoid
- **High-Intensity Interval Training (HIIT)**
- **Heavy Weightlifting**
- **Intense Cardio/Core Workouts**

---

## 🥗 Food & Nutrition
The AI Diet Plan adapts to help manage common symptoms like bloating, low iron, and fatigue.

### Recommended Foods
- **Iron-Rich**: Spinach, lentils (Dal), and pomegranate to replenish energy.
- **Warm & Comforting**: Ginger tea, warm soups, and Khichdi.
- **Magnesium-Rich**: Dark chocolate or nuts to help with cramps.
- **Hydrating**: Watermelons, cucumbers, and plenty of water.

### Limit or Avoid
- **Junk/Processed Food**: High salt increases bloating.
- **Excess Caffeine**: Can worsen anxiety and cramps.
- **Cold Foods**: Traditional wisdom suggests warm foods are more soothing for the digestive system during this time.

---

## ✨ Daily Care Advice
The AI Coach Message will prioritize self-care tips:
- **Hydration & Rest**: "It's 10:00 PM, renu. Your body is working hard—try to get 8-9 hours of restful sleep tonight."
- **Heat Therapy**: Suggesting a warm compress for cramp relief.
- **Emotional Support**: "It’s okay to slow down today. You’re doing enough just by taking care of yourself."

---

## 🛠️ Implementation Strategy
1. **User Profile Update**: Add a "Period Tracking" toggle or logging option.
2. **Logic Override**: When the "Menstrual Days" flag is active:
    - `AIExerciseRecommendationService`: Filters for "Gentle" tags and restorative yoga.
    - `AIDietPlanService`: Replaces regular suggestions with the nutrition guidelines above.
    - `AICoachMessageService`: Switches tone to *Empathetic/Supportive*.

---
> *Remember: It’s okay to slow down and listen to your body. You are strong even when you rest.* 🤍
