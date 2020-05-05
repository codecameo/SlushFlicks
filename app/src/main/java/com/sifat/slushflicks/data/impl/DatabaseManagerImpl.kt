package com.sifat.slushflicks.data.impl

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.sifat.slushflicks.api.details.model.VideoApiModel
import com.sifat.slushflicks.data.DatabaseManager
import com.sifat.slushflicks.db.AppDatabase
import com.sifat.slushflicks.di.app.AppScope
import com.sifat.slushflicks.model.*
import javax.inject.Inject

@AppScope
class DatabaseManagerImpl
@Inject constructor(
    private val database: AppDatabase
) : DatabaseManager {

    override suspend fun saveGenre(genres: List<GenreModel>) {
        database.getGenreDao().insertReplace(genres)
    }

    override suspend fun loadGenres(): List<GenreModel> {
        return database.getGenreDao().getAllGenres()
    }

    override suspend fun insertNewMovieCollection(
        collection: String,
        collectionModels: List<MovieCollectionModel>
    ) {
        database.getMovieCollectionDao().saveMovieCollectionList(collection, collectionModels)
    }

    override suspend fun insertNewTvCollection(
        collection: String,
        collectionModels: List<TvCollectionModel>
    ) {
        database.getTvCollectionDao().saveTvCollectionList(collection, collectionModels)
    }

    override suspend fun softInsertMovie(movies: List<MovieModel>) {
        database.getMovieDao().insertIgnore(movies)
    }

    override suspend fun softInsertTv(tvShows: List<TvModel>) {
        database.getTvDao().insertIgnore(tvShows)
    }

    override suspend fun getMovies(collection: String): List<MovieModel>? {
        return database.getMovieDao().getMovies(collection)
    }

    override fun getPagingMovies(collection: String): DataSource.Factory<Int, ShowModelMinimal> {
        return database.getMovieDao().getPagedMovieSource(collection)
    }

    override fun getPagingTvShows(collection: String): DataSource.Factory<Int, ShowModelMinimal> {
        return database.getTvDao().getPagedTvShowSource(collection)
    }

    override suspend fun addMovieCollection(collectionModels: List<MovieCollectionModel>) {
        database.getMovieCollectionDao().insertReplace(collectionModels)
    }

    override suspend fun addTvCollection(collectionModels: List<TvCollectionModel>) {
        database.getTvCollectionDao().insertReplace(collectionModels)
    }

    override fun getMovieDetails(movieId: Long): LiveData<MovieModel> {
        return database.getMovieDao().getMovie(movieId)
    }

    override suspend fun insertMovieDetails(movie: MovieModel) {
        database.getMovieDao().insertReplace(movie)
    }

    override suspend fun updateMovieDetails(model: MovieModel) {
        database.getMovieDao().update(
            id = model.id,
            voteCount = model.voteCount,
            voteAvg = model.voteAvg,
            revenue = model.revenue,
            releaseData = model.releaseData,
            budget = model.budget,
            popularity = model.popularity,
            genres = model.genres,
            runtime = model.runtime,
            status = model.status,
            tagline = model.tagline
        )
    }

    override suspend fun updateMovieDetails(model: VideoApiModel, movieId: Long) {
        database.getMovieDao().update(movieId, model.key)
    }

    override suspend fun updateMovieDetails(casts: List<CastModel>, movieId: Long) {
        database.getMovieDao().update(movieId, casts)
    }
}