package com.example.slushflicks.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.slushflicks.db.dao.CollectionDao
import com.example.slushflicks.db.dao.GenreDao
import com.example.slushflicks.db.dao.MovieDao
import com.example.slushflicks.model.GenreModel
import com.example.slushflicks.model.MovieCollectionModel
import com.example.slushflicks.model.MovieModel

@Database(
    entities = [GenreModel::class, MovieModel::class, MovieCollectionModel::class],
    version = 2
)
@TypeConverters(RoomConverter::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getGenreDao(): GenreDao

    abstract fun getCollectionDao(): CollectionDao

    abstract fun getMovieDao(): MovieDao

}