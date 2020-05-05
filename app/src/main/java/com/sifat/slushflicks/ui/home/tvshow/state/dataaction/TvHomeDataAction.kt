package com.sifat.slushflicks.ui.home.tvshow.state.dataaction

import com.sifat.slushflicks.model.CollectionModel
import com.sifat.slushflicks.ui.state.DataState

sealed class TvHomeDataAction {
    class TvCollectionDataAction(val dataState: DataState<List<CollectionModel>>) :
        TvHomeDataAction()
}