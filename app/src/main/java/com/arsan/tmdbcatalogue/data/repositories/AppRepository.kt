package com.arsan.tmdbcatalogue.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.arsan.tmdbcatalogue.data.models.*
import com.arsan.tmdbcatalogue.data.repositories.local.LocalRepository
import com.arsan.tmdbcatalogue.data.repositories.remote.RemoteRepository
import com.arsan.tmdbcatalogue.utils.CoroutineContextProvider
import com.arsan.tmdbcatalogue.vo.Resource

class AppRepository(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository,
    private val coroutineContextProvider: CoroutineContextProvider
) : AppDataSource {
    override fun insertFavTvshow(tvshow: FavTvshow) {
        localRepository.insertFavTvshow(tvshow)
    }

    override fun deleteFavTvshow(id: Int) {
        localRepository.deleteFavTvshow(id)
    }

    override fun favTvshowById(id: Int): Int {
        return localRepository.favTvshowById(id)
    }

    override fun favMovieById(id: Int): Int {
        return localRepository.favMovieById(id)
    }

    override fun deleteFavMovie(id: Int) {
        localRepository.deleteFavMovie(id)
    }

    override fun insertFavMovie(movie: FavMovie) {
        localRepository.insertFavMovie(movie)
    }

    override fun getAllTvShow(): LiveData<Resource<List<TvShow>>> {
        return object : NetworkBoundResource<List<TvShow>, TvShowResponse>(coroutineContextProvider) {
            override fun loadFromDB(): LiveData<List<TvShow>> {
                return localRepository.getTvShow()
            }

            override fun shouldFetch(data: List<TvShow>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall() = remoteRepository.getTvShow()

            override fun saveCallResult(data: TvShowResponse) {
                localRepository.insertTvShows(data.results)
            }

        }.asLiveData()
    }

    override fun getAllMovie(): LiveData<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, MoviesResponse>(coroutineContextProvider) {
            override fun loadFromDB(): LiveData<List<Movie>> {
                return localRepository.getMovies()
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall() = remoteRepository.getMovies()

            override fun saveCallResult(data: MoviesResponse) {
                localRepository.insertMovies(data.results)
            }


        }.asLiveData()
    }


}
