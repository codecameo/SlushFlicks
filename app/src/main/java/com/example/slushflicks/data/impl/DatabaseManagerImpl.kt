package com.example.slushflicks.data.impl

import androidx.lifecycle.LiveData
import com.example.slushflicks.data.DatabaseManager
import com.example.slushflicks.db.AppDatabase
import com.example.slushflicks.di.app.AppScope
import com.example.slushflicks.model.GenreModel
import javax.inject.Inject

@AppScope
class DatabaseManagerImpl
@Inject constructor(
    private val database: AppDatabase
) : DatabaseManager {

    override suspend fun saveGenre(genres: List<GenreModel>) {
        database.getGenreDao().saveGenres(genres)
    }

    override suspend fun loadGenres(): List<GenreModel> {
        return database.getGenreDao().getAllGenres()
    }
}