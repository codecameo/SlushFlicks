package com.sifat.slushflicks.ui.home.tvshow.state.viewstate

import androidx.paging.PagedList
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.ui.base.BaseViewState
import com.sifat.slushflicks.ui.home.tvshow.state.viewaction.TvListViewAction

class TvListViewState : BaseViewState<TvListViewAction>() {
    var currentPage = 0
    var totalPage = 0
    var showList: PagedList<ShowModelMinimal>? = null
    fun nextPage() = currentPage + 1
}