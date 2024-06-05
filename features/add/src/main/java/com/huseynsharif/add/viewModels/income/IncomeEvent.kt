package com.huseynsharif.add.viewModels.income

import com.huseynsharif.domain.entities.Account
import com.huseynsharif.domain.entities.Category
import com.huseynsharif.domain.entities.Record

sealed class IncomeEvent {

    data class AddIncome(val record: Record) : IncomeEvent()
    data class SetSelectedAccount(val account: Account) : IncomeEvent()
    data class SetSelectedCategory(val category: Category) : IncomeEvent()

}