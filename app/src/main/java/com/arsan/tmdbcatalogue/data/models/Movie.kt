package com.arsan.tmdbcatalogue.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class Movie(
    val adult: Boolean,
    @ColumnInfo val backdrop_path: String?,
    @PrimaryKey
    @ColumnInfo val id: Int,
    @ColumnInfo val original_language: String,
    @ColumnInfo val original_title: String,
    @ColumnInfo val overview: String,
    val popularity: Double,
    @ColumnInfo val poster_path: String?,
    @ColumnInfo val release_date: String,
    @ColumnInfo val title: String,
    val video: Boolean,
    @ColumnInfo val vote_average: Double,
    val vote_count: Int
)