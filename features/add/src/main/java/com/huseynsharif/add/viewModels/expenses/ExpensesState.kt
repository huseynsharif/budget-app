package com.huseynsharif.add.viewModels.expenses

import com.huseynsharif.domain.entities.Account
import com.huseynsharif.domain.entities.Category
import com.huseynsharif.domain.entities.RecordType

data class ExpensesState(
    val isLoading: Boolean?=false,
    var selectedCategory: Category?= Category("car", "car", RecordType.EXPENSES),
    var selectedAccount: Account?=null
)
