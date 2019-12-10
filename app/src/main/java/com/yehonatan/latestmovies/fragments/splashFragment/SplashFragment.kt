package com.yehonatan.latestmovies.fragments.splashFragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.yehonatan.latestmovies.R
import com.yehonatan.latestmovies.Util.Consts
import com.yehonatan.latestmovies.dataModel.MoviesData

class SplashFragment : Fragment() {
    private lateinit var splashFragmentViewModel: SplashFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setViewModel()
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    private fun setViewModel() {
        splashFragmentViewModel =
            ViewModelProviders.of(this).get(SplashFragmentViewModel::class.java)
        splashFragmentViewModel.getMoviesData().observe(this, Observer { fetchMoviesSuccess(it) })
    }

    private fun fetchMoviesSuccess(moviesData: MoviesData?) {
        val bundle = Bundle()
        moviesData?.let { bundle.putParcelableArrayList(Consts.KEY_BUNDLE_LIST_MOVIES, it.movies) }
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_splashFragment_to_movieListFragment, bundle)
    }


}
