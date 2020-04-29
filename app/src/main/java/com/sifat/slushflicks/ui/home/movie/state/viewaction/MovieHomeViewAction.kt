package com.sifat.slushflicks.ui.home.movie.state.viewaction

import com.sifat.slushflicks.ui.home.adapter.model.CollectionListModel
import com.sifat.slushflicks.ui.state.ViewState

/**
 * View should listen to this action
 * and can take precise action base on the action type
 * (ex: showing shimmer or asyc loading for that particular view part instead of global loading)
 * */
sealed class MovieHomeViewAction {
    class MovieCollectionViewAction(val viewState: ViewState<List<CollectionListModel>>) :
        MovieHomeViewAction()

    class CollectionContainerUpdateViewAction(val viewState: ViewState<String>) :
        MovieHomeViewAction()
}