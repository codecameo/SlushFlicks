package com.sifat.slushflicks.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sifat.slushflicks.db.dao.CollectionDao
import com.sifat.slushflicks.db.dao.GenreDao
import com.sifat.slushflicks.db.dao.MovieDao
import com.sifat.slushflicks.model.GenreModel
import com.sifat.slushflicks.model.MovieCollectionModel
import com.sifat.slushflicks.model.MovieModel

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