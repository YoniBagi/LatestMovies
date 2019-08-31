package com.yehonatan.latestmovies.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.yehonatan.latestmovies.R
import com.yehonatan.latestmovies.dataModel.Movie
import com.yehonatan.latestmovies.databinding.ItemMovieRecyclerBinding


class MovieListAdapter(
    private val mMovieList: List<Movie>,
    internal var mMovieListAdapterCallBack: MovieListAdapterCallBack
) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return mMovieList.size
    }


    @NonNull
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemMovieRecyclerBinding>(
            LayoutInflater.from(viewGroup.context),
            R.layout.item_movie_recycler,
            viewGroup,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.onBind(mMovieList[i], i)
    }

    inner class ViewHolder(var mBinding: ItemMovieRecyclerBinding) :
        RecyclerView.ViewHolder(mBinding.root) {

        fun onBind(movie: Movie, pos: Int) {
            mBinding.movieListAdapter = this
            mBinding.movie = movie
            mBinding.position = pos
            mBinding.ivFav.isSelected = movie.selected
            mBinding.executePendingBindings()
        }

        fun onClickItem(movie: Movie, position: Int) {
            mMovieListAdapterCallBack.onClickItem(movie)
        }

        fun onClickFav(imageView: ImageView, movie: Movie) {
            if (movie.selected) {
                movie.selected = false
                imageView.isSelected = false
            } else {
                movie.selected = true
                imageView.isSelected = true
            }
            mMovieListAdapterCallBack.onClickFav(movie, imageView.isSelected)
        }
    }

    interface MovieListAdapterCallBack {
        fun onClickItem(movie: Movie)
        fun onClickFav(movie: Movie, isSelected: Boolean)
    }
}