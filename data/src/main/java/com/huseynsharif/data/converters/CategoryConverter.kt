package com.huseynsharif.data.converters

import androidx.room.TypeConverter
import com.huseynsharif.data.database.db.DatabaseManager
import com.huseynsharif.domain.entities.Account
import com.huseynsharif.domain.entities.Category

class CategoryConverter {

    @TypeConverter
    fun fromCategory(value: Category): Long{
        return value.id
    }

    @TypeConverter
    fun toCategory(id:Long): Category {
        return DatabaseManager.database.categoryDao().getCategoryById(id)
    }

}