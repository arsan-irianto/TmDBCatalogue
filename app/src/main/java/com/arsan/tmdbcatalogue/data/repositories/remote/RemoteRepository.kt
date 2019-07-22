package com.arsan.tmdbcatalogue.data.repositories.remote

import com.arsan.tmdbcatalogue.data.networks.TmDBServices

class RemoteRepository(private val tmDBServices: TmDBServices) {

    suspend fun getMovies() = tmDBServices.fetchNowPlaying()

    suspend fun getTvShow() = tmDBServices.fetchTvTopRated()
}