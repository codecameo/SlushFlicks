package com.sifat.slushflicks.ui.home.tvshow.state.event

import com.sifat.slushflicks.model.CollectionModel

sealed class TvHomeEventState {
    class TvCollectionEvent(val forceUpdate: Boolean = true) : TvHomeEventState()
    class TvCollectionClickEvent(val index: Int, val collectionModel: CollectionModel) :
        TvHomeEventState()
}