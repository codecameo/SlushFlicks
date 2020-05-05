package com.sifat.slushflicks.ui.home.movie.state.dataaction

import androidx.paging.PagedList
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.ui.state.DataState

sealed class MovieListDataAction {
    class FetchCacheMovieListDataAction(val dataState: DataState<PagedList<ShowModelMinimal>>) :
        MovieListDataAction()

    class FetchNetworkMovieListDataAction(val dataState: DataState<Int>) :
        MovieListDataAction()
}