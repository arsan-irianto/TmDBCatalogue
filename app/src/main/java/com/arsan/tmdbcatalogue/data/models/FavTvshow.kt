package com.arsan.tmdbcatalogue.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_tvshow")
data class FavTvshow(
    @PrimaryKey
    @ColumnInfo val id: Int,
    @ColumnInfo val backdrop_path: String,
    @ColumnInfo val first_air_date: String,
    @ColumnInfo val name: String,
    @ColumnInfo val original_language: String,
    @ColumnInfo val original_name: String,
    @ColumnInfo val overview: String,
    @ColumnInfo val poster_path: String,
    @ColumnInfo val vote_average: Double
)