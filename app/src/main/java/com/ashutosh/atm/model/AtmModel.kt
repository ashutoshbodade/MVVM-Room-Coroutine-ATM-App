package com.ashutosh.atm.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "atm")
data class AtmModel(
    @PrimaryKey
    var id: Int,
    var amount: Int,
    var notes_one_hun: Int,
    var notes_two_hun: Int,
    var notes_five_hun: Int,
    var notes_two_thousand: Int
)