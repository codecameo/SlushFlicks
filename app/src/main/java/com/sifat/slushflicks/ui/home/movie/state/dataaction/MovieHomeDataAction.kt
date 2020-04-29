package com.sifat.slushflicks.ui.home.movie.state.dataaction

import com.sifat.slushflicks.model.CollectionModel
import com.sifat.slushflicks.ui.state.DataState

sealed class MovieHomeDataAction {
    class MovieCollectionDataAction(val dataState: DataState<List<CollectionModel>>) :
        MovieHomeDataAction()
}