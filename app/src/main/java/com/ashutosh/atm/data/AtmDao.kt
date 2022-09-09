package com.ashutosh.atm.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ashutosh.atm.model.AtmModel

@Dao
interface AtmDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAtm(vararg atmModel: AtmModel)


    @Query("SELECT * FROM atm")
    suspend fun getAtmData(): List<AtmModel>

    @Update
    suspend fun updateAtm(data: AtmModel)

}