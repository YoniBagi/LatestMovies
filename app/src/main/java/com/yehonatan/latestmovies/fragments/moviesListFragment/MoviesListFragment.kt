package com.yehonatan.latestmovies.fragments.moviesListFragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.yehonatan.latestmovies.R
import com.yehonatan.latestmovies.Util.Consts
import com.yehonatan.latestmovies.adapters.MovieListAdapter
import com.yehonatan.latestmovies.dataModel.Movie
import com.yehonatan.latestmovies.databinding.FragmentMoviesListBinding

class MoviesListFragment : Fragment(), MovieListAdapter.MovieListAdapterCallBack {

    private var mBinding: FragmentMoviesListBinding? = null
    private lateinit var moviesListFragmentViewModel: MoviesListFragmentViewModel
    private lateinit var moviesList: ArrayList<Movie>
    private var favouriteList: ArrayList<Movie> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val list = arguments?.getParcelableArrayList<Movie>(Consts.KEY_BUNDLE_LIST_MOVIES)
        list.let { moviesList = it as ArrayList<Movie> }
        setViewModel()
    }

    private fun checkFavouriteListFromDB() {
        moviesListFragmentViewModel.getFavouriteList(context)
    }

    private fun checkSelectedMovie() {
        for (favMovie: Movie in favouriteList) {
            for (movie: Movie in moviesList) {
                if (favMovie.id == movie.id) {
                    movie.selected = favMovie.selected
                }
            }
        }
    }

    private fun setViewModel() {
        moviesListFragmentViewModel =
            ViewModelProviders.of(this).get(MoviesListFragmentViewModel::class.java)
        moviesListFragmentViewModel.favouriteList.observe(this, Observer {
            favouriteList = ArrayList(it)
            checkSelectedMovie()
            setAdapter()
        })
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
        mBinding = binding
        checkFavouriteListFromDB()
        return binding.root
    }

    private fun setAdapter() {
        mBinding?.rvMovieList?.adapter = MovieListAdapter(moviesList, this)
    }

    fun onClickFav() {
        val bundle = Bundle()
        bundle.putParcelableArrayList(Consts.KEY_BUNDLE_FAV_LIST, favouriteList)
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_moviesListFragment_to_favouriteFragment, bundle)
    }

    override fun onClickItem(movie: Movie) {
        val bundle = Bundle()
        bundle.putParcelable(Consts.KEY_BUNDLE_MOVIE, movie)
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_moviesListFragment_to_detailsMovieFragment, bundle)
    }

    override fun onClickFav(movie: Movie, isSelected: Boolean) {
        if (isSelected) {
            favouriteList.add(movie)
            moviesListFragmentViewModel.addOrRemoveMovieToDB(movie, true, context)

        } else {
            favouriteList.remove(movie)
            moviesListFragmentViewModel.addOrRemoveMovieToDB(movie, false, context)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        moviesListFragmentViewModel.onDestroy()
    }
}
