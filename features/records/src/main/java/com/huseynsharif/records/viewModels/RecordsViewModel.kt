package com.huseynsharif.records.viewModels

import com.huseynsharif.core.base.BaseViewModel
import com.huseynsharif.data.database.dao.RecordDao
import com.huseynsharif.domain.entities.Record
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class RecordsViewModel @Inject constructor(
    private val recordDao: RecordDao
) : BaseViewModel<RecordsState, RecordsEffect, RecordsEvent>() {
    override fun getInitialState() = RecordsState(isLoading = false)

    fun getAllRecords(): Flow<List<Record>> {
        return recordDao.getAll()
    }

}