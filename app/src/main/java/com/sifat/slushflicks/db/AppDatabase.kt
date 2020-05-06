package com.sifat.slushflicks.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sifat.slushflicks.db.dao.*
import com.sifat.slushflicks.model.*

@Database(
    entities = [GenreModel::class, MovieModel::class, MovieCollectionModel::class, TvModel::class, TvCollectionModel::class],
    version = 5
)
@TypeConverters(RoomConverter::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getGenreDao(): GenreDao

    abstract fun getMovieCollectionDao(): MovieCollectionDao

    abstract fun getTvCollectionDao(): TvCollectionDao

    abstract fun getMovieDao(): MovieDao

    abstract fun getTvDao(): TvDao
}