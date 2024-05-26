package com.huseynsharif.add.viewModels.expenses

import com.huseynsharif.core.base.BaseViewModel

class ExpensesViewModel : BaseViewModel<ExpensesState, ExpensesEffect, ExpensesEvent>() {
    override fun getInitialState() = ExpensesState(isLoading = false)
}