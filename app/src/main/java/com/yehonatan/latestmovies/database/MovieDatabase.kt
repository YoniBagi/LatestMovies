package com.yehonatan.latestmovies.database

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yehonatan.latestmovies.dataModel.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        private var instance: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase? {
            if (instance == null) {
                synchronized(MovieDatabase::class) {
                    instance = Room.databaseBuilder(
                        context,
                        MovieDatabase::class.java, "movie_table"
                    ).build()
                }
            }
            return instance
        }
    }
}