package com.yehonatan.latestmovies.fragments.moviesListFragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.yehonatan.latestmovies.R
import com.yehonatan.latestmovies.Util.Consts
import com.yehonatan.latestmovies.adapters.MovieListAdapter
import com.yehonatan.latestmovies.dataModel.Movie
import com.yehonatan.latestmovies.databinding.FragmentMoviesListBinding


class MoviesListFragment : Fragment(), MovieListAdapter.MovieListAdapterCallBack {

    private lateinit var moviesList: ArrayList<Movie>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val list = arguments?.getParcelableArrayList<Movie>(Consts.KEY_BUNDLE_LIST_MOVIES)
        list.let { moviesList = it as ArrayList<Movie> }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMoviesListBinding>(
            inflater,
            R.layout.fragment_movies_list,
            container,
            false
        )
        binding.movieListFragment = this
        return binding.root
    }

    fun getAdapter(): MovieListAdapter? {
        return MovieListAdapter(moviesList, this)
    }

    override fun onClickItem(movie: Movie) {
        val bundle = Bundle()
        bundle.putParcelable(Consts.KEY_BUNDLE_MOVIE, movie)
        NavHostFragment.findNavController(this).navigate(R.id.action_moviesListFragment_to_detailsMovieFragment, bundle)
    }

}
