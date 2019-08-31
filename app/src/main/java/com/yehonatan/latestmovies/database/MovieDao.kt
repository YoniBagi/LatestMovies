package com.yehonatan.latestmovies.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.yehonatan.latestmovies.dataModel.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)

    @Query("SELECT * FROM movie_table")
    suspend fun getAllFavouriteMovies(): List<Movie>

    @Delete
    suspend fun deleteMovie(movie: Movie)
}