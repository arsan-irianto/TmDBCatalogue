package com.arsan.tmdbcatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.arsan.tmdbcatalogue.data.models.TvShow
import com.arsan.tmdbcatalogue.data.repositories.AppRepository
import com.arsan.tmdbcatalogue.vo.Resource

class TvShowViewModel(private val appRepository: AppRepository) : ViewModel() {
    private var mutableLiveData = MutableLiveData<String>()
    val liveData: LiveData<Resource<List<TvShow>>> = Transformations.switchMap(mutableLiveData) {
        appRepository.getAllTvShow()
    }

    fun setData(mvdata: String) {
        mutableLiveData.value = mvdata
    }
}