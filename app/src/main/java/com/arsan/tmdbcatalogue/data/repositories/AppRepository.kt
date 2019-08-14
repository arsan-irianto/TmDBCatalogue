package com.arsan.tmdbcatalogue.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.arsan.tmdbcatalogue.data.models.*
import com.arsan.tmdbcatalogue.data.repositories.local.LocalRepository
import com.arsan.tmdbcatalogue.data.repositories.remote.ApiResponse
import com.arsan.tmdbcatalogue.data.repositories.remote.RemoteRepository
import com.arsan.tmdbcatalogue.utils.CoroutineContextProvider
import com.arsan.tmdbcatalogue.vo.Resource

open class AppRepository(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository,
    private val coroutineContextProvider: CoroutineContextProvider
) : AppDataSource {

    // Movies Source
    override fun getAllMovie(): LiveData<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, MoviesResponse>(coroutineContextProvider) {
            override fun loadFromDB() = localRepository.getMovies()
            override fun shouldFetch(data: List<Movie>?) = data == null || data.isEmpty()
            override fun createCall() = remoteRepository.getMovies()
            override fun saveCallResult(data: MoviesResponse) = localRepository.insertMovies(data.results)

        }.asLiveData()
    }

    // TvShow Source
    override fun getAllTvShow(): LiveData<Resource<List<TvShow>>> {
        return object : NetworkBoundResource<List<TvShow>, TvShowResponse>(coroutineContextProvider) {
            override fun loadFromDB() = localRepository.getTvShow()
            override fun shouldFetch(data: List<TvShow>?) = data == null || data.isEmpty()
            override fun createCall() = remoteRepository.getTvShow()
            override fun saveCallResult(data: TvShowResponse) = localRepository.insertTvShows(data.results)
        }.asLiveData()
    }

    // Favorites Movie Source
    override fun getFavMovies(): LiveData<Resource<PagedList<FavMovie>>> {
        return object : NetworkBoundResource<PagedList<FavMovie>, MoviesResponse>(coroutineContextProvider) {
            override fun loadFromDB() = LivePagedListBuilder(localRepository.getFavMovies(), 10).build()
            override fun shouldFetch(data: PagedList<FavMovie>?) = false
            override fun createCall() = null!!
            override fun saveCallResult(data: MoviesResponse) {}

        }.asLiveData()
    }
    override fun favMovieById(id: Int) = localRepository.favMovieById(id)
    override fun deleteFavMovie(id: Int) { localRepository.deleteFavMovie(id) }
    override fun insertFavMovie(movie: FavMovie) { localRepository.insertFavMovie(movie) }

    // Favorites TvShows Source
    override fun getFavTvshows(): LiveData<Resource<PagedList<FavTvshow>>> {
        return object : NetworkBoundResource<PagedList<FavTvshow>, TvShowResponse>(coroutineContextProvider) {
            override fun loadFromDB() = LivePagedListBuilder(localRepository.getFavTvShows(), 10).build()
            override fun shouldFetch(data: PagedList<FavTvshow>?) = false
            override fun createCall() = null!!
            override fun saveCallResult(data: TvShowResponse) {}
        }.asLiveData()
    }
    override fun insertFavTvshow(tvshow: FavTvshow) { localRepository.insertFavTvshow(tvshow) }
    override fun deleteFavTvshow(id: Int) { localRepository.deleteFavTvshow(id) }
    override fun favTvshowById(id: Int) = localRepository.favTvshowById(id)

    companion object {
        @Volatile
        private var instance: AppRepository? = null
        fun getInstance(localRepository: LocalRepository,
                        remoteRepository: RemoteRepository,
                        coroutineContextProvider: CoroutineContextProvider) : AppRepository =
            instance ?: synchronized(AppRepository::class.java) {
                AppRepository(localRepository, remoteRepository, coroutineContextProvider)
                    .also { instance = it }
            }
    }
}
