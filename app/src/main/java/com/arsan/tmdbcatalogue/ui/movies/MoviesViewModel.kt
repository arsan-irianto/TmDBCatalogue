package com.arsan.tmdbcatalogue.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arsan.tmdbcatalogue.data.models.MoviesResponse
import com.arsan.tmdbcatalogue.data.networks.RetrofitService
import com.arsan.tmdbcatalogue.data.networks.TmDBServices
import com.arsan.tmdbcatalogue.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel : ViewModel() {
    private var mutableLiveData = MutableLiveData<MoviesResponse>()
    private var retrofitService = RetrofitService

    init {
        moviesRoutine()
    }

    private fun moviesRoutine() {
        val tmDBServices: TmDBServices = retrofitService.createService(TmDBServices::class.java)
        viewModelScope.launch {
            val movieList = withContext(Dispatchers.IO) {
                EspressoIdlingResource.increment()
                tmDBServices.fetchNowPlaying()
            }
            mutableLiveData.value = movieList.body()
            EspressoIdlingResource.decrement()
        }
    }

    fun moviesData(): MutableLiveData<MoviesResponse> {
        return mutableLiveData
    }
}


/*try {
    val movieList = withContext(Dispatchers.IO) {
        tmDBServices.fetchNowPlaying()
    }
    mutableLiveData.value = movieList.body()
} catch (e: Exception) {
    mutableLiveData.value = null
    Log.d("ErrorViewModel", e.message.toString())
}*/

// Old Code
/*        runBlocking {
            val response: Response<MoviesResponse> = tmDBServices.getNowPlaying()
            if (response.code() == 200){
                data.value = response.body()
            }
        }*/
/*        val callAsync: Call<MoviesResponse> = tmDBServices.getNowPlaying()
        callAsync.enqueue(object : Callback<MoviesResponse> {
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                data.value = response.body()
            }

        })*/