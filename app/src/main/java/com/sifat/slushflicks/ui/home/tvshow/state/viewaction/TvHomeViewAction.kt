package com.sifat.slushflicks.ui.home.tvshow.state.viewaction

import com.sifat.slushflicks.ui.home.adapter.model.CollectionListModel
import com.sifat.slushflicks.ui.state.ViewState

sealed class TvHomeViewAction {
    class TvCollectionViewAction(val viewState: ViewState<List<CollectionListModel>>) :
        TvHomeViewAction()

    class CollectionContainerUpdateViewAction(val viewState: ViewState<String>) :
        TvHomeViewAction()
}