package com.arsan.tmdbcatalogue.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arsan.tmdbcatalogue.data.models.Movie
import com.arsan.tmdbcatalogue.data.models.MoviesResponse
import com.arsan.tmdbcatalogue.data.models.TvShow
import com.arsan.tmdbcatalogue.data.models.TvShowResponse
import retrofit2.Response

interface AppDataSource {
    suspend fun movieList(): MutableLiveData<MoviesResponse>
    suspend fun tvShowList(): MutableLiveData<TvShowResponse>
}