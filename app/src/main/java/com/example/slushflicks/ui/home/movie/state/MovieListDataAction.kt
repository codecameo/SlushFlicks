package com.example.slushflicks.ui.home.movie.state

import com.example.slushflicks.ui.home.adapter.model.MovieListModel
import com.example.slushflicks.ui.model.MovieModel
import com.example.slushflicks.ui.state.DataState
import com.example.slushflicks.ui.state.ViewState

sealed class MovieListDataAction {
    class FetchMovieListDataAction(val dataState: DataState<List<MovieModel>>) : MovieListDataAction()
}