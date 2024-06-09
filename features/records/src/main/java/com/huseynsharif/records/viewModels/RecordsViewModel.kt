package com.huseynsharif.records.viewModels

import com.huseynsharif.core.base.BaseViewModel
import com.huseynsharif.data.database.dao.RecordDao
import com.huseynsharif.data.useCases.GetAllRecordsUseCase
import com.huseynsharif.data.useCases.GetSumOfExpensesUseCase
import com.huseynsharif.data.useCases.GetSumOfIncomeUseCase
import com.huseynsharif.domain.entities.Record
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class RecordsViewModel @Inject constructor(
    private val getAllRecordsUseCase: GetAllRecordsUseCase,
    private val getSumOfExpensesUseCase: GetSumOfExpensesUseCase,
    private val getSumOfIncomeUseCase: GetSumOfIncomeUseCase
) : BaseViewModel<RecordsState, RecordsEffect, RecordsEvent>() {
    override fun getInitialState() = RecordsState(isLoading = false)

    fun getAllRecords(): Flow<List<Record>> {
        return getAllRecordsUseCase()
    }

    fun findSumOfExpenses(): Flow<Double?> {
        return getSumOfExpensesUseCase()
    }

    fun findSumOfIncome(): Flow<Double?> {
        return getSumOfIncomeUseCase()
    }
}