package com.example.parliamentmembers.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.parliamentmembers.model.Review

/**
 * name: Nischhal Shrestha
 * date: 9/10/2021
 *
 * Data Access Object of ReviewDataBase
 */

@Dao
interface ReviewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addReview(review: Review)

    @Query("select * from Review")
    fun getAllReview(): LiveData<List<Review>>
}