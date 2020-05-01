package com.sifat.slushflicks.data.impl

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.data.DatabaseManager
import com.sifat.slushflicks.data.FireStoreManager
import com.sifat.slushflicks.data.LocalDataManager
import com.sifat.slushflicks.di.app.AppScope
import com.sifat.slushflicks.model.*
import com.sifat.slushflicks.ui.state.DataState
import javax.inject.Inject

@AppScope
class DataManagerImpl
@Inject constructor(
    private val localDataManager: LocalDataManager,
    private val databaseManager: DatabaseManager,
    private val fireStoreManager: FireStoreManager
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
        collection: String,
        collectionModels: List<MovieCollectionModel>
    ) {
        databaseManager.insertNewMovieCollection(collection, collectionModels)
    }

    override suspend fun softInsertMovie(movies: List<MovieModel>) {
        databaseManager.softInsertMovie(movies)
    }

    override suspend fun getMovies(collection: String): List<MovieModel>? {
        return databaseManager.getMovies(collection)
    }

    override fun getPagingMovies(collection: String): DataSource.Factory<Int, MovieModelMinimal> {
        return databaseManager.getPagingMovies(collection)
    }

    override suspend fun addMovieCollection(collectionModels: List<MovieCollectionModel>) {
        databaseManager.addMovieCollection(collectionModels)
    }

    override fun getMovieDetails(movieId: Long): LiveData<MovieModel> {
        return databaseManager.getMovieDetails(movieId)
    }

    override suspend fun insertMovieDetails(movie: MovieModel) {
        return databaseManager.insertMovieDetails(movie)
    }

    override suspend fun updateMovieDetails(model: MovieModel) {
        databaseManager.updateMovieDetails(model)
    }

    override fun getMovieCollections(): LiveData<DataState<List<CollectionModel>>> {
        return fireStoreManager.getMovieCollections()
    }
}