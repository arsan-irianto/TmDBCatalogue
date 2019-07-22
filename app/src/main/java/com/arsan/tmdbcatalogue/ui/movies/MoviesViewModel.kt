package com.arsan.tmdbcatalogue.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arsan.tmdbcatalogue.data.models.MoviesResponse
import com.arsan.tmdbcatalogue.data.repositories.remote.RemoteRepository
import com.arsan.tmdbcatalogue.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel(private val remoteRepository: RemoteRepository) : ViewModel() {

    private var mutableLiveData = MutableLiveData<MoviesResponse>()
    val liveData: LiveData<MoviesResponse> = mutableLiveData

    init {
        moviesRoutine()
    }

    private fun moviesRoutine() {
        viewModelScope.launch {
            val movieList = withContext(IO) {
                EspressoIdlingResource.increment()
                remoteRepository.getMovies()
            }
            mutableLiveData.postValue(movieList.body())
            EspressoIdlingResource.decrement()
        }
    }
}