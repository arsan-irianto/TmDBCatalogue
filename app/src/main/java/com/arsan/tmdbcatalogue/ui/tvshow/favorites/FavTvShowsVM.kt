package com.arsan.tmdbcatalogue.ui.tvshow.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.arsan.tmdbcatalogue.data.models.FavTvshow
import com.arsan.tmdbcatalogue.data.repositories.local.LocalRepository
import com.arsan.tmdbcatalogue.vo.Resource

class FavTvShowsVM(private val localRepository: LocalRepository) : ViewModel() {
    private var mutableLiveData = MutableLiveData<String>()
    val liveData: LiveData<Resource<List<FavTvshow>>> = Transformations.switchMap(mutableLiveData) {
        localRepository.getFavTvShows()
    }

    fun setData(mvdata: String) {
        mutableLiveData.value = mvdata
    }
}