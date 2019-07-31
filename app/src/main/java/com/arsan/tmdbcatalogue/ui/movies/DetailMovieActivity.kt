package com.arsan.tmdbcatalogue.ui.movies

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.arsan.tmdbcatalogue.BuildConfig
import com.arsan.tmdbcatalogue.R
import com.arsan.tmdbcatalogue.R.drawable.ic_favorite_border_white
import com.arsan.tmdbcatalogue.R.drawable.ic_favorite_white
import com.arsan.tmdbcatalogue.R.menu.menu_detail_movie
import com.arsan.tmdbcatalogue.data.models.FavMovie
import com.arsan.tmdbcatalogue.data.repositories.AppRepository
import com.arsan.tmdbcatalogue.utils.GlideApp
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detail_movie.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var contextView: View
    private val viewModel: DetailMovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        setSupportActionBar(app_bar)
        contextView = findViewById(R.id.movie_detail)

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

        if (viewModel.rowCount() > 0) {
            isFavorite = true
        }
    }

    private fun loadImages() {
        GlideApp.with(this)
            .load(BuildConfig.IMAGE_URL + "w342" + viewModel.moviePoster)
            .into(img_poster_path)

        GlideApp.with(this)
            .load(BuildConfig.IMAGE_URL + "w342" + viewModel.movieBackdrop)
            .into(img_backdrop_path)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.fav_detail_movie -> {
                showSnackbar(viewModel.toggleFavMovie(isFavorite))
                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(menu_detail_movie, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    private fun setFavorite() {
        if (isFavorite) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_favorite_white)
        } else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_favorite_border_white)
        }
    }

    private fun showSnackbar(strMessage: String) {
        Snackbar.make(contextView, strMessage, Snackbar.LENGTH_SHORT).show()
    }
}
