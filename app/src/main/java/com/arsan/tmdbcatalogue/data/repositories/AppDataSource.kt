package com.arsan.tmdbcatalogue.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.arsan.tmdbcatalogue.data.models.FavMovie
import com.arsan.tmdbcatalogue.data.models.FavTvshow
import com.arsan.tmdbcatalogue.data.models.Movie
import com.arsan.tmdbcatalogue.data.models.TvShow
import com.arsan.tmdbcatalogue.vo.Resource

interface AppDataSource {
    fun getAllMovie(): LiveData<Resource<List<Movie>>>
    fun getAllTvShow(): LiveData<Resource<List<TvShow>>>

    // Favorite Movie
    fun insertFavMovie(movie: FavMovie)
    fun deleteFavMovie(id: Int)
    fun favMovieById(id: Int): Int
    fun getFavMovies(): LiveData<Resource<PagedList<FavMovie>>>

    // Favorite TvShow
    fun insertFavTvshow(tvshow: FavTvshow)
    fun deleteFavTvshow(id: Int)
    fun favTvshowById(id: Int): Int
    fun getFavTvshows(): LiveData<Resource<PagedList<FavTvshow>>>
}