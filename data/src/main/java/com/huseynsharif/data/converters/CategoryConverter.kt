package com.huseynsharif.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.huseynsharif.domain.entities.Category

class CategoryConverter {

    private val gson = Gson()


    @TypeConverter
    fun fromCategory(value: Category?): String{
        return gson.toJson(value)
    }

    @TypeConverter
    fun toCategory(value:String?): Category {
        return gson.fromJson(value, Category::class.java)
    }

}