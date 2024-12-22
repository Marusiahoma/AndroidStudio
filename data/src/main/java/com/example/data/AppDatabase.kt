package com.example.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.model.FlowerEntity

@Database(entities = [FlowerEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun FlowerDao(): FlowerDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "flowers_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

