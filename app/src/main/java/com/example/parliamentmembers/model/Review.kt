package com.example.parliamentmembers.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * name: Nischhal Shrestha
 * date: 9/10/2021
 *
 * Data class of Review
 */

@Entity
data class Review(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val personId: Int,
    val comment: String,
    val rating: Int
)
