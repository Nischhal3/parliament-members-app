package com.example.parliamentmembers.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.parliamentmembers.database.ParliamentMemberDatabase
import com.example.parliamentmembers.model.ParliamentMember
import com.example.parliamentmembers.services.ParliamentMembersApi
import kotlinx.coroutines.launch

/**
 * name: Nischhal Shrestha
 * date: 4/10/2021
 *
 * Medium class between database, webserver and viewModel
 * Receives data from the server
 * Inserts received data in the database via Dao
 */

class ParliamentMemberRepository(application: Application) : AndroidViewModel(application) {
    //Reference of the Dao
    private val parliamentMemberDao =
        ParliamentMemberDatabase.getInstance(application).parliamentMemberDAO

    //Fetching all the data from the database
    var parliamentMemberList = parliamentMemberDao.getParliamentMembers()

    private val _fetchedMembersList = MutableLiveData<List<ParliamentMember>>()
    private val fetchedMemberList: LiveData<List<ParliamentMember>>
        get() = _fetchedMembersList

    //Initializing the functions
    init {
        viewModelScope.launch {
            try {
                getParliamentMember()
                insertMembers()
            } catch (e: Exception) {
                Log.d("****", e.toString())
            }
        }
    }

    //fetching data from server through retrofit
    private suspend fun getParliamentMember() {
        _fetchedMembersList.value = ParliamentMembersApi.retrofitService.getParliamentMembers()
    }

    //Inserting parliamentMember in database
    private suspend fun insertMembers() {
        fetchedMemberList.value?.forEach {
            parliamentMemberDao.insert(it)
        }
    }
}




