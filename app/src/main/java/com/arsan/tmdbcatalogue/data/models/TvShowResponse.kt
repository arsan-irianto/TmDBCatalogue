package com.arsan.tmdbcatalogue.data.models

data class TvShowResponse(
    val page: Int,
    val results: List<TvShow>,
    val total_pages: Int,
    val total_results: Int
)