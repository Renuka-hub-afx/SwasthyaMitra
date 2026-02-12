package com.example.swasthyamitra.notifications

object NotificationConstants {

    // Water Reminders (Moto: "Hydration is the easiest way to be a Healthier You")
    val WATER_MESSAGES = listOf(
        "Time for a quick sip! Staying hydrated is the easiest way to be a Healthier You today. 💧",
        "Water break! Your body needs fuel to keep going. 🥤",
        "Hydration check! Clear mind, healthy body. Drink up! 💧",
        "A small sip now for a big energy boost later. 🌊",
        "Your AI Partner says: Don't forget to drink water! 💙"
    )

    // Breakfast (Moto: "Fuel your brain")
    val BREAKFAST_MESSAGES = listOf(
        "Good morning! Let's fuel your brain. Ready for a nutritious breakfast? 🍳",
        "Rise and shine! A healthy breakfast sets the tone for a Healthier You. ☀️",
        "Don't skip the most important meal! What's on your plate today? 🥣",
        "Start your day strong with a wholesome breakfast. Your body will thank you! 🥑",
        "SwasthyaMitra tip: Protein at breakfast keeps you full longer! 🥚"
    )

    // Lunch (Moto: "Recharge")
    val LUNCH_MESSAGES = listOf(
        "It's lunch time! Refuel your body for the rest of the day. 🥗",
        "Mid-day check-in! Did you know a balanced lunch boosts focus? 🍱",
        "Take a break and enjoy a mindful meal. You deserve it! 🍲",
        "Power up with a healthy lunch. Keep that energy steady! ⚡",
        "Don't work through lunch! Your body needs this recharge. 🍛"
    )

    // Dinner (Moto: "Light & Right")
    val DINNER_MESSAGES = listOf(
        "Dinner time! Keep it light and right for a restful sleep. 🌙",
        "Wind down with a nutritious dinner. A Healthier You starts tonight. 🍲",
        "Try to eat 2 hours before bed for better digestion. Bon appétit! 🍽️",
        "Ending the day on a healthy note? functionality check! 🥗",
        "Reflect on your day with a mindful dinner. You did great today! 🌟"
    )

    // Events (Birthday/Festivals)
    fun getBirthdayMessage(name: String) = "Happy Birthday, $name! 🎂 Another year of growth. SwasthyaMitra is proud to be your partner in this journey!"
    
    val FESTIVAL_MESSAGES = mapOf(
        "Diwali" to "Happy Diwali! 🪔 Enjoy the treats, but remember to stay active. Balance is key to health!",
        "Holi" to "Happy Holi! 🎨 play safe and stay hydrated while you celebrate!",
        "Eid" to "Eid Mubarak! 🌙 May this festival bring you peace and health.",
        "Christmas" to "Merry Christmas! 🎄 Enjoy the festive cheer and wholesome food!",
        "New Year" to "Happy New Year! 🎉 Let's make this year your healthiest yet with SwasthyaMitra."
    )
}
