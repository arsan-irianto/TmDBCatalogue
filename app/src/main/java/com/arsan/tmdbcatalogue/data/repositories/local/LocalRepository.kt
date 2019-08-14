package com.arsan.tmdbcatalogue.data.repositories.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.arsan.tmdbcatalogue.data.models.FavMovie
import com.arsan.tmdbcatalogue.data.models.FavTvshow
import com.arsan.tmdbcatalogue.data.models.Movie
import com.arsan.tmdbcatalogue.data.models.TvShow
import com.arsan.tmdbcatalogue.data.room.MovieDao
import com.arsan.tmdbcatalogue.data.room.TvShowDao
import com.arsan.tmdbcatalogue.vo.Resource

class LocalRepository(
    private val movieDao: MovieDao,
    private val tvShowDao: TvShowDao) {

    // Movies Repo
    fun getMovies() = movieDao.getAll()
    fun insertMovies(movies: List<Movie>) = movieDao.insertMovies(movies)

    // TvShow Repo
    fun getTvShow(): LiveData<List<TvShow>> = tvShowDao.getAll()
    fun insertTvShows(tvShows: List<TvShow>) = tvShowDao.insertTvShows(tvShows)

    // Favorites Movie Repo
    fun insertFavMovie(favMovie: FavMovie) = movieDao.insertFavMovie(favMovie)
    fun deleteFavMovie(id: Int) = movieDao.deleteFavMovie(id)
    fun favMovieById(id:Int) = movieDao.favMovieById(id)
    fun getFavMovies(): DataSource.Factory<Int, FavMovie> = movieDao.getAllFavMovie() //{

    // Favorites Tvshow Repo
    fun insertFavTvshow(favTvshow: FavTvshow) = tvShowDao.insertFavTvshow(favTvshow)
    fun deleteFavTvshow(id: Int) = tvShowDao.deleteFavTvshow(id)
    fun favTvshowById(id:Int) = tvShowDao.favTvshowyId(id)
    fun getFavTvShows(): DataSource.Factory<Int, FavTvshow> = tvShowDao.getAllFavTvshow()

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