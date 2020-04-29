package com.sifat.slushflicks.ui.home.movie.state

import com.sifat.slushflicks.ui.home.adapter.model.MovieListModel
import com.sifat.slushflicks.ui.state.ViewState

sealed class MovieListViewAction {
    class FetchMovieListViewAction(val viewState: ViewState<List<MovieListModel>>) :
        MovieListViewAction()
}