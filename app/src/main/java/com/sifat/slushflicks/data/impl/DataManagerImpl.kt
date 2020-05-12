package com.sifat.slushflicks.data.impl

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.sifat.slushflicks.api.details.model.VideoApiModel
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

    override fun addGenre(genres: List<GenreModel>) {
        localDataManager.addGenre(genres)
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
        // Since it's done from api responses.
        // Through initGenres, 2nd response wont be able to set it's genres
        localDataManager.addGenre(genres)
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

    override suspend fun insertNewMovieCollection(movie: MovieCollectionModel) {
        databaseManager.insertNewMovieCollection(movie)
    }

    override suspend fun insertNewTvCollection(
        collection: String,
        collectionModels: List<TvCollectionModel>
    ) {
        databaseManager.insertNewTvCollection(collection, collectionModels)
    }

    override suspend fun insertNewTvCollection(tvShow: TvCollectionModel) {
        databaseManager.insertNewTvCollection(tvShow)
    }

    override suspend fun softInsertMovie(movies: List<MovieModel>) {
        databaseManager.softInsertMovie(movies)
    }

    override suspend fun softInsertTv(tvShows: List<TvModel>) {
        databaseManager.softInsertTv(tvShows)
    }

    override fun getPagingMovies(collection: String): DataSource.Factory<Int, ShowModelMinimal> {
        return databaseManager.getPagingMovies(collection)
    }

    override fun getPagingTvShows(collection: String): DataSource.Factory<Int, ShowModelMinimal> {
        return databaseManager.getPagingTvShows(collection)
    }

    override suspend fun addMovieCollection(collectionModels: List<MovieCollectionModel>) {
        databaseManager.addMovieCollection(collectionModels)
    }

    override suspend fun addTvCollection(collectionModels: List<TvCollectionModel>) {
        databaseManager.addTvCollection(collectionModels)
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

    override suspend fun updateMovieDetails(model: VideoApiModel, movieId: Long) {
        databaseManager.updateMovieDetails(model, movieId)
    }

    override suspend fun updateMovieDetails(casts: List<CastModel>, movieId: Long) {
        databaseManager.updateMovieDetails(casts, movieId)
    }

    override fun getMovieCollections(): LiveData<DataState<List<CollectionModel>>> {
        return fireStoreManager.getMovieCollections()
    }

    override fun getTvCollections(): LiveData<DataState<List<CollectionModel>>> {
        return fireStoreManager.getTvCollections()
    }

    override fun getTvShowDetails(tvShowId: Long): LiveData<TvModel> {
        return databaseManager.getTvShowDetails(tvShowId)
    }

    override suspend fun updateTvDetails(model: TvModel) {
        databaseManager.updateTvDetails(model)
    }

    override suspend fun updateTvDetails(casts: List<CastModel>, tvShowId: Long) {
        databaseManager.updateTvDetails(casts, tvShowId)
    }

    override suspend fun updateTvDetails(videos: VideoApiModel, tvShowId: Long) {
        databaseManager.updateTvDetails(videos, tvShowId)
    }
}