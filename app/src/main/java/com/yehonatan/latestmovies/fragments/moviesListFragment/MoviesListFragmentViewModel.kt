package com.yehonatan.latestmovies.fragments.moviesListFragment

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.yehonatan.latestmovies.dataModel.Movie

class MoviesListFragmentViewModel: ViewModel() {
    var favouriteList = MutableLiveData<List<Movie>>()
    private val observer = Observer<List<Movie>>{
        favouriteList.postValue(it)
    }

    init {
        MoviesListFragmentModel.getInstance()?.favouriteList?.observeForever(observer)
    }
    fun addOrRemoveMovieToDB(
        movie: Movie,
        needToAdd: Boolean,
        context: Context?
    ) {
        MoviesListFragmentModel.getInstance()?.addOrRemoveMovieToDB(movie, needToAdd, context)
    }

    fun onDestroy() {
        MoviesListFragmentModel.getInstance()?.onDestroy()
    }

    fun getFavouriteList(context: Context?) {
        MoviesListFragmentModel.getInstance()?.getFavouriteList(context)
    }

    override fun onCleared() {
        super.onCleared()
        MoviesListFragmentModel.getInstance()?.favouriteList?.removeObserver(observer)
    }
}