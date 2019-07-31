package com.arsan.tmdbcatalogue.data.repositories.local

import androidx.lifecycle.LiveData
import com.arsan.tmdbcatalogue.data.models.FavMovie
import com.arsan.tmdbcatalogue.data.models.FavTvshow
import com.arsan.tmdbcatalogue.data.models.Movie
import com.arsan.tmdbcatalogue.data.models.TvShow
import com.arsan.tmdbcatalogue.data.room.MovieDao
import com.arsan.tmdbcatalogue.data.room.TvShowDao

class LocalRepository(
    private val movieDao: MovieDao,
    private val tvShowDao: TvShowDao) {

    fun getMovies() = movieDao.getAll()

    fun insertMovies(movies: List<Movie>) = movieDao.insertMovies(movies)

    fun getTvShow(): LiveData<List<TvShow>> = tvShowDao.getAll()

    fun insertTvShows(tvShows: List<TvShow>) = tvShowDao.insertTvShows(tvShows)

    fun insertFavMovie(favMovie: FavMovie) = movieDao.insertFavMovie(favMovie)
    fun deleteFavMovie(id: Int) = movieDao.deleteFavMovie(id)
    fun favMovieById(id:Int) = movieDao.favMovieById(id)

    fun insertFavTvshow(favTvshow: FavTvshow) = tvShowDao.insertFavTvshow(favTvshow)
    fun deleteFavTvshow(id: Int) = tvShowDao.deleteFavTvshow(id)
    fun favTvshowById(id:Int) = tvShowDao.favTvshowyId(id)

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