package com.huseynsharif.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.huseynsharif.data.database.dao.AccountDao
import com.huseynsharif.data.database.db.AppDatabase
import com.huseynsharif.domain.entities.Account
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class AccountConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromAccount(value: Account): String{
        return gson.toJson(value)
    }

    @TypeConverter
    fun toAccount(value: String): Account {
        return gson.fromJson(value, Account::class.java)
    }

}