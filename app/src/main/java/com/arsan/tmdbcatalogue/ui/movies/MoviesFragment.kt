package com.arsan.tmdbcatalogue.ui.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.arsan.tmdbcatalogue.R
import com.arsan.tmdbcatalogue.data.models.Movie
import kotlinx.android.synthetic.main.fragment_movies.*
import org.koin.androidx.viewmodel.ext.android.viewModel

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
        //viewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)

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
        pb_movies.visibility = View.VISIBLE
        viewModel.liveData.observe(viewLifecycleOwner, Observer {
            showMovies(it.results)
            pb_movies.visibility = View.INVISIBLE
        })
    }

    private fun showMovies(data: List<Movie>) {
        sr_movies.isRefreshing = false
        movies.clear()
        movies.addAll(data)
        moviesAdapter.notifyDataSetChanged()
    }
}
