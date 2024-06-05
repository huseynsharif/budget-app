package com.huseynsharif.records.viewModels

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.huseynsharif.core.base.BaseViewModel
import com.huseynsharif.data.api.CurrencyService
import com.huseynsharif.data.database.dao.RecordDao
import com.huseynsharif.domain.entities.Record
import com.huseynsharif.domain.entities.RecordType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordsViewModel @Inject constructor(
    private val recordDao: RecordDao,
    private val currencyService: CurrencyService
) : BaseViewModel<RecordsState, RecordsEffect, RecordsEvent>() {
    override fun getInitialState() = RecordsState(isLoading = false)

    fun getAllRecords(): Flow<List<Record>> {
        return recordDao.getAll()
    }

    fun findSumOfExpenses(): Flow<Double> {
        return findSumOfRecordList(getAllFiltered(RecordType.EXPENSES))
    }

    fun findSumOfIncome(): Flow<Double> {
        return findSumOfRecordList(getAllFiltered(RecordType.INCOME))
    }

    private fun findSumOfRecordList(records: Flow<List<Record>>): Flow<Double> {
        val sum = MutableStateFlow(0.0)
        viewModelScope.launch(Dispatchers.IO) {
            records.collect { recordsList ->
                sum.emit(recordsList.sumOf { record ->
                    record.amount
                })
            }
        }
        return sum
    }

    private fun getAllFiltered(type: RecordType): Flow<List<Record>> {

        val recordsFlow = getAllRecords()

        viewModelScope.launch(Dispatchers.IO) {
            recordsFlow.collect {recordsList ->
                recordsList.filter { record ->
                    record.type == type
                }
            }
        }

        return recordsFlow
    }

    fun testExchange(){
        viewModelScope.launch {
            val res = currencyService.exchange("USD", "AZN")
            Log.e("TEST", res.toString())
        }
    }

}