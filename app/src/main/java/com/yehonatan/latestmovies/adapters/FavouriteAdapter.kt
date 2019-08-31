package com.yehonatan.latestmovies.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.yehonatan.latestmovies.R
import com.yehonatan.latestmovies.dataModel.Movie
import com.yehonatan.latestmovies.databinding.ItemFavouriteStaggeredGridViewBinding

class FavouriteAdapter(private val favouriteList: ArrayList<Movie>) :
    RecyclerView.Adapter<FavouriteAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemFavouriteStaggeredGridViewBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_favourite_staggered_grid_view,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return favouriteList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(favouriteList[position])
    }

    inner class ViewHolder(var binding: ItemFavouriteStaggeredGridViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(movie: Movie) {
            binding.favouriteAdapter = this
            binding.movie = movie
            binding.executePendingBindings()
        }
    }
}