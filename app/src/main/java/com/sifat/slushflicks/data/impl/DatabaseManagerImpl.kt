package com.sifat.slushflicks.data.impl

import androidx.paging.DataSource
import com.sifat.slushflicks.data.DatabaseManager
import com.sifat.slushflicks.db.AppDatabase
import com.sifat.slushflicks.di.app.AppScope
import com.sifat.slushflicks.model.GenreModel
import com.sifat.slushflicks.model.MovieCollectionModel
import com.sifat.slushflicks.model.MovieModel
import com.sifat.slushflicks.model.MovieModelMinimal
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
        database.getCollectionDao().saveMovieCollectionList(collection, collectionModels)
    }

    override suspend fun softInsertMovie(movies: List<MovieModel>) {
        database.getMovieDao().insertIgnore(movies)
    }

    override suspend fun getMovies(collection: String): List<MovieModel>? {
        return database.getMovieDao().getMovies(collection)
    }

    override fun getPagingMovies(collection: String): DataSource.Factory<Int, MovieModelMinimal> {
        return database.getMovieDao().getPagedMovieSource(collection)
    }

    override suspend fun addMovieCollection(collectionModels: List<MovieCollectionModel>) {
        database.getCollectionDao().insertReplace(collectionModels)
    }
}