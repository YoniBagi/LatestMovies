package com.yehonatan.latestmovies.fragments.splashFragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.yehonatan.latestmovies.dataModel.MoviesData
import com.yehonatan.latestmovies.network.RetrofitManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashFragmentModel private constructor(){
    companion object{
        var moviesData: MutableLiveData<MoviesData> = MutableLiveData()
        get() {
            fetchData()
            return field
        }

        private fun fetchData(){
            val call : Call<MoviesData> = RetrofitManager.instanceServiceApi.getMoviesData()
            call.enqueue(object :Callback<MoviesData>{
                override fun onFailure(call: Call<MoviesData>, t: Throwable) {
                    Log.d("Fail", t.message)
                }

                override fun onResponse(call: Call<MoviesData>, response: Response<MoviesData>) {
                    if(response.isSuccessful){
                        moviesData.postValue(response.body())
                    }
                }
            })
        }

    }
}