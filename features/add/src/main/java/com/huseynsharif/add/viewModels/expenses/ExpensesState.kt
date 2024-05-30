package com.huseynsharif.add.viewModels.expenses

import com.huseynsharif.domain.entities.Category
import com.huseynsharif.domain.entities.RecordType

data class ExpensesState(
    val isLoading: Boolean,
    var selectedCategory: Category?= Category("car", "car", RecordType.EXPENSES)
)
