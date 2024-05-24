package com.huseynsharif.data.converters

import androidx.room.TypeConverter
import com.huseynsharif.domain.entities.RecordType

class RecordTypeConverter {

    @TypeConverter
    fun fromRecordType(value: RecordType): String{
        return value.name
    }

    @TypeConverter
    fun toRecordType(value: String):RecordType{
        return RecordType.valueOf(value)
    }

}