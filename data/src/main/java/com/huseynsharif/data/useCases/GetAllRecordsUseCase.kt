package com.huseynsharif.data.useCases

import com.huseynsharif.data.database.dao.RecordDao
import com.huseynsharif.domain.entities.Record
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllRecordsUseCase @Inject constructor(
    private val recordDao: RecordDao
){
    operator fun invoke(): Flow<List<Record>> {
        return recordDao.getAll()
    }

}