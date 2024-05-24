package com.huseynsharif.data.converters

import androidx.room.TypeConverter
import com.huseynsharif.data.database.db.DatabaseManager
import com.huseynsharif.domain.entities.Account

class AccountConverter {

    @TypeConverter
    fun fromAccount(value: Account): Long{
        return value.id
    }

    @TypeConverter
    fun toAccount(id:Long): Account {
        return DatabaseManager.database.accountDao().getAccountById(id)
    }

}