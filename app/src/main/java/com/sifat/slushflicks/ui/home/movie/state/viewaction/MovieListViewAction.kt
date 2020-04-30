package com.sifat.slushflicks.ui.home.movie.state.viewaction

import androidx.paging.PagedList
import com.sifat.slushflicks.model.MovieModelMinimal
import com.sifat.slushflicks.ui.state.ViewState

/**
 * View should listen to this action
 * and can take precise action base on the action type
 * (ex: showing shimmer or asyc loading for that particular view part instead of global loading)
 * */
sealed class MovieListViewAction {
    class FetchCacheMovieListViewAction(val viewState: ViewState<PagedList<MovieModelMinimal>>) :
        MovieListViewAction()

    class FetchNetworkMovieListViewAction(val viewState: ViewState<Int>) :
        MovieListViewAction()
}