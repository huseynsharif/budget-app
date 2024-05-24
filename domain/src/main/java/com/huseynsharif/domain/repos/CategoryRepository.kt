package com.huseynsharif.domain.repos

import com.huseynsharif.domain.entities.Category
import com.huseynsharif.domain.entities.Record
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    fun addCategory(category: Category)
    fun deleteCategory(category: Category)
    fun updateCategory(category: Category)
    fun getAll() : Flow<List<Category>>

}