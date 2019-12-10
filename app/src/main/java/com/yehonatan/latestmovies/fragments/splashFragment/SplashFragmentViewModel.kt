package com.yehonatan.latestmovies.fragments.splashFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.yehonatan.latestmovies.dataModel.MoviesData

class SplashFragmentViewModel : ViewModel() {
    private var moviesData: MutableLiveData<MoviesData> = MutableLiveData()
    private val observer = Observer<MoviesData>{
        moviesData.postValue(it)
    }

    init {
        SplashFragmentModel.moviesData.observeForever(observer)
    }

    fun getMoviesData(): LiveData<MoviesData>{
        return moviesData
    }

    override fun onCleared() {
        super.onCleared()
        SplashFragmentModel.moviesData.removeObserver(observer)
    }
}