package com.huseynsharif.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class Category (
    @ColumnInfo(name = "title")
    val title:String,
    @ColumnInfo(name = "icon")
    val icon:String,
    @ColumnInfo(name = "type")
    val type: RecordType,
    @ColumnInfo("category_id")
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0
)