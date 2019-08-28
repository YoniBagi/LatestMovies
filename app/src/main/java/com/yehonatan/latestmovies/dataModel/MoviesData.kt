package com.yehonatan.latestmovies.dataModel


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviesData(
    @SerializedName("page")
    var page: Int,
    @SerializedName("results")
    var movies: ArrayList<Movie>,
    @SerializedName("total_pages")
    var totalPages: Int,
    @SerializedName("total_results")
    var totalResults: Int
) : Parcelable