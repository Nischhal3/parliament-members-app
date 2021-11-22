package com.example.parliamentmembers.repository

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.parliamentmembers.database.ReviewDataBase
import com.example.parliamentmembers.model.Review

/**
 * name: Nischhal Shrestha
 * date: 4/10/2021
 *
 * Medium class between database and viewModel class
 * Receives data from the user reviews
 * Inserts received data in the database via Dao
 */

class ReviewRepository(application: Application): AndroidViewModel(application) {
    //Reference of the Dao
    private val reviewDao = ReviewDataBase.getInstance(application).reviewDao
    //Fetching all the data from the database
    var reviewList: LiveData<List<Review>> = reviewDao.getAllReview()

    /**
     * @param review input review by user
     */
    suspend fun addReview(review: Review){
        reviewDao.addReview(review)
    }
}