package com.arsan.tmdbcatalogue.data.repositories

import androidx.lifecycle.LiveData
import com.arsan.tmdbcatalogue.data.models.Movie
import com.arsan.tmdbcatalogue.data.models.TvShow
import com.arsan.tmdbcatalogue.vo.Resource

interface AppDataSource {
    fun getAllMovie(): LiveData<Resource<List<Movie>>>
    fun getAllTvShow(): LiveData<Resource<List<TvShow>>>
}