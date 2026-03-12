package com.example.swasthyamitra.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Centralized date/time formatting utility for ISO 8601 standardization.
 * Ensures consistent timezone handling across the application.
 */
object DateTimeHelper {
    
    // ISO 8601 format with milliseconds and UTC timezone
    private val iso8601Format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }
    
    // Simple date format for queries and grouping (local timezone)
    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    
    // Time format for display
    private val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.US)
    
    /**
     * Formats a timestamp to ISO 8601 UTC string.
     * @param timestamp Unix timestamp in milliseconds
     * @return ISO 8601 formatted string (e.g., "2026-02-23T14:30:45.123Z")
     */
    fun formatISO8601(timestamp: Long): String {
        return iso8601Format.format(Date(timestamp))
    }
    
    /**
     * Formats current time to ISO 8601 UTC string.
     * @return ISO 8601 formatted string of current time
     */
    fun currentISO8601(): String {
        return formatISO8601(System.currentTimeMillis())
    }
    
    /**
     * Parses ISO 8601 string to timestamp.
     * @param iso8601String ISO 8601 formatted string
     * @return Unix timestamp in milliseconds, or null if parsing fails
     */
    fun parseISO8601(iso8601String: String): Long? {
        return try {
            iso8601Format.parse(iso8601String)?.time
        } catch (e: Exception) {
            null
        }
    }
    
    /**
     * Formats a timestamp to simple date string (yyyy-MM-dd) in local timezone.
     * Used for grouping and queries.
     * @param timestamp Unix timestamp in milliseconds
     * @return Date string (e.g., "2026-02-23")
     */
    fun formatSimpleDate(timestamp: Long): String {
        return simpleDateFormat.format(Date(timestamp))
    }
    
    /**
     * Gets current date in simple format (yyyy-MM-dd).
     * @return Current date string
     */
    fun currentSimpleDate(): String {
        return formatSimpleDate(System.currentTimeMillis())
    }
    
    /**
     * Parses simple date string (yyyy-MM-dd) to timestamp at start of day.
     * @param dateString Date string in yyyy-MM-dd format
     * @return Unix timestamp in milliseconds (at 00:00:00), or null if parsing fails
     */
    fun parseSimpleDate(dateString: String): Long? {
        return try {
            simpleDateFormat.parse(dateString)?.time
        } catch (e: Exception) {
            null
        }
    }
    
    /**
     * Formats timestamp to time string (HH:mm:ss).
     * @param timestamp Unix timestamp in milliseconds
     * @return Time string
     */
    fun formatTime(timestamp: Long): String {
        return timeFormat.format(Date(timestamp))
    }
    
    /**
     * Checks if a date string is valid (yyyy-MM-dd format).
     * @param dateString Date string to validate
     * @return True if valid, false otherwise
     */
    fun isValidDateString(dateString: String): Boolean {
        return try {
            simpleDateFormat.parse(dateString) != null
        } catch (e: Exception) {
            false
        }
    }
    
    /**
     * Gets the start of day timestamp for a given date.
     * @param timestamp Any timestamp within the day
     * @return Timestamp at 00:00:00 of that day
     */
    fun getStartOfDay(timestamp: Long): Long {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = timestamp
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        return calendar.timeInMillis
    }
    
    /**
     * Gets the end of day timestamp for a given date.
     * @param timestamp Any timestamp within the day
     * @return Timestamp at 23:59:59.999 of that day
     */
    fun getEndOfDay(timestamp: Long): Long {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = timestamp
            set(Calendar.HOUR_OF_DAY, 23)
            set(Calendar.MINUTE, 59)
            set(Calendar.SECOND, 59)
            set(Calendar.MILLISECOND, 999)
        }
        return calendar.timeInMillis
    }
    
    /**
     * Calculates days between two timestamps.
     * @param startTimestamp Start timestamp
     * @param endTimestamp End timestamp
     * @return Number of days between timestamps
     */
    fun daysBetween(startTimestamp: Long, endTimestamp: Long): Long {
        val diffMs = endTimestamp - startTimestamp
        return diffMs / (24 * 60 * 60 * 1000)
    }
}
