package com.example.slushflicks.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.slushflicks.db.dao.GenreDao
import com.example.slushflicks.model.GenreModel

@Database(entities = [GenreModel::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getGenreDao(): GenreDao

}