package com.arsan.tmdbcatalogue.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_movie")
data class FavMovie (
    @PrimaryKey
    @ColumnInfo val id: Int,
    @ColumnInfo val original_language: String?,
    @ColumnInfo val original_title: String,
    @ColumnInfo val overview: String,
    @ColumnInfo val backdrop_path: String?,
    @ColumnInfo val poster_path: String?,
    @ColumnInfo val release_date: String?,
    @ColumnInfo val title: String,
    @ColumnInfo val vote_average: Double
)