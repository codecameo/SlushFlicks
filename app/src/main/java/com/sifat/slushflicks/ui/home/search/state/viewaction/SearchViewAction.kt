package com.sifat.slushflicks.ui.home.search.state.viewaction

import androidx.paging.PagedList
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.ui.home.search.state.viewstate.QueryModel
import com.sifat.slushflicks.ui.state.ViewState
import com.sifat.slushflicks.utils.ShowType

sealed class SearchViewAction {
    class SearchShowViewAction(val viewState: ViewState<PagedList<ShowModelMinimal>>) :
        SearchViewAction()

    class ResultFoundViewAction(val viewState: ViewState<Boolean>) :
        SearchViewAction()

    class UpdateShowTypeViewAction(val viewState: ViewState<ShowType>) :
        SearchViewAction()

    class UpdateQueryViewAction(val viewState: ViewState<QueryModel>) :
        SearchViewAction()
}