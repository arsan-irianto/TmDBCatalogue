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

    // Favorite Tvshow Processs
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavTvshow(favTvshow: FavTvshow)

    @Query("DELETE FROM fav_tvshow WHERE id = :tvshowId")
    fun deleteFavTvshow(tvshowId: Int)

    @Query("SELECT count(id) FROM fav_tvshow WHERE id = :tvshowId")
    fun favTvshowyId(tvshowId: Int): Int

    @Query("SELECT * FROM fav_tvshow")
    fun getAllFavTvshow(): LiveData<List<FavTvshow>>
}