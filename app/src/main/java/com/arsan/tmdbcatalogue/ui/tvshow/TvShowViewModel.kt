package com.arsan.tmdbcatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arsan.tmdbcatalogue.data.models.TvShowResponse
import com.arsan.tmdbcatalogue.data.networks.RetrofitService
import com.arsan.tmdbcatalogue.data.networks.TmDBServices
import com.arsan.tmdbcatalogue.data.repositories.AppRepository
import com.arsan.tmdbcatalogue.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TvShowViewModel(private val appRepository: AppRepository) : ViewModel() {
    private var mutableLiveData = MutableLiveData<TvShowResponse>()
    private var retrofitService = RetrofitService

    init {
        tvRoutine()
    }

    private fun tvRoutine() {

        val tmDBServices: TmDBServices = retrofitService.createService(TmDBServices::class.java)
        viewModelScope.launch {
            val tvList = withContext(Dispatchers.IO) {
                EspressoIdlingResource.increment()
                tmDBServices.fetchTvTopRated()
            }
            mutableLiveData.postValue(tvList.body())
            EspressoIdlingResource.decrement()
        }
    }

    fun tvData(): LiveData<TvShowResponse> {
        return mutableLiveData
    }

}