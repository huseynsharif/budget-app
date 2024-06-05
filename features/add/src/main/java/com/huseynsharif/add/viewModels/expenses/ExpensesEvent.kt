package com.huseynsharif.add.viewModels.expenses

import com.huseynsharif.domain.entities.Account
import com.huseynsharif.domain.entities.Category
import com.huseynsharif.domain.entities.Record

sealed class ExpensesEvent {
    data class AddExpense(val record: Record) : ExpensesEvent()
    data class SetSelectedAccount(val account: Account) : ExpensesEvent()
    data class SetSelectedCategory(val category: Category) : ExpensesEvent()
}
