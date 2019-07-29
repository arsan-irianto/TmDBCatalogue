package com.arsan.tmdbcatalogue.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arsan.tmdbcatalogue.data.models.Movie
import com.arsan.tmdbcatalogue.data.models.TvShow

@Database(
    entities = [Movie::class, TvShow::class], version = 3, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao
/*    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, AppDatabase::class.java, "themovie"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }*/
}