package com.huseynsharif.data.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.huseynsharif.data.converters.AccountConverter
import com.huseynsharif.data.converters.CategoryConverter
import com.huseynsharif.data.converters.RecordTypeConverter
import com.huseynsharif.data.database.dao.AccountDao
import com.huseynsharif.data.database.dao.CategoryDao
import com.huseynsharif.data.database.dao.RecordDao
import com.huseynsharif.domain.entities.Account
import com.huseynsharif.domain.entities.Category
import com.huseynsharif.domain.entities.Record

@Database(
    entities =[Record::class, Account::class, Category::class],
    version = 1
)
@TypeConverters(RecordTypeConverter::class, AccountConverter::class, CategoryConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun recordDao():RecordDao
    abstract fun accountDao():AccountDao
    abstract fun categoryDao():CategoryDao

}