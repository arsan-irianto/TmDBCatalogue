package com.arsan.tmdbcatalogue.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arsan.tmdbcatalogue.data.models.FavMovie
import com.arsan.tmdbcatalogue.data.models.FavTvshow
import com.arsan.tmdbcatalogue.data.models.Movie
import com.arsan.tmdbcatalogue.data.models.TvShow

@Database(
    entities = [
        Movie::class,
        TvShow::class,
        FavMovie::class,
        FavTvshow::class],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao
}