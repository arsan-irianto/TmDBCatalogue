package com.arsan.tmdbcatalogue.utils

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

open class CoroutineContextProvider {
    open val Main: CoroutineContext = Dispatchers.Main
    open val IO: CoroutineContext = Dispatchers.IO
    companion object {
        @Volatile
        private var instance: CoroutineContextProvider? = null

        fun getInstance(): CoroutineContextProvider {
            return instance ?: synchronized(this) {
                CoroutineContextProvider()
            }.also { instance = it }
        }
    }
}