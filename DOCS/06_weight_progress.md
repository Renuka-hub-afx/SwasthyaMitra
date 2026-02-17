# Weight Progress Monitoring

## 📋 Overview

The Weight Progress Monitoring feature enables users to track weight changes over time, calculate BMI, visualize trends with charts, and predict future weight using linear regression machine learning.

---

## 🎯 Purpose & Importance

### Why Weight Tracking Matters
- **Goal Achievement**: Monitor progress toward weight loss/gain goals
- **Health Indicators**: BMI helps assess health risks
- **Motivation**: Visual progress encourages continued effort
- **Pattern Recognition**: Identify what works and what doesn't
- **Predictive Insights**: ML-based projections help set realistic expectations

### Key Benefits
- Simple weight logging with date
- Automatic BMI calculation
- Visual trend charts
- Predictive weight projection (30-day forecast)
- Goal progress tracking
- Historical data analysis

---

## 🔄 How It Works

### Complete Workflow

```
User Opens WeightProgressActivity
    ↓
Fetch User Profile (height, goal weight)
    ↓
Load Weight History from Firestore
    ↓
Display Current Weight and BMI
    ↓
Show Line Chart (weight over time)
    ↓
User Clicks "Log Weight"
    ↓
Enter Current Weight
    ↓
Calculate BMI = weight / (height²)
    ↓
Save to Firestore
    ↓
Update Chart with New Data Point
    ↓
Run Linear Regression:
  - Analyze historical trend
  - Calculate slope and intercept
  - Project weight for next 30 days
    ↓
Display Prediction:
  "At current rate, you'll weigh X kg in 30 days"
    ↓
Show Insights and Recommendations
```

---

## 🧮 Logic & Algorithms

### BMI Calculation

#### Standard BMI Formula
```kotlin
fun calculateBMI(weightKg: Double, heightCm: Double): Double {
    val heightMeters = heightCm / 100.0
    return weightKg / (heightMeters * heightMeters)
}
```

**Example**:
```kotlin
// Weight: 70 kg, Height: 175 cm
heightMeters = 175 / 100 = 1.75
BMI = 70 / (1.75 × 1.75)
    = 70 / 3.0625
    = 22.86
```

#### BMI Categories
```kotlin
fun getBMICategory(bmi: Double): String {
    return when {
        bmi < 18.5 -> "Underweight"
        bmi < 25.0 -> "Normal Weight"
        bmi < 30.0 -> "Overweight"
        bmi < 35.0 -> "Obese Class I"
        bmi < 40.0 -> "Obese Class II"
        else -> "Obese Class III"
    }
}

fun getBMIColor(bmi: Double): Int {
    return when {
        bmi < 18.5 -> Color.parseColor("#FFA726")  // Orange
        bmi < 25.0 -> Color.parseColor("#66BB6A")  // Green
        bmi < 30.0 -> Color.parseColor("#FFA726")  // Orange
        else -> Color.parseColor("#EF5350")        // Red
    }
}
```

### Linear Regression for Weight Prediction

#### Simple Linear Regression
```kotlin
class WeightProjectionHelper {
    
    /**
     * Predict future weight using linear regression
     * Formula: y = mx + b
     * where m = slope, b = intercept
     */
    fun predictFutureWeight(
        historicalData: List<WeightLog>,
        daysAhead: Int
    ): Double {
        if (historicalData.size < 2) {
            return historicalData.lastOrNull()?.weight ?: 0.0
        }
        
        // Convert dates to numeric values (days since first entry)
        val xValues = historicalData.mapIndexed { index, _ -> index.toDouble() }
        val yValues = historicalData.map { it.weight }
        
        // Calculate slope (m)
        val slope = calculateSlope(xValues, yValues)
        
        // Calculate intercept (b)
        val intercept = calculateIntercept(xValues, yValues, slope)
        
        // Predict future value
        val futureX = historicalData.size - 1 + daysAhead
        return slope * futureX + intercept
    }
    
    private fun calculateSlope(x: List<Double>, y: List<Double>): Double {
        val n = x.size
        val sumX = x.sum()
        val sumY = y.sum()
        val sumXY = x.zip(y).sumOf { it.first * it.second }
        val sumX2 = x.sumOf { it * it }
        
        // Slope formula: m = (n*ΣXY - ΣX*ΣY) / (n*ΣX² - (ΣX)²)
        return (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX)
    }
    
    private fun calculateIntercept(
        x: List<Double>,
        y: List<Double>,
        slope: Double
    ): Double {
        val meanX = x.average()
        val meanY = y.average()
        
        // Intercept formula: b = ȳ - m*x̄
        return meanY - slope * meanX
    }
    
    /**
     * Calculate R² (coefficient of determination)
     * Indicates how well the linear model fits the data
     * R² = 1 means perfect fit, R² = 0 means no correlation
     */
    fun calculateRSquared(x: List<Double>, y: List<Double>, slope: Double, intercept: Double): Double {
        val meanY = y.average()
        
        // Total sum of squares
        val ssTot = y.sumOf { (it - meanY).pow(2) }
        
        // Residual sum of squares
        val ssRes = x.zip(y).sumOf { (xi, yi) ->
            val predicted = slope * xi + intercept
            (yi - predicted).pow(2)
        }
        
        return 1 - (ssRes / ssTot)
    }
}
```

**Example Calculation**:
```kotlin
// Historical data: [75.0, 74.5, 74.0, 73.5, 73.0] kg over 5 days
x = [0, 1, 2, 3, 4]
y = [75.0, 74.5, 74.0, 73.5, 73.0]

n = 5
ΣX = 10
ΣY = 370.0
ΣXY = 732.0
ΣX² = 30

slope = (5*732 - 10*370) / (5*30 - 10*10)
      = (3660 - 3700) / (150 - 100)
      = -40 / 50
      = -0.5 kg/day

intercept = 74.0 - (-0.5 * 2)
          = 75.0 kg

// Predict weight in 30 days:
futureX = 4 + 30 = 34
predictedWeight = -0.5 * 34 + 75.0
                = -17 + 75
                = 58.0 kg
```

### Goal Progress Calculation

```kotlin
fun calculateGoalProgress(
    currentWeight: Double,
    startWeight: Double,
    goalWeight: Double
): Int {
    val totalChange = goalWeight - startWeight
    val currentChange = currentWeight - startWeight
    
    val progress = (currentChange / totalChange * 100).toInt()
    return progress.coerceIn(0, 100)
}
```

---

## 💻 Technical Implementation

### Key Files

#### **WeightProgressActivity.kt**
```kotlin
class WeightProgressActivity : AppCompatActivity() {
    
    private lateinit var lineChart: LineChart
    private lateinit var projectionHelper: WeightProjectionHelper
    private val weightLogs = mutableListOf<WeightLog>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        projectionHelper = WeightProjectionHelper()
        loadUserProfile()
        loadWeightHistory()
        setupChart()
    }
    
    private fun loadWeightHistory() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        
        db.collection("users").document(userId)
            .collection("weight_logs")
            .orderBy("date", Query.Direction.ASCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) return@addSnapshotListener
                
                weightLogs.clear()
                snapshot?.documents?.forEach { doc ->
                    weightLogs.add(WeightLog(
                        id = doc.id,
                        weight = doc.getDouble("weight") ?: 0.0,
                        date = doc.getString("date") ?: "",
                        bmi = doc.getDouble("bmi") ?: 0.0,
                        notes = doc.getString("notes")
                    ))
                }
                
                updateChart()
                updateStatistics()
                generatePrediction()
            }
    }
    
    private fun logWeight(weight: Double, notes: String = "") {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        
        // Calculate BMI
        val height = getUserHeight()  // from profile
        val bmi = calculateBMI(weight, height)
        
        val weightData = hashMapOf(
            "weight" to weight,
            "date" to today,
            "bmi" to bmi,
            "notes" to notes,
            "timestamp" to FieldValue.serverTimestamp()
        )
        
        db.collection("users").document(userId)
            .collection("weight_logs")
            .add(weightData)
            .addOnSuccessListener {
                Toast.makeText(this, "Weight logged!", Toast.LENGTH_SHORT).show()
                updateUserProfile(weight, bmi)
            }
    }
    
    private fun generatePrediction() {
        if (weightLogs.size < 3) {
            predictionText.text = "Log more weights to see prediction"
            return
        }
        
        val predictedWeight = projectionHelper.predictFutureWeight(weightLogs, 30)
        val currentWeight = weightLogs.lastOrNull()?.weight ?: 0.0
        val change = predictedWeight - currentWeight
        
        val predictionMessage = if (change > 0) {
            "At current rate, you'll gain ${String.format("%.1f", abs(change))} kg in 30 days"
        } else {
            "At current rate, you'll lose ${String.format("%.1f", abs(change))} kg in 30 days"
        }
        
        predictionText.text = predictionMessage
        
        // Show R² (model accuracy)
        val xValues = weightLogs.mapIndexed { index, _ -> index.toDouble() }
        val yValues = weightLogs.map { it.weight }
        val slope = projectionHelper.calculateSlope(xValues, yValues)
        val intercept = projectionHelper.calculateIntercept(xValues, yValues, slope)
        val rSquared = projectionHelper.calculateRSquared(xValues, yValues, slope, intercept)
        
        accuracyText.text = "Prediction accuracy: ${(rSquared * 100).toInt()}%"
    }
    
    private fun updateChart() {
        val entries = weightLogs.mapIndexed { index, log ->
            Entry(index.toFloat(), log.weight.toFloat())
        }
        
        val dataSet = LineDataSet(entries, "Weight (kg)")
        dataSet.color = Color.parseColor("#7B2CBF")
        dataSet.setCircleColor(Color.parseColor("#E91E63"))
        dataSet.lineWidth = 3f
        dataSet.circleRadius = 5f
        dataSet.setDrawValues(true)
        
        // Add goal line
        val goalWeight = getGoalWeight()
        val goalEntries = listOf(
            Entry(0f, goalWeight.toFloat()),
            Entry(weightLogs.size.toFloat(), goalWeight.toFloat())
        )
        val goalDataSet = LineDataSet(goalEntries, "Goal")
        goalDataSet.color = Color.parseColor("#66BB6A")
        goalDataSet.setDrawCircles(false)
        goalDataSet.enableDashedLine(10f, 5f, 0f)
        
        lineChart.data = LineData(dataSet, goalDataSet)
        lineChart.invalidate()
    }
}
```

### Data Models

```kotlin
data class WeightLog(
    val id: String,
    val weight: Double,
    val date: String,
    val bmi: Double,
    val notes: String?
)

data class WeightStatistics(
    val currentWeight: Double,
    val startWeight: Double,
    val goalWeight: Double,
    val totalChange: Double,
    val averageWeeklyChange: Double,
    val currentBMI: Double,
    val goalProgress: Int
)
```

---

## 🎨 Design & UI Structure

### Layout Components
- Current weight display (large text)
- BMI indicator with color coding
- Line chart with goal line
- Prediction card
- Statistics cards
- "Log Weight" FAB
- Weight history list

---

## 🔌 APIs & Services Used

### Cloud Firestore
```json
{
  "weight": 72.5,
  "date": "2026-02-16",
  "bmi": 23.67,
  "notes": "Feeling good",
  "timestamp": "2026-02-16T10:00:00Z"
}
```

---

## 🚀 Future Improvements

1. **Body Composition**: Track body fat %, muscle mass
2. **Photo Progress**: Before/after photos
3. **Measurement Tracking**: Waist, chest, arms, etc.
4. **Weight Reminders**: Weekly weigh-in notifications
5. **Export Reports**: PDF progress reports
6. **Integration**: Sync with smart scales
7. **Advanced ML**: Neural networks for better predictions
8. **Seasonal Adjustments**: Account for holidays, seasons

---

**[← Back to Main README](../README.md)** | **[Next: Gamification →](09_gamification.md)**
