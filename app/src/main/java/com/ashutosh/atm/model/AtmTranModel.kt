package com.ashutosh.atm.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "atm")
data class AtmTranModel(
    @PrimaryKey
    var id: Long,
    var amount: Long,
    val notes_one_hun: Long,
    val notes_two_hun: Long,
    val notes_five_hun: Long,
    val notes_two_thousand: Long,
    val date_time:Long
)