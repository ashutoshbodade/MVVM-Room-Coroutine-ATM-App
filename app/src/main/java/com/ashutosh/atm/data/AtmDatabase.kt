package com.ashutosh.atm.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ashutosh.atm.model.AtmModel
import com.ashutosh.atm.model.AtmTransactionModel

@Database(entities = [AtmModel::class,AtmTransactionModel::class], version = 4, exportSchema = false)
abstract class AtmDatabase : RoomDatabase() {

    abstract fun atmDao(): AtmDao

    abstract fun atmTransactionsDao(): AtmTransactionsDao

}