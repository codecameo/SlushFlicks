package com.sifat.slushflicks.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.sifat.slushflicks.api.details.model.VideoApiModel
import com.sifat.slushflicks.model.*

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

    fun getMovieDetails(movieId: Long): LiveData<MovieModel>

    suspend fun insertMovieDetails(movie: MovieModel)

    suspend fun updateMovieDetails(model: MovieModel)

    suspend fun updateMovieDetails(model: VideoApiModel, movieId: Long)

    suspend fun updateMovieDetails(casts: List<CastModel>, movieId: Long)
}