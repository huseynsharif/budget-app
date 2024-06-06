package com.huseynsharif.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "records")
data class Record(
    @ColumnInfo(name = "type")
    val type: RecordType,
    @ColumnInfo(name = "account")
    val account: Account,
    @ColumnInfo(name = "amount")
    val amount: Double,
    @ColumnInfo(name = "category")
    val category: Category,
    @ColumnInfo(name = "created_at")
    val createdAt: Long,
    @ColumnInfo(name = "note")
    val note: String? = null,
    @ColumnInfo(name = "account_to")
    val accountTo: Account? = null,
    @ColumnInfo(name = "amount_to")
    var amountTo: Double? = null,
    @ColumnInfo(name = "amount_usd")
    var amountUsd: Double? = null,
    @ColumnInfo("record_id")
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
)