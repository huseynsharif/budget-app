package com.huseynsharif.data.repos

import com.huseynsharif.domain.entities.Record
import com.huseynsharif.domain.repos.RecordRepository
import kotlinx.coroutines.flow.Flow

class RecordRepositoryImpl :  RecordRepository{
    override fun addRecord(record: Record) {
        TODO("Not yet implemented")
    }

    override fun deleteRecord(record: Record) {
        TODO("Not yet implemented")
    }

    override fun updateRecord(record: Record) {
        TODO("Not yet implemented")
    }

    override fun getAll(): Flow<List<Record>> {
        TODO("Not yet implemented")
    }
}