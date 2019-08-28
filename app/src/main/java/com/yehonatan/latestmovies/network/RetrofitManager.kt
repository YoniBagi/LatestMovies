package com.yehonatan.latestmovies.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitManager private constructor(){
    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/4/"

        val instanceServiceApi : ServiceApi by lazy {
            val okHttpClient = OkHttpClient.Builder()
            okHttpClient.addInterceptor{cahin ->
            val request = cahin
                .request().
                newBuilder()
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1MjYzYjZjYzBjNWVjYTZiMTI5MWIyZWU0OWFlNTIxNCIsInN1YiI6IjVkNjJiMWJiN2Y2YzhkMDNhY2VjMTc0YSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.-Zdj1UvBn5JRjYaI-v-bfbE817qOBIt67AcF9QXD_8M")
                .build()
                cahin.proceed(request)
            }
            val retrofit = Retrofit.Builder()
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            retrofit.create(ServiceApi::class.java)
        }
    }
}