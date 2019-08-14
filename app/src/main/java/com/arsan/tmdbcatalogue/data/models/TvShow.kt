package com.arsan.tmdbcatalogue.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvshow_table")
data class TvShow(
    @ColumnInfo val backdrop_path: String,
    @ColumnInfo val first_air_date: String,
    @PrimaryKey
    @ColumnInfo val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val original_language: String,
    @ColumnInfo val original_name: String,
    @ColumnInfo val overview: String,
    @ColumnInfo val popularity: Double,
    @ColumnInfo val poster_path: String,
    @ColumnInfo val vote_average: Double,
    @ColumnInfo val vote_count: Int
)