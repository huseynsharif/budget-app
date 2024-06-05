package com.huseynsharif.add.viewModels.income

import androidx.lifecycle.viewModelScope
import com.huseynsharif.core.base.BaseViewModel
import com.huseynsharif.data.database.dao.RecordDao
import com.huseynsharif.domain.entities.Account
import com.huseynsharif.domain.entities.Category
import com.huseynsharif.domain.entities.Record
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IncomeViewModel @Inject constructor(
    private val recordDao: RecordDao
) : BaseViewModel<IncomeState, IncomeEffect, IncomeEvent>() {
    override fun getInitialState() = IncomeState(isLoading = false)

    init {
        initEventSubscribers()
    }

    override fun onEventUpdate(event: IncomeEvent) {
        super.onEventUpdate(event)

        when (event) {
            is IncomeEvent.AddIncome -> saveRecord(event.record)
        }
    }

    private fun saveRecord(record: Record) {
        viewModelScope.launch(Dispatchers.IO) {
            recordDao.insert(record)
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

}