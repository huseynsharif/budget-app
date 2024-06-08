package com.huseynsharif.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accounts")
data class Account(
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "type")
    var type: AccountType,
    @ColumnInfo(name = "currency")
    var currency: String,
    @ColumnInfo(name = "amount")
    var amount: Double,
    @ColumnInfo("account_id")
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
)
