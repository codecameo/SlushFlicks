package com.sifat.slushflicks.ui.home.search.state.viewaction

import androidx.paging.PagedList
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.ui.home.search.viewmodel.SearchViewModel
import com.sifat.slushflicks.ui.state.ViewState
import com.sifat.slushflicks.utils.ShowType

sealed class SearchViewAction {
    class SearchShowViewAction(val viewState: ViewState<PagedList<ShowModelMinimal>>) :
        SearchViewAction()

    class ResultFoundViewAction(val viewState: ViewState<Boolean>) :
        SearchViewAction()

    class UpdateShowTypeViewAction(val viewState: ViewState<ShowType>) :
        SearchViewAction()

    class UpdateInitialViewAction(val viewState: ViewState<SearchViewModel.InitialData>) :
        SearchViewAction()
}