package com.example.parliamentmembers.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * name: Nischhal Shrestha
 * id: 2012216
 * date: 4/10/2021
 *
 * Data class of ParliamentMember
 */

@Entity
data class ParliamentMember(
    val bornYear: Int,
    val constituency: String,
    val first: String,
    val last: String,
    val minister: Boolean,
    val party: String,
    @PrimaryKey
    var personNumber: Int,
    val picture: String,
    val seatNumber: Int,
    val twitter: String
)
