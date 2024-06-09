package com.huseynsharif.data.useCases

import com.huseynsharif.data.database.dao.AccountDao
import com.huseynsharif.domain.entities.Account
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllAccountsUseCase @Inject constructor(
    private val accountDao: AccountDao
){

    operator fun invoke(): Flow<List<Account>> {
        return accountDao.getAll()
    }

}