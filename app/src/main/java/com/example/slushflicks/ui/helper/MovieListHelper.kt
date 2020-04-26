package com.example.slushflicks.ui.helper

import com.example.slushflicks.api.home.movie.MovieApiModel
import com.example.slushflicks.api.home.movie.MovieListApiModel
import com.example.slushflicks.model.GenreModel
import com.example.slushflicks.ui.base.ListViewState
import com.example.slushflicks.ui.base.ListViewState.LOADING
import com.example.slushflicks.ui.home.adapter.model.MovieListModel
import com.example.slushflicks.model.MovieModel
import com.example.slushflicks.ui.state.MetaData
import com.example.slushflicks.utils.getListImageUrl

fun getMovieListLoadingModels(): List<MovieListModel> {
    val list = mutableListOf<MovieListModel>()
    for (count in 0..5) {
        list.add(MovieListModel(null, LOADING))
    }
    return list
}

/**
 * This conversion converts model to List models for view state
 * */
fun getMovieListModel(movies: List<MovieModel>): List<MovieListModel> {
    val movieListModels = mutableListOf<MovieListModel>()
    for (movie in movies) {
        val movieListModel = MovieListModel(movie, ListViewState.VIEW)
        movieListModels.add(movieListModel)
    }
    return movieListModels
}

/**
 * This conversion discard unnecessary data returned from api
 * */
fun getMovieList(movieApiModels: List<MovieApiModel>?): List<MovieModel> {
    val movieModels = mutableListOf<MovieModel>()
    movieApiModels?.let {
        for (movie in movieApiModels) {
            val genresModels = getGenresModels(movie.genreIds)
            val movieModel = MovieModel(
                id = movie.id,
                backdropPath = getListImageUrl(movie.backdropPath),
                overview = movie.overview,
                genres = genresModels,
                popularity = movie.popularity,
                posterPath = getListImageUrl(movie.posterPath),
                releaseData = movie.releaseDate,
                title = movie.title,
                voteAvg = movie.voteAverage,
                voteCount = movie.voteCount
            )
            movieModels.add(movieModel)
        }
    }
    return movieModels
}

fun getGenresModels(genreIds: List<Long>): List<GenreModel> {
    val genres = mutableListOf<GenreModel>()
    for (id in genreIds) {
        val genre = GenreModel(id = id)
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
