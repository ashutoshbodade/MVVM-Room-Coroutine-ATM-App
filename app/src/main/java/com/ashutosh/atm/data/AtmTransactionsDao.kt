package com.ashutosh.atm.data


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ashutosh.atm.model.AtmTransactionModel

@Dao
interface AtmTransactionsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAtmTran(vararg atmTransactionModel: AtmTransactionModel)

    @Query("SELECT * FROM atm_transactions ORDER BY system_time_in_millis DESC")
    suspend fun getAllAtmTranData(): List<AtmTransactionModel>

    @Query("SELECT * FROM atm_transactions ORDER BY system_time_in_millis DESC limit 5")
    suspend fun getLastAtmTranData(): List<AtmTransactionModel>


}