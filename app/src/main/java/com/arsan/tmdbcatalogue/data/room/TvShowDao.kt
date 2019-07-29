package com.arsan.tmdbcatalogue.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.arsan.tmdbcatalogue.data.models.FavTvshow
import com.arsan.tmdbcatalogue.data.models.TvShow

@Dao
interface TvShowDao {
    @Query("SELECT * FROM tvshow_table")
    fun getAll(): LiveData<List<TvShow>>

    @Query("SELECT * FROM tvshow_table WHERE idx IN (:tvshowId)")
    fun getById(tvshowId: IntArray): LiveData<List<TvShow>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvshows: List<TvShow>)

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun updateTvShows(tvshow: TvShow)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavTvshow (favTvshow: FavTvshow)

    @Delete
    fun deleteFavTvshow (favTvshow: FavTvshow)
}