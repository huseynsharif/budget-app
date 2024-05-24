package com.huseynsharif.data.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.huseynsharif.data.database.dao.RecordDao
import com.huseynsharif.domain.entities.Account
import com.huseynsharif.domain.entities.Category
import com.huseynsharif.domain.entities.Record

@Database(
    entities =[Record::class, Account::class, Category::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun recordDao():RecordDao

}