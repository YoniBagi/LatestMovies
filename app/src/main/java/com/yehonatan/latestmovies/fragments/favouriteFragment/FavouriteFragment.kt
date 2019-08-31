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
        list.let {
            favStaggeredGridView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            favStaggeredGridView.adapter = FavouriteAdapter(it as ArrayList<Movie>)
        }
    }


}



/*package com.yehonatan.latestmovies.fragments.favouriteFragment


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
import com.yehonatan.latestmovies.database.MovieDatabase
import kotlinx.android.synthetic.main.fragment_favourite.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class FavouriteFragment : Fragment(), CoroutineScope {

    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

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
        /*GlobalScope.launch(Dispatchers.Main) {
            val list = MovieDatabase.getInstance(context!!)?.movieDao()?.getAllFavouriteMovies()
            list?.value.let {
                favStaggeredGridView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                favStaggeredGridView.adapter = FavouriteAdapter(it as ArrayList<Movie>)
            }
        }*/
        launch {
           val list = MovieDatabase.getInstance(context!!)?.movieDao()?.getAllFavouriteMovies()
            favStaggeredGridView.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            favStaggeredGridView.adapter = list?.let { FavouriteAdapter(it) }
        }
        //val list = arguments?.getParcelableArrayList<Movie>(Consts.KEY_BUNDLE_FAV_LIST)
    }


}
*/