package com.huseynsharif.data.database.db

import android.content.Context
import androidx.room.Room

object DatabaseManager {

    lateinit var database: AppDatabase

    fun initDatabase(context: Context) {
        database = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "budget-db"
        ).build()
    }

}