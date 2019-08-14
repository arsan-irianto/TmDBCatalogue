package com.arsan.tmdbcatalogue.ui.movies.favorites


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.arsan.tmdbcatalogue.R
import com.arsan.tmdbcatalogue.data.models.FavMovie
import com.arsan.tmdbcatalogue.ui.home.HomeActivity
import com.arsan.tmdbcatalogue.ui.movies.details.DetailMovieActivity
import com.arsan.tmdbcatalogue.vo.Status
import kotlinx.android.synthetic.main.fragment_fav_movies.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavMoviesFragment : Fragment() {

    private val viewModel: FavMoviesVM by viewModel()
    private var movies: MutableList<FavMovie> = mutableListOf()
    private lateinit var favMoviesAdapter: FavMoviesAdapter

    companion object {
        fun instance(): FavMoviesFragment = FavMoviesFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sr_fav_movies.isRefreshing = false

        if (activity != null) {

            loadFavMovies()

            favMoviesAdapter = FavMoviesAdapter(requireContext(), movies) {
                val intent = Intent(requireContext(), DetailMovieActivity::class.java).apply {
                    putExtra("id", it.id)
                    putExtra("title", it.title)
                    putExtra("overview", it.overview)
                    putExtra("poster_path", it.poster_path)
                    putExtra("backdrop_path", it.backdrop_path)
                    putExtra("vote_average", it.vote_average)
                }
                startActivity(intent)
            }
            rv_fav_movies.adapter = favMoviesAdapter
            rv_fav_movies.layoutManager = LinearLayoutManager(requireContext())
        }

        sr_fav_movies.setOnRefreshListener {
            loadFavMovies()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fav_movies, container, false)
    }

    private fun loadFavMovies() {
        viewModel.setData("FavMovieList")
        viewModel.liveData.observe(activity as HomeActivity, Observer {
            when (it.status) {
                Status.LOADING -> pb_fav_movies.visibility = View.VISIBLE
                Status.SUCCESS -> it.data?.let { data ->
                    favMoviesAdapter.submitList(data)
                    showFavMovies(data)
                }
                Status.ERROR -> pb_fav_movies.visibility = View.GONE
            }
        })
        sr_fav_movies.isRefreshing = false
    }

    private fun showFavMovies(data: List<FavMovie>) {
        movies.clear()
        movies.addAll(data)
        favMoviesAdapter.notifyDataSetChanged()
        sr_fav_movies.isRefreshing = false
        pb_fav_movies.visibility = View.INVISIBLE
    }

}
