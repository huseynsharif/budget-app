package com.huseynsharif.data.di

import android.content.Context
import androidx.room.Room
import com.huseynsharif.data.converters.AccountConverter
import com.huseynsharif.data.converters.AccountTypeConverter
import com.huseynsharif.data.converters.CategoryConverter
import com.huseynsharif.data.converters.RecordTypeConverter
import com.huseynsharif.data.database.dao.AccountDao
import com.huseynsharif.data.database.dao.CategoryDao
import com.huseynsharif.data.database.dao.RecordDao
import com.huseynsharif.data.database.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {


        return Room.databaseBuilder(
            context, AppDatabase::class.java, "budget-db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRecordDao(
        appDatabase: AppDatabase
    ): RecordDao {
        return appDatabase.recordDao()
    }

    @Provides
    @Singleton
    fun provideAccountDao(
        appDatabase: AppDatabase
    ): AccountDao {
        return appDatabase.accountDao()
    }

    @Provides
    @Singleton
    fun provideCategoryDao(
        appDatabase: AppDatabase
    ): CategoryDao {
        return appDatabase.categoryDao()
    }
}