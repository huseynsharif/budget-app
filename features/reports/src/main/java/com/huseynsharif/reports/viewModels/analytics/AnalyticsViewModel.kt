package com.huseynsharif.reports.viewModels.analytics

import com.huseynsharif.core.base.BaseViewModel
import com.huseynsharif.data.database.dao.RecordDao
import com.huseynsharif.data.useCases.GetSumOfExpensesUseCase
import com.huseynsharif.data.useCases.GetSumOfIncomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class AnalyticsViewModel @Inject constructor(
    private val getSumOfExpensesUseCase: GetSumOfExpensesUseCase,
    private val getSumOfIncomeUseCase: GetSumOfIncomeUseCase
): BaseViewModel<AnalyticsState, AnalyticsEffect, AnalyticsEvent>() {
    override fun getInitialState() = AnalyticsState(isLoading = false)

    fun findSumOfExpenses(): Flow<Double?> {
        return getSumOfExpensesUseCase()
    }

    fun findSumOfIncome(): Flow<Double?> {
        return getSumOfIncomeUseCase()
    }

}