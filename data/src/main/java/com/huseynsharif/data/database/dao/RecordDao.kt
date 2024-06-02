package com.huseynsharif.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.huseynsharif.domain.entities.Record
import kotlinx.coroutines.flow.Flow


@Dao
interface RecordDao {

    @Query("SELECT * FROM records")
    fun getAll(): Flow<List<Record>>

    @Delete
    fun delete(record: Record)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(record: Record)

    @Update
    fun update(record: Record)

}