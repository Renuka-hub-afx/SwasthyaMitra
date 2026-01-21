import * as functions from "firebase-functions";
import * as admin from "firebase-admin";

admin.initializeApp();

const db = admin.firestore();

/**
 * Cloud Function: onFoodLogCreated
 * 
 * Triggers when a new food log is added to Firestore.
 * Calculates daily macro totals and generates meal recommendations
 * if the user has significant calories remaining late in the day.
 */
export const onFoodLogCreated = functions.firestore
    .document("foodLogs/{logId}")
    .onCreate(async (snapshot, context) => {
        const log = snapshot.data();
        const userId = log.userId;
        const today = new Date().toISOString().split("T")[0]; // YYYY-MM-DD

        functions.logger.info(`Food log created for user ${userId}`);

        try {
            // 1. Fetch user's goal
            const goalsSnap = await db.collection("goals").doc(userId).get();
            if (!goalsSnap.exists) {
                functions.logger.warn(`No goals found for user ${userId}`);
                return null;
            }
            const goals = goalsSnap.data()!;
            const targetCalories = goals.targetCalories || goals.dailyCalories || 2000;

            // 2. Fetch user's profile for preferences
            const userSnap = await db.collection("users").doc(userId).get();
            const userProfile = userSnap.exists ? userSnap.data()! : {};
            const preference = userProfile.preference || "Non-Veg";
            const allergies: string[] = userProfile.allergies || [];

            // 3. Sum today's food logs
            const logsSnap = await db.collection("foodLogs")
                .where("userId", "==", userId)
                .where("date", "==", today)
                .get();

            let totalCalories = 0;
            let totalProtein = 0;
            let totalCarbs = 0;
            let totalFat = 0;

            logsSnap.forEach((doc) => {
                const data = doc.data();
                totalCalories += data.calories || 0;
                totalProtein += data.protein || 0;
                totalCarbs += data.carbs || 0;
                totalFat += data.fat || 0;
            });

            // 4. Calculate remaining
            const remainingCalories = targetCalories - totalCalories;
            const currentHour = new Date().getHours();

            functions.logger.info(
                `User ${userId}: ${totalCalories}/${targetCalories} cals consumed, ` +
                `${remainingCalories} remaining, current hour: ${currentHour}`
            );

            // 5. Generate recommendations based on rules

            // Rule 1: Dinner suggestion if >400 cals remaining after 6 PM
            if (remainingCalories > 400 && currentHour >= 18) {
                const dinnerSuggestions = getDinnerSuggestions(
                    remainingCalories,
                    preference,
                    allergies
                );

                await db.collection("recommendations").add({
                    userId: userId,
                    type: "Meal_Suggestion",
                    mealTime: "Dinner",
                    suggestedFoods: dinnerSuggestions,
                    remainingCalories: remainingCalories,
                    message: `You have ${remainingCalories} calories remaining. Here are some dinner options:`,
                    createdAt: admin.firestore.FieldValue.serverTimestamp(),
                    status: "Pending",
                });

                functions.logger.info(`Dinner suggestion created for user ${userId}`);
            }

            // Rule 2: Protein alert if protein intake is very low after lunch time
            const targetProtein = goals.targetProtein || (targetCalories * 0.25) / 4; // ~25% of cals from protein
            if (totalProtein < targetProtein * 0.3 && currentHour >= 14) {
                await db.collection("recommendations").add({
                    userId: userId,
                    type: "Macro_Alert",
                    mealTime: "Anytime",
                    suggestedFoods: getHighProteinSuggestions(preference, allergies),
                    remainingCalories: remainingCalories,
                    message: `You've only had ${Math.round(totalProtein)}g protein today. Consider adding protein-rich foods.`,
                    createdAt: admin.firestore.FieldValue.serverTimestamp(),
                    status: "Pending",
                });

                functions.logger.info(`Protein alert created for user ${userId}`);
            }

            return null;
        } catch (error) {
            functions.logger.error(`Error processing food log for user ${userId}:`, error);
            return null;
        }
    });

/**
 * Helper: Get dinner suggestions based on remaining calories and preferences
 */
function getDinnerSuggestions(
    remainingCalories: number,
    preference: string,
    allergies: string[]
): object[] {
    const vegOptions = [
        { name: "Dal Tadka with Rice", calories: 450, protein: 15, carbs: 70, fat: 10, reason: "Balanced meal with good protein from lentils." },
        { name: "Palak Paneer with 2 Roti", calories: 480, protein: 20, carbs: 45, fat: 22, reason: "High protein vegetarian option." },
        { name: "Rajma Chawal", calories: 420, protein: 14, carbs: 68, fat: 8, reason: "Complete protein from kidney beans and rice." },
        { name: "Vegetable Pulao with Raita", calories: 380, protein: 10, carbs: 55, fat: 12, reason: "Light yet filling dinner option." },
        { name: "Moong Dal Khichdi", calories: 320, protein: 12, carbs: 52, fat: 6, reason: "Easy to digest, perfect for late dinner." },
    ];

    const nonVegOptions = [
        { name: "Grilled Chicken with Salad", calories: 350, protein: 35, carbs: 15, fat: 16, reason: "High protein, low carb option." },
        { name: "Fish Curry with Rice", calories: 420, protein: 28, carbs: 45, fat: 14, reason: "Omega-3 rich meal with balanced macros." },
        { name: "Egg Bhurji with 2 Roti", calories: 380, protein: 22, carbs: 40, fat: 15, reason: "Quick protein-packed dinner." },
        { name: "Chicken Tikka (6 pcs)", calories: 280, protein: 32, carbs: 8, fat: 12, reason: "Low carb, high protein for weight loss." },
    ];

    let options = preference === "Vegetarian" || preference === "Veg" ? vegOptions : [...vegOptions, ...nonVegOptions];

    // Filter by remaining calories (allow 50 cal buffer)
    options = options.filter((food) => food.calories <= remainingCalories + 50);

    // Filter out allergenic foods (simple keyword match)
    if (allergies.length > 0) {
        options = options.filter((food) => {
            const foodNameLower = food.name.toLowerCase();
            return !allergies.some((allergy) => foodNameLower.includes(allergy.toLowerCase()));
        });
    }

    // Return top 3 suggestions
    return options.slice(0, 3).map((food) => ({
        name: food.name,
        calories: food.calories,
        protein: food.protein,
        carbs: food.carbs,
        fat: food.fat,
        servingSize: "1 serving",
        reason: food.reason,
    }));
}

/**
 * Helper: Get high protein food suggestions
 */
function getHighProteinSuggestions(preference: string, allergies: string[]): object[] {
    const vegOptions = [
        { name: "Paneer Bhurji", calories: 250, protein: 18, reason: "Quick paneer scramble, high in protein." },
        { name: "Greek Yogurt (200g)", calories: 130, protein: 15, reason: "Protein-rich snack or dessert." },
        { name: "Moong Dal Cheela", calories: 180, protein: 12, reason: "Protein pancake, great for snacking." },
    ];

    const nonVegOptions = [
        { name: "Boiled Eggs (2)", calories: 140, protein: 12, reason: "Complete protein source." },
        { name: "Chicken Breast (100g)", calories: 165, protein: 31, reason: "Lean protein powerhouse." },
    ];

    let options = preference === "Vegetarian" || preference === "Veg" ? vegOptions : [...vegOptions, ...nonVegOptions];

    if (allergies.length > 0) {
        options = options.filter((food) => {
            const foodNameLower = food.name.toLowerCase();
            return !allergies.some((allergy) => foodNameLower.includes(allergy.toLowerCase()));
        });
    }

    return options.slice(0, 2);
}
