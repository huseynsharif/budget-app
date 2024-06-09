package com.huseynsharif.data.useCases

import com.huseynsharif.data.database.dao.RecordDao
import com.huseynsharif.domain.entities.Record
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSumOfIncomeUseCase @Inject constructor(
    private val recordDao: RecordDao
){

    operator fun invoke(): Flow<Double?> {
        return recordDao.getSumOfIncomes()
    }

}