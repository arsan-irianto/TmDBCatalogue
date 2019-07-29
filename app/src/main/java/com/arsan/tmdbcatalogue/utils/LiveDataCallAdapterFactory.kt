package com.arsan.tmdbcatalogue.utils

import androidx.lifecycle.LiveData
import com.arsan.tmdbcatalogue.data.repositories.remote.ApiResponse
import retrofit2.CallAdapter
import retrofit2.CallAdapter.Factory
import retrofit2.Retrofit
import java.lang.IllegalArgumentException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class LiveDataCallAdapterFactory : Factory() {
    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        if (getRawType(returnType) != LiveData::class.java) return null

        val observableType = getParameterUpperBound(0, returnType as ParameterizedType)
        val rawObservableType = getRawType(observableType)
        if (rawObservableType != ApiResponse::class.java) throw IllegalArgumentException("Type Must be Resource")
        if (observableType !is ParameterizedType) throw IllegalArgumentException("resource must be parametrized")

        val bodyType = getParameterUpperBound(0, observableType)

        return LiveDataCallAdapter<Any>(bodyType)
    }

}