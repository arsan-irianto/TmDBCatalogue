package com.arsan.tmdbcatalogue.data.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.arsan.tmdbcatalogue.data.models.FavMovie
import com.arsan.tmdbcatalogue.data.models.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_table")
    fun getAll(): LiveData<List<Movie>>

    @Query("SELECT * FROM movie_table")
    fun getAllPaging(): DataSource.Factory<Int, Movie>

    @Query("SELECT * FROM movie_table WHERE id IN (:movieId)")
    fun getById(movieId: IntArray): LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<Movie>)

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun updateMovies(movie: Movie)


    // Favorite Movie Processs
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavMovie(favMovie: FavMovie)

    @Query("DELETE FROM fav_movie WHERE id = :movieId")
    fun deleteFavMovie(movieId: Int)

    @Query("SELECT count(id) FROM fav_movie WHERE id = :movieId")
    fun favMovieById(movieId: Int): Int

    @Query("SELECT * FROM fav_movie")
    fun getAllFavMovie(): DataSource.Factory<Int, FavMovie>
}