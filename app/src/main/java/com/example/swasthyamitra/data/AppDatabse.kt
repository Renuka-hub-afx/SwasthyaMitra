package com.example.swasthyamitra.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// 1. Annotate the class to be a Room Database
// 2. List all your tables (Entities) inside the array
// 3. Increment the version number whenever you change the table structure
@Database(entities = [User::class, Goal::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    // Declare your DAOs here so the app can access them
    abstract fun userDao(): UserDao
    abstract fun goalDao(): GoalDao

    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "swasthyamitra_database" // This is the actual name of the file on the phone
                )
                    // Wipes and rebuilds DB if you change the schema (useful for dev/testing)
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}