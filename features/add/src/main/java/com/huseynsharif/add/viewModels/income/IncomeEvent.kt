package com.huseynsharif.add.viewModels.income

import com.huseynsharif.domain.entities.Record

sealed class IncomeEvent {

    data class AddIncome(val record: Record) : IncomeEvent()

}