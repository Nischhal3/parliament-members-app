package com.example.parliamentmembers.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.parliamentmembers.model.ParliamentMember

/**
 * name: Nischhal Shrestha
 * id: 2012216
 * date: 4/10/2021
 *
 * Data Access Object of ParliamentMemberDataBase
 */

@Dao
interface ParliamentMemberDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: ParliamentMember)

    @Query("select * from ParliamentMember")
    fun getParliamentMembers(): LiveData<List<ParliamentMember>>
}