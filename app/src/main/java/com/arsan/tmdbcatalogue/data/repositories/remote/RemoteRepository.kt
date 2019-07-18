package com.arsan.tmdbcatalogue.data.repositories.remote

import com.arsan.tmdbcatalogue.data.models.Movie
import com.arsan.tmdbcatalogue.data.models.MoviesResponse
import com.arsan.tmdbcatalogue.data.networks.RetrofitService
import com.arsan.tmdbcatalogue.data.networks.TmDBServices
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import retrofit2.Response

class RemoteRepository {
    private val tmDBServices: TmDBServices = RetrofitService.createService(TmDBServices::class.java)

    suspend fun getMovies() = tmDBServices.fetchNowPlaying()

    suspend fun getTvShow() = tmDBServices.fetchTvTopRated()

    companion object {
        @Volatile private var instance: RemoteRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: RemoteRepository().also { instance = it }
        }
    }
}