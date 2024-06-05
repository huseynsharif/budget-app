package com.huseynsharif.add.viewModels.expenses

import androidx.lifecycle.viewModelScope
import com.huseynsharif.core.base.BaseViewModel
import com.huseynsharif.data.api.CurrencyService
import com.huseynsharif.data.database.dao.RecordDao
import com.huseynsharif.domain.entities.Account
import com.huseynsharif.domain.entities.Category
import com.huseynsharif.domain.entities.Record
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpensesViewModel @Inject constructor(
    private val recordDao: RecordDao,
    private val currencyService: CurrencyService
) : BaseViewModel<ExpensesState, ExpensesEffect, ExpensesEvent>(currencyService) {
    override fun getInitialState() = ExpensesState(isLoading = false)

    init {
        initEventSubscribers()
    }

    override fun onEventUpdate(event: ExpensesEvent) {
        super.onEventUpdate(event)

        when (event) {
            is ExpensesEvent.AddExpense -> saveRecord(event.record)
            is ExpensesEvent.SetSelectedAccount -> setSelectedAccount(event.account)
            is ExpensesEvent.SetSelectedCategory -> setSelectedCategory(event.category)
        }
    }

    private fun saveRecord(record: Record) {
        viewModelScope.launch(Dispatchers.IO) {
            record.amountUsd = exchange(record.account.currency, "USD", record.amount)
            recordDao.insert(record)
        }
    }

    fun getSelectedAccount(): Account? {
        return state.value.selectedAccount
    }

    fun getSelectedCategory(): Category? {
        return state.value.selectedCategory
    }

    private fun setSelectedCategory(category: Category) {
        setState(
            getCurrentState().copy(
                selectedCategory = category
            )
        )
    }

    private fun setSelectedAccount(account: Account) {
        setState(
            getCurrentState().copy(
                selectedAccount = account
            )
        )
    }


}