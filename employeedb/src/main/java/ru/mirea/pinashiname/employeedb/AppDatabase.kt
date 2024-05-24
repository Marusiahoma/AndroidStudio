package ru.mirea.pinashiname.employeedb

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.mirea.pinashiname.employeedb.EmployeeDao
import ru.mirea.pinashiname.employeedb.Employee

@Database(entities = [Employee::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao?
}