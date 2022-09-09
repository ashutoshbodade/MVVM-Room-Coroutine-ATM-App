package com.ashutosh.atm.di

import android.content.Context
import androidx.room.Room
import com.ashutosh.atm.data.AtmDao
import com.ashutosh.atm.data.AtmDatabase
import com.ashutosh.atm.data.AtmTransactionsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): AtmDatabase =
        Room.databaseBuilder(context, AtmDatabase::class.java, "atm-database")
            .fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun providesAtmDao(database: AtmDatabase): AtmDao = database.atmDao()

    @Singleton
    @Provides
    fun providesAtmTransactions(database: AtmDatabase): AtmTransactionsDao = database.atmTransactionsDao()

}