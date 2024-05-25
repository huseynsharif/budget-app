package com.huseynsharif.data.converters

import androidx.room.TypeConverter
import com.huseynsharif.domain.entities.AccountType

class AccountTypeConverter {

    @TypeConverter
    fun fromAccountType(value: AccountType): String{
        return value.name
    }

    @TypeConverter
    fun toRecordType(value: String): AccountType {
        return AccountType.valueOf(value)
    }

}