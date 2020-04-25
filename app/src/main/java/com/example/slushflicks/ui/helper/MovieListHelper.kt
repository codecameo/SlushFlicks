package com.example.slushflicks.ui.helper

import com.example.slushflicks.ui.base.ListViewState
import com.example.slushflicks.ui.base.ListViewState.LOADING
import com.example.slushflicks.ui.home.adapter.model.MovieListModel
import com.example.slushflicks.ui.model.MovieModel

fun getMovieListLoadingModels(): List<MovieListModel> {
    val list = mutableListOf<MovieListModel>()
    for (count in 0..5) {
        list.add(MovieListModel(null, LOADING))
    }
    return list
}

fun getViewMovieList(movies: List<MovieModel>): List<MovieListModel> {
    val movieListModels = mutableListOf<MovieListModel>()
    for (movie in movies) {
        val movieListModel = MovieListModel(movie, ListViewState.VIEW)
        movieListModels.add(movieListModel)
    }
    return movieListModels
}