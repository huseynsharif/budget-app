package com.huseynsharif.domain.repos

import com.huseynsharif.domain.entities.Account
import com.huseynsharif.domain.entities.Record
import kotlinx.coroutines.flow.Flow

interface AccountRepository {

    fun addAccount(account: Account)
    fun deleteAccount(account: Account)
    fun updateAccount(account: Account)
    fun getAll() : Flow<List<Account>>

}