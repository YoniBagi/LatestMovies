package com.yehonatan.latestmovies.fragments.moviesListFragment

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.yehonatan.latestmovies.dataModel.Movie
import com.yehonatan.latestmovies.database.MovieDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MoviesListFragmentModel private constructor() : CoroutineScope {

    companion object {
        private var moviesListFragmentModel: MoviesListFragmentModel? = null
        fun getInstance(): MoviesListFragmentModel? {
            if (moviesListFragmentModel == null) {
                moviesListFragmentModel = MoviesListFragmentModel()
            }
            return moviesListFragmentModel
        }
    }

    private var job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    var favouriteList = MutableLiveData<List<Movie>>()
    fun getFavouriteList(context: Context?) {
        launch {
            favouriteList.postValue(context?.let {
                MovieDatabase.getInstance(it)?.movieDao()?.getAllFavouriteMovies()
            })
        }
    }

    fun addOrRemoveMovieToDB(
        movie: Movie,
        needToAdd: Boolean,
        context: Context?
    ) {
        launch {
            if (needToAdd) context?.let {
                MovieDatabase.getInstance(it)?.movieDao()?.insertMovie(movie)
            }
            else context?.let { MovieDatabase.getInstance(it)?.movieDao()?.deleteMovie(movie) }
        }
    }

    fun onDestroy() {
        job.cancel()
    }


}