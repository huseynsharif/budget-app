package com.huseynsharif.data.useCases

import com.huseynsharif.data.database.dao.AccountDao
import com.huseynsharif.data.database.dao.RecordDao
import com.huseynsharif.domain.entities.Account
import com.huseynsharif.domain.entities.Record
import javax.inject.Inject

class AddAccountUseCase @Inject constructor(
    private val accountDao: AccountDao
){

    operator fun invoke(account: Account){
        accountDao.insert(account)
    }

}