package com.huseynsharif.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.huseynsharif.domain.entities.Account
import com.huseynsharif.domain.entities.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Query("SELECT * FROM categories")
    fun getAll(): Flow<List<Category>>

    @Delete
    fun delete(category : Category)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(category : Category)

    @Update
    fun update(category : Category)

    @Query("SELECT * FROM categories WHERE category_id = :id")
    fun getCategoryById(id:Long): Category

}