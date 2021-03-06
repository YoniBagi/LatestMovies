package com.yehonatan.latestmovies.fragments.favouriteFragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.yehonatan.latestmovies.R
import com.yehonatan.latestmovies.Util.Consts
import com.yehonatan.latestmovies.adapters.FavouriteAdapter
import com.yehonatan.latestmovies.dataModel.Movie
import kotlinx.android.synthetic.main.fragment_favourite.*

class FavouriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
    }

    private fun setAdapter() {
        val list = arguments?.getParcelableArrayList<Movie>(Consts.KEY_BUNDLE_FAV_LIST)
        list?.let {
            if (it.isEmpty()) {
                tvAddFav.visibility = View.VISIBLE
            } else {
                tvAddFav.visibility = View.GONE
                val manager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                manager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
                favStaggeredGridView.layoutManager = manager
                favStaggeredGridView.adapter = FavouriteAdapter(it as ArrayList<Movie>)
            }
        }
    }
}