package com.ashutosh.atm.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "atm_transactions")
data class AtmTransactionModel(
    var amount: Int,
    val notes_one_hun: Int,
    val notes_two_hun: Int,
    val notes_five_hun: Int,
    val notes_two_thousand: Int,
    val system_time_in_millis: Long,
    @PrimaryKey(autoGenerate = true)
    var id: Int=0,
)