# SwasthyaMitra: Theoretical Architecture & Mission

SwasthyaMitra is a personalized healthcare ecosystem designed to bridge the gap between complex health metrics and daily habit building. Theoretically, the app functions as a **Closed-Loop Feedback System** where your physical data is the "Input," and the AI/Calculations provide the "Actionable Intelligence."

---

## 1. The Mathematical Foundation: Metabolic Modeling

The core of SwasthyaMitra isn't just "counting calories"; it is building a **Digital Twin** of your metabolism.

* **The Logic**: When you enter your age, weight, and height, the app uses the **Mifflin-St Jeor Equation**. Theoretically, this calculates the energy your body needs just to stay alive (BMR).
* **The Adjustment**: By adding your "Lifestyle Factor" (TDEE), the app converts theoretical biology into a daily energy budget.
* **The Shift**: If you want to lose or gain weight, the app creates a "Caloric Delta" (e.g., -500 kcal). This is the scientific basis for weight management—consistently maintaining that delta over time.

---

## 2. The Data Layer: Distributed Trust (Firebase)

The project uses a **Serverless Architecture** to manage user data at scale.

* **Storage (Firestore)**: Instead of keeping data on your phone, everything is stored in a structured NoSQL database. This ensures data persistence across devices and high availability.
* **Security**: Theoretically, every action is guarded by "Conditions." Only the person with the correct "Token" (your UID) can see or change their health logs, ensuring medical-grade privacy and data integrity.

---

## 3. The Intelligence Layer: AI Grounding (Gemini SDK)

This is where the project moves from a simple "Log App" to an "Active Smart Coach."

* **The Hallucination Problem**: Normally, an AI might "guess" the calories in a Samosa, which could be dangerously inaccurate.
* **The Solution (Grounding)**: We utilize a local database of 1000+ Indian foods. When the AI makes a recommendation, it is **"grounded"** in this CSV. It is theoretically forced to choose ONLY from real, verified data.
* **Direct SDK Integration**: Instead of complex API bridges, we use the **Firebase GenAI SDK** to talk directly to Gemini models from the client, reducing latency and complexity.
* **Adaptive Context**: The AI analyzes three pillars:
  1. **Who you are** (Historical profile)
  2. **How you moved** (Recent Exercise Logs)
  3. **What day it is** (Festival/Season sensitivity/Period Mode)
* **Result**: If it’s Diwali or if "Period Mode" is active, the AI calculates a plan that balances biological needs with personal comfort and traditions.

---

## 4. The Maintenance Layer: Habit Reinforcement

Scientific calculations are only effective if they lead to consistent user action.

* **The Hydration Engine**: This uses **Deterministic Logic**. Based on your weight, it calculates a specific volume goal.
* **Active Reminders**: Using the Android **AlarmManager**, the app schedules system-level "Interrupts." Even if the app is closed, the system wakes it up briefly to nudge the user. This maintains the "Maintenance" phase of the closed loop.

---

## 5. The External Ecosystem: Native Orchestration

The project acts as a central **Integrator Hub**. It talks to various specialized services:

* **OpenFoodFacts**: For scanning billions of global barcodes.
* **Firebase GenAI**: For the cognitive "Thinking" process using Gemini models.

---

## 📊 Summary: The "User Journey" Theory

1. **Ingestion**: User provides physical "State" (Weight/Age).
2. **Calculation**: App defines the biological "Target" (TDEE).
3. **Action**: User logs "Input" (Food) and "Output" (Exercise).
4. **Analysis**: Gemini AI analyzes the "Gap" between actions and goals.
5. **Direction**: AI provides a "Correction" (the Meal Plan for tomorrow).

**Conclusion: SwasthyaMitra is a tool that turns your smartphone into a scientific nutrition lab and a personal health coach simultaneously.**
