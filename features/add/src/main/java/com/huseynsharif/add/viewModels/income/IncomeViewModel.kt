package com.huseynsharif.add.viewModels.income

import androidx.lifecycle.viewModelScope
import com.huseynsharif.data.useCases.AddRecordUseCase
import com.huseynsharif.core.base.BaseViewModel
import com.huseynsharif.data.api.CurrencyService
import com.huseynsharif.domain.entities.Account
import com.huseynsharif.domain.entities.Category
import com.huseynsharif.domain.entities.Record
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IncomeViewModel @Inject constructor(
    private val addRecordUseCase: AddRecordUseCase,
    currencyService: CurrencyService
) : BaseViewModel<IncomeState, IncomeEffect, IncomeEvent>(currencyService) {
    override fun getInitialState() = IncomeState(isLoading = false)

    init {
        initEventSubscribers()
    }

    override fun onEventUpdate(event: IncomeEvent) {
        super.onEventUpdate(event)
        when (event) {
            is IncomeEvent.AddIncome -> saveIncome(event.record)
            is IncomeEvent.SetSelectedAccount -> setSelectedAccount(event.account)
            is IncomeEvent.SetSelectedCategory -> setSelectedCategory(event.category)
        }
    }

    private fun saveIncome(record: Record) {
        viewModelScope.launch(Dispatchers.IO) {
            record.amountUsd = if (record.account.currency == USD) {
                record.amount
            } else {
                exchange(record.account.currency, USD, record.amount)
            }
            addRecordUseCase(record)
        }
    }

    fun getSelectedAccount(): Account? {
        return state.value.selectedAccount
    }

    fun getSelectedCategory(): Category? {
        return state.value.selectedCategory
    }

    fun setSelectedCategory(category: Category) {
        setState(
            getCurrentState().copy(
                selectedCategory = category
            )
        )
    }

    fun setSelectedAccount(account: Account) {
        setState(
            getCurrentState().copy(
                selectedAccount = account
            )
        )
    }

    companion object{
        const val USD = "USD"
    }

}