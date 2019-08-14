package com.arsan.tmdbcatalogue.data.networks

import androidx.lifecycle.LiveData
import com.arsan.tmdbcatalogue.BuildConfig
import com.arsan.tmdbcatalogue.data.models.MoviesResponse
import com.arsan.tmdbcatalogue.data.models.TvShowResponse
import com.arsan.tmdbcatalogue.data.repositories.remote.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TmDBServices {
    @GET("movie/now_playing?" + BuildConfig.API_KEY)
    fun fetchNowPlaying(): LiveData<ApiResponse<MoviesResponse>>

    @GET("movie/now_playing?" + BuildConfig.API_KEY)
    //fun fetchNowPlayingAll(@Query("page") page: Int): LiveData<ApiResponse<MoviesResponse>>
    fun fetchNowPlayingAll(@Query("page") page: Int): LiveData<ApiResponse<MoviesResponse>>

    @GET("tv/top_rated?" + BuildConfig.API_KEY)
    fun fetchTvTopRated(): LiveData<ApiResponse<TvShowResponse>>
}