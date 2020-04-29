package com.sifat.slushflicks.ui.home.movie.state.viewaction

import com.sifat.slushflicks.ui.home.adapter.model.MovieListModel
import com.sifat.slushflicks.ui.state.ViewState

/**
 * View should listen to this action
 * and can take precise action base on the action type
 * (ex: showing shimmer or asyc loading for that particular view part instead of global loading)
 * */
sealed class MovieListViewAction {
    class FetchMovieListViewAction(val viewState: ViewState<List<MovieListModel>>) :
        MovieListViewAction()
}