package com.arsan.tmdbcatalogue.ui.movies

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.arsan.tmdbcatalogue.R
import com.arsan.tmdbcatalogue.data.models.Movie
import com.arsan.tmdbcatalogue.ui.home.HomeActivity
import com.arsan.tmdbcatalogue.vo.Status
import kotlinx.android.synthetic.main.fragment_movies.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.IllegalStateException

class MoviesFragment : Fragment() {

    private val viewModel: MoviesViewModel by viewModel()
    private var movies: MutableList<Movie> = mutableListOf()
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sr_movies.isRefreshing = false
        if (activity != null) {

            loadDataMovies()

            moviesAdapter = MoviesAdapter(requireContext(), movies) {
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

            rv_movies.adapter = moviesAdapter
            rv_movies.layoutManager = LinearLayoutManager(requireContext())
        }

        sr_movies.setOnRefreshListener {
            loadDataMovies()
        }
    }


    private fun loadDataMovies() {
        viewModel.setData("MovieList")
        viewModel.liveData.observe(activity as HomeActivity, Observer {
            when(it.status) {
                Status.LOADING ->  pb_movies.visibility = View.VISIBLE
                Status.SUCCESS -> it.data?.let { data -> showMovies(data)}
                Status.ERROR -> pb_movies.visibility = View.GONE
            }
        })
        sr_movies.isRefreshing = false
    }

    private fun showMovies(data: List<Movie>) {
        movies.clear()
        movies.addAll(data)
        moviesAdapter.notifyDataSetChanged()
        sr_movies.isRefreshing = false
        pb_movies.visibility = View.INVISIBLE
    }
}
