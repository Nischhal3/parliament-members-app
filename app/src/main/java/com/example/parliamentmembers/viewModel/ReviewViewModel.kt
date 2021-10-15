package com.example.parliamentmembers.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.parliamentmembers.model.Review
import com.example.parliamentmembers.repository.ReviewRepository
import kotlinx.coroutines.launch

/**
 * name: Nischhal Shrestha
 * id: 2012216
 * date: 9/10/2021
 *
 * Provides data for the UI via Repository from the database
 * Add data from the UI via Repository to the database
 */
class ReviewViewModel(application: Application): AndroidViewModel(application){
    private val repository =  ReviewRepository(application)
    val reviewList: LiveData<List<Review>> = repository.reviewList

    //Add review to the database
    fun addReview(review: Review){
        viewModelScope.launch {
            repository.addReview(review)
        }
    }
}