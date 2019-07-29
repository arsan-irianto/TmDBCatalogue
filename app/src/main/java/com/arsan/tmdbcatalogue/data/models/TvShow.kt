package com.arsan.tmdbcatalogue.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvshow_table")
data class TvShow(

    @PrimaryKey(autoGenerate = true) val idx: Int? = 0,

    @ColumnInfo val backdrop_path: String,
    @ColumnInfo val first_air_date: String,
/*    val genre_ids: List<Int>? = null,*/
    @ColumnInfo(name = "id_tvshow") val id: Int,
    @ColumnInfo val name: String,
/*    val origin_country: List<String>? = null,*/
    @ColumnInfo val original_language: String,
    @ColumnInfo val original_name: String,
    @ColumnInfo val overview: String,
    @ColumnInfo val popularity: Double,
    @ColumnInfo val poster_path: String,
    @ColumnInfo val vote_average: Double,
    @ColumnInfo val vote_count: Int
)