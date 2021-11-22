package com.example.parliamentmembers.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.example.parliamentmembers.repository.ParliamentMemberRepository

/**
 * name: Nischhal Shrestha
 * date: 5/10/2021
 *
 * Provides data for the UI via Repository from the database
 */
class ParliamentMemberViewModel(application: Application): AndroidViewModel(application){
    val parliamentMemberList = ParliamentMemberRepository(application).parliamentMemberList
}