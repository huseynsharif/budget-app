package com.huseynsharif.reports.viewModels.analytics

import com.huseynsharif.core.base.BaseViewModel
import com.huseynsharif.data.database.dao.RecordDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class AnalyticsViewModel @Inject constructor(
    private val recordDao: RecordDao
): BaseViewModel<AnalyticsState, AnalyticsEffect, AnalyticsEvent>() {
    override fun getInitialState() = AnalyticsState(isLoading = false)

    fun findSumOfExpenses(): Flow<Double?> {
        return recordDao.getSumOfExpenses()
    }

    fun findSumOfIncome(): Flow<Double?> {
        return recordDao.getSumOfIncomes()
    }

}