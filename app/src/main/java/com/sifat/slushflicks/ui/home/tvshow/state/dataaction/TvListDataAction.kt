package com.sifat.slushflicks.ui.home.tvshow.state.dataaction

import androidx.paging.PagedList
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.ui.state.DataState

sealed class TvListDataAction {
    class FetchCacheTvListDataAction(val dataState: DataState<PagedList<ShowModelMinimal>>) :
        TvListDataAction()

    class FetchNetworkTvListDataAction(val dataState: DataState<Int>) :
        TvListDataAction()
}