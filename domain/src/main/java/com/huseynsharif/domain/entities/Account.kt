package com.huseynsharif.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accounts")
data class Account (
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "type")
    val type: AccountType,
    @ColumnInfo(name="currency")
    val currency:String,
    @ColumnInfo(name="amount")
    val amount:Double,
    @ColumnInfo(name="amount_usd")
    val amountUsd:Double?=null,
    @ColumnInfo("account_id")
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0
)
