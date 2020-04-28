package com.example.slushflicks.data.impl

import com.example.slushflicks.data.DataManager
import com.example.slushflicks.data.DatabaseManager
import com.example.slushflicks.data.LocalDataManager
import com.example.slushflicks.di.app.AppScope
import com.example.slushflicks.model.GenreModel
import com.example.slushflicks.model.MovieCollectionModel
import com.example.slushflicks.model.MovieModel
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

    override suspend fun insertNewMovieCollection(
        label: String,
        collectionModels: List<MovieCollectionModel>
    ) {
        databaseManager.insertNewMovieCollection(label, collectionModels)
    }

    override suspend fun softInsertMovie(movies: List<MovieModel>) {
        databaseManager.softInsertMovie(movies)
    }

    override suspend fun getMovies(collection: String): List<MovieModel>? {
        return databaseManager.getMovies(collection)
    }

    override suspend fun addMovieCollection(collectionModels: List<MovieCollectionModel>) {
        databaseManager.addMovieCollection(collectionModels)
    }
}