package com.arsan.tmdbcatalogue.ui.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.arsan.academies.utils.GlideApp
import com.arsan.tmdbcatalogue.BuildConfig
import com.arsan.tmdbcatalogue.R
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {

    private var viewModel: DetailMovieViewModel = DetailMovieViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        setSupportActionBar(app_bar)

        viewModel = ViewModelProviders.of(this).get(DetailMovieViewModel::class.java)

        with(viewModel) {

            movieId = intent.getIntExtra("id", 0)
            movieTitle = intent.getStringExtra("title")
            movieOverview = intent.getStringExtra("overview")
            moviePoster = intent.getStringExtra("poster_path")
            movieBackdrop = intent.getStringExtra("backdrop_path")
            movieRating = intent.getDoubleExtra("vote_average", 0.0)

            tv_detail_name.text = movieTitle
            tv_detail_overview.text = movieOverview
            tv_detail_rating.text = movieRating.toString()
        }

        supportActionBar?.title = viewModel.movieTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0F

        loadImages()

    }

    private fun loadImages() {
        GlideApp.with(this)
            .load(BuildConfig.IMAGE_URL + "w342" + viewModel.moviePoster)
            .into(img_poster_path)

        GlideApp.with(this)
            .load(BuildConfig.IMAGE_URL + "w342" + viewModel.movieBackdrop)
            .into(img_backdrop_path)
    }
}
