package com.huseynsharif.domain.repos

import com.huseynsharif.domain.entities.Record
import kotlinx.coroutines.flow.Flow

interface RecordRepository {

    fun addRecord(record: Record)
    fun deleteRecord(record: Record)
    fun updateRecord(record: Record)
    fun getAll() : Flow<List<Record>>
    
}