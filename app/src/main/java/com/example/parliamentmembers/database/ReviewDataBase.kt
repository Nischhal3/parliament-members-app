package com.example.parliamentmembers.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.parliamentmembers.model.Review

/**
 * name: Nischhal Shrestha
 * date: 9/10/2021
 *
 * Creating an actual instance of the database
 */

@Database(entities = [Review::class], version = 5, exportSchema = false)
abstract class ReviewDataBase : RoomDatabase() {
    abstract val reviewDao: ReviewDao

    companion object {
        // Singleton prevents multiple instances of database opening at the same time
        @Volatile
        private var INSTANCE: ReviewDataBase? = null

        fun getInstance(context: Context): ReviewDataBase {
            // Only one thread at a time can access this method
            // if the INSTANCE is not null, then returns it, if it is, then creates the database
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ReviewDataBase::class.java, "review_database"
                    )
                        .fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}