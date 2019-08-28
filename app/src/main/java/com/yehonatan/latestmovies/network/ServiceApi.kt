package com.yehonatan.latestmovies.network

import com.yehonatan.latestmovies.dataModel.MoviesData
import retrofit2.Call
import retrofit2.http.GET

interface ServiceApi {
    @GET("discover/movie")
    fun  getMoviesData(): Call<MoviesData>
}