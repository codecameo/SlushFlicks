package com.example.slushflicks.data.impl

import com.example.slushflicks.data.DatabaseManager
import com.example.slushflicks.db.AppDatabase
import com.example.slushflicks.di.app.AppScope
import com.example.slushflicks.model.GenreModel
import com.example.slushflicks.model.MovieCollectionModel
import com.example.slushflicks.model.MovieModel
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

    override suspend fun addMovieCollection(collectionModels: List<MovieCollectionModel>) {
        database.getCollectionDao().insertReplace(collectionModels)
    }
}