package com.huseynsharif.add.viewModels.income

import com.huseynsharif.domain.entities.Account
import com.huseynsharif.domain.entities.Category
import com.huseynsharif.domain.entities.RecordType

data class IncomeState(

    val isLoading: Boolean?=false,
    var selectedCategory: Category?= Category("salary", "salary", RecordType.INCOME),
    var selectedAccount: Account?=null

)
