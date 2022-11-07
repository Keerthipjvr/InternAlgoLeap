package com.example.algoleap

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room

@Database(entities = [EmpEnt::class], version = 1)
abstract class AppDb: RoomDatabase() {

    abstract fun getEmpDao(): EmpDao

    companion object {
        @Volatile
        private var instance: AppDb? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDb::class.java,
            "app-db"
        ).build()
    }
}