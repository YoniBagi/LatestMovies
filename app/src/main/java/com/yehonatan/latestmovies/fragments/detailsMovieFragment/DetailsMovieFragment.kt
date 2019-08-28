package com.yehonatan.latestmovies.fragments.detailsMovieFragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.yehonatan.latestmovies.R
import com.yehonatan.latestmovies.Util.Consts
import com.yehonatan.latestmovies.dataModel.Movie
import com.yehonatan.latestmovies.databinding.FragmentDetailsMovieBinding

class DetailsMovieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentDetailsMovieBinding>(
            inflater,
            R.layout.fragment_details_movie,
            container,
            false
        )
        setViews(binding)
        return binding.root
    }

    private fun setViews(binding: FragmentDetailsMovieBinding) {
        binding.detailsMovieFragment = this
        val movie = arguments?.getParcelable<Movie>(Consts.KEY_BUNDLE_MOVIE)
        movie.let { binding.movie = it }
        binding.ratingBar.rating = movie?.voteAverage?.toFloat() ?: 0f
    }


}
