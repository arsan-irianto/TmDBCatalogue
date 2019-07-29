package com.arsan.tmdbcatalogue.data.repositories.local

import androidx.lifecycle.LiveData
import com.arsan.tmdbcatalogue.data.models.Movie
import com.arsan.tmdbcatalogue.data.models.TvShow
import com.arsan.tmdbcatalogue.data.room.MovieDao
import com.arsan.tmdbcatalogue.data.room.TvShowDao

class LocalRepository(
    private val movieDao: MovieDao,
    private val tvShowDao: TvShowDao) {

    fun getMovies(): LiveData<List<Movie>> {
        return movieDao.getAll()
    }

    fun insertMovies(movies: List<Movie>) {
        movieDao.insertMovies(movies)
    }

    fun getTvShow(): LiveData<List<TvShow>> {
        return tvShowDao.getAll()
    }

     fun insertTvShows(tvShows: List<TvShow>) {
        return tvShowDao.insertTvShows(tvShows)
    }

    companion object {
        @Volatile
        private var INSTANCE: LocalRepository? = null

        fun getInstance(movieDao: MovieDao, tvShowDao: TvShowDao): LocalRepository {
            INSTANCE ?: synchronized(this) {
                INSTANCE = LocalRepository(movieDao, tvShowDao)
            }
            return INSTANCE!!
        }
    }
}