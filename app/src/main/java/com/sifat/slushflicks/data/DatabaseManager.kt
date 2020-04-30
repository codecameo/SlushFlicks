package com.sifat.slushflicks.data

import androidx.paging.DataSource
import com.sifat.slushflicks.model.GenreModel
import com.sifat.slushflicks.model.MovieCollectionModel
import com.sifat.slushflicks.model.MovieModel
import com.sifat.slushflicks.model.MovieModelMinimal

interface DatabaseManager {

    suspend fun saveGenre(genres: List<GenreModel>)

    suspend fun loadGenres(): List<GenreModel>

    suspend fun insertNewMovieCollection(
        collection: String,
        collectionModels: List<MovieCollectionModel>
    )

    suspend fun softInsertMovie(movies: List<MovieModel>)

    suspend fun getMovies(collection: String): List<MovieModel>?

    fun getPagingMovies(collection: String): DataSource.Factory<Int, MovieModelMinimal>

    suspend fun addMovieCollection(collectionModels: List<MovieCollectionModel>)
}