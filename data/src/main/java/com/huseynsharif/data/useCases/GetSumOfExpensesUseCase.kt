package com.huseynsharif.data.useCases

import com.huseynsharif.data.database.dao.RecordDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSumOfExpensesUseCase @Inject constructor(
    private val recordDao: RecordDao
){

    operator fun invoke(): Flow<Double?> {
        return recordDao.getSumOfExpenses()
    }

}