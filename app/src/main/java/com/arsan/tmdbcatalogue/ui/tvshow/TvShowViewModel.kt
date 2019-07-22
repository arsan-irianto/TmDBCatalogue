package com.arsan.tmdbcatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arsan.tmdbcatalogue.data.models.TvShowResponse
import com.arsan.tmdbcatalogue.data.repositories.remote.RemoteRepository
import com.arsan.tmdbcatalogue.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TvShowViewModel(private val remoteRepository: RemoteRepository) : ViewModel() {
    private var mutableLiveData = MutableLiveData<TvShowResponse>()
    val liveData: LiveData<TvShowResponse> = mutableLiveData

    init {
        tvRoutine()
    }

    private fun tvRoutine() {
        viewModelScope.launch {
            val tvShowList = withContext(IO) {
                EspressoIdlingResource.increment()
                remoteRepository.getTvShow()
            }
            mutableLiveData.postValue(tvShowList.body())
            EspressoIdlingResource.decrement()
        }
    }
}