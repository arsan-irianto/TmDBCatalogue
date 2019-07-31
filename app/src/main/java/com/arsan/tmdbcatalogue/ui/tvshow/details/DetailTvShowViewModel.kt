package com.arsan.tmdbcatalogue.ui.tvshow.details

import androidx.lifecycle.ViewModel
import com.arsan.tmdbcatalogue.data.models.FavTvshow
import com.arsan.tmdbcatalogue.data.repositories.AppRepository

class DetailTvShowViewModel(private val appRepository: AppRepository) : ViewModel() {
    var movieId: Int = 0
    var movieTitle: String = ""
    var movieOverview: String = ""
    var moviePoster: String = ""
    var movieBackdrop: String = ""
    var movieRating: Double = 0.0

    fun rowCount(): Int {
        return appRepository.favTvshowById(movieId)
    }

    fun toggleFavTvshow(state: Boolean): String {
        val tvshow = FavTvshow(
            movieId,
            movieBackdrop,
            "",
            movieTitle,
            "",
            movieTitle,
            movieOverview,
            moviePoster,
            movieRating
        )

        return if (state) {
            appRepository.deleteFavTvshow(movieId)
            "removed from favorite"
        } else {
            appRepository.insertFavTvshow(tvshow)
            "added to favorite"
        }

    }
}