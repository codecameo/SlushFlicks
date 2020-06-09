package com.sifat.slushflicks.ui.helper

import com.sifat.slushflicks.api.home.movie.model.MovieApiModel
import com.sifat.slushflicks.api.home.movie.model.MovieDetailsApiModel
import com.sifat.slushflicks.api.home.movie.model.MovieListApiModel
import com.sifat.slushflicks.model.MovieCollectionModel
import com.sifat.slushflicks.model.MovieModel
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.ui.state.MetaData
import com.sifat.slushflicks.utils.EMPTY_STRING
import com.sifat.slushflicks.utils.INVALID_PAGE
import com.sifat.slushflicks.utils.PAGE_SIZE

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
            val movieModel = movie.run {
                MovieModel(
                    id = id,
                    backdropPath = backdropPath ?: EMPTY_STRING,
                    overview = overview,
                    genres = genresModels,
                    popularity = popularity,
                    posterPath = posterPath ?: EMPTY_STRING,
                    releaseData = releaseDate ?: EMPTY_STRING,
                    title = title,
                    voteAvg = voteAverage,
                    voteCount = voteCount
                )
            }
            movieModels.add(movieModel)
        }
    }
    return movieModels
}

fun getMovieMinimalModel(movies: List<MovieModel>?): List<ShowModelMinimal>? {
    if (movies.isNullOrEmpty()) return null
    val moviesMinimalList = mutableListOf<ShowModelMinimal>()
    for (movie in movies) {
        val movieModelMinimal = movie.run {
            ShowModelMinimal(
                id = movie.id,
                overview = overview,
                title = title,
                genres = genres,
                voteAvg = voteAvg,
                backdropPath = backdropPath
            )
        }
        moviesMinimalList.add(movieModelMinimal)
    }
    return moviesMinimalList
}

fun getMovieMinimalApiModel(
    movie: MovieApiModel,
    genreMap: Map<Long, String>
): ShowModelMinimal {
    val genresModels = getGenresModels(movie.genreIds, genreMap)
    return ShowModelMinimal(
        id = movie.id,
        overview = movie.overview,
        title = movie.title,
        genres = genresModels,
        voteAvg = movie.voteAverage,
        backdropPath = movie.backdropPath ?: EMPTY_STRING
    )
}

fun getMetaData(movieListApiModel: MovieListApiModel?): MetaData? {
    return movieListApiModel?.run {
        MetaData(
            page = page,
            totalResult = totalResults,
            totalPage = totalPages
        )
    }
}

fun getCollectionModels(
    movies: List<MovieModel>,
    collection: String,
    page: Int
): List<MovieCollectionModel> {
    if (page < 1) throw RuntimeException(String.format(INVALID_PAGE, page))
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
            backdropPath = backdropPath ?: EMPTY_STRING,
            title = title,
            genres = genres,
            releaseData = releaseDate ?: EMPTY_STRING,
            posterPath = posterPath ?: EMPTY_STRING,
            popularity = popularity,
            budget = budget,
            revenue = revenue,
            tagline = tagline,
            status = status,
            runtime = runtime
        )
    }
}

/*fun getMovieMinimalApiModels(
    movies: List<MovieApiModel>?,
    genreMap: Map<Long, String>
): List<ShowModelMinimal> {
    if (movies.isNullOrEmpty()) return emptyList()
    val moviesMinimalList = mutableListOf<ShowModelMinimal>()
    for (movie in movies) {
        moviesMinimalList.add(getMovieMinimalApiModel(movie, genreMap))
    }
    return moviesMinimalList
}*/
