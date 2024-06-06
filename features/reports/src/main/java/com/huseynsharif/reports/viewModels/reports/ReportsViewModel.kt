package com.huseynsharif.reports.viewModels.reports

import com.huseynsharif.core.base.BaseViewModel
import com.huseynsharif.data.api.CurrencyService
import com.huseynsharif.data.database.dao.RecordDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ReportsViewModel @Inject constructor(
    private val recordDao: RecordDao,
    currencyService: CurrencyService
) : BaseViewModel<ReportsState, ReportsEffect, ReportsEvent>(currencyService) {
    override fun getInitialState() = ReportsState(isLoading = false)


    fun findSumOfExpenses(): Flow<Double> {
        return recordDao.getSumOfExpenses()
    }

    fun findSumOfIncome(): Flow<Double> {
        return recordDao.getSumOfIncomes()
    }

}