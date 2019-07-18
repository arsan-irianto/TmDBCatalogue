package com.arsan.tmdbcatalogue.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arsan.tmdbcatalogue.data.models.Movie
import com.arsan.tmdbcatalogue.data.models.MoviesResponse
import com.arsan.tmdbcatalogue.data.networks.RetrofitService
import com.arsan.tmdbcatalogue.data.networks.TmDBServices
import com.arsan.tmdbcatalogue.data.repositories.AppRepository
import com.arsan.tmdbcatalogue.data.repositories.remote.RemoteRepository
import com.arsan.tmdbcatalogue.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel(val appRepository: AppRepository): ViewModel() {

    private lateinit var mMovieLiveData: MutableLiveData<MoviesResponse>
    val movieLiveData: LiveData<MoviesResponse> = mMovieLiveData

    private fun moviesRoutine() {
        viewModelScope.launch {
            val movieContext = withContext(IO) {
                return@withContext appRepository.movieList().value
            }
            mMovieLiveData.postValue(movieContext)
        }
    }

    init {
        moviesRoutine()
    }



/*    private var mutableLiveData = MutableLiveData<MoviesResponse>()

    init {
        moviesRoutine()
    }

    private fun moviesRoutine() {
*//*        val tmDBServices: TmDBServices = retrofitService.createService(TmDBServices::class.java)
        viewModelScope.launch {
            val movieList = withContext(Dispatchers.IO) {
                EspressoIdlingResource.increment()
                tmDBServices.fetchNowPlaying()
            }
            mutableLiveData.postValue(movieList.body())
            EspressoIdlingResource.decrement()
        }*//*
        val remoteRepository = RemoteRepository()
        viewModelScope.launch {
            val movieList = withContext(IO) {
                remoteRepository.getMovies()
            }
            mutableLiveData.postValue(movieList.body())
        }
    }


    fun moviesData(): LiveData<MoviesResponse> {
        return mutableLiveData
    }*/
    //val moviesData: LiveData<List<Movie>> = appRepository.movieList()

}