package com.huseynsharif.reports.viewModels.accounts

import androidx.lifecycle.viewModelScope
import com.huseynsharif.core.base.BaseViewModel
import com.huseynsharif.data.api.CurrencyService
import com.huseynsharif.data.database.dao.AccountDao
import com.huseynsharif.domain.entities.Account
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountsViewModel @Inject constructor(
    private val accountDao: AccountDao,
    currencyService: CurrencyService
) : BaseViewModel<AccountsState, AccountsEffect, AccountsEvent>(currencyService) {
    override fun getInitialState() = AccountsState(isLoading = false)

    fun getTotalBalance(): Flow<Double> {
        val totalBalance = MutableStateFlow(0.0)

        viewModelScope.launch {
            accountDao.getAll().collect { accountsList ->
                var total = 0.0
                accountsList.forEach { account ->
                    total += if (account.currency == USD) {
                        account.amount
                    } else {
                        exchange(account.currency, USD, account.amount)
                    }
                }
                totalBalance.emit(total)
            }
        }

        return totalBalance.asStateFlow()
    }

    fun getAllAccounts(): Flow<List<Account>> {
        return accountDao.getAll()
    }


    companion object{
        const val USD = "USD"
    }

}