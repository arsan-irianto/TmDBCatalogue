package com.arsan.tmdbcatalogue.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.arsan.tmdbcatalogue.data.repositories.remote.ApiEmptyResponse
import com.arsan.tmdbcatalogue.data.repositories.remote.ApiErrorResponse
import com.arsan.tmdbcatalogue.data.repositories.remote.ApiResponse
import com.arsan.tmdbcatalogue.data.repositories.remote.ApiSuccessResponse
import com.arsan.tmdbcatalogue.utils.CoroutineContextProvider
import com.arsan.tmdbcatalogue.vo.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Suppress("LeakingThis")
abstract class NetworkBoundResource<ResultType, RequestType>(private val context: CoroutineContextProvider) {
    private val result = MediatorLiveData<Resource<ResultType>>()
    protected abstract fun loadFromDB(): LiveData<ResultType>
    protected abstract fun shouldFetch(data: ResultType?): Boolean
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>
    protected abstract fun saveCallResult(data: RequestType)

    init {
        result.value = Resource.loading(null)
        val dbSource = loadFromDB()
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) fetchFromNetwork(dbSource)
            else {
                result.addSource(dbSource) { newData -> result.postValue(Resource.success(newData)) }
            }
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()
        result.addSource(dbSource) { newData -> result.value = Resource.loading(newData) }
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response) {
                is ApiSuccessResponse -> {
                    CoroutineScope(context.IO).launch {
                        response.body?.let { saveCallResult(it) }
                    }
                    CoroutineScope(context.Main).launch {
                        result.addSource(loadFromDB()) { newData -> result.value = Resource.success(newData) }
                    }
                }
                is ApiEmptyResponse -> {
                    CoroutineScope(context.Main).launch {
                        result.addSource(loadFromDB()) { newData -> result.value = Resource.success(newData) }
                    }
                }
                is ApiErrorResponse -> {
                    onFetchFailed()
                    CoroutineScope(context.Main).launch {
                        result.addSource(dbSource) { newData ->
                            result.value = Resource.error(response.errorMessage, newData)
                        }
                    }
                }
            }
        }
    }

    private fun onFetchFailed() {
        Log.d("onFetchFailed", "FetchFailed")
    }

    fun asLiveData() = result as LiveData<Resource<ResultType>>
}