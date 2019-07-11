package com.arsan.tmdbcatalogue.data.networks

import com.arsan.tmdbcatalogue.BuildConfig
import com.arsan.tmdbcatalogue.data.models.MoviesResponse
import com.arsan.tmdbcatalogue.data.models.TvShowResponse
import retrofit2.Response
import retrofit2.http.GET

interface TmDBServices {
    @GET("movie/now_playing?" + BuildConfig.API_KEY)
    suspend fun fetchNowPlaying(): Response<MoviesResponse>

    @GET("tv/top_rated?" + BuildConfig.API_KEY)
    suspend fun fetchTvTopRated(): Response<TvShowResponse>
}