package com.huseynsharif.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "records")
data class Record(
    @ColumnInfo(name = "type")
    val type: RecordType,
    @ColumnInfo(name = "note")
    val note:String,
    @ColumnInfo(name = "account")
    val account: Account,
    @ColumnInfo(name = "account_to")
    val accountTo: Account,
    @ColumnInfo(name = "amount")
    val amount:Double,
    @ColumnInfo(name = "amount_usd")
    val amountUsd: Double,
    @ColumnInfo(name = "amount_to")
    val amountTo:Double?=null,
    @ColumnInfo(name = "category")
    val category: Category,
    @ColumnInfo("record_id")
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0
)