package com.sifat.slushflicks.ui.helper

import com.sifat.slushflicks.api.home.movie.model.MovieApiModel
import com.sifat.slushflicks.api.home.movie.model.MovieDetailsApiModel
import com.sifat.slushflicks.api.home.movie.model.MovieListApiModel
import com.sifat.slushflicks.model.GenreModel
import com.sifat.slushflicks.model.MovieCollectionModel
import com.sifat.slushflicks.model.MovieModel
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.ui.base.ListViewState.LOADING
import com.sifat.slushflicks.ui.base.ListViewState.VIEW
import com.sifat.slushflicks.ui.home.adapter.model.MovieListModel
import com.sifat.slushflicks.ui.state.MetaData
import com.sifat.slushflicks.utils.EMPTY_STRING
import com.sifat.slushflicks.utils.PAGE_SIZE
import com.sifat.slushflicks.utils.getListImageUrl

fun getMovieListLoadingModels(): List<MovieListModel> {
    val list = mutableListOf<MovieListModel>()
    for (count in 0..5) {
        list.add(MovieListModel(null, LOADING))
    }
    return list
}

/**
 * This conversion discard unnecessary data returned from api
 * */
fun getMovieList(
    movieApiModels: List<MovieApiModel>?,
    genres: Map<Long, String>
): List<MovieModel> {
    val movieModels = mutableListOf<MovieModel>()
    movieApiModels?.let {
        for (movie in movieApiModels) {
            val genresModels = getGenresModels(movie.genreIds, genres)
            val movieModel = MovieModel(
                id = movie.id,
                backdropPath = getListImageUrl(movie.backdropPath),
                overview = movie.overview,
                genres = genresModels,
                popularity = movie.popularity,
                posterPath = getListImageUrl(movie.posterPath),
                releaseData = movie.releaseDate ?: EMPTY_STRING,
                title = movie.title,
                voteAvg = movie.voteAverage,
                voteCount = movie.voteCount
            )
            movieModels.add(movieModel)
        }
    }
    return movieModels
}

/**
 * This conversion converts model to List models for view state
 * */
fun getMovieListModel(shows: List<ShowModelMinimal>?): List<MovieListModel> {
    val movieListModels = mutableListOf<MovieListModel>()
    shows?.let {
        for (movie in shows) {
            val movieListModel = MovieListModel(movie, VIEW)
            movieListModels.add(movieListModel)
        }
    }
    return movieListModels
}

fun getMovieMinimalModel(movies: List<MovieModel>?): List<ShowModelMinimal>? {
    if (movies.isNullOrEmpty()) return null
    val moviesMinimalList = mutableListOf<ShowModelMinimal>()
    for (movie in movies) {
        val movieModelMinimal = ShowModelMinimal(
            id = movie.id,
            overview = movie.overview,
            title = movie.title,
            genres = movie.genres,
            voteAvg = movie.voteAvg,
            backdropPath = movie.backdropPath
        )
        moviesMinimalList.add(movieModelMinimal)
    }
    return moviesMinimalList
}

fun getGenresModels(
    genreIds: List<Long>,
    genreMap: Map<Long, String>
): List<GenreModel> {
    val genres = mutableListOf<GenreModel>()
    for (id in genreIds) {
        val genre = GenreModel(
            id = id,
            name = genreMap[id] ?: EMPTY_STRING
        )
        genres.add(genre)
    }
    return genres
}

fun getMetaData(movieListApiModel: MovieListApiModel?): MetaData? {
    return movieListApiModel?.let { model ->
        MetaData(
            page = model.page,
            totalResult = model.totalResults,
            totalPage = model.totalPages
        )
    }
}

fun getCollectionModels(
    movies: List<MovieModel>,
    collection: String,
    page: Int
): List<MovieCollectionModel> {
    val collectionList = mutableListOf<MovieCollectionModel>()
    for (index in movies.indices) {
        val collectionModel = MovieCollectionModel(
            collection = collection,
            id = movies[index].id,
            index = ((page - 1) * PAGE_SIZE) + index
        )
        collectionList.add(collectionModel)
    }
    return collectionList
}

fun getMovieDetails(apiModel: MovieDetailsApiModel?): MovieModel? {
    return apiModel?.run {
        MovieModel(
            id = id,
            voteAvg = voteAverage,
            overview = overview,
            voteCount = voteCount,
            backdropPath = getListImageUrl(backdropPath),
            title = title,
            genres = genres,
            releaseData = releaseDate,
            posterPath = getListImageUrl(posterPath),
            popularity = popularity,
            budget = budget,
            revenue = revenue,
            tagline = tagline,
            status = status,
            runtime = runtime
        )
    }
}
