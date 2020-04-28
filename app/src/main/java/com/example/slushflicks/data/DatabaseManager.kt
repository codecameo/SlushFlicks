package com.example.slushflicks.data

import com.example.slushflicks.model.GenreModel
import com.example.slushflicks.model.MovieCollectionModel
import com.example.slushflicks.model.MovieModel

interface DatabaseManager {

    suspend fun saveGenre(genres: List<GenreModel>)

    suspend fun loadGenres(): List<GenreModel>

    suspend fun insertNewMovieCollection(
        collection: String,
        collectionModels: List<MovieCollectionModel>
    )

    suspend fun softInsertMovie(movies: List<MovieModel>)

    suspend fun getMovies(collection: String): List<MovieModel>?

    suspend fun addMovieCollection(collectionModels: List<MovieCollectionModel>)
}