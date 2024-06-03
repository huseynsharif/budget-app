package com.huseynsharif.add.viewModels.expenses

import androidx.lifecycle.viewModelScope
import com.huseynsharif.core.base.BaseViewModel
import com.huseynsharif.data.database.dao.CategoryDao
import com.huseynsharif.data.database.dao.RecordDao
import com.huseynsharif.domain.entities.Category
import com.huseynsharif.domain.entities.Record
import com.huseynsharif.domain.entities.RecordType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ExpensesViewModel @Inject constructor(
    private val recordDao: RecordDao,
    private val categoryDao: CategoryDao
) : BaseViewModel<ExpensesState, ExpensesEffect, ExpensesEvent>() {
    override fun getInitialState() = ExpensesState(isLoading = false)

    init {
        initEventSubscribers()
    }

    override fun onEventUpdate(event: ExpensesEvent) {
        super.onEventUpdate(event)

        when (event) {
            is ExpensesEvent.AddExpense -> saveRecord(event.record)
        }
    }

    private fun saveRecord(record: Record) {
        viewModelScope.launch(Dispatchers.IO) {
            recordDao.insert(record)
        }
    }

    fun getCategoryCar(): Category {
        return Category("car", "car", RecordType.EXPENSES)
    }


}