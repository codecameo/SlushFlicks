package com.sifat.slushflicks.ui.home.search.state.dataaction

import androidx.paging.PagedList
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.ui.state.DataState

sealed class SearchDataAction {
    class SearchMovieDataAction(val dataState: DataState<PagedList<ShowModelMinimal>>) :
        SearchDataAction()

    class SearchTvShowDataAction(val dataState: DataState<PagedList<ShowModelMinimal>>) :
        SearchDataAction()
}