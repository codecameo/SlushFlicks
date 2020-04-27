package com.example.slushflicks.data.impl

import com.example.slushflicks.data.DataManager
import com.example.slushflicks.data.DatabaseManager
import com.example.slushflicks.data.LocalDataManager
import com.example.slushflicks.di.app.AppScope
import com.example.slushflicks.model.GenreModel
import javax.inject.Inject

@AppScope
class DataManagerImpl
@Inject constructor(
    private val localDataManager: LocalDataManager,
    private val databaseManager: DatabaseManager
) : DataManager {
    override fun addGenre(id: Long, name: String) {
        localDataManager.addGenre(id, name)
    }

    override fun getGenre(id: Long): String? {
        return localDataManager.getGenre(id)
    }

    override fun initGenres(genres: List<GenreModel>?) {
        localDataManager.initGenres(genres)
    }

    override fun getGenres(): Map<Long, String> {
        return localDataManager.getGenres()
    }

    override suspend fun saveGenre(genres: List<GenreModel>) {
        databaseManager.saveGenre(genres)
    }

    override suspend fun loadGenres(): List<GenreModel> {
        return databaseManager.loadGenres()
    }
}