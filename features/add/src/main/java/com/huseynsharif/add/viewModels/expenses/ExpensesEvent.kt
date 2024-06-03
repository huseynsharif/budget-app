package com.huseynsharif.add.viewModels.expenses

import com.huseynsharif.domain.entities.Record

sealed class ExpensesEvent {
    data class AddExpense(val record: Record) : ExpensesEvent()
}
