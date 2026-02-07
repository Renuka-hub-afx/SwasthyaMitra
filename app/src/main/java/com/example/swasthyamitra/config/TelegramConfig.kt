package com.example.swasthyamitra.config

object TelegramConfig {
    // Bot Token from BotFather
    const val BOT_TOKEN = "8293799206:AAG0pKxo3CHaQDlfGMO6ECREJJWunM5wwxc"
    
    // Your Telegram Chat ID
    const val CHAT_ID = "7071630671"
    
    // Telegram API Base URL
    const val API_BASE_URL = "https://api.telegram.org/bot$BOT_TOKEN"
    
    fun getSendMessageUrl(): String = "$API_BASE_URL/sendMessage"
    fun getUpdatesUrl(offset: Long = 0): String = "$API_BASE_URL/getUpdates?offset=$offset&timeout=30"
}
