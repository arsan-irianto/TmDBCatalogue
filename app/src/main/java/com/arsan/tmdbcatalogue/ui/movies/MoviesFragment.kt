package com.arsan.tmdbcatalogue.ui.movies

import android.os.Bundle
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
import com.arsan.tmdbcatalogue.utils.EspressoIdlingResource
import kotlinx.android.synthetic.main.fragment_movies.*


class MoviesFragment : Fragment() {

    private var viewModel: MoviesViewModel = MoviesViewModel()
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
        viewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)
        viewModel.moviesData().observe(viewLifecycleOwner, Observer {
            showMovies(it.results)
        })

        if(activity!=null) {
            moviesAdapter = MoviesAdapter(requireContext(), movies) {
                Toast.makeText(requireContext(), "Hello World", Toast.LENGTH_SHORT).show()
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


}
