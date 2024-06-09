package com.huseynsharif.data.useCases

import com.huseynsharif.data.database.dao.RecordDao
import com.huseynsharif.domain.entities.Record
import javax.inject.Inject

class DeleteRecordUseCase @Inject constructor(
    private val recordDao: RecordDao
){

    operator fun invoke(record: Record){
        recordDao.delete(record)
    }

}