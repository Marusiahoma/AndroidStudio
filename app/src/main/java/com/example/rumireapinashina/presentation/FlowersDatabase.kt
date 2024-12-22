package com.example.rumireapinashina.presentation

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.FlowerDao
import com.example.data.model.FlowerEntity


@Database(entities = [FlowerEntity::class], version = 3, exportSchema = false)
abstract class FlowersDatabase : RoomDatabase() {

    abstract fun taskDao(): FlowerDao

    companion object {

        @Volatile
        private var INSTANCE: FlowersDatabase? = null

        fun getDatabase(context: Context): FlowersDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FlowersDatabase::class.java,
                    "flowers_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
