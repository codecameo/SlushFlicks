package com.sifat.slushflicks.ui.home.tvshow.state.viewaction

import androidx.paging.PagedList
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.ui.state.ViewState

/**
 * View should listen to this action
 * and can take precise action base on the action type
 * (ex: showing shimmer or asyc loading for that particular view part instead of global loading)
 * */
sealed class TvListViewAction {
    class FetchCacheTvListViewAction(val viewState: ViewState<PagedList<ShowModelMinimal>>) :
        TvListViewAction()

    class FetchNetworkTvListViewAction(val viewState: ViewState<Int>) :
        TvListViewAction()
}