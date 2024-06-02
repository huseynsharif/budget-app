package com.huseynsharif.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.huseynsharif.domain.entities.Account
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {

    @Query("SELECT * FROM accounts")
    fun getAll(): Flow<List<Account>>

    @Delete
    fun delete(account: Account)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(account: Account)

    @Update
    fun update(account: Account)

    @Query("SELECT * FROM accounts WHERE account_id = :id")
    fun getAccountById(id: Long): Account

}