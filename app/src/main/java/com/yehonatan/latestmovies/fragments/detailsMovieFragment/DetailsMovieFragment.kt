package com.yehonatan.latestmovies.fragments.detailsMovieFragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yehonatan.latestmovies.R

/**
 * A simple [Fragment] subclass.
 */
class DetailsMovieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_movie, container, false)
    }


}
