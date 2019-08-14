package com.arsan.tmdbcatalogue.data.repositories.remote

import com.arsan.tmdbcatalogue.data.networks.TmDBServices

class RemoteRepository(private val tmDBServices: TmDBServices) {
    fun getMovies() = tmDBServices.fetchNowPlaying()
    fun getTvShow() = tmDBServices.fetchTvTopRated()
}