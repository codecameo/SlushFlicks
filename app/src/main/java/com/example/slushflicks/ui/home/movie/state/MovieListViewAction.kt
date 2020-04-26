package com.example.slushflicks.ui.home.movie.state

import com.example.slushflicks.ui.home.adapter.model.MovieListModel
import com.example.slushflicks.ui.state.ViewState

sealed class MovieListViewAction {
    class FetchMovieListViewAction(val viewState: ViewState<List<MovieListModel>>) : MovieListViewAction()
}