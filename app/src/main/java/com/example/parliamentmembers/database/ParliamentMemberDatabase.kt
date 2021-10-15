package com.example.parliamentmembers.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.parliamentmembers.model.ParliamentMember

/**
 * name: Nischhal Shrestha
 * id: 2012216
 * date: 4/10/2021
 *
 * Creating an actual instance of the database
 */

@Database(entities = [ParliamentMember::class], version = 5, exportSchema = false)
abstract class ParliamentMemberDatabase : RoomDatabase() {
    abstract val parliamentMemberDAO: ParliamentMemberDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the same time
        @Volatile
        private var INSTANCE: ParliamentMemberDatabase? = null

        fun getInstance(context: Context): ParliamentMemberDatabase {
            // Only one thread at a time can access this method
            // if the INSTANCE is not null, then return it, if it is, then creates the database
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ParliamentMemberDatabase::class.java, "parliament_database"
                    )
                        .fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}