package com.arsan.tmdbcatalogue.ui.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.arsan.tmdbcatalogue.R
import com.arsan.tmdbcatalogue.data.models.Movie
import com.arsan.tmdbcatalogue.data.repositories.AppRepository
import com.arsan.tmdbcatalogue.data.repositories.remote.RemoteRepository
import com.arsan.tmdbcatalogue.ui.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : Fragment() {

    private lateinit var viewModel: MoviesViewModel
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

        val factory  = ViewModelFactory.getInstance()
        ViewModelProviders.of(this, factory).get(MoviesViewModel::class.java)

        if (activity != null) {
            viewModel.movieLiveData.observe(viewLifecycleOwner, Observer { showMovies(it.results) })

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

    }

    private fun showMovies(data: List<Movie>) {
        movies.clear()
        movies.addAll(data)
        moviesAdapter.notifyDataSetChanged()
    }


/*    private fun obtainViewModel(activity: FragmentActivity): MoviesViewModel {
        val factory  = ViewModelFactory.getInstance()
        return ViewModelProviders.of(this, factory).get(MoviesViewModel::class.java)
    }*/
}
