package com.arsan.tmdbcatalogue.data.models

data class MoviesResponse(
    val page: Int,
    val results: List<Movie>,
    val totalPages: Int,
    val totalResults: Int
)