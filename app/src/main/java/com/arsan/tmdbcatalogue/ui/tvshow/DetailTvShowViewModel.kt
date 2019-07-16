package com.arsan.tmdbcatalogue.ui.tvshow

import androidx.lifecycle.ViewModel

class DetailTvShowViewModel: ViewModel() {
    var movieId: Int? = null
    var movieTitle: String? = null
    var movieOverview: String? = null
    var moviePoster: String? = null
    var movieBackdrop: String? = null
    var movieRating: Double? = null
}