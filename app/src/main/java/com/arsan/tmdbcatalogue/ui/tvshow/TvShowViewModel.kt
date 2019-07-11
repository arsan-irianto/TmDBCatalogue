package com.arsan.tmdbcatalogue.ui.tvshow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arsan.tmdbcatalogue.data.models.TvShowResponse
import com.arsan.tmdbcatalogue.data.networks.RetrofitService
import com.arsan.tmdbcatalogue.data.networks.TmDBServices
import com.arsan.tmdbcatalogue.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TvShowViewModel : ViewModel() {
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
            mutableLiveData.value = tvList.body()
            EspressoIdlingResource.decrement()
        }
    }

    fun tvData(): MutableLiveData<TvShowResponse> {
        return mutableLiveData
    }

}