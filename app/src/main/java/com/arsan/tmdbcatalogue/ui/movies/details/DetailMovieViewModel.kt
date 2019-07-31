package com.arsan.tmdbcatalogue.ui.movies.details

import androidx.lifecycle.ViewModel
import com.arsan.tmdbcatalogue.data.models.FavMovie
import com.arsan.tmdbcatalogue.data.repositories.AppRepository

class DetailMovieViewModel(private val appRepository: AppRepository) : ViewModel() {
    var movieId: Int = 0
    var movieTitle: String = ""
    var movieOverview: String = ""
    var moviePoster: String = ""
    var movieBackdrop: String = ""
    var movieRating: Double = 0.0

    fun rowCount(): Int {
        return appRepository.favMovieById(movieId)
    }

    fun toggleFavMovie(state: Boolean): String {
        val movie = FavMovie(
            movieId,
            "",
            movieTitle,
            movieOverview,
            movieBackdrop,
            moviePoster,
            "",
            movieTitle,
            movieRating
        )

        return if (state) {
            appRepository.deleteFavMovie(movieId)
            "removed from favorite"
        } else {
            appRepository.insertFavMovie(movie)
            "added to favorite"
        }

    }
}