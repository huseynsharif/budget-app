package com.huseynsharif.data.useCases

import com.huseynsharif.data.database.dao.AccountDao
import com.huseynsharif.domain.entities.Account
import javax.inject.Inject

class DeleteAccountBottomSheet @Inject constructor(
    private val accountDao: AccountDao
){

    operator fun invoke(account: Account){
        accountDao.delete(account)
    }

}