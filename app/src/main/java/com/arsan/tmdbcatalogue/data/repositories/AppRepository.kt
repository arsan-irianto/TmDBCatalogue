package com.arsan.tmdbcatalogue.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arsan.tmdbcatalogue.data.models.Movie
import com.arsan.tmdbcatalogue.data.models.MoviesResponse
import com.arsan.tmdbcatalogue.data.models.TvShow
import com.arsan.tmdbcatalogue.data.models.TvShowResponse
import com.arsan.tmdbcatalogue.data.repositories.local.LocalRepository
import com.arsan.tmdbcatalogue.data.repositories.remote.RemoteRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import java.util.concurrent.ConcurrentMap

class AppRepository(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository): AppDataSource {
    private lateinit var movies: MutableLiveData<MoviesResponse>
    private lateinit var tvshow: MutableLiveData<TvShowResponse>

    override suspend fun movieList() : MutableLiveData<MoviesResponse> {
        withContext(IO)  {
            movies.postValue(remoteRepository.getMovies().body())
        }
        return movies
    }

    override suspend fun tvShowList(): MutableLiveData<TvShowResponse> {
        withContext(IO) {
            tvshow.postValue(remoteRepository.getTvShow().body())
        }
        return tvshow
    }

    companion object {
        @Volatile
        private var instance: AppRepository? = null

        fun getInstance(localRepo: LocalRepository, remoteRepo: RemoteRepository): AppRepository? {
            instance ?: synchronized(this) {
                instance ?: AppRepository(localRepo, remoteRepo)
            }
            return instance
        }
    }
}
