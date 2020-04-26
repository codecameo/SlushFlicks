package com.example.slushflicks.ui.home.movie.state

import com.example.slushflicks.model.MovieModel
import com.example.slushflicks.ui.state.DataState

sealed class MovieListDataAction {
    class FetchMovieListDataAction(val dataState: DataState<List<MovieModel>>) :
        MovieListDataAction()
}